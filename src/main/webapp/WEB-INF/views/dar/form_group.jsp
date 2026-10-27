
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
                                <c:when test='${document_nos.document_no_id > 0}'>
                                  <c:set var="link" value="/editgroup2-dar"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/addgroup2-dar"/>  
                              </c:otherwise>
                               </c:choose>  
                      
                      
                                <div class="card-body">
                                 <form:form method="POST" modelAttribute="document_no" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 
                                 
                                  <c:if test='${document_nos.document_no_id > 0}'>
                                   <input  type="hidden" name="document_no_id" value="${document_nos.document_no_id}">    
                                   </c:if>
                                    
                                    
                                          <div class="row">
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="common.doc" /></label>
                                                  
                                                      <select name="document_doc"  class="form-select"  >
                                    
                                     <c:set var="type1" value="" />
                                    <c:set var="type2" value="" />
                                    <c:set var="type3" value="" />
                                    <c:set var="type4" value="" />
                                     <c:set var="type5" value="" />
                                     
                                          
                                     <c:if test="${document_nos.document_doc.equals('Quality Manual (QM)')}">  
                                    <c:set var="type1" value="selected" />
                                  </c:if> 
                                  <c:if test="${document_nos.document_doc.equals('Quality Procedure (QP)')}">  
                                    <c:set var="type2" value="selected" />
                                  </c:if> 
                                  <c:if test="${document_nos.document_doc.equals('Work Instruction (WI)')}">  
                                    <c:set var="type3" value="selected" />
                                  </c:if> 
                                  <c:if test="${document_nos.document_doc.equals('Supporting Data (SD)')}">  
                                    <c:set var="type4" value="selected" />
                                  </c:if>
                                  <c:if test="${document_nos.document_doc.equals('Form (FO)')}">
                                    <c:set var="type5" value="selected" />
                                  </c:if> 
                                        
                                         
                                         
                                                    
                                                    <option value="Quality Manual (QM)" ${type1 }>Quality Manual (QM)</option>
                                                    <option value="Quality Procedure (QP)" ${type2 }>Quality Procedure (QP)</option>
                                                    <option value="Work Instruction (WI)" ${type3 }>Work Instruction (WI)</option>
                                                     <option value="Supporting Data (SD)" ${type4 }>Supporting Data (SD) </option>
                                                     <option value="Form (FO)" ${type5 }>Form (FO)</option>

                                                    </select>
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="last-name-column"><spring:message code="common.department" /></label>
                                                     <select class=" form-select"  name="dar_department" id="default-select-multi">
                                           <option value="0">ALL</option> 
                                          
                                                <c:forEach items="${departments }" var="row" varStatus="count">
                                                    <option value="${row.department_id }" 
                                                    <c:if test='${row.department_id == document_nos.document_department }'>selected</c:if> >
                                                    ${row.department_name }
                                                    </option>
                                               </c:forEach>
                                           </select>
                                                </div>
                                            </div>
                                     </div>
                                     
                                        <div class="row">
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="common.pattern" /></label>
                                                    <input type="text" id="first-name-column" class="form-control"  name="document_no_pattern" value="${document_nos.document_no_pattern }">
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="last-name-column"><spring:message code="common.note"  /></label>
                                                    <input type="text" id="last-name-column" class="form-control" name="document_no_note" value="${document_nos.document_no_note }">
                                                </div>
                                            </div>
                                         
                                   
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
				                               <c:choose>
				                                <c:when test='${document_nos.document_no_id  > 0}'>
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
   
   var element = document.getElementById("dar_set1");
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