<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="common/mainhead.jspf" %>    


    <link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">
    <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
     <link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">

<title>Dar</title>
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
          
  <section id="multilingual-datatable">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header border-bottom">
                                    <h4 class="card-title">${type }</h4>
                                     <c:if test="${type.equals('Review and add')}">       
                                        <sec:authorize access="hasAnyAuthority('ADD-DAR')">   
                                    <a href="/add-dar"><button class="btn btn-primary waves-effect waves-float waves-light" ><spring:message code="btn.add"  /></button></a>
                                 </sec:authorize> 
                                </c:if>
                                </div>
                                
                
                             
                                <hr class="my-0" />
                                <div class="card-datatable">
                                    <table class="dt-advanced-search table" id="datatable">
                                        <thead>
                                       <tr>
                                                <th scope="col"><spring:message code="label.no" text="ลำดับ" /></th>
                                                <th scope="col"><spring:message code="dar.id" text="หมายเลขเอกสารใหม่" /></th>
                                                <th scope="col"><spring:message code="dar.name" text="ชื่อเอกสาร" /></th>
                                                 <th scope="col"><spring:message code="dar.Request" text="วัตถุประสงค์" /></th>
                                                  <th scope="col"><spring:message code="dar.Type" text="ชนิดเอกสาร" /></th>
                                                      <th scope="col"><spring:message code="dar.status" text="สถานะ" /></th>
                                                        <th scope="col"><spring:message code="label.date" text="วันที่" /></th>
                                                <th scope="col"><spring:message code="label.building_action" text="จัดการ" /></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                      <c:forEach items="${dar }" var="row" varStatus="count">
                                           
                                              <c:if test="${row.dar_request.equals('request1')}">    
                               <spring:message code="dar.request1" text="ขอออกเอกสารใหม่" var="request" />
                               </c:if>  
                                <c:if test="${row.dar_request.equals('request2')}">    
                              <spring:message code="dar.request2" text="ขอเเก้ไขเอกสาร" var="request"/>
                               </c:if>  
                                <c:if test="${row.dar_request.equals('request3')}">    
                            <spring:message code="dar.request3" text="ขอยกเลิกเอกสาร" var="request"/>
                               </c:if>    
                                       <c:if test="${row.dar_request.equals('request4')}">    
                            <spring:message code="dar.request4" text="ขอยกเลิกเอกสาร" var="request"/>
                               </c:if>
                                 <c:if test="${row.dar_flow == 0}">    
                                <spring:message code="dar.flow" text="ระหว่างจัดทำ" var="flow"/>
                               </c:if>
                                  <c:if test="${row.dar_flow == 1}">    
                                <spring:message code="dar.flow1" text="รออนุมัติ" var="flow"/>
                               </c:if>
                                <c:if test="${row.dar_flow == -1}">    
                                <spring:message code="dar.flow2" text="ไม่อนุมัติ" var="flow"/>
                               </c:if>
                                 <c:if test="${row.dar_flow == 2}">    
                                <spring:message code="dar.flow3" text="รอเเจกจ่าย" var="flow"/>
                               </c:if>
                                <c:if test="${row.dar_flow == -2}">    
                                <spring:message code="dar.flow4" text="ไม่เเจกจ่าย" var="flow"/>
                               </c:if>
                               <c:if test="${row.dar_flow == 3}">    
                                <spring:message code="dar.flow5" text="เสร็จสิ้น" var="flow"/>
                               </c:if>
                                            <tr>
                                                <td>${count.count}</td>
                                                <td>
                                                            <c:if test="${row.dar_request.equals('request1')}">    
                               ${row.dar_doc_id}
                               </c:if>  
                                <c:if test="${!row.dar_request.equals('request1')}">    
                               ${row.dar_docold_id} 
                               </c:if>       
                                              
                                                
                                                </td>
                                                <td>${row.dar_doc_name}</td>
                                                <td>${request }</td>
                                                <td>${row.dar_type}</td>
                                                <td>${flow}</td>
                                                         <td>${row.dar_date_add}</td>
                                                                <td>
                                          <c:if test="${row.dar_flow == 0 or row.dar_flow == 3 or row.dar_flow == -1 }">                       
		                                      <sec:authorize access="hasAnyAuthority('ADD-DAR')">           
		                                     <a href="/show-dar?id=${row.dar_id}&nav=${nav_dar}" class="btn btn-sm btn-light">
									          <spring:message code="btn.view" text="ดู" /></a>  
									         </sec:authorize>                 
                                          </c:if>    
                                         
                                                  <c:if test="${row.dar_flow == 1 or row.dar_flow == -2 or userid == row.dar_id_add and row.dar_flow == 1 or row.dar_flow == -2  and userid == row.dar_id_add}">                     
		                                       <sec:authorize access="hasAnyAuthority('CHECK-DAR')">           
		                                      <a href="/show-dar?id=${row.dar_id}&nav=${nav_dar}" class="btn btn-sm btn-light">
									          <spring:message code="btn.view" text="ดู" /></a>  
									         </sec:authorize>                          
                                          </c:if>       
                                      <c:if test="${row.dar_flow == 2 or userid == row.dar_id_add and row.dar_flow == 2 }">      
		                                       <sec:authorize access="hasAnyAuthority('APP-DAR')">           
		                                       <a href="/show-dar?id=${row.dar_id}&&nav=${nav_dar}" class="btn btn-sm btn-light">
									          <spring:message code="btn.view" text="ดู" /></a>  
									         </sec:authorize>                      
                                           </c:if>        
                                                 
                                                          
                                   <c:if test="${type.equals('Review and add') and row.dar_flow <= 0}"> 
                                      <sec:authorize access="hasAnyAuthority('EDIT-DAR')">                         
							          <a href="/edit-dar?id=${row.dar_id}" class="btn btn-sm btn-info">
							          <spring:message code="btn.edit" text="แก้ไข" /></a>
							           </sec:authorize> 
							          
			   <sec:authorize access="hasAnyAuthority('ADD-DAR')">  				          
           <a href="/delete-dar/${row.dar_id}" class="btn btn-sm btn-danger" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />${row.dar_doc_name}');"><spring:message code="btn.delete" text="ลบ" /></a>
				 </sec:authorize> 				 
                                     </c:if>   
                                        
                                                </td>
                                                    </tr>
                                         
                                              </c:forEach>
                                        
                                        
                                        
                                        
                                    
                                        </tbody>
                                    </table>
                                </div>
                           
                 
                <!--/ Advanced Search -->
                                
                      
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

   

 <script src="<c:url value="/resources/app-assets/vendors/js/vendors.min.js" />"></script>
   <script src="<c:url value="/resources/app-assets/vendors/js/tables/datatable/jquery.dataTables.min.js" />"></script>
   <script src="<c:url value="/resources/app-assets/vendors/js/tables/datatable/dataTables.bootstrap5.min.js" />"></script>
   <script src="<c:url value="/resources/app-assets/vendors/js/tables/datatable/dataTables.responsive.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/tables/datatable/responsive.bootstrap5.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/core/app-menu.js" />"></script>
   <script src="<c:url value="/resources/app-assets/js/core/app.js" />"></script>
    <script src="<c:url value="/resources/app-assets/js/scripts/tables/table-datatables-advanced.js" />"></script>




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
        
 var element = document.getElementById("${nav_dar}");
  element.classList.add("active");  
    </script>
</body>


