package com.thatgamerblue.kubejsrtjc;

import dev.latvian.kubejs.KubeJSPlugin;
import dev.latvian.kubejs.script.BindingsEvent;

public class KubeJSRTJCPlugin extends KubeJSPlugin {
	@Override
	public void addBindings(BindingsEvent event) {
		event.addFunction("createClass", args -> JavassistHelper.createClass());
		event.addFunction("finalizeClass", args -> JavassistHelper.finalizeClass(event, args), CtClassJS.class);

		if (RTJCSettings.instance == null) {
			RTJCSettings.instance = new RTJCSettings();
			RTJCSettings.instance.clearDebugDir(event);
		}
		event.add("rtjcSettings", RTJCSettings.instance);
	}
}