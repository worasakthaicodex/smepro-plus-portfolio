<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tenant Setup Mockup (Fixed)</title>
    <style>
        :root {
            --bg-color: #ffffff;
            --text-color: #333;
            --header-blue: #00265a;
            --selection-blue: #0078d7;
            --border-color: #adadad;
            --btn-bg: #e1e1e1;
            --btn-border: #adadad;
            --font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

 
        .chain_window-container {
            width: 100%;
            background-color: white;
            border: 1px solid #999;
            box-shadow: 0 4px 10px rgba(0,0,0,0.2);
            padding: 20px;
            position: relative;
            box-sizing: border-box;
            font-size: 13px;
            cursor: default;
        }

       .chain_window-container h1 { color: var(--header-blue); font-size: 24px; margin: 0 0 20px 0; font-weight: 600; }
       .chain_window-container p.description { margin-bottom: 15px; color: #000; }

        .chain_window-container .toolbar { display: flex; justify-content: space-between; align-items: center; margin-bottom: 5px; }
        .chain_window-container .toolbar label { font-weight: bold; }

      .chain_window-container  button {
            font-family: var(--font-family);
            background-color: var(--btn-bg);
            border: 1px solid var(--btn-border);
            padding: 4px 12px;
            font-size: 12px;
            cursor: pointer;
            min-width: 70px;
        }
            .foredit {
 
            background-color: #fff !important;
            border: none  !important;
            padding: 0px 4px !important;
            font-size: 15px !important;
            cursor: pointer;
            min-width: 30px !important;
        }
        
        
        
      .chain_window-container  button:hover { background-color: #d1d1d1; }

      .chain_window-container  .grid-wrapper {
            border: 1px solid var(--border-color);
            height: 200px;
            overflow: auto; /* Scrollable grid */
            background-color: #fff;
            padding-bottom: 50px; /* Space for dropdown to overflow */
        }

      .chain_window-container  table { width: 100%; border-collapse: collapse; table-layout: fixed; }

      .chain_window-container  th {
            background-color: #f0f0f0;
            border-right: 1px solid #d0d0d0;
            border-bottom: 1px solid #d0d0d0;
            padding: 4px 8px;
            text-align: left;
            font-weight: normal;
            font-size: 12px;
            color: #333;
            height: 24px;
        }

       .chain_window-container td {
            border-right: 1px solid #e0e0e0;
            border-bottom: 1px solid #e0e0e0;
            padding: 0 5px;
            height: 26px;
            vertical-align: middle;
            white-space: nowrap;
    
        }

        /* Column Widths */
     .chain_window-container   th:nth-child(1) { width: 25px; }
     .chain_window-container   th:nth-child(2) { width: 30px; }
      .chain_window-container  th:nth-child(3) { width: 30px; }
     .chain_window-container   th:nth-child(4) { width: 120px; text-align: center; }
     .chain_window-container   th:nth-child(5) { width: 30px; text-align: center;}
      .chain_window-container  th:nth-child(6) { width: 100px; }
      .chain_window-container  th:nth-child(7) { width: 40px; text-align: center; }
.chain_window-container   td:nth-child(5) { width: 30px; text-align: center;}
      .chain_window-container  tr.selected { background-color: var(--selection-blue); color: white; }
     .chain_window-container   tr.selected td { border-right-color: transparent; }

     .chain_window-container   .cell-content { display: flex; align-items: center; width: 100%; height: 100%; }

        /* --- DROPDOWN STYLES (Fixed) --- */
      .chain_window-container  .dropdown-cell {
            background-color: white;
            color: black;
            height: 20px;
            display: flex;
            align-items: center;
            padding-left: 4px;
            border: 1px solid #a0a0a0;
            position: relative; /* Anchor point for the menu */
            width: 90%;
            cursor: pointer;
        }

      .chain_window-container  .dropdown-arrow {
            margin-left: auto;
            background-color: #f0f0f0;
            border-left: 1px solid #a0a0a0;
            width: 16px;
            height: 100%;
            display: flex;
            justify-content: center;
            align-items: center;
            font-size: 10px;
        }

       .chain_window-container .dropdown-overlay {
            display: none; /* Hidden by default */
            position: absolute;
            top: 100%; /* Push to bottom of cell */
            left: -1px; /* Align left border */
            width: 100%; /* Match width of cell */
            background-color: white;
            border: 1px solid #666;
            box-shadow: 2px 2px 5px rgba(0,0,0,0.2);
            z-index: 999;
            font-size: 12px;
            color: black;
            min-width: 100px; /* Minimum width */
        }

      .chain_window-container  .dropdown-overlay.show {
            display: block; /* Show when active */
        }

      .chain_window-container  .dropdown-item { padding: 3px 6px; cursor: pointer; }
      .chain_window-container  .dropdown-item:hover { background-color: #f0f0f0; }
       .chain_window-container .dropdown-item.active { background-color: var(--selection-blue); color: white; }

    .chain_window-container    .row-indicator {
            width: 0; height: 0; 
            border-top: 5px solid transparent; 
            border-bottom: 5px solid transparent; 
            border-left: 6px solid #333; margin: 0 auto;
        }
     .chain_window-container   tr.selected .row-indicator { border-left-color: white; }

      .chain_window-container  .footer-options { margin-top: 10px; display: flex; gap: 15px; align-items: center; }
       .chain_window-container .nav-buttons { display: flex; justify-content: space-between; align-items: center; margin-top: 20px; border-top: 1px solid #e0e0e0; padding-top: 15px; }
      .chain_window-container  .right-nav { display: flex; gap: 10px; }
      .chain_window-container  .btn-primary { border: 2px solid #0078d7; font-weight: 500; }
     .chain_window-container   .version-info { margin-top: 20px; font-size: 11px; color: #333; }
     .chain_window-container   .version-info a { color: #0066cc; text-decoration: none; }
     
.truncate{
    max-width: 120px;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
}
    </style>
</head>
<body>

    <div class="chain_window-container" >
 
  
        <div class="toolbar">
            <label>สร้างตัวกรองเเบบกำหนดเอง:</label>
            
            <div>

        </div>
    </div>
        <div class="grid-wrapper">
            
            <table>
                <thead>
                    <tr>
                        <th></th><th>ตาราง</th><th>ประเภท</th><th>ค่าที่ใช้</th><th style="">การดำเนินการ</th>
                    </tr>
                </thead>
                
                <tbody id="filterChainBody">
 
            
         
          
                </tbody>
            </table>
             <input type="hidden" id="process_id" name="process_id" value="${item_id}">  
        </div>

        <div class="footer-options">

            <div style="flex-grow: 1;">
             <button  type="button" 
             onclick="openwindow('draggable-window6','Add Filter','9500');getData('/addFilterChain-filemanager', 'mainbody6');">New</button>
            <button  type="button" onclick="deleteSelectedFilters()">Delete</button>
            </div>
           
        </div>

        <div class="nav-buttons">
            <div></div>
            <div class="right-nav">
                <button onclick="closeed2('draggable-window3')">Apply</button>
            </div>
        </div>

        
    </div>

  

</body>
</html>
 <script>
 renderFilterChain();
 </script>