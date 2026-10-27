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
<title>Setting Management App</title>


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
                <div class="content-header-left col-md-12 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-10">
                            <h2 class="content-header-title float-start mb-0"><spring:message code="menu.worksystem" /></h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${workflow_id}">${process.name}</a>
                                    </li>
                                    
                                       <li class="breadcrumb-item"><a href="/documentbox-workflow/${workflow_id}/${management_id}/${process_id}">${management.management_title }</a>
                                    </li>
                                    <li class="breadcrumb-item active">Edit Document Box
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
                                    <h4 class="card-title"><i data-feather='settings'></i>Edit Document Box</h4>
                                </div>
                                <div class="card-body">
                              
                        
                              
                            <form:form method="POST"  action="/savedocumentbox-workflow">
          
                            
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">tab_name</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <input type="text" class="form-select"  name="tab_name" value="${Tab.tab_name }">  
                                                    </div>
                                                </div>
                                            </div>
                                           <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">tab_count</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="tab_count"  id="tab_count"   >
                                      <option value="1" <c:if test="${Tab.tab_count == 1}">selected</c:if>>เเสดงจำนวนเอกสาร</option>
                                      <option value="0" <c:if test="${Tab.tab_count == 0}">selected</c:if>>ไม่เเสดงจำนวนเอกสาร</option>
                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">tab_type_view</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="tab_type_view"  id="tab_type_view"   >
	                                      <option value="0" <c:if test="${Tab.tab_type_view == 0}">selected</c:if>>เห็นทั้งหมด</option>
	                                      <option value="1" <c:if test="${Tab.tab_type_view == 1}">selected</c:if>>เห็นเฉพาะฉัน</option>
	                                      <option value="2" <c:if test="${Tab.tab_type_view == 2}">selected</c:if>>ที่เกี่ยวข้องกับฉัน </option>
                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                                                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">tab_columns</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                           <select class="form-select" name="tab_columns" id="tab_columns" multiple="multiple">
    <%-- เตรียม Array ของค่าที่เลือกไว้จาก Database --%>
    <c:set var="savedValues" value="${fn:split(Tab.tab_columns, ',')}" />

    <%-- รอบที่ 1: วนลูปตามลำดับที่บันทึกไว้ใน DB (เพื่อให้ Selected แสดงขึ้นมาก่อนตามลำดับ) --%>
    <c:forEach items="${savedValues}" var="val">
        <c:forEach items="${eformfields}" var="row">
            <c:set var="thisValue" value="${row.field_cal_type}${row.field_id}" />
            <c:if test="${thisValue == val}">
                <option value="${thisValue}" selected>${row.field_label}</option>
            </c:if>
        </c:forEach>
    </c:forEach>

    <%-- รอบที่ 2: วนลูปเอาตัวที่เหลือที่ "ยังไม่ได้เลือก" มาโชว์ต่อท้าย --%>
    <c:forEach items="${eformfields}" var="row">
        <c:set var="thisValue" value="${row.field_cal_type}${row.field_id}" />
        <c:if test="${!fn:contains(Tab.tab_columns, thisValue)}">
            <option value="${thisValue}">${row.field_label}</option>
        </c:if>
    </c:forEach>
</select>

                                                    </div>
                                                </div>
                                            </div>
                                
                                
                                
                                
                                
                                        
                                                 <div class="col-12">
    <div class="mb-1 row">
        <div class="col-sm-3">
            <label class="col-form-label" for="tab_current_step">tab_current_step</label>
        </div>
        <div class="col-sm-9">
            <select class="form-select select2" name="tab_current_step" id="tab_current_step" multiple="multiple">
                <option value="Create" <c:if test="${fn:contains(Tab.tab_current_step, 'Create')}">selected</c:if>>Create</option>
                <option value="Review" <c:if test="${fn:contains(Tab.tab_current_step, 'Review')}">selected</c:if>>Review</option>
                <option value="Approval" <c:if test="${fn:contains(Tab.tab_current_step, 'Approval')}">selected</c:if>>Approval</option>
                <option value="Proceed" <c:if test="${fn:contains(Tab.tab_current_step, 'Proceed')}">selected</c:if>>Proceed</option>
                <option value="Completion" <c:if test="${fn:contains(Tab.tab_current_step, 'Completion')}">selected</c:if>>Completion</option>
            </select>
        </div>
    </div>
