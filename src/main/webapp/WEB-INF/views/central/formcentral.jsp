<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    

<title>Central</title>
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
                                    <h4 class="card-title">Central</h4>
                                </div>
                                
                                
                                <div class="card-body">
                                      <c:choose>
                                <c:when test='${centrals.central_id > 0}'>
                                  <c:set var="link" value="/editcentral-condition"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/addcentral-condition"/>  
                              </c:otherwise>
                               </c:choose>  
                                
                                
                                    <form:form method="POST" modelAttribute="central" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${centrals.central_id > 0}">    
                                 <input  type="hidden" name="central_id" value="${centrals.central_id }">
                                      <input type="hidden" name="central_level"  class="form-control"  value="${centrals.central_level }">
      							   <input type="hidden" name="central_idrefer"  class="form-control"  value="${centrals.central_idrefer }">
                                   
                               </c:if>  
                                 
                                         <c:if test="${centrals.central_id == null}">  
                                    <input type="hidden" name="central_level"  class="form-control"  value="${level }">
                                       <input type="hidden" name="central_idrefer"  class="form-control"  value="${idrefer }">
                                    </c:if>
                                   
                                   
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Central Name </label>
                                                    <input type="text" name="central_name1"  class="form-control"  value="${centrals.central_name1 }">
                                                </div>
                                                 <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Central Name (EN) </label>
                                                    <input type="text" name="central_name2"  class="form-control"  value="${centrals.central_name2 }">
                                                </div>
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Central type </label>
                                                    <input type="text" name="central_type1"  class="form-control"  value="${centrals.central_type1 }">
                                                </div>
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Central type  (EN)</label>
                                                    <input type="text" name="central_type2"  class="form-control"  value="${centrals.central_type2 }">
                                                </div>
                                              
                                             
                   
                                                
                                                
                                            </div>
                                    
                                
                                            <div class="col-12">
                              <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <c:choose>
                                <c:when test='${centrals.central_id  > 0}'>
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
    
    var element = document.getElementById("e-form3");
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
</body>


