<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>     


<c:if test="${ row.keymap.mainkey == 'product_names' }">


<c:if test="${subtable_readonly == 'readonly'}">

  <input type="text" class="tif-inp-text js-total" 
           id="${row.keymap.mainkey}" 
           name="batchEdata.${key}" 
           value="${subData[key]}" 
           readonly 
           style="background-color: #f0f0f0;"
           autocomplete="off">
 
</c:if>
<c:if test="${subtable_readonly != 'readonly'}">

<select class="${tiftext} js-product-select" 
            id="browser-${key}" 
            name="batchEdata.${key}" 
            placeholder="พิมพ์เพื่อค้นหาบริการ..."
            ${subtable_readonly}>
            
            
            
            
        <c:if test="${subData[key] != null && subData[key] != ''}">
            <option value="${subData[key]}" selected>${subData[key]}</option>
        </c:if>
        
    </select>



 </c:if>
</c:if>
