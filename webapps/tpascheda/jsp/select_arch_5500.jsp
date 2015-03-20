<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.fs.app.tpascheda.mvc.*" %>
<%@ page import="com.fs.app.tpascheda.data.*" %>

<html>

<head>
<script language="JavaScript" type="text/javascript">
window.history.go(1)

function getPDF()
{
var selArch_value;
	if(document.selectArch5500.selArch.length>0)
	{
			for (i=0;i<document.selectArch5500.selArch.length;i++) 
			{ 
		      if (document.selectArch5500.selArch[i].checked) 
		      { 
		             selArch_value = document.selectArch5500.selArch[i].value; 
		             
		      } 
			} 
	}
	else
	{
		selArch_value=document.selectArch5500.selArch.value;
	 
	}
			
		var url='/tpascheda/jsp/handler.jsp?page=SELECT_ARCH_5500&btnView.x=77&selArch='+selArch_value;
		var windowTitle='PDFWindow';
		window.open(url, windowTitle); 
}

</script>
<title>Select 5500 Audit Package</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">

</head>

<body bgcolor="#FFFFFF" marginwidth="0" marginheight="0">
  <%@ taglib uri="ifs.tld" prefix="ifs" %>
<%@ taglib uri="prdsalestags.tld" prefix="prdsal" %>

<%
String sRef = (String) request.getAttribute("FrmAct");
String sParm = (String) request.getAttribute("FrmPrm");
String sNeedForm  = (String) request.getAttribute("NeedForm");
%>
<!-- Global Header - header.inc -->

<form  name="selectArch5500" action="handler.jsp" method="post">

<INPUT TYPE=HIDDEN name="page" value= "SELECT_ARCH_5500" >
<INPUT TYPE=HIDDEN name="direction" value= "" >

<jsp:useBean class="com.fs.app.tpascheda.mvc.SelectArch5500_Bean" id="FormBean" scope="request"/>

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
                    &nbsp;|&nbsp; <a class="t10" href="https://www2.ingretirementplans.com/online_forms/tpa_contact.shtml">Contact
                    Us</a></td>
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
    <td valign="top" width="608"> <table width="100%" border="0" cellspacing="0" cellpadding="3">


<!-- Printer Friendly link thru go button -->


  <tr>
     <td colspan="3"><img src="/tpascheda/images/shim.gif" height="20" width="100%"></td>
  </tr>
</table>



    <!-- *************************************************************** -->
    <!-- **************  The main user interface tables *****************-->
    <!-- *************************************************************** -->
<table width="100%" border="0" cellspacing="0" cellpadding="2">
<tr>
  <td align="center" class="tws26">
    Select 5500 Audit Package
  </td>
</tr>
<tr>
  <td align="center">
    &nbsp;
  </td>
</tr>
</table>

