
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.meetU.mem.model.MemVO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="com.meetU.live.model.*"%>
<%@page import="com.meetU.filerec.model.*"%>
<%@page import="java.util.List"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	LiveService liveSvc = new LiveService();
	List<LiveVO> list = liveSvc.getAll();
	pageContext.setAttribute("list", list);
	MemVO memVO = (MemVO)session.getAttribute("memVO");
	
%>

<!doctype html>
<html lang="en">
  <head>
  <link rel="shortcut icon" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
  <link rel="bookmark" href="<%=request.getContextPath()%>/Templates/favico.ico"/>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<%=request.getContextPath()%>/Templates/bootstrap4/css/bootstrap.min.css">
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/jquery/jquery-3.3.1.min.js"></script>
    
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

   
    
    <!-- Core Stylesheet -->
    <link href="style.css" rel="stylesheet">

    <!-- Responsive CSS -->
    <link href="css/responsive/responsive.css" rel="stylesheet">
    
    
    
    
   
   
   
    
    
    
    
    
    
    
    
    <title>MeetU!!!</title>
    
    <style>
    
  
.list-blog.single-post {
    margin-bottom: 30px;
    border-bottom: 1px solid #721c24ab;
}
    </style>
   
  </head>
  <body>
    <jsp:include page="/Templates/bootstrap4/frontHeader.jsp" />
      
   

    <!-- ****** Header Area Start ****** -->
    <header class="header_area">
        <div class="container">
            <div class="row">
                <!-- Logo Area Start -->
                <div class="col-12">
                    <div class="logo_area text-center">
                        <a href="index.html" class="yummy-logo">MeetU</a>
                    </div>
                </div>
            </div>

   
        </div>
    </header>
    
    <section class="welcome-post-sliders owl-carousel">
    
    
    
    	<c:forEach var="liveVO" items="${list}">

        <!-- Single Slide -->
        <div class="welcome-single-slide">
            <!-- Post Thumb -->
            
            <a href="<%=request.getContextPath()%>/FrontEnd/live/liveHome2.jsp?host_ID=${liveVO.host_ID}"><img src="/CA106G5/ShowPic?HOST_ID=${liveVO.host_ID}" ></a>
            <!-- Overlay Text -->
            <div class="project_title">
                <div class="post-date-commnents d-flex">
                    <a>直播間創立時間</a>
                    <a href="<%=request.getContextPath()%>/FrontEnd/live/liveHome2.jsp?host_ID=${liveVO.host_ID}"><fmt:formatDate value="${liveVO.live_date}"
						pattern="yyyy-MM-dd HH:mm" /></a>
                    
                </div>
                <a href="<%=request.getContextPath()%>/FrontEnd/live/liveHome2.jsp?host_ID=${liveVO.host_ID}">
                    <h5>${liveVO.live_name}</h5>
                </a>
            </div>
        </div>
        
        
		</c:forEach>

<!--         Single Slide -->
<!--         <div class="welcome-single-slide"> -->
<!--             Post Thumb -->
<!--             <img src="img/bg-img/slide-2.jpg" alt=""> -->
<!--             Overlay Text -->
<!--             <div class="project_title"> -->
<!--                 <div class="post-date-commnents d-flex"> -->
<!--                     <a href="#">May 19, 2017</a> -->
<!--                     <a href="#">5 Comment</a> -->
<!--                 </div> -->
<!--                 <a href="#"> -->
<!--                     <h5>“I’ve Come and I’m Gone”: A Tribute to Istanbul’s Street</h5> -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->

<!--         Single Slide -->
<!--         <div class="welcome-single-slide"> -->
<!--             Post Thumb -->
<!--             <img src="img/bg-img/slide-3.jpg" alt=""> -->
<!--             Overlay Text -->
<!--             <div class="project_title"> -->
<!--                 <div class="post-date-commnents d-flex"> -->
<!--                     <a href="#">May 19, 2017</a> -->
<!--                     <a href="#">5 Comment</a> -->
<!--                 </div> -->
<!--                 <a href="#"> -->
<!--                     <h5>“I’ve Come and I’m Gone”: A Tribute to Istanbul’s Street</h5> -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->

<!--         Single Slide -->
<!--         <div class="welcome-single-slide"> -->
<!--             Post Thumb -->
<!--             <img src="img/bg-img/slide-4.jpg" alt=""> -->
<!--             Overlay Text -->
<!--             <div class="project_title"> -->
<!--                 <div class="post-date-commnents d-flex"> -->
<!--                     <a href="#">May 19, 2017</a> -->
<!--                     <a href="#">5 Comment</a> -->
<!--                 </div> -->
<!--                 <a href="#"> -->
<!--                     <h5>“I’ve Come and I’m Gone”: A Tribute to Istanbul’s Street</h5> -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->

<!--         Single Slide -->
<!--         <div class="welcome-single-slide"> -->
<!--             Post Thumb -->
<!--             <img src="img/bg-img/slide-5.jpg" alt=""> -->
<!--             Overlay Text -->
<!--             <div class="project_title"> -->
<!--                 <div class="post-date-commnents d-flex"> -->
<!--                     <a href="#">May 19, 2017</a> -->
<!--                     <a href="#">5 Comment</a> -->
<!--                 </div> -->
<!--                 <a href="#"> -->
<!--                     <h5>“I’ve Come and I’m Gone”: A Tribute to Istanbul’s Street</h5> -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->

