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
import com.fs.app.tpascheda.data.SchedAArchiveEntry;

/**
 * Implementation of IFormBean for TPA Email Correspondence (TPAView)
 *
 * @author Mark Boudreau
 * @version 1.0
**/

public class TPASchedANumComparator
    extends TPASchedAComparator
    implements Comparator
{
    boolean descOrder;
    /**
     * Public Constructor
     */
  public TPASchedANumComparator(boolean descOrderParam)
    {

      descOrder = descOrderParam;
    }

     public int compare(Object o1,Object o2)
     {
       if ( descOrder ) {
       return (doCompare( o1, o2 ) * -1 );
    } else {
       return doCompare( o1, o2 );
    }

     }

    public int doCompare(Object o1,Object o2)
    {
        //System.out.println("In compare");
        SchedAArchiveEntry sc1 = (SchedAArchiveEntry) o1;
        SchedAArchiveEntry sc2 = (SchedAArchiveEntry) o2;
        String s1, s2;
        int j1, j2;
        int i1;
        //If you got here, the Run_dates are the same, so compare Plan Num
       s1 = (String)sc1.getPlan_Number();
       s2 = (String)sc2.getPlan_Number();

       i1 = s1.compareTo(s2);
       if (i1 > 0)
       {
          return 1;
       }
       if (i1 < 0)
       {
          return -1;
       }

        s1 = reformat((String)sc1.getRun_Date());
        s2 = reformat((String)sc2.getRun_Date());
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



        //Try start Date.. ascending
      s1 = reformat((String)sc1.getReport_Start_Date());
      s2 = reformat((String)sc2.getReport_Start_Date());
      i1 = s1.compareTo(s2);

      if (i1 > 0)
      {
         return 1;
      }
      if (i1 < 0)
      {
         return -1;
      }

      //Try end Date.. ascending
   s1 = reformat((String)sc1.getReport_End_Date());
   s2 = reformat((String)sc2.getReport_End_Date());
   i1 = s1.compareTo(s2);

   if (i1 > 0)
   {
      return 1;
   }
   if (i1 < 0)
   {
      return -1;
   }
//still trying..

        s1 = (String)sc1.getCompany_Code();
        s2 = (String)sc2.getCompany_Code();

        i1 = s1.compareTo(s2);
        if (i1 > 0)
        {
           return 1;
        }
        if (i1 < 0)
        {
           return -1;
        }

        //still trying..

              s1 = (String)sc1.getSource_System();
              s2 = (String)sc2.getSource_System();

              i1 = s1.compareTo(s2);
              if (i1 > 0)
              {
                 return 1;
              }
              if (i1 < 0)
              {
                 return -1;
              }

              //still trying..

              s1 = (String)sc1.getRun_Type_Indicator();
              s2 = (String)sc2.getRun_Type_Indicator();

              i1 = s1.compareTo(s2);
              if (i1 > 0)
              {
                 return 1;
              }
              if (i1 < 0)
              {
                 return -1;
              }

              //still trying..

            s1 = (String)sc1.getDistribution_Type();
            s2 = (String)sc2.getDistribution_Type();

            i1 = s1.compareTo(s2);
            if (i1 > 0)
            {
               return 1;
            }
            if (i1 < 0)
            {
               return -1;
            }

            //still trying..

        s1 = (String)sc1.getReceiver_Type();
        s2 = (String)sc2.getReceiver_Type();

        i1 = s1.compareTo(s2);
        if (i1 > 0)
        {
           return 1;
        }
        if (i1 < 0)
        {
           return -1;
        }
        
        s1 = (String)sc1.getTPA_Code();
        s2 = (String)sc2.getTPA_Code();

        i1 = s1.compareTo(s2);
        if (i1 > 0)
        {
           return 1;
        }
        if (i1 < 0)
        {
           return -1;
        }





//finally I give up, it is a duplicate request, drop it
   return 0;
    }
public String reformat(String in)
{
    //Reformat dates from mm/dd/yy format to yy/mm/dd
    String out = in.substring(6) + in.substring(0, 2) + in.substring(3, 5);
    return out;
}

}
