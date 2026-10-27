<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    



 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Finance info</title>




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
                            <h2 class="content-header-title float-start mb-0">Reporting</h2>
                            <div class="breadcrumb-wrapper">
                                <ol class="breadcrumb">
                                    <li class="breadcrumb-item"><a href="/setting2-workflow/${id}">${workflows.workflow_name}</a>
                                    </li>
                                                <li class="breadcrumb-item"><a href="/monitoringapp-workflow/${id}">Reports</a>
                                    </li>
                                    <li class="breadcrumb-item active">Report
                                    </li>
                                </ol>
                                   
                          
                            </div>
                         
                        </div>
                    </div>
                </div>
     
            </div> 

  
 
          

<div class="content-body">
                <!-- Knowledge base Jumbotron -->
                <section id="knowledge-base-search">
                    <div class="row">
                        <div class="col-12">
                            <div class="card knowledge-base-bg text-center" style="background-image: url('/resources/app-assets/images/banner/banner.png')">
                                <div class="card-body">
                                    <h2 class="text-primary"><spring:message code="report.finance"  /></h2>
                                    <p class="card-text mb-2">
                                        <span><spring:message code="finance.data"  /></span>
                                    </p>
                                   
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
                <!--/ Knowledge base Jumbotron -->

                <!-- Knowledge base -->
                <section id="knowledge-base-content">
                    <div class="row kb-search-content-info match-height">
                        <!-- sales card -->
                        <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                <a href="/generalaccount_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FN1.jpg" style="height:250px;" class="card-img-top" alt="knowledge-base-image">

                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.generalaccount"  /></h4>
                                     
                                    </div>
                                </a>
                            </div>
                        </div>
                        
                        
                        
                                  <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                <a href="/accountledger_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FNS.jpg" style="height:250px;" class="card-img-top" alt="knowledge-base-image">

                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.accountledger"  /></h4>
                                     
                                    </div>
                                </a>
                            </div>
                        </div>
                        
  <!-- marketing -->
                        <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                <a href="/trialbalance_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FN.jpg" style="height:250px;" class="card-img-top" alt="knowledge-base-image">
                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.trialbalance"  /></h4>
                                     
                                    </div>
                                </a>
                            </div>
                        </div>
                        <!-- marketing -->
                        <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                <a href="/balancesheet_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FN2.jpg" style="height:250px;" class="card-img-top" alt="knowledge-base-image">
                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.balancesheet"  /></h4>
                                     
                                    </div>
                                </a>
                            </div>
                        </div>

                        <!-- api -->
                        <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                  <a href="/Profit_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FN3.jpg" style="height:250px;" class="card-img-top" alt="knowledge-base-image">
                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.Profit"  /></h4>
                                      
                                    </div>
                                </a>
                            </div>
                        </div>
       <!-- api -->
                        <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                  <a href="/budget_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FN4.jpg"  style="height:250px;"class="card-img-top" alt="knowledge-base-image">
                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.budget"  /></h4>
                                      
                                    </div>
                                </a>
                            </div>
                        </div>
                        
                                  <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                  <a href="/disbursement_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FN5.jpg" style="height:250px;" class="card-img-top" alt="knowledge-base-image">
                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.disbursement"  /></h4>
                                      
                                    </div>
                                </a>
                            </div>
                        </div>
                  
                                  <div class="col-md-4 col-sm-6 col-12 kb-search-content">
                            <div class="card">
                                  <a href="/asset_finance-workflow/${id }">
                                    <img src="/resources/app-assets/images/illustration/FN6.jpg" style="height:250px;" class="card-img-top" alt="knowledge-base-image">
                                    <div class="card-body text-center">
                                        <h4><spring:message code="finance.asset"  /></h4>
                                      
                                    </div>
                                </a>
                            </div>
                        </div>
  


                   
                    </div>
                </section>
                <!-- Knowledge base ends -->

            </div>
                    </div>           
                    
                    
                    
                    
                       
                   
            
          
          

                  
          
                 
                 
            
            
     
                </section>

            </div>
        </div>
 
 
  
 
 

                                      
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



 <div style="clear:both"></div>
    
    
    
<%@ include file="../common/mainfoot.jspf" %>  


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



<script>
    // ดึงปุ่มทั้งหมดที่มี class nav-link
    const links = document.querySelectorAll(".nav-link");

    // วนลูปทุกลิงก์แล้วผูก event
    links.forEach((link, index) => {
        link.addEventListener("click", function (e) {
            e.preventDefault(); // ป้องกันไม่ให้ลิงก์ redirect

            // ซ่อน div ทั้งหมด
            document.querySelectorAll(".main-tab").forEach(tab => {
                tab.style.display = "none";
            });

            // ลบ class active ออกจากทุก nav-link
            links.forEach(l => l.classList.remove("active"));

            // ใส่ class active กับปุ่มที่คลิก
            this.classList.add("active");

            // แสดง div ตาม index ที่คลิก
            const targetId = 'main-tab'+(index);

            
            document.getElementById(targetId).style.display = "block";
        });
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
