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
<title>Cplatform</title>


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
          
     
     
     
    <style>
    
.page-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 2rem;
  padding: 5px;
  user-select: none;
}

.menu-section {
  width: 103rem;
}

.dual-menu-section {
  display: grid;
  grid-template-columns: 33rem 33rem;
  gap: 1rem;
  justify-content: center;
}

.triple-menu-section {
  display: grid;
  grid-template-columns: 33rem 16rem 16rem;
  gap: 1rem;
  justify-content: center;
}

.menu-container {
  position: relative;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 2rem 0 0;
  // box-shadow: 0 0 0 1px #a7afb6;
}

@media screen and (max-width: 1200px) {
  .menu-section {
    width: 50rem;
  }
  .dual-menu-section {
    grid-template-columns: 16rem 33rem;
  }
  .triple-menu-section {
    grid-template-columns: 16rem 16rem 16rem;
  }
}

@media screen and (max-width: 960px) {
  .menu-section {
    width: 33rem;
  }
  .dual-menu-section {
    grid-template-columns: 33rem;
  }
  .triple-menu-section {
    grid-template-columns: 16rem 16rem;
    
    & .menu-container:first-child {
      grid-column-start: 1;
      grid-column-end: 3;
    }
  }
}

@media screen and (max-width: 680px) {
  .menu-section {
    width: 16rem;
  }
  .dual-menu-section,
  .triple-menu-section {
    grid-template-columns: 16rem;
    
    & .menu-container:first-child {
      grid-column-start: 1;
      grid-column-end: 2;
    }
  }
}

.menu-header {
  position: relative;
  font-size: 12px;
  font-weight: 500;
  text-transform: uppercase;
  line-height: 1;
  letter-spacing: 1.5px;
  padding-bottom: 1rem;
  box-shadow: 0 1px 0 0 #e0dedc;
}

.menu-items-container {
  display: grid;
  grid-template-columns: repeat(auto-fill, 20rem);
  justify-content: center;
  gap: 1rem;
}

.menu-items {
  flex: 1 1 0;
  position: relative;
  display: flex;
  gap: 0.5rem;
  padding: 1rem;
  border-radius: 0.25rem;
  cursor: pointer;
  
  &::before {
    z-index: -1;
    content: '';
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    transform: scale(1.1);
    opacity: 0;
    border-radius: 0.25rem;
    background-color: #fafaf9;
    transition: 0.1s ease;
  }
  
  &:hover::before {
    opacity: 1;
    transform: scale(1);
    transition: 0.2s ease;
  }
  
  &:hover .menu-items-header::after {
    opacity: 1;
    margin-left: 0.625rem;
    transition: 0.2s ease;
  }
}

.menu-items-icon {
  width: 1.5rem;
  display: flex;
  justify-content: center;
  color: #a7afb6;
  
  &.red {
    color: #ff5263;
  }
  &.green {
    color: #00bf9c;
  }
  &.purple {
    color: #938ce1;
  }
  &.blue {
    color: #4573d2;
  }
  &.yellow {
    color: #fcbd01;
  }
}

