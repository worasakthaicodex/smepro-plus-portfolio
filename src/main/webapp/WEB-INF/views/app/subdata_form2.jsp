<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    


    															<label> <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="text" name="description" value="${item.description }"  class="form-control" readonly>
                                                                </div>

                                                                <label><spring:message code="item.quantity" text="จำนวน"  />: </label>
                                                                <div class="mb-1">
                                                                    <input type="number"  name="qty" value="${item.qty }"   class="form-control" readonly>
                                                                </div>
                                                                   <label><spring:message code="item.unit" text="หน่วยนับ" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="text"  name="unit"   value="${item.unit }"  class="form-control" readonly>
                                                                </div>
                                                                   <label><spring:message code="item.estimated" text="ราคาประมาณการ" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="number"  name="estimated" value="${item.estimated }" class="form-control" readonly>
 																</div>
 																
 																
 																   <label><spring:message code="item.cost" text="ราคา" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="number"  name="cost" value="${item.cost }" class="form-control" readonly>
 																</div>
 																
 																	   <label><spring:message code="item.received" text="ยอดรับ" />: </label>
                                                                <div class="mb-1">
                                                                    <input type="number"  name="received" value="${item.received }" class="form-control" >
 																</div>
 																
 																	   <input type="hidden"  name="pr_id" value="${item.pr_id }" class="form-control">
 																  <input type="hidden"  name="pr_date" value="${item.pr_date }" class="form-control">
 																<input type="hidden"  name="po_id" value="${item.pr_id }" class="form-control">
 																  <input type="hidden"  name="po_date" value="${item.po_date }" class="form-control">
 																
 																
 																<input type="hidden"  name="gr_id" value="${item.gr_id }" class="form-control">
 																  <input type="hidden"  name="gr_date" value="${item.gr_date }" class="form-control">
 																    <input type="hidden"  name="id" value="${item.id }" class="form-control">
 																   <input type="hidden"  name="clear_id" value="${item.clear_id }" class="form-control">
 																   . <input type="hidden"  name="estimated" value="${item.estimated }" class="form-control">
 																   
 																    . <input type="hidden"  name="gr" value="gr" class="form-control">
 																
 																   
 																   