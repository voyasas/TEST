package com.fs.app.tpascheda.dataaccess;
import com.fs.is.ids.*;
import com.fs.is.ids.exceptions.*;
import java.sql.*;
import com.fs.is.log.*;
import com.fs.app.tpascheda.data.*;
import java.text.SimpleDateFormat;

public class UpdateArchiveExtraInfo
    extends com.fs.is.ids.DataProcessor {
  SimpleDateFormat myFormat = null;
  public UpdateArchiveExtraInfo() {
    myFormat =  new java.text.SimpleDateFormat("MM/dd/yy");
  }
  protected String getSQLScript() {
       return " Update scheda_last_viewed "
             + "  set Last_Viewed_Date = getdate() "
            + "  where scheda_last_viewed_id =?";


  }
  protected void processSQL() throws SQLException, DataServiceException {
  SchedAArchiveEntry sa = (SchedAArchiveEntry) getParameter("SA", 1);
  //set connection parameters (isolation level, read only, auto commit)
  // this.setConParameters(_con, Connection.TRANSACTION_READ_UNCOMMITTED, true, true);
  try {
    String s = getSQLScript();
    _ps = _con.prepareStatement(s);

    this.setParameter(_ps, 1, sa.getLastViewedId(), java.sql.Types.DECIMAL);
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
