<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="common/mainhead.jspf" %>    
    <!-- BEGIN: Page CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/horizontal-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-quill-editor.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/extensions/ext-component-toastr.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/app-email.css">
    <!-- END: Page CSS-->
    
    
    
<title>File Pn</title>
<style>

/* Pagination Container */
.pagination {
    display: inline-flex;
    list-style: none;
    padding: 0;
    margin: 0;
}

.pagination li {
    margin: 0 5px;
}

.pagination li a {
    display: inline-block;
    padding: 8px 12px;
    color: #007bff; /* สีลิงก์ */
    text-decoration: none;
    border: 1px solid #dee2e6; /* เส้นขอบ */
    border-radius: 4px;
    transition: background-color 0.3s, color 0.3s;
}

.pagination li a:hover {
    background-color: #007bff;
    color: #fff; /* สีข้อความบน hover */
}

.pagination li.active a {
    background-color: #007bff; /* สีพื้นหลังสำหรับหน้าปัจจุบัน */
    color: #fff; /* สีข้อความหน้าปัจจุบัน */
    border-color: #007bff;
}

.pagination li.disabled a {
    color: #6c757d; /* สีสำหรับปุ่มที่ถูกปิดใช้งาน */
    pointer-events: none; /* ปิดการคลิก */
    border-color: #dee2e6;
}

.pagination li.disabled a:hover {
    background-color: transparent;
    color: #6c757d;
}

/* ปรับตำแหน่งให้ตรงขอบขวา */
.text-right .pagination {
    justify-content: flex-end;
}
/* เมื่อเมาส์ชี้ที่ปุ่ม Pagination */
.pagination li a:hover {
    background-color: #0056b3; /* เปลี่ยนสีพื้นหลัง */
    color: #fff; /* เปลี่ยนสีข้อความ */
    border-color: #0056b3; /* เปลี่ยนสีขอบ */
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2); /* เพิ่มเงา */
    transform: translateY(-2px); /* ยกปุ่มขึ้นเล็กน้อย */
    transition: all 0.2s ease-in-out; /* เพิ่มการเปลี่ยนแปลงที่นุ่มนวล */
}

/* สำหรับปุ่มที่ Disabled ไม่ต้องมีเอฟเฟกต์ */
.pagination li.disabled a:hover {
    background-color: transparent; /* ไม่มีการเปลี่ยนสี */
    color: #6c757d; /* สีปุ่ม Disabled */
    box-shadow: none; /* ไม่มีเงา */
    transform: none; /* ไม่ยกขึ้น */
}
/* ทำให้เมาส์เป็นรูปมือเมื่อชี้ที่ปุ่ม Pagination */
.pagination li a {
    cursor: pointer; /* เปลี่ยนเมาส์เป็นรูปมือ */
}

/* สำหรับปุ่มที่ Disabled ให้ใช้เมาส์รูปปกติ */
.pagination li.disabled a {
    cursor: default; /* เมาส์รูปปกติ (ไม่ใช่รูปมือ) */
}



.pagination li {
    cursor: default; /* ถ้ามีคำสั่งนี้จะทำให้ pointer ไม่ทำงาน */
}





.cardmain {
    box-shadow: 0px 0px 10px 0px rgba(0, 0, 0, 0.5);
}

.block.block-rounded {
    border-radius: .5rem;
}
@media (min-width: 768px) {
    .content p, .content .push, .content .block, .content .items-push>div {
        margin-bottom: 1.875rem;
    }
}

.content p, .content .push, .content .block, .content .items-push>div {
    margin-bottom: 1rem;
}
.block {
    margin-bottom: 1.875rem;
    background-color: #fff;
    box-shadow: 0 1px 2px #d6dbe280, 0 1px 2px #d6dbe280;
}
.block.block-rounded>.block-header, .block.block-rounded>.nav-tabs {
    border-top-left-radius: .375rem;
    border-top-right-radius: .375rem;
}
.block-header-default {
    background-color: #f6f7f9;
}
.block-header {
    display: flex
;
    flex-direction: row;
    justify-content: space-between;
    align-items: center;
    padding: .625rem 1.25rem;
    transition: opacity .25s ease-out;
}
</style>


<style data-fullcalendar=""></style>

<link href="https://cdn.jsdelivr.net/npm/dragula@3.7.3/dist/dragula.min.css" rel="stylesheet">



