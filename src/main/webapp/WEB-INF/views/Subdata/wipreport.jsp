<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    


 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>WIP</title>


 <style>
 
 
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



td:first-child { text-align: left; }


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
                                                   <li class="breadcrumb-item"><a href="/systemproduction-workflow/${id}">Production Reports</a>
                                    </li>
                                    <li class="breadcrumb-item active">Stock
                                    </li>
                                    
                                    
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                       <div class="content-header-left col-md-3 col-12 mb-2">
            
                            
                                 <a href="/wipsetting-workflow/${id}" class="btn btn-sm btn-primary  waves-effect waves-float waves-light" style="float:right" >  
                            Setting
                             </a>
        <a href="#" id="export-csv" class="btn btn-sm btn-light  waves-effect waves-float waves-light" style="float:right" >  
                            Export
                             </a>
                             </div>
            </div> 

     
<div class="row">
                        <div class="col-lg-12">
                            <div class="card" id="orderList">
                                <div class="card-body border border-dashed border-end-0 border-start-0">
                              <div class="card-body">
                              
                                 <div style="width:250px;float:right">
                              
                                             <div class="mb-1 input-group input-group-merge">
                                    <span class="input-group-text"><svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg></span>
                                    <input type="text" class="form-control" id="icons-search" placeholder="Search ...">
                                </div>
                              </div>
                              
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="homeIcon-tab" data-bs-toggle="tab" href="#homeIcon" 
                                            aria-controls="home" role="tab" aria-selected="false">
                           <c:if test="${filter == null}">                  
                                 DATA
                                </c:if>            
                                  <c:if test="${filter != null}">                  
                                 RAW
                                </c:if>                
                                            </a>
                                        </li>
                                 
                                    
                                    </ul>
                                    <div class="tab-content">
                                       <div class="tab-pane active" id="homeIcon" aria-labelledby="homeIcon-tab" role="tabpanel">
                                            
                                            
                             
                                            
                                                  <c:if test="${filter == null}">               
                                                  <table class="table" id="table">
                                    <thead>
                                        <tr>
                                                 <th><spring:message code="product.lot_batch"  text="-​" /></th>
                                                     <th><spring:message code="doc.code"  text="-​" /></th>
                                                             <th><spring:message code="other.list"  text="-​" /></th>
                                                                 <th><spring:message code="product.department"  text="-​" /></th>
                                                     
                                                   <th class="Action"><spring:message code="btn.action"  text="-​" /></th>
                                        
                        
                                        </tr>
                                    </thead>
                                    <tbody>
                                       
                             <c:forEach  items="${wip }"  var = "row">
                             
                                     <c:set var="batch" value="${empty row.batch ? 'N/A' : row.batch}" />
                         		      <c:set var="pd" value="${empty row.pd ? 'N/A' : row.pd}" />
                                        <tr>
            								 <td><c:out value="${empty row.batch ? 'N/A' : row.batch}" /></td>
     										 <td><c:out value="${empty row.pd ? 'N/A' : row.pd}" /></td>
     										 	 <td>${row.list}</td>
                                        		 <td>${row.department}</td>
                                            <td  class="Action">

                                            <a href="/wipreport-workflow/${id}?filter=${batch}_${pd}" class="btn btn-sm btn-light"><spring:message code="btn.view"  text="-​" /></a></td>
                                            
                                        </tr>
                                  </c:forEach>   
                                        
                                    </tbody>
                                </table>
                                </c:if>
                                
                                                  <c:if test="${filter != null}">   
                                                  
                                                  
                                                  
                                                  
                                                  
                                          <table id="table">
                                    <thead>
                                        <tr>
                                        
                                            <th><spring:message code="product.lot_batch"  text="-​" /></th>
                                                     <th><spring:message code="doc.code"  text="-​" /></th>
                                               
                                                                  <th><spring:message code="other.list"  text="-​" /></th>
                                                          <th><spring:message code="product.date_start"  text="-​" /></th>                                
                                                   <th><spring:message code="product.order_quantity"  text="-​" /></th>
                                         
                                            
                                            
                                                   <th><spring:message code="product.order_quantity"  text="-​" /></th>
                                            <th><spring:message code="product.received_today"  text="-​" /></th>
                                            <th><spring:message code="product.received_accumulated"  text="-​" /></th>
                                            <th><spring:message code="product.pending_received"  text="-​" /></th>
                               
                                         <th><spring:message code="product.paid_today"  text="-​" /></th>
                                            <th><spring:message code="product.paid_accumulated"  text="-​" /></th>
                                            <th><spring:message code="product.pending_paid"  text="-​" /></th>
                                             <th><spring:message code="product.department"  text="-​" /></th>
                                      
                                    		<th><spring:message code="product.line"  text="-​" /></th>
                                            <th><spring:message code="product.machine_id"  text="-​" /></th>       
                                               <th><spring:message code="product.employee"  text="-​" /></th>         
                                        </tr>
                                    </thead>
                                    <tbody>
                                       
                             <c:forEach  items="${wip }"  var = "row">
                                        <tr>
                                      	 <td><c:out value="${empty row.batch ? 'N/A' : row.batch}" /></td>
     										 <td><c:out value="${empty row.pd ? 'N/A' : row.pd}" /></td>
                      
                                              <td>${row.list }</td>
                                                                    <td>${row.date_wip } </td>
                                            <td>${row.orders }</td>
                                            <td>
                                              ${row.received_today }
                                            </td>
                                               <td>
                                              ${row.received_accumulated }
                                            </td>
                                               <td>
                                              ${row.pending_received }
                                            </td>
                                               <td>
                                              ${row.paid_today }
                                            </td>
                                               <td>
                                              ${row.paid_accumulated }
                                            </td>
                                                <td>
                                              ${row.pending_paid }
                                            </td>
                                            <td>
                                              ${row.department }
                                            </td>
                                               <td>
                                              ${row.line }
                                            </td>
                                                <td>
                                              ${row.machine }
                                            </td>
                                                 <td>
                                              ${row.employee }
                                            </td>
                                        </tr>
                                  </c:forEach>      
                                        
                                    </tbody>
                                </table>
                                
                                <br><br>
                                Limit : 500 Rows
                                </c:if>
                                
                                
                                        </div>
                                   
                                 
                                     
                                   
                                 
                                    </div>
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




<script>
document.getElementById('icons-search').addEventListener('input', function () {
  const filter = this.value.toLowerCase();
  const rows = document.querySelectorAll('#table tbody tr');
  
  rows.forEach(row => {
    const text = row.textContent.toLowerCase();
    row.style.display = text.includes(filter) ? '' : 'none';
  });
});




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
