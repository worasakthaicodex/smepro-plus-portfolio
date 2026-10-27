<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

    <style>
 th {
            cursor: pointer;
            background-color: #f4f4f4;
        }
        
   .sort-icon {
            position: absolute;
            right: 10px;
        }      
        
     </style>   
        

                                             <table id="tabledataes" class="tabledata" style="height:auto">
                                    <thead>
                                        <tr >
                                 <c:forEach items="${eformfields2 }" var="row" varStatus="count">     
                              
                            		 <c:if test="${!fn:contains(listhidden,row.field_label)  && fn:contains(alowtitle,count.count) }"> 
                                        <th class="form-label fixhead fixhead2" >${row.field_label }</th>
                       				</c:if>
                               </c:forEach>  
                               
                                 <c:if test='${eformfields2.size() > 0 }'> 
                             <c:if test="${!fn:contains(listhidden,'userinsert')  }"> 
                                       <th class="form-label fixhead fixhead2"><spring:message code="common.userinsert"  /></th>
                            </c:if>
                            <c:if test="${!fn:contains(listhidden,'userrefer')   }"> 
                                       <th  class="form-label fixhead fixhead2"><spring:message code="common.userrefer"  /></th>
                           </c:if>
                                       
                            <c:if test="${!fn:contains(listhidden,'date')   }"> 
                                             <th class="form-label fixhead2"><spring:message code="common.date"  /></th>
                               </c:if>
                                 <c:if test="${!fn:contains(listhidden,'attached')  }">         
									    <th  class="form-label fixhead fixhead2"><spring:message code="common.attached"  /></th>
							</c:if>
                 					 </c:if>
                  
                  
                  
                              <c:forEach items="${eformfields }" var="row" varStatus="count">     
                              
                              <c:if test="${!fn:contains(listhidden2,row.field_label) }"> 
                                        <th class="form-label fixhead" data-column="${count.count }">${row.field_label }</th>
                                </c:if>        
                                          <c:set var="rowcount" value='${count.count}' />
                               </c:forEach>  
                               
                               
                          
                             <c:if test="${!fn:contains(listhidden2,'date')  }"> 
                                       <th class="form-label fixhead fixhead"  data-column="${rowcount+1}"><spring:message code="common.date"  /></th>
                            </c:if>
                            <c:if test="${!fn:contains(listhidden2,'attached')   }"> 
                                       <th  class="form-label fixhead fixhead" data-column="${rowcount+2}"><spring:message code="common.attached"  /></th>
                           </c:if>
                                       
                            <c:if test="${!fn:contains(listhidden2,'userinsert')   }"> 
                                             <th class="form-label fixhead" data-column="${rowcount+3 }"><spring:message code="common.userinsert"  /></th>
                               </c:if>
                                 <c:if test="${!fn:contains(listhidden2,'userrefer')  }">         
									    <th  class="form-label fixhead fixhead" data-column="${rowcount+4 }"><spring:message code="common.userrefer"  /></th>
							</c:if>
                                     <c:if test="${!fn:contains(listhidden2,'usercheck')  }">         
									    <th  class="form-label fixhead fixhead" data-column="${rowcount+5}"><spring:message code="common.usercheck"  /></th>
							</c:if>
							            <c:if test="${!fn:contains(listhidden2,'datecheck')  }">         
									    <th  class="form-label fixhead fixhead" data-column="${rowcount+6}"><spring:message code="common.datecheck"  /></th>
							</c:if>
							           <c:if test="${!fn:contains(listhidden2,'notecheck')  }">         
									    <th  class="form-label fixhead fixhead" data-column="${rowcount+7 }"><spring:message code="common.notecheck"  /></th>
							</c:if>
							
							
                                             <c:if test="${!fn:contains(listhidden2,'status')  }">         
									    <th  class="form-label fixhead fixhead" data-column="${count.count+8 }"><spring:message code="common.status"  /></th>
							</c:if>   
                        
                                    
                                               <th class="form-label fixhead" ><spring:message code="btn.action"  /></th>
                                    </thead>
                               
                               
                               
                                 <tbody>  
                                 
                            
                                 
                                 <c:forEach items="${edatas }" var="rows" varStatus="count">  
                     
                         
                                  
                                
                                 <tr class="rowbody e-${rows.data_id}" id="TR-${rows.data_id}">
                              
                                     <c:if test="${ !fn:contains(listhidden,rows.data_titles1)  && fn:contains(alowtitle,1) }">  
                                     <td data-custom="${rows.data_titles1}" title="${rows.data_values1}">${rows.data_values1 }
                   
                                     </td>
                                  <c:set var="newdata" value='${newdata}=${rows.data_values1}' />
                              
                                      </c:if>
                                    <c:if test="${  !fn:contains(listhidden,rows.data_titles2)  && fn:contains(alowtitle,2) }">   
                                     <td data-custom="${rows.data_titles2}" title="${rows.data_values2}">${rows.data_values2 }</td>
                                       <c:set var="newdata" value='${newdata}=${rows.data_values2}' />
                                      
                                      </c:if>
                                         <c:if test="${!fn:contains(listhidden,rows.data_titles3)  && fn:contains(alowtitle,3) }">  
                                     <td data-custom="${rows.data_titles3}" title="${rows.data_values3}">${rows.data_values3 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values3}' />
                                         
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles4)  && fn:contains(alowtitle,4) }">  
                                     <td data-custom="${rows.data_titles4}" title="${rows.data_values4}">${rows.data_values4 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values4}' />
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles5)  && fn:contains(alowtitle,5)}">  
                                     <td data-custom="${rows.data_titles5}" title="${rows.data_values5}">${rows.data_values5 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values5}' />
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles6)  && fn:contains(alowtitle,6)}">  
                                     <td data-custom="${rows.data_titles6}" title="${rows.data_values6}">${rows.data_values6 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values6}' />
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles7)  && fn:contains(alowtitle,1)}">  
                                     <td data-custom="${rows.data_titles7}" title="${rows.data_values7}">${rows.data_values7 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values7}' />
                                      </c:if>
                                         <c:if test="${  !fn:contains(listhidden,rows.data_titles8)  && fn:contains(alowtitle,8)}">  
                                     <td data-custom="${rows.data_titles8}" title="${rows.data_values8}">${rows.data_values8 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values8}' />
                                      </c:if>
                                         <c:if test="${  !fn:contains(listhidden,rows.data_titles9)  && fn:contains(alowtitle,9)}">  
                                     <td data-custom="${rows.data_titles9}" title="${rows.data_values9}">${rows.data_values9 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values9}' />
                                           
                                           
                                      </c:if>  
                                       <c:if test="${!fn:contains(listhidden,rows.data_titles10)  && fn:contains(alowtitle,10) }">  
                                     <td data-custom="${rows.data_titles10}">${rows.data_values10 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values10}' />
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles11)  && fn:contains(alowtitle,11)}">  
                                     <td data-custom="${rows.data_titles11}" title="${rows.data_values11}">${rows.data_values11 }</td>
                                           <c:set var="newdata" value='${newdata}=${data_values11}' />
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles12)  && fn:contains(alowtitle,12)}">  
                                     <td data-custom="${rows.data_titles12}" title="${rows.data_values12}">${rows.data_values12 }</td>
                                           <c:set var="newdata" value='${newdata}=${data_valuess12}' />
                                      </c:if>
                                         <c:if test="${!fn:contains(listhidden,rows.data_titles13)  && fn:contains(alowtitle,13)}">  
                                     <td data-custom="${rows.data_titles13}" title="${rows.data_values13}">${rows.data_values13 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_values13}' />
                                      </c:if>
                                         <c:if test="${  !fn:contains(listhidden,rows.data_titles14)  && fn:contains(alowtitle,14)}">  
                                     <td data-custom="${rows.data_titles14}" title="${rows.data_values14}">${rows.data_values14 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values14}' />
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles15)  && fn:contains(alowtitle,15)}">  
                                     <td data-custom="${rows.data_titles15}" title="${rows.data_values15}">${rows.data_values15 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values15}' />
                                      </c:if>
                                         <c:if test="${!fn:contains(listhidden,rows.data_titles16)  && fn:contains(alowtitle,16)}">  
                                     <td data-custom="${rows.data_titles16}" title="${rows.data_values16}">${rows.data_values16 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values16}' />
                                      </c:if>
                                         <c:if test="${ !fn:contains(listhidden,rows.data_titles17)  && fn:contains(alowtitle,17)}">  
                                     <td data-custom="${rows.data_titles17}" title="${rows.data_values17}">${rows.data_values17 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values17}' />
                                      </c:if>
                                         <c:if test="${!fn:contains(listhidden,rows.data_titles18)  && fn:contains(alowtitle,18)}">  
                                     <td data-custom="${rows.data_titles18}" title="${rows.data_values18}">${rows.data_values18 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_values18}' />
                                      </c:if>
                                         <c:if test="${!fn:contains(listhidden,rows.data_titles19)  && fn:contains(alowtitle,19)}">  
                                     <td data-custom="${rows.data_titles19}" title="${rows.data_values19}">${rows.data_values19 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values19}' />
                                      </c:if>
                                         <c:if test="${!fn:contains(listhidden,rows.data_titles20)  && fn:contains(alowtitle,20)}">  
                                     <td data-custom="${rows.data_titles20}" title="${rows.data_values20}">${rows.data_values20 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values20}' />
                                      </c:if>
                                      
                                      
                                      
                                      <c:set var="sizes" value="${eformfields2.size() }" />
                                    <c:if test="${sizes > 0}">  
                                      
                                      
                                                <c:if test='${eformfields2.size() > 0 }'> 
                             <c:if test="${!fn:contains(listhidden,'userinsert')  }"> 
                                       <td data-custom="ผู้เพิ่ม">${rows.data_nameadds }</td>
                            </c:if>
                            <c:if test="${!fn:contains(listhidden,'userrefer')   }"> 
                                            <td >${rows.data_namerefers }</td>
                           </c:if>
                                       
                            <c:if test="${!fn:contains(listhidden,'date')   }"> 
                                              <td >${rows.data_dues }</td> 
                               </c:if>
                                 <c:if test="${!fn:contains(listhidden,'attached')  }">         
									 					            <td class="action">   
                                               <c:set var="namesArray" value="${fn:split(rows.data_filetypes, ',')}" />
                      
                      
                         <c:forEach items="${rows.file}" var="file"   varStatus="count">
                         
                       
								<a href="/render-filemanager?file=${rows.data_filepaths }&&count=${count.count}&&file_edata=${file.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); changeRowColor(this); return false;">
									       ${file.file_name_original }
								</a>
								<br>
                                   </c:forEach>            
                                                 </td>
							</c:if>
                 					 </c:if>
                                      
				
                                       </c:if>
                                 
                                 
                                 
                
                                     <c:set var="size" value="${eformfields.size() }" />
                                     <c:if test="${size > 0  && !fn:contains(listhidden2,rows.data_title1) }">  
                                     <c:set var="datas" value="${rows.data_title1 }" />  
                                     
                                     <td data-custom="${rows.data_title1}" title="${rows.data_value1}"  
                                   class="inputDiv inputDiv1"    <c:if test='${fn:contains(listchecksum,datas)}'>  data-expression="${rows.data_value1 }"</c:if>>${rows.data_value1 }</td>
                                  <c:set var="newdata" value='${newdata}=${rows.data_value1}' /> 

                         
                                
                                      
                                         </c:if>
                                         
                                           <c:if test="${size > 1 && !fn:contains(listhidden2,rows.data_title2)  }">     
                           
                                     <td data-custom="${rows.data_title2}" title="${rows.data_value2}" 
                                     	 <c:set var="datas" value="${rows.data_title2 }" /> class="inputDiv inputDiv2"        
                                     	 <c:if test='${fn:contains(listchecksum,datas)}'>data-expression="${rows.data_value2 }"
                                     </c:if>
                                     >${rows.data_value2 }</td>
                                       <c:set var="newdata" value='${newdata}=${rows.data_value2}' />
                                 
	                    
                                     
                                      </c:if>
                                      
                                      
                                   <c:if test="${size > 2 && !fn:contains(listhidden2,rows.data_title3) }">  
                                    <c:set var="datas" value="${rows.data_title3 }" />    
                                     <td data-custom="${rows.data_title3}" title="${rows.data_value3}" class="inputDiv inputDiv3"   
                                     <c:if test='${fn:contains(listchecksum,datas)}'>data-expression="${rows.data_value3 }"  </c:if>  >${rows.data_value3 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value3}' />
                                
                                         
                             
                             
                                      </c:if>
                                <c:if test="${size > 3 && !fn:contains(listhidden2,rows.data_title4) }">  
                                	 <c:set var="datas" value="${rows.data_title4 }" />   
                                     <td data-custom="${rows.data_title4}" title="${rows.data_value4}" class="inputDiv inputDiv4"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'> data-expression="${rows.data_value4 }"   </c:if>  >${rows.data_value4 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value4}' />
                                              
                                
                                     
                                      </c:if>
                                   <c:if test="${size > 4 && !fn:contains(listhidden2,rows.data_title5) }">  
                                   	 <c:set var="datas" value="${rows.data_title5 }" />    
                                     <td data-custom="${rows.data_title5}" title="${rows.data_value5}" class="inputDiv inputDiv5" 
                                        <c:if test='${fn:contains(listchecksum,datas)}'> data-expression="${rows.data_value5 }"   </c:if>  >${rows.data_value5 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value5}' />
                                   
                                   
                             
                                    
                                      </c:if>
                                      <c:if test="${size > 5 && !fn:contains(listhidden2,rows.data_title6) }">   
                                      	 <c:set var="datas" value="${rows.data_title6 }" />    
                                     <td data-custom="${rows.data_title6}" title="${rows.data_value6}" class="inputDiv inputDiv6" 
                                     <c:if test='${fn:contains(listchecksum,datas)}'>
                                       data-expression="${rows.data_value6 }"
                                         </c:if> 
                                       >${rows.data_value6 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value6}' />
                              
                              
                              
                              
                                      </c:if>
                                    <c:if test="${size > 6 && !fn:contains(listhidden2,rows.data_title7) }">  
                                    	 <c:set var="datas" value="${rows.data_title7 }" />  
                                     <td data-custom="${rows.data_title7}" title="${rows.data_value7}" class="inputDiv inputDiv7"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value7 }"
                                        </c:if>  
                                      >${rows.data_value7 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value7}' />
                                 
                             
                                      </c:if>
                                   <c:if test="${size > 7 && !fn:contains(listhidden2,rows.data_title8)  }"> 
                                    <c:set var="datas" value="${rows.data_title8 }" />     
                                     <td data-custom="${rows.data_title8}" title="${rows.data_value8}" class="inputDiv inputDiv8"
                                          <c:if test='${fn:contains(listchecksum,datas)}'>
                                        data-expression="${rows.data_value8 }"
                                           </c:if>  
                                        >${rows.data_value8 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value8}' />
                                           
                     
                                      </c:if>
                               <c:if test="${size > 8 && !fn:contains(listhidden2,rows.data_title9) }">  
                                <c:set var="datas" value="${rows.data_title9}" /> 
                                     <td data-custom="${rows.data_title9}" title="${rows.data_value9}" class="inputDiv inputDiv9"   
                                   <c:if test='${fn:contains(listchecksum,datas)}'>   
                                     data-expression="${rows.data_value9 }"
                                       </c:if>   
                                     >${rows.data_value9 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value9}' />
                             
                                           
                                      </c:if> 
                                      
                                    <c:if test="${size > 9 && !fn:contains(listhidden2,rows.data_title10) }">   
                                    	 <c:set var="datas" value="${rows.data_title10 }" />        
                                     <td data-custom="${rows.data_title10}" title="${rows.data_value10}" class="inputDiv inputDiv10" 
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                       data-expression="${rows.data_value10 }"
                                         </c:if> 
                                       >${rows.data_value10 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value10}' />
                               
                                      </c:if>
                                <c:if test="${size > 10 && !fn:contains(listhidden2,rows.data_title11) }">  
                                 <c:set var="datas" value="${rows.data_title11 }" />  
                                     <td data-custom="${rows.data_title11}" title="${rows.data_value11}" class="inputDiv inputDiv11" 
                                      <c:if test='${fn:contains(listchecksum,datas)}'>   
                                       data-expression="${rows.data_value11 }"
                                        </c:if>    
                                       >${rows.data_value11 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value11}' />
                               
                                   
                                      </c:if>
                                <c:if test="${size > 11 && !fn:contains(listhidden2,rows.data_title12) }">  
                                 <c:set var="datas" value="${rows.data_title12 }" />   
                                     <td data-custom="${rows.data_title12}" title="${rows.data_value12}" class="inputDiv inputDiv12"   
                                  <c:if test='${fn:contains(listchecksum,datas)}'>
                                     data-expression="${rows.data_value12 }"
                                   </c:if>    
                                     >${rows.data_value12 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value12}' />
                               
                                       
                                      </c:if>
                          <c:if test="${size > 12 && !fn:contains(listhidden2,rows.data_title13) }">  
                           <c:set var="datas" value="${rows.data_title13 }" /> 
                                     <td data-custom="${rows.data_title13}" title="${rows.data_value13}"  class="inputDiv inputDiv13"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value13 }"
                                       </c:if>  
                                      >${rows.data_value13 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value13}' />
                                           
                            
                                      </c:if>
                                  <c:if test="${size > 13 && !fn:contains(listhidden2,rows.data_title14) }">  
                                   <c:set var="datas" value="${rows.data_title14 }" />    
                                     <td data-custom="${rows.data_title14}" title="${rows.data_value14}" class="inputDiv inputDiv14"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value14 }"
                                        </c:if>  
                                      >${rows.data_value14 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value14}' />
                                           
                            
                                      </c:if>
                                 <c:if test="${size > 14 && !fn:contains(listhidden2,rows.data_title15) }">  
                                  <c:set var="datas" value="${rows.data_title15 }" />
                                     <td data-custom="${rows.data_title15}" title="${rows.data_value15}" class="inputDiv inputDiv15" 
                                       <c:if test='${fn:contains(avglistchecksum,datas)}'>
                                       data-expression="${rows.data_value15 }"
                                         </c:if>  
                                       >${rows.data_value15 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value15}' />
                              
                                      </c:if>
                                   <c:if test="${size > 15 && !fn:contains(listhidden2,rows.data_title16) }"> 
                                    <c:set var="datas" value="${rows.data_title16 }" /> 
                                     <td data-custom="${rows.data_title16}" title="${rows.data_value16}" class="inputDiv inputDiv16"  
                                       <c:if test='${fn:contains(listchecksum,datas)}'>  
                                      data-expression="${rows.data_value16 }"
                                        </c:if>  
                                      >${rows.data_value16 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value16}' />
                              
                                      </c:if>
                                  <c:if test="${size > 16 && !fn:contains(listhidden2,rows.data_title17) }">  
                                   <c:set var="datas" value="${rows.data_title17 }" />   
                                     <td data-custom="${rows.data_title17}" title="${rows.data_value17}" class="inputDiv inputDiv17"   
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                     data-expression="${rows.data_value17 }"
                                      </c:if>  
                                     >${rows.data_value17 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value17}' />
                              
                                      </c:if>
                                   <c:if test="${size > 17 && !fn:contains(listhidden2,rows.data_title18) }">  
                                    <c:set var="datas" value="${rows.data_title18 }" />    
                                     <td data-custom="${rows.data_title18}" title="${rows.data_value18}" class="inputDiv inputDiv18"  
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value18 }"
                                         </c:if>  
                                      >${rows.data_value18 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value18}' />
                             
                                      </c:if>
                                <c:if test="${size > 18 && !fn:contains(listhidden2,rows.data_title19) }">  
                                	 <c:set var="datas" value="${rows.data_title19 }" />   
                                     <td data-custom="${rows.data_title19}" title="${rows.data_value19}" class="inputDiv inputDiv19" 
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                       data-expression="${rows.data_value19 }"
                                          </c:if>  
                                       >${rows.data_value19 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value19}' />
                                           
                                           
                             
                                      </c:if>
                               <c:if test="${size > 19 && !fn:contains(listhidden2,rows.data_title20) }">  
                                <c:set var="datas" value="${rows.data_title20 }" />   
                                     <td data-custom="${rows.data_title20}" title="${rows.data_value20}" class="inputDiv inputDiv20"   
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                     data-expression="${rows.data_value20 }"
                                          </c:if>  
                                     >${rows.data_value20 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value20}' />
                                           
                            
                                      </c:if>
                                      
                
                     
                     

                                 
                                 
                                               
                                         
                             <c:if test="${!fn:contains(listhidden2,'date')  }"> 
                                                       <td data-custom="data_due"  class="inputDiv inputDiv21"   data-expression="end">${rows.data_due }</td>
                            </c:if>
                            <c:if test="${!fn:contains(listhidden2,'attached')   }"> 
                                        
                                             <td class="action">   
                                               <c:set var="namesArray" value="${fn:split(rows.data_filetype, ',')}" />
                                               
                      <c:forEach items="${rows.file}" var="file"   varStatus="count">
                         
                       
								<a href="/render-filemanager?file=${rows.log_id }&&count=${count.count}&&file_edata=${file.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); changeRowColor(this); return false;">
									       ${file.file_name_original }
								</a>
								<br>
                                   </c:forEach>              
                                                 </td>
                           </c:if>
                                       
                            <c:if test="${!fn:contains(listhidden2,'userinsert')   }"> 
                                                <td data-custom="ผู้เพิ่ม">${rows.data_nameadd }</td>
                               </c:if>
                                 <c:if test="${!fn:contains(listhidden2,'userrefer')  }">         
								    <td >${rows.data_namerefer }</td>
							</c:if>
                                     <c:if test="${!fn:contains(listhidden2,'usercheck')  }">         
									   <td >${rows.data_nameapp }</td>
							</c:if>
							            <c:if test="${!fn:contains(listhidden2,'datecheck')  }">         
									             <td >${rows.data_datecheck }</td>
							</c:if>
					       
								      <c:if test="${!fn:contains(listhidden2,'notecheck')  }">              
                             <td data-custom="บันทึกผู้ตรวจ">   
                                       
               
    												${rows.data_comment }
                                  
                                             
                                             </td>
									          </c:if>
							
							
                                             <c:if test="${!fn:contains(listhidden2,'status')  }">         
									     <c:set var="color" value="" />
									     
									     
                     					<c:if test="${rows.data_flow ==  1 }">   
                      							<spring:message code="status.waitingtocheck" text="รอตรวจ"  var="status"/>
                      						<c:set var="color" value="black" />
                     					 </c:if>
                                        <c:if test="${rows.data_flow  ==  2 }">   
                      						<spring:message code="status.success" text="อนุมัติเเล้ว"  var="status"/>
                      							<c:set var="color" value="green" />
                     					 </c:if>
                     					    <c:if test="${rows.data_flow ==  -1 }">   
                      						<spring:message code="status.reject" text="ไม่อนุมัติ"  var="status"/>
                      							<c:set var="color" value="red" />
                     					 </c:if>
                                                 
												 	 <c:if test="${rows.data_flow ==  -2 }">   
                      			
                      						  <spring:message code="task.taskspendingclearance" text="งานรอเคลียร์" var="status" />
                      					<c:set var="color" value="black" />
                     					 </c:if>
              
                                           <td style="color:${color}">   
                                    
    
    												${status }
                                  
                                             
                                             </td>
							</c:if>   
                        
                                    
                                               
                                               
                                         
                               
                                   
                                    
                           
                                
                                    
                         
                                      <th>   
                            
                                                <div class="btn-group" >
												
									<c:set var="step" value="0" />				
												
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" 
								
								id="ACE${rows.data_id}"
								data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                 <spring:message code="btn.action"  />
                                </button>
                                <div class="dropdown-menu" style="">
                        
                  <c:if test="${upload_pn.turn == 1}">  
                  	<c:if test="${rows.data_flow ==  1 }">   
                                 <c:if test="${Member_name.equals(upload_pn.upload_pn_app)}">      
                                <a class="dropdown-item" href="javascript:void(0)"  class="btn btn-sm btn-success"
                                 onclick="changeRowColor(this);app(${rows.data_id});getdata2('/editdocument2-filemanager/${rows.data_id}/${upload_pn.upload_pn_id}','${rows.log_id}')" data-bs-toggle="modal" data-bs-target="#addNewCards">  
                                <spring:message code="btn.app" text="อนุมัติ"  />
                                  <c:if test="${rows.data_due >  rows.data_due}">** <spring:message code="forms.uploadnotschedule"  />**</c:if>
                                </a>
								
									<c:set var="step" value="1" />		
								
                                </c:if>
                          </c:if>
                            </c:if>  
                            
                                
                                 <c:if test="${upload_pn.turn == 0}">  
				                                       <c:if test="${rows.data_flow ==  0 || rows.data_flow ==  -1|| rows.data_flow ==  1}">  
				                                         <c:if test="${Member_name.equals(rows.data_nameadd)  or rows.data_nameadd == null}"> 
				                                <a class="dropdown-item" href="javascript:void(0)"  onclick="changeRowColor(this);
				                                getdata('/editdocument-filemanager/${rows.data_id}/${upload_pn.upload_pn_id}','${rows.log_id}')" class="btn btn-sm btn-light" data-bs-toggle="modal" data-bs-target="#addNewCards2" >
				                                <spring:message code="btn.edit" text="แก้ไข" /></a>
				                                		 <c:set var="step" value="1" />		
														 </c:if> 
				                                    	</c:if>
				                                  	<c:if test="${rows.data_flow ==  -2 }">   
				                                         <c:if test="${Member_name.equals(rows.data_nameadd)  or rows.data_nameadd == null}"> 
				                                <a class="dropdown-item" href="javascript:void(0)"  onclick="changeRowColor(this);getdata('/editdocument-filemanager/${rows.data_id}/${upload_pn.upload_pn_id}','${rows.log_id}')" class="btn btn-sm btn-light">
				                                <spring:message code="btn.clear" text="เคลียร์" /></a>
				                                		<c:set var="step" value="1" />		
														</c:if> 
				                                    	</c:if>
                                </c:if>
                                
                                
                                
                          
                              	<c:if test="${rows.data_flow ==  1 }">   
                                
                                
                                
                                  <c:if test="${upload_pn.turn == 1}">  
                                             <c:if test="${Member_name.equals(upload_pn.upload_pn_app)}">     
                                 <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  onclick="changeRowColor(this);reject(${rows.data_id})" 
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard"><spring:message code="btn.unapp" text="ไม่อนุมัติ" />
                                 <c:if test="${rows.log_date >  rows.data_due}">**<spring:message code="forms.uploadnotschedule"  />**</c:if>
                                
								</a><c:set var="step" value="1" />		
                                      		</c:if>
                                      	</c:if>	
                                      		
                                      		
                                   	</c:if>
                                    
                                    
                                       <c:if test="${upload_pn.turn == 0}">  
                                       <c:if test="${rows.data_flow ==  0 || rows.data_flow ==  -1 || rows.data_flow ==  1 }">  
                                       
                  
                                         <c:if test="${Member_name.equals(rows.data_nameadd) and  rows.data_join == 0 }"> 
                                <a class="dropdown-item" href="javascript:void(0)"  onclick="changeRowColor(this);return getdata('/undocument-filemanager/${rows.data_id}','${rows.data_id}');" ><spring:message code="btn.del2" text="นำออก" /></a>
                                  		<c:set var="step" value="1" />		
										</c:if> 

                                    		</c:if>
                                    		</c:if>	
                                    			
				
                                   <c:if test="${form != null}">	 
									<c:set var="step" value="1" />													
	                        	<a href="/bill-filemanager?id=${rows.data_id }"  class="dropdown-item" target="_blank" 
										     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); changeRowColor(this); return false;">
										      <spring:message code="forms.Opendocumentform"  />
								</a>
									<a href="/bill-filemanager?id=${rows.data_id }"  class="dropdown-item" target="_blank" >
										       <spring:message code="common.print"  />
								</a>
								 </c:if>
								 <c:if test="${condition.calculation_port1 > 0   || condition.calculation_port2 > 0}"> 
								 <c:set var="step" value="1" />		
                                       <a class="dropdown-item" href="/stock-filemanager/${rows.data_id}"  target="_blank">
                                        <spring:message code="common.data"  />
                                       </a>
                                   </c:if>
                                    		
                     	
                                    		
                                </div>
                            </div>
                            
                
                                            <c:if test="${step == 0}">   
                                                 <c:if test="${rows.data_status == 'Good' }">   
								     	 	<span class="badge rounded-pill bg-success" style="width:20px;">&nbsp;</span>  
								     	 		</c:if>	
								     	 	       <c:if test="${rows.data_status == 'Normal' }">   
								     	 	<span class="badge rounded-pill bg-warning" style="width:20px;">&nbsp;</span>  
								     	 		</c:if>	
								     	 		       <c:if test="${rows.data_status == 'Bad' }">   
								     	 	<span class="badge rounded-pill bg-danger" style="width:20px;">&nbsp;</span>  
								     	 		</c:if>	
								      <script>
					
									    $("#ACE${rows.data_id}").hide(); 
								   </script> 
								</c:if>	
								  
                                      </th>
                                 </tr>
								 
								
								    
							
                                 </c:forEach>
                              
                               

								
                             <script>
       
                             
	  $( function() {
		  
		  var newdata = '${newdata}'; // ข้อความเริ่มต้
		  var trimmedData = newdata.trim(); // ตัดช่องว่างที่อยู่ท้ายสตริง
		var dataItems = trimmedData.split('=');

		  
	    $( ".autocomplete-input" ).autocomplete({
	      source: dataItems
	    });
	  } );
	  
	  
	  

	  

	  
