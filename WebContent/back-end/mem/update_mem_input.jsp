<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.mem.model.*"%>
<%@ page import="com.meetU.memHobby.model.*"%>
<%@ page import="com.meetU.hobby.model.*"%>
<%@ page import="java.util.*"%>

<%
  MemVO memVO = (MemVO) request.getAttribute("memVO"); //MemServlet.java (Concroller) 存入req的memVO物件 (包括幫忙取出的memVO, 也包括輸入資料錯誤時的memVO物件)
%>

<!doctype html>
<html lang="zh-Hant-TW">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">

<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改 - update_mem_input.jsp</title>

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
<body bgcolor='white'>
<jsp:include page="/Templates/bootstrap4/backHeader.jsp" />
<div class="container justify-content-center">
<div class="row">
<div class="col-6">   
<table id="table-1">
	<tr><td>
		 <h3>會員資料修改 - update_mem_input.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<h3>資料修改:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="mem.do" name="form1" enctype="multipart/form-data">
<table>
	<tr>
		<td>會員ID:<font color=red><b>*</b></font></td>
		<td><%=memVO.getMem_ID()%></td>
	</tr>
	<tr>
		<td>會員密碼:</td>
		<td><input type="TEXT" name="mem_pw" size="45" 
			 value="<%= (memVO==null)? "1234" : memVO.getMem_pw()%>" /></td>
	</tr>
	<tr>
		<td>會員姓名:</td>
		<td><input type="TEXT" name="mem_name" size="45" 
			 value="<%= (memVO==null)? "陸游" : memVO.getMem_name()%>" /></td>
	</tr>
	<tr>
		<td>會員帳號:</td>
		<td><input type="TEXT" name="mem_acc" size="45" 
			 value="<%= (memVO==null)? "LuYou" : memVO.getMem_acc()%>" /></td>
	</tr>
	<tr>
		<td>會員暱稱:</td>
		<td><input type="TEXT" name="mem_nickname" size="45" 
			 value="<%= (memVO==null)? "放翁" : memVO.getMem_nickname()%>" /></td>
	</tr>
	<tr>
		<td>會員生日:</td>
		<td><input name="mem_bday" id="f_date1" type="text"></td>
	</tr>	
	<tr>
		<td>會員電子郵件信箱:</td>
		<td><input type="TEXT" name="mem_email" size="45" 
			 value="<%= (memVO==null)? "jack931@gmail.com" : memVO.getMem_email()%>" /></td>
	</tr>
	<tr>
		<td>會員手機:</td>
		<td><input type="TEXT" name="mem_pho" size="45" 
			 value="<%= (memVO==null)? "0988885761" : memVO.getMem_pho()%>" /></td>
	</tr>	
	<tr>
		<td>會員性別:</td>
		<td><input type="TEXT" name="mem_gend" size="45" 
			 value="<%= (memVO==null)? "男性" : memVO.getMem_gend()%>" /></td>
	</tr>
	<tr>
		<td>會員大頭照:</td>
		<td><input type="file" name="mem_pic" onchange='readURL(this)'/><br>
		<img id='pic1' class='pic' src='data:img/png;base64,${encodeText}'  ${(memVO.mem_pic==null) ? 'style="display:none"' : ''}></td>
	</tr>
	<tr>
		<td>會員自我介紹:</td>
		<td>
			<textarea name="mem_intro" rows="4" cols="50"><%= (memVO==null)? "陸游出身於一個由「貧居苦學而仕進」的世宦家庭。陸游的高祖是宋仁宗時太傅陸軫，祖父陸佃，父親陸宰。出生時正值宋朝腐敗不振、屢遭金國（女真族）侵略的年代。出生次年，金兵攻陷北宋首都汴京，他於襁褓中即隨家人顛沛流離，因受社會及家庭環境影響，自幼即立志殺胡（金兵）救國。" : memVO.getMem_intro()%></textarea>
		 </td>
	</tr>
	<tr>
		<td>會員驗證碼:</td>
		<td><input type="TEXT" name="mem_code" size="45" 
			 value="<%= (memVO==null)? 0 : memVO.getMem_code()%>" /></td>
	</tr>
	<tr>
		<td>會員帳號狀態:</td>
		<td><input type="TEXT" name="mem_state" size="45" 
			 value="<%= (memVO==null)? 0 : memVO.getMem_state()%>" /></td>
	</tr>
	<tr>
		<td>會員註冊日期:</td>
		<td><input name="mem_date" id="f_date2" type="text"></td>
	</tr>
	<tr>
		<td>會員最後簽到時間:</td>
		<td><input name="mem_sign_day" id="f_date3" type="text"></td>
	</tr>	
	<tr>
		<td>會員登入狀態:</td>
		<td><input type="TEXT" name="mem_login_state" size="45"
			 value="<%= (memVO==null)? 1 : memVO.getMem_login_state()%>" /></td>
	</tr>
	<tr>
		<td>會員居住地:</td>
		<td><input type="TEXT" name="mem_address" size="45"
			 value="<%= (memVO==null)? "台北市" : memVO.getMem_address()%>" /></td>
	</tr>
	<tr>
		<td>上次配對時間:</td>
		<td><input name="last_pair" id="f_date4" type="text"></td>
	</tr>
	<jsp:useBean id="hobbySvc" scope="page" class="com.meetU.hobby.model.HobbyService" />
	
	<tr>
		<td>會員興趣:</td>
		<td>
		<c:forEach var="hobbyVO" items="${hobbySvc.all}" varStatus="s" >
		${(s.index%4==0)? '<br>' : ''}
			<input type="checkbox"  name="hobby_ID" value="${hobbyVO.hobby_ID}" ${listHobby_ID.contains(hobbyVO.hobby_ID)?'checked':''}> ${hobbyVO.hobby_name}
		</c:forEach>
