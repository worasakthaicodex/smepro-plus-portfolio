// เก็บ instance ของ Tagify ทั้งหมด โดยใช้ id เป็น key
const tagifyInstances = {};

function tagifyAll(appId) {
	
	deltagifyAll(appId);
	
    if (!appId) return;

    // ถ้ายังไม่มี appId ใน object ให้สร้างไว้ก่อน
    if (!tagifyInstances[appId]) {
        tagifyInstances[appId] = {};
    }

    // เลือก input ที่อยู่ใน appId นั้น (อาจจะอยู่ใน div หรือ form ที่มี id=appId)
    const container = document.getElementById("myForms"+appId);
    if (!container) return;

    const inputs = container.querySelectorAll('.form-tags');

    inputs.forEach(input => {
        const id = input.id;
        if (!id) return;

        // ถ้ามีแล้วไม่ต้องสร้างซ้ำ
        if (tagifyInstances[appId][id]) return;

        const blacklist = input.dataset.blacklist
            ? input.dataset.blacklist.split(',')
            : [];

        // สร้าง Tagify instance
        tagifyInstances[appId][id] = new Tagify(input, {
            whitelist: ["A# .NET", "A# (Axiom)", "A-0 System", "A+", "A++", "ABAP", "ABC"],
            blacklist: blacklist
        });
    });
}

function deltagifyAll(appId) {
    if (!appId || !tagifyInstances[appId]) return;

    // ทำลาย Tagify ทุกตัวใน appId นั้น
    for (const id in tagifyInstances[appId]) {
        tagifyInstances[appId][id].destroy();
    }

    // ล้างข้อมูลในหน่วยความจำ
    delete tagifyInstances[appId];
}