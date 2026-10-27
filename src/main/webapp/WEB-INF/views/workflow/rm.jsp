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
           max-width: 90px;
      min-width: 90px;
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
         max-width: 80px;
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
          
			
			      <div class="card-header">
                        <h3 >Meta columns</h3> 
                    </div>      
                                   
<div class="md-stepper-horizontal orange" id="step" >
  
 <div style="padding:3px;">

 <div id="box"> 
                              

     <div class="table-responsive">                                                             
<table class="table" style="width:100%">
                    <tr>
    
                    <th style="width:10%;font-size:12px;padding:3px">Table</th>
                    	<th style="font-size:12px;padding:3px">Column1</th>
					<th style="font-size:12px;padding:3px">Column2</th>
					<th style="font-size:12px;padding:3px">Column3</th>
						<th style="font-size:12px;padding:3px">Column4</th>
							<th style="font-size:12px;padding:3px">Column5</th>
								<th style="font-size:12px;padding:3px">Column6</th>
									<th style="font-size:12px;padding:3px">Column7</th>
										<th style="font-size:12px;padding:3px">Column8</th>	
									
					</tr>
                     
               
    
                        
                        
                        
                                 <tr>

	                        <td style="text-align: left;font-size:12px;padding:3px" rowspan="2" >
	                       1.production_directive
	                        </td>
	                         
	                         
	                         
	                 <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
					 <span class="badge bg-success">RM</span>	 production_directive
						    </td>   
		          <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
					 <span class="badge bg-success">RM</span>	 batch
						    </td>   
    		    <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
					 <span class="badge bg-success">RM</span>	 purchase_order
						    </td>
	                         
	                 
	                         
	                     <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 order_quantity
						    </td>    
						     <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 total_order_quantity
						    </td>
						     <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 delivery_date
						    </td> 
						    
						         <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">  
						    lead_time
						    </td>    
						        <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 <span class="badge bg-success">RM</span>  product
						    </td>
						  
	                </tr>
	             
	                 <tr>

    
          <td style="text-align: left; font-size:12px; padding:3px" title="หมายเลขคำสั่งผลิต">
			หมายเลขคำสั่งผลิต
   		 </td>     
   		 
   		       <td style="text-align: left; font-size:12px; padding:3px" title="หมายเลขคำสั่งผลิต">
		หมายเลขการแบ่งกลุ่มผลิต
   		 </td>     
   		  
        <td style="text-align: left; font-size:12px; padding:3px" title="หมายเลขใบสั่งซื้อ">
			หมายเลขใบสั่งซื้อ
   		 </td>

	
   		 
	        <td style="text-align: left; font-size:12px; padding:3px" title="ปริมาณที่สั่งผลิต">
			ปริมาณที่สั่งผลิต
   		 </td>    
   		 
   		 
	        <td style="text-align: left; font-size:12px; padding:3px" title="ปริมาณสั่งผลิตรวม">
			ปริมาณสั่งผลิตรวม
   		 </td>    
   		 
   		 
	        <td style="text-align: left; font-size:12px; padding:3px" title="วันที่ส่งมอบที่กำหนด">
			วันที่ส่งมอบที่กำหนด
   		 </td>    
   		 
   		 
	        <td style="text-align: left; font-size:12px; padding:3px" title="เวลานำส่ง">
			เวลานำส่ง
   		 </td>    
   		 		   <td style="text-align: left; font-size:12px; padding:3px" title="รายละเอียดของผลิตภัณฑ์***">
			รายละเอียดของผลิตภัณฑ์
   		 </td>     
   		 
   		     
                        </tr> 
                        
                  
                  
                        
                       <tr>
                    <td style="text-align: left;font-size:12px;padding:3px" rowspan="2" >
	                        2.wip & fg
	                        </td>
	                         
	          
						    <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 <span class="badge bg-success">RM</span>  wip
						    </td>
    		   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">batch_wip</td>
	          	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"> <span class="badge bg-success">RM</span> fg</td>
	          	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">batch_fg</td>
	  
	          	   	     <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">wip_incoming</td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">wip_deliver</td>           
	          	   	   	       <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">fg_incoming</td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">fg_deliver</td>   
	          	  
	                </tr>
	            
	                 <tr>

    
        <td style="text-align: left; font-size:12px; padding:3px" title="ชิ้นส่วน***">
				สินค้ากึ่งสำเร็จรูป
   		 </td>
	   <td style="text-align: left; font-size:12px; padding:3px" title="หมายเลขการแบ่งกลุ่มสินค้ากึ่งสำเร็จรูป">หมายเลขการแบ่งกลุ่มสินค้ากึ่งสำเร็จรูป</td>
	    <td style="text-align: left; font-size:12px; padding:3px" title="สินค้าสำเร็จรูป">สินค้าสำเร็จรูป</td>
	   	      <td style="text-align: left; font-size:12px; padding:3px" itle="หมายเลขการแบ่งกลุ่มสินค้าสำเร็จรูป"> หมายเลขการแบ่งกลุ่มสินค้าสำเร็จรูป</td>
	
	   	 	   	      <td style="text-align: left; font-size:12px; padding:3px" title="ยอดรับเข้า">ยอดรับเข้า</td>
	   	     <td style="text-align: left; font-size:12px; padding:3px" title="ยอดจ่ายออก">ยอดจ่ายออก</td>
	   	  	   	      <td style="text-align: left; font-size:12px; padding:3px" title="ยอดรับเข้า">ยอดรับเข้า</td>
	   	     <td style="text-align: left; font-size:12px; padding:3px" title="ยอดจ่ายออก">ยอดจ่ายออก</td>
	 
                        </tr>
                        
                        
                        
                            
                       <tr>
                    <td style="text-align: left;font-size:12px;padding:3px" rowspan="2" >
	                       3.material
	                        </td>
	                         
	          
						    <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 <span class="badge bg-success">RM</span>  material
						    </td>
    		   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">manufacturer</td>
	          	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">batch_material</td>
	          	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">incoming</td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">deliver</td>
	          	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">material_cost</td>
	          	   	   	            	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>     
	   	      	      	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>     
	   	      	      	        
	                </tr>
	            
	                 <tr>

    
        <td style="text-align: left; font-size:12px; padding:3px" title="ชื่อของวัตถุดิบ***">
			ชื่อของวัตถุดิบ
   		 </td>
	   <td style="text-align: left; font-size:12px; padding:3px" title="ชื่อผู้ผลิต">ชื่อผู้ผลิต</td>
	    <td style="text-align: left; font-size:12px; padding:3px" title="หมายเลขการแบ่งกลุ่มของวัตถุดิบ">หมายเลขการแบ่งกลุ่มของวัตถุดิบ</td>
	   	      <td style="text-align: left; font-size:12px; padding:3px" title="ยอดรับเข้า">ยอดรับเข้า</td>
	   	     <td style="text-align: left; font-size:12px; padding:3px" title="ยอดจ่ายออก">ยอดจ่ายออก</td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" title="ต้นทุนวัตถุดิบ">ต้นทุนวัตถุดิบ</td>
	   	   
	                       <td style="text-align: left; font-size:12px; padding:3px" ></td>  
	                             <td style="text-align: left; font-size:12px; padding:3px" ></td>  
	                         
                        </tr> 
                                      <tr>
                    <td style="text-align: left;font-size:12px;padding:3px" rowspan="2" >
	                       4.production
	                        </td>
	                         
	          
						    <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 <span class="badge bg-success">RM</span>  equipment
						    </td>
    		   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee" title="good_production_output">good_production_output</td>
	          	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee" title="defective_production_output">defective_production_output</td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">material_usage</td>     
	          	   	   	      	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee" title="material_batch_usage">material_batch_usage</td>  
	          	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">production</td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   	
	          	   	   	   	  
	          	   	   	   	   	             
	                </tr>
	            
	                 <tr>

    
        <td style="text-align: left; font-size:12px; padding:3px" title="ผลิตที่***">
			ผลิตที่
   		 </td>
	   <td style="text-align: left; font-size:12px; padding:3px" title="ปริมาณผลิตที่ดี">ปริมาณผลิตที่ดี</td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" title="ปริมาณผลิตที่เสีย">ปริมาณผลิตที่เสีย</td>
	  	   <td style="text-align: left; font-size:12px; padding:3px"  title="ปริมาณการใช้วัตถุดิบ">ปริมาณการใช้วัตถุดิบ</td>
	   	 	   	   <td style="text-align: left; font-size:12px; padding:3px" title="ใช้หมายเลขวัตถุดิบอะไร">ใช้หมายเลขวัตถุดิบอะไร</td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	             <td style="text-align: left; font-size:12px; padding:3px" ></td>      
	             
                        </tr>        
                        
                        
                        
                                    <tr>
                    <td style="text-align: left;font-size:12px;padding:3px" rowspan="2" >
	                       5.customer
	                        </td>
	                         
	          
						    <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">
						 <span class="badge bg-success">RM</span>  customer
						    </td>
    		   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">customer_info</td>
	          	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"> </td>
	          	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>               
	          	   	   	   	   	      <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>    
	                </tr>
	            
	                 <tr>

    
        <td style="text-align: left; font-size:12px; padding:3px" title="ชิ้นส่วน***">
			ชื่อของลูกค้า
   		 </td>
	   <td style="text-align: left; font-size:12px; padding:3px" title="ข้อมูลลูกค้า">ข้อมูลลูกค้า</td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	   	  	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	   	 	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	                 	   <td style="text-align: left; font-size:12px; padding:3px" ></td> 
                        </tr> 
                        
                        
                        
                         
                          <tr>
                    <td style="text-align: left;font-size:12px;padding:3px" rowspan="2" >
	                       6.inspection
	                        </td>
	                         
	          
			<td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">quality_control</td> 
    		   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">inspection_result</td>
	          	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">deviations</td>
	          	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>       
	          	   	   	   	   	     	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>               
	                </tr>
	            
	                 <tr>

    
    
	            	     <td style="text-align: left; font-size:12px; padding:3px" title="กระบวนการตรวจสอบ">กระบวนการตรวจสอบ</td>       
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" title="ผลการตรวจสอบ">ผลการตรวจสอบ</td>
	   	  	   	   	   <td style="text-align: left; font-size:12px; padding:3px" title="ปัญหาหรือการเบี่ยงเบน">ปัญหาหรือการเบี่ยงเบน</td>
	   	 	     <td style="text-align: left; font-size:12px; padding:3px" ></td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	                 
                        </tr>                
                        
                          <tr>
                    <td style="text-align: left;font-size:12px;padding:3px" rowspan="2" >
	                       7.common
	                        </td>
	                         
	          
			<td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">storage_space</td> 
    		   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">unit</td>
	          	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">start</td>
	          	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">end</td>
	          	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee">title</td>
	          	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>
	          	   	   	   	   	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>       
	          	   	   	   	   	     	   <td style="text-align: left; font-size:12px; padding:3px;background-color:#eee"></td>               
	                </tr>
	            
	                 <tr>

    
    
	            	     <td style="text-align: left; font-size:12px; padding:3px" title="พื้นที่จัดเก็บ">พื้นที่จัดเก็บ</td>       
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" title="ชนิดของหน่วยนับ">ชนิดของหน่วยนับ</td>
	   	  	   	   <td style="text-align: left; font-size:12px; padding:3px" title="วันเเละเวลาเริ่ม">วันเเละเวลาเริ่ม</td>
	   	 	   	   <td style="text-align: left; font-size:12px; padding:3px" title="วันเเละเวลาสิ้นสุด">วันเเละเวลาสิ้นสุด</td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" title="หัวข้อ">หัวข้อ</td>
	   	   	   <td style="text-align: left; font-size:12px; padding:3px" ></td>
	                 
                        </tr> 
                        
                        
                        
                        
              
