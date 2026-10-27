 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="chs-app-wrapper" id="chsViewPromoApp">
    <style>
        /* นำ CSS เดิมของคุณมาใช้ได้เลย และผมขอเพิ่ม CSS สำหรับหน้า View อีกนิดหน่อยครับ */
        .chs-app-wrapper {
            --sidebar-bg: #111827; --sidebar-active: #10b981; --bg-main: #f8fafc;
            --white: #ffffff; --text-dark: #0f172a; --text-base: #334155;
            --text-muted: #64748b; --border-color: #e2e8f0; --primary: #10b981;
            --primary-hover: #059669; --ai-color: #8b5cf6; --ai-bg: #f5f3ff;
            
            font-family: 'Segoe UI', 'Inter', -apple-system, sans-serif;
            background-color: var(--bg-main); color: var(--text-base);
            display: flex; height: 90vh; min-height: 768px; overflow: hidden;
            font-size: 13px; line-height: 1.5; box-sizing: border-box;
        }
        .chs-app-wrapper * { box-sizing: border-box; margin: 0; padding: 0; }
        .chs-app-wrapper svg { fill: none; stroke: currentColor; stroke-width: 2; stroke-linecap: round; stroke-linejoin: round; }

        /* Layout หลัก */
        .chs-app-wrapper .main-container { flex: 1; display: flex; flex-direction: column; overflow: hidden; }
        .chs-app-wrapper .top-header { background-color: var(--white); height: 64px; min-height: 64px; display: flex; align-items: center; justify-content: space-between; padding: 0 24px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .breadcrumb { display: flex; align-items: center; gap: 8px; font-size: 14px; font-weight: 500; color: var(--text-muted); cursor: pointer; }
        .chs-app-wrapper .breadcrumb-active { color: var(--text-dark); font-weight: 600; cursor: default; }
        .chs-app-wrapper .breadcrumb svg { width: 14px; height: 14px; }
        .chs-app-wrapper .header-actions { display: flex; gap: 12px; align-items: center; }
        
        /* ปุ่ม */
        .chs-app-wrapper .btn-primary { background-color: var(--primary); color: var(--white); border: none; padding: 8px 20px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; }
        .chs-app-wrapper .btn-outline { background-color: var(--white); color: var(--text-dark); border: 1px solid var(--border-color); padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; }

        .chs-app-wrapper .content-area { display: flex; flex: 1; padding: 24px; gap: 24px; overflow: hidden; }

        /* ----- ซ้าย: ข้อมูลโปรโมชั่น (View Mode) ----- */
        .chs-app-wrapper .detail-panel { flex: 1.5; background: var(--white); border-radius: 8px; border: 1px solid var(--border-color); display: flex; flex-direction: column; overflow-y: auto; padding: 32px; }
        
        .chs-app-wrapper .info-section { margin-bottom: 32px; padding-bottom: 24px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .info-section:last-child { border-bottom: none; margin-bottom: 0; padding-bottom: 0; }
        .chs-app-wrapper .section-title { display: flex; align-items: center; gap: 8px; font-size: 16px; font-weight: 600; color: var(--text-dark); margin-bottom: 20px; }
        .chs-app-wrapper .section-title svg { width: 20px; height: 20px; color: var(--primary); }

        /* Grid สำหรับแสดงข้อมูล */
        .chs-app-wrapper .data-grid { display: grid; grid-template-columns: 1fr 1fr; gap: 24px 20px; }
        .chs-app-wrapper .data-item { display: flex; flex-direction: column; gap: 4px; }
        .chs-app-wrapper .data-item.full-width { grid-column: span 2; }
        .chs-app-wrapper .data-label { font-size: 12px; color: var(--text-muted); font-weight: 500; text-transform: uppercase; letter-spacing: 0.5px; }
        .chs-app-wrapper .data-value { font-size: 14px; color: var(--text-dark); font-weight: 500; }
        
        /* Badges */
        .chs-app-wrapper .badge { display: inline-flex; align-items: center; padding: 4px 10px; border-radius: 20px; font-size: 12px; font-weight: 600; }
        .chs-app-wrapper .badge.active { background: #dcfce7; color: #166534; }
        .chs-app-wrapper .badge.type { background: #f1f5f9; color: #334155; border: 1px solid #e2e8f0; }

        /* ----- ขวา: แดชบอร์ดติดตามผล (Tracking) ----- */
        .chs-app-wrapper .side-panel { flex: 1; display: flex; flex-direction: column; gap: 24px; overflow-y: auto; }
        
        /* Progress Card */
        .chs-app-wrapper .kpi-card { background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; padding: 24px; }
        .chs-app-wrapper .kpi-header { font-size: 14px; font-weight: 600; color: var(--text-dark); margin-bottom: 16px; display: flex; justify-content: space-between; align-items: center; }
        
        /* Progress Bar */
        .chs-app-wrapper .progress_container { margin-top: 8px; }
        .chs-app-wrapper .progress-stats { display: flex; justify-content: space-between; margin-bottom: 8px; font-size: 13px; font-weight: 500; }
        .chs-app-wrapper .progress-bar-bg { height: 8px; background: var(--border-color); border-radius: 4px; overflow: hidden; }
        .chs-app-wrapper .progress-bar-fill { height: 100%; background: var(--primary); border-radius: 4px; }

        /* Metric Box */
        .chs-app-wrapper .metric-row { display: grid; grid-template-columns: 1fr 1fr; gap: 16px; margin-top: 24px; }
        .chs-app-wrapper .metric-box { background: var(--bg-main); padding: 16px; border-radius: 6px; border: 1px solid var(--border-color); }
        .chs-app-wrapper .metric-title { font-size: 12px; color: var(--text-muted); margin-bottom: 4px; }
        .chs-app-wrapper .metric-value { font-size: 18px; font-weight: 600; color: var(--text-dark); }
        
        /* History Timeline (Mock) */
        .chs-app-wrapper .history-item { display: flex; justify-content: space-between; padding: 12px 0; border-bottom: 1px solid var(--border-color); font-size: 13px; }
        .chs-app-wrapper .history-item:last-child { border-bottom: none; padding-bottom: 0; }
    </style>

    <aside class="sidebar">
      <%@ include file="nav.jspf" %>  
    </aside>

    <main class="main-container">
        <header class="top-header">
            <div class="breadcrumb" onclick="getData('/myPromos-app/${appid}','microsofts${appid}');">
                <svg viewBox="0 0 24 24"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>
                <span>My Promos</span>
                <span style="color: var(--border-color);">/</span>
                <span class="breadcrumb-active">${Promotion.campaignName}</span>
            </div>
            <div class="header-actions">
             
                <button class="btn-primary" onclick="getData('/editPromotion-app/${appid}/${Promotion.id}','microsofts${appid}');">
                    <span style="display:flex; align-items:center; gap:6px;">
                        <svg viewBox="0 0 24 24" width="14" height="14"><path d="M11 4H4a2 2 0 0 0-2 2v14a2 2 0 0 0 2 2h14a2 2 0 0 0 2-2v-7"></path><path d="M18.5 2.5a2.121 2.121 0 0 1 3 3L12 15l-4 1 1-4 9.5-9.5z"></path></svg>
                        แก้ไขแคมเปญ
                    </span>
                </button>
            </div>
        </header>

        <div class="content-area">
            
            <div class="detail-panel">
                
                <div class="info-section">
                    <div class="section-title">
                        <svg viewBox="0 0 24 24"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                        ข้อมูลทั่วไป (General Info)
                    </div>
                    <div class="data-grid">
                        <div class="data-item full-width">
                            <div class="data-label">ชื่อแคมเปญ</div>
                            <div class="data-value" style="font-size: 18px;">${Promotion.campaignName}</div>
                        </div>
                        <div class="data-item">
                            <div class="data-label">ประเภทข้อเสนอ</div>
                            <div class="data-value">
                                <span class="badge type">
                                    <c:choose>
                                        <c:when test="${Promotion.offerType == 'PERCENTAGE'}">ส่วนลดเปอร์เซ็นต์ (%)</c:when>
                                        <c:when test="${Promotion.offerType == 'FIXED_AMOUNT'}">ส่วนลดเงินสด (บาท)</c:when>
                                        <c:when test="${Promotion.offerType == 'BOGO'}">ซื้อ 1 แถม 1</c:when>
                                        <c:when test="${Promotion.offerType == 'FREE_SHIPPING'}">จัดส่งฟรี</c:when>
                                        <c:otherwise>${Promotion.offerType}</c:otherwise>
                                    </c:choose>
                                </span>
                            </div>
                        </div>
                        <div class="data-item">
                            <div class="data-label">มูลค่าส่วนลด</div>
                            <div class="data-value" style="color: var(--primary); font-size: 16px;">
                                <c:if test="${Promotion.offerType == 'FIXED_AMOUNT'}">฿</c:if>
                                <fmt:formatNumber value="${Promotion.discountValue}" pattern="#,##0.##" />
                                <c:if test="${Promotion.offerType == 'PERCENTAGE'}">%</c:if>
                            </div>
                        </div>
                        <div class="data-item full-width">
                            <div class="data-label">รายละเอียด / บันทึกภายใน</div>
                            <div class="data-value" style="color: var(--text-muted);">${not empty Promotion.description ? Promotion.description : '- ไม่มีรายละเอียด -'}</div>
                        </div>
                    </div>
                </div>

                <div class="info-section">
                    <div class="section-title">
                        <svg viewBox="0 0 24 24"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg>
                        เงื่อนไขการใช้งาน (Conditions)
                    </div>
                    <div class="data-grid">
                        <div class="data-item">
                            <div class="data-label">กลุ่มเป้าหมาย</div>
                            <div class="data-value">
                                <c:choose>
                                    <c:when test="${Promotion.targetAudience == 'SALES_SELECTED'}">แบบพนักงานขายเลือก</c:when>
                                    <c:otherwise>ลูกค้าตามเงื่อนไข</c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                        <div class="data-item">
                            <div class="data-label">ยอดสั่งซื้อขั้นต่ำ</div>
                            <div class="data-value">฿<fmt:formatNumber value="${Promotion.minSpend}" pattern="#,##0.00" /></div>
                        </div>
                        <div class="data-item">
                            <div class="data-label">วันเริ่มต้น (Start Date)</div>
                            <div class="data-value">${Promotion.startDate}</div>
                        </div>
                        <div class="data-item">
                            <div class="data-label">วันสิ้นสุด (End Date)</div>
                            <div class="data-value">${Promotion.endDate}</div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="side-panel">
                
                <div class="kpi-card">
                    <div class="kpi-header">
                        <span>สถานะการใช้สิทธิ์ (Redemption Status)</span>
                        <span class="badge active">Active</span>
                    </div>
                    
                    <c:set var="actual" value="${empty Promotion.actualRedemptions ? 0 : Promotion.actualRedemptions}" />
                    <c:set var="target" value="${empty Promotion.targetRedemptions ? 1 : Promotion.targetRedemptions}" />
                    <c:set var="percent" value="${(actual * 100) / target}" />
                    
                    <div class="progress_container">
                        <div class="progress-stats">
                            <span><fmt:formatNumber value="${actual}" pattern="#,##0"/> ใช้สิทธิ์แล้ว</span>
                            <span style="color: var(--text-muted);">เป้าหมาย <fmt:formatNumber value="${target}" pattern="#,##0"/></span>
                        </div>
                        <div class="progress-bar-bg">
                            <div class="progress-bar-fill" style="width: ${percent > 100 ? 100 : percent}%;"></div>
                        </div>
                        <div style="text-align: right; font-size: 11px; margin-top: 4px; color: var(--primary);">
                            บรรลุเป้าหมาย <fmt:formatNumber value="${percent}" maxFractionDigits="0"/>%
                        </div>
                    </div>

                    <div class="metric-row">
                        <div class="metric-box">
                            <div class="metric-title">รายได้ประเมินต่อสิทธิ์</div>
                            <div class="metric-value">
                           ฿<fmt:formatNumber type="number" pattern="#,##0" value="${(empty Promotion.targetRedemptions ? 0 : Promotion.targetRedemptions) * Promotion.estRevenuePerRedemption}" /></div>
                        </div>
                        <div class="metric-box">
                            <div class="metric-title">รายได้ที่สร้างได้ (โดยประมาณ)</div>
                            <div class="metric-value" style="color: var(--primary);">
                         ฿<fmt:formatNumber type="number" pattern="#,##0.00" value="${empty Promotion.actualRevenue ? 0 : Promotion.actualRevenue}" />
                            </div>
                        </div>
                    </div>
                </div>

               <div class="entry-section" style="background: #fff; border: 1px solid var(--border-color); border-radius: 8px; overflow: hidden;">
            
            <div style="display: flex; border-bottom: 1px solid var(--border-color); background: #f8fafc;">
                <button onclick="openTab('tab-interested')" id="btn-tab-interested" style="flex: 1; padding: 12px; border: none; background: #fff; border-bottom: 2px solid var(--primary); color: var(--primary); font-weight: 600; cursor: pointer; font-size: 13px;">
                    ผู้สนใจ (Interested)[  ${empty Promotion.actualRedemptions ? 0 : Promotion.actualRedemptions} ]
                </button>
                <button onclick="openTab('tab-actual')" id="btn-tab-actual" style="flex: 1; padding: 12px; border: none; background: transparent; border-bottom: 2px solid transparent; color: var(--text-muted); font-weight: 500; cursor: pointer; font-size: 13px;">
                    ผู้ใช้สิทธิ์จริง (Redeemed) [   ${empty Promotion.interestedCount ? 0 : Promotion.interestedCount}]
                </button>
            </div>

            <div id="tab-interested" style="padding: 16px; max-height: 400px; overflow-y: auto;">
                <div class="history-list" style="display: flex; flex-direction: column; gap: 12px;">
                    <c:choose>
                        <c:when test="${not empty interestedList}">
                            <c:forEach items="${interestedList}" var="lead">
                                <div class="history-item" style="display: flex; justify-content: space-between; align-items: center; padding: 12px; border: 1px solid var(--border-color); border-radius: 6px; background: #fdfdfd;">
                                    <div class="history-info" style="display: flex; gap: 12px; align-items: center;">
                                        <div class="history-icon" style="background: #e0f2fe; color: #0284c7; padding: 8px; border-radius: 50%;"><svg viewBox="0 0 24 24" width="16" height="16"><path d="M17 21v-2a4 4 0 0 0-4-4H5a4 4 0 0 0-4 4v2"></path><circle cx="9" cy="7" r="4"></circle><path d="M23 21v-2a4 4 0 0 0-3-3.87"></path><path d="M16 3.13a4 4 0 0 1 0 7.75"></path></svg></div>
                                        <div>
                                            <div class="history-desc" style="font-weight: 500; color: var(--text-dark);">${lead.customer_name}</div>
                                            <div class="history-date" style="font-size: 11px; color: var(--text-muted); margin-top: 4px;">บริการ: ${empty lead.services_offered ? '-' : lead.services_offered}</div>
                                        </div>
                                    </div>
                                    <div class="history-amount" style="font-weight: 600; color: var(--text-dark);">
                                        เสนอราคา: ฿<fmt:formatNumber type="number" pattern="#,##0.00" value="${lead.net_amount}" />
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div style="text-align: center; color: var(--text-muted); padding: 20px 0;">ยังไม่มีรายชื่อผู้สนใจ</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

            <div id="tab-actual" style="padding: 16px; max-height: 400px; overflow-y: auto; display: none;">
                <div class="history-list" style="display: flex; flex-direction: column; gap: 12px;">
                    <c:choose>
                        <c:when test="${not empty successList}">
                            <c:forEach items="${successList}" var="success">
                                <div class="history-item" style="display: flex; justify-content: space-between; align-items: center; padding: 12px; border: 1px solid var(--border-color); border-radius: 6px; background: #f0fdf4;">
                                    <div class="history-info" style="display: flex; gap: 12px; align-items: center;">
                                        <div class="history-icon" style="background: #dcfce7; color: #16a34a; padding: 8px; border-radius: 50%;"><svg viewBox="0 0 24 24" width="16" height="16"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline points="22 4 12 14.01 9 11.01"></polyline></svg></div>
                                        <div>
                                            <div class="history-desc" style="font-weight: 600; color: var(--text-dark);">${success.customer_name}</div>
                                            <div class="history-date" style="font-size: 11px; color: var(--text-muted); margin-top: 4px;">บริการ: ${empty success.services_offered ? '-' : success.services_offered}</div>
                                        </div>
                                    </div>
                                    <div class="history-amount" style="font-weight: 600; color: var(--primary);">
                                        ยอดปิด: ฿<fmt:formatNumber type="number" pattern="#,##0.00" value="${success.net_amount}" />
                                    </div>
                                </div>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <div style="text-align: center; color: var(--text-muted); padding: 20px 0;">ยังไม่มีผู้ใช้สิทธิ์จริง</div>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

        </div>
            </div>
        </div>
    </main>
</div>

       <script>
            function openTab(tabName) {
                // ซ่อนเนื้อหาทั้งหมด
                document.getElementById('tab-interested').style.display = 'none';
                document.getElementById('tab-actual').style.display = 'none';
                
                // รีเซ็ตสไตล์ปุ่ม
                document.getElementById('btn-tab-interested').style.background = 'transparent';
                document.getElementById('btn-tab-interested').style.borderBottom = '2px solid transparent';
                document.getElementById('btn-tab-interested').style.color = 'var(--text-muted)';
                
                document.getElementById('btn-tab-actual').style.background = 'transparent';
                document.getElementById('btn-tab-actual').style.borderBottom = '2px solid transparent';
                document.getElementById('btn-tab-actual').style.color = 'var(--text-muted)';

                // แสดงเนื้อหาที่เลือก
                document.getElementById(tabName).style.display = 'block';
                
                // เน้นสีปุ่มที่ถูกเลือก
                let activeBtn = document.getElementById('btn-' + tabName);
                activeBtn.style.background = '#fff';
                activeBtn.style.borderBottom = '2px solid var(--primary)';
                activeBtn.style.color = 'var(--primary)';
            }
        </script>