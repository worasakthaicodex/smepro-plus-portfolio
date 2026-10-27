<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../common/mainhead.jspf" %>    
 <link href="<c:url value="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/app-assets/css/plugins/extensions/ext-component-drag-drop.css" />" rel="stylesheet">
  <link href="<c:url value="/resources/app-assets/vendors/css/extensions/dragula.min.css" />" rel="stylesheet">
 <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<title>Edata</title>


<style>
		 table {
		    border-collapse:collapse;
		    margin: 0;
		  width: 100%;
		   border: 1px solid #eee;
		   
		}
		
		
		td {
    text-align: center;
   border: 1px solid #eee;
      max-width: 200px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
  padding:10px;
}
td  .action{
 text-align: center;
    border: 1px solid #eee;
    max-width: 200px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
    
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #eee;
  background-color: #f2f2f2;
     height: 30px;
     
}
		
</style>

</head>
<c:set var="typeshow" value="1" />


<div class="modal-body flex-grow-1 pb-sm-0 pb-1">
                                        <ul class="nav nav-tabs tabs-line" role="tablist">
                                            <li class="nav-item">
                                                <a class="nav-link active" data-bs-toggle="tab" href="#details-tab" role="tab" aria-controls="details-tab" aria-selected="true">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                                                    <span class="align-middle ms-25">ข้อมูล</span>
                                                </a>
                                            </li>
                                                <li class="nav-item">
                                                <a class="nav-link" data-bs-toggle="tab" href="#details2-tab" role="tab" aria-controls="details-tab" aria-selected="true">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                                                    <span class="align-middle ms-25">ข้อมูลอ้างอิง</span>
                                                </a>
                                            </li>
                                            
                                              <li class="nav-item">
                                                <a class="nav-link" data-bs-toggle="tab" href="#details4-tab" role="tab" aria-controls="details-tab" aria-selected="true">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-file"><path d="M13 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V9z"></path><polyline points="13 2 13 9 20 9"></polyline></svg>
                                                    <span class="align-middle ms-25">การสื่อสารเเละบันทึก</span>
                                                </a>
                                            </li>
                                            
                                            <li class="nav-item">
                                                <a class="nav-link" data-bs-toggle="tab" href="#activity-tab" role="tab" aria-controls="activity-tab" aria-selected="false">
                                                    <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" 
                                                    stroke-linecap="round" stroke-linejoin="round" class="feather feather-activity"><polyline points="22 12 18 12 15 21 9 3 6 12 2 12"></polyline></svg>
                                                    <span class="align-middle ms-25">ไฟล์เเนบ</span>
                                                </a>
                                            </li>
                                        </ul>
                                        
                                      
                                        
                                        <div class="tab-content" id="myTabContent">
                                         
                                     
                                            <div class="tab-pane fade active show" id="details-tab" role="tabpanel" aria-labelledby="details-tab">
                                             
                                        <hr class="my-2">
                                                  <div class="mb-1 breadcrumb-right" style="float:right;margin-left:5px">
                    
               
                    
                               
                     <c:if test="${
                       flow.current_step == 'draft'
                      or flow.current_step == 'rejected' 
                      or flow.current_step == 'reviewed'
                      or flow.current_step == 'cleared'
                      }">                                   
                                        
                                                           <div class="dropdown">
                <button class="btn-icon btn btn-primary btn-round btn-sm dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-grid"><rect x="3" y="3" width="7" height="7"></rect><rect x="14" y="3" width="7" height="7"></rect><rect x="14" y="14" width="7" height="7"></rect><rect x="3" y="14" width="7" height="7"></rect></svg>
                
                </button>
                            <div class="dropdown-menu dropdown-menu-end">
                      
                            
                            
                                  <c:if test="${flow.current_step == 'reviewed' && Member_id == flow.receiver}">                       
                       
                       
                        <h6 class="dropdown-header">
                              Flow :  <spring:message code="btn.approval"   />
                             
                             </h6>
                               <div class="dropdown-divider"></div>
                              	   <a href="#"   onclick="app('${edata.data_id}')" class="dropdown-item" class="btn btn-primary"
                              	    data-bs-toggle="modal" data-bs-target="#addNewCard" style="color:green">
                                <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-check-square"><polyline points="9 11 12 14 22 4"></polyline><path d="M21 12v7a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h11"></path></svg> 
                                <spring:message code="btn.app" text="อนุมัติ" /></a>
                                       	   <c:set var="step" value="1" />	
                                      	   <a href="#"  onclick="reject('${edata.data_id}')" class="dropdown-item" data-bs-toggle="modal" data-bs-target="#addNewCards" style="color:red">
							                                
							             <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-help-circle"><circle cx="12" cy="12" r="10"></circle><path d="M9.09 9a3 3 0 0 1 5.83 1c0 2-3 3-3 3"></path><line x1="12" y1="17" x2="12.01" y2="17"></line></svg>                   
							                                
							                                  <spring:message code="btn.unapp" text="ไม่อนุมัติ" />
							                </a>	
                               </c:if>
                            
    
                                    <c:if test="${flow.current_step == 'draft' or flow.current_step == 'rejected' or flow.current_step == 'cleared'}">      
                
                       
                    
                                              <c:if test="${flow.current_step == 'draft' }"> 
                                               <h6 class="dropdown-header">
                              Flow :  <spring:message code="btn.sends"  text="การส่งเอกสาร" />
                             
                             </h6>
                    			  <div class="dropdown-divider"></div>
                                           <c:set var="current_step" value="to_review" />
                                           <a href="/senddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}"     class="dropdown-item" >
                                              <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-send me-1"><line x1="22" y1="2" x2="11" y2="13"></line><polygon points="22 2 15 22 11 13 2 9 22 2"></polygon></svg>           
                                                 <spring:message code="btn.send"  text="ส่งเอกสาร" />
                                           
                                           </a> 
                                          </c:if>
                                      
                                       <c:if test="${flow.current_step == 'rejected' }"> 
                                        <h6 class="dropdown-header">
                              Flow :  <spring:message code="btn.sends"  text="การส่งเอกสาร" />
                             
                             </h6>
                    			  <div class="dropdown-divider"></div>
                                          <c:set var="current_step" value="to_review_edit" />
                                                      <a href="/senddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/to_review_edit"    class="dropdown-item" >
                                                      <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-send me-1"><line x1="22" y1="2" x2="11" y2="13"></line><polygon points="22 2 15 22 11 13 2 9 22 2"></polygon></svg>           
                                                 <spring:message code="btn.send"  text="ส่งเอกสาร" />
                                                      
                                                      </a> 
                                       </c:if>
                                      </c:if>    
                                       
