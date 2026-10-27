<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    



   	<h3>สำหรับผู้เคลียร์</h3>
                      		<hr>
                      		
                      		             <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >วันที่เคลียร์</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                          <input type="date" name="date_clear" class="form-control" value="${edatas.date_clear}">
                                                      </div>
                                                     </div> 
                                           </div> 
                      		
                                    
                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >รายละเอียดการเคลียร์</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                <textarea name="details_clearing" class="form-control">${edatas.details_clearing  }</textarea>
                                                      </div>
                                                     </div> 
                                           </div>  
                                           
                                           
                                                 <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >ยอดใช้ไป</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                <input type="number" step="0.01" min="0" class="form-control" name="spent"  value="${edatas.spent }">
                                                      </div>
                                                     </div> 
                                           </div> 
                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >ยอดเบิกเพิ่ม</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                <input type="number" step="0.01" min="0" class="form-control" name="additional"  value="${edatas.additional }">
                                                      </div>
                                                     </div> 
                                           </div> 
                                              <div class="col-12">
                                                <div class="mb-1 row">
                                                
                                                
                                                    <div class="col-sm-3">
                                                        <label class="col-form-label" for="fname-icon" >ยอดคืน</label>
                                                    </div>
 
                                                     <div class="col-sm-9"> 
                                                <input type="number" step="0.01" min="0" class="form-control" name="returned"  value="${edatas.returned }">
                                                      </div>
                                                     </div> 
                                           </div>        