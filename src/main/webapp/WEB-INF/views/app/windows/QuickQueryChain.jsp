<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>	 
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Image Attributes Replica</title>
    <style>
        :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --text-color: #000;
            --highlight-blue: #3399ff;
            --input-border: #7a7a7a;
            --light-border: #fff;
        }


        /* Main Window */
        .quickwindow {
            width: 100%;
            background-color: var(--bg-color);
            border: 1px solid #999;
            box-shadow: 2px 2px 10px rgba(0,0,0,0.3);
            padding: 8px;
            box-sizing: border-box;
            position: relative;
        }

       .quickwindow  h1.title {
            font-size: 12px;
            margin: 0 0 10px 0;
            font-weight: normal;
        }

        /* Tabs */
        .quickwindow .tabs {
            display: flex;
            margin-bottom: -1px; /* Overlap border */
            padding-left: 2px;
        }

        .quickwindow .tab {
            padding: 4px 8px;
            background-color: var(--bg-color);
            border: 1px solid var(--bg-color);
            cursor: pointer;
            margin-right: 2px;
            position: relative;
            top: 1px;
        }

        .quickwindow .tab.active {
            background-color: #fff;
            border: 1px solid #999;
            border-bottom: 1px solid #fff; /* Hide bottom border */
            z-index: 2;
        }

        .quickwindow .tab:hover:not(.active) {
            background-color: #e5e5e5;
        }

        /* Content Panel */
        .quickwindow .panel {
            background-color: #fff;
            border: 1px solid #999;
            padding: 15px;
            height: 380px;
            position: relative;
            z-index: 1;
        }

        /* Section Header with Line */
      .quickwindow .section-header {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
            white-space: nowrap;
        }

       .quickwindow  .section-header span {
            margin-right: 5px;
        }

        .quickwindow .section-header .line {
            width: 100%;
            height: 1px;
            background-color: #a0a0a0;
            margin-top: 4px;
        }

        /* Form Elements */
       .quickwindow  .radio-group {
            margin-left: 10px;
            margin-bottom: 15px;
            display: flex;
            flex-direction: column;
            gap: 6px;
        }

       .quickwindow  .radio-row {
            display: flex;
            align-items: center;
        }

       .quickwindow  input[type="radio"] {
            margin: 0 6px 0 0;
        }

        .quickwindow .form-row {
            display: flex;
            align-items: center;
            margin-bottom: 8px;
        }

       .quickwindow  .label {
            width: 120px;
            flex-shrink: 0;
        }

      .quickwindow   input[type="text"] {
            flex-grow: 1;
          
        
        }

        /* Custom Dropdown Simulation */
        .quickwindow .dropdown-container {
            flex-grow: 1;
            position: relative;
        }

       .quickwindow  .fake-select {
            border: 1px solid #3399ff; /* Blue border like active */
            padding: 3px;
            background: #fff;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        
       .quickwindow  .fake-select::after {
            content: "▼";
            font-size: 8px;
            margin-left: 5px;
        }

        /* The Open List */
     .quickwindow    .dropdown-list {
            position: absolute;
            top: 100%;
            left: 0;
            width: 100%;
            background: #fff;
            border: 1px solid #666;
            list-style: none;
            padding: 0;
            margin: 0;
            box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
            z-index: 100;
            max-height: 200px;
            overflow-y: auto;
        }

      .quickwindow   .dropdown-list li {
            padding: 2px 5px;
            cursor: pointer;
        }

       .quickwindow  .dropdown-list li:hover {
            background-color: #3399ff;
            color: white;
        }

     .quickwindow    .dropdown-list li.selected {
            background-color: #3399ff;
            color: white;
            border: 1px dotted #fff;
        }

        /* Footer Buttons */
      .quickwindow   .footer {
            display: flex;
            justify-content: flex-end;
            margin-top: 15px;
            gap: 10px;
        }

      .quickwindow   button {
            min-width: 75px;
            padding: 4px 10px;
            background-color: #e1e1e1;
            border: 1px solid #adadad;
            font-size: 12px;
            cursor: pointer;
        }
        
       .quickwindow  button:hover {
            border-color: #0078d7;
            background-color: #e5f1fb;
        }

     .quickwindow   button.primary {
            border: 1px solid #0078d7;
            box-shadow: inset 0 0 0 1px #8dcce8;
        }

    </style>
</head>
<body>

<div class="quickwindow">

                  <form id="setquick" name="setquick">   

    <div class="tabs">
        <c:forEach items="${queryChains}" var="row" varStatus="count">
            <div class="tab ${count.count == 1 ? 'active' : ''}"  id="tabChains-${count.count }"
                 onclick="switchtabActiveAndData('Chains','${queryChains.size()}','${count.count }')">
                ${row.type}
            </div>
        </c:forEach>
    </div>

    <c:forEach items="${queryChains}" var="chain" varStatus="loop">
        
        <div id="showChains-${loop.count}" class="panel" style="${loop.count == 1 ? '' : 'display:none;'}">
            
            <c:if test="${not empty chain.filterDisplayItem}">
                
                <div class="section-header">
                    <span>Condition Group 1</span>
                    <div class="line"></div>
                </div>

                <c:forEach items="${chain.filterDisplayItem}" var="item">
                    
                    <c:if test="${item.type == 'OPERATOR'}">
                        <c:choose>
                            <c:when test="${item.operator == 'OR'}">
                                <div class="section-header" style="margin-top: 15px;">
                                    <span>OR (New Condition Group)</span>
                                    <div class="line"></div>
                                </div>
                            </c:when>
                            <c:when test="${item.operator == 'AND'}">
                            
                                </c:when>
                        </c:choose>
                    </c:if>

                <c:if test="${item.type == 'RULE'}">
    <c:set var="rule" value="${item.ruleData}" />
    
    <div class="form-row">
        <span class="label">${rule.column_tag}:</span>

        <c:set var="op" value="${rule.operation}" />
        <c:set var="isNoValueCondition" value="${fn:containsIgnoreCase(op, 'null') || fn:containsIgnoreCase(op, 'empty') || fn:containsIgnoreCase(op, 'passed')}" />

        <c:choose>
            
            <c:when test="${isNoValueCondition}">
                <input type="text"  
                       readonly 
                       style="background-color: #f0f0f0; color: #888; cursor: not-allowed; font-style: italic;" placeholder="${rule.operation}">
                     
                   
    				   
            </c:when>

            <c:otherwise>
                <c:choose>
                    <c:when test="${rule.type == 'date'}">
                        <input type="date" name="field" value="${rule.value}" placeholder="${rule.operation}">
                            <input type="hidden" 
       					name="type" 
    				   value="${rule.column_id}">
    				     <input type="hidden" 
       					name="id_refer" 
    				   value="${chain.id}">
                    </c:when>

                    <c:when test="${rule.type == 'number'}">
                        <input type="number" name="field" value="${rule.value}" placeholder="${rule.operation}">
                            <input type="hidden" 
       					name="type" 
    				   value="${rule.column_id}">
    				     <input type="hidden" 
       					name="id_refer" 
    				   value="${chain.id}">
                    </c:when>

                    <c:when test="${rule.type == 'select' || rule.type == 'select_multiple'}">
                    
                    <c:set var="selectedWithComma" value="|${rule.value}|" />
                    
                       <select id="sel_${rule.id}" 
            ${rule.type == 'select_multiple' ? 'multiple' : ''} 
            onchange="updateHiddenField('${rule.id}', this)">
                            <option value="-">-- Select (${rule.operation}) --</option>
                            <c:if test="${not empty rule.valueselected}">
                                <c:forEach items="${fn:split(rule.valueselected, ',')}" var="optionStr">
                                    <c:set var="parts" value="${fn:split(optionStr, ':')}" />
                                    <c:set var="optVal" value="${parts[0]}" />
                                    <c:set var="optTxt" value="${fn:length(parts) > 1 ? parts[1] : parts[0]}" />
                                    
                                    <option value="${optVal}" ${fn:contains(selectedWithComma, optVal) ? 'selected' : ''}>
                                        ${optTxt} 
                                    </option>
                                </c:forEach>
                            </c:if>
                        </select>
                        
                        <input type="hidden" id="hid_${rule.id}" name="field" value="${rule.value}">
                               <input type="hidden" 
       					name="type" 
    				   value="${rule.column_id}">
    				     <input type="hidden" 
       					name="id_refer" 
    				   value="${chain.id}">
                    </c:when>
                    
                    <c:otherwise>
                        <input type="text" name="field" value="${rule.value}" placeholder="${rule.operation}">
                               <input type="hidden" 
       					name="type" 
    				   value="${rule.column_id}">
    				     <input type="hidden" 
       					name="id_refer" 
    				   value="${chain.id}">
                    </c:otherwise>
                </c:choose>
            </c:otherwise>
            
        </c:choose>
    </div>
</c:if>
                    
                </c:forEach>
            </c:if>
            
           
            </div>
    </c:forEach>

    <div class="footer">
        <button type="button" class="primary" onclick="postData('/saveQuickQueryChain-filemanager?${_csrf.parameterName}=${_csrf.token}', 'setquick','mainbody3');closeed2('draggable-window3')">Apply</button>
    </div>
    
<input type="hidden" name="config_id" value="${config_id }">
<input type="hidden" name="process_id" value="${process_id }">
</form>
</div>

</body>
</html>