<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<td class="ant-table-cell" colspan="20">
    <div class="ant-table-wrapper css-xepvsj">
     <div class="ant-spin-nested-loading css-xepvsj">
      <div class="ant-spin-container">
       <div class="ant-table ant-table-bordered css-xepvsj">
        <div class="ant-table-container">
         <div class="ant-table-content" style="max-height:450px;overflow-y:auto">
          <table style="table-layout: auto;" class="table-wrapper">
           <colgroup>
            <col class="ant-table-expand-icon-col">
           </colgroup>
           
           <thead class="ant-table-thead">
  
            <tr >
             <th class="ant-table-cell ant-table-row-expand-icon-cell" style="background-color: #E6F7FF !important;color: #333;"></th>
           <c:forEach items="${clHead }"   var="row" varStatus="counts">                 
             <th class="ant-table-cell"  scope="col" style="background-color: #E6F7FF !important;color: #333;">${row.customizeName}</th>
            </c:forEach>
             </th>

             <th class="ant-table-cell" scope="col" style="background-color: #E6F7FF !important;color: #333;">Action</th>
             
            </tr>
           </thead>
           <tbody class="ant-table-tbody">
           
     
   <c:forEach items="${exampleData }"   var="row" varStatus="counts">      
            <tr class="ant-table-row ant-table-row-level-0 job-row" data-row-key="0" id="SUBCL-${row.id}">
             <td class="ant-table-cell ant-table-row-expand-icon-cell">
             ${counts.count }
             </td>
             
            <c:forEach items="${clHead }"   var="rows" varStatus="counts">          
             <td class="ant-table-cell" id="${rows.columnName}-${row.id}">${row[rows.columnName] }</td>
             </c:forEach>
             
                  
             <td>
             <c:set var="thisCodeCustomer" value="${row.register_code }${row.register_daterun }-${row.register_id }" />
             
    <button type="button" class="btn btn-modern ant-btn css-xepvsj ant-btn-default ant-btn-color-default ant-btn-variant-outlined"
     onclick="
     callBack('/ShortcutClOnerow-filemanager/${data_id}/${process.id }/${clcode }/${thisTab.id }/${row.id }/${nextTab.id}','SUBCL-${row.id}');
     openAppAndLoadData('${thisCodeCustomer}', '${thisTab.shortcut_profile_app}','${data_id}');
     ">
     <span><img src="/app/system/icon/preview.ico"></span>
    </button> 
    
    
        <button type="button" class="btn btn-modern ant-btn css-xepvsj ant-btn-default ant-btn-color-default ant-btn-variant-outlined"
     onclick="callBack('/ShortcutClOnerow-filemanager/${data_id}/${process.id }/${clcode }/${thisTab.id }/${row.id }/${nextTab.id}','SUBCL-${row.id}');
     getData('/calendar-filemanager/${process.id}?edata_id=${rows.data_id}&&index_id=${row.id}', 'mainbody3');shows2('draggable-window3','Calendar');">
     <span><img src="/app/system/icon/calendar.ico"></span>
    </button> 
    
<c:if test="${nextTab != null}">
        <button type="button" class="btn btn-modern ant-btn css-xepvsj ant-btn-default ant-btn-color-default ant-btn-variant-outlined"
     onclick="openAppAndLoadData2('${nextTab.id}', 'Cl${data_id}-${row.id }','-', '${_csrf.parameterName}=${_csrf.token}');stopCallBack()">
     <span><img src="/app/system/icon/plus.ico"></span>
    </button> 
    <form name="newfile-Cl${data_id}-${row.id }" id="newfile-Cl${data_id}-${row.id }" style="display:none;">  
            <input type="text" name="name_file" value="${nextTab.name}_Undefined">
            <input type="text" name="customerCode" value="${thisCodeCustomer}">
            <input type="text" name="relation_id" value="${data_id}">
            <input type="text" name="tab_id" value="${thisTab.id}">
            <input type="text" name="app_processes_id" value="${nextTab.id}">
     </form>
    </c:if>
             </td>
             
       
            </tr>
           </c:forEach> 
            
            
            
  
            
        </tbody>
          </table>
         </div>
        </div>
       </div>
      </div>
     </div>
    </div>
   </td>