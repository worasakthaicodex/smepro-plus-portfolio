<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<c:if test="${logs.log_id > 0 }"> 
		<script>      

		$("#log_id").val("${logs.log_id}");
		$("#fp-default").val("${logs.log_due}");
		
		</script>  

</c:if>


<script>  
$("#data_filetype").val("${edatas.data_filetype}");
$("#data_id").val("${edatas.data_id}");
$("#namerefer").val("${edatas.data_namerefer }");







</script>  



<c:forEach items="${eformfields }" var="row" varStatus="count">
                                      <c:set var="title" value="data_value${row.field_id }" />
                                        <c:set var="title2" value="data_value${count.count }" />

    
                    
<script>
    <c:if test='${row.field_cal_type == "manufacturer"}'>   
        $("#manufacturer").val("${edatas.manufacturer}");
        $("#action_manufacturer").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "material"}'>   
        $("#material").val("${edatas.material}");
        $("#action_material").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "batch_material"}'>   
        $("#batch_material").val("${edatas.batch_material}");
        $("#action_batch_material").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "production_directive"}'>   
        $("#production_directive").val("${edatas.production_directive}");
        $("#action_production_directive").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "batch"}'>   
        $("#batch").val("${edatas.batch}");
        $("#action_batch").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "incoming"}'>   
        $("#incoming").val("${edatas.incoming}");
        $("#action_incoming").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "unit"}'>   
        $("#unit").val("${edatas.unit}");
        $("#action_unit").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "title"}'>   
        $("#title").val("${edatas.title}");
        $("#action_title").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "equipment"}'>   
        $("#equipment").val("${edatas.equipment}");
        $("#action_equipment").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "quality_control"}'>   
        $("#quality_control").val("${edatas.quality_control}");
        $("#action_quality_control").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "inspection_result"}'>   
        $("#inspection_result").val("${edatas.inspection_result}");
        $("#action_inspection_result").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "deviations"}'>   
        $("#deviations").val("${edatas.deviations}");
        $("#action_deviations").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "start"}'>   
        $("#start").val("${edatas.start}");
        $("#action_start").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "end"}'>   
        $("#end").val("${edatas.end}");
        $("#action_end").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "good_production_output"}'>   
        $("#good_production_output").val("${edatas.good_production_output}");
        $("#action_good_production_output").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "customer"}'>   
        $("#customer").val("${edatas.customer}");
        $("#action_customer").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "product"}'>   
        $("#product").val("${edatas.product}");
        $("#action_product").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "po"}'>   
        $("#po").val("${edatas.po}");
        $("#action_po").val("${row.field_cal_type2}");
    </c:if>

    <c:if test='${row.field_cal_type == "deliver"}'>   
        $("#deliver").val("${edatas.deliver}");
        $("#action_deliver").val("${row.field_cal_type2}");
    </c:if>
