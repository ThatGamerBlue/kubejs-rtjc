package com.thatgamerblue.kubejsrtjc;

import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.NativeJavaClass;
import dev.latvian.mods.rhino.Scriptable;
import javassist.CannotCompileException;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.CtNewMethod;

public class CtClassJS {
	private final CtClass ctClass;

	public CtClassJS(CtClass ctClass) {
		this.ctClass = ctClass;
	}

	public void addMethod(String code) {
		try {
			CtMethod method = CtNewMethod.make(code, ctClass);
			ctClass.addMethod(method);
		} catch (CannotCompileException e) {
			throw Context.throwAsScriptRuntimeEx(e);
		}
	}

	public NativeJavaClass __finalize(Scriptable scope) {
		try {
			return new NativeJavaClass(scope, ctClass.toClass());
		} catch (CannotCompileException e) {
			throw Context.throwAsScriptRuntimeEx(e);
		}
	}
}