</script>      
                                 </tbody>
                                 
                                 
                                             
                               
                                
                                 </table>            
              

  
	<script>
// อ่านค่าคุกกี้
function getCookie(name) {
	  let pageId = new URLSearchParams(window.location.search).get("id");
	    if (!pageId) return null; // ถ้าไม่มี id ไม่ต้องอ่านค่า

	    let cookies = document.cookie.split(';');
	    for (let cookie of cookies) {
	        let [key, value] = cookie.split('=');
	        if (key.trim() === name + "_" + pageId) {
	            return decodeURIComponent(value);
	        }
	    }
	    return null;
}

var oldheaded = null;

// ตั้งค่าคุกกี้
function setCookie(name, value, days = 365) {
    let pageId = new URLSearchParams(window.location.search).get("id"); // ดึงค่า id จาก URL
    if (!pageId) return; // ถ้าไม่มี id ไม่ต้องบันทึก

    document.cookie = name + "_" + pageId + "=" + encodeURIComponent(value) + ";path=/"; 
    
}

// ฟังก์ชันอัปเดตไอคอน ▲▼
function updateSortIcons(table, column, order) {
    table.querySelectorAll(".sort-icon").forEach(icon => icon.innerText = "");
    
    var head= 'th[data-column="'+column+'"]';
    var header = table.querySelector(head);
    
    if(header){
    	
    	  var oldhead = header.innerHTML.replace(/▲|▼/g, "");
    	   header.innerText = order === "asc" ? oldhead+" ▲" : oldhead+" ▼";
    	   
    	   if(oldheaded != null && oldheaded !=  head){ 
    		   var header2 = table.querySelector(oldheaded);
    		   oldhead = header2.innerHTML.replace(/▲|▼/g, "");
    		   header2.innerText = oldhead;
    	   }
    	   oldheaded = head;
    }
 
    
    
}

