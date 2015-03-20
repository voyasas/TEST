package com.fs.app.tpascheda.dataaccess;

import com.fs.is.ids.*;
import com.fs.is.ids.exceptions.*;
import java.sql.*;
import com.fs.is.log.*;

public class getPureCntPlanPart
    extends com.fs.is.ids.DataProcessor {
  public getPureCntPlanPart() {
  }

  protected String getSQLScript() {

    String s = "SELECT count(*)as PlanCnt from dbo.cases  "
        + " WHERE case_num like ?";
    return s;
  }

  protected void processSQL() throws SQLException, DataServiceException {

    //String tpa_code = (String) getParameter("tpa", 1);
    String plan = (String) getParameter("plan", 1);

    //set connection parameters (isolation level, read only, auto commit)
    this.setConParameters(_con, Connection.TRANSACTION_READ_UNCOMMITTED, true, true);
    try {
      String s = getSQLScript();
      _ps = _con.prepareStatement(s);

      //this.setParameter(_ps, 1, tpa_code, java.sql.Types.CHAR);
      this.setParameter(_ps, 1, plan +"%", java.sql.Types.CHAR);

      _rs = _ps.executeQuery();

      this.populateResponse(_response, _rs);

      // java.sql.SQLWarning sw = _con.getWarnings();

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

}
