package com.thatgamerblue.kubejsrtjc;

import dev.latvian.kubejs.script.BindingsEvent;
import dev.latvian.mods.rhino.Context;
import dev.latvian.mods.rhino.NativeJavaClass;
import dev.latvian.mods.rhino.util.HideFromJS;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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

	@HideFromJS
	public NativeJavaClass finalize(BindingsEvent event) {
		try {
			Class<?> clazz = getJavaVersion() < 9 ? ctClass.toClass() : ctClass.toClass(JavassistHelper.class);

			if (RTJCSettings.instance.getDebug()) {
				event.type.console.info("RTJC: Generated class: " + clazz.getName());
				byte[] classFile = ctClass.toBytecode();
				Path outputDir = RTJCSettings.instance.getDebugDir();

				Path outputFile = outputDir.resolve(clazz.getName() + ".class");
				Files.write(outputFile, classFile);
			}

			return new NativeJavaClass(event.scope, clazz);
		} catch (CannotCompileException | IOException e) {
			throw Context.throwAsScriptRuntimeEx(e);
		}
	}

	private static int getJavaVersion() {
		String version = System.getProperty("java.version");
		if (version.startsWith("1.")) {
			version = version.substring(2, 3);
		} else {
			int dot = version.indexOf(".");
			if (dot != -1) {
				version = version.substring(0, dot);
			}
		}
		return Integer.parseInt(version);
	}
}