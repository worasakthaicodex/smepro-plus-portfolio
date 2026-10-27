<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ include file="../common/mainhead.jspf" %>    




<title>List</title>
        <link href="<c:url value="/lib/Spreadsheet/css/dynamic-table.jquery.css" />" rel="stylesheet">
       <link href="<c:url value="/lib/Spreadsheet/css/jquery-ui.css" />" rel="stylesheet">
   
   
   

              
   
   <style>
.ui-dynamic-table-header-cell{
  background-color: #2B579A;
  color:#fff;
   font-family: 'IBM Plex Sans Thai', 'Montserrat', sans-serif; /* ฟอนต์หลักสำหรับภาษาไทยและอังกฤษ */
}
      </style>
    
      
      <style type="text/css">
      
         body {
            font-family: sans-serif;
            font-size: 14px;
            margin: 0px;
            padding: 20px;
            background-color: #666666;
         }
         
         #sample-grid {
            width: 100%; 
            height: 500px;
         }
      
         #selected-data,
         #save-data {
            width: 700px; 
            background-color: #f3f3f3;
            padding: 5px;
            margin-top: 10px;
            border-radius: 5px;
            box-sizing:border-box;
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
          <section id="multiple-column-form">
                    <div class="row">
                  
                   <div class="col-12">
                   
                   <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">	<spring:message code="menu.Oversee" /></h4>
                                 
                      
                            </div>
                             
                            <div class="card-body">
           
   
   <body>
   
   
                 
 

     <div id="sample-grid"  style="width: 100%;"></div>      

         <br>       <br>    <br>    <br>           
 </div>                               
                 
   
                                </div>
                  
              
                  
                  
                  
                  
                   </div>   </div>
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
 
 <script src="<c:url value="/lib/Spreadsheet/js/jquery.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/jquery-ui.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/moment.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/dynamic-table.jquery.js" />"></script>	  
 <script src="<c:url value="/lib/Spreadsheet/js/dynamic-table-editor.jquery.js" />"></script>	  
    
    
    <script>
    
    var element = document.getElementById("nav_Traceability");
    element.classList.add("active");  
    </script>
    
   
</body>



  <script>
      
      $(function() {
         
         // Initialize table
         $("#sample-grid").dynamicTable({
            fillParent : false,
            showCounter: true,
            changeColumns : true
         });
         
         // Define columns
         var myColumns = [
        	 	   
	        	   {// Searchable text column
	                   name       : "Status",
	                   type       : "string",
	                   visible    : true,
	                   filterType : "search",
	                   width      : 200
	                },   
        	 		<c:forEach items="${eformfields }"   var="row" varStatus="count">
        	 		
        	 	 
        	 		<c:if test="${row.keymap != null   }">  
		        	 	
        	 		 <c:choose>
        	 		 
        	 		
           	 		
        	 		  <c:when test='${row.keymap.change_format == "NUMBER" }'>
                      {  // Hidden Identifier column
                          name       : "${row.field_label }",
                          type       : "number",
                          visible    : true
                       },
        	 		   </c:when> 
                       
                 	  <c:when test='${row.keymap.change_format == "Enum" }'>
                 	 {// Limited values with default picklist filter 
                         name       : "${row.field_label }",
                         type       : "string",
                         visible    : true,
                      },
        	 		   </c:when> 
                       
                 	  <c:when test='${row.keymap.change_format == "DATE" }'>
                 	    {// Date column with date filter
                            name       : "${row.field_label }",
                            type       : "date",
                            visible    : true,
                            format     : "d-MMM-YYYY", 
                            filterType : "dateRange",
                            cssClass   : function (aColumn, aValue, aDisplayValue) {
                               return aValue != null  ? "has-birth-date" : null
                            }
                         },
         	 		   </c:when> 
                      
                         <c:when test='${row.keymap.change_format == "TEXT" }'>
                         { 
                             name       : "${row.field_label }",
                             type       : "string",
                             visible    : true,
                             editor     : $("<div/>").dynamicTableEditor({
                                editHandler: function(aData, aContext) {
                                   $("#save-data").html("Saving note: <strong>" + aData + "</strong>");       
                                }
                             })              
                          },
          	 		   </c:when> 
                       
               	    <c:otherwise>
          	          {// Searchable text column
                             name       : "${row.field_label }",
                             type       : "string",
                             visible    : true,
                             filterType : "search",
                             width      : 200
                          },
          	  		  </c:otherwise>
                      
                          
        	 		</c:choose>   
                      </c:if>
                          </c:forEach>
                          
                          
                          
           
                      
                          
                     
                         
                         ];
         
         
         // Get the data. This would usually be done in an AJAS call
         // ($.getJSON) but for demo purposes we show hardcoded data
    var myData = [
    <c:forEach items="${item_batch}" var="transaction" varStatus="loopStatus">
    [
        <c:choose>
            <c:when test="${transaction['status'] == 'Good'}">
                "<span class='badge rounded-pill bg-success' style='width:20px;'>&nbsp;</span>",
            </c:when>
            <c:when test="${transaction['status'] == 'Normal'}">
                "<span class='badge rounded-pill bg-warning' style='width:20px;'>&nbsp;</span>",
            </c:when>
            <c:when test="${transaction['status'] == 'Bad'}">
                "<span class='badge rounded-pill bg-danger' style='width:20px;'>&nbsp;</span>",
            </c:when>
            <c:otherwise>
                "<span class='badge rounded-pill bg-secondary' style='width:20px;'>&nbsp;</span>",
            </c:otherwise>
        </c:choose>

        <c:forEach items="${eformfields}" var="row" varStatus="fieldStatus">
            <c:if test="${row.keymap != null}">
                "${transaction[row.keymap.mainkey]}"<c:if test="${!fieldStatus.last}">,</c:if>
            </c:if>
        </c:forEach>
    ]<c:if test="${!loopStatus.last}">,</c:if>
    </c:forEach>
];
         
         // Load the data into the grid
         $("#sample-grid").dynamicTable("data", myData, myColumns);
         
         setTimeout(function () {
        	    adjustDynamicTableWidth();
        	}, 100);

      });
      
      
      function adjustDynamicTableWidth() {
    	    let header = $(".ui-dynamic-table-header");
    	    let body = $("#sample-grid");

    	    // บังคับให้ header กว้างเท่า body
    	    if (header.length && body.length) {
    	        header.width(body.width());
    	    }
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
