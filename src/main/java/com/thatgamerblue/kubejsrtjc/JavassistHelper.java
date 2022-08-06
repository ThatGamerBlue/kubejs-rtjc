package com.thatgamerblue.kubejsrtjc;

import dev.latvian.kubejs.script.BindingsEvent;
import dev.latvian.mods.rhino.NativeJavaClass;
import java.util.Iterator;
import javassist.ClassClassPath;
import javassist.ClassPool;

public class JavassistHelper {
	private static int count = 0;
	private static ClassPool pool = null;

	public static Object addImport(Object[] args) {
		initClassPool();
		pool.importPackage((String) args[0]);
		return null;
	}

	public static Object removeImport(Object[] args) {
		initClassPool();
		Iterator<String> it = pool.getImportedPackages();
		while (it.hasNext()) {
			String p = it.next();
			if (p.equals(args[0])) {
				it.remove();
				return null;
			}
		}
		return null;
	}

	public static CtClassJS createClass() {
		initClassPool();
		return new CtClassJS(pool.makeClass("com.thatgamerblue.kubejsrtjc.JavassistHelper$GeneratedClass" + count++));
	}

	public static NativeJavaClass finalizeClass(BindingsEvent event, Object[] args) {
		return ((CtClassJS) args[0]).finalize(event);
	}

	private static void initClassPool() {
		if (pool != null) {
			return;
		}

		pool = ClassPool.getDefault();
		// this gets forge-loaded classes on the classpath in release
		pool.insertClassPath(new ClassClassPath(JavassistHelper.class));
	}
}