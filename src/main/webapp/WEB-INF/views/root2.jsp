  <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">
 <style type="text/css">
   
.tree ul {
  padding-top: 20px; position: relative;

  transition: all 2s;
  -webkit-transition: all 0.5s;
  -moz-transition: all 2s;
}

.tree li {
  float: left; text-align: center;
  list-style-type: none;
  position: relative;
  padding: 20px 5px 0 5px;

  transition: all 0.5s;
  -webkit-transition: all 0.5s;
  -moz-transition: all 2s;
}

/*We will use ::before and ::after to draw the connectors*/

.tree li::before, .tree li::after{
  content: '';
  position: absolute; top: 0; right: 50%;
  border-top: 1px solid #ccc;
  width: 50%; height: 20px;
}
.tree li::after{
  right: auto; left: 50%;
  border-left: 1px solid #ccc;
}

/*We need to remove left-right connectors from elements without 
any siblings*/
.tree li:only-child::after, .tree li:only-child::before {
  display: none;
}

/*Remove space from the top of single children*/
.tree li:only-child{ padding-top: 0;}

/*Remove left connector from first child and 
right connector from last child*/
.tree li:first-child::before, .tree li:last-child::after{
  border: 0 none;
}
/*Adding back the vertical connector to the last nodes*/
.tree li:last-child::before{
  border-right: 1px solid #ccc;
  border-radius: 0 5px 0 0;
  -webkit-border-radius: 0 5px 0 0;
  -moz-border-radius: 0 5px 0 0;
}
.tree li:first-child::after{
  border-radius: 5px 0 0 0;
  -webkit-border-radius: 5px 0 0 0;
  -moz-border-radius: 5px 0 0 0;
}

/*Time to add downward connectors from parents*/
.tree ul ul::before{
  content: '';
  position: absolute; top: 0; left: 50%;
  border-left: 1px solid #ccc;
  width: 0; height: 20px;
}

.tree li a{
  border: 1px solid #ccc;
  padding: 5px 10px;
  text-decoration: none;
  color: #666;
  font-family: arial, verdana, tahoma;
  font-size: 1em;
  display: inline-block;

  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
background-color: lightgray;
  transition: all 0.5s;
  -webkit-transition: all 0.5s;
  -moz-transition: all 2s;
}

/*Time for some hover effects*/
/*We will apply the hover effect the the lineage of the element also*/
.tree li a:hover, .tree li a:hover+ul li a {
  background: #d1c4e9; color: #000; border: 1px solid #d1c4e9;
}
/*Connector styles on hover*/
.tree li a:hover+ul li::after, 
.tree li a:hover+ul li::before, 
.tree li a:hover+ul::before, 
.tree li a:hover+ul ul::before{
  border-color:  #94a0b4;
}






body {
 cursor: grab;
     overflow: hidden;  
}

body:active {
    cursor: grabbing;
}

ul ul {
  display: none;
}

/* แสดงรายการลูกและหลานเมื่อคลิกที่ node */
ul ul.show {
  display: block;
}

    </style>
    
<style>
.leaflet-bottom {
    bottom: 0;
}
.leaflet-right {
    right: 0;
}
.leaflet-top, .leaflet-bottom {
    position: absolute;
    z-index: 1000;

}
*, *::before, *::after {
    -webkit-box-sizing: border-box;
    box-sizing: border-box;
}

div {
    display: block;
}
.leaflet-locpicker-map .leaflet-container {
    position: absolute;
    top: 5px;
    right: 5px;
    bottom: 5px;
    left: 5px;
    cursor: crosshair;
    border: 1px solid rgba(100,100,100,0.2);
}
.leaflet-container {
    font: 12px/1.5 "Helvetica Neue", Arial, Helvetica, sans-serif;
}
.leaflet-grab {
    cursor: -webkit-grab;
    cursor: -moz-grab;
    cursor: grab;
}

.leaflet-container {
    -webkit-tap-highlight-color: transparent;
}


.leaflet-locpicker-map .leaflet-bar a:first-child {
    border-top-left-radius: 0;
    border-top-right-radius: 0;
}
.leaflet-bar a:first-child {
    border-top-left-radius: 4px;
    border-top-right-radius: 4px;
}
.leaflet-bar a, .leaflet-control-layers-toggle {
    background-position: 50% 50%;
    background-repeat: no-repeat;
    display: block;
}
.leaflet-bar a, .leaflet-bar a:hover {
    background-color: #fff;
    border-bottom: 1px solid #ccc;
    width: 50px;
    height: 50px;
    line-height: 50px;
    display: block;
    text-align: center;
    text-decoration: none;
    color: blue;
}