</div>

<div class="col-12">
    <div class="mb-1 row">
        <div class="col-sm-3">
            <label class="col-form-label" for="tab_closing_status">tab_closing_status *(Completion Only)</label>
        </div>
        <div class="col-sm-9">
            <select class="form-select select2" name="tab_closing_status" id="tab_closing_status" multiple="multiple">
                <option value="-">-</option>
                <c:forEach items="${dataset_data}" var="row" varStatus="tabcounted"> 
                    <option value="${row.dataset_data_title}" <c:if test="${fn:contains(Tab.tab_closing_status, row.dataset_data_title)}">selected</c:if>>
                        ${row.dataset_data_title}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
                                          
                                         
                  
                                
                                
 
  								  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Acknowledge</label>
                                                    </div>
                                                    <div class="col-sm-9">
						                                 <%-- เอาลูกน้ำมาประกบหน้าหลัง เพื่อแก้บั๊กเรื่อง -1 กับ 1 --%>
				
					     <select class="form-select select2" name="acknowledge"  id="acknowledge"   multiple="multiple">
                                              <option value="4" <c:if test="${Tab.acknowledge == 4}">selected</c:if>>-</option>
                                  <option value="0" <c:if test="${Tab.acknowledge == 0}">selected</c:if>>ยังไม่รับ</option>
                                      <option value="1" <c:if test="${Tab.acknowledge == 1}">selected</c:if>>รับเเล้ว</option>
                                          <option value="-1" <c:if test="${Tab.acknowledge == -1}">selected</c:if>>ไม่รับ</option>
                                          <option value="2" <c:if test="${Tab.acknowledge == 2}">selected</c:if>>รับเเล้ว,ไม่รับ</option>
     									<option value="3" <c:if test="${Tab.acknowledge == 3}">selected</c:if>>ยังไม่รับ,ไม่รับ</option>
                                      </select>


                                                    </div>
                                                </div>
                                            </div>
 
                                	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">acknowledge_user</label>
                                                    </div>
                                                    <div class="col-sm-9">
						                                 <%-- เอาลูกน้ำมาประกบหน้าหลัง เพื่อแก้บั๊กเรื่อง -1 กับ 1 --%>
				
					     <select class="form-select select2" name="acknowledge_user"  id="acknowledge_user"   multiple="multiple">
                                              <option value="0" <c:if test="${Tab.acknowledge_user == 0}">selected</c:if>>-</option>
  												<option value="1" <c:if test="${Tab.acknowledge_user == 1}">selected</c:if>>ฉัน</option>
											<option value="2" <c:if test="${Tab.acknowledge_user == 2}">selected</c:if>>ไม่ใช่ฉัน</option>
                                      </select>


                                                    </div>
                                                </div>
                                            </div>          
        
        
                                	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Followup</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="followup"  id="followup"   >
                                    <option value="-1" <c:if test="${Tab.followup == -1}">selected</c:if>>-</option>
                                      <option value="1" <c:if test="${Tab.followup == 1}">selected</c:if>>ตึงไว้</option>
                                          <option value="0" <c:if test="${Tab.followup == 0}">selected</c:if>>ไม่ได้ตึง</option>
                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">การหมดอายุ</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="expired"  id="expired"   >
                                    <option value="-1" <c:if test="${Tab.expired == -1}">selected</c:if>>-</option>
                                      <option value="1" <c:if test="${Tab.expired == 1}">selected</c:if>>หมดอายุ</option>
                                          <option value="0" <c:if test="${Tab.expired == 0}">selected</c:if>>ยังไม่หมด</option>
                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            
                                            
                                            
                                	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">กำหนดเเผนติดตามไว้</label>
                                                    </div>
                                                    <div class="col-sm-9">
                             <select class="form-select" name="followup_plan"  id="followup_plan">
                                    <option value="0" <c:if test="${Tab.followup_plan == 0}">selected</c:if>>-</option>
                                    <option value="1" <c:if test="${Tab.followup_plan == 1}">selected</c:if>>ยังไม่ได้กำหนดเเผน</option>
                                    <option value="2" <c:if test="${Tab.followup_plan == 2}">selected</c:if>>กำหนดเเผนเเล้ว [ยังไม่ถึงกำหนด]</option>
                                    <option value="3" <c:if test="${Tab.followup_plan == 3}">selected</c:if>>ถึงกำหนดเเล้ว</option>
                                    <option value="4" <c:if test="${Tab.followup_plan == 4}">selected</c:if>>ติดตามเเล้ว</option>
                            </select>
                                                    </div>
                                                </div>
                                            </div>
                                
        
        	  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">การขอเเก้ไข</label>
                                                    </div>
                                                    <div class="col-sm-9">
                             <select class="form-select" name="request_to_edit"  id="request_to_edit">
                                    <option value="0" <c:if test="${Tab.request_to_edit == 0}">selected</c:if>>ไม่</option>
                                    <option value="1" <c:if test="${Tab.request_to_edit == 1}">selected</c:if>>ใช่</option>

                            </select>
                                                    </div>
                                                </div>
                                            </div>
                                
        
        
        
        
        
        
        <hr>
                     
                                        
                                                 <div class="col-12">
    <div class="mb-1 row">
        <div class="col-sm-3">
            <label class="col-form-label" for="tab_current_step">tab_current_step2</label>
        </div>
        <div class="col-sm-9">
            <select class="form-select select2" name="tab_current_step2" id="tab_current_step2" multiple="multiple">
                <option value="Create" <c:if test="${fn:contains(Tab.tab_current_step2, 'Create')}">selected</c:if>>Create</option>
                <option value="Review" <c:if test="${fn:contains(Tab.tab_current_step2, 'Review')}">selected</c:if>>Review</option>
                <option value="Approval" <c:if test="${fn:contains(Tab.tab_current_step2, 'Approval')}">selected</c:if>>Approval</option>
                <option value="Proceed" <c:if test="${fn:contains(Tab.tab_current_step2, 'Proceed')}">selected</c:if>>Proceed</option>
                <option value="Completion" <c:if test="${fn:contains(Tab.tab_current_step2, 'Completion')}">selected</c:if>>Completion</option>
            </select>
        </div>
    </div>
