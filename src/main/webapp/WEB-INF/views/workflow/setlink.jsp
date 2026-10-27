<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined" />



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
<title>Link</title>


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
                                    <li class="breadcrumb-item active">Workflow Setting
                                    </li>
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
           
            </div> 

     
     
<div class="row">



  <c:forEach items="${process }" var="row" varStatus="count">
	       <div class="accordion accordion-border" id="accordionBorder" data-toggle-hover="true">
                                            <div class="accordion-item">
                                                <h2 class="accordion-header" id="headingBorderOne">
                                                    <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#accordionBorderOne${count.count }" aria-expanded="false" aria-controls="accordionBorderOne">
                                           ${row.name }
                                                    </button>
                                
                                                </h2>
                
                      
                                                         
                                                
                                                
                                                <div id="accordionBorderOne${count.count }" class="accordion-collapse collapse" aria-labelledby="headingBorderOne" data-bs-parent="#accordionBorder" style="">
                                                    <div class="accordion-body" >
                                                                   
                             
   
        <div >
                                 <div class="d-flex align-items-center justify-content-between">
                                    
      <div class="form-check" style="display: flex; align-items: center; gap: 8px;">
  <div class="badge rounded-pill badge-light-primary">
    <span class="material-symbols-outlined">calculate</span>
  </div>
  <label style="margin: 0;">Calculation</label>
</div> 
                                              <span class="badge">
                                             
                                    
                                    
                                          <div class="dropdown blog-detail-share">
                                                        
                                                               
                                                        
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle waves-effect waves-float waves-light" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div class="dropdown-menu" style="">
                               
                                    <a class="dropdown-item" 
                                   onclick="getdata('/setcalculation-workflow/${row.id}/${id }','divlink')"
                                    data-bs-toggle="modal" data-bs-target="#link" >
                                    การคำนวณผลรวมของเเถว
                                    </a>
  									   <a class="dropdown-item" 
                                   onclick="getdata('/settablecalculation-workflow/${row.id}/${id }','divlink')"
                                    data-bs-toggle="modal" data-bs-target="#link" >
                                    การคำนวณท้ายตาราง</a>
                                    
                            		
                           
                                 
                                     </div>
                            </div>
                            
                   
                                           
                            
                                           
                                            </div>
                                    
                                            </span>
                                     
                                        </div>
                                    </div>
                            
                            
                            
                                     <table style="width:40%;margin-left:5%;border:0px">
                                     
                                           <c:forEach items="${row.rowcalculation }" var="rowcalculation" >   
                                           
                                              
                                           <tr style="border:0px"><td style="border:0px;width:90%;"> - ${rowcalculation.row_name }</td>
                                            <td style="border:0px"> 
                                            
                                             <a href="#"  onclick="getdata('/setcalculation-workflow/${row.id}/${id }/${rowcalculation.id }','divlink')" type="button" class="btn  btn-sm btn-primary waves-effect waves-float waves-light" 
                                             style="padding:3 5 3 5px;">
                                                 <span class="material-symbols-outlined" style="font-size:12px"   data-bs-toggle="modal" data-bs-target="#link">edit</span></a>
                                            
                                              <a href="/delcalculation-workflow/${rowcalculation.id }/${id}" type="button" class="btn  btn-sm btn-danger  waves-effect waves-float waves-light" 
                                            onclick="return confirm('are you sure delete?')" style="padding:3 5 3 5px"> X </a>
                                                 
                                                 </td>  </tr>
                                              
                                                </c:forEach> 
                                                 
                                                  <c:forEach items="${row.footercalculation }" var="rowfootercalculation" >   
                                           
                                              
                                           <tr style="border:0px"><td style="border:0px;width:90%;"> - ${rowfootercalculation.footer_name }</td>
                                            <td style="border:0px"> 
                                            
                                             <a href="#"  onclick="getdata('/settablecalculation-workflow/${row.id}/${id }/${rowfootercalculation.id }','divlink')" type="button" class="btn  btn-sm btn-primary waves-effect waves-float waves-light" 
                                             style="padding:3 5 3 5px;">
                                                 <span class="material-symbols-outlined" style="font-size:12px"   data-bs-toggle="modal" data-bs-target="#link">edit</span></a>
                                            
                                              <a href="/deltablecalculation-workflow/${rowfootercalculation.id }/${id}" type="button" class="btn  btn-sm btn-danger  waves-effect waves-float waves-light" 
                                            onclick="return confirm('are you sure delete?')" style="padding:3 5 3 5px"> X </a>
                                                 
                                                 </td>  </tr>
                                              
                                                </c:forEach> 
                                                 
                                            </table>
                                  
                                       
                                          <br>
                                       
                               <div class="business-item">
                                 <div class="d-flex align-items-center justify-content-between">
                                    
      <div class="form-check" style="display: flex; align-items: center; gap: 8px;">
  <div class="badge rounded-pill badge-light-primary">
