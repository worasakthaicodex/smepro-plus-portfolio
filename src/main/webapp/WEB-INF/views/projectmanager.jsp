<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>






<%@ include file="common/mainhead.jspf" %>    


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
 

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  
        <link rel="stylesheet" type="text/css" href="../../../app-assets/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../../app-assets/css/bootstrap-extended.css">
    
    

        <link href="<c:url value="/resources/app-assets/css/bootstrap.css" />" rel="stylesheet">
                 <link href="<c:url value="/resources/app-assets/vendors/css/calendars/fullcalendar.min.css" />" rel="stylesheet">
      <link href="<c:url value="/resources/app-assets/css/pages/app-calendar.css" />" rel="stylesheet">
     

<title>File Pn</title>

<style>

.select2-selection__arrow{
display:none;

}
body{

background-color:#fff;
}
  :root {
            --primary: #4361ee;
            --secondary: #3f37c9;
            --success: #4cc9f0;
            --danger: #f72585;
            --warning: #f8961e;
            --dark: #212529;
            --light: #f8f9fa;
            --gray: #6c757d;
            --light-gray: #e9ecef;
        }
        
.selected-border {
            border: 2px solid rgba(0, 0, 255, 0.8); /* ขอบสีน้ำเงินโปร่งใส */
             border-radius: 10px;
        }


.design-planning {
cursor: pointer; 
}

.ui-autocomplete{

z-index:9999;
}

.container {
  max-width: 960px;
}

.panel-default>.panel-heading {
  color: #333;
  background-color: #fff;
  border-color: #e4e5e7;
  padding: 0;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
}

.panel-default>.panel-heading a {
  display: block;
  padding: 10px 15px;
}

.panel-default>.panel-heading a:after {
  content: "";
  position: relative;
  top: 1px;
  display: inline-block;
  font-family: 'Glyphicons Halflings';
  font-style: normal;
  font-weight: 400;
  line-height: 1;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  float: right;
  transition: transform .25s linear;
  -webkit-transition: -webkit-transform .25s linear;
}

.panel-default>.panel-heading a[aria-expanded="true"] {
  background-color: #eee;
}

.panel-default>.panel-heading a[aria-expanded="true"]:after {
  content: "\2212";
  -webkit-transform: rotate(180deg);
  transform: rotate(180deg);
}

.panel-default>.panel-heading a[aria-expanded="false"]:after {
  content: "\002b";
  -webkit-transform: rotate(90deg);
  transform: rotate(90deg);
}

.accordion-option {
  width: 100%;
  float: left;
  clear: both;
  margin: 15px 0;
}

.accordion-option .title {
  font-size: 20px;
  font-weight: bold;
  float: left;
  padding: 0;
  margin: 0;
}

.accordion-option .toggle-accordion {
  float: right;
  font-size: 16px;
  color: #6a6c6f;
}

.accordion-option .toggle-accordion:before {
  content: "Expand All";
}

.accordion-option .toggle-accordion.active:before {
  content: "Collapse All";
}



.progress {
    height: 15px;
    margin-bottom: 15px;
    overflow: hidden;
    background-color: #f5f5f5;
    border-radius: 4px;
    -webkit-box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
    box-shadow: inset 0 1px 2px rgba(0,0,0,0.1);
    
}



dselect2 {
    width: 200px; /* ปรับเป็นค่าที่คุณต้องการ */
    max-height: 40px; /* ปรับเป็นค่าที่คุณต้องการ */
    overflow-y: auto /*  ; เพื่อให้มีการเลื่อนแถบเลื่อนเมื่อข้อมูลเกินขอบเขตสูงสุด */
}
.center-text{

  display: flex;
  align-items: flex-start; /* จัดตรงขอบบนขององค์ประกอบ */
  justify-content: center; /* จัดตรงกลางในแนวนอน */
  height: 100%; /* กำหนดความสูงของ div ให้เต็มส่วนทั้งหมด */
}
 .excl{
 
 border: none;
   border:0px;
   outline:0px;
 }
 

::placeholder {
  color: red;
  opacity: 1; /* Firefox */
}

:-ms-input-placeholder { /* Internet Explorer 10-11 */
 color: red;
}

::-ms-input-placeholder { /* Microsoft Edge */
 color: red;
}



.draggable {
  cursor: red;
}



 .mytable {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
   color:#111111;
  
}


  
 .mytable td {
    text-align: center;
   border: 1.8px solid #dddddd;
      max-width: 150px;
     font-size:12px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
  padding:2px;
}
 .mytable td  .action{
 text-align: center;
    border: 1px solid #dddddd;
    max-width: 150px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



 .mytable td:first-child { text-align: left; }


 .mytable th {
    text-align: center;
   border: 1.8px solid #d1d1d1;
     height: 30px;
       box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  border-top: 1px solid #ccc;
  border-left: 1px solid #ccc;
  border-right: 1px solid #ccc;

}





 .mytable.fixhead{
    max-width: 150px;
      min-width: 80px;
       
}

 .mytable thead {
    position: sticky;
    top: 0;
 background-color: #f4f4f4;
    z-index: 1; /* ให้หัวตารางอยู่ด้านบนของเนื้อหา */
      background-color: #dcdcdc;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
  
}

.fixhead2{
    position: sticky;
    top: 0;
  background-color: #f4f4f4;
    z-index: 1; /* ให้หัวตารางอยู่ด้านบนของเนื้อหา */
      background-color: #dcdcdc;
  box-shadow: 0px 4px 6px rgba(0, 0, 0, 0.1);
}



</style>
<style>
.card-app-design .design-planning-wrapper:not(:last-child) {
    margin-bottom: 1.3rem;
}
.card-app-design .design-planning-wrapper {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
}
.py-75 {
    padding-top: 0.75rem !important;
    padding-bottom: 0.75rem !important;
}

.card-app-design .design-planning-wrapper .design-planning:not(:last-child) {
    margin-right: 0.7rem;
}
.card-app-design .design-planning-wrapper .design-planning {
    padding: 0.5rem;
    margin-bottom: 0.7rem;
    text-align: center;
    background-color: #f8f8f8;
    border-radius: 0.357rem;
    min-width: 6.4rem;
}



.sidebar {
  padding: 16px;
  background-color: #f9fafb;
  font-family: sans-serif;
}

.menus {
  list-style: none;
  padding: 0;
  margin: 0;
}

.menus-item {
  display: block;
  padding: 8px 16px;
  font-size: 14px;
  font-weight: 500;
  color: #6b7280; /* text-gray-500 */
  border-radius: 8px;
  text-decoration: none;
  transition: background-color 0.2s, color 0.2s;
}

.menus-item:hover {
  background-color: #f3f4f6; /* hover:bg-gray-100 */
  color: #374151; /* hover:text-gray-700 */
}

.menus-item.active {
  background-color: #f3f4f6; /* bg-gray-100 */
  color: #374151; /* text-gray-700 */
}



  /* Resources Page */
        .resource-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }
        
        .resource-card {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            transition: transform 0.3s;
        }
        
        .resource-card:hover {
            transform: translateY(-5px);
        }
        
        .resource-header {
            padding: 20px;
            text-align: center;
            background: linear-gradient(120deg, var(--primary), var(--secondary));
            color: white;
        }
        
        .resource-icon {
            font-size: 2.5rem;
            margin-bottom: 10px;
        }
        
        .resource-name {
            font-size: 1.2rem;
            font-weight: 600;
        }
        
        .resource-body {
            padding: 20px;
        }
        
        .resource-detail {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid var(--light-gray);
        }
        
        .resource-detail:last-child {
            border-bottom: none;
        }
        
        
        
        
         /* Planning Page */
        .planning-container {
            display: grid;
            grid-template-columns: 2fr 1fr;
            gap: 20px;
        }
        
    
        /* Resources Page */
        .resource-grid {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }
        
        .resource-card {
            background: white;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 2px 10px rgba(0,0,0,0.05);
            transition: transform 0.3s;
        }
        
        .resource-card:hover {
            transform: translateY(-5px);
        }
        
        .resource-header {
            padding: 20px;
            text-align: center;
            background: linear-gradient(120deg, var(--primary), var(--secondary));
            color: white;
        }
        
        .resource-icon {
            font-size: 2.5rem;
            margin-bottom: 10px;
        }
        
        .resource-name {
            font-size: 1.2rem;
            font-weight: 600;
        }
        
        .resource-body {
            padding: 20px;
        }
        
        .resource-detail {
            display: flex;
            justify-content: space-between;
            padding: 8px 0;
            border-bottom: 1px solid var(--light-gray);
        }
        
        .resource-detail:last-child {
            border-bottom: none;
        }
        
        /* Responsive */
        @media (max-width: 992px) {
            .sidebar {
                width: 70px;
            }
            
            .sidebar .logo h1, .sidebar .nav-text {
                display: none;
            }
            
            .sidebar .nav-links {
                padding: 0 10px;
            }
            
            .sidebar .nav-links i {
                margin-right: 0;
                font-size: 1.5rem;
            }
            
            .sidebar .nav-links a {
                justify-content: center;
                padding: 15px 0;
            }
            
            .main-content {
                margin-left: 70px;
            }
        }
        
        @media (max-width: 768px) {
            .dashboard {
                grid-template-columns: 1fr;
            }
            
            .planning-container {
                grid-template-columns: 1fr;
            }
        }   
        
        
        
        
        
        #chart-container{
 height: 500px; 
}


.tabs {
	display: flex;
	flex-wrap: wrap; // make sure it wraps
}
.tabs label {
	order: 1; // Put the labels first
	display: block;
	padding: 10px;
	margin-right: 0.2rem;
	cursor: pointer;
	font-size:12px;
}
.tabs .tab {
  order: 99; // Put the tabs last
  flex-grow: 1;
	width: 100%;
	display: none;
  padding: 2px;
  background: #fff;
}
.tabs input[type="radio"] {
	display: none;
}
.tabs input[type="radio"]:checked + label {
	background: #fff;
}
.tabs input[type="radio"]:checked + label + .tab {
	display: block;
}

@media (max-width: 45em) {
  .tabs .tab,
  .tabs label {
    order: initial;
  }
  .tabs label {
    width: 100%;
    margin-right: 0;
    margin-top: 0.2rem;
  }
}


.page {
  opacity: 0;
  visibility: hidden;  /* ซ่อนไม่ให้รับ click */
  transition: opacity 0.3s ease;

}
.page.active {
  opacity: 1;
  visibility: visible;
}
</style>

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  







    <!-- BEGIN: Content-->
    <div class="app-content content " style="height:100%;">
    
    <c:if test="${error == 'condition_null'  }">
    <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span>Error01 <strong> E-form </strong>.  <spring:message code="forms.error1"  /></span>
        </div>
          </div>
      </c:if>    
        <c:if test="${error == 'report_null'  }">
    <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span>Error02.<spring:message code="forms.error2"  /> </span>
        </div>
          </div>
      </c:if>  
      
              <c:if test="${error == 'user_null'  }">
    <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span>Error03. <spring:message code="forms.error3"  /></span>
        </div>
          </div>
      </c:if>
      
                                  
    
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            
             <div style="float:left">
             
      
                        <div class="card">
                     <div class="sidebar">
<ul class="menus" id="tabdata"> 
<li><a href="#" class="menus-item active" data-custom="1"><spring:message code="project.home"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="2"><spring:message code="project.plan"  /> </a></li> 
<li><a href="#" class="menus-item" data-custom="3"><spring:message code="project.task"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="4"><spring:message code="project.team"  /> ( ${listcms2.size() } )</a></li> 
<li><a href="#" class="menus-item" data-custom="5"><spring:message code="project.resource"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="6" ><spring:message code="project.calendar"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="7" style="display:none"><spring:message code="project.timeline"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="8" style="display:none"><spring:message code="project.reporting"  /></a></li> 
</ul>
</div>



                     	</div>  
				               
           </div>  
            <div class="content-body " >
            
           
                  <!-- Detailed Email View -->
                        <div class="email-data-list" style="display:block;z-index:999;width:100%">
                            <!-- Detailed Email Header starts -->
                          

                            <!-- Detailed Email Content starts -->
                            <div >
                              

                       
                                <div class="row ">
                                
                               
                                
                                   <div class="col-12 page" id="page1" >
                             
                                                
                                                
                         
                                                 <div class="mail-attachments">
                                                    <div class="d-flex align-items-center mb-1">
                                           
                                                        <i data-feather='archive' class="font-medium-1 me-50"></i>
                                                        <h4 class="fw-bolder text-body mb-0">${upload_pn.upload_pn_note }</h4>
                                                    </div>
                                                    <div class="d-flex flex-column">
                                                    
                                                   
                                               <div class="row">
                          
                <div class="col-xl-8 col-lg-6 col-md-6" >   
                
                           <div class="card">
                        
        <div class="card-body">
									<div class="progress">
                                     
                                        <div class="progress-bar bg-success" role="progressbar" style="width: ${Progress.plan}%" aria-valuenow="${Progress.plan}" aria-valuemin="0" aria-valuemax="100">
                                        Plan: ${Progress.plan}%
                                        </div>
                                 
                                    </div>
                                  		<div class="progress">
                                  	<c:set var="bar" value="warning" />	          
                        <c:if test="${ (Progress.plan -Progress.result) > 5  }">  
                             	<c:set var="bar" value="danger" />	                   
                        </c:if>
                               <c:if test="${ Progress.result >= Progress.plan }">  
                             	<c:set var="bar" value="success" />	                   
                        </c:if>             
                                        <div class="progress-bar bg-${bar }" role="progressbar" style="width: ${Progress.result}%" aria-valuenow="${Progress.result}" aria-valuemin="0" aria-valuemax="100">
                                        Result: ${Progress.result}%
                                        </div>
                                 
                                    </div>
                                    
                                     
     </div>
 </div>

                </div>            
                       <div class="col-xl-4 col-lg-6 col-md-6" >   
                
