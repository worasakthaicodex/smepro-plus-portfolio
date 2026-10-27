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
          
     
          
          
          
                    <div class="row">
                        <div class="col-lg-12 col-12">
                            <div class="card" >
                                <div class="card-header">
    <h3 >${workflows.workflow_name}  <c:if test="${workflows.workflow_name  ==  null  }"> <spring:message code="workflows.Notyetdetermined" />  </c:if>
    
   <c:if test="${workflows.workflow_note  >  0  }">        
                       <br>   
                     
               
             
            
                   
                   
            
                    </c:if> 
        </h3>
        
            
                               
            
            
                               
                               
                     
                                          
                                </div>
                                
                                
                         <c:if test="${workflows.workflow_note  == 0  }">
    <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span>Error01  <spring:message code="workflows.Notyetdetermineds" /></span>
        </div>
          </div>
      </c:if>           
                                
                                    <div class="card-body mt-2" id="search" >      
                                
                     
                                   
                                   <section class="vertical-wizard">
                    <div class="bs-stepper vertical vertical-wizard-example">
                        <div class="bs-stepper-header">
                          
                             <c:forEach items="${flow }" var="flows" varStatus="count">  
   
                               <c:set var="active" value="" />
                                     <c:if test="${flowid  == Idflow[count.count-1] }">  <c:set var="active" value="active" />  </c:if>
                 
                            <div class="step ${active }"  >
	                               
	                               
			                                <button  type="button" class="step-trigger" >
			                              
			                                    <span class="bs-stepper-box">${count.count }</span>
			                                    <span class="bs-stepper-label">
			                                              <a href="/view-workflow/${id}?flowid=${Idflow[count.count-1]}"> <span class="bs-stepper-title">${flows }</span>    
			                                               </a>
			                                      
			                                    </span>
			                                  
			                                </button>
	                                 
                            </div>
                        
                            </c:forEach>
                            
                            
                         
                        </div>
                        <div class="bs-stepper-content">
                            <div id="account-details-vertical" class="content dstepper-block active" role="tabpanel" aria-labelledby="account-details-vertical-trigger">
                       
                                   <c:if test="${workflows.workflow_note  >  0  }">            
                      
                                <form:form method="POST" modelAttribute="user" action="/search-workflow"  target="_blank">
           <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                   <input  type="hidden" name="ids" value="${workflows.workflow_id}">                      
                            <input  type="hidden" name="flowid" value="${flowid}">            
                                  
                                       
                                               <c:if test="${key1  !=  null  }">  
                                      <div class="col-md-6">
                                                    <label class="form-label">${key1.field_label }:</label>
                                      <select class="form-select select2"  name="purchase_order">   
		                                      <option value="">-</option>
		                                      
		                                           <c:set var="key" value="${key1}"/>
		                                           <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select> 
                                       
                                       
                                      
                                      <input type="hidden"   name="keytitle_purchase_order" value="${key1.field_label }">   
                                         <input type="hidden"  name="keyno_purchase_order" value="${key1.field_postion }">   
                                       </div>  
                                       <br>
                                            </c:if>
                                            
                                            
                                                    <c:if test="${key2  !=  null  }">       
                                         <div class="col-md-6">
                                                    <label class="form-label">${key2.field_label }:</label>
                         
                                      <input type="hidden" class="form-select"  name="keytitle_customer" value="${key2.field_label }">   
                                        <select class="form-select select2"  name="customer">   
		                                      <option value=" ">-</option>
		                                      
		                                      
		                                   		         <c:set var="key" value="${key2}"/>
		                                      <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select> 
                                      
                                      
                                            <input type="hidden" class="form-select"  name="keyno_customer" value="${key2.field_postion }">   
                                       </div>  
                                           <br>
                                           </c:if>
                                       
                                                    <c:if test="${key3  !=  null  }"> 
                                           <div class="col-md-6">
                                                    <label class="form-label">${key3.field_label }:</label>
                             
                                      <input type="hidden" class="form-select"  name="keytitle_product" value="${key3.field_label }">   
                                            <input type="hidden" class="form-select"  name="keyno_product" value="${key3.field_postion }">   
                                            
                                            
                                            
                                              <select class="form-select select2"  name="product">   
		                                      <option value=" ">-</option>
		                                      
		                                      
		                                   		         <c:set var="key" value="${key3}"/>
		                                      <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select> 
                                            
                                            
                                            
                                            
                                       </div> 
                                           <br>
                                           </c:if>
                                       
                                               <c:if test="${key4  !=  null  }">    
               						<div class="col-md-6">
                                 <label class="form-label">${key4.field_label }:</label>
                 
                                      <input type="hidden" class="form-select"  name="keytitle_lot" value="${key4.field_label }">   
                                             <input type="hidden" class="form-select"  name="keyno_lot" value="${key4.field_postion }">   
                                             
                                             
                                                  
                                              <select class="form-select select2"  name="lot">   
		                                      <option value=" ">-</option>
		                                      
		                                      
		                                   		         <c:set var="key" value="${key4}"/>
		                                      <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select> 
                                            
                                  </div>   
                                      <br>
                                     </c:if>
                         
                                      <c:if test="${key5  !=  null  }"> 
                                     <div class="col-md-6">
	    					<label class="form-label">${key5.field_label }:</label>
                            
                                      <input type="hidden" class="form-select"  name="keytitle_material" value="${key5.field_label }">   
                                         <input type="hidden" class="form-select"  name="keyno_material" value="${key5.field_postion }"> 
                                         
                                              <select class="form-select select2"  name="material">   
		                                      <option value=" ">-</option>
		                                      
		                                      
		                                   		         <c:set var="key" value="${key5}"/>
		                                      <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select> 
                                  </div>
                                      <br>
                                     </c:if>
                                  
                                      <c:if test="${key6  !=  null  }"> 
                                     <div class="col-md-6">
                                  	<label class="form-label">${key6.field_label }:</label>
                                
                                      <input type="hidden" class="form-select"  name="keytitle_lot_material" value="${key6.field_label }">   
                                         <input type="hidden" class="form-select"  name="keyno_lot_material" value="${key6.field_postion }"> 
                                         
                                            <select class="form-select select2"  name="lot_material">   
		                                      <option value=" ">-</option>
		                                      
		                                      
		                                   		         <c:set var="key" value="${key6}"/>
		                                      <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select> 
                                  </div>  
                                      <br>  
                                     </c:if>
                                  
                                      <c:if test="${key7  !=  null  }"> 
                                   <div class="col-md-6">
                                  	<label class="form-label">${key7.field_label }:</label>
                 
                                      <input type="hidden" class="form-select"  name="keytitle_wip" value="${key7.field_label }">  
                                        <input type="hidden" class="form-select"  name="keyno_wip" value="${key7.field_postion }">  
                                        
                                             <select class="form-select select2"  name="wip">   
		                                      <option value=" ">-</option>
		                                      
		                                      
		                                   		         <c:set var="key" value="${key7}"/>
		                                      <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select> 
                                        
                                  </div>      
                                      <br>
	       </c:if>
	        <c:if test="${key8  !=  null  }"> 
	                            <div class="col-md-6">
                                  	<label class="form-label">${key8.field_label }:</label>
                            
                                      <input type="hidden" class="form-select"  name="keytitle_lot_wip" value="${key8.field_label }">
                                            <input type="hidden" class="form-select"  name="keyno_lot_wip" value="${key8.field_postion }">   
                                            
                                                 <select class="form-select select2"  name="lot_wip">   
		                                      <option value=" ">-</option>
		                                      
		                                      
		                                   		         <c:set var="key" value="${key8}"/>
		                                      <c:if test="${key.field_postion == 1 }"><c:set var="option" value="${option1}"/></c:if>
		                                           <c:if test="${key.field_postion == 2 }"><c:set var="option" value="${option2}"/></c:if>
		                                           <c:if test="${key.field_postion == 3 }"><c:set var="option" value="${option3}"/></c:if>
		                                           <c:if test="${key.field_postion == 4 }"><c:set var="option" value="${option4}"/></c:if>
		                                           <c:if test="${key.field_postion == 5 }"><c:set var="option" value="${option5}"/></c:if>
		                                           <c:if test="${key.field_postion == 6 }"><c:set var="option" value="${option6}"/></c:if>
		                                           <c:if test="${key.field_postion == 7 }"><c:set var="option" value="${option7}"/></c:if>
		                                           <c:if test="${key.field_postion == 8 }"><c:set var="option" value="${option8}"/></c:if>
		                                           <c:if test="${key.field_postion == 9 }"><c:set var="option" value="${option9}"/></c:if>
		                                           <c:if test="${key.field_postion == 10 }"><c:set var="option" value="${option10}"/></c:if>
		                                           <c:if test="${key.field_postion == 11 }"><c:set var="option" value="${option11}"/></c:if>
		                                          <c:if test="${key.field_postion == 12 }"><c:set var="option" value="${option12}"/></c:if>
		                                           <c:if test="${key.field_postion == 13 }"><c:set var="option" value="${option13}"/></c:if>
		                                           <c:if test="${key.field_postion == 14 }"><c:set var="option" value="${option14}"/></c:if>
		                                           <c:if test="${key.field_postion == 15 }"><c:set var="option" value="${option15}"/></c:if>
		                                           <c:if test="${key.field_postion == 16 }"><c:set var="option" value="${option16}"/></c:if>
		                                           <c:if test="${key.field_postion == 17 }"><c:set var="option" value="${option17}"/></c:if>
		                                           <c:if test="${key.field_postion == 18 }"><c:set var="option" value="${option18}"/></c:if>
		                                           <c:if test="${key.field_postion == 19 }"><c:set var="option" value="${option19}"/></c:if>
		                                           <c:if test="${key.field_postion == 20 }"><c:set var="option" value="${option20}"/></c:if>
		                                      
		                                      
		                                      
		                                     <c:forEach items="${option}" var="row"> 
		                                     
		                                     <c:if test="${key.field_postion == 1 }">
		                                        <option value="${row.data_value1}">${row.data_value1}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 2 }">
		                                        <option value="${row.data_value2}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 3 }">
		                                        <option value="${row.data_value3}">${row.data_value3}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 4 }">
		                                        <option value="${row.data_value4}">${row.data_value4}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 5 }">
		                                        <option value="${row.data_value5}">${row.data_value5}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 6 }">
		                                        <option value="${row.data_value6}">${row.data_value6}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 7 }">
		                                        <option value="${row.data_value7}">${row.data_value7}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 8 }">
		                                        <option value="${row.data_value8}">${row.data_value8}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 9 }">
		                                        <option value="${row.data_value9}">${row.data_value9}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 10 }">
		                                        <option value="${row.data_value10}">${row.data_value10}</option>
		                                     </c:if>
		                                      
		                                       <c:if test="${key.field_postion == 11 }">
		                                        <option value="${row.data_value11}">${row.data_value11}</option>
		                                     </c:if>
		                                   <c:if test="${key.field_postion == 12 }">
		                                        <option value="${row.data_value12}">${row.data_value2}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 13 }">
		                                        <option value="${row.data_value13}">${row.data_value13}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 14 }">
		                                        <option value="${row.data_value14}">${row.data_value14}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 15 }">
		                                        <option value="${row.data_value15}">${row.data_value15}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 16 }">
		                                        <option value="${row.data_value16}">${row.data_value16}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 17 }">
		                                        <option value="${row.data_value17}">${row.data_value17}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 18 }">
		                                        <option value="${row.data_value18}">${row.data_value18}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 19 }">
		                                        <option value="${row.data_value19}">${row.data_value19}</option>
		                                     </c:if>
		                                     <c:if test="${key.field_postion == 20 }">
		                                        <option value="${row.data_value20}">${row.data_value20}</option>
		                                     </c:if>
		                                      
		                                      </c:forEach>
                                       </select>   
                               </div>  
                                      <br>
                                   </c:if>  
	
	    	
                                               <c:if test="${key9  !=  null  }">  
                                             <div class="col-md-6">
                                                <input type="hidden" class="form-select"  name="eflow_key5" value="${key9.field_postion }">   
                                                    <label class="form-label">${key9.field_label }:</label>
                                     <input type="text" class="form-control dt-date flatpickr-range dt-input" data-column="5" 
                                     placeholder="StartDate to EndDate" data-column-index="6" name="dt_date" /> 
                                           <input type="hidden" class="form-select"  name="keytitle_date" value="${key9.field_label }">
                                        
                                       </div>
                                       
                                             <br>
                                          </c:if>  
                                <c:if test="${key9  ==  null  }">  
                                             <div class="col-md-6">
                                                    <label class="form-label"><spring:message code="comment.date"/>:</label>
                                     <input type="text" class="form-control dt-date flatpickr-range dt-input" data-column="5" 
                                     placeholder="StartDate to EndDate" data-column-index="6" name="dt_date" /> 
                                               <input type="hidden" class="form-select"  name="keytitle_date" value="วันที่อัพโหลด">
                                       </div>
                                </c:if>  
                                         <div class="col-md-6">
                                  	<label class="form-label">ประเภทสอบกลับ:</label>
                            
               
    
                                            
                                                 <select class="form-select select2"  name="flowtype">   
		                                 
		                              <c:if test="${forward  ==  null  }">  
		                                        <option value="backward">backward</option>
		                              </c:if> 
		                            			  <option value="forward">forward</option>
		                                  
                                       </select>   
                               </div>  
                                      <br>
                                    
                                      <br>  <br>  <br>  
                            
                            
                            
                            
                            
                              
                                
      
            
           
                                <div class="d-flex justify-content-between">
                                  <input type="submit" class="btn btn-success" value="<spring:message code="btn.search" />">    
                                </div>
                                
                                     </form:form>
                                             </c:if> 
                            </div>
                            <div id="personal-info-vertical" class="content dstepper-block" role="tabpanel" aria-labelledby="personal-info-vertical-trigger">
                                <div class="content-header">
                                    <h5 class="mb-0">Personal Info</h5>
                                    <small>Enter Your Personal Info.</small>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-first-name">First Name</label>
                                        <input type="text" id="vertical-first-name" class="form-control" placeholder="John">
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-last-name">Last Name</label>
                                        <input type="text" id="vertical-last-name" class="form-control" placeholder="Doe">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-country">Country</label>
                                        <div class="position-relative"><select class="select2 w-100 select2-hidden-accessible" id="vertical-country" data-select2-id="vertical-country" tabindex="-1" aria-hidden="true">
                                            <option label=" " data-select2-id="5"></option>
                                            <option>UK</option>
                                            <option>USA</option>
                                            <option>Spain</option>
                                            <option>France</option>
                                            <option>Italy</option>
                                            <option>Australia</option>
                                        </select><span class="select2 select2-container select2-container--default" dir="ltr" data-select2-id="4" style="width: auto;"><span class="selection"><span class="select2-selection select2-selection--single" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="0" aria-disabled="false" aria-labelledby="select2-vertical-country-container"><span class="select2-selection__rendered" id="select2-vertical-country-container" role="textbox" aria-readonly="true"><span class="select2-selection__placeholder">Select value</span></span><span class="select2-selection__arrow" role="presentation"><b role="presentation"></b></span></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span></div>
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-language">Language</label>
                                        <div class="position-relative"><select class="select2 w-100 select2-hidden-accessible" id="vertical-language" multiple="" data-select2-id="vertical-language" tabindex="-1" aria-hidden="true">
                                            <option>English</option>
                                            <option>French</option>
                                            <option>Spanish</option>
                                        </select><span class="select2 select2-container select2-container--default" dir="ltr" data-select2-id="6" style="width: auto;"><span class="selection"><span class="select2-selection select2-selection--multiple" role="combobox" aria-haspopup="true" aria-expanded="false" tabindex="-1" aria-disabled="false"><ul class="select2-selection__rendered"><li class="select2-search select2-search--inline"><input class="select2-search__field" type="search" tabindex="0" autocomplete="off" autocorrect="off" autocapitalize="none" spellcheck="false" role="searchbox" aria-autocomplete="list" placeholder="Select value" style="width: 0px;"></li></ul></span></span><span class="dropdown-wrapper" aria-hidden="true"></span></span></div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <button class="btn btn-primary btn-prev waves-effect waves-float waves-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left align-middle me-sm-25 me-0"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                                        <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                    </button>
                                    <button class="btn btn-primary btn-next waves-effect waves-float waves-light">
                                        <span class="align-middle d-sm-inline-block d-none">Next</span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right align-middle ms-sm-25 ms-0"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>
                                    </button>
                                </div>
                            </div>
                            <div id="address-step-vertical" class="content dstepper-block" role="tabpanel" aria-labelledby="address-step-vertical-trigger">
                                <div class="content-header">
                                    <h5 class="mb-0">Address</h5>
                                    <small>Enter Your Address.</small>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-address">Address</label>
                                        <input type="text" id="vertical-address" class="form-control" placeholder="98  Borough bridge Road, Birmingham">
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-landmark">Landmark</label>
                                        <input type="text" id="vertical-landmark" class="form-control" placeholder="Borough bridge">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="pincode2">Pincode</label>
                                        <input type="text" id="pincode2" class="form-control" placeholder="658921">
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="city2">City</label>
                                        <input type="text" id="city2" class="form-control" placeholder="Birmingham">
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <button class="btn btn-primary btn-prev waves-effect waves-float waves-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left align-middle me-sm-25 me-0"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                                        <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                    </button>
                                    <button class="btn btn-primary btn-next waves-effect waves-float waves-light">
                                        <span class="align-middle d-sm-inline-block d-none">Next</span>
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-right align-middle ms-sm-25 ms-0"><line x1="5" y1="12" x2="19" y2="12"></line><polyline points="12 5 19 12 12 19"></polyline></svg>
                                    </button>
                                </div>
                            </div>
                            <div id="social-links-vertical" class="content dstepper-block" role="tabpanel" aria-labelledby="social-links-vertical-trigger">
                                <div class="content-header">
                                    <h5 class="mb-0">Social Links</h5>
                                    <small>Enter Your Social Links.</small>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-twitter">Twitter</label>
                                        <input type="text" id="vertical-twitter" class="form-control" placeholder="https://twitter.com/abc">
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-facebook">Facebook</label>
                                        <input type="text" id="vertical-facebook" class="form-control" placeholder="https://facebook.com/abc">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-google">Google+</label>
                                        <input type="text" id="vertical-google" class="form-control" placeholder="https://plus.google.com/abc">
                                    </div>
                                    <div class="mb-1 col-md-6">
                                        <label class="form-label" for="vertical-linkedin">Linkedin</label>
                                        <input type="text" id="vertical-linkedin" class="form-control" placeholder="https://linkedin.com/abc">
                                    </div>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <button class="btn btn-primary btn-prev waves-effect waves-float waves-light">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-arrow-left align-middle me-sm-25 me-0"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                                        <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                    </button>
                                    <button class="btn btn-success btn-submit waves-effect waves-float waves-light">Submit</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
          
               
                                   
                              
                                </div>
						                 
						                
