<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
/* ========================================================
   BI DASHBOARD LAYOUT (จำกัดความสูงไม่ให้ล้น 740px)
   ======================================================== */
.bi-dashboard-panel {
    display: flex; 
    gap: 15px; 
    margin-top: 10px; 
    height: 700px; /* ล็อกความสูงรวมไม่ให้เกินจอ */
}

.bi-zone {
    background-color: #ffffff; 
    border: 1px solid #dcdcdc;
    display: flex; 
    flex-direction: column; 
    border-radius: 6px;
    height: 100%; /* ให้เต็ม 700px พอดี */
    overflow: hidden; /* ป้องกันข้อมูลดันกล่องทะลุ */
}

.bi-zone-left { flex: 4.5; min-width: 400px; }
.bi-zone-right { flex: 5.5; min-width: 450px; }

.bi-zone-header {
    background-color: #f5f6f8; 
    padding: 12px 15px; 
    border-bottom: 1px solid #dcdcdc;
    font-size: 14px; /* ขยายฟอนต์หัวข้อ */
    font-weight: bold; 
    color: #333; 
    text-transform: uppercase;
}

/* ใส่ Scrollbar ให้เนื้อหาด้านในถ้ามันยาวเกินความสูงกล่อง */
.bi-zone-body { 
    padding: 15px; 
    flex: 1; 
    overflow-y: auto; 
}

/* ========================================================
   SECTION & COMPONENTS (ขยายตัวหนังสือ)
   ======================================================== */
.section-title {
    font-size: 14px; /* ขยายฟอนต์หมวดหมู่ */
    font-weight: bold; 
    color: #555; 
    margin: 20px 0 10px 0;
    text-transform: uppercase; 
    border-bottom: 2px solid #eee; 
    padding-bottom: 6px;
    display: flex; 
    align-items: center; 
    gap: 8px;
}
.section-title:first-child { margin-top: 0; }

