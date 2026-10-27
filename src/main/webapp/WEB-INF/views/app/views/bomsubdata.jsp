<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>


  <link href="https://fonts.googleapis.com/css2?family=IBM+Plex+Sans+Thai:wght@400;600&family=Montserrat:wght@400;600&display=swap" rel="stylesheet">

<!doctype html>

<html>
   
   <head>
   
      <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
      
      <title>Sample of the Dynamic Table in Use </title>
      
       <link href="<c:url value="/lib/Spreadsheet/css/dynamic-table.jquery.css" />" rel="stylesheet">
       <link href="<c:url value="/lib/Spreadsheet/css/jquery-ui.css" />" rel="stylesheet">
   
   
   
 <script src="<c:url value="/lib/Spreadsheet/js/jquery.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/jquery-ui.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/moment.js" />"></script>	  
<script src="<c:url value="/lib/Spreadsheet/js/dynamic-table.jquery.js" />"></script>	  
 <script src="<c:url value="/lib/Spreadsheet/js/dynamic-table-editor.jquery.js" />"></script>	  
              
 
   <style>
.ui-dynamic-table-header-cell{
  background-color: #2B579A;
  color:#fff;
   font-family: 'IBM Plex Sans Thai', 'Montserrat', sans-serif; /* ฟอนต์หลักสำหรับภาษาไทยและอังกฤษ */
}
      </style>
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
        	 	   
                         
        	 		<c:forEach items="${eformfields }"   var="row" varStatus="count">
        
        	 		<c:if test="${row.keymap != null   && fn:contains(action_batch_insert,row.keymap.mainkey) }">  	
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
                             name       : "${row.field_label } ",
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
        	  <c:forEach items="${item_batch }" var="transaction" varStatus="count">
              [	<c:forEach items="${eformfields }"   var="row" varStatus="count">
       		<c:if test="${row.keymap != null   && fn:contains(action_batch_insert,row.keymap.mainkey) }">  	
             	 "${transaction[row.keymap.mainkey]}",
             	 </c:if>
             	 </c:forEach>
             	 ],
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
      
      </script>
      
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
   
   <body>
   
   
   

                       
                        
 

     <div id="sample-grid"  style="width: 100%;"></div>      

         <br>       <br>    <br>    <br>           
 </div>                               
                 
   
   
   
   
   </body>
   
</html>                      
     