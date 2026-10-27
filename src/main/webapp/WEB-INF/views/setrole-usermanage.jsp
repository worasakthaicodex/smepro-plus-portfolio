<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


<%@ include file="common/mainhead.jspf" %>    

<title>index</title>
</head>

                            <c:forEach items="${authoritiess}" var="parameter">
                            
                          
                                <c:if test="${parameter.authority.equals('READ-UE')}">    
                                  <c:set var="READUE" value="checked" />
                              
                               </c:if>  
                              <c:if test="${parameter.authority.equals('ADD-UE')}">    
                                  <c:set var="ADDUE" value="checked" />
                               </c:if>  
                                 <c:if test="${parameter.authority.equals('SET-UE')}">    
                                  <c:set var="SETUE" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('EDIT-UE')}">    
                                  <c:set var="EDITUE" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('DELETE-UE')}">    
                                  <c:set var="DELETEUE" value="checked" />
                               </c:if>  
                               
                                 
                                <c:if test="${parameter.authority.equals('READ-RUE')}">    
                                  <c:set var="READRUE" value="checked" />
                               </c:if> 
                               <c:if test="${parameter.authority.equals('EDIT-RUE')}">    
                                  <c:set var="EDITRUE" value="checked" />
                               </c:if> 
                                   <c:if test="${parameter.authority.equals('DELETE-RUE')}">    
                                  <c:set var="DELETERUE" value="checked" />
                               </c:if> 
                                   <c:if test="${parameter.authority.equals('SET-RUE')}">    
                                  <c:set var="SETRUE" value="checked" />
                               </c:if> 
                               <c:if test="${parameter.authority.equals('ADD-RUE')}">    
                                  <c:set var="ADDRUE" value="checked" />
                               </c:if>  
                                 <c:if test="${parameter.authority.equals('ADD-DRIVE')}">    
                                  <c:set var="ADDDRIVE" value="checked" />
                               </c:if> 
                                 <c:if test="${parameter.authority.equals('EDIT-DRIVE')}">    
                                  <c:set var="EDITDRIVE" value="checked" />
                               </c:if> 
                                 <c:if test="${parameter.authority.equals('DELETE-DRIVE')}">    
                                  <c:set var="DELETEDRIVE" value="checked" />
                               </c:if> 
                                       <c:if test="${parameter.authority.equals('READ-DAR')}">    
                                  <c:set var="READDAR" value="checked" />
                               </c:if> 
                                  <c:if test="${parameter.authority.equals('ADD-DAR')}">    
                                  <c:set var="ADDDAR" value="checked" />
                               </c:if> 
                                  <c:if test="${parameter.authority.equals('EDIT-DAR')}">    
                                  <c:set var="EDITDAR" value="checked" />
                               </c:if> 
                                  <c:if test="${parameter.authority.equals('CHECK-DAR')}">    
                                  <c:set var="CHECKDAR" value="checked" />
                               </c:if> 
                                  <c:if test="${parameter.authority.equals('APP-DAR')}">    
                                  <c:set var="APPDAR" value="checked" />
                               </c:if> 
                               
                               <c:if test="${parameter.authority.equals('READ-DS')}">    
                                  <c:set var="READDS" value="checked" />
                              
                               </c:if>  
                              <c:if test="${parameter.authority.equals('ADD-DS')}">    
                                  <c:set var="ADDDS" value="checked" />
                               </c:if>  
                                 <c:if test="${parameter.authority.equals('SET-DS')}">    
                                  <c:set var="SETDS" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('EDIT-DS')}">    
                                  <c:set var="EDITDS" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('DELETE-DS')}">    
                                  <c:set var="DELETEDS" value="checked" />
                               </c:if>  
                               
                               
                                   
                               <c:if test="${parameter.authority.equals('READ-OT')}">    
                                  <c:set var="READOT" value="checked" />
                              
                               </c:if>  
                              <c:if test="${parameter.authority.equals('ADD-OT')}">    
                                  <c:set var="ADDOT" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('EDIT-OT')}">    
                                  <c:set var="EDITOT" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('DELETE-OT')}">    
                                  <c:set var="DELETEOT" value="checked" />
                               </c:if>  
                               
                                    <c:if test="${parameter.authority.equals('READ-H')}">    
                                  <c:set var="READH" value="checked" />
                              
                               </c:if>  
                              <c:if test="${parameter.authority.equals('ADD-H')}">    
                                  <c:set var="ADDH" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('EDIT-H')}">    
                                  <c:set var="EDITH" value="checked" />
                               </c:if>  
                                <c:if test="${parameter.authority.equals('DELETE-H')}">    
                                  <c:set var="DELETEH" value="checked" />
                               </c:if>  
                               
                                  <c:if test="${parameter.authority.equals('READ-RST')}">    
                                  <c:set var="READRST" value="checked" />
                               </c:if>  
                               
                        
