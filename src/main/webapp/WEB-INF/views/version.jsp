<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">

<title>Upload</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Version</h4>
                                </div>
                                <div class="card-body">
                                     
                                
                                
                             <div class="tab-pane active" id="faq-product-services" role="tabpanel" aria-labelledby="product-services" aria-expanded="false">
                                    <!-- icon and header -->
                                    <div class="d-flex align-items-center">
                                        <div class="avatar avatar-tag bg-light-primary me-1">
                                            <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-settings font-medium-4"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg>
                                        </div>
                                        <div>
                                            <h4 class="mb-0">Version : 3.0</h4>
                                            <span>2023-12-01</span>
                                        </div>
                                    </div>

                                    <!-- frequent answer and question  collapse  -->
                                    <div class="accordion accordion-margin mt-2" id="faq-product-qna">
                                    
                                        <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productOne12">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-one12" aria-expanded="false" aria-controls="faq-product-one">
                                                   Version : 3.0
                                                </button>
                                            </h2>

                                            <div id="faq-product-one12" class="accordion-collapse collapse" aria-labelledby="productOne12" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                	  -เพิ่ม E-form  (NEW)<br>
                                         		      -เพิ่ม Workflow Monitor (NEW)<br>
                                  					  -ปรับปรุง UX / UI ใหม่ให้ทันสมัย<br>
                                  					   -ปรับปรุงข้อมูลให้ผู้ใช้ทวนสอบระบบได้<br>
                                                </div>
                                            </div>
                                        </div>
                                    
                                          <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productOne11">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-one11" aria-expanded="false" aria-controls="faq-product-one">
                                                   Version : 2.01
                                                </button>
                                            </h2>

                                            <div id="faq-product-one11" class="accordion-collapse collapse" aria-labelledby="productOne11" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                	  -เพิ่มรายงานใหม่หน้า i-doc  (NEW)<br>
                                         		      -ปรับปรุงประสิทธิภาพรายงาน<br>
                                   
                                                </div>
                                            </div>
                                        </div>
                                    
                                         <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productOne10">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-one10" aria-expanded="false" aria-controls="faq-product-one">
                                                   Version : 2.00
                                                </button>
                                            </h2>

                                            <div id="faq-product-one10" class="accordion-collapse collapse" aria-labelledby="productOne10" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                	  -เพิ่มการอัพโหลดบันทึกหลายไฟล์ได้  (NEW)<br>
                                                  	  -เพิ่มกราฟ 8 กราฟ (NEW)<br>
                                         		      -เพิ่มรายงานใหม่ 1 รายงาน (NEW)<br>
                                         		      -เพิ่มหน้าค้นหาบันทึกคุณภาพเเบบละเอียด (NEW)<br>
                                         		        -เพิ่มตัวช่วยในการตั้งค่าสิทธิ์ (NEW)<br>
                                         		      -ปรับปรุงประสิทธิภาพการส่งเมล์<br>
                                         		       -ปรับปรุงประสิทธิในการตั้งค่าสิทธิ์<br>
                                                </div>
                                            </div>
                                        </div>
                                    
                                         <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productOne9">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-one9" aria-expanded="false" aria-controls="faq-product-one">
                                                   Version : 1.09
                                                </button>
                                            </h2>

                                            <div id="faq-product-one9" class="accordion-collapse collapse" aria-labelledby="productOne8" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                	  -แก้ปัญหาอัพใบ DAR บางชนิดไม่ได้<br>
                                                  	  -ปรับให้ Super Admin ทำได้เฉพาะการตั้งค่าเเละการเข้าถึงเท่านั้น เพื่อให้ใช้ User นี้ในส่วนการตั้งค่าสำคัญเท่านั้น<br>
                                         		  
                                                </div>
                                            </div>
                                        </div>
                                    <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productOne7">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-one7" aria-expanded="false" aria-controls="faq-product-one">
                                                   Version : 1.08
                                                </button>
                                            </h2>

                                            <div id="faq-product-one8" class="accordion-collapse collapse" aria-labelledby="productOne7" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                	  -เพิ่มการออกรายงานเเยกตามรายบุลคล<br>
                                                  	 -แก้ไขข้อผิดพลาดในการออกรายงานในวันที่ที่ไม่มีอยู่จริง<br>
                                         		   	 -แก้ไขข้อผิดพลาดการนับรายงานผิด<br>
                                     				  -แก้ไขข้อผิดพลาดการเเจ้งเตือนทางอีเมล์<br>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productOne7">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-one7" aria-expanded="false" aria-controls="faq-product-one">
                                                   Version : 1.07
                                                </button>
                                            </h2>

                                            <div id="faq-product-one7" class="accordion-collapse collapse" aria-labelledby="productOne7" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                   -แยกหมวดหมู่กราฟใหม่<br>
                                                   -เพิ่มการเก็บประวัติข้อมูล ประวัติ DAR ,ประวัติการใช้งานของผู้ใช้ ,ประวัติการอัพโหลดบันทึก,ประวัติการเข้าถึงเอกสารเเจกจ่าย เเละปรับปรุงให้ดีขึ้น <br>
                                                   -เพิ่มการนำออกเอกสารเป็น excel เเละเพิ่มการค้นหาในทุกหน้ารายการ <br>
                                                   -เพิ่มการบันทึกการเพิ่มเติ่มในหน้า ประวัติการเข้าถึงเอกสารเเจกจ่าย,หน้าข้อมูลการอัพโหลดบันทึก<br>
                                                   -เพิ่มกราฟใหม่ การ Login ใช้งาน,การ Login ใช้งานของพนักงานระดับต่างๆ,การสร้างใบ DAR ตามเหตุผล,เอกสารที่ครบอายุการจัดเก็บในแต่ละปี  <br>
                                                   -เพิ่มรายละเอียด  ข้อมูลวันที่ Backup , ข้อมูลการอัพเดตโปรเเกรม <br>
                                                    - เเก้ไขตำเเหน่งงาน ให้สามารถเลือก ระดับของพนักงาน ได้<br>
                                                   -ปรับเปลี่ยนคำพูดให้เหมาะสม <br>
                                 				    -เเก้ไขข้อผิดพลาด <br>
                                                   -ปรับเปลี่ยนตำเเหน่งเมนูการใช้งานใหม่  <br>
                                       
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productOne">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-one" aria-expanded="false" aria-controls="faq-product-one">
                                                   Version : 1.06
                                                </button>
                                            </h2>

                                            <div id="faq-product-one" class="accordion-collapse collapse" aria-labelledby="productOne" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                   -ปรับเปลี่ยนคำพูดให้เหมาะสม <br>
                                 				    -เเก้ไขข้อผิดพลาด <br>
                                                   -ปรับเปลี่ยนตำเเหน่งเมนูการใช้งานใหม่  <br>
                                                 
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productTwo">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-two" aria-expanded="false" aria-controls="faq-product-two">
                                                     Version : 1.05
                                                </button>
                                            </h2>
                                            <div id="faq-product-two" class="accordion-collapse collapse" aria-labelledby="productTwo" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                 -เเก้ไขข้อผิดพลาด
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productThree">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-three" aria-expanded="false" aria-controls="faq-product-three">
                                                        Version : 1.04
                                                </button>
                                            </h2>
                                            <div id="faq-product-three" class="collapse" aria-labelledby="productThree" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
						                -เเก้ไขข้อผิดพลาด
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productFour">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-four" aria-expanded="false" aria-controls="faq-product-four">
                                                    Version : 1.03
                                                </button>
                                            </h2>
                                            <div id="faq-product-four" class="accordion-collapse collapse" aria-labelledby="productFour" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                           -เเก้ไขข้อผิดพลาด
                                                </div>
                                            </div>
                                        </div>
                                           <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productFour4">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-four4" aria-expanded="false" aria-controls="faq-product-four">
                                                    Version : 1.02
                                                </button>
                                            </h2>
                                            <div id="faq-product-four4" class="accordion-collapse collapse" aria-labelledby="productFour4" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                          -เเก้ไขข้อผิดพลาด
                                                </div>
                                            </div>
                                        </div>
                                        
                                           <div class="card accordion-item">
                                            <h2 class="accordion-header" id="productFour5">
                                                <button class="accordion-button collapsed" data-bs-toggle="collapse" role="button" data-bs-target="#faq-product-four5" aria-expanded="false" aria-controls="faq-product-four">
                                                    Version : 1.01
                                                </button>
                                            </h2>
                                            <div id="faq-product-four5" class="accordion-collapse collapse" aria-labelledby="productFour5" data-bs-parent="#faq-product-qna" style="">
                                                <div class="accordion-body">
                                                 -เเก้ไขข้อผิดพลาด
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                          
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>

 




<%@ include file="common/mainfoot.jspf" %>  
  <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>

    <script>
    var element = document.getElementById("nav_set3");
    element.classList.add("active");  
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        

    </script>
</body>


