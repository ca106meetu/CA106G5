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
<!-- map -->
	<script src="http://maps.google.com/maps/api/js?key=AIzaSyBbAAPKAKdERmjzz1pWIZVULGozcKOY6Y8&sensor=false"></script>
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
        margin-top: 50px;
      }
      
   *{
      	font-family:微軟正黑體;
      }
      
   #map {
        margin:50px 0px 50px 0;
        height: 400px;
        width: 100%;
   }
   .ckedit{
 		margin:50px 0px 50px 0px;
 	}   
</style>

</head>
<body onload='initMap();'>

<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤</font>
	<ul>
	<c:forEach var="msg" items="${errorMsgs}">
		<li style="color:red">${msg}</li>
	</c:forEach>
	</ul>
</c:if>

<div class="container">
	<FORM METHOD="POST" ACTION="<%=request.getContextPath()%>/FrontEnd/meetup/meetup.do" name="form1" enctype='multipart/form-data'>
    <div class="row">
      	 <div class="col-6">
			<div class="headIntro introPic form-group">
	    	   	<label for="imgUpload"></label>
	       		<input type="file" name="meetup_pic" size="45" id="imgUpload" value=""/>
				<img class='pic' src='data:img/png;base64,${encodeText}'/>
          	</div>
        </div> 
        
        <div class="col-6">
         	 <div class="headIntro form-group">
          		<ul>
	          		<li>聯誼名稱 <input type="hidden" name="meetup_name" value="<%=meetupVO.getMeetup_name()%>" /><%=meetupVO.getMeetup_name()%></li>

	          		<li>聯誼地點 <select id="twCityName" class="form-control">
					  <option >--請選擇縣市--</option>
					  <c:forEach var="city" items="${listCity}">
					  	<option value="${city}"> ${city}</option>
					  </c:forEach>
				  		</select>
				  
				  		<select id="CityAreaName" class="form-control">
						  <option >--請選擇區域--</option>
				  		</select>
						    
			      		<select id="AreaRoadName" class="form-control">
						  <option >--請選擇路名--</option>
				  		</select>	    
				  
					  	<input type="text" class="form-control" placeholder="請輸入門牌號碼" id="num">
					  	<input type="button" class="btn btn-info form-control" value="確認" id="btnLoc" >	
					
						<input type="text" id="addressTotal" class="form-control" name="meetup_loc" value="<%=meetupVO.getMeetup_loc()%>">
						<input type="button" class="btn btn-info form-control" value="在地圖上顯示" onClick="codeAddress()">	
					</li>
	          	</ul>
        	</div>
       </div>
   </div><!-- 來自ROW-->	
      
   <div class="row">
       <div class="col-12">
   	   		<div class="ckedit" >	
	      		<script src="<%=request.getContextPath()%>/ckeditor4/ckeditor.js"></script>
	      		<textarea name="meetup_info" rows="10" cols="45" >${param.meetup_info}</textarea>
	       		<script>CKEDITOR.replace('meetup_info');</script>
       		</div>
       </div>
   </div><!-- 來自Row-->   
	  	<input type="hidden" name="meetup_name" value="<%=meetupVO.getMeetup_name()%>" />
		<input type="hidden" name="meetup_ID" value="<%=meetupVO.getMeetup_ID()%>" />
		<input type="hidden" name="meetup_minppl" value="<%=meetupVO.getMeetup_minppl()%>" />
		<input type="hidden" name="meetup_maxppl" value="<%=meetupVO.getMeetup_maxppl()%>" />
		<input type="hidden" name="meetup_status" value="1"/>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="meetup_date" value="<%=meetupVO.getMeetup_date()%>" />
        <input type="hidden" name="meetup_joindate" value="<%=meetupVO.getMeetup_joindate()%>" />
	<button type="submit" class="form-control btn btn-danger">送出修改</button>
</FORM>
    
	<div id="map"></div>
</div>
		
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
<script>
//GoogleMap 
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

<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
<% 
  java.sql.Timestamp meetup_date = null;
  try {
	  meetup_date = meetupVO.getMeetup_date();
   } catch (Exception e) {
	  meetup_date = new java.sql.Timestamp(System.currentTimeMillis());
   }
%>

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

<script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
<script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>

</body>
</html>
