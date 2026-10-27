package com.smepro.plugin;

import java.util.List;

import com.smepro.dao.RecordApp_Component;
import com.smepro.models.LoadApp;

public interface AbilityHandler {
		List<String> supportedAbilities(); 
	    void handle(LoadApp loadApp, RecordApp_Component recordApp_Components);
}
