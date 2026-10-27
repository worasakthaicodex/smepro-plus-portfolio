<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en"  >

    <div id="body" class="mainbody2" style="max-height:150px"  >
     
        <style>
      
        .datatable thead th {
      
          position: sticky;
          top: 0;
          font-size: 13px;


    white-space: nowrap;
    font-family: 'Lato', sans-serif;

 
    height: 30px;
    background-repeat: no-repeat;
    color: white;
    text-shadow: #012b4d 2px 2px 2px;
    text-align: center;
    background-image: url("https://www.jqueryscript.net/demo/jQuery-Plugin-For-Draggable-Resizable-Table-Columns-colResizable/img/th.png");

}
table {
  border-collapse: collapse;
    text-indent: initial;
    border-spacing: 2px;
    width: 100%;
}


td{
  text-indent: 5px;
    color: #444;
    border-bottom: 1px solid #bbb;
    border-left: 1px solid #bbb;
    font-size: 13px;
}     
.sorted{
  font-size: 15px;

}      
 
      </style>

<div class="container">
      <div class="bar" id="barmainbody3"></div>
  <div class="menus toolbar" style="min-height: 35px;">
<div class="btn-group" >

  <button class="fa-button dark" title="บันทึก" 
  onclick="postData('/savesubtable-filemanager?${_csrf.parameterName}=${_csrf.token}', 'myFormssubtable','mainbody3');
  closeed('draggable-window6');"
   data-original-title="Img">
    <i class="material-icons text-success">save</i>
  </button>
  
<button class="fa-button dark"  id="bt5" title="add" data-original-title="Img" 
onclick=" shows2('draggable-window6',' [เพิ่มข้อมูล]');
getData('/addsubtable-filemanager/${edatas.data_id+0}/${process.id}', 'mainbody6')">
                <i class="material-icons text-light">
            
                  post_add
                </i>
               </button>
               
               
  
  <span style="float:right;padding: 0;margin-top:-4px" >
      <form id="myFormssubtable" name="myFormssubtable">
                                 
 
                                  	<input type="hidden" name="app_tempId"   value="${tempId}" >
                                  	<input type="hidden" name="edata.data_id"   value="${tempId}" >
                                  	<input type="hidden" name="app_processes_id" value="${process.id}">
         </form>                               
                                        
      </span>
  

    </div>

</div>
</div>


        <table class="datatable">
          <thead>
            <tr>

  <th class="form-label fixhead" style="padding:5px;font-size:13px">#</th>
<c:forEach items="${eformfields }"   var="row" varStatus="count">
    

 			 <th class="form-label fixhead" style="padding:5px;font-size:13px">${row.field_label }</th>

</c:forEach>

  <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="tables.action" text="จัดการ" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                         
                            
                                    
                                        <c:set var="Clear_status" value="0.00" />
                                             <c:set var="countmain" value="0" />
                                        <c:forEach items="${item }" var="row"  varStatus="count">
				                                          <c:set var="countmain" value="${countmain + 1}" />
				                                     
				                                      <tr id="item-${row.id }" class="RF-${row.doc } CHECK-${row.check }">
				                                       <td style="padding:5px;font-size:13px" title="${row.id } <-> ${row.id_refer } ">${count.count }</td >
			
				      										<c:forEach items="${eformfields }"   var="rows">
							
															  
														 			       		 <td style="padding:5px;font-size:13px">${row[rows.keymap.mainkey]}</td >
														
															</c:forEach>
											                                        
				                                       		 <td style="padding:5px;font-size:13px">
				                    <form id="myFormssubtable${row.id }" name="myFormssubtable${row.id }">
                                 
                                 
                                 	<input type="hidden" name="app_item_id"   value="${count.count-1 }" >
                                  	<input type="hidden" name="app_tempId"   value="${tempId}" >
                                  	<input type="hidden" name="edata.data_id"   value="${tempId}" >
                                  	<input type="hidden" name="app_processes_id" value="${process.id}">
                                        
                                        
                                        
                                        
                                        	<button type="button"  class="fa-button dark"   title="กลับ"  
                                        	  onclick=" shows2('draggable-window6','[แก้ไขข้อมูล]');
											getData('/edititem-filemanager/${count.count-1 }/${tempId}/${process.id}', 'mainbody6')"
										style="color:black;font-size: 9px;" data-original-title="Bold - Ctrl+B">
									      <span class="material-icons text-light">
									       
									         	 border_color
									        </span>
									    </button>   
                                        
                                        
                                        <button  type="button" class="fa-button dark"  style="color:black" data-original-title="Img" title="ลบ"
											onclick="confirmAndDelete('/delitem-filemanager?${_csrf.parameterName}=${_csrf.token}', 
											'myFormssubtable${row.id }', 'mainbody3')">
									    	    <i class="material-icons text-danger">delete</i>
										</button>
									</form>	       
										        
				                                       		 
				                                       		 </td >
				                                      
  														
				                                          
				                                        </tr>
                                        </c:forEach>

         
        </tbody>
        </table>
  

      

      </div>
      <div class="footer">
    
      </div>
    
        
    