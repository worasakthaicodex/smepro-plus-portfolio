<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>











<c:set var="listhidden" value="${upload_pn.upload_pn_avg }" />
<c:set var="listhidden2" value="${upload_pn.upload_pn_hidden }" />
<c:set var="alowtitle" value="${condition.condition_titlerefer }" />


<script>  

const modal = bootstrap.Modal.getInstance(document.getElementById('addNewCards2'));
modal.hide();

unedit();
</script>  


<c:if test="${edatasave  != null  }"> 
 <script>   
 
 

 
 
var td2 =`
    <c:set var="newdata" value='' />

	<c:if test="${status_action  == null  }"> 
  <tr class="rowbody e-${edatasave.data_id}" id="TR-${edatasave.data_id}">
  </c:if>
  
  
  <c:if test="${edatasave.data_titles1 != null &&  !fn:contains(listhidden,edatasave.data_titles1)  && fn:contains(alowtitle,1) }">  
  <td data-custom="${edatasave.data_titles1}" title="${edatasave.data_values1}" data-expression="${edatasave.data_values1 }">${edatasave.data_values1 }</td>
<c:set var="newdata" value='${newdata} ${edatasave.data_values1}' />
   </c:if>
 <c:if test="${edatasave.data_titles2 != null  &&  !fn:contains(listhidden,edatasave.data_titles2)  && fn:contains(alowtitle,2)}">   
  <td data-custom="${edatasave.data_titles2}" title="${edatasave.data_values2}" data-expression="${edatasave.data_values2 }">${edatasave.data_values2 }</td>
    <c:set var="newdata" value='${newdata} ${edatasave.data_values2}' />
   </c:if>
      <c:if test="${edatasave.data_titles3 != null &&  !fn:contains(listhidden,edatasave.data_titles3)  && fn:contains(alowtitle,3) }">  
  <td data-custom="${edatasave.data_titles3}" title="${edatasave.data_values3}" data-expression="${edatasave.data_values3 }">${edatasave.data_values3 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values3}' />
   </c:if>
      <c:if test="${edatasave.data_titles4 != null &&  !fn:contains(listhidden,edatasave.data_titles4)  && fn:contains(alowtitle,4)}">  
  <td data-custom="${edatasave.data_titles4}" title="${edatasave.data_values4}" data-expression="${edatasave.data_values4 }">${edatasave.data_values4 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values4}' />
   </c:if>
      <c:if test="${edatasave.data_titles5 != null &&  !fn:contains(listhidden,edatasave.data_titles5)  && fn:contains(alowtitle,5)}">  
  <td data-custom="${edatasave.data_titles5}" title="${edatasave.data_values5}" data-expression="${edatasave.data_values5 }">${edatasave.data_values5 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values5}' />
   </c:if>
      <c:if test="${edatasave.data_titles6 != null &&  !fn:contains(listhidden,edatasave.data_titles6)  && fn:contains(alowtitle,6)}">  
  <td data-custom="${edatasave.data_titles6}" title="${edatasave.data_values6}" data-expression="${edatasave.data_values6 }">${edatasave.data_values6 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values6}' />
   </c:if>
      <c:if test="${edatasave.data_titles7 != null &&  !fn:contains(listhidden,edatasave.data_titles7)  && fn:contains(alowtitle,7)}">  
  <td data-custom="${edatasave.data_titles7}" title="${edatasave.data_values7}" data-expression="${edatasave.data_values7 }">${edatasave.data_values7 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values7}' />
   </c:if>
      <c:if test="${edatasave.data_titles8 != null &&  !fn:contains(listhidden,edatasave.data_titles8)  && fn:contains(alowtitle,8)}">  
  <td data-custom="${edatasave.data_titles8}" title="${edatasave.data_values8}" data-expression="${edatasave.data_values8 }">${edatasave.data_values8 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values8}' />
   </c:if>
      <c:if test="${edatasave.data_titles9 != null &&  !fn:contains(listhidden,edatasave.data_titles9)  && fn:contains(alowtitle,9)}">  
  <td data-custom="${edatasave.data_titles9}" title="${edatasave.data_values9}" data-expression="${edatasave.data_values9 }">${edatasave.data_values9 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values9}' />
        
        
   </c:if>   <c:if test="${edatasave.data_titles10 != null &&  !fn:contains(listhidden,edatasave.data_titles10)  && fn:contains(alowtitle,10)}">  
  <td data-custom="${edatasave.data_titles10}" data-expression="${edatasave.data_values10 }">${edatasave.data_values10 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values10}' />
   </c:if>
      <c:if test="${edatasave.data_titles11 != null &&  !fn:contains(listhidden,edatasave.data_titles11)  && fn:contains(alowtitle,11)}">  
  <td data-custom="${edatasave.data_titles11}" title="${edatasave.data_values11}" data-expression="${edatasave.data_values11 }">${edatasave.data_values11 }</td>
        <c:set var="newdata" value='${newdata} ${data_values11}' />
   </c:if>
      <c:if test="${edatasave.data_titles12 != null &&  !fn:contains(listhidden,edatasave.data_titles12)  && fn:contains(alowtitle,12)}">  
  <td data-custom="${edatasave.data_titles12}" title="${edatasave.data_values12}" data-expression="${edatasave.data_values12 }">${edatasave.data_values12 }</td>
        <c:set var="newdata" value='${newdata} ${data_valuess12}' />
   </c:if>
      <c:if test="${edatasave.data_titles13 != null &&  !fn:contains(listhidden,edatasave.data_titles13)  && fn:contains(alowtitle,13)}">  
  <td data-custom="${edatasave.data_titles13}" title="${edatasave.data_values13}" data-expression="${edatasave.data_values13 }">${edatasave.data_values13 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values13}' />
   </c:if>
      <c:if test="${edatasave.data_titles14 != null &&  !fn:contains(listhidden,edatasave.data_titles14)  && fn:contains(alowtitle,14)}">  
  <td data-custom="${edatasave.data_titles14}" title="${edatasave.data_values14}" data-expression="${edatasave.data_values14 }">${edatasave.data_values14 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values14}' />
   </c:if>
      <c:if test="${edatasave.data_titles15 != null &&  !fn:contains(listhidden,edatasave.data_titles15)  && fn:contains(alowtitle,15)}">  
  <td data-custom="${edatasave.data_titles15}" title="${edatasave.data_values15}" data-expression="${edatasave.data_values15 }">${edatasave.data_values15 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values15}' />
   </c:if>
      <c:if test="${edatasave.data_titles16 != null &&  !fn:contains(listhidden,edatasave.data_titles16)  && fn:contains(alowtitle,16)}">  
  <td data-custom="${edatasave.data_titles16}" title="${edatasave.data_values16}" data-expression="${edatasave.data_values16 }">${edatasave.data_values16 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values16}' />
   </c:if>
      <c:if test="${edatasave.data_titles17 != null &&  !fn:contains(listhidden,edatasave.data_titles17)  && fn:contains(alowtitle,17)}">  
  <td data-custom="${edatasave.data_titles17}" title="${edatasave.data_values17}" data-expression="${edatasave.data_values17 }">${edatasave.data_values17 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values17}' />
   </c:if>
      <c:if test="${edatasave.data_titles18 != null &&  !fn:contains(listhidden,edatasave.data_titles18)  && fn:contains(alowtitle,18)}">  
  <td data-custom="${edatasave.data_titles18}" title="${edatasave.data_values18}" data-expression="${edatasave.data_values18 }">${edatasave.data_values18 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values18}' />
   </c:if>
      <c:if test="${edatasave.data_titles19 != null &&  !fn:contains(listhidden,edatasave.data_titles19)  && fn:contains(alowtitle,19)}">  
  <td data-custom="${edatasave.data_titles19}" title="${edatasave.data_values19}" data-expression="${edatasave.data_values19 }">${edatasave.data_values19 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values19}' />
   </c:if>
      <c:if test="${edatasave.data_titles20 != null &&  !fn:contains(listhidden,edatasave.data_titles20)  && fn:contains(alowtitle,20)}">  
  <td data-custom="${edatasave.data_titles20}" title="${edatasave.data_values20}" data-expression="${edatasave.data_values20 }">${edatasave.data_values20 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_values20}' />
   </c:if>
 <c:if test="${edatasave.data_nameadds != null }">  
 
 
 <c:if test="${!fn:contains(listhidden,'userinsert') }">   <td >${edatasave.data_nameadds }</td> </c:if>
 <c:if test="${!fn:contains(listhidden,'userrefer') }"> <td >${edatasave.data_namerefers }</td></c:if>
 <c:if test="${!fn:contains(listhidden,'date') }"> <td >${edatasave.data_dues }</td> </c:if>
 <c:if test="${!fn:contains(listhidden,'attached') }"> <td class="action">   
   <c:set var="namesArray" value="${fn:split(edatasave.data_filetypes, ',')}" />
<c:forEach var="nameItem" items="${namesArray}" begin="0" end="5" varStatus="count">
<a href="/render-filemanager?file=${edatasave.data_filepaths }&&count=${count.count}" target="_blank" 
onclick="openCenteredWindow(this.href, 'File Window', 900, 600); changeRowColor(this); return false;">
   ${nameItem}
</a>
<br>
</c:forEach>            
     </td>
     </c:if>
     
     
   
</c:if>



   <c:set var="size" value="${eformfields.size()  }" />
  <c:if test="${size > 0 &&  !fn:contains(listhidden2,edatasave.data_title1)}">  
  <td data-custom="${edatasave.data_title1}" title="${edatasave.data_value1}" class="inputDiv inputDiv1"   data-expression="${edatasave.data_value1 }">${edatasave.data_value1 }</td>
<c:set var="newdata" value='${newdata} ${edatasave.data_value1}' />
   </c:if>
   <c:if test="${size > 1 &&  !fn:contains(listhidden2,edatasave.data_title2)}">  
  <td data-custom="${edatasave.data_title2 }" title="${edatasave.data_value2}" class="inputDiv inputDiv2"   data-expression="${edatasave.data_value2 }">${edatasave.data_value2 }</td>
    <c:set var="newdata" value='${newdata} ${edatasave.data_value2}' />
   </c:if>
   <c:if test="${size > 2 &&  !fn:contains(listhidden2,edatasave.data_title3)}">    
  <td data-custom="${edatasave.data_title3}" title="${edatasave.data_value3}" class="inputDiv inputDiv3"   data-expression="${edatasave.data_value3 }">${edatasave.data_value3 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value3}' />
   </c:if>
   <c:if test="${size > 3 &&  !fn:contains(listhidden2,edatasave.data_title4)}">  
  <td data-custom="${edatasave.data_title4}" title="${edatasave.data_value4}" class="inputDiv inputDiv4"   data-expression="${edatasave.data_value4 }">${edatasave.data_value4 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value4}' />
   </c:if>
   <c:if test="${size > 4 &&  !fn:contains(listhidden2,edatasave.data_title5)}">  
  <td data-custom="${edatasave.data_title5}" title="${edatasave.data_value5}" class="inputDiv inputDiv5"   data-expression="${edatasave.data_value5 }">${edatasave.data_value5 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value5}' />
   </c:if>
   <c:if test="${size > 5 &&  !fn:contains(listhidden2,edatasave.data_title6)}">  
  <td data-custom="${edatasave.data_title6}" title="${edatasave.data_value6}" class="inputDiv inputDiv6"   data-expression="${edatasave.data_value6 }">${edatasave.data_value6 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value6}' />
   </c:if>
   <c:if test="${size > 6 &&  !fn:contains(listhidden2,edatasave.data_title7)}">  
  <td data-custom="${edatasave.data_title7}" title="${edatasave.data_value7}" class="inputDiv inputDiv7"   data-expression="${edatasave.data_value7 }">${edatasave.data_value7 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value7}' />
   </c:if>
   <c:if test="${size > 7 &&  !fn:contains(listhidden2,edatasave.data_title8)}">  
  <td data-custom="${edatasave.data_title8}" title="${edatasave.data_value8}" class="inputDiv inputDiv8"   data-expression="${edatasave.data_value8 }">${edatasave.data_value8 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value8}' />
   </c:if>
   <c:if test="${size > 8 &&  !fn:contains(listhidden2,edatasave.data_title9)}">    
  <td data-custom="${edatasave.data_title9}" title="${edatasave.data_value9}" class="inputDiv inputDiv9"   data-expression="${edatasave.data_value9 }">${edatasave.data_value9 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value9}' />
        
        
   </c:if> 
   <c:if test="${size > 9 &&  !fn:contains(listhidden2,edatasave.data_title10)}">  
  <td data-custom="${edatasave.data_title10}" title="${edatasave.data_value10}" class="inputDiv inputDiv10"   data-expression="${edatasave.data_value10 }">${edatasave.data_value10 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value10}' />
   </c:if>
   <c:if test="${size > 10 &&  !fn:contains(listhidden2,edatasave.data_title11)}">  
  <td data-custom="${edatasave.data_title11}" title="${edatasave.data_value11}" class="inputDiv inputDiv11"   data-expression="${edatasave.data_value11 }">${edatasave.data_value11 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value11}' />
   </c:if>
   <c:if test="${size > 11 &&  !fn:contains(listhidden2,edatasave.data_title12)}">  
  <td data-custom="${edatasave.data_title12}" title="${edatasave.data_value12}" class="inputDiv inputDiv12"   data-expression="${edatasave.data_value12 }">${edatasave.data_value12 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value12}' />
   </c:if>
   <c:if test="${size > 12 &&  !fn:contains(listhidden2,edatasave.data_title13)}">  
  <td data-custom="${edatasave.data_title13}" title="${edatasave.data_value13}" class="inputDiv inputDiv13"   data-expression="${edatasave.data_value13 }">${edatasave.data_value13 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value13}' />
   </c:if>
   <c:if test="${size > 13 &&  !fn:contains(listhidden2,edatasave.data_title14)}">  
  <td data-custom="${edatasave.data_title14}" title="${edatasave.data_value14}" class="inputDiv inputDiv14"   data-expression="${edatasave.data_value14 }">${edatasave.data_value14 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value14}' />
   </c:if>
   <c:if test="${size > 14 &&  !fn:contains(listhidden2,edatasave.data_title15)}">   
  <td data-custom="${edatasave.data_title15}" title="${edatasave.data_value15}" class="inputDiv inputDiv15"   data-expression="${edatasave.data_value15 }">${edatasave.data_value15 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value15}' />
   </c:if>
   <c:if test="${size > 15 &&  !fn:contains(listhidden2,edatasave.data_title16)}">  
  <td data-custom="${edatasave.data_title16}" title="${edatasave.data_value16}" class="inputDiv inputDiv16"   data-expression="${edatasave.data_value16 }">${edatasave.data_value16 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value16}' />
   </c:if>
   <c:if test="${size > 16 &&  !fn:contains(listhidden2,edatasave.data_title17)}">   
  <td data-custom="${edatasave.data_title17}" title="${edatasave.data_value17}" class="inputDiv inputDiv17"   data-expression="${edatasave.data_value17 }">${edatasave.data_value17 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value17}' />
   </c:if>
   <c:if test="${size > 17 &&  !fn:contains(listhidden2,edatasave.data_title18)}">  
  <td data-custom="${edatasave.data_title18}" title="${edatasave.data_value18}" class="inputDiv inputDiv18"   data-expression="${edatasave.data_value18 }">${edatasave.data_value18 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value18}' />
   </c:if>
   <c:if test="${size > 18 &&  !fn:contains(listhidden2,edatasave.data_title19)}">  
  <td data-custom="${edatasave.data_title19}" title="${edatasave.data_value19}" class="inputDiv inputDiv19"   data-expression="${edatasave.data_value19 }">${edatasave.data_value19 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value19}' />
   </c:if>
   <c:if test="${size > 19 &&  !fn:contains(listhidden2,edatasave.data_title20)}">  
  <td data-custom="${edatasave.data_title20}" title="${edatasave.data_value20}" class="inputDiv inputDiv20"   data-expression="${edatasave.data_value20 }">${edatasave.data_value20 }</td>
        <c:set var="newdata" value='${newdata} ${edatasave.data_value20}' />
   </c:if>
       
   
   
   
   
   <c:if test="${ !fn:contains(listhidden2,'date')}">    
<td class="inputDiv inputDiv21"   data-expression="end">${edatasave.data_due }</td>
</c:if>

<c:if test="${ !fn:contains(listhidden2,'attached')}">    
              <td class="action">   
                <c:set var="namesArray" value="${fn:split(edatasave.data_filetype, ',')}" />
                
                	   <c:forEach items="${edatasave.file}" var="file"   varStatus="count">
                
                
				<a href="/render-filemanager?file=${rows.log_id+0 }&&count=${count.count}&&file_edata=${file.file_id }" target="_blank" 
					     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); changeRowColor(this); return false;">
					       ${file.file_name_original }
				</a>
				<br>
                   </c:forEach>                   
                  </td>
                  </c:if>        
           
          
                  <c:if test="${ !fn:contains(listhidden2,'userinsert')}">    
 
       <td>${edatasave.data_nameadd } 
       </td>
       </c:if>   
       
      
   
	 

 
       <c:if test="${ !fn:contains(listhidden2,'userrefer')}">   <td >${edatasave.data_namerefer }</td>    </c:if> 
       <c:if test="${ !fn:contains(listhidden2,'usercheck')}">  <td >${edatasave.data_nameapp }</td>    </c:if> 
       <c:if test="${ !fn:contains(listhidden2,'datecheck')}">   <td >${edatasave.data_datecheck }</td>    </c:if> 
      
<c:if test="${ !fn:contains(listhidden2,'notecheck')}">            
 <td data-custom="บันทึกผู้ตรวจ">      
	${edatasave.data_comment }
</td>
</c:if> 

<c:if test="${ !fn:contains(listhidden2,'status')}">
<c:set var="color" value="" />
	<c:if test="${edatasave.data_flow ==  1 }">   
				<spring:message code="status.waitingtocheck" text="รอตรวจ"  var="status"/>
			<c:set var="color" value="black" />
	 </c:if>
   <c:if test="${edatasave.data_flow ==  2 }">   
			<spring:message code="status.success" text="อนุมัติเเล้ว"  var="status"/>
				<c:set var="color" value="green" />
	 </c:if>
	    <c:if test="${edatasave.data_flow ==  -1 }">   
			<spring:message code="status.reject" text="ไม่อนุมัติ"  var="status"/>
				<c:set var="color" value="red" />
	 </c:if>
	 
	 <c:if test="${edatasave.data_flow ==  -2 }">   
	 <spring:message code="task.taskspendingclearance" text="งานรอเคลียร์" var="status" />

	<c:set var="color" value="black" />
	 </c:if>
      <td style="color:${color}">   
  

				${status }

        
        </td>
        </c:if> 
        
        
        
       <th>   
   <c:if test="${edatasave.data_flow <  2 }">  
                 <div class="btn-group">
 <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
 <spring:message code="btn.action" />
 </button>
 <div class="dropdown-menu" style="">
<c:if test="${edatasave.log_flow ==  0 }">  
  <c:if test="${Member_name ==  upload_pn.upload_pn_app }">      
  
 </c:if>
    </c:if>
 
       <c:if test="${edatasave.data_flow ==  0 or  edatasave.data_flow  == -1 or  edatasave.data_flow  == 1  }">  
          <c:if test="${Member_name ==  edatasave.data_nameadd }"> 
 <a class="dropdown-item" href="javascript:void(0)"  
 onclick="changeRowColor(this);return getdata('/editdocument-filemanager/${edatasave.data_id}/${upload_pn.upload_pn_id}','${edatasave.log_id}')" class="btn btn-sm btn-light" data-bs-toggle="modal" data-bs-target="#addNewCards2" >
 <spring:message code="btn.edit" text="แก้ไข" /></a>
 		 </c:if> 
     	</c:if>
        <c:if test="${edatasave.data_flow  ==-2 }">  
        <c:if test="${Member_name ==  upload_pn.upload_pn_app }">     
<a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
onclick="changeRowColor(this);reject(${edatasave.data_id})" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard">
<spring:message code="btn.clear" text="เคลียร์" /></a>
 		</c:if>
 
 <div class="dropdown-divider"></div>
     <c:if test="${edatasave.data_flow == 1 }">  
              <c:if test="${Member_name ==  upload_pn.upload_pn_app }">     
  <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
  onclick="changeRowColor(this);reject(${edatasave.data_id})" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard"><spring:message code="btn.unapp" text="ไม่อนุมัติ" /></a>
       		</c:if>
     </c:if>
     
       
   	  
</c:if>
     		 <c:if test="${edatasave.data_flow ==  0 or  edatasave.data_flow  == -1  or  edatasave.data_flow  == 1 }">  
          <c:if test="${Member_name ==  edatasave.data_nameadd }"> 
 <a class="dropdown-item" href="javascript:void(0)"  
 onclick="changeRowColor(this);return getdata('/undocument-filemanager/${edatasave.data_id}','${edatasave.log_id}');" ><spring:message code="btn.del2" text="นำออก" /></a>
   		 </c:if> 
     		</c:if>
     		
 </div>
</div>
          	</c:if>    
       </th>
       
   	<c:if test="${status_action  == null  }"> 
    </tr>
    </c:if>      

  
  
`;
<c:if test="${status_action  == null  }"> 
document.getElementById("tabledataes").insertAdjacentHTML('afterbegin', td2);
</c:if>  

<c:if test="${status_action  != null  }"> 
document.getElementById("TR-${edatasave.data_id}").innerHTML = td2;
</c:if>  
</script>      
</c:if>

<script>
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>

<c:if test="${log_id > 0 }"> 
  <script>      

$(".u-${log_id}").remove();
</script>  
</c:if>

<c:if test="${edata_id  != null  }"> 
  <script>      

  $(".e-${edata_id}").remove();
</script>  




</c:if>

<%@ include file="../other/alert.jsp" %>
  <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
	 var button = document.getElementById("submits");
     button.disabled = false;
    </script>
