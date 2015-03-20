package com.fs.app.tpascheda.mvc;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;

import com.fs.app.tpahomeca.bo.TPAPlanAuthorizationEntitlementBean;
import com.fs.app.tpahomeca.wswraper.TPAHomeCAProxy;
import com.fs.app.tpascheda.data.SchedAArchiveEntry;
import com.fs.app.tpascheda.exceptions.NotAuthorized;
import com.fs.is.cmd.Command;
import com.fs.is.cmd.ServiceClient;
import com.fs.is.configuration.ConfigurationFactory;
import com.fs.is.configuration.IConfiguration;
import com.fs.is.ids.DataAccessRequest;
import com.fs.is.ids.DataAccessResponse;
import com.fs.is.log.LOG;
import com.fs.is.state.IState;
import com.fs.is.state.StateFactory;
import com.fs.is.tags.handler.IBusinessInteraction;
import com.ing.ia3.tpaservices.archiveWebservice.ArchiveDocumentSearchHits;
import com.ing.ia3.tpaservices.archiveWebservice.ArchiveDocumentSearchRequest;
import com.ing.ia3.tpaservices.archiveWebservice.ArchiveDocumentSearchResponse;
import com.ing.ia3.tpaservices.archiveWebservice.ArchiveWebserviceSoapBindingStub;
import com.ing.ia3.tpaservices.archiveWebservice.BetweenAndNotBetween;
import com.ing.ia3.tpaservices.archiveWebservice.ClientReturnURL;
import com.ing.ia3.tpaservices.archiveWebservice.CriteriaValue;
import com.ing.ia3.tpaservices.archiveWebservice.DocumentColumnsAndValues;
import com.ing.ia3.tpaservices.archiveWebservice.Operator;
import com.ing.ia3.tpaservices.archiveWebservice.RequestType;
import com.ing.ia3.tpaservices.archiveWebservice.SearchCriteria;
import com.ing.ia3.tpaservices.archiveWebservice.SearchCriteriaType;

