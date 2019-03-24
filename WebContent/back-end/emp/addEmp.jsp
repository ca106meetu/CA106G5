<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.emp.model.*"%>

<%
  EmpVO empVO = (EmpVO) request.getAttribute("empVO");
%>
<html lang="zh-Hant-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
     <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>員工資料新增 - addEmp.jsp</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
    
<div class="container justify-content-center">
<div class="row">
<div class="col">
			<center>
		     <h3>員工資料新增 - addEmp.jsp</h3>
			</center>
			<center>
		     <h4><a href="select_page.jsp">回首頁</a></h4>
	    	</center>
			<center>
			<h3>資料新增:</h3>
			</center>
<%-- 錯誤表列 --%>
<center>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</center>
<center>
<FORM METHOD="post" ACTION="emp.do" name="form1" enctype="multipart/form-data">
<table>
    <tr>
		<td>員工密碼:</td>
		<td><input type="TEXT" name="emp_pw" class="form-control" 
			 value="<%= (empVO==null)? "1234" : empVO.getEmp_pw()%>" /></td>
	</tr>
	<tr>
		<td>員工姓名:</td>
		<td><input type="TEXT" name="emp_name" class="form-control"
			 value="<%= (empVO==null)? "陸游" : empVO.getEmp_name()%>" /></td>
	</tr>
	<tr>
		<td>員工生日:</td>
		<td>
		<input name="emp_bday" id="f_date1" type="text" class="form-control">
		</td>
	</tr>	
	<tr>
		<td>員工電子郵件信箱:</td>
		<td><input type="TEXT" name="emp_email" class="form-control"
			 value="<%= (empVO==null)? "jack931@gmail.com" : empVO.getEmp_email()%>" /></td>
	</tr>
	<tr>
		<td>員工手機:</td>
		<td><input type="TEXT" name="emp_pho" class="form-control"
			 value="<%= (empVO==null)? "098885761" : empVO.getEmp_pho()%>" /></td>
	</tr>	
	<tr>
		<td>員工性別:</td>
		<td><input type="TEXT" name="emp_gend" class="form-control" 
			 value="<%= (empVO==null)? "男性" : empVO.getEmp_gend()%>" /></td>
	</tr>
	 <tr>
		<td>員工大頭照:</td>
		<td><input type="file" name="emp_pic" onchange='readURL(this)' class="form-control"/><br>
		<img class='pic' src='data:img/png;base64,${encodeText}'  ${(empVO.emp_pic==null) ? 'style="display:none"' : ''}></td>
	</tr>
	<tr>
		<td>員工帳號狀態:</td>
		<td>
		<select name="emp_state">
		<c:forEach var='emp_state' items='${eS}'>
		<option value='${eS.indexOf(emp_state)}' 
							${empVO.emp_state==eS.indexOf(emp_state) ? 'selected' : '' }> ${emp_state}		
		</c:forEach>
		</select>
<%-- 			 value="<%= (empVO==null)? "0" : empVO.getEmp_state()%>" /></td> --%>
	</tr>
	<tr>
		<td>員工就職時間:</td>
		<td><input name="emp_hday" id="f_date2" type="text" class="form-control"></td>
	</tr>
	<tr>
		<td>員工居住地:</td>
		<td><input type="TEXT" name="emp_address" class="form-control"
			 value="<%= (empVO==null)? "" : empVO.getEmp_address()%>" /></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" class="btn btn-outline-info">
</FORM>
</center>

</div>
</div>
</div>
  
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
  
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<% 
  java.sql.Date emp_bday = null;
  try {
	  emp_bday = empVO.getEmp_bday();
   } catch (Exception e) {
	  emp_bday = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date emp_hday = null;
  try {
	  emp_hday = empVO.getEmp_hday();
   } catch (Exception e) {
	   emp_hday = new java.sql.Date(System.currentTimeMillis());
   }
%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>

<style>
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
</style>

<script>
	function readURL(input){
		var reader = new FileReader();
	  		reader.onload = function (e) {
	 					$(".pic").attr('src', e.target.result).css("display","");
	 			
			}
			reader.readAsDataURL(input.files[0]);
	}
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=emp_bday%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           //minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
        
        $('#f_date2').datetimepicker({
 	       theme: '',              //theme: 'dark',
 	       timepicker:true,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=emp_hday%>', // value:   new Date(),
            //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
            //startDate:	            '2017/07/10',  // 起始日
            //minDate:               '-1970-01-01', // 去除今日(不含)之前
            //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
         });
        
        
   
        // ----------------------------------------------------------以下用來排定無法選擇的日期-----------------------------------------------------------

        //      1.以下為某一天之前的日期無法選擇
        //      var somedate1 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});

        
        //      2.以下為某一天之後的日期無法選擇
        //      var somedate2 = new Date('2017-06-15');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});


        //      3.以下為兩個日期之外的日期無法選擇 (也可按需要換成其他日期)
        //      var somedate1 = new Date('2017-06-15');
        //      var somedate2 = new Date('2017-06-25');
        //      $('#f_date1').datetimepicker({
        //          beforeShowDay: function(date) {
        //        	  if (  date.getYear() <  somedate1.getYear() || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() <  somedate1.getMonth()) || 
        //		           (date.getYear() == somedate1.getYear() && date.getMonth() == somedate1.getMonth() && date.getDate() < somedate1.getDate())
        //		             ||
        //		            date.getYear() >  somedate2.getYear() || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
        //		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
        //              ) {
        //                   return [false, ""]
        //              }
        //              return [true, ""];
        //      }});
        
</script>
</html>