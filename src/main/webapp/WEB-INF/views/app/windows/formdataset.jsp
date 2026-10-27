<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html lang="en"  >

    <div id="body2" class="mainbody2"   >
     
        <style>
      
        .datatable thead th {
      
          position: sticky;
          top: 0;
          font-size: 13px;


    white-space: nowrap;
    font-family: 'Lato', sans-serif;

 
    height: 30px;
    background-repeat: no-repeat;
    color: white;
    text-shadow: #012b4d 2px 2px 2px;
    text-align: center;
    background-image: url("https://www.jqueryscript.net/demo/jQuery-Plugin-For-Draggable-Resizable-Table-Columns-colResizable/img/th.png");

}
table {
  border-collapse: collapse;
    text-indent: initial;
    border-spacing: 2px;
    width: 100%;
}


td{
  text-indent: 5px;
    color: #444;
    border-bottom: 1px solid #bbb;
    border-left: 1px solid #bbb;
    font-size: 13px;
}     
.sorted{
  font-size: 15px;

}      
 
      </style>

<div class="container">

  <div class=" toolbar" style="border-top: 1px solid #808080;display: flex;
    width: 100%;
    justify-content: space-between;">
<div class="btn-group" >

<button class="fa-button dark"  id="bt5" title="add" data-original-title="Img" 
onclick=" shows2('draggable-window6','${datasets.dataset_name } [เพิ่มข้อมูล]');
getData('/adddatasetapp-condition/${datasets.dataset_id}', 'mainbody6')">
                <i class="material-icons text-light">
            
                  post_add
                </i>
               </button>
  
  <span style="float:right;padding: 0;margin-top:-4px" >
  
      </span>
  

    </div>
</div>
</div>

  <div class="datatable_list">
        <table  >
          <thead>
            <tr>
       <th>${datasets.dataset_1 }</th>
	<c:if test="${datasets.dataset_2 != null } ">			<th>${datasets.dataset_2 }</th></c:if>
	
	<c:if test="${datasets.dataset_3 != null } ">	<th>${datasets.dataset_3 }</th></c:if>
		<c:if test="${datasets.dataset_4 != null } ">	<th>${datasets.dataset_4 }</th></c:if>
			<c:if test="${datasets.dataset_5 != null } ">	<th>${datasets.dataset_5 }</th></c:if>

		 		 <th style="width:13%">Action</th>
    		</tr>

                                    </thead>
                                    <tbody>
                         
                                   <c:forEach items="${dataset_data }" var="row" varStatus="count">       
		                                <tr>
				                                <td>${row.dataset_data_title }</ttd>
				                            <c:if test="${row.dataset_data_subtitle1 != null } ">     <td>${row.dataset_data_subtitle1 }</td></c:if>
				                        	<c:if test="${row.dataset_data_subtitle2 != null } "> <td>${row.dataset_data_subtitle2 }</td></c:if>
				                               	<c:if test="${row.dataset_data_subtitle3 != null } ">   <td>${row.dataset_data_subtitle3 }</td></c:if>
				                             	<c:if test="${row.dataset_data_subtitle4 != null } ">    <td>${row.dataset_data_subtitle4 }</td></c:if>
				                  
				                               <td>

     
                                    <form id="myForms${row.dataset_data_id }" name="myForms${row.dataset_data_id }">
                                  	 <input type="hidden" name="id" value="${row.dataset_data_id }">
                                  	 <input type="hidden" name="id_refer" value="${datasets.dataset_id}">
                                        
                                        	<button type="button"  class="fa-button dark"   title="กลับ"  
                                        	  onclick=" shows2('draggable-window6','${datasets.dataset_name } [แก้ไขข้อมูล]');
											getData('/editdatasetapp-condition/${datasets.dataset_id}/${row.dataset_data_id}', 'mainbody6')"
										style="color:black;font-size: 9px;" data-original-title="Bold - Ctrl+B">
									      <span class="material-icons text-light">
									       
									         	 border_color
									        </span>
									    </button>   
                                        
                                        
                                        <button  type="button" class="fa-button dark"  style="color:black" data-original-title="Img" title="ลบ"
											onclick="confirmAndDelete('/deletesubdataset2-condition?${_csrf.parameterName}=${_csrf.token}', 'myForms${row.dataset_data_id }', 'mainbody3')">
									    	    <i class="material-icons text-danger">delete</i>
										</button>
									</form>	       
										       
				                               </td>
		                                </tr>
		           </c:forEach>   
       
        </tbody>
        </table>
      
</div>

      </div>
     
        
    