</table>

<br><br>

 </div>


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
 
 
  <div class="modal fade" id="addNew2" tabindex="-1" aria-labelledby="exampleModalScrollableTitle"  aria-modal="true" >
                                                <div class="modal-dialog modal-lg modal-dialog-scrollable">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalScrollableTitle">RowMapper</h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        
                       <form method="POST"  modelAttribute="eformfield" action="/rowmapper-workflow">
                                                        <div class="modal-body">
                     
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 <input  type="hidden" name="datalink_workflow" value="${workflows.workflow_id }">
                  
         
         				<div id="mainkey"></div>
         
                                 
                        
                                                        <div class="modal-footer">
                                                        <a type="button"  href="/rm-workflow"
                                                class="btn btn-light me-1 waves-effect waves-float waves-light"><spring:message code="maindata.RM"  /></a>
                                                        
                                                            <button type="submit" 
                                                class="btn btn-primary me-1 waves-effect waves-float waves-light">Save</button>
                                                        </div>
                                              	</form>             
                                                        
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
                                                        
                                                                        <form method="POST"  modelAttribute="datalink" action="/addsublink-workflow">
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
                                 <c:if test="${workflows.workflow_type == 0}"> 
				                                 	 <optgroup label="<spring:message code="forms.type1" />">	 
				                                          <option value="rawmaterial">Rawmaterial</option>
				                                        <option value="production">Production</option>
				                                         <option value="finish_product">Finish Product</option>
				                                    </optgroup>
                            	</c:if>		 
                            			  
                            	<c:if test="${workflows.workflow_type == 1}"> 
                                 	 <optgroup label="<spring:message code="forms.type2" />">	 
                                    		<option value="goods_receipt">Goods receipt</option>
                                        		 <option value="purchase_requisition">Purchase requisition</option>
                                                 <option value="purchase_order">Purchase order</option>
                                       </optgroup>            
                                             
                                                         <optgroup label="<spring:message code="forms.type4" />">	 
                                                  <option value="requisition_form">Requisition form</option>
                                                     </optgroup>  
                                                     	 <optgroup label="<spring:message code="forms.type3" />">	        
                                                <option value="petty_cash_book">Petty cash book</option>
                                                        </optgroup> 
                                                        
                                 </c:if>                 
                                                         
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
	        
    var element = document.getElementById("nav_datalink");
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



