<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    

       <link href="<c:url value="/resources/app-assets/css/pages/app-email.css" />" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>


<link href="<c:url value="/resources/app-assets/vendors/css/pickers/pickadate/pickadate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-pickadate.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 

  <link href="<c:url value="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css" />" rel="stylesheet">
  
  
   <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-wizard.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-validation.css" />" rel="stylesheet">


    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">


 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Setting App</title>


 <style>
 .select2-selection__arrow{
 
 display:none;
 }
 
 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 150px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid #dddddd;
    max-width: 150px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
}


.fixhead{
    max-width: 150px;
      min-width: 80px;
      
}





 
 
 
 .md-stepper-horizontal {
	display:table;
	width:100%;
	margin:0 auto;
	background-color:#FFFFFF;
	box-shadow: 0 3px 8px -6px rgba(0,0,0,.50);
}
.md-stepper-horizontal .md-step {
	display:table-cell;
	position:relative;
	padding:24px;
}
.md-stepper-horizontal .md-step:hover,
.md-stepper-horizontal .md-step:active {
	background-color:rgba(0,0,0,0.04);
}
.md-stepper-horizontal .md-step:active {
	border-radius: 15% / 75%;
}
.md-stepper-horizontal .md-step:first-child:active {
	border-top-left-radius: 0;
	border-bottom-left-radius: 0;
}
.md-stepper-horizontal .md-step:last-child:active {
	border-top-right-radius: 0;
	border-bottom-right-radius: 0;
}
.md-stepper-horizontal .md-step:hover .md-step-circle {
	background-color:#757575;
}
.md-stepper-horizontal .md-step:first-child .md-step-bar-left,
.md-stepper-horizontal .md-step:last-child .md-step-bar-right {
	display:none;
}
.md-stepper-horizontal .md-step .md-step-circle {
	width:30px;
	height:30px;
	margin:0 auto;
	background-color:#999999;
	border-radius: 50%;
	text-align: center;
	line-height:30px;
	font-size: 16px;
	font-weight: 600;
	color:#FFFFFF;
}
.md-stepper-horizontal.green .md-step.active .md-step-circle {
	background-color:#00AE4D;
}
.md-stepper-horizontal.orange .md-step.active .md-step-circle {
	background-color:#F96302;
}
.md-stepper-horizontal .md-step.active .md-step-circle {
	background-color: rgb(33,150,243);
}
.md-stepper-horizontal .md-step.done .md-step-circle:before {
	font-family:'FontAwesome';
	font-weight:100;
	content: "\f00c";
}
.md-stepper-horizontal .md-step.done .md-step-circle *,
.md-stepper-horizontal .md-step.editable .md-step-circle * {
	display:none;
}
.md-stepper-horizontal .md-step.editable .md-step-circle {
	-moz-transform: scaleX(-1);
	-o-transform: scaleX(-1);
	-webkit-transform: scaleX(-1);
	transform: scaleX(-1);
}
.md-stepper-horizontal .md-step.editable .md-step-circle:before {
	font-family:'FontAwesome';
	font-weight:100;
	content: "\f040";
}
.md-stepper-horizontal .md-step .md-step-title {
	margin-top:16px;
	font-size:16px;
	font-weight:600;
}
.md-stepper-horizontal .md-step .md-step-title,
.md-stepper-horizontal .md-step .md-step-optional {
	text-align: center;
	color:rgba(0,0,0,.26);
}
.md-stepper-horizontal .md-step.active .md-step-title {
	font-weight: 600;
	color:rgba(0,0,0,.87);
}
.md-stepper-horizontal .md-step.active.done .md-step-title,
.md-stepper-horizontal .md-step.active.editable .md-step-title {
	font-weight:600;
}
.md-stepper-horizontal .md-step .md-step-optional {
	font-size:12px;
}
.md-stepper-horizontal .md-step.active .md-step-optional {
	color:rgba(0,0,0,.54);
}
.md-stepper-horizontal .md-step .md-step-bar-left,
.md-stepper-horizontal .md-step .md-step-bar-right {
	position:absolute;
	top:36px;
	height:1px;
	border-top:1px solid #DDDDDD;
}
.md-stepper-horizontal .md-step .md-step-bar-right {
	right:0;
	left:50%;
	margin-left:20px;
}
.md-stepper-horizontal .md-step .md-step-bar-left {
	left:0;
	right:50%;
	margin-right:20px;
}
 
    .photoviewer-modal {
      background-color: transparent;
      border: none;
      border-radius: 0;
      box-shadow: 0 0 6px 2px rgba(0, 0, 0, .3);

    pointer-events: none;

    }

    .photoviewer-header .photoviewer-toolbar {
      background-color: rgba(0, 0, 0, .5);
    }

    .photoviewer-stage {
      top: 0;
      right: 0;
      bottom: 0;
      left: 0;
      background-color: rgba(0, 0, 0, .85);
      border: none;
    }

    .photoviewer-footer .photoviewer-toolbar {
      background-color: rgba(0, 0, 0, .5);
      border-top-left-radius: 5px;
      border-top-right-radius: 5px;
    }

    .photoviewer-header,
    .photoviewer-footer {
      border-radius: 0;
      pointer-events: none;
    }

    .photoviewer-title {
      color: #ccc;
    }

    .photoviewer-button {
      color: #ccc;
      pointer-events: auto;
    }

    .photoviewer-header .photoviewer-button:hover,
    .photoviewer-footer .photoviewer-button:hover {
      color: white;
    }
    #content{
    width:40%;
    word-wrap: break-word;
    white-space:pre-wrap;
}
  </style>

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
            <div class="content-body" >
          <section id="multiple-column-form" >
          
     
     
    <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            <h2 class="content-header-title float-start mb-0"><spring:message code="menu.worksystem" /></h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${workflows.workflow_id}">${process.name}</a>
                                    </li>
                                       <li class="breadcrumb-item"><a href="/indexscope-workflow/${workflows.workflow_id}">Index  Scope</a>
                                    </li>
                                    <li class="breadcrumb-item active">Extension Setting
                                    </li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
           
            </div> 

     
     
