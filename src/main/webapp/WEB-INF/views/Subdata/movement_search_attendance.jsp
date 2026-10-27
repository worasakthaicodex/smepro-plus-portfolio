<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    
 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/s/select/select2.min.css" />" rel="stylesheet">

 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Search Attendance</title>

 <style>
 
 .select2-selection__arrow{
 display:none;
 
 }
 
 
 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
      font-size:5px;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 150px;
     font-size:12px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
  
    padding:3px;
  
}





th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     font-size:10px;
     padding:3px;
       max-width: 80px;
       text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
         min-width: 30px;
}





  </style>

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body" >
          <section id="multiple-column-form" >
          
     
     
    <div class="content-header row">
                <div class="content-header-left col-md-9 col-12 mb-2">
                    <div class="row breadcrumbs-top">
                        <div class="col-12">
                            <h2 class="content-header-title float-start mb-0">Report</h2>
                            <div class="breadcrumb-wrapper">
                                     <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${id}">${workflows.workflow_name}</a>
                                    </li>
                                                <li class="breadcrumb-item"><a href="/monitoringapp-workflow/${id}">Reports</a>
                                    </li>
                                   
                                      <li class="breadcrumb-item"><a href="/mainreport-workflow/${id}">Main Reports</a>
                                    </li>
                                             <li class="breadcrumb-item"><a href="/systemhr-workflow/${id}">HR Reports</a>
                                    </li>
                                              
                                  
                                    
                                    
                                    <li class="breadcrumb-item active">Attendance
                                    </li>
                                    
                                    
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
               
            </div> 

     
<div class="row">
                          <div class="col-md-8 col-12">
                            <div class="card">
                          
                                <div class="card-body">
                                     <form:form method="POST" modelAttribute="user" action="/attendance_search-workflow">
                                        <div class="row">
                                           <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><b><spring:message code="report.month" /></b></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                    
                                  
                                        <input type="month" id="monthPicker" name="searchMonth" class="form-control" required >
                                                    </div>
                                                </div>
                                            </div>
                                 
                                        
                                        
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><b><spring:message code="forms.Selectuser" /></b></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                        
                                       <select  class="form-select select2" name="Eyid">
                                       
                                       		<option value = "0">ALL</option>
                                            <c:forEach items="${userall }" var="row"  varStatus="count">
                                       			<option value = "${row.user_id }">${row.user_name }</option>
                                       		</c:forEach>
                                       </select>
                                       
                                    
                                                    </div>
                                                </div>
                                            </div>
                                 
                     <div class="col-12">
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="id" value="${id }"> 
                                      <input type="hidden" class="form-select"  name="stock_code" value="-"> 
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"><spring:message code="btn.search" /></button>
                                            </div>
                           
                                        </div>
                                        
                                        
                        
                                         
                                          
                                    </form:form>
                                </div>
                            </div>
  							 </div>
  							 
  							 
  							 
  							 
  							 
  							 
  		
  							 
                        </div>
                        <!--end col-->
                    </div>              
                   
                   </div>
          
          

                  
          
                 
                 
            
            
     
                </section>

            </div>
        </div>
    </div>
 
 
  
 
 

 
 
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



 <div style="clear:both"></div>
    
    
    
<%@ include file="../common/mainfoot.jspf" %>  
    <script src="/resources/app-assets/vendors/js/forms/select/select2.full.min.js"></script>



<script>



//In your Javascript (external .js resource or <script> tag)
$(document).ready(function() {
    $('.select2').select2();
});






let selectedFilter = 'all'; // ค่าเริ่มต้น

// เมื่อคลิกเลือก filter
document.querySelectorAll('.dropdown-item').forEach(item => {
    item.addEventListener('click', function (event) {
        selectedFilter = this.getAttribute('data-value');
        document.getElementById('filter-button').textContent = this.textContent;
        applyFilters(); // เรียก filter ทันทีเมื่อเลือก
    });
});

// เมื่อพิมพ์ในช่อง search
document.getElementById('icons-search').addEventListener('input', function () {
    applyFilters();
});

function applyFilters() {
    const keyword = document.getElementById('icons-search').value.toLowerCase();
    const rows = document.querySelectorAll('#table tbody tr');

    rows.forEach(row => {
        const rowText = row.textContent.toLowerCase();
        const matchesKeyword = rowText.includes(keyword);
        const matchesFilter = selectedFilter === 'all' || rowText.includes(selectedFilter.toLowerCase());

        row.style.display = (matchesKeyword && matchesFilter) ? '' : 'none';
    });
}
</script>


<script>

document.getElementById('export-csv').addEventListener('click', function () {
	  const table = document.getElementById('table');
	  const rows = table.querySelectorAll('tr');
	  let csv = [];

	  rows.forEach(row => {
	    if (row.style.display === 'none') return;

	    const cols = row.querySelectorAll('th, td');
	    const rowData = [];

	    cols.forEach(col => {
	      // ข้ามถ้ามี class "Action"
	      if (col.classList.contains('Action')) return;

	      let text = col.textContent.trim().replace(/"/g, '""');
	      rowData.push('"' + text + '"');
	    });

	    // ถ้ามีข้อมูลจริงจึงเพิ่มเข้า csv
	    if (rowData.length > 0) {
	      csv.push(rowData.join(','));
	    }
	  });

	  const csvContent = csv.join('\n');
	  const blob = new Blob([csvContent], { type: 'text/csv;charset=utf-8;' });
	  const link = document.createElement('a');
	  link.href = URL.createObjectURL(blob);
	  link.download = 'table.csv';
	  link.click();
	});


</script>


 <%@ include file="../other/alert.jsp" %>

  <c:if test="${Sys_Save == 'Save_Sucess'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
    
    </script>
</c:if>
  <c:if test="${Sys_Save == 'Sys_Failed'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('error','Save','Failed');
      });
    
    </script>
</c:if>
</body>



  
	    <script>
	      $(window).on('load', function() {
	            if (feather) {
	                feather.replace({
	                    width: 14,
	                    height: 14
	                });
	            }
	        })
	        
    var element = document.getElementById("nav_workflowsystem");
    element.classList.add("active"); 
    
    
    
    
    
    
    
    
    
    function postdatacal(Mode,Mode2,Mode3) {

	    var formData = new FormData($("#"+ Mode2)[0]);

			    $.ajax({
			        url: Mode,
			        type: "POST",
			        data: formData,
			        processData: false,
			        contentType: false,
				    success: function(result){
				    $('.' + Mode3).html(result);
				       },
				    error: function(jqXHR, textStatus, errorThrown) {
				            if (jqXHR.status === 401) {
				                // Redirect to login page if unauthorized
				                window.location.href = '/login'; // URL ของหน้า login
				     }}
				    });
			

		
			   
	    return false;
	    }	
    
    
    
	   function getdata(url,id){
	        $.ajax({
	        type:'GET',
	        url:url,
	        data: {ID : id},
	        
	     success: function(data){
	    $("#mainkey").html(data);	

	     }
	});





return false;

}
    </script>				

 <script>
    
 
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
<script>
    document.querySelectorAll('.dropdown-item').forEach(function(item) {
        item.addEventListener('click', function(event) {
            const selectedText = event.target.textContent;
            document.getElementById('filter-button').textContent = selectedText;
        });
    });
</script>