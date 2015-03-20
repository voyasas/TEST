<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.fs.app.tpascheda.mvc.*" %>
<%@ page import="com.fs.is.configuration.ConfigurationFactory" %>
<%@ page import="com.fs.is.configuration.IConfiguration" %>

<%
IConfiguration objConfig = (IConfiguration)ConfigurationFactory.getConfiguration("tpascheda.xml");
String topMessage = objConfig.getAttribute("section/Constant", "5500_MESSAGES" + "/" + "TOP_MESSAGE", "value");
%>
<html>

<head>
<script language="JavaScript" type="text/javascript">
window.history.go(1)
</script>
<title>5500 Document Archive</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">

</head>

<body bgcolor="#FFFFFF" marginwidth="0" marginheight="0">
  <%@ taglib uri="ifs.tld" prefix="ifs" %>
<%@ taglib uri="prdsalestags.tld" prefix="prdsal" %>

<SCRIPT language=javascript>
	function Xpopup(url,windowname,width,height,scrollbars, toolbar, menubar, location, resizable, status) 
	{
		width=(width)?width:screen.width/3;
		height=(height)?height:screen.height/3;
		var screenX = (screen.width/2 - width/2);
		var screenY = (screen.height/2 - height/2)-100;
		var features= "width=" + width + ",height=" + height;
		features += ",screenX=" + screenX + ",left=" + screenX;
		features += ",screenY=" + screenY  +",top=" + screenY;
		features += ",scrollbars="+scrollbars+",toolbar="+toolbar+",menubar="+menubar+",location="+location+",resizable="+resizable+",status="+status ;
		//alert("Features"+features);
		var mywin=window.open(url, windowname, features);
		if (mywin) 
			mywin.focus();
		return mywin;
	}
</SCRIPT>


<!-- Global Header - header.inc -->

<form  name="rangeEntry5500" action="handler.jsp" method="post">

<INPUT TYPE=HIDDEN name="page" value= "RANGE_ENTRY_5500" >


<jsp:useBean class="com.fs.app.tpascheda.mvc.RangeEntry5500_Bean" id="FormBean" scope="request"/>


<!-- Begin Spacer table -->
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
                    <td width="606" align="right" valign="top"><a class="t10" href="/static/tpahome/secure/index.shtml">Home</a>
                    &nbsp;|&nbsp; <a class="t10" href="http://www.ingretirementplans.com">ingretirementplans.com</a>
                    &nbsp;|&nbsp; <a class="t10" href="/static/tpahome/secure/directory/index.shtml">Help</a>
		    &nbsp;|&nbsp; <A class=t10 onclick="Xpopup(this.href,'ContactUs',850,550,'yes','yes','no','no','no','yes'); return false;" 
        					href="https://www2.ingretirementplans.com/online_forms/tpa_contact.shtml">Contact Us</A></td>

				</tr>

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



<!-- Begin SUB level table -->
<table border="0" width="760" " border="0" cellspacing="0" cellpadding="0" bgcolor="#ff6600">
	<!-- 1 pixel Grey border - TOP -->

	<tr>
		<td width="10" bgcolor="#ffffff"><img src="/tpascheda/images/shim.gif"  height="1" alt="" border="0"  ></td>

		<td colspan="9" bgcolor="#999999"><img src="/tpascheda/images/shim.gif" height="1" alt="" border="0"  ></td>
	</tr>

	<tr>
		<!-- 10 WIDE SPACER COLUMN  -->
		<td width="10" bgcolor="#ffffff"><img src="/tpascheda/images/shim.gif" width="2"  alt="" border="0"  ></td>

		<!-- 1 pixel Grey border LEFT -->
		<td bgcolor="#999999" width="1"><img src="/tpascheda/images/shim.gif" width="1"></td>

		<!-- sub level image -->
        <td >
            <img src="/tpascheda/images/hdr_tpa_sublevel.jpg" alt="photo" width="144" border="0" height="42"><br>
        </td>

		<!-- 1 pixel Grey border - MID   *** SET BACK TO ORANGE ***-->
		<td bgcolor="#FF6600" width="1"><img src="/tpascheda/images/shim.gif" width="1" alt="" border="0"  ></td>

		<!-- 10 WIDE SPACER COLUMN  -->
		<td width="10"><img src="/tpascheda/images/shim.gif" width="2" alt="" border="0"  ></td>

		<!-- 294 TITLE  -->
		<td width="400" align="left" class="t29">5500 Package Archive</td>

	  	<!-- 153 SPACE ORANGE BGRND  -->
		<td width="50"><img src="/tpascheda/images/shim.gif" width="8"   alt="" border="0"  ></td>

	  	<!-- 136 SEARCH  -->
		<td width="136" align="right" bgcolor="#FF6600"></td>

	  	<!-- 1 pixel Grey border -RIGHT  -->
		<td bgcolor="#999999" width="2"><img src="/tpascheda/images/shim.gif" width="1"  alt="" border="0"  ></td>
	</tr>

	<!-- 1 pixel Grey border -BOTTOM -->
	<tr>
		<td width="10" bgcolor="#ffffff"><img src="/tpascheda/images/shim.gif" width="2" height="1"  alt="" border="0"  ></td>

		<td colspan="9" bgcolor="#999999"><img src="/tpascheda/images/shim.gif" width="1" height="1" alt="" border="0"  ></td>
	</tr>

