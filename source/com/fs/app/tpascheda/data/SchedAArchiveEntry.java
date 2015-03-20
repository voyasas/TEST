package com.fs.app.tpascheda.data;

import com.fs.app.tpascheda.mvc.*;
import java.io.*;
import java.sql.*;
import java.util.*;

public class SchedAArchiveEntry
    extends TPASchedAComparator
    implements Serializable {

  private Double scheda_last_viewed_id;

  private String Last_Viewed_Date;
  private Hashtable ainfo;
  private Boolean bbentosybase;

  private String plan_name;

  public SchedAArchiveEntry(Hashtable h) {
    ainfo = h;
    bbentosybase = new Boolean(false);
    Last_Viewed_Date = "&nbsp;";
  }

  public boolean getBentoSybase() {
    return bbentosybase.booleanValue();
  }

  public void setBentoSybase(boolean b) {
    bbentosybase = new Boolean(b);
  }

  public Double getLastViewedId() {
    return scheda_last_viewed_id;
  }

  public void setLastViewedId(Double d) {
    scheda_last_viewed_id = d;
  }

  public String getLastViewedDate() {
    return Last_Viewed_Date;
  }

  public void setLastViewedDate(String d) {
    Last_Viewed_Date = d;
  }

  public void setLArchiveInfo(Hashtable d) {
    ainfo = d;
  }
  public Hashtable getLArchiveInfo() {
   return ainfo;
 }


  public String getTPA_Code() {
    return (String) ainfo.get(sTPA);
  }

  public String getPlan_Number() {
    return (String) ainfo.get(sPLAN_NUMBER);
  }

  public String getReport_Start_Date() {

    return (String) ainfo.get(sStartDate);
  }

  public String getReport_End_Date() {

    return (String) ainfo.get(sEndDate);
  }

  public String getCompany_Code() {

    return (String) ainfo.get(sCOMPANY_CODE);
  }

  public String getSource_System() {

    return (String) ainfo.get(sSOURCE_SYSTEM);
  }

  public String getView_Indicator() {

    return (String) ainfo.get(sView);
  }

  public String getRun_Date() {

    return (String) ainfo.get(sRun);
  }

  public String getRun_Type_Indicator() {

    return (String) ainfo.get(sRunType);
  }

  public String getDistribution_Type() {

    return (String) ainfo.get(sDist);
  }

  public String getReceiver_Type() {

    return (String) ainfo.get(sReceiver);
  }

  public String getPlan_Name() {

    //return (String) ainfo.get(sReceiver);
    return plan_name;
  }

  public void setPlan_Name(String s) {

    plan_name = s;

  }

}
