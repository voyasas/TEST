package com.fs.app.tpascheda.util;

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

public class TPAViewComparator implements Comparator
{
    protected String sRptDate = "Run_date";
    protected String sFileNumber = "File_Number";
    protected String sSSN = "SSN";

    /**
     * Public Constructor
     */
    public TPAViewComparator()
    {
    }

    public int compare(Object o1,Object o2)
    {
        //System.out.println("In compare");
        Hashtable sc1 = (Hashtable) o1;
        Hashtable sc2 = (Hashtable) o2;
        String s1, s2;
        int j1, j2;
        int i1;
        s1 = reformat((String)sc1.get("Run_date"));
        s2 = reformat((String)sc2.get("Run_date"));
        //Displays for testing
        //System.out.println("First Run Date = " + s1);
        //System.out.println("Second Run Date = " + s1);
        //for (Enumeration e = sc1.keys() ; e.hasMoreElements() ;)
        //{
        //    System.out.println(e.nextElement());
        //}

        //The first sort is by Run_date descending
        i1 = s1.compareTo(s2);

        if (i1 < 0)
        {
           return 1;
        }
        if (i1 > 0)
        {
           return -1;
        }

        //If you got here, the Run_dates are the same, so compare SSNs
        s1 = (String)sc1.get("SSN");
        s2 = (String)sc2.get("SSN");
        //System.out.println ("First SSN = " + s1);
        //System.out.println ("Second SSN = " + s2);

        i1 = s1.compareTo(s2);
        if (i1 > 0)
        {
           return 1;
        }
        if (i1 < 0)
        {
           return -1;
        }

        //Since both of the other criteria the same, compare File Numbers
        s1 = (String)sc1.get("File_num");
        s2 = (String)sc2.get("File_num");
        j1 = Integer.parseInt(s1);
        j2 = Integer.parseInt(s2);
        //System.out.println ("First File_num = " + s1);
        //System.out.println ("Second File_num = " + s2);
        if (j1 < j2)
        {
           return 1;
        }
        else if (j1 > j2)
        {
           return -1;
        }
        else
        {
            return 0;
        }
    }
public String reformat(String in)
{
    //Reformat dates from mm/dd/yy format to yy/mm/dd
    String out = in.substring(6) + in.substring(0, 2) + in.substring(3, 5);
    return out;
}

}