<div class="card">
                                <div class="card-body d-flex align-items-center justify-content-between">
                                    <div>
                                        <h3 class="fw-bolder mb-75">${problem_success } / ${problem_all }</h3>
                                        <span>เเก้ไขเเล้ว / ปัญหาที่พบ</span>
                                    </div>
                                    <div class="avatar bg-light-danger p-50">
                                        <span class="avatar-content">
                                       <i data-feather='alert-circle'></i> </span>
                                    </div>
                           
                                </div>
                                    <br>  
                            </div>
     
                </div>         
                 <div >
                                        
                                                                    
                                   <style>
@import url("https://fonts.googleapis.com/css?family=Roboto:100,400,700");
body,
html {
  font-family: "Roboto", sans-serif;
  background-color: #cddade;
  height: 100%;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
}

.header {
  color: #202125;
  margin-bottom: 40px;
}
.header h2 {
  font-weight: 600;
}
.header p {
  font-weight: 300;
}

.wrapper {
  max-width: 1200px;
  min-width: 700px;
  margin: 0 auto;
  padding: 10px;
}

.gantt {
  display: grid;
  border: 0;

  position: relative;
  overflow: hidden;
  box-sizing: border-box;
  overflow-x: auto; /* Enable scrolling for the entire Gantt chart if needed */
}


.gantt__row {
  display: grid;
  grid-template-columns: 200px 1fr;
  background-color: #fff;
  
  
}


.gantt__row:nth-child(odd) {
 //background-color: #f5f5f5;
}
.gantt__row:nth-child(odd) .gantt__row-first {
   //background-color: #f5f5f5;
}
.gantt__row:nth-child(3) .gantt__row-bars {
  border-top: 0;
}
.gantt__row:nth-child(3) .gantt__row-first {
  border-top: 0;
}
.gantt__row--empty {
  background-color: #ffd6d2 !important;
  z-index: 1;
}
.gantt__row--empty .gantt__row-first {
  border-width: 1px 1px 0 0;
}
.gantt__row--lines {
  position: absolute;
  height: 100%;
  width: 100%;
  background-color: transparent;
  grid-template-columns: 200px repeat(12, 1fr);
}
.gantt__row--lines span {
  display: block;
  border-right: 1px solid rgba(0, 0, 0, 0.1);
}
.gantt__row--lines span.marker {
  background-color: rgba(10, 52, 68, 0.13);
  z-index: 2;
}
.gantt__row--lines:after {
  grid-row: 1;
  grid-column: 0;
  background-color: #1688b345;
  z-index: 2;
  height: 100%;
}
.gantt__row--months {
  color: #fff;
  background-color: #0a3444 !important;
  border-bottom: 1px solid rgba(0, 0, 0, 0.1);
  grid-template-columns: 200px repeat(24, 1fr);
  
 
  
}
.gantt__row--months .gantt__row-first {
  border-top: 0 !important;
  background-color: #0a3444 !important;
}
.gantt__row--months span {
  text-align: center;
  font-size: 13px;
  align-self: center;
  font-weight: bold;
  padding: 20px 0;
  
}
.gantt__row-first {
  background-color: #fff;
  border-width: 1px 0 0 0;
  border-color: rgba(0, 0, 0, 0.1);
  border-style: solid;
  padding: 10px;
  font-size: 13px;
  font-weight: bold;

 text-overflow: ellipsis;
   overflow: hidden;
    white-space: nowrap;

}


.gantt__row-first.sub__row{
  padding: 10px 0;
  padding-left: 15px;
  font-size: 10px;
    text-align: left;
}





.gantt__row-bars {
  list-style: none;
  display: grid;
  padding: 9px 0;
  margin: 0;
  grid-template-columns: repeat(24, 1fr);
  grid-gap: 8px 0;
  border-top: 1px solid rgba(221, 221, 221, 0.8);
}

.gantt__row-bars.sub__row li {
  font-weight: 300;
  min-height: 10px;
    height: 22px;
    font-size: 12px;
      padding: 4px;
      padding-bottom:6px;
}



.gantt__row-bars li {
  font-weight: 500;
  text-align: left;
  font-size: 14px;
  min-height: 15px;
  background-color: #55de84;
  padding: 5px 12px;
  color: #fff;
  overflow: hidden;
  position: relative;
  cursor: pointer;
  border-radius: 20px;
  

    overflow: hidden;
    white-space: nowrap;
    text-overflow: ellipsis;
  
}
.gantt__row-bars li.stripes {
  background-image: repeating-linear-gradient(45deg, transparent, transparent 5px, rgba(255, 255, 255, 0.1) 5px, rgba(255, 255, 255, 0.1) 12px);
}
.gantt__row-bars li:before, .gantt__row-bars li:after {
  content: "";
  height: 100%;
  top: 0;
  z-index: 4;
  position: absolute;
  background-color: rgba(0, 0, 0, 0.3);
}
.gantt__row-bars li:before {
  left: 0;
}
.gantt__row-bars li:after {
  right: 0;
}
</style>


<div class="wrapper">

	<div class="gantt">
		<div class="gantt__row gantt__row--months" id="gantt__row">
			<div class="gantt__row-first"></div>

	
		
		</div>
		<div class="gantt__row gantt__row--lines" >
			<span class="marker"></span>
			<span ></span>
				<span ></span>
				<span ></span>
			<span ></span>
			<span ></span>
			<span ></span>
			<span ></span>
			<span></span>
			<span></span>
			<span></span>
			<span></span>
		</div>
		<div id="gantt__rows" >
			<div class="gantt__row">
			<div class="gantt__row-first">
				Barnard Posselt
			</div>
			<ul class="gantt__row-bars">
				<li style="grid-column: 4/11; background-color: #2ecaac;">Even longer project</li>
			</ul>
		</div>
	
		<div class="gantt__row">
			<div class="gantt__row-first">
				Lanie Erwin
			</div>
			<ul class="gantt__row-bars">
				<li style="grid-column: 2/5; background-color: #2ecaac;">Start Februar</li>
				<li style="grid-column: 1/6; background-color: #ff6252;" class="stripes"></li>
				<li style="grid-column: 7/11; background-color: #54c6f9;">Same line</li>
			</ul>
		</div>
	
		<div class="gantt__row">
			<div class="gantt__row-first">
				Virgilio Jeanes
			</div>
			<ul class="gantt__row-bars">
				<li style="grid-column: 2/5; background-color: #2ecaac;"></li>
			</ul>
		</div>
		<div class="gantt__row">
			<div class="gantt__row-first">
				Ky Verick
			</div>
			<ul class="gantt__row-bars">
				<li style="grid-column: 3/8; background-color: #54c6f9;">Long project</li>
			</ul>
		</div>

		<div class="gantt__row">
			<div class="gantt__row-first">
				Ketti Waterworth
			</div>
			<ul class="gantt__row-bars">
				<li style="grid-column: 4/9; background-color: #ff6252;" class="stripes">A title</li>
			</ul>
		</div>
	</div>
	

	
	<div class="gantt__row gantt__row--empty">
			<div class="gantt__row-first">
			
			</div>
			<ul class="gantt__row-bars"></ul>
		</div>
	</div>
  
  
  
                  
<script>

function createGanttRowByMonth(startDateStr, endDateStr) {
    var ganttRow = document.getElementById('gantt__row');

    // ล้าง span เดิม (ยกเว้น .gantt__row-first)
    var oldSpans = ganttRow.querySelectorAll('span:not(.gantt__row-first)');
    oldSpans.forEach(function(s) { s.remove(); });

    var startDate = new Date(startDateStr);
    var endDate   = new Date(endDateStr);

    if (isNaN(startDate) || isNaN(endDate) || startDate > endDate) {
        console.error('วันที่ไม่ถูกต้อง');
        return;
    }

    // คำนวณจำนวนวันระหว่าง start กับ end (รวมทั้งสองวัน)
    var dayDiff = Math.floor((endDate - startDate) / (1000*60*60*24)) + 1;

    var labels = [];
    var ids    = [];
    var dates  = []; // เก็บวันที่ที่ต้องการแสดงจริงๆ

    if (dayDiff <= 60) {
        // === โหมดวัน (แสดงทุก 7 วัน) ===
        var cur = new Date(startDate);
        cur.setHours(0,0,0,0);
        var dayCount = 0;
        
        while (cur <= endDate) {
            var d = cur.getDate();
            var m = cur.getMonth() + 1;
            var y = cur.getFullYear() % 100;

            // เงื่อนไขการแสดง: ทุก 7 วัน, วันแรก, วันสุดท้าย, หรือวันแรกของเดือน
            var shouldDisplay = (dayCount % 7 === 0) || 
                               (dayCount === 0) || 
                               (cur.getTime() === endDate.getTime()) ||
                               (d === 1 && dayCount > 0);

            if (shouldDisplay) {
                var txt = d + '/' + (m < 10 ? '0'+m : m);
                var id = y + '-' + (m < 10 ? '0'+m : m) + '-' + (d < 10 ? '0'+d : d);
                
                labels.push(txt);
                ids.push(id);
                dates.push(new Date(cur)); // เก็บวันที่สำหรับคำนวณระยะห่าง
            }

            dayCount++;
            cur.setDate(cur.getDate() + 1);
        }
    } else {
        // === โหมดเดือน (เดิม) ===
        var cur = new Date(startDate.getFullYear(), startDate.getMonth(), 1);
        var idx = 0, total = 0;
        var tmp = new Date(cur);
        while (tmp <= endDate) { total++; tmp.setMonth(tmp.getMonth() + 1); }
        cur = new Date(startDate.getFullYear(), startDate.getMonth(), 1);

        while (cur <= endDate) {
            var y = cur.getFullYear() % 100;
            var m = cur.getMonth() + 1;
            var first = idx === 0;
            var last  = idx === total - 1;
            var yearChange = idx > 0 && cur.getFullYear() !==
                             new Date(startDate.getFullYear(), startDate.getMonth() + idx - 1, 1).getFullYear();

            var txt = (first || last || yearChange)
                      ? m + '/' + (y < 10 ? '0' + y : y)
                      : '' + m;

            var id = m + '-' + (y < 10 ? '0' + y : y);
            labels.push(txt);
            ids.push(id);
            idx++;
            cur.setMonth(cur.getMonth() + 1);
        }
    }

    // สร้าง span เฉพาะวันที่ต้องการแสดง
    for (var i = 0; i < labels.length; i++) {
        var span = document.createElement('span');
        span.textContent = labels[i];
        span.id = ids[i];
        span.setAttribute('data-custom', i + 1);
        
        // ถ้าเป็นโหมดวัน ให้คำนวณความกว้างตามระยะห่างของวันที่
        if (dayDiff <= 60 && i < dates.length - 1) {
            var daysBetween = Math.floor((dates[i+1] - dates[i]) / (1000*60*60*24));
            span.style.flex = daysBetween;
        }
        
        ganttRow.appendChild(span);
    }

    console.log('แสดง:', labels.join(' | '));
    console.log('ID    :', ids.join(' | '));
    console.log('โหมด:', dayDiff <= 60 ? 'วัน (' + dayDiff + ' วัน, แสดง ' + labels.length + ' จุด)' : 'เดือน');
}






