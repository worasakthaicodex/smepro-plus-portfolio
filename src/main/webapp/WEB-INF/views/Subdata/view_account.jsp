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

.menu {
  list-style: none;
  padding: 0;
  margin: 0;
}

.menu-item {
  display: block;
  padding: 8px 16px;
  font-size: 14px;
  font-weight: 500;
  color: #6b7280; /* text-gray-500 */
  border-radius: 8px;
  text-decoration: none;
  transition: background-color 0.2s, color 0.2s;
}

.menu-item:hover {
  background-color: #f3f4f6; /* hover:bg-gray-100 */
  color: #374151; /* hover:text-gray-700 */
}

.menu-item.active {
  background-color: #f3f4f6; /* bg-gray-100 */
  color: #374151; /* text-gray-700 */
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
                 
                        
                           <div class="col-lg-9 col-12">
                         
                          <div class="card" >     
                            <div class="card-body" >     
<div class="profile-container">
  <dl class="profile-list">
    <div class="profile-item">
      <dt class="profile-label">Title</dt>
      <dd class="profile-value">${data.register_daterun }${data.register_id }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label">Name</dt>
      <dd class="profile-value">${data.register_name }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label">Occupation</dt>
      <dd class="profile-value">${data.register_groups }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label">Salary</dt>
      <dd class="profile-value">${data.register_address }</dd>
    </div>

    <div class="profile-item">
      <dt class="profile-label">Bio</dt>
      <dd class="profile-value">
${data.register_note }
      </dd>
    </div>
  </dl>
</div>
 
 
 
 </div>
	      </div>  
                        </div>
                           <div class="col-lg-3 col-12">
                           
                                  <div class="card" >
                     <div class="sidebar">
  <ul class="menu">
    <li><a href="#" class="menu-item active">General</a></li>
    <li><a href="#" class="menu-item">Teams</a></li>
    <li><a href="#" class="menu-item">Billing</a></li>
    <li><a href="#" class="menu-item">Invoices</a></li>
    <li><a href="#" class="menu-item">Account</a></li>
  </ul>
</div>
                     	</div>  
						</div>  
                        
</div>
</div>
                  
                 
                 
                 
                <!--/ add new card modal  -->
                   
  
              
                    
                 
                 
            
            
     
                </section>

            </div>
        </div>
    </div>
 
 
  
 
 
 
  <div class="modal fade" id="addNew" tabindex="-1" aria-labelledby="exampleModalScrollableTitle"  aria-modal="true" >
                                                <div class="modal-dialog modal-lg modal-dialog-scrollable">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalScrollableTitle"><spring:message code="forms.AddForm" /></h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        
                                                                        <form method="POST"  modelAttribute="datalink" action="/addsublink2-workflow">
                                                        <div class="modal-body">
                     
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 <input  type="hidden" name="datalink_workflow" value="${workflows.workflow_id }">
                  
         
                                 
                             <div class="col-md-12">
                                                    <label class="form-label"><spring:message code="forms.Name" />:</label>
                                       <select class="form-select"  name="datalink_idrefer" id="datalink_idrefer" >
                     
                                 <c:forEach items="${conditionadd }" var="row" varStatus="count">
                                        <option value="${row.condition_id}">${row.condition_title }</option>
                                 </c:forEach>   
                                        </select> 
                                 
                                              <div class="col-md-12">
                                                              <label class="form-label"><spring:message code="forms.type" />:</label>
                                       <select class="form-select"  name="datalink_name" id="datalink_name" >
                     
                           
                                 		 <option value="-">-</option>
                    		  
                            	
                                 	 <optgroup label="<spring:message code="forms.type2" />">	 
                                    	
                                        		 <option value="purchase_requisition">Purchase requisition ***</option>
                                                 <option value="purchase_order">Purchase order *** </option>
                                                 	<option value="goods_receipt">Goods receipt</option>
                                       </optgroup>            
                                             
                                                         <optgroup label="<spring:message code="forms.type4" />">	 
                                                 <option value="requisition_form">Requisition form *** </option>
                                                     </optgroup>  
                                                     	 <optgroup label="<spring:message code="forms.type3" />">	        
                                                <option value="petty_cash_book">Petty cash book *** </option>
                                                        </optgroup> 
                                                        <optgroup label="<spring:message code="forms.type5" />">	
                                                             	 
                                                    <option value="inventory_registration">Inventory Registration form *** </option>            	         
                                                    <option value="stock_requisition">Stock Requisition *** </option>
                                                    <option value="direct_goods_receipt">Direct goods Receipt***</option>
                                                        </optgroup> 
                                           
                                           
                                           
                                                         
                                        </select> 
                                        
  											 </div>
               
               
               
                           <br>
            
                                          <br>
                                          <br>
            
                                          <br>  <br>
            
                                          <br>  <br>
            
                                          <br>  
                                                 
                                       <br>   <br>   <br>  
                                    
                                                        </div>
                                                        <div class="modal-footer">
                                                            <button type="submit" 
                                                class="btn btn-primary me-1 waves-effect waves-float waves-light">Save</button>
                                                        </div>
                                              	</form>             
                                                        
                                                    </div>
                                                </div>
                                            </div> 
                       
 
 
   *** Requested
 
 
 
 
 
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



