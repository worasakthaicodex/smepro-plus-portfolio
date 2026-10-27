<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


  <div id="body" class="mainbody2"  >
     

<form action="#" name="myFormssetting"  method="POST" id="myFormssetting" >

<c:if test="${dataset_datas.dataset_data_id > 0}">    
<input  type="hidden" name="dataset_data_id" value="${dataset_datas.dataset_data_id }">
</c:if>  
<input  type="hidden" name="dataset_data_id_refer" value="${datasets.dataset_id }">
                               

<fieldset style="margin: 10px;height:450px">

  <label for="first-input" >${datasets.dataset_1 } ***</label>
   <input type="text" name="dataset_data_title"  value="${dataset_datas.dataset_data_title }" required>
<br>

<c:if test="${not empty  datasets.dataset_2}">  
<br>              
   <label for="first-input" >${datasets.dataset_2 }</label>
   <input type="text" name="dataset_data_subtitle1"  value="${dataset_datas.dataset_data_subtitle1 }" required>                                 
     <br>
</c:if> 
<c:if test="${not empty  datasets.dataset_3}">  
<br>              
   <label for="first-input" >${datasets.dataset_3 }</label>
   <input type="text" name="dataset_data_subtitle2"  value="${dataset_datas.dataset_data_subtitle2 }" required>                                 
     <br>
</c:if> 
<c:if test="${not empty  datasets.dataset_4}">  
<br>              
   <label for="first-input" >${datasets.dataset_4 }</label>
   <input type="text" name="dataset_data_subtitle3"  value="${dataset_datas.dataset_data_subtitle3 }" required>                                 
     <br>
</c:if> 
<c:if test="${not empty  datasets.dataset_5}">  
<br>              
   <label for="first-input" >${datasets.dataset_5 }</label>
   <input type="text" name="dataset_data_subtitle4"  value="${dataset_datas.dataset_data_subtitle4 }" required>                                 
     <br>
</c:if> 




</fieldset>
</form>
  </div>
<div class="footer">
<c:if test="${dataset_datas == null}">    
<button id="new"  class="ripple" onclick="postData('/addsubdataset2-condition?${_csrf.parameterName}=${_csrf.token}', 'myFormssetting','mainbody3')">ตกลง</button>
</c:if>
<c:if test="${dataset_datas.dataset_data_id > 0}">    
<button id="new"  class="ripple" onclick="postData('/addsubdataset2-condition?${_csrf.parameterName}=${_csrf.token}', 'myFormssetting','mainbody3');closeed2('draggable-window6')">ตกลง</button>
</c:if>
</div>
        

    