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
<title>Policies</title>


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
 <c:set var="readonly" value="" /> 
  <c:set var="disabled" value="" /> 
<c:if test="${type == 'view'   }"> 
 <c:set var="readonly" value="readonly" /> 
  <c:set var="disabled" value="disabled" /> 
   <c:set var="readonly" value="" /> 
  <c:set var="disabled" value="" />
</c:if>	   




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
          
     
          <c:if test="${type != 'view'   }">                          
    <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            <h2 class="content-header-title float-start mb-0"><spring:message code="menu.worksystem" /></h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${workflows.workflow_id}">${workflows.workflow_name}</a>
                                    </li>
                                    <li class="breadcrumb-item active"><a href="/workload-workflow/${workflows.workflow_id}">Access </a>
                                    </li>
                                      <li class="breadcrumb-item active">Edit 
                                    </li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
           
            </div> 
</c:if>
          
          <c:if test="${type == 'view'   }">                          
    <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            <h2 class="content-header-title float-start mb-0"><spring:message code="app.Policies" /></h2>
                    
                        </div>
                    </div>
                </div>
           
            </div> 
</c:if>
     
     
<div class="row">



   <form:form method="POST" modelAttribute="policies" action="/editpolicies-workflow">
                                          
                                          
                                          <div class="card">
                                             <div class="card-body">     
                                               <c:if test="${type != 'view'   }">                             
                                                     <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                     <input type="hidden" class="form-select"  name="process" value="${sub_processes[0].policies.process }"> 
                                                     <input type="hidden" class="form-select"  name="group_id" value="${sub_processes[0].policies.group_id }"> 
                                                     <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }"> 
                                             </c:if>        
                                                     
                                                       
                                                            <div class="modal-body">
                                                              <ul class="nav nav-tabs" role="tablist">
                  
                 
                  												   <li class="nav-item ">
						                                            <a class="nav-link active" id="homeIcon-tab${count.count}0" data-bs-toggle="tab" href="#homeIconapp${count.count}0" aria-controls="home" role="tab" aria-selected="true">
						                                    <spring:message code="app.Policies" /> </a>
						                                       
						                                        </li>
                 
                 
                  <c:forEach items="${sub_processes }" var="rowed" varStatus="tabcounted"> 
                   
                   <c:set var="active" value="" />  
	                   <c:if test='${tabcounted.count == 1}'>   
	                   <c:set var="active" value="active" />  
                   </c:if>
                  
		           
		                                    
									<c:set var="steps" value="${rowed.description}" />
						           <c:if test="${rowed.description == '' }"> <c:set var="steps" value="${rowed.step}" /></c:if>
						                                       
						                                       
						                                        <li class="nav-item ">
						                                            <a class="nav-link" id="homeIcon-tab${count.count}${tabcounted.count }" data-bs-toggle="tab" href="#homeIconapp${count.count}${tabcounted.count }" aria-controls="home" role="tab" aria-selected="true">
						                                 ${tabcounted.count }.${steps } </a>
						                                       
						                                        </li>
		           
                </c:forEach>
       
                                      
                                 
                                        
                                    </ul>
                                    
                                         <div class="tab-content">
                                         
                                              <div class="tab-pane active" id="homeIconapp${count.count}0" aria-labelledby="homeIcon-tab" role="tabpanel">
                                             <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-1">
						                                                        <label class="col-form-label" for="first-name">
						                                                         <spring:message code="item.Policies" />
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-9">
						                                      <input type="text" class="form-select"  name="name" value="${sub_processes[0].policies.name }"  ${readonly}>  
						                                                    </div>
						                                                </div>
						                      					  </div>
						                      					  </div>
                               </div>
                                         
                                         
                                    <c:forEach items="${sub_processes }" var="rowed" varStatus="tabcounted"> 
                   
                   
                          <input type="hidden" class="form-select"  name="sub_processes_string" value="${rowed.id }"> 
                   <c:set var="active" value="" />  
	                   <c:if test='${tabcounted.count == 1}'>   
	                   <c:set var="active" value="active" />  
                   </c:if>
                  
		                                              
                                    
                                    
                               
                           
                               
                               
                                        <div class="tab-pane " id="homeIconapp${count.count}${tabcounted.count }" aria-labelledby="homeIcon-tab" role="tabpanel">
          
           		<c:set var="listed" value="Record,Forward,Receive" />
		         <c:set var="listchecked" value="${rowed.step}" />  
		                 <c:if test='${!fn:contains(listed,listchecked)}'>  
		                 <c:if test='${ rowed.step == "Approval" || rowed.step == "Review"   }'>     
                                                             <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-2 row">
						                                                    <div class="col-sm-3">
						                                                        <label class="col-form-label" for="first-name">
						                                                         <spring:message code="item.responsibleperson" />
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-9">
						                                       <select class="form-select select2"   name="respon" ${disabled}>
						                                   
						                                   		   	<option value="Creator"
		        														   		<c:if test='${rowed.policies.respon == "Creator" }'>   
						                                        						selected
						                                        					</c:if>
		        														   	>Creator</option>
						                                   
						                                      <c:forEach items="${userall}" var="userss" varStatus="tabcounted"> 
						                                          <c:set var="responlist" value="user_${userss.user_id }" />
						                                      
						                                      
						                                        			<option value="user_${userss.user_id}"
						                                        			<c:if test='${rowed.policies.respon == responlist}'>   
						                                        			selected
						                                        			</c:if>
						                                        			
						                                        			
						                                        			
						                                        			>${userss.user_name }</option>
						                                        	</c:forEach>
						                                      </select>
						                                                    </div>
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      					  
						                      			 <c:if test='${ rowed.step != "Approval"}'>    
						                      			   <input type="hidden" class="form-select" step="0.01"  name="max_approval_amount" value="0"  ${readonly}>  
						                      			    </c:if>  			  
						                      		 <c:if test='${ rowed.step == "Approval"}'>     			  
   														 <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-2 row">
						                                                    <div class="col-sm-3">
						                                                        <label class="col-form-label" for="first-name">
						                                                         <spring:message code="item.max_approval_amount" />
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-9">
						                                      <input type="hidden" class="form-select" step="0.01"  name="max_approval_amount" value="${rowed.policies.max_approval_amount }"  ${readonly} required>  
						                                                    </div>
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      			   </c:if>  		  
						                      					  
						             </c:if>           					  
						                      					  
						               <c:if test='${ rowed.step != "Review" &&  rowed.step != "Approval"    }'>     
                                                             <div class="row">
						                                            <div class="col-12">
						                                                <div class="mb-2 row">
						                                                    <div class="col-sm-3">
						                                                        <label class="col-form-label" for="first-name">
						                                                         <spring:message code="item.responsibleperson" />
						                                                         
						                                                           <c:if test='${  rowed.step == "Completion"  }'>     
						                                                                         <spring:message code="item.responsiblepersonReceivedocuments" />
						                                                        
						                                                           </c:if>
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-9">
						        
						                                      <select class="form-select select2s"  data-custom="action${rows.id}${rowed.id}"  multiple="multiple"  ${disabled}>
								                                       
								                                       
								                                          <c:set var="list" value="${rowed.policies.respon }" />
		                  													<c:set var="listcheck" value="Creator" />  
		                  												  	<option value="Creator"
		        														   		<c:if test='${fn:contains(list,listcheck)}'>   
						                                        						selected
						                                        					</c:if>
		        														   	>Creator</option>
		        														   	
		        														   	
		        														   	
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
						                                       <input type="hidden" class="form-select" step="0.01"  name="max_approval_amount" value="0" >  	
						                                 
						                                     <input type="hidden" name="respon" class="hiddenInput" value="${fn:replace(rowed.policies.respon, ',', '|')}"  data-custom="action${rows.id}${rowed.id}">
						                                                    </div>
						                                                </div>
						                      					  </div>
						                      					  </div>
						                      					  					                      					  
