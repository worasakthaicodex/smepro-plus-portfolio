
<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
 

<link rel='stylesheet' href='https://code.ionicframework.com/ionicons/1.5.2/css/ionicons.min.css'>
 <link href="<c:url value="/resources/treeview/style.css" />" rel="stylesheet">
 
 <form method="POST" id="Createapp" modelAttribute="process" action="/savedatasource-workflow">
                          
                          
                          
   <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="homeIcon-tab" data-bs-toggle="tab" href="#homeIcon" aria-controls="home" role="tab" aria-selected="true">
                                       <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-search"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
                                            
                                             <spring:message code="app.Supporting" text="-" /></a>
                                       
                                        </li>
                                    </ul>
     
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="homeIcon" aria-labelledby="homeIcon-tab" role="tabpanel">
                                               <div class="modal-body">
                                               <%@ include file="Subportdata.jspf" %>  
                                                </div>
                                        
                                        </div>
                                   
                                       </div>                                     
                                       
                                         </div>
                                            </div>
<script>

$(document).ready(function () {
    $(".select2s").select2({
        tags: true
    }).on("change", function () {
        let selectedValues = $(this).val();
        let customId = $(this).data("custom");

        $('input[name="filter' + customId + '"]').val(selectedValues ? selectedValues.join("|") : "");
    });
});
</script>                             
	      <script src="<c:url value="/resources/app-assets/vendors/js/forms/select/select2.full.min.js" />"></script>
  <script src="<c:url value="/resources/app-assets/js/scripts/forms/form-select2.js" />"></script>      
  
  
         <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">         
                                                     <input type="hidden"  name="sub_processes_id" id="sub_processes_id" value="${sub_processes.id }"  class="form-control">            
                                                     <input type="hidden"  name="processe_id" id="processe_id"value="${processes.id }"  class="form-control">
                                                     <input type="hidden" class="form-select"  name="workflow_id" value="${workflows.workflow_id }"> 
                                                     
                                                    	
                                                            <div class="modal-footer" >
                                                              <a  href="/docworkflowapp-workflow"  target="_blank" class="btn btn-light waves-effect waves-float waves-light" >Doc</a>
                                                                <input type="Submit"  value="Save"  class="btn btn-primary waves-effect waves-float waves-light">
                                                            </div>
                                                </form>
                                                                                               