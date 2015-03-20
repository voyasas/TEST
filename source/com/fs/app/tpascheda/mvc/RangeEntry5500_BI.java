package com.fs.app.tpascheda.mvc;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.fs.app.tpahomeca.bo.TPAPlanAuthorizationEntitlementBean;
import com.fs.is.cmd.Command;
import com.fs.is.cmd.ServiceClient;
import com.fs.is.configuration.ConfigException;
import com.fs.is.configuration.ConfigurationFactory;
import com.fs.is.configuration.IConfiguration;
import com.fs.is.ids.DataAccessRequest;
import com.fs.is.ids.DataAccessResponse;
import com.fs.is.log.LOG;
import com.fs.is.state.IState;
import com.fs.is.state.StateFactory;
import com.fs.is.tags.handler.ErrorResponse;
import com.fs.is.tags.handler.IBusinessInteraction;

public class RangeEntry5500_BI implements IBusinessInteraction {

  protected IState state;
  protected HttpServletRequest request;
  protected String sconfig = "tpascheda.xml";
  private Vector userTPAEntitlement;
  
  boolean isInternalUser()
  {
  	if(userTPAEntitlement != null && userTPAEntitlement.size() != 0)
  	{
  		Iterator ite  = userTPAEntitlement.iterator();
  		while(ite.hasNext())
  		{
  			TPAPlanAuthorizationEntitlementBean actBean = (TPAPlanAuthorizationEntitlementBean)ite.next();
  			System.out.println(actBean.getEntryType());
  			if(actBean.getEntryType().equals("Internal"))
  			{
  				return true;
  			}
  		}
  	}
  	return false;
  }
 
  public RangeEntry5500_BI() {
  }

  public void interact(HttpServletRequest request) throws Exception {
    this.state = (IState) StateFactory.getState(request);

    //checkLoginInfo(request);
    userTPAEntitlement = (Vector) state.getAttribute("getUserTPAEntitlement");
    request.setAttribute("Reset","Yes");
    ErrorResponse error = new ErrorResponse();

    RangeEntry5500_Bean bean = (RangeEntry5500_Bean) request.getAttribute("FormBean");
    state.setAttribute("todate", bean.getToDate());
	//state.setAttribute("UserID",request.getAttribute("UserID"));
    String splan = bean.getPlanNumber();
    if (splan == null) {
      splan = "";

    }
    
    state.setAttribute("displan", splan);
      //a plan muber is entered, verify the tpa has access to the plan
      if(isInternalUser())
      {
      	if(splan.trim().length() == 6) 
      	{
      		if (!verifyplanexists(splan)) 
      		{
      			error.setError("plan", "The Plan number you entered is not a valid plan number please enter a valid plan number");
      		}
      	}
      	else
      	{
      		error.setError("plan", "Please enter the complete plan number");
      	}
      }

    
    //validate date range in acceptable range

    //get number of mon ths from xml..
    String s;
    IConfiguration appConfig = null;
    try {
      appConfig = ConfigurationFactory.getConfiguration("tpascheda.xml");
      s = appConfig.getAttribute("section/Constant", "DISPLAY/SCHEDA",
                                 "months");
    }
    catch (ConfigException ex) {
      s = null;
    }
    if (s == null) {
      s = "1";
    }
    else
    if (s.trim().length() == 0) {
      s = "1";
    }

    Integer I = new Integer(s);

    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    ParsePosition pos = new ParsePosition(0);
    pos = new ParsePosition(0);
    Date toDt = sdf.parse(bean.getToDate(), pos);
    cal.clear(Calendar.HOUR);
    cal.clear(Calendar.MINUTE);
    cal.clear(Calendar.SECOND);
    cal.clear(Calendar.MILLISECOND);
    cal.clear(Calendar.HOUR_OF_DAY); //This makes it AM instead of PM

	cal.setTime(toDt);
	cal.add(Calendar.MONTH, -I.intValue());

	state.setAttribute("fromdate", sdf.format(cal.getTime()));
    if (error.getErrorCount() > 0) {
      request.setAttribute("ErrorResponse", error);
      bean.setNextPage("RANGE_ENTRY_5500");

    }

  }
  private boolean verifyplanexists(String splan) throws Exception {
    Integer I = new Integer(0);
    int mycount = 0;
    try {
        ServiceClient serviceClient = new ServiceClient(sconfig);

        Map params = new HashMap();        
        params.put("planNo", splan);
        params.put("XML_FILE", sconfig);
        DataAccessRequest request = new DataAccessRequest(params);
        Command cmd = new Command();
        cmd.setTarget("IDS");
        cmd.setConfig(sconfig);
        cmd.setRequest(request);
        cmd.setVerb("ensurePlanExist");
        cmd = serviceClient.processCommand(cmd);
        DataAccessResponse resp = (DataAccessResponse) cmd.getResponse();
        int size = resp.getNumberOfRecords();
        // LOG.DEBUG (sconfig, "Number of Cases " + size);
        if (size > 0) {
          Map[] maps = resp.getRecords();
          for (int z = 0; z < size; z++) {
            Map myRow = maps[z];
            if(myRow.containsKey("PlanCnt"))
            {
            	try
				{
            		I = (Integer) myRow.get("PlanCnt");
				}
            	catch(NullPointerException nullExp)
				{
            		I = new Integer(0);
				}
            }
          }
        }
      }
      catch (Throwable th) {
        LOG.ERROR(sconfig, th.toString());
        throw new Exception(th.toString());
      }
      mycount = mycount + I.intValue();
    if (mycount > 0) {
      return true;
    }
    return false;

  }

