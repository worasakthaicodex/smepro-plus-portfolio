<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
 <c:set var = "now" value = "<%= new java.util.Date()%>" />
<%@ include file="../common/mainhead.jspf" %>    
  
   
<title>Report</title>
<style>

 @media print {
 
		    .notprint{
		      display: none;
		    }
		 .card{
   		   height:500px;
   		   margin-top:10px;
     		 }
     		 
     		 .vertical-layout{
     		 margin:0px;
     		  padding:0px;
     		 }
			.app-content{
			 margin:0px;
     		  padding:0px;
     		    top:0;
			}
			  .content{
			  	 margin:0px;
     		  padding:0px;
     		  
     		  top:0;
			  }
	
			
			
			
}


html body .app-content{
			  padding:5px;
			    	 margin:5px;
			    	 
			}


 table {
    border-collapse:collapse;
    margin: 0;
  width: 100%;
   border: 1px solid #dddddd;
    background-color: #FFF;
   
}
th .pvtRowLabel{
 background-color: #FFF;
 
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
      background-color: #FFF;
      
      


}


.fixhead{
    max-width: 150px;
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
.pvtUi{
border:none;
}


.card{

min-height:750px;
over-flow:hidden;

}

</style>


</head>

<body class="vertical-layout vertical-menu-modern  navbar-floating footer-static  "   data-open="click" data-menu="vertical-menu-modern" data-col="" >

<span class="notprint">

<%@ include file="../common/mainbar.jspf" %>  
</span>

    <!-- BEGIN: Content-->
    <div class="app-content " >

        <div class="content-wrapper container-xxl p-0">
         
            <div class="content-header row">
            </div>
            <div class="content-body">
          <section id="multiple-column-form">
                    <div class="row" style="padding-left:10px;margin:10px">
             

                   
                    
                        <div class="col-12">
                       
                        
                            <div  class="card" style="border:none">
                      
                       <div class="card-header" style="width:100%">
                               
                                         <div  style="float:right">
                                       
                                          <button id="printButton" class="btn btn-light notprint"  >พิมพ์</button>       
                                          
                             <c:if test="${reports.report_by == access}">     
                                
                           
                                      
			                       <button onclick="Setting()" class="btn btn-light notprint" id="Setting" >Setting</button> 
			                 </c:if>
			                           
			                           
			                          <button onclick="View()" class="btn btn-light notprint"  id="View"  style="display:none">หน้าเเสดงผล</button> 
			   
			                     <button onclick="Clear()" class="btn btn-warning notprint"  id="Clear" style="display:none">ล้างข้อมูลออก</button> 
			                          <button onclick="run()" class="btn btn-success notprint" id="Save"  style="display:none">Save</button>
                        
                      
                                </div>
                                
                                    <c:if test="${reports.report_by == access}">
                                 <c:if test="${reports.report_status == 0}">        
                                   		<a class="btn btn-success notprint"    href="/setstatus-condition/${conditions.condition_id}/${reports.report_id}"  
                                   		 onclick="return confirm('เเน่ใจจะยืนยันสถานะ ยืนยันเเล้วจะเเก้ไขไม่ได้?')"  style="falot:right">Completed</a>   
                                   </c:if>
                         
                               </c:if>
                                </div>
                         <div class="card-header">
                                    <h4 class="card-title" style="margin:auto;width:20%;">${reports.report_name }</h4>
                                    
                       
                                </div>
                                <div class="card-body show" >
                            
    
    
            <div class="d-flex show">
                                         <c:if test="${reports.foot == 1}">  
                                            <div class="author-info show" style="width:100%" style="display:none">
                                          
                                                <small class="  ms-50 me-25 show" style="color:black">ผู้จัดทำรายงาน</small>
                                                <small class="show"><input type="text"  class="pt" value="${reports.report_by }"></small>
                      
                                            </div>
                                            </c:if>
                                            
                                        </div>
                       
                       
                       
                       
                       
                       
                       
                       
                       
                       
                            
                               <div id="output" style="margin: 10px;"></div>
<form:form method="POST"  id="cmform" modelAttribute="report"   name="cmform">                             
<input type="hidden" id="outputs" name="report_report" value='${reports.report_report}'>
<input type="hidden" id="report_id " name="report_id " value='${reports.report_id}'>


<div id="cmdiv"></div>



 <c:if test="${reports.foot == 1}">  
      <c:set var = "ck" value = "checked" />
   </c:if>
   
    <c:if test="${reports.title3 == 1}">  
      <c:set var = "title3s1" value = "checked" />
   </c:if>
    <c:if test="${reports.title3 == 2}">  
      <c:set var = "title3s2" value = "checked" />
   </c:if>
   
 <div class="settingf"><input type="checkbox"  name="foot" value="1" ${ck }> ไมใช้ส่วนท้าย</div>


 <c:if test="${reports.foot == 0}">  
          <div style="float:left;">      
                                            <br>  <br>   
                                            
                              <span style="display: inline-block;width:100px">จัดทำโดย  : </span><input type="text"  name="report_by" value="${reports.report_by }"  style="border: none; border-bottom: 1px solid #aaa">                 
                                    </div>
                               
                                <div style="float:right;">
                                   <br>  <br>             
                                    <span style="display: inline-block;width:250px">ค่าใช้จ่าย${tag }เดือน ${month }  : </span><input type="text" name="title1"   value="${reports.title1 }"  style="border: none; border-bottom: 1px solid #aaa"> </br>   </br>    
                                    <span style="display: inline-block;width:250px">รวมใช้เงินทั้งหมด  : </span><input type="text"  name="title2" value="${reports.title1 }"  style=" border: none;border-bottom: 1px solid #aaa"></br>  </br>    
                                    <span style="display: inline-block;width:250px">ต้อง </span><input type="checkbox" name="title3" value="1" ${title3s1 } > 
                                    หักคืน <input type="checkbox" value="2"  name="title3" style="border: none; border-bottom: 1px solid #aaa" ${title3s2 }>   เบิกเพิ่ม   </br> </br>     
                                   <span style="display: inline-block;width:250px;"> จำนวน : </span><input type="text" name="title4"  value="${reports.title4 }"  style="border: none; border-bottom: 1px solid #aaa"></br>          </br>        
                                          </div>

 </c:if>


<pre style="display:none">
${conditions.condition_report}
</pre>
</form:form>
                                </div>
                            </div>
                        </div>
                  
                  
                     <c:forEach items="${files }" var="rows" varStatus="count">  
                     
                        <c:if test="${rows.file_type.equals('image/png') or rows.file_type.equals('image/webp') or rows.file_type.equals('image/webp')
                         or rows.file_type.equals('image/jpg')  or rows.file_type.equals('image/jpeg') }"> 	        
		              
                        <div class="col-12">
                       
                        
                            <div  class="card" style="border:none">
                      
                       <div class="card-header" style="width:100%">
                       เอกสารประกอบ ${rows.file_name_original }
                     </div>
                     
                     <img src="/readdocument/${rows.file_path }/${rows.file_name }" alt="Your Image" style="max-width: 80%;max-height:650px; margin:auto; z-index: 1;" />
       
                    	 
                    
            
                        
                     
                        </div>
                        
                        
                        
                      </div>
                       </c:if>
                       
               
               
               
               
                       
                             </c:forEach>
                     
                    </div>
                </section>


            </div>
        </div>
    </div>
    <!-- END: Content-->

    <div class="sidenav-overlay"></div>
    <div class="drag-target"></div>



