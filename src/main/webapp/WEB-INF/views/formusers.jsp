<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/mainhead.jspf" %>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<title>User manage</title>
</head>
<body class="crm_body_bg">
<%@ include file="common/mainbar.jspf" %>   

<section class="main_content dashboard_part large_header_bg">
   <div class="main_content_iner ">
        <div class="container-fluid p-0">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="dashboard_header mb_50">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="dashboard_header_title">
                                    <h3> <spring:message code="btn.Profile" text="ข้อมูลส่วนตัว" /></h3>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="dashboard_breadcam text-end">
                                    <p><spring:message code="btn.index" text="หน้าเเรก" /> <i class="ti-angle-right"></i> <spring:message code="btn.Profile" text="ข้อมูลส่วนตัว" /></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
             
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
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.passwordpattern" text="ชื่อผู้ใช้รูปเเบบไม่ถูกต้อง" /></div>
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
                          <form:form method="POST" modelAttribute="user" action="add-mainusermanage">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                               
                                <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.username" text="ชื่อผู้ใช้" /></label>
                                    <input type="text" name="username"  class="form-control"  value="${user.username }">
                               
                                </div>
                                   <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.password" text="รหัสผ่าน" /></label>
                                    <input type="text" name="password"  class="form-control"  value="${user.password }">
                               
                                </div>
                                     <div class="mb-3">

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
            </div>
        </div>
    </div>

</section>

      <script>
      var element = document.getElementById("usermanage3");
      element.classList.add("active");      
  </script>
</body>
<%@ include file="common/mainfoot.jspf" %>  




