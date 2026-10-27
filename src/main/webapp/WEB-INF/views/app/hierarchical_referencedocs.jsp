<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<title>Info</title>
<%@ include file="../common/mainhead.jspf" %>    
<style>
/* === HEADING STYLE #3 === */
.three {
width:100%;
;
}


.three h1 {
  font-size: 16px;
  list-style-type: none;
  margin: 0;
 padding: 6px 8px;
  overflow: hidden;
  border: 1px solid #e7e7e7;
  background-color: #f3f3f3;
}

 
</style>

<div class="mb-1 row">
<div class="col-sm-3"></div>
</div>  
	    <c:set var="reference" value="reference" />
<%@ include file="views/listview.jspf" %> 





<style>
ul.tabs2 {
    margin: 0;
    padding: 0;
    list-style: none;
    border-bottom: 1px solid #eee;
    width: 100%;
    display: flex;
    align-items: flex-end; /* Align tabs at the bottom */
    overflow: hidden; /* Hide overflowing tabs */
    position: relative;
    white-space: nowrap; /* Prevent wrapping */
}

ul.tabs2 li {
    margin: 0 -10px 0 0; /* Negative right margin for overlap */
    cursor: pointer;
    padding: 0px 21px;
    height: 31px;
    line-height: 31px;
    border-top: 1px solid #eee;
    border-left: 1px solid #eee;
    border-bottom: 1px solid #eee;
    background-color: #eee;
    color: #ccc;
    position: relative;
    z-index: 1; /* Default stacking order */
 
}

ul.tabs2 li div {
    transform: skewX(20deg); /* Counter-skew content for readability */
    display: inline-block;
}

.tab_last { 
    border-right: 1px solid #333; 
}

ul.tabs2 li:hover {
    background-color: #ccc;
    color: #333;
    z-index: 2; /* Bring hovered tab forward */
}

ul.tabs2 li.active {
    background-color: #2B579A;
    color: #fff;
    border-bottom: 1px solid #2B579A;
    z-index: 3; /* Active tab on top */
}

.tab_container {
    border: 1px solid #eee;
    border-top: none;
    clear: both;
    width: 100%;
    background: #fff;
    overflow: auto;
}

.tab_content {
    padding: 20px;
    display: none;
}

.tab_drawer_heading { 
    display: none; 
}

@media screen and (max-width: 480px) {
    ul.tabs2 {
        display: none; /* Keep existing mobile behavior */
    }
    .tab_drawer_heading {
        background-color: #ccc;
        color: #fff;
        border-top: 1px solid #333;
        margin: 0;
        padding: 5px 20px;
        display: block;
        cursor: pointer;
        -webkit-touch-callout: none;
        -webkit-user-select: none;
        -khtml-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
    }
    .d_active {
        background-color: #666;
        color: #fff;
    }
}
</style>

 <c:set var="action_batch" value="${action_batch_insert},${action_batch_modify},${action_batch_continue}" />	
 <ul class="tabs2" >
 	<c:if test="${item.size() > 0}">  
	 <li  class="activetab"  id="defaultTab0"  rel="tabed0" >Data</li> 
  </c:if>
  
  
 <c:if test="${doc_join != null  && doc_join != '' }">  
	<c:set var="namesArray" value="${fn:split(doc_join, ',')}" />
		<c:set var="namesArray2" value="${fn:split(doc_join_id, ',')}" />
	<c:forEach var="docjoin"  items="${namesArray}" varStatus="count">
	 <li  class="activetab <c:if test="${count.count == 1}">active</c:if> "  id="defaultTab${count.count}"  rel="tabeds1" 
	 onclick="setActiveTab(this);getdatash('/loadhierarchical-filemanager/${namesArray2[count.count -1 ] }/${doc}','tabeds1')">${docjoin}</li> 
	</c:forEach>
 </c:if>

  
  
</ul>
<div class="tab_container">
  <div id="tabeds0" class="tab_content2" style="width:100%;display:none" > 

   <c:set var="reference" value="reference" />

<c:if test="${action_batch != null }">  

	<c:set var="reference_type" value="${reference_type }" />
				<c:set var="typeshow" value="5" />
				<%@ include file="../app/general_system.jspf" %>                                    
		</c:if>   
  </div>
  <div id="tabeds1" class="tab_content2" style="width:100%" > 

  </div>



</div>




<%@ include file="../common/mainfoot.jspf" %>  
    <script>
 

    $("#defaultTab0").click(function() {
		
    	   $("#tabeds1").html("");	
    	

   $("#tabeds0").show();

	 $(".activetab").removeClass("active");
     $("#defaultTab0").addClass("active");

	  
    });

	    </script>

<script>
    function setActiveTab(clickedTab) {
         $("#tabeds0").hide();
    	
        $(".activetab").removeClass('active');
           clickedTab.classList.add('active');
    }
</script>
 
	 <script>
var defaultTab = document.getElementById("defaultTab1");
 defaultTab.click(); // คลิก tab แรกอัตโนมัติ

 
 
 
 
 
 
 function getdatash(url,div){
		

		let loadingSpinner = `
		    <div class="d-flex justify-content-center align-items-center" style="height: 100%;">
		        <div class="spinner-border text-primary" role="status">
		            <span class="visually-hidden">Loading...</span>
		        </div>
		    </div>`;
			$("#" + div).html(loadingSpinner);
			


					        $.ajax({
					        type:'GET',
					        url:url,
					        data: {ID : 0},
					        
					     success: function(data){
					  			  $("#"+div).html(data);	

					     },
					   error: function(jqXHR, textStatus, errorThrown) {
				             window.location.href = '/error'; // URL ของหน้า login
					   
					  }      
					        
					});
			
			
			 
			 
		return false;

		}
</script>
                    
</div> 

<br><br><br>