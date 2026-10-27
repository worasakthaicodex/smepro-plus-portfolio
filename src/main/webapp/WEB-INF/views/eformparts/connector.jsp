<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


  <link href="<c:url value="/lib/orgchart/css/jquery.orgchart.css" />" rel="stylesheet">

    <style>
    .title {
    cursor: pointer;
}


    #chart-container {
  font-family: Arial;
  height: 420px;
  border: 2px dashed #aaa;
  border-radius: 5px;
  overflow: auto;
  text-align: center;
}

.orgchart {
  background: #fff; 
}
.orgchart td.left, .orgchart td.right, .orgchart td.top {
  border-color: #aaa;
}
.orgchart td>.down {
  background-color: #aaa;
}
.orgchart .middle-level .title {
  background-color: #006699;
}
.orgchart .middle-level .content {
  border-color: #006699;
}
.orgchart .product-dept .title {
  background-color: #009933;
}
.orgchart .product-dept .content {
  border-color: #009933;
}
.orgchart .rd-dept .title {
  background-color: #993366;
}
.orgchart .rd-dept .content {
  border-color: #993366;
}
.orgchart .pipeline1 .title {
  background-color: #996633;
}
.orgchart .pipeline1 .content {
  border-color: #996633;
}
.orgchart .frontend1 .title {
  background-color: #cc0066;
}
.orgchart .frontend1 .content {
  border-color: #cc0066;
}

#github-link {
  position: fixed;
  top: 0px;
  right: 10px;
  font-size: 3em;
}
    </style>
 <div id="chart-container"></div>

            
            
        
        
  <script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>

  <script type="text/javascript" src="/lib/orgchart/js/jquery.orgchart.js"></script>
    <script>

    'use strict';

    (function($){

      $(function() {

        var datascource = {
          'name': 'ระบบหลัก',
          'title': 'ผังการอัพโหลดบันทึก',
          
          'children': [
        	  <c:forEach items="${upload_pn }" var="row" varStatus="count">
        	  
        	  
            { 'name': '${row.folder_name}', 'title': 'แฟ้ม', 'className': 'middle-level',
              'children': [
            	  <c:forEach items="${upload_pn2 }" var="rows" varStatus="count">
		            	  <c:if test="${rows.folder_name eq row.folder_name}">
		            	  
		            	  
		            	  
		           
		                { 'name': '${rows.upload_pn_name}', 'title': 'เอกสาร', 'className': 'product-dept',
		                    'children': [
		                    	
		                   	  <c:forEach items="${upload_pn3 }" var="rowss" varStatus="count">
			            	  <c:if test="${rowss.folder_name eq row.folder_name  and rows.upload_pn_name eq rowss.upload_pn_name}">
					                      { 'name': '${rowss.upload_pn_app}', 'title': 'ผู้ตรวจ', 'className': 'pipeline1',
							                    'children': [
							                  	  <c:forEach items="${upload_pn4 }" var="rowsss" varStatus="count">
								            	  <c:if test="${rowsss.folder_name eq row.folder_name and rowss.upload_pn_app eq rowsss.upload_pn_app and rows.upload_pn_name eq rowsss.upload_pn_name}">
								                    			  { 'name': '${rowsss.upload_pn_respon}', 'title': 'ผู้อัพโหลด', 'className': 'frontend1' },
								                  </c:if>
									              </c:forEach>    
								                    ] },
	
					                    </c:if>
					               	    </c:forEach>          
					                    
		                    ]
		                  },
		                  
		                  
		                  
		                </c:if>
           	    </c:forEach>
                
                
              
              ]
            },
       	  </c:forEach>	
            
       
          ]
        };

        var oc = $('#chart-container').orgchart({
          'data' : datascource,
          'nodeContent': 'title'
        });
        
        $('.title').click(function() {
        	 var clickedText = $(this).text().trim();
        	    alert(clickedText);
        	});

      });

    })(jQuery);

    
    
 

    </script>      
        