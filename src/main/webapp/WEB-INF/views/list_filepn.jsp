<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@ include file="common/mainhead.jspf" %>    
    <!-- BEGIN: Page CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/horizontal-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-quill-editor.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/extensions/ext-component-toastr.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/app-email.css">
    <!-- END: Page CSS-->
    
    
    
<title>File Pn</title>
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
    <div class="app-content content email-application"
        <div class="card">
    <div class="block-rounded block">
  <div class="block-header block-header-default">
    <h3 class="block-title"> <spring:message code="log.RecordsList" text="" />
      <!---->
    </h3>
    <div class="block-options space-x-1">
      <div class="space-x-1">
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
                                            <th class="form-label fixhead">  <spring:message code="tables.No" text="ลำดับ"  /></th>
                                            <th class="form-label fixhead"><spring:message code="tables.record" text="ชื่อบันทึก"  /></th>
                                        
                                                 <th class="form-label fixhead"><spring:message code="tables.type" text="ประเภท" /></th>
                                              <th class="form-label fixhead"> <spring:message code="tables.due" text="กำหนด" /></th>
                                              
                                              
                                                     <th>   <spring:message code="tables.target1" text="เป้าหมาย" /></th>
      <th>   <spring:message code="tables.target2" text="ตรงกำหนด" /></th>
      <th>   <spring:message code="tables.target3" text="ล่าช้า" /></th>
      <th>   <spring:message code="tables.target4" text="ขาดส่ง" /></th>
                                              
                                              <th class="form-label fixhead"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                              <th class="form-label fixhead"><spring:message code="tables.inspector" text="ผู้ดูเเล" /></th>
                                                   <th class="form-label fixhead">Status</th>
                                       
                                            
                                              <th class="form-label fixhead"><spring:message code="tables.action" text="จัดการ" /></th>
                                        </tr>
                                    </thead>
      <c:set var="use" value="0" />
                                    <c:set var="notuse" value="0" />
                                     <c:forEach items="${upload_pns }" var="row" varStatus="count">
                                        <c:set var="due" value="" />
                                       <c:if test="${row.upload_pn_due ==  0 && row.upload_pn_type == 0 }">   
                      						<spring:message code="due.date" text="ทุกวัน"  var="due"/>
                     					 </c:if>
                                        <c:if test="${row.upload_pn_due ==  1 }">   
                      						<spring:message code="due.week" text="ทุกอาทิตย์"  var="due"/>
                     					 </c:if>
                     					    <c:if test="${row.upload_pn_due ==  2 }">   
                      						<spring:message code="due.month" text="ทุกเดือน"  var="due"/>
                     					 </c:if>
                     					
                     				
                                        <tr>
                                        
                                            <td>${count.count }</td>
                                            <td>
                                                                 
                                            
       <a href="/relationplan-condition/${row.upload_pn_monitor}" target="_blank"   
              onclick="openCenteredWindow(this.href, 'File Window', 1500, 600);return false;" title="Relation view"  style="display:none"> ${row.upload_pn_note } </a>       
                                   
                                          
                          
                                            ${row.upload_pn_note }
                                         
                                             
                                             
                                            </td>
                                         
                                          
                                           
                                              <td>
                                              
                                               <c:set var="type" value="" />
                                                  <c:if test="${row.upload_pn_type == 0}">    
                                                             <spring:message code="due.type1" text="กำหนดตามช่วงเวลา"  var="type"/>
                                                         </c:if>  
                                                      <c:if test="${row.upload_pn_type == 1}">    
                                                            <spring:message code="due.type2" text="กำหนดตามวันที่" var="type"/>
                                                         </c:if>  
                                                    <c:if test="${row.upload_pn_type ==2}">    
                                                            <spring:message code="due.type3" text="ไม่มีกำหนด" var="type"/>
                                                         </c:if>  
                                              
                                         ${type }
                                              </td>
                                            <td>${due }
                                            	     <c:if test="${row.upload_pn_type == 1 }">   
                      					 	${row.upload_pn_datedue }
                     					 </c:if>
                                            
                                            </td>
                                               <c:set var="target" value="${ (row.upload_pn_unsend+row.upload_pn_send)  }" />
                                                  <td> ${target}</td>
    
                                           <td>  ${row.upload_pn_ontime} </td>
                                           <td>    ${row.upload_pn_late} </td>
                                             <td>   ${target - row.upload_pn_send}  </td>
                                             <td title="${row.upload_pn_respon }">${row.upload_pn_respon }</td>
                                              <td>${row.upload_pn_app }</td>
                                         
                                        
                                           
                                         
                                        <td>
            
            
              <c:set var="flow" value="${ (row.upload_pn_send *100) / (row.upload_pn_unsend + row.upload_pn_send)  }" />

           
             <c:if test="${flow < 30}">
              	<span class="badge  bg-danger "><spring:message code="due.risk"  /></span>
             </c:if>
                 <c:if test="${flow > 30 && flow < 75}">
              	<span class="badge  bg-warning "><spring:message code="due.normal"  /></span>
             </c:if>
                 <c:if test="${flow > 76}">
              	<span class="badge  bg-success "><spring:message code="due.flawless"  /></span>
             </c:if>
            </td>  
                                         
                                         
                                         
             
                                               
                                               
                                            <td>
                                            
                     
       <a href="/detailspn-filemanager?id=${row.upload_pn_id}" class="btn btn-sm btn-info">
							          <spring:message code="btn.view" text="แก้ไข" /></a>
							          

                                            
                                            
                                            
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
    
    
    
    
    
    
    var element = document.getElementById("nav_other7");
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


