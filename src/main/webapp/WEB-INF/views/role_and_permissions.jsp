<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    
<c:set var="list" value="${permission }" />
<title>Role</title>
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
                                    <h4 class="card-title"><spring:message code="nav.Roles" text="ตำแหน่งงานและสิทธิ์" /> </h4>
                                      <c:set var="listcheck" value="SET-UE" />
                           		  <sec:authorize access="hasAnyAuthority('ADD-UE')">  
                                    <a href="/addrole-usermanage"><button class="btn btn-primary waves-effect waves-float waves-light" ><spring:message code="btn.add"/></button></a>
                                 </sec:authorize> 
                                </div>
                                <div class="card-datatable">
                                    <table class="dt-multilingual table" id="example">
                                        <thead>
                                       <tr>
                                                <th scope="col"><spring:message code="label.no" text="ลำดับ" /></th>
                                                <th scope="col"><spring:message code="usermanage.role" text="บทบาท" /></th>
                                                
                        						<th scope="col"><spring:message code="tables.JobLevel" text="" /></th>
                        						 <th scope="col"><spring:message code="tables.department" text="" /></th>
                                                <th scope="col"><spring:message code="label.building_action" text="จัดการ" /></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                      <c:forEach items="${groups }" var="row" varStatus="count">
                                           
                                           
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>${row.group_name}</td>
                                                  <td>${row.group_level}</td>
                                                 <td>${row.group_department}</td>
                                                  
                                                                <td>
                                             
                               <sec:authorize access="hasAnyAuthority('SET-RUE')">                    
                                               <a href="/setrole-usermanage?id=${row.id}" class="btn btn-light">
							          <spring:message code="btn.set" text="ปรับค่า" /></a>                   
                               </sec:authorize> 
                                                               
                                                    
                                    <sec:authorize access="hasAnyAuthority('EDIT-RUE')">                             
							          <a href="/editrole-usermanage?id=${row.id}" class="btn btn-info">
							          <spring:message code="btn.edit" text="แก้ไข" /></a>
							         </sec:authorize> 
							      
							      
							               
                                           <sec:authorize access="hasAnyAuthority('DELETE-RUE')">     
							          
           <a href="/deleterole-usermanage/${row.id}" class="btn btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.group_name}');"><spring:message code="btn.delete" text="ลบ" /></a>
								 
                                 </sec:authorize> 
                                        
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
        $(window).on('load', function() {
        	$('#example').DataTable(); 
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
            var element = document.getElementById("usermanage2");
  element.classList.add("active");      

    </script>
</body>