<c:if test="${ ( flow.current_step == 'draft' or flow.current_step == 'rejected' or flow.current_step == 'cleared'  )  &&  flow.receiver == Member_id }">    
 <c:if test="${flow.current_step == 'cleared'  && ( key.datalink_name != 'requisition_form' )  }"> 
                                
                                                <h6 class="dropdown-header">
                               Flow : <spring:message code="btn.verification"  text="การยืนยันเอกสาร" />
                             
                             </h6>
                             
                             
                                <c:if test="${CLEARED_COUNT == 0}">    
                                       <a class="dropdown-item" href="/cleardocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/clear" 
                                      class="dropdown-item"  >&nbsp; &nbsp; <i data-feather='edit-3'></i>
                            <span class="align-middle"><spring:message code="btn.clear"/></span></a>
          
                            </c:if>
                            

                           <c:if test="${CLEARED_COUNT > 0}">   
                            <a class="dropdown-item" href="#" 
                                      class="dropdown-item"  >&nbsp; &nbsp; <i data-feather='edit-3'></i>
                            <span class="align-middle" title="คุณยังเคลียร์รายการไม่ครบ [You still haven't cleared all items.]"><del><spring:message code="btn.clear"/></span></del></a>
                                   
                               
                            
                                 </c:if>
                             
                           
                                       
                              
                                             
							
							                 
                             
                                            
