<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="chs-app-wrapper" id="chsAiIntelApp">
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
            --ai-color: #8b5cf6; /* AI Theme Color */
            --ai-bg: #f5f3ff;
            --danger: #ef4444;
            
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
        .chs-app-wrapper .header-title { font-size: 18px; font-weight: 600; color: var(--text-dark); display: flex; align-items: center; gap: 8px; }
        .chs-app-wrapper .header-title svg { color: var(--ai-color); }
        .chs-app-wrapper .btn-outline { background-color: var(--white); color: var(--text-dark); border: 1px solid var(--border-color); padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; display: flex; align-items: center; gap: 8px; }

        /* ================= CONTENT BODY ================= */
        .chs-app-wrapper .content-area { padding: 24px; overflow-y: auto; flex: 1; display: flex; flex-direction: column; gap: 24px; }

        /* AI Input Scanner */
        .chs-app-wrapper .ai-scanner { background: var(--white); border: 1px dashed var(--ai-color); border-radius: 8px; padding: 20px; display: flex; align-items: center; gap: 16px; background-color: var(--ai-bg); }
        .chs-app-wrapper .scanner-input-group { flex: 1; display: flex; gap: 12px; }
        .chs-app-wrapper .scanner-input { flex: 1; padding: 10px 16px; border: 1px solid var(--border-color); border-radius: 4px; font-size: 13px; outline: none; }
        .chs-app-wrapper .btn-scan { background-color: var(--ai-color); color: var(--white); border: none; padding: 10px 20px; border-radius: 4px; cursor: pointer; font-weight: 500; display: flex; align-items: center; gap: 8px; }

        /* Comparison Grid */
        .chs-app-wrapper .comparison-wrapper { display: flex; gap: 24px; }
        
        /* Column Styles */
        .chs-app-wrapper .col-promo { flex: 1; background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; overflow: hidden; display: flex; flex-direction: column; }
        .chs-app-wrapper .col-header { padding: 16px 20px; border-bottom: 1px solid var(--border-color); display: flex; justify-content: space-between; align-items: center; }
        .chs-app-wrapper .col-title { font-size: 14px; font-weight: 600; color: var(--text-dark); }
        .chs-app-wrapper .col-body { padding: 20px; flex: 1; }
        
        /* Select Dropdown styling for "Our Promo" */
        .chs-app-wrapper .promo-select { width: 100%; padding: 10px; border: 1px solid var(--border-color); border-radius: 4px; font-size: 14px; font-weight: 500; color: var(--text-dark); margin-bottom: 24px; outline: none; appearance: none; background: #f8fafc url("data:image/svg+xml;utf8,<svg viewBox='0 0 24 24' width='16' height='16' fill='none' stroke='%2364748b' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'><polyline points='6 9 12 15 18 9'></polyline></svg>") no-repeat right 12px center; }

        /* Competitor Extracted Data Header */
        .chs-app-wrapper .competitor-detected { margin-bottom: 24px; padding: 12px; background: #f1f5f9; border-radius: 4px; border-left: 3px solid var(--danger); }
        .chs-app-wrapper .detected-title { font-size: 14px; font-weight: 600; color: var(--text-dark); margin-bottom: 4px; }
        .chs-app-wrapper .detected-source { font-size: 11px; color: var(--text-muted); display: flex; align-items: center; gap: 4px; }

        /* Data Rows */
        .chs-app-wrapper .data-row { display: flex; flex-direction: column; margin-bottom: 16px; }
        .chs-app-wrapper .data-label { font-size: 11px; color: var(--text-muted); text-transform: uppercase; letter-spacing: 0.5px; margin-bottom: 4px; }
        .chs-app-wrapper .data-value { font-size: 14px; font-weight: 500; color: var(--text-dark); }
        
        .chs-app-wrapper .highlight-green { color: var(--primary); font-weight: 600; }
        .chs-app-wrapper .highlight-red { color: var(--danger); font-weight: 600; }

        /* AI Recommendation Bottom Panel */
        .chs-app-wrapper .ai-insight-panel { background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; padding: 20px; border-top: 4px solid var(--ai-color); }
        .chs-app-wrapper .insight-title { font-size: 16px; font-weight: 600; color: var(--text-dark); margin-bottom: 16px; display: flex; align-items: center; gap: 8px; }
        
        .chs-app-wrapper .insight-grid { display: grid; grid-template-columns: 1fr 1fr 1fr; gap: 20px; }
        .chs-app-wrapper .insight-box { padding: 16px; border-radius: 6px; background: #f8fafc; }
        .chs-app-wrapper .box-heading { font-size: 13px; font-weight: 600; margin-bottom: 8px; display: flex; align-items: center; gap: 6px; }
        .chs-app-wrapper .box-text { font-size: 12px; color: var(--text-muted); line-height: 1.6; }
        
        .chs-app-wrapper .action-btn { margin-top: 12px; background: none; border: 1px solid var(--ai-color); color: var(--ai-color); padding: 6px 12px; border-radius: 4px; font-size: 12px; font-weight: 500; cursor: pointer; transition: all 0.2s; }
        .chs-app-wrapper .action-btn:hover { background: var(--ai-color); color: var(--white); }

    </style>

    <aside class="sidebar">
         <%@ include file="nav.jspf" %>  
    </aside>

    <main class="main-container">
        <header class="top-header">
            <div class="header-title">
                <svg viewBox="0 0 24 24" width="20" height="20"><polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon></svg>
                AI Competitor Analysis
            </div>
            <button class="btn-outline">
                <svg viewBox="0 0 24 24" width="16" height="16"><path d="M21 15v4a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2v-4"></path><polyline points="7 10 12 15 17 10"></polyline><line x1="12" y1="15" x2="12" y2="3"></line></svg>
                Export Report
            </button>
        </header>

        <div class="content-area">
            
 

            <div class="comparison-wrapper">
                
              <div class="col-promo">
    <div class="col-header">
        <span class="col-title">Our Active Promotion</span>
        <span style="font-size: 11px; padding: 2px 8px; background: #e2e8f0; border-radius: 10px;">Select to compare</span>
    </div>
    <div class="col-body">
        
        <select class="promo-select" id="myPromoSelect" onchange="updateMyPromoDetails()">
            <option value="">-- กรุณาเลือกโปรโมชั่นเพื่อวิเคราะห์ --</option>
            <c:forEach items="${activePromotions}" var="promo">
                <option value="${promo.id}" 
                        data-type="${promo.offerType}"
                        data-target="${promo.targetAudience}"
                        data-condition="ซื้อขั้นต่ำ ${promo.minSpend} บาท"
                        data-duration="${promo.startDate} ถึง ${promo.endDate}"
                        data-target-red="เป้าหมาย: ${promo.targetRedemptions} Orders">
                    ${promo.campaignName} (${promo.status})
                </option>
            </c:forEach>
        </select>

        <div class="data-row">
            <span class="data-label">Offer Type</span>
            <span class="data-value" id="myOfferType">-</span>
        </div>
        <div class="data-row">
            <span class="data-label">Target Audience</span>
            <span class="data-value" id="myTargetAudience">-</span>
        </div>
        <div class="data-row">
            <span class="data-label">Condition (เงื่อนไข)</span>
            <span class="data-value" id="myCondition">-</span>
        </div>
        <div class="data-row">
            <span class="data-label">Duration</span>
            <span class="data-value" id="myDuration">-</span>
        </div>
        <div class="data-row">
            <span class="data-label">Manual Tracking Target</span>
            <span class="data-value" id="myTargetRed">-</span>
        </div>
        
        <button type="button" class="btn-primary" onclick="triggerAIAnalysis()" style="width: 100%; margin-top: 16px; background: var(--ai-color); justify-content: center;">
            <svg viewBox="0 0 24 24" width="16" height="16" style="margin-right: 6px;"><circle cx="11" cy="11" r="8"></circle><line x1="21" y1="21" x2="16.65" y2="16.65"></line></svg>
            Analyze Competitor via AI
        </button>
        
    </div>
</div>

                <div class="col-promo" style="border-color: var(--danger);">
                    <div class="col-header" style="background: #fef2f2;">
                        <span class="col-title" style="color: var(--danger);">Competitor's Offer</span>
                        <span style="font-size: 11px; padding: 2px 8px; background: #fecaca; color: #991b1b; border-radius: 10px;">AI Extracted</span>
                    </div>
                    <div class="col-body">
                        <div class="competitor-detected">
                            <div class="detected-title"></div>
                            <div class="detected-source">
                                <svg viewBox="0 0 24 24" width="12" height="12"><path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"></path><path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"></path></svg>
                          
                            </div>
                        </div>

                        <div class="data-row">
                            <span class="data-label">Offer Type</span>
                            <span class="data-value highlight-red">-</span>
                        </div>
                        <div class="data-row">
                            <span class="data-label">Target Audience</span>
                            <span class="data-value">-</span>
                        </div>
                        <div class="data-row">
                            <span class="data-label">Condition (เงื่อนไข)</span>
                            <span class="data-value highlight-green">-</span>
                        </div>
                        <div class="data-row">
                            <span class="data-label">Duration</span>
                            <span class="data-value">-</span>
                        </div>
                        <div class="data-row">
                            <span class="data-label">AI Confidence Score</span>
                            <span class="data-value" style="display: flex; align-items: center; gap: 6px;">
                                <div style="flex:1; height:4px; background:#e2e8f0; border-radius:2px;"><div style="width:0%; height:100%; background:var(--ai-color); border-radius:2px;"></div></div>
                                0%
                            </span>
                        </div>
                    </div>
                </div>

            </div>

            <div class="ai-insight-panel">
                <div class="insight-title">
                    <svg viewBox="0 0 24 24" width="20" height="20" style="color: var(--ai-color);"><path d="M12 2v20M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"></path></svg>
                    Strategic Recommendations by AI
                </div>
                
                <div class="insight-grid">
                    <div class="insight-box" style="border-left: 3px solid var(--danger);">
                        <div class="box-heading" style="color: var(--danger);">
                            <svg viewBox="0 0 24 24" width="14" height="14"><polyline points="23 18 13.5 8.5 8.5 13.5 1 6"></polyline><polyline points="17 18 23 18 23 12"></polyline></svg>
                            Threats (ความเสี่ยง)
                        </div>
                        <div class="box-text">
                      -
                        </div>
                    </div>
                    
                    <div class="insight-box" style="border-left: 3px solid var(--primary);">
                        <div class="box-heading" style="color: var(--primary);">
                            <svg viewBox="0 0 24 24" width="14" height="14"><path d="M22 11.08V12a10 10 0 1 1-5.93-9.14"></path><polyline points="22 4 12 14.01 9 11.01"></polyline></svg>
                            Our Advantage (จุดได้เปรียบของเรา)
                        </div>
                        <div class="box-text">
              -
                        </div>
                    </div>

                    <div class="insight-box" style="border-left: 3px solid var(--ai-color);">
                        <div class="box-heading" style="color: var(--ai-color);">
                            <svg viewBox="0 0 24 24" width="14" height="14"><circle cx="12" cy="12" r="10"></circle><line x1="12" y1="16" x2="12" y2="12"></line><line x1="12" y1="8" x2="12.01" y2="8"></line></svg>
                            Suggested Action (ข้อเสนอแนะ)
                        </div>
                        <div class="box-text">
                      -
                        </div>
             
                    </div>
                </div>
            </div>

        </div>
    </main>
</div>

<script>
//1. ฟังก์ชันอัปเดตข้อมูลฝั่งซ้าย (Our Promotion) เมื่อผู้ใช้เปลี่ยน Dropdown
function updateMyPromoDetails() {
    const select = document.getElementById('myPromoSelect');
    const selectedOption = select.options[select.selectedIndex];

    if (!selectedOption.value) {
        // ถ้าไม่ได้เลือกอะไร ให้เคลียร์ค่าเป็นขีด (-)
        document.getElementById('myOfferType').textContent = '-';
        document.getElementById('myTargetAudience').textContent = '-';
        document.getElementById('myCondition').textContent = '-';
        document.getElementById('myDuration').textContent = '-';
        document.getElementById('myTargetRed').textContent = '-';
        return;
    }

    // ดึงค่าจาก data-* attribute มาหยอดลง HTML
    document.getElementById('myOfferType').textContent = selectedOption.getAttribute('data-type');
    document.getElementById('myTargetAudience').textContent = selectedOption.getAttribute('data-target');
    document.getElementById('myCondition').textContent = selectedOption.getAttribute('data-condition');
    document.getElementById('myDuration').textContent = selectedOption.getAttribute('data-duration');
    document.getElementById('myTargetRed').textContent = selectedOption.getAttribute('data-target-red');
}

// 2. ฟังก์ชันสั่ง AI ค้นหาและเปรียบเทียบ (ยิงไปหา Java Controller แบบ GET)
function triggerAIAnalysis() {
    const promoId = document.getElementById('myPromoSelect').value;
    
    if (!promoId) {
        alert("กรุณาเลือก Our Active Promotion จาก Dropdown ก่อนครับ");
        return;
    }

    // โชว์ Loader สไตล์ Web OS
    const targetId = 'chsReportsApp'; // เปลี่ยนเป็น ID ของ Workspace ปัจจุบันถ้าจำเป็น


    // 🟢 ยิง API แบบ GET คลีนๆ (ดึงข้อมูลเฉยๆ ไม่ต้องส่ง CSRF และ Body)
    fetch('/api/ai-auto-compare/'+promoId)
    .then(response => response.json())
    .then(data => {


        if (data.error) {
            alert(data.error);
            return;
        }

        // ==========================================
        // 🟢 1. หยอดข้อมูลลงฝั่งขวา (Competitor's Offer) 🟢
        // ==========================================
    let sourceUrl = data.competitorSource.startsWith('http') ? data.competitorSource : 'https://' + data.competitorSource;
	document.querySelector('.competitor-detected .detected-source').innerHTML = 
	    `<svg viewBox="0 0 24 24" width="12" height="12"><path d="M10 13a5 5 0 0 0 7.54.54l3-3a5 5 0 0 0-7.07-7.07l-1.72 1.71"></path><path d="M14 11a5 5 0 0 0-7.54-.54l-3 3a5 5 0 0 0 7.07 7.07l1.71-1.71"></path></svg> 
	     <a href="`+sourceUrl+`" target="_blank" style="color: inherit; text-decoration: underline; cursor: pointer;">
	         Source: ${data.competitorSource}
	     </a>`;
	     
        // หา .col-promo ตัวที่สอง (ฝั่งคู่แข่ง) และดึง .data-value ทั้งหมดมาเรียงหยอด
        const competitorValues = document.querySelectorAll('.col-promo:nth-child(2) .data-value');
        if (competitorValues.length >= 5) {
            competitorValues[0].textContent = data.offerType;
            competitorValues[1].textContent = data.targetAudience;
            competitorValues[2].textContent = data.condition;
            competitorValues[3].textContent = data.duration;
            
            // หลอด AI Confidence Score
            competitorValues[4].innerHTML = `
                <div style="flex:1; height:4px; background:#e2e8f0; border-radius:2px;">
                    <div style="width:${data.confidenceScore}; height:100%; background:var(--ai-color); border-radius:2px;"></div>
                </div>
                ${data.confidenceScore}
            `;
        }

        // ==========================================
        // 🟢 2. หยอดข้อมูลลง Insight Panel ด้านล่าง 🟢
        // ==========================================
        const insightBoxes = document.querySelectorAll('.insight-box .box-text');
        if (insightBoxes.length >= 3) {
            insightBoxes[0].textContent = data.threats;        // กล่องสีแดง
            insightBoxes[1].textContent = data.ourAdvantage;   // กล่องสีน้ำเงิน
            insightBoxes[2].textContent = data.suggestedAction;// กล่องสีม่วง (AI)
        }

        console.log("AI Analysis Complete!");
    })
    .catch(error => {
        console.error('AI Fetch Error:', error);
        alert("เกิดข้อผิดพลาดในการเชื่อมต่อกับเซิร์ฟเวอร์ AI");
        Loader3.hide(targetId);
    });
}
</script>
