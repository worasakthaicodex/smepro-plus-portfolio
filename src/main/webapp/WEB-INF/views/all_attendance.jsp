<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="common/mainhead.jspf" %>    
 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
<title>Log pn</title>
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
          <section id="multiple-column-form">
                    <div class="row">
                  
                  
                  
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"> <spring:message code="nav.attendance"  /></h4>
                            </div>
                                <form:form method="POST"  action="/attendance-usermanage"> 
     <div class="row" style="padding:10px">       
    
 <div class="col-md-3">
<label class="form-label">User:</label>
<div class="mb-0">

<c:set var="list" value="${selectuser }" />
<select class="form-select select2" multiple="multiple"  name="selectuser" />
 <c:forEach items="${user}" var="row" varStatus="count">  
   
   
   <c:set var="selected" value="" />
       <c:set var="listcheck" value="${row.user_name }" />
    
    
    
    <c:if test='${fn:contains(list,listcheck)}'>
     <c:set var="selected" value="selected" />
    </c:if>
<option value="${row.user_name }" ${selected }>${row.user_name }</option>
</c:forEach>
</select>


 </div>
</div>                     
<div class="col-md-3">
<label class="form-label">From:</label>
<div class="mb-0">
<input type="date" class="form-control dt-date flatpickr-range dt-input" data-column="5"  name="dt_datestart" value="${dt_datestart }" />
 </div>
</div>        
<div class="col-md-3">
<label class="form-label">End:</label>
<div class="mb-0">
<input type="date" class="form-control dt-date flatpickr-range dt-input" data-column="5"  name="dt_dateend"  value="${dt_dateend }"/>
 </div>
</div> 

 <div class="col-md-3"><br><input type="submit" value="Search" class="btn btn-success"></div> 
 
</div>               
              </form:form>                 
                            
                            <div class="card-body"> 
                           
                                    <div class="card">
           
  
                            <div class="table-responsive">
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Check IN</th>
                                            <th>Check OUT</th>
                                              <th>Name</th>
                                             <th>IP</th>
                                                 <th>Image IN</th>
                                              <th>Image OUT</th>
                                               <th>Location</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                  <c:forEach items="${list_attendances}" var="row" varStatus="count">  
                                        <tr>
                                            <td>${row.attendance_date }</td>
                                             <td>${row.attendance_checkin }</td>
                                             <td>${row.attendance_checkout }</td>
                                                <td>${row.user_name }</td>
                                            <td>${row.attendance_ip }</td>
                                                  <td> <c:if test="${row.attendance_file != null}"> 
                                        
                                         <img src="/readdocument/Attendance/${row.attendance_file}" style="width:100px;cursor: pointer;" target="_blank" 
									     onclick="openCenteredWindow(this.src, 'File Window', 500, 500);return false;"></img></c:if></td>
                                         <td> <c:if test="${row.attendance_file2 != null}">
                                         <img src="/readdocument/Attendance/${row.attendance_file2}" style="width:100px;cursor: pointer;" target="_blank" 
									     onclick="openCenteredWindow(this.src, 'File Window', 500, 500);return false;"></img></c:if></td>
									     
                                             <td><a href="https://nominatim.openstreetmap.org/reverse?lat=${row.attendance_lat }&lon=${row.attendance_lon }&format=json"  target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 500, 200);return false;">${row.attendance_lat },${row.attendance_lon }</a></td>
                                        </tr>
                               </c:forEach>   
                                        
                                  
                                    </tbody>
                                </table>
                            </div>
                            
                           
                        </div>
                   </div>   </div>
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


        <!-- add new card modal  -->
                <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.uncheck" /></h1>
                              

                                <!-- form -->
                                <form  action="/rejectdocument-filemanager"id="addNewCardValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control add-credit-card-mask" type="text" required/>
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type"></span>
                                            </span>
                                        </div>
                                          <input type="hidden" name="id" id="id" value=""> 
                                    </div>

                                   
                                

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->

<%@ include file="common/mainfoot.jspf" %>  
    <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>

    <script>
    
    
    function openCenteredWindow(url, name, width, height) {
        // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
        var left = (window.innerWidth / 2) - (width / 2);
        var top = (window.innerHeight / 2) - (height / 2);

        // เปิดหน้าต่างใหม่
        window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
    }

  
    
    
    var element = document.getElementById("usermanage4");
    element.classList.add("active");  
    
    
    function reject(id){
    	$("#id").val(id);
    }
    
 
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


