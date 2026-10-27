<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="common/mainhead.jspf" %>    

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
                                 <div class="card-header">
                                    <h4 class="card-title">${folder.folder_name }</h4>
                                </div>
                                <div class="card-header border-bottom">
                                    <h4 class="card-title"><spring:message code="action.Setrolepermissions"  /></h4>
                                      <c:set var="listcheck" value="SET-UE" />
                           
                                </div>
                                 <div class="card-body">
          
                                <!-- Add role form -->
                               <form:form method="POST" modelAttribute="building" action="/setauthorities-filemanager">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 <input  name="folderid"   type="hidden" value="${folder.folder_id }"/>
                                    <div class="col-12">
                                 
                                        <!-- Permission table --> 
                                        <div class="table-responsive">
                                            <table class="table table-flush-spacing">
                                                <tbody>
                                                    <tr>
                                                        <td class="text-nowrap fw-bolder">
                                                         <spring:message code="usermanage.access" text="สิทธิ์ในการเข้าถึง" />
                                                            <span data-bs-toggle="tooltip" data-bs-placement="top" title="Allows a full access to the system">
                                                                <i data-feather="info"></i>
                                                            </span>
                                                        </td>
                                                        <td>
                                                            <div class="form-check">
                                                                <input class="form-check-input" type="checkbox" id="select-all" />
                                                                <label class="form-check-label" for="selectAll">  <spring:message code="action.SelectAll"  /> </label>
                                                            </div>
                                                        </td>
                                                    </tr>
                                                    

                                    <c:set var="list" value="${authoritiess }" />                              
                              <c:forEach items="${groups }" var="row" varStatus="count">  
                                 
                                    <c:set var="check1" value="" />
								     <c:set var="check2" value="" />
								     <c:set var="check3" value="" />
								     <c:set var="check4" value="" />
								     <c:set var="check5" value="" />
								     <c:set var="check6" value="" />
								     <c:set var="check7" value="" />
								      <c:set var="check8" value="" /> 
								      <c:set var="check9" value="" /> 
								      <c:set var="check10" value="" /> 
                                  <c:set var="listcheck" value="ADD-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check1" value="checked" />
                                   </c:if>
                                   
                                          <c:set var="listcheck" value="EDIT-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check2" value="checked" />
                                   </c:if>				                             
					                <c:set var="listcheck" value="CHECK-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check3" value="checked" />
                                   </c:if>								         
								        <c:set var="listcheck" value="APP-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check4" value="checked" />
                                   </c:if>		
                                           <c:set var="listcheck" value="READ-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check5" value="checked" />
                                   </c:if>	
                                                  <c:set var="listcheck" value="UPLOAD-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check6" value="checked" />
                                   </c:if>	
                                  <c:set var="listcheck" value="SET-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check7" value="checked" />
                                   </c:if>	 
                                     <c:set var="listcheck" value="DELETE-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check8" value="checked" />
                                   </c:if>	        
                                   
                                     <c:set var="listcheck" value="DOWNLOAD-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check9" value="checked" />
                                   </c:if>	 
                                   
                                         <c:set var="listcheck" value="DELETEITEM-${folder.folder_type }-${row.id }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check10" value="checked" />
                                   </c:if>	     
                                            
                                                    <tr>
                                                        <td class="text-nowrap fw-bolder">${count.count} ${row.group_name} [${row.group_department}]</td>
                                                        <td>
                                                            <div class="d-flex">
                                                                <div class="form-check me-2 me-lg-2">
                                                                   <input  name="id"   type="hidden" value="${row.id }"/>
                                                                    <input class="form-check-input" name="ADD"   type="checkbox" id="userManagementAdd${row.id }" value="ADD-${folder.folder_type }_${row.id }" ${check1 }/>
                                                                    <label class="form-check-label" for="userManagementAdd${row.id }"> <spring:message code="usermanage.add" text="จัดทำ" /> </label>
                                                                </div>
                                                                <div class="form-check me-2 me-lg-2">
                                                                    <input class="form-check-input" name="EDIT" type="checkbox" id="userManagementedit${row.id }" value="EDIT-${folder.folder_type }_${row.id }" ${check2 }/>
                                                                    <label class="form-check-label" for="userManagementedit${row.id }"> <spring:message code="usermanage.edit" text="แก้ไข" /> </label>
                                                                </div>
                                                                <div class="form-check me-2 me-lg-2" style="display:none">
                                                                    <input class="form-check-input" name="CHECK" type="checkbox" id="userManagementcheck${row.id }" value="CHECK-${folder.folder_type }_${row.id }" ${check3 }/>
                                                                    <label class="form-check-label" for="userManagementcheck${row.id }"> <spring:message code="usermanage.check" text="ตรวจสอบ" /> </label>
                                                                </div>
                                                                
                                                                   <div class="form-check me-2 me-lg-2"  style="display:none">
                                                                    <input class="form-check-input" name="APP"  type="checkbox" id="userManagementapp${row.id }" value="APP-${folder.folder_type }_${row.id }" ${check4 }/>
                                                                    <label class="form-check-label" for="userManagementapp${row.id }"> <spring:message code="usermanage.app" text="อนุมัติ" /> </label>
                                                                </div>
                                                                
                                                                   <div class="form-check me-2 me-lg-2">
                                                                    <input class="form-check-input" name="READ" type="checkbox" id="userManagementread${row.id }" value="READ-${folder.folder_type }_${row.id }" ${check5 }/>
                                                                    <label class="form-check-label" for="userManagementread${row.id }"> <spring:message code="usermanage.read" text="อ่าน" /> </label>
                                                                </div>
                                                                   <div class="form-check me-2 me-lg-2">
                                                                    <input class="form-check-input" name="UPLOAD" type="checkbox" id="userManagementupload${row.id }" value="UPLOAD-${folder.folder_type }_${row.id }" ${check6 }/>
                                                                    <label class="form-check-label" for="userManagementupload${row.id }"> <spring:message code="usermanage.upload" text="อัพโหลด" /> </label>
                                                                </div>
                                                                
                                                                         <div class="form-check me-2 me-lg-2">
                                                                    <input class="form-check-input" name="DOWNLOAD" type="checkbox" id="userManagementdownload${row.id }" value="DOWNLOAD-${folder.folder_type }_${row.id }" ${check9 }/>
                                                                    <label class="form-check-label" for="userManagementdownload${row.id }"> <spring:message code="usermanage.download" text="ดาวน์โหลด" /> </label>
                                                                </div>
                                                                
                                                                
                                                                   <div class="form-check me-2 me-lg-2">
                                                                    <input class="form-check-input" name="SET" type="checkbox" id="userManagementsetting${row.id }" value="SET-${folder.folder_type }_${row.id }" ${check7 }/>
                                                                    <label class="form-check-label" for="userManagementsetting${row.id }"> <spring:message code="usermanage.setting" text="ตั้งค่า" /> </label>
                                                                </div>
                                                          
                                                                   <div class="form-check me-2 me-lg-2">
                                                                    <input class="form-check-input" name="DELETEITEM" type="checkbox" id="userManagementdeleteitem${row.id }" value="DELETEITEM-${folder.folder_type }_${row.id }" ${check10 }/>
                                                                    <label class="form-check-label" for="userManagementdeleteitem${row.id }"> <spring:message code="usermanage.deleteitem" text="ลบ(ภายใน)" /> </label>
                                                                </div>
                                                                
                                                                  <div class="form-check me-2 me-lg-2">
                                                                    <input class="form-check-input" name="DELETE" type="checkbox" id="userManagementdelete${row.id }" value="DELETE-${folder.folder_type }_${row.id }" ${check8 }/>
                                                                    <label class="form-check-label" for="userManagementdelete${row.id }"> <spring:message code="usermanage.delete" text="ลบ" /> </label>
                                                                </div>
                                                            </div>
                                                        </td>
                                                    </tr>
                                             
                                                </c:forEach>
                                             
                                                </tbody>
                                            </table>
                                        </div>
                                        <!-- Permission table -->
                                    </div>
                                    <div class="col-12 text-center mt-2">
                                        <button type="submit" class="btn btn-primary me-1"><spring:message code="btn.save" /></button>
                                        
                                    </div>
                                 </form:form>    
                                <!--/ Add role form -->
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

  <%@ include file="other/alert.jsp" %>

  <c:if test="${save == 'success'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Scccess');
      });
    window.close();
    </script>
</c:if>


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
        

  $('#select-all').click(function(event) {   
    if(this.checked) {
        // Iterate each checkbox
        $(':checkbox').each(function() {
            this.checked = true;                        
        });
    } else {
        $(':checkbox').each(function() {
            this.checked = false;                       
        });
    }
});       
        
    </script>
 
 
    <script>

    var element = document.getElementById("nav_doc");
    element.classList.add("active");     

    </script>
</body>