<!--         Single Slide -->
<!--         <div class="welcome-single-slide"> -->
<!--             Post Thumb -->
<!--             <img src="img/bg-img/slide-6.jpg" alt=""> -->
<!--             Overlay Text -->
<!--             <div class="project_title"> -->
<!--                 <div class="post-date-commnents d-flex"> -->
<!--                     <a href="#">May 19, 2017</a> -->
<!--                     <a href="#">5 Comment</a> -->
<!--                 </div> -->
<!--                 <a href="#"> -->
<!--                     <h5>“I’ve Come and I’m Gone”: A Tribute to Istanbul’s Street</h5> -->
<!--                 </a> -->
<!--             </div> -->
<!--         </div> -->

    </section>
    <!-- ****** Welcome Area End ****** -->




    <!-- ****** Categories Area Start ****** -->
    <section class="categories_area clearfix" id="about">
        <div class="container">
            <div class="row">
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".3s">
                        <img src="img/catagory-img/1.jpg" alt="">
                        <div class="catagory-title">
                            <a href="<%=request.getContextPath()%>/FrontEnd/live/liveHome.jsp">
                                <h5>直播間</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".6s">
                        <img src="img/catagory-img/2.jpg" alt="">
                        <div class="catagory-title">
                            <a href="#">
                                <h5>聯誼活動</h5>
                            </a>
                        </div>
                    </div>
                </div>
                <div class="col-12 col-md-6 col-lg-4">
                    <div class="single_catagory wow fadeInUp" data-wow-delay=".9s">
                        <img src="img/catagory-img/3.jpg" alt="">
                        <div class="catagory-title">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp">
                                <h5>商城商品</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ****** Categories Area End ****** -->

    <!-- ****** Blog Area Start ****** -->
    <section class="blog_area section_padding_0_80">
        <div class="container">
            <div class="row justify-content-center">
                
                       
                        

                        <!-- ******* List Blog Area Start ******* -->

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".2s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="img/blog-img/6.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By Marian</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">May 19, 2017</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">The 10 Best Bars By The Seaside In Blackpool, UK</h4>
                                    </a>
                                    <p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                                    <a href="#" class="read-more">Continue Reading..</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".4s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="img/blog-img/7.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By Marian</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">May 19, 2017</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">How To Get a Narcissist to Feel Empathy</h4>
                                    </a>
                                    <p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                                    <a href="#" class="read-more">Continue Reading..</a>
                                </div>
                            </div>
                        </div>

                        <!-- Single Post -->
                        <div class="col-12">
                            <div class="list-blog single-post d-sm-flex wow fadeInUpBig" data-wow-delay=".6s">
                                <!-- Post Thumb -->
                                <div class="post-thumb">
                                    <img src="img/blog-img/8.jpg" alt="">
                                </div>
                                <!-- Post Content -->
                                <div class="post-content">
                                    <div class="post-meta d-flex">
                                        <div class="post-author-date-area d-flex">
                                            <!-- Post Author -->
                                            <div class="post-author">
                                                <a href="#">By Marian</a>
                                            </div>
                                            <!-- Post Date -->
                                            <div class="post-date">
                                                <a href="#">May 19, 2017</a>
                                            </div>
                                        </div>
                                        <!-- Post Comment & Share Area -->
                                        <div class="post-comment-share-area d-flex">
                                            <!-- Post Favourite -->
                                            <div class="post-favourite">
                                                <a href="#"><i class="fa fa-heart-o" aria-hidden="true"></i> 10</a>
                                            </div>
                                            <!-- Post Comments -->
                                            <div class="post-comments">
                                                <a href="#"><i class="fa fa-comment-o" aria-hidden="true"></i> 12</a>
                                            </div>
                                            <!-- Post Share -->
                                            <div class="post-share">
                                                <a href="#"><i class="fa fa-share-alt" aria-hidden="true"></i></a>
                                            </div>
                                        </div>
                                    </div>
                                    <a href="#">
                                        <h4 class="post-headline">Bristol's 10 Amazing Weekend Brunches And Late Breakfasts</h4>
                                    </a>
                                    <p>Tiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation</p>
                                    <a href="#" class="read-more">Continue Reading..</a>
                                </div>
                            </div>
                        </div>

               

                    </div>
                </div>

       
            </div>
        </div>
    </section>
    <!-- ****** Blog Area End ****** -->

    <!-- ****** Instagram Area Start ****** -->
    <div class="instargram_area owl-carousel section_padding_100_0 clearfix" id="portfolio">

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/1.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/2.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/3.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/4.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/5.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/6.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/7.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Instagram Item -->
        <div class="instagram_gallery_item">
            <!-- Instagram Thumb -->
            <img src="img/instagram-img/8.jpg" alt="">
            <!-- Hover -->
            <div class="hover_overlay">
                <div class="yummy-table">
                    <div class="yummy-table-cell">
                        <div class="follow-me text-center">
                            <a href="<%=request.getContextPath()%>/FrontEnd/cart/EShop.jsp"><i class="fa fa-instagram" aria-hidden="true"></i>前往購買</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
    <!-- ****** Our Creative Portfolio Area End ****** -->

    

    <!-- ****** Footer Menu Area Start ****** -->
    <footer class="footer_area">
   

        <div class="container">
            <div class="row">
                <div class="col-12">
                    <!-- Copywrite Text -->
                    <div class="copy_right_text text-center">
                      <i class="fa " aria-hidden="true"></i>
                    </div>
                </div>
            </div>
        </div>
    </footer>
      
      
      
      
      
      
      
      
      
      
      
      
      
      
   
      
      
      
      
      
      
      
      
      
      
      
      
    <jsp:include page="/Templates/bootstrap4/frontFooter.jsp" />

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/popper.min.js"></script>
    <script src="<%=request.getContextPath()%>/Templates/bootstrap4/js/bootstrap.min.js"></script>
    
     <script src="js/others/plugins.js"></script>
    
    <script src="js/active.js"></script>
      
  </body>
</html>