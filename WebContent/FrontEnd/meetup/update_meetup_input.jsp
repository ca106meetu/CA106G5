<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup.model.*"%>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<% 
	MeetupVO meetupVO =(MeetupVO) request.getAttribute("meetupVO");
	//EmpServlet.java (Concroller) 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<html>
<head>
<!-- DateTimer -->
	
<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
<!-- page label -->    
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  	<link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
<title>聯誼內容修改 update Meetup Content</title>

<style>
  img{
	 	width:300px;
	 	height:auto;
	 }
	 
  .headIntro{
        height: 400px;
        margin-bottom: 20px;
        
      }
      
   #rep{
      	display:none;      
      }
      
   *{
      	font-family:微軟正黑體;
      }
      
   .heart{
   		margin: 16px 16px 0px 16px;
   	  }
   #btnRep{
   		margin-bottom:6px;
   	  }
   
   .HeartnRep{
   	  	margin-top:5px;
      }
      
</style>

<style>
  	
  table {
	width: 450px;
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
<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
	<c:forEach var="msg" items="${errorMsgs}">
		<li style="color:red">${msg}</li>
	</c:forEach>
	</ul>
</c:if>

<FORM METHOD="POST" ACTION="meetup.do" name="form1" enctype='multipart/form-data'>
<table>
	<tr>
		<th>聯誼編號</th>
		<td><input type="text" name="meetup_ID" size="45" value="<%=meetupVO.getMeetup_ID()%>" readonly/></td>
	</tr>
	
	<tr>
		<th>聯誼名稱</th>
		<td><input type="text" name="meetup_name" size="45" value="<%=meetupVO.getMeetup_name()%>"/></td>
	</tr>	
	<tr>		
		<th>聯誼日期</th>
		<td><input type="text" name="meetup_date" id="f_date1"/></td>
	</tr>	
	<tr>
		<th>聯誼地址</th>
		<td><input type="text" name="meetup_loc" size="45" value="<%=meetupVO.getMeetup_loc()%>" /></td>
	</tr>	
	<tr>	
		<th>聯誼狀態</th>
		<td><input type="hidden" name="meetup_status" size="45" value="<%=meetupVO.getMeetup_status()%>"/><%=meetupVO.getMeetup_status()%></td>
	</tr>	 
	<tr>
		<th>聯誼封面照</th>
		<td><input type="file" name="meetup_pic" size="45" id="imgUpload" value=""/>
			
			<br>
			<img class='pic' src='data:img/png;base64,${encodeText}'/></td>
			
	</tr>		
	<tr>	
		<th>聯誼資訊</th>
		<td><input type="text" name="meetup_info" size="45" value="<%=meetupVO.getMeetup_info()%>"/></td>			
	</tr>
</table>
<br>
<input type="hidden" name="action" value="update">
<button type="submit" >送出修改 </button>
</FORM>

<script>



$('#imgUpload').change(function(){
    //當檔案改變後，做一些事 
   readURL(this);   // this代表<input id="imgUpload">
 });
 
function readURL(input){
	var reader = new FileReader();
  	reader.onload = function (e) { $(".pic").attr('src', e.target.result); }
	reader.readAsDataURL(input.files[0]);
}
</script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=meetupVO.getMeetup_date()%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '-1970-01-01+3M'  // 去除今日(不含)之後
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
<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
</body>
</html>
