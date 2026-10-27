<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
      max-width: 140px;
     font-size:12px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */

         min-width: 5px;
    padding:3px;
  
}





th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     font-size:10px;
     padding:3px;
     max-width: 140px;
       text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
         min-width: 5px;
}





  </style>


<c:if test="${stock_code == '-' }">
<%@ include file="../common/mainhead.jspf" %>    


 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Stock</title>


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
      max-width: 140px;
     font-size:12px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */

         min-width: 5px;
    padding:3px;
  
}





th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     font-size:10px;
     padding:3px;
     max-width: 140px;
       text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
         min-width: 5px;
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
                                             <li class="breadcrumb-item"><a href="/systemstock-workflow/${id}">Stock Reports</a>
                                    </li>
                                    <li class="breadcrumb-item active">Movement 
                                    </li>
                                    
                                    
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
                
                
                
                       <div class="content-header-left col-md-3 col-12 mb-2">
            
                            
                             
        <a href="#" id="export-csv" class="btn btn-sm btn-light  waves-effect waves-float waves-light" style="float:right;background-color:#fff" >  
                            Export
                             </a>
                             </div>
            </div> 

<div class="row">
                        <div class="col-lg-12">
                            <div class="card" id="orderList">
                                <div class="card-body border border-dashed border-end-0 border-start-0">
                              <div class="card-body">

                                   <br>   
                               
                                    <div class="tab-content">
                                       <div class="tab-pane active" id="homeIcon" aria-labelledby="homeIcon-tab" role="tabpanel">
                                            
           </c:if>                                 
                             
                             <br>               
                                                 
                                                  <table  id="table" width="100%">
                                    <thead>
                                        <tr>  <th class="form-label fixhead" style="width:10px">#</th>    
                                         <th class="form-label fixhead" ><spring:message code="fg.date"  /> </th>    
   <th class="form-label fixhead" ><spring:message code="product.lot_batch"  /></th>  	                          
<th class="form-label fixhead" ><spring:message code="material.stock_code"  /></th>     
<th class="form-label fixhead" ><spring:message code="material.stock_name"  /></th>  
  
<th class="form-label fixhead" ><spring:message code="maindata.batch_producer"  /> </th>     
<th class="form-label fixhead" ><spring:message code="fg.receive"  /></th>   
<th class="form-label fixhead" ><spring:message code="fg.withdraw"  /></th>     
<th class="form-label fixhead" ><spring:message code="workflows.remaining"  /></th> 
<th class="form-label fixhead" ><spring:message code="fg.unit"  /></th>     
   <th class="form-label fixhead" ><spring:message code="product.lot_batch2"  /></th>     
<th class="form-label fixhead" ><spring:message code="fg.drawer"  /></th>                                      
<th class="form-label fixhead" ><spring:message code="fg.payer"  /></th>     
<th class="form-label fixhead" ><spring:message code="file.file"  /></th>                          
                                        </tr>
                                    </thead>
                                    <tbody>
                                       

                                        <c:forEach items="${item }" var="rows"  varStatus="count">
				                                     
				                                     
				                                                            <tr>
                                        
            	 <td class="form-label fixhead" style="padding:5px;font-size:13px">${count.count }</td>   
            	 <td class="form-label fixhead" style="padding:5px;font-size:13px" >${rows.stock_receipt_dateupdate }</td>     
            	                          
 		  
 					
 		<td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="code">${rows.stock_batch }</td>  				 
<td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="stock_code"  title="${rows.stock_code }">${rows.stock_code }</td>     
<td class="form-label fixhead" style="padding:5px;font-size:13px"  data-custom="stock_name"   title="${rows.stock_list }">${rows.stock_list }</td> 
			<td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="code">${rows.tracking_number }</td>      
      
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.stock_received }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.stock_quantity }</td>
	<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.stock_remaining }</td>      
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.stock_unit }</td>  
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.stock_batch2 }</td>  
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.drawer }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.payer }</td>  


               <td >
                          	            <c:forEach items="${rows.file}" var="file"   varStatus="count">
                         
                       
								<a href="/render-filemanager?file=${rows.edata_id }&&count=${count.count}&&file_edata=${file.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;">
									       ${file.file_name_original }
								</a>
								<br>
                                   </c:forEach>     
                          	      </td>

                                        </tr>
									</c:forEach>					
				                                 
                                     
                           
                                        </tr>
                                    </tbody>
                                </table>
                               
                                
                                
                       <c:if test="${stock_code == '-' }">                 
                                
                                
                                
                                
                                
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

</c:if>

<script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>