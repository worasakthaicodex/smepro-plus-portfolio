<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ include file="../common/mainhead.jspf" %> 


<title>index </title>


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
      max-width: 150px;
     font-size:14px;
      overflow: hidden;   /*ซ่อนเนื้อหาที่ล้น */
  text-overflow: ellipsis; /* เพิ่มจุดไข่ปลา (ellipsis) เมื่อเนื้อหาล้น */
  white-space: nowrap; /* ป้องกันการขึ้นบรรทัดใหม่ */
}
td  .action{
 text-align: center;
    border: 1px solid #dddddd;
    max-width: 150px;
    font-size: 14px;
    white-space: normal; /* ขึ้นบรรทัดใหม่เมื่อเนื้อหาล้น */
}



td:first-child { text-align: left; }


th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
       font-size:14px;
}


.fixhead{
    max-width: 150px;
      min-width: 80px;
      
}

thead {
    position: sticky;
    top: 0;
    background-color: #f1f1f1; /* สีพื้นหลังของหัวตารางเมื่อ sticky */
    z-index: 1; /* ให้หัวตารางอยู่ด้านบนของเนื้อหา */
}
tfoot {
    position: sticky;
    top: 0;
    background-color: #f1f1f1; /* สีพื้นหลังของหัวตารางเมื่อ sticky */
    z-index: 1; /* ให้หัวตารางอยู่ด้านบนของเนื้อหา */

      bottom: 0;
      top: auto;
    }


</style>

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">

