
   
   /* --- Styles สำหรับรูปร่าง --- */
.shape-box {
    display: inline-block;
    width: 100px;
    height: 50px;
    border: 2px solid black;
    background: transparent;
    vertical-align: bottom;
    margin: 5px;
    padding: 5px; /* เผื่อพิมพ์ข้อความข้างใน */
    box-sizing: border-box;
}

.shape-circle {
    display: inline-block;
    width: 60px;
    height: 60px;
    border: 2px solid black;
    border-radius: 50%; /* ทำเป็นวงกลม */
    background: transparent;
    vertical-align: bottom;
    margin: 5px;
}

/* สามเหลี่ยม (ใช้เทคนิค Border) พิมพ์ข้างในไม่ได้นะอันนี้ */
.shape-triangle {
    display: inline-block;
    width: 0; 
    height: 0; 
    border-left: 30px solid transparent;
    border-right: 30px solid transparent;
    border-bottom: 60px solid black; /* สีสามเหลี่ยม */
    vertical-align: bottom;
    margin: 5px;
    position: relative;
}

.shape-line {
    display: inline-block;
    width: 100px;
    height: 2px;
    background-color: black;
    vertical-align: middle;
    margin: 5px;
}
   /* หูหิ้วสำหรับลาก (Drag Handle) */
.drag-handle {
    display: inline-block;
    width: 20px;
    height: 20px;
    background-color: #ddd; /* สีเทาๆ */
    color: #555;
    cursor: move; /* เมาส์เป็นรูปย้าย */
    font-size: 14px;
    line-height: 20px;
    text-align: center;
    border-radius: 3px;
    margin-right: 5px;
    user-select: none; /* ห้ามคลุมดำตัวนี้ */
    vertical-align: middle;
}

.drag-handle:hover {
    background-color: #bbb; /* เปลี่ยนสีตอนชี้ */
}

/* --- กระดานวาดรูป (Canvas Container) --- */
.drawing-canvas {
    display: inline-block;
    width: 300px;          /* ขนาดเริ่มต้น */
    height: 200px;
    border: 2px dashed #aaa; /* เส้นประให้รู้ขอบเขต */
    background-color: #fafafa;
    position: relative;    /* สำคัญ! เป็นจุดอ้างอิงให้ลูกๆ */
    resize: both;          /* ให้ user ดึงมุมขยายขนาดได้ */
    overflow: hidden;      /* ห้ามลูกหลุดออกนอกกล่อง */
    margin: 10px;
    vertical-align: top;
}

/* ถ้าคลิกเลือกกระดาน ให้เส้นเข้มขึ้น */
.drawing-canvas:focus-within, .drawing-canvas:hover {
    border-color: #007bff;
}

/* --- รูปร่างที่ลากได้ (Absolute Shape) --- */
.abs-shape {
    position: absolute;    /* ลอยตัวอิสระ */
    cursor: move;          /* เมาส์เป็นรูปย้าย */
    box-sizing: border-box;
}

/* ตัวเลือกกรอบสีแดงเวลาเลือก */
.selected-shape {
    outline: 2px solid red;
    z-index: 100; /* ให้ลอยทับเพื่อน */
}


/* --- อัปเดต: รูปร่างอิสระ --- */
.abs-shape {
    position: absolute;
    box-sizing: border-box;
    resize: both;       /* ย่อขยายได้ */
    overflow: hidden;   /* สำคัญมาก ไม่งั้นมุมดึงไม่ขึ้น */
    min-width: 30px;
    min-height: 30px;
    
    /* จัดกึ่งกลางสำหรับพวก Emoji/Shape */
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
}

/* ตอนเอาเมาส์ชี้ ให้ขึ้น Cursor ย้าย */
.abs-shape:hover {
    cursor: move; 
    outline: 1px dashed #ccc; /* เส้นประจางๆ บอกขอบเขต */
}

/* ตอนคลิกเลือก (Focus) */
.selected-shape {
    outline: 2px solid #007bff !important; /* เส้นสีฟ้าชัดๆ */
    z-index: 1000; /* ลอยทับเพื่อน */
}

/* Emoji Shape */
.emoji-shape {
    font-size: 40px;
    background: transparent;
    border: none;
    line-height: 1;
}

/* กล่องข้อความ (พิมพ์ได้) */
.textbox-shape {
    border: 1px dotted #999;
    background: transparent;
    cursor: text !important; /* เมาส์เป็นรูปตัว I */
    display: block; /* แก้เรื่องจัดกึ่งกลางให้พิมพ์ชิดซ้ายบน */
    padding: 5px;
    overflow: auto; /* ถ้าข้อความล้นให้เลื่อนได้ */
}

