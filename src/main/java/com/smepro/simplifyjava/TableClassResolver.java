package com.smepro.simplifyjava;

import java.util.HashMap;
import java.util.Map;

import com.smepro.entity.account_ledger;

public class TableClassResolver {

    private static final Map<String, Class<?>> TABLE_CLASS_MAP = new HashMap<>();

    static {
        TABLE_CLASS_MAP.put("account_ledger", account_ledger.class);
        // เพิ่มตามตารางที่คุณใช้
    }

    public Class<?> resolve(String type) {
        return TABLE_CLASS_MAP.get(type);
    }
}