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
<title>Goals</title>


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
                            <h2 class="content-header-title float-start mb-0">KPI & Measurement Rules</h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                    
                                    <li class="breadcrumb-item active">Measurement Rules
                                    </li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
           
            </div> 

     
     
<div class="row">
<div class="card business-card">

       <div class="card-header pb-1">
                                <h4 class="card-title">Rules</h4>
                          
                          
                  
                                <button type="button" class="btn btn-primary me-1 waves-effect waves-float waves-light" data-bs-toggle="modal" data-bs-target="#Integrations" 
                                onclick="setID('${rows.id}','${rows.table}')"
                                >  <spring:message code="btn.add" /></button>
                  

                            
                           </div>



         
       
                            <div class="card-body">
                        
                                <div class="business-items">
<table class="table">
                   <tr>
                   <th >Rules</th>
                   <th>Action </th>
                   </tr>    
                             <c:forEach items="${Measurement_rules }" var="row" >
                      
                                   <tr>
                   <td> ${row.title } </td>
                   <td style="width:10%">   
                   <a href="/editMeasurementrules-workflow/${row.id }" type="button" class="btn  btn-sm btn-light me-1 waves-effect waves-float waves-light" >
                                                  <spring:message code="btn.edit" /></a>  
                      </td>
                   </tr>    
                                
                              	</c:forEach>	  
                             
   </table>                          
                                     
                                </div>
                              
                                <div >
                                  
                                      
                                             
                                </div>
                      
                                
                            </div>
                     
                            
                        </div>
        </div>        
                        
                        
                	 





     <div class="modal fade" id="Integrations" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true" >
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered" style="min-width:1000px;">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                              <h4 class="modal-title" id="myModalLabel33">Rules</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                  
                                                       <form:form method="POST" modelAttribute="Measurement_rules" action="/saveMeasurement_rules-workflow">
                                               
                                                     <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                     
                                              
                                                    <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }"> 
                                               

                                                            <c:if test='${goal.id > 0}'>   
	                 											  <input type="text"   name="id" value="${goal.id}"> 
               											    </c:if>
                  
                                                            <div class="modal-body">
                                                            
                                     
                                                 <div class="row">
						                                              <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-3">
						                                                        <label class="col-form-label" for="first-name">
						                                                         <spring:message code="goals.name" />Title
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-9">
						                                      <input type="text" class="form-control"  name="title" value="" >  
						                                                    </div>
						                                                </div>
						                      	  </div> 	
						                      	  </div>   
						                      	  


						                      	         
						                      	  
						                      	  
						                      	  
						                          
                                                                   <div class="row">
						                                              <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-3">
						                                                        <label class="col-form-label" for="first-name">
						                                                   this_process
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-9">
						                                    <select class="form-select"  name="this_process" id="this_process" >  
						                                    <c:forEach items="${process }" var="row" >
						                                        <option value="${row.id}">${row.name}</option>
						                                        </c:forEach>
						                                        </select>
						                                                    </div>
						                                                </div>
						                      	  </div> 	
						                      	  </div>        
						                      	  
						                      	  
						                      	   <div class="row">
						                                              <div class="col-12">
						                                                <div class="mb-1 row">
						                                                    <div class="col-sm-3">
						                                                        <label class="col-form-label" for="first-name">
						                                                        destination_process
						                                                         </label>
						                                                    </div>
						                                                    <div class="col-sm-9">
						                                    <select class="form-select"  name="destination_process" id="destination_process" >  
						                                       <c:forEach items="${process }" var="row" >
						                                   <option value="${row.id}">${row.name}</option>
						                                        </c:forEach>
						                                        </select>
						                                                    </div>
						                                                </div>
						                      	  </div> 	
						                      	  </div>    
						                      	  
                   

                                                
                                                    
                                                
                                                               
                                                            
                                                            
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
          
          

                  
          
                 
                 
            
            
     
                </section>

            </div>
        </div>
    </div>
 
 
  
 
 <script>
 function  setID(ID,table){
  	 
  	 $("#goalprocesses_id").val(ID);
  	 $("#goaltable").val(table);
   }
 
function updateSettoOptions() {
    var frequency = document.getElementById('frequency').value;
    var settoContainer = document.getElementById('setto-container');

    // Clear previous content
    settoContainer.innerHTML = "";


    // Create element for new input
    var html = "";

    if (frequency === "EVERY_MONTH") {
        html += "<select class='form-select' name='setto'>";
        for (var i = 1; i <= 31; i++) {
            html += "<option value='" + i + "'>วันที่ " + i + "</option>";
        }
        html += "<option value='LAST'>วันสุดท้ายของเดือน</option>";
        html += "</select>";
    }

    if (frequency === "EVERY_WEEK") {
        html += "<select class='form-select' name='setto'>";
        var days = [
            ["MONDAY", "จันทร์"],
            ["TUESDAY", "อังคาร"],
            ["WEDNESDAY", "พุธ"],
            ["THURSDAY", "พฤหัสบดี"],
            ["FRIDAY", "ศุกร์"],
            ["SATURDAY", "เสาร์"],
            ["SUNDAY", "อาทิตย์"]
        ];
        for (var i = 0; i < days.length; i++) {
            html += "<option value='" + days[i][0] + "'>" + days[i][1] + "</option>";
        }
        html += "</select>";
    }

    if (frequency === "EVERY_YEAR") {
        html += "<input type='text' class='form-control' name='setto' placeholder='กรุณากรอกเป็น 01-02 (dd-MM)' />";
    }

    // For EVERY_DAY or IRREGULAR → don't show input
    if (frequency === "EVERY_DAY" || frequency === "IRREGULAR") {
    	 html += "<input type='text' class='form-control' name='setto'  readonly />";
    }

    
    settoContainer.innerHTML = html;
}

document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("frequency").addEventListener("change", updateSettoOptions);
    updateSettoOptions(); // โหลดตอนเริ่มต้น
});
</script>
 
 <script>
  $(document).ready(function() {
    $('#file_tag').select2({
      tags: true, // เปิดฟังก์ชันการพิมพ์ tag ใหม่
      placeholder: "พิมพ์ tag ที่ต้องการ", // ข้อความแนะนำเมื่อไม่มีการเลือก
      allowClear: true // เพิ่มปุ่มเคลียร์
    });
  });
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



