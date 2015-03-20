package com.fs.app.tpascheda.mvc;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;

import com.fs.app.tpahomeca.bo.TPAPlanAuthorizationEntitlementBean;
import com.fs.app.tpahomeca.exception.NotAuthorizedException;
import com.fs.app.tpahomeca.exception.ServiceException;
import com.fs.app.tpahomeca.wswraper.TPAHomeCAProxy;
import com.fs.is.configuration.ConfigException;
import com.fs.is.configuration.ConfigurationFactory;
import com.fs.is.configuration.IConfiguration;
import com.fs.is.log.LOG;
import com.fs.is.state.IState;
import com.fs.is.state.StateFactory;
import com.fs.is.tags.handler.IBusinessInteraction;

public class RangeEntry5500_PreDisplay implements
		IBusinessInteraction {

	protected IState state;

	protected HttpServletRequest request;

	protected String sconfig = "tpascheda.xml";

	protected String slogin_id = null;

	protected String saccess_level = null;

	protected String saccessor_type = null;

	SimpleDateFormat myFormat = null;

	RangeEntry5500_Bean bean =null;

	public RangeEntry5500_PreDisplay() {

	}

public void interact(HttpServletRequest inrequest) throws Exception {
	    //System.out.println("RangeEntry5500_PreDisplay");
		this.request = inrequest;
		myFormat = new java.text.SimpleDateFormat("MM/dd/yyyy");
		boolean firstVisitThisSession;
		try
		{
			//Initial setup

			 bean = (RangeEntry5500_Bean) request.getAttribute("FormBean");
                         LOG.DEBUG (sconfig, "Checking Headers");
                         LOG.DEBUG (sconfig, "Checking Headers");
                         LOG.DEBUG (sconfig, "Checking Headers");
                         LOG.DEBUG(sconfig,"attr  value - input" + inrequest.getAttribute("sm_user"));
                         LOG.DEBUG(sconfig,"attr  value - class" + request.getAttribute("sm_user"));
                         LOG.DEBUG(sconfig,"header  value - input" + inrequest.getHeader("sm_user"));
                         LOG.DEBUG(sconfig,"header  value - class" + request.getHeader("sm_user"));
                         LOG.DEBUG (sconfig, "Checking Headers");
                        LOG.DEBUG (sconfig, "Checking Headers");
                        LOG.DEBUG (sconfig, "Checking Headers");


			this.state = (IState)StateFactory.getState(request);
			// to ensure the user is authorized to access the application
			firstVisitThisSession = checkLoginInfo();


			/*if(request.getAttribute("sm_user") != null )
			{
				state.setAttribute("sm_user",request.getAttribute("sm_user"));
			}
			else if(state.getAttribute("sm_user") != null)
			{
				request.setAttribute("sm_user",state.getAttribute("sm_user"));
			}*/

			String s;
			IConfiguration appConfig = null;
			try
			{
				appConfig = ConfigurationFactory.getConfiguration("tpascheda.xml");
				s = appConfig.getAttribute("section/Constant", "DISPLAY/SCHEDA","months");
			}
			catch (ConfigException ex)
			{
				s = null;
			}
			if (s == null)
			{
				s = "1";
			}
			else if (s.trim().length() == 0)
			{
				s = "1";
			}
			String sdm = " ";
			Integer I = new Integer(s);
			if (I.intValue() == 1)
			{
				sdm = "(A Date Range is required and cannot exceed 1 month)";
			}
			else
			{
				sdm = "(A Date Range is required and cannot exceed " + I.toString()+ " months.)";
			}
			bean.setDateMessage(sdm);
			/*
			  New Change on 09/14/2006 by Brijesh Singh for Report Home Page integration
			*/
			if(inrequest.getParameter("PLANNUMBER")!=null && inrequest.getParameter("PLANNUMBER").trim().length()>0)
				bean.setPlanNumber(inrequest.getParameter("PLANNUMBER").trim());
			/* Chnage End */
			String sfrom = (String) state.getAttribute("fromdate");
			String sto = (String) state.getAttribute("todate");
			if (sfrom == null)
			{
				Calendar rightNow = Calendar.getInstance();
				rightNow.set(Calendar.DAY_OF_MONTH, 1);
				rightNow.add(Calendar.DAY_OF_MONTH, -1);
				sto = myFormat.format(rightNow.getTime());
				rightNow.set(Calendar.DAY_OF_MONTH, 1);
				sfrom = myFormat.format(rightNow.getTime());
			}
			if (!(bean.hasFailedEdits()))
			{
				bean.setToDate(sto);
			}
			if (bean.hasFailedEdits())
			{
				bean.setPlanNumber((String) request.getParameter("planNumber"));
				if (bean.getToDate().trim().equals(""))
				{
					bean.setToDate((String) request.getParameter("toDate"));
				}
				return;
			}
			else
			{
				bean.setNextPage("SELECT_ARCH_5500");
			}
		}
		catch(NotAuthorizedException exp)
		{
			throw new NotAuthorizedException();
		}
	}	//************************** Private Methods

	// *************************************
private boolean checkLoginInfo() throws Exception
{
	boolean isFirstVisit = false;
	//Vector toStoreInSession = new Vector();
	if (state.getAttribute("getUserTPAEntitlement") == null) {
		TPAHomeCAProxy ws = new TPAHomeCAProxy();
		try
		{
			Vector v = ws.getUserTPAEntitlement(request,"TPASCHEDA");
			this.state.setAttribute("getUserTPAEntitlement",v);
			Iterator ite = v.iterator();
			while (ite.hasNext())
			{
				TPAPlanAuthorizationEntitlementBean actBean = (TPAPlanAuthorizationEntitlementBean) ite.next();
				printBeanValue(actBean);
				if (actBean.getWebApplicationId().equalsIgnoreCase("tpascheda"))
				{
					if (actBean.getIsfunctionAuthorized().equalsIgnoreCase("true"))
					{
						isFirstVisit = true;
						return true;
						//state.setAttribute("sm_user",actBean.getUserID());
						//toStoreInSession.add(actBean);
					}
				}
			}
		} catch (NotAuthorizedException exp) {
			//exp.printStackTrace();
			throw new NotAuthorizedException();
		} catch (ServiceException exp) {
			//exp.printStackTrace();
			throw new ServiceException();
		}
		/*if(isFirstVisit)
		{
			this.state.setAttribute("getUserTPAEntitlement",toStoreInSession);
		}*/
	}
	return isFirstVisit;

}

	void printBeanValue(TPAPlanAuthorizationEntitlementBean actBean) {
		System.out.println("\n ******************* \n");
		System.out.println(actBean.getEntryType());
		System.out.println(actBean.getIsfunctionAuthorized());
		System.out.println(actBean.getPlanAccessType());
		System.out.println(actBean.getRoleName());
		System.out.println(actBean.getTpaCode());
		System.out.println(actBean.getTpaName());
		System.out.println(actBean.getWebApplicationDesc());
		System.out.println(actBean.getWebApplicationId());
		System.out.println(actBean.getWebApplicationURL());
		System.out.println("\n ******************* \n");
		System.out.println("\n");

	}
}

