<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../../common/mainhead.jspf" %>    

       <link href="<c:url value="/resources/app-assets/css/pages/app-email.css" />" rel="stylesheet">
<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/2.4.0/jspdf.umd.min.js"></script>


<link href="<c:url value="/resources/app-assets/vendors/css/pickers/pickadate/pickadate.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/vendors/css/pickers/flatpickr/flatpickr.min.css" />" rel="stylesheet">

<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-flat-pickr.css" />" rel="stylesheet">
<link href="<c:url value="/resources/app-assets/css/plugins/forms/pickers/form-pickadate.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">

 <link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">
 

  <link href="<c:url value="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css" />" rel="stylesheet">
  
  
   <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-wizard.css" />" rel="stylesheet">
   <link href="<c:url value="/resources/app-assets/css/plugins/forms/form-validation.css" />" rel="stylesheet">




 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>File info</title>


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
      border-top:2px solid #dddddd;
   
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


 <style>
 
 
 
 
   #searchdata3 {
  width: 100%;
  max-width: 100%;
  overflow-x: auto; /* แก้ปัญหา table ล้นโดยให้ scroll แนวนอน */
}
 
  #transfer_searchdata {
  width: 100%;
  max-width: 100%;
  overflow-x: auto; /* แก้ปัญหา table ล้นโดยให้ scroll แนวนอน */
}
 #searchdata {
  width: 100%;
  max-width: 100%;
  overflow-x: auto; /* แก้ปัญหา table ล้นโดยให้ scroll แนวนอน */
}
 
 .profile-container {

  background-color: #fff;
 


  margin: 0 auto;
  font-family: sans-serif;
}

.profile-list {
  display: flex;
  flex-direction: column;
  font-size: 14px;
}

.profile-item {
  display: grid;
  grid-template-columns: 1fr 2fr;
  gap: 10px;
  padding: 12px 0;
  border-bottom: 1px solid #e5e7eb;
}

.profile-label {
  font-weight: 600;
  color: #111827;
}

.profile-value {
  color: #374151;
}

.profile-item:last-child {
  border-bottom: none;
}


.sidebar {
  padding: 16px;
  background-color: #f9fafb;
  font-family: sans-serif;
}

.menus {
  list-style: none;
  padding: 0;
  margin: 0;
}

.menus-item {
  display: block;
  padding: 8px 16px;
  font-size: 14px;
  font-weight: 500;
  color: #6b7280; /* text-gray-500 */
  border-radius: 8px;
  text-decoration: none;
  transition: background-color 0.2s, color 0.2s;
}

.menus-item:hover {
  background-color: #f3f4f6; /* hover:bg-gray-100 */
  color: #374151; /* hover:text-gray-700 */
}

.menus-item.active {
  background-color: #f3f4f6; /* bg-gray-100 */
  color: #374151; /* text-gray-700 */
}


td{
font-size: 16px;
}



.image-gallery {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 10px;
  min-height: 95vh;
}

.image-item img {
  width: 100%;
  border-radius: 5px;
  max-width: 175px;
  cursor: pointer;
  transition: 0.25s;
}

.image-item img:hover {
  transform: scale(0.97);
}




#caption {
  margin: auto;
  display: block;
  width: 80%;
  max-width: 700px;
  white-space: pre-line;
  text-align: center;
  color: #ccc;
  padding: 25px 0px 42px 0px;
  height: 150px;
  opacity: 0;
  visibility: hidden;
  transition: opacity 0.25s ease, visibility 0.25s;
}
  </style>

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../../common/subhead.jspf" %>  
<%@ include file="../../common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body" >
          <section id="multiple-column-form" >
          
     
     
      
          
          
                    <div class="row">
                 
                        
                           <div class="col-lg-10 col-12">
                         
                          <div class="card page" id="page1">     
                            <div class="card-body" >     