body {
    background-color: #F0F0F0;

    margin: 0;
    padding: 0;
}

.title-bar {
    background-color: #90AFC5; /* น้ำเงินอ่อน */
    color: white;
    text-align: center;
    padding: 1rem;
    position: fixed;
    width: 100%;
    top: 0;
    z-index: 1000;
    /* กำหนดสไตล์ของ title-bar ตามที่คุณต้องการ */
}

p{
font-size:10px;
}

</style>
</head>
<div class="title-bar">
ระบุตำเเหน่ง : <select id="Groups">
      <option value="0">ไม่ระบุ</option>   
   <c:forEach items="${Groups}" var="rows" > 

   <option value="${rows.id}">${rows.group_name}</option>
  </c:forEach>
  </select>
</div>
  
  
<div class="tree"  id="trees" style="width:100%;height:800px;overflow:hidden;margin-top:30px">
<div class="tree"  id="trees2" style="width:10000px;height:100%;">
  <ul >
    <li class="levle0">
      <a href="javascript:void(0)"  class="node">ROOT</a>
      <ul class="subnode">
      
       <c:forEach items="${drivess}" var="row" varStatus="count">
        <li class="levle1 subnode">
          <a href="javascript:void(0)"  class="node auth-${row.drives_drive   } nodes">${row.drives_name}
               <p id="authorities-${row.drives_drive }"  class="authorities" style="display:none">Authorities : </p> 
          </a>
       
          <ul class="subnode">
          
          <c:forEach items="${folders}" var="rows">    
			<c:if test="${rows.folder_idrefer == row.drives_id   }">      
		            <li class="levle2 ">
		              <a href="javascript:void(0)"  class="node auth-${rows.folder_type  } nodes">${rows.folder_name}
		              
		                    <c:if test="${rows.folder_doc > 0  }">      
                        <p>Quality Documents : ${rows.folder_doc}</p>
                        </c:if>
                            <c:if test="${rows.folder_work > 0  }">      
                        <p>Work : ${rows.folder_work}</p>
                        </c:if>
                            <p id="authorities-${rows.folder_type}"  class="authorities"   style="display:none">Authorities : </p> 
		              </a>
		         
		              <ul class="subnode">
		                <c:forEach items="${folders}" var="rowss">    
						<c:if test="${rowss.folder_idrefer == rows.folder_id }">   
                    <li class="levle3 "  >
                    
                    <a href="javascript:void(0)"  class="node auth-${rowss.folder_type  } nodes" >${rowss.folder_name} 
                    <c:if test="${rowss.folder_doc > 0  }">      
                        <p>Quality Documents : ${rowss.folder_doc}</p>
                        </c:if>
                            <c:if test="${rowss.folder_work > 0  }">      
                        <p>Work : ${rowss.folder_work}</p>
                        </c:if>
                           <p id="authorities-${rowss.folder_type}"  class="authorities"   style="display:none">Authorities : </p> 
                    </a>
                
                    </li>
                    </c:if>
                    </c:forEach>
                    </ul>
		         
		            </li>
                 </c:if> 	
             </c:forEach>  
            
          </ul>
        </li>
       </c:forEach>

        
      
      </ul>
    </li>
  </ul>
</div>
</div>

