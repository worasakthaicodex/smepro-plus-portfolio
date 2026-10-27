package com.smepro.repository;

public interface SalesFollowupCopyRepository {
    void copySalesFollowupHeader(int oldEdataId, int newEdataId);
    void copySalesFollowupChildren(int oldEdataId, int newEdataId);
}