<span class="notprint">

<%@ include file="../common/mainfoot.jspf" %>  
</span>


    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui-touch-punch/0.2.3/jquery.ui.touch-punch.min.js"></script>
 <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
    <!-- Include PivotTable.js dependencies -->
    <link href="<c:url value="/lib/pivot/dist/pivot.css" />" rel="stylesheet">            
    <script src="<c:url value="/lib/pivot/dist/pivot.js" />"></script>
    
    
</body>

         

      




    <script type="text/javascript">
 
    document.getElementById('printButton').addEventListener('click', function() {

	    window.print(); // เรียกใช้งานการพิมพ์
	    
	  });
    
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


			    //$("#"+Mode2)[0].reset();
			    
	    return false;
	    }	
    
    
    
    
    
    
    // กำหนดฟังก์ชัน run
    function run() { 
    	//$.removeCookie("pivotConfigs");
       var config = $("#output").data("pivotUIOptions");
       var config_copy = JSON.parse(JSON.stringify(config));
       delete config_copy["aggregators"];
        delete config_copy["renderers"];
        // $.cookie("pivotConfigs", JSON.stringify(config_copy));
       $("#outputs").val(JSON.stringify(config_copy));
        postdata("/settingreportplan-condition?${_csrf.parameterName}=${_csrf.token}","cmform","cmdiv");
        
    }

    function Clear() { 
    	if (confirm("Clear setup ? ")) {
        postdata("/unsettingreportplan-condition?${_csrf.parameterName}=${_csrf.token}","cmform","cmdiv");
    	}
    }
    
    function getCookie(name) {
        var value = "; " + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length == 2) return parts.pop().split(";").shift();
    }

  
    
    

    var savedConfig = '${reports.report_report}';
    if (savedConfig != null && savedConfig !== "") {
    	
    	
        try {
    
            $("#output").pivotUI(
                [
                	  <c:forEach items="${edatas }" var="rows" varStatus="count">  
                    { 
                    	"${fields1}": "<c:if test='${rows.data_value1 != null}'>${rows.data_value1}</c:if>", 
                    	"${fields2}": "<c:if test='${rows.data_value2 != null}'>${rows.data_value2}</c:if>", 
                    	"${fields3}": "<c:if test='${rows.data_value3 != null}'>${rows.data_value3}</c:if>",
                     	"${fields4}": "<c:if test='${rows.data_value4 != null}'>${rows.data_value4}</c:if>",
                     	"${fields5}": "<c:if test='${rows.data_value5 != null}'>${rows.data_value5}</c:if>",
                     	"${fields6}": "<c:if test='${rows.data_value6 != null}'>${rows.data_value6}</c:if>",
                     	"${fields7}": "<c:if test='${rows.data_value7 != null}'>${rows.data_value7}</c:if>",
                     	"${fields8}": "<c:if test='${rows.data_value8 != null}'>${rows.data_value8}</c:if>",
                     	"${fields9}": "<c:if test='${rows.data_value9 != null}'>${rows.data_value9}</c:if>",
                     	"${fields10}": "<c:if test='${rows.data_value10 != null}'>${rows.data_value10}</c:if>",
                     	"${fields11}": "<c:if test='${rows.data_value11 != null}'>${rows.data_value11}</c:if>",
                     	"${fields12}": "<c:if test='${rows.data_value12 != null}'>${rows.data_value12}</c:if>",
                     	"${fields13}": "<c:if test='${rows.data_value13 != null}'>${rows.data_value13}</c:if>",
                     	"${fields14}": "<c:if test='${rows.data_value14 != null}'>${rows.data_value14}</c:if>",
                     	"${fields15}": "<c:if test='${rows.data_value15 != null}'>${rows.data_value15}</c:if>",
                     	"${fields16}": "<c:if test='${rows.data_value16 != null}'>${rows.data_value16}</c:if>",
                     	"${fields17}": "<c:if test='${rows.data_value17 != null}'>${rows.data_value17}</c:if>",
                     	"$fields18}": "<c:if test='${rows.data_value18 != null}'>${rows.data_value18}</c:if>",
                     	"${fields19}": "<c:if test='${rows.data_value19 != null}'>${rows.data_value19}</c:if>",
                     	"${fields20}": "<c:if test='${rows.data_value20 != null}'>${rows.data_value20}</c:if>",
                     	"ผู้เพิ่ม": "${rows.data_nameadd }",
                     	"ผู้ตรวจ": "${rows.log_app }",
                     	"บันทึกผู้ตรวจ": "${rows.log_note }",
                     	"วันที่": "${rows.data_due }",

                    
                    	
                    },
                    </c:forEach>
                    

                ],JSON.parse(savedConfig),true
            );
          //  console.log("Parsed Config:", JSON.parse(savedConfig));
            
            
        } catch (error) {
         
        	
        	  $("#output").pivotUI(
        	            [
        	            	 <c:forEach items="${edatas }" var="rows" varStatus="count">  
        	                    { 
        	                    	"${fields1}": "<c:if test='${rows.data_value1 != null}'>${rows.data_value1}</c:if>", 
        	                    	"${fields2}": "<c:if test='${rows.data_value2 != null}'>${rows.data_value2}</c:if>", 
        	                    	"${fields3}": "<c:if test='${rows.data_value3 != null}'>${rows.data_value3}</c:if>",
        	                     	"${fields4}": "<c:if test='${rows.data_value4 != null}'>${rows.data_value4}</c:if>",
        	                     	"${fields5}": "<c:if test='${rows.data_value5 != null}'>${rows.data_value5}</c:if>",
        	                     	"${fields6}": "<c:if test='${rows.data_value6 != null}'>${rows.data_value6}</c:if>",
        	                     	"${fields7}": "<c:if test='${rows.data_value7 != null}'>${rows.data_value7}</c:if>",
        	                     	"${fields8}": "<c:if test='${rows.data_value8 != null}'>${rows.data_value8}</c:if>",
        	                     	"${fields9}": "<c:if test='${rows.data_value9 != null}'>${rows.data_value9}</c:if>",
        	                     	"${fields10}": "<c:if test='${rows.data_value10 != null}'>${rows.data_value10}</c:if>",
        	                     	"${fields11}": "<c:if test='${rows.data_value11 != null}'>${rows.data_value11}</c:if>",
        	                     	"${fields12}": "<c:if test='${rows.data_value12 != null}'>${rows.data_value12}</c:if>",
        	                     	"${fields13}": "<c:if test='${rows.data_value13 != null}'>${rows.data_value13}</c:if>",
        	                     	"${fields14}": "<c:if test='${rows.data_value14 != null}'>${rows.data_value14}</c:if>",
        	                     	"${fields15}": "<c:if test='${rows.data_value15 != null}'>${rows.data_value15}</c:if>",
        	                     	"${fields16}": "<c:if test='${rows.data_value16 != null}'>${rows.data_value16}</c:if>",
        	                     	"${fields17}": "<c:if test='${rows.data_value17 != null}'>${rows.data_value17}</c:if>",
        	                     	"${fields18}": "<c:if test='${rows.data_value18 != null}'>${rows.data_value18}</c:if>",
        	                     	"${fields19}": "<c:if test='${rows.data_value19 != null}'>${rows.data_value19}</c:if>",
        	                     	"${fields20}": "<c:if test='${rows.data_value20 != null}'>${rows.data_value20}</c:if>",
        	                    	"ผู้เพิ่ม": "${rows.data_nameadd }",
        	                     	"ผู้ตรวจ": "${rows.log_app }",
        	                     	"บันทึกผู้ตรวจ": "${rows.log_note }",
        	                     	"วันที่": "${rows.data_due }",
  
        	                     	
        	                    	
        	                    },
        	                    </c:forEach>
        	            ],
        	            {
        	              
        	            }
        	        );
        	
        	
        }
    } else {
        $("#output").pivotUI(
            [
            	 <c:forEach items="${edatas }" var="rows" varStatus="count">  
                 { 
                		"${fields1}": "<c:if test='${rows.data_value1 != null}'>${rows.data_value1}</c:if>", 
                    	"${fields2}": "<c:if test='${rows.data_value2 != null}'>${rows.data_value2}</c:if>", 
                    	"${fields3}": "<c:if test='${rows.data_value3 != null}'>${rows.data_value3}</c:if>",
                     	"${fields4}": "<c:if test='${rows.data_value4 != null}'>${rows.data_value4}</c:if>",
                     	"${fields5}": "<c:if test='${rows.data_value5 != null}'>${rows.data_value5}</c:if>",
                     	"${fields6}": "<c:if test='${rows.data_value6 != null}'>${rows.data_value6}</c:if>",
                     	"${fields7}": "<c:if test='${rows.data_value7 != null}'>${rows.data_value7}</c:if>",
                     	"${fields8}": "<c:if test='${rows.data_value8 != null}'>${rows.data_value8}</c:if>",
                     	"${fields9}": "<c:if test='${rows.data_value9 != null}'>${rows.data_value9}</c:if>",
                     	"${fields10}": "<c:if test='${rows.data_value10 != null}'>${rows.data_value10}</c:if>",
                     	"${fields11}": "<c:if test='${rows.data_value11 != null}'>${rows.data_value11}</c:if>",
                     	"${fields12}": "<c:if test='${rows.data_value12 != null}'>${rows.data_value12}</c:if>",
                     	"${fields13}": "<c:if test='${rows.data_value13 != null}'>${rows.data_value13}</c:if>",
                     	"${fields14}": "<c:if test='${rows.data_value14 != null}'>${rows.data_value14}</c:if>",
                     	"${fields15}": "<c:if test='${rows.data_value15 != null}'>${rows.data_value15}</c:if>",
                     	"${fields16}": "<c:if test='${rows.data_value16 != null}'>${rows.data_value16}</c:if>",
                     	"${fields17}": "<c:if test='${rows.data_value17 != null}'>${rows.data_value17}</c:if>",
                     	"${fields18}": "<c:if test='${rows.data_value18 != null}'>${rows.data_value18}</c:if>",
                     	"${fields19}": "<c:if test='${rows.data_value19 != null}'>${rows.data_value19}</c:if>",
                     	"${fields20}": "<c:if test='${rows.data_value20 != null}'>${rows.data_value20}</c:if>",
                	"ผู้เพิ่ม": "${rows.data_nameadd }",
                 	"ผู้ตรวจ": "${rows.log_app }",
                 	"วันที่": "${rows.data_due }",
                 	
                 },
                 </c:forEach>
            ],
            {
               
            
            }
        );
    }

    $("#output").find('.pvtVals').hide();
    $("#output").find('.pvtAxisContainer').hide(); 
    $("#output").find('.pvtUiCell').hide();  
    $(".settingf").hide(); 

    function  Setting(){
    	
    	  $("#output").find('.pvtVals').show();
    	    $("#output").find('.pvtAxisContainer').show();   
    	    $("#output").find('.pvtUiCell').show();  
    	    
    	    $("#Setting").hide();  
    	    $("#View").show(); 
    	    $("#Clear").show(); 
    	    $("#Save").show(); 
    	    $(".settingf").show(); 
    }
    
    
   function  View(){
    	
	   $("#output").find('.pvtVals').hide();
	    $("#output").find('.pvtAxisContainer').hide();  
	    $("#output").find('.pvtUiCell').hide();  
	    
	    $(".settingf").hide(); 
	    $("#Setting").show();   
	    $("#Save").hide(); 
	    $("#View").hide(); 
	    $("#Clear").hide(); 
    }
   
   
   
   
   
   
    
   $(window).on('load', function() {
       if (feather) {
           feather.replace({
               width: 14,
               height: 14
           });
       }
   })
    </script>

</body>
</html>