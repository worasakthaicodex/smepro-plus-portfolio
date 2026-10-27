<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    



    <!-- BEGIN: Vendor CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/vendors.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/select/select2.min.css">
    <!-- END: Vendor CSS-->

   <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-wizard.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-validation.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/modal-create-app.css">



<title>List</title>
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
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"><spring:message code="menu.worksystem" /></h4>
                                 
                      
                            </div>
                             
                            <div class="card-body">
                             
                                 
                                         <a href="#"  data-bs-toggle="modal" data-bs-target="#addNew">
                                         <button class="btn btn-primary waves-effect waves-float waves-light" >
                                         <spring:message code="btn.add"  /></button></a>
                                               <a href="/KPI_Measurement_Rules-workflow"  >
                                         <button class="btn btn-primary waves-effect waves-float waves-light" >KPI Measurement Rules
                                         </button></a>
                                         
                               
                                         
                                        
                                 <br>   <br>
                             <div class="table-responsive">
                   <table class="table" >
                                    <thead>
                                        <tr >
                                            <th >
                                            <spring:message code="tables.No" text="ลำดับ​" />
                                            </th>
                                            
                                            
                                                <th ><spring:message code="workflows.DataLink" /></th>
                                            <th > <spring:message code="workflows.admin" /></th>
                                          <th > <spring:message code="workflows.creator" /></th>
                                          
                                          
                                           <th >
                                             <spring:message code="tables.action" text="ดำเนินการ" />
                                           </th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        
                                         <c:forEach items="${workflow }" var="row" varStatus="count">
                                         
                     
                                        <tr class="datatable">
                                        
                                            <td>${count.count }</td>
                                            <td>${row.workflow_name }</td>
                                    <td>${row.workflow_name }</td>
                                 
                                     <td></td>
                                                   <td>

                                        
                                       
                                            
                                            
                                            
                                            
                                                  <a href="/setting2-workflow/${row.workflow_id}" class="btn btn-sm  btn-light" >
                                                           <spring:message code="btn.Setting" />
                                          </a>  
                                             
                                                   </td>
                                        </tr>
                                          </c:forEach>
                                    </tbody>
                                </table></div>
                   </div>   </div>
                    </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



  <div class="modal fade" id="addNew" tabindex="-1" aria-labelledby="exampleModalScrollableTitle"  aria-modal="true" >
  
  
                                                <div class="modal-dialog modal-lg modal-dialog-scrollable">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalScrollableTitle"><spring:message code="menu.worksystem" /></h5>
                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                        </div>
                                                        
                                                                        <form method="POST"  modelAttribute="datalink" action="/neweflow2-workflow">
                                                        <div class="modal-body">
                     
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                             
                                 
                             <div class="col-md-12">
                                                    <label class="form-label"><spring:message code="menu.worksystem" /> :</label>
                       
                         <input type="text" name="workflow_name" class="form-control" required>
                                 
                                              <div class="col-md-12">
                                                              <label class="form-label"><spring:message code="forms.type" />:</label>
                                       <select class="form-select"  name="workflow_app" id="workflow_app" >
                                 	 <optgroup label="<spring:message code="forms.group" />">	 
                                                <option value="<spring:message code="forms.type2" />"><spring:message code="forms.type2" /></option>
                                                <option value="<spring:message code="forms.type3" />"><spring:message code="forms.type3" /></option>
                                                <option value="<spring:message code="forms.type4" />"><spring:message code="forms.type4" /></option>
                                                <option value="<spring:message code="forms.type5" />"><spring:message code="forms.type5" /></option>
                                       </optgroup>            
                                        <optgroup label="<spring:message code="forms.group2" />">	 
                                        		 <option value="<spring:message code="forms.type6" />" selected><spring:message code="forms.type6" /></option>
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
                       
 





<%@ include file="../common/mainfoot.jspf" %>  
 
    <script src="/resources/app-assets/vendors/js/forms/wizard/bs-stepper.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/select/select2.full.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/cleave/cleave.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/cleave/addons/cleave-phone.us.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/validation/jquery.validate.min.js"></script>
    <!-- END: Page Vendor JS-->

    <!-- BEGIN: Theme JS-->
    <script src="/resources/app-assets/js/core/app-menu.js"></script>
    <script src="/resources/app-assets/js/core/app.js"></script>
    <!-- END: Theme JS-->

    <!-- BEGIN: Page JS-->
    <script src="/resources/app-assets/js/scripts/pages/modal-add-new-cc.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/page-pricing.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-add-new-address.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-create-app.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-two-factor-auth.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-edit-user.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-share-project.js"></script>
    <script>
    
    var element = document.getElementById("nav_workflowsystem");
    element.classList.add("active");  
    
    
    
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
        

$('li.level2[data-customer]').hide();
$('li.level3[data-customer]').hide();    


   $('input[name="condition_industry"]').change(function() {
    var selectedIndustry = $(this).val();

   
    
    // ซ่อนทุก <li class="level2"> ที่มี data-customer ไม่เท่ากับค่าที่ถูกเลือก
    $('li.level2[data-customer]').each(function() {
    	console.log($(this).data('customer'));
    	
        if ($(this).data('customer') != selectedIndustry) {
        	
        	
            $(this).hide();
        } else {
            $(this).show();
        }
    });
    
    // แสดงทุก <li class="level3">
   // $('li.level3').show();
});
        

        
        $('input[name="condition_department"]').change(function() {
            var selectedDepartment = $(this).val();

            // ซ่อนทุก <li class="level3"> ที่มี data-customer ไม่เท่ากับค่าที่ถูกเลือก
            $('li.level3[data-customer]').each(function() {
                if ($(this).data('customer') != selectedDepartment) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            });

            // แสดงทุก <li class="level2">
            $('li.level2').show();
        });
        
    </script>
</body>

 <script>
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>
