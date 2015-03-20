package com.fs.app.tpascheda.mvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;

import com.fs.app.tpascheda.data.SchedAArchiveEntry;
import com.fs.is.cmd.Command;
import com.fs.is.cmd.ServiceClient;
import com.fs.is.ids.DataAccessRequest;
import com.fs.is.ids.DataAccessResponse;
import com.fs.is.log.LOG;
import com.fs.is.state.IState;
import com.fs.is.state.StateFactory;
import com.fs.is.tags.handler.IBusinessInteraction;

public class SelectArch5500_BI
    implements IBusinessInteraction {

  protected IState state;
  //protected HttpServletRequest request;
  protected String sconfig = "tpascheda.xml";
  protected String slogin_id = null;
  protected String saccess_level = null;
  protected String saccessor_type = null;
  protected TreeSet archlist;

  public SelectArch5500_BI() {
  }

  public void interact(HttpServletRequest request) throws Exception {
    state = (IState) StateFactory.getState(request);   

      request.setAttribute("NeedForm", "No");
    SelectArch5500_Bean bean = (SelectArch5500_Bean) request.getAttribute(
        "FormBean");
    boolean bstay = false;
    if ( (request.getParameter("btnBack.x") != null)
        && request.getParameter("btnBack.x").compareTo("0") != 0) {
      bean.setNextPage("RANGE_ENTRY_5500");
      return;

    }
    if ( (request.getParameter("btnView.x") != null)
        && request.getParameter("btnView.x").compareTo("0") != 0) {
      bstay = true;

    }
    if (!bstay) {
      bean.setNextPage("SELECT_ARCH_5500");
      return;
    }

    if ( (request.getParameter("selArch") == null)) {
      return;
    }
    String s = request.getParameter("selArch");
    Double D = new Double(s);
    request.setAttribute("selArchGo",s);
    archlist = (TreeSet) state.getAttribute("sschedalist");
    Iterator IT = archlist.iterator();
    SchedAArchiveEntry sa = null;
    while (IT.hasNext()) {
      sa = (SchedAArchiveEntry) IT.next();
      if (sa.getLastViewedId() == null)
      {
        continue;
      }
      if (sa.getLastViewedId().doubleValue() == D.doubleValue()) {
        Hashtable hStatement = (Hashtable) sa.getLArchiveInfo();
        String sFrmAct = (String) hStatement.get("HREF");
        String sFrmPrm = (String) hStatement.get("PARMS");
        request.setAttribute("NeedForm", "Yes");
        request.setAttribute("FrmAct", sFrmAct);
        request.setAttribute("FrmPrm", sFrmPrm);
        Calendar rightNow = Calendar.getInstance();
        Date dt =  rightNow.getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yy");
        sa.setLastViewedDate(df.format(dt));
        UpdateSybase(sa);
        state.setAttribute("sschedalist",archlist);
        break;



      }

    }

  }

  /**
   * UpdateSybase
   *
   * @param sa SchedAArchiveEntry
   */
  private void UpdateSybase(SchedAArchiveEntry sa) {
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
     cmd.setVerb("UpdateArchiveExtraInfo");
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

}