/* 1. Original Data Grid */
.stat-grid {
    display: grid; grid-template-columns: 1fr 1fr; gap: 12px; margin-bottom: 15px;
}
.stat-box {
    background: #fdfdfd; border: 1px solid #eee; border-radius: 6px; padding: 12px; text-align: center;
}
.stat-box.full-width { grid-column: span 2; }
.stat-box.primary-bg { background: linear-gradient(135deg, #0078D4, #005A9E); color: white; border: none; }
.stat-label { font-size: 13px; color: #666; font-weight: bold; margin-bottom: 4px; } /* ขยาย Label */
.stat-value { font-size: 26px; font-weight: bold; color: #333; } /* ขยายตัวเลข */

.stat-box.primary-bg .stat-label { color: #cce4ff; }
.stat-box.primary-bg .stat-value { color: #ffffff; }

/* 2. KPI Progress */
.kpi-box { background: #fff; border: 1px solid #eee; border-radius: 6px; padding: 12px; margin-bottom: 10px; }
.kpi-header { display: flex; justify-content: space-between; font-size: 13px; font-weight: bold; } /* ขยาย Header */
.kpi-progress-bg { height: 8px; background: #e9ecef; border-radius: 4px; margin-top: 8px; overflow: hidden; }
.kpi-progress-fill { height: 100%; transition: width 0.5s; }

/* 3. Error 5 Dimensions Grid */
.error-grid {
    display: grid; grid-template-columns: repeat(5, 1fr); gap: 6px; margin-bottom: 15px;
}
.error-box {
    background: #fff5f5; border: 1px solid #ffe3e3; border-radius: 4px;
    padding: 10px 4px; text-align: center;
}
.error-label { font-size: 11px; color: #cc5c5c; font-weight: bold; margin-bottom: 4px; height: 28px; display: flex; align-items: center; justify-content: center; line-height: 1.1;}
.error-value { font-size: 20px; font-weight: bold; color: #d13438; } /* ขยายตัวเลข Error */

/* 4. Retry Card */
.retry-card {
    background: #fffaf0; border: 1px solid #ffebcc; border-left: 5px solid #f59e0b;
    border-radius: 6px; padding: 15px; display: flex; justify-content: space-between; align-items: center;
}
.retry-info .label { font-size: 14px; color: #92400e; font-weight: bold; }
.retry-info .desc { font-size: 12px; color: #b45309; margin-top: 2px;}
.retry-value { text-align: right; }
.retry-value .num { font-size: 28px; font-weight: bold; color: #d97706; display: block; }
.retry-value .unit { font-size: 12px; color: #b45309; }

/* Chart Containers (แบ่งความสูงคนละครึ่งให้พอดี 700px) */
.chart-row { height: calc(50% - 40px); min-height: 220x; margin-bottom: 20px;width:48%;float:left }
</style>

<div>
    <div class="bi-dashboard-panel">
        
        <div class="bi-zone bi-zone-left">
            <div class="bi-zone-header"><i class="fa fa-chart-simple"></i> Segmentation Details</div>
            
            <div class="bi-zone-body">
                
                <div class="section-title"><i class="fa fa-database"></i> General Info</div>
                <div class="stat-grid">
                    <div class="stat-box primary-bg full-width" style="display:none">
                        <div class="stat-label">Segment Name</div>
                        <div class="stat-value">
                            <c:out value="${Segmentation.name != null ? Segmentation.name : 'ไม่ได้ระบุชื่อกลุ่ม'}" />
                        </div>
                    </div>
                    <div class="stat-box full-width" style="background: #f8f9fa;">
                        <div class="stat-label">Total Data Selected</div>
                        <div class="stat-value">
                            <fmt:formatNumber value="${Segmentation.total_data_select != null ? Segmentation.total_data_select : 5000}" pattern="#,##0"/>
                        </div>
                    </div>
                    <div class="stat-box">
                        <div class="stat-label">Total Calls</div>
                        <div class="stat-value" style="color: #0078D4;">
                            <fmt:formatNumber value="${Segmentation.call_total != null ? Segmentation.call_total : 1250}" pattern="#,##0"/>
                        </div>
                    </div>
                    <div class="stat-box">
                        <div class="stat-label">Unique Calls</div>
                        <div class="stat-value" style="color: #107c10;">
                            <fmt:formatNumber value="${Segmentation.call_unique_count != null ? Segmentation.call_unique_count : 980}" pattern="#,##0"/>
                        </div>
                    </div>
                </div>

               

                <div class="section-title"><i class="fa fa-clock"></i> Follow-up Pipeline</div>
                <div class="retry-card">
                    <div class="retry-info">
                        <div class="label">รายชื่อลงแผนโทรใหม่</div>
                        <div class="desc">รอครบกำหนดเวลา (Cooling Period)</div>
                    </div>
                    <div class="retry-value">
                        <span class="num">${countScheduledCalls }</span>
                        <span class="unit">รายชื่อ</span>
                    </div>
                </div>

            </div>
        </div>

        <div class="bi-zone bi-zone-right">
            <div class="bi-zone-header"><i class="fa fa-eye"></i> BI Analytics Visualizer</div>
            
            <div class="bi-zone-body" style="overflow-y: hidden;">
                
                <div style="font-size: 13px; font-weight: bold; margin-bottom: 8px; color: #555;">📊 KPI Comparison</div>
                <div class="chart-row">
                    <canvas id="kpiChart"></canvas>
                </div>

                <div style="font-size: 13px; font-weight: bold; margin-bottom: 8px; color: #555; margin-top: 10px;">🕸️ Data Error Analysis</div>
                <div class="chart-row">
                    <canvas id="errorRadarChart"></canvas>
                </div>

            </div>
        </div>

    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

<script>

//1. ดึงข้อมูลจริงจาก JSP มาสร้าง JavaScript Arrays
var dynamicLabels = [];
var actualDataArray = [];
var targetDataArray = [];
var backgroundColors = [];

// ชุดสีที่จะวนใช้ในกราฟให้ตรงกับหลอด Progress
var colorPalette = ['#0078D4', '#8764b8', '#107c10', '#d97706', '#d13438', '#00B7C3'];

<c:forEach items="${rulesList}" var="rule" varStatus="status">
	<c:set var="arr_ex" value="${rule.this_target}" />

    dynamicLabels.push('${rule.title}');
    
    var actual = parseFloat('${rule.values.current_value != null ? rule.values.current_value : 0}');
    actualDataArray.push(actual);
    
    <c:choose>
    <%-- ถ้า arr_ex ไม่ใช่ค่าว่าง และ ไม่ใช่ '-' ให้ดึงค่าจาก edatas --%>
    <c:when test="${not empty arr_ex and arr_ex != '-'}">
        var target = parseFloat('${edatas[arr_ex] != null ? edatas[arr_ex] : 0}');
    </c:when>
    <%-- ถ้าเป็น '-' หรือค่าไม่ถูกต้อง ให้ target เป็น 0 ทันที --%>
    <c:otherwise>
        var target = 0;
    </c:otherwise>
</c:choose>

targetDataArray.push(target);
    backgroundColors.push(colorPalette[${status.index} % colorPalette.length]);
</c:forEach>

// วาดกราฟ KPI ด้วยข้อมูลจริง
var ctxKpi = document.getElementById('kpiChart');
if (ctxKpi) {
    new Chart(ctxKpi, {
        type: 'bar',
        data: {
            labels: dynamicLabels,
            datasets: [
                { 
                    label: 'เป้าหมาย (Target)', 
                    data: targetDataArray, 
                    backgroundColor: '#E34E3B' 
                },
                { 
                    label: 'ผลลัพธ์ (Actual)', 
                    data: actualDataArray, 
                    backgroundColor: backgroundColors 
                }
            ]
        },
        options: { 
            responsive: true, 
            maintainAspectRatio: false,
            plugins: { legend: { position: 'top', labels: { font: { size: 12 } } } },
            scales: {
                y: { beginAtZero: true }
            }
        }
    });
}

var radarLabels = [];
var radarData = [];

<c:forEach items="${problemStats}" var="entry">
    radarLabels.push('${entry.key}');
    radarData.push(${entry.value});
</c:forEach>

// แล้วเอา radarLabels กับ radarData ไปใส่ใน Chart.js
new Chart(document.getElementById('errorRadarChart'), {
    type: 'radar',
    data: {
        labels: radarLabels, // ใส่ตัวแปรตรงนี้
        datasets: [{
            label: 'ข้อมูลการโทร',
            data: radarData, // ใส่ตัวแปรตรงนี้
            fill: true,
            backgroundColor: 'rgba(209, 52, 56, 0.2)',
            borderColor: 'rgb(209, 52, 56)',
            pointBackgroundColor: 'rgb(209, 52, 56)'
        }]
    },
    // ... options ...
});

</script>