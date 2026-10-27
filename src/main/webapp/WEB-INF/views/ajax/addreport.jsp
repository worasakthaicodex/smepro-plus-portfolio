<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="../common/mainhead.jspf" %>    
<link href="<c:url value="/resources/app-assets/vendors/css/forms/select/select2.min.css" />" rel="stylesheet">




<title>Upload</title>

<style>
 @media print {
		    .notprint {
		      display: none;
		    }
		
		    body {
		      width:  297mm ; /* กว้างของกระดาษ A4 */
		      height:210mm; /* สูงของกระดาษ A4 */
		      margin: 0; /* กำหนดขอบเพจเป็น 0 */
		 
		    }
		    
		    .card{
		    border:0px;
		    }
		.footer{
		   display: none;
		}
		.table-responsive{
		   border:0px;
		}
		
		
		 table {
		    border-collapse:collapse;
		    margin: 0;
		  width: 100%;
		   border: 1px solid black;
		   
		}
		
		
		td {
    text-align: center;
   border: 1px solid black;
      max-width: 200px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid black;
    max-width: 200px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid black;
  background-color: #f2f2f2;
     height: 30px;
     
}
		
		
		
  }
  
  
  
  
  
    * {
		 background-color:#fff;
		 margin:5px;
		    }
  
  
  
  
.select2-selection__arrow{
display:none;
}



 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
   
}


  
td {
    text-align: center;
   border: 1px solid #dddddd;
      max-width: 200px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid #dddddd;
    max-width: 200px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     
}


.fixhead{
    max-width: 200px;
      min-width: 80px;
       
}



.fixhead2{
 background-color: #F0F8FF;
}



</style>



