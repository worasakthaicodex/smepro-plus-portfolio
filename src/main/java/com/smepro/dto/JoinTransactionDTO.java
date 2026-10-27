package com.smepro.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JoinTransactionDTO {
    public Map<String, String> data = new HashMap<>();
    public List<JoinTransactionDTO> join1 = new ArrayList<>();
}
