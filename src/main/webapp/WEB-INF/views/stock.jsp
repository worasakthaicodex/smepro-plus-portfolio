<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ include file="common/mainhead.jspf" %>    



    <!-- BEGIN: Vendor CSS-->
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/vendors.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/wizard/bs-stepper.min.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/vendors/css/forms/select/select2.min.css">
    <!-- END: Vendor CSS-->

   <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/core/menu/menu-types/vertical-menu.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-wizard.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/plugins/forms/form-validation.css">
    <link rel="stylesheet" type="text/css" href="/resources/app-assets/css/pages/modal-create-app.css">

<style>


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



<title>List</title>
</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="common/subhead.jspf" %>  
<%@ include file="common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">ข้อมูล</h4>
                                 
                      
                            </div>
                             
                            <div class="card-body">
                           
                           
                <div class="row">
                
                   <c:if test="${stock.data_title1 != null}">  
                                            <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title1 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value1 }">
                                                  
                                                </div>
                                            </div>
                 </c:if>
                 
                       <c:if test="${stock.data_title2 != null}">   
                                          <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title2 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value2 }">
                                                  
                                                </div>
                                            </div>
                                             </c:if>  
                                            
                                          <c:if test="${stock.data_title3 != null}">       
                                             <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title3 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value3 }">
                                                  
                                                </div>
                                            </div>
                                          </c:if>   
                                              
                                            
                                       <c:if test="${stock.data_title4 != null}">          
                                              <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title4 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value4 }">
                                                  
                                                </div>
                                            </div>
                                         </c:if>   
                                            
                                            
                                   <c:if test="${stock.data_title5 != null}">             
                                   
                                             <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title5 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value5 }">
                                                  
                                                </div>
                                            </div>
                                      </c:if>       
                             <c:if test="${stock.data_title6 != null}">                    
                                             <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title6 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value6 }">
                                                  
                                                </div>
                                            </div>
                                  </c:if>          
                                         <c:if test="${stock.data_title7 != null}">               
                                             <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title7 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value7 }">
                                                  
                                                </div>
                                            </div>
                                                 </c:if> 
                                            
                                             <c:if test="${stock.data_title8 != null}">           
                                             <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title8 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value8 }">
                                                  
                                                </div>
                                            </div>
                                                 </c:if> 
                                            
                                                 <c:if test="${stock.data_title9 != null}">       
                                             <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title9 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value9 }">
                                                  
                                                </div>
                                            </div>
                                                 </c:if> 
                                            
                                                   <c:if test="${stock.data_title10 != null}">     
                                             <div class="mb-2 col-md-6 form-password-toggle">
                                                <label class="form-label" for="newPassword"> ${stock.data_title10 }</label>
                                                <div class="input-group input-group-merge form-password-toggle">
                                                    <input class="form-control" type="text" value="${stock.data_value10 }">
                                                  
                                                </div>
                                            </div>
                                                 </c:if> 
                                                 
                                                 
                                                
                                        <div> 
                                        
                                     <c:if test="${stock.stock == 1}">            
                                        
                              <a href="/openstock-filemanager/${stock.data_id}" class="me-50 btn btn-sm  btn-success notprint">  
	                                                เปิดใช้
	                                                 </a>     
	                                       </c:if>                 
	                                                  
	                            <c:if test="${stock.stock == 0}">                            
                                                 <a href="/closestock-filemanager/${stock.data_id}" class="me-50 btn btn-sm  btn-danger notprint">  
	                                                 ปิดใช้
	                                                </a >          
                                  </c:if> 
                                        </div>
                     
                                        </div>
                               
                           
                              
