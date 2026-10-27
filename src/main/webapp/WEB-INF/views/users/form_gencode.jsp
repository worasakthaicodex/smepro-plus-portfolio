
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    



<title>List</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  

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
                                <h4 class="card-title"><spring:message code="nav.generate_doc" /></h4>
                                 
                      
                            </div>
                             
                         
                              <c:choose>
                                <c:when test='${UserCode.user_code_id > 0}'>
                                  <c:set var="link" value="/updateUserCode-usermanage"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/insertUserCode-usermanage"/>  
                              </c:otherwise>
                               </c:choose>  
                      
                      
                                <div class="card-body">
                                 <form:form method="POST" modelAttribute="User_code" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 
                                 
                                  <c:if test='${UserCode.user_code_id > 0}'>
                                   <input  type="hidden" name="user_code_id" value="${UserCode.user_code_id}">    
                                   </c:if>
                                    
                                    
                                          <div class="row">
                                       
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="last-name-column"><spring:message code="common.department" /></label>
                                                     <select class=" form-select"  name="user_code_department" id="default-select-multi">
                                           <option value="0">ALL</option> 
                                          
                                                <c:forEach items="${departments }" var="row" varStatus="count">
                                                    <option value="${row.department_id }" 
                                                    <c:if test='${row.department_id == UserCode.user_code_department }'>selected</c:if> >
                                                    ${row.department_name }
                                                    </option>
                                               </c:forEach>
                                           </select>
                                                </div>
                                            </div>
                                                 <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="common.pattern" /></label>
                                                    <input type="text" id="first-name-column" class="form-control"  name="user_code_pattern" value="${UserCode.user_code_pattern }">
                                                </div>
                                            </div>
                                     </div>
                                     
                                        <div class="row">
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="common.code_daterun" /></label>
                                                  
                                                      <select name="user_code_daterun"  class="form-select"  >
                                 
                                                    
                                                    <option value="1"   <c:if test='${row.department_id == UserCode.user_code_daterun }'>selected</c:if> ><spring:message code="workflows.public" /></option>
                                                    <option value="0"   <c:if test='${row.department_id == UserCode.user_code_daterun }'>selected</c:if> ><spring:message code="workflows.private" /></option>
                                       

                                                    </select>
                                                </div>
                                            </div>
                                        
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="last-name-column"><spring:message code="common.note"  /></label>
                                                    <input type="text" id="last-name-column" class="form-control" name="user_code_note" value="${UserCode.user_code_note }">
                                                </div>
                                            </div>
                                         
                                     <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="last-name-column"><spring:message code="common.defaultpassword"  /></label>
                                                    <input type="text"  class="form-control" name="defaultpassword" value="${UserCode.defaultpassword }">
                                                </div>
                                            </div>
                                         
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
				                               <c:choose>
				                                <c:when test='${UserCode.user_code_id  > 0}'>
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

<%@ include file="../common/mainfoot.jspf" %>  
   <script>
   
   var element = document.getElementById("dar_set2");
   element.classList.add("active"); 
   
   
   
    $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
  
  </script>