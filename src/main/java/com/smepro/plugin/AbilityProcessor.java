package com.smepro.plugin;

import java.util.ArrayList;
import java.util.List;
import java.util.ServiceLoader;

import com.smepro.dao.RecordApp_Component;
import com.smepro.models.LoadApp;

public class AbilityProcessor {
	

    private final List<AbilityHandler> handlers = new ArrayList<>();

    public AbilityProcessor() {
        // Built-in handler
        handlers.add(new MainHandler()); // รวม FG ทุก ability
      //  loadDynamicHandlers(); // Dynamic JAR
    }

    private void loadDynamicHandlers() {
        ServiceLoader<AbilityHandler> loader = ServiceLoader.load(AbilityHandler.class);
        for (AbilityHandler handler : loader) {
            handlers.add(handler);
        }
    }

    public void process(LoadApp loadApp, RecordApp_Component recordApp_Components) {
        for (String ability : loadApp.getAbility()) {
            for (AbilityHandler handler : handlers) {
                if (handler.supportedAbilities().contains(ability)) {
                    handler.handle(loadApp,recordApp_Components);
                    return;
                }
            }
        }
    }
}
