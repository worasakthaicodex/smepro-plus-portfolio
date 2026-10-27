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
                                    <h4 class="card-title">Backup</h4>
                                </div>
                                <div class="card-body">
                                     
                                
                           <div class="table-responsive">
                                <table class="table table-borderless">
                                    <thead>
                                        <tr>
                                            <th><spring:message code="common.date" text="วันที่"  /> </th>
                                            <th><spring:message code="common.service" text="บริการ"  /> </th>
                                             <th><spring:message code="common.note" text="บันทึกเพิ่มเติม"  /> </th>
                                             <th><spring:message code="common.by"/> </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                     <c:forEach items="${backup }" var="row" varStatus="count">
                                         
                     
                                        <tr>
                                            <td>${row.backup_date }</td>
                                            <td>${row.backup_service }</td>
                                            <td>${row.backup_note }</td>
                                            <td>${row.backup_name }</td>
                                           
                                        </tr>
                                       </c:forEach>
                                    </tbody>
                                </table>
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

    <script>
    
    
    $(window).on('load', function() {
        if (feather) {
            feather.replace({
                width: 14,
                height: 14
            });
        }
    })
    
    
    var element = document.getElementById("nav_set4");
    element.classList.add("active");  
    
    
   
        

    </script>
</body>


