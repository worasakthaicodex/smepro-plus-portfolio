<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Profile</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
       <div class="col-12">
                        <ul class="nav nav-pills mb-2">
                            <!-- account -->
                            <li class="nav-item">
                                <a class="nav-link active" href="#">
                                    <i data-feather="user" class="font-medium-3 me-50"></i>
                                    <span class="fw-bold">Account</span>
                                </a>
                            </li>
                            <!-- security -->
                            <li class="nav-item" style="display:none">
                                <a class="nav-link" href="page-account-settings-security.html">
                                    <i data-feather="lock" class="font-medium-3 me-50"></i>
                                    <span class="fw-bold">Security</span>
                                </a>
                            </li>
                            <!-- billing and plans -->
                            <li class="nav-item" style="display:none">
                                <a class="nav-link" href="page-account-settings-billing.html">
                                    <i data-feather="bookmark" class="font-medium-3 me-50"></i>
                                    <span class="fw-bold">Billings &amp; Plans</span>
                                </a>
                            </li>
                            <!-- notification -->
                            <li class="nav-item" style="display:none">
                                <a class="nav-link" href="page-account-settings-notifications.html">
                                    <i data-feather="bell" class="font-medium-3 me-50"></i>
                                    <span class="fw-bold">Notifications</span>
                                </a>
                            </li>
                          
                        </ul>

                        <!-- profile -->
                        <div class="card">
                            <div class="card-header border-bottom">
                                <h4 class="card-title">Profile Details</h4>
                            </div>
                            <div class="card-body py-2 my-25">
                                <!-- header section -->
                                <div class="d-flex">
                                
                                   <c:if test="${user.user_img == null}">   
                                    <a href="#" class="me-25">
                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" id="account-upload-img" 
                                        class="uploadedAvatar rounded me-50" alt="profile image"  width="100" />
                                    </a>
                                    </c:if>
                                    
                                    <c:if test="${user.user_img != null}">   
                                       <a href="#" class="me-25">
                                        <img src="/avatar/${user.user_img}" id="account-upload-img" 
                                        class="uploadedAvatar rounded me-50" alt="profile image"  width="100" />
                                    </a>
                                    </c:if>
                     
                                    
                                    <!-- upload and reset button -->
                                    <div class="d-flex align-items-end mt-75 ms-1" style="display:none">
                                        <div >
                                            <form method="POST" modelAttribute="user" action="/addprofileimage-profile?${_csrf.parameterName}=${_csrf.token}" id="form" enctype="multipart/form-data">
	                                            <label for="account-upload"  class="btn btn-sm btn-primary mb-75 me-75">Upload</label>
	                                            <input type="file" id="account-upload" name="file"  hidden accept="image/*" />
	                                            <a href="/resetprofileimage-profile" id="account-reset" class="btn btn-sm btn-outline-secondary mb-75">Reset</a>
	                                            <p class="mb-0">Allowed file types: png, jpg, jpeg.</p>
                                            </form>
                                        </div>
                                    </div>
                                    <!--/ upload and reset button -->
                                </div>
                                <!--/ header section -->

                           
                                
                                
                                   <form:form method="POST" modelAttribute="user" action="edit-profile">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="row">
                                        <div class="col-12 col-sm-6 mb-1">
                                      
                                           <label class="form-label" for="exampleInputEmail1"><spring:message code="label.name" text="ชื่อ - นามสกุล" /></label>
                                    <input type="text" name="user_name"  class="form-control"  value="${user.user_name }">
                               
                                        </div>
                                        <div class="col-12 col-sm-6 mb-1">
                            <label class="form-label" for="exampleInputEmail1"><spring:message code="label.address" text="ที่อยู่" /></label>
                                    <input type="text" name="user_address"  class="form-control"  value="${user.user_address }"> </div>
                                    
                                              <div class="col-12 col-sm-6 mb-1">
                            <label class="form-label" for="exampleInputEmail1">Email</label>
                                    <input type="text" name="user_email"  class="form-control"  value="${user.user_email }"> </div>
                                    
                                               <div class="col-12 col-sm-6 mb-1">
                            <label class="form-label" for="exampleInputEmail1">Line</label>
                                    <input type="text" name="user_line"  class="form-control"  value="${user.user_line }"> </div> 
                                    
                                    
                                        <div class="col-12 col-sm-6 mb-1">
                                   <label class="form-label" for="exampleInputEmail1"><spring:message code="label.tel" text="เบอร์ติดต่อ" /></label>
                                    <input type="number" name="user_tel"  class="form-control"  value="${user.user_tel }">
                                      </div>
                                        <div class="col-12 col-sm-6 mb-1">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.Idcard" text="เลขบัตรประชาชน" /></label>
                                    <input type="number" name="user_idcard"  class="form-control"  value="${user.user_idcard }">   </div>
                                        <div class="col-12 col-sm-6 mb-1">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.passport" text="หนังสือเดินทาง" /></label>
                                    <input type="text" name="user_districtpassport"  class="form-control"  value="${user.user_districtpassport }">   </div>
                                        <div class="col-12 col-sm-6 mb-1">
                                <label class="form-label" for="exampleInputEmail1"><spring:message code="label.countryp" text="ประเทศที่ออกให้" /></label>
                                    <input type="text" name="user_countrypassport"  class="form-control"  value="${user.user_countrypassport }">    </div>
                                        <div class="col-12 col-sm-6 mb-1">
                              <label class="form-label" for="exampleInputEmail1"><spring:message code="label.Datep" text="วันที่ออกหนังสือเดินทาง" /></label>
                                    <input type="date" name="user_datepassport"  class="form-control"  value="${user.user_datepassport }">
                                        </div>
                                        <div class="col-12 col-sm-6 mb-1">
                              <label class="form-label" for="exampleInputEmail1"><spring:message code="label.birthday" text="วันเกิด" /></label>
                                    <input type="date" name="user_birthday"  class="form-control"  value="${user.user_birthday }">       </div>
                                       
                                      
                                        
                                        <div class="col-12">
                                            <button type="submit" class="btn btn-primary mt-1 me-1">Save changes</button>
                                            
                                        </div>
                                    </div>
                                 </form:form> 
                                <!--/ form -->
                            </div>
                        </div>

                        <!-- deactivate account  -->
                        <div class="card">
                            <div class="card-header border-bottom">
                                <h4 class="card-title">เปลี่ยน Username</h4>
                            </div>
                            <div class="card-body py-2 my-25">
                                <div class="alert alert-warning">
                                    <h4 class="alert-heading">การเเก้ไข Username</h4>
                                    <div class="alert-body fw-normal">
                                       อาจทำให้เกิดปัญหาบางอย่างที่ไม่ตั้งใจ เเละคุณต้องเข้าสู่ระบบใหม่
                                    </div>
                                </div>
                                     <c:if test="${error.equals('Exists')}">    
                                     <font style="color:red">
                                Username ซ้ำ
                                </font>
                              
                               </c:if> 
                             