</table>
<!-- END  SUB level table -->


<table width="760" border="0" cellspacing="0" cellpadding="0" bgcolor="#FFFFFF">

  <tr bgcolor="#FFFFFF"><!-- cell spacing column to hold gutter dimensions -->
    <td><img src="/tpascheda/images/shim.gif" height="2" vspace="5" width="1" alt="" border="0" /></td>
  </tr>

</table>


<table width="760" border="0" cellspacing="1" cellpadding="0" bgcolor="#ffffff">
  <tr>
    <td width="142" align="left" valign="top">
      
       <prdsal:menu navpage="PADMRPTRPT" menuxml="tpiRptmenu.xml"/>
    </td>

    <!-- cell spacing column to hold gutter dimensions -->
    <td width="10"><img src="/tpascheda/images/shim.gif" height="1" hspace="4" width="2" alt="" border="0" /></td>
    <td valign="top" width="608">
    <%=topMessage%>
    <table width="100%" border="0" cellspacing="0" cellpadding="3">


<!-- Printer Friendly link thru go button -->


  <tr>
     <td colspan="3"><img src="/tpascheda/images/shim.gif" height="20" width="100%"></td>
  </tr>
</table>



    <!-- *************************************************************** -->
    <!-- **************  The main user interface table *****************-->
    <!-- *************************************************************** -->

 <ifs:ifError>
                    <table width="80%" border="0">
                      <ifs:errorResponse>
                      <tr>
                        <td align="center"><font face="Verdana" color='red' size="2"><%=message%></font></td>
                      </tr>
                      </ifs:errorResponse>
                       <tr>
                        <td align="center"><font face="Verdana" color='red' size="2">&nbsp;</font></td>
                      </tr>
                    </table>
                    </ifs:ifError>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td width="30%" class="t8" align="left">Report&nbsp;End&nbsp;Date&nbsp;</td>
        <td width="10%" class="t8"><input type="text" id="toDate" name="toDate" value="<%= FormBean.getToDate() %>" maxlength="10" size="10"></td>
        <td colspan="3" class="t8">&nbsp;</td>
    </tr>
    <tr>
    	<td colspan="5" class="t8">(this will include any packages that match the report end date and three months prior)</td>
    </tr>

     <tr>
        <td colspan="5" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td width="40%" class="t8" align="left"> Plan Number &nbsp;&nbsp;&nbsp;</td>
        <td width="10%" class="t8"><input type="text" id="planNumber" name="planNumber" value="<%= FormBean.getPlanNumber() %>" maxlength="6" size="6"></td>
        <td colspan="3" class="t8">&nbsp;</td>
    </tr>
    <tr>

	   	<%
 if("external".equalsIgnoreCase((String)request.getSession(true).getAttribute("UserType"))) { %>
		       <td width="40%" class="t8" align="left">(Plan Number is optional.)</td>
		<% }%>
        <td colspan="4" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="5" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="5" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td class="t8">&nbsp;</td>
        <td colspan="3" class="t8" align="center"><input type="image" id="btnSubmit" name="btnSubmit" maxlength="6" size="6" src="/tpascheda/images/btn_submit.gif"></td>
        <td class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="5" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="5" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="5" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="5" class="t8">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="5" class="t2">Warning: The time frame requested and/or the number of plans retrieved may cause a delay in  response time.</td>
     </tr>

</table>

</td></tr></table></td></tr></table>



<!-- ***************************** -->
<!-- End Main User Interface table -->
<!-- ***************************** -->



<%@ include file="tpafooter.html" %>


<!--********************** Java Script functions **********************************  -->
<script language="JavaScript" type="text/javascript">

function submitTheForm() {

   document.planListing.submit();
   return true;
}

function resortPage(newOrder){

   document.planListing.sortOrder.value = newOrder;
   document.planListing.pagingDirection.value = "resort";
   document.planListing.submit();
}

function setPagingDirection(newDirection){

   document.planListing.pagingDirection.value = newDirection;
}

function applyFilt(){

   if ( document.planListing.filterText.value == "" ){
        alert("Please type in search criteria");
        return false;
   }

   document.planListing.filterIsApplied.value = "Yes";
   document.planListing.pagingDirection.value = "apply_filter";
   document.planListing.submit();
}

function removeFilter(){

   document.planListing.filterIsApplied.value = "No";
   document.planListing.pagingDirection.value = "remove_filter";
   document.planListing.submit();
}

function pagingJump(){

   document.planListing.pagingDirection.value = "page_jump";
   document.planListing.submit();
}

</script>
<!-- ***********************************************************************************   -->



</form>
</body>
</html>
