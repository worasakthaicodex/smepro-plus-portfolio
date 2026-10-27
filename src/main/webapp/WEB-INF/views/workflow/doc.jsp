<%@ page  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>


<%@ include file="../common/mainhead.jspf" %>    



 <% pageContext.setAttribute("newLineChar", "\n"); %>
<title>Doc</title>

 <style>
 
 .authdoc-section{
 min-height:1000px;
 
 }
 
   .sub-section {
            padding-left: 100px;
        }
    .authdoc-container {
      display: flex;
    }
    .authdoc-sidebar {
      width: 280px;
      background-color: #fff;
      border-right: 1px solid #e0e0e0;
      padding: 20px;
      position: sticky;
      top: 0;
      height: 100vh;
      overflow-y: auto;
    }
    .authdoc-sidebar h3 {
      margin-top: 0;
      font-size: 18px;
      margin-bottom: 16px;
    }
    .authdoc-sidebar ul {
      list-style: none;
      padding: 0;
    }
    .authdoc-sidebar ul li {
      margin-bottom: 10px;
    }
    .authdoc-sidebar a {
      text-decoration: none;
      color: #0070f3;
      font-size: 14px;
    }
    .authdoc-content {
      flex: 1;
      padding: 40px;
    }

    
       body {
            font-family: Arial, sans-serif;
            line-height: 1.6;
            margin: 20px;
        
        
        }
        h1, h2, h3 {
            color: #2c3e50;
        }
        .section {
            margin-bottom: 20px;
        }
        .step {
            background-color: #f9f9f9;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        .lang-th, .lang-en {
            margin: 10px 0;
        }
        .lang-th {
            color: #34495e;
        }
        .lang-en {
            color: #7f8c8d;
        }
        .note {
            background-color: #e8f4f8;
            padding: 10px;
            border-left: 4px solid #3498db;
            margin-top: 20px;
        }
        .example {
            background-color: #f1f8e9;
            padding: 10px;
            border-left: 4px solid #8bc34a;
            margin: 10px 0;
        }
        
         table {
            width: 100%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
  </style>
</head>
<body>
  <div class="authdoc-container">
    <nav class="authdoc-sidebar">
      <h3>IDOC Manual</h3>
      <ul>
        <li><a href="#how-it-works">การตั้งค่าทั่วไปของ (Workflow System)</a></li>
           <li><a href="#Main0">นิยามคำว่า (Main Process Setting)</a></li>
           <li><a href="#Workflow0">นิยามคำว่า (Workflow Setting)</a></li>
          <li><a href="#Step0">นิยามคำว่า Step (Definition of Step)</a></li>
         <li><a href="#Access0">การกำหนดสิทธิ์การเข้าถึง Access Permissions)</a></li>
         <li><a href="#Reporting0">รายงานต่างๆ (Reporting)</a></li>
         <li><a href="#Log0">บันทึกการทำงาน (Log)</a></li>
         <li><a href="#Goals0">การกำหนดเป้าหมาย (Goals)</a></li>
         <li><a href="#Dashboard0">บอร์ดเเสดงกราฟ (Dashboard)</a></li>
         <li><a href="#Backend0">แก้ไขข้อผิดพลาด  (Backend)</a></li>
         <li><a href="#LowCode"> WorkSystem: อัลกอริทึมสำหรับจัดการระบบ Low Code</a></li>
        <li><a href="#RSD">RSD คืออะไร</a></li>
      </ul>
    </nav> 
    <div class="authdoc-content">
      <section id="how-it-works" class="authdoc-section"> 
           <h1>คู่มือ: การตั้งค่าทั่วไปของ Workflow System / Manual: General Settings for Workflow System</h1>

    <div class="section">
        <h2>ภาพรวมของการตั้งค่าทั่วไป / Overview of General Settings</h2>
        <p class="lang-th">การตั้งค่าทั่วไปของ Workflow System เป็นขั้นตอนที่ช่วยให้ผู้ใช้สร้างและจัดการ Workflow System เช่น ระบบผลิต โดยกำหนดชื่อ,Admin, การเชื่อมต่อฐานข้อมูล (Database หรือ DB), และโฟลเดอร์สำหรับจัดเก็บเอกสาร การตั้งค่านี้ช่วยให้ระบบทำงานได้ตามความต้องการขององค์กร</p>
        <p class="lang-en">General settings for the Workflow System enable users to create and manage systems, such as a Production System, by defining the name, Admin, database (DB) connections, and document storage folder. These settings ensure the system operates according to organizational needs.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึงการตั้งค่าทั่วไป Setup/ Accessing General Settings</h2>
        <div class="step">
            <p class="lang-th">การตั้งค่าทั่วไปของ Workflow System สามารถเข้าถึงได้เมื่อสร้าง Workflow System ใหม่หรือแก้ไขระบบที่มีอยู่ โดยเป็นส่วนหนึ่งของการกำหนดโครงสร้างพื้นฐานของระบบ</p>
            <p class="lang-en">General settings for the Workflow System are accessible when creating a new Workflow System or editing an existing one, as part of defining the system's foundational structure.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. ขั้นตอนการตั้งค่าทั่วไป / Setting</h2>
        <div class="step">
            <p class="lang-th">การตั้งค่าทั่วไปของ Workflow System ประกอบด้วยการกำหนดข้อมูลพื้นฐาน, การเชื่อมต่อฐานข้อมูล, และการจัดการเอกสาร ดังนี้:</p>
            <p class="lang-en">Configuring general settings for the Workflow System involves defining basic information, database connections, and document management, as follows:</p>
            <ul>
                <li><strong>กำหนดชื่อ Workflow System (Setting) / Define Workflow System Name</strong>
                    <p class="lang-th">ตั้งชื่อให้ Workflow System เช่น “ระบบผลิต” หรือ “ระบบจัดซื้อ” เพื่อระบุวัตถุประสงค์ของระบบ</p>
                    <p class="lang-en">Assign a name to the Workflow System, e.g., “Production System” or “Procurement System”, to indicate its purpose.</p>
                </li>
                <li><strong>กำหนด Admin  (Setting) / Define Responsible Person</strong>
                    <p class="lang-th">ระบุผู้รับผิดชอบหลัก (ปกติคือผู้สร้าง) และสามารถกำหนดผู้รับผิดชอบสำรองได้ 1 คนสำหรับกรณีฉุกเฉิน ผู้รับผิดชอบมีสิทธิ์จัดการการตั้งค่าทั้งหมด</p>
                    <p class="lang-en">Specify the primary responsible person (typically the creator) and optionally assign one backup responsible person for emergencies. The responsible person has full control over all settings.</p>
                </li>
                <li><strong>เชื่อมต่อฐานข้อมูล (Installation) / Connect Databases</strong>
                    <p class="lang-th">เลือกฐานข้อมูล (DB) สูงสุด 3 ตัว โดยมี 1 ตัวเป็น DB หลักและ 2 ตัวเป็น DB รอง ดังนี้:</p>
                    <p class="lang-en">Select up to three databases (DBs), with one as the primary DB and two as secondary DBs, as follows:</p>
                    <ul>
                        <li><strong>DB หลัก  / Main database</strong>
                            <p class="lang-th">ใช้บันทึกและจัดการข้อมูลหลักของ Workflow สามารถเข้าถึง Table ทั้งหมด เช่น PR, PO, GR ใน DB “Procurement_SupplyChain”</p>
                            <p class="lang-en">Used to record and manage the Workflow’s primary data, with access to all Tables, e.g., PR, PO, GR in the “Procurement_SupplyChain” DB.</p>
                        </li>
                        <li><strong>DB รอง / Sub database</strong>
                            <p class="lang-th">ใช้ดึงข้อมูลประกอบ (Supporting Data) เท่านั้น ไม่สามารถใช้ Table หลักของ DB รองได้</p>
                            <p class="lang-en">Used only to retrieve supporting data, without access to the primary Tables of the secondary DBs.</p>
                        </li>
                    </ul>
                    <p class="lang-th">ตัวอย่าง DB ที่รองรับ: Finance_Accounting, HR_Workforce, Machinery_Factory, Procurement_SupplyChain, Production, Sales_Customer, Stock_Warehouse</p>
                    <p class="lang-en">Supported DB examples: Finance_Accounting, HR_Workforce, Machinery_Factory, Procurement_SupplyChain, Production, Sales_Customer, Stock_Warehouse</p>
                </li>
                <li><strong>กำหนดโฟลเดอร์จัดเก็บเอกสาร / Define Document Storage Folder</strong>
                    <p class="lang-th">ระบุโฟลเดอร์ในระบบที่ไฟล์ทั้งหมดของ Workflow System จะถูกจัดเก็บ เพื่อการจัดการเอกสารที่มีประสิทธิภาพ</p>
                    <p class="lang-en">Specify the folder in the system where all files of the Workflow System will be stored, for efficient document management.</p>
                </li>
            </ul>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 1 / Example 1</h3>
            <p class="lang-th">
                <strong>การตั้งค่า Workflow System สำหรับระบบผลิต:</strong><br>
                - ชื่อ: ระบบผลิต<br>
                - ผู้รับผิดชอบ: นาย ก (ผู้สร้าง), ผู้รับผิดชอบสำรอง: นาย ข<br>
                - ฐานข้อมูล:<br>
                  - DB หลัก: Production (ใช้ Table สำหรับบันทึกกระบวนการผลิต)<br>
                  - DB รอง 1: Machinery_Factory (ดึงข้อมูลเครื่องจักร)<br>
                  - DB รอง 2: Stock_Warehouse (ดึงข้อมูลสินค้าคงคลัง)<br>
                - โฟลเดอร์จัดเก็บ: /Production_Documents<br>
                ผลลัพธ์: Workflow System สำหรับการผลิตที่บันทึกข้อมูลใน DB “Production” และดึงข้อมูลประกอบจาก “Machinery_Factory” และ “Stock_Warehouse” โดยเอกสารจัดเก็บในโฟลเดอร์ที่กำหนด
            </p>
            <p class="lang-en">
                <strong>Workflow System Setup for Production System:</strong><br>
                - Name: Production System<br>
                - Responsible Person: Mr. A (Creator), Backup: Mr. B<br>
                - Databases:<br>
                  - Primary DB: Production (uses Tables for recording production processes)<br>
                  - Secondary DB 1: Machinery_Factory (retrieves machinery data)<br>
                  - Secondary DB 2: Stock_Warehouse (retrieves inventory data)<br>
                - Storage Folder: /Production_Documents<br>
                Outcome: A Workflow System for production that records data in the “Production” DB, retrieves supporting data from “Machinery_Factory” and “Stock_Warehouse”, with documents stored in the specified folder.
            </p>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 2 / Example 2</h3>
            <p class="lang-th">
                <strong>การตั้งค่า Workflow System สำหรับระบบจัดซื้อ:</strong><br>
                - ชื่อ: ระบบจัดซื้อ<br>
                - ผู้รับผิดชอบ: นาย ค (ผู้สร้าง), ผู้รับผิดชอบสำรอง: นาย ง<br>
                - ฐานข้อมูล:<br>
                  - DB หลัก: Procurement_SupplyChain (ใช้ Table PR, PO, GR)<br>
                  - DB รอง 1: Stock_Warehouse (ดึงข้อมูลสินค้าคงคลัง)<br>
                  - DB รอง 2: Finance_Accounting (ดึงข้อมูลงบประมาณ)<br>
                - โฟลเดอร์จัดเก็บ: /Procurement_Documents<br>
                ผลลัพธ์: Workflow System สำหรับการจัดซื้อที่บันทึกข้อมูลใน DB “Procurement_SupplyChain” และดึงข้อมูลประกอบจาก “Stock_Warehouse” และ “Finance_Accounting” โดยเอกสารจัดเก็บในโฟลเดอร์ที่กำหนด
            </p>
            <p class="lang-en">
                <strong>Workflow System Setup for Procurement System:</strong><br>
                - Name: Procurement System<br>
                - Responsible Person: Mr. C (Creator), Backup: Mr. D<br>
                - Databases:<br>
                  - Primary DB: Procurement_SupplyChain (uses PR, PO, GR Tables)<br>
                  - Secondary DB 1: Stock_Warehouse (retrieves inventory data)<br>
                  - Secondary DB 2: Finance_Accounting (retrieves budget data)<br>
                - Storage Folder: /Procurement_Documents<br>
                Outcome: A Workflow System for procurement that records data in the “Procurement_SupplyChain” DB, retrieves supporting data from “Stock_Warehouse” and “Finance_Accounting”, with documents stored in the specified folder.
            </p>
        </div>
    </div>
 
     <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การตั้งค่าทั่วไปของ Workflow System ต้องระบุชื่อ, ผู้รับผิดชอบ, ฐานข้อมูล, และโฟลเดอร์จัดเก็บให้ชัดเจน การเลือก DB หลักและ DB รองควรสอดคล้องกับความต้องการของ Workflow เพื่อให้ข้อมูลครบถ้วนและเหมาะสม</p>
        <p class="lang-en">Configuring general settings for the Workflow System requires clear specification of the name, responsible person, databases, and storage folder. The selection of primary and secondary DBs should align with the Workflow’s needs to ensure complete and appropriate data.</p>
    </div>
    
    
    
         
                            <br>   <br> 
  <section id="Backend0" class="authdoc-section">  
          <h1>คู่มือ: Backend ใน Workflow System / Manual: Backend in Workflow System</h1> <br>

    <div class="section">
        <h2>ภาพรวมของ Backend / Overview of Backend</h2>
        <p class="lang-th">Backend เป็นฟีเจอร์ใน Workflow System ที่ออกแบบมาเพื่อจัดการข้อผิดพลาดในระบบ โดยอนุญาตให้ Admin แก้ไขข้อมูลในขอบเขตที่กำหนด พร้อมบันทึกประวัติการแก้ไขเพื่อความโปร่งใส</p>
        <p class="lang-en">Backend is a feature in the Workflow System designed to handle system errors, allowing Admins to correct data within defined scopes while logging all changes for transparency.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Backend / Accessing Backend</h2>
        <div class="step">
            <p class="lang-th">Backend สามารถเข้าถึงได้ผ่านส่วน Backend Management ใน Workflow System โดย Admin เท่านั้นที่ได้รับอนุญาตให้แก้ไข</p>
            <p class="lang-en">Backend can be accessed via the Backend Management section in the Workflow System, restricted to Admins only for editing.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. การตั้งค่าและขอบเขตการแก้ไข / Configuration and Editing Scope</h2>
        <div class="step">
            <p class="lang-th">Backend อนุญาตให้แก้ไขข้อผิดพลาดในขอบเขตที่กำหนด ดังนี้:</p>
            <p class="lang-en">Backend allows error correction within defined scopes as follows:</p>
            <ul>
                <li><strong>อนุญาตให้แก้ไข</strong>:
                    <p class="lang-th">ข้อผิดพลาดทั่วไป เช่น การปิดเอกสารผิดหรือกรอกข้อมูลผิดแล้วอนุมัติ โดย Admin สามารถแก้ไขได้โดยตรง</p>
                    <p class="lang-en">Common errors, such as closing a document incorrectly or approving incorrect data, which Admins can fix directly.</p>
                </li>
                <li><strong>ไม่อนุญาตให้แก้ไข</strong>:
                    <p class="lang-th">ข้อมูลสำคัญ เช่น ค่าทะเบียนควบคุมหรือทะเบียนต่างๆ (เช่น รายชื่อลูกค้า) ต้องผ่านกระบวนการ Action</p>
                    <p class="lang-en">Critical data, such as controlled registers or various registries (e.g., customer lists), requiring an Action process.</p>
                </li>
                <li><strong>บันทึกประวัติ</strong>:
                    <p class="lang-th">ระบบบันทึกทุกการแก้ไข เช่น ใครแก้ไข อะไร เมื่อไหร่</p>
                    <p class="lang-en">The system logs all changes, including who edited, what was edited, and when.</p>
                </li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h2>3. การใช้งาน Backend / Using Backend</h2>
        <div class="step">
            <p class="lang-th">Backend ช่วยให้ Admin แก้ไขข้อผิดพลาดเพื่อให้ระบบดำเนินต่อได้ พร้อมรักษาความปลอดภัยของข้อมูลสำคัญ</p>
            <p class="lang-en">Backend enables Admins to correct errors to keep the system running, while maintaining the security of critical data.</p>
        </div>
        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">
                <strong>การแก้ไขข้อผิดพลาด:</strong><br>
                - <strong>อนุญาต</strong>: ผู้ใช้ปิดใบสั่งซื้อผิดเมื่อ 2025-04-20<br>
                  - การแก้ไข: Admin เปิดใบสั่งซื้อใหม่และบันทึกประวัติ<br>
                - <strong>ไม่อนุญาต</strong>: ผู้ใช้ลบรายชื่อลูกค้าออก<br>
                  - การแก้ไข: ต้องยื่น Action ขออนุมัติจากผู้มีอำนาจ<br>
            </p>
            <p class="lang-en">
                <strong>Error Correction:</strong><br>
                - <strong>Allowed</strong>: User incorrectly closed a purchase order on 2025-04-20<br>
                  - Correction: Admin reopens the purchase order and logs the change<br>
                - <strong>Not Allowed</strong>: User deleted a customer name<br>
                  - Correction: Requires an Action request for approval from authorized personnel<br>
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">ตรวจสอบขอบเขตการแก้ไขและสิทธิ์ของ Admin เพื่อให้ Backend ทำงานได้อย่างราบรื่น การบันทึกประวัติช่วยเพิ่มความโปร่งใสและความปลอดภัย</p>
        <p class="lang-en">Verify the editing scope and Admin permissions to ensure smooth Backend operation. Change logging enhances transparency and security.</p>
    </div>
         
    </section>     
         
                   <br>   <br> 
  <section id="Dashboard0" class="authdoc-section">   
    <h1>คู่มือ: Dashboard ใน Workflow System / Manual: Dashboard in Workflow System</h1><br>

    <div class="section">
        <h2>ภาพรวมของ Dashboard / Overview of Dashboard</h2>
        <p class="lang-th">Dashboard เป็นฟีเจอร์ใน Workflow System ที่ช่วยให้ผู้ใช้สร้างกระดานแสดงข้อมูลจาก Custom Report ได้ไม่จำกัดจำนวน และตั้งเป็นหน้ากลางได้ตามต้องการ โดยแสดง Custom Report ได้ 3-7 รายการต่อกระดาน</p>
        <p class="lang-en">Dashboard is a feature in the Workflow System that allows users to create unlimited boards to display Custom Reports, set as the main page as desired, and show 3-7 Custom Reports per board.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Dashboard / Accessing Dashboard</h2>
        <div class="step">
            <p class="lang-th">Dashboard สามารถเข้าถึงได้ผ่านส่วน Dashboard ใน Workflow System ซึ่งใช้สร้างและจัดการกระดาน</p>
            <p class="lang-en">Dashboard can be accessed via the Dashboard section in the Workflow System, used to create and manage boards.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. การตั้งค่า Dashboard / Setting Up Dashboard</h2>
        <div class="step">
            <p class="lang-th">Dashboard ช่วยให้ผู้ใช้สร้างและปรับแต่งกระดานได้ ดังนี้:</p>
            <p class="lang-en">Dashboard enables users to create and customize boards as follows:</p>
            <ul>
                <li><strong>สร้างกระดานไม่จำกัด</strong>: สร้างกระดานได้ตามต้องการ เช่น “ภาพรวมการผลิต”</li>
                <li><strong>ตั้งเป็นหน้ากลาง</strong>: เลือกกระดานใดกระดานหนึ่งเป็นหน้าหลักเมื่อล็อกอิน</li>
                <li><strong>แสดง Custom Report</strong>: เลือก Custom Report 3-7 รายการต่อกระดาน เช่น กราฟ, ตาราง, Pivot Table</li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h2>3. การใช้งาน Dashboard / Using Dashboard</h2>
        <div class="step">
            <p class="lang-th">Dashboard ช่วยให้ผู้ใช้เห็นภาพรวมของข้อมูลจาก Custom Report ในหน้าเดียว สะดวกสำหรับการมอนิเตอร์และตัดสินใจ</p>
            <p class="lang-en">Dashboard allows users to view an overview of data from Custom Reports on a single page, convenient for monitoring and decision-making.</p>
        </div>
        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">
                <strong>Dashboard การผลิต:</strong><br>
                - กระดาน: “ภาพรวมการผลิต” (ตั้งเป็นหน้ากลาง)<br>
                - Custom Report: 5 รายการ<br>
                  1. กราฟความถี่ผลผลิต<br>
                  2. ตารางสรุปรายวัน<br>
                  3. Pivot Table รายเดือน<br>
                  4. กราฟเส้นผลผลิต<br>
                  5. ตารางเปรียบเทียบล็อต<br>
                - ผลลัพธ์: ผู้จัดการเห็นข้อมูลทั้ง 5 รายการในหน้าเดียวเมื่อล็อกอิน
            </p>
            <p class="lang-en">
                <strong>Production Dashboard:</strong><br>
                - Board: “Production Overview” (set as main page)<br>
                - Custom Reports: 5 items<br>
                  1. Production frequency graph<br>
                  2. Daily summary table<br>
                  3. Monthly Pivot Table<br>
                  4. Production line graph<br>
                  5. Lot comparison table<br>
                - Outcome: Manager sees all 5 items on one page upon login
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">ตรวจสอบ Custom Report ที่เลือกเพื่อให้แสดงผลได้อย่างถูกต้อง การจัดเรียง Custom Report 3-7 รายการต่อกระดานช่วยให้ Dashboard อ่านง่ายและมีประสิทธิภาพ</p>
        <p class="lang-en">Ensure selected Custom Reports are configured correctly for proper display. Arranging 3-7 Custom Reports per board makes the Dashboard readable and efficient.</p>
    </div>
  </section>     
                <br>   <br> 
  <section id="Goals0" class="authdoc-section">   
       
        <h1>คู่มือ: Goals ใน Workflow System / Manual: Goals in Workflow System</h1><br>

    <div class="section">
        <h2>ภาพรวมของ Goals / Overview of Goals</h2>
        <p class="lang-th">Goals เป็นฟีเจอร์ใน Workflow System ที่ใช้กำหนดเวลาติดตามสำหรับ Main Process โดยทำงานในรูปแบบ Task เพื่อควบคุมปริมาณข้อมูลและติดตามความคืบหน้า</p>
        <p class="lang-en">Goals is a feature in the Workflow System that sets tracking timelines for Main Processes, operating as Tasks to control data volume and monitor progress.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Goals / Accessing Goals</h2>
        <div class="step">
            <p class="lang-th">Goals สามารถเข้าถึงได้ผ่านส่วน Goals Dashboard ใน Workflow System ซึ่งใช้ตั้งค่าและดูเป้าหมายของ Main Process</p>
            <p class="lang-en">Goals can be accessed via the Goals Dashboard in the Workflow System, used to set and view goals for Main Processes.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. การตั้งค่า Goals / Setting Up Goals</h2>
        <div class="step">
            <p class="lang-th">Goals ช่วยกำหนดกรอบเวลาให้ Main Process โดยตั้งค่าได้ดังนี้:</p>
            <p class="lang-en">Goals allow setting timelines for Main Processes with the following options:</p>
            <ul>
                <li><strong>รายวัน / Daily</strong>: ตั้งเป้าหมายรายวัน เช่น เสร็จภายใน 1 วัน</li>
                <li><strong>หลายเดือน / Multi-month</strong>: ตั้งเป้าหมายระยะยาว เช่น 3 เดือน</li>
                <li><strong>ปี / Yearly</strong>: ตั้งเป้าหมายรายปี เช่น เสร็จภายในปี 2025</li>
                <li><strong>กำหนดวัน / Specific Date</strong>: ตั้งวันครบกำหนด เช่น 2025-04-30</li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h2>3. การใช้งาน Goals / Using Goals</h2>
        <div class="step">
            <p class="lang-th">Goals ทำให้ Main Process ทำงานเหมือน Task โดยควบคุมปริมาณข้อมูลและแจ้งเตือนเมื่อใกล้ครบกำหนด</p>
            <p class="lang-en">Goals enable Main Processes to function as Tasks, controlling data volume and sending alerts when nearing deadlines.</p>
        </div>
        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">
                <strong>Goals การผลิตล็อตสินค้า:</strong><br>
                - Main Process: การผลิตล็อต A123<br>
                - Goal: เสร็จภายใน 7 วัน (ตั้งเป็น Task รายวัน)<br>
                - ผลลัพธ์: ระบบแจ้งเตือนเมื่อเหลือ 2 วัน และจำกัดข้อมูลเฉพาะล็อต A123
            </p>
            <p class="lang-en">
                <strong>Goals for Product Lot Production:</strong><br>
                - Main Process: Production of Lot A123<br>
                - Goal: Complete within 7 days (set as a daily Task)<br>
                - Outcome: System alerts when 2 days remain and limits data to Lot A123
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">ตรวจสอบการตั้งค่า Main Process เพื่อให้ Goals ทำงานได้อย่างราบรื่น การตั้งเวลาให้เหมาะสมช่วยควบคุมข้อมูลและเพิ่มประสิทธิภาพ</p>
        <p class="lang-en">Ensure Main Process settings are configured for smooth Goals operation. Setting appropriate timelines helps control data and improve efficiency.</p>
    </div>
    </section>   
        
           <br>   <br> 
  <section id="Log0" class="authdoc-section">
 <h1>คู่มือ: Log ใน Workflow System / Manual: Log in Workflow System</h1><br>

    <div class="section">
        <h2>ภาพรวมของ Log / Overview of Log</h2>
        <p class="lang-th">Log เป็นฟีเจอร์ใน Workflow System ที่บันทึกและติดตามสถานะของ Main Process เฉพาะ โดยแบ่งเป็น In Progress และ Success เพื่อนับจำนวนกระบวนการที่เกิดขึ้นจริงและติดตามการค้าง</p>
        <p class="lang-en">Log is a feature in the Workflow System that records and tracks the status of Main Processes only, categorized into In Progress and Success, to count actual processes and monitor pending tasks.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Log / Accessing Log</h2>
        <div class="step">
            <p class="lang-th">Log สามารถเข้าถึงได้ผ่านส่วน Log Dashboard ใน Workflow System ซึ่งแสดงสถานะ In Progress และ Success ของ Main Process</p>
            <p class="lang-en">Log can be accessed via the Log Dashboard in the Workflow System, displaying In Progress and Success statuses of Main Processes.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. หัวข้อของ Log / Log Categories</h2>
        <div class="step">
            <p class="lang-th">Log มี 2 หัวข้อหลัก ดังนี้:</p>
            <p class="lang-en">Log has two main categories as follows:</p>
            <ul>
                <li><strong>In Progress / กำลังดำเนินการ</strong>
                    <p class="lang-th">บันทึกเมื่อผู้ใช้เริ่ม Create Main Process แต่ยังไม่ถึงขั้น Completion ใช้เพื่อติดตามกระบวนการที่ค้าง</p>
                    <p class="lang-en">Recorded when a user starts Creating a Main Process but has not reached Completion. Used to track pending processes.</p>
                </li>
                <li><strong>Success / สำเร็จ</strong>
                    <p class="lang-th">บันทึกเมื่อผู้ใช้ดำเนินการ Main Process จนถึงขั้น Completion ใช้เพื่อนับกระบวนการที่สำเร็จ</p>
                    <p class="lang-en">Recorded when a user completes a Main Process. Used to count successful processes.</p>
                </li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h2>3. การใช้งาน Log / Using Log</h2>
        <div class="step">
            <p class="lang-th">Log ช่วยให้ผู้ใช้ตรวจสอบสถานะและความคืบหน้าของ Main Process ได้ โดยสามารถดูจำนวนกระบวนการที่ค้างและสำเร็จ</p>
            <p class="lang-en">Log enables users to check the status and progress of Main Processes, viewing the number of pending and completed processes.</p>
        </div>
        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">
                <strong>Log การผลิตล็อตสินค้า:</strong><br>
                - In Progress: ล็อต A123 เริ่มผลิตเมื่อ 2025-04-20 แต่รอตรวจสอบคุณภาพ<br>
                - Success: ล็อต A124 เสร็จสิ้นและผ่านอนุมัติเมื่อ 2025-04-22<br>
                - ผลลัพธ์: แสดงว่า มี 5 ล็อตค้าง (In Progress) และ 10 ล็อตสำเร็จ (Success)
            </p>
            <p class="lang-en">
                <strong>Log for Product Lot Production:</strong><br>
                - In Progress: Lot A123 started on 2025-04-20 but awaiting quality check<br>
                - Success: Lot A124 completed and approved on 2025-04-22<br>
                - Outcome: Shows 5 lots pending (In Progress) and 10 lots completed (Success)
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">ตรวจสอบการตั้งค่า Main Process เพื่อให้ Log บันทึกสถานะได้อย่างแม่นยำ Log เหมาะสำหรับการติดตามการค้างและประเมินประสิทธิภาพของกระบวนการ</p>
        <p class="lang-en">Ensure Main Process settings are configured for accurate Log recording. Log is ideal for tracking pending tasks and evaluating process efficiency.</p>
    </div>
  </section>
     <br>   <br> 
  
  <section id="Reporting0" class="authdoc-section">
  
  <h1>คู่มือ: Reporting ความสามารถ / Manual: Reporting Capabilities</h1><br>

    <div class="section">
        <h2>ภาพรวมของ Reporting / Overview of Reporting</h2>
        <p class="lang-th">Reporting เป็นฟีเจอร์ที่ช่วยให้ผู้ใช้สร้างและวิเคราะห์รายงานใน Workflow System โดยมี 4 รูปแบบ: Main Reporting, Custom Report, Process Tracking และ Traceability แต่ละรูปแบบตอบโจทย์ความต้องการที่แตกต่างกัน ตั้งแต่รายงานทั่วไปไปจนถึงการติดตามกระบวนการแบบเรียลไทม์และการตรวจสอบย้อนกลับ</p>
        <p class="lang-en">Reporting is a feature that enables users to create and analyze reports in the Workflow System, available in four formats: Main Reporting, Custom Report, Process Tracking, and Traceability. Each format addresses different needs, from general reports to real-time process tracking and traceability.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Reporting / Accessing Reporting</h2>
        <div class="step">
            <p class="lang-th">Reporting สามารถเข้าถึงได้ผ่านส่วน Report Dashboard ใน Workflow System ซึ่งรวมถึง Main Reporting, Custom Report, Process Tracking และ Traceability</p>
            <p class="lang-en">Reporting can be accessed via the Report Dashboard in the Workflow System, which includes Main Reporting, Custom Report, Process Tracking, and Traceability.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. ประเภทของ Reporting / Types of Reporting</h2>
        <div class="step">
            <p class="lang-th">Reporting มี 4 รูปแบบ ดังนี้:</p>
            <p class="lang-en">Reporting is available in four formats as follows:</p>
            <ul>
                <li><strong>Main Reporting / รายงานหลัก</strong>
                    <p class="lang-th">รายงานที่ระบบจัดเตรียมไว้ เช่น รายงานด้านสต็อก เหมาะสำหรับการใช้งานทั่วไป</p>
                    <p class="lang-en">Pre-prepared reports by the system, e.g., stock reports, suitable for general use.</p>
                </li>
                <li><strong>Custom Report / รายงานกำหนดเอง</strong>
                    <p class="lang-th">รายงานที่ผู้ใช้สร้างเอง รองรับการสร้างกราฟ, การปรับแต่งตาราง, การกรองข้อมูลด้วย Query และการประมวลผลด้วย Pivot Table</p>
                    <p class="lang-en">User-defined reports supporting graph creation, table customization, data filtering with Query, and processing with Pivot Table.</p>
                </li>
                <li><strong>Process Tracking / การติดตามกระบวนการ</strong>
                    <p class="lang-th">การติดตามกระบวนการที่เชื่อมต่อกัน โดยใช้ Dataset คงที่ (เช่น การอบ, การผัด, การทอด) แสดงผลแบบเรียลไทม์ในช่วง 7 วันล่าสุด</p>
                    <p class="lang-en">Tracking of interconnected processes using fixed Datasets (e.g., Baking, Stir-frying, Frying), displayed in real-time for the last 7 days.</p>
                </li>
                <li><strong>Traceability / การตรวจสอบย้อนกลับ</strong>
                    <p class="lang-th">การดึงข้อมูลย้อนไป-ย้อนมาแบบอัตโนมัติ เฉพาะฐานข้อมูลประเภท Production เช่น การติดตามวัตถุดิบหรือผลิตภัณฑ์</p>
                    <p class="lang-en">Automatic forward and backward data tracing, exclusive to Production databases, e.g., tracking raw materials or products.</p>
                </li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h2>3. รายละเอียดและคุณสมบัติ / Details and Features</h2>

        <div class="step">
            <h3>Main Reporting / รายงานหลัก</h3>
            <p class="lang-th">Main Reporting เป็นรายงานที่พร้อมใช้งาน เช่น รายงานสต็อก ซึ่งออกแบบมาเพื่อตอบโจทย์การใช้งานทั่วไปในองค์กร</p>
            <p class="lang-en">Main Reporting provides ready-to-use reports, such as stock reports, designed for general organizational needs.</p>
        </div>

        <div class="step">
            <h3>Custom Report / รายงานกำหนดเอง</h3>
            <p class="lang-th">Custom Report มีคุณสมบัติที่ช่วยให้ผู้ใช้ปรับแต่งและวิเคราะห์ข้อมูลได้อย่างยืดหยุ่น ดังนี้:</p>
            <p class="lang-en">Custom Report offers features for flexible data customization and analysis, as follows:</p>
            <ul>
                <li><strong>สร้างได้ไม่จำกัด / Unlimited Creation</strong>: สร้างรายงานได้ตามต้องการ</li>
                <li><strong>ดึงข้อมูล 1 ตารางต่อกราฟ / One Table per Graph</strong>: ดึงข้อมูลจาก 1 ตาราง (เช่น ใบบันทึกผลผลิต) โดยระบบ Join อัตโนมัติผ่านการอ้างอิงเอกสาร</li>
                <li><strong>ปรับแต่งตาราง / Table Customization</strong>: จัดลำดับ, ซ่อน/แสดงคอลัมน์, ปรับขนาดคอลัมน์</li>
                <li><strong>กราฟ 12 ประเภท / 12 Graph Types</strong>: เช่น Bar, Line, Pie โดยกราฟเปลี่ยนตามการจัดเรียงตาราง</li>
                <li><strong>Query</strong>: กรองข้อมูลด้วยเงื่อนไขสูงสุด 3 ข้อ (AND/OR) เช่น ช่วงวันที่หรือข้อมูลเฉพาะ</li>
                <li><strong>Pivot Table</strong>: ประมวลผลตาราง เช่น ความถี่หรือสรุปตามเวลา</li>
            </ul>
        </div>

        <div class="step">
            <h3>Process Tracking / การติดตามกระบวนการ</h3>
            <p class="lang-th">Process Tracking ช่วยติดตามกระบวนการที่เชื่อมต่อกัน โดยใช้ Dataset คงที่ เช่น การอบ, การผัด, การทอด คุณสมบัติ:</p>
            <p class="lang-en">Process Tracking monitors interconnected processes using fixed Datasets, e.g., Baking, Stir-frying, Frying. Features:</p>
            <ul>
                <li><strong>เรียงลำดับ Process</strong>: ผู้ใช้จัดเรียงกระบวนการตามความเป็นจริง เช่น การอบ → การผัด → การทอด</li>
                <li><strong>ปรับแต่ง Tag</strong>: เลือกสีที่แสดง:
                    <ul>
                        <li>Last: ใช้สีล่าสุด</li>
                        <li>Max: ใช้สีที่มีจำนวนมากสุด</li>
                        <li>Important: ใช้สีแดง (ถ้ามี) หรือสีล่าสุด</li>
                    </ul>
                </li>
                <li><strong>สีจาก Workflow</strong>: สีแดง, เขียว, เหลือง จากการอนุมัติหรือปิดติใน Workflow System</li>
                <li><strong>มอนิเตอร์เรียลไทม์</strong>: แสดงข้อมูล 7 วันล่าสุด</li>
                <li><strong>แสดงบนบอร์ดกลาง</strong>: ผู้ใช้เลือกได้ว่าจะแสดงบน Dashboard หรือไม่</li>
            </ul>
        </div>

        <div class="step">
            <h3>Traceability / การตรวจสอบย้อนกลับ</h3>
            <p class="lang-th">Traceability ช่วยดึงข้อมูลย้อนไป-ย้อนมาแบบอัตโนมัติ เฉพาะฐานข้อมูล Production เช่น การติดตามวัตถุดิบหรือผลิตภัณฑ์</p>
            <p class="lang-en">Traceability enables automatic forward and backward data tracing, exclusive to Production databases, e.g., tracking raw materials or products.</p>
        </div>
    </div>

    <div class="section">
        <h2>4. ตัวอย่างการใช้งาน / Usage Examples</h2>

        <div class="example">
            <h3>ตัวอย่างที่ 1: Custom Report / Example 1: Custom Report</h3>
            <p class="lang-th">
                <strong>รายงานผลสอบ:</strong><br>
                - ตาราง: ผลสอบ (ชื่อ, คะแนน, เกรด)<br>
                - Query: กรองเกรด H และ U<br>
                - Pivot Table: แปลงเป็นความถี่<br>
                - ผลลัพธ์:<br>
                จาก: นาย A: 95, H; นาย B: 80, H; นาย C: 10, U; นาย D: 20, U<br>
                เป็น: U (80-): 2; S (50-80): 0; H (-50): 2<br>
                - กราฟ: Bar Graph แสดงความถี่ของเกรด
            </p>
            <p class="lang-en">
                <strong>Exam Results Report:</strong><br>
                - Table: Exam Results (Name, Score, Grade)<br>
                - Query: Filter grades H and U<br>
                - Pivot Table: Convert to frequency<br>
                - Outcome: From: Mr. A: 95, H; Mr. B: 80, H; Mr. C: 10, U; Mr. D: 20, U<br>
                To: U (80-): 2; S (50-80): 0; H (-50): 2<br>
                - Graph: Bar Graph showing grade frequency
            </p>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 2: Process Tracking / Example 2: Process Tracking</h3>
            <p class="lang-th">
                <strong>การติดตามกระบวนการผลิต:</strong><br>
                - Process: การอบ → การผัด → การทอด<br>
                - Tag: Important (ใช้สีแดง ถ้าไม่มีใช้สีล่าสุด)<br>
                - สี: การอบ (เขียว), การผัด (แดง), การทอด (เหลือง)<br>
                - ผลลัพธ์: แสดงกระบวนการผลิตใน 7 วันล่าสุด โดยเน้นสีแดง (การผัด) บน Dashboard<br>
            </p>
            <p class="lang-en">
                <strong>Production Process Tracking:</strong><br>
                - Process: Baking → Stir-frying → Frying<br>
                - Tag: Important (use red, or latest if no red)<br>
                - Colors: Baking (Green), Stir-frying (Red), Frying (Yellow)<br>
                - Outcome: Displays production process for the last 7 days, highlighting red (Stir-frying) on the Dashboard
            </p>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 3: Traceability / Example 3: Traceability</h3>
            <p class="lang-th">
                <strong>การตรวจสอบย้อนกลับวัตถุดิบ:</strong><br>
                - ฐานข้อมูล: Production<br>
                - การใช้งาน: ติดตามวัตถุดิบจากล็อต A123 ไปยังผลิตภัณฑ์สำเร็จรูป<br>
                - ผลลัพธ์: แสดงข้อมูลย้อนกลับอัตโนมัติ เช่น วัตถุดิบ → การอบ → ผลิตภัณฑ์
            </p>
            <p class="lang-en">
                <strong>Raw Material Traceability:</strong><br>
                - Database: Production<br>
                - Usage: Track raw material from Lot A123 to finished product<br>
                - Outcome: Automatically displays traceability, e.g., Raw Material → Baking → Product
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การใช้ Reporting ควรเลือกประเภทที่เหมาะสมกับความต้องการ Process Tracking และ Traceability เหมาะสำหรับการผลิตที่ต้องการการมอนิเตอร์และตรวจสอบย้อนกลับ ตรวจสอบการอ้างอิงเอกสารและการตั้งค่า Process เพื่อให้รายงานทำงานได้อย่างราบรื่น</p>
        <p class="lang-en">Select the appropriate Reporting type for your needs. Process Tracking and Traceability are ideal for production requiring monitoring and backward tracing. Ensure document references and Process settings are configured for smooth reporting.</p>
    </div>
  
        </section>
          <br>   <br> 
        
  <section id="Eform0" class="authdoc-section">
   <h1>คู่มือ: Eform ความสามารถ / Manual: Eform Capabilities</h1>  <br> 

    <div class="section">
        <h2>ภาพรวมของ Eform / Overview of Eform</h2>
        <p class="lang-th">Eform เป็นฟีเจอร์ที่ช่วยให้ผู้ใช้สร้างแบบฟอร์มดิจิทัล (Form) แบบลากวางเพื่อรับข้อมูลเข้าในระบบตามความต้องการ โดยควบคุมเวอร์ชันผ่านใบ DAR (Document Approval Request) Eform ใช้ได้ 1 ชนิดงานต่อ 1 แบบฟอร์ม และสามารถใช้ในงาน Task หรือ Main Process Setting</p>
        <p class="lang-en">Eform is a feature that allows users to create digital forms using a drag-and-drop interface to collect data as needed, with version control managed via a DAR (Document Approval Request). Each Eform is used for one type of task and can be applied in Task workflows or Main Process Settings.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Eform / Accessing Eform</h2>
        <div class="step">
            <p class="lang-th">Eform สามารถสร้างและจัดการได้ผ่านส่วน Eform Designer ในระบบ ซึ่งอยู่ใน Workflow System หรือ Task Management</p>
            <p class="lang-en">Eform can be created and managed through the Eform Designer section of the system, located within the Workflow System or Task Management.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. ขั้นตอนการสร้างและใช้งาน Eform / Steps to Create and Use Eform</h2>
        <div class="step">
            <p class="lang-th">การสร้างและใช้งาน Eform ประกอบด้วยการออกแบบแบบฟอร์ม, การควบคุมเวอร์ชัน, และการประยุกต์ใช้ในงานต่างๆ ดังนี้:</p>
            <p class="lang-en">Creating and using Eform involves designing the form, managing versions, and applying it to various tasks, as follows:</p>
            <ul>
                <li><strong>ออกแบบ Eform / Design Eform</strong>
                    <p class="lang-th">ใช้เครื่องมือลากวางเพื่อสร้างช่องรับข้อมูล (Input Fields) และกำหนดหัวข้อ เช่น ช่องสำหรับ “วันที่”, “ชื่อเครื่องจักร”, “ค่าเครื่องจักร”</p>
                    <p class="lang-en">Use the drag-and-drop tool to create input fields and define headers, e.g., fields for “Date”, “Machine Name”, “Machine Value”.</p>
                </li>
                <li><strong>ควบคุมเวอร์ชันผ่านใบ DAR / Version Control via DAR</strong>
                    <p class="lang-th">ทุกการแก้ไข Eform ต้องผ่านใบ DAR เพื่อบันทึกเวอร์ชันใหม่และรักษาข้อมูลเก่า</p>
                    <p class="lang-en">Every Eform revision requires a DAR to record the new version and preserve old data.</p>
                </li>
                <li><strong>ใช้งานใน Task / Use in Task</strong>
                    <p class="lang-th">กำหนด Eform ให้ใช้ในงาน Task เช่น การกรอกข้อมูลเครื่องจักรรายวัน ผู้ใช้จะกรอกข้อมูลตามหัวข้อที่กำหนดใน Eform</p>
                    <p class="lang-en">Assign Eform to Task workflows, e.g., daily machine data entry, where users fill in data based on the defined headers.</p>
                </li>
                <li><strong>ใช้งานใน Main Process Setting / Use in Main Process Setting</strong>
                    <p class="lang-th">ใช้ Eform เพื่อรับข้อมูลเข้าใน Workflow System โดยกำหนดหัวข้อล่วงหน้า เช่น การกรอกข้อมูลในระบบจัดซื้อ (PR, PO)</p>
                    <p class="lang-en">Use Eform to collect input data in the Workflow System with predefined headers, e.g., data entry for procurement (PR, PO).</p>
                </li>
            </ul>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 1 / Example 1</h3>
            <p class="lang-th">
                <strong>Eform สำหรับบันทึกข้อมูลเครื่องจักรรายวัน:</strong><br>
                - หัวข้อ: วันที่, ชื่อเครื่องจักร, ค่าเครื่องจักร, ผู้บันทึก<br>
                - การใช้งาน: ใช้ใน Task เพื่อให้พนักงานกรอกข้อมูลเครื่องจักรทุกวัน<br>
                - การควบคุมเวอร์ชัน: อัพเดต Eform ผ่านใบ DAR เมื่อเพิ่มช่อง “หมายเหตุ”<br>
                ผลลัพธ์: พนักงานกรอกข้อมูลเครื่องจักรตาม Eform และข้อมูลบันทึกในระบบเรียบร้อย
            </p>
            <p class="lang-en">
                <strong>Eform for Daily Machine Data Entry:</strong><br>
                - Headers: Date, Machine Name, Machine Value, Recorder<br>
                - Usage: Applied in Task for daily machine data entry by staff<br>
                - Version Control: Updated Eform via DAR when adding a “Remarks” field<br>
                Outcome: Staff enter machine data using Eform, and data is recorded in the system.
            </p>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 2 / Example 2</h3>
            <p class="lang-th">
                <strong>Eform สำหรับระบบจัดซื้อ:</strong><br>
                - หัวข้อ: เลขที่ PR, วันที่, รายการสินค้า, ผู้ขอซื้อ<br>
                - การใช้งาน: ใช้ใน Main Process Setting เพื่อรับข้อมูล PR ใน Workflow System<br>
                - การควบคุมเวอร์ชัน: อัพเดต Eform ผ่านใบ DAR เมื่อเปลี่ยนหัวข้อ “ผู้ขอซื้อ” เป็น “ผู้อนุมัติ”<br>
                ผลลัพธ์: ข้อมูล PR ถูกกรอกผ่าน Eform และไหลตาม Workflow แต่ต้องตั้งค่า Main Process ใหม่หากเปลี่ยนแปลงมาก
            </p>
            <p class="lang-en">
                <strong>Eform for Procurement System:</strong><br>
                - Headers: PR Number, Date, Item List, Requester<br>
                - Usage: Applied in Main Process Setting to collect PR data in the Workflow System<br>
                - Version Control: Updated Eform via DAR when changing “Requester” to “Approver”<br>
                Outcome: PR data is entered via Eform and flows through the Workflow, but Main Process settings may need reconfiguration for significant changes.
            </p>
        </div>
    </div>

    <div class="section">
        <h2>3. ข้อจำกัดและการจัดการเวอร์ชัน / Limitations and Version Management</h2>
        <div class="step">
            <p class="lang-th">เมื่อมีการแก้ไข Eform (Revision) อาจเกิดปัญหาดังนี้:</p>
            <p class="lang-en">When revising an Eform, the following issues may arise:</p>
            <ul>
                <li><strong>การดูข้อมูลเก่า / Accessing Old Data</strong>
                    <p class="lang-th">ผู้ใช้ต้องสลับระหว่างเวอร์ชันใหม่และเก่าของ Eform เพื่อดูข้อมูลเก่า ซึ่งอาจทำให้สับสน</p>
                    <p class="lang-en">Users must switch between new and old Eform versions to view old data, which can be confusing.</p>
                </li>
                <li><strong>ผลกระทบต่อ Main Process Setting / Impact on Main Process Setting</strong>
                    <p class="lang-th">หากการเปลี่ยนแปลง Eform มีมาก อาจต้องตั้งค่า Main Process ใหม่ใน Workflow System แต่ข้อมูลใน Task ยังใช้งานได้ปกติ</p>
                    <p class="lang-en">Significant Eform changes may require reconfiguring Main Process settings in the Workflow System, though Task data remains functional.</p>
                </li>
            </ul>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การออกแบบ Eform ควรคำนึงถึงความจำเป็นในการเปลี่ยนแปลงในอนาคตเพื่อลดผลกระทบจากการแก้ไขเวอร์ชัน ผู้ใช้ควรตรวจสอบข้อมูลเก่าและตั้งค่า Main Process ใหม่เมื่อจำเป็น</p>
        <p class="lang-en">Eform design should consider future changes to minimize the impact of revisions. Users should verify old data and reconfigure Main Process settings when necessary.</p>
    </div>
      </section>
    
       
       
       
       
              
       
      </section>
      
       <br>   <br> <br> 
      <section id="RSD" class="authdoc-section">
       <br> 
       <h1>RSD (Relative Standard Deviation) หรือชื่อเต็มคือ %RSD</h1> <br>

    <div class="section">
    RSD (Relative Standard Deviation) หรือชื่อเต็มคือ %RSD ใช้วัด ความแปรปรวนสัมพัทธ์ ของข้อมูลเมื่อเทียบกับค่าเฉลี่ย (mean) ในรูปแบบ เปอร์เซ็นต์ เพื่อบอกว่า “ข้อมูลกระจายตัวมากน้อยแค่ไหนเมื่อเทียบกับค่าเฉลี่ย”
    
  <table border="1" cellpadding="8" cellspacing="0" style="border-collapse: collapse; text-align: center;">
  <thead style="background-color: #f2f2f2;">
    <tr>
      <th>RSD (%)</th>
      <th>ความหมายทั่วไป</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>&lt; 2%</td>
      <td>ดีมาก – ข้อมูลมีความเสถียรสูง</td>
    </tr>
    <tr>
      <td>2% – 5%</td>
      <td>ดีพอสมควร – ยังคงใช้งานได้</td>
    </tr>
    <tr>
      <td>5% – 10%</td>
      <td>พอใช้ – ข้อมูลเริ่มแปรปรวน แต่ยังไม่หลุดเกณฑ์</td>
    </tr>
    <tr>
      <td>&gt; 10%</td>
      <td>ไม่เสถียร – ข้อมูลมีความแปรปรวนสูง</td>
    </tr>
  </tbody>
