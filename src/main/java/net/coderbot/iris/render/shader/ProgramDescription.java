package net.coderbot.iris.render.shader;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

// Based off of https://github.com/CaffeineMC/sodium-fabric/blob/1.19.x/next/components/gfx/src/main/java/net/caffeinemc/gfx/api/shader/ShaderDescription.java
public final class ProgramDescription {
	public final String name;
	public final Map<ShaderType, ShaderDescription> shaders;

	private ProgramDescription(String name, Map<ShaderType, ShaderDescription> shaders) {
		this.name = name;
		this.shaders = shaders;
	}

	public static Builder builder() {
		return new Builder();
	}

	private static class Builder {
		private final Map<ShaderType, ShaderDescription> shaders = new EnumMap<>(ShaderType.class);

		public Builder addShader(ShaderType type, ShaderDescription description) {
			shaders.put(type, description);
			return this;
		}

		public ProgramDescription build(String name) {
			if (shaders.isEmpty()) throw new IllegalStateException("No shader sources specified");
			return new ProgramDescription(name, Collections.unmodifiableMap(shaders));
		}
	}
}
