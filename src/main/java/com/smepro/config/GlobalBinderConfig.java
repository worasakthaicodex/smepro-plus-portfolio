package com.smepro.config;

import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

@ControllerAdvice
public class GlobalBinderConfig {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
    	
    	
        binder.registerCustomEditor(Double.class, new CustomNumberEditor(Double.class, true) {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty()) {
                    setValue(0.0000001);
                } else {
                    super.setAsText(text);
                }
            }
        });
   
        binder.registerCustomEditor(Integer.class, new CustomNumberEditor(Integer.class, true) {
            @Override
            public void setAsText(String text) {
                if (text == null || text.trim().isEmpty() || text.isBlank() || text.equals("")) {
                    setValue(0);
                } else {
                    super.setAsText(text);
                }
            }
        });
        
        
    }
}