<div class="profile-container">
  <dl class="profile-list">
    <div class="profile-item">
      <dt class="profile-label"><spring:message code="material.stock_code"  /></dt>
      <dd class="profile-value">${data.stock_code }${data.stock_receipt_date }${data.stock_id }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label"><spring:message code="material.stock_name"  /></dt>
      <dd class="profile-value">${data.stock_list }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label"><spring:message code="stock.type"  /></dt>
      <dd class="profile-value">${data.stock_type }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label"><spring:message code="stock.groups"  /></dt>
      <dd class="profile-value">${data.stock_group }</dd>
    </div>
      <div class="profile-item">
      <dt class="profile-label"><spring:message code="workflows.details"  /></dt>
      <dd class="profile-value">${details }</dd>
    </div>

   <div class="profile-item">
      <dt class="profile-label"><spring:message code="bom.bom_price"  /></dt>
      <dd class="profile-value">${totalPrice }</dd>
    </div>
    
        <div class="profile-item">
      <dt class="profile-label"><spring:message code="stock.min"  /></dt>
      <dd class="profile-value">
${data.stock_min }
      </dd>
    </div>
        <div class="profile-item">
      <dt class="profile-label"><spring:message code="stock.max"  /></dt>
      <dd class="profile-value">
${data.stock_max }
      </dd>
    </div>
    
    <div class="profile-item">
      <dt class="profile-label"><spring:message code="workflows.remaining"  /></dt>
      <dd class="profile-value">
<b>${totalRemaining } ${data.stock_unit }</b>
      </dd>
    </div>






       <div class="profile-item">
      <dt class="profile-label"><spring:message code="tables.status"  /></dt>
      <dd class="profile-value">


<c:if test="${data.stock_status == 'IN_STOCK' }">
<span class="badge badge-light-success rounded-pill">${data.stock_status }</span>
</c:if>
<c:if test="${data.stock_status == 'OUT_OF_STOCK' }">
<span class="badge badge-light-danger rounded-pill">${data.stock_status }</span>
</c:if>
<c:if test="${data.stock_status == 'LOW_STOCK' || data.stock_status == 'HEIGHT_STOCK' }">
<span class="badge badge-light-warning rounded-pill">${data.stock_status }</span>
</c:if>
      </dd>
    </div> 
    
  </dl>
</div> 	 
	 
	 
	 <div class="image-gallery">


 <c:forEach  items="${ex_files}" var="row" varStatus="count">
  <div class="image-item">
    <img src="/document/${row.file_path}/${row.file_name }" >
  </div>
  </c:forEach>
  
  
  <!-- Repeat -->
</div>
	 
	 
	 


		 
 
 
 
 
 
 
 
 
 </div>
	      </div>  
                      
                                      <div class="card page" id="page2" style="display: none;">   
                                           <div class="card-header">
          <h4 class="card-title"><spring:message code="material.InventoryList"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>   
                            <div class="card-body" >     
   <table  style="font-size:16px">
                                    <thead>
                                        <tr>
                                        
            	 <th class="form-label fixhead" >#</th>   
            	 
<th class="form-label fixhead" ><spring:message code="product.lot_batch"  /></th>         	                          
<th class="form-label fixhead" ><spring:message code="material.stock_code"  /></th>     
<th class="form-label fixhead" ><spring:message code="material.stock_name"  /></th>  
<th class="form-label fixhead" ><spring:message code="maindata.batch_producer"  /> </th>  
<th class="form-label fixhead" ><spring:message code="fg.quantity"  /></th>     
<th class="form-label fixhead" ><spring:message code="fg.unit"  /></th>     
<th class="form-label fixhead" ><spring:message code="bom.bom_price"  /></th>   
<th class="form-label fixhead" ><spring:message code="maindata.storagelocation"  /></th>   
<th class="form-label fixhead" ><spring:message code="tables.status"  /></th>     


    
                                        </tr>
                                    </thead>
                                    <tbody>

 
														
				                   <c:forEach items="${substock }" var="row"  varStatus="count">   
				                                                              <tr>
                                        
            	 <td class="form-label fixhead" >${count.count }</td>   
            	 
            	                          
 					<td class="form-label fixhead"  data-custom="code" 
 					title="${row.lot_batch }">${row.lot_batch }</td>    
 					
 					 
<td class="form-label fixhead" >${row.stock_code }-${row.stock_id }</td>     
<td class="form-label fixhead"  >${row.stock_name }</td>     
<td class="form-label fixhead"  >${row.tracking_number }</td>  
<td class="form-label fixhead" >${row.stock_remaining }</td>   
<td class="form-label fixhead" > ${data.stock_unit }</td>  
<td class="form-label fixhead"> ${row.price }</td>  
<td class="form-label fixhead"> ${row.stock_storage }</td> 

