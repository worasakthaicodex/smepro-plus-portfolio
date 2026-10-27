 <%@ include file="../other/alert.jsp" %>

  <c:if test="${save == 'success'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
    
    </script>
</c:if>