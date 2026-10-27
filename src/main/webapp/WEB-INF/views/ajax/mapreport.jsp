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

  		
								
                           

             
                               <div class="row">
                            <!-- Blog -->
                            <div class="col-12" id="maindata">

        
   <div  style="min-height:450px" >
                                      
 
                       <form method="POST"  action="/mapkey-filemanager">
                               
                       <input  type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">  
                       <input type="hidden" name="id" value="${id }" required>
                       <input type="hidden" name="id2" value="${id2 }" required>
                       
                       <input type="hidden" name="mapping_id" value="${mapping_id }" required>
                       
                       
                                                                    <button  class="me-50 btn btn-sm  btn-success notprint" style="float:right;margin:10px;">  
	                                                 Save
	                                                 </button >
                                             <table id="tabledata" class="tabledata" style="height:auto;width:95%;margin:auto" >
                            
                             			<tr>	
                             			
                                       <th>${conditions2.condition_name } : คอลัมท์</th> 
                                       <th>${conditions1.condition_name } : คอลัมท์</th>
                       				</tr>
                             <c:forEach items="${eformfields2 }" var="row" varStatus="count" >     
                              <c:set var="key" value="" />  
                                <c:if test='${count.count == 1}'>
                               		<c:set var="key" value="${key1}" />  
                               </c:if>
                                     <c:if test='${count.count == 2}'>
                               		<c:set var="key" value="${key2}" />  
                               </c:if>
                                     <c:if test='${count.count == 3}'>
                               		<c:set var="key" value="${key3}" />  
                               </c:if>
                                     <c:if test='${count.count == 4}'>
                               		<c:set var="key" value="${key4}" />  
                               </c:if>
                                     <c:if test='${count.count == 5}'>
                               		<c:set var="key" value="${key5}" />  
                               </c:if>
                                     <c:if test='${count.count == 6}'>
                               		<c:set var="key" value="${key6}" />  
                               </c:if>
                                     <c:if test='${count.count == 7}'>
                               		<c:set var="key" value="${key7}" />  
                               </c:if>
                                     <c:if test='${count.count == 8}'>
                               		<c:set var="key" value="${key8}" />  
                               </c:if>
                                     <c:if test='${count.count == 9}'>
                               		<c:set var="key" value="${key9}" />  
                               </c:if>
                                      <c:if test='${count.count == 10}'>
                               		<c:set var="key" value="${key10}" />  
                               </c:if>
                               
                            	    <tr>	
                                       <td>${row.field_label }</td>
                                        <td>
                                        <select name="mapping_key${count.count}">
                                            <option value="0" <c:if test='${key > eformfields2.size() }'>selected</c:if>>เลือกคอลัมท์อัตโนมัติ</option>
                                              <option value="-" <c:if test='${key == "-"}'>selected</c:if>>ไม่ระบุ</option>
                                         <c:forEach items="${eformfields }" var="rows" varStatus="counts">
                                         <option value="${counts.count}" <c:if test='${key == counts.count}'>selected</c:if>>${rows.field_label } </option>    
                                         </c:forEach>    
                                       
                                        </select></td>
                       				</tr>
                               </c:forEach>  
                                
                                 </table>            
                               
                                      </form >
                                          </div>     
                                        
                                        
                                        <br>  <br>
                                        
                            
                            
                                    
                            
                            
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


   

   evaluateAndDisplay();
   evaluateAndDisplay2();
   sum();
   
   document.getElementById('printButton').addEventListener('click', function() {
	    window.print(); // เรียกใช้งานการพิมพ์
	  });
 </script>


 
	    