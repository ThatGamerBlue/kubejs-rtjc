package com.thatgamerblue.kubejsrtjc;

import dev.latvian.mods.rhino.NativeJavaClass;
import dev.latvian.mods.rhino.Scriptable;
import javassist.ClassPool;

public class JavassistHelper {
	private static int count = 0;

	public static CtClassJS createClass() {
		ClassPool pool = ClassPool.getDefault();
		return new CtClassJS(pool.makeClass("com.thatgamerblue.kubejsrtjc.JavassistHelper$GeneratedClass" + count++));
	}

	public static NativeJavaClass finalizeClass(Scriptable scope, Object[] args) {
		return ((CtClassJS) args[0]).__finalize(scope);
	}
}