<br><br></div>
                   </div>   </div>
                    </div>
                   
                   
                      <div class="col-12">
                   
                   <div class="card">
                      
                      <div class="card-body">
                                    <ul class="nav nav-tabs" role="tablist">
                                        <li class="nav-item">
                                          <c:if test='${conditions != null }'>  
                                           <a class="nav-link active" id="home-tab" data-bs-toggle="tab" href="#home" aria-controls="home" role="tab" aria-selected="true">${conditions.condition_name}</a>
                                        </c:if>
                                        </li>
                                         <c:if test='${conditions2 != null  }'>  
                                        <li class="nav-item">
                                            <a class="nav-link" id="profile-tab" data-bs-toggle="tab" href="#profile" aria-controls="profile" role="tab" aria-selected="false">${conditions2.condition_name}</a>
                                        </li>
                                             </c:if>
                                    
                                    </ul>
                                    <div class="tab-content">
                                        <div class="tab-pane active" id="home" aria-labelledby="home-tab" role="tabpanel">
                                         
                                                                   
                                 <c:set var="listchecksum" value="${upload_pn.upload_pn_sum }" />
                                 <c:set var="listhidden" value="${upload_pn.upload_pn_avg }" />
                                  <c:set var="alowtitle" value="${conditions.condition_titlerefer }" />
                                  
            
                                             <table id="tabledata" class="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                                      
                                        
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
                                   	
                        
                                 <tr class="rowbody e-${rows.data_id}">
                                 
                                            
                                 
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
                               
                                   
                                              
                                  
                                   
                                          
                                        
                                        </div>
                                        <div class="tab-pane" id="profile" aria-labelledby="profile-tab" role="tabpanel">
                                             
                                                                          
                                 <c:set var="listchecksum" value="${upload_pn.upload_pn_sum }" />
                                 <c:set var="listhidden" value="${upload_pn.upload_pn_avg }" />
                                  <c:set var="alowtitle" value="${conditions2.condition_titlerefer }" />
                                  
                                     
                                     <c:if test="${conditions2.condition_id > 0 }">   
                                     
                                     
                                                                
                                             <table id="tabledata" class="tabledata" style="height:auto">
                                    <thead>
                                        <tr>
                                              
                                        
                                 <c:forEach items="${eformfields4 }" var="row" varStatus="count">     
                              
                            		
                                        <th class="form-label fixhead fixhead2" >${row.field_label }</th>
                       				
                               </c:forEach>  
                               
                                 <c:if test='${eformfields4.size() > 0 }'> 
                                       <th class="form-label fixhead fixhead2">ผู้เพิ่ม</th>
                               
                                       <th  class="form-label fixhead fixhead2">ผู้อ้างอิง</th>
                 					 </c:if>
                  
                  
                  
                              <c:forEach items="${eformfields3 }" var="row" varStatus="count">     
                              
                            
                              
                              
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
                                 
                            
                                 
                                 <c:forEach items="${edatas2 }" var="rows" varStatus="count">  
                                 
                      
                                   	<c:set var="sum" value='0' />
                                   	<c:set var="count" value='0' />
                                   	
                        
                                 <tr class="rowbody e-${rows.data_id}">
                                 
                                           
                                 
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
                 
                               <c:forEach items="${eformfields4 }" var="row" varStatus="count">     

                            		 <c:if test="${!fn:contains(listhidden,row.field_label)  && fn:contains(alowtitle,count.count) }"> 
                                      <th class="form-label fixhead" 
                                    
                                <c:if test='${fn:contains(listchecksum,row.field_label)}'>             
                                     id="${row.field_label }" 
                              	</c:if>  
                                      ></th>
                       				</c:if>
                               </c:forEach>
                               
                               
                        <c:if test="${eformfields4.size() > 0}">  
                                     <th  class="form-label fixhead"></th>
                                       <th class="form-label fixhead"></th>
                                       </c:if>
                                <c:forEach items="${eformfields3 }" var="rows" varStatus="count">  
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
                                         
                                              
                                  
                                    
                                       
                               
                
                                        </div>
                                    
                                  
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


    <!-- create app modal -->
                <div class="modal fade" id="createAppModal" tabindex="-1" aria-labelledby="createAppTitle" aria-hidden="true">
                    <div class="modal-dialog modal-dialog-centered modal-lg">
                        <div class="modal-content">
                            <div class="modal-header bg-transparent">
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body pb-3 px-sm-3">
                                <h1 class="text-center mb-1" id="createAppTitle">Create E-Forms</h1>
                                <p class="text-center mb-2">จัดทำ E-form</p>

                                <div class="bs-stepper vertical wizard-modern create-app-wizard">
                                    
                                    
                                    <div class="bs-stepper-header" role="tablist">
                                    
                                    
                                    
                                    
                                        <div class="step" data-target="#create-app-details" role="tab" id="create-app-details-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="book" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">อุตสาหกรรม</span>
                                                    <span class="bs-stepper-subtitle">Enter Information</span>
                                                </span>
                                            </button>
                                        </div>
                                        <div class="step" data-target="#create-app-frameworks" role="tab" id="create-app-frameworks-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="package" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">แผนก</span>
                                                    <span class="bs-stepper-subtitle">Enter Information</span>
                                                </span>
                                            </button>
                                        </div>
                                          <div class="step" data-target="#create-app-database" role="tab" id="create-app-database-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="package" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">ประเภทเเบบฟอร์ม</span>
                                                    <span class="bs-stepper-subtitle">Enter Information</span>
                                                </span>
                                            </button>
                                        </div>
                                        
                                        
                                        <div class="step" data-target="#create-app-billing" role="tab" id="create-app-billing-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="credit-card" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">E-Forms</span>
                                                    <span class="bs-stepper-subtitle">Enter E-Forms name</span>
                                                </span>
                                            </button>
                                        </div>
                                    
                                        <div class="step" data-target="#create-app-submit" role="tab" id="create-app-submit-trigger">
                                            <button type="button" class="step-trigger py-75">
                                                <span class="bs-stepper-box">
                                                    <i data-feather="check" class="font-medium-3"></i>
                                                </span>
                                                <span class="bs-stepper-label">
                                                    <span class="bs-stepper-title">Submit</span>
                                                    <span class="bs-stepper-subtitle">Submit your E-Forms</span>
                                                </span>
                                            </button>
                                        </div>
                                    </div>

                                    <!-- content -->
                                    <div class="bs-stepper-content shadow-none">
                         
                                          <form:form method="POST" modelAttribute="condition" action="/addconditions-condition">
                                          
                                          
                                          
                                           <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                        <div id="create-app-details" class="content" role="tabpanel" aria-labelledby="create-app-details-trigger">
                                          

                                            <h5 >อุตสาหกรรม</h5>
                                                <ul class="list-group list-group-flush" style="height:400px;overflow-y: scroll;">
                                               
                                               <c:forEach items="${centrals }" var="row" varStatus="count">
                                               
                                               
                                               
                                                <li class="list-group-item border-0 px-0 " >
                                                    <label for="createAppOnlineLearning" class="d-flex cursor-pointer">
                                                        <span class="avatar avatar-tag bg-light-danger me-1">
                                                            <i data-feather="award" class="font-medium-5"></i>
                                                        </span>
                                                        <span class="d-flex align-items-center justify-content-between flex-grow-1">
                                                            <span class="me-1">
                                                                <span class="h5 d-block fw-bolder">${row.central_name2 }</span>
                                                                <span>${row.central_name1 }</span>
                                                            </span>
                                                            <span> 
                                                                <input class="form-check-input" type="radio"  value="${row.central_id }"  name="condition_industry" required/>
                                                            </span>
                                                        </span>
                                                    </label>
                                                </li>
                                       
                                                </c:forEach>
                                                
                                            
                                                 
                                    
                                                
                                                
                                                
                                                
                                              
                                                
                                            </ul>
                                            <br>     <br>     <br><br><br>
                                            <div class="d-flex justify-content-between mt-2">
                                                <button type="button"  class="btn btn-outline-secondary btn-prev" disabled>
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button type="button" class="btn btn-primary btn-next">
                                                    <span   class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div id="create-app-frameworks" class="content" role="tabpanel" aria-labelledby="create-app-frameworks-trigger">
                                            <h5>แผนก</h5>
                                                    <ul class="list-group list-group-flush" style="height:400px;overflow-y: scroll;">
                                               
                                            
                                            
                                              <c:forEach items="${centrals2 }" var="row" varStatus="count">
                                                       <li class="list-group-item border-0 px-0 level2"  data-customer="${row.central_idrefer }">
                                                    <label for="createAppEcommerce" class="d-flex cursor-pointer">
                                                        <span class="avatar avatar-tag bg-light-info me-1">
                                                            <i data-feather="briefcase" class="font-medium-5"></i>
                                                        </span>
                                                        <span class="d-flex align-items-center justify-content-between flex-grow-1">
                                                            <span class="me-1">
                                                                <span class="h5 d-block fw-bolder">${row.central_name2 }</span>
                                                                <span>${row.central_name1 } </span>
                                                            </span>
                                                            <span>
                                                                <input class="form-check-input" type="radio"   value="${row.central_id }" name="condition_department" required/>
                                                            </span>
                                                        </span>
                                                    </label>
                                                </li>
                                                </c:forEach>
                                                    
                                            </ul>
                                            <div class="d-flex justify-content-between mt-2">
                                                <button type="button" class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button type="button" class="btn btn-primary btn-next">
                                                    <span class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div id="create-app-database" class="content" role="tabpanel" aria-labelledby="create-app-database-trigger">
                                            <h5>ประเภทเเบบฟอร์ม</h5>

                                            <ul class="list-group list-group-flush" style="height:400px;overflow-y: scroll;">
                                               
                                                <c:forEach items="${centrals3 }" var="row" varStatus="count">
                                                <li class="list-group-item border-0 px-0 level3" data-customer="${row.central_idrefer }" >
                                                    <label for="createAppFirebase" class="d-flex cursor-pointer">
                                                            <span class="avatar avatar-tag bg-light-secondary me-1">
                                                           <img src="/resources/app-assets/images/icons/database.png" height="25" alt="database" />
                                                        </span>
                                                        <span class="d-flex align-items-center justify-content-between flex-grow-1">
                                                            <span class="me-1">
                                                                <span class="h5 d-block fw-bolder">${row.central_name2 }</span>
                                                                <span>${row.central_name1 }</span>
                                                            </span>
                                                            <span>
                                                                <input class="form-check-input"  type="radio" value="${row.central_id }" name="condition_type" required/>
                                                            </span>
                                                        </span>
                                                    </label>
                                                </li>
                                                </c:forEach>
                                                
                                              
                                           
                                            </ul>
                                            <div class="d-flex justify-content-between mt-2">
                                                <button type="button"  class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button type="button"  class="btn btn-primary btn-next">
                                                    <span class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                        <div id="create-app-billing" class="content" role="tabpanel" aria-labelledby="create-app-billing-trigger">
                                            <h5 class="mb-1">E-Forms</h5>

                                            <!-- form -->
                                         
                                                <div class="col-12">
                                                    <label class="form-label" for="cardNumberBilling">E-Forms name</label>
                                                    <div class="input-group ">
                                                        <input name="condition_name" class="form-control " type="text" required />
                                                        
                                                    </div>
                                                </div>
  												  <div class="col-12">
                                                    <label class="form-label" for="cardNumberBilling">ชื่อที่ใช้ในเอกสาร</label>
                                                    <div class="input-group ">
                                                        <input name="condition_title" class="form-control " type="text" required />
                                                        
                                                    </div>
                                                </div>
                                                
                                                
                                                <div class="col-md-12" style="display:none">
                                                 
				                                                            <label class="form-label" for="itemname">หัวข้อเพิ่มเติ่ม</label>  <br>
				                                                   
				                                                            <input type="checkbox"  name="condition_support1" value="1" ${support1 }/> วันที่  <br>
				                                                            <input type="checkbox"  name="condition_support2" value="1" ${support2 }/> เวลา <br>
				                                                            <input type="checkbox"  name="condition_support3" value="1" ${support3 }/> แผนก <br>
				                                                            <input type="checkbox"  name="condition_support4" value="1"  ${support4 }/> ความถี่ <br>
				                                                            <input type="checkbox"  name="condition_support5" value="1"  ${support5 }/>  บันทึกเพิ่มเติ่ม <br>
				                                                             <input type="checkbox"  name="condition_support6" value="1" ${support6 }/> ประเภทการบันทึก  <br>  
                                              
    														 </div>
                                             

                                               
                                      

                                            <div class="d-flex justify-content-between mt-5 pt-1">
                                                <button type="button"  class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button  type="button" class="btn btn-primary btn-next">
                                                    <span class="align-middle d-sm-inline-block d-none">Next</span>
                                                    <i data-feather="arrow-right" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                                
                                                 
                                            </div>
                                        </div>
                                        <div id="create-app-submit" class="content text-center" role="tabpanel" aria-labelledby="create-app-submit-trigger">
                                            <h3>Submit 🥳</h3>
                                            <p>ตรวจสอบข้อมูลให้เรียบร้อย E-form ที่ถูกสร้างเเล้วจะเเก้ไขรายละเอียดไม่ได้</p>
                                            <img src="/resources/app-assets/images/illustration/pricing-Illustration.svg" height="218" alt="illustration" />
                                            <div class="d-flex justify-content-between mt-3">
                                                <button type="button"  class="btn btn-primary btn-prev">
                                                    <i data-feather="arrow-left" class="align-middle me-sm-25 me-0"></i>
                                                    <span class="align-middle d-sm-inline-block d-none">Previous</span>
                                                </button>
                                                <button  class="btn btn-success">
                                                    <span class="align-middle d-sm-inline-block d-none">Submit</span>
                                                    <i data-feather="check" class="align-middle ms-sm-25 ms-0"></i>
                                                </button>
                                            </div>
                                        </div>
                                           
                                                 </form:form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- / create app modal -->



