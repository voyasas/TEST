package com.fs.app.tpascheda.mvc;

import javax.servlet.http.*;
import java.util.*;
import java.text.*;

import com.fs.is.tags.handler.*;

public class SelectArch5500_Bean
    implements IFormBean {

//Navigation control
  private String Page;
  private String nextPage;
   private String sort;
  private boolean invalidDateRange;
  private String invalidDateRangeMsg;
  private boolean noMatchingPlans;
  private boolean failedEdits;

//Form data
  private String fromDate;
  private String toDate;
  private String planNumber;

  private String jumpPageNum;
 private String numOfPages;
 private Boolean hasPrev;
 private Boolean hasNext;
 private String JumpPageNum;
 private double dselarch;

 Vector archlist;



  public SelectArch5500_Bean() {
    fromDate = "";
    toDate = "";
    planNumber = "";
    dselarch = -1;
  }

  /**
   * There is no validation for this application. Just set next page.
   *
   * @param request
   */
  public boolean validate(HttpServletRequest request) {
    setNextPage("SELECT_ARCH_5500");
    return true;
  }

//********    Getters and Setters   *******************
   public double  getSelArch() {
      return dselarch;
    }
   public void setSelArch(double d) {
      dselarch = d;
   }

   public String getSort() {
    return sort;
  }
 public void setSort(String s) {
    sort = s;
 }
 public String getNumOfPages() {
  return numOfPages;
}
public void setNumOfPages(String s) {
  numOfPages = s;
}



   public String getFromDate() {
     return fromDate;
   }

  public void setFromDate(String s) {
    fromDate = s;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String s) {
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

  public String getInvalidDateRangeMsg() {
    return invalidDateRangeMsg;
  }

  public void setInvalidDateRangeMsg(String s) {
    invalidDateRangeMsg = s;
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

  public void setPageList(Vector list) {
  this.archlist = list;
}

public Vector getPageList() {
  return archlist;
}

public void setHasPrev(Boolean b) {
this.hasPrev = b;
}

public boolean getHasPrev() {
return hasPrev.booleanValue();
}


public void setHasNext(Boolean b) {
this.hasNext = b;
}

public boolean getHasNext() {
return hasNext.booleanValue();
}

public void setJumpPageNum(String b) {
JumpPageNum = b;
}

public String getJumpPageNum() {
return JumpPageNum;
}




}