  /**
   * verifypartplan
   *
   * @param splan String
   * @return boolean
   */
  /* private boolean checkLoginInfo(HttpServletRequest request) throws Exception 
   {
   	 boolean isFirstVisit = false;
	 if (state.getAttribute("getUserTPAEntitlement") == null) 
	 {
		TPAHomeCAProxy ws = new TPAHomeCAProxy();
		try
		{
			userTPAEntitlement = ws.getUserTPAEntitlement(request,"TPASCHEDA");
			Iterator ite = userTPAEntitlement.iterator();
			while (ite.hasNext()) 
			{
				TPAPlanAuthorizationEntitlementBean actBean = (TPAPlanAuthorizationEntitlementBean) ite.next();
				//printBeanValue(actBean);
				state.setAttribute("UserType",actBean.getEntryType());
				if (actBean.getWebApplicationId().equalsIgnoreCase("tpascheda")) 
				{		
					if (actBean.getIsfunctionAuthorized().equalsIgnoreCase("true")) {
						isFirstVisit = true;
						state.setAttribute("sm_user",actBean.getUserID());
						return isFirstVisit;
					} 
					else
					{
						throw new NotAuthorizedException();
					}
				}
			}
		} catch (NotAuthorizedException exp) {
			//exp.printStackTrace();
			throw new NotAuthorizedException();
		} catch (ServiceException exp) {
			//exp.printStackTrace();
			throw new NotAuthorizedException();
		}
	}
	else
	{
		userTPAEntitlement = (Vector) state.getAttribute("getUserTPAEntitlement");
	}
	return isFirstVisit; 

  }*/
  /* void printBeanValue(TPAPlanAuthorizationEntitlementBean actBean) 
   {
	
   	System.out.println("\n ******************* \n");
	System.out.println(actBean.getEntryType());
	System.out.println(actBean.getIsfunctionAuthorized());
	System.out.println(actBean.getPlanAccessType());
	System.out.println(actBean.getRoleName());
	System.out.println(actBean.getTpaCode());
	System.out.println(actBean.getTpaName());
	System.out.println(actBean.getWebApplicationDesc());
	System.out.println(actBean.getWebApplicationId());
	System.out.println(actBean.getWebApplicationURL());
	System.out.println("\n ******************* \n");
	System.out.println("\n");

    }*/
}