</div>

<div class="col-12">
    <div class="mb-1 row">
        <div class="col-sm-3">
            <label class="col-form-label" for="tab_closing_status">tab_closing_status2 *(Completion Only)</label>
        </div>
        <div class="col-sm-9">
            <select class="form-select select2" name="tab_closing_status2" id="tab_closing_status2" multiple="multiple">
                <option value="-">-</option>
                <c:forEach items="${dataset_data}" var="row" varStatus="tabcounted"> 
                    <option value="${row.dataset_data_title}" <c:if test="${fn:contains(Tab.tab_closing_status2, row.dataset_data_title)}">selected</c:if>>
                        ${row.dataset_data_title}
                    </option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
                                          
                                         
                  
                                
                                
 
  								  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">Acknowledge2</label>
                                                    </div>
                                                    <div class="col-sm-9">
						                                 <%-- เอาลูกน้ำมาประกบหน้าหลัง เพื่อแก้บั๊กเรื่อง -1 กับ 1 --%>
				
					     <select class="form-select select2" name="acknowledge2"  id="acknowledge2"   multiple="multiple">
                                              <option value="4" <c:if test="${Tab.acknowledge2 == 4}">selected</c:if>>-</option>
                                  <option value="0" <c:if test="${Tab.acknowledge2 == 0}">selected</c:if>>ยังไม่รับ</option>
                                      <option value="1" <c:if test="${Tab.acknowledge2 == 1}">selected</c:if>>รับเเล้ว</option>
                                          <option value="-1" <c:if test="${Tab.acknowledge2 == -1}">selected</c:if>>ไม่รับ</option>
                                          <option value="2" <c:if test="${Tab.acknowledge2 == 2}">selected</c:if>>รับเเล้ว,ไม่รับ</option>
     									<option value="3" <c:if test="${Tab.acknowledge2 == 3}">selected</c:if>>ยังไม่รับ,ไม่รับ</option>
                                      </select>


                                                    </div>
                                                </div>
                                            </div>
 
                           
                      
  								  <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">acknowledge_user</label>
                                                    </div>
                                                    <div class="col-sm-9">
						                                 <%-- เอาลูกน้ำมาประกบหน้าหลัง เพื่อแก้บั๊กเรื่อง -1 กับ 1 --%>
				
					     <select class="form-select select2" name="acknowledge_user2"  id="acknowledge_user2"   multiple="multiple">
                                              <option value="0" <c:if test="${Tab.acknowledge_user2 == 0}">selected</c:if>>-</option>
  												<option value="1" <c:if test="${Tab.acknowledge_user2 == 1}">selected</c:if>>ฉัน</option>
											<option value="2" <c:if test="${Tab.acknowledge_user2 == 2}">selected</c:if>>ไม่ใช่ฉัน</option>
                                      </select>


                                                    </div>
                                                </div>
                                            </div>          
                         <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">การขอเเก้ไข2</label>
                                                    </div>
                                                    <div class="col-sm-9">
                             <select class="form-select" name="request_to_edit2"  id="request_to_edit2">
                                    <option value="0" <c:if test="${Tab.request_to_edit2 == 0}">selected</c:if>>ไม่</option>
                                    <option value="1" <c:if test="${Tab.request_to_edit2 == 1}">selected</c:if>>ใช่</option>

                            </select>
                                                    </div>
                                                </div>
        
        
        
        
        
        
                                
                                <hr>
                                
                  <div class="card-header">
                                    <h4 class="card-title">Shortcut to profile</h4>
                                </div>                              
                                
                                                                             <div class="col-12">
                                                                             
                                                                             
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Shortcut to open profile [Link name]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                   <input type="text" class="form-select"  name="shortcut_profile_linkname" value="${Tab.shortcut_profile_linkname }">  
                                                    </div>
                                                </div>
                                            </div>                        
                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Shortcut to open profile [Code]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                    <select class="form-select" name="shortcut_profile_code" id="shortcut_profile_code" >
		                                    
		                                         <option value="-" >-</option>
		                                    
												    <c:forEach items="${eformfields}" var="row" varStatus="tabcounted">
     														<c:set var="thisValue" value="${row.field_cal_type}${row.field_id}" />
												        <option value="${thisValue}" 
												        <c:if test="${Tab.shortcut_profile_code == thisValue}">selected</c:if> >
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
                                                        <label class="col-form-label" for="first-name">