</table>
    </div>
      </section>
      
      
      <br>   <br> <br>   <br> <br>   <br> <br>   <br> <br>   <br>
      <section id="Main0" class="authdoc-section">
       <br> 
 <h1>คู่มือ: Main Process Setting ความสามารถ / Manual: Main Process Setting Capabilities</h1> <br>

    <div class="section">
        <h2>ภาพรวมของ Main Process Setting / Overview of Main Process Setting</h2>
        <p class="lang-th">Main Process Setting เป็นขั้นตอนหลักในการสร้าง Workflow System หรือแอปพลิเคชันภายในระบบ ผู้ใช้สามารถกำหนดกระบวนการหลัก (Main Process) เช่น ระบบขอซื้อ, ระบบจัดซื้อ, หรือระบบรับสินค้า และตั้งค่าพื้นฐานก่อนเข้าสู่การกำหนดขั้นตอนย่อยใน Workflow Setting</p>
        <p class="lang-en">Main Process Setting is the primary step in creating a Workflow System or application within the platform. Users can define main processes, such as Purchase Requisition, Purchase Order, or Goods Receipt, and configure basic settings before proceeding to detailed Workflow Setting.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Main Process Setting / Accessing Main Process Setting</h2>
        <div class="step">
            <p class="lang-th">Main Process Setting เป็นหนึ่งในเมนูการจัดการ Workflow System ผู้ใช้สามารถเข้าถึงเมนูนี้เพื่อสร้าง Main Process ใหม่สำหรับแอปพลิเคชันที่ต้องการ</p>
            <p class="lang-en">Main Process Setting is a menu option within the Workflow System management interface. Users can access this menu to create a new Main Process for their desired application.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. ขั้นตอนการตั้งค่า Main Process / Steps to Configure Main Process</h2>
        <div class="step">
            <p class="lang-th">การตั้งค่า Main Process ประกอบด้วยขั้นตอนต่อไปนี้:</p>
            <p class="lang-en">Configuring a Main Process involves the following steps:</p>
            <ul>
                <li><strong>ตั้งชื่อ Main Process / Name the Main Process</strong>
                    <p class="lang-th">ระบุชื่อกระบวนการ เช่น “ระบบขอซื้อ”, “ระบบจัดซื้อ”, หรือ “ระบบรับสินค้า”</p>
                    <p class="lang-en">Specify the process name, e.g., “Purchase Requisition”, “Purchase Order”, or “Goods Receipt”.</p>
                </li>
                <li><strong>เพิ่มคำอธิบาย / Add Description</strong>
                    <p class="lang-th">อธิบายวัตถุประสงค์ของกระบวนการ เช่น “จัดการคำขอซื้อสินค้า” หรือ “รับและตรวจสอบสินค้าเข้า”</p>
                    <p class="lang-en">Describe the purpose of the process, e.g., “Manage purchase requests” or “Receive and verify incoming goods”.</p>
                </li>
                <li><strong>ระบุ Eform ที่จะใช้ / Specify Eform</strong>
                    <p class="lang-th">เลือก Eform ที่จะใช้ในกระบวนการ เช่น Eform สำหรับ PR, PO, หรือ GR</p>
                    <p class="lang-en">Select the Eform to be used in the process, e.g., Eform for PR, PO, or GR.</p>
                </li>
                <li><strong>เลือก Process ก่อนหน้าที่จะรับเอกสาร (Join) / Select Previous Process for Document Joining</strong>
                    <p class="lang-th">กำหนดกระบวนการที่เชื่อมโยง เช่น Join ระบบขอซื้อ (PR) เพื่อส่งเอกสารไปยังระบบจัดซื้อ (PO)</p>
                    <p class="lang-en">Define the linked process, e.g., Join Purchase Requisition (PR) to send documents to Purchase Order (PO).</p>
                </li>
                <li><strong>เลือกไอคอน / Select Icon</strong>
                    <p class="lang-th">เลือกไอคอนเพื่อแสดงในระบบ เช่น ไอคอนเอกสารสำหรับ PR หรือไอคอนรถเข็นสำหรับ PO</p>
                    <p class="lang-en">Choose an icon to represent the process, e.g., a document icon for PR or a cart icon for PO.</p>
                </li>
            </ul>
        </div>

        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">
                <strong>การตั้งค่าระบบจัดซื้อ:</strong><br>
                - ชื่อ: ระบบจัดซื้อ<br>
                - คำอธิบาย: จัดการการสั่งซื้อสินค้าจากคำขอซื้อ<br>
                - Eform: Eform PO<br>
                - Join: ระบบขอซื้อ (PR)<br>
                - ไอคอน: รถเข็น<br>
                ผลลัพธ์: สร้าง Main Process สำหรับระบบจัดซื้อที่พร้อมเชื่อมต่อกับ PR และเข้าสู่ Workflow Setting เพื่อกำหนดขั้นตอนต่อไป
            </p>
            <p class="lang-en">
                <strong>Configuring Purchase Order System:</strong><br>
                - Name: Purchase Order<br>
                - Description: Manage procurement from purchase requests<br>
                - Eform: PO Eform<br>
                - Join: Purchase Requisition (PR)<br>
                - Icon: Cart<br>
                Outcome: Creates a Main Process for the Purchase Order system, linked to PR, ready for Workflow Setting to define subsequent steps.
            </p>
        </div>
    </div>

    <div class="section">
        <h2>3. การเข้าสู่ Workflow Setting / Proceeding to Workflow Setting</h2>
        <div class="step">
            <p class="lang-th">เมื่อตั้งค่า Main Process เสร็จสิ้น ผู้ใช้สามารถเข้าสู่ Workflow Setting เพื่อกำหนดขั้นตอนย่อย เช่น Create Step, Review Step, Approval Step, และ Receive Step เพื่อทำให้ Workflow System ทำงานได้ครบถ้วน</p>
            <p class="lang-en">Once the Main Process is configured, users can proceed to Workflow Setting to define sub-steps, such as Create Step, Review Step, Approval Step, and Receive Step, to complete the Workflow System.</p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การตั้งค่า Main Process เป็นขั้นตอนสำคัญที่ต้องระบุข้อมูลให้ครบถ้วน เช่น การ Join กระบวนการที่ถูกต้อง เพื่อให้ Workflow System ทำงานได้อย่างราบรื่น การเลือก Eform และไอคอนช่วยให้ผู้ใช้เข้าใจกระบวนการได้ง่ายขึ้น</p>
        <p class="lang-en">Configuring the Main Process is a critical step that requires complete information, such as accurate process joining, to ensure the Workflow System operates smoothly. Selecting the appropriate Eform and icon enhances user understanding of the process.</p>
    </div>

      </section>

    <br>   <br>
      <section id="Workflow0" class="authdoc-section">  
        <h1>คู่มือ: Workflow Setting ความสามารถ / Manual: Workflow Setting Capabilities</h1>

    <div class="section">
        <h2>ภาพรวมของ Workflow Setting / Overview of Workflow Setting</h2>
        <p class="lang-th">Workflow Setting เป็นขั้นตอนที่ผู้ใช้กำหนดลำดับขั้นตอน (Steps) ของกระบวนการใน Workflow System โดยสามารถเลือกและจัดเรียงขั้นตอน เช่น จัดทำ (Create), ตรวจสอบ (Review), อนุมัติ (Approval), หรือเสร็จสิ้น (Complete) และตั้งชื่อขั้นตอนได้ตามต้องการ เพื่อสร้างกระบวนการที่ยืดหยุ่นและไม่ซ้ำกัน</p>
        <p class="lang-en">Workflow Setting allows users to define the sequence of steps in a Workflow System. Users can select and arrange steps, such as Create, Review, Approval, or Complete, and name them as needed, enabling flexible and unique process configurations.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Workflow Setting / Accessing Workflow Setting</h2>
        <div class="step">
            <p class="lang-th">Workflow Setting เข้าถึงได้หลังจากตั้งค่า Main Process Setting เสร็จสิ้น ผู้ใช้สามารถกำหนดขั้นตอนย่อยของ Workflow System เพื่อให้กระบวนการทำงานได้ครบถ้วน</p>
            <p class="lang-en">Workflow Setting is accessible after completing Main Process Setting. Users can define sub-steps of the Workflow System to ensure the process operates fully.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. ขั้นตอนการตั้งค่า Workflow Setting / Steps to Configure Workflow Setting</h2>
        <div class="step">
            <p class="lang-th">การตั้งค่า Workflow Setting ประกอบด้วยการกำหนดลำดับขั้นตอนและชื่อขั้นตอน ดังนี้:</p>
            <p class="lang-en">Configuring Workflow Setting involves defining the sequence of steps and naming them, as follows:</p>
            <ul>
                <li><strong>เลือกและจัดเรียงขั้นตอน / Select and Arrange Steps</strong>
                    <p class="lang-th">ผู้ใช้สามารถเลือกขั้นตอน เช่น จัดทำ (Create), ตรวจสอบ (Review), อนุมัติ (Approval), หรือเสร็จสิ้น (Complete) และจัดเรียงตามลำดับที่ต้องการ</p>
                    <p class="lang-en">Users can select steps, such as Create, Review, Approval, or Complete, and arrange them in the desired order.</p>
                </li>
                <li><strong>ตั้งชื่อขั้นตอน / Name the Steps</strong>
                    <p class="lang-th">กำหนดชื่อขั้นตอนตามความต้องการ เช่น “จัดทำคำขอซื้อ”, “ตรวจสอบเอกสาร”, หรือ “อนุมัติการสั่งซื้อ”</p>
                    <p class="lang-en">Assign custom names to steps, e.g., “Prepare Purchase Request”, “Verify Documents”, or “Approve Purchase Order”.</p>
                </li>
                <li><strong>กำหนด Step ให้ตรงกับกระบวนการ / Assign Steps to Processes</strong>
                    <p class="lang-th">เชื่อมโยงขั้นตอนกับกระบวนการที่กำหนดใน Main Process Setting เช่น ขั้นตอน “จัดทำ” ใช้ Eform PR หรือขั้นตอน “อนุมัติ” ใช้ Approval Step</p>
                    <p class="lang-en">Link steps to processes defined in Main Process Setting, e.g., the “Create” step uses the PR Eform, or the “Approval” step uses the Approval Step.</p>
                </li>
            </ul>
        </div>

        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">
                <strong>Workflow สำหรับระบบจัดซื้อ:</strong><br>
                - ลำดับขั้นตอน: จัดทำ -> ตรวจสอบ -> อนุมัติ -> เสร็จสิ้น<br>
                - ชื่อขั้นตอน:<br>
                  - จัดทำ: “สร้างคำสั่งซื้อ” (ใช้ Eform PO)<br>
                  - ตรวจสอบ: “ตรวจสอบเอกสาร PO” (ใช้ Review Step)<br>
                  - อนุมัติ: “อนุมัติ PO” (ใช้ Approval Step)<br>
                  - เสร็จสิ้น: “ปิดกระบวนการ”<br>
                ผลลัพธ์: Workflow System สำหรับระบบจัดซื้อที่ครบถ้วนและพร้อมใช้งาน
            </p>
            <p class="lang-en">
                <strong>Workflow for Purchase Order System:</strong><br>
                - Step Sequence: Create -> Review -> Approval -> Complete<br>
                - Step Names:<br>
                  - Create: “Generate Purchase Order” (uses PO Eform)<br>
                  - Review: “Verify PO Documents” (uses Review Step)<br>
                  - Approval: “Approve PO” (uses Approval Step)<br>
                  - Complete: “Close Process”<br>
                Outcome: A complete and ready-to-use Workflow System for the Purchase Order process.
            </p>
        </div>

        <div class="example">
            <h3>ตัวอย่างเพิ่มเติม / Additional Example</h3>
            <p class="lang-th">
                <strong>Workflow สำหรับระบบขอซื้อที่ง่าย:</strong><br>
                - ลำดับขั้นตอน: จัดทำ -> อนุมัติ -> เสร็จสิ้น<br>
                - ชื่อขั้นตอน:<br>
                  - จัดทำ: “สร้างคำขอซื้อ” (ใช้ Eform PR)<br>
                  - อนุมัติ: “อนุมัติ PR” (ใช้ Approval Step)<br>
                  - เสร็จสิ้น: “ปิดคำขอ”<br>
                ผลลัพธ์: Workflow System ที่เรียบง่ายสำหรับการขอซื้อ
            </p>
            <p class="lang-en">
                <strong>Workflow for Simplified Purchase Requisition:</strong><br>
                - Step Sequence: Create -> Approval -> Complete<br>
                - Step Names:<br>
                  - Create: “Generate Purchase Request” (uses PR Eform)<br>
                  - Approval: “Approve PR” (uses Approval Step)<br>
                  - Complete: “Close Request”<br>
                Outcome: A simple Workflow System for purchase requisitions.
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">Workflow Setting ต้องกำหนดขั้นตอนให้สอดคล้องกับกระบวนการที่ตั้งไว้ใน Main Process Setting การตั้งชื่อขั้นตอนที่ชัดเจนและการเชื่อมโยง Step ให้ตรง (เช่น Create Step, Approval Step) จะช่วยให้ Workflow System ทำงานได้อย่างราบรื่น</p>
        <p class="lang-en">Workflow Setting must align steps with the processes defined in Main Process Setting. Clear step naming and accurate step assignment (e.g., Create Step, Approval Step) ensure the Workflow System operates smoothly.</p>
    </div>
          </section>  


  <br>   <br>
   <section id="Access0" class="authdoc-section">
   <h1>คู่มือ: Access Permissions ความสามารถ / Manual: Access Permissions Capabilities</h1><br>

    <div class="section">
        <h2>ภาพรวมของ Access Permissions / Overview of Access Permissions</h2>
        <p class="lang-th">Access Permissions เป็นการกำหนดสิทธิ์การเข้าถึงสำหรับแต่ละ Main Process โดยสามารถปรับแต่งตามสายงาน (Job Description หรือ JD) และกำหนดผู้ใช้ตามบทบาท เช่น ผู้จัดทำ, ผู้ตรวจ, หรือผู้อนุมัติ ผู้ใช้สามารถสร้าง Access Permissions ได้ไม่จำกัดเพื่อให้เหมาะสมกับความต้องการ และเอกสารจะไหลตาม Workflow ตามสิทธิ์ที่กำหนด</p>
        <p class="lang-en">Access Permissions define access rights for each Main Process, customizable by job description (JD) and assigned to roles such as Creator, Reviewer, or Approver. Users can create unlimited Access Permissions to fit their needs, and documents flow through the Workflow based on the defined rights.</p>
    </div>

    <div class="section">
        <h2>1. การเข้าถึง Access Permissions / Accessing Access Permissions</h2>
        <div class="step">
            <p class="lang-th">Access Permissions สามารถกำหนดได้ภายใน Main Process Setting หรือ Workflow Setting โดยเป็นส่วนหนึ่งของการจัดการสิทธิ์การเข้าถึงสำหรับ Workflow System</p>
            <p class="lang-en">Access Permissions can be configured within Main Process Setting or Workflow Setting, as part of managing access rights for the Workflow System.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. ขั้นตอนการตั้งค่า Access Permissions / Steps to Configure Access Permissions</h2>
        <div class="step">
            <p class="lang-th">การตั้งค่า Access Permissions ประกอบด้วยการกำหนดบทบาทและสิทธิ์สำหรับแต่ละขั้นตอนใน Workflow ดังนี้:</p>
            <p class="lang-en">Configuring Access Permissions involves defining roles and rights for each step in the Workflow, as follows:</p>
            <ul>
                <li><strong>กำหนดบทบาทสำหรับผู้จัดทำ / Define Role for Creator</strong>
                    <p class="lang-th">ระบุกลุ่มหรือสายงานที่สามารถจัดทำเอกสารได้ เช่น “ผลิต” หรือ “วิศวะ” หรือ “ทุกคนในฝ่ายผลิต”</p>
                    <p class="lang-en">Specify the group or department that can create documents, e.g., “Production”, “Engineering”, or “All Production Staff”.</p>
                </li>
                <li><strong>กำหนดผู้ตรวจหรือผู้อนุมัติ / Define Reviewer or Approver</strong>
                    <p class="lang-th">ระบุชื่อบุคคลหรือตำแหน่งเฉพาะสำหรับขั้นตอนตรวจสอบหรืออนุมัติ เช่น “หัวหน้าผลิต” หรือ “หัวหน้าวิศวะ”</p>
                    <p class="lang-en">Specify the individual or position for review or approval steps, e.g., “Production Supervisor” or “Engineering Manager”.</p>
                </li>
                  <li><strong>กำหนดผู้รับเอกสาร / Define Document Recipient</strong>
                    <p class="lang-th">ระบุกลุ่มหรือบุคคลที่สามารถรับเอกสารได้ เช่น “ฝ่ายคลัง” หรือ “เจ้าหน้าที่จัดซื้อ”</p>
                    <p class="lang-en">Specify the group or individual who can receive documents, e.g., “Warehouse Department” or “Procurement Officer”.</p>
                </li>
                <li><strong>กำหนดยอดอนุมัติ (ถ้ามี) / Define Approval Limits (if applicable)</strong>
                    <p class="lang-th">กำหนดวงเงินอนุมัติสำหรับผู้อนุมัติ เช่น “หัวหน้าผลิต: อนุมัติ 500”, “ผู้จัดการผลิต: อนุมัติ 5,000”</p>
                    <p class="lang-en">Set approval limits for approvers, e.g., “Production Supervisor: Approve 500”, “Production Manager: Approve 5,000”.</p>
                </li>
                <li><strong>จัดการการไหลของเอกสาร / Manage Document Flow</strong>
                    <p class="lang-th">เอกสารจะไหลตาม Workflow ตามสิทธิ์ที่กำหนด เช่น จาก “ผู้จัดทำ” ไป “ผู้ตรวจ” และ “ผู้อนุมัติ”</p>
                    <p class="lang-en">Documents flow through the Workflow based on defined rights, e.g., from “Creator” to “Reviewer” and “Approver”.</p>
                </li>
            </ul>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 1 / Example 1</h3>
            <p class="lang-th">
                <strong>Access Permissions สำหรับระบบการผลิต:</strong><br>
                - ผู้จัดทำ: ฝ่ายผลิต<br>
                - ผู้ตรวจ: หัวหน้าผลิต (ระบุชื่อ)<br>
                - ผู้อนุมัติ: ผู้จัดการผลิต (ระบุชื่อ)<br>
                ผลลัพธ์: เอกสารการผลิตจะไหลจากฝ่ายผลิตไปยังหัวหน้าผลิตเพื่อตรวจสอบ และไปยังผู้จัดการผลิตเพื่ออนุมัติ
            </p>
            <p class="lang-en">
                <strong>Access Permissions for Production System:</strong><br>
                - Creator: Production Department<br>
                - Reviewer: Production Supervisor (specified name)<br>
                - Approver: Production Manager (specified name)<br>
                Outcome: Production documents flow from the Production Department to the Supervisor for review and to the Manager for approval.
            </p>
        </div>

        <div class="example">
            <h3>ตัวอย่างที่ 2 / Example 2</h3>
            <p class="lang-th">
                <strong>Access Permissions สำหรับระบบจัดซื้อด้วยยอดอนุมัติ:</strong><br>
                - ผู้จัดทำ: ทุกคนในฝ่ายผลิต<br>
                - ผู้อนุมัติ 1: หัวหน้าผลิต (อนุมัติ 500)<br>
                - ผู้อนุมัติ 2: ผู้จัดการผลิต (อนุมัติ 5,000)<br>
                ผลลัพธ์: เอกสารจัดซื้อที่มีมูลค่าไม่เกิน 500 จะได้รับการอนุมัติจากหัวหน้าผลิต ถ้าเกิน 500 จะต้องไปถึงผู้จัดการผลิต
            </p>
            <p class="lang-en">
                <strong>Access Permissions for Purchase System with Approval Limits:</strong><br>
                - Creator: All Production Staff<br>
                - Approver 1: Production Supervisor (Approves 500)<br>
                - Approver 2: Production Manager (Approves 5,000)<br>
                Outcome: Purchase documents up to 500 are approved by the Supervisor; those exceeding 500 require Manager approval.
            </p>
        </div>
    </div>

    <div class="section">
        <h2>3. การจัดการกรณีที่มีหลาย Access Permissions / Handling Multiple Access Permissions</h2>
        <div class="step">
            <p class="lang-th">หากผู้จัดทำมีหลาย Access Permissions ระบบจะเลือก Access Permissions แรกเป็นค่าเริ่มต้น ผู้ใช้ควรตรวจสอบและเลือก Access Permissions ที่เหมาะสมก่อนดำเนินการ</p>
            <p class="lang-en">If a creator has multiple Access Permissions, the system defaults to the first one. Users should verify and select the appropriate Access Permissions before proceeding.</p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การตั้งค่า Access Permissions ต้องระบุบทบาทและผู้ใช้ให้ชัดเจน โดยเฉพาะในขั้นตอนตรวจสอบและอนุมัติ การกำหนดยอดอนุมัติช่วยให้ Workflow สอดคล้องกับนโยบายขององค์กร ควรระวังกรณีที่มีหลาย Access Permissions เพื่อป้องกันความสับสน</p>
        <p class="lang-en">Configuring Access Permissions requires clear role and user assignments, especially for review and approval steps. Setting approval limits ensures the Workflow aligns with organizational policies. Be cautious with multiple Access Permissions to avoid confusion.</p>
    </div>
      </section>
      






      <section id="jwt" class="authdoc-section">
        <h2>JWT</h2>
        <p>JWT is the default authentication method. It works by issuing a signed token after login...</p>
      </section>

      <section id="Step0" class="authdoc-section">
        <h2>นิยามคำว่า Step (Definition of Step)</h2><br>
        <p>"Step" หมายถึง ขั้นตอนหรือคำสั่งที่กำหนดการดำเนินงานในระบบ โดยแต่ละ Step มีหน้าที่เฉพาะและทำงานตามลำดับที่กำหนดเพื่อให้กระบวนการสมบูรณ์</p>
      <p>     
