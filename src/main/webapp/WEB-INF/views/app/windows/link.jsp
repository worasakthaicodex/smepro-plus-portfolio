<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
 
 
<div style="height:100px; padding:10px; width:100%">
    <div style="width:100%">
        <span style="display:inline-block; min-width:150px">ไฟล์เชื่อมโยง</span>
        <input type="text" class="form-control newinput">
        <button class="fa-button" style="padding:4px"
         onclick=" shows2('draggable-window6','Choose Files');getData('/Chooselinkapp-condition/${app_processes_id}/${process_join}', 'mainbody6')"
        
        >เลือก.......</button> 
    </div> 
    <br>
    <div style="width:100%">
        <span style="display:inline-block; min-width:150px">เลือกข้อมูลที่จะเชื่อม</span>
        <input type="text" class="form-control newinput">
    </div> 
</div>					 
<div class="footer">
<button id="new"  class="ripple" onclick="postData('/addsubdataset2-condition?${_csrf.parameterName}=${_csrf.token}', 'myFormssetting','mainbody3');closeed2('draggable-window6')">ตกลง</button>
</div>