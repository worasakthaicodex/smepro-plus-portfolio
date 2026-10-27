<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../common/mainhead.jspf" %>    
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<title>Edata</title>


<style>
		 table {
		    border-collapse:collapse;
		    margin: 0;
		  width: 100%;
		   border: 1px solid #eee;
		   
		}
		
		
		td {
    text-align: center;
   border: 1px solid #eee;
      max-width: 200px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
  padding:10px;
}
td  .action{
 text-align: center;
    border: 1px solid #eee;
    max-width: 200px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
    
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #eee;
  background-color: #f2f2f2;
     height: 30px;
     
}
		
</style>

</head>

<div class="modal-body flex-grow-1 pb-sm-0 pb-1">
                                        <ul class="nav nav-tabs tabs-line" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-bs-toggle="tab" href="#details-tab" role="tab" aria-controls="details-tab" aria-selected="true">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                                                    <span class="align-middle ms-25">ทะเบียนสินค้า/บริการ</span>
                                                </a>
                                            </li>
 
                                        </ul>
                                        <div class="tab-content" id="myTabContent">
                                         
                                     
                                            <div class="tab-pane fade active show" id="details-tab" role="tabpanel" aria-labelledby="details-tab">
                                             
                                                <hr class="my-2">
                                
                                            
                                                            
          
                                                           
                                                        
                                                          <input type="hidden"  name="item_id" value="${data_id }" class="form-control">
                                                          <input type="hidden"  name="upload_pn_ids" value="${upload_pn_id}" class="form-control">
                                                          
                                                            
                                                       <input type="text" class="files-filter2" placeholder="Search" style="float:right" onkeyup="search()">
                                                         
                                                        
                                                          <div >
                                                                <button type="submit"  class="btn btn-sm btn-primary waves-effect waves-float waves-light">เพิ่ม</button>
                                                            </div>             
                                                       <br>
                                                              
							<div style="height:500px; overflow-y:auto;"> <!-- ปรับ over-flow เป็น overflow-y -->
								    <table class="tabledata" style="height:auto; width: 100%;">
								        <thead>
								            <tr>
								                <th class="form-label fixhead" style="position: sticky; top: 0; background-color: #fff; z-index: 1;">#</th>
								                <th class="form-label fixhead" style="position: sticky; top: 0; background-color: #fff; z-index: 1;">
								                    <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" />
								                </th>
								                <th class="form-label fixhead" style="position: sticky; top: 0; background-color: #fff; z-index: 1;">
								                    <spring:message code="item.unit" text="หน่วยนับ" />
								                </th>
								                <th class="form-label fixhead" style="position: sticky; top: 0; background-color: #fff; z-index: 1;">
								                    <spring:message code="item.cost" text="ราคา" />
								                </th>
								                <th class="form-label fixhead" style="position: sticky; top: 0; background-color: #fff; z-index: 1;">
								                    ผู้ค้า
								                </th>
								                <th class="form-label fixhead" style="position: sticky; top: 0; background-color: #fff; z-index: 1;">
								                    เลือก
								                </th>
								            </tr>
								        </thead>
                                    <tbody>
                          
                          
                         						 <c:set var="Clear_status" value="" />
                                      				  <c:forEach items="${items }" var="row" varStatus="count">
				                                        <tr class="datatable">
				                                            <td>${count.count }</td >
				                                             <td>${row.description }</td >
				                                            <td>${row.unit }</td >
				                                            <td>${row.cost }</td >     
				                                        <td> ${row.distributor_name}</td >    
            											<td> <input type="checkbox" name="selectedIds" value="${row.id }"></td >    
				                                        </tr>
                                        </c:forEach>
                                        
                                        
                                    </tbody>
                                    </table>   
                       </div>
    	
                               	                 
                                            </div>
                                           
                                           
                              
                                             
                                            </div>
                                        </div>
                             
                                    
   <script>
   
       function search() {

	     var filter = $('.files-filter2').val();
	      $('.datatable').filter(function() {
	    
	    	    $(this).hide();
	            var regex = new RegExp(filter, 'ig');
	          
	            if (regex.test($(this).text())) {
	                $(this).show();
	            } else {
	                $(this).hide();
	            }
	            
	            
	        });
	  
   
}
   
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>                                 
                                    