<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
  
  
  
  
  
   <script>
	
	  $("#step").html(" ");
	  
	  var div = `<c:set var="num" value="0" />
	      <c:forEach items="${eflows }" var="rows" varStatus="count"> 
	      
	                                        <c:if test="${count.count == 1 }">  
	                   
	                                           <c:set var="edatas" value="${edatas1}" />
	                                       
	                                      </c:if>     
	                                        <c:if test="${count.count == 2 }">  
	                                    
	                                          <c:set var="edatas" value="${edatas2}" />
	                                          
	                                      </c:if>       
	                          		  <c:if test="${count.count == 3 }">  
	                                   
	                                          <c:set var="edatas" value="${edatas3}" />
	                                 
	                                      </c:if>   
	                            	  <c:if test="${count.count == 4 }">  
	                                     
	                                          <c:set var="edatas" value="${edatas4}" />
	                                         
	                                      </c:if>  
	                  
	                                  <c:if test="${count.count == 5 }">  
	                                   
	                                          <c:set var="edatas" value="${edatas5}" />
	                                
	                                      </c:if>  
	                                    <c:if test="${count.count == 6 }">  
	                                     
	                                          <c:set var="edatas" value="${edatas6}" />
	                            
	                                      </c:if>  
	                                       <c:if test="${count.count == 7 }">  
	                                
	                                          <c:set var="edatas" value="${edatas7}" />
	                               
	                                      </c:if>  
	                                          <c:if test="${count.count == 8 }">  
	                                     
	                                          <c:set var="edatas" value="${edatas8}" />
	                       
	                                      </c:if>  
	                                      
	                                            <c:if test="${count.count == 9 }">  
	           >
	                                          <c:set var="edatas" value="${edatas9}" />
	                                      </c:if>
	                                              <c:if test="${count.count == 10 }">  
	                                          <c:set var="edatas" value="${edatas10}" />
	                                      </c:if>
	                                      
	      
	      
			 <c:set var="num" value="${num+1 }" />
			    <div class="md-step <c:if test="${edatas.size() > 0 }">active </c:if>">
			      <div class="md-step-circle"><span>${num}</span></div>
			      <div class="md-step-title"> ${rows.eflow_name}</div>
			      <div class="md-step-bar-left"></div>
			      <div class="md-step-bar-right"></div>
			    </div>
	    </c:forEach>
	    `;
	    
	    document.getElementById("step").insertAdjacentHTML('afterbegin', div);
	  
