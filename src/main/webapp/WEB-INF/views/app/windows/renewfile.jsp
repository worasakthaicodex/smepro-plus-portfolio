<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
 
<div style= "height:<c:if test="${join > 0}">150px;</c:if><c:if test="${join < 1}">80px;</c:if> padding:10px; width:100%">

<form id="newfile" name="newfile" style="padding:20px">
   <div style="width:100%;">
        <span style="display:inline-block; min-width:150px">ชื่อไฟล์</span>
        <input type="text" id="doc_title"class="form-control newinput" name="name_file" value="${edata.data_title}" id="doc_title" value="" style="    width: 450px;">
        
        
        
        
        <input type="hidden" name="processes_id" value="${edata.processes_id}">
<input type="hidden" name="id" value="${edata.data_id}">       

    </div>     <br>
    
    

 
        
        
 </form>   
    
</div>					 
<div class="footer">
<button id="new"  class="ripple" onclick="postData('/renewfilesave-filemanager?${_csrf.parameterName}=${_csrf.token}', 'newfile','microsofts${edata.processes_id}');closeed2('draggable-window3')">ตกลง</button>
</div>


