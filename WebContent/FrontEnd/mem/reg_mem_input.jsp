<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.mem.model.*"%>
<%@ page import="com.meetU.memHobby.model.*"%>
<%@ page import="java.util.*"%>

<%
  MemVO memVO = (MemVO) session.getAttribute("memVO"); //MemServlet.java (Concroller) 存入req的memVO物件 (包括幫忙取出的memVO, 也包括輸入資料錯誤時的memVO物件)
  
//   System.out.println("---------------------------------------");
//   System.out.println(memVO==null);
//   System.out.println("---------------------------------------");
%>

<!doctype html>
<html lang="zh-Hant-TW">
  <head>
	<meta charset="utf-8">
    <!-- Required meta tags -->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Bootstrap CSS -->
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/jquery-3.2.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/sweetalert2.all.js"></script> 
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/sweetalert2.css"> 
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
	/*width: 450px;*/
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
  table#main_data td .laber{width:140px;}
</style>

</head>
<body bgcolor='white'>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<div class="container justify-content-center">
<div class="row justify-content-center">
<div class="col">   
	<center>
		 <h3>會員資料註冊</h3>
	</center>
</div>
</div>

<div class="row justify-content-center">
<div class="col">   
	<center>
		<h3>會員資料註冊:</h3>
	</center>
</div>
</div>	

<div class="row justify-content-center">
<div class="col"> 
<center>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>
</center>
	</div>
 </div>
<div class="row justify-content-end">
    <div class="col-1">
      <button type="button" class="btn btn-outline-secondary btn-sm" id="Qclick">小按鈕</button>
    </div>
</div> 
<div class="row justify-content-center">
	<div class="col">
		<center>
<FORM METHOD="post" ACTION="FrontMem.do" name="form1" enctype="multipart/form-data">
<table id="main_data">

		<td class="laber">會員帳號:</td>
		<td><input type="hidden" name="mem_acc" value="<%= (memVO==null)? "" : memVO.getMem_acc()%>"/> 
			 <%= (memVO==null)? "" : memVO.getMem_acc()%> </td>
		</tr>
	<tr>
		<td class="laber">會員密碼:</td>
		<td><input type="TEXT" name="mem_pw" class="form-control" aria-label="Default" id="tm_pw" 
			aria-describedby="inputGroup-sizing-default" value="<%= (memVO==null)? "" : memVO.getMem_pw()%>" />
		</td>
	</tr>
	<tr>
		<td class="laber">會員姓名:</td>
		<td><input type="TEXT" name="mem_name" class="form-control" aria-label="Default"  id="tm_name"
			aria-describedby="inputGroup-sizing-default" value="<%= (memVO.getMem_name()==null)? "" : memVO.getMem_name()%>" />
		</td>
	</tr>
	<tr>
		<td class="laber">會員暱稱:</td>
		<td><input type="TEXT" name="mem_nickname" class="form-control" aria-label="Default" id="tm_nickname"
			aria-describedby="inputGroup-sizing-default" value="<%= (memVO.getMem_nickname()==null)? "" : memVO.getMem_nickname()%>" />
		</td>
	</tr>
	<tr>
		<td class="laber">會員生日:</td>
		<td><input name="mem_bday" id="f_date1" type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"
		     /></td>
	</tr>	
	<tr>
		<td class="laber">會員電子郵件信箱:</td>
		<td><input type="hidden" name="mem_email" value="<%=memVO.getMem_email()%>"> 
			 <%= (memVO.getMem_email()==null)? "" : memVO.getMem_email()%></td>
	</tr>
	<tr>
		<td class="laber">會員手機:</td>
		<td><input type="TEXT" name="mem_pho" class="form-control" aria-label="Default" id="tm_pho"
			aria-describedby="inputGroup-sizing-default" value="<%= (memVO.getMem_pho()==null)? "" : memVO.getMem_pho()%>" />
		</td>
	</tr>	
	<tr>
		<td class="laber"><label for="mem_gend">會員性別:</label></td>
		<td><input type="TEXT" name="mem_gend" class="form-control" aria-label="Default" id="tm_gend"
			aria-describedby="inputGroup-sizing-default" value="<%= (memVO.getMem_gend()==null)? "" : memVO.getMem_gend()%>" />
		
        </td>
	</tr>
	<tr>
		<td class="laber">會員大頭照:</td>
		<td><input type="file" name="mem_pic" onchange='readURL(this)' class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default"/><br>
		<img id='pic1' class='pic' src='data:img/png;base64,${encodeText}'  ${(memVO.mem_pic==null) ? 'style="display:none"' : ''}></td>
	</tr>
	<tr>
		<td class="laber">會員自我介紹:</td>
		<td>
			<textarea name="mem_intro" rows="4" cols="50" id="tm_intro"><%= (memVO.getMem_intro()==null)? "" : memVO.getMem_intro()%></textarea>
		 </td>
	</tr>

	<tr>
		<td class="laber">會員註冊日期:</td>
		<td><input type="hidden" name="mem_date" id="f_date2" >
			<%= memVO.getMem_date() %>
		</td>
	</tr>

	<tr>
		<td class="laber">會員居住地:</td>
		<td><input type="TEXT" name="mem_address" aria-label="Default" id="tm_address"
			aria-describedby="inputGroup-sizing-default" value="<%= (memVO.getMem_address()==null)? "" : memVO.getMem_address()%>" /></td>
	</tr>
	
	<tr>
		<td class="laber">會員點數:</td>
		<td><input type="hidden" name="mem_get_point" value="<%= (memVO.getMem_get_point()==null)? 0 : memVO.getMem_get_point()%>">
			 <%= (memVO.getMem_get_point()==null)? 0 : memVO.getMem_get_point()%></td>
	</tr>
		<input type="hidden" name="mem_QRCODE" />
		<input type="hidden" name="mem_sign_day" id="f_date3" >
		<input type="hidden" name="mem_login_state" value="1">
		<input type="hidden" name="mem_hobby" value="打LOL">

</table>
<br>
<input type="hidden" name="action" value="reg">
<input type="hidden" name="mem_ID" value="<%=memVO.getMem_ID()%>">
<button class="btn btn-outline-success" type="submit"><b>送出修改</b></button>
</FORM>
</center>
</div>
</div>
</div>
<br><br><br>
   
    
    
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
 	<script type="text/javascript">
 	$(document).ready(function(){
	 	gend.map(function(data){
	        $('#mem_gend').append('<option value="'+ data.value +'">' + data.title + '</option>');
	    });
	 	
	 	$("#Qclick").click(function(){
    	  	$("#tm_pw").val("123456");
    	  	$("#tm_name").val("大衛海鮮");
    	  	$("#tm_nickname").val("嘴炮傭兵");
    	    $("#f_date1").val("1991-02-01");
    	    $("#tm_pho").val("0916066866");
    	    $("#tm_gend").val("男性");
    	    $("#tm_intro").val("大衛海鮮原本是傭兵，為了治療癌症而在早期接受「Weapon X」的實驗，就算接受酷刑也能夠迅速再生，身體四分五裂了也能恢復原狀，也因此造成腦細胞過度增長，因而變得瘋瘋癲癲的。再生能力與癌細胞互相排斥，導致身體滿是疤痕，自己深感羞愧不想連累女友的幸福人生，所以離開了她。");
    	    $("#tm_address").val("桃園市");
    	});
	 	
 	});
 	var gend = [
 	    {title:'男性',value:'男性'},
 	    {title:'女性',value:'女性'},
 	    {title:'不公開',value:'不公開'},    
 		];
 	</script>
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