Shortcut to open profile [App]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                    <select class="form-select" name="shortcut_profile_app" id="shortcut_profile_app" >
		                                             <option value="0" >-</option>
										
												    <c:forEach items="${processIndex}" var="row" varStatus="tabcounted">
												        <%-- 2. สร้าง option และใช้ fn:contains เช็ค --%>
												        <option value="${row.id}" <c:if test="${Tab.shortcut_profile_app == row.id}">selected</c:if> >
												            ${row.name}
												        </option>
												
												    </c:forEach>
											</select>
                                                    </div>
                                                </div>
                                            </div>
                                <hr>
                               
                <div class="card-header">
                                    <h4 class="card-title">Shortcut new doc</h4>
                                </div>     
                                
              
                                               

                                                                             <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
new doc  [Link name]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                   <input type="text" class="form-select"  name="shortcut_newdoc_linkname" value="${Tab.shortcut_newdoc_linkname }">  
                                                    </div>
                                                </div>
                                            </div>                        
                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
new doc profile [Code]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                    <select class="form-select" name="shortcut_newdoc_code" id="shortcut_newdoc_code" >
		                                    
		                                         <option value="-" >-</option>
		                                    
												    <c:forEach items="${eformfields}" var="row" varStatus="tabcounted">
     														<c:set var="thisValue" value="${row.field_cal_type}${row.field_id}" />
												        <option value="${thisValue}" 
												        <c:if test="${Tab.shortcut_newdoc_code == thisValue}">selected</c:if> >
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
                                                        <label class="col-form-label" for="first-name">
 new doc  [Change closing status] *(Completion Only)</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="shortcut_newdoc_change_closing_status"  id="shortcut_newdoc_change_closing_status"   >
                                      
                                       <option value="-">-</option>
                                      
	                                   <c:forEach items="${dataset_data}" var="row" varStatus="tabcounted"> 
						                    <option value="${row.dataset_data_title}"  <c:if test="${Tab.shortcut_newdoc_change_closing_status == row.dataset_data_title}">selected</c:if> 	>${row.dataset_data_title}</option>
						                </c:forEach>
                                      </select>
                                                    </div>
                                                </div>
                                            </div>

                                <hr>
                                                     
   <div class="card-header">