<div class="md-stepper-horizontal orange" id="step" style="display:none">
 <div style="padding:20px;">

           <form:form method="POST" modelAttribute="user" action="/edit-workflow">
           <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="col-md-6">
                                                    <label class="form-label"><spring:message code="workflows.name" />:</label>
                                      <input type="text" class="form-select"  name="workflow_name" value="${workflows.workflow_name }">   
                                           <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }">   
                                       </div>
            <br>
              <div class="col-md-6">
                                                    <label class="form-label"><spring:message code="workflows.datalink" />:</label>
                                       <select  class="form-select select2" name="workflow_note">
                                       
                                        <option value="0">-</option>
                                         
                                           
                                                    <c:forEach items="${conditionadd }" var="row" varStatus="count">  
                                                      <c:set var="check" value="" />
										             <c:if test="${workflows.workflow_note == row.condition_id }">  
										                <c:set var="check" value="selected" />
										                	
										           </c:if>  
										                                                    
                                                    
                                                   			 <option value="${row.condition_id }" ${check }>${row.condition_name }</option>
                                                  </c:forEach>
                                        </select>     
                                       </div>
                                            <br>
                                            
                       
                                       
                                       
                                   <br>    <br>
                               <br>   
                           
                        
                        
                          
                                     
                                         <input type="submit" class="btn btn-success" value="<spring:message code="btn.save" />">
                                   
                                         
                                          <a href="/del-workflow/${workflows.workflow_id}" class="btn btn-danger" style="float:right"
                                           onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${workflows.workflow_name}');" >
                                                      <spring:message code="comment.del" />
                                          </a>   
                                           </form:form>
                                           
              
                                 
                                 <style>
  #box {
  border:1px solid #ddd;
  padding:0px;
  }



