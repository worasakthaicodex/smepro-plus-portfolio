<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>  

<title>Error</title>
</head>
<body>
<body class="vertical-layout vertical-menu-modern blank-page navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="blank-page">
    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper">
            <div class="content-header row">
            </div>
            <div class="content-body">
                <!-- Error page-->
                <div class="misc-wrapper"><a class="brand-logo" href="#">
                       
                     
                  
                    <div class="misc-inner p-2 p-sm-3">
                        <div class="w-100 text-center">
                            <h1 class="mb-1">Error code ${error}    🕵🏻‍♀️ <a href="/"> <spring:message code="error.00" text="( กรุณากดย้อนกลับไปเเก้ไข )" /></a></h1>
                            <h2 class="mb-2">
                            
                            
                            Oops! 😖 
                             <c:if test="${error.equals('error 01') }">
                           <spring:message code="error.01" text="จำนวน Drives หรือ Folder มีจำนวนเต็มลิมิตเเล้ว" />
                            </c:if>
                            <c:if test="${error.equals('error 02') }">
                           <spring:message code="error.02" text="ขนาดพื้นที่ไม่เพียงพอ" />
                            </c:if>
                             <c:if test="${error.equals('error 05') }">
                           <spring:message code="error.05" text="คุณกำลังพยายามเปลี่ยนเส้นทางที่ไม่ถูกต้อง" />
                            </c:if>
                               <c:if test="${error.equals('error 07') }">
                           <spring:message code="error.07" text="คุณกำลังพยายามลบ Drives ที่มีข้อมูล" />
                            </c:if>
                                  <c:if test="${error.equals('error 08') }">
                         <spring:message code="error.08" text="ชนิดไฟล์ไม่ถูกต้อง" />
                    
                            </c:if>
                            
                         <c:if test="${error == null}">
                         <spring:message code="error.06" text="คุณเรียกหน้าไม่ถูกต้องหรือไม่มีสิทธิ์ในหน้านี้" />
                               <BR><BR>The requested URL was not found on this server  or 
                            </c:if>
                            
                                     <c:if test="${error.equals('error 09') }">
                         <spring:message code="error.09" text="หมายเลขเอกสารใหม่มีอยู่เเล้วในระบบ" />
                         
                            </c:if>
                          
                                         <c:if test="${error.equals('error 10') }">
                                         
                                      
                                         
                         <spring:message code="error.10" text="ไม่พบหมายเลขเอกสารนี้ในระบบ" />
                        
                          
                          
                            </c:if>
                         
                    <c:if test="${error.equals('error 11') }">
                         <spring:message code="error.11" text="คำขอนี้จำเป็นต้องกรอกหมายเลขเอกสารเดิม" />
                         
                            </c:if>  
                         
                                             <c:if test="${error.equals('error 12') }">
                         <spring:message code="error.12" text="คำขอนี้จำเป็นต้องกรอกหมายเลขเอกสารเใหม่" />
                         
                            </c:if>
                            
                            
                         <c:if test="${error.equals('error 13') }">
                         <spring:message code="error.13" text="คุณต้องกำหนดตำเเหน่งเอกสาร" />
                         
                            </c:if>
                            </h2>
                            
                           
       
                            <img class="img-fluid" src="/resources/app-assets/images/pages/error-dark.svg" alt="Error page" />
                            
                            
                            
                        </div>
                    </div>
                </div>
                <!-- / Error page-->
            </div>
        </div>
    </div>
    <!-- END: Content-->


    <!-- BEGIN: Vendor JS-->
    <script src="../../../app-assets/vendors/js/vendors.min.js"></script>
    <!-- BEGIN Vendor JS-->

    <!-- BEGIN: Page Vendor JS-->
    <!-- END: Page Vendor JS-->

    <!-- BEGIN: Theme JS-->
    <script src="../../../app-assets/js/core/app-menu.js"></script>
    <script src="../../../app-assets/js/core/app.js"></script>
    <!-- END: Theme JS-->

    <!-- BEGIN: Page JS-->
    <!-- END: Page JS-->

    <script>
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

</body>
</html>