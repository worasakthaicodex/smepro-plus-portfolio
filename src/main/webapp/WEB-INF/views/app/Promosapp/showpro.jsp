 <%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${not empty selectedPromo}">
        
        <div class="detail-header">
            <div class="detail-title-row" style="display: flex; justify-content: space-between; align-items: flex-start; margin-bottom: 20px;">
                <div>
                    <div class="detail-title" style="font-size: 18px; font-weight: 600; color: var(--text-dark);">${selectedPromo.campaignName}</div>
                    <div class="detail-subtitle" style="font-size: 13px; color: var(--text-muted); display: flex; align-items: center; gap: 6px; margin-top: 4px;">
                        <svg viewBox="0 0 24 24" width="14" height="14"><rect x="3" y="4" width="18" height="18" rx="2" ry="2"></rect><line x1="16" y1="2" x2="16" y2="6"></line><line x1="8" y1="2" x2="8" y2="6"></line><line x1="3" y1="10" x2="21" y2="10"></line></svg>
                        ใช้ได้ถึง: ${selectedPromo.endDate} 
                    </div>
                </div>
                <button class="btn-outline" style="padding: 6px 12px;" onclick="getData('/DetailPromotion-app/${appid}/${selectedPromo.id}','microsofts${appid}')">รายละเอียดแคมเปญ</button>
            </div>

            <div class="metrics-row" style="display: grid; grid-template-columns: repeat(4, 1fr); gap: 16px; margin-bottom: 24px;">
                <div class="metric-box" style="background: #fff; padding: 16px; border: 1px solid var(--border-color); border-radius: 8px;">
                    <div class="metric-label" style="font-size: 12px; color: var(--text-muted); margin-bottom: 8px;">เป้าหมาย / ใช้สิทธิ์จริง</div>
                    <div class="metric-value" style="font-size: 18px; font-weight: 600; color: var(--text-dark);">
                        ${empty selectedPromo.actualRedemptions ? 0 : selectedPromo.actualRedemptions} 
                        <span style="font-size: 13px; font-weight: 400; color: var(--text-muted);">/ ${selectedPromo.targetRedemptions}</span>
                    </div>
                </div>
                <div class="metric-box" style="background: #fff; padding: 16px; border: 1px solid var(--border-color); border-radius: 8px;">
                    <div class="metric-label" style="font-size: 12px; color: var(--text-muted); margin-bottom: 8px;">จำนวนผู้สนใจ (Leads)</div>
                    <div class="metric-value" style="font-size: 18px; font-weight: 600; color: #3b82f6;">
                        ${empty selectedPromo.interestedCount ? 0 : selectedPromo.interestedCount} <span style="font-size: 13px; font-weight: 400; color: var(--text-muted);">ราย</span>
                    </div>
                </div>
                <div class="metric-box" style="background: #fff; padding: 16px; border: 1px solid var(--border-color); border-radius: 8px;">
                    <div class="metric-label" style="font-size: 12px; color: var(--text-muted); margin-bottom: 8px;">รายได้ประเมิน</div>
                    <div class="metric-value" style="font-size: 18px; font-weight: 600; color: var(--text-dark);">
                        ฿<fmt:formatNumber type="number" pattern="#,##0" value="${(empty selectedPromo.targetRedemptions ? 0 : selectedPromo.targetRedemptions) * selectedPromo.estRevenuePerRedemption}" />
                    </div>
                </div>
                <div class="metric-box" style="background: #fff; padding: 16px; border: 1px solid var(--border-color); border-radius: 8px; border-left: 4px solid var(--primary);">
                    <div class="metric-label" style="font-size: 12px; color: var(--text-muted); margin-bottom: 8px;">รายได้จริง (Actual Revenue)</div>
                    <div class="metric-value" style="font-size: 18px; font-weight: 600; color: var(--primary);">
                        ฿<fmt:formatNumber type="number" pattern="#,##0.00" value="${empty selectedPromo.actualRevenue ? 0 : selectedPromo.actualRevenue}" />
                    </div>
                </div>
            </div>
        </div>

        <div class="entry-section" style="background: #fff; border: 1px solid var(--border-color); border-radius: 8px; overflow: hidden;">
            
            <div style="display: flex; border-bottom: 1px solid var(--border-color); background: #f8fafc;">
                <button onclick="openTab('tab-interested')" id="btn-tab-interested" style="flex: 1; padding: 12px; border: none; background: #fff; border-bottom: 2px solid var(--primary); color: var(--primary); font-weight: 600; cursor: pointer; font-size: 13px;">
                    ผู้สนใจ (Interested)
                </button>
                <button onclick="openTab('tab-actual')" id="btn-tab-actual" style="flex: 1; padding: 12px; border: none; background: transparent; border-bottom: 2px solid transparent; color: var(--text-muted); font-weight: 500; cursor: pointer; font-size: 13px;">
                    ผู้ใช้สิทธิ์จริง (Redeemed)
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

    </c:when>
    
    <c:otherwise>
        <div style="display:flex; justify-content:center; align-items:center; height:100%; color:var(--text-muted);">
            <p>กรุณาเลือกแคมเปญจากรายการด้านซ้ายเพื่อดูรายละเอียด</p>
        </div>
    </c:otherwise>
</c:choose>