<h4 class="card-title">Shortcut Change closing status</h4>
</div>      
       
       
                                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Shortcut Change closing status  [Link name]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                   <input type="text" class="form-select"  name="shortcut_change_closing_linkname" value="${Tab.shortcut_change_closing_linkname }">  
                                                    </div>
                                                </div>
                                            </div>                                      	   
                         
     
                                        <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Shortcut Change closing status *(Completion Only)</label>
                                                    </div>
                                                    <div class="col-sm-9">
                       
                                        <select class="form-select select2" name="shortcut_change_closing_status" id="shortcut_change_closing_status" multiple="multiple">
												    <c:forEach items="${dataset_data}" var="row" varStatus="tabcounted">
												        
												        <%-- 1. สร้างตัวแปรเก็บค่าที่จะใช้เทียบ เพื่อความชัวร์ --%>
												        <c:set var="thisValue" value="${row.dataset_data_title}" />
												
												        <%-- 2. สร้าง option และใช้ fn:contains เช็ค --%>
												        <option value="${thisValue}" 
												            <c:if test="${fn:contains(Tab.shortcut_change_closing_status, thisValue)}">selected</c:if> 
												        >
												            ${row.dataset_data_title}
												        </option>
												
												    </c:forEach>
											</select>
                                      
                                                    </div>
                                                </div>
                                            </div>
                       <hr>                                     
                                            
                      <div class="card-header">
<h4 class="card-title">Shortcut Segment</h4>
</div>      
       
       
                                                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Shortcut Segment  [Link name]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                   <input type="text" class="form-select"  name="shortcut_segment_linkname" value="${Tab.shortcut_segment_linkname }">  
                                                    </div>
                                                </div>
                                            </div>                                      	   
                         
     
                                        <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Shortcut Segment [Segment Code]</label>
                                                    </div>
                                                    <div class="col-sm-9">
                       
                                     <select class="form-select" name="shortcut_segment_code" id="shortcut_segment_code" >
		                                    
		                                         <option value="-" >-</option>
		                                    
												    <c:forEach items="${eformfields}" var="row" varStatus="tabcounted">
     														<c:set var="thisValue" value="${row.field_cal_type}${row.field_id}" />
												        <option value="${thisValue}" 
												        <c:if test="${Tab.shortcut_segment_code == thisValue}">selected</c:if> >
												            ${row.field_label}
												        </option>

												    </c:forEach>
											</select>
                                      
                                                    </div>
                                                </div>
                                                   
                                                             
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Shortcut Segment Column </label>
                                                    </div>
                                                    <div class="col-sm-9">
                       
                                              <select class="form-select select2" name="shortcut_segment_column" id="shortcut_segment_column" multiple="multiple">
												 
