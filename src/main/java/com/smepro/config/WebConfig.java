package com.smepro.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc // 👈 ใส่ตัวนี้เพื่อปลุกพลัง Spring MVC ให้รู้จัก ResourceHandler
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ดักจับ URL รูปภาพ, ไฟล์ใน resources, และไฟล์ .ico ทั้งหมด
        registry.addResourceHandler("/resources/**", "/images/**", "/*.ico", "/**/*.js") // แอบแถม .js ให้ด้วยครับ
                .addResourceLocations("/public/", "/resources/", "/")
                // 💡 สั่งให้เบราว์เซอร์จำ 365 วัน!
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS).cachePublic()); 
    }
}