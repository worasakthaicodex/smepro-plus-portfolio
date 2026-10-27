  <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
 
 <link href="https://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">


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
}

</style>


	  <div  style="overflow:hidden;width:99%;height:580px;border:2px solid #F1EFEF">
			        <iframe src="/root2-filemanager" id = "textareathingy" class="iframe-container"  width="100%" height="580px"  frameborder="0" allowfullscreen scrolling="no" ></iframe>
	</div>							

<div class="leaflet-bottom leaflet-right"><div class="leaflet-control-zoom leaflet-bar leaflet-control">
<a class="leaflet-control-zoom-in" href="#" title="Zoom in"   onclick = "zoom(0.1)">+</a>
<a class="leaflet-control-zoom-out" href="#" title="Zoom out" onclick = "zoom(-0.1)">−</a></div></div>


<script src="https://code.jquery.com/jquery-1.9.0.min.js" type="text/javascript"></script>



    <script type="text/javascript">
    
    



    var scale = 1.0;

    $('#textareathingy').on('wheel', function(event) {
    	
    	
        var delta = event.originalEvent.deltaY;

        if (delta > 0) {
            // เมื่อเมาส์เลื่อนลง (zoom out)
            zoom(-0.1); // คุณสามารถปรับตัวแปรนี้ตามความต้องการ
        } else {
            // เมื่อเมาส์เลื่อนขึ้น (zoom in)
            zoom(0.1); // คุณสามารถปรับตัวแปรนี้ตามความต้องการ
        }
    });

    function zoom(scales) {
    	
        scale += scales;
        if (scale > 0.5 && scale < 20) {
            $('#textareathingy').css({
                'transform': 'scale(' + scale + ')',
                'transform-origin': '0 0'
            });
        }
    }





</script>