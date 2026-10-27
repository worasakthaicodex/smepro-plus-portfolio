<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">


<link href="<c:url value="/resources/app-assets/vendors/css/vendors.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/form-validation.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/form-wizard.css" />" rel="stylesheet">




<title>Upload</title>

<style>

.select2-selection__arrow{
display:none;
}


</style>

<c:choose>
                                <c:when test='${cookie.theme.value.equals("moon")}'>
                               			<style>
											.select2-container--default .select2-selection--single .select2-selection__rendered{
											color:#b4b7bd;
											}
									 </style>
                                </c:when>
                                <c:otherwise>
								
                              </c:otherwise>
                               </c:choose> 

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  




    <!-- BEGIN: Content-->
    <div class="app-content content ">
 
        <div class="content-wrapper container-xxl p-0">
            <c:choose>
                                <c:when test='${upload_pn.upload_pn_id > 0}'>
                                  <c:set var="link" value="/editupload_pn-filemanager"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/addupload_pn-filemanager"/>  
                              </c:otherwise>
                               </c:choose>  
               
        
                                    <form:form method="POST" modelAttribute="upload_pn" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <c:if test="${upload_pn.upload_pn_id > 0}">    
                                 <input  type="hidden" name="upload_pn_id" value="${upload_pn.upload_pn_id }">
                               
                               </c:if>  
                                <input  type="hidden" name="idfolder" value="${idfolder }">
                                    <input  type="hidden" name="upload_pn_date" value='<fmt:formatDate pattern = "yyyy-MM-dd" value = "${now}" />'>
               <section class="modern-horizontal-wizard" style="height:500px">
               
                   
                    <div class="bs-stepper wizard-modern modern-wizard-example">
                        <div class="bs-stepper-header">
                            <div class="step active" data-target="#account-details-modern" role="tab" id="account-details-modern-trigger">
                                <button type="button" class="step-trigger" aria-selected="true">
                                        <span class="bs-stepper-box">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-link font-medium-3"><path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"></path><path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"></path></svg>
                                    </span>
                                    <span class="bs-stepper-label">
                                        <span class="bs-stepper-title"><spring:message code="plan.set" text="กำหนดแผนงาน"  /></span>
                                        <span class="bs-stepper-subtitle"><spring:message code="plan.setname" text="กำหนดชื่อเแผนงาน"  /></span>
                                    </span>
                                </button>
                            </div>
                            <div class="line">
                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-chevron-right font-medium-2"><polyline points="9 18 15 12 9 6"></polyline></svg>
                            </div>
                            <div class="step" data-target="#personal-info-modern" role="tab" id="personal-info-modern-trigger">
                                <button type="button" class="step-trigger" aria-selected="false">
                                 
                                      <span class="bs-stepper-box">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file-text font-medium-3"><path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path><polyline points="14 2 14 8 20 8"></polyline><line x1="16" y1="13" x2="8" y2="13"></line><line x1="16" y1="17" x2="8" y2="17"></line><polyline points="10 9 9 9 8 9"></polyline></svg>
                                    </span>
                                    <span class="bs-stepper-label">
                                        <span class="bs-stepper-title"><spring:message code="plan.setfile" text="กำหนดไฟล์เเนบ"  /></span>
                                        <span class="bs-stepper-subtitle"><spring:message code="plan.setfilenote" text="กำหนดไฟล์ที่ต้องการให้ผู้รับผิดชอบเเนบ"  /></span>
                                    </span>
                                </button>
                            </div>
                            <div class="line">
                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-chevron-right font-medium-2"><polyline points="9 18 15 12 9 6"></polyline></svg>
                            </div>
                            <div class="step" data-target="#address-step-modern" role="tab" id="address-step-modern-trigger">
                                <button type="button" class="step-trigger" aria-selected="false">
                                    <span class="bs-stepper-box">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-map-pin font-medium-3"><path d="M21 10c0 7-9 13-9 13s-9-6-9-13a9 9 0 0 1 18 0z"></path><circle cx="12" cy="10" r="3"></circle></svg>
                                    </span>
                                    <span class="bs-stepper-label">
                                        <span class="bs-stepper-title"><spring:message code="plan.setfrequency" text="กำหนดความถี่"  /></span>
                                        <span class="bs-stepper-subtitle"><spring:message code="plan.setfrequencynote" text="กำหนดระยะเวลาที่เหมาะสมสำหรับการส่งรายงาน"  /></span>
                                    </span>
                                </button>
                            </div>
                            <div class="line">
                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-chevron-right font-medium-2"><polyline points="9 18 15 12 9 6"></polyline></svg>
                            </div>
                            <div class="step" data-target="#social-links-modern" role="tab" id="social-links-modern-trigger">
                                <button type="button" class="step-trigger" aria-selected="false" >
                               
                                            <span class="bs-stepper-box">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-user font-medium-3"><path d="M20 21v-2a4 4 0 0 0-4-4H8a4 4 0 0 0-4 4v2"></path><circle cx="12" cy="7" r="4"></circle></svg>
                                    </span>
                                    <span class="bs-stepper-label">
                                        <span class="bs-stepper-title"><spring:message code="plan.responsibility" text="กำหนดผู้รับผิดชอบ"  /></span>
                                        <span class="bs-stepper-subtitle"><spring:message code="plan.responsibilitynote" text="กำหนดผู้อัพโหลดเละผู้ตรวจสอบ"  /></span>
                                    </span>
                                </button>
                            </div>
                        </div>
                        <div class="bs-stepper-content">
                            <div id="account-details-modern" class="content active dstepper-block" role="tabpanel" aria-labelledby="account-details-modern-trigger">
                                <div class="content-header">
                                    <h5 class="mb-0"><spring:message code="plan.set" text="กำหนดแผนงาน"  /></h5>
                                    <small class="text-muted"></small>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-username"><spring:message code="plan.setname" text="กำหนดชื่อเแผนงาน"  /></label>
                                        <input type="text" name="upload_pn_note"  class="form-control"  value="${upload_pn.upload_pn_note }">
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-email">E-form</label>
                                         <select class="form-select select2"   name="upload_pn_monitor" id="default-select-multi3" >   
                               	    
                               	    
                               	    
                               	      <c:set var="monitors" value="" />
                               	      <c:if test="${upload_pn.upload_pn_monitor == 0 }">    
                                                              <c:set var="monitors" value="selected" />
                                           </c:if>  
                               	     
                               	      <option value="0" ${monitor }>Disable</option>   
                               	      
                               	      
                               	                   
                                    <c:forEach items="${conditions }" var="row" varStatus="count">
                                    
               							 <c:set var="monitor" value="" />
                                     <c:if test="${upload_pn.upload_pn_monitor == row.condition_id }">    
                                         <c:set var="monitor" value="selected" />
                                     </c:if>  
                                     
                                            <option value="${row.condition_id }" ${monitor }>${row.condition_name }</option>
                                      </c:forEach>
                                  </select>
                                    </div>
                                </div>
                        <br> <br>
                                <div class="d-flex justify-content-between">
                                    <button type="button" class="btn btn-outline-secondary btn-prev waves-effect" disabled="">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left align-middle me-sm-25 me-0"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                                        <span class="align-middle d-sm-inline-block d-none"><spring:message code="btn.previous"  /></span>
                                    </button>
                                    <button type="button" class="btn btn-primary btn-next waves-effect waves-float waves-light">
                                        <span class="align-middle d-sm-inline-block d-none"><spring:message code="btn.next"  /></span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right align-middle ms-sm-25 ms-0"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>
                                    </button>
                                </div>
                            </div>
                            <div id="personal-info-modern" class="content" role="tabpanel" aria-labelledby="personal-info-modern-trigger">
                                <div class="content-header">
                                    <h5 class="mb-0"><spring:message code="plan.setfile" text="กำหนดไฟล์เเนบ"  /></h5>
                                    <small></small>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                    
                      
                                    
                                        <label class="form-label" for="modern-first-name">1.<spring:message code="tables.record" text="ชื่อของเอกสารที่จะเก็บไว้"  /></label>
                                  
                                        
                                                      <div class="input-group">
                                                    <div class="input-group-text">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="inputCheckbox1" onchange="toggleReadonly(1,0)" >
                                                        </div>
                                                    </div>
                                                         <input type="text" id="upload_pn_name" name="upload_pn_name"  class="form-control"  value="${upload_pn.upload_pn_name }" readonly>
                                                </div>
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-last-name">2.<spring:message code="tables.record" text="ชื่อของเอกสารที่จะเก็บไว้"  /></label>
                                       
                                            <div class="input-group">
                                                    <div class="input-group-text">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="inputCheckbox2" onchange="toggleReadonly(2,0)">
                                                        </div>
                                                    </div>
                                                        <input type="text" name="upload_pn_name2" id="upload_pn_name2"  class="form-control"  value="${upload_pn.upload_pn_name2 }" readonly>
                                                </div>
                                       
                                    </div>
                                </div>
                                
                                   <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-first-name">3.<spring:message code="tables.record" text="ชื่อของเอกสารที่จะเก็บไว้"  /></label>
                                        
                                        
                                          <div class="input-group">
                                                    <div class="input-group-text">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="inputCheckbox3" onchange="toggleReadonly(3,0)">
                                                        </div>
                                                    </div>
                                                    <input type="text" name="upload_pn_name3" id="upload_pn_name3"   class="form-control"  value="${upload_pn.upload_pn_name3 }" readonly>
                                                      
                                                </div>
                                       
                                        
                                        
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-last-name">4.<spring:message code="tables.record" text="ชื่อของเอกสารที่จะเก็บไว้"  /></label>
                                        
                                           
                                          <div class="input-group">
                                                    <div class="input-group-text">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="inputCheckbox4" onchange="toggleReadonly(4,0)">
                                                        </div>
                                                    </div>
                                                    <input type="text" name="upload_pn_name4" id="upload_pn_name4"   class="form-control"  value="${upload_pn.upload_pn_name4 }" readonly>
                                                      
                                                </div>
                                        
                                 
                                    </div>
                                </div>  
                                     <div class="row">
                                 <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-last-name">5.<spring:message code="tables.record" text="ชื่อของเอกสารที่จะเก็บไว้"  /></label>
                                        
                                           
                                          <div class="input-group">
                                                    <div class="input-group-text">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="inputCheckbox5" onchange="toggleReadonly(5,0)">
                                                        </div>
                                                    </div>
                                                    <input type="text" name="upload_pn_name5"  id="upload_pn_name5"  class="form-control"  value="${upload_pn.upload_pn_name5 }" readonly>
                                                      
                                                </div>
                                        
                                 
                                    </div>
                                   <div class="mb-1 col-md-6" style="display:none">
                                        <label class="form-label" for="modern-last-name">6.<spring:message code="tables.record" text="ชื่อของเอกสารที่จะเก็บไว้"  /></label>
                                        
                                           
                                          <div class="input-group">
                                                    <div class="input-group-text">
                                                        <div class="form-check">
                                                            <input class="form-check-input" type="checkbox" id="inputCheckbox" onchange="toggleReadonly()">
                                                        </div>
                                                    </div>
                                                    <input type="text" name="upload_pn_name6"  class="form-control"  value="${upload_pn.upload_pn_name5 }" readonly>
                                                      
                                                </div>
                                        
                                 
                                    </div>
                                </div>  
                                
                                <br>
                                <hr>
                                    <br>  
                               <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-first-name"><spring:message code="due.namefile" text="การกำหนดรูปเเบบชื่อบันทึก" /></label>
                                         <c:set var="namefile1" value="" />
                                                          <c:if test="${upload_pn.upload_pn_namefile == 0}">    
                                                              <c:set var="namefile1" value="selected" />
                                                         </c:if>  
                                                                  <c:set var="namefile2" value="" />
                                                      <c:if test="${upload_pn.upload_pn_namefile == 1}">    
                                                              <c:set var="namefile2" value="selected" />
                                                         </c:if>  
                                                          <c:set var="namefile3" value="" />
                                                      <c:if test="${upload_pn.upload_pn_namefile == 3}">    
                                                              <c:set var="namefile3" value="selected" />
                                                         </c:if>  
                                                            <c:set var="namefile4" value="" />
                                                      <c:if test="${upload_pn.upload_pn_namefile == 4}">    
                                                              <c:set var="namefile4" value="selected" />
                                                         </c:if>  
                                                                
                                                     
                                                    
                                                    <select name="upload_pn_namefile"  class="form-select" >
                                             <option value="3" ${namefile3 }><spring:message code="due.namefile3" text="ไม่เเก้ไขชื่อไฟล์" /> (<spring:message code="usermanage.recommend" text="เเนะนำ" />)</option>
                                                      <option value="0" ${namefile1 }><spring:message code="due.namefile1" text="ชื่อบันทึกตามด้วยชื่อผู้อัพโหลด"  /></option>
                                                    <option value="4" ${namefile4 }><spring:message code="due.namefile4" text="ชื่อบันทึก"  /> </option>
                                                      <option value="1" ${namefile2 }><spring:message code="due.namefile2" text="ชื่อผู้อัพโหลด" /></option>
                                                          
                                                	
                                                    </select>
                                    </div>
                                      <div class="mb-1 col-md-6">
                                        <label class="form-label" for="modern-first-name"><spring:message code="due.Lifetime" text="อายุการใช้งานเอกสาร" /></label>
                                       <c:if test="${upload_pn.upload_pn_end == 0}">    
                                                              <c:set var="sizes1" value="selected" />
                                                         </c:if>  
                                                      <c:if test="${upload_pn.upload_pn_end == 1}">    
                                                              <c:set var="sizes2" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${upload_pn.upload_pn_end == 2}">    
                                                              <c:set var="sizes3" value="selected" />
                                                         </c:if>  
                                                      <c:if test="${upload_pn.upload_pn_end == 3}">    
                                                              <c:set var="sizes4" value="selected" />
                                                         </c:if> 
                                                             <c:if test="${upload_pn.upload_pn_end == 4}">    
                                                              <c:set var="sizes5" value="selected" />
                                                         </c:if> 
                                                          
                                                         
                                                    <select name="upload_pn_end"  class="form-select">
                                                      <option value="0" ${size1 }>Indefinite</option>
                                                      <option value="1" ${size2 }>6 Month </option>
                                                      <option value="2" ${size3 }>1 Year </option>
                                                      <option value="3" ${size4 }>3 Year</option>
                                                       <option value="4" ${size5 }>5 Year</option>
                                                    </select>
                                    </div>
                                    
                                            <div class="row">
	                                         <div class="mb-1 col-md-6">
	                                        <label class="form-label" for="modern-first-name"><spring:message code="plan.mustfile" text="ต้องอัพโหลดไฟล์ครบตามรายการ"  /></label>
	                                         <c:set var="namefile1" value="" />
	                                                          <c:if test="${upload_pn.upload_pn_filecheck == 0}">    
	                                                              <c:set var="namefile1" value="selected" />
	                                                         </c:if>  
	                                                                  <c:set var="namefile2" value="" />
	                                                      <c:if test="${upload_pn.upload_pn_filecheck == 1}">    
	                                                              <c:set var="namefile2" value="selected" />
	                                                         </c:if>  
	                                                 
	                                                    
	                                                    <select name="upload_pn_filecheck"  class="form-select" >
	  
	                                                     <option value="0" ${namefile1 }><spring:message code="plan.no" text="ไม่"  /></option>
	                                                	  <option value="1" ${namefile2 }><spring:message code="plan.yes" text="ใช่"  /></option>
	                                                    </select>
	                                    </div>
                                    </div>
                                </div>  
                         
                         
                        
                        
                 
                                                    
                                                            
                        
                         
                         
                                <div class="d-flex justify-content-between">
                                    <button type="button" class="btn btn-primary btn-prev waves-effect waves-float waves-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left align-middle me-sm-25 me-0"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                                        <span class="align-middle d-sm-inline-block d-none"><spring:message code="btn.previous"  /></span>
                                    </button>
                                    <button type="button" class="btn btn-primary btn-next waves-effect waves-float waves-light">
                                        <span class="align-middle d-sm-inline-block d-none"><spring:message code="btn.next"  /></span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right align-middle ms-sm-25 ms-0"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>
                                    </button>
                                </div>
                            </div>
                            <div id="address-step-modern" class="content" role="tabpanel" aria-labelledby="address-step-modern-trigger">
                                <div class="content-header">
                                    <h5 class="mb-0"><spring:message code="plan.setfrequency" text="กำหนดความถี่"  /></h5>
                                    <small></small>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-4">
                                        <label class="form-label" for="modern-address"><spring:message code="tables.type2" text="กำหนดการอัพโหลด" /></label>
                                        <c:if test="${upload_pn.upload_pn_type == 0}">    
                                                              <c:set var="type1" value="selected" />
                                                         </c:if>  
                                                      <c:if test="${upload_pn.upload_pn_type == 1}">    
                                                              <c:set var="type2" value="selected" />
                                                         </c:if>  
                                                    <c:if test="${upload_pn.upload_pn_type ==2}">    
                                                              <c:set var="type3" value="selected" />
                                                         </c:if>  
                                                             <c:if test="${upload_pn.upload_pn_type ==3}">    
                                                              <c:set var="type4" value="selected" />
                                                         </c:if>    
                                                     
                                                    
                                                    <select name="upload_pn_type" id="duetype"  class="form-select" onchange="mytype()">
                                                  
                                                      <option value="0" ${type1 }><spring:message code="due.type1" text="กำหนดตามช่วงเวลา"  /></option>
                                                      <option value="1" ${type2 }><spring:message code="due.type2" text="กำหนดตามวันที่" /></option>
                                                     <option value="2" ${type3 }><spring:message code="due.type3" text="ไม่มีกำหนด" /></option>
                                                     <option value="3" ${type4 } style="display:none"><spring:message code="menu.project"  /></option>
                                              
                                                    </select>
                                    </div>
                                    <div class="mb-1 col-md-4">
                                      
                                        
                                                                <c:if test="${upload_pn.upload_pn_type == 0 || upload_pn.upload_pn_type == null}"> 
                                                   <c:set var="duetype1" value="blog" />
                                                   <c:set var="duetype2" value="none" />
                                                        </c:if> 
                                                       <c:if test="${upload_pn.upload_pn_type == 1}">  
                                                  <c:set var="duetype1" value="none" />
                                                   <c:set var="duetype2" value="blog" />
                                                    </c:if>       
                                                        
                                                        
                                            <div class="col-md-12 col-12" style="display:${duetype1}" id="duetype1">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"> <spring:message code="tables.due" text="ความถี่การอัพโหลด" /></label>
                                                  
                                                    
                                                          <c:if test="${upload_pn.upload_pn_due == 0}">    
                                                              <c:set var="size1" value="selected" />
                                                         </c:if>  
                                                      <c:if test="${upload_pn.upload_pn_due == 1}">    
                                                              <c:set var="size2" value="selected" />
                                                         </c:if>  
                                                           <c:if test="${upload_pn.upload_pn_due == 2}">    
                                                              <c:set var="size3" value="selected" />
                                                         </c:if>  
                                                           
                                                     
                                                    
                                                    <select name="upload_pn_due"   id="upload_pn_due"  class="form-select" onchange="mytype2()">
                                                  
                                                      <option value="0" ${size1 }><spring:message code="due.date" text="ทุกวัน"  /></option>
                                                      <option value="1" ${size2 }><spring:message code="due.week" text="ทุกอาทิตย์" /></option>
                                                      <option value="2" ${size3 }>	<spring:message code="due.month" text="ทุกเดือน"  /></option>
                                                	
                                                    </select>
                                                </div>
                                            </div>  
                                            
                                                  
                                                
                                               
                                            <div class="col-md-12 col-12" style="display:${duetype2}" id="duetype2">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"> <spring:message code="due.datedue"  /></label>
                                                  
                                     <input type="date" name="upload_pn_datedue"  class="form-control"  value="${upload_pn.upload_pn_datedue }">
                                                </div>
 
                                            </div>
                                                    
                                                        
                                                
                                                    
                                    </div>
                                    
                                      <div class="mb-1 col-md-4" id="upload_pn_setdue1"  style="display:<c:if test="${upload_pn.upload_pn_due != 1}">none</c:if>">
                                        <label class="form-label" for="first-name-column">Date</label>
                                        
                                          <c:set var="setdue" value="${upload_pn.upload_pn_setdue}" />
                                          
                                          
                                            <c:if test="${upload_pn.upload_pn_setdue == 'W-1' }">    
                                                              <c:set var="size1" value="selected" />'
                                                                 <c:set var="setdue" value="0" />
                                                         </c:if>  
              
                                                      <c:if test="${upload_pn.upload_pn_setdue == '2'}">    
                                                              <c:set var="size2" value="selected" />
                                                                     <c:set var="setdue" value="0" />
                                                         </c:if>  
                                                           <c:if test="${upload_pn.upload_pn_setdue == '3'}">    
                                                              <c:set var="size3" value="selected" />
                                                                     <c:set var="setdue" value="0" />
                                                         </c:if>  
                                                            <c:if test="${upload_pn.upload_pn_setdue == '4'}">    
                                                              <c:set var="size4" value="selected" />
                                                                     <c:set var="setdue" value="0" />
                                                         </c:if>  
                                                            <c:if test="${upload_pn.upload_pn_setdue == '5'}">    
                                                              <c:set var="size5" value="selected" />
                                                                     <c:set var="setdue" value="0" />
                                                         </c:if>  
                                                            <c:if test="${upload_pn.upload_pn_setdue == '6'}">    
                                                              <c:set var="size6" value="selected" />
                                                                     <c:set var="setdue" value="0" />
                                                         </c:if>  
                                                            <c:if test="${upload_pn.upload_pn_setdue == '7'}">    
                                                              <c:set var="size7" value="selected" />
                                                                     <c:set var="setdue" value="0" />
                                                         </c:if>    
                                                     <c:if test="${upload_pn.upload_pn_setdue == null or upload_pn.upload_pn_setdue == ''}">    
                                                            
                                                                <c:set var="setdue" value="0" />
                                                         </c:if> 
                                        
                                           <select name="upload_pn_setdue2"  class="form-select">
                                                  
                                                   <option value="1" ${sizes1 }><spring:message code="plan.setdate1" text="วันเเรกของสัปดาห์" /></option>
                                                      <option value="2" ${sizes2 }><spring:message code="plan.setdate2" text="วันที่ 2 ของสัปดาห์" /></option>
                                                      <option value="3" ${sizes3 }><spring:message code="plan.setdate3" text="วันที่ 3 ของสัปดาห์" /></option>
                                                      <option value="4" ${sizes4 }><spring:message code="plan.setdate4" text="วันที่ 4 ของสัปดาห์" /></option>
                                                	  <option value="5" ${sizes5 }><spring:message code="plan.setdate5" text="วันที่ 5 ของสัปดาห์" /></option>
                                                	  <option value="6" ${sizes6 }><spring:message code="plan.setdate6" text="วันที่ 6 ของสัปดาห์" /></option>
                                                	  <option value="7" ${sizes7 }><spring:message code="plan.setdate7" text="วันที่ 7 ของสัปดาห์" /></option>
                                                    </select>
                                                      
                                    </div>
                                    
                                     <div class="mb-1 col-md-4"  id="upload_pn_setdue2" style="display:<c:if test="${upload_pn.upload_pn_due != 2}">none</c:if>">
                                        <label class="form-label" for="first-name-column">Date</label>
                                        
                                         <input type="number" name="upload_pn_setdue"  class="form-control" min ="0" max="31" value="${setdue }">
                                                      
                                    </div>
                                    
                             
                             
                                     
                                     
                                </div>
                                       <br>  
                                   <hr><br>
                                    <div class="col-md-12 col-12" >
                                     <div class="row" >
                           		 <div class="mb-1 col-md-4" >
                                                  <label class="form-label" for="first-name-column"><spring:message code="due.start" /></label>
                                     			<input type="date" name="upload_pn_activitystart"  class="form-control"  value="${upload_pn.upload_pn_activitystart }">
                                                </div>
    								 <div class="mb-1 col-md-4" >
                                                  <label class="form-label" for="first-name-column"><spring:message code="due.within" text="ภายในวันที่" /></label>
                                     			<input type="date" name="upload_pn_activityend"  class="form-control"  value="${upload_pn.upload_pn_activityend }">
                                                </div>
                                            </div> 
                                   </div> 
                         <br>    <br>    <br>
                                <div class="d-flex justify-content-between">
                                    <button type="button" class="btn btn-primary btn-prev waves-effect waves-float waves-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left align-middle me-sm-25 me-0"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                                        <span class="align-middle d-sm-inline-block d-none"><spring:message code="btn.previous"  /></span>
                                    </button>
                                    <button  type="button" class="btn btn-primary btn-next waves-effect waves-float waves-light">
                                        <span class="align-middle d-sm-inline-block d-none"><spring:message code="btn.next"  /></span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right align-middle ms-sm-25 ms-0"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>
                                    </button>
                                </div>
                            </div>
                            <div id="social-links-modern" class="content" role="tabpanel" aria-labelledby="social-links-modern-trigger">
                                <div class="content-header">
                                    <h5 class="mb-0"><spring:message code="plan.responsibility" text="กำหนดผู้รับผิดชอบ"  /></h5>
                                    <small></small>
                                </div>
                                
                                
                                
                                
                                <div class="row">         
                                
                                
                                <div class="mb-1 col-md-4">
                                        <label class="form-label" for="modern-facebook"><spring:message code="due.selectby"  /></label>
                                
     
                                     <select class="form-select" name="selectby"   id="myselectby"  onchange="myselectbys()" >
                                         <option value="0" <c:if test="${upload_pn.id_policy == 0}">selected</c:if>><spring:message code="due.byname"  /></option>
        								 <option value="1" <c:if test="${upload_pn.id_policy > 0}">selected</c:if>><spring:message code="due.policy"  /></option>
        								
                                      </select>
                                </div>
                             
                             <div class="mb-1 col-md-4">
                                        <label class="form-label" for="modern-twitter"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /> </label>
    
                                <div id="divupload_pn_respon1">
                     <select class="select2 form-select"  name="upload_pn_respon" id="upload_pn_respon1"    multiple="multiple">
                           <c:set var="listrespon" value="${upload_pn.upload_pn_respon }" />                                 
                                                  
                          	  <c:set var="group_name" value="" />
                          	  
             	  
                          	  
        <c:forEach items="${users }" var="row" varStatus="count">
     
       
        	  <c:if test="${row.group_name != group_name}">  
        	
        	  
        	    <optgroup label="${row.group_name}">
        	   <c:set var="group_name" value="${row.group_name}" />

                <c:forEach items="${users }" var="row" varStatus="count">
            	<c:if test="${row.user_name != '' and row.group_name == group_name}">  
	                                      <c:set var="listcheck" value="${row.user_name }" />
	                                           
	                                                        <c:set var="check" value="" />
						                                     <c:if test='${fn:contains(listrespon,listcheck)}'>
						                                       <c:set var="check" value="selected" />
						                                     </c:if>  	          
	                                 
  
          <option value="${row.user_name }"${check }>${row.user_name } (${row.group_name})</option>
                </c:if>
         </c:forEach>  
         </optgroup>
        </c:if>
        
        </c:forEach>                        
                                                  

            </select>                        
                    </div>   
                         <div id="divupload_pn_respon2"> 
            <select class="form-select"  name="id_policy" id="upload_pn_respon2"   >    
              		 <option value="0">-</option>                                                
                        <c:forEach items="${mypolicy }" var="row" varStatus="count">
                        <option value="${row.id }"   <c:if test='${upload_pn.id_policy == row.id}'> selected </c:if> >${row.name }</option>
               		   </c:forEach>  
                    	  
            </select>   
            </div>
                                    </div>
                  
                  
                  
                                    <div class="mb-1 col-md-4">
                                        <label class="form-label" for="modern-facebook"><spring:message code="tables.inspector" text="ผู้ดูเเล" /></label>
                                            <c:set var="listrespon2" value="${upload_pn.upload_pn_app }" />
                            
                                                     
                                                      <select class="form-select select2 "   name="upload_pn_app" id="default-select-multi2" required>
                                                     
                                                     
                                                     
                                                        <c:forEach items="${users }" var="row" varStatus="count">
                                                        
                                                        <c:set var="check" value="" />
					                                     <c:if test='${upload_pn.upload_pn_app == row.user_name}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>    
                                                    <option value="${row.user_name }"  ${check } >${row.user_name } (${row.group_name})</option>
                                                 
                                                     
                                                      </c:forEach>
                                                </select>
                                    </div>
                            
                  
                         </div>
                        
                                <div class="d-flex justify-content-between">
                                    <button class="btn btn-primary btn-prev waves-effect waves-float waves-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left align-middle me-sm-25 me-0"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                                        <span class="align-middle d-sm-inline-block d-none"><spring:message code="btn.previous"  /></span>
                                    </button>
                                    <button class="btn btn-success btn-submit waves-effect waves-float waves-light"><spring:message code="btn.save"  /></button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                           </form:form>
                    
             
            </div>
        </div>



    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





