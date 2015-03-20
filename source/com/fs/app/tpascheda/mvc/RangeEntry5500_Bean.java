package com.fs.app.tpascheda.mvc;

import javax.servlet.http.*;
import java.util.*;
import java.text.*;


import com.fs.is.tags.handler.*;
//import com.fs.is.tags.util.


public class RangeEntry5500_Bean implements IFormBean {

//Navigation control
private String Page;
private String nextPage;

private boolean invalidDateRange;
//private String invalidDateRangeMsg;
private boolean noMatchingPlans;
private boolean failedEdits;

//Form data
//private String fromDate;
private String toDate;
private String toDateBadM;
private String fromDateBadM;
private String planNumber;
private ErrorResponse error;
private String sdatemessage;
private String userType="";


  public RangeEntry5500_Bean() {
    //fromDate = "";
    toDate = "";
    planNumber = "";
    toDateBadM = "Please enter a valid 'Report End Date' as MM/DD/YYYY";
    fromDateBadM = "Please enter a valid 'Report End Date' as MM/DD/YYYY";
    error  = new ErrorResponse();
  }

  /**
   * There is no validation for this application. Just set next page.
   *
   * @param request
   */
  public boolean validate(HttpServletRequest request) {


    boolean fromThisPage;
    String strBuff = null;

    strBuff = request.getParameter("page");
    if ( strBuff != null && strBuff.equalsIgnoreCase("RANGE_ENTRY_5500") ) {
        fromThisPage = true;
    } else {
        fromThisPage = false;
    }

    if ( fromThisPage ) {
        //Perform edits
        if ( failedEdits( request ) ) {
            //Go back and display error to user
            setNextPage("RANGE_ENTRY_5500");
            setFailedEdits(true);
            request.setAttribute("ErrorResponse",error);
            return false;
        }
    }

    setNextPage("SELECT_ARCH_5500");
    return true;
  }

//********    Getters and Setters   *******************
// public String getFromDate() {
//    return fromDate;
//  }
// public void setFromDate(String s) {
//    fromDate = s;
// }
 public String getToDate() {
    return toDate;
  }
 public void setToDate(String s) 
 {
    toDate = s;
 }
 public String getPlanNumber() {
    return planNumber;
  }
 public void setPlanNumber(String s) {
    planNumber = s;
 }
 public boolean hasFailedEdits() {
    return failedEdits;
  }
 public void setFailedEdits(boolean b) {
    failedEdits = b;
 }
 public boolean hasNoMatchingPlans() {
    return noMatchingPlans;
  }
 public void setNoMatchingPlans(boolean b) {
    noMatchingPlans = b;
 }
 public boolean hasInvalidDateRange() {
    return invalidDateRange;
  }
 public void setInvalidDateRange(boolean b) {
    invalidDateRange = b;
 }


   public void setNextPage(String nextPage) {
    this.nextPage = nextPage;
  }

  public String getNextPage() {
    return nextPage;
  }

  public void setPage(String Page) {
    this.Page = Page;
  }

