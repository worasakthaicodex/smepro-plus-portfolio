 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

  <script> 
  

var div = `         
   
	  <div id="project${id}"> 
	  <form id="project_card_${id}" modelAttribute="workflow"  >
    <div class="project-card card" style="border-color:#DC143C" > 
    <div class="top">
    
      <div class="top-name-wrap">
  
      <input type="text" name="workflow_name" value="กรอกหัวข้อที่นี้" style="border:0px;border-bottom: 1px solid black;margin-bottom:10px;width:300px;">
      
      <h3 style="display:none">กรอกหัวข้อที่นี้</h3>
        <div class="p-link">
       
        <span id="key${id}">
         
           </span>       
           <a class="btn btn-sm btn-light" style="padding:2px" data-bs-toggle="modal" data-bs-target="#addNewCard2"  onclick="setkey('${id}');"><i data-feather='tag'></i></a>
          <div>
            <h6>คำอธิบาย:</h6>
            <input type="hidden"  name="workflow_key1" class="workflow_key_1_${id}"  value="" >
            <input type="hidden"  name="workflow_key2" class="workflow_key_2_${id}" value="" >
            <input  type="hidden"  name="workflow_key3" class="workflow_key_3_${id}" value="">
            <input  type="hidden" name="workflow_key4" class="workflow_key_4_${id}" value="" >
            <input type="hidden"  name="ids" value="${id}" >
            <textarea  name="workflow_note" ></textarea>
          <span style="display:none"></span>
          
         <br> 
          </div>


          
           <a class="btn btn-sm btn-info" style="width:100px;margin-right:5px;display:none" >View</a>
                 <a class="btn btn-sm btn-success" style="width:100px;margin-right:5px;"  
                 onclick="postdata('/add-workflow?${_csrf.parameterName}=${_csrf.token}','project_card_${id}','savestatus')">Save</a>
                   <a class="btn btn-sm btn-light"  style="width:100px;margin-right:5px;" onclick="remove('project_card_${id}')">Cancel</a>
                   <a class="btn btn-sm btn-light"  style="width:100px;margin-right:5px;display:none">Cancel</a>
           <a class="btn btn-sm btn-light"  style="width:100px;margin-right:5px;;display:none">Edit</a>
        
        </div>
      </div>
    </div>
    <div>

    </div>
  </div>
  </form>
  </div>

  `;
        
  
      function  addeform(id){
    	  $("#wrap").val(id);
    	  
      }
  
        document.getElementById("maindata").insertAdjacentHTML('afterbegin', div);
        
        
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
       
              
        
 
        
 </script>       
        