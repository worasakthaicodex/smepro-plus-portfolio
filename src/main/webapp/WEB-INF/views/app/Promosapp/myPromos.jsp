<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="chs-app-wrapper" id="chsMyPromosApp">
    <style>
        /* CSS Reset & Scoping */
        .chs-app-wrapper {
            --sidebar-bg: #111827;
            --sidebar-active: #10b981;
            --bg-main: #f8fafc;
            --white: #ffffff;
            --text-dark: #0f172a;
            --text-base: #334155;
            --text-muted: #64748b;
            --border-color: #e2e8f0;
            --primary: #10b981;
            --primary-hover: #059669;
            --blue: #0ea5e9;
            
            font-family: 'Segoe UI', 'Inter', -apple-system, sans-serif;
            background-color: var(--bg-main);
            color: var(--text-base);
            display: flex;
            height: 90vh;
            min-height: 768px;

            overflow: hidden;
            box-shadow: 0 4px 20px rgba(0,0,0,0.05);
            font-size: 13px;
            line-height: 1.5;
            box-sizing: border-box;
        }

        .chs-app-wrapper * { box-sizing: border-box; margin: 0; padding: 0; }
        .chs-app-wrapper svg { fill: none; stroke: currentColor; stroke-width: 2; stroke-linecap: round; stroke-linejoin: round; }

  
        /* ================= MAIN LAYOUT & HEADER ================= */
        .chs-app-wrapper .main-container { flex: 1; display: flex; flex-direction: column; overflow: hidden; }
        .chs-app-wrapper .top-header { background-color: var(--white); height: 64px; min-height: 64px; display: flex; align-items: center; justify-content: space-between; padding: 0 24px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .header-title { font-size: 18px; font-weight: 600; color: var(--text-dark); }
        .chs-app-wrapper .header-actions { display: flex; gap: 12px; align-items: center; }
        
        .chs-app-wrapper .btn-primary { background-color: var(--primary); color: var(--white); border: none; padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; display: flex; align-items: center; gap: 6px; transition: background 0.2s;}
        .chs-app-wrapper .btn-primary:hover { background-color: var(--primary-hover); }
        .chs-app-wrapper .btn-outline { background-color: var(--white); color: var(--text-dark); border: 1px solid var(--border-color); padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; }

        /* ================= CONTENT BODY (SPLIT PANELS) ================= */
        .chs-app-wrapper .content-area { display: flex; flex: 1; padding: 24px; gap: 24px; overflow: hidden; }

        /* ----- LEFT PANEL (LIST) ----- */
        .chs-app-wrapper .panel-left { flex: 1.2; background: var(--white); border-radius: 8px; border: 1px solid var(--border-color); display: flex; flex-direction: column; overflow: hidden; box-shadow: 0 1px 3px rgba(0,0,0,0.02); }
        
        .chs-app-wrapper .list-header { padding: 20px 24px 0 24px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .tabs-nav { display: flex; gap: 24px; }
        .chs-app-wrapper .tab-item { padding-bottom: 12px; color: var(--text-muted); cursor: pointer; font-weight: 500; position: relative; display: flex; align-items: center; gap: 6px; }
        .chs-app-wrapper .tab-item.active { color: var(--text-dark); }
        .chs-app-wrapper .tab-item.active::after { content: ''; position: absolute; bottom: -1px; left: 0; width: 100%; height: 2px; background-color: var(--text-dark); }
        .chs-app-wrapper .badge-count { background: #f1f5f9; color: var(--text-muted); font-size: 10px; padding: 2px 6px; border-radius: 10px; }

        .chs-app-wrapper .table-scroll { flex: 1; overflow-y: auto; }
        .chs-app-wrapper table { width: 100%; border-collapse: collapse; text-align: left; }
        .chs-app-wrapper th { padding: 12px 24px; font-weight: 500; color: var(--text-muted); font-size: 12px; border-bottom: 1px solid var(--border-color); position: sticky; top: 0; background: var(--white); z-index: 1; }
        .chs-app-wrapper td { padding: 16px 24px; border-bottom: 1px solid #f1f5f9; color: var(--text-base); font-size: 13px; }
        .chs-app-wrapper tr { cursor: pointer; transition: background 0.2s; }
        .chs-app-wrapper tr:hover td { background-color: #f8fafc; }
        .chs-app-wrapper tr.selected td { background-color: #f0fdf4; border-left: 3px solid var(--primary); }

        .chs-app-wrapper .status-badge { display: inline-block; padding: 4px 10px; border-radius: 12px; font-size: 11px; font-weight: 500; }
        .chs-app-wrapper .status-active { background: #dcfce7; color: #166534; }
        .chs-app-wrapper .status-draft { background: #f1f5f9; color: #475569; }
        .chs-app-wrapper .status-ended { background: #fee2e2; color: #991b1b; }

        .chs-app-wrapper .progress_container { width: 100%; max-width: 150px; margin-top: 6px; }
        .chs-app-wrapper .progress-text { display: flex; justify-content: space-between; font-size: 11px; color: var(--text-muted); margin-bottom: 4px; }
        .chs-app-wrapper .progress-bg { width: 100%; height: 6px; background: #e2e8f0; border-radius: 3px; overflow: hidden; }
        .chs-app-wrapper .progress-fill { height: 100%; background: var(--primary); border-radius: 3px; }

        /* ----- RIGHT PANEL (DATA ENTRY & DETAILS) ----- */
        .chs-app-wrapper .panel-right { flex: 1; background: var(--white); border-radius: 8px; border: 1px solid var(--border-color); display: flex; flex-direction: column; overflow: hidden; box-shadow: 0 1px 3px rgba(0,0,0,0.02); }
        
        .chs-app-wrapper .detail-header { padding: 24px; border-bottom: 1px solid var(--border-color); background: #f8fafc; }
        .chs-app-wrapper .detail-title-row { display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 12px; }
        .chs-app-wrapper .detail-title { font-size: 18px; font-weight: 600; color: var(--text-dark); margin-bottom: 4px; }
        .chs-app-wrapper .detail-subtitle { font-size: 12px; color: var(--text-muted); display: flex; align-items: center; gap: 6px; }
        .chs-app-wrapper .detail-subtitle svg { width: 12px; height: 12px; }

        /* Key Metrics in Detail */
        .chs-app-wrapper .metrics-row { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; margin-top: 20px; }
        .chs-app-wrapper .metric-box { background: var(--white); border: 1px solid var(--border-color); padding: 16px; border-radius: 6px; }
        .chs-app-wrapper .metric-label { font-size: 11px; color: var(--text-muted); text-transform: uppercase; letter-spacing: 0.5px; margin-bottom: 8px; }
        .chs-app-wrapper .metric-value { font-size: 20px; font-weight: 600; color: var(--text-dark); display: flex; align-items: baseline; gap: 4px; }
        .chs-app-wrapper .metric-sub { font-size: 12px; font-weight: 400; color: var(--text-muted); }

        /* Manual Entry Form */
        .chs-app-wrapper .entry-section { padding: 24px; flex: 1; overflow-y: auto; }
        .chs-app-wrapper .section-title { font-size: 14px; font-weight: 600; color: var(--text-dark); margin-bottom: 16px; display: flex; align-items: center; gap: 8px; }
        .chs-app-wrapper .section-title svg { color: var(--primary); width: 16px; height: 16px; }

        .chs-app-wrapper .entry-form { background: #f8fafc; border: 1px solid var(--border-color); padding: 20px; border-radius: 8px; margin-bottom: 24px; }
        .chs-app-wrapper .form-group { margin-bottom: 16px; }
        .chs-app-wrapper .form-label { display: block; font-size: 12px; font-weight: 500; color: var(--text-dark); margin-bottom: 6px; }
        .chs-app-wrapper .form-input { width: 100%; padding: 10px 12px; border: 1px solid var(--border-color); border-radius: 4px; font-size: 13px; outline: none; background: var(--white); }
        .chs-app-wrapper .form-input:focus { border-color: var(--primary); }
        .chs-app-wrapper .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; }

        /* History List */
        .chs-app-wrapper .history-list { display: flex; flex-direction: column; gap: 12px; }
        .chs-app-wrapper .history-item { display: flex; justify-content: space-between; align-items: center; padding: 12px 0; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .history-item:last-child { border-bottom: none; }
        .chs-app-wrapper .history-info { display: flex; align-items: center; gap: 12px; }
        .chs-app-wrapper .history-icon { width: 32px; height: 32px; background: #e0f2fe; color: var(--blue); border-radius: 50%; display: flex; align-items: center; justify-content: center; }
        .chs-app-wrapper .history-icon svg { width: 14px; height: 14px; }
        .chs-app-wrapper .history-desc { font-size: 13px; color: var(--text-dark); font-weight: 500; }
        .chs-app-wrapper .history-date { font-size: 11px; color: var(--text-muted); }
        .chs-app-wrapper .history-amount { font-weight: 600; color: var(--primary); font-size: 14px; }

    </style>

    <aside class="sidebar">
       <%@ include file="nav.jspf" %>  
    </aside>

    <main class="main-container">
        <header class="top-header">
            <div class="header-title">My Promotions</div>
            <div class="header-actions">
               <button class="btn-primary" onclick="getData('/appPromotion-app/${appid}','microsofts${appid}')">สร้างโปรโมชั่นใหม่</button>
            </div>
        </header>

        <div class="content-area">
            
         <div class="panel-left">
    <div class="list-header">
        <div class="tabs-nav" id="promoTabs">
            <div class="tab-item active" data-filter="ALL">ทั้งหมด</div>
            <div class="tab-item" data-filter="ACTIVE">ใช้งานอยู่</div>
            <div class="tab-item" data-filter="DRAFT">ฉบับร่าง</div>
            <div class="tab-item" data-filter="ENDED">สิ้นสุดแล้ว</div>
        </div>
    </div>
    
    <div class="table-scroll">
        <table>
            <thead>
                <tr>
                    <th>ชื่อแคมเปญ</th>
                    <th>สถานะ</th>
                    <th>เป้าหมาย (สิทธิ์ที่ใช้ / ทั้งหมด)</th>
                    <th>วันสิ้นสุด</th>
                </tr>
            </thead>
            <tbody id="promoTableBody">
                
                <c:forEach var="promo" items="${ListPromotion}">
                    
                  <tr data-status="${promo.status}" onclick="getData('/showpromotions-app/${appid}?id=${promo.id}','panel-right');highlightRow(this);">
                        <td style="font-weight: 600;">${promo.campaignName}</td>
                        
                        <td>
                            <c:if test="${promo.status == 'ACTIVE'}">
                                <span class="status-badge status-active">ใช้งานอยู่</span>
                            </c:if>
                            <c:if test="${promo.status == 'DRAFT'}">
                                <span class="status-badge status-draft">ฉบับร่าง</span>
                            </c:if>
                            <c:if test="${promo.status == 'ENDED'}">
                                <span class="status-badge status-ended">สิ้นสุดแล้ว</span>
                            </c:if>
                        </td>
                        
                        <td>
                            <c:choose>
                                <c:when test="${not empty promo.targetRedemptions and promo.targetRedemptions > 0}">
                                    <div class="progress_container">
                                        <div class="progress-text">
                                            <span>
                                                ${empty promo.actualRedemptions ? 0 : promo.actualRedemptions} ใช้สิทธิ์แล้ว
                                            </span>
                                            <span>${promo.targetRedemptions}</span>
                                        </div>
                                        <div class="progress-bg">
                                            <div class="progress-fill" 
                                                 style="width: ${(empty promo.actualRedemptions ? 0 : promo.actualRedemptions) * 100 / promo.targetRedemptions}%; 
                                                        ${promo.status == 'ENDED' ? 'background: #94a3b8;' : ''}">
                                            </div>
                                        </div>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <span style="font-size: 11px; color: var(--text-muted);">ยังไม่ได้ตั้งเป้าหมาย</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        
                        <td>${promo.formattedEndDate}</td>
                    </tr>
                    
                </c:forEach>

            </tbody>
        </table>
    </div>
</div>


          <div class="panel-right" id="panel-right">
     <%@ include file="showpro.jsp" %>  
  
</div>

        </div>
    </main>
</div>

<script>
//ฟังก์ชันสำหรับย้าย class="selected" เมื่อคลิกแบบ AJAX
function highlightRow(clickedRow) {
    // 1. หา tr ทั้งหมดในตาราง แล้วเอา class 'selected' ออก
    const rows = document.querySelectorAll('#promoTableBody tr');
    rows.forEach(row => row.classList.remove('selected'));
    
    // 2. ใส่ class 'selected' ให้กับแถวที่เพิ่งคลิก
    clickedRow.classList.add('selected');
}
    function promotab() {
        var tabs = document.querySelectorAll('.tab-item');
        var rows = document.querySelectorAll('#promoTableBody tr');

        tabs.forEach(tab => {
            tab.addEventListener('click', function() {
                // เปลี่ยนสีแท็บที่เลือก
                tabs.forEach(t => t.classList.remove('active'));
                this.classList.add('active');

                const filter = this.getAttribute('data-filter');

                // ซ่อน/แสดง แถวในตาราง
                rows.forEach(row => {
                    if (filter === 'ALL' || row.getAttribute('data-status') === filter) {
                        row.style.display = '';
                    } else {
                        row.style.display = 'none';
                    }
                });
            });
        });
    }
    
    promotab();
</script>