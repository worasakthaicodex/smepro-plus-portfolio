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
            max-height: 380px;
             height: 380px;
            overflow-y:scroll;
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
            width: 150px;
            flex-shrink: 0;
        }

      .quickwindow   input[type="text"],select {
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
       #setquick { 
        padding: 8px;
		}
    </style>
    
    <style>
/* CSS สำหรับปุ่มไอคอนสไตล์ BI */
.bi-icon-btn {
    display: inline-flex;
    align-items: center;
    justify-content: center;
    width: 28px;
    height: 28px;
    background-color: #f4f5f7;
    border: 1px solid transparent;
    border-radius: 4px;
    cursor: pointer;
    margin-left: 6px;
    transition: all 0.2s ease;
    flex-shrink: 0;
}
.bi-icon-btn:hover {
    background-color: #ebecf0;
    border-color: #dfe1e6;
}
.bi-icon-btn svg {
    width: 18px;
    height: 18px;
    fill: #505f79;
}
</style>
</head>
<body>

<div class="quickwindow">

                  <form id="setquick" name="setquick">   

    <div id="dynamicQuickEditContainer"></div>
</form>
    <div class="footer">
        <button type="button" class="primary" onclick="injectQuickFiltersToJS();closeed2('draggable-window3')">Apply</button>
    </div>
    

</div>

</body>
</html>


<script>

renderQuickEditFromJS(false);
</script>