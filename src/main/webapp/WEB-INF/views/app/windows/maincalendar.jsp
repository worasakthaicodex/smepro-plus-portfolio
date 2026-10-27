<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

   <div id="save-calendar">
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
            width: 900px;
            height: 370px;
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
                 max-height:380px;
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
            max-height:380px;
            overflow-y:scroll;
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


/* ========================================================
   DYNAMIC CALENDAR LAYOUT (สลับ บน-ล่าง เป็น ซ้าย-ขวา)
   ======================================================== */
.cal-layout-wrapper {
    display: flex;
    flex-direction: column; /* ปกติเรียงจากบนลงล่าง */
    gap: 20px;
    padding: 10px;
}

.cal-top-section {
    display: flex;
    flex-direction: row; /* โหมดปกติ ฟอร์มอยู่ซ้าย ตารางอยู่ขวา */
    gap: 20px;
    align-items: flex-start;
}

.cal-form-area { flex: 4; }
.cal-table-area { flex: 6; }

.cal-calendar-area {
    width: 100%;
  
}

/* 🟢 โหมด TRAY = 1 (ปฏิทินไปขวา, ตารางตกมาอยู่ใต้ฟอร์ม) 🟢 */
.cal-layout-wrapper.is-tray {
    flex-direction: row; /* เปลี่ยนเป็นเรียงซ้ายขวา */
    align-items: stretch;
}

.cal-layout-wrapper.is-tray .cal-top-section {
    flex-direction: column; /* บังคับให้ตารางหล่นมาใต้ฟอร์ม */
    flex: 3.5; /* ฝั่งซ้ายกินพื้นที่ 35% */
    min-width: 320px;
}

.cal-layout-wrapper.is-tray .cal-calendar-area {
    flex: 6.5; /* ฝั่งขวากินพื้นที่ 65% */
    min-width: 500px;
    max-height: 400px;
}

/* ปลดล็อคขนาดปฏิทินให้ยืดหยุ่น 100% ตามกรอบ (แทนการฟิกซ์ 900px) */
#jerry-calendar-app {
    width: 100% !important; 
    height: 100% !important;
    min-height: 400px;
    margin: 0 !important;
    box-shadow: none;
    border: 1px solid #ccc; /* กรอบเหลี่ยมคลาสสิก */
}
* ========================================================
   DYNAMIC CALENDAR LAYOUT (แก้ตารางหดเหลือครึ่งเดียว)
   ======================================================== */
.cal-layout-wrapper {
    display: flex;
    flex-direction: column;
    gap: 20px;
    padding: 10px;
}

.cal-top-section {
    display: flex;
    flex-direction: row; 
    gap: 20px;
    align-items: flex-start;
}

/* บังคับให้กล่องฟอร์มและตารางพร้อมขยายตัวเสมอ */
.cal-form-area { flex: 4; width: 100%; }
.cal-table-area { flex: 6; width: 100%; }

.cal-calendar-area {
    width: 100%;
}

/* 🟢 โหมด TRAY = 1 (ปฏิทินไปขวา, ตารางตกมาอยู่ใต้ฟอร์ม) 🟢 */
.cal-layout-wrapper.is-tray {
    flex-direction: row; 
    align-items: stretch;
}

.cal-layout-wrapper.is-tray .cal-top-section {
    flex-direction: column; /* จับตารางเรียงลงมาใต้ฟอร์ม */
    align-items: stretch; /* 👇 ทริคสำคัญ: บังคับให้ฟอร์มและตารางยืดกว้าง 100% ของฝั่งซ้าย */
    flex: 3.5; 
    min-width: 350px; /* ขยายความกว้างขั้นต่ำฝั่งซ้ายขึ้นนิดนึงจะได้ไม่เบียด */
}

.cal-layout-wrapper.is-tray .cal-calendar-area {
    flex: 6.5; 
    min-width: 500px;
}

/* ปลดล็อคขนาดปฏิทิน */
#jerry-calendar-app {
    width: 100% !important; 
    height: 100% !important;
    min-height: 450px;
    margin: 0 !important;
    box-shadow: none;
    border: 1px solid #ccc; 
}

/* ตกแต่งตาราง */
.erp-table {
    width: 100%;
    border-collapse: collapse;
    font-size: 12px;
}
.erp-table th {
    background-color: #f5f5f5;
    border: 1px solid #ccc;
    padding: 6px;
    text-align: left;
    position: sticky; top: 0;
}
.erp-table td {
    border: 1px solid #eee;
    padding: 6px;
}