const plans = [
  
	
  

	{
        start: "${upload_pn.project_start }",
        end: "${upload_pn.project_end }",
        title: "${upload_pn.upload_pn_note}",
        process: "100",
        status: "1",
        sub: [
        	  <c:forEach items="${keyactivitys2 }" var="row" varStatus="count">  
            {
                start: "${row.datestart}",
                end: "${row.dateend}",
                title: "${row.expected_results }",
                process: "80",
                status: "${row.status }"
            },
            </c:forEach>
            
        ]
    }, 
  
    
];
function renderGantt(plans, timelineStartStr, timelineEndStr) {
    var headerRow = document.getElementById('gantt__row');
    var rowsContainer = document.getElementById('gantt__rows');

    // 1. สร้าง Header
    createGanttRowByMonth(timelineStartStr, timelineEndStr);

    // รอ DOM สร้าง span
    setTimeout(function() {
        var timelineSpans = headerRow.querySelectorAll('span:not(.gantt__row-first)');
        var spanMap = {}; // id => { index: data-custom, date: Date }

        for (var i = 0; i < timelineSpans.length; i++) {
            var span = timelineSpans[i];
            var id = span.id;
            var custom = parseInt(span.getAttribute('data-custom'));
            
            // แยกส่วน ID สำหรับโหมดวัน (รูปแบบ: YY-MM-DD)
            var parts = id.split('-');
            var year = 2000 + parseInt(parts[0]);
            var month = parseInt(parts[1]) - 1;
            var day = parseInt(parts[2]);
            
            spanMap[id] = { 
                index: custom,
                date: new Date(year, month, day)
            };
        }

        // ล้างแถวเก่า
        rowsContainer.innerHTML = '';

        // 2. สร้างแถว
        for (var p = 0; p < plans.length; p++) {
            var plan = plans[p];
            var mainRow = createPlanRow(plan, spanMap);
            rowsContainer.appendChild(mainRow);

            if (plan.sub && plan.sub.length > 0) {
                for (var s = 0; s < plan.sub.length; s++) {
                    var sub = plan.sub[s];
                    var subRow = createPlanRow(sub, spanMap, true);
                    rowsContainer.appendChild(subRow);
                }
            }
        }
    }, 0);
}

function createPlanRow(plan, spanMap, isSub) {
    isSub = isSub || false;
    var startDate = new Date(plan.start);
    var endDate   = new Date(plan.end);
    
    startDate.setHours(0,0,0,0);
    endDate.setHours(23,59,59,999);

    var startCol = null;
    var endCol   = null;

    // ค้นหา span ที่ตรงกับวันที่เริ่มต้นและสิ้นสุด
    var foundStart = false;
    var foundEnd = false;

    for (var id in spanMap) {
        if (!spanMap.hasOwnProperty(id)) continue;

        var spanInfo = spanMap[id];
        var spanDate = spanInfo.date;
        var spanIndex = spanInfo.index;

        // ตรวจสอบว่า span นี้เป็นจุดเริ่มต้นของงาน
        if (!foundStart && spanDate.getTime() === startDate.getTime()) {
            startCol = spanIndex;
            foundStart = true;
        }

        // ตรวจสอบว่า span นี้เป็นจุดสิ้นสุดของงาน
        if (!foundEnd && spanDate.getTime() === endDate.getTime()) {
            endCol = spanIndex;
            foundEnd = true;
        }

        // ถ้าเจอทั้งเริ่มต้นและสิ้นสุดแล้ว ก็หยุด loop
        if (foundStart && foundEnd) break;
    }

    // ถ้าไม่เจอจุดเริ่มต้นหรือสิ้นสุดที่ตรงเป๊ะ ให้หา span ที่ใกล้ที่สุด
    if (!foundStart || !foundEnd) {
        var minStartDiff = Infinity;
        var minEndDiff = Infinity;
        var candidateStartCol = null;
        var candidateEndCol = null;

        for (var id in spanMap) {
            if (!spanMap.hasOwnProperty(id)) continue;

            var spanInfo = spanMap[id];
            var spanDate = spanInfo.date;
            var spanIndex = spanInfo.index;

            // หา span ที่ใกล้กับ startDate มากที่สุด
            var startDiff = Math.abs(spanDate - startDate);
            if (startDiff < minStartDiff) {
                minStartDiff = startDiff;
                candidateStartCol = spanIndex;
            }

            // หา span ที่ใกล้กับ endDate มากที่สุด
            var endDiff = Math.abs(spanDate - endDate);
            if (endDiff < minEndDiff) {
                minEndDiff = endDiff;
                candidateEndCol = spanIndex;
            }
        }

        if (!foundStart && candidateStartCol !== null) {
            startCol = candidateStartCol;
        }
        if (!foundEnd && candidateEndCol !== null) {
            endCol = candidateEndCol;
        }
    }

    // ถ้ายังไม่เจอ → ใช้เต็มช่วง
    if (startCol === null || endCol === null) {
        startCol = 1;
        var max = 0;
        for (var k in spanMap) {
            if (spanMap.hasOwnProperty(k) && spanMap[k].index > max) {
                max = spanMap[k].index;
            }
        }
        endCol = max;
    }

    // ตรวจสอบให้แน่ใจว่า startCol ไม่มากกว่า endCol
    if (startCol > endCol) {
        var temp = startCol;
        startCol = endCol;
        endCol = temp;
    }

    // สร้างแถว
    var row = document.createElement('div');
    row.className = 'gantt__row';

    var firstCell = document.createElement('div');
    firstCell.className = isSub ? 'gantt__row-first sub__row' : 'gantt__row-first';
    firstCell.textContent = isSub ? '├── ' + plan.title : plan.title;
    row.appendChild(firstCell);

    var bars = document.createElement('ul');
    bars.className = isSub ? 'gantt__row-bars sub__row' : 'gantt__row-bars';

    var bar = document.createElement('li');
    bar.style.setProperty('grid-column', startCol + ' / ' + (endCol + 1));
    bar.style.backgroundColor = getStatusColor(plan.status);
    bar.textContent = plan.title;
    bar.title = plan.start + ' → ' + plan.end + ' | Status: ' + plan.status;

    bars.appendChild(bar);
    row.appendChild(bars);

    console.log('Plan:', plan.title, 'Start:', startCol, 'End:', endCol, 'Grid:', startCol + ' / ' + (endCol + 1));
    
    return row;
}

function getStatusColor(status) {
    var map = {
        '1': '#8207DB',
        '2': '#4CAF50',
        '0'  : '#90A1B9',
        '-1'  : '#f44336'
    };
    return map[status] || '#cccccc';
}
createGanttRowByMonth('${upload_pn.project_start}', '${upload_pn.project_end}');
renderGantt(plans, '${upload_pn.project_start}', '${upload_pn.project_end}');

</script>               
                                          </div>
                              
            
     				     </div>     
     				       </div>                      
                  </div> 

                                </div>            
                        </div> 
                                
                                    <div class="col-12 page" id="page2" >
                                    
                                                          <div class="tabs">
  <input type="radio" name="tabs" id="tabone" checked="checked">
  <label for="tabone"><spring:message code="project.plan"  /></label>
  <div class="tab">

                                            
                                           
                                                <div class="user-details"  style="width:100%">
   		
                                                    <div class="mail-items" style="width:100%"  id="project_details" >
                                                    <br>
                                                        <div class="email-info-dropup dropdown">
                                         
                                                         <label  style="color:#615F5F;font-weight: bold;width:20%"><spring:message code="project.name"  /> :</label>
                                                      <label  style="color:black;width:70%">${upload_pn.upload_pn_note }</label><br><br>
                                                        
                                                        
                                                      <label  style="color:#615F5F;font-weight: bold;width:20%"><spring:message code="project.manager"  /> :</label>
                                                      <label  style="color:black;width:70%">${upload_pn.project_manager }</label><br><br>
                                                      
                                                      
                                                       <label  style="color:#615F5F;font-weight: bold;width:20%"><spring:message code="project.duration"  /> :</label>
                                                              <label  style="color:black;width:70%">${upload_pn.project_duration }</label><br><br>
                                                      <label style="color:#615F5F;width:20%;font-weight: bold;display:inline-block;vertical-align:top;"><spring:message code="project.objectives"  /> :</label>
                                                               <label  style="color:black;width:70%;white-space:pre-wrap;padding-top:2px"><per>${upload_pn.project_objectives }</per></label><br><br>
                                                   <label style="color:#615F5F;width:20%;font-weight: bold;display:inline-block;vertical-align:top;"><spring:message code="project.scope"  /></label>
                                                                 <label  style="color:black;width:70%;white-space:pre-wrap;padding-top:2px"><per>${upload_pn.project_scope }</per></label><br><br>
                                                                      <label  style="color:#615F5F;font-weight: bold;width:20%"><spring:message code="project.references"  /></label>
                                                                 <label  style="color:black;width:70%">${upload_pn.project_references }</label><br><br>
                                                                      
                                                     <label  style="color:#615F5F;font-weight: bold;width:20%">
                                                     <spring:message code="project.status"  />
                                                     </label>
                                                                 <label  style="color:black;width:70%">
                                                               <c:if test="${upload_pn.project  == 0}">   
                                                                  
                                                                  <span class="badge badge-light-warning rounded-pill ms-auto me-2"><spring:message code="project.planning"  /></span>
                                                                 </c:if>
                                                                  <c:if test="${upload_pn.project  == 1}">   
                                                                 
                                                                   <span class="badge badge-light-primary rounded-pill ms-auto me-2">  <spring:message code="project.executing"  /></span>
                                                                 </c:if>
                                                                     <c:if test="${upload_pn.project  == 2}">   
                                                                   
                                                                   <span class="badge badge-light-success rounded-pill ms-auto me-2"><spring:message code="project.closed"  /></span>
                                                                 </c:if>
                                                                 </label><br><br>
                                                                      
                                                                
    
	

                                                    </div>
                                                    <br>
                                                      <a href="#" class="btn btn-light" onclick="tab('project_form','project_details')">  <spring:message code="btn.setting"  /></a>
  
      <c:if test="${upload_pn.project  == 0}"> 
   <a href="/run_project-filemanager?id=${upload_pn.upload_pn_id}"  class="btn btn-success " style="float:right">
   <spring:message code="project.executing"  /></a>
   </c:if>
       <c:if test="${upload_pn.project  == 1}"> 
   <a href="/close_project-filemanager?id=${upload_pn.upload_pn_id}"  class="btn btn-success " style="float:right">
   <spring:message code="project.closure"  /></a>
   </c:if>
   
                                                </div>
                                              
   
 
                                                </div>
                                                
                                                
                                                <div id="project_form" style="display:none">
                                                
      <br>
                                                  <form:form method="POST" modelAttribute="upload_pn" action="/editproject-filemanager">
                     
                                        <div class="row">
                          
                                          <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="project.name"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
             <input type="text" name="upload_pn_note"  class="form-control"  value="${upload_pn.upload_pn_note }">
                                                    </div>
                                                </div>
                                            </div> 
                                        
                                                         <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="project.duration"  /></label>
                                                    </div>
                                                    <div class="col-sm-4">
                <input type="date" name="project_start"  class="form-control" value="${upload_pn.project_start }"  required>
                                                    </div>
                                                     <div class="col-sm-1 text-center" style="padding-top:7px"> TO </div>
                                                    
                                                                                             <div class="col-sm-4">
                <input type="date" name="project_end"  class="form-control" value="${upload_pn.project_end }"   required>
                                                    </div>
                                                </div>
                                            </div> 
                                  
                                                         <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="project.objectives"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
             <textarea name="project_objectives"  class="form-control"  >${upload_pn.project_objectives }</textarea>
                                                    </div>
                                                </div>
                                            </div> 
                                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">   <spring:message code="project.scope"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                <textarea name="project_scope"  class="form-control"  >${upload_pn.project_scope }</textarea>
                                                    </div>
                                                </div>
                                            </div> 
                                  
                                                               <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="project.references"  /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                <input type="text" name="project_references"  class="form-control" value="${upload_pn.project_references }" >
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                                                    <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><spring:message code="btn.policy" /></label>
                                                    </div>
                                                    <div class="col-sm-9">
                  <select class="form-select"  name="id_policy" id="upload_pn_respon2"   >    
              		 <option value="0">-</option>                                                
                        <c:forEach items="${mypolicy }" var="row" varStatus="count">
                        <option value="${row.id }"   <c:if test='${upload_pn.id_policy == row.id}'> selected </c:if> >${row.name }</option>
               		   </c:forEach>  
                    	  
            </select> 
                                        
                                        
                                 <input type="hidden" name="selectby"  class="form-control" value="1" >              
                                        
                                                    </div>
                                                </div>
                                            </div> 
                                            
                                            
                                  
                                    </div>
                                    
                                    
                       <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                      <input type="hidden"  name="upload_pn_id" value="${upload_pn.upload_pn_id }"> 
            
                                      <br>
                                      
                                      
                                      
                                    <input type="submit"  class="btn btn-primary" value="<spring:message code="btn.save"  />">

                              <a href="#" class="btn btn-light" onclick="tab('project_details','project_form')"><spring:message code="btn.cancel"  /></a>
                                     </form:form>
                     
                           
                                                 

 
                                                </div>
                                                
      
      
      
                                                
                                        
  </div>
  
  <input type="radio" name="tabs" id="tabtwo">
  <label for="tabtwo"><spring:message code="project.key_activity"  /></label>
  <div class="tab">

   
       <div class="card-header">
            <c:if test="${upload_pn.project  == 0}">  
                         <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCards6" style="float:right"><spring:message code="btn.add"  /></button>
                         </c:if>
                         <br>
                    </div>
   
    <table class="mytable" style="font-size:16px">
                                    <thead>
                                        <tr>
                                       
            	 <th class="form-label fixhead" style="width:3%">#</th>   
            	 
