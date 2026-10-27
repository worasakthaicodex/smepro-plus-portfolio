<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>



<%@ include file="common/head.jspf" %>   
<title>Smepro</title>
</head>
<!-- END: Head-->

<!-- BEGIN: Body-->




<body class="vertical-layout vertical-menu-modern blank-page navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="blank-page">
    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper">
            <div class="content-header row">
            </div>
            <div class="content-body">
                <div class="auth-wrapper auth-cover">
                    <div class="auth-inner row m-0">
                        <!-- Brand logo--><a class="brand-logo" href="/">
                         
                            <h2 class="brand-text text-primary ms-1"> <img src="/resources/app-assets/images/icons/qexcellent.png" class="me-25" alt="png" height="25"> I-Doc Plus</h2>
                        </a>
                        <!-- /Brand logo-->
                        <!-- Left Text-->
                        <div class="d-none d-lg-flex col-lg-8 align-items-center p-5">
                            <div class="w-100 d-lg-flex align-items-center justify-content-center px-5"><img class="img-fluid" src="/resources/app-assets/images/pages/coming-soon.svg" alt="Login V2" /></div>
                        </div>
                        <!-- /Left Text-->
                        <!-- Login-->
                        <div class="d-flex col-lg-4 align-items-center auth-bg px-2 p-lg-5">
                            <div class="col-12 col-sm-8 col-md-6 col-lg-12 px-xl-2 mx-auto">
                                <h2 class="card-title fw-bold mb-1"><spring:message code="welcome.welcome1"  /> 👋  </h2>
                                <p class="card-text mb-2"><spring:message code="welcome.welcome2"  /></p>
                            
						                                  <c:url value="/login" var="loginUrl"/>  
						  
          <c:if test="${verif  == 'false'}"> 
		               <div class="alert text-white bg-danger d-flex align-items-center" role="alert">
		                                   <div class="alert-icon me-2">
		                                      <i class="ti-alert"></i>
		                                   </div>
		                                   <div class="alert-text"> <b>Certificate verification failed </b><spring:message code="certificate.error" text="กรุณาตรวจสอบข้อมูลใบรับรองของคุณหรือรีเฟรช" />
		                                  &nbsp;&nbsp;&nbsp; <a href="/">Check</a></div>
		                                </div> 
                  </c:if> 
                

<c:if test="${verif  == 'true'}"> 
   <form action="${loginUrl}" class="auth-login-form mt-2" method="post">
						                                       <input type="hidden"                          
						            name="${_csrf.parameterName}"  
						            value="${_csrf.token}"/>    
                                    <div class="mb-1">
                                        <label class="form-label" for="login-email"><spring:message code="welcome.Username"  /></label>
                                        <input class="form-control" id="login-email" type="text" name="username"  autofocus="" tabindex="1" />
                                    </div>
                                    <div class="mb-1">
                                        <div class="d-flex justify-content-between">
                                            <label class="form-label" for="login-password"><spring:message code="welcome.Password"  /> </label><a href="auth-forgot-password-cover.html"><small style="display:none">Forgot Password?</small></a>
                                        </div>
                                        <div class="input-group input-group-merge form-password-toggle">
                                            <input class="form-control form-control-merge" id="login-password" type="password" name="password"  aria-describedby="login-password" tabindex="2" /><span class="input-group-text cursor-pointer"><i data-feather="eye"></i></span>
                                        </div>
                                    </div>
                                    <div class="mb-1" style="display:none">
                                        <div class="form-check">
                                            <input class="form-check-input" id="remember-me" type="checkbox" tabindex="3" />
                                            <label class="form-check-label" for="remember-me"> Remember Me</label>
                                        </div>
                                    </div>
                                    <button class="btn btn-primary" ><spring:message code="btn.login" text="เข้าสู่ระบบ" /></button>
                                            <a  href="" id="newlink1"  class="btn btn-light" style="display:none"><i data-feather='map-pin'></i><spring:message code="welcome.IN-OUT"  /> </a>
                                </form>
                          
                                
                                <p class="text-center mt-2">
                                
                               <input class="form-check-input" id="basic-checkbox" type="checkbox" checked="checked"  name="remember-me"/><label class="form-check-label mb-0" for="basic-checkbox">Remember me</label>
                                
                                <br> <br>
                                <span>
                              
                                 <a href="?language=en">English</a>|<a href="?language=th">ไทย</a>  <br>
                                 <c:if test="${param.error != null}">          
												     
												                 <spring:message code="welcome.logoutfalse" text="ชื่อผู้ใช้และรหัสผ่านไม่ถูกต้อง" />
												     
												     
												        
</c:if>  


<div id="demo"></div>

												        <c:if test="${param.logout != null}">         
												           
												                <spring:message code="welcome.islogout" text="คุณออกจากระบบแล้ว" />
												     
												        </c:if> 
	</c:if> 											        
 <div>												        
Activate Status<br>
Version : 1.00 preview<br>
 license Status : ${cstatus }     <br><br><br>
 </div>   
                            </div>
                        </div>
                        <!-- /Login-->
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- END: Content-->


  


</body>
<%@ include file="common/foot.jspf" %>  



    <script>
    getLocation();
    
    
    var x = document.getElementById("demo");
    var lat=0;
    var lon=0;
    function getLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(showPosition);
      } else { 
        x.innerHTML = "Geolocation is not supported by this browser.";
        
        document.getElementById("newlink1").href="/Attendance?lat="+lat+"&&lon="+lon; 

      }
    }

    function showPosition(position) {
     // x.innerHTML = "Latitude: " + position.coords.latitude + 
    //  "<br>Longitude: " + position.coords.longitude;
    	lat =position.coords.latitude;
    	lon =position.coords.longitude;
    	
        document.getElementById("newlink1").href="/Attendance?lat="+lat+"&&lon="+lon; 

    }
    
    </script>

    <script>
    
    
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
    </script>


