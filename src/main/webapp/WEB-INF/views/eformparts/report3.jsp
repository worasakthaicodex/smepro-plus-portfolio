<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="../common/mainhead.jspf" %>    
  
   
<title>Report</title>

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
     

}





.fixhead{
    max-width: 150px;
      min-width: 80px;
       
}



.fixhead2{
 background-color: #F0F8FF;
 
}



</style>


</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  " data-open="click" data-menu="vertical-menu-modern" data-col="">
<%@ include file="../common/subhead.jspf" %>  
<%@ include file="../common/mainbar.jspf" %>  

    <!-- BEGIN: Content-->
    <div class="app-content content ">

        <div class="content-wrapper container-xxl p-0">
         
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row">
             
                   
                    
                        <div class="col-12">
                       
                        
                            <div  class="card">
                      
                       <div class="card-header">
                                    <h4 class="card-title">Main Report : ${conditions.condition_title }</h4>
                                    
                                         <div  style="float:right">
                                       
                                     
			                       <a  href="/addreport-filemanager?id=${upload_pn.upload_pn_id}" onclick="openCenteredWindow(this.href, 'File Window', 1500, 600);return false; " class="btn btn-success" >Add</a> 
			                       
                 
                         
                         
                         
                                </div>
                                </div>
                      
                                <div class="card-body">
                            
                         <table>  
                          
                            <thead>
                         <tr>
                           <th class="form-label fixhead">ลำดับ</th>
                          <th class="form-label fixhead">วันที่</th>
                           <th class="form-label fixhead">ชื่อรายงาน</th>
                          <th class="form-label fixhead">โดย</th>
                         <th class="form-label fixhead">ดำเนินการ</th>
                                  
                         </tr>
                           </thead>
                           
                           <tbody>     
            <c:forEach items="${reportall }" var="row" varStatus="count">                          
			      
			        
			         <tr>
                           <td>${count.count }</td>
                          <td >${row.report_date } </td>
                           <td >${row.report_name } </td>
                          <td >${row.report_by }</td>
                         <td >
                         
                         <a href="/mainreportplans-condition/${conditions.condition_id}/${row.report_id}" class="btn btn-light">ดู</a>
                     <c:if test="${row.report_by == access}">     
		                        <c:if test="${row.report_status == 0}">    
		                         <a href="/delmainreportplans-condition/${conditions.condition_id}/${upload_pn.upload_pn_id}/${row.report_id}" class="btn btn-danger"
		                          onclick="return confirm('Are you sure delete?')">ลบ</a>
		                         </c:if>
                  </c:if>
                         </td>
                                  
                         </tr>
           </c:forEach> 
                              </tbody>
                            </table>
                            



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





<%@ include file="../common/mainfoot.jspf" %>  
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
 <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <!-- Include PivotTable.js dependencies -->
    <link href="<c:url value="/lib/pivot/dist/pivot.css" />" rel="stylesheet">            
    <script src="<c:url value="/lib/pivot/dist/pivot.js" />"></script>
    
    
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

</body>
</html>