<th class="form-label fixhead" ><spring:message code="project.key_activity"  /></th>         	                          
  <th class="form-label fixhead" ><spring:message code="project.key_note" /></th>     
  
    <th class="form-label fixhead" > <spring:message code="task.task_datestart" /></th>   
      <th class="form-label fixhead" > <spring:message code="task.task_dateend" /></th>   
    <th class="form-label fixhead" > <spring:message code="tables.status" /></th>   
<th class="form-label fixhead" ><spring:message code="results.task"  /> </th>  
<th class="form-label fixhead" ><spring:message code="results.process"  /></th>     
<th class="form-label fixhead" > <spring:message code="tables.action" text="ดำเนินการ" /></th>  




    
                                        </tr>
                                    </thead>
                                    <tbody>

 
														
				                           <c:forEach items="${keyactivitys2 }" var="row" varStatus="count">  
				                                                              <tr>
                                        
<td class="form-label fixhead" style="width:3%">&nbsp; ${count.count }</td>   
<td class="form-label fixhead" >${row.expected_results}</td>       
<td class="form-label fixhead" >${row.success_indicators }</td>   
<td class="form-label fixhead" >${row.datestart }</td>   
<td class="form-label fixhead" >${row.dateend }</td>  
<td class="form-label fixhead" >
							<c:if test="${row.status == 0 }">
                                            <span class="badge rounded-pill badge-light-warning"><spring:message code="status.pending" /></span>
                                            </small>
                                   </c:if>
 		 						<c:if test="${row.status == 1 }">
                                            <span class="badge rounded-pill badge-light-primary"><spring:message code="status.inprocess" /></span>
                                            </small>
                                   </c:if>
                                       <c:if test="${row.status == 2 }">
                                       	<c:set var="success" value="${success+1 }" />	
                                            <span class="badge rounded-pill badge-light-success"><spring:message code="status.success" /></span>
                                            </small>
                                   </c:if>   
									<c:if test="${row.status == -1 }">
                                            <span class="badge rounded-pill badge-danger-info"><spring:message code="status.lated" /></span>
                                            </small>
                                   </c:if>

</td>  
<td class="form-label fixhead" >${row.task }</td> 
<td class="form-label fixhead" >

<div class="progress mb-1" style="height: 5px;margin:10px">
                <div class="progress-bar bg-success" role="progressbar" aria-valuenow="${row.process }" aria-valuemin="0" aria-valuemax="100" style="width: ${row.process }%;"></div>
              </div>
</td>  
 <td class="form-label fixhead">
     
       
     <form action="/deletekeyactivity-filemanager" method="post">
              <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <!-- hidden ส่งค่า id ของ outcome -->
        <input type="hidden" name="id" value="${row.id}">
        <input type="hidden" name="project_id" value="${row.project_id}">
     
        <a onclick="getdatas('/viewkeyactivitys-filemanager/${row.id}','use_detail',0);"
          class="btn btn-light btn-sm"  data-bs-target="#addDetail" data-bs-toggle="modal" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='eye' ></i></a>
                 <c:if test="${upload_pn.project  == 0}">  
        <button type="submit" class="btn btn-danger btn-sm" style="padding:5px;font-size:8px;maggin:0px" onclick="return confirm('<spring:message code="policy.deleteconfirm"  />?');"><i data-feather='trash' ></i></button>
         </c:if>
      </form>
    </td>

 
                               </tr>
												                  
				              
                          </c:forEach>                
				                                      
                                      
                                        
                                    </tbody>
                                    </table> 
 
 </div>
  
  
   <div style="display:none">
  <input type="radio" name="tabs" id="tabthree">
  <label for="tabthree"><spring:message code="project.expected_results"  /></label>
  <div class="tab">
     <div class="card-header">
          <c:if test="${upload_pn.project  == 0}">  
                         <button class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#addNewCards"style="float:right"><spring:message code="btn.add"  /></button>
                         </c:if>
                         <br>
                    </div>
 <table  class="mytable" style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	 
<th class="form-label fixhead" ><spring:message code="results.expectedresults" /></th>         	                          
<th class="form-label fixhead" ><spring:message code="results.successindicators" /></th>     
<th class="form-label fixhead" ><spring:message code="results.metric" /></th>   
<th class="form-label fixhead" > <spring:message code="tables.action" text="ดำเนินการ" /></th>  




    
                                        </tr>
                                    </thead>
                                    <tbody>

 
														
				                           <c:forEach items="${outcomes }" var="row" varStatus="count">  
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >&nbsp;  ${count.count }</td>   
            	 
<td class="form-label fixhead" >${row.expected_results}</td>     
<td class="form-label fixhead"  ><spring:message code="${row.success_indicators }" text="-"/></td>     
<td class="form-label fixhead"  >${row.metric }</td>  
 <td class="form-label fixhead">
      <form action="/deleteoutcome-filemanager" method="post">
              <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <!-- hidden ส่งค่า id ของ outcome -->
        <input type="hidden" name="id" value="${row.id}">
        <input type="hidden" name="project_id" value="${row.project_id}">
             <c:if test="${upload_pn.project  == 0}">  
             <a onclick="getdatas('/viewoutcome-filemanager/${row.id}','use_detail',0);"
          class="btn btn-light btn-sm"  data-bs-target="#addDetail" data-bs-toggle="modal" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='eye' ></i></a>
          </c:if>
        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('<spring:message code="policy.deleteconfirm"  />?');" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='trash' ></i></button>
         
      </form>
      
    
    </td>

 
                               </tr>
												                  
				              
                          </c:forEach>                
				                                      
                                      
                                        
                                    </tbody>
                                    </table>     
  </div>
   </div>
  
  
    <input type="radio" name="tabs" id="tab4">
  <label for="tab4"><spring:message code="project.budget"  /></label>
  <div class="tab">
     <div class="card-header">
          <c:if test="${upload_pn.project  == 0}">  
                         <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCards3" style="float:right"><spring:message code="btn.add"  /></button>
                         </c:if>
                         <br>
                    </div>
         <table  class="mytable" style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	 
<th class="form-label fixhead" ><spring:message code="risk.description" /></th>     
<th class="form-label fixhead" ><spring:message code="risk.category" /></th>     
<th class="form-label fixhead" ><spring:message code="project.budget" /></th>        	     
<th class="form-label fixhead" ><spring:message code="project.actual" /></th> 
<th class="form-label fixhead" ><spring:message code="project.variance" /></th>                      
<th class="form-label fixhead" > <spring:message code="tables.action" text="ดำเนินการ" /></th>  




    
                                        </tr>
                                    </thead>
                                    <tbody>

 
														
				                           <c:forEach items="${budgets }" var="row" varStatus="count">  
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >&nbsp;  ${count.count }</td>   
            	 
<td class="form-label fixhead" >${row.detail}</td>     
<td class="form-label fixhead"  ><spring:message code="${row.category }" text="-"/></td>     
<td class="form-label fixhead" >${row.budget }</td>   
<td class="form-label fixhead" > ${row.actual  }</td> 
<td class="form-label fixhead" > ${row.variance  }</td> 
 <td class="form-label fixhead">
       <form action="/deletebudget-filemanager" method="post">
              <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <!-- hidden ส่งค่า id ของ outcome -->
        <input type="hidden" name="id" value="${row.id}">
        <input type="hidden" name="project_id" value="${row.project_id}">
        
     <a onclick="getdatas('/viewbudget-filemanager/${row.id}','use_detail',0);"
          class="btn btn-light btn-sm"  data-bs-target="#addDetail" data-bs-toggle="modal" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='eye' ></i></a>
        <c:if test="${upload_pn.project  == 0}">  
        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('<spring:message code="policy.deleteconfirm"  />?');" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='trash' ></i></button>
         </c:if>
      </form>

    </td>

 
                               </tr>
												                  
				              
                          </c:forEach>                
				                                      
                                      
                                        
                                    </tbody>
                                    </table>   
                       
  </div>
  
  
  
  
  
      <input type="radio" name="tabs" id="tab5">
  <label for="tab5"><spring:message code="project.milestone"  /></label>
  <div class="tab">
     <div class="card-header">
          <c:if test="${upload_pn.project  == 0}">  
                         <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCards4" style="float:right"><spring:message code="btn.add"  /></button>
                         </c:if>
                         <br>
                    </div>
          <table  class="mytable" style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	 
<th class="form-label fixhead" ><spring:message code="milestone.detail" /></th>     
<th class="form-label fixhead" ><spring:message code="milestone.date_due" /></th>     
<th class="form-label fixhead" ><spring:message code="milestone.date_alert" /></th>        	     
<th class="form-label fixhead" ><spring:message code="milestone.billing_amount" /></th> 
<th class="form-label fixhead" ><spring:message code="milestone.user_work_name" /></th>  
<th class="form-label fixhead" ><spring:message code="milestone.status" /></th>  
<th class="form-label fixhead" > <spring:message code="tables.action" text="ดำเนินการ" /></th>  




    
                                        </tr>
                                    </thead>
                                    <tbody>

 
														
				                           <c:forEach items="${milestones }" var="row" varStatus="count">  
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >&nbsp;  ${count.count }</td>   
            	 
<td class="form-label fixhead" >${row.detail}</td>     
<td class="form-label fixhead" >${row.date_due }</td>   
<td class="form-label fixhead" > ${row.date_alert  }</td> 
<td class="form-label fixhead" > ${row.billing_amount  }</td> 
<td class="form-label fixhead" > ${row.user_work_name  }</td> 
<td class="form-label fixhead" > 
 <c:if test="${row.status == 0 }">
                                            <span class="badge rounded-pill badge-light-info"><spring:message code="status.INCOMPLETE" /></span>
                                            </small>
                                   </c:if>
                   
                                       <c:if test="${row.status == 1 }">
                                       	<c:set var="success" value="${success+1 }" />	
                                            <span class="badge rounded-pill badge-light-success"><spring:message code="status.COMPLETED" /></span>
                                            </small>
                                   </c:if>   

</td> 

 <td class="form-label fixhead">
       <form action="/deletemilestone-filemanager" method="post">
              <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <!-- hidden ส่งค่า id ของ outcome -->
        <input type="hidden" name="id" value="${row.id}">
        <input type="hidden" name="project_id" value="${row.project_id}">
        <a onclick="getdatas('/viewmilestone-filemanager/${row.id}','milestone_form',0);"
          class="btn btn-light btn-sm"  data-bs-target="#addNewCards5" data-bs-toggle="modal" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='eye' ></i></a>
           <c:if test="${upload_pn.project  == 0}">  
        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('<spring:message code="policy.deleteconfirm"  />?');" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='trash' ></i></button>
         </c:if>
      </form>

    </td>

 
                               </tr>
												                  
				              
                          </c:forEach>                
				                                      
                                      
                                        
                                    </tbody>
                                    </table> 
                       
  </div>
  
  

                      
                                     
       
         <input type="radio" name="tabs" id="tab6">
  <label for="tab6"><spring:message code="project.risks"  /></label>
  <div class="tab">
     <div class="card-header">
          <c:if test="${upload_pn.project  == 0}">  
                         <button class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCards2"  style="float:right"><spring:message code="btn.add"  /></button>
                         </c:if>
                         <br>
                    </div>
        
            <table  class="mytable" style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	 
<th class="form-label fixhead" ><spring:message code="risk.description" /></th>     
<th class="form-label fixhead" ><spring:message code="risk.category" /></th>        	                          
<th class="form-label fixhead" ><spring:message code="risk.level" /></th>      
<th class="form-label fixhead" ><spring:message code="risk.mitigation" /></th>       
<th class="form-label fixhead" > <spring:message code="tables.action" text="ดำเนินการ" /></th>  




    
                                        </tr>
                                    </thead>
                                    <tbody>

 
														
				                           <c:forEach items="${risks }" var="row" varStatus="count">  
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >&nbsp;  ${count.count }</td>   
            	 
