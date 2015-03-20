<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page isErrorPage="true" %>
<%@ page import="com.fs.is.state.*" %>
<%@ page import="com.fs.is.tags.exception.*" %>
<%@ page import="java.io.*" %>
<%@ taglib uri="ifs.tld" prefix="ifs" %>
<HTML>

<script>
	function redirect(redirectPath)
	{
		window.location.href=redirectPath;
	}
</script>


<%

   Throwable th = (Throwable) request.getAttribute("javax.servlet.jsp.jspException");


	

	
	th=((com.fs.is.tags.exception.IFSTagException)th).getRootCause();
	System.out.println("Message is "+th.getMessage());
	if (th != null && (th.getMessage().equalsIgnoreCase("Not Authorized") || ( th.getClass().getName().equals("com.fs.app.tpahomeca.exception.NotAuthorizedException"))  ) )
    {
	%>
		<HEAD>
			<TITLE>Not Authorized!</TITLE>
		</HEAD>

		<BODY ONLOAD="redirect(<%="'AccessControl.jsp'"%>)">
	<%	
	}
	else
	{
	%>
		<HEAD>
		<TITLE>Fatal Error Occurred !</TITLE>
		</HEAD>
		<body>
	<%
	}
	%>
<table width="760" height="10" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
	<tr>
		<td width="760" height="10"><img src="/tpascheda/images/shim.gif" width="760" height="2" vspace="4" alt="" border="0" /></td>
	</tr>
</table>

<!-- End Spacer table -->


<table width="760" height="56" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
	<tr>
		<td width="142" height="56" valign="top">
			<table width="142" height="56" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
				<tr>
                    <td width="10"><img src="/tpascheda/images/shim.gif" width="10" height="8" hspace="1" alt="" border="0" /></td>
                    <td valign="bottom" align="left"><img src="/tpascheda/images/logo.gif" width="142" height="40"></td>
				</tr>
			</table>
		</td>
		<td width="606" height="56" align="right">
			<table width="606" height="56" border="0" cellspacing="0" cellpadding="0" bgcolor="#ffffff">
				<tr>
                  <td width="606" align="right" valign="bottom" class="t20"> </td>
                </tr>

		     </table>
		</td>
	</tr>
	
	<tr>
		<td height="5"><img src="/tpascheda/images/shim.gif" width="1" height="1" vspace="2" alt="" border="0" /></td>
	</tr>
</table>

<table width="760" height="65" border="0" cellspacing="0" cellpadding="0">

    <tr>

		<td width="10" bgcolor="#ffffff"><img src="/tpascheda/images/shim.gif" width="2" height="1" hspace="4" alt="" border="0" /></td>

		<td colspan="9" bgcolor="#999999"><img src="/tpascheda/images/shim.gif" width="1" height="1" alt="" border="0" /></td>

	</tr>

    <tr>

        <td width="10" bgcolor="#ffffff"><img src="/tpascheda/images/shim.gif" width="10" height="8" hspace="1" alt="" border="0" /></td>

        <td bgcolor="#999999" width="1"><img src="/tpascheda/images/shim.gif" width="1" height="1" alt="" border="0" /></td>

	    <td colspan="4" bgcolor="#FF6600"><img src="/tpascheda/images/ing_tpa_topbar.jpg" width="602" height="65"></td>

		<td width="136" align="right" bgcolor="#FF6600"></td>

	    <td bgcolor="#999999" width="1"><img src="/tpascheda/images/shim.gif" width="1" height="1" alt="" border="0" /></td>

    </tr>

    <tr>

		<td width="10" bgcolor="#ffffff"><img src="/tpascheda/images/shim.gif" width="2" height="1" hspace="4" alt="" border="0" /></td>

		<td colspan="9" bgcolor="#999999"><img src="/tpascheda/images/shim.gif" width="1" height="1" alt="" border="0" /></td>

	</tr>

</table>



<table width="760" border="0" rows="3" cols="2" cellspacing="1" cellpadding="0" bgcolor="#ffffff">
  <tr>
    <td width="143" class='tws26' align="left" valign="top" rowspan="3" >
       <br><br>
       &nbsp;&nbsp;&nbsp;<A HREF='/static/tpahome/secure/index.shtml'>Home</A>
    </td>
    
    <td valign="top" width="608"> 
     <!--<table width="100%" border="0" cellspacing="0" cellpadding="3">-->
   
        <P>
        <P valign="center" ALIGN="CENTER"><B><FONT SIZE="5" COLOR="#CC0000">System Error</FONT></B>
        </P>

        <P ALIGN="CENTER"><FONT SIZE="3" COLOR="#CC0000">
        We are sorry that you are experiencing problems with this session. If you have reached this error page multiple times we recommend that you quit out of your browser, then open it again. If errors persist after exiting your browser and re-entering the site, please email: <a href="https://www2.ingretirementplans.com/online_forms/tpa_contact.shtml">TPAWebmaster@us.ing.com</a>
        </FONT></P>
     </td>
  </tr>
    
  <!-- <tr><td>
        <ifs:rootCause/>
  </td></tr> -->
  
  
  <tr><td>
    <%

        if (th != null)
        {
        //    out.println("<H4>"+th.toString()+"</H4>\n");

            PrintWriter pr = new PrintWriter(out);
			
            if (th instanceof javax.servlet.ServletException)
            {
	            th=((javax.servlet.ServletException)th).getRootCause();
            }
            if (th instanceof com.fs.is.tags.exception.IFSTagException)
            {
    	        tagMessage=th.getMessage();
        	    th=((com.fs.is.tags.exception.IFSTagException)th).getRootCause();
            }
            if (th.getMessage() != null)
                pr.println("<H4> RootCause: " + th.getMessage() +  "</H4>");

            pr.println("Exception Name: " + th.getClass().getName());
            th.printStackTrace(pr);
        }

        try
        {
            // clean up the state object
            IState stateObj = (IState) StateFactory.getState(request);
            if ( stateObj != null)
            {
    	        stateObj.removeAttributes();
            }
        }
        catch ( Throwable throwable)
        {
            out.println("<H4>Error removing State</H4>\n");
            out.println("<H4>"+throwable.getMessage()+"</H4>\n");
        }
    %>

   </td></tr>

</BODY>

</HTML>
