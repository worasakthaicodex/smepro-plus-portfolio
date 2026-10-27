package com.smepro.dto;

public class FieldMetaDTO {
    private String key;
    private String title;
    private String format;

    public FieldMetaDTO(String key, String title, String format) {
        this.key = key;
        this.title = title;
        this.format = format;
    }

    public String getKey() { return key; }
    public String getTitle() { return title; }
    public String getFormat() { return format; }
}
