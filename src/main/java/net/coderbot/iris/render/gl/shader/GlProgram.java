package net.coderbot.iris.render.gl.shader;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL20C;

import it.unimi.dsi.fastutil.ints.IntArrayList;
import net.coderbot.iris.render.gl.GlResource;
import net.coderbot.iris.render.gl.GlTypeConverter;
import net.coderbot.iris.render.shader.Program;
import net.coderbot.iris.render.shader.ProgramDescription;
import net.coderbot.iris.render.shader.ShaderDescription;
import net.coderbot.iris.render.shader.ShaderType;

public final class GlProgram extends GlResource implements Program {
	private static final Logger LOGGER = LogManager.getLogger(GlProgram.class);

	public GlProgram(ProgramDescription description) {
		IntArrayList shaderHandles = new IntArrayList(description.shaders.size());

		for (Map.Entry<ShaderType, ShaderDescription> entry : description.shaders.entrySet()) {
			shaderHandles.add(createShader(entry.getKey(), entry.getValue()));
		}

		int handle = GL20C.glCreateProgram();

		for (int shaderHandle : shaderHandles) {
			GL20C.glAttachShader(handle, shaderHandle);
		}

		GL20C.glLinkProgram(handle);

		for (int shaderHandle : shaderHandles) {
			GL20C.glDetachShader(handle, shaderHandle);
			GL20C.glDeleteShader(shaderHandle);
		}

		String log = GL20C.glGetProgramInfoLog(handle);

		if (!log.isEmpty()) {
			LOGGER.warn("Program link log for " + description.name + ": " + log);
		}

		int result = GL20C.glGetProgrami(handle, GL20C.GL_LINK_STATUS);

		if (result != GL20C.GL_TRUE) {
			throw new RuntimeException("Shader program linking failed, see log for details");
		}

		setHandle(handle);
	}

	private static int createShader(ShaderType type, ShaderDescription description) {
		int handle = GL20C.glCreateShader(GlTypeConverter.convert(type));
		ShaderWorkarounds.safeShaderSource(handle, description.src);
		GL20C.glCompileShader(handle);

		// GLDebug.nameObject(KHRDebug.GL_SHADER, handle, name + "(" + type.name().toLowerCase(Locale.ROOT) + ")");

		String log = GL20C.glGetShaderInfoLog(handle);

		if (!log.isEmpty()) {
			LOGGER.warn("Shader compilation log for " + description.name + ": " + log);
		}

		int result = GL20C.glGetShaderi(handle, GL20C.GL_COMPILE_STATUS);

		if (result != GL20C.GL_TRUE) {
			GL20C.glDeleteShader(handle);
			throw new RuntimeException("Shader compilation failed, see log for details");
		}

		return handle;
	}
}
