package com.smepro.repository;

import java.util.List;

import com.smepro.models.edata;

public interface CPlatformPartialRepositoryCustom<T> {
    T findById(long id);
}
