<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    

  
 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 




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
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${id}">${workflows.workflow_name}</a>
                                    </li>
                                            <li class="breadcrumb-item"><a href="/monitoringapp-workflow/${id}">Reports</a>
                                    </li>
                                          <li class="breadcrumb-item"><a href="/trackingapp-workflow/${id}">Tracking</a>
                                    </li>
                                    <li class="breadcrumb-item active">Setting
                                    </li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
           
            </div> 

     
     
<div class="row">
                                     <form:form method="POST" modelAttribute="user" action="/settingtrackingapp-workflow">
          <div class="col-md-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><i data-feather='settings'></i> Main Process Tracking</h4>
                                </div>
                                <div class="card-body">

                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="workflows.DataLink" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                               
                                 <select class="form-select select2" id="tracking_main_step" name="tracking_main_step" multiple="multiple">
										    <option value="0">-</option>
										    
										    <!-- วนลูปตามลำดับใน work.tracking_step สำหรับตัวเลือกที่ถูกเลือก -->
										    <c:forEach items="${workflows.tracking_main_step}" var="selected_id">
										        <c:forEach items="${process}" var="row">
										            <c:if test="${row.id == selected_id}">
										                <option value="${row.id}" selected>
										                    ${row.name}
										                </option>
										            </c:if>
										        </c:forEach>
										    </c:forEach>
										    
										    <!-- เพิ่มตัวเลือกที่ไม่ได้เลือก -->
										    <c:forEach items="${process}" var="row">
										        <c:if test="${!workflows.tracking_main_step.contains(row.name)}">
										            <option value="${row.id}">
										            ${row.name}
										            </option>
										        </c:if>
										    </c:forEach>
										</select>
                
                                                    </div>
                                                </div>
                                            </div>
                                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="workflows.display"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select class="form-select" id="tracking_main_type" name="tracking_main_type" >
                                    	 <option value="last"   <c:if test="${workflows.tracking_main_type == 'Last' }">selected</c:if>><spring:message code="workflows.last" /></option>
                                         <option value="Max"  <c:if test="${workflows.tracking_main_type == 'Max' }">selected</c:if>><spring:message code="workflows.max" /></option>
                                            <option value="Important"  <c:if test="${workflows.tracking_main_type == 'Important' }">selected</c:if>><spring:message code="workflows.Important" /></option>
                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                          <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="workflows.publication" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                         <select class="form-select" id="tracking_main_status" name="tracking_main_status" >
                                 
                                         <option value="0"  <c:if test="${workflows.tracking_main_status == '0' }">selected</c:if>><spring:message code="workflows.private" /></option>
                                         <option value="1"   <c:if test="${workflows.tracking_main_status == '1' }">selected</c:if>><spring:message code="workflows.public" /></option>
                                     </select>
                               
                                             
                                             
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            
                                    
                                                     
                                            
                                            
                                            
                             
                 
                                 
                                     
                                            <div class="col-sm-9 offset-sm-3">
                         
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"><spring:message code="btn.save" /></button>
                                          
                                            </div>
                  
                                        </div>
                                        
                                        
                        
                                         
                                          
                              
                                </div>
                            </div>
                            
                  
                           
                    
                            
                        </div>
                  
                        <div class="col-md-8 col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><i data-feather='settings'></i> Process Tracking</h4>
                                </div>
                                <div class="card-body">
                             
                                        <div class="row">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="workflows.DataLink" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                               
                                 <select class="form-select select2" id="tracking_step" name="tracking_step" multiple="multiple">
										    <option value="0">-</option>
										    
										    <!-- วนลูปตามลำดับใน work.tracking_step สำหรับตัวเลือกที่ถูกเลือก -->
										    <c:forEach items="${workflows.tracking_step}" var="selected_id">
										        <c:forEach items="${Process}" var="row">
										            <c:if test="${row.dataset_data_title == selected_id}">
										                <option value="${row.dataset_data_title}" selected>
										                    ${row.dataset_data_title}
										                </option>
										            </c:if>
										        </c:forEach>
										    </c:forEach>
										    
										    <!-- เพิ่มตัวเลือกที่ไม่ได้เลือก -->
										    <c:forEach items="${Process}" var="row">
										        <c:if test="${!workflows.tracking_step.contains(row.dataset_data_title)}">
										            <option value="${row.dataset_data_title}">
										                ${row.dataset_data_title}
										            </option>
										        </c:if>
										    </c:forEach>
										</select>
                
                                                    </div>
                                                </div>
                                            </div>
                                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="workflows.display"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                     <select class="form-select" id="tracking_type" name="tracking_type" >
                                    	 <option value="last"   <c:if test="${workflows.tracking_type == 'Last' }">selected</c:if>><spring:message code="workflows.last" /></option>
                                         <option value="Max"  <c:if test="${workflows.tracking_type == 'Max' }">selected</c:if>><spring:message code="workflows.max" /></option>
                                            <option value="Important"  <c:if test="${workflows.tracking_type == 'Important' }">selected</c:if>><spring:message code="workflows.Important" /></option>
                                     </select>
                                     
                                     
                                                    </div>
                                                </div>
                                            </div> 
                                          <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="workflows.publication" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                                         <select class="form-select" id="tracking_status" name="tracking_status" >
                                 
                                         <option value="0"  <c:if test="${workflows.tracking_status == '0' }">selected</c:if>><spring:message code="workflows.private" /></option>
                                         <option value="1"   <c:if test="${workflows.tracking_status == '1' }">selected</c:if>><spring:message code="workflows.public" /></option>
                                     </select>
                               
                                             
                                             
                                                    </div>
                                                </div>
                                            </div>
                                            
                                            
                                                <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="process.name" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                       <select class="form-select select2"  name="tracking_process_type" id="tracking_process_type" >
								                               <option value="0">-</option>
								                               <c:forEach items="${process }" var="row" varStatus="count">
								                                        <option value="${row.id}" <c:if test="${workflows.tracking_process_type == row.id }">selected</c:if>>${row.name }</option>
								                                 </c:forEach>   
								       </select> 
                                             
                                             
                                                    </div>
                                                </div>
                                            </div>
                                            
                                                     
                                            
                                            
                                            
                             
                 
                                 
                                     
                                            <div class="col-sm-9 offset-sm-3">
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }"> 
                          
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"><spring:message code="btn.save" /></button>
                                          
                                            </div>
                  
                                        </div>
                                        
                                        
                        
                                         
                               
                                </div>
                            </div>
                            
                  
                           
                    
                            
                        </div>
                  
                        </form:form>
            
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
<script>
$(document).ready(function() {
    // เริ่มต้น Select2
    $('#tracking_step').select2();

    // อาร์เรย์สำหรับเก็บลำดับที่เลือก
    let selectedOrder = [];

    // เมื่อเลือกตัวเลือกใหม่
    $('#tracking_step').on('select2:select', function(e) {
        let selectedValue = e.params.data.id;
        // เพิ่มค่าใหม่ลงในอาร์เรย์ตามลำดับที่เลือก
        if (selectedValue !== '0') { // ข้ามค่า default ถ้าไม่ต้องการ
            selectedOrder.push(selectedValue);
        }
        updateSelectOptions();
    });

    // เมื่อยกเลิกการเลือก
    $('#tracking_step').on('select2:unselect', function(e) {
        let unselectedValue = e.params.data.id;
        // ลบค่าที่ถูกยกเลิกออกจากอาร์เรย์
        selectedOrder = selectedOrder.filter(value => value !== unselectedValue);
        updateSelectOptions();
    });

    // ฟังก์ชันสำหรับอัปเดต <option> ใน <select>
    function updateSelectOptions() {
        let $select = $('#tracking_step');
        let options = $select.find('option').get();

        // แยกตัวเลือกที่เลือกและไม่ได้เลือก
        let selectedOptions = [];
        let unselectedOptions = [];

        options.forEach(opt => {
            if (selectedOrder.includes($(opt).val())) {
                selectedOptions.push(opt);
            } else {
                unselectedOptions.push(opt);
            }
        });

        // จัดเรียงตัวเลือกที่เลือกตามลำดับใน selectedOrder
        selectedOptions.sort((a, b) => {
            return selectedOrder.indexOf($(a).val()) - selectedOrder.indexOf($(b).val());
        });

        // ล้าง <select> และเพิ่ม <option> ใหม่
        $select.empty();
        selectedOptions.forEach(opt => $select.append(opt));
        unselectedOptions.forEach(opt => $select.append(opt));

        // อัปเดต Select2
        $select.trigger('change');
    }

    // เมื่อส่งฟอร์ม ตรวจสอบว่าค่าถูกส่งตามลำดับ
    $('form').on('submit', function() {
        let selectedValues = $('#tracking_step').val();
        console.log('Selected values in order:', selectedValues);
        // ค่าจะถูกส่งตามลำดับที่เลือก
    });
});
</script>