<%@ include file="common/mainfoot.jspf" %>  
  <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>



    <script>
    
    
 

    
    
    
    function toggleReadonly(id,type) {
        var checkbox = document.getElementById("inputCheckbox"+id);
        
        if(id == 1){
        	 var uploadPnNameInput = document.getElementById("upload_pn_name");
        }else{
        	 var uploadPnNameInput = document.getElementById("upload_pn_name"+id);
        }
        


        if (checkbox.checked) {
            // Checkbox is checked, remove readonly and keep the value
            uploadPnNameInput.removeAttribute("readonly");
        } else {
            // Checkbox is not checked, set readonly and clear the value
            uploadPnNameInput.setAttribute("readonly", true);
           
            if(type == 0){
            	 uploadPnNameInput.value = "";	
            }else{
            	checkbox.checked = true;
            }
            
           
        }
    }
  
    
    function myselectbys(){
    	 var selectby = document.getElementById("myselectby").value;
    	
    
    	 
      	 if(selectby == 0){
      		 
      		 $("#divupload_pn_respon1").show();

      		 $("#divupload_pn_respon2").hide();
    		
      	 }else{
    		 $("#divupload_pn_respon2").show();
    		 $("#divupload_pn_respon1").hide();
    	 }
    	 
    }
    
    function mytype(){
   	 var value = document.getElementById("duetype").value;
   	
   	 
   	 
     	 if(value == 1){
   		 $("#duetype2").show();
   		 $("#duetype1").hide();
   		 $("#upload_pn_setdue1").hide();
   		 $("#upload_pn_setdue2").hide();
   		
   	 }else if(value == 2){
   
   		 $("#duetype1").hide();
   		 $("#duetype2").hide();
   		 $("#upload_pn_setdue1").hide();
   		 $("#upload_pn_setdue2").hide();
   		 
   	 } else if(value == 3){
   
   		 $("#duetype1").hide();
   		 $("#duetype2").hide();
   		 $("#upload_pn_setdue1").hide();
   		 $("#upload_pn_setdue2").hide();
   		 
   	 }else{
   		 $("#duetype1").show();
   		 $("#duetype2").hide();
   	 }
   	 
   }
   
    
   
    
    
    function mytype2(){
    	 var value = document.getElementById("upload_pn_due").value;
    	

      	 if(value == 1){
    		 $("#upload_pn_setdue1").show();
    		 $("#upload_pn_setdue2").hide();
    		
    	 }else if(value == 2){
    
    		 $("#upload_pn_setdue2").show();
    		 $("#upload_pn_setdue1").hide();
    		 
    	 }else{
    		 $("#upload_pn_setdue1").hide();
    		 $("#upload_pn_setdue2").hide();
    	 }
    	 
    }
    
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
            var value = document.getElementById("duetype").value;
         	 if(value == 1){
        		 $("#duetype2").show();
        		 $("#duetype1").hide();
        		
        	 }else if(value == 2){
        
        		 $("#duetype1").hide();
        		 $("#duetype2").hide();
        		 
        	 }else{
        		 $("#duetype1").show();
        		 $("#duetype2").hide();
        	 }
        	 
         	 var selectby = document.getElementById("myselectby").value;
         	
             
        	 
          	 if(selectby == 0){
          		 
          		 $("#divupload_pn_respon1").show();
          		 $("#divupload_pn_respon2").hide();
        		
          	 }else{
        		 $("#divupload_pn_respon2").show();
        		 $("#divupload_pn_respon1").hide();
        	 }
        	 
       
            
        })
        
        var element = document.getElementById("nav_doc");
  element.classList.add("active");      



  
  
    </script>
    
    