<span class="material-symbols-outlined">
stethoscope_check
</span>
  </div>
  <label style="margin: 0;">Incoming Links</label>
</div> 
                                    <span class="badge">
                                             
                                    
                                    
                                          <div class="dropdown blog-detail-share">
                                                        
                                                               
                                                        
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle waves-effect waves-float waves-light" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div class="dropdown-menu" style="">
                               
                             
                                    <a class="dropdown-item" 
                                   onclick="getdata('/setlinktolink-workflow/${row.id}/${id }','divlink2')"
                                    data-bs-toggle="modal" data-bs-target="#link2" >
                                   ตั้งค่าการลิงค์
                                    </a>
  								
                            		
                           
                                 
                                     </div>
                            </div>
                            
                   
                                           
                            
                                           
                                            </div>
                                    
                                            </span>
                                           
                                        </div>
                                    </div>                
                                     
                                        <table style="width:40%;margin-left:5%;border:0px">
                                         <c:forEach items="${row.inlinks }" var="rowinlinks" > 
                                       
                                           <tr style="border:0px">
                                           <td style="border:0px;width:90%;"> - ${rowinlinks.link_name }</td>
                                         
                                      
                                            <td style="border:0px">   <a  class="btn  btn-sm btn-primary me-1 waves-effect waves-float waves-light" 
                                             onclick="getdata('/setcalculationlinks-workflow/${rowinlinks.id_refer}/${id }/${rowinlinks.id }','divlink')"
                                    data-bs-toggle="modal" data-bs-target="#link" 
                                             style="padding:3 5 3 5px">
                                                 +</a>
                                                 
                                                 
                                                 </td>  </tr>
                                            </table>
                                            
                                            
                                     <table style="width:40%;margin-left:5%;border:0px">
                                       <c:forEach items="${rowinlinks.rollup_calculation }" var="rowRollup" > 
                                           <tr style="border:0px"><td style="border:0px;width:70%;padding-left:50px;"> Calculation : ${rowRollup.rollup_name }</td>
                                            <td style="border:0px">   
                                            
                                            
                                         <a href="#" onclick="getdata('/setcalculationlinks-workflow/${rowinlinks.id_refer}/${id }/${rowinlinks.id }/${rowRollup.id }','divlink2')"
                                    data-bs-toggle="modal" data-bs-target="#link2" 
                                            type="button" class="btn  btn-sm btn-primary  waves-effect waves-float waves-light" 
                                       style="padding:3 5 3 5px">
                                                <span class="material-symbols-outlined" style="font-size:12px"  >edit</span></a>
                                                 
                                                     <a href="/delcalculationlinks-workflow/${rowRollup.id }/${id}" type="button" class="btn  btn-sm btn-danger  waves-effect waves-float waves-light" 
                                            onclick="return confirm('are you sure delete?')" style="padding:3 5 3 5px"> X </a>
                                                 </td>
                                                 </td>
                                                 
                                                 
                                                   </tr>
                                                 
                                                 </c:forEach>    
                                                 
                                                 
                                            </table>
                                      </c:forEach>
                                    <br>
                                    
                                                                <div class="business-item">
                                 <div class="d-flex align-items-center justify-content-between">
                                    
      <div class="form-check" style="display: flex; align-items: center; gap: 8px;">
  <div class="badge rounded-pill badge-light-primary">
   <span class="material-symbols-outlined">
stethoscope_arrow
</span>
  </div>
  <label style="margin: 0;">Outgoing Links</label>