<td class="form-label fixhead"  >
<c:if test="${row.stock_status == 'IN_STOCK' }">
<span class="badge badge-light-success rounded-pill">${row.stock_status }</span>
</c:if>
<c:if test="${row.stock_status == 'OUT_OF_STOCK' }">
<span class="badge badge-light-danger rounded-pill">${row.stock_status }</span>
</c:if>
<c:if test="${row.stock_status == 'LOW_STOCK' || row.stock_status == 'HEIGHT_STOCK' }">
<span class="badge badge-light-warning rounded-pill">${row.stock_status }</span>
</c:if>
</td>  





 
                               </tr>
												                  
				                       	</c:forEach>                  
				                                      
                                      
                                        
                                    </tbody>
                                    </table>     
       
 </div>
	      </div>  
                              
                         <div class="card page" id="page3" style="display: none;">     
                                                             <div class="card-header">
          <h4 class="card-title"><spring:message code="file.file"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>   
                            <div class="card-body" >     
  <%@ include file="../file.jsp" %>  
 </div>
	      </div>  
                      
                      
                      
                      <div class="card page" id="page4" style="display: none;">     
                           <div class="card-header">
          <h4 class="card-title"><spring:message code="form.disbursement"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div> 
                     
                      <div class="card-body" >   
                      
                        <div class="row">
                          <div class="col-md-6 col-12">
                            <div class="card">
                          
                                <div class="card-body">
               
                                        <div class="row">
                                       <form:form method="POST" id="search1" modelAttribute="user" action="/Movement_search-workflow">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><b><spring:message code="report.date" /></b></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                        <input type="date" id="monthPicker" name="search" class="form-control" required>
                                    
                                                    </div>
                                                </div>
                                            </div>
                                                 <input type="hidden" class="form-select"  name="stock_code" value="${data.stock_code }${data.stock_receipt_date }${data.stock_id }"> 
                                                      <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="id" value="${id }"> 
                                            </form:form>
                     <div class="col-12">
                          
                                  
                                       <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"
                                                 onclick='postdata("/Movement_search-workflow?${_csrf.parameterName}=${_csrf.token}","search1","searchdata")'
                                                ><spring:message code="btn.search" /></button>
                                            </div>
                           
                                        </div>
                                </div>
                            </div>
  							 </div>
  							 
  							 
  							 
  							 
  							 
  							 
  							     <div class="col-md-6 col-12">
                            <div class="card">
      
                                <div class="card-body">
                                   
                                        <div class="row">
                                          <form:form method="POST" id="search2"  modelAttribute="user" action="/Movement_search-workflow">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><b><spring:message code="report.month" /></b></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                    
                                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="id" value="${id }"> 
                                      <input type="hidden" class="form-select"  name="stock_code" value="${data.stock_code }${data.stock_receipt_date }${data.stock_id }"> 
                                        <input type="month" id="monthPicker" name="search" class="form-control" required >
                                        
                                                    </div>
                                                </div>
                                            </div>
                                          </form:form>
                     <div class="col-12">
                              
                                  
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"
                                                 onclick='postdata("/Movement_search-workflow?${_csrf.parameterName}=${_csrf.token}","search2","searchdata")'
                                                ><spring:message code="btn.search" /></button>
                                                
                                            </div>
                           
                                        </div>
                                        
                                        
                        
                                       
                                          
                           
                                </div>
                            </div>
  							 </div>
  							 
  							 <br>
  							 <div>                             
        <a href="#" id="export-csv" class="btn btn-sm btn-light  waves-effect waves-float waves-light" style="float:right;background-color:#fff" >  
                            Export
                             </a>
                               </div>
                             
  							 <div id="searchdata"></div>
  							 
  							 
                        </div>
    
                      </div>
                      </div>
                       
                       
                       
                       
                             <div class="card page" id="page5" style="display: none;">   
                                    <div class="card-header">
          <h4 class="card-title"><spring:message code="form.transfer"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>  
                      <div class="card-body" >   
                      
                      
     
                      
                        <div class="row">
                          <div class="col-md-6 col-12">
                            <div class="card">
                          
                                <div class="card-body">
               
                                        <div class="row">
                                       <form:form method="POST" id="search3" modelAttribute="user" action="/transfer_search-workflow">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><b><spring:message code="report.date" /></b></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                        <input type="date" id="monthPicker" name="search" class="form-control" required>
                                    
                                                    </div>
                                                </div>
                                            </div>
                                                 <input type="hidden" class="form-select"  name="stock_code" value="${data.stock_code }${data.stock_receipt_date }${data.stock_id }"> 
                                                      <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="id" value="${id }"> 
                                            </form:form>
                     <div class="col-12">
                          
                                  
                                       <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"
                                                 onclick='postdata("/transfer_search-workflow?${_csrf.parameterName}=${_csrf.token}","search3","transfer_searchdata")'
                                                ><spring:message code="btn.search" /></button>
                                            </div>
                           
                                        </div>
                                        
                                        
                        
                                         
                                          
                         
                                </div>
                            </div>
  							 </div>
  							 
  							 
  							 
  							 
  							 
  							 
  							     <div class="col-md-6 col-12">
                            <div class="card">
       
                                <div class="card-body">
                                   
                                        <div class="row">
                                          <form:form method="POST" id="search4"  modelAttribute="user" action="/transfer_search-workflow">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name"><b><spring:message code="report.month" /></b></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                    
                                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="id" value="${id }"> 
                                      <input type="hidden" class="form-select"  name="stock_code" value="${data.stock_code }${data.stock_receipt_date }${data.stock_id }"> 
                                        <input type="month" id="monthPicker" name="search" class="form-control" required >
                                        
                                                    </div>
                                                </div>
                                            </div>
                                          </form:form>
                     <div class="col-12">
                              
                                  
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"
                                                 onclick='postdata("/transfer_search-workflow?${_csrf.parameterName}=${_csrf.token}","search4","transfer_searchdata")'
                                                ><spring:message code="btn.search" /></button>
                                                
                                            </div>
                           
                                        </div>
                                        
                                        
                        
                                       
                                          
                           
                                </div>
                            </div>
  							 </div>
  							 
  							 <br>
  							 <div>                             
        <a href="#" id="export-csv2" class="btn btn-sm btn-light  waves-effect waves-float waves-light" style="float:right;background-color:#fff" >  
                            Export
                             </a>
                               </div>
                             
  							 <div id="transfer_searchdata" </div>
  							 
  							 
                        </div>
    
                      </div>
                      </div>
                       
       
                       
 </div>
               
               
               
                             <div class="card page" id="page6" style="display: none;">     
                   
                                            <div class="card-header">
                                <h4 class="card-title"><spring:message code="form.adjustment"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>  
                      <div class="card-body" >   
                      
                        <div class="row">
                    
  							 <br>
  							 <div>                             
        <a href="#" id="export-csv3" class="btn btn-sm btn-light  waves-effect waves-float waves-light" style="float:right;background-color:#fff" >  
                            Export
                             </a>
                               </div>
                             
  							 <div id="searchdata3">
  							 <br>
  							       <table  id="table3" width="100%">
                                    <thead>
                                        <tr>  <th class="form-label fixhead" style="width:10px">#</th>    
                                         <th class="form-label fixhead" ><spring:message code="fg.date"  /> </th>    
                                         
