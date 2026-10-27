<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<div class="mb-1 row">
<div class="col-sm-3"></div>
</div>  
	    <c:set var="reference" value="reference" />
<%@ include file="views/listview2.jspf" %> 





 <style>
ul.tabs2 {
	margin: 0;
	padding: 0;
	float: left;
	list-style: none;
	height: 32px;
	border-bottom: 1px solid  #eee;
	width: 100%;
}

ul.tabs2 li {
	float: left;
	margin: 0;
	cursor: pointer;
	padding: 0px 21px;
	height: 31px;
	line-height: 31px;
	border-top: 1px solid #eee;
	border-left: 1px solid #eee;
	border-bottom: 1px solid #eee;
	background-color: #eee;
	color: #ccc;
	overflow: hidden;
	position: relative;
}

.tab_last { border-right: 1px solid #333; }

ul.tabs2 li:hover {
	background-color: #ccc;
	color: #333;
}

ul.tabs2 li.active {
	background-color: #2B579A;
	color: #fff;
	border-bottom: 1px solid #2B579A;
	display: block;
}

.tab_container {
	border: 1px solid #eee;
	border-top: none;
	clear: both;
	float: left;
	width: 100%;
	background: #fff;
	overflow: auto;
}

.tab_content {
	padding: 20px;
	display: none;
}

.tab_drawer_heading { display: none; }

@media screen and (max-width: 480px) {
	.tabs2 {
		display: none;
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



 <ul class="tabs2" >
 
 
 
 

  
	  <c:forEach items="${boms }"  var="row" varStatus="count">
	 <li  onclick=" setActiveTab(this);getdatas('/loadattachs-filemanager/Bom/${bom.id}/${row.id}/${total +0}','tabed1') " 
	  class="activetab <c:if test="${count.count == 1}">active</c:if>"  id="defaultTab${count.count}"  rel="tabed1" >
	 ${row.bom_category}
	 </li> 
	</c:forEach>
 

  
  
</ul>
<div class="tab_container">

  <div id="tabed1" class="tab_content2" style="width:100%" > 

  </div>



</div>

<script>
    function setActiveTab(clickedTab) {
        $(".activetab").removeClass('active');
           clickedTab.classList.add('active');
    }
</script>
 
	 <script>
    var defaultTab = document.getElementById("defaultTab1");
     defaultTab.click(); // คลิก tab แรกอัตโนมัติ


</script>
                    
</div> 

<br><br><br>