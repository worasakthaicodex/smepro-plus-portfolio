<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>index</title>
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
                                    <h4 class="card-title">Role</h4>
                                </div>
                                <div class="card-body">
                                      <c:choose>
                                <c:when test='${groups.id > 0}'>
                                  <c:set var="link" value="/editrole-usermanage"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/addrole-usermanage"/>  
                              </c:otherwise>
                               </c:choose>  
                                
                                
                                    <form:form method="POST" modelAttribute="building" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${groups.id > 0}">    
                                 <input  type="hidden" name="id" value="${groups.id }">
                               </c:if>  
                                 
                                 
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="usermanage.role" text="บทบาท" /></label>
                                                    <input type="text" name="group_name"  class="form-control"  value="${groups.group_name }">
                                                </div>
                                            </div>
                                            
                                             <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="tables.JobLevel" text="" /></label>
                                                     <select name="group_level"  class="form-select">
                                                  
                                           
                                                       <c:set var="level1" value="" />
                                                       <c:if test="${row.group_level == 'Labour'}">    
                                                              <c:set var="level1" value="selected" />
                                                         </c:if>  
                                                          <c:set var="level2" value="" />
                                                       <c:if test="${row.group_level == 'Seniour Labour'}">    
                                                              <c:set var="level2" value="selected" />
                                                         </c:if>  
                                                           <c:set var="level3" value="" />
                                                       <c:if test="${row.group_level == 'Supervisor'}">    
                                                              <c:set var="level3" value="selected" />
                                                         </c:if>  
                                                           <c:set var="level4" value="" />
                                                       <c:if test="${row.group_level == 'Manager'}">    
                                                              <c:set var="level4" value="selected" />
                                                         </c:if>   
                                                            <c:set var="level5" value="" />
                                                       <c:if test="${row.group_level == 'Director'}">    
                                                              <c:set var="level5" value="selected" />
                                                         </c:if>   
                                                         
                                                         
                                                         
                                                          <option value="Labour" ${level5 }>Labour</option>
                                                             <option value="Seniour Labour" ${level4 }>Seniour Labour</option>
                                                                 <option value="Supervisor" ${level3 }>Supervisor</option>  
                                                                 <option value="Manager" ${level2 }>Manager</option>
                                                      <option value="Director" ${level1 }>Director</option>
                                                  
                                             
                                                       
                                                             
                                                
                                                    </select>
                                                </div>
                                            </div>
                                            
                                            
                                               <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="tables.department" text="" /></label>
                                                     <select name="group_department"  class="form-select">
                                                  
                                                  <c:forEach items="${department }" var="row" >
                                                     
                                                       <c:set var="s_department" value="" />
                                                       <c:if test="${row.department_name == groups.group_department}">    
                                                              <c:set var="s_department" value="selected" />
                                                         </c:if>  
                                                         
                                                      <option value="${row.department_name }" ${s_department }>${row.department_name }</option>
                                                  </c:forEach>
                                                
                                                    </select>
                                                </div>
                                            </div>
                                        
                                         
                                            <div class="col-12">
                              <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <c:choose>
                                <c:when test='${groups.id > 0}'>
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
        
 var element = document.getElementById("usermanage3");
      element.classList.add("active");   
    </script>
</body>