<!-- 		<input type="TEXT" name="mem_hobby" size="45" -->
<%-- 			 value="<%= (memVO==null)? "寫Java" : memVO.getMem_hobby()%>" /> --%>
			 </td>
	</tr>
	<tr>
		<td>會員QRCODE:</td>
		<td><input type="file" name="mem_QRCODE" onchange='readURL2(this)'/><br>
		<img id='pic2' class='pic' src='data:img/png;base64,${encodeText2}'  ${(memVO.mem_QRCODE==null) ? 'style="display:none"' : ''}></td>
	</tr>
	
	<tr>
		<td>會員點數:</td>
		<td><input type="TEXT" name="mem_get_point" size="45"
			 value="<%= (memVO==null)? "10" : memVO.getMem_get_point()%>" /></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_ID" value="<%=memVO.getMem_ID()%>">
<input type="submit" value="送出修改"></FORM>

</div>
</div>
</div>
 <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/bootstrap4/js/bootstrap.min.js"></script>

    
    
    
    
    <jsp:include page="/Templates/bootstrap4/backFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery-3.3.1.slim.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
 
</body>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<% 
  java.sql.Date mem_bday = null;
  try {
	  mem_bday = memVO.getMem_bday();
   } catch (Exception e) {
	  mem_bday = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Date mem_date = null;
  try {
	  mem_date = memVO.getMem_date();
   } catch (Exception e) {
	  mem_date = new java.sql.Date(System.currentTimeMillis());
   }
  
  java.sql.Timestamp mem_sign_day = null;
  try {
	  mem_sign_day = memVO.getMem_sign_day();
   } catch (Exception e) {
	   mem_sign_day = new java.sql.Timestamp(System.currentTimeMillis());
   }
  
  java.sql.Timestamp last_pair = null;
  try {
	  last_pair = memVO.getLast_pair();
   } catch (Exception e) {
	  last_pair = new java.sql.Timestamp(System.currentTimeMillis());
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
 			$("#pic1").attr('src', e.target.result).css("display","");
 			
		}
		reader.readAsDataURL(input.files[0]);
}
function readURL2(input){
	var reader = new FileReader();
  		reader.onload = function (e) {
 			$("#pic2").attr('src', e.target.result).css("display","");
 			
		}
		reader.readAsDataURL(input.files[1]);
}
    $.datetimepicker.setLocale('zh');
    $('#f_date1').datetimepicker({
       theme: '',              //theme: 'dark',
       timepicker:false,       //timepicker:true,
       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
       format:'Y-m-d',         //format:'Y-m-d H:i:s',
	   value: '<%=mem_bday%>', // value:   new Date(),
       //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
       //startDate:	            '2017/07/10',  // 起始日
       //minDate:               '-1970-01-01', // 去除今日(不含)之前
       //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
    });
    
    $('#f_date2').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:false,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
		   value: '<%=mem_date%>', // value:   new Date(),
        //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
        //startDate:	            '2017/07/10',  // 起始日
        //minDate:               '-1970-01-01', // 去除今日(不含)之前
        //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
     });
    $('#f_date3').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=mem_sign_day%>', // value:   new Date(),
         //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
         //startDate:	            '2017/07/10',  // 起始日
         //minDate:               '-1970-01-01', // 去除今日(不含)之前
         //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
      });
    $('#f_date4').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //timepicker:true,
	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //format:'Y-m-d H:i:s',
		   value: '<%=last_pair%>', // value:   new Date(),
          //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
          //startDate:	            '2017/07/10',  // 起始日
          //minDate:               '-1970-01-01', // 去除今日(不含)之前
          //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
       });
    var somedate2 = new Date('2017-06-15');
         $('#f_date1').datetimepicker({
             beforeShowDay: function(date) {
           	  if (  date.getYear() >  somedate2.getYear() || 
    		           (date.getYear() == somedate2.getYear() && date.getMonth() >  somedate2.getMonth()) || 
    		           (date.getYear() == somedate2.getYear() && date.getMonth() == somedate2.getMonth() && date.getDate() > somedate2.getDate())
                 ) {
                      return [false, ""]
                 }
                 return [true, ""];
         }});
   
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