<div class="row">


                        <div class="col-md-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><i data-feather='settings'></i>Extension</h4>
                                </div>
                                <div class="card-body">
                                               <form:form method="POST"  action="/saveextension2-workflow">
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Extension</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <input type="text" class="form-select"  name="name" value="${useextension.name }">  
                                                    </div>
                                                </div>
                                            </div>
                                      
                                          <br>   <br>
                                            
                                     <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">โลโก้</label>
                                                    </div>
                                                    <div class="col-sm-9">
               
                                     <select class="form-select" name="icon" id="icon">
    <option value="0">- เลือกไอคอน -</option>

    <option value="fa-comments" <c:if test="${useextension.icon == 'fa-comments'}">selected</c:if>>💬 การสื่อสาร (fa-comments)</option>
    <option value="fa-phone-volume" <c:if test="${useextension.icon == 'fa-phone-volume'}">selected</c:if>>📞 บันทึกโทร (fa-phone-volume)</option>
    <option value="fa-envelope" <c:if test="${useextension.icon == 'fa-envelope'}">selected</c:if>>✉️ อีเมลและจดหมาย (fa-envelope)</option>
    <option value="fa-headset" <c:if test="${useextension.icon == 'fa-headset'}">selected</c:if>>🎧 บริการช่วยเหลือ/Support (fa-headset)</option>

    <option value="fa-handshake" <c:if test="${useextension.icon == 'fa-handshake'}">selected</c:if>>🤝 การเจรจา/ปิดดีล (fa-handshake)</option>
    <option value="fa-funnel-dollar" <c:if test="${useextension.icon == 'fa-funnel-dollar'}">selected</c:if>>💰 โอกาสการขาย/Pipeline (fa-funnel-dollar)</option>
    <option value="fa-bullseye" <c:if test="${useextension.icon == 'fa-bullseye'}">selected</c:if>>🎯 เป้าหมาย/KPI (fa-bullseye)</option>
    <option value="fa-money-check-alt" <c:if test="${useextension.icon == 'fa-money-check-alt'}">selected</c:if>>💳 ใบเสนอราคา/Quotation (fa-money-check-alt)</option>

    <option value="fa-bullhorn" <c:if test="${useextension.icon == 'fa-bullhorn'}">selected</c:if>>📢 แคมเปญการตลาด (fa-bullhorn)</option>
    <option value="fa-star" <c:if test="${useextension.icon == 'fa-star'}">selected</c:if>>⭐ ลูกค้า VIP/ให้คะแนน (fa-star)</option>
    <option value="fa-tags" <c:if test="${useextension.icon == 'fa-tags'}">selected</c:if>>🏷️ ป้ายกำกับ/จัดกลุ่ม (fa-tags)</option>
    <option value="fa-gift" <c:if test="${useextension.icon == 'fa-gift'}">selected</c:if>>🎁 สิทธิพิเศษ/Rewards (fa-gift)</option>

    <option value="fa-sticky-note" <c:if test="${useextension.icon == 'fa-sticky-note'}">selected</c:if>>📝 บันทึกย่อ/Notes (fa-sticky-note)</option>
    <option value="fa-history" <c:if test="${useextension.icon == 'fa-history'}">selected</c:if>>🕒 ประวัติกิจกรรม/Log (fa-history)</option>
    <option value="fa-map-marked-alt" <c:if test="${useextension.icon == 'fa-map-marked-alt'}">selected</c:if>>🗺️ พื้นที่การขาย/ออกเยี่ยม (fa-map-marked-alt)</option>