A "Step" refers to a stage or command that dictates an operation in the system. Each Step has a specific function and operates in a defined sequence to complete the process.

</p>



 <div class="section">
        <h2>คำสั่งและขั้นตอนในระบบ / Commands and Steps in the System</h2>

        <div class="step">
            <h3><a href="#Step1">1. Create (สร้าง/กรอกข้อมูล)</a></h3>
            <p class="lang-th">ใช้สำหรับสร้างรายการใหม่หรือกรอกข้อมูลเริ่มต้น เป็นขั้นตอนแรกของกระบวนการเสมอ</p>
            <p class="lang-en">Used to create a new entry or input initial data. This is always the first step in the process.</p>
        </div>

        <div class="step">
            <h3><a href="#Step2">2. Review (ตรวจสอบ)</a></h3>
            <p class="lang-th">การทวนสอบข้อมูล โดยผู้ใช้มีสิทธิ์แก้ไขข้อมูลได้ในขั้นตอนนี้</p>
            <p class="lang-en">The process of verifying data, where users have the authority to edit the information.</p>
        </div>

        <div class="step">
            <h3><a href="#Step3">3. Approval (การตัดสินใจ)</a></h3>
            <p class="lang-th">การอนุมัติหรือตัดสินใจ เมื่อดำเนินการแล้วจะกระตุ้นให้ Step อัตโนมัติบางตัวทำงาน เช่น Record หรือ Forward</p>
            <p class="lang-en">The decision-making or approval step. Once completed, it triggers certain automatic Steps like Record or Forward.</p>
        </div>

        <div class="step">
            <h3>4. Clear (การปิดงาน)</h3>
            <p class="lang-th">การกรอกข้อมูลต่อเนื่อง เช่น การเบิกจ่ายและลงยอดการใช้งานจริง</p>
            <p class="lang-en">Continuous data entry, such as recording disbursements and actual usage.</p>
        </div>

        <div class="step">
            <h3>5. Record (บันทึกอัตโนมัติ)</h3>
            <p class="lang-th">คำสั่งอัตโนมัติสำหรับจัดการข้อมูลประเภททะเบียน เมื่อ Approval เสร็จสิ้นและมี Step Record ระบบจะปรับปรุงทะเบียนในขั้นตอนนี้</p>
            <p class="lang-en">An automatic command for handling registry-type data. After Approval, if a Record Step exists, the system updates the registry in this step.</p>
        </div>

        <div class="step">
            <h3>6. Forward (ส่งต่ออัตโนมัติ)</h3>
            <p class="lang-th">คำสั่งอัตโนมัติที่ส่งเอกสารไปยังระบบอื่นในแอปพลิเคชัน เมื่อ Approval เสร็จสิ้นและมี Step Forward</p>
            <p class="lang-en">An automatic command that forwards documents to another system within the application after Approval, if a Forward Step is defined.</p>
        </div>

        <div class="step">
            <h3><a href="#Step7">7. Receive (รับเอกสาร)</a></h3>
            <p class="lang-th">คำสั่งที่อนุญาตให้รับเอกสารที่ถูก Forward มา ต้องวางไว้หลัง Step Create หรือ Clear เสมอ</p>
            <p class="lang-en">A command that allows receiving forwarded documents. It must always follow a Create or Clear Step.</p>
        </div>

        <div class="step">
            <h3>8. Completion (ปิดเอกสาร)</h3>
            <p class="lang-th">คำสั่งอัตโนมัติที่ปิดเอกสารเมื่อกระบวนการถึงขั้นตอนนี้</p>
            <p class="lang-en">An automatic command that closes the document when the process reaches this step.</p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">ลำดับของ Step มีความสำคัญและต้องจัดเรียงอย่างเหมาะสมเพื่อให้ระบบทำงานได้อย่างราบรื่น<br>Step อัตโนมัติ เช่น Record, Forward และ Completion จะทำงานตามเงื่อนไขที่กำหนดไว้เท่านั้น</p>
        <p class="lang-en">The sequence of Steps is critical and must be arranged appropriately for smooth system operation.<br>Automatic Steps like Record, Forward, and Completion will only execute based on predefined conditions.</p>
    </div>


      </section>
      
          <br>     <br>
            <section id="Step1" class="authdoc-section">
        
              <h1>คู่มือ: Create Step ความสามารถ / Manual: Create Step Capabilities</h1><br>

    <div >
        <h2>1. การตั้งค่า Supporting Data / Setting Up Supporting Data</h2>
        <div class="step">
            <p class="lang-th">การตั้งค่า Supporting Data คือการดึงข้อมูลจากทะเบียนข้อมูลเพื่อใช้ในการดำเนินการ โดยทั่วไปจะทำงานคล้ายกันในทุกระบบ คือการจับคู่คอลัมน์หลักของระบบกับ Eform ของผู้ใช้ เช่น หากเลือก DB Production ซึ่งมีการเก็บ Product Master ไว้ จะสามารถดึง Product Master มาแสดงในช่องของ Eform ผ่านการจับคู่ได้ โดยปุ่มสำหรับเลือกข้อมูลจะปรากฏเมื่อเริ่ม Step Create</p>
            <p class="lang-en">Setting up Supporting Data involves retrieving data from a registry to be used in operations. Typically, it works similarly across systems by mapping the system's primary columns to the user's Eform. For example, selecting DB Production, which stores a Product Master, allows the Product Master to be displayed in Eform fields through mapping. A button to select data appears when starting the Create Step.</p>
        </div>
        
         <div style="padding-left:100px">  
          <h4>1. ภาพรวมการทำงานของ Supporting Data</h4>
    <p>ระบบ Supporting Data ช่วยให้ผู้ใช้เลือกข้อมูลจากฐานข้อมูลที่กำหนดไว้ล่วงหน้า โดยแต่ละฐานข้อมูลจะมี <strong>Supporting Data</strong>, <strong>Table</strong>, <strong>Dataset</strong> และ <strong>Filter</strong> ที่เกี่ยวข้องกัน เมื่อผู้ใช้เลือกฐานข้อมูล ระบบจะดึงข้อมูลที่ต้องการมาแสดงใน Eform เพื่อป้องกันการกรอกข้อมูลผิดพลาด</p>

    <h4>ตัวอย่างโครงสร้างข้อมูล</h4>
    <table>
        <tr>
            <th>Database</th>
            <th>Supporting Data</th>
            <th>Table</th>
            <th>Dataset</th>
            <th>Filter</th>
        </tr>
        <tr>
            <td>Production</td>
            <td>ข้อมูลสินค้า</td>
            <td>master_product</td>
            <td>Product_Category</td>
            <td>ประเภทสินค้า</td>
        </tr>
        <tr>
            <td>Sales_Customer</td>
            <td>ข้อมูลลูกค้า หรือ Sale</td>
            <td>register</td>
            <td>Register_Type</td>
            <td>ลูกค้า หรือ Sale</td>
        </tr>
        <tr>
            <td>Stock_Warehouse</td>
            <td>ข้อมูล Stock</td>
            <td>stock</td>
            <td>Stock_Type</td>
            <td>WIP, FG, ...</td>
        </tr>
    </table>

    <h4>2. การทำงานของ Eform</h4>
    <p>เมื่อผู้ใช้เลือกฐานข้อมูลและ Supporting Data ที่ต้องการ ระบบจะดึงข้อมูลจาก <strong>Table</strong> และ <strong>Dataset</strong> ที่เกี่ยวข้องมาแสดงใน Eform โดยอัตโนมัติ ซึ่งช่วย:</p>
    <ul>
        <li>ลดข้อผิดพลาดจากการกรอกข้อมูลด้วยมือ</li>
        <li>ให้ผู้ใช้เลือกเฉพาะข้อมูลที่ถูกต้องตามที่กำหนดไว้ใน Dataset</li>
        <li>เพิ่มความสะดวกและรวดเร็วในการทำงาน</li>
    </ul>

    <h4>ตัวอย่างการทำงานของ Eform</h4>
    <p>สมมติว่าเลือกฐานข้อมูล <code>Stock_Warehouse</code> และ Supporting Data <code>ข้อมูล Stock</code>:</p>
    <ol>
        <li>ระบบจะดึงข้อมูลจากตาราง <code>stock</code></li>
        <li>แสดงตัวเลือกใน Eform ตาม Dataset <code>Stock_Type</code> เช่น <code>WIP</code>, <code>FG</code></li>
        <li>ผู้ใช้เลือกตัวเลือกที่ต้องการ และระบบจะบันทึกข้อมูลโดยไม่ต้องกรอกด้วยมือ</li>
    </ol>

    <h4>3. การตั้งค่า Filter และ Dataset</h4>
    <p>Filter และ Dataset เป็นส่วนสำคัญที่ช่วยควบคุมข้อมูลที่แสดงใน Eform โดยมีคุณสมบัติดังนี้:</p>
    <ul>
        <li><strong>Dataset</strong>: เป็นค่าคงที่ที่ Admin สามารถปรับแต่งได้ เช่น การกำหนด <code>Stock_Type</code> เป็น <code>WIP</code>, <code>FG</code> หรืออื่น ๆ</li>
        <li><strong>Filter</strong>: ใช้สำหรับกรองข้อมูลตั้งแต่ขั้นตอนการตั้งค่า เพื่อให้ผู้ใช้เห็นเฉพาะข้อมูลที่เกี่ยวข้อง และป้องกันการเลือกข้อมูลที่ไม่ถูกต้อง</li>
    </ul>

    <div class="note">
        <p><strong>หมายเหตุ:</strong> การตั้งค่า Filter จะถูกกำหนดในส่วนการตั้งค่าระบบ ทำให้เมื่อผู้ใช้โหลดข้อมูล ระบบจะจำกัดขอบเขตข้อมูลตามที่กำหนดไว้ล่วงหน้า และป้องกันการหลุดไปยังข้อมูลที่ไม่เกี่ยวข้อง</p>
    </div>
