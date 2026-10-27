  <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

		   <c:if test="${Keyinterface == 0}">   	
   <div title="KET->${row.keymap.mainkey }">
							<label for="i51">${title } *</label>
						       <div class="input-group">
                                 
           <input type="text" class="form-control" name="edata.${key}"  value="${edatas[key]}"  id="${process.id}-${row.keymap.mainkey}"  readonly required>
                                    
        <span class="input-group-text cursor-pointer">
        <a   href="/addsubdataset-condition/${row.keymap.filter_enum}/${row.keymap.id}/${row.keymap.processes_id}" target="_blank" >  <i data-feather="server"></i></a>
            </span> 
                                    </div>      
                                 
							 </div>
							 </c:if> 
<c:if test="${Keyinterface == 1}">  
 <input type="hidden" class="form-control" name="edata.${key}"  value="${edatas[key]}"  id="${process.id}-${row.keymap.mainkey}"  readonly required>
</c:if> 