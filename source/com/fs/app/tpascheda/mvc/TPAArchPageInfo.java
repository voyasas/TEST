package com.fs.app.tpascheda.mvc;

import java.util.Collection;
import java.util.Vector;
import java.util.TreeSet;
import com.fs.app.tpascheda.data.SchedAArchiveEntry;
import java.util.Iterator;
import com.fs.is.configuration.IConfiguration;
import com.fs.is.configuration.ConfigurationFactory;
import com.fs.is.configuration.*;

public class TPAArchPageInfo {
  boolean gotostart;
  boolean bhasPrev;
  boolean bhasNext;
  int numOfPages;

  TreeSet list;
  int ipage;
  int prtsperpage = 20;
  Vector v;
  SchedAArchiveEntry pd;
  public TPAArchPageInfo() {
    gotostart = false;
    bhasPrev = false;
    bhasNext = false;
    ipage = 1;
  }
  public void setGotoStart(boolean b){
    gotostart = b;
    if (gotostart)
    {
      ipage = 1;
    }
  }
  public void setList(TreeSet c)
  {
    list = c;
  }
  public void setPageNum(Integer I)
  {
    ipage = I.intValue();
  }
  public Integer getPageNum()
  {
    return new Integer(ipage);
  }
  public void Calculate()
  {
    String s;
    IConfiguration appConfig = null;
    try {
      appConfig = ConfigurationFactory.getConfiguration("tpascheda.xml");
      s = appConfig.getAttribute("section/Constant", "DISPLAY/SCHEDA",
                                        "max");
    }
    catch (ConfigException ex) {
      s = null;
    }
    if (s == null)
    {
      s = "20";
    }
    prtsperpage = new Integer(s).intValue();
    bhasPrev = false;
    bhasNext = false;
    v = new Vector();
    int i = 0;
    if (list.size() == 0)
    {
    bhasPrev = false;
    bhasNext = false;
    return;
    }
    if (gotostart)
        {
          ipage = 1;
        }

    int iend = prtsperpage * ipage;
    if (iend > list.size())
    {
      iend = list.size();
    }
    //int istart = iend - prtsperpage;
    int istart = prtsperpage * (ipage -1);

    Iterator IT = list.iterator();

    //figure out number of pages
    numOfPages = (int) list.size()/prtsperpage;
    if ( list.size()%prtsperpage > 0 ) {
        numOfPages++;
    }


    if (gotostart)
    {
       istart = 0;
       iend = prtsperpage;
    }
     while ( IT.hasNext())
      {
        i++;
        pd = (SchedAArchiveEntry) IT.next();

        if (i > istart)
        {
          v.add(pd);
        }
        if( i >= iend)
        {
          break;
        }
        if (istart > 0)
        {
          bhasPrev = true;
        }
        if (iend < list.size())
        {
          bhasNext = true;
        }
    }


  }


  public Boolean hasPrev()
  {
    return new Boolean(bhasPrev);
  }
  public Boolean hasNext()
  {
    return new Boolean(bhasNext);
  }

  public Vector getPageList()
  {

  return v;


  }


}
