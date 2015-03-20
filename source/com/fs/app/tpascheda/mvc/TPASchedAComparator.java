package com.fs.app.tpascheda.mvc;

import java.util.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.*;
import java.io.Serializable;

import com.fs.is.cmd.*;
import com.fs.is.Exceptions.*;
import com.fs.is.configuration.*;
import com.fs.is.dataaccess.*;
import com.fs.is.log.*;
import com.fs.is.infrastructure.*;

/**
 * Implementation of IFormBean for TPA Email Correspondence (TPAView)
 *
 * @author Mark Boudreau
 * @version 1.0
**/

public class TPASchedAComparator
{
    protected String sTPA = "TPA_Code";
    protected String sPLAN_NUMBER = "Plan_Number";
    protected String sStartDate = "Report_Start_Date";
    protected String sEndDate= "Report_End_Date";
    protected String sCOMPANY_CODE = "Company_Code";
    protected String  sSOURCE_SYSTEM = "Source_System";
    protected String sView = "View_Indicator";
    protected String sRun = "Run_Date";
    protected String sRunType = "Run_Type_Indicator";
    protected String sDist= "Distribution_Type";
     protected String sReceiver= "Receiver_Type";


    /**
     * Public Constructor
     */
    public TPASchedAComparator()
    {
    }


public String reformat(String in)
{
    //Reformat dates from mm/dd/yy format to yy/mm/dd
    String out = in.substring(6) + in.substring(0, 2) + in.substring(3, 5);
    return out;
}
/*
public String reformat(java.util.Date in)
{
    //Reformat dates from mm/dd/yy format to yy/mm/dd
    String out = in.substring(6) + in.substring(0, 2) + in.substring(3, 5);
    return out;
}
      }
     */

}
