<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    
<c:set var="list" value="${permission }" />
<title>Group label</title>
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
                                    <h4 class="card-title">กลุ่มข้อมูล</h4>
                                   
                                      <sec:authorize access="hasAnyAuthority('ADD-UE')"> 
                                    <a href="/addgroup_label-condition"><button class="btn btn-primary waves-effect waves-float waves-light" >
                                    <spring:message code="btn.add"/></button></a>
                                     </sec:authorize> 
                                      
                                </div>
                                <div class="card-datatable">
                                    <table class="dt-multilingual table" id="example">
                                        <thead>
                                       <tr>
                                                <th scope="col"><spring:message code="label.no" text="ลำดับ" /></th>
                                                <th scope="col">ชื่อกลุ่มข้อมูล</th>
                        
                                                <th scope="col"><spring:message code="label.building_action" text="จัดการ" /></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                      <c:forEach items="${listgroup_label }" var="row" varStatus="count">
                                           
                                           
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>${row.group_label_name}</td>
                                                                <td>
                                      
                                                               
                                                    
                                   
							          <a href="/editgroup_label-condition/${row.id}" class="btn btn-info">
							          <spring:message code="btn.edit" text="แก้ไข" /></a>
	
           <a href="/delgroup_label-condition/${row.id}" class="btn btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />
           ${row.group_label_name}');"><spring:message code="btn.delete" text="ลบ" /></a>
				
                                        
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
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





<%@ include file="common/mainfoot.jspf" %>  
 
 
    <script>
    var element = document.getElementById("usermanage1");
    element.classList.add("active");  
    
        $(window).on('load', function() {
        	$('#example').DataTable(); 
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        

    </script>
</body>


