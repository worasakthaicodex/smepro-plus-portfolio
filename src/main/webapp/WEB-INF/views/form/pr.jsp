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
		
		    th {
    text-align: center;
   border: 1px solid #dddddd;
  background-color: #f2f2f2;
     height: 30px;
     
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
		
		 margin:5px;
		    }
  
      body {
		      width: 210mm; /* กว้างของกระดาษ A4 */
		      height: 297mm ; /* สูงของกระดาษ A4 */
		      margin: 0 auto; /* กำหนดขอบเพจเป็น 0 */
		  background-color:#ddd;
		  
		    }	
	.row{
	  
	  background-color:#fff;
	  
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


.pt{

 outline: 0;
  border-width: 0 0 1px;
  border-color: black;
  width:100px
}
.pts{

background:transparent;border:0;



 }
.pt3{

 outline: 0;
  border-width: 0 0 0px;
  width:100%;
}

*{
color:black;
}

</style>

<c:choose>
                                <c:when test='${cookie.theme.value.equals("moon")}'>
                               			<style>
											.select2-container--default .select2-selection--single .select2-selection__rendered{
											color:#b4b7bd;
											}
									 </style>
                                </c:when>
                                <c:otherwise>
								
                              </c:otherwise>
                               </c:choose> 

</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">



             
                               <div class="row">
                            <!-- Blog -->
                            <div class="col-12" id="maindata">
                            
                    <div  id="card${tasks.task_id}">
                                   <div class="card-body">
                                     
                                        <form:form method="POST" modelAttribute="report" action="/savereport-filemanager">
                                     
                                        <h4 class="card-title" style="width:150px;margin:auto">ใบขอซื้อ</h4>
                                        <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                            
                                             <div style="float:left">
                                                <small class="  ms-50 ms-50" style="color:black">วันที่</small>
                                                <small><input type="text" class="pt"  value="${edata.data_dateupdate }"></small>
                                                
                                                </div> 
                                                <div style="float:right">
                                                <span class=" ms-50 me-25" style="color:black">เลขที่</span>
                                                <small class=""><input type="text" class="pt"  value="PR${pr.date }${pr.pr_id  }"></small>
                                                </div>
                                                
                                            </div>
                                        </div>
                                        
                                        
                                        
                                        
                                        
                                                   <div class="d-flex">
                                         
                                          
                                        </div>
                                                   <div class="d-flex">
                                         
                                           
                                        </div>
                                        <br>
                                        
                                        
                                        
                                        
           						  <table   class="tabledata" style="height:auto" >
                                    <thead>
                                        <tr>
                                            <th class="form-label fixhead">#</th>
                                            <th class="form-label fixhead"> <spring:message code="item.description" text="รายละเอียดสินค้า/บริการ" /></th>
                                             <th class="form-label fixhead"><spring:message code="item.quantity" text="จำนวน" /></th>
                                                      <th class="form-label fixhead"><spring:message code="item.unit" text="หน่วยนับ" /></th>
                                              <th class="form-label fixhead"> <spring:message code="item.estimated" text="ราคาประมาณการ" /></th>
                                                 <th class="form-label fixhead"><spring:message code="item.total" text="มูลค่ารวม" /></th>
                                          
                                        </tr>
                                    </thead>
                                    <tbody>
                                          <c:set var="Clear_status" value="0.0" />
                                        <c:forEach items="${items }" var="row" varStatus="count">
				                                      <tr>
				                                            <td>${count.count }</td >
				                                             <td>${row.description }</td >
				                                             <td>${row.qty }</td >
				                                            <td>${row.unit }</td >
				                                            <td>${row.estimated }</td >
				                                            <td><fmt:formatNumber type = "number" maxFractionDigits = "3" value = "${row.estimated * row.qty  }" /></td>
				                                               <c:set var="Clear_status" value="${Clear_status +(row.estimated * row.qty)  }" />
				                                        </tr>
                                        </c:forEach>
                                        
                                        
                                    </tbody>
                                    </table>   
                                        
                                           <br>
                                  <h3 style="float:right"> รวม  : <fmt:formatNumber type = "number" maxFractionDigits = "3" value = " ${Clear_status }" /></h3>
                                
                
<br>
      <div class="d-flex">
                                         
                                            <div class="author-info" style="width:100%">
                                          
                                                <small class="  ms-50 me-25" style="color:black">ผู้ขอเบิก</small>
                                                <small><input type="text" class="pt" value="${usersed_send.user_name }"></small>
                                                  <small class="  ms-50 me-25" style="color:black">ผู้อนุมัติ</small>
                                                <small><input type="text" class="pt" value="${usersed_app.user_name }"></small>
                                            </div>
                                        </div>
<br>
<hr>
<br>
        
                                        
                                        
                        <input type="hidden" name="report_id"  value="${report.report_id }" >
                                    <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    </form:form >
                                   
                                  
                              
                                    </div>
                                </div>
                          </div>
                            <!--/ Blog -->

                        

                            
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



 
	    