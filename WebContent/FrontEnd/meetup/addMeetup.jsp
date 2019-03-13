<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.meetU.meetup.model.*"%>
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<% MeetupVO meetupVO = (MeetupVO) request.getAttribute("meetupVO");%>

<html>
<head>
<title>新增聯誼</title>

<style>
  #map {
        height: 400px;
        width: 100%;
       }
   
</style>
<!-- Required meta tags -->
<meta>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
 
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

</head>
<!--<body onload='initMap();'>-->
<body>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="msg" items="${errorMsgs}">
			<li style="color:red">${msg}</li>
		</c:forEach>
	</ul>
</c:if>

<div class="container">
	<div class="row">
    	<div class="col">
			
<FORM METHOD="POST" ACTION="meetup.do" name="form1" enctype='multipart/form-data'>
<table>
	<tr>
		<th>聯誼名稱</th>
		<td><input type="text" name="meetup_name" size="45" value="<%=(meetupVO==null)? "地方媽媽需要愛":meetupVO.getMeetup_name()%>"/></td>
	</tr>	
	<tr>
		<th>主揪</th>
		<td><input type="text" name="mem_ID" size="45" value="<%=(meetupVO==null)? "M000005":meetupVO.getMem_ID()%>"/></td>
	</tr>	
	<tr>		
		<th>聯誼日期</th>
		<td><input type="text" name="meetup_date" id="f_date1"/></td>
	</tr>	
	<tr>
		<th>聯誼地址</th>
		<td>
			<select id="twCityName">
			  <option >--請選擇縣市--</option>
			  <c:forEach var="city" items="${listCity}">
			  	<option value="${city}"> ${city}</option>
			  </c:forEach>
	  		</select>
	  
	  		<select id="CityAreaName" >
			  <option >--請選擇區域--</option>
	  		</select>
			    
      		<select id="AreaRoadName" >
			  <option >--請選擇路名--</option>
	  		</select>	    
	  
		  	<input type="text" placeholder="請輸入門牌號碼" id="num">
		  	<input type="button" value="確認" id="btnLoc">	
		</td>
	</tr>
	<tr>
		<th></th>
		<td>
			<input id="addressTotal" name="meetup_loc" type="text" size="50" value="<%=(meetupVO==null)?"資策會":meetupVO.getMeetup_loc()%>">
		</td>
	</tr>
	<tr>
		<th>聯誼封面照</th>
		<td><input type="file" name="meetup_pic" size="45" id="imgUpload"/><br>
			<input type='hidden' name='encodeText' value='${encodeText}'>
			<img class='pic' src='data:img/png;base64,${encodeText}' ${(meetupVO.meetup_pic == null) ? "style='display:none'" : ''}></td>
	</tr>	
	<tr>	
		<th>聯誼狀態</th>
		<td><input type="text" name="meetup_status" size="45" value="<%=(meetupVO==null)?"1":meetupVO.getMeetup_status()%>"/></td>
	</tr>	 
	<tr>	
		<th>聯誼資訊</th>
		<td><input type="text" name="meetup_info" size="45" value="<%=(meetupVO==null)?"來嘛~":meetupVO.getMeetup_info()%>"/></td>			
	</tr>
</table>
<input type="hidden" name="action" value="insert">
<button type="submit" >送出新增</button>
</FORM>
    <div id="map"></div>
</div>
</div>
</div>
<script>
<!-- 圖片上傳 -->
$('#imgUpload').change(function(){
    //當檔案改變後，做一些事 
   readURL(this);   // this代表<input id="imgUpload">
 });
 
function readURL(input){
	var reader = new FileReader();
  	reader.onload = function (e) { $(".pic").attr('src', e.target.result).css('display',''); }
	reader.readAsDataURL(input.files[0]);
}
</script>

