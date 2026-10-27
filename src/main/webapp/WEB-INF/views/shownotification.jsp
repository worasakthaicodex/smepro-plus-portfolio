<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Notification</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
      
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">การเเจ้งเตือน ${notification2.notification_type }</h4>
                                </div>
                                <div class="card-body">
                                    ${notification2.notification_iduser }  ${notification2.notification_alert }
                                    
                                      <br>
                                      ${notification2.notification_note } 
                                    <br> <br>
                                      ${notification2.notification_date } 
                                      
                                         <br> <br>
                                         
                                         
                                         
                                                 <c:choose>
                                <c:when test='${notification2.notification_type == "Job queue" or notification2.notification_type == "Job queue error"  }'>
                                               
                                               <c:if test="${report_pn.report_pn_id > 0}">      
                                                   <a href="/duepn-filemanager?id=${report_pn.report_pn_idrefer}&&step=0&&group=3"  class="btn btn-light">
							          <spring:message code="btn.view" text="ดู" /></a>    
							                   </c:if>
							              <c:if test="${report_pn.report_pn_id == null}">      
                                   Record deleted
							                   </c:if>
     
                                </c:when>


  					<c:when test='${notification2.notification_type == "Job queue all" }'>
                                               
                                               <c:if test="${upload_pn.upload_pn_id > 0}">      
                                                   <a href="/duepn-filemanager?id=${upload_pn.upload_pn_id}&&step=0&&group=3"  class="btn btn-light">
							          <spring:message code="btn.view" text="ดู" /></a>    
							                   </c:if>
							              <c:if test="${upload_pn.upload_pn_id == null}">      
                                   Record deleted
							                   </c:if>
     
                                </c:when>

                              <c:when test='${notification2.notification_type == "Dar workflow (CREATE)"  or notification2.notification_type == "Dar workflow (Disapproved)"  }'>
                                                   <a href="/darcheck-dar"  class="btn btn-light">
							          <spring:message code="btn.view" text="ดู" /></a>         
                                </c:when> 
                                    <c:when test='${notification2.notification_type == "Dar workflow (passed)"  }'>
                                                   <a href="/darapp-dar"  class="btn btn-light">
							          <spring:message code="btn.view" text="ดู" /></a>         
                                </c:when> 
                              
                                    <c:when test='${notification2.notification_type == "Dar workflow (failed)"  }'>
                                                   <a href="/daradd-dar"  class="btn btn-light">
							          <spring:message code="btn.view" text="ดู" /></a>         
                                </c:when> 
                               
                                 <c:when test='${notification2.notification_type == "You have pending project activities."  }'>
                                                   <a href="/task-filemanager/${notification2.notification_refer }"  class="btn btn-light">
							          <spring:message code="btn.view" text="ดู" /></a>         
                                </c:when> 
                                <c:otherwise>
                              
                              </c:otherwise>
                              
                              
                              
                              
                               </c:choose> 
                                         
                                         
                     
							          
							          
							          
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
    </script>
</body>


