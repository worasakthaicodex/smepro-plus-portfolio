package com.smepro.dto;

import java.util.List;

import com.smepro.models.condition;
import com.smepro.models.keymap;
import com.smepro.models.process;
import com.smepro.models.workflow;

public class AppBlueprint {
		    public String version = "0.0";
		    public process processData;
		    public workflow workData;
		    public   condition condData;
		    public   List<keymap> keymapData;
		
		    public AppBlueprint(String version, process p, workflow w, condition c, List<keymap> k) {
		        this.version = version;
		        this.processData = p;
		        this.workData = w;
		        this.condData = c;
		        this.keymapData = k;
		    }
}
