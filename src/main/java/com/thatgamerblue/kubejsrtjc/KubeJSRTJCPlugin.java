package com.thatgamerblue.kubejsrtjc;

import dev.latvian.kubejs.KubeJSPlugin;
import dev.latvian.kubejs.script.BindingsEvent;

public class KubeJSRTJCPlugin extends KubeJSPlugin {
	@Override
	@SuppressWarnings("Convert2MethodRef") // i prefer the way the code looks like this
	public void addBindings(BindingsEvent event) {
		event.addFunction("addImport", args -> JavassistHelper.addImport(args), String.class);
		event.addFunction("removeImport", args -> JavassistHelper.removeImport(args), String.class);
		event.addFunction("createClass", args -> JavassistHelper.createClass());
		event.addFunction("finalizeClass", args -> JavassistHelper.finalizeClass(event, args), CtClassJS.class);

		if (RTJCSettings.instance == null) {
			RTJCSettings.instance = new RTJCSettings();
			RTJCSettings.instance.clearDebugDir(event);
		}
		event.add("rtjcSettings", RTJCSettings.instance);
	}
}