<%@ include file="common/mainfoot.jspf" %>  
 
    <script src="/resources/app-assets/vendors/js/forms/wizard/bs-stepper.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/select/select2.full.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/cleave/cleave.min.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/cleave/addons/cleave-phone.us.js"></script>
    <script src="/resources/app-assets/vendors/js/forms/validation/jquery.validate.min.js"></script>
    <!-- END: Page Vendor JS-->

    <!-- BEGIN: Theme JS-->
    <script src="/resources/app-assets/js/core/app-menu.js"></script>
    <script src="/resources/app-assets/js/core/app.js"></script>
    <!-- END: Theme JS-->

    <!-- BEGIN: Page JS-->
    <script src="/resources/app-assets/js/scripts/pages/modal-add-new-cc.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/page-pricing.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-add-new-address.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-create-app.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-two-factor-auth.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-edit-user.js"></script>
    <script src="/resources/app-assets/js/scripts/pages/modal-share-project.js"></script>
    <script>
    
    var element = document.getElementById("e-form1");
    element.classList.add("active");  
    
    
    
    function search() {

	     var filter = $('.files-filter2').val();
	      $('.datatable').filter(function() {
	    
	    	    $(this).hide();
	            var regex = new RegExp(filter, 'ig');
	          
	            if (regex.test($(this).text())) {
	                $(this).show();
	            } else {
	                $(this).hide();
	            }
	            
	            
	        });
	  
   
}
 
    $('.trigger').click(function(e){
      e.preventDefault();
      
      var childUl = $(this).siblings("ul.tree-parent");
      
      if( childUl.hasClass('open') ){
        
        childUl.removeClass('open');
      } else {
        
        childUl.addClass('open');
      }
      
    });
    
    
        $(window).on('load', function() {
            if (feather) {
                feather.replace({
                    width: 14,
                    height: 14
                });
            }
        })
        

