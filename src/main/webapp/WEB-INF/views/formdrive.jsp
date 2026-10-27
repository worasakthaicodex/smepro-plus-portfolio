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
                                    <h4 class="card-title">Drives</h4>
                                </div>
                                <div class="card-body">
                                      <c:choose>
                                <c:when test='${drives.drives_id > 0}'>
                                  <c:set var="link" value="/editdrives-filemanager"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/adddrives-filemanager"/>  
                              </c:otherwise>
                               </c:choose>  
                                
                                
                                    <form:form method="POST" modelAttribute="drives" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${drives.drives_id > 0}">    
                                 <input  type="hidden" name="drives_id" value="${drives.drives_id }">
                               </c:if>  
                                 
                                 
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">
                                                    <spring:message code="drives.name" text="ชื่อ Drives" /></label>
                                                    <input type="text" name="drives_name"  class="form-control"  value="${drives.drives_name }">
                                                </div>
                                            </div>
                                    
                                           <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">
                                                    <spring:message code="drives.size" text="ขนาด Drives" /></label>
                                                  
                                                    
                                                          <c:if test="${drives.drives_size == 100000000}">    
                                                              <c:set var="size1" value="selected" />
                                                         </c:if>  
                                                      <c:if test="${drives.drives_size == 250000000}">    
                                                              <c:set var="size2" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${drives.drives_size == 500000000}">    
                                                              <c:set var="size3" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${drives.drives_size == 1000000000}">    
                                                              <c:set var="size4" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${drives.drives_size == 50000000000}">    
                                                              <c:set var="size5" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${drives.drives_size == 250000000000}">    
                                                              <c:set var="size6" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${drives.drives_size == 500000000000}">    
                                                              <c:set var="size7" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${drives.drives_size == 1000000000000}">    
                                                              <c:set var="size8" value="selected" />
                                                         </c:if>  
                                                    
                                                    <select name="drives_size"  class="form-select">
                                                  
                                                    <option value="100000000" ${size1 }>100MB</option>
                                                    <option value="250000000" ${size2 }>250MB</option>
                                                    <option value="500000000" ${size3 }>500MB</option>
                                                    <option value="1000000000" ${size4 }>1GB</option>
                                                     <option value="50000000000" ${size5 }>50GB</option>
                                                     <option value="250000000000" ${size6 }>250GB</option>
                                                      <option value="500000000000" ${size7 }>500GB</option>
                                                      <option value="1000000000000" ${size8 }>1TB</option>
                                                    </select>
                                                </div>
                                            </div>  
                                             </div>  
                                            <div class="col-12">
                              <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <c:choose>
                                <c:when test='${drives.drives_id > 0}'>
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
        
                      var element = document.getElementById("nav_doc");
  element.classList.add("active");  
    </script>
</body>