<script>
    $(document).ready(function () {
        $(".select2s").select2({
            tags: true
        }).on("change", function () {
            let selectedValues = $(this).val();
            let customId = $(this).data("custom"); // Get data-custom

            // Find matching hidden input and update it
            $('input[name="respon"][data-custom="' + customId + '"]')
                .val(selectedValues ? selectedValues.join("|") : "");
        });
    });
</script>			            					  
			                    </c:if>  </c:if>	
			                    
			                    
<c:if test='${fn:contains(listed,listchecked)}'>  
 <input type="hidden" class="form-select" step="0.01"  name="max_approval_amount" value="0" >  		
<input type="hidden" name="respon" class="hiddenInput" value="0"  >   					         
</c:if>          
							        
						        
						                 					  
                                   </div>
                                              
                </c:forEach>     
                                  
                                       
                                    </div>
                                       </div> 
                                                          
                                                        
                                                      
                                                      
       <br>   <br>     <br>                                                       
<div class="modal-footer" style="display: inline-block;width:100%">
  <c:if test="${type != 'view'   }"> 
        <button type="Submit" class="btn btn-primary waves-effect waves-float waves-light" data-bs-dismiss="modal"> <spring:message code="btn.edit" /></button>
  </c:if>
</div>
                                                </form:form>





                        
                   </div>
          
          

                  
          
                 
                 
            
            
     
                </section>

            </div>
        </div>
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