</c:if>            
</c:if>   
                                      <c:if test="${flow.current_step == 'cleared'  && ( key.datalink_name == 'requisition_form' )}"> 
                                     
                             <h6 class="dropdown-header">
                              Flow :  <spring:message code="btn.sends"  text="การส่งเอกสาร" />
                             
                             </h6>
                    			  <div class="dropdown-divider"></div>
                    
                                                      <a href="/cleardocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/to_review_cleared" class="dropdown-item" >
                                                 
                                      <svg xmlns="http://www.w3.org/2000/svg" width="14" height="14" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-send me-1"><line x1="22" y1="2" x2="11" y2="13"></line><polygon points="22 2 15 22 11 13 2 9 22 2"></polygon></svg>           
                                                   <spring:message code="btn.send"  text="ส่งเอกสาร" /> [<spring:message code="btn.cleared"  text="ยืนยันเเล้ว" />]
                                                      </a> 
                                       </c:if>
                              
                              
                            
                      </div>
                    </div>
                                       
                      </c:if>   
                </div>
            
                <br>   
                       
                                                
                                      
                                                <h6 class="file-manager-title my-2">Info</h6>
                                                <ul class="list-unstyled" style="border:1px solid #eee;max-height:650px;padding:5px">
                                                   
                                                       <c:if test="${flow.remarks_rejected !=  null  and flow.current_step != 'published' }">  
                                                  <div class="alert-danger" ><p>[เอกสารนี้เคยถูก rejected] ${flow.remarks_rejected} </p></div>  
                                                  	</c:if> 
                                                  	
                                                  	
                                                  	   <li class="d-flex justify-content-between align-items-center">
	                                                        <p>ผู้สร้าง / รับผิดชอบ</p>
	                                                        <p class="fw-bold">${usersed_send.user_name} [${usersed_send.group_name}]  / ${usersed_app.user_name} [${usersed_app.group_name}] </p>
	                                                    </li>
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>Status / Clear BY </p>
                                                        <p class="fw-bold">
                                                        
                                       				 <c:if test="${ flow.current_step == 'cleared'  }">  
                                                     <spring:message code="btn.toclear"  />  
                                                        </c:if> 
                                                
                                                        <c:if test="${ flow.current_step == 'draft'  }">  
                                                     <spring:message code="btn.draft"  />  
                                                        </c:if>   
                                                   <c:if test="${ flow.current_step == 'reviewed'  }">  
                                                     <spring:message code="btn.reviewed"  text="รอตรวจ" />  
                                                        </c:if>
                                              
                                                               <c:if test="${ flow.current_step == 'published'  }">  
                                                     <spring:message code="btn.published"  text="เสร็จสิ้น" />
                                                        </c:if>
                                                            <c:if test="${flow.cleared == 1   }">  
                                                        [<spring:message code="btn.cleared"  text="การยืนยัน" />]
                                                        </c:if>
                                            
                                         
                                                        
                                                            <c:if test="${edata != null and ( key.datalink_name == 'purchase_requisition' )  }">  
                                                            / purchase_order
                                                            </c:if>
                                                          <c:if test="${edata != null and ( key.datalink_name == 'purchase_order' ) }">  
                                                            / goods_receipt
                                                            </c:if>
                                                
                                                        </p>
                                                    </li>
                                              
                                              
                                          
                                              
                                                         <c:if test="${edata.data_status != ''}">        
                                                  
                                           		   <li class="d-flex justify-content-between align-items-center">
	                                                        <p>ประเมินผล</p>
	                                                        <p class="fw-bold">${edata.data_status}</p>
	                                                    </li>
                                                  	</c:if> 
                                                  	
                                                      <li class="d-flex justify-content-between align-items-center">
                                                        <p>รายการ</p>
                                                        <p class="fw-bold">${edata.title}</p>
                                                    </li>
                                             
                                             
                                                <c:if test="${edata != null and ( key.datalink_name == 'inventory_registration' ) }">  
                                                		<c:set var="actiontype" value="Register" />
                                              		  <%@ include file="inventory_registration.jspf" %>  
                                                </c:if>
                                                   
                                                   
                                              <c:if test="${edata != null and ( key.datalink_name == 'direct_goods_receipt' ) }">  
                                                		<c:set var="actiontype" value="Register" />
                                              		  <%@ include file="direct_goods_receipt.jspf" %>  
                                               </c:if>
                                             
                                               <c:if test="${edata != null and ( key.datalink_name == 'stock_requisition' ) }">  
                                                		<c:set var="actiontype" value="Register" />
                                              		  <%@ include file="stock_requisition.jspf" %>  
                                               </c:if>
                                                     
                                             
                                                     
                                                   
                                                   
                                                   <c:if test="${edata.data_title1 != ''}"> 
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>${edata.data_title1}</p>
                                                        <p class="fw-bold">${edata.data_value1}</p>
                                                    </li>
                                                    </c:if>
                                                    
                                                    <c:if test="${edata.data_title2 != ''}">   
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title2}</p>
	                                                        <p class="fw-bold">${edata.data_value2}</p>
	                                                    </li>
                                                     </c:if>
                                                      <c:if test="${edata.data_title3 != ''}">     
		                                                    <li class="d-flex justify-content-between align-items-center">
		                                              			<p>${edata.data_title3}</p>
		                                                        <p class="fw-bold">${edata.data_value3}</p>
		                                                    </li>
                                                      </c:if>
                                                    
                                                    
                                                  <c:if test="${edata.data_title4 != ''}">      
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                     <p>${edata.data_title4}</p>
	                                                        <p class="fw-bold">${edata.data_value4}</p>
	                                                    </li>
                                                   </c:if> 
                                                    
                                                    <c:if test="${edata.data_title5 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title5}</p>
	                                                        <p class="fw-bold">${edata.data_value5}</p>
	                                                    </li>
 													</c:if> 
                                                  
                                                  
                                                      <c:if test="${edata.data_title6 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title6}</p>
	                                                        <p class="fw-bold">${edata.data_value6}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title7 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title7}</p>
	                                                        <p class="fw-bold">${edata.data_value7}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title8 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title8}</p>
	                                                        <p class="fw-bold">${edata.data_value8}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title9 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title9}</p>
	                                                        <p class="fw-bold">${edata.data_value9}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title10 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title10}</p>
	                                                        <p class="fw-bold">${edata.data_value10}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title11 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title11}</p>
	                                                        <p class="fw-bold">${edata.data_value11}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title12 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title12}</p>
	                                                        <p class="fw-bold">${edata.data_value12}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title13 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title13}</p>
	                                                        <p class="fw-bold">${edata.data_value13}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title14 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title14}</p>
	                                                        <p class="fw-bold">${edata.data_value14}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title15 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title15}</p>
	                                                        <p class="fw-bold">${edata.data_value15}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title16 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title16}</p>
	                                                        <p class="fw-bold">${edata.data_value16}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title17 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title17}</p>
	                                                        <p class="fw-bold">${edata.data_value17}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title18 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title18}</p>
	                                                        <p class="fw-bold">${edata.data_value18}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title19 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title19}</p>
	                                                        <p class="fw-bold">${edata.data_value19}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_title20 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_title20}</p>
	                                                        <p class="fw-bold">${edata.data_value20}</p>
	                                                    </li>
 													</c:if> 
                                                
                                                </ul>
                                                
                            
                               <c:if test="${key.datalink_name == 'purchase_requisition' }">  
                        			  <c:set var="type" value="pr" />
                               	</c:if>   
                               	<c:if test="${key.datalink_name == 'purchase_order' }">  
                     			     <c:set var="type" value="po" />
                               	</c:if>
                               <c:if test="${key.datalink_name == 'goods_receipt' }">  
                         			 <c:set var="type" value="gr" />
                               	</c:if>  
                                   <c:if test="${key.datalink_name == 'petty_cash_book' }">  
                         			 <c:set var="type" value="vc" />
                               	</c:if>   	
                               	   <c:if test="${key.datalink_name == 'requisition_form' }">  
                         			 <c:set var="type" value="r" />
                               	</c:if>    
                               		   <c:if test="${key.datalink_name == 'inventory_registration' }">  
                         			 <c:set var="type" value="ir" />
                               	</c:if>                    
                                                
                                       <c:if test="${key.datalink_name == 'direct_goods_receipt' }">  
                         			 <c:set var="type" value="dgr" />
                               	</c:if>        
                               	
                               	
                               <c:if test="${key.datalink_name == 'stock_requisition' }">  
                         			 <c:set var="type" value="sr" />
                               	</c:if>        
                               	
                               	
                               	              
                                                     
                                                  <c:if test="${ 
                                                   ( flow.current_step == 'reviewed'  )  
                                                  && Member_id == flow.sender
                                                   &&  key.datalink_name != 'goods_receipt' 
                                                   && flow.cleared != '1' 
                                               
                                                   }">     
                                                     <c:set var="current_step" value="un_review" />
                                                   
                                                 <a href="/unsenddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}" class="btn btn-sm btn-warning" style="float:right">UnSend</a> 
                                     
                                                </c:if>
                                                
                                                 
                                                 
      					 						<c:if test="${ (flow.current_step == 'cleared')  && Member_id == flow.sender &&  ( key.datalink_name != 'goods_receipt' )  
      					 						
      					 						}">     
                                                     <c:set var="current_step" value="unpublish_cleared" />      
                                                 <a href="/unsenddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}" class="btn btn-sm btn-warning" style="float:right">UnPublish</a> 
                                     
       											 </c:if>
                                                 
                                                
                                                
                                                    <c:if test="${flow.current_step == 'published' && flow.cleared == '0'  && Member_id == flow.sender }">     
                                                     <c:set var="current_step" value="unpublish" />
                                                   
                                                 <a href="/unsenddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}" class="btn btn-sm btn-warning" style="float:right">UnPublish</a> 
                                     
                                                </c:if>
                                                
                                                    <c:if test="${flow.current_step == 'published' && flow.cleared == '1' &&  Member_id == flow.sender && actiontype != 'Register' }">     
                                               <c:set var="current_step" value="unclear" />
                                               
                                                 <a href="/unsenddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}" class="btn btn-sm btn-warning" style="float:right">UnClear</a>
                                                  
                                                </c:if>
                                                
                                                       <c:if test="${flow.current_step == 'partial' && flow.cleared == '1' &&  Member_id == flow.sender }">     
                                               <c:set var="current_step" value="unpartial" />
                                                 <a href="/unsenddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}" class="btn btn-sm btn-warning" style="float:right">UnPartial</a>
                                                  
                                                </c:if>
                                                
                                                       <c:if test="${flow.current_step == 'canceled' && flow.cleared == '1' &&  Member_id == flow.sender  }">     
                                               <c:set var="current_step" value="uncancel" />
                                                 <a href="/unsenddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}" class="btn btn-sm btn-warning" style="float:right">UnCancel</a>
                                                  
                                                </c:if>
                                                
                                                
                                                    <c:if test="${flow.current_step == 'reviewed' && flow.cleared == '1' &&  Member_id == flow.sender && actiontype != 'Register' }">     
                                               <c:set var="current_step" value="to_cleared" />
                                         
                                                 <a href="/unsenddocument-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}/${current_step}" class="btn btn-sm btn-warning" style="float:right">UnClear</a>
                                                  
                                                </c:if>
                              
                              
                                   
                                           
                                                
                                                
                                               
                                               