</div> 
                                             <span class="badge">
                                               <div class="dropdown blog-detail-share">
                                                        
                                                               
                                                        
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle waves-effect waves-float waves-light" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div class="dropdown-menu" style="">
                               
                               
                                     <a class="dropdown-item" 
                                   onclick="getdata('/setlinksline-workflow/${row.id}/${id }','divlink2')"
                                    data-bs-toggle="modal" data-bs-target="#link2" >
                                สร้างการลิงค์
                                    </a>
                                
  								
                            		
                           
                                 
                                     </div>
                            </div>
                            
                   
                                           
                            
                                           
                                            </div>
                                            </span>
                                        </div>
                                    </div>  
                                    
                                   <table style="width:40%;margin-left:5%;border:0px">
                                          
                                          
                          <c:forEach items="${row.links }" var="rowlinks" >    
                                           <tr style="border:0px">
                                           <td style="border:0px;width:90%;"> - ${rowlinks.link_name }</td>
                                         
                                      
                                            <td style="border:0px">   <a href="#" onclick="getdata('/setlinksline-workflow/${row.id}/${id }/${rowlinks.id }','divlink2')"
                                    data-bs-toggle="modal" data-bs-target="#link2" 
                                            type="button" class="btn  btn-sm btn-primary  waves-effect waves-float waves-light" 
                                       style="padding:3 5 3 5px">
                                                <span class="material-symbols-outlined" style="font-size:12px"  >edit</span></a>
                                                 
                                                     <a href="/dellinksline-workflow/${rowlinks.id }/${id}" type="button" class="btn  btn-sm btn-danger  waves-effect waves-float waves-light" 
                                            onclick="return confirm('are you sure delete?')" style="padding:3 5 3 5px"> X </a>
                                                 </td>  </tr>
                                            </table>
                                     <table style="width:40%;margin-left:5%;border:0px">
                                           <tr style="border:0px"><td style="border:0px;width:70%;padding-left:50px;"> ${rowlinks.link_name }</td>
                                            <td style="border:0px">   <a  href="#" onclick="getdata('/setfeels-workflow/${row.id}/${id }/${rowlinks.id }','divlink2')"
                                    data-bs-toggle="modal" data-bs-target="#link2" type="button" class="btn  btn-sm btn-primary waves-effect waves-float waves-light" 
                                         style="padding:3 5 3 5px">
                                              <span class="material-symbols-outlined" style="font-size:12px"  >edit</span></a></td>  </tr>
                                                 
                           </c:forEach>
                                                 
                                                 
                                            </table>
     
   <br>
   
   
    
        
        
          </div>
                                                
                                                    </div>
                                                </div>
                                    	                
                                                
                                                
                                                
                                            </div> 
                                             	</c:forEach>
                                           </div>
                                          
                                          
                                    
                                        </div>



<script>
document.addEventListener("DOMContentLoaded", function() {
    const accordions = document.querySelectorAll('.accordion-button');
    const scrollToId = sessionStorage.getItem("accordion-scroll-to");

    // ถ้ามี accordion ที่เคยเปิดไว้
    if (scrollToId) {
        const target = document.getElementById(scrollToId);
        if (target) {
            const collapse = new bootstrap.Collapse(target, {
                toggle: true
            });

            // scroll ไปยัง accordion ที่เคยเปิด
            setTimeout(() => {
                target.scrollIntoView({ behavior: "smooth", block: "center" });
            }, 300);
        }
        sessionStorage.removeItem("accordion-scroll-to");
    }

    // เมื่อมีการคลิก accordion ให้บันทึก id ลง sessionStorage
    accordions.forEach(button => {
        button.addEventListener('click', function() {
            const targetId = this.getAttribute('data-bs-target').substring(1); // ตัด #
            sessionStorage.setItem("accordion-scroll-to", targetId);
        });
    });
});
</script>

<div class="modal fade" id="link" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                              <h4 class="modal-title" id="myModalLabel33">Calculation</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                     <div id="divlink"></div>
                         </div>    
                               
                    </div>
                                        </div>



<div class="modal fade" id="link2" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
 
                    <div class="modal-dialog   modal-lg modal-dialog-centered">
                         <div class="modal-content">
                                                        <div class="modal-header">
                                                              <h4 class="modal-title" id="myModalLabel33">Link</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                     <div id="divlink2"></div>
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

	   function getdata(url,div){
			        $.ajax({
			        type:'GET',
			        url:url,
			     success: function(data){
			    $("#"+div).html(data);	
		
			     }
			});
				return false;
		}
    </script>				



