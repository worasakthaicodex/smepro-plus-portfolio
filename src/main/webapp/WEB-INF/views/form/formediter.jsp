<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../common/mainhead.jspf" %>    


<style>
		select {
		  -webkit-appearance: menulist-button;
		  color: black;
		}
		
		select:invalid {
		  color: green;
		}
</style>

      
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
       
                    <div class="row">
                        <div class="col-12">
                            <div class="card">
                                <div class="card-header">
                                    <h4 class="card-title">Form</h4>
                                </div>
                                <div class="card-body">
                                
                                    <form:form method="POST" modelAttribute="form" action="/editformsave-condition">
 <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">E-Form</label>
                                    <select  class="form-select "  name="form_condition">
                                                 
                                                    <c:forEach items="${allconditions}" var="rows" varStatus="count">  
                                                    
                                                    
                                           
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${rows.condition_id == forms.form_condition}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.condition_id}" ${check }>${rows.condition_name }</option>
														</c:forEach>  
                                 
                                                    </select>
</div>



  
  


     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag1</label>
                                    <select  class="form-select "  name="form_tag1">
                                                    	<option value="" >ไม่ระบุ </option>
                                                    	
                                                    	
                                                    	
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag1}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag1}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


		
                        	
													

													
                                                    </select>
</div>
  
  



  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag2</label>
                                    <select  class="form-select "  name="form_tag2">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag2}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag2}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
				

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag3</label>
                                    <select  class="form-select "  name="form_tag3">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag3}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag3}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
								

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag4</label>
                                    <select  class="form-select "  name="form_tag4">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag4}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag4}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
									

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag5</label>
                                    <select  class="form-select "  name="form_tag5">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag5}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag5}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag6</label>
                                    <select  class="form-select "  name="form_tag6">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag6}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag6}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
													
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
		

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag7</label>
                                    <select  class="form-select "  name="form_tag7">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag7}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag7}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag8</label>
                                    <select  class="form-select "  name="form_tag8">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag8}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag8}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
								

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag9</label>
                                    <select  class="form-select "  name="form_tag9">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag9}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag9}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
						

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag10</label>
                                    <select  class="form-select "  name="form_tag10">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag10}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag10}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
						

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag11</label>
                                    <select  class="form-select "  name="form_tag11">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag11}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag11}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
			

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag12</label>
                                    <select  class="form-select "  name="form_tag12">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag12}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag12}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
													
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
		

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag13</label>
                                    <select  class="form-select "  name="form_tag13">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag13}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag13}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
													
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
			

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag14</label>
                                    <select  class="form-select "  name="form_tag14">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag14}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag14}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag15</label>
                                    <select  class="form-select "  name="form_tag15">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag15}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag15}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
			

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag16</label>
                                    <select  class="form-select "  name="form_tag16">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag16}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag16}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
													
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
	

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag17</label>
                                    <select  class="form-select "  name="form_tag17">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag17}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag17}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
		<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
		

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag18</label>
                                    <select  class="form-select "  name="form_tag18">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag18}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag18}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
	

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag19</label>
                                    <select  class="form-select "  name="form_tag19">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag19}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag19}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
		<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag20</label>
                                    <select  class="form-select "  name="form_tag20">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag20}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag20}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag21</label>
                                    <select  class="form-select "  name="form_tag21">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag21}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag21}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
		<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
			

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag22</label>
                                    <select  class="form-select "  name="form_tag22">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag22}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag22}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
		<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag23</label>
                                    <select  class="form-select "  name="form_tag23">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag23}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag23}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
		<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag24</label>
                                    <select  class="form-select "  name="form_tag24">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag24}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag24}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
													
			<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag25</label>
                                    <select  class="form-select "  name="form_tag25">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag25}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag25}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
		<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag26</label>
                                    <select  class="form-select "  name="form_tag26">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag26}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag26}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
													
			<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag27</label>
                                    <select  class="form-select "  name="form_tag27">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag27}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag27}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
			<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag28</label>
                                    <select  class="form-select "  name="form_tag28">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag28}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag28}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
				<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						
      	
													

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag29</label>
                                    <select  class="form-select "  name="form_tag29">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag29}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag29}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag30</label>
                                    <select  class="form-select "  name="form_tag30">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag30}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag30}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
			<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag31</label>
                                    <select  class="form-select "  name="form_tag31">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag31}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag31}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
		<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag32</label>
                                    <select  class="form-select "  name="form_tag32">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag32}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag32}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag33</label>
                                    <select  class="form-select "  name="form_tag33">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag33}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag33}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
			<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						

 	
                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag34</label>
                                    <select  class="form-select "  name="form_tag34">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag34}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag34}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
				<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag35</label>
                                    <select  class="form-select "  name="form_tag35">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag35}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag35}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
				<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag36</label>
                                    <select  class="form-select "  name="form_tag36">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag36}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag36}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
					<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag37</label>
                                    <select  class="form-select "  name="form_tag37">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag37}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag37}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
								<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						

	
                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag38</label>
                                    <select  class="form-select "  name="form_tag38">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag38}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag38}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
	<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag39</label>
                                    <select  class="form-select "  name="form_tag39">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag39}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag39}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
														
							<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						

                                                    </select>