<c:if test="${ (flow.current_step == 'draft' or flow.current_step == 'rejected' ) && Member_id == flow.receiver }">              
	  <a href="/editdocumentapp-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}" class="btn btn-sm btn-light"  >
	                                          <spring:message code="btn.view"/>   </a>  
</c:if>         
      <c:if test="${flow.current_step == 'cleared'  && ( key.datalink_name != 'purchase_requisition' ||  key.datalink_name != 'purchase_order')   && Member_id == flow.receiver }"> 
      	  <a href="/editdocumentapp-filemanager/${edata.data_id}/${upload_pn.upload_pn_id}" class="btn btn-sm btn-light"  >
			                                     <spring:message code="btn.view"/>  
			                                        </a>  
  </c:if>                                             
                                               
                                               
                                               
                                               
                                               
                                                
                                            </div>
                                           
                                             <div class="tab-pane fade" id="details2-tab" role="tabpanel" aria-labelledby="details-tab">
                                             
                                                <hr class="my-2">
                                                <h6 class="file-manager-title my-2">Info       


                               <c:if test="${key.datalink_name == 'purchase_requisition' }">  
                        			  <c:set var="type" value="pr" />
                               	</c:if>   
                               	<c:if test="${key.datalink_name == 'purchase_order' }">  
                     			     <c:set var="type" value="po" />
                               	</c:if>
                               <c:if test="${key.datalink_name == 'goods_receipt' }">  
                         			 <c:set var="type" value="gr" />
                               	</c:if>  
                                   <c:if test="${key.datalink_name == 'petty_cash_book' }">  
                         			 <c:set var="type" value="vc" />
                               	</c:if>   	
                               	   <c:if test="${key.datalink_name == 'requisition_form' }">  
                         			 <c:set var="type" value="r" />
                               	</c:if> 
                               	
                               	<c:if test="${actiontype != 'Register'  }">  
											<a class="btn btn-sm btn-light" href="/docprint-filemanager?id=${edata.data_id }&&type=${type}"   target="_blank" 
												     onclick="openCenteredWindow(this.href, 'File Window', 900, 600); return false;" style="float:right">พิมพ์
											</a>
								</c:if> 
							
							
							
									
                                   </h6>
                                                
                                               
     <c:if test="${edata != null and ( key.datalink_name == 'purchase_requisition' ) }">  
                      				 <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >PR</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                       ${pr.pr_date}${pr.pr_id}
                                                      </div>
                                                     </div> 
                                           </div>  
    </c:if>         
                                        
        <c:if test="${edata != null and ( key.datalink_name == 'purchase_order' ) }">  
                      				 <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >PO</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                       ${po.po_date}${po.po_id}
                                                      </div>
                                                     </div> 
                                           </div>  
                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >ผู้รับสินค้า</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                         ${recipient.user_name}   [  ${recipient.group_name} ]
                                                     </div> 
                                           </div>  
                                             </div>    
                                            <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >ข้อมูลจัดส่ง</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                         ${shipping.shipping_name}  
                                                       <br>
                                                       ${shipping.shipping_address}
                                                      </div>
                                                     </div> 
                                           </div>  
                                            
                                           
                                          
                                           
                                           				<div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >ผู้จัดจำหน่าย</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                       ${distributor.distributor_name}
                                                          <br>
                                                                   ${distributor.distributor_address}
                                                      </div>
                                                     </div> 
                                           </div> 
                                           
                                      
               
    </c:if>                                         
                               

