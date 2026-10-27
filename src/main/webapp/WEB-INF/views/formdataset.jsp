<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Data set</title>
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
                                    <h4 class="card-title">Data set</h4>
                                </div>
                                <div class="card-body">
                                      <c:choose>
                                <c:when test='${datasets.dataset_id > 0}'>
                                  <c:set var="link" value="/editdataset-condition"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/adddataset-condition"/>  
                              </c:otherwise>
                               </c:choose>  
                                
                                
                                    <form:form method="POST" modelAttribute="folders" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${datasets.dataset_id > 0}">    
                                 <input  type="hidden" name="dataset_id" value="${datasets.dataset_id }">
                                  
                               </c:if>  
                          
                                                       <input type="hidden" name="setgroup"  class="form-control"  value="${setgroup }">
                                                
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Name </label>
                                                    <input type="text" name="dataset_name"  class="form-control"  value="${datasets.dataset_name }">
                                                </div>
                                                 <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Main data ***</label>
                                                    <input type="text" name="dataset_1"  class="form-control"  value="${datasets.dataset_1 }" required>
                                                </div>
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Sub data1 </label>
                                                    <input type="text" name="dataset_2"  class="form-control"  value="${datasets.dataset_2 }">
                                                </div>
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Sub data2</label>
                                                    <input type="text" name="dataset_3"  class="form-control"  value="${datasets.dataset_3 }">
                                                </div>
                                                  <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Sub data3</label>
                                                    <input type="text" name="dataset_4"  class="form-control"  value="${datasets.dataset_4 }">
                                                </div>
                                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">Sub data4</label>
                                                    <input type="text" name="dataset_5"  class="form-control"  value="${datasets.dataset_5 }">
                                                </div>
                                     <c:set var="Public" value="" />
                                       <c:set var="Private" value="" />               
                              <c:if test="${datasets.dataset_type == 'Public'}">    
                                  <c:set var="Public" value="checked" />
                               </c:if> 
                                <c:if test="${datasets.dataset_type == 'Private'}">    
                                  <c:set var="Private" value="checked" />
                               </c:if>       
                             
                                               
                                                
                                                
                                            </div>
                                    
                                
                                            <div class="col-12">
                              <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <c:choose>
                                <c:when test='${datasets.dataset_id > 0}'>
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
    
    var element = document.getElementById("e-form2");
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