/* --- สไตล์สำหรับลูกศร --- */
.arrow-shape {
    display: flex;
    align-items: center;
    justify-content: center;
    background: transparent;
    font-size: 40px; /* ขนาดลูกศรเริ่มต้น */
    line-height: 1;
    color: black;
    border: none !important; /* ไม่มีขอบ */
        z-index: 5;    
}

/* --- สไตล์สำหรับกล่องข้อความ (พื้นใส) --- */
.textbox-shape {
    background-color: transparent; /* พื้นใส ไว้แปะทับคนอื่น */
    border: 1px dotted #aaa;       /* ขอบจางๆ พิมพ์เสร็จค่อยลบขอบได้ */
    padding: 5px;
    z-index: 10;                   /* ให้ลอยทับรูปทรงอื่นเสมอ */
}

/* แก้ไขสามเหลี่ยมให้ย่อขยายได้ (ใช้เทคนิค Clip Path แทน Border) */
.triangle-resize {
    background-color: black;
    clip-path: polygon(50% 0%, 0% 100%, 100% 100%);
    width: 100%;
    height: 100%;
}







/* --- Shape ทั่วไป --- */
.abs-shape {
    position: absolute;
    box-sizing: border-box;
    resize: both;
    overflow: hidden;
    min-width: 30px;
    min-height: 30px;
    display: flex;
    align-items: center;
    justify-content: center;
    text-align: center;
    
    /* แก้ข้อ 1: ตั้งค่าเริ่มต้นให้พอมีลำดับ */
    z-index: 10; 
}

/* แก้ข้อ 1: ตัวที่ถูกเลือก ต้องอยู่หน้าสุดเสมอ! */
.selected-shape {
    outline: 2px solid #007bff !important;
    z-index: 9999 !important; /* เอาให้ทะลุฟ้า */
}

/* แก้ข้อ 2: กล่องข้อความ */
.textbox-shape {
    border: 1px dotted #999;
    background: transparent;
    display: block;
    padding: 5px;
    overflow: hidden; /* ซ่อน Scrollbar */
    z-index: 20; /* ให้สูงกว่ารูปปกตินิดนึง จะได้วางทับได้ */
    
    /* เทคนิค: ถ้าไม่ได้เลือก ให้เมาส์เป็น Move ไว้ก่อน จะได้ลากง่าย */
    cursor: move; 
}

/* พอ Double Click หรือ Focus เข้าไปพิมพ์ ค่อยเปลี่ยนเมาส์ */
.textbox-shape:focus {
    cursor: text;
    outline: 1px dashed #007bff;
    z-index: 9999; /* ตอนพิมพ์ก็ต้องอยู่บนสุด */
}


/* --- Ribbon Improvement --- */
    .ribbon-section {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: flex-start;
        padding: 0 5px;
        height: 100%;
    }

    .ribbon-group {
        display: none; /* ซ่อนก่อน */
        align-items: flex-start; /* จัดชิดบน */
        height: 100%;
    }
    .ribbon-group.active { display: flex; }

    /* เส้นคั่นแนวตั้งระหว่างกลุ่ม */
    .separator {
        width: 1px;
        background-color: #ddd;
        height: 60px;
        margin: 0 5px;
        align-self: center;
    }
    .separator-small {
        width: 1px;
        background-color: #ddd;
        height: 20px;
        margin: 0 3px;
    }

    /* ปุ่มมาตรฐาน */
    .tool-btn {
        border: 1px solid transparent;
        background: none;
        padding: 2px 5px;
        cursor: pointer;
        border-radius: 2px;
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        font-size: 11px;
        color: #333;
        min-width: 25px;
        text-align: center;
    }
    .tool-btn:hover { background-color: #cce8ff; border-color: #99d1ff; }
    .tool-btn i, .tool-btn b { margin-bottom: 2px; font-size: 14px; }

    /* ปุ่มแบบแถวแนวนอน (Small Button) */
    .small-btn {
        flex-direction: row; /* เรียงแนวนอน */
        width: 100%;
        justify-content: flex-start;
        padding: 2px 4px;
        min-height: 22px;
        font-size: 11px;
    }
    .small-btn span { margin-right: 5px; }

    /* Wrapper สำหรับปุ่มสี (ที่มีแถบสีข้างล่าง) */
    .tool-btn-wrapper {
        display: flex;
        flex-direction: column;
        align-items: center;
        position: relative;
        cursor: pointer;
        border: 1px solid transparent;
    }
    .tool-btn-wrapper:hover { background-color: #cce8ff; border-color: #99d1ff; }
    
    .mini-color-bar {
        width: 100%;
        height: 4px;
        margin-top: -2px;
        overflow: hidden;
    }
    .mini-color-bar input {
        width: 100%;
        height: 20px;
        margin-top: -10px;
        cursor: pointer;
        opacity: 0;
    }
    
    
    
    
    