<table width="100%" border="2" cellspacing="0" cellpadding="2">
    <tr>
        <td width="10%" class="inga1" align="center"><b>Selection</b></td>
        <td width="10%" class="inga1" align="center"><A HREF="javascript:document.f1.submit()"><b></b>Run Date</b></A></td>
        <td width="10%" class="inga1" align="center"><A HREF="javascript:document.f2.submit()"><b></b>Plan Number</b></A></td>
		<td width="10%" class="inga1" align="center"><b>TPA Code</b></td>
        <td width="30%" class="inga1" align="center"><b>Plan Name</b></td>
        <td width="10%" class="inga1" align="center"><b>Report Start Date</b></td>
        <td width="10%" class="inga1" align="center"><b>Report End Date</b></td>
        <td width="10%" class="inga1" align="center"><b>Date Last Viewed</b></td>
    </tr>
    <%
       Vector v = FormBean.getPageList();
       if (v.size() == 0)
       {
       %>
        <tr>
        <td width="10%" class="inga2a" align="center">&nbsp;</td>
        <td colspan=5 align="center"><b> No 5500 audit packages available.</b></td>
        <td width="10%" class="inga2a" align="center">&nbsp;</td>
         </tr>
         </table>
 <table width="100%" border="0" cellspacing="0" cellpadding="2">
        <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td colspan="2" align="center">
            <INPUT TYPE="IMAGE" src="/tpascheda/images/btn_back.gif" NAME="btnBack">
         </td>

         <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
    </tr>
   </form>
   </table>
    <%
       }
       else
       {
        for (int i = 0; i < v.size();i++)
        {
          SchedAArchiveEntry p = (SchedAArchiveEntry) v.elementAt(i);
      %>
    <tr>
        <td width="10%" class="inga2a" align="center"><input type="radio" name="selArch" value="<%=p.getLastViewedId()%>" <% if (p.getLastViewedId().doubleValue() == FormBean.getSelArch()){ %> checked <%}%>  ></td>
        <td width="10%" class="inga2a" align="center"><%=p.getRun_Date()%></td>
        <td width="10%" class="inga2a" align="center"><%=p.getPlan_Number()%></td>
		<td width="10%" class="inga2a" align="center"><%=p.getTPA_Code()%></td>
        <td width="30%" class="inga2a" align="center"><%=p.getPlan_Name()%></td>
        <td width="10%" class="inga2a" align="center"><%=p.getReport_Start_Date()%></td>
        <td width="10%" class="inga2a" align="center"><%=p.getReport_End_Date()%></td>
        <td width="10%" class="inga2a" align="center"><%=p.getLastViewedDate()%></td>

    </tr>

          <%

        }  //en for loop
      %>
    </table>
    <table width="100%" border="0" cellspacing="0" cellpadding="2">
     <tr>
       <%
          if (FormBean.getHasPrev())
          {
          %>
           <td>
         <input type="Image" name="prevpage" src="/tpascheda/images/btn_previous.gif">
           </td>
             <%
          }
           else
           {
             %>
             <td class="inga2a"><b>Start of List</b></td>
             <%
           }
           %>
            <td class="inga2a">&nbsp;</td>
            <td class="inga2a">&nbsp;</td>
            <td class="inga2a">&nbsp;</td>
            <td>&nbsp;</td>
             <td>&nbsp;</td>
              <td>&nbsp;</td>
          <td class="inga2a" align="right">&nbsp;</td>
           <% if (FormBean.getHasNext())
          {
          %>
          <td align="right">
         <input type="Image" name="nxtpage" src="/tpascheda/images/btn_next.gif" width="53" height="15" align="right">
           </td>
           <%
          }
           else
           {
             %>
             <td class="inga2a" align="right"><b>End of List</b></td>
             <%
           }
           %>
          </tr>
        <tr>
        <td>&nbsp;</td>
        <td>&nbsp;</td>
        <td align="left" >
            <INPUT TYPE="IMAGE" src="/tpascheda/images/btn_back.gif" NAME="btnBack">
         </td>
         <td align="right" colspan="2">
            <a href="#" ><img src="/tpascheda/images/btn_view.gif"  border="0" onClick="javascript:getPDF();"></a>
        </td>
        <td>&nbsp;</td>
        <td>&nbsp;</td>

    </tr>
   </form>
   </table>
  <table width="100%" border="0" cellspacing="0" cellpadding="2">
   <form name="JumpForm" method="POST" action="handler.jsp">
  <input type = hidden name="page" value="SELECT_ARCH_5500" />
  <input type = hidden name="jump.x" value="23" />
  <tr>

    <td colspan="7" align="center" valign="bottom" >
      <table width="100%" border="0" cellspacing="0" cellpadding="0">

         <td align="right" valign="center" width="30%">&nbsp;</td>
         <td align="right" valign="center" width="15%">Page</td>

         <td align="center" valign="top" width="5%">
          <select id="jumpPageNum" align="center"  name="jumpPageNum"  onchange="javascript:document.JumpForm.submit()">
            <%
                int pageNum = Integer.parseInt( FormBean.getJumpPageNum() );
                int numPages = Integer.parseInt( FormBean.getNumOfPages() );
                for (int i = 1; i <= numPages; i++) {
            %>
                    <option id="<%= i %>" <% if ( i == pageNum  ){ %><%="selected"%><%}%> > <%=i%>

            <%  }%>
           </select>
          </td>
          <td align="left" valign="center" width="20%">
            &nbsp; of &nbsp; <%=FormBean.getNumOfPages()%>

          </td>
           <td align="right" valign="center" width="30%">&nbsp;</td>
      </table>
    </td>
  </tr>
    <tr>
    <td colspan="7" align="center" valign="bottom" > &nbsp; </td>
    </tr>
  </form>

</table>
<%
       }
       %>
 <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                <td width="10%" class="t8">&nbsp;</td>
                <td width="80%"  valign="top" class="t1">
                  <p>These documents are kept in PDF format for consistent viewing.
                    To view these files a copy of the Adobe Acrobat Reader must
                    be installed on your PC. If you have not already downloaded
                    the Acrobat Reader, click on the button below and follow
                    the instructions.</p>
                </td>
                <td width="10%" class="t8">&nbsp;</td>
              </tr>
              <tr>
                 <td width="10%" class="t8">&nbsp;</td>
               <td width="80%" class="t8">
                <div align="center"><a href="http://www.adobe.com/products/acrobat/readstep.html" TARGET="_blank"><img src="/static/tpahome/secure/images/getacro.gif" width="88" height="31" border="0"></a>
                </div>
               </td>
               <td width="10%" class="t8">&nbsp;</td>

             </tr>
            </table>
</td></tr></table></td></tr></table>



<!-- ****************************** -->
<!-- End Main User Interface tables -->
<!-- ****************************** -->



<%@ include file="tpafooter.html" %>

  <form name="f1" method="POST" action="handler.jsp">
              <input type = hidden name="page"  value="SELECT_ARCH_5500" />
              <input type="hidden" name="sortdate.x" value="24">
            </form>

  <form name="f2" method="POST" action="handler.jsp">
              <input type = hidden name="page" value="SELECT_ARCH_5500" />
              <input type="hidden" name="sortNum.x"  value="24">
            </form>
<!-- ************ END Footer Table *****************  -->


<!--********************** Java Script functions **********************************  -->
<script language="JavaScript" type="text/javascript">

function resortPage(newVal){
alert("got here");
   document.selectArch5500.direction.value = newVal;
   document.selectArch5500.submit() = newVal;
}


</script>
<!-- ***********************************************************************************   -->

<%
if (sNeedForm != null)
{
    if (sNeedForm.compareTo("Yes") == 0)
    {

        %>
<form NAME = "Archive" ACTION="<%=sRef%>">
            <input type="hidden" name="PARAMS" value="<%=sParm%>">

</form>
<script language="JavaScript">
setTimeout('getrpt()',200)
function getrpt() {

document.forms["Archive"].method="POST";
document.forms["Archive"].submit();
}
</script>

    <%
    }
}
    %>


</body>
</html>