<th class="form-label fixhead" ><spring:message code="product.lot_batch"  /></th>      	                          
<th class="form-label fixhead" ><spring:message code="material.stock_code"  /></th>     
<th class="form-label fixhead" ><spring:message code="material.stock_name"  /></th>  
<th class="form-label fixhead" ><spring:message code="maindata.batch_producer"  /> </th>   
<th class="form-label fixhead" ><spring:message code="fg.reason"  /></th>   
<th class="form-label fixhead" ><spring:message code="fg.before"  /></th> 
<th class="form-label fixhead" ><spring:message code="fg.after"  /></th>    
<th class="form-label fixhead" ><spring:message code="fg.unit"  /></th>     
<th class="form-label fixhead" ><spring:message code="report.operation"  /></th>                             
<th class="form-label fixhead" ><spring:message code="dar.inspector"  /></th>         
<th class="form-label fixhead" ><spring:message code="file.file"  /></th>                           
                                        </tr>
                                    </thead>
                                    <tbody>
                                       

                                        <c:forEach items="${adjustment_sk }" var="rows"  varStatus="count">
				                                     
				                                     
				                                                            <tr>
                                        
            	 <td class="form-label fixhead" style="padding:5px;font-size:13px">${count.count }</td>   
            	 <td class="form-label fixhead" style="padding:5px;font-size:13px" >${rows.ska_date }</td>     
            	                          
 		  
 					
 <td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="code">${rows.ska_lot_batch }</td>       					 