<c:if test="${key.datalink_name == 'inventory_registration'  }">  
<c:set var="typeshow" value="4" />
  <%@ include file="inventory_registration.jspf" %>                                           
</c:if>

<c:if test="${key.datalink_name == 'direct_goods_receipt'  }">  
<c:set var="typeshow" value="4" />
  <%@ include file="direct_goods_receipt.jspf" %>                                           
</c:if>    
         
<c:if test="${key.datalink_name == 'stock_requisition'  }">  
<c:set var="typeshow" value="4" />
  <%@ include file="stock_requisition.jspf" %>                                           
</c:if>        
           
 <c:if test="${key.datalink_name == 'petty_cash_book'  }">  
<c:set var="typeshow" value="4" />
  <%@ include file="petty_cash_book.jspf" %>                                           
</c:if>                     
  <c:if test="${key.datalink_name == 'requisition_form'  }">  
<c:set var="typeshow" value="4" />
  <%@ include file="requisition_form.jspf" %>       
                                 
</c:if>               
                 
                 
                 
                 
                   <c:if test="${key.datalink_name == 'purchase_requisition'
                    || key.datalink_name == 'purchase_order' 
                   || key.datalink_name == 'goods_receipt'   }">  
                        		    
                                                  <c:if test="${items.size() > 0}">                                      
                             <table   class="tabledata" style="height:auto" >
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead">#</th>
                                            <th class="form-label fixhead"> <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" /></th>
                                             <th class="form-label fixhead"><spring:message code="item.quantity" text="จำนวน" /></th>
                                                      <th class="form-label fixhead"><spring:message code="item.unit" text="หน่วยนับ" /></th>
                                               
                                               
                                                      
                                      <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'  || key.datalink_name == 'purchase_requisition') }">  
                                              <th class="form-label fixhead"> <spring:message code="item.estimated" text="ราคาประมาณการ" /></th>
                                         </c:if>
                                              <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'  || key.datalink_name == 'purchase_requisition'  ) }">  
                                                <th class="form-label fixhead"><spring:message code="item.cost" text="ราคา" /></th>
                                              
                                              </c:if>
                                              
                                              
                                                  <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'  || key.datalink_name == 'purchase_requisition') }">  
                                                 <th class="form-label fixhead"><spring:message code="item.total" text="มูลค่ารวม" /></th>
                                           </c:if>
                                           <c:if test="${edata != null and ( key.datalink_name == 'purchase_order' || key.datalink_name == 'goods_receipt') }">  
                                        		  <th class="form-label fixhead">Refer</th>
                                        		    <th class="form-label fixhead">ยอดรับ</th>
                                             </c:if>
                                             
                                             
                                                 
                                             
                                             
                                             
                                          <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'  || key.datalink_name == 'purchase_requisition') }">  
                                           <th class="form-label fixhead">Clear</th>
                                                   </c:if>
                                           
                                            </tr>
                                           
                                           
                                        </tr>
                                    </thead>
                                    <tbody>
                          
                          
                          <c:set var="Clear_status" value="0" />
                                        <c:forEach items="${items }" var="row" varStatus="count">
				                                      <tr>
				                                            <td>${count.count }</td >
				                                             <td>${row.description }</td >
				                                             <td>${row.qty }</td >
				                                            <td>${row.unit }</td >
				                                            
				                                               <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'  || key.datalink_name == 'purchase_requisition') }">  
				                                            <td>${row.estimated }</td>
				                                             </c:if> 
				                                            
				                                            
							                                <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'  || key.datalink_name == 'purchase_requisition' ) }">  
							                                 <td>${row.cost }</td >
							                                 </c:if>        
				                                         
				                                         
				                                        <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'  || key.datalink_name == 'purchase_requisition') }">            
								                                            <td>
								                                              <c:if test="${edata != null and ( key.datalink_name == 'purchase_requisition' ) }">  
								                                            ${row.estimated * row.qty  }
								                                                <c:set var="Clear_status" value=" ${Clear_status+ ( row.estimated * row.qty)  }" />
								                                            </c:if>
								                                              <c:if test="${edata != null and ( key.datalink_name == 'purchase_order'   ) }">  
								                                            ${row.cost * row.qty  }
								                                                <c:set var="Clear_status" value="  ${Clear_status+ ( row.cost * row.qty)  }" />
								                                            </c:if>
								                                            </td>
								                                            
								                                           
				                                     </c:if>     
				                                            
				                            
				                                <c:if test="${edata != null and ( key.datalink_name == 'purchase_order' ) }">  
										                                             <td> <c:if test="${row.pr_id > 0}">     
										                                                 PR${row.pr_date }${row.pr_id  }
										                                               </c:if>  
										                                                </td>
										                                                
										                                                
										                      <c:if test="${row.pr_id == 0}">  
										                    
										                     </c:if> 
				                                 </c:if>
				                                   
				                                    
				                                          
				                                            
				                                        
				                                                <c:if test="${edata != null and ( key.datalink_name == 'purchase_requisition' || key.datalink_name == 'goods_receipt')}">  
										                                              <td>  <c:if test="${row.po_id > 0}">     
										                                                 PO${row.po_date }${row.po_id  }
										                                               </c:if>    </td>
										                                               
										                     <c:if test="${row.po_id == 0}">  
										            
										                     </c:if> 
										                                             
				                                 </c:if>    
				                                           
				                                               <c:if test="${edata != null and key.datalink_name == 'goods_receipt' }">  
										                                             <td>   
										                                               ${ row.received  }
										                                               
				
										                                                </td>
										                
										                        </c:if>    
										                     
										                         <c:if test="${edata != null and ( key.datalink_name == 'purchase_order' ) }">  
										                                             <td>   
										                                        ${row.amount_paid  }
										                                                </td>
										             
										                     </c:if> 
										                     
				                                 
				                                              
				                                         
				                                         
				                                         
				                                         
				                                        </tr>
                                        </c:forEach>
                                        
                                    </tbody>
                                    </table>  
                                    <br>
                                  <c:if test="${key.datalink_name != 'goods_receipt'}">  
                                  <h3 style="float:right"> รวม  :  ${Clear_status }</h3>
                       </c:if>  
                               	</c:if> 
                   		</c:if>             	
                               	
                               	
                       
                               	
                               	
                               	
                               	     <c:if test="${key.datalink_name == 'petty_cash_book' }">  
                        		    
                                                  <c:if test="${items.size() > 0}">                                      
                           <table  class="table">
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead" style="padding:5px;font-size:13px">#</th>
                                            <th class="form-label fixhead" style="padding:5px;font-size:13px"> <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" /></th>
                                             <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.amount_received" text="ยอดรับเงิน" /></th>
                                                      <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.amount_paid" text="ยอดจ่ายเงิน" /></th>
                                              <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.total" text="คงเหลือ" /></th>
                                                <th class="form-label fixhead" style="padding:5px;font-size:13px"><spring:message code="item.requester" text="ผู้เขอ" /></th>
                                    

                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:set var="Clear_status" value="0" />
                                         <c:set var="Clear_status2" value="0" />
                                        <c:forEach items="${items }" var="row" varStatus="count">
				                                      <tr>
				                                            <td style="padding:5px;font-size:13px">${count.count }</td >
				                                             <td style="padding:5px;font-size:13px">${row.description }</td >
				                                             <td style="padding:5px;font-size:13px">${row.amount_received }</td >
				                                            <td style="padding:5px;font-size:13px">${row.amount_paid }</td >
				                                            
				                                                  <c:set var="Clear_status" value="${Clear_status + row.amount_received  }" />
				           										  <c:set var="Clear_status2" value="${Clear_status2 + row.amount_paid  }" />
				           										  
				                                        	<td style="padding:5px;font-size:13px"> ${Clear_status - Clear_status2  }</td >
				                                         	<td style="padding:5px;font-size:13px"> ${row.requester }</td >
				                                                  
				                                   
				                                        </tr>
                                        </c:forEach>
                                              </tbody>
                                              
                                                <tfoot> 
                                        				 <tr>
				                                            <td style="padding:5px;font-size:13px"></td >
				                                             <td style="padding:5px;font-size:13px"><b>TOTAL</b></td >
				                                             <td style="padding:5px;font-size:13px"><b>${Clear_status}</b></td >
				                                            <td style="padding:5px;font-size:13px"><b>${Clear_status2}</b></td >
				                                        	<td style="padding:5px;font-size:13px"> <b>${Clear_status - Clear_status2 }</b></td >
				                                             <td style="padding:5px;font-size:13px"></td>
				                                          
				                                        </tr>
                                                 </tfoot>
                              
                                    </table>  
                                           
                                    <br>
                      
                               	</c:if> 
                   		</c:if>             	
                               	
                               	
                               	
                               	
                                                
                                                
                                                <ul class="list-unstyled">
                                                     
                                                   
                                                   
                                                            <c:if test="${edata.data_titles1 != ''}"> 
                                                    <li class="d-flex justify-content-between align-items-center">
                                                        <p>${edata.data_titles1}</p>
                                                        <p class="fw-bold">${edata.data_values1}</p>
                                                    </li>
                                                    </c:if>
                                                    
                                                  
	                                                     
                                                    
                                                    
                                                    <c:if test="${edata.data_titles2 != ''}">   
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles2}</p>
	                                                        <p class="fw-bold">${edata.data_values2}</p>
	                                                    </li>
                                                     </c:if>
                                                      <c:if test="${edata.data_titles3 != ''}">     
		                                                    <li class="d-flex justify-content-between align-items-center">
		                                              			<p>${edata.data_titles3}</p>
		                                                        <p class="fw-bold">${edata.data_values3}</p>
		                                                    </li>
                                                      </c:if>
                                                    
                                                    
                                                  <c:if test="${edata.data_titles4 != ''}">      
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                     <p>${edata.data_titles4}</p>
	                                                        <p class="fw-bold">${edata.data_values4}</p>
	                                                    </li>
                                                   </c:if> 
                                                    
                                                    <c:if test="${edata.data_titles5 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles5}</p>
	                                                        <p class="fw-bold">${edata.data_values5}</p>
	                                                    </li>
 													</c:if> 
                                                  
                                                  
                                                      <c:if test="${edata.data_titles6 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles6}</p>
	                                                        <p class="fw-bold">${edata.data_values6}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles7 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles7}</p>
	                                                        <p class="fw-bold">${edata.data_values7}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles8 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles8}</p>
	                                                        <p class="fw-bold">${edata.data_values8}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles9 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles9}</p>
	                                                        <p class="fw-bold">${edata.data_values9}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles10 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles10}</p>
	                                                        <p class="fw-bold">${edata.data_values10}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles11 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles11}</p>
	                                                        <p class="fw-bold">${edata.data_values11}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles12 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles12}</p>
	                                                        <p class="fw-bold">${edata.data_values12}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles13 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles13}</p>
	                                                        <p class="fw-bold">${edata.data_values13}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles14 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles14}</p>
	                                                        <p class="fw-bold">${edata.data_values14}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles15 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles15}</p>
	                                                        <p class="fw-bold">${edata.data_values15}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles16 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles16}</p>
	                                                        <p class="fw-bold">${edata.data_values16}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles17 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles17}</p>
	                                                        <p class="fw-bold">${edata.data_values17}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles18 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles18}</p>
	                                                        <p class="fw-bold">${edata.data_values18}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles19 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles19}</p>
	                                                        <p class="fw-bold">${edata.data_values19}</p>
	                                                    </li>
 													</c:if> 
 													
 													    <c:if test="${edata.data_titles20 != ''}">        
	                                                    <li class="d-flex justify-content-between align-items-center">
	                                                        <p>${edata.data_titles20}</p>
	                                                        <p class="fw-bold">${edata.data_values20}</p>
	                                                    </li>
 													</c:if> 
                                                  
                                                      
	                                                
                                                  
                                                  
                                                  
                                                </ul>
                   
                               	
                               	
                               	                 
                                            </div>
                                           
                                           
                                            <div class="tab-pane fade" id="activity-tab" role="tabpanel" aria-labelledby="activity-tab">

                     ไฟล์เเนบข้อมูลหลัก<br>
						-----------------------------------------------------------<br>
                                          
                       
                         <c:forEach  items="${fileall}" var="row" begin="0" end="5" varStatus="count">
                         
								<a href="/render-filemanager?file=${edata.data_filepath }&&file_edata=${row.file_id }" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600);  return false;">
									       
							
									       <c:if test="${count.count == 1}"> [${upload_pn["upload_pn_name"] }]  ${row.file_name_original} </c:if>
									          <c:if test="${count.count == 2}"> [${upload_pn["upload_pn_name2"] }]  ${row.file_name_original} </c:if>
									             <c:if test="${count.count == 3}"> [${upload_pn["upload_pn_name3"] }]  ${row.file_name_original} </c:if>
									                <c:if test="${count.count == 4}"> [${upload_pn["upload_pn_name4"] }]  ${row.file_name_original} </c:if>
									                   <c:if test="${count.count == 5}"> [${upload_pn["upload_pn_name5"] }]  ${row.file_name_original} </c:if>
									    
								</a>
								<br>
                                   </c:forEach>            
                                   
                                  
                               <div style="display:none">    
                                   <br>  <br>  
                          ไฟล์เเนบข้อมูลอิง<br>                    
						-----------------------------------------------------------<br>
						      <c:set var="namesArray" value="${fn:split(edata.data_filetypes, ',')}" />
                                              <c:forEach var="nameItem" items="${namesArray}" begin="0" end="5" varStatus="count">
								<a href="/render-filemanager?file=${edata.data_filepaths }&&count=${count.count}" target="_blank" 
									     onclick="openCenteredWindow(this.href, 'File Window', 900, 600);  return false;">
									      -  ${nameItem}
								</a>
								<br>
                                   </c:forEach>     
                                   
                                   </div>
                                   
                      
                                                </div>
                                                
                                            
                                                    <div class="tab-pane fade" id="details4-tab" role="tabpanel" aria-labelledby="details-tab">
                                             
                                                <hr class="my-2">
                                    
                                 
                                    <form:form method="POST"  id="cmform" name="cmform">
                                     
                                                 <input type="hidden" name="cm_idrefer" value="${edata.data_id}">
                                            	  <input type="hidden" name="type" value="edata">
                                            	  
                                            	  
                                        <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                            <div class="action-tags">
                                                <div class="mb-1">
                                                    <label for="todoTitleAdd" class="form-label">กรอกข้อความข้อคุณ</label>
                                                    <textarea type="text" name="cm_title" id="todoTitleAdd" name="todoTitleAdd" class="new-todo-item-title form-control" ></textarea>
                                                </div>
                                          
                                             
                                          
                                            </div>
                                            <div class="my-1">
                                                <button type="button" onclick='postdata("/addcm-filemanager?${_csrf.parameterName}=${_csrf.token}","cmform","cmdiv")' class="btn btn-primary add-todo-item me-1 waves-effect waves-float waves-light">Add</button>
                                                <button type="button"     class="btn btn-outline-secondary add-todo-item waves-effect" data-bs-dismiss="modal">
                                                    Cancel
                                                </button>
                                               
                                             
                                            </div>
                                        </div>
                                    </form:form>
                                     
                                     
                                      <div class="modal-body flex-grow-1 pb-sm-0 pb-3">
                                    <div class="tab-content" id="myTabContent">
                                      
                                            <div class="tab-pane fade active show" id="activity-tab" role="tabpanel" aria-labelledby="activity-tab">
                                                <h6 class="file-manager-title my-2">วันนี้</h6>
                                                
                                   <div id="cmdiv">
                                                
                                    <c:forEach items="${listcms2 }" var="row" varStatus="count">
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
                                                       <button type="button" onclick="return getdata('/delcm-filemanager/${row.cm_id}/${row.type}');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    Delete
                                                </button>
                                          </div>


                               </c:forEach>
                                 </div>
                                              
                                              
                                                <h6 class="file-manager-title mt-3 mb-2">ความคิดเห็นทั้งหมด</h6>
                                            <c:forEach items="${listcms1 }" var="row" varStatus="count">
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
                                                       <button type="button"   onclick="return getdata('/delcm-filemanager/${row.cm_id}');"  class="btn btn-sm btn-outline-danger update-btn waves-effect ">
                                                    Delete
                                                </button>
                                          </div>


                               </c:forEach>
                                       
                                             
                                            </div>
                                        </div>
                                            </div>
                              
                                            
                                             </div>
                                             
                                             
                                            </div>
                                        </div>
                                    </div>
         <script>
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
    </script>                              
   <script>
function openCenteredWindow(url, name, width, height) {
	
	

    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>                                 
                                    