<td class="form-label fixhead" >${row.description}</td>     
<td class="form-label fixhead"  ><spring:message code="${row.category }" text="-"/></td>     
<td class="form-label fixhead" >${row.level }</td>   
<td class="form-label fixhead" ><per>${risk.mitigation }</per></td>   
 <td class="form-label fixhead">
       <form action="/deleterisks-filemanager" method="post">
              <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        <!-- hidden ส่งค่า id ของ outcome -->
        <input type="hidden" name="id" value="${row.id}">
        <input type="hidden" name="project_id" value="${row.project_id}">
      
           <c:if test="${upload_pn.project  == 0}">  
        <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('<spring:message code="policy.deleteconfirm"  />?');" style="padding:5px;font-size:8px;maggin:0px"><i data-feather='trash' ></i></button>
         </c:if>
      </form>

    </td>

 
                               </tr>
												                  
				              
                          </c:forEach>                
				                                      
                                      
                                        
                                    </tbody>
                                    </table>                
  </div>
  
  
</div>                                             
       
      </div>                                                
                                              
                                       
         
                         
                    
           
                         
                         
                         
                       
                                        
                                 
                                       <div class="col-12 page" id="page3" style="display:none" >
                                         
                                         
                                         <div class="row">
                        <div class="col-lg-4 col-sm-6">
                            <div class="card">
                                <div class="card-body d-flex align-items-center justify-content-between">
                                    <div>
                                        <h3 class="fw-bolder mb-75">${all_task }</h3>
                                        <span><spring:message code="task.alltasks"  /></span>
                                    </div>
                                    <div class="avatar bg-light-primary p-50">
                                        <span class="avatar-content">
                                      <i data-feather='package'></i>
                                        </span>
                                    </div>
                                </div>
                                  <br>
                            </div>
                        </div>
                        <div class="col-lg-4 col-sm-6">
                            <div class="card">
                                <div class="card-body d-flex align-items-center justify-content-between">
                                    <div>
                                        <h3 class="fw-bolder mb-75">${pending_task }</h3>
                                        <span><spring:message code="task.pending"  /></span>
                                    </div>
                                    <div class="avatar bg-light-warning  p-50">
                                        <span class="avatar-content">
                                     <i data-feather='archive'></i>
                                        </span>
                                    </div>
                                </div>
                                <br>
                            </div>
                        </div>
             
                        <div class="col-lg-4 col-sm-6">
                            
                        <div class="card">
                            <div class="row">
                                <div class="col-sm-5">
                                    <div class="d-flex align-items-end justify-content-center h-100">
                                        <img src="/resources/app-assets/images/illustration/faq-illustrations.svg" class="img-fluid mt-2" alt="Image" width="80">
                                    </div>
                                </div>
                                <div class="col-sm-7">
                                    <div class="card-body text-sm-end text-center ps-sm-0">
                         
                           
                                        <a href="javascript:void(0)" style="float:left;"  data-bs-target="#addRoleModal" data-bs-toggle="modal" >
                                            <span class="btn btn-primary mb-1 waves-effect waves-float waves-light"><spring:message code="task.add"  /></span>
                                        </a> 
                              <c:set var="listcheck" value="${Member_name}" />  
                             <c:set var="list2" value="${upload_pnsall.upload_pn_app}" /> 
                              <c:if test='${fn:contains(list,listcheck)}'>     </c:if>
                                        
                                        <a href="javascript:void(0)" 
                                         onclick="getdatas('/alltask-filemanager/${upload_pn.upload_pn_id}','divdata',0);" style="float:left;margin-left:1px;"  data-bs-target="#addRoleModal4" data-bs-toggle="modal">
                                            <span class="btn btn-primary mb-1 waves-effect waves-float waves-light" style="display:none"></span>
                                        </a>
                                        <p class="mb-0" style="clear:both"><spring:message code="task.assign"  /></p>
                                    </div>
                                </div>
                            </div>
                     
                    </div>
                        </div>
                    </div>
                                         
                                     
                                               <div class="card card-app-design "   style="padding:20px;" >
                      
                                            
                                                 <div class="mail-attachments">
                                              
                                                    <div class="d-flex align-items-center mb-1" style="float:left">
                                           
                                                        <i data-feather='archive' class="font-medium-1 me-50"></i>
                                                        <h4 class="fw-bolder text-body mb-0" ><spring:message code="${taskview.title }"   text="-"/></h4>
                                                  
                                    
                                    
                                                    </div>
                                                                                            <div class="dropdown chart-dropdown" style="float:right">
                                        <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-more-vertical font-medium-3 cursor-pointer" data-bs-toggle="dropdown"><circle cx="12" cy="12" r="1"></circle><circle cx="12" cy="5" r="1"></circle><circle cx="12" cy="19" r="1"></circle></svg>
                                        <div class="dropdown-menu dropdown-menu-end">
                                            <a class="dropdown-item" href="/taskview-filemanager/${upload_pn.upload_pn_id}/1" ><spring:message code="work.mytasks"  /></a>
                                            <a class="dropdown-item" href="/taskview-filemanager/${upload_pn.upload_pn_id}/2"><spring:message code="work.todo"  /></a>
                                            <a class="dropdown-item" href="/taskview-filemanager/${upload_pn.upload_pn_id}/3"><spring:message code="work.byoutcome"  /></a>
                                               <a class="dropdown-item" href="/taskview-filemanager/${upload_pn.upload_pn_id}/4"><spring:message code="work.alltasks"  /></a>
                                        </div>
                                    </div>
                                                    <div class="d-flex flex-column" style="clear:both">
                                                    
                                             <br>
                                               <div class="row" style="height:480px;overflow:auto">
                                          
                                             <%@ include file="task/taskview1.jspf" %>  
                                               
                      
              
                   
                    
                    
                    
                </div>
                                                     
                                                        
                                                    </div>
                                                </div>
                                                
                                       <br> <br>         
                
                        
                        
                        
                        
                        
                        
                                            </div>
                                     
                                     
                                     
                                    </div>
                                         
                                         
                                          <div class="col-12 page" id="page4" style="display:none" >
                                              <div class="modal-content p-0">
                                    <form method="POST"  id="cmform" name="cmform">
                                        <div class="modal-header align-items-center mb-1">
                                            <h5 class="modal-title">Team Collaboration </h5>
                                            <div class="todo-item-action d-flex align-items-center justify-content-between ms-auto">
                                               
                                                 <input type="hidden" name="cm_idrefer" value="${id}">
                                                <input type="hidden" name="type" value="upload_pn">
                                                
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="3" stroke-linecap="round" stroke-linejoin="round" class="feather feather-x cursor-pointer" data-bs-dismiss="modal"><line x1="18" y1="6" x2="6" y2="18"></line><line x1="6" y1="6" x2="18" y2="18"></line></svg>
                                            </div>
                                        </div>
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                            <div class="action-tags">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">กรอกข้อความข้อคุณ</label>
                                                    <textarea type="text" name="cm_title" id="todoTitleAdd" name="todoTitleAdd" class="new-todo-item-title form-control" ></textarea>
                                                </div>
                                                
                                                <div class="row">
                                            <div class="col-md-6 col-12">
                                              <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">เเนบไฟล์</label>
                                                    <input type="file" name="cm_title" id="todoTitleAdd" name="todoTitleAdd" class="new-todo-item-title form-control" >
                                                </div>
                                                   </div>
                                                     <div class="col-md-6 col-12">
                                                 <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">Tag ถึง</label>
                                                    <input type="file" name="cm_title" id="todoTitleAdd" name="todoTitleAdd" class="new-todo-item-title form-control" >
                                                </div>
                                             </div>   </div>
                                            </div>
                                            <div class="my-1">
                                                <button type="button" onclick='postdata("/addcm-filemanager?${_csrf.parameterName}=${_csrf.token}","cmform","cmdiv")' class="btn btn-primary add-todo-item me-1 waves-effect waves-float waves-light">Add</button>
                                            
                                               
                                             
                                            </div>
                                        </div>
                                    </form>
                                      <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                    <div class="tab-content" id="myTabContent">
                                      
                                            <div class="tab-pane fade active show" id="activity-tab" role="tabpanel" aria-labelledby="activity-tab">
                                                <h6 class="file-manager-title my-2">วันนี้</h6>
                                                
                                   <div id="cmdiv">
                                                
                                    <c:forEach items="${listcms2 }" var="row" varStatus="count">
                                   <div class="d-flex align-items-center mb-2" id="cm-${row.cm_id }">
                                                    <div class="avatar avatar-sm me-50" >
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar" width="28">
                                                    </div>
                                                    <div class="more-info" style="width:90%">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">${row.cm_user_name }</span> <span class="fw-bold"> ${cm_date } </span>
                                                            <br>
                                                            ${row.cm_title }
                                                       
                                                        </p>
                                                    </div>
                                                       <button type="button" onclick="return getdata('/delcm-filemanager/${row.cm_id}');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    Delete
                                                </button>
                                          </div>


                               </c:forEach>
                                 </div>
                                              
                                              
                                                <h6 class="file-manager-title mt-3 mb-2">ความคิดเห็นทั้งหมด</h6>
                                            <c:forEach items="${listcms1 }" var="row" varStatus="count">
                                   <div class="d-flex align-items-center mb-2" id="cm-${row.cm_id }">
                                                    <div class="avatar avatar-sm me-50" >
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar" width="28">
                                                    </div>
                                                    <div class="more-info" style="width:90%">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">${row.cm_user_name }</span> <span class="fw-bold"> ${cm_date } </span>
                                                            <br>
                                                            ${row.cm_title }
                                                       
                                                        </p>
                                                    </div>
                                                       <button type="button"   onclick="return getdata('/delcm-filemanager/${row.cm_id}/${row.type} }');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    Delete
                                                </button>
                                          </div>


                               </c:forEach>
                                       
                                             
                                            </div>
                                        </div>
                                            </div>
                                </div>
                             </div>
                                         
                                         
                                         
                                          <div class="col-12 page" id="page5" style="display:none" >
                                         <div id="resources" >
                <div class="card mb-20">
                    <div class="card-header">
                        <h3 class="card-title"><spring:message code="work.manage_resources"  /></h3>
                     
                    </div>
                         </div>
                    <div class="resource-grid">
                        <div class="resource-card">
                            <div class="resource-header">
                                <div class="resource-icon">

                                  <i data-feather='user-check' class="font-medium-1 me-50"></i>
                                </div>
                                <div class="resource-name"><spring:message code="work.labor"  /></div>
                            </div>
                            <div class="resource-body">
                                <div class="resource-detail">
                                    <span><spring:message code="work.numberof"  />:</span>
                                    <span>${LaborCount}</span>
                                </div>
                                <div class="resource-detail">
                                    <span><spring:message code="resources.estimatedCost"  />:</span>
                                    <span>${LaborEstimatedCost}</span>
                                    
                                    
                                </div>
                             <div class="resource-detail">
                                  <span></span>
                              		  <button class="btn btn-light" onclick="getdatas('/resourcesmanager-filemanager/labor/${upload_pn.upload_pn_id}','resources',0)">
                              		      	  <spring:message code="btn.view"  />
                              		  </button>
                                  </div>
                            </div>
                        </div>
                        
                        <div class="resource-card">
                            <div class="resource-header">
                                <div class="resource-icon">
                                    <i data-feather='server' class="font-medium-1 me-50"></i>
                                    
                                </div>
                                <div class="resource-name"><spring:message code="work.equipment"  />
                                
                                </div>
                            </div>
                            <div class="resource-body">
                                <div class="resource-detail">
                                    <span><spring:message code="work.numberof"  />:</span>
                                  <span>${EquipmentCount}</span>
                                </div>
                                <div class="resource-detail">
                                    <span><spring:message code="resources.estimatedCost"  />:</span>
                                    <span>${EquipmentmatedCost}</span>
                                </div>
                     <div class="resource-detail">
                                  <span></span>
                            
                              		    <button class="btn btn-light" onclick="getdatas('/resourcesmanager-filemanager/Equipment/${upload_pn.upload_pn_id}','resources',0)">
                              		        	  <spring:message code="btn.view"  />
                              		    </button>
                                  </div>
                            </div>
                        </div>
                        
                        <div class="resource-card">
                            <div class="resource-header">
                                <div class="resource-icon">
                              
                                  <i data-feather='book'  class="font-medium-1 me-50"></i>
                              
                                </div>
                                <div class="resource-name"><spring:message code="work.materials"  /></div>
                            </div>
                            <div class="resource-body">
                                <div class="resource-detail">
                                    <span><spring:message code="work.numberof"  />:</span>
                                        <span>${MaterialCount}</span>
                                </div>
                                <div class="resource-detail">
                                    <span><spring:message code="resources.estimatedCost"  />:</span>
                                   <span>${MaterialmatedCost}</span>
                                </div>
                              <div class="resource-detail">
                                  <span></span>
                              	  <button class="btn btn-light" onclick="getdatas('/resourcesmanager-filemanager/Material/${upload_pn.upload_pn_id}','resources',0)">
                              	  <spring:message code="btn.view"  />
                              	  </button>
                                  </div>
                            </div>
                        </div>
                        
                        <div class="resource-card">
                            <div class="resource-header">
                                <div class="resource-icon">
                          
                                  <i data-feather='map-pin' class="font-medium-1 me-50"></i>
                                </div>
                                <div class="resource-name"><spring:message code="work.facilities"  /></div>
                            </div>
                            <div class="resource-body">
                                <div class="resource-detail">
                                    <span><spring:message code="work.numberof"  />:</span>
                                       <span>${FacilityCount}</span>
                                </div>
                                <div class="resource-detail">
                                    <span><spring:message code="resources.estimatedCost"  />:</span>
                                      <span>${FacilityEstimatedCost}</span>
                                </div>
                        <div class="resource-detail">
                                  <span></span>
                              		  <button class="btn btn-light" onclick="getdatas('/resourcesmanager-filemanager/Facility/${upload_pn.upload_pn_id}','resources',0)">
                              		      	  <spring:message code="btn.view"  />
                              		  </button>
                                  </div>
                            </div>
                        </div>
                    </div>
           
            </div>
                            </div>                
                                       
                                     <div class="col-12 page" id="page6" style="display:none" >     
                                          <div class="app-calendar overflow-hidden border">
                        <div class="row g-0">
                            <!-- Sidebar -->
                            <div class="col app-calendar-sidebar flex-grow-0 overflow-hidden d-flex flex-column" id="app-calendar-sidebar">
                                <div class="sidebar-wrapper">
                                 
                                    <div class="card-body pb-0">
                                        <h5 class="section-label mb-1">
                                            <span class="align-middle">Filter</span>
                                        </h5>
                                        <div class="form-check mb-1">
                                            <input type="checkbox" class="form-check-input select-all" id="select-all" checked />
                                            <label class="form-check-label" for="select-all">View All</label>
                                        </div>
                                        <div class="calendar-events-filter">
                                        
                                            <div class="form-check form-check-primary mb-1">
                                                <input type="checkbox" class="form-check-input input-filter" id="business" data-value="business" checked />
                                                <label class="form-check-label" for="business">Task</label>
                                            </div>
                                            <div class="form-check form-check-danger mb-1">
                                                <input type="checkbox" class="form-check-input input-filter" id="personal" data-value="personal" checked />
                                                <label class="form-check-label" for="personal">Milestone</label>
                                            </div>
                                        
                                            <div class="form-check form-check-success mb-1">
                                                <input type="checkbox" class="form-check-input input-filter" id="holiday" data-value="holiday" checked />
                                                <label class="form-check-label" for="holiday">Holiday</label>
                                            </div>
                                        
                                        </div>
                                    </div>
                                </div>
                                <div class="mt-auto">
                                   
                                </div>
                            </div>
                            <!-- /Sidebar -->

                            <!-- Calendar -->
                            <div class="col position-relative">
                                <div class="card shadow-none border-0 mb-0 rounded-0">
                                    <div class="card-body pb-0">
                                        <div id="calendar"></div>
                                    </div>
                                </div>
                            </div>
                            <!-- /Calendar -->
                            <div class="body-content-overlay"></div>
                        </div>
                    </div>
                                           </div>
                                           
                                           
                                              <div class="col-12 page" id="page7" style="display:none" >     
                                 
                              
                                           
                                           
                                         </div>
                                     
                                            <div class="col-12 page" id="page8" style="display:none" >   
                                            Report
                                             </div>
                                        </div>
                                               </div>      
                                        
                                    </div>
                                </div>
                             
                            </div>
                            <!-- Detailed Email Content ends -->
                        </div>
                        <!--/ Detailed Email View -->
                      
                      
                      


            </div>
         
        </div>
    </div>
    <!-- END: Content-->


         <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.uncheck" /></h1>
                              

                                <!-- form -->
                                <form    action="/rejectdocument-filemanager" id="formaddNewCardValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control autocomplete-input2" type="text" required/>
                                              
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type">   </span>
                                            </span>
                                        </div>
                                        ไม่ได้เข้า,ข้อมูลไม่ถูกต้อง หรือ อื่นๆ
                                          <input type="hidden" name="id" id="idreject" value=""> 
                                    </div>

                                   
                                

                                    <div class="col-12 text-center">
                                        <button type="button" class="btn btn-primary me-1 mt-1"  
                                        onclick='postdata("/rejectdocument-filemanager?${_csrf.parameterName}=${_csrf.token}","formaddNewCardValidation","savestatus")' data-bs-dismiss="modal" aria-label="Close">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                                                 <script>

	  $( function() {
		  
		  var newdata = 'ไม่ได้เข้า,ข้อมูลไม่ถูกต้อง'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง
		var dataItems = trimmedData.split(',');
		  
	    $( ".autocomplete-input2" ).autocomplete({
	      source: dataItems
	    });
	  } );