<br>
    <h4>ตัวอย่างการตั้งค่า Filter</h4>
    <p>หาก Admin ต้องการให้ผู้ใช้เห็นเฉพาะข้อมูลประเภท <code>FG</code> ใน <code>Stock_Warehouse</code>:</p>
    <ol>
        <li>Admin เข้าไปที่ส่วนตั้งค่าระบบ</li>
        <li>เลือกฐานข้อมูล <code>Stock_Warehouse</code> และ Dataset <code>Stock_Type</code></li>
        <li>กำหนด Filter ให้แสดงเฉพาะ <code>FG</code></li>
        <li>บันทึกการตั้งค่า และเมื่อผู้ใช้โหลด Eform จะเห็นเฉพาะตัวเลือก <code>FG</code> เท่านั้น</li>
    </ol>

    <h4>4. การจัดการและปรับแต่งโดย Admin</h4>
    <p>Admin มีสิทธิ์ในการจัดการ Supporting Data และ Filter เพื่อให้เหมาะสมกับความต้องการขององค์กร โดยสามารถ:</p>
    <ul>
        <li>เพิ่ม, ลบ, หรือแก้ไข Dataset เช่น การเพิ่มประเภทใหม่ใน <code>Stock_Type</code></li>
        <li>กำหนด Filter เพื่อจำกัดข้อมูลที่แสดงใน Eform</li>
        <li>ตรวจสอบและอัปเดต Table และ Supporting Data เพื่อให้สอดคล้องกับข้อมูลในระบบ</li>
    </ul>

    <h4>5. ประโยชน์ของระบบ Supporting Data</h4>
    <ul>
        <li><strong>ลดข้อผิดพลาด</strong>: Eform ช่วยให้ผู้ใช้เลือกข้อมูลที่ถูกต้อง</li>
        <li><strong>ยืดหยุ่น</strong>: Admin สามารถปรับแต่ง Dataset และ Filter ได้ตามความต้องการ</li>
        <li><strong>ควบคุมข้อมูล</strong>: Filter ช่วยจำกัดขอบเขตข้อมูล ป้องกันการเลือกข้อมูลที่ไม่เกี่ยวข้อง</li>
        <li><strong>ใช้งานง่าย</strong>: ผู้ใช้ไม่ต้องกรอกข้อมูลด้วยมือ ลดเวลาและความซับซ้อน</li>
    </ul>

    <h4>6. ข้อแนะนำสำหรับผู้ใช้</h4>
    <ul>
        <li>ตรวจสอบว่าคุณเลือกฐานข้อมูลและ Supporting Data ที่ถูกต้องการก่อนใช้งาน Eform</li>
        <li>หากไม่พบตัวเลือกที่ต้องการใน Eform ติดต่อ Admin เพื่อตรวจสอบการตั้งค่า Dataset หรือ Filter</li>
        <li>สำหรับ Admin: ควรทดสอบการตั้งค่า Filter ทุกครั้งหลังปรับแต่ง เพื่อให้แน่ใจว่าผู้ใช้เห็นข้อมูลที่ถูกต้อง</li>
    </ul>
    </div>

     </div>
     
     
    <br> 
