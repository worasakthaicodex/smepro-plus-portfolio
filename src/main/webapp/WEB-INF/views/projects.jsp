<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<%@ include file="common/mainhead.jspf" %>    
    <!-- BEGIN: Page CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/horizontal-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-quill-editor.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/extensions/ext-component-toastr.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/app-email.css">
    <!-- END: Page CSS-->
    
    

<title>Projects</title>
<style>



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
    <div class="app-content content email-application">
        <a href="/projectslog-filemanager" class="btn btn-success" style="marging:5px">Log</a><br><br>

        <div class="card">
        

    <div class="block-rounded block">
  <div class="block-header block-header-default" style="display:none">
    <h3 class="block-title">     <spring:message code="menu.project"  var="type"/>
      <!---->
    </h3>
    <div class="block-options space-x-1">
      <div class="space-x-1">
       
        <div class="dropdown d-inline-block">
        
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
          
                  <th class="d-none d-xl-table-cell"> <spring:message code="menu.project" /></th>
          
     	  <th> <spring:message code="tables.start" text="เริ่ม" /></th>
          <th> <spring:message code="tables.end" text="สิ้นสุด" /></th>
      <th> <spring:message code="tables.inspector" text="ผู้ดูเเล" /></th>
    
            <th><spring:message code="tables.status" /></th>
            <th class="d-none d-sm-table-cell text-center"><spring:message code="tables.profit" /></th>
     
            <th class="d-none d-sm-table-cell text-end"><spring:message code="btn.view"  /></th>
          </tr>
        </thead>
        <tbody class="fs-sm">
       
       
       
         <c:forEach items="${project }" var="row" varStatus="count">  
          <tr>
            <td>
                 <p class="fs-sm fw-medium mb-0">${row.upload_pn_note }</p>
   
            </td>
            <td class="d-none d-xl-table-cell">
    
              <p class="fs-sm fw-medium mb-0">${row.upload_pn_date }</p>
            </td>
               <td class="d-none d-xl-table-cell">
    
              <p class="fs-sm fw-medium mb-0">${row.upload_pn_dateend }</p>
            </td>
               <td class="d-none d-xl-table-cell">
    
              <p class="fs-sm fw-medium mb-0">${row.upload_pn_app }</p>
            </td>
            
           
            <td>
            
         		     <c:set var="flow1" value="${ (row.upload_pn_sendnow *100) / (row.upload_pn_unsendnow+row.upload_pn_sendnow)  }" />
              <c:set var="flow" value="${ (row.upload_pn_send *100) / (row.upload_pn_unsend+row.upload_pn_send)  }" />
   		

                 <c:if test="${row.upload_pn_end == 0 }">  

      
             <c:if test="${flow1 < 50}">
              	<span class="badge  bg-danger "><spring:message code="due.risk"  /></span>
             </c:if>
                 <c:if test="${flow1 >= 50 && flow1 <= 75}">
              	<span class="badge  bg-warning "><spring:message code="due.normal"  /></span>
             </c:if>
                 <c:if test="${flow1 > 76}">
              	<span class="badge  bg-success "><spring:message code="due.flawless"  /></span>
             </c:if>
             
                </c:if>
                              <c:if test="${row.upload_pn_end == 1 }">  
                              	<span class="badge  bg-success "><spring:message code="due.success"  /></span>
                                  </c:if>
             
            </td>
            <td class="d-none d-sm-table-cell">
              <div class="progress mb-1" style="height: 5px;">
                <div class="progress-bar bg-success" role="progressbar" aria-valuenow="${flow }" aria-valuemin="0" aria-valuemax="100" style="width: ${flow}%;"></div>
              </div>
              <p class="fs-xs fw-semibold mb-0"> <fmt:formatNumber type = "number" maxFractionDigits = "0" value = "${flow+0.0  }" />%</p>
            </td>
        
            <td class="d-none d-sm-table-cell text-end">
      		  <a href="/overview-filemanager?id=${row.upload_pn_id }&&step=0&&group=1" class="btn btn-sm btn-success"><spring:message code="btn.view"  /></a>
            </td>
          </tr>

         </c:forEach>



         
         
      
         
        </tbody>
      </table>
    </div>
  </div>

</div>

</div>
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
    
    
    
    
    
    
    var element = document.getElementById("nav_project");
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