</script>     
                </div>
                <!--/ add new card modal  -->




     <div class="modal fade" id="addNewCards6" tabindex="-1" aria-labelledby="addNewCardTitle5" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="project.key_activity"  /></h1>
                              

                                <!-- form -->
                                <form    action="/savekeyactivity-filemanager" id="formaddNewCardValidation2" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                                
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="project.key_activity"  /></label>
                                               <input  type="text" class="form-control"  name="expected_results"  value="">
                                           </div>      
    									<div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="project.key_note"  /></label>
                                               <input  type="text" class="form-control"  name="success_indicators"  value="">
                                           </div>   
                                           
                                           
                                                <div class="col-6 mb-1">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_datestart" /></label>
                                       <input type="date"  name="datestart" id="datestart"    class="form-control" required >
                                    </div>
                                        <div class="col-6 mb-1">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_dateend" /></label>
                                         <input type="date" name="dateend" id="dateend"     class="form-control" required >
                                    </div>
                                    
                                           
                                             
				                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				                      <input type="hidden"  name="project_id" value="${upload_pn.upload_pn_id }"> 
            
                        
                    
                                
                            
                                    
                                    

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"  ><spring:message code="btn.save" /></button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>
               
  




     <div class="modal fade" id="addNewCards" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="project.expected_results" /></h1>
                              

                                <!-- form -->
                                <form    action="/appoutcome-filemanager" id="formaddNewCardValidation2" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                                
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="results.expectedresults" /></label>
                                               <input  type="text" class="form-control"  name="expected_results"  value="">
                                           </div>      
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="results.successindicators" /></label>
                                                   <select  name="success_indicators"  id="data_values${row.field_id }" class="form-select">
                                                   <option value="results.deliverables"><spring:message code="results.deliverables" /></option>
                                                   <option value="results.satisfaction"><spring:message code="results.satisfaction" /></option>
                                                   <option value="results.documentsrequired"><spring:message code="results.documentsrequired" /></option>
                                                   <option value="results.otherneeds"><spring:message code="results.otherneeds" /></option>
                                                 </select>
                                           </div>       
                                        <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="results.metric" /></label>
                                               <input  type="number" class="form-control"  name="metric" value="">
                                           </div> 
                                
               
            
				                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				                      <input type="hidden"  name="project_id" value="${upload_pn.upload_pn_id }"> 
            
                        
                    
                                
                            
                                    
                                    

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"  ><spring:message code="btn.save" /></button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>
               
  

   <div class="modal fade" id="addNewCards2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"><spring:message code="project.risks"  /></h1>
                              

                                <!-- form -->
                                <form    action="/addrisk-filemanager" id="formaddNewCardValidation2" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                                
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="risk.description" /></label>
                                               <input  type="text" class="form-control"  name="description"  value="">
                                           </div>      
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="risk.category" /></label>
                                                   <select  name="category"   class="form-select">
                                                   <option value="category.technology"><spring:message code="category.technology" /></option>
                                                         <option value="category.management"><spring:message code="category.management" /></option>
                                                   <option value="category.human"><spring:message code="category.human" /></option>
                                                   <option value="category.finance"><spring:message code="category.finance" /></option>
                                                     <option value="risk.shipping"><spring:message code="risk.shipping" /></option>
                                                       <option value="risk.quality"><spring:message code="risk.quality" /></option>
                                                   <option value="category.external"><spring:message code="category.external" /></option>
                                                 </select>
                                           </div>       
               								    <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="risk.mitigation" /></label>
                                               <textarea  class="form-control" name="mitigation" ></textarea>
                                           </div>   
               
				                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				                      <input type="hidden"  name="project_id" value="${upload_pn.upload_pn_id }"> 
             

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"  ><spring:message code="btn.save" /></button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>
               



   <div class="modal fade" id="addNewCards3" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"><spring:message code="project.budget"  /></h1>
                              

                                <!-- form -->
                                <form    action="/addbudget-filemanager" id="formaddNewCardValidation2" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                                
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="risk.description" /></label>
                                               <input  type="text" class="form-control"  name="detail"  value="">
                                           </div>      
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="risk.category" /></label>
                                                   <select  name="category"   class="form-select">
                                                   <option value="budget.personnel"><spring:message code="budget.personnel" /></option>
                                                   <option value="budget.equipment"><spring:message code="budget.equipment" /></option>
                                                   <option value="budget.materials"><spring:message code="budget.materials" /></option>
                                                   <option value="budget.miscellaneous"><spring:message code="budget.miscellaneous" /></option>
                                                 </select>
                                           </div>       
                     					<div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="budget.budget" /></label>
                                               <input  type="number" class="form-control"  name="budget"  required>
                                           </div>  
               
				                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				                      <input type="hidden"  name="project_id" value="${upload_pn.upload_pn_id }"> 
             

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"  ><spring:message code="btn.save" /></button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>
               





   <div class="modal fade" id="addNewCards4" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"><spring:message code="project.milestone"  /></h1>
                              

                                <!-- form -->
                                <form    action="/addmilestone-filemanager" id="formaddNewCardValidation2" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                            <div >
                                
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.detail" /></label>
                                               <input  type="text" class="form-control"  name="detail"  value="">
                                           </div>      
                                   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.date_due" /></label>
                                                           <input  type="date" class="form-control"  name="date_due"  value="">
                                           </div>       
                     				
                     				   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.date_alert" /></label>
                                                           <input  type="date" class="form-control"  name="date_alert"  value="">
                                           </div>   
                     				
                     				   <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.billing_amount" /></label>
                                                           <input  type="number" class="form-control"  name="billing_amount"  value="">
                                           </div>   
                     				
                     				 
               	  				    	 <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.user_work_name" /></label>
								                                             <select class="select2 form-select" name="user_work_name" > 
								    <c:set var="listrespon" value="${milestone.user_work_name}" />
								 
								    <c:forEach var="nameItem" items="${user_all}">
								            <option value="${nameItem.user_name}" ${fn:contains(listrespon, nameItem.user_name) ? 'selected' : ''}>${nameItem.user_name}</option>
								    </c:forEach>
								</select>
                                           </div>   
                                    <br>       
                                           <hr>
                                         <br>         
                                              <div class="mb-2 row">
                                               <div class="col-4">
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.processing_date" /></label>
                                                           <input  type="date" class="form-control"  name="processing_date"  value="">
                                                               </div>  
                                           <div class="col-4 ">
                                           
                                                    <label class="form-label" for="first-name-column"><spring:message code="milestone.collected_payment" /></label>
                                                           <input  type="number" class="form-control"  name="collected_payment"  value="">
                                                               </div>  
                                        
                                              <div class="col-4 ">
                                           
                                                    <label class="form-label" for="first-name-column"><spring:message code="tables.status" /></label>
                                                          <select class="form-select" name="status">
                                                          <option value="0"><spring:message code="status.INCOMPLETE" /></option>
                                                            <option value="1"><spring:message code="status.COMPLETED" /></option>
                                                          </select>
                                                               </div>  
                                           </div>    
                                           
                     				 </div>   
                     				 
                     				 
                        
                     				
				                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				                      <input type="hidden"  name="project_id" value="${upload_pn.upload_pn_id }"> 
             

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"  ><spring:message code="btn.save" /></button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>
         



   <div class="modal fade" id="addNewCards5" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"><spring:message code="project.milestone"  /></h1>
                              

                                <!-- form -->
                                <form    action="/addmilestone-filemanager" id="formaddNewCardValidation2" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            
                            <div id="milestone_form">
                         
                     				 </div>   
                     				 
                     				 
                        
                     				
				                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
				                      <input type="hidden"  name="project_id" value="${upload_pn.upload_pn_id }"> 
             

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1"  ><spring:message code="btn.save" /></button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         <spring:message code="btn.cancel" />
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    
                </div>
         


    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



 <div class="modal fade text-start" id="large" tabindex="-1" aria-labelledby="myModalLabel17" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">Data Set</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        <div class="modal-body">
                                                          <input type="hidden"  id="dataset_node" value="">
                                                         <select class="form-select select2" id="dataset_data">
                                                <c:forEach items="${datasets }" var="row" varStatus="count">
                                                
                             
                                           <c:set var="data" value='${row.dataset_1 }' />
                                           <option value="${row.dataset_id }"  data-custom="${data}">${row.dataset_name }</option>
                                           </c:forEach>
                                                         </select>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-primary" data-bs-dismiss="modal" onclick="adddataset()">Import</button>
                                                        </div>
                                                    </div>
                                                </div>
      </div>
      
      
      
      
   
      
      
      
      