</div>
  
  
  
  
     <div class="mb-1" >
                                                    <label for="todoTitleAdd" class="form-label">#Tag40</label>
                                    <select  class="form-select"  name="form_tag40">
                                                    	<option value="" >ไม่ระบุ</option>
                                                    <c:forEach items="${eformfields}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag40}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
                                              <c:forEach items="${eformfields2}" var="rows" varStatus="count">  
                                                    
                                                    
                                                        <c:set var="listcheck" value="${forms.form_tag40}" />
                                                        <c:set var="listrespon" value="${rows.field_label}" />    
                                                           
                                                        <c:set var="check" value="" />
					                                        <c:if test='${fn:contains(listcheck,listrespon)}'>
					                                       <c:set var="check" value="selected" />
					                                     </c:if>  
					                                     
                                                    
                                                    
                                                                <option value="${rows.field_label}" ${check }>${rows.field_label }</option>
														</c:forEach>  
					<option value="ผู้เพิ่ม">ผู้เพิ่ม</option>
<option value="ผู้อ้างอิง">ผู้อ้างอิง</option>
<option value="ผู้ตรวจ">ผู้ตรวจ</option>
<option value="วันที่เพิ่ม">วันที่เพิ่ม</option>
<option value="วันที่ตรวจ">วันที่ตรวจ</option>
<option value="ผู้เพิ่ม(ข้อมูลที่ส่งมา)">ผู้เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="ผู้อ้างอิง(ข้อมูลที่ส่งมา)">ผู้อ้างอิง(ข้อมูลที่ส่งมา)</option>
<option value="ผู้ตรวจ(ข้อมูลที่ส่งมา)">ผู้ตรวจ(ข้อมูลที่ส่งมา)</option>
<option value="วันที่เพิ่ม(ข้อมูลที่ส่งมา)">วันที่เพิ่ม(ข้อมูลที่ส่งมา)</option>
<option value="วันที่ตรวจ(ข้อมูลที่ส่งมา)">วันที่ตรวจ(ข้อมูลที่ส่งมา)</option>


 <c:if test='${check == ""}'>														
<option value="${listcheck}" selected>${listcheck}</option>													
</c:if>						


                                                    </select>
</div>
  
  

  
  
  
  
  
  
  
  
  
  
  
  
  
  
  <input type="hidden" name="form_id"  value="${forms.form_id}" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
  <input type="submit" class="btn btn-success" value="save" >

</form:form >
                      

 </div>
 </div>
  </div>
   </div>
    </div>
    </div>
     

               