</select>
								             </div>                           
               							   </div>
                                       </div>
                                      
                                      
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">User Access</label>
                                                    </div>
                                                    <div class="col-sm-9">
                        
                                      <select class="form-select select2" name="access_user"  id="access_user"   multiple="multiple"  >
								                                       
								                                       
								                                          <c:set var="list" value="${useextension.access_user }" />
		                  													<c:set var="listcheck" value="Creator" />  
		                  												
		        														   	
		        														   	
		        														   	
		        														   <c:set var="listcheck" value="ALL" />  
		        														   	<option value="ALL"
		        														   		<c:if test='${fn:contains(list,listcheck)}'>   
						                                        						selected
						                                        					</c:if>
		        														   	>ALL</option>
								                                       
								                                       
								                                       
								                                       
								                                       
								                                          <optgroup label = '<spring:message code="nav.department" />'>
								                                         	<c:forEach items="${departments}" var="department" varStatus="tabcounted"> 
								                                         	<c:set var="listcheck" value="department_${department.department_id}" />  
								                                        			<option value="department_${department.department_id}"
								                                        	      <c:if test='${fn:contains(list,listcheck)}'>selected</c:if>		
								                                        			>${department.department_name }</option>
								                                        	</c:forEach>
								                                       </optgroup> 	
								                                     
								                                        <optgroup label = '<spring:message code="nav.position" />'>
								                                         	<c:forEach items="${groupss}" var="groups" varStatus="tabcounted"> 
								                                         		<c:set var="listcheck" value="group_${groups.id}" />  
								                                        			<option value="group_${groups.id}"
								                                        				      <c:if test='${fn:contains(list,listcheck)}'>selected</c:if>		
								                                        			>${groups.group_name }</option>
								                                        	</c:forEach>
								                                       </optgroup> 	
								                                       
								                                        <optgroup label = '<spring:message code="nav.user" />'>	
								                                      	  	<c:forEach items="${userall}" var="userss" varStatus="tabcounted"> 
								                                      	  		<c:set var="listcheck" value="user_${userss.user_id}" />  
								                                        			<option value="user_${userss.user_id}"
								                                        				      <c:if test='${fn:contains(list,listcheck)}'>selected</c:if>		
								                                        			>${userss.user_name }</option>
								                                        	</c:forEach>
								                                    	</optgroup> 
						                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Admin Access</label>
                                                    </div>
                                                    <div class="col-sm-9">
               
                                        <select class="form-select select2" name="access_admin" id="access_admin"  multiple="multiple"  >
								                                       
								                                       
								                                          <c:set var="list" value="${useextension.access_admin }" />
		                  													<c:set var="listcheck" value="Creator" />  
		                  												 
		        														   	
		        														   	
		        														   <c:set var="listcheck" value="ALL" />  
		        														   	<option value="ALL"
		        														   		<c:if test='${fn:contains(list,listcheck)}'>   
						                                        						selected
						                                        					</c:if>
		        														   	>ALL</option>
								                                       
								                                       
								                                       
								                                       
								                                       
								                                          <optgroup label = '<spring:message code="nav.department" />'>
								                                         	<c:forEach items="${departments}" var="department" varStatus="tabcounted"> 
								                                         	<c:set var="listcheck" value="department_${department.department_id}" />  
								                                        			<option value="department_${department.department_id}"
								                                        	      <c:if test='${fn:contains(list,listcheck)}'>selected</c:if>		
								                                        			>${department.department_name }</option>
								                                        	</c:forEach>
								                                       </optgroup> 	
								                                     
								                                        <optgroup label = '<spring:message code="nav.position" />'>
								                                         	<c:forEach items="${groupss}" var="groups" varStatus="tabcounted"> 
								                                         		<c:set var="listcheck" value="group_${groups.id}" />  
								                                        			<option value="group_${groups.id}"
								                                        				      <c:if test='${fn:contains(list,listcheck)}'>selected</c:if>		
								                                        			>${groups.group_name }</option>
								                                        	</c:forEach>
								                                       </optgroup> 	
								                                       
								                                        <optgroup label = '<spring:message code="nav.user" />'>	
								                                      	  	<c:forEach items="${userall}" var="userss" varStatus="tabcounted"> 
								                                      	  		<c:set var="listcheck" value="user_${userss.user_id}" />  
								                                        			<option value="user_${userss.user_id}"
								                                        				      <c:if test='${fn:contains(list,listcheck)}'>selected</c:if>		
								                                        			>${userss.user_name }</option>
								                                        	</c:forEach>
								                                    	</optgroup> 
						                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                
                                
                                
                                <hr>
                               
                                            
                                      
                           		<c:if test='${extension.component_req == "eform"}'>            
                      
                                  
                              	        <div class="card-header">
                                    <h4 class="card-title"><i data-feather='database'></i>Needed for processing</h4>
                                </div>       
                                           
                                       
                                  <br>   <br>
                                            
                                     <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">เชื่อมเข้ากับ E-Form</label>
                                                    </div>
                                                    <div class="col-sm-9">
               
                                        <select class="form-select select2" name="eform_id" id="eform_id"  >
                                          <option value="0" >-</option>
                                       <c:forEach items="${conditions}" var="row" varStatus="tabcounted"> 
                                        <option value="${row.condition_id }" 	<c:if test='${useextension.eform_id == row.condition_id}'>selected</c:if>>
                                        ${row.condition_title }</option>
                                       </c:forEach>
                                       
                                         </select>
								             </div>                           
               							   </div>
                                       </div>
                                                <br>
                                     	</c:if>   
                               
                               
                               <c:if test='${extension.component_req == "process"}'>            
                             
                                  	        <div class="card-header">
                                    <h4 class="card-title"><i data-feather='database'></i>Needed for processing</h4>
                                </div>       
                                       
                                       
                                  <br>          <br>  
                                     <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">เชื่อมเข้ากับโปรเเกรม</label>
                                                    </div>
                                                    <div class="col-sm-9">
               
                                        <select class="form-select select2" name="use_process_id" id="use_process_id"  >
                                          <option value="0" >-</option>
                                       <c:forEach items="${processall}" var="row" varStatus="tabcounted"> 
                                        <option value="${row.id }" 	<c:if test='${useextension.use_process_id == row.id}'>selected</c:if>>
                                        ${row.name }</option>
                                       </c:forEach>
                                       
                                         </select>
								             </div>                           
               							   </div>
                                       </div>
                                       <br>
                                     	</c:if> 
                                     	 
                               <hr>
                               
                              <div class="card-header">
                                    <h4 class="card-title"><i data-feather='database'></i>Auto Update Latest Data</h4>
                                </div>
                                <div class="card-body">
                              
                                        <div class="row">
                                          <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       select Column
                                                    </div>
                                                    <div class="col-sm-9">  
								              
								 <select class="form-select select2" name="auto_update_parameter_column" id="auto_update_parameter_column" multiple="multiple">
								    <c:forEach items="${eformfields}" var="row">
								        <option value="${row.field_postion}" 
								            <c:if test="${fn:contains(useextension.auto_update_parameter_column, row.field_postion)}">selected</c:if>>
								            ${row.field_label}
								        </option>
								    </c:forEach>
								</select>
								</div>
								</div>
									</div>
								                                          
                        			  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       tocolumn
                                                    </div>
                                                    <div class="col-sm-9">  

                                            <select class="form-select" name="auto_update_tocolumn"  id="auto_update_tocolumn"   >
                                         <option value="0"  <c:if test="${useextension.auto_update_tocolumn == 0}">selected</c:if>>-</option>     
                                      <option value="1"  <c:if test="${useextension.auto_update_tocolumn == 1}">selected</c:if>>Extension Column 1</option>
                                   	  <option value="2" <c:if test="${useextension.auto_update_tocolumn == 2}">selected</c:if>>Extension Column 2</option>
                                   	  <option value="3" <c:if test="${useextension.auto_update_tocolumn == 3}">selected</c:if>>Extension Column 3</option>
                                   	   <option value="4" <c:if test="${useextension.auto_update_tocolumn == 4}">selected</c:if>>Extension Column 4</option>
                                   	   <option value="5" <c:if test="${useextension.auto_update_tocolumn == 5}">selected</c:if>>Extension Column 5</option>
                                   	   <option value="6" <c:if test="${useextension.auto_update_tocolumn == 6}">selected</c:if>>Extension Column 6</option>
                                   	  <option value="7" <c:if test="${useextension.auto_update_tocolumn == 7}">selected</c:if>>Extension Column 7</option>
                                   	     <option value="8" <c:if test="${useextension.auto_update_tocolumn == 8}">selected</c:if>>Extension Column 8</option>
                                   	    <option value="9" <c:if test="${useextension.auto_update_tocolumn == 9}">selected</c:if>>Extension Column 9</option>
                                 	    <option value="10" <c:if test="${useextension.auto_update_tocolumn == 10}">selected</c:if>>Extension Column 10</option>
                                 	    
                                     <option value="11"  <c:if test="${useextension.auto_update_tocolumn == 11}">selected</c:if>>Extension Column 11</option>
                                 	  <option value="12"  <c:if test="${useextension.auto_update_tocolumn == 12}">selected</c:if>>Extension Column 12</option>
                                 	 <option value="13"  <c:if test="${useextension.auto_update_tocolumn == 13}">selected</c:if>>Extension Column 13</option>
                                 	 <option value="14"  <c:if test="${useextension.auto_update_tocolumn == 14}">selected</c:if>>Extension Column 14</option>
                                 	  <option value="15"  <c:if test="${useextension.auto_update_tocolumn == 15}">selected</c:if>>Extension Column 15</option>
                                 	   <option value="16"  <c:if test="${useextension.auto_update_tocolumn == 16}">selected</c:if>>Extension Column 16</option>
                                 	   <option value="17"  <c:if test="${useextension.auto_update_tocolumn == 17}">selected</c:if>>Extension Column 17</option>
                                 	    <option value="18"  <c:if test="${useextension.auto_update_tocolumn == 18}">selected</c:if>>Extension Column 18</option>
                                 	     <option value="19"  <c:if test="${useextension.auto_update_tocolumn == 19}">selected</c:if>>Extension Column 19</option>
                                 	   <option value="20"  <c:if test="${useextension.auto_update_tocolumn == 20}">selected</c:if>>Extension Column 20</option>
                                      </select>
                                                    
                                  
                                                    </div>
                                           </div>   
                                              </div>    
                                               
                                               
                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       Auto Update segment 
                                                    </div>
                        <div class="col-sm-9">        
								 <select class="form-select" name="auto_update_segment" id="auto_update_segment" >	 		
								        <option value="0" <c:if test="${useextension.auto_update_segment == 0}">selected</c:if>>No</option>
								        <option value="1" <c:if test="${useextension.auto_update_segment == 1}">selected</c:if>>Yes</option>
								</select>
						</div>
								</div>
									</div>
                                  
                                  
                                                               
                                               
                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       Segment Count
                                                    </div>
                        <div class="col-sm-9">        
								 <select class="form-select" name="segment_count" id="segment_count" >	 		
								        <option value="0" <c:if test="${useextension.segment_count == 0}">selected</c:if>>No</option>
								        <option value="1" <c:if test="${useextension.segment_count == 1}">selected</c:if>>Yes</option>
								</select>
						</div>
								</div>
									</div>
                                               
                                  
                                                 <div class="card-header">
                                    <h4 class="card-title"><i data-feather='database'></i>Segment Error</h4>
                                </div>
                                <div class="card-body">
                              
                                        <div class="row">
                                          <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                     Segment issue
                                                    </div>
                                                    <div class="col-sm-9">  
								              
								 <select class="form-select select2" name="segment_issue_count" id="segment_issue_count" multiple="multiple">
							    <option value="0" 
								            <c:if test="${useextension.segment_issue_count == 0}">selected</c:if>>
								           NO
								        </option>
								          <option value="1" 
								            <c:if test="${useextension.segment_issue_count == 1}">selected</c:if>>
								           YES
								        </option>
								</select>
								</div>
								</div>
									</div>
								                                          
                        			  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       issue column Type
                                                    </div>
                                                    <div class="col-sm-9">  
								 <select class="form-select select2" name="segment_issue_column" id="segment_issue_column"  >
                                          <option value="0" >-</option>
                                          <c:forEach items="${eformfields}" var="row">
								        <option value="${row.field_postion}" 
								            <c:if test="${fn:contains(useextension.segment_issue_column, row.field_postion)}">selected</c:if>>
								            ${row.field_label}
								        </option>
								    </c:forEach>
                                       
                                         </select>
                                                    
                                  
                                                    </div>
                                           </div>   
                                              </div>    
                                               
                                   
                                  
                                                               
                                               
                                    
                       				   <hr>
                       				 
                       				 
                       				 
                       				       
                        
                                            <br><br>
                                            
                                                
                                     	   
                                            <div class="col-sm-9 offset-sm-3">
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden"   name="extension_id" value="${useextension.extension_id }"> 
                                         <input type="hidden"   name="workflow_id" value="${workflows.workflow_id }"> 
                                       <input type="hidden"  name="process_id" value="${useextension.process_id }"> 
                                    <input type="hidden" class="form-select"  name="id" value="${useextension.id }"> 
                          
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"><spring:message code="btn.save" /></button>
                                          
                                            </div>
                      
                                        </div>
                                        
                                        
                        
                                  
                                     
                         
                                            </div>
                                   
                                    </div>   
                                          
                                    </form:form>
                                </div>
                            </div>
                            
                
                       
                              <form:form method="POST" modelAttribute="user" action="/saveuseextension-workflow">
                              
                              
                              <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><i data-feather='database'></i> Extension Setup[<spring:message code="${ extension.extension_name}" text='-' />]</h4>
                                </div>
                                <div class="card-body">
                              
                                        <div class="row">
                                        
                                        
                              <c:set var="parameter_name" value="${fn:split(extension.extension_parameter_name, ',')}"/>  
                                <c:set var="parameter_type" value="${fn:split(extension.extension_parameter_type, ',')}"/>  
                              
                              
                         
                         <c:forEach var="item" items="${fn:split(extension.extension_parameter, ',')}" varStatus="count">
                        			  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       Parameter 
                                                    </div>
                                                    <div class="col-sm-9">  
                                                      <input type="text"  class="form-control"  value="<spring:message code="${parameter_name[count.count-1] }" text='-' />" readonly>
                                                    
                                                    <input type="hidden" name="parameter_keyname"  class="form-control"  value="${item }">
                                                    </div>
                                              
                                                 </div>   
                       				   </div> 
                        			  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       eform_column
                                                    </div>
                                                    <div class="col-sm-9">  
                                                    
                                               <select class="form-select " name="parameter_column" id="parameter_column"  >
                                         <option value="0" >-</option>
      
                                       <c:forEach items="${eformfields}" var="row" varStatus="tabcounted"> 
                                        <c:set var="selected" value="" />    
                                         <c:if test='${ ExtensionParameter.size() >= count.count}'>
                                          <c:if test='${ ExtensionParameter.get(count.count-1).parameter_column == row.field_id}'> 
                                           <c:set var="selected" value="selected" />   
                                           </c:if>
                                         </c:if>
                                       
                                        <option value="${row.field_id }" ${selected}>
                                        ${row.field_label }</option>
                                       </c:forEach>
                                       
                                         </select>
                                       <input type="hidden"  name="extension_process" value="${useextension.process_id }"> 
                                                    </div>
                                           </div>   
                                              </div>    
                                        <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                       parameter_type
                                                    </div>
                                                    <div class="col-sm-9"> 
                                                    <c:set var="list" value="string,file,date,auto_date" />                    
                                         
                                    				    <c:if test='${fn:contains(list,parameter_type[count.count-1])}'>
                                                    
                                                     <input type="text" name="parameter_type"  value="${parameter_type[count.count-1] }" class="form-control" readonly>
                                                    <input type="hidden" name="parameter_columnismain"  class="form-control" value="0">
                                                    </c:if>
                                                       <c:if test='${parameter_type[count.count-1] == "enum"}'>
                                                       <input type="hidden" name="parameter_type"  class="form-control" value="${parameter_type[count.count-1] }"  >
                                           
                                                        
                                                             <select class="form-select " name="parameter_columnismain" id="parameter_columnismain"  >
                                         <option value="0" >-</option>
                                      
                                       <c:forEach items="${datasets}" var="row" varStatus="tabcounted"> 
                                       
                                         <c:set var="selected" value="" />    
                                         <c:if test='${ ExtensionParameter.size() >= count.count}'>
                                          <c:if test='${ ExtensionParameter.get(count.count-1).parameter_columnismain == row.dataset_id}'> 
                                           <c:set var="selected" value="selected" />   
                                           </c:if>
                                         </c:if>
                                       
                                       
                                       
                                        <option value="${row.dataset_id }" 	${selected}>
                                        ${row.dataset_name }</option>
                                       </c:forEach>
                                       
                                         </select>
                                                    
                                                        
                                                        
                                                        </c:if>
                                                    </div>
                                           </div>       
                                            </div>                   
                       				   <hr>
                       				       
                       			</c:forEach>	   
                                            <br><br>
                                            
                                                
                                     
                                            <div class="col-sm-12 offset-sm-3">
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }"> 
               
                                      <input type="hidden" class="form-select"  name="extension_id" value="${useextension.id}"> 
                                       <input type="hidden"  name="process_id" value="${useextension.process_id }"> 
                          
                                  <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"><spring:message code="btn.save" /></button>
                                          
                                            </div>
                          
                                            </div>
                                   
                                    </div>
                                     </div>
             
                                     </form:form>
                            
                           
                            
                            
                            
                            
                   
                            
                                             </div>
                        </div>
                   </div>
          
          

                  
          
                 
                 
            
            
     
                </section>

            </div>
        </div>
    </div>
 
 
  
 
 

 
 
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



 <div style="clear:both"></div>
    
    
    