<div class="leaflet-bottom leaflet-right"><div class="leaflet-control-zoom leaflet-bar leaflet-control">
<a class="leaflet-control-zoom-in" href="#" title="Zoom in"   onclick = "zoom(0.1)">+</a>
<a class="leaflet-control-zoom-out" href="#" title="Zoom out" onclick = "zoom(-0.1)">−</a>
<a class="leaflet-control-zoom-out" href="#" title="Zoom out" onclick = "show()">Show</a>
<a class="leaflet-control-zoom-out" href="#" title="Zoom out" onclick = "unshow()">Hide</a>
</div></div>

     <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>



    <script type="text/javascript">
 
    
    document.addEventListener("DOMContentLoaded", function() {
  	  var nodes = document.querySelectorAll(".node");

  	  nodes.forEach(function(node) {
  	    node.addEventListener("click", function() {
  	      var childUl = this.nextElementSibling;
  	      if (childUl) {
  	        childUl.classList.toggle("show");
  	      }
  	    });
  	  });
  	});
  
  
  document.addEventListener("DOMContentLoaded", function() {
      var liElements = document.querySelectorAll("li");

      liElements.forEach(function(li) {
          var link = li.querySelector("a");
          var hasNestedUl = li.querySelector("ul");
          var hasNestedLi = li.querySelector("ul li");

          if (hasNestedUl && hasNestedLi) {
              // ถ้า <li> นี้มีลูก <ul> และลูก <ul> มีลูก <li>
          	link.style.backgroundColor = "#dbffbe"; 
          }
      });
  });
  
    
    
    
    document.getElementById("Groups").onchange = function() {
    	
        var selectedValue = this.value;
        
       
        postdatas('/rootgroups-filemanager',selectedValue);
    };

    
    
    function show() {
        $(".subnode").addClass("show");
    }
    function unshow() {
        $('.subnode').removeClass("show");
    }


    var scale = 1.0;

    function zoom(scales) {
    	
        scale += scales;
        if (scale > 0.2 && scale < 20) {
            $('#trees2').css({
                'transform': 'scale(' + scale + ')',
                'transform-origin': '0 0'
            });
        }
    }





</script>




    <script type="text/javascript">
    
    
    
    

    var action = true;
	function postdatas(Mode,ID) {

	
	        $.ajax({
	        type:'GET',
	        url:Mode,
	        data: {id : ID},
	       success: function(data){

	    	    var READ = data;
	    	    var parts = READ.split(',');
	  	
    	    	
    	    	
    	    	if(action == false){
    	    		var nodes = document.querySelectorAll('.nodes');

    	    		nodes.forEach(function(node) {
    	    		    node.style.borderColor = '#ccc';
    	    		}); // ตั้งสีขอบเป็นน้ำเงิน
    	    		
    	    		
    	    		var nodes2 = document.querySelectorAll('.authorities');

    	    		nodes2.forEach(function(nodes2) {
    	    			nodes2.textContent = "Authorities : "; // กำหนดข้อความ "Authorities: READ"
    	    			nodes2.style.display = 'none'; // แสดงข้อความ
    	    		}); // ตั้งสีขอบเป็นน้ำเงิน
    	    		
    	    		 action = true;
 
    	    	}
    	    	
    	    	
	    	    if(action == true){
			    	    for (var i = 0; i < parts.length; i++) {
			    	    	
			    	    	var partsed = parts[i].split('-'); 
			    	    	var keyword = partsed[0]; 
			    	    	var folder = partsed[1]; 
			    	    	
			    	    	
			    	   	
					    		
					    	   		 var authFolder35 = document.querySelector('.auth-'+folder);
				 			        if (authFolder35) {
				 					        authFolder35.style.borderColor = 'blue'; // ตั้งสีขอบเป็นน้ำเงิน
				 					        var authoritiesFolder35 = document.getElementById('authorities-'+folder);
				 					        authoritiesFolder35.textContent = authoritiesFolder35.textContent+" "+keyword; // กำหนดข้อความ "Authorities: READ"
				 					        authoritiesFolder35.style.display = 'block'; // แสดงข้อความ
				 			        } 
			    				 
			    			 }		       
	    	    } 
	    	    
	    	    
	    	    action = false;
	     }
	});
	
	return false;
	    
	}
   
    
    
    
    
    
    


    
    var clicking = false;
    var previousX;
    var previousY;
    var treesElement = document.getElementById('trees');

    $(document).on('mousedown', '#trees', function(e) {
        e.preventDefault();
        previousX = e.clientX;
        previousY = e.clientY;
        clicking = true;
    });

    window.addEventListener('mouseup', function() {
        clicking = false;
    });

    $(document).on('mousemove', function(e) {
        if (clicking) {
            e.preventDefault();
            var currentX = e.clientX;
            var currentY = e.clientY;
            var deltaX = currentX - previousX;
            var deltaY = currentY - previousY;
            treesElement.scrollLeft -= deltaX;
            treesElement.scrollTop -= deltaY;
            previousX = currentX;
            previousY = currentY;
        }
    });



    
    
    </script>