<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/mainhead.jspf" %>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<title>User manage</title>
</head>
<body class="crm_body_bg">
<%@ include file="common/mainbar.jspf" %>   

<section class="main_content dashboard_part large_header_bg">
   <div class="main_content_iner ">
        <div class="container-fluid p-0">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="dashboard_header mb_50">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="dashboard_header_title">
                                    <h3> <spring:message code="btn.Profile" text="ข้อมูลส่วนตัว" /></h3>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="dashboard_breadcam text-end">
                                    <p><spring:message code="btn.index" text="หน้าเเรก" /> <i class="ti-angle-right"></i> <spring:message code="btn.Profile" text="ข้อมูลส่วนตัว" /></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
             
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
                       <form:form method="POST" modelAttribute="user" action="/edit-mainusermanage">
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
                       </form:form>           
                                
                                
                        </div>
                   
           
                    </div>
                </div>
            </div>
        </div>
    </div>

</section>


 <script>
 var element = document.getElementById("usermanage3");
 element.classList.add("active");   
  
  </script>
  
  
</body>
<%@ include file="common/mainfoot.jspf" %>  