<hr>
  <br> 
    <div class="section">
        <h2>2. การตั้งค่า Action / Configuring Action for Create Step</h2>
        <div class="step">
            <p class="lang-th">การตั้งค่า Action คือการกำหนดว่า Create Step จะมีหน้าที่ทำอะไร โดยรองรับ Action ได้ 4 รูปแบบ และแต่ละฟอร์มสามารถเลือกได้เพียง 1 รูปแบบเท่านั้น:</p>
            <p class="lang-en">Configuring an Action defines the purpose of the Create Step. It supports 4 types of Actions, and each form can only select one type:</p>
            <ul>
                <li><strong>Register Entry</strong> 
                    <p class="lang-th">การขอขึ้นทะเบียน เป็นข้อมูลควบคุมที่ต้องตรวจสอบรายการที่ทำได้</p>
                    <p class="lang-en">Request for registry entry, involving controlled data that requires verification of permissible items.</p>
                </li>
                <li><strong>Guideline Request</strong> 
                    <p class="lang-th">การขอขึ้นทะเบียน เป็นเอกสารประกอบ</p>
                    <p class="lang-en">Request for registry entry, serving as supporting documentation.</p>
                </li>
                <li><strong>Register Processing</strong> 
                    <p class="lang-th">การขอปรับปรุงทะเบียนควบคุม</p>
                    <p class="lang-en">Request to update a controlled registry.</p>
                </li>
                <li><strong>Request Action</strong> 
                    <p class="lang-th">การดำเนินการอื่นๆ โดยเเต่ละรายการจะมีคีย์หลักดังนี้</p>
                    <p class="lang-en">Other operations, each item has the following primary keys:.</p>
        <ul>
         <li><a href="#Eform0">Eform</a></li>
           <li><a href="#PL1">PL</a></li>
 
        </ul>
        
                    
                </li>
            </ul>
        </div>

        <div class="step">
            <h3>การตั้งค่า Action และการเปิดใช้เอกสาร / Action Configuration and Document Activation</h3>
            <p class="lang-th">ทั้ง 4 รูปแบบ Action มีวิธีการตั้งค่าเหมือนกัน คือการเปิดใช้เอกสาร (Activate) ซึ่งเป็นการเลือกชื่อเอกสารที่จะดำเนินการ เช่น หากเลือก DB เป็น Production และเลือก Request Action จะมีเอกสารให้เลือกดังนี้: PL, PD, production_record, QCI, OTHER ผู้ใช้ต้องเลือกว่าเอกสารจะเป็นประเภทใด (เรียกว่าการเปิดใช้)</p>
            <p class="lang-en">All 4 Action types follow the same configuration process, which involves activating a document by selecting its name. For example, if DB is set to Production and Request Action is chosen, available documents include: PL, PD, production_record, QCI, OTHER. The user must select the document type (referred to as activation).</p>

            <p class="lang-th">เมื่อเลือกเอกสารแล้ว ระบบจะแสดงคอลัมน์หลักให้จับคู่กับ Eform ที่ผู้ใช้สร้าง โดยคอลัมน์ใน Eform จะถูกแปลงให้เป็นประเภทเดียวกับคอลัมน์หลัก คอลัมน์หลักที่มีเครื่องหมาย * เป็นส่วนที่จำเป็นต้องจับคู่ ส่วนที่ไม่มีเครื่องหมาย * ไม่จำเป็นต้องจับคู่ และสามารถใช้คอลัมน์ใน Eform ได้</p>
            <p class="lang-en">After selecting a document, the system displays primary columns for mapping to the user-created Eform. Eform columns are converted to match the primary column types. Primary columns marked with * are mandatory for mapping, while those without * are optional, allowing direct use of Eform columns.</p>
        </div>

        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">เมื่อบันทึกข้อมูล เช่น สำหรับเอกสาร <strong>production_record</strong>:<br>
                <code>production_record {ID:1, สินค้า: "น้ำพริก", ยอดผลิต: 1000, edata_ID:1}</code><br>
                <code>edata {ID:1, วันที่: "2024-11-01", อื่นๆ: "-"}</code><br>
                หากคอลัมน์ "สินค้า" และ "ยอดผลิต" ถูกจับคู่ จะบันทึกลงใน <strong>production_record</strong> หากไม่จับคู่ จะบันทึกลงใน <strong>edata</strong> ซึ่งเป็นฟอร์มใน Eform
            </p>
            <p class="lang-en">When saving data, for example, for the document <strong>production_record</strong>:<br>
                <code>production_record {ID:1, Product: "Chili Paste", Production_Amount: 1000, edata_ID:1}</code><br>
                <code>edata {ID:1, Date: "2024-11-01", Others: "-"}</code><br>
                If the "Product" and "Production_Amount" columns are mapped, they are saved to <strong>production_record</strong>. If not mapped, they are saved to <strong>edata</strong>, which is the Eform form.
            </p>
            <p class="lang-th"><strong>production_record</strong> เป็นคอลัมน์หลักที่ช่วยให้คำนวณข้อมูลแบบอัตโนมัติได้ง่าย</p>
            <p class="lang-en"><strong>production_record</strong> serves as the primary column, facilitating easy automatic data calculations.</p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การจับคู่คอลัมน์หลักกับ Eform มีความสำคัญเพื่อให้ข้อมูลสอดคล้องกับทะเบียนของระบบ คอลัมน์ที่ไม่จับคู่จะถูกบันทึกใน Eform โดยไม่กระทบกับทะเบียนหลัก</p>
        <p class="lang-en">Mapping primary columns to Eform is crucial to ensure data consistency with the system registry. Unmapped columns are saved in Eform without affecting the primary registry.</p>
    </div>
            
           </section>

          <br>     <br>
    <section id="Step2" class="authdoc-section">
 <h1>คู่มือ: Review Step ความสามารถ / Manual: Review Step Capabilities</h1><br>

    <div class="section">
        <h2>การตั้งค่า Review Step / Configuring Review Step</h2>
        <p class="lang-th">Review Step สามารถตั้งค่าได้ 2 รูปแบบหลัก: Authorization และ Supporting for Review โดยแต่ละรูปแบบมีหน้าที่และการใช้งานที่แตกต่างกัน</p>
        <p class="lang-en">The Review Step can be configured in two main forms: Authorization and Supporting for Review, each with distinct functions and uses.</p>
    </div>

    <div class="section">
        <h2>1. Authorization / ความสามารถในการแก้ไข</h2>
        <div class="step">
            <p class="lang-th">Authorization หมายถึง ความสามารถในการอนุญาตให้ผู้รีวิวแก้ไขข้อมูลได้ หากเปิดใช้งาน (Activate) ผู้ใช้สามารถตั้งค่าได้ 2 ส่วน:</p>
            <p class="lang-en">Authorization refers to the ability to allow reviewers to edit information. If activated, users can configure two components:</p>
            <ul>
                <li><strong>Can Edit Information</strong> 
                    <p class="lang-th">อนุญาตให้แก้ไขข้อมูลในฟอร์มได้</p>
                    <p class="lang-en">Allows editing of data within the form.</p>
                </li>
                <li><strong>Able to Edit Supporting Documents</strong> 
                    <p class="lang-th">อนุญาตให้แก้ไขไฟล์แนบได้</p>
                    <p class="lang-en">Allows editing of attached files.</p>
                </li>
            </ul>
            <p class="lang-th">ผู้ใช้สามารถเลือกเปิดใช้งานส่วนใดส่วนหนึ่งหรือทั้งสองส่วนตามความจำเป็น</p>
            <p class="lang-en">Users can choose to enable one or both components as needed.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. Supporting for Review / ข้อมูลสนับสนุนสำหรับการรีวิว</h2>
        <div class="step">
            <p class="lang-th">Supporting for Review เป็นการเลือกข้อมูลประกอบเพื่อช่วยในการตัดสินใจในขั้นตอนรีวิว เช่น ข้อมูลสนับสนุนกระบวนการลงทะเบียน ระบบจะดึงข้อมูลโอกาสซ้ำ (ในรูปแบบเปอร์เซ็นต์ %) พร้อมรายชื่อ 5 อันดับแรกมาแสดง</p>
            <p class="lang-en">Supporting for Review involves selecting supporting data to aid decision-making during the review process. For example, for registry processes, the system retrieves duplicate opportunity data (in percentage %) along with the top 5 entries.</p>
            <p class="lang-th">ผู้ใช้เพียงแค่เปิดใช้งาน (Activate) และเลือกข้อมูลที่ต้องการให้ระบบดึงมาแสดง</p>
            <p class="lang-en">Users only need to activate the feature and select the data for the system to retrieve and display.</p>
        </div>

        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">สมมติว่าผู้ใช้เปิดใช้งาน Supporting for Review สำหรับกระบวนการลงทะเบียน:<br>
                ระบบจะแสดงข้อมูล เช่น “โอกาสซ้ำ: 15%” พร้อมรายชื่อ 5 รายการที่อาจซ้ำ เช่น “สินค้า A, สินค้า B, สินค้า C, สินค้า D, สินค้า E” เพื่อช่วยผู้รีวิวตัดสินใจ
            </p>
            <p class="lang-en">Assume the user activates Supporting for Review for a registry process:<br>
                The system displays data such as “Duplicate Opportunity: 15%” along with a list of 5 potential duplicates, e.g., “Product A, Product B, Product C, Product D, Product E,” to assist the reviewer in decision-making.
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
Authorization และ Supporting for Review สามารถใช้งานร่วมกันได้เพื่อเพิ่มประสิทธิภาพในขั้นตอนรีวิว
การตั้งค่าทั้งสองรูปแบบขึ้นอยู่กับความต้องการของกระบวนการและบทบาทของผู้รีวิว
Authorization and Supporting for Review can be used together to enhance the efficiency of the review process.
The configuration of both forms depends on the process requirements and the reviewer’s role.
    </div>

    </section>
