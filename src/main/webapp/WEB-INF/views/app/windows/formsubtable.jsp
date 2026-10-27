<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div id="body" class="mainbody2">
<form action="#" name="formsubtable" class="column" style="padding:5px"  method="POST" id="formsubtable" >
	<%@ include file="common.jsp" %>     
	<input type="hidden" name="id" id="id"  readonly value="0" >
	<c:if  test ="${app_item_id != null}">
		<input type="hidden" name="app_tempitemid" id="app_tempitemid"  readonly value="${app_item_id}" >
	</c:if>
	<input type="hidden" name="app_tempId" id="tempId"  readonly value="${tempId}" >
	<input type="hidden" name="edata.data_id" id="data_id"  readonly value="${tempId}" >
	<input type="hidden" name="sub_processes_id"  id="sub_processes_id"  value="${sub_processes.id}">                                                           
	<input type="hidden" name="app_processes_id"  id="processes_id"  value="${process.id}">   
	<input type="hidden" name="procurement_type"  id="type"  value="Create">       
</form>
</div>
  
  
<div class="footer">
<c:if  test ="${app_item_id != null}">
<button id="new"  class="ripple" onclick="postData('/additem-filemanager?${_csrf.parameterName}=${_csrf.token}', 'formsubtable','mainbody3');closeed2('draggable-window6')">ตกลง</button>
</c:if>

<c:if  test ="${app_item_id == null}">
<button id="new"  class="ripple" onclick="postData('/additem-filemanager?${_csrf.parameterName}=${_csrf.token}', 'formsubtable','mainbody3')">ตกลง</button>
</c:if>
</div>
        

    