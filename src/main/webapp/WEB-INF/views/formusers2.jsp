<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Add user</title>
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
             <div class="card"><div class="card-body">
                <div class="col-md-12">
                    <div class="white_box QA_section mb_30">
                        <div class="white_box_tittle list_header">
                            <h4><spring:message code="lable.insertuser" text="เพิ่มผู้ใช้ใหม่" /></h4>
                            <div class="box_right d-flex lms_block">
                                <div class="serach_field_2">
                              
                                </div>
                            </div>
                        </div>

                        <div class="QA_table" id="profilediv">
                        
                      <c:if test="${Error  == 'usernamelength'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.usernamelength" text="ชื่อผู้ใช้ต้องยาวกว่านี้" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if>
                        
                          <c:if test="${Error  == 'passwordlength'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.passwordlength" text="รหัสผ่านต้องยาวกว่านี้" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if> 
                        
                        
                          <c:if test="${Error  == 'passwordpattern'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.passwordpattern" text="รหัสผ่านรูปเเบบไม่ถูกต้อง" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if> 
                             <c:if test="${Error  == 'usernamepattern'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.usernamepattern" text="ชื่อผู้ใช้รูปเเบบไม่ถูกต้อง" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if> 
                             <c:if test="${Error  == 'usernameexists'}">    
                        
                        <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.usernameexists" text="ชื่อผู้ใช้นี้มีผู้ใช้เเล้ว" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if> 
                          <form:form method="POST" modelAttribute="user" action="add-usermanage">
                              <br>
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                               
                                <div class="mb-2">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.username" text="ชื่อผู้ใช้" /></label>
                                    <input type="text" name="username"  class="form-control"  value="${user.username }">
                               
                                </div>
                                   <div class="mb-2">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.password" text="รหัสผ่าน" /></label>
                                    <input type="text" name="password"  class="form-control"  value="${user.password }">
                               
                                </div>
                                    <div class="mb-2">

                                  <label class="form-label" for="exampleInputEmail1"><spring:message code="usermanage.role" text="บทบาท" /></label>  
                               <select id="inputState" class="form-select" name="group_id">
                             <c:forEach items="${groups }" var="row" >
                            <option value="${row.id }">${row.group_name }</option>
                             </c:forEach>
                              
                               </select>
                                </div>
                                
                                
                          <br><br>       
                       <button type="submit" class="btn btn-primary"><spring:message code="btn.insert" text="เพิ่ม" /></button>           
                   </form:form>
                                
                        </div>
                   
          
                    </div>
                </div>
                       </div> </div> </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>




<%@ include file="common/mainfoot.jspf" %>  
 
 
    <script>
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
            var element = document.getElementById("usermanage3");
  element.classList.add("active");      

    </script>
</body>
