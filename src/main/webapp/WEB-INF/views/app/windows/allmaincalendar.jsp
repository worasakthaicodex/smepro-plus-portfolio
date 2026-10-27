<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<!DOCTYPE html>
<html lang="th">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointment and Event Details</title>
    <style>
        :root {
            --bg-color: #f0f0f0;
            --border-color: #d9d9d9;
            --input-border: #7a7a7a;
            --focus-border: #0078d7;
            --text-color: #222;
            --font-stack: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

 
/* 1. ตรึง Toolbar ไว้บนสุด */
.toolbar {
    position: sticky;
    top: 0;
    z-index: 100;      /* ให้อยู่เหนือชั้นอื่นๆ */
    background: #fff;  /* ต้องใส่สีพื้นหลัง ไม่งั้นเนื้อหาจะทะลุมาข้างหลัง */
}

/* 2. ตรึงหัวตารางไว้ใต้ Toolbar */
/* สมมติ toolbar สูง 32px เราต้องสั่งให้หัวตารางห่างจาก top 32px */
.my-table thead th {
    position: sticky;
    top: 32px;         /* ปรับตามความสูงจริงของ Toolbar */
    z-index: 90;

    border-bottom: 2px solid #dee2e6;
}

   /* --- Scope Isolation: ป้องกัน CSS ชน --- */
        #jerry-calendar-app {
            display: flex;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            border: 1px solid #ccc;
            width: 95%;
            height: 500px;
            margin: 10px auto;
            background: #fff;
            color: #333;
           
        }

        /* --- Layout --- */
        #jerry-calendar-app .side-panel {
            flex: 1.2; /* ปรับให้ปฏิทินกว้างขึ้นนิดนึงเพื่อให้เห็นจุดชัด */
            display: flex;
            flex-direction: column;
            border-right: 1px solid #ddd;
        }

        #jerry-calendar-app .events-panel {
            flex: 1;
            display: flex;
            flex-direction: column;
            background-color: #f9f9f9;
        }

        /* --- Header Styles (คงเดิม) --- */
        #jerry-calendar-app .main-header {
            background-color: #008ba3; /* สี Telerik เดิม */
            color: white;
            padding: 12px 15px;
            font-size: 15px;
            font-weight: 600;
            display: flex;
            justify-content: space-between;
            align-items: center;
        }

        /* --- Calendar Area --- */
        #jerry-calendar-app .calendar-container {
            padding: 20px;
            flex-grow: 1;
        }

        #jerry-calendar-app .cal-controls {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
            font-weight: bold;
            font-size: 1.1em;
            color: #444;
        }

        #jerry-calendar-app .btn-nav {
            background: none;
            border: 1px solid #ddd;
            border-radius: 4px;
            cursor: pointer;
            padding: 2px 8px;
            color: #666;
        }
        #jerry-calendar-app .btn-nav:hover { background: #eee; }

        /* Grid System */
        #jerry-calendar-app .cal-grid {
            display: grid;
            grid-template-columns: 35px repeat(7, 1fr); /* ช่องแรกคือ week number */
            border-top: 1px solid #eee;
            border-left: 1px solid #eee;
        }

        #jerry-calendar-app .cal-cell {
            height: 45px;
            border-right: 1px solid #eee;
            border-bottom: 1px solid #eee;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: center;
            position: relative;
            font-size: 13px;
        }

        /* Week Number Column */
        #jerry-calendar-app .cal-week-col {
            background-color: #f4f8fb;
            color: #008ba3;
            font-weight: bold;
            font-size: 11px;
        }

        /* Header Row */
        #jerry-calendar-app .cal-head {
            height: 30px;
            background-color: #fff;
            color: #777;
            font-weight: normal;
            font-size: 11px;
            text-transform: uppercase;
        }

        /* Date Cells */
        #jerry-calendar-app .cal-date {
            cursor: pointer;
            background: #fff;
            transition: background 0.2s;
        }
        
        #jerry-calendar-app .cal-date:hover {
            background-color: #eaf6f8;
        }

        #jerry-calendar-app .cal-date.selected {
            background-color: #008ba3;
            color: white;
        }

        #jerry-calendar-app .cal-date.other-month {
            color: #ccc;
            background-color: #fafafa;
        }

        /* --- จุดกลมๆ (Event Dot) --- */
        #jerry-calendar-app .event-dot {
            width: 6px;
            height: 6px;
            background-color: #ff8c00; /* สีส้ม */
            border-radius: 50%;
            position: absolute;
            bottom: 4px;
            /* ถ้าวันที่ถูกเลือก (พื้นฟ้า) ให้จุดเป็นสีขาวเพื่อให้เห็นชัด */
        }
        #jerry-calendar-app .cal-date.selected .event-dot {
            background-color: #fff; 
        }

        /* --- Event List Right Side --- */
        #jerry-calendar-app .event-list {
            overflow-y: auto;
            flex-grow: 1;
            padding: 0;
        }

        #jerry-calendar-app .event-item {
            padding: 15px;
            border-bottom: 1px solid #e0e0e0;
            background: #fff;
        }
        #jerry-calendar-app .event-item:nth-child(even) { background: #fcfcfc; }

        #jerry-calendar-app .event-date-text {
            font-size: 11px;
            color: #888;
            margin-bottom: 5px;
            text-transform: uppercase;
            letter-spacing: 0.5px;
        }

        #jerry-calendar-app .event-title {
            font-size: 14px;
            font-weight: 600;
            color: #333;
            margin-bottom: 5px;
        }

        #jerry-calendar-app .event-sub {
            font-size: 12px;
            color: #666;
        }

        #jerry-calendar-app .empty-state {
            padding: 30px;
            text-align: center;
            color: #999;
            font-style: italic;
        }

    </style>
</head>
<body>

    <div class="cl_window">
      
  



        <div class="window-body">
     

        
                
          
                    
                    
     
            <div >
       <div class="cal-calendar-area" id="jerry-calendar-app${process_id}">
    <div id="jerry-calendar-app">
        <div class="side-panel">
            <div class="calendar-container">
                <div class="cal-controls">
                    <button class="btn-nav" onclick="changeMonth(-1, '${process_id}')">&#9664;</button>
                    
                    <span id="monthYearLabel_${process_id}">October 2021</span>
                    
                    <button class="btn-nav" onclick="changeMonth(1, '${process_id}')">&#9654;</button>
                </div>
                
                <div class="cal-grid">
                    <div class="cal-cell cal-head"></div> <div class="cal-cell cal-head">SU</div>
                    <div class="cal-cell cal-head">MO</div>
                    <div class="cal-cell cal-head">TU</div>
                    <div class="cal-cell cal-head">WE</div>
                    <div class="cal-cell cal-head">TH</div>
                    <div class="cal-cell cal-head">FR</div>
                    <div class="cal-cell cal-head">SA</div>
                </div>
                
                <div class="cal-grid" id="calendarBody_${process_id}"></div>
            </div>
        </div>

        <div class="events-panel">
            <div class="event-list" id="eventList_${process_id}">
                <div class="empty-state">Loading data...</div>
            </div>
        </div>
    </div>
</div>

         
            </div>
            
           
            

        </div>

    
    </div>
                   
                </div>

               
            
            </div>

</body>

  <div id="SaveCl"></div>               
 <script>    
 callCalendar('/CalendarAlljsonapp-filemanager','${process_id+0 }');
 </script>     
</html>