</script>      
  
  
  
  
  
  
    <c:forEach items="${eflows }" var="rows" varStatus="count"> 
                        <div class="card-header">
                                <h4 class="card-title">${rows.eflow_name} </h4>
                            </div>
                            <div class="card-body"> 
                            
                                                      
                                             <table id="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                                        
                                        
                                      <c:if test="${count.count == 1 }">  
                                        <c:set var="eformfields" value="${eformfields1}" />
                                           <c:set var="edatas" value="${edatas1}" />
                                             <c:set var="eflows" value="${eflows1}" />
                                      </c:if>     
                                        <c:if test="${count.count == 2 }">  
                                        <c:set var="eformfields" value="${eformfields2}" />
                                          <c:set var="edatas" value="${edatas2}" />
                                            <c:set var="eflows" value="${eflows2}" />
                                      </c:if>       
                          
                  
                              <c:forEach items="${eformfields }" var="row" varStatus="count">     
                              
                            
                                              <c:set var="color" value="" />
                                           <c:if test="${row.field_label == eflows.eflow_key1   }"> 
                                   			<c:set var="color" value="#F08080" />
                                     		 </c:if>
                                          <c:if test="${row.field_label == eflows.eflow_key2   }"> 
                                   			<c:set var="color" value="#87CEFA" />
                                     		 </c:if>
                                     		 <c:if test="${row.field_label == eflows.eflow_key3   }"> 
                                   			<c:set var="color" value="#FFFACD" />
                                     		 </c:if> 
                                     		  <c:if test="${row.field_label == eflows.eflow_key4   }"> 
                                   			<c:set var="color" value="#DDA0DD" />
                                     		 </c:if>
                                     		 
                                        <th class="form-label fixhead" style="background-color:${color}">${row.field_label }</th>
                               </c:forEach>  
                            <th class="form-label fixhead">วันที่</th>
                                     <th class="form-label fixhead">เอกสารเเนบ</th>
                               
                                     <th class="form-label fixhead">ผู้เพิ่ม</th>
                                 
                                    </thead>
                               
                               
                               
                                 <tbody>  
                                 
                                   <c:set var="newdata" value='' />
                                 <c:forEach items="${edatas }" var="rows" varStatus="count">  
                                 <tr class="rowbody e-${rows.data_id}">
                                 
                                      <c:if test="${rows.data_title1 != null }">  
                                     <td data-custom="${rows.data_title1}">${rows.data_value1 }</td>
                                  <c:set var="newdata" value='${newdata} ${rows.data_value1}' />
                                      </c:if>
                                    <c:if test="${rows.data_title2 != null }">   
                                     <td data-custom="${rows.data_title2}">${rows.data_value2 }</td>
                                       <c:set var="newdata" value='${newdata} ${rows.data_value2}' />
                                      </c:if>
                                         <c:if test="${rows.data_title3 != null }">  
                                     <td data-custom="${rows.data_title3}">${rows.data_value3 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value3}' />
                                      </c:if>
                                         <c:if test="${rows.data_title4 != null }">  
                                     <td data-custom="${rows.data_title4}">${rows.data_value4 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value4}' />
                                      </c:if>
                                         <c:if test="${rows.data_title5 != null }">  
                                     <td data-custom="${rows.data_title5}">${rows.data_value5 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value5}' />
                                      </c:if>
                                         <c:if test="${rows.data_title6 != null }">  
                                     <td data-custom="${rows.data_title6}">${rows.data_value6 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value6}' />
                                      </c:if>
                                         <c:if test="${rows.data_title7 != null }">  
                                     <td data-custom="${rows.data_title7}">${rows.data_value7 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value7}' />
                                      </c:if>
                                         <c:if test="${rows.data_title8 != null }">  
                                     <td data-custom="${rows.data_title8}">${rows.data_value8 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value8}' />
                                      </c:if>
                                         <c:if test="${rows.data_title9 != null }">  
                                     <td data-custom="${rows.data_title9}">${rows.data_value9 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value9}' />
                                      </c:if>   <c:if test="${rows.data_title10 != null }">  
                                     <td data-custom="${rows.data_title10}">${rows.data_value10 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value10}' />
                                      </c:if>
                                         <c:if test="${rows.data_title11 != null }">  
                                     <td data-custom="${rows.data_title11}">${rows.data_value11 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value11}' />
                                      </c:if>
                                         <c:if test="${rows.data_title12 != null }">  
                                     <td data-custom="${rows.data_title12}">${rows.data_value12 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.อ12}' />
                                      </c:if>
                                         <c:if test="${rows.data_title13 != null }">  
                                     <td data-custom="${rows.data_title13}">${rows.data_value13 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value13}' />
                                      </c:if>
                                         <c:if test="${rows.data_title14 != null }">  
                                     <td data-custom="${rows.data_title14}">${rows.data_value14 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value14}' />
                                      </c:if>
                                         <c:if test="${rows.data_title15 != null }">  
                                     <td data-custom="${rows.data_title15}">${rows.data_value15 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value15}' />
                                      </c:if>
                                         <c:if test="${rows.data_title16 != null }">  
                                     <td data-custom="${rows.data_title16}">${rows.data_value16 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value16}' />
                                      </c:if>
                                         <c:if test="${rows.data_title17 != null }">  
                                     <td data-custom="${rows.data_title17}">${rows.data_value17 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value17}' />
                                      </c:if>
                                         <c:if test="${rows.data_title18 != null }">  
                                     <td data-custom="${rows.data_title18}">${rows.data_value18 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value18}' />
                                      </c:if>
                                         <c:if test="${rows.data_title19 != null }">  
                                     <td data-custom="${rows.data_title19}">${rows.data_value19 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value19}' />
                                      </c:if>
                                         <c:if test="${rows.data_title20 != null }">  
                                     <td data-custom="${rows.data_title20}">${rows.data_value20 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_value20}' />
                                      </c:if>
                                      
                 <td data-custom="data_due">${rows.data_due }</td>
                                 
                                  
                                             <td class="action">   
                                               <c:set var="namesArray" value="${fn:split(rows.data_filetype, ',')}" />
                         <c:forEach var="nameItem" items="${namesArray}" begin="0" end="5" varStatus="count">
								<a href="/render-filemanager?file=${rows.data_filepath }&&count=${count.count}" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); changeRowColor(this); return false;">
									        ${nameItem}
								</a>
								<br>
                                   </c:forEach>            
                                                 </td>
                                               
                                         
                                         
                                         
                                      <td data-custom="ผู้เพิ่ม">${rows.data_nameadd }</td>
                                     
                                                                                 <c:set var="color" value="" />
                     					<c:if test="${rows.log_flow ==  0 }">   
                      							<spring:message code="status.waitingtocheck" text="รอตรวจ"  var="status"/>
                      						<c:set var="color" value="black" />
                     					 </c:if>
                                        <c:if test="${rows.log_flow ==  1 }">   
                      						<spring:message code="status.success" text="อนุมัติเเล้ว"  var="status"/>
                      							<c:set var="color" value="green" />
                     					 </c:if>
                     					    <c:if test="${rows.log_flow ==  -1 }">   
                      						<spring:message code="status.reject" text="ไม่อนุมัติ"  var="status"/>
                      							<c:set var="color" value="red" />
                     					 </c:if>
                                                 
                                        
                                     
                            
                                  
                                 </tr>
                                 </c:forEach>
                                 
                                                                  
                         
                                 </tbody>
                                 
                                 
                                 
                                 
                                 </table>            
                               
                            
                              </div>
                   </c:forEach>
                   
          
                    
                        <div class="card-header">
                                <h4 class="card-title">Attendance </h4>
                            </div>
                            <div class="card-body"> 
                                <table class="table">
                                    <thead>
                                        <tr>
                                            <th>Date</th>
                                            <th>Check IN</th>
                                            <th>Check OUT</th>
                                              <th>Name</th>
                                             <th>IP</th>
                                                 <th>Image IN</th>
                                              <th>Image OUT</th>
                                               <th>Location</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    
                                  <c:forEach items="${list_attendances}" var="row" varStatus="count">  
                                        <tr>
                                            <td>${row.attendance_date }</td>
                                             <td>${row.attendance_checkin }</td>
                                             <td>${row.attendance_checkout }</td>
                                                <td>${row.user_name }</td>
                                            <td>${row.attendance_ip }</td>
                                                  <td> <c:if test="${row.attendance_file != null}"> 
                                        
                                         <img src="/readdocument/Attendance/${row.attendance_file}" style="width:100px;cursor: pointer;" target="_blank" 
									     onclick="openCenteredWindow(this.src, 'File Window', 500, 500);return false;"></img></c:if></td>
                                         <td> <c:if test="${row.attendance_file2 != null}">
                                         <img src="/readdocument/Attendance/${row.attendance_file2}" style="width:100px;cursor: pointer;" target="_blank" 
									     onclick="openCenteredWindow(this.src, 'File Window', 500, 500);return false;"></img></c:if></td>
									     
                                             <td><a href="https://nominatim.openstreetmap.org/reverse?lat=${row.attendance_lat }&lon=${row.attendance_lon }&format=json"  target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 500, 200);return false;">${row.attendance_lat },${row.attendance_lon }</a></td>
                                        </tr>
                               </c:forEach>   
                                        
                                  
                                    </tbody>
                                </table>
                                       
                              </div>
           
                 
                        
                        
