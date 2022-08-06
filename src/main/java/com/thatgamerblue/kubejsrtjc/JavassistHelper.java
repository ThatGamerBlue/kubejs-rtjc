package com.thatgamerblue.kubejsrtjc;

import dev.latvian.kubejs.script.BindingsEvent;
import dev.latvian.mods.rhino.NativeJavaClass;
import javassist.ClassClassPath;
import javassist.ClassPool;

public class JavassistHelper {
	private static int count = 0;

	public static CtClassJS createClass() {
		ClassPool pool = ClassPool.getDefault();
		// this gets forge-loaded classes on the classpath in release
		pool.insertClassPath(new ClassClassPath(JavassistHelper.class));
		return new CtClassJS(pool.makeClass("com.thatgamerblue.kubejsrtjc.JavassistHelper$GeneratedClass" + count++));
	}

	public static NativeJavaClass finalizeClass(BindingsEvent event, Object[] args) {
		return ((CtClassJS) args[0]).finalize(event);
	}
}