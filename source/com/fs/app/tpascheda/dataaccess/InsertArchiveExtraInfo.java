package com.fs.app.tpascheda.dataaccess;
import com.fs.is.ids.*;
import com.fs.is.ids.exceptions.*;
import java.sql.*;
import com.fs.is.log.*;
import com.fs.app.tpascheda.data.*;
import java.text.SimpleDateFormat;

public class InsertArchiveExtraInfo
 extends com.fs.is.ids.DataProcessor {
  SimpleDateFormat myFormat = null;
  public InsertArchiveExtraInfo() {
    myFormat =  new java.text.SimpleDateFormat("MM/dd/yy");
  }
  protected String getSQLScript() {
       return " INSERT INTO scheda_last_viewed ("
             + "  TPA_Code"
            + "  , Plan_Number"
            + "  , Report_Start_Date"
            + "  , Report_End_Date"
            + "  , Company_Code"
            + "  , Source_System"
            + "  , View_Indicator"
            + "  , Run_Date"
            + "  , Run_Type_Indicator"
            + "  , Distribution_Type"
            + "  , Receiver_Type)"
            + " VALUES (?,?,?,?,?,?,?,?,?,?,?)";

  }
  protected void processSQL() throws SQLException, DataServiceException {
  SchedAArchiveEntry sa = (SchedAArchiveEntry) getParameter("SA", 1);
  //set connection parameters (isolation level, read only, auto commit)
  // this.setConParameters(_con, Connection.TRANSACTION_READ_UNCOMMITTED, true, true);
  try {
    String s = getSQLScript();
    _ps = _con.prepareStatement(s);

    this.setParameter(_ps, 1, sa.getTPA_Code(), java.sql.Types.CHAR);
     this.setParameter(_ps, 2, sa.getPlan_Number(), java.sql.Types.CHAR);
     this.setParameter(_ps, 3, parseDate(sa.getReport_Start_Date()), java.sql.Types.DATE);
     this.setParameter(_ps, 4, parseDate(sa.getReport_End_Date()), java.sql.Types.DATE);
     this.setParameter(_ps, 5, sa.getCompany_Code(), java.sql.Types.CHAR);
     this.setParameter(_ps, 6, sa.getSource_System(), java.sql.Types.CHAR);
     this.setParameter(_ps, 7, sa.getView_Indicator(), java.sql.Types.CHAR);
     this.setParameter(_ps, 8, parseDate(sa.getRun_Date()), java.sql.Types.DATE);
     this.setParameter(_ps, 9, sa.getRun_Type_Indicator(), java.sql.Types.CHAR);
     this.setParameter(_ps, 10, sa.getDistribution_Type(), java.sql.Types.CHAR);
     this.setParameter(_ps, 11, sa.getReceiver_Type(), java.sql.Types.CHAR);



    _ps.executeUpdate();


  }
  catch (DataServiceException se) {

    throw se;
  }
  catch (SQLException sql) {

    throw new DataServiceException(sql);
  }
  catch (Throwable t) {

    t.printStackTrace();
    throw new DataServiceException(t);
  }

}
public java.sql.Date parseDate(String s)
{
 java.util.Date dt;
  try
  {
     dt =  myFormat.parse(s);
  }
  catch (Throwable t) {

    t.printStackTrace();
    return null;
  }



   java.sql.Date  toDt = new Date(dt.getTime());

   return toDt;
}


}