</script>

                    
                                               
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
                                                      <c:set var="maindata" value="${edatas.data_value1 }" />
                                                   <script>      
														$("#${title}").val("${edatas.data_value1 }");	 
													</script>  
												 </c:if>
												 
												 
												 
												 
													     <c:if test='${count.count == 2}'>   
													           <c:set var="maindata" value="${edatas.data_value2 }" />
                                                   <script>      
                             
														$("#${title}").val("${edatas.data_value2 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 3}'>   
												            <c:set var="maindata" value="${edatas.data_value3 }" />
                                                   <script>      
														$("#${title}").val("${edatas.data_value3 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 4}'>   
												            <c:set var="maindata" value="${edatas.data_value4 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value4 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 5}'>   
												            <c:set var="maindata" value="${edatas.data_value5 }" />
												      
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value5 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 6}'>   
												            <c:set var="maindata" value="${edatas.data_value6 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value6 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 7}'> 
												            <c:set var="maindata" value="${edatas.data_value7 }" />  
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value7 }");
														
													</script>  
												 </c:if>
												      <c:if test='${count.count == 8}'>   
												            <c:set var="maindata" value="${edatas.data_value8 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value8 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 9}'>   
												            <c:set var="maindata" value="${edatas.data_value9 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value9 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 10}'>   
												            <c:set var="maindata" value="${edatas.data_value10 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value10 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 11}'> 
												            <c:set var="maindata" value="${edatas.data_value11 }" />  
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value11 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 12}'>  
												            <c:set var="maindata" value="${edatas.data_value12 }" /> 
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value12 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 13}'>   
												            <c:set var="maindata" value="${edatas.data_value13 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value13 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 14}'>   
												            <c:set var="maindata" value="${edatas.data_value14 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value14 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 15}'>   
												            <c:set var="maindata" value="${edatas.data_value15 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value15 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 16}'>   
												            <c:set var="maindata" value="${edatas.data_value16}" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value16 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 17}'>  
												            <c:set var="maindata" value="${edatas.data_value17 }" /> 
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value17 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 18}'>  
												            <c:set var="maindata" value="${edatas.data_value18 }" /> 
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value18 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 19}'>   
												            <c:set var="maindata" value="${edatas.data_value19 }" />
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value19 }");
													</script>  
												 </c:if>
												      <c:if test='${count.count == 20}'>  
												            <c:set var="maindata" value="${edatas.data_value20 }" /> 
                                                   <script>      
                                
														$("#${title}").val("${edatas.data_value20 }");
													</script>  
												 </c:if>
												 
												 
												    <script>
												    
												   // alert("${row.field_cal_type }");
													 	<c:if test='${row.field_cal_type == "production_directive" }'>   
													  //  alert("${maindata }");
															$("#production_directive").val("${maindata }");
														</c:if>
													 	<c:if test='${row.field_cal_type == "batch" }'>   
														$("#batch").val("${maindata }");
														</c:if>	
												 	</script> 
												 	
												 	
                                                  </c:if>
             
                                                  
                          
                                            

</c:forEach>
	
	

	
	
	<c:forEach items="${edatas.file}" var="row"  begin="0" end="5" varStatus="count">

	<script> 
	



	var  link = `<a href="/render-filemanager?file=${edatas.log_id+0 }&&file_edata=${row.file_id }"  id="oldfile-${row.file_step}" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600);return false;">
									     ${row.file_name_original}
								</a>
							
						
								
					<a href="javascript:void(0)" style="color:red" onclick="unfile('${row.file_step}')" id="oldfiles-${row.file_step}" ><i data-feather='trash-2'></i></a>
								 <input type="hidden" name="oldfile${row.file_step}"  id="oldfile${row.file_step}" value="${row.file_name }" >
								<input type="hidden" name="oldfilename${row.file_step}" id="oldfilename${row.file_step}" value="${row.file_name_original}" >
							<br>`;
							 
							 if("${row.file_name }" != ''){
								 $("#filedata${row.file_step}").html(link);			
								 $("#filedatas${row.file_step}").hide();	 
							 }
							 feather.replace({
								    width: 14,
								    height: 14
								});		

						     function unfile(id) {
				            	   if ( confirm("คุณต้องการลบ Files นี้ ") ) {
				            		   $("#filedatas"+id).show();	
				            		   $("#oldfilename"+id).val("del");	
				            		   $("#oldfile-"+id).remove();	
				            		   $("#oldfiles-"+id).remove();	
									}
				            	   
				               }         
   
	</script> 			 
							 
</c:forEach> 		 



<script> 
feather.replace({
    width: 14,
    height: 14
});
window.scrollTo(0, 0);

$("#aboutdata").show();	
$("#edit").val(1);	
$("#report_id").val("${reportid}");	

   
       

</script> 
               <script>
               
               function unfile(id) {
            	   if ( confirm("คุณต้องการลบ Files นี้ ") ) {
            		   $("#filedatas"+id).show();	
            		   $("#oldfilename"+id).val("del");	
            		   $("#oldfile-"+id).remove();	
            		   $("#oldfiles-"+id).remove();	
					}
            	   
               }         
               
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}

</script>