<br><br>
 <section id="Step3" class="authdoc-section">
<h1>คู่มือ: Approval Step ความสามารถ / Manual: Approval Step Capabilities</h1><br>

    <div class="section">
        <h2>การตั้งค่า Approval Step / Configuring Approval Step</h2>
        <p class="lang-th">Approval Step มีความสามารถในการตั้งค่า 3 รูปแบบ: Supporting for Review (เหมือนกับ Review Step), Document Code Generation และ Set Registration Codes โดยแต่ละรูปแบบมีหน้าที่เฉพาะเพื่อสนับสนุนการอนุมัติ</p>
        <p class="lang-en">The Approval Step can be configured in three forms: Supporting for Review (similar to Review Step), Document Code Generation, and Set Registration Codes, each with specific functions to support the approval process.</p>
    </div>

    <div class="section">
        <h2>1. Supporting for Review / ข้อมูลสนับสนุนสำหรับการรีวิว</h2>
        <div class="step">
            <p class="lang-th">เหมือนกับ Review Step, Supporting for Review ช่วยดึงข้อมูลประกอบเพื่อใช้ในการตัดสินใจ เช่น ข้อมูลโอกาสซ้ำ (ในรูปแบบเปอร์เซ็นต์ %) พร้อมรายชื่อ 5 อันดับแรก โดยผู้ใช้เพียงเปิดใช้งาน (Activate) และเลือกข้อมูลที่ต้องการ</p>
            <p class="lang-en">Similar to the Review Step, Supporting for Review retrieves supporting data to aid decision-making, such as duplicate opportunity data (in percentage %) with the top 5 entries. Users only need to activate the feature and select the desired data.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. Document Code Generation / การออกรหัสเอกสาร</h2>
        <div class="step">
            <p class="lang-th">Document Code Generation เป็นการสร้างรหัสเอกสารอัตโนมัติทันทีเมื่ออนุมัติ โดยสามารถกำหนดคำนำหน้า (Prefixes) ได้ เช่น <code>PR</code>. รูปแบบรหัสเอกสารจะเป็น <code>Prefixes + YYYYMM + AUTO ID</code></p>
            <p class="lang-en">Document Code Generation automatically generates a document code upon approval. Users can define a prefix, such as <code>PR</code>. The document code format is <code>Prefixes + YYYYMM + AUTO ID</code>.</p>
            <p class="lang-th">หากมีการตีกลับและอนุมัติรอบสอง รหัสจะเพิ่มเลขการปรับปรุง เช่น <code>PR2024122-R1</code> โดยต้องมีการจับคู่คอลัมน์เพื่อให้ระบบบันทึกรหัสเอกสารอย่างถูกต้อง</p>
            <p class="lang-en">If the document is rejected and re-approved, the code includes a revision number, e.g., <code>PR2024122-R1</code>. Column mapping is required to ensure accurate document code recording.</p>
        </div>
        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">หากกำหนดคำนำหน้าเป็น <code>PR</code> และอนุมัติในเดือนธันวาคม 2024:<br>
                รหัสเอกสาร: <code>PR2024122</code><br>
                หากมีการตีกลับและอนุมัติใหม่: <code>PR2024122-R1</code>
            </p>
            <p class="lang-en">If the prefix is set to <code>PR</code> and approval occurs in December 2024:<br>
                Document code: <code>PR2024122</code><br>
                If rejected and re-approved: <code>PR2024122-R1</code>
            </p>
        </div>
    </div>

    <div class="section">
        <h2>3. Set Registration Codes / การกำหนดรหัสทะเบียน</h2>
        <div class="step">
            <p class="lang-th">Set Registration Codes เป็นการกำหนดคำนำหน้าสำหรับทะเบียนควบคุม เช่น การอนุมัติการขึ้นทะเบียนลูกค้า หากกำหนดคำนำหน้าเป็น <code>C</code> รหัสจะเป็น <code>C2024122</code> โดยรหัสนี้จะไม่มีการเพิ่มเลขการปรับปรุงเมื่อมีการแก้ไข</p>
            <p class="lang-en">Set Registration Codes assigns a prefix for controlled registries, such as customer registration approval. If the prefix is set to <code>C</code>, the code will be <code>C2024122</code>. This code does not include a revision number upon updates.</p>
            <p class="lang-th">ต้องมีการจับคู่คอลัมน์เพื่อให้ระบบบันทึกรหัสทะเบียนอย่างถูกต้อง</p>
            <p class="lang-en">Column mapping is required to ensure accurate recording of the registry code.</p>
        </div>
        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">หากกำหนดคำนำหน้าเป็น <code>C</code> สำหรับทะเบียนลูกค้าในเดือนธันวาคม 2024:<br>
                รหัสทะเบียน: <code>C2024122</code><br>
                หากมีการแก้ไข รหัสจะยังคงเป็น <code>C2024122</code> (ไม่มีเลขการปรับปรุง)
            </p>
            <p class="lang-en">If the prefix is set to <code>C</code> for a customer registry in December 2024:<br>
                Registry code: <code>C2024122</code><br>
                If updated, the code remains <code>C2024122</code> (no revision number).
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การตั้งค่า Document Code Generation และ Set Registration Codes ต้องมีการจับคู่คอลัมน์อย่างถูกต้องเพื่อให้รหัสที่สร้างขึ้นสอดคล้องกับข้อมูลในระบบ Supporting for Review สามารถใช้ร่วมกับการตั้งค่าอื่นเพื่อเพิ่มประสิทธิภาพในการตัดสินใจ</p>
        <p class="lang-en">Configuring Document Code Generation and Set Registration Codes requires accurate column mapping to ensure generated codes align with system data. Supporting for Review can be used alongside other configurations to enhance decision-making efficiency.</p>
    </div>
 </section>
 
 <br> <br>
  <section id="Step7" class="authdoc-section">
   <h1>คู่มือ: Receive Step ความสามารถ / Manual: Receive Step Capabilities</h1><br>

    <div class="section">
        <h2>การตั้งค่า Receive Step / Configuring Receive Step</h2>
        <p class="lang-th">Receive Step เป็นขั้นตอนสำหรับรับเอกสารและเชื่อมโยงกระบวนการระหว่างระบบ เช่น ระบบขอซื้อ (PR) และระบบสั่งซื้อ (PO) โดยต้องมีการ Join กระบวนการก่อนเพื่อให้ทั้งสองระบบมองเห็นกัน</p>
        <p class="lang-en">Receive Step is the process for receiving documents and linking processes between systems, such as Purchase Requisition (PR) and Purchase Order (PO). Processes must be joined beforehand to ensure visibility between systems.</p>
    </div>

    <div class="section">
        <h2>1. การ Join กระบวนการ / Process Joining</h2>
        <div class="step">
            <p class="lang-th">ก่อนตั้งค่า Receive Step ต้อง Join กระบวนการ เช่น ระบบขอซื้อ (PR) -> ระบบสั่งซื้อ (PO) เพื่อให้ทั้งสองระบบสามารถรับและส่งเอกสารระหว่างกันได้ เช่น ระบบขอซื้อ Send -> ระบบสั่งซื้อ Receive</p>
            <p class="lang-en">Before configuring Receive Step, processes must be joined, e.g., Purchase Requisition (PR) -> Purchase Order (PO), to enable document sending and receiving between systems, such as PR Send -> PO Receive.</p>
            <p class="lang-th">เมื่อ Join สำเร็จ ผู้ใช้สามารถตั้งค่า Receive Step ได้</p>
            <p class="lang-en">Once joined successfully, users can configure the Receive Step.</p>
        </div>
    </div>

    <div class="section">
        <h2>2. การตั้งค่ากล่องรับเอกสาร / Configuring Document Inboxes</h2>
        <div class="step">
            <p class="lang-th">เมื่อตั้งค่า Receive Step จะมีกล่องรับเอกสาร 2 ประเภทปรากฏใน Tool Menu:</p>
            <p class="lang-en">Upon configuring Receive Step, two types of document inboxes appear in the Tool Menu:</p>
            <ul>
                <li><strong>กล่องรับเอกสารทั่วไป / General Document Inbox</strong>
                    <p class="lang-th">สำหรับรับเอกสารหลัก เมื่อดำเนินการอ้างอิง (Reference) หรือกดปิด กล่องนี้จะหายไป</p>
                    <p class="lang-en">For receiving main documents. Once referenced or closed, this inbox disappears.</p>
                </li>
                <li><strong>กล่องรับเอกสารประกอบ / Supporting Document Inbox</strong>
                    <p class="lang-th">สำหรับรับเอกสารประกอบ เช่น มาตรฐานการผลิต กล่องนี้จะคงอยู่จนกว่าจะแจ้งเลิกใช้</p>
                    <p class="lang-en">For receiving supporting documents, e.g., production standards. This inbox persists until manually deactivated.</p>
                </li>
            </ul>
        </div>
    </div>

    <div class="section">
        <h2>3. การอ้างอิงเอกสาร (Reference) / Document Reference</h2>
        <div class="step">
            <p class="lang-th">Receive Step รองรับการอ้างอิงเอกสาร (Reference) 4 รูปแบบ คล้ายกับ Action ใน Create Step โดยผู้ใช้เลือกได้ 1 รูปแบบต่อการตั้งค่า:</p>
            <p class="lang-en">Receive Step supports four document reference types, similar to Actions in Create Step. Users can select one type per configuration:</p>
            <ul>
                <li><strong>Reference Only / อ้างอิงเท่านั้น</strong>
                    <p class="lang-th">รับเอกสารเพื่อแนบเป็นเอกสารประกอบ เช่น มาตรฐานการตรวจ ต้องจับคู่คีย์เพื่อเชื่อมโยงข้อมูล เช่น ค่าความชื้นจากเอกสารประกอบจะถูกส่งไปยังฟอร์มการตรวจที่อ้างอิง</p>
                    <p class="lang-en">Receives a document as a supporting attachment, e.g., inspection standards. Requires key mapping to link data, such as humidity values, to the referencing inspection form.</p>
                </li>
                <li><strong>Reference Continue / อ้างอิงและกรอกต่อ</strong>
                    <p class="lang-th">รับเอกสารเพื่อกรอกข้อมูลต่อ เช่น นำเข้าเอกสาร PO เข้ามาในระบบจัดซื้อ (GR) เอกสาร PO จะคัดลอกข้อมูลไปทับคอลัมน์ที่จับคู่ไว้ และเหลือช่องให้กรอกเพิ่ม เช่น ช่องรับสินค้า</p>
                    <p class="lang-en">Receives a document to continue data entry, e.g., importing a PO into the Goods Receipt (GR) system. The PO copies data to mapped columns, leaving fields like receipt quantity for further input.</p>
                </li>
                <li><strong>Reference & Modify / อ้างอิงและแก้ไข</strong>
                    <p class="lang-th">รับเอกสารเพื่อเปรียบเทียบข้อมูลและดำเนินการที่แตกต่างได้ เช่น นำเข้าเอกสาร PR เพื่อสร้าง PO ผู้ใช้สามารถเลือกย้ายรายการจาก PR ไป PO แบบคัดลอก และเปรียบเทียบเพื่อให้เหมือนกันหรือแก้ไขได้ รองรับการนำเข้าหลายรายการ</p>
                    <p class="lang-en">Receives a document for data comparison and allows different actions, e.g., importing a PR to create a PO. Users can copy items from PR to PO, compare for consistency, or modify. Supports multiple imports.</p>
                </li>
                <li><strong>Hierarchical Reference / อ้างอิงแบบลำดับชั้น</strong>
                    <p class="lang-th">รับเอกสารเป็นเอกสารแม่ เช่น นำเข้า PD เข้ามาใน production_record ข้อมูลจาก PD (เช่น ผลิตอะไร, จำนวน) จะปรากฏใน production_record และเมื่อกรอก production_record ข้อมูลจะอัปเดตใน PD แบบเรียลไทม์</p>
                    <p class="lang-en">Receives a document as a parent document, e.g., importing a PD into production_record. Data from PD (e.g., product, quantity) appears in production_record, and updates to production_record reflect in PD in real-time.</p>
                </li>
            </ul>
        </div>

        <div class="example">
            <h3>ตัวอย่าง / Example</h3>
            <p class="lang-th">
                <strong>Reference & Modify:</strong><br>
                นำเข้าเอกสาร PR1 (ซื้อกระดาษ 1 รีม, ปากกา 1 กล่อง) และ PR2 (ซื้อกระดาษ 1 รีม) เพื่อสร้าง PO:<br>
                - หาก PO สร้างเป็น “ซื้อกระดาษ 2 รีม, ปากกา 1 กล่อง” รายการ PR1 และ PR2 จะแสดงสีเขียว (สอดคล้อง)<br>
                - หาก PO สร้างเป็น “ซื้อกระดาษ 5 รีม, ปากกา 1 กล่อง” รายการ PR1 และ PR2 จะแสดงสีแดง (ไม่สอดคล้อง) แต่ยังใช้งานได้<br>
            </p>
            <p class="lang-en">
                <strong>Reference & Modify:</strong><br>
                Import PR1 (buy 1 ream of paper, 1 box of pens) and PR2 (buy 1 ream of paper) to create a PO:<br>
                - If PO is created as “buy 2 reams of paper, 1 box of pens,” PR1 and PR2 items show green (consistent).<br>
                - If PO is created as “buy 5 reams of paper, 1 box of pens,” PR1 and PR2 items show red (inconsistent) but remain usable.<br>
            </p>
        </div>
    </div>

    <div class="note">
        <h3>หมายเหตุ / Notes</h3>
        <p class="lang-th">การตั้งค่า Receive Step ต้องมีการ Join กระบวนการให้สมบูรณ์ก่อน การจับคู่คีย์หรือคอลัมน์เป็นสิ่งสำคัญเพื่อให้การอ้างอิงเอกสารทำงานได้ถูกต้อง การเลือกประเภท Reference ขึ้นอยู่กับความต้องการของกระบวนการ เช่น การเปรียบเทียบข้อมูลหรือการกรอกข้อมูลต่อ</p>
        <p class="lang-en">Configuring Receive Step requires a complete process join beforehand. Key or column mapping is critical for accurate document referencing. The choice of Reference type depends on the process needs, such as data comparison or continued data entry.</p>
    </div>
  </section>
 
 
 <br>
  <hr>
 <br> 

      <section id="PL1" class="authdoc-section">
      
            <h4>คู่มือการจับคู่คีย์หลักใน Request Action (กรณี PL)</h4>
        <p>เอกสารนี้แสดงการจับคู่คีย์หลักใน <strong>Request Action</strong> สำหรับกรณีเลือก <strong>PL</strong> ซึ่งคือ <strong>เอกสารแผนการผลิต</strong> โดยแบ่งกลุ่มคีย์หลักและอธิบายการใช้งานในภาษาไทยและภาษาอังกฤษ</p>
      
      <br>
    <div class="sub-section">
  
        <h4>1. กลุ่มคีย์หลัก</h4>
        <p>ต่อไปนี้คือกลุ่มคีย์หลักที่ใช้ในระบบสำหรับเอกสาร PL:</p>

        <h4>1.1 กลุ่ม <spring:message code="key.key1" text="-" /></h4>
        <p>กลุ่มนี้เกี่ยวข้องกับข้อมูลพื้นฐานของเอกสารแผนการผลิต (This group relates to the basic information of the production plan document):</p>
        <table>
            <tr>
                <th>คีย์</th>
                <th>การใช้งาน (TH)</th>
                <th>Usage (EN)</th>
                <th>ความสำคัญ</th>
            </tr>
            <tr>
                <td><spring:message code="doc.code" text="-" /></td>
                <td>ใช้ระบุเอกสารแผนการผลิตที่ไม่ซ้ำกัน</td>
                <td>Identifies the unique production plan document</td>
                <td>จำเป็น (Required)</td>
            </tr>
            <tr>
                <td><spring:message code="product.lot_batch" text="-" /></td>
                <td>ใช้ระบุหมายเลขล็อตหรือแบทช์ของการผลิต</td>
                <td>Identifies the lot or batch number of production</td>
                <td>จำเป็น (Required)</td>
            </tr>
        </table>

        <h4>1.2 กลุ่ม <spring:message code="key.key2" text="-" /></h4>
        <p>กลุ่มนี้เกี่ยวข้องกับข้อมูลสินค้าและรายละเอียดการผลิต (This group relates to product information and production details):</p>
        <table>
            <tr>
                <th>คีย์</th>
                <th>การใช้งาน (TH)</th>
                <th>Usage (EN)</th>
                <th>ความสำคัญ</th>
            </tr>
            <tr>
                <td><spring:message code="product.product_code" text="-" /></td>
                <td>ใช้ระบุรหัสสินค้าที่จะผลิต</td>
                <td>Identifies the product to be produced</td>
                <td>จำเป็น (Required)</td>
            </tr>
            <tr>
                <td><spring:message code="product.product_name" text="-" /></td>
                <td>ใช้ระบุชื่อสินค้าที่จะผลิต</td>
                <td>Specifies the name of the product to be produced</td>
                <td>จำเป็น (Required)</td>
            </tr>
            <tr>
                <td><spring:message code="product.order_quantity" text="-" /></td>
                <td>ใช้กำหนดจำนวนสินค้าที่ต้องผลิต</td>
                <td>Specifies the quantity of products to be produced</td>
                <td>จำเป็น (Required)</td>
            </tr>
            <tr>
                <td><spring:message code="product.unit" text="-" /></td>
                <td>ใช้ระบุหน่วยของสินค้าที่ผลิต เช่น กิโลกรัม, ชิ้น</td>
                <td>Specifies the unit of the produced product, e.g., kg, pieces</td>
                <td></td>
            </tr>
            <tr>
                <td><spring:message code="product.start_date" text="-" /></td>
                <td>ใช้กำหนดวันที่เริ่มต้นการผลิต</td>
                <td>Specifies the start date of production</td>
                <td></td>
            </tr>
            <tr>
                <td><spring:message code="product.end_date" text="-" /></td>
                <td>ใช้กำหนดวันที่ต้องผลิตให้เสร็จ</td>
                <td>Specifies the date by which production must be completed</td>
                <td></td>
            </tr>
            <tr>
                <td><spring:message code="product.remarks" text="-" /></td>
                <td>ใช้บันทึกหมายเหตุเพิ่มเติมเกี่ยวกับการผลิต</td>
                <td>Records additional notes about the production</td>
                <td></td>
            </tr>
        </table>

        <h4>1.3 กลุ่ม <spring:message code="key.key3" text="-" /></h4>
        <p>กลุ่มนี้เกี่ยวข้องกับขั้นตอนและองค์ประกอบการผลิต (This group relates to production processes and components):</p>
        <table>
            <tr>
                <th>คีย์</th>
                <th>การใช้งาน (TH)</th>
                <th>Usage (EN)</th>
                <th>ความสำคัญ</th>
            </tr>
            <tr>
                <td><spring:message code="product.processes" text="-" /></td>
                <td>ใช้ระบุขั้นตอนการผลิต เช่น การพัด, อบ, ทอด</td>
                <td>Specifies production processes, e.g., folding, baking, frying</td>
                <td>จำเป็น (Required)</td>
            </tr>
            <tr>
                <td><spring:message code="product.item_code" text="-" /></td>
                <td>ใช้ระบุการเเยกผลิตเเบบองค์ประกอบ เช่น การเเยกผลิตชิ้นส่วนรถยนต์</td>
                <td>Used to specify the separation of component production, such as the separation of automobile parts production.</td>
                <td></td>
            </tr>
            <tr>
                <td><spring:message code="product.sub_order_quantity" text="-" /></td>
                <td>ใช้กำหนดจำนวนที่ต้องผลิตในแต่ละขั้นตอน</td>
                <td>Specifies the quantity to be produced in each process</td>
                <td>จำเป็น (Required)</td>
            </tr>
            <tr>
                <td><spring:message code="product.responsible_person" text="-" /></td>
                <td>ใช้ระบุผู้รับผิดชอบในขั้นตอนการผลิต</td>
                <td>Identifies the person responsible for the production process</td>
                <td></td>
            </tr>
            <tr>
                <td><spring:message code="product.production_location" text="-" /></td>
                <td>ใช้ระบุสถานที่ที่ดำเนินการผลิต</td>
                <td>Specifies the location where production takes place</td>
                <td></td>
            </tr>
        </table>

        <h4>1.4 กลุ่ม <spring:message code="key.key4" text="-" /></h4>
        <p>กลุ่มนี้เกี่ยวข้องกับข้อมูลลูกค้าที่เกี่ยวข้องกับแผนการผลิต (This group relates to customer information associated with the production plan):</p>
        <table>
            <tr>
                <th>คีย์</th>
                <th>การใช้งาน (TH)</th>
                <th>Usage (EN)</th>
                <th>ความสำคัญ</th>
            </tr>
            <tr>
                <td><spring:message code="product.customer_code" text="-" /></td>
                <td>ใช้ระบุรหัสลูกค้าที่สั่งผลิต</td>
                <td>Identifies the customer who ordered the production</td>
                <td>จำเป็น (Required)</td>
            </tr>
            <tr>
                <td><spring:message code="product.customer_name" text="-" /></td>
                <td>ใช้ระบุชื่อลูกค้าที่สั่งผลิต</td>
                <td>Specifies the name of the customer who ordered the production</td>
                <td>จำเป็น (Required)</td>
            </tr>
        </table>

        <h4>2. หมายเหตุเพิ่มเติม</h4>
        <div class="note">
            <p><strong>หมายเหตุ:</strong> คีย์ที่ระบุว่า "จำเป็น" ต้องกรอกข้อมูลในระบบเพื่อให้เอกสารแผนการผลิตสมบูรณ์ ส่วนคีย์ที่ "ไม่จำเป็น" สามารถปล่อยว่างได้ตามความเหมาะสม (Note: Keys marked as "Required" must be filled in the system to complete the production plan document, while "Optional" keys can be left blank as appropriate).</p>
        </div>
    </div>
          <br>
            <br>