<div id="savestatus"></div>
<%@ include file="common/mainfoot.jspf" %>  

      
        <script>


	  // เมื่อโหลดหน้าเว็บ: ซ่อนทุกหน้า ยกเว้น active
	document.querySelectorAll('.page').forEach(div => div.style.display = 'none');
	const activeTab = document.querySelector('.menus-item.active');
	
	if (activeTab) {
	  const pageId = 'page' + activeTab.getAttribute('data-custom');
	  document.getElementById(pageId).style.display = 'block';
	} else {
	  // ถ้าไม่มี active tab ให้คลิก page1
	  const defaultTab = document.querySelector('.menus-item[data-custom="1"]');
	  if (defaultTab) {
	    defaultTab.classList.add('active');
	    const pageId = 'page1';
	    document.getElementById(pageId).style.display = 'block';
	    // ถ้าอยากให้มัน trigger event click จริงๆ
	    defaultTab.click();
	  }
	}


  // เมื่อคลิกเปลี่ยนแท็บ
  document.querySelectorAll('.menus-item').forEach(item => {
    item.addEventListener('click', function (e) {
      e.preventDefault();


      
      // ลบ active เดิม
      document.querySelectorAll('.menus-item').forEach(el => el.classList.remove('active'));
  
      // ตั้ง active ใหม่
      this.classList.add('active');

      // ซ่อนทุกหน้า
      document.querySelectorAll('.page').forEach(div => div.style.display = 'none');
      document.querySelectorAll('.page').forEach(div => div.classList.remove('active'));
      // แสดงหน้าที่ถูกเลือก
      const pageId = 'page' + this.getAttribute('data-custom');
      const page = document.getElementById(pageId);
      if (page) {
        page.style.display = 'block';
        page.classList.add('active');
        
        document.querySelector('.fc-dayGridMonth-button').click();
        
      }
    });
  });
</script>   
       


  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/legacy.js" />"></script>
  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.date.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>
    <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>


 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
 	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	  


 <script src="<c:url value="/resources/app-assets/vendors/js/calendar/fullcalendar.min.js" />"></script>

  


<script>
function addOneDay(dateStr) {
    const date = new Date(dateStr);
    date.setDate(date.getDate() + 1);
    return date.toISOString().split('T')[0];
}

var events = [

    <c:forEach items="${keyactivitys2 }" var="row" varStatus="count">  
    <c:forEach items="${row.tasks }" var="tasks" varStatus="count">  
	{
    id: ${count.count},
    url: '',
    title: '${tasks.task_name}',
    start: '${tasks.task_datestart}',
    end: addOneDay('${tasks.task_dateend}'),
    allDay: true,
    extendedProps: {
   calendar: 'Business'
  
    	
    }
	  },
  </c:forEach>
	  </c:forEach>
	  <c:forEach items="${milestones }" var="row" varStatus="count">  
	  
	  {
		    id: ${count.count + 99},
		    url: '',
		    title: '${row.detail}',
		    start: '${row.date_due}',
		    end: '${row.date_due}',
		    allDay: true,
		    extendedProps: {
		  	 calendar: 'Personal'
		  
		    	
		    }
			  },
	

  </c:forEach>
	
  
  
];



</script>

<script src="<c:url value="/resources/app-assets/js/scripts/pages/app-calendar.js" />"></script>
  <c:if test="${TAB > 0}"> 
   <script> 

    </script>
</c:if>
   <script> 

   function tab(show,hide){
       

	   $("#"+hide).hide();
	   $("#"+show).show();
	
   }
        
   
   
   document.addEventListener("DOMContentLoaded", function() {
	    let tab = sessionStorage.getItem('TAB') || 0;
	    if(tab > 0) {
	        document.querySelector('#tabdata .menus-item[data-custom="' + tab + '"]').click();
	        document.querySelector('.fc-dayGridMonth-button').click();
	    }

	    document.querySelectorAll('#tabdata .menus-item').forEach(item => {
	        item.addEventListener('click', function() {
	            sessionStorage.setItem('TAB', item.dataset.custom);
	        });
	    });
	    
	    
	});
    </script>
    


	    <script>  
	    function deletefile(url,file,div){
		    
	    	var status = 0;
	
	    		if (confirm("คุณต้องการดำเนินการ?"))
				{
	    			status=1;
				}
	    
	
				
				if(status == 1){
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        
			    	     success: function(data){
			    	    $("#"+div).html(data);	
		
			    	     }
			    	});
					   $("#"+file).remove();	
				}
				    	  
			return false;
			
	}

	    function getdatas(url,div,type){
	    
	    	var status = 0;
	    	
	    	
	    	if(type == 1){
	    		if (confirm("คุณต้องการดำเนินการ?"))
				{
	    			status=1;
				}
	    	}else{
	    		status=1;
	    	}
	
				
				if(status == 1){
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        
			    	     success: function(data){
			    	    $("#"+div).html(data);	
		
			    	     }
			    	});
					
				}
				    	  
		
			
			return false;
			
	}
	    
	    
	    function getdatasselect(url,div){
		    
		
	    	alert(url)
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        
			    	     success: function(data){
			    	    $("#"+div).html(data);	
		
			    	     }
			    	});
					

			return false;
			
	   }
	    
	    
	    
	    
	    
	    function reject(id){
	    	$("#idreject").val(id);
	    }
	    
	    function app(id){
	    	$("#idapp").val(id);
	    }
	    
	    
	    function postdata(Mode,Mode2,Mode3) {
	     	

		    var formData = new FormData($("#"+ Mode2)[0]);

				    $.ajax({
				        url: Mode,
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
					    success: function(result){
					    $('#' + Mode3).html(result);
					   
				        evaluateAndDisplay();
				        evaluateAndDisplay2();
				        sum();
					       }
					    });
				

				    $("#"+Mode2)[0].reset();
				   
		    return false;
		    }	
	    
	    
	    
	    
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
	    
	    
	    function uploadfile(Mode,Mode2,Mode3) {
	    	
	    	var due = $("#fp-default").val();
	    	var edit = $("#edit").val();
	    	$("#reserve").html("");
	    	
	    	<c:if test="${eformfields2 != null  }">   
	    	
	    	
		    	if(edit == 0){
				    	alert("ฟอร์มนี้รองรับเเต่การเเก้ไขข้อมูลเท่านั้น");
			    		return false;
		    	}
	    	</c:if>
	    	
            <c:if test="${upload_pn.upload_pn_filecheck == 1}"> 
            
	       	 var fileInput1 = $('#files');
	    	 var fileInput2 = $('#files2');
	    	 var fileInput3 = $('#files3');
	    	 var fileInput4 = $('#files4');
	    	 var fileInput5 = $('#files5');
    	 
	    	 
	    	    if (fileInput1.length) { 
	    	   
	    	    	if(!fileInput1.val() ){
				    	alert("Please select a file to upload");
			    		return false;
		    		}
	    	    }
	    	    
	    	    
			  if (fileInput2.length) { 
				    	    	
				    	    	if(!fileInput2.val() ){
							    	alert("Please select a file to upload");
						    		return false;
					    		}
			  }
			  
			  
			  if (fileInput3.length) { 
			  	
			  	if(!fileInput3.val() ){
				    	alert("Please select a file to upload");
			  		return false;
					}
			  }
			  
			  
			  if (fileInput4.length) { 
			  	
			  	if(!fileInput4.val() ){
				    	alert("Please select a file to upload");
			  		return false;
					}
			  }
			  
			  
			  if (fileInput5.length) { 
			  	
			  	if(!fileInput5.val() ){
				    	alert("Please select a file to upload");
			  		return false;
					}
			  }
	    	 
	    	 	
            </c:if>
	    
	    	 
	    	if(due.trim() === ""){
	    		alert("กรอกวันที่ด้วย");
	    		return false;
	    	}
	    	
	    
	    	
	    	
	    	 var button = document.getElementById("submits");
	           button.disabled = true;

	         
	    var formData = new FormData($("#"+ Mode2)[0]);
	    var $fileUpload = $("input[type='file']");

	        if (parseInt($fileUpload.get(0).files.length)>20){
	            alert("You can only upload a maximum of 20 files");
	    		return false;
	    	
	        }
	    $.ajax({
	        url: Mode,
	        type: "POST",
	        data: formData,
	           xhr: function() {
	                    var myXhr = $.ajaxSettings.xhr();
	                    if(myXhr.upload){
	                        myXhr.upload.addEventListener('progress',progress, false);
	                    }
	                    return myXhr;
	            },
	    processData: false,
	    contentType: false,
	    success: function(result){
	    $('#' + Mode3).html(result);
        evaluateAndDisplay();
        evaluateAndDisplay2();
        sum();
	    
	    
	    
        button.disabled = false;
	       }
	    });


	    return false;
	    }	


	    
	    

	    function progress(e){

	        if(e.lengthComputable){
	            var max = e.total;
	            var current = e.loaded;

	            var Percentage = (current * 100)/max;
	          //  console.log(Percentage);
	    $("#progress_div").css("display", "block");	
	    $("#bar").css("width",Percentage + "%");
	    var Per = Math.round(Percentage);
	    $('#percent').html(Per + "%");

	            if(Percentage >= 100)
	            {
	    $("#progress_div").css("display", "none");
	            }
	        }  
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	   function getdata(url,id){
	
		   unedit();
		 
		 var result = url.split("/")[1]; 
		 var data = ""; 
		  
		 if(result == "editdocument-filemanager"){
			   data="คุณต้องการเเก้ไขรายการนี้ใช่หรือไม่?"
		   }else if(result == "appdocument-filemanager"){
			   data="คุณต้องการอนุมัติรายการนี้ใช่หรือไม่?"
		   }else{
			   data="คุณต้องการลบรายการนี้ใช่หรือไม่?" 
		   }
			   
			 if ( confirm(data) ) {
	
	
					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {ID : id},
					        
					     success: function(data){
					    $("#savestatus").html(data);	
					    evaluateAndDisplay();
				        evaluateAndDisplay2();
				        sum();
					     },
					        error: function(jqXHR, textStatus, errorThrown) {
					            if (jqXHR.status === 401) {
					                // Redirect to login page if unauthorized
					                window.location.href = '/login'; // URL ของหน้า login
					            }      
					        }   
					});
			}
			 
			
			 
			 
		return false;
	    
		}
 
	   function getdata2(url,id){
					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {ID : id},
					        
					     success: function(data){
					    $("#savestatus").html(data);	

					     }
					});
			
			 
			
			 
			 
		return false;
	    
		}
 
	    </script>
	  
	  
	  
    <script>
    
    
    
   
  
    
  


    
   


    

    
    
    
    

    
    
    

    
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
</body>
 <script src="<c:url value="/lib/xlsx.full.min.js" />"></script> 

 <script>
    
const download = type => {

	  const el = document.getElementById("data-table");
	  const file = XLSX.utils.table_to_book(el);
	  // Configuring width of the columns:
	  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
	  return XLSX.writeFile(file, "UPLOAD." + type);
	};
	
	
	const download2 = type => {

		  const el = document.getElementById("data-table2");
		  const file = XLSX.utils.table_to_book(el);
		  // Configuring width of the columns:
		  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
		  return XLSX.writeFile(file, "UPLOAD." + type);
		};
		
		
		const download3 = type => {

			  const el = document.getElementById("data-table3");
			  const file = XLSX.utils.table_to_book(el);
			  // Configuring width of the columns:
			  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
			  return XLSX.writeFile(file, "UPLOAD." + type);
			};
			const download4 = type => {

				  const el = document.getElementById("tabledata");
				  const file = XLSX.utils.table_to_book(el);
				  // Configuring width of the columns:
				  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
				  return XLSX.writeFile(file, "UPLOAD." + type);
				};		
			
			
