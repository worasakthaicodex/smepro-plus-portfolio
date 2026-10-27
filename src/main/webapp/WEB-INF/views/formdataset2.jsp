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
               
               <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            <h2 class="content-header-title float-start mb-0">Register</h2>
                          
                        </div>
                        
                        
                    </div>
                    
                  
                    
                </div>
                     <div class="row">
		                        <div class="col-12">
		                            <div class="card"> 
		           
		                                <div class="card-body">
		 						
                                                
		     <%@ include file="Subdata/register.jspf" %>                        
		                       
		                       
		                             
		                                
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
        

 <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">เพิ่มข้อมูล</h1>
                              
                      
                                <!-- form -->
                                <form:form method="POST" modelAttribute="folders" action="/addsubdataset-condition">
                      
                                   
                              
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${subdatasets.dataset_data_id > 0}">    
                                 <input  type="hidden" name="dataset_data_id" value="${subdatasets.dataset_data_id }">
                               </c:if>  
                                   <input  type="hidden" name="dataset_data_id_refer" value="${datasets.dataset_id }">
                               
                                        <div class="row">
                                        
                           
                                          <div class="col-md-12 col-12">
                                                 <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_1 } ***</label>
                                                    <input type="text" name="dataset_data_title"  class="form-control"  value="" required>
                                                </div>
                                           
                           
                                             
                                     <c:if test="${not empty  datasets.dataset_2}">                
                                                     
                                
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_2 }</label>
                                                    <input type="text" name="dataset_data_subtitle1"  class="form-control"  value="">
                                                </div>
                                          
                                         </c:if>        
                                             
                                        <c:if test="${not empty  datasets.dataset_3 }">            
                                            
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_3 }</label>
                                                    <input type="text" name="dataset_data_subtitle2"  class="form-control"  value="">
                                                </div>
                                           
                                            </c:if>       
                                          
                                                       
                                        <c:if test="${not empty  datasets.dataset_4 }">         
                                           
                                                  <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_4 }</label>
                                                    <input type="text" name="dataset_data_subtitle3"  class="form-control"  value="">
                                        
                                                </c:if>        
                                                
                                             <c:if test="${not empty  datasets.dataset_5}">               
                                                     
                                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_5 }</label>
                                                    <input type="text" name="dataset_data_subtitle4"  class="form-control"  value="">
                                                </div>
                                            
                                                   </c:if>      
                                            
                                                    </div> 
                                                
                                                
                                            </div>
                        
                                 

                                    <div class="col-12 text-center">
                                         <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <c:choose>
                                <c:when test='${subdatasets.dataset_data_id > 0}'>
                                <spring:message code="btn.edit" text="แก้ไข" />
                                </c:when>
                                <c:otherwise>
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </c:otherwise>
         
                               </c:choose>       
                               </button> 
                                    </div>
                              </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->


 <div class="modal fade" id="addNewCard2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">แกไขข้อมูล</h1>
                              
                      
                                <!-- form -->
                                <form:form method="POST" modelAttribute="folders" action="/editsubdataset-condition">
                      
                                   
                              
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    
                                 <input  type="hidden" id="dataset_data_id"  name="dataset_data_id" value="">
                                <input  type="hidden"  id="dataset_data_id_refer" name="dataset_data_id_refer" value="${datasets.dataset_id }">
                               
                                        <div class="row">
                                        
                           
                                          <div class="col-md-12 col-12">
                                                 <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_1 } ***</label>
                                                    <input type="text"  id="dataset_data_title" name="dataset_data_title"  class="form-control"  value="" required>
                                                </div>
                                           
                           
                                             
                                     <c:if test="${not empty  datasets.dataset_2}">                
                                                     
                                
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_2 }</label>
                                                    <input type="text" id="dataset_data_subtitle1"  name="dataset_data_subtitle1"  class="form-control"  value="">
                                                </div>
                                          
                                         </c:if>        
                                             
                                        <c:if test="${not empty  datasets.dataset_3 }">            
                                            
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_3 }</label>
                                                    <input type="text"  id="dataset_data_subtitle2" name="dataset_data_subtitle2"  class="form-control"  value="">
                                                </div>
                                           
                                            </c:if>       
                                          
                                                       
                                        <c:if test="${not empty  datasets.dataset_4 }">         
                                           
                                                  <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_4 }</label>
                                                    <input type="text"  id="dataset_data_subtitle3"  name="dataset_data_subtitle3"  class="form-control"  value="">
                                        
                                                </c:if>        
                                                
                                             <c:if test="${not empty  datasets.dataset_5}">               
                                                     
                                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">${datasets.dataset_5 }</label>
                                                    <input type="text"  id="dataset_data_subtitle4" name="dataset_data_subtitle4"  class="form-control"  value="">
                                                </div>
                                            
                                                   </c:if>      
                                            
                                                    </div> 
                                                
                                                
                                            </div>
                        
                                 

                                    <div class="col-12 text-center">
                                         <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">
                                <c:choose>
                                <c:when test='${subdatasets.dataset_data_id > 0}'>
                                <spring:message code="btn.edit" text="แก้ไข" />
                                </c:when>
                                <c:otherwise>
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </c:otherwise>
         
                               </c:choose>       
                               </button> 
                                    </div>
                              </form:form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->

<%@ include file="common/mainfoot.jspf" %>  
 
 
 
</body>