<h4>คู่มือการใช้งาน Batch Insert สำหรับ PL (กลุ่ม <spring:message code="key.key3" text="-" />)</h4>
<p>เอกสารนี้ อธิบายเกี่ยวกับ <strong>Batch Insert</strong> สำหรับกิจกรรมในกลุ่ม <strong><spring:message code="key.key3" text="-" /></strong> ซึ่งเกี่ยวข้องกับขั้นตอนและองค์ประกอบการผลิตใน <strong>PL (เอกสารแผนการผลิต)</strong> รวมถึงการเปรียบเทียบการกรอกข้อมูลแบบปกติและแบบ Batch และการทำงานของ Tool การกรอกแบบตาราง</p>
          <br>
        <div class="sub-section">

        <h4>1. Batch Insert คืออะไร</h4>
        <p><strong>Batch Insert</strong> คือการบันทึกข้อมูลหลายชุด (records) พร้อมกันในครั้งเดียว โดยอนุญาตให้ผู้ใช้กรอกข้อมูลที่เกี่ยวข้องกันในรูปแบบตาราง แทนการกรอกทีละชุดในฟอร์มเดียว ซึ่งเหมาะสำหรับกรณีที่ต้องระบุข้อมูลที่มีความหลากหลาย เช่น ขั้นตอนการผลิตหลายขั้นตอนในเอกสาร PL</p>
        <p>ในบริบทของกลุ่ม <strong><spring:message code="key.key3" text="-" /></strong> Batch Insert ช่วยให้ผู้ใช้สามารถระบุ <strong>ขั้นตอนการผลิต</strong> (<code>processes</code>) และ <strong>จำนวนที่ต้องผลิตในแต่ละขั้นตอน</strong> (<code>sub_order_quantity</code>) ได้หลายชุดในคราวเดียว</p>

        <h4>2. การกรอกข้อมูลแบบปกติ (Non-Batch)</h4>
        <p>เมื่อผู้ใช้ไม่ใช้ Batch Insert การกรอกข้อมูลจะจำกัดอยู่ที่ <strong>1 ฟอร์มต่อ 1 ชุดข้อมูล</strong> ซึ่งเหมาะสำหรับกรณีที่ข้อมูลไม่ซับซ้อน แต่ในกรณีของ PL ที่ต้องระบุขั้นตอนการผลิตหลายขั้นตอน การกรอกแบบนี้จะช้าและไม่สะดวก</p>
        <p><strong>ตัวอย่างการกรอกแบบปกติ:</strong></p>
        <pre>
{PL: 1, order: 800, processes: อบ, sub_order_quantity: 800}
        </pre>
        <p>ในตัวอย่างนี้ ผู้ใช้ต้องกรอกฟอร์มสำหรับขั้นตอน "อบ" และจำนวน 800 หน่วย และถ้าต้องการเพิ่มขั้นตอนอื่น เช่น "ผัด" จะต้องกรอกฟอร์มใหม่ทีละชุด</p>

        <h4>3. การกรอกข้อมูลแบบ Batch Insert</h4>
        <p>เมื่อใช้ Batch Insert ระบบจะอนุญาตให้ผู้ใช้กรอกข้อมูลหลายชุดในครั้งเดียว โดยเฉพาะสำหรับคีย์ในกลุ่ม <strong><spring:message code="key.key3" text="-" /></strong> เช่น <code>processes</code> และ <code>sub_order_quantity</code> ซึ่งถูกตั้งเป็น Batch</p>
        <p><strong>ตัวอย่างการกรอกแบบ Batch:</strong></p>
        <pre>
{PL: 1, order: 800}
{PL: 1, processes: อบ, sub_order_quantity: 1000}
{PL: 1, processes: ผัด, sub_order_quantity: 800}
        </pre>
        <p>ในตัวอย่างนี้:
        - ข้อมูลหลัก เช่น <code>PL</code> และ <code>order</code> กรอกเพียงครั้งเดียว
        - ข้อมูลในกลุ่ม <strong><spring:message code="key.key3" text="-" /></strong> เช่น <code>processes</code> และ <code>sub_order_quantity</code> สามารถกรอกได้หลายชุดในคราวเดียว</p>

        <h4>4. Tool การกรอกแบบตาราง</h4>
        <p>เมื่อมีการตั้งค่า Batch Insert ระบบจะสร้าง <strong>Tool การกรอกแบบตาราง</strong> โดยอัตโนมัติ เพื่อให้ผู้ใช้สามารถกรอกข้อมูลหลายชุดในรูปแบบตารางได้อย่างสะดวก</p>
        <p><strong>ตัวอย่างตารางที่ระบบสร้าง:</strong></p>
        <table>
            <tr>
                <th><spring:message code="product.processes" text="-" /></th>
                <th><spring:message code="product.sub_order_quantity" text="-" /></th>
            </tr>
            <tr>
                <td>อบ</td>
                <td>1000</td>
            </tr>
            <tr>
                <td>ผัด</td>
                <td>800</td>
            </tr>
        </table>
        <p>ผู้ใช้สามารถ:
        - เพิ่มแถวในตารางเพื่อระบุขั้นตอนและจำนวนเพิ่มเติม
        - แก้ไขหรือลบแถวได้ตามต้องการ
        - บันทึกข้อมูลทั้งหมดในครั้งเดียว</p>

        <h4>5. ประโยชน์ของ Batch Insert</h4>
        <ul>
            <li><strong>ประหยัดเวลา:</strong> กรอกข้อมูลหลายขั้นตอนได้ในคราวเดียว แทนการกรอกทีละฟอร์ม</li>
            <li><strong>ยืดหยุ่น:</strong> รองรับการระบุขั้นตอนและจำนวนที่แตกต่างกันในเอกสาร PL เดียว</li>
            <li><strong>ใช้งานง่าย:</strong> Tool การกรอกแบบตารางช่วยให้มองเห็นภาพรวมและจัดการข้อมูลได้สะดวก</li>
        </ul>

        <h4>6. การตั้งค่า Batch Insert</h4>
        <p>Admin สามารถตั้งค่าคีย์ที่ต้องการให้เป็น Batch ได้ โดยในกรณีนี้ คีย์ <code>processes</code> และ <code>sub_order_quantity</code> ในกลุ่ม <strong><spring:message code="key.key3" text="-" /></strong> ถูกกำหนดให้เป็น Batch หากไม่มีการตั้งค่า Batch Insert ผู้ใช้จะต้องกรอกข้อมูลทีละชุด</p>

        <h4>7. หมายเหตุเพิ่มเติม</h4>
        <div class="note">
            <p><strong>หมายเหตุ:</strong> การใช้ Batch Insert เหมาะสำหรับกรณีที่มีขั้นตอนการผลิตหลายขั้นตอนในเอกสาร PL เดียว ผู้ใช้ควรตรวจสอบข้อมูลในตารางก่อนบันทึก เพื่อให้แน่ใจว่าข้อมูลครบถ้วนและถูกต้อง (Note: Batch Insert is ideal for cases with multiple production processes in a single PL document. Users should review the table before saving to ensure the data is complete and accurate).</p>
        </div>
    </div>
      
      
      </section>