<%@ include file="../common/mainfoot.jspf" %>  


	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>

 
    <script src="<c:url value="/resources/app-assets/js/scripts/tables/table-datatables-advanced.js" />"></script>
 
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/legacy.js" />"></script>
  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.date.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>



 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>

 <%@ include file="../other/alert.jsp" %>

  <c:if test="${Sys_Save == 'Save_Sucess'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
    
    </script>
</c:if>
  <c:if test="${Sys_Save == 'Sys_Failed'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('error','Save','Failed');
      });
    
    </script>
</c:if>
</body>



  
	    <script>
	      $(window).on('load', function() {
	            if (feather) {
	                feather.replace({
	                    width: 14,
	                    height: 14
	                });
	            }
	        })
	        
    var element = document.getElementById("nav_workflowsystem");
    element.classList.add("active"); 
    
    
    
    
    
    
    
    
    
    function postdatacal(Mode,Mode2,Mode3) {

	    var formData = new FormData($("#"+ Mode2)[0]);

			    $.ajax({
			        url: Mode,
			        type: "POST",
			        data: formData,
			        processData: false,
			        contentType: false,
				    success: function(result){
				    $('.' + Mode3).html(result);
				       },
				    error: function(jqXHR, textStatus, errorThrown) {
				            if (jqXHR.status === 401) {
				                // Redirect to login page if unauthorized
				                window.location.href = '/login'; // URL ของหน้า login
				     }}
				    });
			

		
			   
	    return false;
	    }	
    
    
    
	   function getdata(url,id){
	        $.ajax({
	        type:'GET',
	        url:url,
	        data: {ID : id},
	        
	     success: function(data){
	    $("#mainkey").html(data);	

	     }
	});





return false;

}
    </script>				

 <script>
    
 
    $('.trigger').click(function(e){
      e.preventDefault();
      
      var childUl = $(this).siblings("ul.tree-parent");
      
      if( childUl.hasClass('open') ){
        
        childUl.removeClass('open');
      } else {
        
        childUl.addClass('open');
      }
      
    });
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
        document.getElementById("file").onchange = function() {
    document.getElementById("form").submit();
};


    </script>
