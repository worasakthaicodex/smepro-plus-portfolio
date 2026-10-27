<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<table class="table" style="width:100%;font-size:12px">
<thead>
<tr>
<th>File</th><th>TAG</th><th>ACTION</th>
</tr>
</thead>
 <c:forEach  items="${files}" var="row" varStatus="count">
<tr>
<td style="word-break: break-word; max-width: 200px;">${row.file_name_original} </td>
<td>${row.file_tag}</td>
<td>

							
							
				
						  

<span style="margin-left:5px;cursor: pointer;" class="btn-hover-effect">
        <a onclick="shows2('draggable-window3','Preview image'); 
        getData('/renderbyapp-filemanager?file=${edatas.data_filepath }&&file_edata=${row.file_id }', 'mainbody3')" data-original-title="Img">
            <img src="/app/system/icon/preview.ico">
         </a>
         
      </span>

<c:set var="accessRole" value="APP-USER-${process.id}" />

<sec:authorize access="hasAnyAuthority('${accessRole}') or #row.file_by == #USERNAME">

			<span style="margin-left:5px;cursor: pointer;" class="btn-hover-effect">
        <a onclick="getData('/unfile_appindex-filemanager/${row.file_id }/${item_id}/${process.id }', 'main${item_id}')" data-original-title="Img">
            <img src="/app/system/icon/recycle bin.ico">
         </a>
         
      </span>
	</sec:authorize>							
									
									    	
</td>
                         
						
</tr>
</c:forEach>            
 </table>     
 
 	     