<c:if test="${upload_pn.upload_pn_name != '' and upload_pn.upload_pn_name != null}">
   <script>
toggleReadonly(1,1);
   </script>
</c:if>
<c:if test="${upload_pn.upload_pn_name2 != '' and upload_pn.upload_pn_name2 != null}">
   <script>
toggleReadonly(2,1);
   </script>
</c:if>
<c:if test="${upload_pn.upload_pn_name3 != '' and upload_pn.upload_pn_name3 != null}">
   <script>
toggleReadonly(3,1);
   </script>
</c:if>
<c:if test="${upload_pn.upload_pn_name4 != '' and upload_pn.upload_pn_name4 != null}">
   <script>
toggleReadonly(4,1);
   </script>
</c:if>
<c:if test="${upload_pn.upload_pn_name5 != '' and upload_pn.upload_pn_name5 != null}">
   <script>
toggleReadonly(5,1);
   </script>
</c:if>
    
    
    
</body>

                

                                    
<script src="<c:url value="/resources/app-assets/vendors/js/forms/wizard/bs-stepper.min.js" />"></script>                       
<script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>                       
<script src="<c:url value="/resources/app-assets/vendors/js/forms/validation/jquery.validate.min.js" />"></script>
    <script src="/resources/app-assets/js/scripts/forms/form-wizard.js"></script>              
                                    
                                    
  <link href="<c:url value="/lib/multiselect/css/jquery.multiselect.css" />" rel="stylesheet">   
  <link href="<c:url value="/lib/multiselect/css/app.css" />" rel="stylesheet">                                
 <script src="<c:url value="/lib/multiselect/js/jquery.multiselect.js" />"></script>
 <script src="<c:url value="/lib/multiselect/js/app.js" />"></script>
 
 
            
      