<option value="register_name" <c:if test="${fn:contains(Tab.shortcut_segment_column,'register_name')}">selected</c:if> ><spring:message code="register.name" text="-"/></option>
<option value="register_type" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'register_type')}">selected</c:if> ><spring:message code="register.type" text="-"/></option>												 
<option value="register_groups" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'register_groups')}">selected</c:if> ><spring:message code="register.groups" text="-"/></option>			
<option value="register_address" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'register_address')}">selected</c:if> ><spring:message code="register.address" text="-"/></option>		
<option value="register_note" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'register_note')}">selected</c:if> ><spring:message code="register.note" text="-"/></option>				
<option value="contact_person" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'contact_person')}">selected</c:if> ><spring:message code="register.contact_person" text="-"/></option>			
<option value="phone_number" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'phone_number')}">selected</c:if> ><spring:message code="register.phone_number" text="-"/></option>				
<option value="mobile_number" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'mobile_number')}">selected</c:if> ><spring:message code="register.mobile_number" text="-"/></option>			
<option value="phone_number" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'phone_number')}">selected</c:if> ><spring:message code="register.phone_number" text="-"/></option>					
<option value="website" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'website')}">selected</c:if> ><spring:message code="register.website" text="-"/></option>					
<option value="social" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'social')}">selected</c:if> ><spring:message code="register.social" text="-"/></option>
<option value="tax_id" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'tax_id')}">selected</c:if> ><spring:message code="register.tax_id" text="-"/></option>
<option value="branch_code" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'branch_code')}">selected</c:if> ><spring:message code="register.branch_code" text="-"/></option>	
<option value="business_type" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'business_type')}">selected</c:if> ><spring:message code="register.business_type" text="-"/></option>	
<option value="payment_terms" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'payment_terms')}">selected</c:if> ><spring:message code="register.payment_terms" text="-"/></option>	
<option value="credit_limit" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'credit_limit')}">selected</c:if> ><spring:message code="register.credit_limit" text="-"/></option>	
<option value="bank_account" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'bank_account')}">selected</c:if> ><spring:message code="register.bank_account" text="-"/></option>
<option value="preferred_currency" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'preferred_currency')}">selected</c:if> ><spring:message code="register.preferred_currency" text="-"/></option>


<option value="extension_column_1" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_1')}">selected</c:if> >extension_column_1</option>
<option value="extension_column_2" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_2')}">selected</c:if> >extension_column_2</option>
<option value="extension_column_3" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_3')}">selected</c:if> >extension_column_3</option>
<option value="extension_column_4" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_4')}">selected</c:if> >extension_column_4</option>
<option value="extension_column_5" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_5')}">selected</c:if> >extension_column_5</option>
<option value="extension_column_6" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_6')}">selected</c:if> >extension_column_6</option>
<option value="extension_column_7" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_7')}">selected</c:if> >extension_column_7</option>
<option value="extension_column_8" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_8')}">selected</c:if> >extension_column_8</option>
<option value="extension_column_9" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_9')}">selected</c:if> >extension_column_9</option>
<option value="extension_column_10" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_10')}">selected</c:if> >extension_column_10</option>
<option value="extension_column_11" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_11')}">selected</c:if> >extension_column_11</option>
<option value="extension_column_12" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_12')}">selected</c:if> >extension_column_12</option>
<option value="extension_column_13" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_13')}">selected</c:if> >extension_column_13</option>
<option value="extension_column_14" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_14')}">selected</c:if> >extension_column_14</option>
<option value="extension_column_15" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_15')}">selected</c:if> >extension_column_15</option>
<option value="extension_column_16" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_16')}">selected</c:if> >extension_column_16</option>
<option value="extension_column_17" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_17')}">selected</c:if> >extension_column_17</option>
<option value="extension_column_18" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_18')}">selected</c:if> >extension_column_18</option>
<option value="extension_column_19" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_19')}">selected</c:if> >extension_column_19</option>
<option value="extension_column_20" <c:if test="${fn:contains(Tab.shortcut_segment_column, 'extension_column_20')}">selected</c:if> >extension_column_20</option>

												 
												 
												 
											
											</select>
                                      
                                                    </div>
                                                </div>
                                                
                                                
                                                                                     
                        <hr>                                     
                                            
                      <div class="card-header">
