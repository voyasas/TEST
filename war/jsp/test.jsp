<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="com.fs.app.tpascheda.mvc.*" %>
<%@ page import="com.fs.app.tpascheda.data.TPAPlanListingInfo" %>
<html>

<head>
<script language="JavaScript" type="text/javascript">
window.history.go(1)
</script>
<title>Plan Listing</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href="style.css" rel="stylesheet" type="text/css">

</head>

<body bgcolor="#FFFFFF" marginwidth="0" marginheight="0">
  <%@ taglib uri="ifs.tld" prefix="ifs" %>
<%@ taglib uri="prdsalestags.tld" prefix="prdsal" %>


<!-- Global Header - header.inc -->

<form  name="planListing" action="handler.jsp" method="post">

<INPUT TYPE=HIDDEN name="page" value= "RANGE_ENTRY_5500" >


<jsp:useBean class="com.fs.app.tpascheda.mvc.TPPTpaPlanListingBean" id="FormBean" scope="request"/>



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

<!-- BEGIN SUB level table -->
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



    <td ><img src="/tpascheda/images/hdr_tpa_sublevel.jpg" alt="photo" width="144" border="0" height="42"><br>

    </td>

		<!-- 1 pixel Grey border - MID   *** SET BACK TO ORANGE ***-->

		<td bgcolor="#FF6600" width="1"><img src="/tpascheda/images/shim.gif" width="1" alt="" border="0"  ></td>

		<!-- 10 WIDE SPACER COLUMN  -->

		<td width="10"><img src="/tpascheda/images/shim.gif" width="2" alt="" border="0"  ></td>

		<!-- 294 TITLE  -->

		<td width="294" align="left" class="t29">Plan Listing</td>

	  	<!-- 153 SPACE ORANGE BGRND  -->

		<td width="153"><img src="/tpascheda/images/shim.gif" width="8"   alt="" border="0"  ></td>

	  	<!-- 136 SEARCH  -->


		<td width="136" align="right" bgcolor="#FF6600">

</td>

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
       <prdsal:menu navpage="TPPPLAN" menuxml="tpaschedamenu.xml"/>

    </td>

    <!-- cell spacing column to hold gutter dimensions -->
    <td width="10"><img src="/tpascheda/images/shim.gif" height="1" hspace="4" width="2" alt="" border="0" /></td>
    <td valign="top" width="608"> <table width="100%" border="0" cellspacing="0" cellpadding="3">


        <tr>
          <td width="26%" class="t2" align="right"><nobr>TPA Code and Name:</nobr></td>
          <td width="33%"> <select id="selectcode" name="selectcode"  onchange="submitTheForm()">

              </select> </td>
            </td>

	        <td>
	            <INPUT TYPE="IMAGE" NAME="submit" src="/tpascheda/images/btn_refresh.gif" onclick="setPagingDirection('refresh')">
	        </td>

        </tr>
        <tr>
          <td colspan="2"class="t8">Plans available to more than
            one TPA code will be counted once. <br> Month End Cash Values reflect SDBA amounts.
            <br>
            <br>
            &nbsp;&nbsp;&nbsp;&nbsp;<b><u><a href="javascript:OpenTPAAltWindow('handler.jsp?page=TPPPRINTPLANLISTING&submit.x=33',1,0)">Printer Friendly Version </a></u></b>
           <table width="100%" border="0" cellspacing="0" cellpadding="0">

                <tr><td colspan="4">&nbsp;</td></tr>
                <tr><td colspan="4">&nbsp;</td></tr>

                <tr>
                    <td width="30%">&nbsp;</td>
                    <td width="5%">&nbsp;</td>
                    <td align="left" width="50%">Search:</td>
                    <td width="15%">&nbsp;</td>
                </tr>

                <tr>
                    <td align="right" class="t8" width="30%">
                        Plan Number:<input type="radio" name="filterType" value="Plan Number" checked>
                        <br>
                        Plan Name:<input type="radio" name="filterType" value="Plan Name">
                    </td>
                    <td width="5%" >&nbsp;</td>

                    <td class="t8" width="50%" align="left"><input type="text" name="filterText" size="25" maxlength="35" ></td>

                    <td class="t8" ><img src="/tpascheda/images/btn_go.GIF" align="left" onclick="applyFilt()"></td>

                </tr>
           </table>
           </td>

        </tr>
        <tr>
          <td colspan="3"><img src="/tpascheda/images/shim.gif" height="20" width="100%"></td>
        </tr>
      </table>


<table width="760" border="0" cellspacing="0" cellpadding="1" >



  <tr>
          <td class="inga1" width="10%" align="left"><b><a href="#" onclick="resortPage('<%=TPPConstants.planListInfoSortBy_Code_PlanNum_CashVal%>')" >TPA<br>Code</a></b></td>
          <td class="inga1" width="10%" align="left"><b><a href="#" onclick="resortPage('<%=TPPConstants.planListInfoSortBy_PlanNumber%>')" >Plan<br>Number</a></b></td>
          <td class="inga1" width="30%" align="left"><b><a href="#" onclick="resortPage('<%=TPPConstants.planListInfoSortBy_PlanName%>')" >Plan<br>Name</a></b></td>
          <td class="inga1" width="10%" align="left">Plan<br>Type</td>
          <td class="inga1" width="15%" align="left">Product<br>Description</td>
          <td class="inga1" width="10%" align="center">Month End Active Plan Participants</td>
          <td class="inga1" width="15%" align="center"><a href="#" onclick="resortPage('<%=TPPConstants.planListInfoSortBy_CashValue%>')" >Month End Plan Cash Value</a></td>
  </tr>




  <tr><td colspan="7"> &nbsp </td></tr>

</table></td></tr></table></td></tr></table>






<!-- End Body Container B -->
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
