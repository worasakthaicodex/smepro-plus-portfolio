 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
<c:set var="key1" value="${key1 }" />
<c:set var="key2" value="${key2 }" />
<c:set var="key3" value="${key3 }" />
<c:set var="key4" value="${key4 }" />

<c:set var="mainkey1" value="${workflows.workflow_key1 }" />
<c:set var="mainkey2" value="${workflows.workflow_key2 }" />
<c:set var="mainkey3" value="${workflows.workflow_key3 }" />
<c:set var="mainkey4" value="${workflows.workflow_key4 }" />

  <script>

var div = `
${key1}
<c:if test="${mainkey1 != null && mainkey1 != '' }"> 
<span style="width:50px;padding:10px;">KEY 1 :</span><select class="form-control"  id="keyed1">
<option value="" >-</option>
<c:forEach items="${eformfields }" var="row" varStatus="count">     
	<c:set var="selete1" value="" />
	<c:if test="${key1.equals(row.field_label)}"> 
	<c:set var="selete1" value="selected" />
	</c:if>
<option  value="${row.field_label }"  ${selete1}>${row.field_label }</option>
</c:forEach> 
</select><br>
</c:if>
<c:if test="${mainkey2 != null && mainkey2 != '' }"> 
<span style="width:50px;padding:10px;">KEY 2 : </span><select class="form-control"  id="keyed2">



<option value="" >-</option>
<c:forEach items="${eformfields }" var="row" varStatus="count">   
<c:set var="selete2" value="" />
<c:if test="${key2.equals(row.field_label)}"> 
<c:set var="selete2" value="selected" />
</c:if>
<option value="${row.field_label }"  ${selete2}>${row.field_label }</option>
</c:forEach> 
</select><br>
</c:if>


<c:if test="${mainkey3 != null && mainkey3 != '' }"> 
<span style="width:50px;padding:10px;">KEY 3 : </span><select class="form-control"  id="keyed3">


<option value="" >-</option>
<c:forEach items="${eformfields }" var="row" varStatus="count">   
<c:set var="selete3" value="" />
<c:if test="${key3.equals(row.field_label)}"> 
<c:set var="selete3" value="selected" />
</c:if>
<option value="${row.field_label }"  ${selete3}>${row.field_label }</option>
</c:forEach> 
</select><br>
</c:if>

<c:if test="${mainkey4 != null && mainkey4 != '' }"> 
<span style="width:50px;padding:10px;">KEY 4 : </span><select class="form-control"  id="keyed4">


<option value="" >-</option>
<c:forEach items="${eformfields }" var="row" varStatus="count">   
<c:set var="selete4" value="" />
<c:if test="${key4.equals(row.field_label)}"> 
<c:set var="selete4" value="selected" />
</c:if>
<option value="${row.field_label }"  ${selete4}>${row.field_label }</option>
</c:forEach> 
</select><br>


</c:if>
<input type="hidden" name="id"  value="${id}">
<input type="hidden" name="subkey" id="subkey"  value="${subkey}">
`;
$("#setkeyzone").html("");
document.getElementById("setkeyzone").insertAdjacentHTML('afterbegin', div);  













 </script>       
        