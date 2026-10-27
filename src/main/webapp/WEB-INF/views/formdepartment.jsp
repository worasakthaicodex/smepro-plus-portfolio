<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Department</title>
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
                                    <h4 class="card-title">Department</h4>
                                </div>
                                <div class="card-body">
                                      <c:choose>
                                <c:when test='${department.department_id > 0}'>
                                  <c:set var="link" value="/editdepartment-usermanage"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/adddepartment-usermanage"/>  
                              </c:otherwise>
                               </c:choose>  
                                
                                
                                    <form:form method="POST" modelAttribute="department" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${department.department_id > 0}">    
                                 <input  type="hidden" name="department_id" value="${department.department_id }">
                               </c:if>  
                                 
                                 
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="tables.department" text="หน่วยงาน" /></label>
                                                    <input type="text" name="department_name"  class="form-control"  value="${department.department_name }">
                                                </div>
                                            </div>
                                         
                                            <div class="col-12">
                              <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <c:choose>
                                <c:when test='${department.department_id > 0}'>
                                <spring:message code="btn.edit" text="แก้ไข" />
                                </c:when>
                                <c:otherwise>
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </c:otherwise>
         
                               </c:choose>       
                               </button>   
                                            </div>
                                        </div>
                                    </form:form>
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
 
 
    <script>
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
   var element = document.getElementById("usermanage1");
    element.classList.add("active");   
    </script>
</body>