</c:forEach>
<c:set var="list" value="${authoritiess }" />

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">

<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>   


    <!-- BEGIN: Content-->
    <div class="app-content content ">
       
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
                <div class="col-md-12">
                    <div class="white_box QA_section mb_30">
                        <div class="white_box_tittle list_header">
                            <h4>${role.group_name }      <span style="float:right"> <input type="checkbox"onClick="toggle(this)" /> Check All</span></h4>
                            <div class="box_right d-flex lms_block">
                                <div class="serach_field_2">
                                    
                                </div>
                            </div>
                             <div class="add_button ms-2"  >
                           
                             <a class="btn btn-sm btn-success"  style="float:right;margin-right:5px;margin-top:5px" onclick="set(1)">Set to User</a>
                              <a class="btn btn-sm btn-success"  style="float:right;margin-right:5px;margin-top:5px" onclick="set(2)">Set to Authorities</a> 
                             <a class="btn btn-sm btn-success"  style="float:right;margin-right:5px;margin-top:5px" onclick="set(3)">Set to  Supervisor</a> 
                             <a class="btn btn-sm btn-success"  style="float:right;margin-right:5px;margin-top:5px" onclick="set(4)">Set to  DDC</a>
                              <a class="btn btn-sm btn-success"  style="float:right;margin-right:5px;margin-top:5px" onclick="set(5)">Set to  HR</a>
                             <a class="btn btn-sm btn-success"  style="float:right;margin-right:5px;margin-top:5px" onclick="set(6)">Set to Super Admin</a>
                          
                          
                          
                          
                                        </div>
                        </div>

                        <div class="QA_table">
          
 
  
                                             
   
 
                                 <form:form method="POST" modelAttribute="building" action="/setrole-usermanage">
                                 <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                 
                                 
                         
                                 <input  type="hidden" name="group_id" value="${role.id }">
             <br>  <h6 style="padding-bottom:10px;">  <spring:message code="nav.setting" text="สิทธิ์สำหรับการตั้งค่า" /> <font style="font-size:9px">
             <spring:message code="usermanage.introduce" text="***เเนะนำ Super Admin***" /></font> </h6>     
                     <table class="table">
                              <thead class="table-dark">
                                   <tr>
                                      <th scope="col" style="min-width:20px;max-width:20px"><spring:message code="label.no" text="ลำดับ" /></th>
                                      <th scope="col" style="max-width:220px;min-width:220px"><spring:message code="usermanage.permissions" text="สิทธิ์" /></th>
                                       <th scope="col"><spring:message code="usermanage.read" text="อ่าน" /></th>
                                       <th scope="col"><spring:message code="usermanage.add" text="จัดทำ" /></th>
                                       <th scope="col"><spring:message code="usermanage.edit" text="แก้ไข" /></th>
                                       <th scope="col"><spring:message code="usermanage.check" text="ตรวจสอบ" /></th>
                                       <th scope="col"><spring:message code="usermanage.app" text="อนุมัติ" /></th>
                                      
                                       <th scope="col"><spring:message code="usermanage.upload" text="อัพโหลด" /></th>
                                         <th scope="col"><spring:message code="usermanage.download" text="ดาวน์โหลด" /></th>
                                       <th scope="col"><spring:message code="usermanage.setting" text="ตั้งค่า" /></th>
                                       <th scope="col"><spring:message code="usermanage.deleteitem" text="ล้างข้อมูล" /></th>
                                    <th scope="col"><spring:message code="usermanage.delete" text="ลบ" /></th>
                                  </tr>
                              </thead>
                              <tbody>
                              
                                  
                                    <tr>
                                      <th scope="row">1</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="set.role" text="การจัดการบทบาทเเละสิทธิ์" /> </td>
                                        <td><input type="checkbox" class="form-check-input low " name="permissions" value="READ-RUE" ${READRUE }></td>
                                      <td><input type="checkbox" class="form-check-input low " name="permissions" value="ADD-RUE" ${ADDRUE }></td>
                                      <td><input type="checkbox" class="form-check-input low " name="permissions" value="EDIT-RUE" ${EDITRUE }></td>
                                    
                                      <td></td>
                                        
                                      <td></td>
                                       <td></td>
                                      <td></td>
                                      <td><input type="checkbox" class="form-check-input low " name="permissions" value="SET-RUE" ${SETRUE }></td>
                                       <td></td>
                                      <td><input type="checkbox" class="form-check-input low " name="permissions" value="DELETE-RUE"  ${DELETERUE }></td>
                                       
                                  </tr>         
                                   
                                      <tr>
                                      <th scope="row"><span></span>2</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="usermanage.manager" text="สิทธิ์ทั่วไป" /> Drive </td>
                                      <td><input type="checkbox" class="form-check-input low" name="permissions" value="ADD-DRIVE" ${ADDDRIVE }></td>
                                    <td><input type="checkbox" class="form-check-input low" name="permissions" value="EDIT-DRIVE" ${EDITDRIVE }></td>
                                      <td></td>
                                      <td></td>
                                     <td></td>
                                      <td></td>
                                    <td></td>
                                       <td></td>
                                        <td></td>
                                      <td><input type="checkbox" class="form-check-input low" name="permissions" value="DELETE-DRIVE" ${DELETEDRIVE }></td>
                                        
                                  </tr> 
                                  
                                      <tr>
                                      <th scope="row"><span></span>3</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="usermanage.rst" text="ผู้รักษาการ" /></td>
                                      <td><input type="checkbox" class="form-check-input low" name="permissions" value="READ-RST" ${READRST }></td>
                                    <td></td>
                                      <td></td>
                                      <td></td>
                                     <td></td>
                                      <td></td>
                                    <td></td>
                                       <td></td>
                                        <td></td>
                                      <td></td>
                                        
                                  </tr> 
                         
                         
                 
        
   
                           
                                  
                <tr>
                
                
                
                
               </tbody>
               </table>
               
               <br><h6>   <spring:message code="nav.settinghr" text="การตั้งค่าระบบ HR" /> </h6>  
                   <table class="table">
                              <thead class="table-dark">
                                   <tr>
                                      <th scope="col" style="min-width:20px;max-width:20px"><spring:message code="label.no" text="ลำดับ" /></th>
                                      <th scope="col" style="max-width:220px;min-width:220px"><spring:message code="usermanage.permissions" text="สิทธิ์" /></th>
                                       <th scope="col"><spring:message code="usermanage.read" text="อ่าน" /></th>
                                       <th scope="col"><spring:message code="usermanage.add" text="จัดทำ" /></th>
                                       <th scope="col"><spring:message code="usermanage.edit" text="แก้ไข" /></th>
                                       <th scope="col"><spring:message code="usermanage.check" text="ตรวจสอบ" /></th>
                                       <th scope="col"><spring:message code="usermanage.app" text="อนุมัติ" /></th>
                                      
                                       <th scope="col"><spring:message code="usermanage.upload" text="อัพโหลด" /></th>
                                         <th scope="col"><spring:message code="usermanage.download" text="ดาวน์โหลด" /></th>
                                       <th scope="col"><spring:message code="usermanage.setting" text="ตั้งค่า" /></th>
                                       <th scope="col"><spring:message code="usermanage.deleteitem" text="ล้างข้อมูล" /></th>
                                    <th scope="col"><spring:message code="usermanage.delete" text="ลบ" /></th>
                                  </tr>
                              </thead>
                              <tbody>
                         <tr>
                                      <th scope="row">1</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="nav.registration" text="ทะเบียนผู้ใช้" /> </td>
                                             <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="READ-UE" ${READUE }></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="ADD-UE" ${ADDUE }></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="EDIT-UE" ${EDITUE }></td>
                                      <td></td>
                                      <td></td>
                                       
                               
                                        <td></td>
                                      <td></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="SET-UE" ${SETUE }></td>
                                         <td></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="DELETE-UE"  ${DELETEUE }></td>
                                       
                                  </tr>   
                                  
                                     <tr>
                                      <th scope="row">2</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="nav.attendance" text="การเข้างาน" /></td>
                                             <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="READ-OT" ${READOT }></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="ADD-OT" ${ADDOT }></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="EDIT-OT" ${EDITOT }></td>
                                      <td></td>
                                      <td></td>
                                       
                               
                                        <td></td>
                                      <td></td>
                                      <td></td>
                                         <td></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="DELETE-OT"  ${DELETEOT }></td>
                                       
                                  </tr> 
                                  
                                        <tr>
                                      <th scope="row">3</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="nav.holiday" text="วันหยุด" /></td>
                                    <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="READ-H" ${READH }></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="ADD-H" ${ADDH }></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="EDIT-H" ${EDITH }></td>
                                      <td></td>
                                      <td></td>
                                       
                               
                                        <td></td>
                                      <td></td>
                                      <td></td>
                                         <td></td>
                                      <td><input type="checkbox" class="form-check-input low low6" name="permissions" value="DELETE-H"  ${DELETEH }></td>
                                       
                                  </tr> 
                                  
               </tbody>
               </table>
               
               
               
               
                <br><h6>   <spring:message code="nav.appfilemanager" text="การจัดการเอกสาร" /> </h6>  
               
                     <table class="table">
                              <thead class="table-dark">
                                   <tr>
                              
                                   <th scope="col" style="min-width:20px;max-width:20px">
                                   <spring:message code="label.no" text="ลำดับ" /></th>
                                      <th scope="col" style="max-width:220px;min-width:220px;"><spring:message code="usermanage.permissions" text="สิทธิ์" /></th>
                                             <th scope="col"><spring:message code="usermanage.read" text="อ่าน" /></th>
                                       <th scope="col"><spring:message code="usermanage.add" text="จัดทำ" /></th>
                                       <th scope="col"><spring:message code="usermanage.edit" text="แก้ไข" /></th>
                                       <th scope="col"><spring:message code="usermanage.check" text="ตรวจสอบ" /></th>
                                       <th scope="col"><spring:message code="usermanage.app" text="อนุมัติ" /></th>
                                     
                                       <th scope="col"><spring:message code="usermanage.upload" text="อัพโหลด" /></th>
                                         <th scope="col"><spring:message code="usermanage.download" text="ดาวน์โหลด" /></th>
                                       <th scope="col"><spring:message code="usermanage.setting" text="ตั้งค่า" /></th>
                                      
                                              <th scope="col"><spring:message code="usermanage.deleteitem" text="ล้างข้อมูล" /></th>
                                    <th scope="col"><spring:message code="usermanage.delete" text="ลบ" /></th>
                                   
                                  </tr>
                              </thead>
                              <tbody>
                                
                          <tr>
                                      <th scope="row"  style="min-width:20px;max-width:20px">1</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="usermanage.manager" text="สิทธิ์ทั่วไป" /> Dar</td>
                                       <td ><input type="checkbox" class="form-check-input low low2"  name="permissions" value="READ-DAR" ${READDAR }></td>
                                     <td><input type="checkbox" class="form-check-input low low2" name="permissions" value="ADD-DAR" ${ADDDAR }></td>
                                     <td><input type="checkbox" class="form-check-input low low2" name="permissions" value="EDIT-DAR" ${EDITDAR }></td>
                                     <td><input type="checkbox" class="form-check-input low low3" name="permissions" value="CHECK-DAR" ${CHECKDAR }></td>
                                     <td><input type="checkbox" class="form-check-input low low4" name="permissions" value="APP-DAR" ${APPDAR }></td>
                                     <td></td>
                                      <td></td>
                                    <td></td>
                                      <td></td>
                                           <td></td>
                        </tr>
                                 
                          <tr>
                                      <th scope="row"  style="min-width:20px;max-width:20px">2</th>
                                      <td style="max-width:220px;min-width:220px;"><spring:message code="usermanage.manager" text="สิทธิ์ทั่วไป" /> Data set</td>
                                       <td><input type="checkbox" class="form-check-input low low2"  name="permissions" value="READ-DS" ${READDS }></td>
                                     <td><input type="checkbox" class="form-check-input low low2" name="permissions" value="ADD-DS" ${ADDDS }></td>
                                     <td><input type="checkbox" class="form-check-input low low2" name="permissions" value="EDIT-DS" ${EDITDS }></td>
                                     <td></td>
                                     <td></td>
                                     <td></td>
                                      <td></td>
                                    <td><input type="checkbox" class="form-check-input low low2" name="permissions" value="SET-DS" ${SETDS }></td>
                                      <td></td>
                                           <td><input type="checkbox" class="form-check-input low low2" name="permissions" value="DELETE-DS" ${DELETEDS }></td>
                        </tr>        
                                 
                                 
                                  
                       <c:forEach items="${drivess}" var="row" varStatus="count">
                                      <c:set var="check1" value="" />
								     <c:set var="check2" value="" />
								     <c:set var="check3" value="" />
								     <c:set var="check4" value="" />
								     <c:set var="check5" value="" />
								     <c:set var="check6" value="" />
								     <c:set var="check7" value="" />
								      <c:set var="check8" value="" />  
								      <c:set var="check9" value="" />
								       <c:set var="check10" value="" />  
				                <c:set var="listcheck" value="ADD-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check1" value="checked" />
                                   </c:if>
                                   
                                          <c:set var="listcheck" value="EDIT-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check2" value="checked" />
                                   </c:if>				                             
					                <c:set var="listcheck" value="CHECK-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check3" value="checked" />
                                   </c:if>								         
								        <c:set var="listcheck" value="APP-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check4" value="checked" />
                                   </c:if>		
                                           <c:set var="listcheck" value="READ-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check5" value="checked" />
                                   </c:if>	
                                                  <c:set var="listcheck" value="UPLOAD-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check6" value="checked" />
                                   </c:if>	
                                  <c:set var="listcheck" value="SET-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check7" value="checked" />
                                   </c:if>	 
                                     <c:set var="listcheck" value="DELETE-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check8" value="checked" />
                                   </c:if>	
                                   
                                   
                                  <c:set var="listcheck" value="DELETEITEM-${row.drives_drive }" />
                                   <c:if test='${fn:contains(list,listcheck)}'>
                                    <c:set var="check10" value="checked" />
                                   </c:if>	
              
               
                <tr>
                                      <th  style="min-width:20px;max-width:20px">
                                      
                                    ${count.count+2 }</th>
                                      <td style="max-width:220px;min-width:220px;">Drives : ${row.drives_name}</td>
                                        <td><input type="checkbox" class="form-check-input low low1" name="permissions" value="READ-${row.drives_drive}" ${check5 }></td>
                                         <td><input type="checkbox" class="form-check-input low " name="permissions" value="ADD-${row.drives_drive}" ${check1 }></td>
                                         <td><input type="checkbox" class="form-check-input low " name="permissions" value="EDIT-${row.drives_drive}" ${check2 } style="display:none"></td>
                                         <td></td>
                                         <td></td>
                                           
                                                  <td></td>
                                         <td></td>
                                    
                                         <td></td>
                                  <td><input type="checkbox" class="form-check-input low" name="permissions" value="DELETEITEM-${row.drives_drive}"  ${check10 } ></td>
                                 <td></td>
                                
                              
                                
                                
                                  </tr >
                                  
    
          </c:forEach>               
                                  
                              </tbody>
                          </table>
                      
                           <br>    <br>    
                            <button type="submit" class="btn btn-primary"><spring:message code="btn.edit" text="แก้ไข" /></button>            
                                          
                               
                       </form:form>           
                               
                        </div>
                   
            
                    </div>
                </div>
        
        
          </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>





