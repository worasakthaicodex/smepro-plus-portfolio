<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>



<input type="text" id="search" style="float:right">
<div id="jstree-checkbox_inbox"></div>

   <script> 	
   $(function () {
	   'use strict';

	   var checkboxTree = $('#jstree-checkbox_inbox');

	   if (checkboxTree.length) {
	     checkboxTree.jstree({
	       core: {
	         data: [
	           <c:forEach items="${transaction_sends}" var="row">  	
	             {
	               text: '${row.data_value1} ${row.data_value2}',
	               children: [
	                 {
	                   id: '${row.data_value1}',
	                   text: '${row.created_at} ${row.data_nameadd}',
	                   type: 'css'
	                 }
	               ]
	             },
	           </c:forEach>
	         ]
	       },
	       plugins: ['types', 'checkbox', 'wholerow', 'search'],
	       types: {
	         default: {
	           icon: 'fab fa-css3-alt text-info'
	         },
	         css: {
	           icon: 'fab fa-css3-alt text-info'
	         }
	       },
	       search: {
	         show_only_matches: true, // จะทำการซ่อน node ที่ไม่ตรงกับการค้นหา
	         case_sensitive: false // ค้นหาไม่สนใจตัวพิมพ์ใหญ่เล็ก
	       }
	     });

	     // ฟัง event checkbox เปลี่ยน
	     checkboxTree.on('changed.jstree', function (e, data) {
	       var selectedNodes = [];
	       if (data.selected.length) {
	         for (var i = 0; i < data.selected.length; i++) {
	           var nodeId = data.selected[i];
	           var node = data.instance.get_node(nodeId);
	           if (node.id && !node.id.startsWith('j')) {
	             selectedNodes.push(node.id);
	           }
	         }
	       }
	       $('#selectedIds').val(selectedNodes.join(','));
	     });

	     // การค้นหา
	     $('#search').on('keyup', function () {
	       var query = $(this).val();
	       $('#jstree-checkbox').jstree(true).search(query);
	     });
	   }
	 });
</script> 	

                              