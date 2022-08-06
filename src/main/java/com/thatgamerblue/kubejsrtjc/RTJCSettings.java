package com.thatgamerblue.kubejsrtjc;

import dev.latvian.kubejs.script.BindingsEvent;
import dev.latvian.mods.rhino.util.HideFromJS;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import me.shedaniel.architectury.platform.Platform;
import org.apache.commons.io.FileUtils;

public class RTJCSettings {
	public static RTJCSettings instance;

	public boolean debug = false;

	public boolean getDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	@HideFromJS
	public void clearDebugDir(BindingsEvent event) {
		try {
			FileUtils.cleanDirectory(getDebugDir().toFile());
		} catch (IOException e) {
			event.type.console.error("RTJC: Failed to clear debug directory", e);
		}
	}

	@HideFromJS
	public Path getDebugDir() {
		Path dir = Platform.getGameFolder().resolve("logs/kubejs/rtjc");
		if (!Files.exists(dir)) {
			try {
				Files.createDirectories(dir);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return dir;
	}
}
