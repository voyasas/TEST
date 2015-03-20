package com.fs.app.tpascheda.servlets;
import java.io.*;
import java.util.Vector;
import java.util.Map;
import java.util.HashMap;



import javax.servlet.*;
import javax.servlet.http.*;
import com.fs.is.configuration.ConfigException;
//import com.fs.app.tpadownload.utils.dumprequest;
import com.fs.is.state.IState;
import com.fs.is.state.StateFactory;

import com.fs.is.cmd.*;
import com.fs.is.ids.*;
import com.fs.is.ids.exceptions.*;
import com.fs.is.log.LOG;




/**
 *
 * This servlet returns the Summary of assets by investment
 */
public class TPASchedABridgeServlet
    extends HttpServlet {



    public static final String tojsp = "/jsp/handler.jsp";
    public static final String todl = "/jsp/scheda2.pdf";

    public void doGet (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
    {

          doPost(req,res);

    }

 public void doPost (HttpServletRequest req, HttpServletResponse res)
    throws ServletException, IOException
    {
       // System.out.println("In do Post");
        String s = getDestination(req);
         ServletContext SD  = getServletContext();
         RequestDispatcher RD =  SD.getRequestDispatcher(s);
         RD.forward(req,res);



    }
 public String getDestination (HttpServletRequest req)
 {

   // new dumprequest(req);
    //start by getting the selected request item

     if (req.getParameter("btnBack.x") != null) //do not validate
     {
        return tojsp;
    }


    return todl;
    //begin processing for ready request

    }
    //end processing for ready request
    //begin processing for pending request

 }