</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">

  				  <c:choose>
                                <c:when test='${save.equals("success")}'>
                               		<div class="alert alert-success" role="alert">
                                            <h4 class="alert-heading">Success</h4>
                                            <div class="alert-body">
                                               บันทึกรายงานของคุณเเล้ว
                                            </div>
                                        </div>
                                </c:when>
                                <c:otherwise>
								
                           

             
                               <div class="row">
                            <!-- Blog -->
                            <div class="col-12" id="maindata">
                            
                    <div  id="card${tasks.task_id}">
                                   <div class="card-body">
                                     
                                        <form:form method="POST" modelAttribute="report" action="/addreportsearch-filemanager">
                                     
                                     
           
                     เริ่มต้น : <input type="date" name="start" required>
                     สิ้นสุด : <input type="date" name="end" required>
                     <input type="hidden" name="id" value="${id }">
                     
                             <button  type="submit" class="me-50 btn btn-sm btn-light notprint">  
	                                                 กรอง
	                                                 </button >
	                                                 
	                                                  <c:if test='${monitor2 > 0 }'>  
			                                                  <a  href="/mapreport-filemanager/${monitor1}/${monitor2}" class="me-50 btn btn-sm btn-light notprint">  
			                                                 ปรับค่า
			                                                 </a >
	                                                  </c:if>
	                                                 
	                                                 
	                         </form:form>    
	  
                     <br>
                        <hr>
                     <br>
                                          <form method="POST"  action="/addreportsearchsave-filemanager">
                               
                       <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">  
                      <input type="hidden" name="start"   value="${start }"  required>
                      <input type="hidden" name="end"  value="${end }"  required>
                       <input type="hidden" name="id" value="${id }" required>
           
                             ชื่อรายงาน : <input type="text" name="report_name"> 
                             
                         <button  class="me-50 btn btn-sm  btn-success notprint">  
	                                                 Save
	                                                 </button >
	               
                                  
                             <hr>
   
   
 เริ่มต้น -  สิ้นสุด : ${start }- ${end }
        
   <div  style="min-height:450px" >
                                      
              <c:if test='${edatas.size() > 0 }'>                              
                                 <c:set var="listchecksum" value="${upload_pn.upload_pn_sum }" />
                                 <c:set var="listhidden" value="${upload_pn.upload_pn_avg }" />
                                  <c:set var="alowtitle" value="${condition.condition_titlerefer }" />
                                  
                                     
                                     <c:if test="${condition.condition_id > 0 }">   
                                     
                                     
                                      <input type="checkbox" id="mains1"  onchange="updatedata(this);" checked> ทั้งหมด   <input type="checkbox" id="mains2"  onchange="updatedata2(this);"> เครียร์เเล้ว                  
                                             <table id="tabledata" class="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                                                  <th class="form-label fixhead">เลือก</th>
                                        
                                 <c:forEach items="${eformfields2 }" var="row" varStatus="count">     
                              
                            		
                                        <th class="form-label fixhead fixhead2" >${row.field_label }</th>
                       				
                               </c:forEach>  
                               
                                 <c:if test='${eformfields2.size() > 0 }'> 
                                       <th class="form-label fixhead fixhead2">ผู้เพิ่ม</th>
                               
                                       <th  class="form-label fixhead fixhead2">ผู้อ้างอิง</th>
                 					 </c:if>
                  
                  
                  
                              <c:forEach items="${eformfields }" var="row" varStatus="count">     
                              
                            
                              
                              
                                        <th class="form-label fixhead">${row.field_label }</th>
                               </c:forEach>  
                               
                          
                                    
                            <th class="form-label fixhead">วันที่</th>
                                     <th class="form-label fixhead">เอกสารเเนบ</th>
                               
                                     <th class="form-label fixhead">ผู้เพิ่ม</th>
                                       <th  class="form-label fixhead">ผู้อ้างอิง</th>
                                     <th  class="form-label fixhead">ผู้ตรวจ</th>
                                      <th class="form-label fixhead">วันที่ตรวจ</th>
                             
                                    </thead>
                               
                               
                               
                                 <tbody>  
                                 
                            
                                 
                                 <c:forEach items="${edatas }" var="rows" varStatus="count">  
                                 
                      
                                   	<c:set var="sum" value='0' />
                                   	<c:set var="count" value='0' />
                                   	<c:set var="join" value='' />
                        
                                 <tr class="rowbody e-${rows.data_id}">
                                 
                                 
                                      <c:if test="${rows.data_join > 0  && rows.log_flow  > 0  }">  
                                 	<c:set var="join" value='datasub' />
                                     </c:if>
                                 
                                            	  <td > <input type="checkbox" class="datamain ${join}" name="importid" value="${rows.data_id }" checked></td>
                                 
                                     <c:if test="${rows.data_titles1 != null &&  !fn:contains(listhidden,row.data_titles1)  && fn:contains(alowtitle,count.count) }">  
                                     <td data-custom="${rows.data_titles1}" title="${rows.data_values1}">${rows.data_values1 }
                                     </td>
                                  <c:set var="newdata" value='${newdata}=${rows.data_values1}' />
                              
                                      </c:if>
                                    <c:if test="${rows.data_titles2 != null  &&  !fn:contains(listhidden,row.data_titles2)  && fn:contains(alowtitle,count.count) }">   
                                     <td data-custom="${rows.data_titles2}" title="${rows.data_values2}">${rows.data_values2 }</td>
                                       <c:set var="newdata" value='${newdata}=${rows.data_values2}' />
                                      
                                      </c:if>
                                         <c:if test="${rows.data_titles3 != null &&  !fn:contains(listhidden,row.data_titles3)  && fn:contains(alowtitle,count.count) }">  
                                     <td data-custom="${rows.data_titles3}" title="${rows.data_values3}">${rows.data_values3 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values3}' />
                                         
                                      </c:if>
                                         <c:if test="${rows.data_titles4 != null &&  !fn:contains(listhidden,row.data_titles4)  && fn:contains(alowtitle,count.count) }">  
                                     <td data-custom="${rows.data_titles4}" title="${rows.data_values4}">${rows.data_values4 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values4}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles5 != null  &&  !fn:contains(listhidden,row.data_titles5)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles5}" title="${rows.data_values5}">${rows.data_values5 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values5}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles6 != null &&  !fn:contains(listhidden,row.data_titles6)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles6}" title="${rows.data_values6}">${rows.data_values6 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values6}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles7 != null &&  !fn:contains(listhidden,row.data_titles7)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles7}" title="${rows.data_values7}">${rows.data_values7 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values7}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles8 != null &&  !fn:contains(listhidden,row.data_titles8)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles8}" title="${rows.data_values8}">${rows.data_values8 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values8}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles9 != null &&  !fn:contains(listhidden,row.data_titles9)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles9}" title="${rows.data_values9}">${rows.data_values9 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values9}' />
                                           
                                           
                                      </c:if>  
                                       <c:if test="${rows.data_titles10 != null &&  !fn:contains(listhidden,row.data_titles10)  && fn:contains(alowtitle,count.count) }">  
                                     <td data-custom="${rows.data_titles10}">${rows.data_values10 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values10}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles11 != null &&  !fn:contains(listhidden,row.data_titles11)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles11}" title="${rows.data_values11}">${rows.data_values11 }</td>
                                           <c:set var="newdata" value='${newdata}=${data_values11}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles12 != null &&  !fn:contains(listhidden,row.data_titles12)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles12}" title="${rows.data_values12}">${rows.data_values12 }</td>
                                           <c:set var="newdata" value='${newdata}=${data_valuess12}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles13 != null &&  !fn:contains(listhidden,row.data_titles13)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles13}" title="${rows.data_values13}">${rows.data_values13 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_values13}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles14 != null &&  !fn:contains(listhidden,row.data_titles14)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles14}" title="${rows.data_values14}">${rows.data_values14 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values14}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles15 != null &&  !fn:contains(listhidden,row.data_titles15)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles15}" title="${rows.data_values15}">${rows.data_values15 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values15}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles16 != null &&  !fn:contains(listhidden,row.data_titles16)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles16}" title="${rows.data_values16}">${rows.data_values16 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values16}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles17 != null &&  !fn:contains(listhidden,row.data_titles17)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles17}" title="${rows.data_values17}">${rows.data_values17 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values17}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles18 != null &&  !fn:contains(listhidden,row.data_titles18)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles18}" title="${rows.data_values18}">${rows.data_values18 }</td>
                                           <c:set var="newdata" value='${newdata} ${rows.data_values18}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles19 != null &&  !fn:contains(listhidden,row.data_titles19)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles19}" title="${rows.data_values19}">${rows.data_values19 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values19}' />
                                      </c:if>
                                         <c:if test="${rows.data_titles20 != null &&  !fn:contains(listhidden,row.data_titles20)  && fn:contains(alowtitle,count.count)}">  
                                     <td data-custom="${rows.data_titles20}" title="${rows.data_values20}">${rows.data_values20 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_values20}' />
                                      </c:if>
                                      <c:set var="sizes" value="${eformfields2.size() }" />
                                    <c:if test="${sizes > 0}">  
                                      <td data-custom="ผู้เพิ่ม">${rows.data_nameadds }</td>
                                      <td >${rows.data_namerefers }</td>
                                       </c:if>
                                 
                                 
                                 
                
                                     <c:set var="size" value="${eformfields.size() }" />
                                     <c:if test="${size > 0}">  
                                     <c:set var="datas" value="${rows.data_title1 }" />  
                                     
                                     <td data-custom="${rows.data_title1}" title="${rows.data_value1}"  
                                   class="inputDiv inputDiv1"    <c:if test='${fn:contains(listchecksum,datas)}'>  data-expression="${rows.data_value1 }"</c:if>>${rows.data_value1 }</td>
                                  <c:set var="newdata" value='${newdata}=${rows.data_value1}' /> 

                         
                                
                                      
                                         </c:if>
                                         
                                         
                                         
                                         
                                         
                             <c:if test="${size > 1}">  
                                     <td data-custom="${rows.data_title2}" title="${rows.data_value2}" 
                                     	 <c:set var="datas" value="${rows.data_title2 }" /> class="inputDiv inputDiv2"        
                                     	 <c:if test='${fn:contains(listchecksum,datas)}'>data-expression="${rows.data_value2 }"
                                     </c:if>
                                     >${rows.data_value2 }</td>
                                       <c:set var="newdata" value='${newdata}=${rows.data_value2}' />
                                 
	                                 <c:if test="${rows.data_value2.matches('[0-9]+')}">
                               							
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value2 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                         
                                         
                                      </c:if>
                                   <c:if test="${size > 2}">  
                                    <c:set var="datas" value="${rows.data_title3 }" />    
                                     <td data-custom="${rows.data_title3}" title="${rows.data_value3}" class="inputDiv inputDiv3"   
                                     <c:if test='${fn:contains(listchecksum,datas)}'>data-expression="${rows.data_value3 }"  </c:if>  >${rows.data_value3 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value3}' />
                                      <c:if test="${rows.data_value3.matches('[0-9]+')}">
                               								
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value3 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                         
                             
                             
                                      </c:if>
                                <c:if test="${size > 3}">  
                                	 <c:set var="datas" value="${rows.data_title4 }" />   
                                     <td data-custom="${rows.data_title4}" title="${rows.data_value4}" class="inputDiv inputDiv4"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'> data-expression="${rows.data_value4 }"   </c:if>  >${rows.data_value4 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value4}' />
                                              
                                       <c:if test="${rows.data_value4.matches('[0-9]+')}">
                               							 
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value4 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                     
                                      </c:if>
                                   <c:if test="${size > 4}">  
                                   	 <c:set var="datas" value="${rows.data_title5 }" />    
                                     <td data-custom="${rows.data_title5}" title="${rows.data_value5}" class="inputDiv inputDiv5" 
                                        <c:if test='${fn:contains(listchecksum,datas)}'> data-expression="${rows.data_value5 }"   </c:if>  >${rows.data_value5 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value5}' />
                                   
                                   
                                   <c:if test="${rows.data_value5.matches('[0-9]+')}">
                               							
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value5 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						             </c:if>  
								</c:if>
                                    
                                      </c:if>
                                      <c:if test="${size > 5}">   
                                      	 <c:set var="datas" value="${rows.data_title6 }" />    
                                     <td data-custom="${rows.data_title6}" title="${rows.data_value6}" class="inputDiv inputDiv6" 
                                     <c:if test='${fn:contains(listchecksum,datas)}'>
                                       data-expression="${rows.data_value6 }"
                                         </c:if> 
                                       >${rows.data_value6 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value6}' />
                              
                              
                                   <c:if test="${rows.data_value6.matches('[0-9]+')}">
                               							 
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value6 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                              
                                      </c:if>
                                    <c:if test="${size > 6}">  
                                    	 <c:set var="datas" value="${rows.data_title7 }" />  
                                     <td data-custom="${rows.data_title7}" title="${rows.data_value7}" class="inputDiv inputDiv7"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value7 }"
                                        </c:if>  
                                      >${rows.data_value7 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value7}' />
                                 
                                 
                                      <c:if test="${rows.data_value7.matches('[0-9]+')}">
                               							  
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value7 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                
                                      </c:if>
                                   <c:if test="${size > 7 }"> 
                                    <c:set var="datas" value="${rows.data_title8 }" />     
                                     <td data-custom="${rows.data_title8}" title="${rows.data_value8}" class="inputDiv inputDiv8"
                                          <c:if test='${fn:contains(listchecksum,datas)}'>
                                        data-expression="${rows.data_value8 }"
                                           </c:if>  
                                        >${rows.data_value8 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value8}' />
                                           
                            <c:if test="${rows.data_value8.matches('[0-9]+')}">
                               								
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value8 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                               <c:if test="${size > 8}">  
                                <c:set var="datas" value="${rows.data_title9}" /> 
                                     <td data-custom="${rows.data_title9}" title="${rows.data_value9}" class="inputDiv inputDiv9"   
                                   <c:if test='${fn:contains(listchecksum,datas)}'>   
                                     data-expression="${rows.data_value9 }"
                                       </c:if>   
                                     >${rows.data_value9 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value9}' />
                                        <c:if test="${rows.data_value9.matches('[0-9]+')}">
                               								   
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value9 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                           
                                      </c:if> 
                                      
                                    <c:if test="${size > 9}">   
                                    	 <c:set var="datas" value="${rows.data_title10 }" />        
                                     <td data-custom="${rows.data_title10}" title="${rows.data_value10}" class="inputDiv inputDiv10" 
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                       data-expression="${rows.data_value10 }"
                                         </c:if> 
                                       >${rows.data_value10 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value10}' />
                                       <c:if test="${rows.data_value10.matches('[0-9]+')}">
                               							 
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value10 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                             
                                      </c:if>
                                <c:if test="${size > 10}">  
                                 <c:set var="datas" value="${rows.data_title11 }" />  
                                     <td data-custom="${rows.data_title11}" title="${rows.data_value11}" class="inputDiv inputDiv11" 
                                      <c:if test='${fn:contains(listchecksum,datas)}'>   
                                       data-expression="${rows.data_value11 }"
                                        </c:if>    
                                       >${rows.data_value11 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value11}' />
                                        <c:if test="${rows.data_value11.matches('[0-9]+')}">
                               								  
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value11 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                   
                                      </c:if>
                                <c:if test="${size > 11}">  
                                 <c:set var="datas" value="${rows.data_title12 }" />   
                                     <td data-custom="${rows.data_title12}" title="${rows.data_value12}" class="inputDiv inputDiv12"   
                                  <c:if test='${fn:contains(listchecksum,datas)}'>
                                     data-expression="${rows.data_value12 }"
                                   </c:if>    
                                     >${rows.data_value12 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value12}' />
                                         <c:if test="${rows.data_value12.matches('[0-9]+')}">
                               								 
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value12 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                       
                                      </c:if>
                          <c:if test="${size > 12}">  
                           <c:set var="datas" value="${rows.data_title13 }" /> 
                                     <td data-custom="${rows.data_title13}" title="${rows.data_value13}"  class="inputDiv inputDiv13"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value13 }"
                                       </c:if>  
                                      >${rows.data_value13 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value13}' />
                                           
                                      <c:if test="${rows.data_value13.matches('[0-9]+')}">
                               								   
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value13 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                                  <c:if test="${size > 13}">  
                                   <c:set var="datas" value="${rows.data_title14 }" />    
                                     <td data-custom="${rows.data_title14}" title="${rows.data_value14}" class="inputDiv inputDiv14"  
                                      <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value14 }"
                                        </c:if>  
                                      >${rows.data_value14 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value14}' />
                                           
                                  <c:if test="${rows.data_value14.matches('[0-9]+')}">
                               								
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value14 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                                 <c:if test="${size > 14}">  
                                  <c:set var="datas" value="${rows.data_title15 }" />
                                     <td data-custom="${rows.data_title15}" title="${rows.data_value15}" class="inputDiv inputDiv15" 
                                       <c:if test='${fn:contains(avglistchecksum,datas)}'>
                                       data-expression="${rows.data_value15 }"
                                         </c:if>  
                                       >${rows.data_value15 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value15}' />
                                           
                                     <c:if test="${rows.data_value15.matches('[0-9]+')}">
                               								    
						                                     <c:if test='${fn:contains(avglistchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value15 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheck,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                                   <c:if test="${size > 15}"> 
                                    <c:set var="datas" value="${rows.data_title16 }" /> 
                                     <td data-custom="${rows.data_title16}" title="${rows.data_value16}" class="inputDiv inputDiv16"  
                                       <c:if test='${fn:contains(listchecksum,datas)}'>  
                                      data-expression="${rows.data_value16 }"
                                        </c:if>  
                                      >${rows.data_value16 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value16}' />
                                      <c:if test="${rows.data_value16.matches('[0-9]+')}">
                               								    
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value16 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                             
                                      </c:if>
                                  <c:if test="${size > 16}">  
                                   <c:set var="datas" value="${rows.data_title17 }" />   
                                     <td data-custom="${rows.data_title17}" title="${rows.data_value17}" class="inputDiv inputDiv17"   
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                     data-expression="${rows.data_value17 }"
                                      </c:if>  
                                     >${rows.data_value17 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value17}' />
                                        <c:if test="${rows.data_value17.matches('[0-9]+')}">
                               								  
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value17 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                                   <c:if test="${size > 17}">  
                                    <c:set var="datas" value="${rows.data_title18 }" />    
                                     <td data-custom="${rows.data_title18}" title="${rows.data_value18}" class="inputDiv inputDiv18"  
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                      data-expression="${rows.data_value18 }"
                                         </c:if>  
                                      >${rows.data_value18 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value18}' />
                                 <c:if test="${rows.data_value18.matches('[0-9]+')}">
                               								
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value18 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                                <c:if test="${size > 18}">  
                                	 <c:set var="datas" value="${rows.data_title19 }" />   
                                     <td data-custom="${rows.data_title19}" title="${rows.data_value19}" class="inputDiv inputDiv19" 
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                       data-expression="${rows.data_value19 }"
                                          </c:if>  
                                       >${rows.data_value19 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value19}' />
                                           
                                           
                                   <c:if test="${rows.data_value19.matches('[0-9]+')}">
                               							 
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value19 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                               <c:if test="${size > 19}">  
                                <c:set var="datas" value="${rows.data_title20 }" />   
                                     <td data-custom="${rows.data_title20}" title="${rows.data_value20}" class="inputDiv inputDiv20"   
                                       <c:if test='${fn:contains(listchecksum,datas)}'>
                                     data-expression="${rows.data_value20 }"
                                          </c:if>  
                                     >${rows.data_value20 }</td>
                                           <c:set var="newdata" value='${newdata}=${rows.data_value20}' />
                                           
                                  <c:if test="${rows.data_value20.matches('[0-9]+')}">
                               								 
						                                     <c:if test='${fn:contains(listchecksum,datas)}'>
						                                    <c:set var="sum" value='${sum + rows.data_value20 }' />
						                                     </c:if>  
                                                            <c:if test='${fn:contains(listcheckavg,datas)}'>
						                                      		<c:set var="count" value='${count + 1 }' />
						                                     </c:if>  
								</c:if>
                                      </c:if>
                                      
                
                     
                     
                 <td data-custom="data_due"  class="inputDiv inputDiv21"   data-expression="end">${rows.data_due }</td>
                                 
                                  
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
                                       <td >${rows.data_namerefer }</td>
                                      <td >${rows.log_app }</td>
                                     <td >${rows.report_pn_datecheck }</td>
                                
                                      
                                       
                                          
                            
                                     
                                 </tr>
                                 </c:forEach>
                                 
  
                                 </tbody>
                                 
                                 
                                               <footer>
                                        <tr>
                 
                               <c:forEach items="${eformfields2 }" var="row" varStatus="count">     

                            		 <c:if test="${!fn:contains(listhidden,row.field_label)  && fn:contains(alowtitle,count.count) }"> 
                                      <th class="form-label fixhead" 
                                    
                                <c:if test='${fn:contains(listchecksum,row.field_label)}'>             
                                     id="${row.field_label }" 
                              	</c:if>  
                                      ></th>
                       				</c:if>
                               </c:forEach>
                               
                               
                        <c:if test="${eformfields2.size() > 0}">  
                                     <th  class="form-label fixhead"></th>
                                       <th class="form-label fixhead"></th>
                                       </c:if>
                                <c:forEach items="${eformfields }" var="rows" varStatus="count">  
                                     <th class="form-label fixhead" 
                               <c:if test='${fn:contains(listchecksum,rows.field_label)}'>             
                                     id="${rows.field_label }"
                              </c:if>  
                                      ></th>
                                    
                               </c:forEach>  
         
                                     <th  class="form-label fixhead"></th>
        
                            <th class="form-label fixhead"></th>
                                     <th class="form-label fixhead"></th>
                               
                                     <th class="form-label fixhead"></th>
                                     <th  class="form-label fixhead"></th>
                                       <th class="form-label fixhead"></th>
                        
                                    </footer>
                               
                                
                                 </table>            
                               
                                     </c:if>
                                         
                                              
                                  
                                    
                                         </c:if>
                                
                               
                
                                          </div>     
                                        
                                        
                                        <br>  <br>
                                        
                            
                            
                                        
                                    
                                        <div class="d-flex align-items-center justify-content-between notprint">
                                          
                                            <div class="dropdown blog-detail-share" style='display:none'>
                                                        
                                                        
                                                        
                                                         <div class="btn-group">
                                <button type="button" class="btn btn-sm btn-success dropdown-toggle" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                Action
                                </button>
                                <div class="dropdown-menu" style="">
                             
                                
                                     
                                                   
   <a class="dropdown-item" href="javascript:void(0)" onclick="getdata('/savereport-filemanager/${tasks.task_id}','${tasks.task_id}')" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard">บันทึก
                              
                                 </a>             
                                
                              
                                 
   <a class="dropdown-item" href="javascript:void(0)" onclick="getdata('/edittask-filemanager/${tasks.task_id}','${tasks.task_id}')" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard">เเก้ไข
                              
                                 </a>
                                     <a class="dropdown-item" href="/deltask-filemanager/${tasks.task_id }"
        onclick="return confirm('คุณเเน่ใจว่าจะลบ ${tasks.task_name } ?');" class="btn btn-sm btn-danger" >ลบ
                              
                                 </a>
                                      <div class="dropdown-divider"></div>    
                                  
                                 
                               <c:if test='${tasks.task_status == 1}'>
                                    <a class="dropdown-item" href="javascript:void(0)"  onclick="getdata2('/endtask-filemanager/${tasks.task_id}/2','1')"  class="btn btn-sm btn-danger"  
                                 class="btn btn-primary">เปลี่ยนสถานเป็น "เสร็จสิ้น"
                              
                                 </a>
                                 
                                 
                                 </c:if>
                                    <c:if test='${tasks.task_status == 2}'>
     <a class="dropdown-item" href="javascript:void(0)" onclick="getdata2('/endtask-filemanager/${tasks.task_id}/1','1')" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" >เปลี่ยนสถานะเป็น "ระหว่างดำเนินการ"
                              
                                 </a>
                               </c:if>
  
                                      <div class="dropdown-divider"></div>          
                                 <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard2">เพิ่มผู้รับผิดชอบ
                              
                                 </a>
                                    
                                    <a class="dropdown-item" href="javascript:void(0)" class="btn btn-sm btn-danger"  
                                 class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addNewCard3">เพิ่มเอกสารประกอบ
                              
                                 </a>     
                                </div>
                            </div>
                                        
                                           
                                           
                                            </div>
                                        </div>
                                
                                    </form >
                                   
                                
                                    </div>
                                </div>
                          </div>
                            <!--/ Blog -->

                            <!-- Blog Comment -->
                            <div class="col-12 mt-1" id="blogComment" style="display:none">
                                <h6 class="section-label mt-25">นำเสนอ</h6>
		                               <div id="dataplan">
				          <%@ include file="subtask.jsp" %>  
		                            </div>
                             </div>
                            <!--/ Blog Comment -->



         <c:set var="listcheck" value="${Member_name}" />                           
                                <c:set var="list" value="${tasks.task_iduser}" />                    
                                           <c:if test='${fn:contains(list,listcheck)}'>
                            <!-- Leave a Blog Comment -->
                            <div class="col-12 mt-1" style="display:none">
                                <h6 class="section-label mt-25">Action</h6>
                                <div class="card">
                                    <div class="card-body">
                                    
                           
                          
                                 
                     
                                
                                
                                    </div>
                                </div>
                            </div>
                            <!--/ Leave a Blog Comment -->
                              </c:if>  
                            
                            
                        </div>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </section>

            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>







    <script>
    
    function mytype(){
    	 var value = document.getElementById("duetype").value;
    	
    	 
    	 
      	 if(value == 1){
    		 $("#duetype2").show();
    		 $("#duetype1").hide();
    		
    	 }else if(value == 2){
    
    		 $("#duetype1").hide();
    		 $("#duetype2").hide();
    		 
    	 }else{
    		 $("#duetype1").show();
    		 $("#duetype2").hide();
    	 }
    	 
    }
    
    
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
            var value = document.getElementById("duetype").value;
         	 if(value == 1){
        		 $("#duetype2").show();
        		 $("#duetype1").hide();
        		
        	 }else if(value == 2){
        
        		 $("#duetype1").hide();
        		 $("#duetype2").hide();
        		 
        	 }else{
        		 $("#duetype1").show();
        		 $("#duetype2").hide();
        	 }
        	 
        	 
            
            
        })
        
        var element = document.getElementById("nav_doc");
  element.classList.add("active");      


    </script>
    
       </c:otherwise>
                               </c:choose> 
    
    