/* 👇 แก้ไขความสูงตาราง: ปลดล็อคให้ตารางแสดงผลได้เต็มที่ ไม่โดนตัดเหลือครึ่งเดียว 👇 */
.table-scroll-wrapper {
    max-height: 400px; /* เพิ่มความสูงตารางให้ยาวขึ้น หรือเปลี่ยนเป็น none ถ้าไม่อยากให้มี scrollbar */
    overflow-y: auto; 
    border: 1px solid #ccc; 
    background: #fff;
}
    </style>
</head>
<body>

    <div class="cl_window">
      
  <div class="window-body">
    
    <div class="cal-layout-wrapper <c:if test='${Tray == 1}'>is-tray</c:if>">
        
        <div class="cal-top-section">
            
            <div class="cal-form-area">
                <form id="formCalendar" name="formCalendar">
                    <input type="hidden" name="edata_id" value="${edata_id }">
                    <input type="hidden" name="index_id" value="${index_id }">
                    <input type="hidden" name="Tray" value="${Tray}">
                    <c:if test="${Cal.id > 0 }">
                        <input type="hidden" name="id" value="${Cal.id }">
                    </c:if>
                    <input type="hidden" name="process_id" value="${process_id }">
                    
                    <div class="main-form" style="border: 1px solid #ccc; padding: 15px; background: #fff;">
                        <div class="form-row">
                            <label>กิจกรรม:</label>
                            <input type="text" name="event" class="full-width" value="${Cal.event }">
                        </div>
                        <div class="form-row">
                            <label>วันที่:</label>
                            <input type="date" name="date" class="full-width" value="${Cal.year }-${Cal.month }-${Cal.day }">
                        </div>
                        <div class="form-row" style="margin-top: 15px;">
                            <label>&nbsp;</label>
                            <button type="button" onclick="postDataCallBack('/saveCalendar-filemanager?${_csrf.parameterName}=${_csrf.token}', 'formCalendar','save-calendar');" style="width: 120px; background: #000; color: #fff; border: none; padding: 5px 10px; cursor: pointer; font-weight: bold;">
                                <i class="fa fa-save"></i> บันทึก...
                            </button>     
                        </div>
                    </div>
                </form>
            </div>

            <div class="cal-table-area">
                <div style="max-height: 200px; overflow-y: auto; border: 1px solid #ccc; background: #fff;">
                    <table class="erp-table">
                        <thead>
                            <tr>
                                <th style="width:10%; text-align:center;">ทำเเล้ว</th>
                                <th>กิจกรรม</th>
                                <th style="width:25%;">วันที่</th>
                                <th style="width:10%;"></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${Calendar}" var="row">
                                <tr>
                                    <td style="text-align:center;">
                                        <input type="checkbox" onchange="getData('/checkCalendar-filemanager/${row.id }','-');sendCallBack()" <c:if test="${row.status == 1 }">checked</c:if>>
                                    </td>
                                    <td>${row.event }</td>
                                    <td>${row.day }-${row.month }-${row.year }</td>
                                    <td style="text-align:center;">
                                        <form id="formdelCalendar${row.id }" name="formdelCalendar${row.id }" style="display:none;">
                                            <input type="hidden" name="id" value="${row.id }">
                                            <input type="hidden" name="process_id" value="${process_id }">
                                            <input type="hidden" name="edata_id" value="${edata_id }">
                                            <input type="hidden" name="index_id" value="${index_id }">
                                              <input type="hidden" name="Tray" value="${Tray }">
                                        </form>
                                        <a title="ลบ" onclick="confirmAndDelete('/delCalendar-filemanager?Tray=${Tray }&&${_csrf.parameterName}=${_csrf.token}','formdelCalendar${row.id }','save-calendar')" style="cursor: pointer;"> 
                                            <img src="/app/system/icon/recycle bin.ico" alt="Delete" style="width: 16px;">
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>

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
<c:if test='${Tray != 1}'>
        <div class="window-footer">
         
            <button onclick="closeed2('draggable-window3')">Cancel</button>
        </div>
    </div>
</c:if>
</body>

  <div id="SaveCl"></div>               
 <script>    
 
 callCalendar('/Calendarjsonapp-filemanager','${process_id+0 }');
   
 </script>     

</html>
 </div>