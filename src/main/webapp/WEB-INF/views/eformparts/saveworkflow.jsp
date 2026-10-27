 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

  <script> 
 
  $("#project${ids}").html(" ");
  
  var div = `     
	  <form id="project_card_${workflow.workflow_id}" modelAttribute="workflow"  >
	    <div class="project-card card"  > 
	    <div class="top">
	    
	      <div class="top-name-wrap">
	     
	      <input type="text" name="workflow_name" value="${workflow.workflow_name}"
	      style="border:0px;border-bottom: 1px solid black;margin-bottom:10px;width:300px;display:none" id="inputname_${workflow.workflow_id}">
	      
	      <h3 id="divname_${workflow.workflow_id}">${workflow.workflow_name}</h3>
	        <div class="p-link">
	       
	        <span id="key${workflow.workflow_id}">
	        
	        <c:if test="${workflow.workflow_key1 != '' }">    
	        <span class="badge badge-light-primary">${workflow.workflow_key1}</span>
	        </c:if>
	        <c:if test="${workflow.workflow_key2 != '' }">    
	        <span class="badge badge-light-primary">${workflow.workflow_key2}</span>
	        </c:if>
	        <c:if test="${workflow.workflow_key3 != '' }">    
	        <span class="badge badge-light-primary">${workflow.workflow_key3}</span>
	        </c:if>
	        <c:if test="${workflow.workflow_key4 != '' }">    
	        <span class="badge badge-light-primary">${workflow.workflow_key4}</span>
	        </c:if>
	        
	           </span>   
	           
	           <a class="btn btn-sm btn-light" style="padding:2px;display:none;" data-bs-toggle="modal" data-bs-target="#addNewCard2"  id="tag_${workflow.workflow_id}" 
		           onclick="setkey('${workflow.workflow_id}','${workflow.workflow_key1}','${workflow.workflow_key2}','${workflow.workflow_key3}','${workflow.workflow_key4}');">
		           <i data-feather='tag'></i></a>
	           

	          <div>
	            <h6>คำอธิบาย:</h6>
	            <input type="hidden"  name="workflow_id"  value="${workflow.workflow_id}" >
	            <input type="hidden"  name="workflow_key1" class="workflow_key_1_${workflow.workflow_id}"  value="${workflow.workflow_key1}" >
	            <input type="hidden"  name="workflow_key2" class="workflow_key_2_${workflow.workflow_id}" value="${workflow.workflow_key2}" >
	            <input  type="hidden"  name="workflow_key3" class="workflow_key_3_${workflow.workflow_id}" value="${workflow.workflow_key3}">
	            <input  type="hidden" name="workflow_key4" class="workflow_key_4_${workflow.workflow_id}" value="${workflow.workflow_key4}" >
	            <textarea  name="workflow_note" style="display:none" id="inputnote_${workflow.workflow_id}">${workflow.workflow_note}</textarea>
	          <span id="divnote_${workflow.workflow_id}">${workflow.workflow_note}</span>
	          
	         <br> 
	          </div>

	          
	   
	                   <a href="/view-workflow/${workflow.workflow_id}" class="btn btn-sm btn-info" style="width:100px;margin-right:5px;" id="view_${workflow.workflow_id}">View</a>
	   <a class="btn btn-sm btn-success" style="width:100px;margin-right:5px;display:none;" onclick="postdata('/edit-workflow?${_csrf.parameterName}=${_csrf.token}','project_card_${workflow.workflow_id}','savestatus');saves('${workflow.workflow_id}');" id="save_${workflow.workflow_id}" >Save</a>
	                     <a class="btn btn-sm btn-light"  style="width:100px;margin-right:5px;display:none;"
	                      id="cancel_${workflow.workflow_id}" onclick="cancel(${workflow.workflow_id})" >Cancel</a>
	             <a class="btn btn-sm btn-light"  style="width:100px;margin-right:5px;" onclick="edit(${workflow.workflow_id})" id="edit_${workflow.workflow_id}">Edit</a>
	  	  
	        </div>
	      </div>
	    </div>
	    <div>
	      <div class="boost-wrap wrap${workflow.workflow_id}" id="card-drag-area">
	   
	     
	        
	        
	             <a href="#" data-bs-toggle="modal"
	             data-bs-target="#addNewCard"  onclick="addeform(${workflow.workflow_id})"  style="display:none" id="modal_${workflow.workflow_id}">
	             <div class="booster-card" style="text-align: center;  justify-content: center;font-size: 20px;border: 1px dotted;height:100px;padding-top:35px"> 
	    
	+

	        </div></a>
	        
	        
	      </div>
	    </div>
	  </div>
	    </form>
	  `;
	  
	   document.getElementById("project${ids}").insertAdjacentHTML('afterbegin', div);

       if (feather) {
           feather.replace({
               width: 14,
               height: 14
           });
       }
  
       
       $(function () {
    	   'use strict';

    	   // Draggable Cards
    	   dragula([document.getElementById('card-drag-area')]);

    	   // Sortable Lists
    	   dragula([document.getElementById('basic-list-group')]);
    	   dragula([document.getElementById('multiple-list-group-a'), document.getElementById('multiple-list-group-b')]);

    	   // Cloning
    	   dragula([document.getElementById('badge-list-1'), document.getElementById('badge-list-2')], {
    	     copy: true
    	   });

    	   // With Handles

    	   dragula([document.getElementById('handle-list-1'), document.getElementById('handle-list-2')], {
    	     moves: function (el, container, handle) {
    	       return handle.classList.contains('handle');
    	     }
    	   });
    	 });
       
       
 </script>       
        