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
                                <h4 class="card-title">Notification</h4>
                                
                                
                                
                            </div>
                            <div class="card-body"> 
                            
                         <a href="/readall-notification" class="btn btn-info" >Read all</a>   
                            <br>   <br>
                            <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr>
                                            <th>ลำดับ</th>
                                          
                                            <th>วันที่</th>
                                            <th>การเเจ้งเตือน</th>
                                            <th>ประเภท</th>
                                            <th>ดำเนินการ</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${allnotification }" var="row" varStatus="count">
                                         
                     
                                        <tr style="background-color:<c:if test="${row.notification_read ==  1 }">#eee</c:if>">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.notification_date }</td>
                                            <td>${row.notification_alert }</td>
                                            <td>${row.notification_type }</td>
                                             <td>
                                                      <a href="/notification/${row.notification_id }"  class="btn btn-light">
							          <spring:message code="btn.view" text="ดู" /></a>                   
                                                                
                                             </td>
                                       
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                   </div>   </div>
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
</body>