<br><br>
                             <form:form method="POST" modelAttribute="user" action="editusername-profile">
                                
                                    <div>
                                    
                                     
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                               
                                <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.name" text="เปลี่ยนชื่อผู้ใช้" /></label>
                                    <input type="text" name="username"  class="form-control"  value="${user.username }"><br>
                                <div class="form-check">
                                        <input class="form-check-input" type="checkbox" name="accountActivation" id="accountActivation" data-msg="Please confirm you want to delete account" />
                                        <label class="form-check-label font-small-3" for="accountActivation">
                                            ฉันเข้าใจ
                                        </label>
                                    </div>
                                </div>
                                   
                          <br>       
                       <button type="submit" class="btn btn-primary">แก้ไข (ต้องเข้าสู่ระบบใหม่)</button>           
                    
                            
                                    </div>
                               </form:form> 
                            </div>
                        </div>
                        <!--/ profile -->
                    </div>
              
              
              
              
              
              
                <!-- deactivate account  -->
                        <div class="card">
                            <div class="card-header border-bottom">
                                <h4 class="card-title">เปลี่ยน Password</h4>
                            </div>
                            <div class="card-body py-2 my-25">

             
             
             
                  <c:if test="${error2  == 'passwordlength'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.passwordlength" text="รหัสผ่านต้องยาวกว่านี้" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if> 
                        
                        
                          <c:if test="${error2  == 'passwordpattern'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.passwordpattern" text="ชื่อผู้ใช้รูปเเบบไม่ถูกต้อง" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if> 
                           
                             <c:if test="${error2  == 'passwordnotmatch'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.passwordnotmatch" text="รหัสผ่านไม่่ถูกต้อง" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if>   
                             
<br><br>
                           <form:form method="POST" modelAttribute="user" action="/editpassword-profile">
                                
                             
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                               
                                <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.oldpassword" text="รหัสผ่านเดิม" /></label>
                                    <input type="text" name="oldpassword"  class="form-control"  value="">
                       
                                    </div>
                             
                                    <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.newpassword" text="รหัสผ่านใหม่" /></label>
                                    <input type="text" name="newpassword"  class="form-control"  value="">
                              
                                </div>
                                   
                          <br>       
                       <button type="submit" class="btn btn-primary">แก้ไข (ต้องเข้าสู่ระบบใหม่)</button>           
                    
                            
                                    </div>
                               </form:form> 
                            </div>
                            
                            
                            
                            
                               <!-- deactivate account  -->
                        <div class="card">
                            <div class="card-header border-bottom">
                                <h4 class="card-title">รหัสยืนยันตัวตน</h4>
                            </div>
                            <div class="card-body py-2 my-25">

             
                                    <div class="mb-3">
                                    <input type="text" name="auth"  class="form-control"  value="${user.auth }">
                                    </div>
                                    
                                      <a  href="/unsetprofile" type="submit" class="btn btn-primary">ขอใหม่</a>           
                            </div> 
                            
                            
                            
                        </div>
                        <!--/ profile -->
                    </div>
              
              
              

            </div>
        </div>
    </div>
    <!-- END: Content-->




<%@ include file="common/mainfoot.jspf" %>  
  <%@ include file="other/alert.jsp" %>

  <c:if test="${save == 'success'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Scccess');
      });
    
    </script>
</c:if>

    <script>
   
 
    $('.trigger').click(function(e){
      e.preventDefault();
      
      var childUl = $(this).siblings("ul.tree-parent");
      
      if( childUl.hasClass('open') ){
        
        childUl.removeClass('open');
      } else {
        
        childUl.addClass('open');
      }
      
    });
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
        document.getElementById("account-upload").onchange = function() {
    document.getElementById("form").submit();
};




    </script>  



</body>

<button class="btn btn-block btn-outline-warning" onclick="notify('ban','Sample Message','This is a sample message.');"><i class="fas fa-2x fa-ban"></i></button>


   