<h4 class="card-title">Grouping </h4>
</div>      
       
                                            
 <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Grouping row</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                 
                                                    
		                                    <select class="form-select" name="grouping_row" id="grouping_row" >
		                                             <option value="0"  <c:if test="${Tab.grouping_row == 0}">selected</c:if>>NO</option>
													<option value="1"  <c:if test="${Tab.grouping_row == 1}">selected</c:if>>YES</option>
												 
											</select>
                                                    </div>
                                                </div>
                                              
  
   <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Grouping row [Check]</label>
                                                    </div>
                                                    <div class="col-sm-9">  
		                               <input type="number" name="grouping_row_check" class="form-control"  id="grouping_row_check" value="${Tab.grouping_row_check}">
                                                    </div>
                                                </div>                                               
      
   <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
Grouping row [date]</label>
                                                    </div>
                                                    <div class="col-sm-9">  
		                               <input type="number" name="grouping_row_date"  class="form-control" id="grouping_row_date" value="${Tab.grouping_row_date}">
                                                    </div>
                                                </div>                                               
                                                
                                                
                                                
                                            </div>
                      
                   
                                         
                                         
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                    
                                  <input type="hidden" class="form-select"  name="id" value="${Tab.id }">  
                                     <input type="hidden"   name="process_id" value="${process_id}"> 
                                         <input type="hidden"   name="workflow_id" value="${workflow_id}"> 
                                       <input type="hidden"  name="management_id" value="${management_id}"> 
                 
                                        
                                        
                                        
                        </div>
                                                            <div class="modal-footer" Style="display: inline-block;">
                                                                <button type="Submit"   class="btn btn-primary waves-effect waves-float waves-light" data-bs-dismiss="modal">เพิ่ม</button>
                                                            </div>
                                         
                                          
                                    </form:form>
                  							       
                                </div>
                            </div>
                            
                
                       
                           
                            
                            
                        </div>
                   </div>
          
          

                  
                <div class="col-md-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><i data-feather='settings'></i>Delete Document Box</h4>
                                </div>
                                <div class="card-body">
                                
                                
							                               <%-- เพิ่ม onsubmit ตรงนี้ --%>
							<form:form method="POST" action="/deletedocumentbox-workflow" 
							           onsubmit="return confirm('ยืนยันที่จะลบข้อมูลใช่หรือไม่?');">
						
							    <div class="row">
							        <div class="col-12">
							            <div class="mb-1 row">
							                <div class="col-sm-3">
							                    <label class="col-form-label" for="first-name">Delete ?</label>
							                </div>
							                <div class="col-sm-9">
							                    <%-- ปุ่มไม่ต้องแก้อะไร --%>
							                    <button type="Submit" class="btn btn-danger waves-effect waves-float waves-light">
							                        ลบ
							                    </button>
							                </div>
							            </div>
							        </div>   
							    </div>   
								
							    <input type="hidden" class="form-select" name="id" value="${Tab.id }">  
							       <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden"   name="process_id" value="${process_id }"> 
                                         <input type="hidden"   name="workflow_id" value="${workflow_id}"> 
                                       <input type="hidden"  name="management_id" value="${management_id}"> 
							</form:form>
                                </div>
                  </div>
                 
                </div>
            
     
                </section>

            </div>
        </div>
    </div>
 
 
  
 
 

 

   <script>                                       
  function addprocess(Id){
	  $("#processe_id").val(Id);
  }
		  
	</script>	  
 
 
 
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