public class SelectArch5500_PreDisplay 
    implements IBusinessInteraction {

  protected IState state;

  //protected HttpServletRequest request;
  protected String sconfig = "tpascheda.xml";
  protected String slogin_id = null;
  protected String saccess_level = null;
  protected String saccessor_type = null;

  //filter criteria
  protected String Start_date;
  protected String End_date;
  protected String Plannum;

  protected String Old_Start_date;
  protected String Old_End_date;
  protected String Old_Plannum;

  protected TreeSet archlist;
  protected SimpleDateFormat df;
  
  protected String authDirName = "";
  protected String smUser = "";

  protected String userType = null;
  protected String userID = null;
  //sort paging stuff
  private Integer mypage;
  private String mysort;
  private String planAccessType = null; 
  public SelectArch5500_PreDisplay() {
    df = new SimpleDateFormat("MM/dd/yy");

  }

  public void interact(HttpServletRequest request) throws Exception {

    state = (IState) StateFactory.getState(request);

    authDirName = request.getHeader("sm_authdirname");
    
    smUser =(String)request.getHeader("sm_user");
    //this.request = req;
    boolean firstVisitThisSession;
    boolean bgotostart = false;
    //Initial setup
    SelectArch5500_Bean bean = (SelectArch5500_Bean) request.getAttribute(
        "FormBean");
    String s = (String) state.getAttribute("testit");

    boolean bDescOrderInd = false;

    Boolean b = (Boolean) state.getAttribute("SCHEDADESCORDERIND");
    if (b != null) {
      bDescOrderInd = b.booleanValue();
    }
    // get info passed in

    Start_date = (String) state.getAttribute("fromdate");
    End_date = (String) state.getAttribute("todate");
    Plannum = (String) state.getAttribute("displan");

    Old_Start_date = (String) state.getAttribute("oldfromdate");
    Old_End_date = (String) state.getAttribute("oldtodate");
    Old_Plannum = (String) state.getAttribute("olddisplan");

    archlist = (TreeSet) state.getAttribute("sschedalist");
    Integer mypage = (Integer) state.getAttribute("SCHEDAPAGE");

    boolean bgetarchives = false;
    String sreset = (String) request.getAttribute("Reset");
    if (sreset != null)
    {
      if (sreset.compareToIgnoreCase("Yes") == 0)
      {
         bgetarchives = true;
      }
    }
    if (archlist == null) {
      bgetarchives = true;
    }
    else
    if (Start_date.compareToIgnoreCase(Old_Start_date) != 0) {
      bgetarchives = true;
    }

    else
    if (End_date.compareToIgnoreCase(Old_End_date) != 0) {
      bgetarchives = true;
    }
    else
    if (Plannum.compareToIgnoreCase(Old_Plannum) != 0) {
      bgetarchives = true;
    }

    if (bgetarchives) {
      //get the list
      //reset the parameters
      this.GetDocumentsForTPACodes(request);
      bgotostart = true;
      Old_Start_date = Start_date;
      Old_End_date = End_date;
      Old_Plannum = Plannum;
      state.setAttribute("oldfromdate", Old_Start_date);
      state.setAttribute("oldtodate", Old_End_date);
      state.setAttribute("olddisplan", Old_Plannum);
      state.setAttribute("SCHEDADESCORDERIND", new Boolean(false));
      bDescOrderInd = false;
      state.setAttribute("SCHEDAPAGE", new Integer(0));
      state.setAttribute("SCHEDASORT", "Run");
      state.setAttribute("sschedalist", archlist);
    }

    String mysort;
    mysort = (String) state.getAttribute("SCHEDASORT");
    if (mysort == null) {
      mysort = "Run";
    }
    mypage = (Integer) state.getAttribute("SCHEDAPAGE");
    if (mysort == null) {
      mysort = "Run";
    }
    Double D = null;
    String sarch =  (String) request.getAttribute("selArchGo");
    if (sarch != null) {
      D = new Double(sarch);
    }

    //what did the user click on..
    //sort by ssn
    if (request.getParameter("sortdate.x") != null) {
      if (mysort.compareToIgnoreCase("Run") != 0) {
        state.setAttribute("SCHEDASORT", "Run");
        mysort = "Run";
        mypage = new Integer(0);
        bgotostart = true;
        bDescOrderInd = false;
        Comparator c = new TPASchedARunComparator(bDescOrderInd);
        TreeSet tempspartlist = new TreeSet(c);
        tempspartlist.addAll(archlist);
        archlist = tempspartlist;
        state.setAttribute("sschedalist", archlist);

      }
      else {
        //User has clicked same sort column again,
        //Reverse the sort.

        bDescOrderInd = !bDescOrderInd;

        mypage = new Integer(0);
        bgotostart = true;
        Comparator c = new TPASchedARunComparator(bDescOrderInd);
        TreeSet tempspartlist = new TreeSet(c);
        tempspartlist.addAll(archlist);
        archlist = tempspartlist;
        state.setAttribute("spartlist", archlist);
      }
    }

    if (request.getParameter("sortNum.x") != null) {
      if (mysort.compareToIgnoreCase("Num") != 0) {
        state.setAttribute("SCHEDASORT", "Num");
        mysort = "Num";
        mypage = new Integer(0);
        bgotostart = true;
        bDescOrderInd = false;
        Comparator c = new TPASchedANumComparator(bDescOrderInd);
        TreeSet tempspartlist = new TreeSet(c);
        tempspartlist.addAll(archlist);
        archlist = tempspartlist;
        state.setAttribute("sschedalist", archlist);

      }
      else {
        //User has clicked same sort column again,
        //Reverse the sort.

        bDescOrderInd = !bDescOrderInd;

        mypage = new Integer(0);
        bgotostart = true;
        Comparator c = new TPASchedANumComparator(bDescOrderInd);
        TreeSet tempspartlist = new TreeSet(c);
        tempspartlist.addAll(archlist);
        archlist = tempspartlist;
        state.setAttribute("spartlist", archlist);
      }
    }

    if (mypage == null) {
      mypage = new Integer(0);
      bgotostart = true;

    }
    if (request.getParameter("nxtpage.x") != null) {
      int ip = mypage.intValue() + 1;
      mypage = new Integer(ip);
    }
    if (request.getParameter("prevpage.x") != null) {
      int ip = mypage.intValue() - 1;
      if (ip < 0) {
        ip = 0;
      }
      mypage = new Integer(ip);
    }

    String strPageNum = request.getParameter("jumpPageNum");
    if (strPageNum != null) {
      mypage = new Integer(strPageNum);
    }

    bean.setSort(mysort);
    TPAArchPageInfo pi = new TPAArchPageInfo();
    pi.setGotoStart(bgotostart);
    pi.setPageNum(mypage);
    pi.setList(archlist);
    //*************************************************
    pi.Calculate(); //derives "the" page in pi object.
    //*************************************************

    mypage = pi.getPageNum();
    bean.setNumOfPages(String.valueOf(pi.numOfPages));
    Vector V = pi.getPageList();
    int i = V.size();
    FillfromSybase(V);
    if (i > 0) {
      if (D == null) {
        SchedAArchiveEntry sa = (SchedAArchiveEntry) V.elementAt(0);
        D = sa.getLastViewedId();
      }
    }
    else {
      D = new Double( -1);
    }
    bean.setSelArch(D.doubleValue());
    bean.setPageList(V);
    bean.setHasNext(pi.hasNext());
    bean.setHasPrev(pi.hasPrev());
    state.setAttribute("SCHEDAPAGE", mypage);
    bean.setJumpPageNum(String.valueOf(mypage));
    state.setAttribute("testit", "test");
    state.setAttribute("SCHEDADESCORDERIND", new Boolean(bDescOrderInd));



  } //End Interact

  /**
   * FillfromSybase
   *
   * @param V Vector
   */
  private void FillfromSybase(Vector V) {
    for (int i = 0; i < V.size(); i++) {
      SchedAArchiveEntry sa = (SchedAArchiveEntry) V.elementAt(i);
      if (!sa.getBentoSybase()) {
        //SchedAArchiveEntry mysa =  getSybaseInfo(sa);
        //sa.setPlan_Name(mysa.getPlan_Name());
        // sa.setPlan_Name(mysa.getPlan_Name());
        getSybaseInfo(sa);
      }
      if (!sa.getBentoSybase()) {
        InsertSybase(sa);
        getSybaseInfo(sa);
      }

    }

  }

  /**
   * InsertSybase
   *
   * @param sa SchedAArchiveEntry
   */
  private void InsertSybase(SchedAArchiveEntry sa) {
    try {
      ServiceClient serviceClient = new ServiceClient(sconfig);

      // set up request

      Map params = new HashMap();
      params.put("SA", sa);
      params.put("XML_FILE", sconfig);
      DataAccessRequest request = new DataAccessRequest(params);

      // set up command
      Command cmd = new Command();
      cmd.setTarget("IDS");
      cmd.setConfig(sconfig);
      cmd.setRequest(request);
      cmd.setVerb("InsertArchiveExtraInfo");
      cmd = serviceClient.processCommand(cmd);
      DataAccessResponse resp = (DataAccessResponse) cmd.getResponse();
      int size = resp.getNumberOfRecords();
      // LOG.DEBUG (sconfig, "Number of Cases " + size);

    }

    catch (Throwable th) {
      LOG.ERROR(sconfig, th.toString());
      //return null;

    }

  }

  /**
   * getSybaseInfo
   *
   * @param sa SchedAArchiveEntry
   * @return boolean
   */
//private SchedAArchiveEntry getSybaseInfo(SchedAArchiveEntry sa) {
  private void getSybaseInfo(SchedAArchiveEntry sa) {
    try {
      ServiceClient serviceClient = new ServiceClient(sconfig);

      // set up request

      Map params = new HashMap();
      params.put("SA", sa);
      params.put("XML_FILE", sconfig);
      DataAccessRequest request = new DataAccessRequest(params);

      // set up command
      Command cmd = new Command();
      cmd.setTarget("IDS");
      cmd.setConfig(sconfig);
      cmd.setRequest(request);
      cmd.setVerb("GetArchiveExtraInfo");
      cmd = serviceClient.processCommand(cmd);
      DataAccessResponse resp = (DataAccessResponse) cmd.getResponse();
      int size = resp.getNumberOfRecords();
      // LOG.DEBUG (sconfig, "Number of Cases " + size);

      if (size > 0) {
        Map[] maps = resp.getRecords();
        for (int z = 0; z < size; z++) {
          Map myRow = maps[z];

          String tb = (String) myRow.get("type");

          if (tb != null) {
            if (tb.compareToIgnoreCase("C") == 0) {
              sa.setPlan_Name( (String) myRow.get("PLAN_NAME"));
            }

            if (tb != null) {
              if (tb.compareToIgnoreCase("A") == 0) {
                sa.setLastViewedId( (Double) myRow.get("scheda_last_viewed_id"));
                Date d = (Date) myRow.get("Last_Viewed_Date");
                if (d != null) {
                  sa.setLastViewedDate(formatfordispay(d));
                }
                sa.setBentoSybase(true);
              }
            }

          }
        }
      }
    }
    catch (Throwable th) {
      LOG.ERROR(sconfig, th.toString());
      //return null;

    }

    //return sa;
  }

  /**
   * formatfordispay
   *
   * @param d Date
   * @return String
   */
  private String formatfordispay(Date d) 
  {
    return df.format(d);
  }
  
  private Hashtable getTPACodesFromList(HttpServletRequest request) throws NotAuthorized
  {
  	Hashtable returnHashtable = new Hashtable();
  	Vector userTPAEntitlement = null;
  	
  		if(state.getAttribute("getUserTPAEntitlement") != null)
  			userTPAEntitlement = (Vector)state.getAttribute("getUserTPAEntitlement");
  		if(userTPAEntitlement != null)
  		{
  			Iterator ite  = userTPAEntitlement.iterator();
  			while(ite.hasNext())
  			{
  				TPAPlanAuthorizationEntitlementBean actBean = (TPAPlanAuthorizationEntitlementBean)ite.next();
  				this.userType = actBean.getEntryType();
  				this.userID = actBean.getUserID();
  				//this.planAccessType = actBean.getPlanAccessType();
  				returnHashtable.put(actBean.getTpaCode(),actBean.getPlanAccessType());  			
  			}
  		}
  		
	
  	
  	return returnHashtable;
  }
  protected boolean GetDocumentsForTPACodesFromArchiveServlet(HttpServletRequest request) throws Exception 
  {
    //For each TPA Code retrieve, call the Archive Serivlet and get
    //all documents for that TPA Code
    //int numRecs = vTPA_Code.size();

    Hashtable clist = this.getTPACodesFromList(request);
    //Use the following when it's for real - iterate through all of the
    //TPA Codes
   // int numRecs = 1;
    int numRecs = clist.size();
    Vector templist = null;
    Comparator c = new TPASchedARunComparator(false);
    archlist = new TreeSet(c);
    Enumeration enumeration = clist.keys();
    while(enumeration.hasMoreElements())
    {	
    	String tpa_code = (String) enumeration.nextElement(); 
    	System.out.println("TPA Code"+tpa_code);
    	this.planAccessType  = (String)clist.get(tpa_code);
    	System.out.println("planAccessType  "+planAccessType);
    	Vector planAccessExceptionTable = null;
    	if(this.planAccessType.equalsIgnoreCase("SP")|| this.planAccessType.equalsIgnoreCase("EX"))
    	{
    		planAccessExceptionTable = getPlanAccessException(request,tpa_code);
    		System.out.println("Web Service Executed ");
    	}
    	
      try 
	  {
        boolean bValidated = true;
        String sErrMsg = "";
        String sArchRole = "ScheduleASupport";
        String sArchFolder = "TPA";
        Hashtable hRequest = new Hashtable();

        hRequest.put("ROLE", sArchRole);
        hRequest.put("FOLDER", sArchFolder);

        //LOG.DEBUG(sconfig, "Role: " + sArchRole + ", Folder: " + sArchFolder);

        Hashtable hSearchCrit = new Hashtable();
        String parm = "";
        String sParm[] = new String[4];
        if(! userType.equalsIgnoreCase("Internal"))
        {
        	//String parm = "PLAN_NUMBER";
        	parm = "TPA_CODE";        	
        	//sParm[0] = (String) vTPA_Code.get(j);
        	//sParm[0] = "0000";
        	sParm[0] = tpa_code;        	
        	// sParm[0] = "1719";
        	sParm[1] = "eq";
        	sParm[2] = "string";
        	hSearchCrit.put(parm, sParm);
        }
        else
        {
        	 parm = "Receiver_Type";
             sParm = new String[4];
             sParm[0] = "T";
             sParm[1] = "eq";
             sParm[2] = "string";
             hSearchCrit.put(parm, sParm);
        }

        //parm = "Run_Date";
        parm = "Report_End_Date";
        sParm = new String[4];
        sParm[0] = Start_date;
        sParm[1] = "between";
        sParm[2] = "date";
        sParm[3] = End_date + "betweenField";
        hSearchCrit.put(parm, sParm);

        parm = "View_Indicator";
        sParm = new String[4];
        // sParm[0] = (String) vTPA_Code.get(j);
        sParm[0] = "Y";
        sParm[1] = "eq";
        sParm[2] = "string";
        hSearchCrit.put(parm, sParm);

        if (Plannum != null) {
          if (Plannum.trim().length() > 0) {
          	
            parm = "Plan_Number";
            sParm = new String[4];
            // sParm[0] = (String) vTPA_Code.get(j);
            sParm[0] = Plannum.toUpperCase();           
            sParm[1] = "like";
            sParm[2] = "string";
            hSearchCrit.put(parm, sParm);
          }
        }
        hRequest.put("SEARCH", hSearchCrit);
        ServiceClient serviceClient = new ServiceClient();
        Command command = new Command();
        command.setVerb("GetResultsSQL");
        command.setTarget("archive");
        command.setRequest(hRequest);
        command.setConfig(sconfig);
        command = serviceClient.processCommand(command);
        templist = (Vector) command.getResponse();

        if (templist == null) {
          //LOG.DEBUG(sconfig, "Back From archive, null list");
          return false;
        }
        else {
          LOG.DEBUG(sconfig, "Back From archive, count is " + templist.size());
        }
      }
      catch (Exception SE) 
	  {
        if (SE.getMessage().equals(
            "java.lang.ClassCastException: java.lang.String")) 
        {
          LOG.ERROR(sconfig, "We had an error parsing a field into a date");
          return false;
        }
        String errMsg = SE.getMessage();
        final String NO_HIT_STR = "No hits found for the search criteria";
        if (errMsg != null && errMsg.indexOf(NO_HIT_STR) != -1) 
		{
        	LOG.DEBUG(sconfig,"getLoanReportsResults -- Archive search found no documents in the archive for the search criteria ");

		}
		else
		{
			LOG.ERROR(sconfig,"getLoanReportsResults error while calling Archive Service : " + errMsg);
		}
        return false;
      }
      catch (Throwable th) 
	  {
        LOG.ERROR(sconfig, "We got an error of type: " + th.getClass());
        LOG.ERROR(sconfig, th.getMessage());
        return false;
      }
      
      //We have list, now sort the entries.  First, put then all in Treeset
      
      int i = templist.size();    
      System.out.println("For Testing Size = "+i);
      for (int i1 = 0; i1 < templist.size(); i1++) 
      {     
      	Hashtable ht = (Hashtable) templist.elementAt(i1);       
        SchedAArchiveEntry sa = new SchedAArchiveEntry(ht);
        //sa.setTpaCode(tpa_code);
        if(this.planAccessType.equalsIgnoreCase("ALL"))
        {
        	archlist.add(sa);
        }
        else if(this.planAccessType.equalsIgnoreCase("SP"))
        {
        	if(planAccessExceptionTable == null)
        	{
        		// Error Message has to be printed
        	}        	
        	System.out.println("Plan Number from Archive Servlet  =  "+sa.getPlan_Number());
        	System.out.println("Vector Returned  from WebService =  "+planAccessExceptionTable);
        	
        	if(planAccessExceptionTable.size() > 0 && planAccessExceptionTable.contains(sa.getPlan_Number()))
        	{
        		archlist.add(sa);
        	}        	
        }
        else if(this.planAccessType.equalsIgnoreCase("EX"))
        {
        	if(planAccessExceptionTable == null)
        	{
        		//Error message has to be set here ... 
        		//planAccessExceptionTable = getPlanAccessException(request,tpa_code);
        	}
        	if(planAccessExceptionTable.size()>0 && !planAccessExceptionTable.contains(sa.getPlan_Number()))
        	{
        		archlist.add(sa);
        	}        	
        }
        
      }
      numRecs++;
    }
    if (archlist.size() != 0) {
      return true;
    }
    else {
      return false;
    }
  }  
  private Vector getPlanAccessException(HttpServletRequest request,String tpaCode) 
  {
  	Vector planNumbers = new Vector();
  	Iterator ite =  makeWebServiceCall(request,tpaCode).iterator();
	while(ite.hasNext())
	{
		com.fs.app.tpahomeca.bo.TPAPlanAuthorizationPlanException returnBean =( com.fs.app.tpahomeca.bo.TPAPlanAuthorizationPlanException)ite.next();
		planNumbers.add(returnBean.getPlanNumber());
		
	}
	return planNumbers;
  }
  
  private Vector makeWebServiceCall(HttpServletRequest request,String tpaCode)
  {
  	Vector output  = null;
  	try
	{
  		TPAHomeCAProxy ws = new TPAHomeCAProxy();
  		Vector temp = (Vector)(this.state.getAttribute("getUserTPAEntitlement"));
  		TPAPlanAuthorizationEntitlementBean actBean  =(TPAPlanAuthorizationEntitlementBean) (temp.get(0));
  		System.out.println("User ID"+actBean.getUserID());
  		output = ws.getPlanAccessExceptions(request,tpaCode,actBean.getUserID());  		
	}
  	catch(Exception exp)
	{
  		exp.printStackTrace();
	}
  	return output;
  }
  protected boolean GetDocumentsForTPACodes(HttpServletRequest request) throws Exception 
  {
    //For each TPA Code retrieve, call the Archive Service and get
    //all documents for that TPA Code
    //int numRecs = vTPA_Code.size();

    Hashtable clist = this.getTPACodesFromList(request);
    //Use the following when it's for real - iterate through all of the
    //TPA Codes
   // int numRecs = 1;
    int numRecs = clist.size();
    //Vector templist = null;
    Vector results = null;
    Comparator c = new TPASchedARunComparator(false);
    archlist = new TreeSet(c);
    Enumeration enumeration = clist.keys();
    while(enumeration.hasMoreElements())
    {	
    	String tpa_code = (String) enumeration.nextElement();
    	results = null;
    	System.out.println("TPA Code"+tpa_code);
    	this.planAccessType  = (String)clist.get(tpa_code);
    	System.out.println("planAccessType  "+planAccessType);
    	Vector planAccessExceptionTable = null;
    	if(this.planAccessType.equalsIgnoreCase("SP")|| this.planAccessType.equalsIgnoreCase("EX"))
    	{
    		planAccessExceptionTable = getPlanAccessException(request,tpa_code);
    		System.out.println("Web Service Executed ");
    	}
    	
      try 
	  {
    	    String	ArchiveServiceURL= getConstant(sconfig,"ARCHIVESERVICE_PROPERTIES","ArchiveServiceURL");;
    		 String	ArchiveServiceID=getConstant(sconfig,"ARCHIVESERVICE_PROPERTIES","ArchiveServiceID");
    		 String	ArchiveServicePassword=  getConstant(sconfig,"ARCHIVESERVICE_PROPERTIES","ArchiveServicePassword");
    		 String	OnDemandEncryptedPassword=getConstant(sconfig,"ARCHIVESERVICE_PROPERTIES","OnDemandEncryptedPassword");
    		 String ArchiveServiceTimeOut=getConstant(sconfig,"ARCHIVESERVICE_PROPERTIES","ArchiveServiceTimeOut");
    		 String clientReturnURLType=getConstant(sconfig,"ARCHIVESERVICE_PROPERTIES",authDirName+"_ClientURLType");
    		 ArchiveWebserviceSoapBindingStub stub = null;
    		  String sArchRole = "ScheduleASupport";
    	        String sArchFolder = getConstant(sconfig,"ARCHIVESERVICE_PROPERTIES","Arch5500Folder");
    		  URL url = null;
    		 int iTimeOut =0;
    			try{
    				iTimeOut = Integer.parseInt(ArchiveServiceTimeOut);
    			}
    			catch(Exception e)
    			{
    				iTimeOut =10000;
    			}
    			url = new URL(ArchiveServiceURL);
    			stub = new ArchiveWebserviceSoapBindingStub(url,null);
    			stub.setUsername(ArchiveServiceID);
    			stub.setPassword(ArchiveServicePassword);

    			stub._setProperty(org.apache.axis.transport.http.HTTPConstants.HEADER_COOKIE, "SMCHALLENGE=YES");
    			stub.setMaintainSession(true);
    			stub.setTimeout(iTimeOut);
    			LOG.DEBUG(sconfig,"GetDocumentsForTPACodes calling Archive Service : " + ArchiveServiceURL);
    			ArchiveDocumentSearchRequest serviceRequest= new ArchiveDocumentSearchRequest();
    			ArchiveDocumentSearchResponse serviceResponse = null;
    			serviceRequest.setCustomerLoginId(smUser);
    			//System.out.println("User sent to Archive Service : "+this.userID);
    			serviceRequest.setContentUserId(sArchFolder);
    			serviceRequest.setContentPassword(OnDemandEncryptedPassword);
    			serviceRequest.setFolderName(sArchFolder);
    			serviceRequest.setRequestType(RequestType.standard);
    			//serviceRequest.setIsCriteriaORed(Boolean.FALSE);	
    			serviceRequest.setClientReturnURL(ClientReturnURL.fromString(clientReturnURLType));
        int size=3;
        if (Plannum != null) {
            if (Plannum.trim().length() > 0) {
            	size=4;
            }
        }
    	SearchCriteria[] searchCriteria  = new SearchCriteria[size];
    	CriteriaValue  criteriaValue = new CriteriaValue();
    	searchCriteria = new SearchCriteria[size];
         int i=0;
        if(! userType.equalsIgnoreCase("Internal"))
        {     	
          	searchCriteria[i]= new SearchCriteria();
	    	criteriaValue = new CriteriaValue();
	    	searchCriteria[i].setCriteriaName("TPA_CODE");
	    	searchCriteria[i].setOperator(Operator.OPEqual);
	    	criteriaValue.setOperatorValue(tpa_code);
	    	searchCriteria[i].setCriteriaValue(criteriaValue);
        }
        else
        {
          	searchCriteria[i]= new SearchCriteria();
	    	criteriaValue = new CriteriaValue();
	    	searchCriteria[i].setCriteriaName("Receiver_Type");
	    	searchCriteria[i].setOperator(Operator.OPEqual);
	    	criteriaValue.setOperatorValue("T");
	    	searchCriteria[i].setCriteriaValue(criteriaValue);
        }
        i=i+1;
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(false);
		Date sDate = sdf.parse(Start_date);
		Date eDate = sdf.parse(End_date);

		long start_date=0;
		long end_date=0;

		start_date = (sDate.getTime() / 0x5265c00L)+1L;
		end_date = (eDate.getTime() / 0x5265c00L)+1L;


		searchCriteria[i]= new SearchCriteria();
		searchCriteria[i].setCriteriaName("CHAR(Report_End_Date)");
		searchCriteria[i].setOperator(Operator.OPBetween);
		criteriaValue = new CriteriaValue();
		BetweenAndNotBetween betweenAndNotBetween = new BetweenAndNotBetween();
		betweenAndNotBetween.setFirstValue(new Long(start_date).toString());
		betweenAndNotBetween.setSecondValue(new Long(end_date).toString());

		criteriaValue.setBetweenAndNotBetween(betweenAndNotBetween);
		searchCriteria[i].setCriteriaValue(criteriaValue);
		i =i+1;
		searchCriteria[i]= new SearchCriteria();
		criteriaValue = new CriteriaValue();
		searchCriteria[i].setCriteriaName("View_Indicator");
		searchCriteria[i].setOperator(Operator.OPEqual);
		criteriaValue.setOperatorValue("Y");
		searchCriteria[i].setCriteriaValue(criteriaValue);
       i=i+1;

        if (Plannum != null) {
          if (Plannum.trim().length() > 0) {
        		searchCriteria[i]= new SearchCriteria();
    			criteriaValue = new CriteriaValue();
    			searchCriteria[i].setCriteriaName("Plan_Number");
    			searchCriteria[i].setOperator(Operator.OPLike);
    			//for testing
    			//criteriaValue.setOperatorValue("559167");
    			criteriaValue.setOperatorValue(Plannum.toUpperCase());
    			searchCriteria[i].setCriteriaValue(criteriaValue);
          }
        }
        SearchCriteriaType inputSearchCriteria = new SearchCriteriaType();
		inputSearchCriteria.setItem(searchCriteria);
		serviceRequest.setSearchCriteria(inputSearchCriteria);

		serviceResponse  =stub.retrieveDocumentHitList(serviceRequest);
		ArchiveDocumentSearchHits[] hits = null;
		hits = serviceResponse.getArchiveDocumentSearchHits();


		URL archViewURL = new URL(serviceResponse.getDocumentViewingURL());

		if(hits.length > 0)
		{
			results = new Vector();
			Hashtable row = null;
			DocumentColumnsAndValues columns[] = null;
			for(int k = 0; k < hits.length; k++)
			{

				columns = hits[k].getDocumentColumnsAndValues();
				row = new Hashtable();
				row.put("HREF", archViewURL.toString());
				row.put("PARMS", hits[k].getDocumentQueryStringParameterValue());
				if(columns.length > 0)
				{
					for(int j = 0; j < columns.length; j++)
					{
						row.put(columns[j].getColumnName(), columns[j].getColumnValue());
						if(columns[j].getColumnName().equalsIgnoreCase("Plan_Number"))
							LOG.DEBUG(sconfig,"Arch5500 report for "+columns[j].getColumnName() + " " +columns[j].getColumnValue()+" ");
					}


				}
				results.add(row);
			}

		}
        if (results == null) {
          //LOG.DEBUG(sconfig, "Back From archive, null list");
          //return false;
        }
        else {
          LOG.DEBUG(sconfig, "Back From archive, count is " + results.size());
        }
      }
      catch (Exception SE) 
	  {
    	  if (SE.getMessage().equals(
          "java.lang.ClassCastException: java.lang.String")) 
      {
        LOG.ERROR(sconfig, "We had an error parsing a field into a date");
        return false;
      }
          String errMsg = SE.getMessage();
          final String NO_HIT_STR = "No hits found for the search criteria";
          if (errMsg != null && errMsg.indexOf(NO_HIT_STR) != -1) 
  		{
          	LOG.DEBUG(sconfig,"SelectArch5500_PreDisplay.GetDocumentsForTPACodes -- Archive search found no documents in the archive for the search criteria ");

  		}
  		else
  		{
  			LOG.ERROR(sconfig,"SelectArch5500_PreDisplay.GetDocumentsForTPACodes error while calling Archive Service : " + errMsg);
  		}
          //return false;
      }
      catch (Throwable th) 
	  {
        LOG.ERROR(sconfig, "We got an error of type: " + th.getClass());
        LOG.ERROR(sconfig, th.getMessage());
       // return false;
      }
      
      //We have list, now sort the entries.  First, put then all in Treeset
      
      //int i = results.size();    
      if(results!=null)
      {
      System.out.println("For Testing Size = "+results.size());
      for (int i = 0; i < results.size(); i++) 
      {     
      	Hashtable ht = (Hashtable) results.elementAt(i);       
        SchedAArchiveEntry sa = new SchedAArchiveEntry(ht);
        //sa.setTpaCode(tpa_code);
        if(this.planAccessType.equalsIgnoreCase("ALL"))
        {
        	archlist.add(sa);
        }
        else if(this.planAccessType.equalsIgnoreCase("SP"))
        {
        	if(planAccessExceptionTable == null)
        	{
        		// Error Message has to be printed
        	}        	
        	LOG.DEBUG(sconfig,"Plan Number from Archive Servlet  =  "+sa.getPlan_Number());
        	LOG.DEBUG(sconfig,"Vector Returned  from WebService =  "+planAccessExceptionTable);
        	
        	if(planAccessExceptionTable.size() > 0 && planAccessExceptionTable.contains(sa.getPlan_Number()))
        	{
        		archlist.add(sa);
        	}        	
        }
        else if(this.planAccessType.equalsIgnoreCase("EX"))
        {
        	if(planAccessExceptionTable == null)
        	{
        		//Error message has to be set here ... 
        		//planAccessExceptionTable = getPlanAccessException(request,tpa_code);
        	}
        	if(planAccessExceptionTable.size()>0 && !planAccessExceptionTable.contains(sa.getPlan_Number()))
        	{
        		archlist.add(sa);
        	}        	
        }
        
      }
      }
      numRecs++;
    }
    if (archlist.size() != 0) {
      return true;
    }
    else {
      return false;
    }
  }  
  
	public static String getConstant (String strConfig, String type, String key) throws Exception {

		IConfiguration objConfig = (IConfiguration)ConfigurationFactory.getConfiguration(strConfig);

		return  objConfig.getAttribute("section/Constant", type + "/" + key, "value");

	} // End of getConstant()
}
