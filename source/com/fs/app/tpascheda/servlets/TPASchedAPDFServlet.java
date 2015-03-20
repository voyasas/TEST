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
import com.fs.is.configuration.*;


public class TPASchedAPDFServlet extends HttpServlet{
   protected String sconfig = "tpascheda.xml";
  public void doGet (HttpServletRequest req, HttpServletResponse res)
     throws ServletException, IOException
     {

           doPost(req,res);

     }

  public void doPost (HttpServletRequest req, HttpServletResponse res)
     throws ServletException, IOException
     {
       String s;
       IConfiguration appConfig = null;
          try {
            appConfig = ConfigurationFactory.getConfiguration(sconfig);
            s = appConfig.getAttribute("section/Constant", "pdf/file1",
                                              "site");
          }
          catch (ConfigException ex) {
            s = null;
          }

        InputStream fileIn = new FileInputStream(s);

        res.setContentType( "application/pdf; charset=ISO8859_1" ) ;
        String shdr = "attachment; filename=\"" + "scheda.pdf" + "\"" ;
        //res.addHeader( "Content-Disposition", shdr ) ;
          ServletOutputStream svout = res.getOutputStream() ;
          while( true)
          {
            int i = fileIn.read();

            if (i == -1)
            {
              break;
            }
            svout.write(i);
          }
          fileIn.close();
          svout.flush();
          svout.close();








     }

}
