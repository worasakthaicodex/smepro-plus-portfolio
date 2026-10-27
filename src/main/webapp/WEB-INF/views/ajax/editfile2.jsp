<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


	







<c:forEach items="${eformfields }" var="row" varStatus="count">
                                      <c:set var="title" value="data_values${row.field_id }" />
                                        <c:set var="title2" value="data_values${count.count }" />
                                  
                                    
                                                   
                                               
                                 <c:if test='${row.field_type.equals("checkbox")}'>
                                   
                                                   
                                                   
                                              <c:if test='${count.count == 1}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value1 }"; 
                                                   </script>                                                     
													
               									</c:if>
                                                <c:if test='${count.count == 2}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value2 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 3}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value3 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 4}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value4 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 5}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value5 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 6}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value6 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 7}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value7 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 8}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value8 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 9}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value9 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 10}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value10 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 11}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value11 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 12}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value12 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 13}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value13 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 14}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value14 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 15}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value15 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 16}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value16 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 17}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value17 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 18}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value18 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 19}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value19 }"; 
                                                   </script>                                                     
													
               									</c:if>
               									  <c:if test='${count.count == 20}'>   
                                                   <script>     
                                              	 var id = "${edatas.data_value20 }"; 
                                                   </script>                                                     
													
               									</c:if>     
                                                   
                                                   
                                                   <script>  
													
															var idArray = id.split(","); 
															idArray.forEach(function(value) {
																
															    $('input[name="${title2}"][value="'+value+'"]').prop('checked', true);
															});
									               </script>                                                     
													
									</c:if>
                                                 
                                                 
                                                 
                                                   <c:if test='${row.field_type.equals("text")  or row.field_type.equals("number") or row.field_type.equals("textarea") or row.field_type.equals("date") 
                                                   or row.field_type.equals("time") or row.field_type.equals("select")}'>      
 
                                                    <c:if test='${count.count == 1}'>   
                                                   <script>      
                                        
														$("#${title}").val("${edatas.data_value1 }");
													</script>  
												 </c:if>
													     <c:if test='${count.count == 2}'>   
                                                   <script>      
                             
														$("#${title}").val("${edatas.data_value2 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 3}'>   
                                                   <script>      
														$("#${title}").val("${edatas.data_value3 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 4}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value4 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 5}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value5 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 6}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value6 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 7}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value7 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 8}'>   
                                                   <script>      
														$("#${title}").val("${edatas.data_value8 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 9}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value9 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 10}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value10 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 11}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value11 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 12}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value12 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 13}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value13 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 14}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value14 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 15}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value15 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 16}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value16 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 17}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value17 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 18}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value18 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 19}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value19 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 20}'>   
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value20 }");
													</script>  
												 </c:if>
												 
												 
												 
                                                  </c:if>
             
                                                  
                          
                                            

</c:forEach>






 