$('li.level2[data-customer]').hide();
$('li.level3[data-customer]').hide();    


   $('input[name="condition_industry"]').change(function() {
    var selectedIndustry = $(this).val();

   
    
    // ซ่อนทุก <li class="level2"> ที่มี data-customer ไม่เท่ากับค่าที่ถูกเลือก
    $('li.level2[data-customer]').each(function() {
    	console.log($(this).data('customer'));
    	
        if ($(this).data('customer') != selectedIndustry) {
        	
        	
            $(this).hide();
        } else {
            $(this).show();
        }
    });
    
    // แสดงทุก <li class="level3">
   // $('li.level3').show();
});
        

        
        $('input[name="condition_department"]').change(function() {
            var selectedDepartment = $(this).val();

            // ซ่อนทุก <li class="level3"> ที่มี data-customer ไม่เท่ากับค่าที่ถูกเลือก
            $('li.level3[data-customer]').each(function() {
                if ($(this).data('customer') != selectedDepartment) {
                    $(this).hide();
                } else {
                    $(this).show();
                }
            });

            // แสดงทุก <li class="level2">
            $('li.level2').show();
        });
        
    </script>
</body>

 <script>
function openCenteredWindow(url, name, width, height) {
    // คำนวณตำแหน่งในแกน x และ y ที่ทำให้หน้าต่างอยู่กลางจอ
    var left = (window.innerWidth / 2) - (width / 2);
    var top = (window.innerHeight / 2) - (height / 2);

    // เปิดหน้าต่างใหม่
    window.open(url, name, 'width=' + width + ', height=' + height + ', top=' + top + ', left=' + left);
}
</script>