<td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="stock_code"  title="${rows.ska_stock_code }">${rows.ska_stock_code }</td>     
<td class="form-label fixhead" style="padding:5px;font-size:13px"  data-custom="stock_name"   title="${rows.ska_stock_name }">${rows.ska_stock_name }</td> 
 <td class="form-label fixhead" style="padding:5px;font-size:13px" data-custom="code">${rows.ska_tracking_number }</td>       
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.ska_reason }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.ska_remaining }</td>  
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.ska_quantity }</td>  
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.ska_unit }</td>  
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.data_nameadd }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px">${rows.ska_approved_by }</td>  

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
				                                 
                                        
                                    </tbody>
                                </table>
  							 </div>
  							 
  							 
                        </div>
    
                      </div>
                      </div>        
                      
                       <div class="card page" id="page7" style="display: none;">     
                           
            <div class="card-header">
                                <h4 class="card-title"><spring:message code="workflows.Traceability"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>  
                      <div class="card-body" >  
                               <div class="row">
                                          <form:form method="POST" id="searchs"  modelAttribute="user" action="/traceability_search-workflow">
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="first-name">
                                                        <b><th class="form-label fixhead" ><spring:message code="maindata.batch_producer"  /> </th>    </b></label>
                                                    </div>
                                                    <div class="col-sm-9">
                                    
                                        <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                     <input type="hidden" class="form-select"  name="id" value="${id }"> 

                                        <select name="search" class="form-select select2">
                                      
                                      <option value="-">-</option>
                                       <c:forEach items="${list_tracking_number }" var="row"  varStatus="count">   
                                        <option value="${row.ski_tracking_number }">${row.ski_tracking_number }</option>
                                        </c:forEach>
                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                               
                                          </form:form>
                     <div class="col-12">
                              
                                  
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light"
                                                 onclick='postdata("/traceability_search-workflow?${_csrf.parameterName}=${_csrf.token}","searchs","traceability_searchdata")'
                                                ><spring:message code="btn.search" /></button>
                                                
                                                    <button onclick="printDiv()" class="btn btn-light">Print</button>
                                                
                                            </div>
                           
                           
                            <div id="traceability_searchdata">
                            </div>
                           
                           
                           
                                        </div>
                       </div>   
          </div>      
          
          
         
          
          
            <div class="card page" id="page8" style="display: none;">     
                           
            <div class="card-header">
                                <h4 class="card-title"><spring:message code="workflows.log"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>  
                      <div class="card-body" >  
                       <table  id="tables" width="100%">
                                    <thead>
<tr>  <th class="form-label fixhead" style="width:10px">#</th>    
 <th class="form-label fixhead" ><spring:message code="fg.date"  /> </th>    
 <th class="form-label fixhead" ><spring:message code="material.stock_code"  /></th>   	

    <th class="form-label fixhead" ><spring:message code="material.stock_name"  /></th>     
<th class="form-label fixhead" ><spring:message code="stock.type"  /></th>  
<th class="form-label fixhead" ><spring:message code="stock.groups"  /></th>   
<th class="form-label fixhead" ><spring:message code="bom.bom_price"  /></th>   
<th class="form-label fixhead" ><spring:message code="stock.min"  /></th>
<th class="form-label fixhead" ><spring:message code="stock.max"  /> </th>    
<th class="form-label fixhead" ><spring:message code="fg.unit"  /></th>    
         </tr>
                                    </thead>
                                    <tbody>
                                       
                                       
                                
                                        <c:forEach items="${fgList }" var="rows"  varStatus="count">
				                                   			                                     
 <tr>