<script>
<!-- Location Dropdown Menu -->
$(document).ready(function(){
	
	$("#twCityName").change(function(){
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/twAddress",
			 data: {"action":"twCityName",
				 	"twCityName":$('#twCityName option:selected').val()},
			 dataType: "json",
			 success: function(result){
				 $("#CityAreaName").empty();
				
				 $("#CityAreaName").append("<option >--請選擇區域--</option>")
				 for(var i=0; i<result.length; i++){
				 	$("#CityAreaName").append('<option value="'+result[i]+'">'+result[i]+'</option>');
				 }
			 },
	         error: function(){
	        	 alert("AJAX-grade發生錯誤囉!")
	        	 }
	    });
	});
	
	$("#CityAreaName").change(function(){
		$.ajax({
			 type: "POST",
			 url: "<%=request.getContextPath()%>/twAddress",
			 data: {"action":"CityAreaName",
				 	"twCityName":$('#twCityName option:selected').val(),
				 	"CityAreaName":$('#CityAreaName option:selected').val()},
			 dataType: "json",
			 success: function(result){
				 $("#AreaRoadName").empty();
				 $("#AreaRoadName").append("<option >--請選擇區域--</option>")
				 for(var i=0; i<result.length; i++){
				 	$("#AreaRoadName").append('<option value="'+result[i]+'">'+result[i]+'</option>');
				 }
			 },
	         error: function(){
	        	 alert("AJAX-grade發生錯誤囉!")
	        	 }
	    });
	});
	
	$("#btnLoc").click(function(){
		
		var twCityName = ($('#twCityName').get(0).selectedIndex)>0? $('#twCityName option:selected').val() :'';
		
		var CityAreaName = ($('#CityAreaName').get(0).selectedIndex)>0? $('#CityAreaName option:selected').val() :'';
		
		var AreaRoadName = ($('#AreaRoadName').get(0).selectedIndex)>0? $('#AreaRoadName option:selected').val() :'' ;
		
		var num = $('#num').val().trim().length != 0 ? $('#num').val()+"號" :'' ; 

		var locTotal = twCityName+CityAreaName+AreaRoadName+num;
		$("#addressTotal").attr("value",locTotal);
	})
})
</script>

<script>
<%-- GoogleMap 
      var geocoder= new google.maps.Geocoder();

      function initMap() {
        var uluru = {lat: 24.9678606, lng: 121.1917912};
        var map = new google.maps.Map(document.getElementById('map'), {
          zoom: 15,
          center: uluru
        });
        var marker = new google.maps.Marker({
          position: uluru,
          map: map
        });
      }

      function codeAddress() {
        var address = document.getElementById("addressTotal").value;
        geocoder.geocode( { 'address': address}, function(results, status) {
          if (status == google.maps.GeocoderStatus.OK) {
            var map = new google.maps.Map(document.getElementById('map'), {
              zoom: 15,
              center: results[0].geometry.location
            });
                var marker = new google.maps.Marker({
                map: map,
                position: results[0].geometry.location
            });

          } else {
            alert("失敗, 原因: " + status);
          }
        });
      }
      --%>
</script>

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
<% 
  java.sql.Date meetup_date = null;
  try {
	  meetup_date = meetupVO.getMeetup_date();
   } catch (Exception e) {
	  meetup_date = new java.sql.Date(System.currentTimeMillis());
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
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
           theme: '',              //theme: 'dark',
 	       timepicker:false,       //timepicker:true,
 	       step: 1,                //step: 60 (這是timepicker的預設間隔60分鐘)
 	       format:'Y-m-d',         //format:'Y-m-d H:i:s',
 		   value: '<%=meetup_date%>', // value:   new Date(),
           //disabledDates:        ['2017/06/08','2017/06/09','2017/06/10'], // 去除特定不含
           //startDate:	            '2017/07/10',  // 起始日
           minDate:               '-1970-01-01', // 去除今日(不含)之前
           //maxDate:               '+1970-01-01'  // 去除今日(不含)之後
        });
</script>

<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
</body>
</html>

