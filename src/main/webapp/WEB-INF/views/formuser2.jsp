<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>index</title>
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
              <div class="card"><div class="card-body">
                <div class="col-md-12">
                    <div class="white_box QA_section mb_30">
                        <div class="white_box_tittle list_header">
                            <h4><spring:message code="profile.Profile" text="ประวัติโดยย่อ" /></h4>
                            <div class="box_right d-flex lms_block">
                                <div class="serach_field_2">
                              
                                </div>
                            </div>
                        </div>

                        <div class="QA_table" id="profilediv">
                       <form:form method="POST" modelAttribute="user" action="/edit-usermanage">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                  <input  type="hidden" name="user_id" value="${user.user_id}">
                                <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.name" text="ชื่อ - นามสกุล" /></label>
                                    <input type="text" name="user_name"  class="form-control"  value="${user.user_name }">
                               
                                </div>
                                
                                       <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.address" text="ที่อยู่" /></label>
                                    <input type="text" name="user_address"  class="form-control"  value="${user.user_address }">
                              
                                </div>
                                
                                       <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.tel" text="เบอร์ติดต่อ" /></label>
                                    <input type="number" name="user_tel"  class="form-control"  value="${user.user_tel }">
                                  
                                </div>
                                
                                  
                                
                                
                                           <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.Idcard" text="เลขบัตรประชาชน" /></label>
                                    <input type="number" name="user_idcard"  class="form-control"  value="${user.user_idcard }">
                                  
                                </div>
                                
                                             <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.passport" text="หนังสือเดินทาง" /></label>
                                    <input type="text" name="user_districtpassport"  class="form-control"  value="${user.user_districtpassport }">
                                  
                                </div>
                                       <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.countryp" text="ประเทศที่ออกให้" /></label>
                                    <input type="text" name="user_countrypassport"  class="form-control"  value="${user.user_countrypassport }">
                                  
                                </div>
                                
                                  <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.Datep" text="วันที่ออกหนังสือเดินทาง" /></label>
                                    <input type="date" name="user_datepassport"  class="form-control"  value="${user.user_datepassport }">
                                  
                                </div>
                                            <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="label.birthday" text="วันเกิด" /></label>
                                    <input type="date" name="user_birthday"  class="form-control"  value="${user.user_birthday }">
                                  
                                </div>
                                
                                
                                <br><br>
                                <button type="submit" class="btn btn-primary"><spring:message code="btn.edit" text="แก้ไข" /></button>
                           
                             <c:if test="${user.user_name  != ''}">   
                                 <a href="/resetpassword/${user.user_id}" class="btn btn-primary">reset password</a>
                        </c:if> 
                        
                            <c:if test="${user.user_name  != ''}">   
                                 <a href="#" data-bs-toggle="modal" data-bs-target="#addNewCard4"   class="btn btn-primary">โอนถ่ายงาน</a>
                        </c:if> 
                       
                       </form:form>           
                                
                                
                        </div>
                   
           
                    </div>
                </div>
                       </div></div></div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>

 
 <div class="modal fade text-start" id="addNewCard4" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
                                                <div class="modal-dialog modal-dialog-centered modal-lg">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h4 class="modal-title" id="myModalLabel17">ย้ายงาน</h4>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        
                                                         <form:form method="POST" modelAttribute="e_report"  action="/transfer-usermanage" class="row gy-1 gx-2 mt-75"  >
                                                          <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                             <input  type="hidden" name="user_name" value="${user.user_name}">
                                                                 <input  type="hidden" name="id" value="${user.user_id}">
                                                        <div class="modal-body">
                                                          
                                                         <select class="form-select select2" id="dataset_data" name="user_names">
                                                <c:forEach items="${usered }" var="row" varStatus="count">
                                          				 <option value="${row.user_name }" >${row.user_name }</option>
                                         		  </c:forEach>
                                                         </select>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" class="btn btn-primary" >ย้ายงาน</button>
                                                        </div>
                                                        </form:form>
                                                        
                                                    </div>
                                                </div>
      </div>
      
      
      


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
        
 var element = document.getElementById("usermanage3");
      element.classList.add("active");   
    </script>
</body>
  <%@ include file="other/alert.jsp" %>

  <c:if test="${save == 'success'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
    
    </script>
</c:if>