<td class="form-label fixhead" style="padding:5px;font-size:13px">${count.count }</td>   
 <td class="form-label fixhead" style="padding:5px;font-size:13px" >${rows.skim_date }</td>   
 <td class="form-label fixhead" style="padding:5px;font-size:13px"  title="${rows.skim_stock_code }">${rows.skim_stock_code }</td>     	
<td class="form-label fixhead" style="padding:5px;font-size:13px"  title="${rows.skim_stock_name }">${rows.skim_stock_name }</td>      
<td class="form-label fixhead" style="padding:5px;font-size:13px"   title="${rows.skim_stock_type }">${rows.skim_stock_type }</td> 
<td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.skim_stock_group }">${rows.skim_stock_group }</td>   
<td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.skim_stock_details }">${rows.skim_stock_details }</td>      	
  <td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.skim_stock_min }">${rows.skim_stock_min }</td>
  <td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.skim_stock_max }">${rows.skim_stock_max }</td>     
   <td class="form-label fixhead" style="padding:5px;font-size:13px" title="${rows.skim_stock_unit }">${rows.skim_stock_unit }</td>    
</tr>
</c:forEach>					
				                                 
                                        
                                    </tbody>
                                </table>
             </div>
          
               </div>
                      
                       <div class="card page" id="page9" style="display: none;">     
                           
            <div class="card-header">
                                <h4 class="card-title"><spring:message code="rating.comment"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>  
                      <div class="card-body" > 
                   
                      
                        <%@ include file="cm.jspf" %>     
             </div>
          
               </div>
          
          
                        <div class="card page" id="page10" style="display: none;">     
                           
            <div class="card-header">
                                <h4 class="card-title"><spring:message code="report.datausage"  /></h4>
          <div class="d-flex align-items-center">
          </div>
        </div>  
                      <div class="card-body" > 
                   
                      
                        <%@ include file="datausage.jspf" %>     
             </div>
          
               </div>
          
                     
      </div>
                                 
                      <div class="col-lg-2 col-12">
                           
                                  <div class="card" >
                     <div class="sidebar">
<ul class="menus" id="tabdata"> 
<li><a href="#" class="menus-item active" data-custom="1"><spring:message code="form.stockcard"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="2"><spring:message code="material.InventoryList"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="3"><spring:message code="file.file"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="4"><spring:message code="form.disbursement"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="5"><spring:message code="form.transfer"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="6"><spring:message code="form.adjustment"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="7"><spring:message code="workflows.Traceability"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="8"><spring:message code="workflows.log"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="9"><spring:message code="rating.comment"  /></a></li> 
<li><a href="#" class="menus-item" data-custom="10"><spring:message code="report.datausage"  /></a></li> 
</ul>
</div>
                     	</div>  
						</div>  
                        
</div>



















</div>
                  
                 
                 
                 
             
                 
                 
            
            
     
                </section>

            </div>
        </div>
    </div>
 
 
  


 
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



 <div style="clear:both"></div>
    
    
    
<%@ include file="../../common/mainfoot.jspf" %>  


	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>

 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>

 
    <script src="<c:url value="/resources/app-assets/js/scripts/tables/table-datatables-advanced.js" />"></script>
 
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/legacy.js" />"></script>
  <script src="<c:url value="/resources/app-assets/vendors/js/pickers/pickadate/picker.date.js" />"></script>
 <script src="<c:url value="/resources/app-assets/vendors/js/pickers/flatpickr/flatpickr.min.js" />"></script>
 <script src="<c:url value="/resources/app-assets/js/scripts/forms/pickers/form-pickers.js" />"></script>



 <script src="<c:url value="/resources/app-assets/js/scripts/extensions/ext-component-drag-drop.js" />"></script>
