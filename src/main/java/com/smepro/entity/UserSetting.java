package com.smepro.entity;

import javax.persistence.*;

@Entity
@Table(name = "user_settings")
public class UserSetting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true, nullable = false)
    private Integer userId; // ผูกกับ User

    @Column(name = "background_image")
    private String backgroundImage; // เก็บ Path รูป

    @Column(name = "hidden_apps", columnDefinition = "TEXT")
    private String hiddenApps; // เก็บ ID แอพที่ซ่อน แบบ comma separated เช่น "1,5,9"

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }
    public String getBackgroundImage() { return backgroundImage; }
    public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }
    public String getHiddenApps() { return hiddenApps; }
    public void setHiddenApps(String hiddenApps) { this.hiddenApps = hiddenApps; }
}