<script>
$(document).ready(function() {
    // เริ่มต้น Select2
    $('#tracking_main_step').select2();

    // อาร์เรย์สำหรับเก็บลำดับที่เลือก
    let selectedOrder = [];

    // เมื่อเลือกตัวเลือกใหม่
    $('#tracking_main_step').on('select2:select', function(e) {
        let selectedValue = e.params.data.id;
        // เพิ่มค่าใหม่ลงในอาร์เรย์ตามลำดับที่เลือก
        if (selectedValue !== '0') { // ข้ามค่า default ถ้าไม่ต้องการ
            selectedOrder.push(selectedValue);
        }
        updateSelectOptions();
    });

    // เมื่อยกเลิกการเลือก
    $('#tracking_main_step').on('select2:unselect', function(e) {
        let unselectedValue = e.params.data.id;
        // ลบค่าที่ถูกยกเลิกออกจากอาร์เรย์
        selectedOrder = selectedOrder.filter(value => value !== unselectedValue);
        updateSelectOptions();
    });

    // ฟังก์ชันสำหรับอัปเดต <option> ใน <select>
    function updateSelectOptions() {
        let $select = $('#tracking_main_step');
        let options = $select.find('option').get();

        // แยกตัวเลือกที่เลือกและไม่ได้เลือก
        let selectedOptions = [];
        let unselectedOptions = [];

        options.forEach(opt => {
            if (selectedOrder.includes($(opt).val())) {
                selectedOptions.push(opt);
            } else {
                unselectedOptions.push(opt);
            }
        });

        // จัดเรียงตัวเลือกที่เลือกตามลำดับใน selectedOrder
        selectedOptions.sort((a, b) => {
            return selectedOrder.indexOf($(a).val()) - selectedOrder.indexOf($(b).val());
        });

        // ล้าง <select> และเพิ่ม <option> ใหม่
        $select.empty();
        selectedOptions.forEach(opt => $select.append(opt));
        unselectedOptions.forEach(opt => $select.append(opt));

        // อัปเดต Select2
        $select.trigger('change');
    }

    // เมื่อส่งฟอร์ม ตรวจสอบว่าค่าถูกส่งตามลำดับ
    $('form').on('submit', function() {
        let selectedValues = $('#tracking_main_step').val();
        console.log('Selected values in order:', selectedValues);
        // ค่าจะถูกส่งตามลำดับที่เลือก
    });
});
</script>