<script src="<c:url value="/resources/app-assets/vendors/js/extensions/dragula.min.js" />"></script>
	  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>


 <%@ include file="../../other/alert.jsp" %>

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
	    
	    function printDiv() {
	    	  var content = document.getElementById('traceability_searchdata').innerHTML;
	    	  var mywindow = window.open('', 'Print', 'height=600,width=800');

	    	  mywindow.document.write('<html><head><title>Print</title>');
	    	  mywindow.document.write(`<style>
	    	    td {
	    	      text-align: center;
	    	      border: 1px solid #dddddd;
	    	      max-width: 140px;
	    	      font-size: 12px;
	    	      overflow: hidden;
	    	      text-overflow: ellipsis;
	    	      white-space: nowrap;
	    	      min-width: 5px;
	    	      padding: 3px;
	    	    }
	    	    th {
	    	      text-align: center;
	    	      border: 1px solid #dddddd;
	    	      background-color: #f2f2f2;
	    	      height: 30px;
	    	      font-size: 10px;
	    	      padding: 3px;
	    	      max-width: 140px;
	    	      text-overflow: ellipsis;
	    	      min-width: 5px;
	    	    }
	    	    table {
	    	      border-collapse: collapse;
	    	      width: 100%;
	    	    }
	    	  </style>`);

	    	  mywindow.document.write('</head><body>');
	    	  mywindow.document.write(content);
	    	  mywindow.document.write('</body></html>');

	    	  mywindow.document.close();
	    	  mywindow.focus();

	    	  mywindow.print();
	    	  mywindow.close();

	    	  return true;
	    	}

	    
	    
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
    
    
    
    function getdata(url) {
    	
    	  if (confirm("You want to take action.?")) {
    	    $.ajax({
    	      type: 'GET',
    	      url: url,
    	      success: function(data) {
    	        $("#savedata").html(data);
    	      }
    	    });
    	  } else {
    	    console.log("ยกเลิกการโหลดข้อมูล");
    	  }
    	  return false;
    	}


    </script>				



<script> 	



function postdata(Mode, Mode2, Mode3) {

    let targetDiv = $("#" + Mode3);
    
    // ทำให้เนื้อหาเก่าจางลง
    targetDiv.css("opacity", "0.5").css("position", "relative");

    // สร้าง Spinner อยู่ตรงกลางของ div
    let loadingSpinner = `
        <div class="spinner-overlay">
            <div class="spinner-border text-primary" role="status">
                <span class="visually-hidden">Loading...</span>
            </div>
        </div>`;

    // แทรก Spinner เข้าไปใน div
    targetDiv.append(loadingSpinner);

		
	
	
    var formData = new FormData($("#" + Mode2)[0]);

    $.ajax({
        url: Mode,
        type: "POST",
        data: formData,
        processData: false,
        contentType: false,
        success: function(result){
            $('#' + Mode3).html(result);
            targetDiv.css("opacity", "1"); // คืนค่า opacity ปกติ
        },
        error: function(jqXHR, textStatus, errorThrown) {
                window.location.href = '/login'; // URL ของหน้า login
       
        }
    });

   // $("#" + Mode2)[0].reset();
   
    return false;
}
	    
</script> 		    
	    
	   <script>
  // เมื่อโหลดหน้าเว็บ: ซ่อนทุกหน้า ยกเว้น active
  document.querySelectorAll('.page').forEach(div => div.style.display = 'none');
  const activeTab = document.querySelector('.menus-item.active');
  if (activeTab) {
    const pageId = 'page' + activeTab.getAttribute('data-custom');
    document.getElementById(pageId).style.display = 'block';
  }

  // เมื่อคลิกเปลี่ยนแท็บ
  document.querySelectorAll('.menus-item').forEach(item => {
    item.addEventListener('click', function (e) {
      e.preventDefault();


      
      // ลบ active เดิม
      document.querySelectorAll('.menus-item').forEach(el => el.classList.remove('active'));

      // ตั้ง active ใหม่
      this.classList.add('active');

      // ซ่อนทุกหน้า
      document.querySelectorAll('.page').forEach(div => div.style.display = 'none');

      // แสดงหน้าที่ถูกเลือก
      const pageId = 'page' + this.getAttribute('data-custom');
      const page = document.getElementById(pageId);
      if (page) {
        page.style.display = 'block';
      }
    });
  });
</script> 

<script>

document.getElementById('export-csv3').addEventListener('click', function () {
	  const table = document.getElementById('table3');
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


<script>

document.getElementById('export-csv2').addEventListener('click', function () {
	  const table = document.getElementById('table2');
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
<script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>