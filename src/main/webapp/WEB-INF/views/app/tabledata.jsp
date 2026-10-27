<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>






<c:if test="${type == 'Create'  }">



<c:set var="action_batch" value="${action_batch_insert},${action_batch_continue},${action_batch_modify}" />	
 <c:set var="steptype" value="create,reference_continue,reference_modify" />   
 
<c:set var="typeshow" value="5" />
<%@ include file="../app/general_system.jspf" %>   
</c:if>
<c:if test="${type == 'Clear'  }">
<c:set var="typeshow" value="11" />
<%@ include file="../app/general_system.jspf" %>   
</c:if>

	   <script>

            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
 
    </script>