.menu-items-info {
  flex: 1 1 0;
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

.menu-items-header {
  display: block;
  position: relative;
  font-size: 14px;
  font-weight: 600;
  line-height: 1.5rem;
  color: #151b26;
  
  &::after {
    content: '›';
    position: absolute;
    transform: translateY(-50%) scale(1.7);
    font-weight: 400;
    color: #a7afb6;
    top: 41%;
    margin-left: 1rem;
    opacity: 0;
    transition: 0.1s ease;
  }
}

.menu-items-text {
  font-size: 12px;
  font-weight: 400;
  line-height: 1.6;
  color: #222b37;
}
  .menu-items-header a {
  color: inherit; /* ใช้สีเดียวกับ parent */
  text-decoration: none; /* ไม่ต้องมีขีดเส้นใต้ */
}  
    </style> 
     
<!-- Icons -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Rounded:opsz,wght,FILL,GRAD@40,300,0,0" />

  
     
     
          
          
          
                    <div class="row">
                        <div class="col-lg-12 col-12">
                        
                                          <div class="card" >
                                <div class="card-header">
    <h3 ><spring:message code="menu.worksystem" /> : ${workflows.workflow_name} 
</h3>
</div>
</div>
                    
                        
                          <div class="card" >         
                                        
<!-- Content -->
<div class="page-container">
  <section class="menu-section single-menu-section">
    <div class="menu-container feature-menu-container">
      <div class="menu-items-container"></div>
    </div>  
  </section>

 
</div>
      
 <script>    
const featureMenuItemContainer = document.querySelector('.feature-menu-container .menu-items-container')


const featureMenuItems = [
  
	{
	    icon: 'app_registration',
	    iconColor: 'red',
	    href: '/integrationsapp-workflow/${id}',
	    title: 'Create program',
	    text: '<spring:message code="app.processes" />',
	  },
	{
    icon: 'rebase_edit',
    iconColor: 'green',
    href: '/setworkflowapp-workflow/${id}',
    title: 'Workflow Setting',
    text: '<spring:message code="app.workflow" />',
  },
  
  {
	    icon: 'article',
	    iconColor: 'red',
	    title: 'Task Definition',
	    href: '/setformsworkflowapp-workflow/${id}',
	    text: '<spring:message code="app.eform" />',
	  },
	  
	  {
		    icon: 'account_tree',
		    iconColor: 'purple',
		    title: 'Link & Calculation',
		    href: '/setlink-workflow/${id}',
		    text: '<spring:message code="app.eform" />',
		  },
	  
	
	  {
		    icon: 'analytics',
		    iconColor: 'purple',
		    title: 'Access',
		    href: '/workload-workflow/${id}',
		    text: '<spring:message code="app.workload" />',
		  },
		  {
			    icon: 'view_object_track',
			    iconColor: 'green',
			    title: 'Index Scope',
			    href: '/indexscope-workflow/${id}',
			    text: 'Sync your work in real-time to all your devices',
			  },
			  
			  {
				    icon: 'table_chart',
				    iconColor: 'yellow',
				    title: 'Management Scope',
				    href: '/Managementscope-workflow/${id}',
				    text: '<spring:message code="app.reporting" />',
				  },
			  
		  {
			    icon: 'fact_check',
			    iconColor: 'green',
			    href: '/goals-workflow/${id}',
			    title: 'Goals',
			    text: '<spring:message code="app.goals" />',
			  },
		
  {
    icon: 'timeline',
    iconColor: 'purple',
    title: 'Log',
    href: '/logapp-workflow/${id}',
    text: '<spring:message code="app.timeline" />',
  },
  
  {
	    icon: 'monitoring',
	    iconColor: 'yellow',
	    title: 'Reporting',
	    href: '/monitoringapp-workflow/${id}',
	    text: '<spring:message code="app.reporting" />',
	  },
  {
    icon: 'table_chart',
    iconColor: 'yellow',
    href: '/dashboard-workflow/${id}',
    title: 'KPI & Measurement Rules',
    text: '<spring:message code="app.dashboard" />',
  },

  {
	    icon: 'settings',
	    iconColor: 'yellow',
	    href: '/settingapp-workflow/${id}',
	    title: 'Setting',
	    text: '<spring:message code="app.setting" />',
},	  




  
  

]

const createMenuItem = (itemInfo) => {
  // Create menu item
  const menuItem = document.createElement('div')
  menuItem.classList.add('menu-items')
  
  // Add icon
  if (itemInfo.icon) {
    // Add icon container
    const iconContainer = document.createElement('div')
    iconContainer.classList.add('menu-items-icon', itemInfo.iconColor)
    menuItem.appendChild(iconContainer)

    // Add icon to icon container
    const icon = document.createElement('span')
    icon.classList.add('material-symbols-rounded')
    icon.innerText = itemInfo.icon
    iconContainer.appendChild(icon)
  }
  
  // Add info container
  const infoContainer = document.createElement('div')
  infoContainer.classList.add('menu-items-info')
  menuItem.appendChild(infoContainer)
  
  // Add info header
const infoHeader = document.createElement('h4')
infoHeader.classList.add('menu-items-header')

if (itemInfo.href) {
  const link = document.createElement('a')
  link.href = itemInfo.href
  link.innerText = itemInfo.title
  infoHeader.appendChild(link)
} else {
  infoHeader.innerText = itemInfo.title
}

infoContainer.appendChild(infoHeader)

  if (itemInfo.text) {
    // Add info text
    const infoText = document.createElement('p')
    infoText.classList.add('menu-items-text')
    infoText.innerText = itemInfo.text
    infoContainer.appendChild(infoText)
  }

  return menuItem
}

const renderMenuItems = () => {
  featureMenuItems.forEach((itemInfo) => {
    featureMenuItemContainer.appendChild(createMenuItem(itemInfo))
  })
 
}

renderMenuItems()
     
    </script>		
                        </div>
                        
                        
                            <div class="card" style="display:none">
                                <div class="card-header">
    <h3 ><spring:message code="menu.worksystem" /> : ${workflows.workflow_name}  [${workflows.workflow_app}]  
    
 
        </h3>
        
            
                               
            
            
                               
                               
                               
                             
                                          
                                </div>
                                
                                
                         

		
			
			      <div class="card-header">
                        <h3 >Table Connect:</h3> 
                    </div>      
                       
<div class="md-stepper-horizontal orange" id="step" >
  
 <div style="padding:20px;">

 <div id="box"> 
                              
<a class="btn btn-primary" style="margin:10px"  data-bs-dismiss="modal" aria-label="Close" data-bs-toggle="modal" data-bs-target="#addNew">Add</a>
 
         <c:if test="${error  > 0}">     
           <br>       
                                           <div class="alert text-white bg-danger d-flex align-items-center justify-content-between" role="alert" style="padding:20px;margin:10px">
                                       <div class="alert-text">Error <b>Major</b> <spring:message code="error.TableConnect" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if> 
                        
                  
                        
                 <c:if test="${notice  > 0}">     
           <br>       
                                           <div class="alert text-white bg-warning d-flex align-items-center justify-content-between" role="alert" style="padding:20px;margin:10px">
                                       <div class="alert-text">Notice <spring:message code="error.activatedEform" /></div>
                                       <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                    </div>
                        
                        <br>
                        </c:if>                                                           
<table class="table">
                    <tr>
                    <th>#</th>
                    <th style="width:40%">Name</th>
                    	<th>TYPE</th>
					<th>KEY</th>
					<th>Action</th>
					</tr>
                     
                   <c:forEach items="${flow }" var="flows" varStatus="count">  
                         <tr>
	                        <td>${count.count}</td>
	                        <td style="text-align: left;">${flows.condition_title}</td>
	                 
	                                <td style="text-align: left;"> ${flows.datalink_name} </td> 
	                          <td style="text-align: left;">
	                          <select value="key1" name="key1" style="width:90%">
	              
	                      <c:forEach items="${flows.eformfields }" var="fields" varStatus="count">   
	                      
	                     
	                          <option  value="${fields.field_id }"  <c:if test="${fields.field_id == flows.key1 }">selected</c:if>>${fields.field_label } 
	                          </option>
	                      </c:forEach>
	                          </select>
	                          </td>
	                    
	         
	                        
	                        
	                        <td>
	                       
	                        
	                        <a class="btn btn-sm btn-danger" href="/deldatalink2-workflow/${flows.condition_id}/${workflows.workflow_id}" onClick="confirm('Delete ?')">ลบออก</a>
	                        
	                        
	                        </td>
                        </tr>
                        
                        
                        
                   </c:forEach>  
</table>
 


</div>
</div>
</div>            					                                  
</div>
</div>
</div>
                  
                 
                 
                 
                <!--/ add new card modal  -->
                   
  
              
                    
                 
                 
            
            
     
                </section>

            </div>
        </div>
    </div>
 
 
  
 
 
 
  <div class="modal fade" id="addNew" tabindex="-1" aria-labelledby="exampleModalScrollableTitle"  aria-modal="true" >
                                                <div class="modal-dialog modal-lg modal-dialog-scrollable">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalScrollableTitle"><spring:message code="forms.AddForm" /></h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        
                                                                        <form method="POST"  modelAttribute="datalink" action="/addsublink2-workflow">
                                                        <div class="modal-body">
                     
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 <input  type="hidden" name="datalink_workflow" value="${workflows.workflow_id }">
                  
         
                                 
                             <div class="col-md-12">
                                                    <label class="form-label"><spring:message code="forms.Name" />:</label>
                                       <select class="form-select"  name="datalink_idrefer" id="datalink_idrefer" >
                     
                                 <c:forEach items="${conditionadd }" var="row" varStatus="count">
                                        <option value="${row.condition_id}">${row.condition_title }</option>
                                 </c:forEach>   
                                        </select> 
                                 
                                              <div class="col-md-12">
                                                              <label class="form-label"><spring:message code="forms.type" />:</label>
                                       <select class="form-select"  name="datalink_name" id="datalink_name" >
                     
                           
                                 		 <option value="-">-</option>
                    		  
                            	
                                 	 <optgroup label="<spring:message code="forms.type2" />">	 
                                    	
                                        		 <option value="purchase_requisition">Purchase requisition ***</option>
                                                 <option value="purchase_order">Purchase order *** </option>
                                                 	<option value="goods_receipt">Goods receipt</option>
                                       </optgroup>            
                                             
                                                         <optgroup label="<spring:message code="forms.type4" />">	 
                                                 <option value="requisition_form">Requisition form *** </option>
                                                     </optgroup>  
                                                     	 <optgroup label="<spring:message code="forms.type3" />">	        
                                                <option value="petty_cash_book">Petty cash book *** </option>
                                                        </optgroup> 
                                                        <optgroup label="<spring:message code="forms.type5" />">	
                                                             	 
                                                    <option value="inventory_registration">Inventory Registration form *** </option>            	         
                                                    <option value="stock_requisition">Stock Requisition *** </option>
                                                    <option value="direct_goods_receipt">Direct goods Receipt***</option>
                                                        </optgroup> 
                                           
                                           
                                           
                                                         
                                        </select> 
                                        
  											 </div>
               
               
               
                           <br>
            
                                          <br>
                                          <br>
            
                                          <br>  <br>
            
                                          <br>  <br>
            
                                          <br>  
                                                 
                                       <br>   <br>   <br>  
                                    
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" 
                                                class="btn btn-primary me-1 waves-effect waves-float waves-light">Save</button>
                                                        </div>
                                              	</form>             
                                                        
                                                    </div>
                                                </div>
                                            </div> 
                       
 
 
   *** Requested
 
 
 
 
 
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