<br>
<hr>
<br>

      <section id="LowCode" class="authdoc-section">
  <h1>คู่มือ WorkSystem: อัลกอริทึมสำหรับจัดการระบบ Low Code</h1>

        <div class="section">
            <h2>1. แนวคิดหลักของ WorkSystem</h2>
            <p>WorkSystem เป็นอัลกอริทึมที่ออกแบบมาเพื่อจัดการระบบ Low Code โดยใช้แนวคิดการแบ่งการทำงานออกเป็น <strong>วงจร (Cycle)</strong> เพื่อให้การจัดการข้อมูลและการประมวลผลมีความยืดหยุ่นและมีประสิทธิภาพ</p>
            <h3>จุดเด่นของแนวคิด</h3>
            <ul>
                <li><strong>การแยกส่วนการทำงาน (Modularity):</strong> การแบ่งการโหลดข้อมูลออกเป็นฟังก์ชันย่อย เช่น LoadApp, LoadApphasChanges, LoadAppViewMode และ LoadSubData</li>
                <li><strong>การจัดการสถานะข้อมูล:</strong> รองรับข้อมูลทั้งแบบถาวร (ใน DB) และชั่วคราว (ใน Session)</li>
                <li><strong>ประสิทธิภาพ:</strong> การโหลดข้อมูลเฉพาะส่วนช่วยลดการใช้ทรัพยากร</li>
                <li><strong>ความยืดหยุ่น:</strong> รองรับการทำงานในโหมดต่าง ๆ เช่น การแสดงผลแบบดูอย่างเดียว</li>
            </ul>
        </div>

        <div class="section">
            <h2>2. วงจรการทำงานของ WorkSystem</h2>
            <p>WorkSystem แบ่งการทำงานออกเป็นวงจรย่อย ดังนี้:</p>

            <h3>2.1 LoadApp</h3>
            <p>ทำงานเมื่อผู้ใช้เรียกดูเอกสาร โดยโหลดข้อมูลทั้งหมดจากฐานข้อมูล (Database) เพื่อให้พร้อมใช้งาน</p>
            <ul>
                <li><strong>ขั้นตอน:</strong>
                    <ol>
                        <li>ดึงข้อมูลหลัก (Main Data)</li>
                        <li>ดึงข้อมูลลูก (Sub Data)</li>
                        <li>ดึงเอกสารอ้างอิงและข้อมูลประกอบ</li>
                        <li>จัดเตรียมข้อมูลสำหรับแอปพลิเคชัน</li>
                    </ol>
                </li>
            </ul>
            <div class="example">
                <strong>ตัวอย่าง:</strong><br>
                ผู้ใช้เปิดใบสั่งซื้อ ระบบโหลด:<br>
                - ข้อมูลใบสั่งซื้อ (เลขที่, วันที่)<br>
                - รายการสินค้า (ข้อมูลลูก)<br>
                - เอกสารอ้างอิง (ใบเสนอราคา)<br>
                ผลลัพธ์: <code>{id:1, list:A}, {id:2, list:B}</code>
            </div>

            <h3>2.2 LoadApphasChanges</h3>
            <p>โหลดข้อมูลที่อยู่ในสถานะชั่วคราว (Unsaved Changes) จากเซสชัน (Session) โดยไม่ดึงจากฐานข้อมูล</p>
            <ul>
                <li><strong>ขั้นตอน:</strong>
                    <ol>
                        <li>ตรวจสอบข้อมูลในเซสชัน</li>
                        <li>รวมข้อมูลจากเซสชันกับข้อมูลจาก LoadApp</li>
                        <li>ส่งข้อมูลรวมกลับไปยังแอปพลิเคชัน</li>
                    </ol>
                </li>
            </ul>
            <div class="example">
                <strong>ตัวอย่าง:</strong><br>
                ข้อมูลจาก LoadApp: <code>{id:1, list:A}, {id:2, list:B}</code><br>
                ผู้ใช้เพิ่ม: <code>{id:3, list:C}</code><br>
                ผลลัพธ์: <code>{id:1, list:A}, {id:2, list:B}, {id:3, list:C}</code><br>
                หากยกเลิก: เหลือ <code>{id:1, list:A}, {id:2, list:B}</code>
            </div>

            <h3>2.3 LoadAppViewMode</h3>
            <p>โหลดข้อมูลในโหมดดูอย่างเดียว (View Mode) เฉพาะข้อมูลหลักและข้อมูลลูกสำหรับการแสดงผล</p>
            <ul>
                <li><strong>ขั้นตอน:</strong>
                    <ol>
                        <li>ดึงข้อมูลหลักและข้อมูลลูก</li>
                        <li>ไม่ดึงข้อมูลประกอบที่ไม่จำเป็น</li>
                        <li>จัดรูปแบบข้อมูลสำหรับการแสดงผล</li>
                    </ol>
                </li>
            </ul>
            <div class="example">
                <strong>ตัวอย่าง:</strong><br>
                ผู้ใช้ดูใบสั่งซื้อในโหมดดูอย่างเดียว ระบบโหลด:<br>
                - ข้อมูลใบสั่งซื้อ (เลขที่, วันที่)<br>
                - รายการสินค้า
            </div>

            <h3>2.4 LoadSubData</h3>
            <p>โหลดเฉพาะข้อมูลลูก (Sub Data) โดยไม่โหลดข้อมูลหลัก</p>
            <ul>
                <li><strong>ขั้นตอน:</strong>
                    <ol>
                        <li>ระบุข้อมูลลูกที่ต้องการ</li>
                        <li>ดึงข้อมูลจากฐานข้อมูลหรือเซสชัน</li>
                        <li>ส่งข้อมูลลูกกลับไปยังแอปพลิเคชัน</li>
                    </ol>
                </li>
            </ul>
            <div class="example">
                <strong>ตัวอย่าง:</strong><br>
                ผู้ใช้ดูรายการสินค้าในใบสั่งซื้อ ระบบโหลด:<br>
                <code>{id:1, list:A}, {id:2, list:B}</code>
            </div>
        </div>

        <div class="section">
            <h2>3. การวิเคราะห์แนวคิด</h2>
            <h3>ข้อดี</h3>
            <ul>
                <li><strong>ความยืดหยุ่น:</strong> รองรับการโหลดข้อมูลในสถานการณ์ต่าง ๆ</li>
                <li><strong>การจัดการสถานะชั่วคราว:</strong> ใช้เซสชันเพื่อเก็บข้อมูลที่ยังไม่บันทึก</li>
                <li><strong>ประสิทธิภาพ:</strong> การโหลดเฉพาะส่วนลดการใช้ทรัพยากร</li>
                <li><strong>รองรับ Low Code:</strong> ง่ายต่อการกำหนดค่าและปรับแต่ง</li>
            </ul>

            <h3>ข้อจำกัดและแนวทางแก้ไข</h3>
            <ul>
                <li><strong>ความซับซ้อนในการจัดการเซสชัน:</strong> อาจเกิดปัญหาเมื่อเซสชันมีขนาดใหญ่<br>
                    <strong>แนวทางแก้ไข:</strong> ใช้ระบบแคช เช่น Redis</li>
                <li><strong>ความขัดแย้งของข้อมูล:</strong> ผู้ใช้หลายคนแก้ไขพร้อมกัน<br>
                    <strong>แนวทางแก้ไข:</strong> ใช้กลไกการล็อกข้อมูล</li>
                <li><strong>การโหลดซ้ำซ้อน:</strong> การเรียกโหลดข้อมูลบ่อยครั้ง<br>
                    <strong>แนวทางแก้ไข:</strong> ใช้ Lazy Loading หรือ Cache</li>
            </ul>

            <h3>การประยุกต์ใช้ใน Low Code</h3>
            <ul>
                <li>สร้างฟอร์มอัตโนมัติด้วย LoadApp และ LoadApphasChanges</li>
                <li>แสดงผล Dashboard ด้วย LoadAppViewMode</li>
                <li>จัดการ Workflow ด้วย LoadSubData</li>
                <li>รองรับการแก้ไขแบบเรียลไทม์ด้วย LoadApphasChanges</li>
            </ul>
        </div>

        <div class="section">
            <h2>4. ขั้นตอนการใช้งาน WorkSystem</h2>
            <ol>
                <li>กำหนดโครงสร้างข้อมูลหลักและข้อมูลลูก</li>
                <li>ตั้งค่าวงจรการโหลดข้อมูลตามความต้องการ</li>
                <li>กำหนด UI Components ให้สอดคล้องกับฟังก์ชัน</li>
                <li>ทดสอบการโหลดข้อมูลในสถานการณ์ต่าง ๆ</li>
                <li>ปรับประสิทธิภาพด้วยการแคชหรือจำกัดการโหลด</li>
            </ol>
        </div>

        <div class="section">
            <h2>5. ตัวอย่างการใช้งานจริง</h2>
            <p>สมมติว่าระบบจัดการใบสั่งซื้อ:</p>
            <ol>
                <li><strong>ผู้ใช้เปิดใบสั่งซื้อ:</strong><br>
                    เรียก LoadApp โหลดข้อมูลทั้งหมด<br>
                    ผลลัพธ์: <code>{id:1, list:ProductA}, {id:2, list:ProductB}</code></li>
                <li><strong>ผู้ใช้เพิ่มสินค้าใหม่:</strong><br>
                    เรียก LoadApphasChanges รวมข้อมูลใหม่<br>
                    ผลลัพธ์: <code>{id:1, list:ProductA}, {id:2, list:ProductB}, {id:3, list:ProductC}</code '$(id:3, list:ProductC}</code></li>
                <li><strong>ผู้ใช้ดูตัวอย่าง:</strong><br>
                    เรียก LoadAppViewMode โหลดข้อมูลหลักและลูก</li>
                <li><strong>ผู้ใช้แก้ไขรายการสินค้า:</strong><br>
                    เรียก LoadSubData โหลดเฉพาะข้อมูลลูก</li>
            </ol>
        </div>

        <div class="section">
            <h2>6. สรุป</h2>
            <p>WorkSystem เป็นอัลกอริทึมที่ออกแบบมาเพื่อจัดการระบบ Low Code ด้วยการแบ่งการทำงานเป็นวงจรที่ชัดเจนและยืดหยุ่น การแยกฟังก์ชันการโหลดข้อมูลช่วยให้ระบบมีประสิทธิภาพและตอบสนองความต้องการของผู้ใช้ได้หลากหลาย การวิเคราะห์ข้อดีและข้อจำกัดช่วยให้สามารถปรับปรุงระบบให้เหมาะสมกับการใช้งานจริง</p>
        </div>
      </section>
      
      
      

      <section id="paths-config" class="authdoc-section">
        <h2>Authentication Paths Configuration</h2>
        <p>Configure login, logout, and redirect paths...</p>
      </section>

      <section id="guards" class="authdoc-section">
        <h2>Guards</h2>
        <p>Guards help protect routes and check session validity...</p>
      </section>
    </div>
  </div>
</body>
</html>