<%@ include file="common/mainfoot.jspf" %>  
  <%@ include file="other/alert.jsp" %>

  <c:if test="${save == 'success'}"> 
   <script> 
    $(document).ready(function() {
    	
    	   notify('success','Save','Success');
      });
    
    </script>
</c:if>


 <script>
 
 function show(myclass){
	 
	 var minus ='<img src="/resources/app-assets/images/icons/minus.png" style="width:20px"></img>';
	 var plus ='<img src="/resources/app-assets/images/icons/plus.png" style="width:20px"></img>';
	 
	 $(".sub"+myclass).toggle();
	 
	 var value =  $("#show"+myclass).attr('data-no-turbolink');

	 if( value == "true"){
		 $("#show"+myclass).html(minus);
		 $("#show"+myclass).attr('data-no-turbolink',false);
	 }else if(value == "false"){
		 $("#show"+myclass).html(plus); 
		 $("#show"+myclass).attr('data-no-turbolink',true);
	 }
	 
    
	 
 }
 
 
function toggle(source) {
	  checkboxes = document.getElementsByClassName('low');
	  for(var i=0, n=checkboxes.length;i<n;i++) {
	    checkboxes[i].checked = source.checked;
	  }
	}
	
	
function set(source) {
	$(".low").prop("checked", false);
	
	
	if(source == 1){
		$(".low1").prop("checked", true);
	}
	if( source == 2){
		$(".low1").prop("checked", true);
		$(".low2").prop("checked", true);
		}
	if(source == 3){
		$(".low1").prop("checked", true);
		$(".low2").prop("checked", true);
		$(".low3").prop("checked", true);
	}
	if(source == 4){
		$(".low1").prop("checked", true);
		$(".low2").prop("checked", true);
		$(".low4").prop("checked", true);
	}
	if(source == 5){
		$(".low1").prop("checked", true);
		$(".low2").prop("checked", true);
		$(".low5").prop("checked", true);
		$(".low6").prop("checked", true);

	}if(source == 6){
		$(".low").prop("checked", true);

	}
	}
	

</script>
 
    <script>
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        
          var element = document.getElementById("usermanage2");
  element.classList.add("active");       

    </script>
</body>


            