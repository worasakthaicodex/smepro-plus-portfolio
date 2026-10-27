<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> <%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>


<div class="chs-app-wrapper" id="chsCreatePromoApp">
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
            --ai-color: #8b5cf6;
            --ai-bg: #f5f3ff;
            
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
        
        .chs-app-wrapper .breadcrumb { display: flex; align-items: center; gap: 8px; font-size: 14px; font-weight: 500; color: var(--text-muted); cursor: pointer; }
        .chs-app-wrapper .breadcrumb-active { color: var(--text-dark); font-weight: 600; cursor: default; }
        .chs-app-wrapper .breadcrumb svg { width: 14px; height: 14px; }

        .chs-app-wrapper .header-actions { display: flex; gap: 12px; align-items: center; }
        .chs-app-wrapper .btn-primary { background-color: var(--primary); color: var(--white); border: none; padding: 8px 20px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; transition: background 0.2s; }
        .chs-app-wrapper .btn-primary:hover { background-color: var(--primary-hover); }
        .chs-app-wrapper .btn-outline { background-color: var(--white); color: var(--text-dark); border: 1px solid var(--border-color); padding: 8px 16px; border-radius: 4px; cursor: pointer; font-size: 13px; font-weight: 500; }
        .chs-app-wrapper .btn-text { background: transparent; color: var(--text-muted); border: none; padding: 8px 16px; cursor: pointer; font-weight: 500; }
        .chs-app-wrapper .btn-text:hover { color: var(--text-dark); }

        /* ================= CONTENT BODY (FORM & PREVIEW) ================= */
        .chs-app-wrapper .content-area { display: flex; flex: 1; padding: 24px; gap: 24px; overflow: hidden; }

        /* ----- LEFT PANEL (FORM) ----- */
        .chs-app-wrapper .form-panel { flex: 1.5; background: var(--white); border-radius: 8px; border: 1px solid var(--border-color); display: flex; flex-direction: column; overflow-y: auto; box-shadow: 0 1px 3px rgba(0,0,0,0.02); padding: 32px; }
        
        .chs-app-wrapper .form-section { margin-bottom: 32px; padding-bottom: 24px; border-bottom: 1px solid var(--border-color); }
        .chs-app-wrapper .form-section:last-child { border-bottom: none; margin-bottom: 0; padding-bottom: 0; }
        
        .chs-app-wrapper .section-header { display: flex; align-items: center; gap: 10px; margin-bottom: 20px; }
        .chs-app-wrapper .section-num { width: 24px; height: 24px; background: var(--text-dark); color: var(--white); border-radius: 50%; display: flex; align-items: center; justify-content: center; font-size: 12px; font-weight: 600; }
        .chs-app-wrapper .section-title { font-size: 16px; font-weight: 600; color: var(--text-dark); }

        /* Form Elements */
        .chs-app-wrapper .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 20px; margin-bottom: 16px; }
        .chs-app-wrapper .form-group { display: flex; flex-direction: column; gap: 6px; margin-bottom: 16px; }
        .chs-app-wrapper .form-label { font-size: 13px; font-weight: 500; color: var(--text-dark); display: flex; align-items: center; gap: 6px; }
        .chs-app-wrapper .form-label span.required { color: #ef4444; }
        
        .chs-app-wrapper .form-control { width: 100%; padding: 10px 14px; border: 1px solid var(--border-color); border-radius: 6px; font-size: 13px; outline: none; background: var(--white); color: var(--text-base); font-family: inherit; }
        .chs-app-wrapper .form-control:focus { border-color: var(--primary); box-shadow: 0 0 0 2px rgba(16, 185, 129, 0.1); }
        .chs-app-wrapper textarea.form-control { resize: vertical; min-height: 80px; }
        
        .chs-app-wrapper select.form-control { appearance: none; background-image: url("data:image/svg+xml;utf8,<svg viewBox='0 0 24 24' width='16' height='16' fill='none' stroke='%2364748b' stroke-width='2' stroke-linecap='round' stroke-linejoin='round'><polyline points='6 9 12 15 18 9'></polyline></svg>"); background-repeat: no-repeat; background-position: right 12px center; padding-right: 32px; }

        /* Custom Radio/Checkbox Cards */
        .chs-app-wrapper .options-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 12px; }
        .chs-app-wrapper .option-card { border: 1px solid var(--border-color); border-radius: 6px; padding: 12px; cursor: pointer; display: flex; align-items: center; gap: 10px; transition: all 0.2s; }
        .chs-app-wrapper .option-card:hover { border-color: var(--primary); }
        .chs-app-wrapper .option-card.active { border-color: var(--primary); background: #f0fdf4; }
        .chs-app-wrapper .option-card.active .radio-circle { border-color: var(--primary); }
        .chs-app-wrapper .option-card.active .radio-inner { background: var(--primary); }
        
        .chs-app-wrapper .radio-circle { width: 16px; height: 16px; border: 1px solid var(--border-color); border-radius: 50%; display: flex; align-items: center; justify-content: center; }
        .chs-app-wrapper .radio-inner { width: 8px; height: 8px; border-radius: 50%; background: transparent; transition: background 0.2s; }
        .chs-app-wrapper .option-text { font-weight: 500; font-size: 13px; color: var(--text-dark); }

        /* Input with Add-on (e.g. THB, %) */
        .chs-app-wrapper .input-group { display: flex; align-items: center; border: 1px solid var(--border-color); border-radius: 6px; overflow: hidden; }
        .chs-app-wrapper .input-group .form-control { border: none; border-radius: 0; box-shadow: none !important; }
        .chs-app-wrapper .input-addon { padding: 10px 14px; width: 30%;background: #f8fafc; border-left: 1px solid var(--border-color); color: var(--text-muted); font-weight: 500; }
        .chs-app-wrapper .input-addon-left { border-left: none; border-right: 1px solid var(--border-color); }

        /* ----- RIGHT PANEL (AI & PREVIEW) ----- */
        .chs-app-wrapper .side-panel { flex: 1; display: flex; flex-direction: column; gap: 24px; overflow-y: auto; }
        
        /* AI Assistant Box */
        .chs-app-wrapper .ai-assistant { background: var(--ai-bg); border: 1px solid #ddd6fe; border-radius: 8px; padding: 24px; }
        .chs-app-wrapper .ai-header { display: flex; align-items: center; gap: 8px; font-weight: 600; color: var(--ai-color); margin-bottom: 12px; font-size: 14px; }
        .chs-app-wrapper .ai-header svg { width: 18px; height: 18px; }
        .chs-app-wrapper .ai-desc { font-size: 12px; color: var(--text-base); margin-bottom: 16px; line-height: 1.6; }
        .chs-app-wrapper .btn-ai { width: 100%; background-color: var(--ai-color); color: var(--white); border: none; padding: 10px 16px; border-radius: 6px; cursor: pointer; font-size: 13px; font-weight: 500; display: flex; align-items: center; justify-content: center; gap: 8px; transition: opacity 0.2s; }
        .chs-app-wrapper .btn-ai:hover { opacity: 0.9; }

        /* Summary Card */
        .chs-app-wrapper .summary-card { background: var(--white); border: 1px solid var(--border-color); border-radius: 8px; padding: 24px; box-shadow: 0 1px 3px rgba(0,0,0,0.02); }
        .chs-app-wrapper .summary-title { font-size: 14px; font-weight: 600; color: var(--text-dark); margin-bottom: 16px; padding-bottom: 12px; border-bottom: 1px solid var(--border-color); }
        
        .chs-app-wrapper .summary-row { display: flex; justify-content: space-between; margin-bottom: 12px; font-size: 13px; }
        .chs-app-wrapper .summary-label { color: var(--text-muted); }
        .chs-app-wrapper .summary-value { font-weight: 500; color: var(--text-dark); text-align: right; }
        .chs-app-wrapper .summary-total { margin-top: 16px; padding-top: 16px; border-top: 1px dashed var(--border-color); display: flex; justify-content: space-between; font-size: 15px; font-weight: 600; color: var(--text-dark); }
      
      /* Custom Checkbox Cards สำหรับการวัดผล */
.chs-app-wrapper .check-square { width: 16px; height: 16px; border: 1px solid var(--border-color); border-radius: 4px; display: flex; align-items: center; justify-content: center; transition: all 0.2s; }
.chs-app-wrapper .option-card.active .check-square { border-color: var(--primary); background: var(--primary); }
.chs-app-wrapper .option-card.active .check-square::after { content: ''; width: 4px; height: 8px; border: solid white; border-width: 0 2px 2px 0; transform: rotate(45deg); margin-bottom: 2px; }  
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
                <span class="breadcrumb-active">Create New Promotion</span>
            </div>
            <div class="header-actions">
            
            <div class="header-actions">
       <c:if test="${Promotion.id > 0}">
    <form action="#" name="deletePromos" id="deletePromos" method="POST" style="margin: 0;" 
          onsubmit="return confirm('คุณแน่ใจหรือไม่ว่าต้องการลบแคมเปญนี้? ข้อมูลจะไม่สามารถกู้คืนได้');">
        
        <input type="hidden" name="id" value="${Promotion.id}">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          </form>  
        <button type="submit" class="btn-outline" style="color: #ef4444; border-color: #ef4444;" onclick='postData("/deletePromos-app/${appid}?${_csrf.parameterName}=${_csrf.token}","deletePromos","microsofts${appid}")'>
            <svg viewBox="0 0 24 24" width="14" height="14" style="margin-right: 4px; display: inline-block; vertical-align: middle;">
                <polyline points="3 6 5 6 21 6"></polyline><path d="M19 6v14a2 2 0 0 1-2 2H7a2 2 0 0 1-2-2V6m3 0V4a2 2 0 0 1 2-2h4a2 2 0 0 1 2 2v2"></path>
            </svg>
            ลบออก
        </button>


    <form action="#" name="formupdate" id="formupdate" method="POST" style="margin: 0;">
        <input type="hidden" name="id" value="${Promotion.id}">
        
        <input type="hidden" name="status" value="ACTIVE">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>   
        
            <form action="#" name="formupdate2" id="formupdate2" method="POST" style="margin: 0;">
        <input type="hidden" name="id" value="${Promotion.id}">
        
        <input type="hidden" name="status" value="ENDED">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
        </form>  
        <c:choose>
            <c:when test="${Promotion.status == 'ACTIVE'}">
                <button type="button" class="btn-primary" style="background: var(--text-muted); cursor: not-allowed;" onclick='postData("/updatePromos-app/${appid}?${_csrf.parameterName}=${_csrf.token}","formupdate2","microsofts${appid}")'>
                    ปิดใช้โปรโมชั่น
                </button>
            </c:when>
            <c:otherwise>
                <button type="submit" class="btn-primary" onclick='postData("/updatePromos-app/${appid}?${_csrf.parameterName}=${_csrf.token}","formupdate","microsofts${appid}")'>
                    <svg viewBox="0 0 24 24" width="14" height="14" style="margin-right: 4px; display: inline-block; vertical-align: middle;">
                        <polygon points="5 3 19 12 5 21 5 3"></polygon>
                    </svg>
                    เปิดใช้โปรโมชั่น
                </button>
            </c:otherwise>
        </c:choose>
</c:if>
    
</div>
            </div>
        </header>

        <div class="content-area">
            
            <div class="form-panel">
                
        <form action="#" id="formPromos" name="formPromos" method="POST">
    
    <input type="hidden" name="appid" value="${appid}">
    
   <c:if test="${Promotion.id > 0}">
    <input type="hidden" name="id" value="${Promotion.id}">
    </c:if>
    <div class="form-panel">
        
        <div class="form-section">
            <div class="section-header">
                <div class="section-num">1</div>
                <div class="section-title">รายละเอียดโปรโมชั่น (Promotion Details)</div>
            </div>
            
            <div class="form-group">
                <label class="form-label">ชื่อแคมเปญ (Campaign Name) <span class="required">*</span></label>
                <input type="text" name="campaignName" class="form-control" placeholder="เช่น แคมเปญอัปเกรด VIP ฤดูร้อน 2026" value="${Promotion.campaignName}" required>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label class="form-label">ประเภทข้อเสนอ (Offer Type) <span class="required">*</span></label>
                    <select name="offerType" class="form-control" required>
                        <option value="PERCENTAGE" ${Promotion.offerType == 'PERCENTAGE' ? 'selected' : ''}>ส่วนลดแบบเปอร์เซ็นต์ (%)</option>
                        <option value="FIXED_AMOUNT" ${Promotion.offerType == 'FIXED_AMOUNT' ? 'selected' : ''}>ส่วนลดแบบจำนวนเงิน (บาท)</option>
                        <option value="BOGO" ${Promotion.offerType == 'BOGO' ? 'selected' : ''}>ซื้อ 1 แถม 1 (Buy 1 Get 1 Free)</option>
                        <option value="FREE_SHIPPING" ${Promotion.offerType == 'FREE_SHIPPING' ? 'selected' : ''}>จัดส่งฟรี / บริการฟรี</option>
                    </select>
                </div>
                <div class="form-group">
                    <label class="form-label">มูลค่าส่วนลด (Discount Value)</label>
                    <div class="input-group">
                        <input type="number" name="discountValue" class="form-control" placeholder="เช่น 30" value="${Promotion.discountValue}" step="0.01">
                        <div class="input-addon">% / ฿</div>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="form-label">รายละเอียด / บันทึกภายใน (Internal Note)</label>
                <textarea name="description" class="form-control" placeholder="อธิบายวัตถุประสงค์ของแคมเปญนี้สำหรับทีมขาย...">${Promotion.description}</textarea>
            </div>
        </div>

        <div class="form-section">
            <div class="section-header">
                <div class="section-num">2</div>
                <div class="section-title">กลุ่มเป้าหมายและเงื่อนไข (Target Audience & Conditions)</div>
            </div>

            <div class="form-group">
                <label class="form-label">ผู้มีสิทธิ์เข้าร่วม (Audience)</label>
                <div class="options-grid">
                 <label class="option-card ${Promotion == null || Promotion.targetAudience == 'SALES_SELECTED' ? 'active' : ''}">
        <input type="radio" name="targetAudience" value="SALES_SELECTED" ${Promotion == null || Promotion.targetAudience == 'SALES_SELECTED' ? 'checked' : ''} style="display:none;">
        <div class="radio-circle"><div class="radio-inner"></div></div>
        <span class="option-text">แบบพนักงานขายเลือก</span>
    </label>

    <label class="option-card ${Promotion != null && Promotion.targetAudience == 'CUSTOM_CONDITIONS' ? 'active' : ''}">
        <input type="radio" name="targetAudience" value="CUSTOM_CONDITIONS" ${Promotion != null && Promotion.targetAudience == 'CUSTOM_CONDITIONS' ? 'checked' : ''} style="display:none;">
        <div class="radio-circle"><div class="radio-inner"></div></div>
        <span class="option-text">ลูกค้าตามเงื่อนไข</span>
    </label>
                </div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label class="form-label">ยอดสั่งซื้อขั้นต่ำ (Minimum Spend Required)</label>
                    <div class="input-group">
                        <div class="input-addon input-addon-left">฿</div>
                        <input type="number" name="minSpend" class="form-control" placeholder="0.00" value="${Promotion != null ? Promotion.minSpend : '5000'}" step="0.01" onchange="updateSummary();">
                    </div>
                </div>
                <div class="form-group">
                    <label class="form-label">ระยะเวลา (Duration)</label>
                    <div class="input-group">
                        <input type="date" name="startDate" class="form-control" value="${Promotion.startDate}">
                        <div class="input-addon" style="border-left:none; border-right:none; padding: 10px 8px;" onchange="updateSummary();">ถึง</div>
                        <input type="date" name="endDate" class="form-control" value="${Promotion.endDate}" onchange="updateSummary();">
                    </div>
                </div>
            </div>
        </div>

        <div class="form-section">
            <div class="section-header">
                <div class="section-num">3</div>
                <div class="section-title">เป้าหมายสำหรับการติดตาม <span style="font-size:12px; color:var(--text-muted); font-weight:normal;">(สำหรับ Dashboard KPI)</span></div>
            </div>

            <div class="form-row">
                <div class="form-group">
                    <label class="form-label">เป้าหมายจำนวนผู้ใช้สิทธิ์ (Target Redemptions)</label>
                    <input type="number" name="targetRedemptions" class="form-control" placeholder="จำนวนลูกค้าเป้าหมาย" value="${Promotion != null ? Promotion.targetRedemptions : '500'}" onchange="updateSummary();">
                </div>
                <div class="form-group">
                    <label class="form-label">คาดการณ์รายได้ต่อการใช้สิทธิ์ (Est. Revenue per Redemption)</label>
                    <div class="input-group">
                        <div class="input-addon input-addon-left">฿</div>
                        <input type="number" name="estRevenuePerRedemption" class="form-control" placeholder="ยอดสั่งซื้อเฉลี่ย" value="${Promotion != null ? Promotion.estRevenuePerRedemption : '2500'}" step="0.01" onchange="updateSummary();">
                    </div>
                </div>
            </div>
        </div>
        <div class="form-section">
    <div class="section-header">
        <div class="section-num">4</div>
        <div class="section-title">การวัดผล (Measurement)</div>
    </div>

    <div class="form-group">
        <label class="form-label">โปรแกรมที่ใช้วัดผล (Measurement Process)</label>
        <select name="measurementProcessId" class="form-control">
            <option value="">-- เลือกโปรแกรมที่จะผูก (LossLink) --</option>
            <c:forEach items="${app}" var="processItem">
                <option value="${processItem.id}" ${Promotion.measurementProcessId == processItem.id ? 'selected' : ''}>
                    ${processItem.name}
                </option>
            </c:forEach>
        </select>
    </div>

    <div class="form-group">
        <label class="form-label">ผลการสมัครที่นับเป็นความสำเร็จ (Workflow Status)</label>
        <div class="options-grid" style="grid-template-columns: repeat(2, 1fr);">
            <c:forEach items="${actionType}" var="status">
                
                <c:set var="isChecked" value="false" />
                <c:if test="${not empty Promotion.successWorkflowStatusIds}">
                    <c:forEach items="${fn:split(Promotion.successWorkflowStatusIds, ',')}" var="selectedId">
                        <c:if test="${fn:trim(selectedId) == status.dataset_data_id}">
                            <c:set var="isChecked" value="true" />
                        </c:if>
                    </c:forEach>
                </c:if>

                <label class="option-card ${isChecked ? 'active' : ''}">
                    <input type="checkbox" name="successWorkflowStatusIds" value="${status.dataset_data_id}" 
                           ${isChecked ? 'checked' : ''} style="display:none;" 
                           onchange="this.parentElement.classList.toggle('active', this.checked)">
                    <div class="check-square"></div>
                    <span class="option-text">${status.dataset_data_title}</span>
                </label>
            </c:forEach>
        </div>
        <small style="color: var(--text-muted); margin-top: 6px; display: block;">* สามารถเลือกได้หลายรายการ (เช่น ปิดการขายได้, ยืนยันคำสั่งซื้อ)</small>
    </div>
</div>
        <div class="form-actions" style="margin-top: 20px; text-align: right;">
            <button type="reset" class="btn btn-secondary">ล้างข้อมูล</button>
            <button type="button" class="btn btn-primary" onclick='postData("/addPromos-app?${_csrf.parameterName}=${_csrf.token}","formPromos","microsofts${appid}")'>บันทึก</button>
        </div>

    </div>
</form>

            </div>

            <div class="side-panel">
                
                <div class="ai-assistant">
                    <div class="ai-header">
                        <svg viewBox="0 0 24 24"><polygon points="12 2 15.09 8.26 22 9.27 17 14.14 18.18 21.02 12 17.77 5.82 21.02 7 14.14 2 9.27 8.91 8.26 12 2"></polygon></svg>
                        AI Promo Strategist
                    </div>
                    <div class="ai-desc">
                        Stuck on ideas? Let AI generate the optimal campaign settings based on your recent competitor scans and past successful promotions.
                    </div>
                    <button class="btn-ai">
                        <svg viewBox="0 0 24 24" width="16" height="16"><path d="M12 2v20M17 5H9.5a3.5 3.5 0 0 0 0 7h5a3.5 3.5 0 0 1 0 7H6"></path></svg>
                        Auto-fill with Best Strategy
                    </button>
                </div>

          <div class="summary-card">
    <div class="summary-title">สรุปแคมเปญ (Campaign Summary)</div>
    
    <div class="summary-row">
        <span class="summary-label">กลุ่มเป้าหมาย (Target Audience)</span>
        <span class="summary-value" id="sum-audience">ลูกค้า VIP ปัจจุบัน</span>
    </div>
    <div class="summary-row">
        <span class="summary-label">เงื่อนไข (Condition)</span>
        <span class="summary-value" id="sum-condition">ยอดซื้อขั้นต่ำ ฿5,000</span>
    </div>
    <div class="summary-row">
        <span class="summary-label">ระยะเวลา (Duration)</span>
        <span class="summary-value" id="sum-duration">60 วัน</span>
    </div>
    <div class="summary-row">
        <span class="summary-label">เป้าหมายการใช้งาน (Target Uses)</span>
        <span class="summary-value" id="sum-uses">500 สิทธิ์</span>
    </div>
    
    <div class="summary-total">
        <span>คาดการณ์รายได้รวม (Projected Total Revenue)</span>
        <span style="color: var(--primary);" id="sum-total">฿1,250,000</span>
    </div>
    <div style="font-size: 11px; color: var(--text-muted); text-align: right; margin-top: 4px;">
        อ้างอิงจาก: เป้าหมายการใช้งาน x คาดการณ์รายได้ต่อสิทธิ์
    </div>
</div>
            </div>

        </div>
    </main>
</div>

<script>
loadupdateSummary();
function loadupdateSummary() {
	// ดึง Element กลุ่มเป้าหมาย
    var audienceCards = document.querySelectorAll('.option-card');
    var audienceRadios = document.querySelectorAll('input[name="targetAudience"]');

    // ... (โค้ดส่วนดึง Element อื่นๆ ของคุณ ยังคงเดิม) ...

    // ฟังก์ชันจัดการเมื่อมีการเลือกกลุ่มเป้าหมาย (Radio Button)
    audienceRadios.forEach(radio => {
        radio.addEventListener('change', function() {
            
            // 1. ลบคลาส 'active' ออกจาก Card ทุกใบก่อน
            audienceCards.forEach(card => card.classList.remove('active'));
            
            // 2. ใส่คลาส 'active' ให้กับ Card ใบที่ถูกคลิก (หา Parent ที่เป็น .option-card)
            if (this.checked) {
                this.closest('.option-card').classList.add('active');
            }

            // 3. เรียกใช้ฟังก์ชันอัปเดต Summary (ถ้าคุณใช้โค้ดสรุปผลจากก่อนหน้านี้)
            updateSummary(); 
        });
    });
    updateSummary(); 
}
// 3. ฟังก์ชันหลักสำหรับอัปเดตข้อมูล
function updateSummary() {
    // 1. ดึง Element ฝั่งแบบฟอร์ม (Input)
    var audienceRadios = document.querySelectorAll('input[name="targetAudience"]');
    var minSpendInput = document.querySelector('input[name="minSpend"]');
    var startDateInput = document.querySelector('input[name="startDate"]');
    var endDateInput = document.querySelector('input[name="endDate"]');
    var targetRedemptionsInput = document.querySelector('input[name="targetRedemptions"]');
    var estRevenueInput = document.querySelector('input[name="estRevenuePerRedemption"]');

    // 2. ดึง Element ฝั่งแสดงผลสรุป (Summary Card)
    var sumAudience = document.getElementById('sum-audience');
    var sumCondition = document.getElementById('sum-condition');
    var sumDuration = document.getElementById('sum-duration');
    var sumUses = document.getElementById('sum-uses');
    var sumTotal = document.getElementById('sum-total');

    // ฟังก์ชันจัดรูปแบบตัวเลขให้มีลูกน้ำ (Comma)
    var formatCurrency = (number) => {
        return new Intl.NumberFormat('th-TH', { minimumFractionDigits: 0, maximumFractionDigits: 2 }).format(number);
    };

    // -- อัปเดตกลุ่มเป้าหมาย --
    let selectedAudience = document.querySelector('input[name="targetAudience"]:checked').value;
     if (selectedAudience === 'SALES_SELECTED') sumAudience.textContent = 'แบบพนักงานขายเลือก';
    else if (selectedAudience === 'CUSTOM_CONDITIONS') sumAudience.textContent = 'ตามเงื่อนไข';

    // -- อัปเดตเงื่อนไขขั้นต่ำ --
    let minSpend = parseFloat(minSpendInput.value) || 0;
    sumCondition.textContent = 'ยอดซื้อขั้นต่ำ ฿' + formatCurrency(minSpend);

    // -- คำนวณระยะเวลา (วัน) --
    if (startDateInput.value && endDateInput.value) {
        const start = new Date(startDateInput.value);
        const end = new Date(endDateInput.value);
        const diffTime = Math.abs(end - start);
        const diffDays = Math.ceil(diffTime / (1000 * 60 * 60 * 24)); 
        sumDuration.textContent = diffDays + ' วัน';
    } else {
        sumDuration.textContent = '- วัน';
    }

    // -- อัปเดตเป้าหมายผู้ใช้สิทธิ์ --
    let targetUses = parseInt(targetRedemptionsInput.value) || 0;
    sumUses.textContent = formatCurrency(targetUses) + ' สิทธิ์';

    // -- คำนวณรายได้รวม (รายได้ต่อหัว x จำนวนคน) --
    let estRevenue = parseFloat(estRevenueInput.value) || 0;
    let totalRevenue = targetUses * estRevenue;
    sumTotal.textContent = '฿' + formatCurrency(totalRevenue);
}

// 4. ผูก Event Listener ให้ทำงาน "ทันที" เมื่อมีการพิมพ์หรือเลือกข้อมูล
var inputsToWatch = [minSpendInput, startDateInput, endDateInput, targetRedemptionsInput, estRevenueInput];

inputsToWatch.forEach(input => {
    input.addEventListener('input', updateSummary);
});

audienceRadios.forEach(radio => {
    radio.addEventListener('change', updateSummary);
});

// เรียกทำงา
</script>