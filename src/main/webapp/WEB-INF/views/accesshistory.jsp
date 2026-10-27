<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="common/mainhead.jspf" %>    

<title>Log pn</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

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
                  
                   <div class="col-7">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"><spring:message code="new.history" text="ประวัติการอ่านเอกสารเเจกจ่ายแยกตามเเหล่งที่เก็บ" /></h4>

                            </div>
                            
                             
                            
                            
                            <div class="card-body"> 
                            
                              <button onclick="download('xlsx');"  type="button"  class="btn btn-primary"> <spring:message code="btn.export" /></button>
                              <br> <br>
                              <table class="table" id="data-table">
                                    <thead>
                                        <tr>
                                            <th><spring:message code="label.no" text="ลำดับ" /></th>
                                          
                                            <th><spring:message code="tables.docname" text="ชื่อเอกสาร" /></th>
                                            
                                                 <th><spring:message code="file.path" text="ที่เก็บเอกสาร" /></th>
                                            <th><spring:message code="dar.view" text="จำนวนครั้งที่มีการเปิดดู" /></th>
                                            <th>  <spring:message code="tables.action" text="ดำเนินการ" /></th>
                                        </tr>
                                        </thead>
                                       
                                        <c:forEach items="${doc }" var="row" varStatus="count">
                                      
                                        <tr >
                                            <td> ${count.count } </td>
                                             <td> ${row.file_name_original }</td>
                                             <td> ${row.file_path_full }</td>
                                             <td> ${row.log_note }</td>
                                               <th> <a  href="/datadistribution-log?refer=${row.log_refer }" class="btn btn-sm btn-light">
                                               <spring:message code="btn.view" /></a></th>
                                      </tr>
                                      
                            		  </c:forEach>
                                        
                                         </table>
                            
                              </div>
                            
                            <div>
                   </div>   </div>
                   
                   
                   
                           
                    </div>
                    
                    
                        
                   
                   
                   
                 
                   
                          <div class="col-5">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title"><spring:message code="comment.comment" /></h4>
                                </div>
                                <div class="card-body">
                                     
                                <div class="row">
                        <div class="col-12">
                            <div class="card">
                      
                                <div class="card-body">
                                 <form:form method="POST" modelAttribute="comment" action="/commentrecord2-log">
                                 
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                   
                                          <input  type="hidden" name="comment_idrefer" value="${id }">
                                          
                                      <c:if test="${comment.comment_id > 0  }">      
                                    <input  type="hidden" name="comment_id" value="${comment.comment_id}">
                                    </c:if>
                                    
                                      <input  type="hidden" name="comment_type" value="log">   
                                        <div class="row">
                                            <div class="col-md-12 col-12">
                                                <div class="mb-1">
                                                    <label class="form-label" for="first-name-column"><spring:message code="comment.comment" /></label>
                                                    <textarea id="first-name-column" class="form-control"  name="comment_comment" >${comment.comment_comment }</textarea>
                                                </div>
                                                  <div class="mb-1">
                                                    <label class="form-label" for="last-name-column"><spring:message code="comment.note" /></label>
                                                    <input type="text" id="last-name-column" class="form-control" name="comment_note" value="${comment.comment_note }">
                                                </div>
                                            </div>
                                          
                                      
                                         
                                   
                                            <div class="col-12">
                                                <button type="submit" class="btn btn-primary me-1 waves-effect waves-float waves-light">Submit</button>
                                          
                                            </div>
                                        </div>
                                    </form:form> 
                                </div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <br>  <br>
              <table class="table table-borderless" style="font-size:12px">
                                    <thead>
                                        <tr>
                                            <th>
                                            	<spring:message code="comment.date" />
                                            </th>
                                            <th><spring:message code="comment.comment" /></th>
                                             <th><spring:message code="comment.note" /></th>
                                  
                                                  <th><spring:message code="comment.add" /></th>
                                                    <th><spring:message code="comment.action" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                     <c:forEach items="${comments }" var="row" varStatus="count">
                                         
                     
                                        <tr>
                                            <td>${row.comment_date }</td>
                                            <td>${row.comment_comment }</td>
                                            <td>${row.comment_note }</td>
                                             <td>${row.comment_name }</td>
                                            <td>  
                                            
                                 <c:if test="${Member_name ==  row.comment_name  }">                
							   <a href="/distribution-log?comment_id=${row.comment_id}&&refer=${id }" class="btn btn-primary waves-effect waves-float waves-light btn-sm">
							         <spring:message code="comment.edit" /></a>
							        </c:if>
							            <c:if test="${Member_name ==  row.comment_name  or report_pn.report_pn_app}">   
							    <a href="/delcomment2-log?comment_id=${row.comment_id}&refer=${id }" class="btn btn-danger waves-effect waves-float waves-light btn-sm" onclick="return confirm('<spring:message code="action.delete" text="คุณต้องการลบ " />');">
							          <spring:message code="comment.del" /></a>
							         </c:if>
							          
							          
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
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>


        <!-- add new card modal  -->
                <div class="modal fade" id="addNewCard" tabindex="-1" aria-labelledby="addNewCardTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body px-sm-5 mx-50 pb-5">
                                <h1 class="text-center mb-1" id="addNewCardTitle"> <spring:message code="dar.uncheck" /></h1>
                              

                                <!-- form -->
                                <form  action="/rejectdocument-filemanager"id="addNewCardValidation" class="row gy-1 gx-2 mt-75" method="POST" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <div class="col-12">
                                        <label class="form-label" for="modalAddCardNumber"><spring:message code="dar.reason" /></label>
                                        <div class="input-group input-group-merge">
                                       
                                            <input id="modalAddCardNumber" name="note" class="form-control add-credit-card-mask" type="text" required/>
                                            <span class="input-group-text cursor-pointer p-25" id="modalAddCard2">
                                                <span class="add-card-type"></span>
                                            </span>
                                        </div>
                                          <input type="hidden" name="id" id="id" value=""> 
                                    </div>

                                   
                                

                                    <div class="col-12 text-center">
                                        <button type="submit" class="btn btn-primary me-1 mt-1">Save</button>
                                        <button type="reset" class="btn btn-outline-secondary mt-1" data-bs-dismiss="modal" aria-label="Close">
                                         Cancel
                                        </button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <!--/ add new card modal  -->

<%@ include file="common/mainfoot.jspf" %>  
 

    <script>
    
    function reject(id){
    	$("#id").val(id);
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
        
        document.getElementById("file").onchange = function() {
    document.getElementById("form").submit();
};



    </script>
    
    <script src="<c:url value="/lib/xlsx.full.min.js" />"></script> 
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
const download = type => {
	  const el = document.getElementById("data-table");
	  const file = XLSX.utils.table_to_book(el);
	  // Configuring width of the columns:
	  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
	  return XLSX.writeFile(file, "alllog." + type);
	};
</script>
</body>