</script>


  <script>
  
  
  function changeRowColor(linkElement) {
	    var rowbodies = document.querySelectorAll('.rowbody');
	    
	    // กำหนด event listener สำหรับทุก .rowbody
	    rowbodies.forEach(function(rowbody) {
	        rowbody.addEventListener('click', function(event) {
	            rowbodies.forEach(function(body) {
	                body.style.backgroundColor = 'transparent'; // กำหนดสีพื้นหลังเป็น透วสีทั้งหมด
	                
	                body.classList.remove('selected-border');
	            });
	           // row.style.backgroundColor = 'rgba(0, 0, 255, 0.5)'; // เปลี่ยนสีพื้นหลังของ .rowbody ที่ถูกคลิกเป็นสีเหลือง
	            row.classList.add('selected-border');
	        });
	    });
	    
	    // หา <tr> ที่ประกอบไปด้วยลิงก์ที่ถูกคลิก
	    var row = linkElement.closest('tr');
	    
	    // กำหนดสีพื้นหลังของ <tr> ที่ถูกคลิกเป็นสีเหลือง
	    row.style.backgroundColor = '#F5F5F5';
	}
  
  
  
</script>
  
  
<script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>
    <script>
    
    function adddatasets(type,id,ids) {
 	   
 	   
 	   
	    if (type === "dataset") {
	    	 // ดึงค่า value ของ <select> โดยใช้ jQuery
	        var selectedValue = $("#dataset_data").val();

	        // หา <option> ที่มี value ตรงกับ selectedValue โดยใช้ jQuery
	        var selectedOption = $("#dataset_data option[value='"+id+"']");

	        // ดึงค่า data-custom จาก selectedOption โดยใช้ jQuery
	        var dataCustomValue = selectedOption.attr("data-custom");
	
		   return dataCustomValue;
	      
	    }
	    
	    return null;
        
	}
    

    
     function addfield(elementType,ids,label,datarefertype,datareferid,datarefercols,mainfield,count){

			 	  
			     if (elementType === 'select') {
			    	 var dataold = adddatasets(datarefertype,datareferid,ids);
			  	
			
			     }
			     
			     
			     if (elementType === 'checkbox') {
			    	 var dataold = adddatasets(datarefertype,datareferid,ids);
			   
			     }
			     
			
			 
			     if(dataold && elementType === 'select'){
			        	
			     	 
			 		      	 var selected = document.getElementById("data_value"+ids);
			 		      	 var values = dataold.split(',');
			 	
			 		       for (var i = 0; i < values.length; i++) {
			 		      	 
			 				      	 if (values[i].trim() !== '') {
			 				           // สร้าง <option> element
			 				           var option = document.createElement('option');
			 				           option.value = values[i];
			 				           option.textContent = values[i];
			 				           
			 				           // เพิ่ม <option> ลงใน <select>
			 				           selected.appendChild(option);
			 				      	 }
			 				           
			 		           
			 		       }
			
			        }
			      if(dataold && elementType === 'checkbox'){
			        	
			        	 
				      	 var checkbox = document.getElementById("data_value"+ids);
				      	 var values = dataold.split(',');
			
				      	 
				      	 $(checkbox).html(''); 
				      	 
				     	    var hiddenInput = document.createElement('input');
				     	    hiddenInput.type = 'hidden';
				          	hiddenInput.className = 'mydata';
				          	hiddenInput.value = "";
					        checkbox.appendChild(hiddenInput);
					    
				      	 
				      	 for (var i = 0; i < values.length; i++) {
				      		 
				      		    if (values[i].trim() !== '') {
				      		  
				      		    	
				      		        // สร้าง <input> element ประเภท checkbox
				      		        var checkboxs = document.createElement('input');
				      		        checkboxs.type = 'checkbox';
				      		        checkboxs.className = 'mydatas';
				      		        checkboxs.value = values[i];
				      		        checkboxs.name = 'data_value'+count;
				      		        
				      		        // สร้าง <label> element ที่มีค่าจาก values[i]
				      		        var label = document.createElement('label');
				      		        label.innerHTML = values[i]+'&nbsp;&nbsp;&nbsp;&nbsp;';
				      		        
				      		        // เพิ่ม <input> และ <label> ลงใน <span class="checkbox">
				      		        checkbox.appendChild(checkboxs);
				      		        checkbox.appendChild(label);
				      		        
				      		        // เพิ่มขีดจำนวนระหว่าง checkbox
				      		
				      		        
				      		        checkboxs.addEventListener('change', function() {
				      		        
				      		            var selectedValues = [];
				      		            var checkboxes = checkbox.querySelectorAll('.mydatas:checked');
				      		            checkboxes.forEach(function(checkbox) {
				      		       
				      		            	
				      		                selectedValues.push(checkbox.value);
				      		            });
				      		            hiddenInput.value = selectedValues.join(',');
				      		        });
				      		        
				      		    }
				      		    
				      		    
				      		  
				      		}
			
			 	  }
			      
      
      
    } 
    
     
     var element = document.getElementById("project");
     element.classList.add("active"); 
     
       </script>
  





<div class="modal fade" id="addDetail" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role" >
                        <div class="modal-content" id="use_detail">
                        </div>
                    </div>
                </div>

  



<div class="modal modal-slide-in sidebar-todo-modal fade show" id="new-task-modal"  aria-modal="true" role="dialog">
                            <div class="modal-dialog sidebar-lg">
                            </div>
                        </div>





<div class="modal fade" id="addRoleModal4" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role" >
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                               <div class="text-center mb-4">
                                    <h1 class="role-title"><spring:message code="work.assignwork"  /></h1>
                                    <p><spring:message code="work.tasksteam"  /></p> 
                                </div>
                                <!-- Add role form -->
                             <div id="divdata"></div>
                            
                       
                                <!--/ Add role form -->
                               <!--/ Add role form -->
                      <c:set var="listcheck" value="${Member_name}" />  
                             <c:set var="list2" value="${upload_pnsall.upload_pn_app}" /> 
                              <c:if test='${fn:contains(list,listcheck)}'>
                              
                         <c:if test='${upload_pn.project == 0}'>            
                       <a href="/run_project-filemanager?id=${upload_pn.upload_pn_id}" class="btn btn-sm btn-success" onclick="return confirm('Are you sure?')"> เริ่มโครงการ </a>    
                          </c:if>
                       
                         <c:if test='${upload_pn.project == 1}'>       
                      <a href="/close_project-filemanager?id=${upload_pn.upload_pn_id}" class="btn btn-sm btn-light" onclick="return confirm('Are you sure?')"> ปิดโครงการ </a>
                         </c:if>
                       
                                </c:if>
                            </div>
                        </div>
                    </div>
                </div>

  





  <div class="modal fade" id="addresourcesModal1" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role" >
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                               <div class="text-center mb-4">
                                    <h1 class="role-title"><spring:message code="work.manage_resources"  /></h1>
                                   
                                </div>
                             <form  id="formResourcesValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                            	<div id="saveresourcesmanage"></div>
                            
                            </form>
                            
                       
                  
                            </div>
                                <div class="modal-footer">
                                                        <button type="button" onclick='postdata("/saveresourcesmanager-filemanager?${_csrf.parameterName}=${_csrf.token}","formResourcesValidation","resources")' 
                    class="btn btn-primary add-todo-item me-1 waves-effect waves-float waves-light"   data-bs-dismiss="modal" aria-label="Close"><spring:message code="btn.save"  /></button>
                                              
             	
                        </div>
                    </div>
                </div>

        </div>







<div class="modal fade" id="addRoleModal" tabindex="-1" style="display: none;" aria-hidden="true">
                    <div class="modal-dialog modal-lg modal-dialog-centered modal-add-new-role">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-5 pb-5">
                               <div class="text-center mb-4">
                                    <h1 class="role-title"><spring:message code="work.assignwork"  /></h1>
                                    <p><spring:message code="work.tasksteam"  /></p> 
                                </div>
                                <!-- Add role form -->
                                
                                
                                <c:if test="${keyactivitys.size() == 0 }">
                              
                                <div class="alert alert-danger mt-1 alert-validation-msg" role="alert">
                                            <div class="alert-body d-flex align-items-center">
                                                <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-info me-50"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                                                <span><spring:message code="error.notfound"  /> <strong><spring:message code="project.key_activity"  /></strong>.   .</span>
                                            </div>
                                        </div>
                                </c:if>
                                
                                
                              <form:form method="POST" modelAttribute="task" action="/addtask-filemanager">
                                
                                
                                
                                
                                
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 <input  type="hidden" name="id" value="${id}">
                                  <input  type="hidden" name="step" value="${step}">
                                   <input  type="hidden" name="group" value="${group}">
                                
              
                                
                                <div  class="row">
                                    <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_name" /></label>
                                        <input type="text"  name="task_name"  id="task_name"  class="form-control" required >
                                    </div>
                                    <br>
                                    <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_note" /></label>
                                        <textarea  class="form-control" name="task_note" id="task_note"  rows="5"  required></textarea>
                                    </div>
                                                    
                                                    
                        <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="project.key_activity"  /></label>  
                      <select class=" form-select" name="keyactivity_id"  > 
                                       	
                                          	 <c:forEach items="${keyactivitys }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" >${row.expected_results}</option>
                                     		  </c:forEach>
                                       </select>
                         </div>
                                    
                                         <div class="col-12 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.outcome" /></label>
                                     
                                       
                      <select class="select2 form-select" name="outcome"  multiple="multiple"> 
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${outcomes }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" >${row.expected_results}</option>
                                     		  </c:forEach>
                                  
                                       </select>
                                       
                                    </div>
                                         <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.take_action" /></label>
                                       
                                        <select  class="form-select" name="task_payment">
                                       		 <option value="0">-</option>
                                             <c:forEach items="${milestones }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" >[${row.date_due }] ${row.detail} </option>
                                     		  </c:forEach>
                                  
                                       </select>
                                       
                                       
                                    </div>
                                    
                                    
                                         <div class="col-6 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_datestart" /></label>
                                       <input type="date"  name="task_datestart" id="task_datestart"   value="${tasks.task_datestart }" class="form-control" required >
                                    </div>
                                        <div class="col-6 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.task_dateend" /></label>
                                         <input type="date" name="task_dateend" id="task_dateend"    value="${tasks.task_dateend }" class="form-control" required >
                                    </div>
                                    
                                        
                                            <div class="col-12 mt-2">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget_source" /></label>
                                      
                                       <select  class="form-select" name="budget_source1">
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${budgets }" var="row" varStatus="count">  
                                     		  <option value="${row.id}"> ${row.detail} <spring:message code="project.budget" /> ${row.budget }</option>
                                     		  </c:forEach>
                                      
                                       </select>
                                       
                                    </div> 
                                   
                                 <div class="col-6 mt-2"  style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget" /></label>
                                       <input type="number" id="modalRoleName" name="task_budget1" class="form-control" min="0.0" value="${tasks.task_budget1 }"  >
                                    </div>
                                
                                           <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName">2.<spring:message code="task.budget_source" /></label>
                                      
                                       <select  class="form-select"  name="budget_source2">
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${budgets }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" > ${row.detail} <spring:message code="project.budget" /> ${row.budget }</option>
                                     		  </c:forEach>
                                    
                                       </select>
                                       
                                    </div> 
                                 <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget" /></label>
                                       <input type="number" id="modalRoleName" name="task_budget2" class="form-control" min="0.0" value="${tasks.task_budget2 }"  >
                                    </div>
                                    
                                    
                                    
                                          <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName">3.<spring:message code="task.budget_source" /></label>
                                              <select  class="form-select" name="budget_source3">
                                       		 <option value="0">-</option>
                                          	 <c:forEach items="${budgets }" var="row" varStatus="count">  
                                     		  <option value="${row.id}" > ${row.detail} <spring:message code="project.budget" /> ${row.budget }</option>
                                     		  </c:forEach>
                                       
                                       </select>
                                    </div>
                                 <div class="col-6 mt-2" style="display:none">
                                        <label class="form-label" for="modalRoleName"><spring:message code="task.budget" /></label>
                                       <input type="number" id="modalRoleName" name="task_budget3" class="form-control" min="0.0" value="${tasks.task_budget3 }"  >
                                    </div>
                                   
                                 
                                         <br>
                                    <div class="col-12 text-center mt-2">
                                        <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">บันทึก</button>
                                    </div>
                            </form:form>
                            
                 <link href="<c:url value="/lib/multiselect/css/jquery.multiselect.css" />" rel="stylesheet">   
  <link href="<c:url value="/lib/multiselect/css/app.css" />" rel="stylesheet">                                
 <script src="<c:url value="/lib/multiselect/js/jquery.multiselect.js" />"></script>
 <script src="<c:url value="/lib/multiselect/js/app.js" />"></script>
 

 
 
                                <!--/ Add role form -->
                            </div>
                        </div>
                    </div>
                </div>

  
  

      
  
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  













    