package com.smepro.simplifyjava;

import java.util.List;

public interface SqlServices {
    <T> List<T> getCustomSql(Class<T> type,String sql, int limit);
}