<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>     

    <!-- BEGIN: Content-->
    <div class="app-content content ">
        <div class="content-overlay"></div>
        <div class="header-navbar-shadow"></div>
        <div class="content-wrapper container-xxl p-0">
            <div class="content-header row">
            </div>
            <div class="content-body">
                         <!--Bar Chart Start -->
                <section id="dashboard-analytics">
                
                   <div class="row match-height">
                   <div class="row" id="basic-table">
                    <div class="col-12">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title"><spring:message code="plan.info" text="ข้อมูลรายงาน"/></h4>
                                
                            </div>
                   
                                 
                              
                      <form:form method="POST"  action="/reportrecords-log">
                            
                           
                                  <span style="float:left"><spring:message code="plan.datebw" text="ค้นหาตามวันที่"/>  : <input type="date" placeholder="Search" name="datestart" value="${datestart}">-<input type="date" placeholder="Search" name="dateend" value="${dateend}"> </span>
                                  
                                  
                                     <button onclick="download('xlsx');"  type="button"  class="btn btn-primary btn-sm" style="float:right;margin-left:5px">  <spring:message code="btn.export"  /></button>
                                <span style="float:right;">  <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /> : 
                                <select name="respon">
                                  <option value=""><spring:message code="forms.all"  /></option>
                                  <c:forEach items="${respon }" var="row" varStatus="count">
                                  
                                    <c:set var="select1" value="" />
                                    
                                      <c:if test="${row.report_pn_respon.equals(respons) }"> 
                                                             <c:set var="select1" value="selected" />
                                       </c:if>
                                  
                                  <option value="${row.report_pn_respon}" ${select1 }>${row.report_pn_respon}</option>
                                  </c:forEach>
                                 </select> 
                                   <spring:message code="tables.inspector" text="ผู้ดูเเล" /> : 
                                    <select name="app">
                                  <option value=""><spring:message code="forms.all"  /></option>
                                  <c:forEach items="${app }" var="row" varStatus="count">
			                                   <c:set var="select2" value="" />
			                                   <c:if test="${row.report_pn_app.equals(apps) }"> 
			                                     <c:set var="select2" value="selected" />
			                                   </c:if>
                                  <option value="${row.report_pn_app}" ${select2 }>${row.report_pn_app}</option>
                                  </c:forEach>
                                 </select> 
                                   
                                   
                                  <spring:message code="tables.type" text="ประเภท" /> :  <select name="type">
                                  <option value="0"  <c:if test='${type == 0 }'>selected</c:if>><spring:message code="forms.all"  /></option>
                                   <option value="1"  <c:if test='${type == 1 }'>selected</c:if>><spring:message code="due.date" text="ทุกวัน"  /> </option>
                                    <option value="2" <c:if test='${type == 2 }'>selected</c:if>> <spring:message code="due.week" text="ทุกอาทิตย์" /> </option>
                                     <option value="3" <c:if test='${type == 3}'>selected</c:if>><spring:message code="due.month" text="ทุกเดือน"  /></option>
                                      <option value="4" <c:if test='${type == 4 }'>selected</c:if>><spring:message code="due.type2" text="กำหนดตามวันที่" /></option>
                                      <option value="5" <c:if test='${type == 5 }'>selected</c:if>> <spring:message code="due.type3" text="ไม่กำหนด" /></option>
                                 
           
                                 </select> 
                                 
                                 
                                 
                                  <spring:message code="due.sort" text="เรี่ยงตาม" /> :   <select name="sort">
                                  
                                 
                                  
                                  <option value="report_pn_dateupload" <c:if test='${sort.equals("report_pn_dateupload") }'>selected</c:if> >
                                  <spring:message code="plan.dateadd" text="วันที่อัพโหลด" /></option>
                                   <option value="report_pn_date" <c:if test='${sort.equals("report_pn_date") }'>selected</c:if> >
                                  <spring:message code="plan.dateplan"  /></option>
                                    <option value="upload_pn_name" <c:if test='${sort.equals("upload_pn_name") }'>selected</c:if> >
                                    <spring:message code="plan.namefile" text="เอกสารที่ต้องอัพโหลด" /></option>
                                     <option value="report_pn_respon" <c:if test='${sort.equals("report_pn_respon") }'>selected</c:if> >
                                     <spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></option>
                                    
                                       <option value="report_pn_app" <c:if test='${sort.equals("report_pn_app") }'>selected</c:if> >
                                       <spring:message code="tables.inspector" text="ผู้ดูเเล" /></option>
                                        <option value="report_pn_datecheck" <c:if test='${sort.equals("report_pn_datecheck") }'>selected</c:if> >
                                       <spring:message code="plan.checkdate" text="วันที่ตรวจสอบ" /></option>
                                         <option value="report_pn_target" <c:if test='${sort.equals("report_pn_target") }'>selected</c:if> >
                                         <spring:message code="plan.target" text="เป้าหมาย" />
                                         </option>
                                           <option value="report_pn_sendstatus" <c:if test='${sort.equals("report_pn_sendstatus") }'>selected</c:if> >
                                           <spring:message code="plan.notsend" text="ขาดส่ง" /></option>
                                             <option value="report_pn_statuserror" <c:if test='${sort.equals("report_pn_statuserror") }'>selected</c:if> >
                                            <spring:message code="plan.uploadlate" text="อัพโหลดช้าสะสม (วัน)" /></option>
                                              <option value="report_pn_appstatus" <c:if test='${sort.equals("report_pn_appstatus") }'>selected</c:if> >
                                             <spring:message code="plan.checklate" text="ตรวจสอบช้าสะสม (วัน)" /></option>
                                               <option value="upload_pn_type" <c:if test='${sort.equals("upload_pn_type") }'>selected</c:if> >
                                              <spring:message code="tables.due" text="กำหนด" /></option>
                                 </select> 
                                  <button type="submit" class="btn btn-sm btn-primary waves-effect waves-float waves-light"><spring:message code="btn.search"  /></button>
                                  
                                   </span>
                           </form:form >



                                 
                                     <div class="table-responsive" style='padding:5px'>
                            
                               
                               
             <div class="table-responsive" style="height:700px;clear:both">
                                <table id="data-table" >
                                    <thead>
                                        <tr>
                                        <th class="fixhead">#</th>
                                             <th class="fixhead"><spring:message code="plan.name" /></th>
                                           <th class="fixhead"><spring:message code="plan.dateplan"  /></th>
                                       
                                            <th class="fixhead"><spring:message code="plan.namefile" text="เอกสารที่ต้องอัพโหลด" /></th>
                                      
                                            <th class="fixhead"><spring:message code="tables.responsible" text="ผู้รับผิดชอบ" /></th>
                                             <th class="fixhead"><spring:message code="plan.dateadd" text="วันที่อัพโหลด" /></th>
                                            <th class="fixhead"><spring:message code="tables.inspector" text="ผู้ดูเเล" /></th>
                                              <th class="fixhead"><spring:message code="plan.checkdate" text="วันที่ตรวจสอบ" /></th>
                                            <th class="fixhead"><spring:message code="plan.target" text="เป้าหมาย" /></th>
                                              <th class="fixhead"><spring:message code="plan.notsend" text="ขาดส่ง" /></th>
                                            <th class="fixhead"><spring:message code="plan.uploadlate" text="อัพโหลดช้าสะสม (วัน)" /></th>
                                              <th class="fixhead"> <spring:message code="plan.checklate" text="ตรวจสอบช้าสะสม (วัน)" /></th>
                                                   <th class="fixhead"><spring:message code="tables.due" text="กำหนด" /></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                
                                   <c:set var="total1" value="0" />   
                                   <c:set var="total2" value="0" />    
                                   <c:set var="total3" value="0" />   
                                   <c:set var="total4" value="0" />   
                                        <c:forEach items="${rawreport }" var="row" varStatus="count">
                                        
                                        <tr class="datatable ">
                                           <td>${count.count}</td>
                                                   <td>${row.upload_pn_note}</td>
                                                   <td>${row.report_pn_date}</td>
                                            <td title="${row.upload_pn_name}">
                                           ${row.upload_pn_name}
                                           
                                           
                                             <c:if test="${!row.upload_pn_name2.equals('') && row.upload_pn_name2 != null }"> 
                                            ,${row.upload_pn_name2}
                                               </c:if>
                                                 <c:if test="${!row.upload_pn_name3.equals('') && row.upload_pn_name3 != null }"> 
                                             ,${row.upload_pn_name3}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name4.equals('') && row.upload_pn_name4 != null }"> 
                                           ,${row.upload_pn_name4}
                                               </c:if>
                                               <c:if test="${!row.upload_pn_name5.equals('') && row.upload_pn_name5 != null }"> 
                                             ,${row.upload_pn_name5}
                                               </c:if>
                                           
                                              
                                            </td>
                                
                                            <td>
                                                ${row.report_pn_respon}
                                            </td>
                                              <td> ${row.report_pn_dateupload}</td>
                                          
                                            
                                                <td>
                                                ${row.report_pn_app}
                                            </td>
                                              <td> 
                                               ${row.report_pn_datecheck} 
                                             </td>
                                             <td><c:set var="total1" value="${total1 + row.report_pn_target}" />  ${row.report_pn_target}</td>
                                            
                                       
                                            <td> <c:set var="total2" value="${total2 + row.report_pn_sendstatus}" />    ${row.report_pn_sendstatus}</td>
                                                   <td><c:set var="total3" value="${total3 + row.report_pn_statuserror}" /> ${row.report_pn_statuserror}</td>
                                                   
                                       <td> <c:set var="total4" value="${total4 + row.report_pn_appstatus}" />  ${row.report_pn_appstatus}</td>
                                   <td>
                                   
                                   <c:if test='${row.upload_pn_type == 0 and row.upload_pn_due  == 0}'><spring:message code="due.date" text="ทุกวัน"  /></c:if>
                                    <c:if test='${row.upload_pn_type == 0 and row.upload_pn_due  == 1}'> <spring:message code="due.week" text="ทุกอาทิตย์" /></c:if>
                                    <c:if test='${row.upload_pn_type == 0 and row.upload_pn_due  == 2}'> <spring:message code="due.month" text="ทุกเดือน"  /></c:if>
          							<c:if test='${row.upload_pn_type == 1}'> <spring:message code="due.type2" text="กำหนดตามวันที่" /></c:if>
             					  <c:if test='${row.upload_pn_type == 2}'><spring:message code="due.type3" text="ไม่กำหนด" /></c:if>

                                   </td>
                                        </tr>
                                 
                                 
                               
                                
                                 
                                  </c:forEach>
                                     </tbody>
                                    <tfoot>
                                 
                                    <tr >
                                           <th class="fixhead"></th>
                                                   <th class="fixhead"></th>
                                                   <th class="fixhead"></th>
                                            <th class="fixhead"> </th>
                                
                                            <th class="fixhead"></th>
                                              <th class="fixhead"> </th>
                                          
                                            
                                                <th class="fixhead"></th>
                                                     <th class="fixhead"></th>
                                             <th class="fixhead"> 
                                                ${total1}
                                             </th>
                                             <th class="fixhead">${total2}</th>
                                            
                                       
                                            <th class="fixhead">${total3}</th>
                                                   <th class="fixhead">${total4}</th>
                                                   
                            
                            
                                                   
                                                   
                                  
                                   <th class="fixhead">
                              
                                   </th>
                                        </tr>
                                              </tfoot>                 
                                            
                                   
                                </table>
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
                       
 
    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>




 
<%@ include file="../common/mainfoot.jspf" %>   


     <script>
     var element = document.getElementById("nav_other9");
     element.classList.add("active");

      
     $(window).on('load', function() {
      	
        	
         if (feather) {
             feather.replace({
                 width: 14,
                 height: 14
             });
         }
         
     });   
         
   

  
         
         </script>   
         
         
<script src="<c:url value="/lib/xlsx.full.min.js" />"></script> 
    <script>
    
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
const download = type => {
	  const el = document.getElementById("data-table");
	  const file = XLSX.utils.table_to_book(el);
	  // Configuring width of the columns:
	  file.Sheets.Sheet1["!cols"] = [{ wpx: 210 }, { wpx: 120 }, { wpx: 80 }];
	  return XLSX.writeFile(file, "alllog." + type);
	};
</script>        
         