</body>

                             
    

  	  <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
            
                
  <script>  
  
  
function edit(div){
	  
	  $("#sub-"+div).toggle();
	  $("#edit-"+div).toggle();
	  var c = $("#c-"+div).val();
	  var f = $("#f-"+div).val();
	  $("#subimg-"+div).html(f);
     $("#comment-"+div).val(c);
	  $("#img-"+div).show();
	 $("#imgs-"+div).hide();
	  
	 
	 
}
function edit2(div){
	  
	  $("#subs-"+div).toggle();
	  $("#edits-"+div).toggle();
	  var c = $("#c-"+div).val();
	  var f = $("#f-"+div).val();
	  $("#subimg-"+div).html(f);
      $("#comment-"+div).val(c);
	 $("#img-"+div).show();
	$("#imgs-"+div).hide();
	  
}
  function filedownload(file){
  	
  	window.open("/filedownload-filemanager?id="+file, '_blank').focus();
  }
	   
	    function postdata(Mode,Mode2,Mode3) {

		    var formData = new FormData($("#"+ Mode2)[0]);

				    $.ajax({
				        url: Mode,
				        type: "POST",
				        data: formData,
				        processData: false,
				        contentType: false,
					    success: function(result){
					    $('#' + Mode3).html(result);
			
					       }
					    });


				    $("#"+Mode2)[0].reset();
				    
		    return false;
		    }	
	    
	    
	    function getdata(url,id){
	    	
						        $.ajax({
						        type:'GET',
						        url:url,
						        data: {ID : id},
						        
						     success: function(data){
						    $("#savestatus").html(data);	
					
						     }
						});
			
			return false;
		    
			}
	    
	    
	    function getdata2(url,id){
	    	
	    	
	    	if (confirm("คุณต้องการดำเนินการ?"))
	    	{
			    	    $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : id},
			    	        
			    	     success: function(data){
			    	    $("#maindata").html(data);	
		
			    	     }
			    	});
	    		
	    	}
	    	
	    	
	    	
	    	

