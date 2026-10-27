<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">

<title>Upload</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">



            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Backup</h4>
                                </div>
                                <div class="card-body">
                                     
                                <div class="row">
                        <div class="col-12">
                            <div class="card">
                      
                                <div class="card-body">
                                 <form:form method="POST" modelAttribute="backup" action="/backups">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        
                                        <div class="row">
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column">วันที่</label>
                                                    <input type="text" id="first-name-column" class="form-control"  name="backup_date">
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="last-name-column">บริการ</label>
                                                    <input type="text" id="last-name-column" class="form-control" name="backup_service">
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="city-column">บันทึกเพิ่มเติม</label>
                                                    <input type="text" id="city-column" class="form-control" name="backup_note">
                                                </div>
                                            </div>
                                            <div class="col-md-6 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="country-floating">ผู้ดำเนินการ</label>
                                                    <input type="text" id="country-floating" class="form-control" name="backup_name">
                                                </div>
                                            </div>
                                   
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">Submit</button>
                                          
                                            </div>
                                        </div>
                                    </form:form> 
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <br>  <br>
              <table class="table table-borderless">
                                    <thead>
                                        <tr>
                                            <th>วันที่</th>
                                            <th>บริการ</th>
                                             <th>บันทึกเพิ่มเติม</th>
                                             <th>ผู้ดำเนินการ</th>
                                             <th></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                     <c:forEach items="${backup }" var="row" varStatus="count">
                                         
                     
                                        <tr>
                                            <td>${row.backup_date }</td>
                                            <td>${row.backup_service }</td>
                                            <td>${row.backup_note }</td>
                                            <td>${row.backup_name }</td>
                                            <td>  
							<a href="/delbackup?id=${row.backup_id}" class="btn btn-danger waves-effect waves-float waves-light">
							          ลบ</a>
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





<%@ include file="common/mainfoot.jspf" %>  

</body>