// ฟังก์ชันเรียงลำดับ
function sortTable(table, column, order) {
    let rows = Array.from(table.querySelector("tbody").rows);
    rows.sort((rowA, rowB) => {
        let cellA = rowA.cells[column].innerText.trim();
        let cellB = rowB.cells[column].innerText.trim();

        let isNumeric = !isNaN(parseFloat(cellA)) && !isNaN(parseFloat(cellB));
        if (isNumeric) {
            return order === "asc" ? cellA - cellB : cellB - cellA;
        } else {
            return order === "asc" ? cellA.localeCompare(cellB) : cellB.localeCompare(cellA);
        }
    });

    let tbody = table.querySelector("tbody");
    tbody.innerHTML = "";
    rows.forEach(row => tbody.appendChild(row));

    // บันทึกค่าลงคุกกี้
    setCookie("sort_column", column);
    setCookie("sort_order", order);

    // อัปเดตไอคอน
    updateSortIcons(table, column, order);
}

document.addEventListener("DOMContentLoaded", function() {
    let table = document.getElementById("tabledataes");

    // อ่านค่าจากคุกกี้
    let savedColumn = getCookie("sort_column");
    let savedOrder = getCookie("sort_order");

    if (savedColumn !== null && savedOrder !== null) {
        sortTable(table, parseInt(savedColumn), savedOrder);
    }

    // เพิ่ม event listener ให้หัวตาราง
    table.querySelectorAll("th").forEach(th => {
        th.addEventListener("click", function() {
            let column = parseInt(this.getAttribute("data-column"));
            let currentOrder = getCookie("sort_order") === "asc" ? "desc" : "asc";
            sortTable(table, column, currentOrder);
        });
    });
});







</script>
                      