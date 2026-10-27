<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">

<title>Upload</title>
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
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">การตั้งค่าระบบ</h4>
                                </div>
                                <div class="card-body">
                                     
                                
                                
                             
                               <div class="nav-vertical">
                                        <ul class="nav nav-tabs nav-left flex-column" role="tablist" style="height: 220px;">
                                            <li class="nav-item">
                                                <a class="nav-link active" id="baseVerticalLeft-tab1" data-bs-toggle="tab" aria-controls="tabVerticalLeft1" href="#tabVerticalLeft1" role="tab" aria-selected="true">ตั้งค่าอีเมล์</a>
                                            </li>
                                            <li class="nav-item" style="display:none">
                                                <a class="nav-link" id="baseVerticalLeft-tab2" data-bs-toggle="tab" aria-controls="tabVerticalLeft2" href="#tabVerticalLeft2" role="tab" aria-selected="false">การเชื่อมต่อ API</a>
                                            </li>
                                        
                                        </ul>
                                        <div class="tab-content" >
                                            <div class="tab-pane active" id="tabVerticalLeft1" role="tabpanel" aria-labelledby="baseVerticalLeft-tab1" style="overflow:hidden">
                                                
                                                         <c:if test="${sendmail  == 'Error'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Notice</b>
                                        <spring:message code="email.error" text="ส่งเมล์ไม่สำเร็จ" /> 
                                       <a href="/question?error=email"> <spring:message code="question.learnmore" text="เรียนรู้เพิ่มเติ่ม" /></a></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if>
                        
                          <c:if test="${sendmail  == 'Successfully'}">    
                        
                        <div class="alert text-white bg-success d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Successfully <b></b>
                                        <spring:message code="email.success" text="ส่งเมล์สำเร็จ" /> 
                                       </div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if>
                                                
                                                <p>
                                                         <form:form method="POST" modelAttribute="setmail" action="editsetmail-setting">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                               <input type="hidden" name="id_refer"  class="form-control"  value="${setmail.id_refer }">
                               
                               
                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">username</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                       
                                    <input type="text" name="username"  class="form-control"  value="${setmail.username }">
                                                    </div>
                                                </div>
                                            </div>
                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">password</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                   
                                    <input type="password" name="password"  class="form-control"  value="${setmail.password }">
                                                    </div>
                                                </div>
                                            </div>
                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Hostname</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                     
                                    <input type="text" name="hostname"  class="form-control"  value="${setmail.hostname }">
                                                    </div>
                                                </div>
                                            </div>
                          
                          
                                  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Starttls Port</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                      
                               <input type="number" name="starttls_port"  class="form-control"  value="${setmail.starttls_port }">
                                                    </div>
                                                </div>
                                            </div>
                          
                          
                                        <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Url</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                    
                               <input type="text" name="url"  class="form-control"  value="${setmail.url }">
                                                    </div>
                                                </div>
                                            </div>   
                        
                                          <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Email support</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                     
                                <input type="email" name="support"  class="form-control"  value="${setmail.support }">
                                                    </div>
                                                </div>
                                            </div>   
                                   
                             <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Email for test</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                         
                                   <input type="email" name="emailtest"  class="form-control"  value="${setmail.emailtest }">
                                                    </div>
                                                </div>
                                            </div>   
                               
                               
                                    <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Starttls</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                            
                             <c:if test="${setmail.starttls == true}">    
                                             <c:set var="vat_status" value="selected" />  
                                        </c:if> 
                                    
                                    
                                    <select name="starttls"  class="form-select" >
                                     <option value="0" ${vat_status }>False</option>
                                     <option value="1" ${vat_status }>True</option>
                                  </select>
                                                    </div>
                                                </div>
                                            </div>   
                               
                              <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Auth</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                        
                                  <c:if test="${setmail.auth == true}">    
                                             <c:set var="vat_status" value="selected" />  
                                        </c:if> 
                                    
                                    
                                    <select name="auth"  class="form-select" >
                                     <option value="0" ${vat_status }>False</option>
                                     <option value="1" ${vat_status }>True</option>
                                  </select>
                                                    </div>
                                                </div>
                                            </div>  
                           
                           
                               
                            
                             
                             
                             
                          <br><br>       
                       <button type="submit" class="btn btn-primary"><spring:message code="btn.edit" text="แก้ไข" /></button>     
                         <a href="/testmail-setting" class="btn btn-info"><spring:message code="btn.test" text="ทดสอบ" /></a>             
                   </form:form>    
                                                </p>
                                            </div>
                                            <div class="tab-pane" id="tabVerticalLeft2" role="tabpanel" aria-labelledby="baseVerticalLeft-tab2">
                                                <p>
                                                  Coming soon
                                                </p>
                                            </div>
                                         
                                        </div>
                                    </div>
                          
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>




<%@ include file="common/mainfoot.jspf" %>  
  <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>

    <script>
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
        var element = document.getElementById("nav_set2");
element.classList.add("active");  
    </script>
</body>