return false;

} 
	    
	    
	    function getdatas(url,div,type){
	    
	    	var status = 0;
	    	
	    	
	    	if(type == 1){
	    		if (confirm("คุณต้องการดำเนินการ?"))
				{
	    			status=1;
				}
	    	}else{
	    		status=1;
	    	}
	
				
				if(status == 1){
					  $.ajax({
			    	        type:'GET',
			    	        url:url,
			    	        data: {ID : 0},
			    	        
			    	     success: function(data){
			    	    $("#"+div).html(data);	
		
			    	     }
			    	});
					
				}
				    	  
			
			
			
			return false;
			
	}
	
	    
</script>  


   

   



   
      <script>
   
  
    function evaluateAndDisplay() {

   
      // ดึงทุก tr ที่มี class เป็น "inputDiv"
      var inputDivs = document.querySelectorAll('.inputDiv');

      // วนลูปทุก inputDivs
      inputDivs.forEach(function(inputDiv) {
    
    	    try {  
    	  // ดึงค่านิพจน์จาก attribute data-expression
        var expression = inputDiv.getAttribute('data-expression');
    	//alert(expression);
        // นำนิพจน์มาประมวลผล
   if(expression){
	      // นำนิพจน์มาประมวลผล
	      var result = eval(expression);

	      // แสดงผลลัพธ์ใน innerText ของ tr
	      inputDiv.innerText = result;
	   
   }

    } catch (error) {
        
    }
        
        
      });
    }
  </script>
   
 <script>
    function evaluateAndDisplay2() {
    	

    	
      // ดึงทุก tr ที่มี class เป็น "inputDiv"
      var inputDivs = document.querySelectorAll('[class^="inputDiv"]');

      
      var i1 =0;var i2 =0;var i3 =0;var i4 =0;var i5 =0;
      var i6 =0;var i7 =0;var i8 =0;var i9 =0;var i10 =0;
      var i11 =0;var i12 =0;var i13 =0;var i14 =0;var i15 =0;
      var i16 =0;var i17 =0;var i18 =0;var i19 =0;var i20 =0;
      // วนลูปทุก inputDivs
      var x =0;
      inputDivs.forEach(function(inputDiv) {
        // ดึงค่าตัวเลขจาก innerText


        x++;
        
        // ดึงค่านิพจน์จาก attribute data-expression
    try { 
        var expression = inputDiv.getAttribute('data-expression');
       
        
        if(expression){
        	  if(x == 1 ){i1 = expression;}if(x == 2 ){ i2 = expression; }if(x == 3 ){  i3 = expression;}if(x == 4 ){  i4 = expression;}if(x == 5 ){  i5 = expression;}
        }
      
        
       if( expression == "end" ){
    	   i1 =0; i2 =0; i3 =0; i4 =0; i5 =0;
    	   i6 =0; i7 =0; i8 =0; i9 =0; i10 =0;
    	   i11 =0; i12 =0; i13 =0; i14 =0; i15 =0;
    	   i16 =0; i17 =0; i18 =0; i19 =0; i20 =0;
    	   x=0;
       }
        
        
        
        // แทนที่ตัวเลขในนิพจน์ด้วยค่าที่ได้จาก innerText
       expression = expression.replace(/#1/g, i1).replace(/#2/g, i2).replace(/#3/g, i3).replace(/#4/g, i4).replace(/#5/g, i5)
       .replace(/#6/g, i6).replace(/#7/g, i7).replace(/#8/g, i8).replace(/#9/g, i9).replace(/#10/g, i10)
       .replace(/#11/g, i11).replace(/#12/g, i12).replace(/#13/g, i13).replace(/#14/g, i14).replace(/#15/g, i15)
        .replace(/#16/g, i16).replace(/#17/g, i17).replace(/#18/g, i18).replace(/#19/g, i19).replace(/#20/g, i20);
        
	
        
   
            // นำนิพจน์มาประมวลผล
            var result = eval(expression);

            // แสดงผลลัพธ์ใน innerText ของ tr
            inputDiv.innerText = result;
          } catch (error) {
              
          }
              

      });
    }
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



   <script>

   function  sum(){
 	  
 <c:forEach items="${eformfields }" var="rows" varStatus="count">  



 var total = 0;
 $('tr:has(td[data-custom="${rows.field_label }"]:visible)').each(function() {
     var value = parseInt($(this).find('td[data-custom="${rows.field_label }"]').text());
     if (!isNaN(value)) {
         total += value;
     }
 });







 // แทรกผลรวมลงใน <td> ที่มี id="ผลผลิต"



 $('#${rows.field_label }').text(total);

 </c:forEach>  



 <c:forEach items="${eformfields2 }" var="rows" varStatus="count">  



 var total = 0;
 $('tr:has(td[data-custom="${rows.field_label }"]:visible)').each(function() {
     var value = parseInt($(this).find('td[data-custom="${rows.field_label }"]').text());
     if (!isNaN(value)) {
         total += value;
     }
 });


 $('#${rows.field_label }').text(total);

 </c:forEach>  

   }


   
   
   
   
   
   
   
   

  // evaluateAndDisplay();
  // evaluateAndDisplay2();
 //  sum();
   
   document.getElementById('printButton').addEventListener('click', function() {
	    window.print(); // เรียกใช้งานการพิมพ์
	  });
   
   

   function updatedata2(checkbox) {
		  // หาก checkbox ที่ถูกคลิกมีค่าเป็น true (ถูก check)
		  if (checkbox.checked) {
		    // ให้ loop ทุกตัวที่มี class เป็น "set3"
		    document.querySelectorAll('.datasub').forEach(function(item) {
		      // ให้เปลี่ยนสถานะของ checkbox นั้นเป็น checked
		      item.checked = checkbox.checked;
		    });
		  } else {
		    // หาก checkbox ถูก uncheck
		    // ให้ loop ทุกตัวที่มี class เป็น "set3"
		    document.querySelectorAll('.datasub').forEach(function(item) {
		      // ให้เปลี่ยนสถานะของ checkbox นั้นเป็น unchecked
		      item.checked = false;
		    });
		  }
		}  
   
   

   function updatedata(checkbox) {
		  // หาก checkbox ที่ถูกคลิกมีค่าเป็น true (ถูก check)
		  if (checkbox.checked) {
		    // ให้ loop ทุกตัวที่มี class เป็น "set3"
		    document.querySelectorAll('.datamain').forEach(function(item) {
		      // ให้เปลี่ยนสถานะของ checkbox นั้นเป็น checked
		      item.checked = checkbox.checked;
		    });
		  } else {
		    // หาก checkbox ถูก uncheck
		    // ให้ loop ทุกตัวที่มี class เป็น "set3"
		    document.querySelectorAll('.datamain').forEach(function(item) {
		      // ให้เปลี่ยนสถานะของ checkbox นั้นเป็น unchecked
		      item.checked = false;
		    });
		  }
		}

   
   
   
 </script>


 
	    