</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  








    <!-- BEGIN: Content-->
    <div class="app-content content email-application"
        <div class="card">
    <div class="block-rounded block">
  <div class="block-header block-header-default">
    <h3 class="block-title"> <spring:message code="nav.alllog" text="ประวัติการใช้งาน" />
      <!---->
    </h3>
    <div class="block-options space-x-1">
      <div class="space-x-1" style="display:none">
        <button type="button" class="btn btn-sm btn-alt-secondary">
          <i class="fa fa-search"></i>
        </button>
        <div class="dropdown d-inline-block">
          <button type="button" class="btn btn-sm btn-alt-secondary" id="dropdown-recent-orders-filters" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-fw fa-flask"></i> Filters <i class="fa fa-angle-down ms-1"></i>
          </button>
          <div class="dropdown-menu dropdown-menu-md dropdown-menu-end fs-sm" aria-labelledby="dropdown-recent-orders-filters">
            <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between" href="javascript:void(0)"> Pending <span class="badge bg-primary rounded-pill">20</span>
            </a>
            <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between" href="javascript:void(0)"> Active <span class="badge bg-primary rounded-pill">72</span>
            </a>
            <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between" href="javascript:void(0)"> Completed <span class="badge bg-primary rounded-pill">890</span>
            </a>
            <a class="dropdown-item fw-medium d-flex align-items-center justify-content-between" href="javascript:void(0)"> All <span class="badge bg-primary rounded-pill">997</span>
            </a>
          </div>

      </div>
          </div>
      <!---->
      <!---->
      <!---->
      <!---->
    </div>
  </div>
  <!---->
  <!---->
  <!---->
  <div class="block-content block-content-full">
    <div class="table-responsive">
      <table class="table table-hover table-vcenter">
          <thead>
                                        <tr>
                                            
                                                <th><spring:message code="label.date" text="วันที่" /></th>
                                            <th><spring:message code="label.name" text="ชื่อผู้ใช้" /></th>
                                              <th><spring:message code="label.name" text="ชื่อผู้ใช้" /></th>
                                            <th><spring:message code="tables.action" text="ดำเนินการ"/></th>
                                            <th><spring:message code="tables.storage" text="แหล่งที่เก็บ"/></th>
                                             <th><spring:message code="tables.note" text="หมายเหตุ"/></th>
                                        
                                        </tr>
                                    </thead>
                                    <tbody >
                                        
                                         <c:forEach items="${log }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                       
                                            <td>${row.log_date } ${row.log_time }</td>
                                            <td>${row.user_name }</td>
                                                 <td>${row.group_level }</td>
                                            <td>${row.log_step }</td>
                                             <td>${row.log_folder }</td>
                                             <td>${row.log_note }</td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                             
      </table>
    </div>
  </div>

</div>

	
		<c:if test="${log.size() > 0 && totalPages > 0}">  	  
			    <ul class="pagination">
              <li class="<c:if test="${OFFSET-1 <= 0 }">disabled</c:if>">
              
              <a href="/logattendance-filemanager?type=${type }&&OFFSET=${OFFSET-1 }"   class="<c:if test="${OFFSET-1 <= 0 }">disabled</c:if>">«</a>
              
              </li>
               <c:if test="${OFFSET-1 > 1 }">   <li><a href="/logattendance-filemanager?type=${type }&&OFFSET=${OFFSET-2 }">${OFFSET - 2 }</a></li>  </c:if>
               <c:if test="${OFFSET > 1 }">  <li><a href="/logattendance-filemanager?type=${type }&&OFFSET=${OFFSET-1 }">${OFFSET -1 }</a></li> </c:if>
              
              <li class="active"><a href="/logattendance-filemanager?type=${type }&&OFFSET=${OFFSET }">${OFFSET }</a>
              </li>
              
               <c:if test="${totalPages > (OFFSET) * 20}"> <li><a href="/logattendance-filemanager?type=${type }&&OFFSET=${OFFSET+1 }">${OFFSET+1 }</a></li> </c:if>
               <c:if test="${ totalPages > (OFFSET+1) * 20 }"> <li><a href="/logattendance-filemanager?type=${type }&&OFFSET=${OFFSET+2 }">${OFFSET+2 }</a></li> </c:if>
              <li class="<c:if test="${totalPages < (OFFSET) * 20}">disabled</c:if>"><a href="/logattendance-filemanager?type=${type }&&OFFSET=${OFFSET+1 }"
               class="<c:if test="${totalPages < (OFFSET) * 20}">disabled</c:if>">»</a></li>
            </ul>
		</c:if>
            
            
			
  
</div>

		<br>	<br>		<br>		<br>		<br>		


            </div>




    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





<%@ include file="common/mainfoot.jspf" %>  
     <!-- BEGIN: Page JS-->
     
         <script src="/resources/app-assets/vendors/js/ui/jquery.sticky.js"></script>
    <script src="/resources/app-assets/vendors/js/editors/quill/katex.min.js"></script>
    <script src="/resources/app-assets/vendors/js/editors/quill/highlight.min.js"></script>
    <script src="/resources/app-assets/vendors/js/editors/quill/quill.min.js"></script>
    <script src="/resources/app-assets/vendors/js/extensions/toastr.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/select/select2.full.min.js"></script>

    <script src="/resources/app-assets/js/scripts/pages/app-email.js"></script>
    <!-- END: Page JS-->




    <script>
    
    
    function getdatas(url,div){
	    
   
				  $.ajax({
		    	        type:'GET',
		    	        url:url,
		    	        data: {ID : 0},
		    	        
		    	     success: function(data){
		    	    $("#"+div).html(data);	
	
		    	     }
		    	});
	  
		return false;
		
}
    
    
    
    
    
    
    var element = document.getElementById("nav_other1");
    element.classList.add("active"); 
    
    
    

    

    
    
    $('.normal').hide();
    
    
    function active (){
    	
   
    	if (document.getElementById('active').checked) {
    		  $('.normal').hide();
    		  $('.success').hide();
    		 
        } else {
            $('.normal').show();
            $('.success').show();
         
        }
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
    
    

    if (feather) {
        feather.replace({
            width: 14,
            height: 14
        });
    }

 



    </script>

 
 
 
 

 
    <script>

    
    $('#search').keyup(delay(function (e) {
    	
    	  
    	return search(this);
    	
     
    return false;	   
    }, 500));


    function search() {
        var filter = $('#search').val();
         $('li.itemList').filter(function() {
             var regex = new RegExp(filter, 'ig');

             if (regex.test($(this).text())) {
                 $(this).show();
             } else {
                 $(this).hide();
             }
         });
     

    }

</script>
    
</body>