</style>
                                    <hr>
                                           <div id="box"> 
                                            <table class="table" >
                                            
                                            <thead>
                            <tr>
                        <th style="width:80%">Name</th><th>Setting</th>
                        </tr  
                   <c:forEach items="${flow }" var="flows" varStatus="count">  
                           <tr>
                        <td>${flows}</td>
                        
                        <td>
                        <a class="btn btn-light btn-sm" onclick="getdata('/property-workflow/${Idflow[count.count-1]}/${workflows.workflow_id}','${Idflow[count.count-1]}','maindata');"   
                        data-bs-dismiss="modal" aria-label="Close" data-bs-toggle="modal" data-bs-target="#addNewCard2">Set</a>
                        </td>
                        
                        
                        </tr>
                              </thead>
                   </c:forEach>  
                        </table>            </div>
                                                </div>
  </div>            
						                 
						                 
						                                  
                                </div>
                            </div>
                        </div>
                  
                 
             <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle2" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle">Traceability Setting</h1>               
                      <table class="table">
                            <tr>
                        <th style="width:80%">Name</th><th>Setting</th>
                        </tr  
                   <c:forEach items="${flow }" var="flows" varStatus="count">  
                           <tr>
                        <td>${flows}</td>
                        
                        <td>
                        <a class="btn btn-light btn-sm" onclick="getdata('/property-workflow/${Idflow[count.count-1]}/${workflows.workflow_id}','${Idflow[count.count-1]}','maindata');"   
                        data-bs-dismiss="modal" aria-label="Close" data-bs-toggle="modal" data-bs-target="#addNewCard2">Set</a>
                        </td>
                        
                        
                        </tr>
                   </c:forEach>  
                        </table> 
                            </div>
                        </div>
                    </div>
                     
                </div>
                
                
                
                
                
                
                <div class="modal fade" id="addNewCard2" tabindex="-1" aria-labelledby="exampleModalScrollableTitle"  aria-modal="true" >
                                                <div class="modal-dialog modal-dialog-scrollable">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalScrollableTitle">Property Keys</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                               <form:form method="POST" id="FORM1" modelAttribute="fieldindex" action="#">
                              
                       
                  			  <div id="maindata"></div>
                  			  
                  			  <div class="col-12">
                                               
                                          
                                            </div>
                  	</form:form>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" onclick="postdata('/save-workflow?${_csrf.parameterName}=${_csrf.token}','FORM1','maindata')" 
                                                class="btn btn-primary me-1 waves-effect waves-float waves-light">Save</button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                
                 
                 
                <!--/ add new card modal  -->
                   
                        
     <script>
           
	  $( function() {
		  
		  var newdata = '${newdata}'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง

		// แยกข้อมูลด้วยช่องว่างเป็น array
		var dataItems = trimmedData.split(/\s+/);

		// แปลงทุกตัวใน array เป็นตัวพิมพ์ใหญ่หรือตัวพิมพ์เล็ก
		var availableTags = new Set(dataItems.map(function(item) {
		    return item.toLowerCase(); // หรือใช้ .toUpperCase() ถ้าต้องการให้เป็นตัวพิมพ์ใหญ่ทั้งหมด
		}));

		// แปลง Set กลับเป็น array
		var uniqueTags = Array.from(availableTags);
		  
		  
	    $( ".autocomplete-input" ).autocomplete({
	      source: uniqueTags
	    });
	  } );
