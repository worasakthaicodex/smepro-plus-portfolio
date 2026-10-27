<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    


    															<label> <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="text" name="description" value="${item.description }"  class="form-control">
                                                                </div>

                                                                <label><spring:message code="item.amount_received" text="ยอดรับเงิน" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="number"  name="amount_received" value="${item.amount_received }"   class="form-control">
                                                                </div>
                                                                   <label><spring:message code="item.amount_paid" text="ยอดจ่ายเงิน" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="text"  name="amount_paid"   value="${item.amount_paid }"  class="form-control">
                                                                </div>
                                                                
                                                               <label><spring:message code="item.requester" text="ผู้ขอ" />: </label>
								 					<select name="requester" class="form-select select2">
											            <c:forEach items="${users }"   var="row" varStatus="count">
											                   <option value="${row.user_name }" <c:if test="${item.requester == row.user_name}">selected</c:if>>${row.user_name }</option>
											           </c:forEach>
										            </select>

                                                         		<input type="hidden"  name="vc" value="vc" class="form-control">
 																 <input type="hidden"  name="cost" value="${item.cost }" class="form-control">
 																 
 																 
 																   <input type="hidden"  name="pr_id" value="${item.pr_id }" class="form-control">
 																  <input type="hidden"  name="pr_date" value="${item.pr_date }" class="form-control">
 																<input type="hidden"  name="po_id" value="${item.pr_id }" class="form-control">
 																  <input type="hidden"  name="po_date" value="${item.po_date }" class="form-control">
 																
 																
 																<input type="hidden"  name="gr_id" value="${item.gr_id }" class="form-control">
 																  <input type="hidden"  name="gr_date" value="${item.gr_date }" class="form-control">
 																  
 																  
 																  
 																    <input type="hidden"  name="id" value="${item.id }" class="form-control">
 																   <input type="hidden"  name="clear_id" value="${item.id }" class="form-control">
 																   
 																   
 																       <td style="padding:5px;font-size:13px">${row.amount_received }</td >
				                                            <td style="padding:5px;font-size:13px">${row.amount_paid }</td >
 																   .
 																   
 																   
 																   
 																   
 																   