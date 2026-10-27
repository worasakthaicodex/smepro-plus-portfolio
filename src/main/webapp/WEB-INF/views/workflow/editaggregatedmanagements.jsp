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
                                    
                                       <li class="breadcrumb-item"><a href="/aggregatedmanagements-workflow/${workflow_id}/${management_id}/${process_id}">${management.management_title }</a>
                                    </li>
                                    <li class="breadcrumb-item active">Edit Aggregated
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
                                    <h4 class="card-title"><i data-feather='settings'></i>Edit Aggregated</h4>
                                </div>
                                <div class="card-body">
                              
                                  
                              
                                                                         
<form:form method="POST"  action="/saveAggregated-workflow">
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">buildin_title</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <input type="text" class="form-select"  name="buildin_title" value="${BuildIn.buildin_title }">  
                                                    </div>
                                                </div>
                                            </div>
                                            
                                                 <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">buildin_note</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <textarea type="text" class="form-select"  name="buildin_note" value="${BuildIn.buildin_note }"></textarea>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            
                                           <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">source_type</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="source_type"  id="source_type"   style="pointer-events: none; background-color: #e9ecef;" tabindex="-1" aria-disabled="true" >
                                      <option value="1" <c:if test="${BuildIn.source_type == 1}">selected</c:if>>Workflow</option>
                                   	  <option value="2" <c:if test="${BuildIn.source_type == 2}">selected</c:if>>Segment</option>
                                   	  <option value="4" <c:if test="${BuildIn.source_type == 4}">selected</c:if>>Measure</option>
                                   	   <option value="5" <c:if test="${BuildIn.source_type == 5}">selected</c:if>>Profile</option>
                                   	      <option value="6" <c:if test="${BuildIn.source_type == 6}">selected</c:if>>Product</option>
                                   	        <option value="7" <c:if test="${BuildIn.source_type == 7}">selected</c:if>>Calendar</option>
                                   	
                                   	
                                      </select>
                                      
                                                    </div>
                                                </div>
                                            </div>
                                             
                                        
                                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">current_step</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="current_step"  id="current_step"  >
                                       <option value="-">-</option>
	                                      <option value="Create" <c:if test="${BuildIn.current_step == 'Create'}">selected</c:if>>Create</option>
	                                      <option value="Review" <c:if test="${BuildIn.current_step == 'Review'}">selected</c:if>>Review</option>
	                                              <option value="Approval" <c:if test="${BuildIn.current_step == 'Approval'}">selected</c:if>>Approval</option>
	                                      <option value="Proceed" <c:if test="${BuildIn.current_step == 'Completion'}">Proceed</c:if>>Proceed</option>
	                                       <option value="Completion" <c:if test="${BuildIn.current_step == 'Completion'}">selected</c:if>>Completion</option>
                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            
                                             
                                                       <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">source_param</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                   <select class="form-select" name="source_param" id="source_param">
									    <option value="-">-</option>
									    <c:if test="${BuildIn.source_type == 1}">
									        <option value="workflow_system_date" <c:if test="${BuildIn.source_param == 'workflow_system_date'}">selected</c:if>>วันที่เอกสารอยู่ในระบบ</option>
									        <option value="workflow_create_date" <c:if test="${BuildIn.source_param == 'workflow_create_date'}">selected</c:if>>วันที่สร้าง</option>
									        <option value="workflow_approve_date" <c:if test="${BuildIn.source_param == 'workflow_approve_date'}">selected</c:if>>วันที่อนุมัติ</option>
									        <option value="workflow_close_date" <c:if test="${BuildIn.source_param == 'workflow_close_date'}">selected</c:if>>วันที่ปิด</option>
									        <option value="workflow_closer_day_total" <c:if test="${BuildIn.source_param == 'workflow_closer_day_total'}">selected</c:if>>ปิดได้ภายในกี่วัน</option>
									        
									         <option value="workflow_closing_status" <c:if test="${BuildIn.source_param == 'workflow_closing_status'}">selected</c:if>>ขอ สถานะปิดเอกสาร</option>
									      <option value="workflow_closing_status_rejected" <c:if test="${BuildIn.source_param == 'workflow_closing_status_rejected'}">selected</c:if>>ขอ สาเหตุที่ปิดไม่ได้</option>
									       <option value="workflow_remarks" <c:if test="${BuildIn.source_param == 'workflow_remarks'}">selected</c:if>>ขอ บันทึกเพิ่มเติม</option>
									      
									        <option value="workflow_creator_name" <c:if test="${BuildIn.source_param == 'workflow_creator_name'}">selected</c:if>>ขอชื่อพนักงานที่สร้าง</option>
									        <option value="workflow_approver_name" <c:if test="${BuildIn.source_param == 'workflow_approver_name'}">selected</c:if>>ขอชื่อพนักงานที่อนุมัติ</option>
									        <option value="workflow_closer_name" <c:if test="${BuildIn.source_param == 'workflow_closer_name'}">selected</c:if>>ขอชื่อพนักงานที่ปิด</option>
									
									        
									        <option value="workflow_creator_role" <c:if test="${BuildIn.source_param == 'workflow_creator_role'}">selected</c:if>>ขอตำแหน่งงานพนักงานที่สร้าง</option>
									        <option value="workflow_approver_role" <c:if test="${BuildIn.source_param == 'workflow_approver_role'}">selected</c:if>>ขอตำแหน่งงานพนักงานที่อนุมัติ</option>
									        <option value="workflow_closer_role" <c:if test="${BuildIn.source_param == 'workflow_closer_role'}">selected</c:if>>ขอตำแหน่งงานพนักงานที่ปิด</option>
								  			
									    </c:if>
									
									    <c:if test="${BuildIn.source_type == 2}">
									        <option value="segment_factor" <c:if test="${BuildIn.source_param == 'segment_factor'}">selected</c:if>>ขอปัจจัยที่ segmentation เลือก</option>
									        <option value="segment_count" <c:if test="${BuildIn.source_param == 'segment_count'}">selected</c:if>>ขอจำนวนรายชื่อ</option>
									        
									        <option value="segment_call" <c:if test="${BuildIn.source_param == 'segment_number_of_calls'}">selected</c:if>>จำนวนโทร</option>
									        <option value="segment_call_unique" <c:if test="${BuildIn.source_param == 'segment_number_of_per_call'}">selected</c:if>>จำนวนโทร (ราย)</option>
									        <option value="segment_promotion" <c:if test="${BuildIn.source_param == 'segment_promotion'}">selected</c:if>>โปรโมชั่นที่เข้าร่วม</option>
									   
									    </c:if>
									    
									    
			
									    <c:if test="${BuildIn.source_type == 4}">
									        <option value="measure_current" <c:if test="${BuildIn.source_param == 'measure_current'}">selected</c:if>>ขอข้อมูลล่าสุด (Current Value)</option>
									          <option value="measure_0" <c:if test="${BuildIn.source_param == 'measure_10'}">selected</c:if>>ขอข้อมูล < 10%</option>
									        <option value="measure_10" <c:if test="${BuildIn.source_param == 'measure_10'}">selected</c:if>>ขอข้อมูล 10%</option>
									        <option value="measure_20" <c:if test="${BuildIn.source_param == 'measure_20'}">selected</c:if>>ขอข้อมูล 20%</option>
									        <option value="measure_30" <c:if test="${BuildIn.source_param == 'measure_30'}">selected</c:if>>ขอข้อมูล 30%</option>
									        <option value="measure_40" <c:if test="${BuildIn.source_param == 'measure_40'}">selected</c:if>>ขอข้อมูล 40%</option>
									        <option value="measure_50" <c:if test="${BuildIn.source_param == 'measure_50'}">selected</c:if>>ขอข้อมูล 50%</option>
									        <option value="measure_60" <c:if test="${BuildIn.source_param == 'measure_60'}">selected</c:if>>ขอข้อมูล 60%</option>
									        <option value="measure_70" <c:if test="${BuildIn.source_param == 'measure_70'}">selected</c:if>>ขอข้อมูล 70%</option>
									        <option value="measure_80" <c:if test="${BuildIn.source_param == 'measure_80'}">selected</c:if>>ขอข้อมูล 80%</option>
									        <option value="measure_90" <c:if test="${BuildIn.source_param == 'measure_90'}">selected</c:if>>ขอข้อมูล 90%</option>
									        <option value="measure_100" <c:if test="${BuildIn.source_param == 'measure_100'}">selected</c:if>>ขอข้อมูล 100%</option>
									    </c:if>
									
									    <c:if test="${BuildIn.source_type == 5}">
									       <option value="register_name" <c:if test="${BuildIn.source_param == 'register_name'}">selected</c:if>>ขอข้อมูล ชื่อลูกค้า</option>
									        <option value="register_type" <c:if test="${BuildIn.source_param == 'register_type'}">selected</c:if>>ขอข้อมูล ประเภทลูกค้า</option>
									        <option value="register_groups" <c:if test="${BuildIn.source_param == 'register_groups'}">selected</c:if>>ขอข้อมูล กลุ่มลูกค้า</option>
									        <option value="register_address" <c:if test="${BuildIn.source_param == 'register_address'}">selected</c:if>>ขอข้อมูล ที่อยู่</option>
									        <option value="register_note" <c:if test="${BuildIn.source_param == 'register_note'}">selected</c:if>>ขอข้อมูล หมายเหตุ</option>
									        <option value="register_poin" <c:if test="${BuildIn.source_param == 'register_poin'}">selected</c:if>>ขอข้อมูล คะแนนสะสม</option>
									        
									        <option value="contact_person" <c:if test="${BuildIn.source_param == 'contact_person'}">selected</c:if>>ขอข้อมูล ชื่อผู้ติดต่อ</option>
									        <option value="phone_number" <c:if test="${BuildIn.source_param == 'phone_number'}">selected</c:if>>ขอข้อมูล เบอร์โทรศัพท์</option>
									        <option value="mobile_number" <c:if test="${BuildIn.source_param == 'mobile_number'}">selected</c:if>>ขอข้อมูล เบอร์มือถือ</option>
									        <option value="website" <c:if test="${BuildIn.source_param == 'website'}">selected</c:if>>ขอข้อมูล เว็บไซต์</option>
									        <option value="social" <c:if test="${BuildIn.source_param == 'social'}">selected</c:if>>ขอข้อมูล โซเชียลมีเดีย</option>
									        
									        <option value="tax_id" <c:if test="${BuildIn.source_param == 'tax_id'}">selected</c:if>>ขอข้อมูล เลขผู้เสียภาษี</option>
									        <option value="branch_code" <c:if test="${BuildIn.source_param == 'branch_code'}">selected</c:if>>ขอข้อมูล รหัสสาขา</option>
									        <option value="business_type" <c:if test="${BuildIn.source_param == 'business_type'}">selected</c:if>>ขอข้อมูล ประเภทธุรกิจ</option>
									        <option value="payment_terms" <c:if test="${BuildIn.source_param == 'payment_terms'}">selected</c:if>>ขอข้อมูล เงื่อนไขการชำระเงิน</option>
									        <option value="credit_limit" <c:if test="${BuildIn.source_param == 'credit_limit'}">selected</c:if>>ขอข้อมูล วงเงินเครดิต</option>
									        <option value="bank_account" <c:if test="${BuildIn.source_param == 'bank_account'}">selected</c:if>>ขอข้อมูล เลขที่บัญชีธนาคาร</option>
									        <option value="preferred_currency" <c:if test="${BuildIn.source_param == 'preferred_currency'}">selected</c:if>>ขอข้อมูล สกุลเงิน</option>
									        
									           <option value="extension_column_1" <c:if test="${BuildIn.source_param == 'extension_column_1'}">selected</c:if>>ขอข้อมูล extension_column_1</option>
									            <option value="extension_column_2" <c:if test="${BuildIn.source_param == 'extension_column_2'}">selected</c:if>>ขอข้อมูล extension_column_2</option>
									             <option value="extension_column_3" <c:if test="${BuildIn.source_param == 'extension_column_3'}">selected</c:if>>ขอข้อมูล extension_column_3</option>
									              <option value="extension_column_4" <c:if test="${BuildIn.source_param == 'extension_column_4'}">selected</c:if>>ขอข้อมูล extension_column_4</option>
									               <option value="extension_column_5" <c:if test="${BuildIn.source_param == 'extension_column_5'}">selected</c:if>>ขอข้อมูล extension_column_5</option>
									                <option value="extension_column_6" <c:if test="${BuildIn.source_param == 'extension_column_6'}">selected</c:if>>ขอข้อมูล extension_column_6</option>
									                 <option value="extension_column_7" <c:if test="${BuildIn.source_param == 'extension_column_7'}">selected</c:if>>ขอข้อมูล extension_column_7</option>
									                  <option value="extension_column_8" <c:if test="${BuildIn.source_param == 'extension_column_8'}">selected</c:if>>ขอข้อมูล extension_column_8</option>
									                   <option value="extension_column_9" <c:if test="${BuildIn.source_param == 'extension_column_9'}">selected</c:if>>ขอข้อมูล extension_column_9</option>
									                    <option value="extension_column_10" <c:if test="${BuildIn.source_param == 'extension_column_10'}">selected</c:if>>ขอข้อมูล extension_column_10</option>
									           
									           
									           
									    </c:if>
										    <c:if test="${BuildIn.source_type == 6}">
									       <option value="product_names" <c:if test="${BuildIn.source_param == 'product_names'}">selected</c:if>>ขอข้อมูล ชื่อสินค้าเเละบริการ</option>
									        <option value="product_type" <c:if test="${BuildIn.source_param == 'product_type'}">selected</c:if>>ขอข้อมูล ประเภทสินค้า</option>
									        <option value="product_category" <c:if test="${BuildIn.source_param == 'product_category'}">selected</c:if>>ขอข้อมูล กลุ่มสินค้า</option>
									        <option value="unit" <c:if test="${BuildIn.source_param == 'unit'}">selected</c:if>>ขอข้อมูล หน่วยนับ</option>
									        <option value="product_status" <c:if test="${BuildIn.source_param == 'product_status'}">selected</c:if>>ขอข้อมูล สถานะ</option>
									   
									    </c:if>
									       <c:if test="${BuildIn.source_type == 7}">
									         
									         <option value="latest_scheduled_all" <c:if test="${BuildIn.source_param == 'latest_scheduled_all'}">selected</c:if>>
											    ขอข้อมูล วันที่กำหนดติดตามล่าสุด 
											</option>
											
											<option value="latest_scheduled_pending" <c:if test="${BuildIn.source_param == 'latest_scheduled_pending'}">selected</c:if>>
											    ขอข้อมูล วันที่กำหนดติดตามล่าสุด (ที่ยังไม่เสร็จ)
											</option>
																				         
									         
									       <option value="tracking_schedule" <c:if test="${BuildIn.source_param == 'tracking_schedule'}">selected</c:if>>ขอข้อมูล จำนวนที่กำหนดการติดตามไว้</option>
									        <option value="tracking_is_due" <c:if test="${BuildIn.source_param == 'tracking_is_due'}">selected</c:if>>ขอข้อมูล จำนวนถึงกำหนดการติดตาม</option>
									        <option value="missed_scheduled_followup" <c:if test="${BuildIn.source_param == 'missed_scheduled_followup'}">selected</c:if>>ขอข้อมูล จำนวนพลาดกำหนดการติดตาม</option>
											 <option value="followed" <c:if test="${BuildIn.source_param == 'followed'}">selected</c:if>>ขอข้อมูล จำนวนติดตามเเล้ว</option>
											  <option value="latest_scheduled" <c:if test="${BuildIn.source_param == 'latest_scheduled'}">selected</c:if>>ขอข้อมูล วันที่กำหนดติดตาม</option>
									    </c:if>
									    
									    
								
									</select>



		 
                                      
                                                    </div>
                                                </div>
                                            </div>
                                             
                                          
                                          
                                          
                                    
								<c:if test="${BuildIn.source_type == 4}">        
                                            	 <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">MeasurementRules</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="measurement_rules_id"  id="measurement_rules_id">
                                         <option value="0">-</option>
                                         
                                        <c:forEach items="${Rules}" var="row"> 
	                                      <option value="${row.id}" <c:if test="${row.id == BuildIn.measurement_rules_id }">selected</c:if>>${row.title }</option>
	                                    </c:forEach>
	                                    
	                                    
                                      </select>
                                                    </div>
                                                </div>
                                            </div>
                                 </c:if>
                                            
                            
							
      								
                                            
                                            
                                            
                                                      <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">closing_status_stop</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="closing_status_stop"  id="closing_status_stop"   >
                                      
                                       <option value="-">-</option>
                                      
	                                   <c:forEach items="${dataset_data}" var="row" varStatus="tabcounted"> 
						                    <option value="${row.dataset_data_title}" <c:if test="${BuildIn.closing_status_stop == row.dataset_data_title}">selected</c:if>>${row.dataset_data_title}</option>
						                </c:forEach>
                                      </select>
                                      
                                      
                                      
                                                    </div>
                                                </div>
                                            </div>
                                          
                                         
                                 
                                
                                   <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">tocolumn</label>
                                                    </div>
                                                    <div class="col-sm-9">
                                      <select class="form-select" name="tocolumn"  id="tocolumn"   >
                                      <option value="1"  <c:if test="${BuildIn.tocolumn == 1}">selected</c:if>>Column 1</option>
                                   	  <option value="2" <c:if test="${BuildIn.tocolumn == 2}">selected</c:if>>Column 2</option>
                                   	  <option value="3" <c:if test="${BuildIn.tocolumn == 3}">selected</c:if>>Column 3</option>
                                   	   <option value="4" <c:if test="${BuildIn.tocolumn == 4}">selected</c:if>>Column 4</option>
                                   	   <option value="5" <c:if test="${BuildIn.tocolumn == 5}">selected</c:if>>Column 5</option>
                                   	   <option value="6" <c:if test="${BuildIn.tocolumn == 6}">selected</c:if>>Column 6</option>
                                   	  <option value="7" <c:if test="${BuildIn.tocolumn == 7}">selected</c:if>>Column 7</option>
                                   	     <option value="8" <c:if test="${BuildIn.tocolumn == 8}">selected</c:if>>Column 8</option>
                                   	    <option value="9" <c:if test="${BuildIn.tocolumn == 9}">selected</c:if>>Column 9</option>
                                 	    <option value="10" <c:if test="${BuildIn.tocolumn == 10}">selected</c:if>>Column 10</option>
                                 	    
                                     <option value="11"  <c:if test="${BuildIn.tocolumn == 11}">selected</c:if>>Column 11</option>
                                 	  <option value="12"  <c:if test="${BuildIn.tocolumn == 12}">selected</c:if>>Column 12</option>
                                 	 <option value="13"  <c:if test="${BuildIn.tocolumn == 13}">selected</c:if>>Column 13</option>
                                 	 <option value="14"  <c:if test="${BuildIn.tocolumn == 14}">selected</c:if>>Column 14</option>
                                 	  <option value="15"  <c:if test="${BuildIn.tocolumn == 15}">selected</c:if>>Column 15</option>
                                 	   <option value="16"  <c:if test="${BuildIn.tocolumn == 16}">selected</c:if>>Column 16</option>
                                 	   <option value="17"  <c:if test="${BuildIn.tocolumn == 17}">selected</c:if>>Column 17</option>
                                 	    <option value="18"  <c:if test="${BuildIn.tocolumn == 18}">selected</c:if>>Column 18</option>
                                 	     <option value="19"  <c:if test="${BuildIn.tocolumn == 19}">selected</c:if>>Column 19</option>
                                 	   <option value="20"  <c:if test="${BuildIn.tocolumn == 20}">selected</c:if>>Column 20</option>
                                      </select>
                                      
                                                    </div>
                                                </div>
                                            </div>
                                             
                                
                                <hr>
                               
                    
                                     	   
                                 			    <input type="hidden" class="form-select" name="id" value="${BuildIn.id }">  
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden"   name="process_id" value="${process_id}"> 
                                         <input type="hidden"   name="workflow_id" value="${workflow_id}"> 
                                       <input type="hidden"  name="management_id" value="${management_id}"> 
                 
                          
                                        
                                        
                                        
                        </div>
                                                  <div class="modal-footer" Style="display: inline-block;">
                                                                <button type="Submit"   class="btn btn-primary waves-effect waves-float waves-light" data-bs-dismiss="modal">
                                                                <spring:message code="btn.save" />
                                                                </button>
                                                            </div>          
                                          
                                    </form:form>
                  							       
                                </div>
                            </div>
                            
                
                       
                           
                            
                            
                        </div>
                   </div>
          
          

                  
                <div class="col-md-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><i data-feather='settings'></i>Delete Aggregated</h4>
                                </div>
                                <div class="card-body">
                                
                                
							                               <%-- เพิ่ม onsubmit ตรงนี้ --%>
							<form:form method="POST" action="/deleteAggregated-workflow" 
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
								
							    <input type="hidden" class="form-select" name="id" value="${BuildIn.id }">  
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
