<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Profile</title>
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
                
                  <section id="multilingual-datatable">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title"> รายงานเเยกตามรายบุคคล </h4>
                                   
                           		
                                </div>
                                <div class="card-datatable">
                                    <table class="dt-multilingual table" id="example">
                                        <thead>
                                         <tr>
                                                <th scope="col"><spring:message code="label.no" text="ลำดับ" /></th>
                                                 <th scope="col"><spring:message code="label.username" text="ชื่อผู้ใช้" /></th>
                                                <th scope="col"><spring:message code="label.name" text="ชื่อ" /></th>  
                                                 <th scope="col"><spring:message code="usermanage.role" text="บทบาท" /></th>       
                                                     <th scope="col"><spring:message code="tables.department" text="หน่วยงาน" /></th>   
                                                                     
                                                <th scope="col"><spring:message code="label.building_action" text="จัดการ" /></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                      <c:forEach items="${user }" var="row" varStatus="count">
                                           
                                           
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>${row.username}</td>
                                                <td>${row.user_name}</td>
                                                <td>${row.group_name}</td>
                                                     
                                                  <td>${row.group_department}</td>         
                                                   
                                                                <td>
                                                                
                                                                
                                                         
                                                                
                                                        
  				          <a href="/showreportuser-filemanager?id=${row.user_id}" class="btn btn-info">
							          <spring:message code="btn.view" /></a>
						
							          
					 
                                    
                                        
                                                </td>
                                                    </tr>
                                         
                                              </c:forEach>
                                        
                                        
                                        
                                        
                                    
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
         
                
        </div>
            </div>
        
              
              

            </div>
        </div>
    </div>
    <!-- END: Content-->


<%@ include file="common/mainfoot.jspf" %>  
 
 
    <script>
        $(window).on('load', function() {
        	$('#example').DataTable(); 
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
            var element = document.getElementById("nav_other8");
  element.classList.add("active");      

    </script>
</body>

