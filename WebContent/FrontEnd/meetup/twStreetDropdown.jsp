<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<style>
      #map {
        height: 400px;
        width: 100%;
       }
    </style>
<meta>
<title>twStreetDropdown</title>

<!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
 <script src="http://maps.google.com/maps/api/js?key=AIzaSyBbAAPKAKdERmjzz1pWIZVULGozcKOY6Y8&sensor=false"></script>
	<script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>

</head>

<body onload='initMap();'>
<jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />

<div class="container">
	<div class="row">
    	<div class="col">
			<div class="dropdown">
	  
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
			</div>
		</div>
	</div>
	<div class="row">
		 <p> 輸入欲查詢的地址<input id="addressTotal" type="text" size="50" >   
		 <input type="button" value="查經緯度並在地圖上顯示" onClick="codeAddress()"> </p>
    <div id="map"></div>
   
<script>
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
	</div>
</div>

<script> 

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
		
	});
	
})


</script>
<jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
    </body>
</body>
</html>