  public String getPage() {
    return Page;
  }
  public String getDateMessage()
  {
    return sdatemessage;

  }
  public void setDateMessage(String Page) {
   this.sdatemessage = Page;
 }



// *****************  private methods *********************

//This method runs all edits for the screen.
private boolean failedEdits( HttpServletRequest req ) 
{

    String whichDate;

    RangeEntry5500_Bean bean = (RangeEntry5500_Bean)req.getAttribute("FormBean");

    //**************    Date validations    ********************
    //String fromDate = (String) req.getParameter("fromDate");
    String toDate = (String) req.getParameter("toDate");

    //Verify that data is entered in both date text boxes.
//    if ( fromDate == null || fromDate.equals("") ) 
//	{
//        setInvalidDateRange(true);
//        error.setError("From Date ", fromDateBadM);
//
//    }
    //Verify that data is entered in both date text boxes.
    if ( toDate == null || toDate.equals("") ) 
	{
        setInvalidDateRange(true);
        error.setError("To Date ", toDateBadM);

    }
    if (error.getErrorCount() > 0)
    {
      return true;
    }

    //Verify that 'to date' is a valid date.


    //Verify that 'from date' is a valid date.
//    if ( !datePassesEdits( fromDate, fromDateBadM ) )
//	{
//        setInvalidDateRange(true);
//	} 
//	else 
//	{
//        bean.setFromDate( convertToDateFormat( fromDate ));
//    }
    if( !datePassesEdits( toDate, toDateBadM ) )
	{
       setInvalidDateRange(true);
       //setInvalidDateRangeMsg("Please enter 'to date' as MM/DD/YYYY");

	}
	else 
	{
       bean.setToDate( convertToDateFormat( toDate ));
	}

    if (error.getErrorCount() > 0)
    {
       return true;
    }

    //Both dates are valid. Now check the date range
    //fromDate = convertToDateFormat( fromDate );
    toDate = convertToDateFormat( toDate );

//    if ( !dateRangePassesEdits( fromDate, toDate )) 
//	{
//        setInvalidDateRange(true);
//        return true;
//    }

    return false;
}


//This method runs the edits for the date RANGE. Valid dates assumed
private boolean dateRangePassesEdits( String strFrom, String strTo ) {

    //Turn input strings into a dates.
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    ParsePosition pos = new ParsePosition(0);

    //Note that hours minitues seconds are set to 12:00:00 AM
    Date fromDt = sdf.parse( strFrom, pos);

    pos = new ParsePosition(0);
    Date toDt = sdf.parse( strTo, pos);

    //Get today's date.
    //This returns an instance of Gregorian Calander
    //with current system date and time
    Calendar cal = Calendar.getInstance();

    //Set time portion to 12:00:00 AM
    cal.clear( Calendar.HOUR );
    cal.clear( Calendar.MINUTE );
    cal.clear( Calendar.SECOND );
    cal.clear( Calendar.MILLISECOND );
    cal.clear( Calendar.HOUR_OF_DAY );//This makes it AM instead of PM

    // ( toDate > today ) is not allowed.
    if ( toDt.after(cal.getTime()) ) {
        error.setError("To Date", "'To Date' cannot be later than today's date.");
        }

    // ( fromDate > toDate ) is not allowed.
    if ( fromDt.after(toDt) ) {
         error.setError("From Date", "'From Date' cannot be later than 'To Date'");

    }


/*
    // (  toDate > fromDate + 1 month  )  is not allowed.
    cal.setTime(fromDt);
    cal.add( Calendar.MONTH, 1 ); //Add one month to the from date
    if ( toDt.after(cal.getTime()) ) {
       error.setError("Date range", "Date range cannot exceed one month.");
       }
    if (!( toDt.before(cal.getTime()) )) {
           error.setError("Date range", "Date range cannot exceed one month.");
        }

        if (error.getErrorCount() > 0)
              {
               return false;

              }
*/
if (error.getErrorCount() > 0)
             {
              return false;

             }

    return true;
}


//This method runs all edits (format, validity)  against a single date.
private boolean datePassesEdits( String strDate, String whichDate ) {

    //validDate = true;//till proven other wise
    String strBuff = strDate;;

    if ( strBuff==null || (strBuff.trim().length() != 10 && strBuff.trim().length() != 8 ) ) {
         error.setError("Date range", whichDate);
        return false;
    }

    //Parse date to check for invalid characters
    if ( stringHasInvalidChar(strBuff) ) {
        error.setError("Date range", whichDate);
        return false;    }

    //Convert date to MM/DD/YYYY and store it in the bean
    strBuff = convertToDateFormat( strDate );

    if ( !dateIsValid( strBuff ) ) {
       // setInvalidDateRangeMsg( "'" + whichDate + "'" + " is not an actual date, please enter an appropriate date in MM/DD/YYYY format");
       error.setError("Date range", whichDate);
        return false;
    }

    return true;
}


//This method accepts a properly formatted date (MM/DD/YYYY) and
//checks to see if it is an actual date
// e.g 05/35/2004 would fail because there are not 35 days in May.
private boolean dateIsValid(String strBuff){

	try {

	    int month = Integer.parseInt( strBuff.substring(0,2) );
	    int day = Integer.parseInt( strBuff.substring(3,5) );
	    int year = Integer.parseInt( strBuff.substring(6,10) );

	    java.util.GregorianCalendar gcDate = new java.util.GregorianCalendar();
	    gcDate.clear();
	    gcDate.setLenient(false);
	    gcDate.set(year, month - 1, day);
            //gcDate.set(1999, 01, 30);

		gcDate.get(Calendar.DATE);

	} catch (Exception e) {
		 return false;
	}

	return true;
}


//This converts strings of eight or ten characters in length
// to:  MM/DD/YYYY
private String convertToDateFormat(String strIn){
    String strOut;

    if ( strIn.length() == 8 ) {
        strOut = strIn.substring(0,2) + "/" + strIn.substring(2,4) + "/" + strIn.substring(4,8);
    } else {
        strOut = strIn.substring(0,2) + "/" + strIn.substring(3,5) + "/" + strIn.substring(6,10);
    }

    return strOut;

}


//This method checks a string to ensure that only allowed character
// comprise it. Allowed characters are: 0 - 9, "/", "\", "-"
//These are the only allowed characters for a date.
private boolean stringHasInvalidChar(String strBuff) {
    Character C;
    int charCd;

    for ( int i=0; i < strBuff.length(); ++i ) {
        C = new Character(strBuff.charAt(i));
        charCd = C.hashCode();  //Ascii decimal

        //Allow numbers, "/", "\", "-"
        if ( (charCd < 47 || charCd > 57) && charCd != 92 && charCd != 45 ) {
            return true;
        }
    }
    return false;
}


/**
 * @return Returns the userType.
 */
public String getUserType() {
	return userType;
}
/**
 * @param userType The userType to set.
 */
public void setUserType(String userType) {
	this.userType = userType;
}
}
