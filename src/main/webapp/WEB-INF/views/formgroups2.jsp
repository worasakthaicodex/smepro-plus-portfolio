<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="common/mainhead.jspf" %>    
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<title>Profile</title>
</head>
<body class="crm_body_bg">
<%@ include file="common/mainbar.jspf" %>   

<section class="main_content dashboard_part large_header_bg">
<%@ include file="common/subhead.jspf" %>   
   <div class="main_content_iner ">
        <div class="container-fluid p-0">
            <div class="row justify-content-center">
                <div class="col-12">
                    <div class="dashboard_header mb_50">
                        <div class="row">
                            <div class="col-lg-6">
                                <div class="dashboard_header_title">
                                    <h3> <spring:message code="label.role_name" text="บทบาท" /></h3>
                                </div>
                            </div>
                            <div class="col-lg-6">
                                <div class="dashboard_breadcam text-end">
                                    <p><spring:message code="nav.role" text="บทบาทเเละสิทธิ์" /> <i class="ti-angle-right"></i>  <spring:message code="label.role_name" text="บทบาท" /></p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
             
                <div class="col-md-12">
                    <div class="white_box QA_section mb_30">
                        <div class="white_box_tittle list_header">
                            <h4><spring:message code="label.role_name" text="บทบาท" /></h4>
                            <div class="box_right d-flex lms_block">
                                <div class="serach_field_2">
                              
                                </div>
                            </div>
                        </div>

                        <div class="QA_table">
  
  
                                 <c:choose>
                                <c:when test='${groups.id > 0}'>
                                  <c:set var="link" value="/editrole-usermanage"/>      
                                </c:when>
                                <c:otherwise>
                                <c:set var="link" value="/addrole-usermanage"/>  
                              </c:otherwise>
                               </c:choose>                       
 
                                 <form:form method="POST" modelAttribute="building" action="${link }">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 
                                 
                                 <c:if test="${groups.id > 0}">    
                                 <input  type="hidden" name="id" value="${groups.id }">
                               </c:if>  
                                <%-- 
                               text 
                               number  วงเล็บสีเเดง
                               date ขึ้นต้นด้วยวัน
                               
                               --%>
                                
                                <div class="mb-3">
                                    <label class="form-label" for="exampleInputEmail1"><spring:message code="usermanage.role" text="บทบาท" /></label>
                                    <input type="text" name="group_name"  class="form-control"  value="${groups.group_name }">
                               
                                </div>
                                
                      
                                
                                     
                                          
                                <br><br>
                                <button type="submit" class="btn btn-primary"> 
                                <c:choose>
                                <c:when test='${groups.id > 0}'>
                                <spring:message code="btn.edit" text="แก้ไข" />
                                </c:when>
                                <c:otherwise>
                                <spring:message code="btn.add" text="เพิ่ม" />
                               </c:otherwise>
         
                               </c:choose>       
                               </button>
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


