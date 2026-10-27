<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div id="deletecm"></div>

  <c:forEach items="${listcms }" var="row" varStatus="count">
<div class="d-flex align-items-center mb-2" id="cm-${row.cm_id }">
                                                    <div class="avatar avatar-sm me-50" >
                                                        <img src="/resources/app-assets/images/portrait/small/avatar.jpg" alt="avatar" width="28">
                                                    </div>
                                                    <div class="more-info" style="width:90%">
                                                        <p class="mb-0">
                                                            <span class="fw-bold">${row.cm_user_name }</span> <span class="fw-bold"> ${cm_date } </span>
                                                            <br>
                                                            ${row.cm_title }
                                                       
                                                        </p>
                                                    </div>
                                                       <button type="button"   onclick="return deletecm('/delcm-filemanager/${row.cm_id}/${row.type}','deletecm','1');"   class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    
                                                    <spring:message code="btn.delete" text="ลบ" />
           </button>
</div>


</c:forEach>

  <script>

  
  function deletecm(url,div,type){
  
  	var status = 0;
  	
  	
  	if(type == 1){
  		if (confirm('<spring:message code="policy.deleteconfirm"  />'))
			{
  			status=1;
			}
  	}else{
  		status=1;
  	}

			
			if(status == 1){
				  $.ajax({
		    	        type:'GET',
		    	        url:url,
		    	        data: {ID : 0},
		    	        
		    	     success: function(data){
		    	    $("#"+div).html(data);	
	
		    	     }
		    	});
				
			}
			    	  
		
		
		
		return false;
		
}

$("#comment").html("["+${total}+"]");
</script> 