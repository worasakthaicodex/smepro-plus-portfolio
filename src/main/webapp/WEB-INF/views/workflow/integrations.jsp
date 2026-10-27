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
<title>Integrations</title>


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



aside img {

width: 50px;

}

aside {
  position: absolute;
  padding: 18px;
  text-align: center;
  padding-top: 25px;
  width: 1000px; /* ปรับตามต้องการ */


  display: flex;
  flex-wrap: wrap; /* 🔥 ให้ตัดขึ้นบรรทัดใหม่อัตโนมัติ */
  justify-content: center; /* จัดให้อยู่กลางแนวนอน */
  gap: 15px; /* เว้นช่องระหว่างแต่ละอัน */

}

aside p {

font-size: 12px;

}

aside a{
color:black;
}

aside .apps:hover {
cursor: pointer;
background: rgba(97, 97, 97, 0.25);
border: 1px solid rgba(97, 97, 97, 0.5);
color:black;
}

aside .apps {
  width: 100px;
  text-align: center;
  padding: 4px 5px 0px 5px;
  cursor: context-menu;
  border: 1px solid rgba(238, 238, 238, 0);
  border-radius: 4px;
  max-height: 85px;
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
                                    <li class="breadcrumb-item active"><spring:message code="app.create_a_program" />
                                    </li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
           
            </div> 

     
     
<div class="row">





<div class="modal fade" id="Integrations" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                              <h4 class="modal-title" id="myModalLabel33"> <spring:message code="app.create_a_program" /></h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                  
                                                       <form:form method="POST" modelAttribute="process" action="/saveprocess-workflow">
                                               
                                                            <div class="modal-body">
                                                                <label><spring:message code="process.name" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="text" name="name"  id="name" class="form-control">
                                                                </div>

                                                                <label><spring:message code="process.description" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="text"  name="description" id="description"    class="form-control">
                                                                </div>
                                                                   <label><spring:message code="process.relatedpeople" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="text"  name="relatedpeople"  id="relatedpeople"    class="form-control">
                                                                </div>
                                                              
                                                                       
                                                                     <label>ประเภทเเอฟ: </label>
                                                                <div class="mb-1">
                                                                     <select class="form-select"  name="program_type" id="program_type" >
                                                                       <option value="0">WorkFlow program</option>
                                                                        <option value="1">Management System</option>
                                                                          <option value="2">Index program</option>
                                                                        <option value="3">Report program</option>
                                                                     </select>
                                                                </div>        
                                                                       
                                                                       
                                                              <label><spring:message code="forms.join" />: </label>
                                                                <div class="mb-1">
								                                     <select class="form-select select2"  name="process_join" id="process_join" >
								                               <option value="0">-</option>
								                                 <optgroup label="${workflows.workflow_name}">
								                               <c:forEach items="${process }" var="rows" varStatus="count">
								                                        <option value="${rows.id}">${rows.name }</option>
								                                 </c:forEach>   
								                                   </optgroup>
								                                   
								                                   
								                                  <c:set var="previousNote" value="" />
																	<c:forEach items="${process2}" var="row" varStatus="count">
																	    <c:if test="${row.note != previousNote}">
																	        <c:if test="${not empty previousNote}">
																	            </optgroup>
																	        </c:if>
																	        <optgroup label="${row.note}">
																	        <c:set var="previousNote" value="${row.note}" />
																	    </c:if>
																	    
																	    <option value="${row.id}">${row.name}</option>
																	</c:forEach>
																	<c:if test="${not empty previousNote}">
																	    </optgroup>
																	    </c:if>
								                                        </select> 
                                                                </div>
                                                    
                                                              
                                                                
                                                                
                                                              <label><spring:message code="forms.select" />: </label>
                                                                <div class="mb-1">
								                                     <select class="form-select select2"  name="id_condition" id="id_condition" >
								                                 <c:forEach items="${conditionadd }" var="row" varStatus="count">
								                                        <option value="${row.condition_id}">${row.condition_title }</option>
								                                 </c:forEach>   
								                                        </select> 
                                                                </div>
                                                                                      
                                                            <label><spring:message code="forms.logo" />: </label>
<div class="mb-1">
  <select class="form-select" name="process_logo" id="process_logo" onchange="updateIconPreview(this)">
    <option value="">-- เลือกไอคอน --</option>
    <option value="business-plan.png" <c:if test="${'business-plan.png' == this_process.process_logo}">selected</c:if>>📋 Business Plan</option>
    <option value="cargo.png" <c:if test="${'cargo.png' == this_process.process_logo}">selected</c:if>>🚚 Cargo</option>
    <option value="icons8-believable-64.png" <c:if test="${'icons8-believable-64.png' == this_process.process_logo}">selected</c:if>>👍 Believable</option>
    <option value="icons8-bill-96.png" <c:if test="${'icons8-bill-96.png' == this_process.process_logo}">selected</c:if>>🧾 Bill</option>
    <option value="icons8-customer-96.png" <c:if test="${'icons8-customer-96.png' == this_process.process_logo}">selected</c:if>>👤 Customer 96</option>
    <option value="icons8-customer-100.png" <c:if test="${'icons8-customer-100.png' == this_process.process_logo}">selected</c:if>>👤 Customer 100</option>
    <option value="icons8-inspection-64.png" <c:if test="${'icons8-inspection-64.png' == this_process.process_logo}">selected</c:if>>🔍 Inspection 64</option>
    <option value="icons8-inspection-100.png" <c:if test="${'icons8-inspection-100.png' == this_process.process_logo}">selected</c:if>>🔍 Inspection 100</option>
    <option value="icons8-po-64.png" <c:if test="${'icons8-po-64.png' == this_process.process_logo}">selected</c:if>>📦 PO 64</option>
    <option value="icons8-po-100.png" <c:if test="${'icons8-po-100.png' == this_process.process_logo}">selected</c:if>>📦 PO 100</option>
    <option value="icons8-production-80.png" <c:if test="${'icons8-production-80.png' == this_process.process_logo}">selected</c:if>>🏭 Production 80</option>
    <option value="icons8-production-96.png" <c:if test="${'icons8-production-96.png' == this_process.process_logo}">selected</c:if>>🏭 Production 96</option>
    <option value="icons8-registration-100.png" <c:if test="${'icons8-registration-100.png' == this_process.process_logo}">selected</c:if>>📝 Registration</option>
    <option value="icons8-video-call-64.png" <c:if test="${'icons8-video-call-64.png' == this_process.process_logo}">selected</c:if>>📞 Video Call</option>
    <option value="manifest.png" <c:if test="${'manifest.png' == this_process.process_logo}">selected</c:if>>📃 Manifest</option>
    <option value="online-registration.png" <c:if test="${'online-registration.png' == this_process.process_logo}">selected</c:if>>🌐 Online Registration</option>
    <option value="order.png" <c:if test="${'order.png' == this_process.process_logo}">selected</c:if>>📦 Order</option>
    <option value="order-processing.png" <c:if test="${'order-processing.png' == this_process.process_logo}">selected</c:if>>🔄 Order Processing</option>
    <option value="planning.png" <c:if test="${'planning.png' == this_process.process_logo}">selected</c:if>>📅 Planning</option>
    <option value="sign-form.png" <c:if test="${'sign-form.png' == this_process.process_logo}">selected</c:if>>✍️ Sign Form</option>
    <option value="website.png" <c:if test="${'website.png' == this_process.process_logo}">selected</c:if>>🌐 Website</option>
    <option value="work-plan.png" <c:if test="${'work-plan.png' == this_process.process_logo}">selected</c:if>>🧠 Work Plan</option>
   <option value="no-insects.png" <c:if test="${'no-insects.png' == this_process.process_logo}">selected</c:if>> insects</option>
   <option value="lathe-machine.png" <c:if test="${'lathe-machine.png' == this_process.process_logo}">selected</c:if>> machine</option>
    <option value="directional-arrows.png" <c:if test="${'directional-arrows.png' == this_process.process_logo}">selected</c:if>> transfer</option>
     <option value="examination.png" <c:if test="${'examination.png' == this_process.process_logo}">selected</c:if>> examination</option>
    <!-- เพิ่มตามต้องการ -->
  </select>
</div>

<!-- Preview image -->
<div id="icon-preview" style="margin-top: 10px;">
  <img src="" id="icon-image" style="max-height: 80px; display: none;" />
</div>

<script>
function updateIconPreview(select) {
    const value = select.value;
    const img = document.getElementById("icon-image");
    if (value) {
        img.src = "/resources/app-assets/images/icons/APP/" + value;
        img.style.display = "block";
    } else {
        img.style.display = "none";
    }
}
</script>
          
                                                                               
                                     
                                                    <div class="mb-1">  
      												<input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                   					  <input type="hidden" class="form-select"  name="id_refer" value="${workflows.workflow_id }"> 
                  							       </div>
                  				     
                  							       
                  							       
                                                          
                                                            </div>
                                                      
                                                      
                                                      
                                                      
                                                            
                                                            
                                                            <div class="modal-footer" Style="display: inline-block;">
                                                                <button type="Submit"   class="btn btn-primary waves-effect waves-float waves-light" data-bs-dismiss="modal">เพิ่ม</button>
                                                            </div>
                                                </form:form>
                                                    </div>    
                               
                                        </div>
                                        </div>
                                        


<div class="modal fade" id="Integrations2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                              <h4 class="modal-title" id="myModalLabel33"> <spring:message code="app.create_a_program" /></h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                       <div class="modal-body">
       
<table>
<thead>
<tr>
<th style="width:40px">No.</th>
<th style="width:33%">ไทย (หัวข้อ / คำอธิบาย)</th>
<th style="width:33%">English (Title / Description)</th>
</tr>
</thead>
<tbody>
<tr>
<td>1</td>
<td><strong>Demand Planning</strong><br>การวางแผนความต้องการสินค้าล่วงหน้า (Forecast)<br><em>เพื่อคาดการณ์ยอดขายและเตรียมแผนผลิตให้สมดุลกับความต้องการตลาด</em></td>
<td><strong>Demand Planning</strong><br>Forecasting future product demand<br><em>To predict sales and prepare production plans to balance market demand</em></td>
</tr>


<tr>
<td>2</td>
<td><strong>Production Definition</strong><br>การนิยามโครงสร้างสินค้าที่ผลิต เช่น รหัสสินค้า, ประเภทการผลิต (Make to Stock / Make to Order)<br><em>เพื่อกำหนดข้อมูลพื้นฐานของสินค้าผลิตภัณฑ์ในระบบ</em></td>
<td><strong>Production Definition</strong><br>Define product structure (item code, production type: MTS/MTO)<br><em>To set basic product master data in the system</em></td>
</tr>


<tr>
<td>3</td>
<td><strong>Production Plan Control</strong><br>การสร้างและควบคุมแผนผลิตประจำเดือน/สัปดาห์/วัน<br><em>เพื่อให้การผลิตดำเนินไปตามแผนที่กำหนด และควบคุมปริมาณการผลิต</em></td>
<td><strong>Production Plan Control</strong><br>Create and control monthly/weekly/daily production plans<br><em>To ensure production follows plan and volume control</em></td>
</tr>


<tr>
<td>4</td>
<td><strong>Packing Definition</strong><br>การกำหนดรูปแบบการบรรจุ (เช่น 1 กล่อง = 10 ชิ้น, 1 พาเลท = 20 กล่อง)<br><em>เพื่อใช้ในการคำนวณวัตถุดิบ บรรจุภัณฑ์ และการวางแผนจัดส่ง</em></td>
<td><strong>Packing Definition</strong><br>Define packaging format (e.g. 1 box = 10 pcs, 1 pallet = 20 boxes)<br><em>Used for calculating packaging materials and shipping planning</em></td>
</tr>


<tr>
<td>5</td>
<td><strong>Pack Plan Control</strong><br>การควบคุมแผนการบรรจุหลังการผลิต เช่น ตารางบรรจุ, เครื่องบรรจุที่ใช้<br><em>เพื่อให้การบรรจุสอดคล้องกับปริมาณผลิตจริงและแผนจัดส่ง</em></td>
<td><strong>Pack Plan Control</strong><br>Control post-production packing plans (packing schedule, machines used)<br><em>To align packing with actual production and shipping plan</em></td>
</tr>


<tr>
<td>6</td>
<td><strong>Bill of Materials (BOM)</strong><br>รายการวัตถุดิบที่ใช้ผลิตสินค้าหนึ่งหน่วย<br><em>ใช้คำนวณปริมาณวัตถุดิบที่ต้องใช้ในแต่ละคำสั่งผลิต (Work Order)</em></td>
<td><strong>Bill of Materials (BOM)</strong><br>List of raw materials for one finished unit<br><em>Used to calculate material requirements for each Work Order</em></td>
</tr>


<tr>
<td>7</td>
<td><strong>Routing</strong><br>เส้นทางการผลิต เช่น ขั้นตอนการทำงาน, เครื่องจักร, เวลามาตรฐาน<br><em>ใช้กำหนดกระบวนการผลิตในแต่ละสถานีงาน (Work Center)</em></td>
<td><strong>Routing</strong><br>Production route: operations, machines, standard times<br><em>Defines production process at each work center</em></td>
</tr>


<tr>
<td>8</td>
<td><strong>Work Order (WO)</strong><br>คำสั่งผลิตจริงที่ออกจากแผนผลิต เพื่อให้หน่วยผลิตดำเนินงาน<br><em>ใช้ติดตามสถานะการผลิต, ปริมาณ, เวลา, ผู้รับผิดชอบ</em></td>
<td><strong>Work Order (WO)</strong><br>Actual production order issued from the plan<br><em>To track status, quantity, time and owner</em></td>
</tr>


<tr>
<td>9</td>
<td><strong>Scheduling</strong><br>การจัดตารางการผลิต — ลำดับคิวเครื่องจักรและเวลาการทำงาน<br><em>เพื่อใช้ทรัพยากรอย่างมีประสิทธิภาพและลดเวลารอ (Lead Time)</em></td>
<td><strong>Scheduling</strong><br>Production scheduling — machine queues and timings<br><em>To utilize resources efficiently and reduce lead time</em></td>
</tr>


<tr>
<td>10</td>
<td><strong>MRP (Material Requirement Planning)</strong><br>การวางแผนความต้องการวัตถุดิบตาม BOM และ Demand<br><em>เพื่อให้มีวัตถุดิบพร้อมก่อนเริ่มการผลิต ลดของขาดสต็อก</em></td>
<td><strong>MRP (Material Requirement Planning)</strong><br>Material planning based on BOM and demand<br><em>To ensure materials are available before production</em></td>
</tr>


</table>
      </div>    
                                                    </div>    
                               
                                        </div>
                                        </div>


<div>
                      
                                <button type="button" class="btn btn-primary me-1 waves-effect waves-float waves-light"  style="float:right"
                                 data-bs-toggle="modal" data-bs-target="#Integrations"><spring:message code="app.create_a_program" /></button>
                                 
                                  <button type="button" class="btn btn-light me-1 waves-effect waves-float waves-light"  style="background:#fff;float:right"
                                 data-bs-toggle="modal" data-bs-target="#Integrations2"><spring:message code="app.scope" /></button>
                                   </div>
                              
                         
                         
                             
                     
                  
                      
                        
                            
                        </div>
	       	          	 
	  








                        
                   </div>
          
          

                  
          
                      
                        <aside>
 
                       
                              <c:forEach items="${process }" var="row" varStatus="count">
                      			 <c:set var="logo" value="qexcellent.png" />
                          <c:if test='${row.process_logo  != null && row.process_logo  != "" }'>
				       <c:set var="logo" value="${row.process_logo}" />	
				       </c:if>
				       
				       <a  href="/editprocess-workflow/${workflows.workflow_id }/${row.id }">
                         		  <div class='apps'
          
  					    data-logo="/resources/app-assets/images/icons/APP/${logo}"
             		    data-box=" [<spring:message code="btn.InProgress"  text="-​" />]"
             		   ondblclick="App(this)"  oncontextmenu="menu(event)">
    <img src='/resources/app-assets/images/icons/APP/${logo}'>
    <p>   ${row.name}</p>
  </div>
  </a>
             	
                           	</c:forEach>	 
                      
                        </aside>
                 
            
            
     
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