</script>   
              
              
                    
                 
                 
            
            
     
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





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
 <script src="/resources/app-assets/js/scripts/forms/form-wizard.js"></script>
    <script>
    
  
    
    function openCenteredWindow(url, name, width, height) {
        // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
        var left = (window.innerWidth / 2) - (width / 2);
        var top = (window.innerHeight / 2) - (height / 2);

        // เปิดหน้าต่างใหม่
        window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
    }

  
    
    function postdata(Mode,Mode2,Mode3) {


	    var formData = new FormData(document.getElementById(Mode2));

			    $.ajax({
			        url: Mode,
			        type: "POST",
			        data: formData,
			        processData: false,
			        contentType: false,
				    success: function(result){
				    $('#' + Mode3).html(result);
		
				       }
				    });

	    return false;
	    }	
    
    function getdata(Mode,Mode2,Mode3) {
			    $.ajax({
			        url: Mode,
			        type: "GET",
			        data: {ID : Mode2},
			        processData: false,
			        contentType: false,
				    success: function(result){
				    $('#' + Mode3).html(result);
		
				       }
				    });

	    return false;
	    }	
    
    
    function changeRowColor(linkElement) {
	    var rowbodies = document.querySelectorAll('.rowbody');
	    
	    // กำหนด event listener สำหรับทุก .rowbody
	    rowbodies.forEach(function(rowbody) {
	        rowbody.addEventListener('click', function(event) {
	            rowbodies.forEach(function(body) {
	                body.style.backgroundColor = 'transparent'; // กำหนดสีพื้นหลังเป็น透วสีทั้งหมด
	            });
	            rowbody.style.backgroundColor = '#F5F5F5'; // เปลี่ยนสีพื้นหลังของ .rowbody ที่ถูกคลิกเป็นสีเหลือง
	        });
	    });
	    
	    // หา <tr> ที่ประกอบไปด้วยลิงก์ที่ถูกคลิก
	    var row = linkElement.closest('tr');
	    
	    // กำหนดสีพื้นหลังของ <tr> ที่ถูกคลิกเป็นสีเหลือง
	    row.style.backgroundColor = '#F5F5F5';
	}
  
    
    function openCenteredWindow(url, name, width, height) {
        // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
        var left = (window.innerWidth / 2) - (width / 2);
        var top = (window.innerHeight / 2) - (height / 2);

        // เปิดหน้าต่างใหม่
        window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
    }
    function step(id){
    	
    	if(id == 1){
    		$("#search").show();
    		$("#step").hide();
    	}else{
    		$("#step").show();
    		$("#search").hide();
    	}
    	
    
    	
    }
    

        feather.replace({
            width: 14,
            height: 14
        });
 
    
    
        var element = document.getElementById("nav_datalink");
        element.classList.add("active");  

    </script>
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


