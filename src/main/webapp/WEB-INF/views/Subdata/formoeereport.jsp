<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    


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
<title>File info</title>


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
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${workflow_id}">${workflows.workflow_name}</a>
                                    </li>
                                      <li class="breadcrumb-item"><a href="/integrationsapp-workflow/${workflow_id}">Process Setting</a>
                                    </li>
                                    
                                        <li class="breadcrumb-item"><a href="/mainreport-workflow/${workflow_id}">Main Reports</a>
                                    </li>
                                             <li class="breadcrumb-item"><a href="/systemproduction-workflow/${workflow_id}">Production Reports</a>
                                    </li>
                                    
                                    <li class="breadcrumb-item active">Setting [${reportsetting.reportsetting_type }]
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
                              
                                <div class="card-body">
                            <div class="modal-content">          

                  
                                                       <form:form method="POST" modelAttribute="reportsettings" action="/editsettingwip-workflow">
                                               
                                                     <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                     <input type="hidden"   name="workflow_id" value="${workflows.workflow_id }"> 
                                                       <input  type="hidden" name="reportsetting_id" value="${reportsetting.reportsetting_id}">
        									
						                      		
						                      		
						                      		
						                      		    <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.machine_id" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_machine" id="reportsetting_machine" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                                   
								                                <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_batch == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_batch == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
								                              
								                              
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      					  
						                      					  
						                      				<div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.date" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_date_wip" id="reportsetting_date_wip" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                                        
								                                                 <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_date_wip == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_date_wip == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
								                             
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      		
						                      		        <c:if test="${reportsetting.reportsetting_type == 'availability' }">
						                      		        
						                      		        
						                      		          <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.downtime" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_downtime" id="reportsetting_downtime" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                               
								                                  <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_downtime == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_downtime == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
  															    
  																	
								                               
								                                     
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div> 					  
						                      		        
						                      		        	        
						                      		          <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.planned_time" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_planned_time" id="reportsetting_planned_time" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                               
								                                  <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_planned_time == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_planned_time == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
  															    
  																	
								                               
								                                     
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div> 		
						                      		        
						                      		        
						                      		        
						                      		        
						                      		        </c:if>			  
						                      			
						                      			      <c:if test="${reportsetting.reportsetting_type == 'performance' }">
						                      			      
						                      			      	  		    <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.speed" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_speed" id="reportsetting_speed" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                               
								                                  <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_speed == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_speed == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
  															    
  																	
								                               
								                                     
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      			      
						                      			      
						                      			      	  		    <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.total_output" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_total_count" id="reportsetting_total_count" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                               
								                                  <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_total_count == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_total_count == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
  															    
  																	
								                               
								                                     
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      			      
						                      			      
						                      			        <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.operating_time" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_operating_time" id="reportsetting_operating_time" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                               
								                                  <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_operating_time == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_operating_time == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
  															    
  																	
								                               
								                                     
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      			      
						                      			      
						                      		        </c:if>			  
						                      					  
						                      					  
						                      				<c:if test="${reportsetting.reportsetting_type == 'quality' }">
						                      				
						                      				   	  		    <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.total_output" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_total_count" id="reportsetting_total_count" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                               
								                                  <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_total_count == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_total_count == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
  															    
  																	
								                               
								                                     
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      				    
						                      			        <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-4">
						                                                        <label class="col-form-label" for="first-name">
						                                                       <spring:message code="product.actual_output" />: 
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-8">
						                            			           <select class="form-select select2"  name="reportsetting_good_count" id="reportsetting_good_count" >
								                               <option value="0">-</option>
								                               <c:forEach items="${eformfields }" var="row" varStatus="count">
								                               
								                                  <c:if test="${row.keymap != null}">
  															 
  															    <option value="${row.keymap.mainkey}" 
  															    <c:if test="${ reportsetting.reportsetting_good_count == row.keymap.mainkey }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															     </c:if>
  															     
  															     <c:if test="${row.keymap == null}">
  															       <c:set var="data_value" value="data_value${row.field_postion}"/>    
  															    <option value="${data_value}" 
  															    <c:if test="${ reportsetting.reportsetting_good_count == data_value }">selected</c:if>>
  															    ${row.field_label }
  															    </option>
  															    </c:if>
  															    
  																	
								                               
								                                     
								                                 </c:forEach>   
								                                        </select> 
						                                                    </div>
						                                   
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      			      
						                      		        </c:if>			  

						                      					  
						                      					  <br>  <br>
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