</body>



  
	    <script>
	    $(document).ready(function() {
	        let $select = $('#tab_columns');
	        
	        // 1. เริ่มต้น Select2 
	        $select.select2({
	            placeholder: "เลือกรายการ...",
	            allowClear: true
	        });

	        // 2. จัดการของที่เลือกใหม่ ให้ไปอยู่ "ต่อท้ายสุด" เสมอ
	        $select.on("select2:select", function (evt) {
	            let element = evt.params.data.element; // ดึงเอา <option> ที่ถูกเลือก
	            let $element = $(element);
	            
	            $element.detach(); // ดึงออก
	            $(this).append($element); // เอาไปต่อท้าย
	            $(this).trigger("change"); // สั่งอัปเดต
	        });

	        // --- ระบบ Drag & Drop (แก้ไขใหม่ ปลอดภัย 100%) ---
	        let draggedTitle = null; // เก็บแค่ "ชื่อข้อความ" ไม่เก็บตัว Element แล้ว

	        // แอบใส่ draggable ให้แท็กทุกตัวเมื่อเอาเมาส์ชี้
	        $(document).on('mouseenter', '.select2-selection__choice', function() {
	            $(this).attr('draggable', 'true');
	            $(this).css('cursor', 'grab');
	        });

	        // ตอนเริ่มลาก
	        $(document).on('dragstart', '.select2-selection__choice', function(e) {
	            draggedTitle = $(this).attr('title'); // จำชื่อป้ายที่ถูกลาก
	            setTimeout(() => $(this).css('opacity', '0.4'), 0);
	        });

	        // ตอนปล่อยลาก
	        $(document).on('dragend', '.select2-selection__choice', function(e) {
	            $(this).css('opacity', '1');
	            draggedTitle = null;
	        });

	        $(document).on('dragover', '.select2-selection__choice', function(e) {
	            e.preventDefault(); 
	        });

	        // ตอนวาง (Drop) ทับตัวอื่น
	        $(document).on('drop', '.select2-selection__choice', function(e) {
	            e.preventDefault();
	            
	            let targetTitle = $(this).attr('title'); // จำชื่อป้ายที่โดนวางทับ
	            
	            // ถ้าเป็นการลากไปทับตัวอื่นจริงๆ ไม่ใช่ตัวมันเอง
	            if (draggedTitle && targetTitle && draggedTitle !== targetTitle) {
	                reorderByData($select, draggedTitle, targetTitle);
	            }
	        });
	    });

	    // ฟังก์ชันนี้จะจัดการสลับที่ <option> หลังบ้าน แล้วสั่งให้ Select2 วาดใหม่
	    function reorderByData($select, draggedTitle, targetTitle) {
	        let draggedVal = null;
	        let targetVal = null;

	        // 1. หา Value ของตัวที่ลาก กับ ตัวที่โดนทับ
	        $select.find('option').each(function() {
	            if ($(this).text() === draggedTitle) draggedVal = $(this).val();
	            if ($(this).text() === targetTitle) targetVal = $(this).val();
	        });

	        if (!draggedVal || !targetVal) return;

	        // 2. ดึงค่าทั้งหมดที่เลือกไว้ออกมาเป็น Array
	        let currentValues = $select.val() || [];

	        // 3. คำนวณลำดับ Array ใหม่ (เอาตัวที่ลาก ไปแทรกหน้าตัวที่โดนทับ)
	        currentValues = currentValues.filter(val => val !== draggedVal); // ลบตัวที่ลากออกไปก่อน
	        let targetIndex = currentValues.indexOf(targetVal); // หาตำแหน่งของเป้าหมาย
	        currentValues.splice(targetIndex, 0, draggedVal); // เสียบค่าที่ลากกลับเข้าไป

	        // 4. จัดเรียง <option> ใน HTML ดิบใหม่
	        let $options = $select.find('option').clone();
	        $select.empty();

	        // ใส่ตัวที่เลือกลงไปตามลำดับใหม่
	        $.each(currentValues, function(index, val) {
	            $select.append($options.filter('[value="' + val + '"]'));
	        });

	        // เอาตัวที่ไม่ได้เลือกมาต่อท้าย
	        $options.filter(function() {
	            return currentValues.indexOf($(this).val()) === -1;
	        }).each(function() {
	            $select.append($(this));
	        });

	        // 5. สั่ง Select2 ให้อ่านค่าและวาดหน้าจอใหม่ทั้งหมด (แก้บั๊กค้าง)
	        $select.val(currentValues).trigger('change');
	    }
    </script>				

 
