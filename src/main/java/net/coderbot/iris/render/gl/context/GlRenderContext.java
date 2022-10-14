package net.coderbot.iris.render.gl.context;

import java.util.function.Supplier;

import org.apache.commons.lang3.Validate;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20C;
import org.lwjgl.opengl.GLCapabilities;
import org.lwjgl.system.MemoryUtil;

import net.coderbot.iris.render.context.RenderContext;
import net.coderbot.iris.render.context.RenderContextProperties;
import net.coderbot.iris.render.gl.shader.GlProgram;
import net.coderbot.iris.render.pipeline.RenderPipeline;
import net.coderbot.iris.render.shader.Program;
import net.coderbot.iris.render.shader.ProgramDescription;

public final class GlRenderContext implements RenderContext {
	private static GlRenderContext instance = null;
	public static final Supplier<GlRenderContext> INSTANCE = () -> {
		if (instance == null) return instance = new GlRenderContext();
		return instance;
	};

	private static boolean initialized = false;

	private final RenderContextProperties properties;

	private GlRenderContext() {
		Validate.isTrue(!initialized, "Something tried to initialize GlRenderContext twice!!!");

		final GLCapabilities glCaps = GL.getCapabilities();
		final boolean computeShaders = glCaps.glDispatchCompute != MemoryUtil.NULL;

		RenderContextProperties.Capabilities.DSASupport dsaSupport;
		if (glCaps.OpenGL45) {
			dsaSupport = RenderContextProperties.Capabilities.DSASupport.CORE;
			// Iris.logger.info("OpenGL 4.5 detected, enabling DSA.");
		} else if (glCaps.GL_ARB_direct_state_access) {
			dsaSupport = RenderContextProperties.Capabilities.DSASupport.ARB;
			// Iris.logger.info("ARB_direct_state_access detected, enabling DSA.");
		} else {
			dsaSupport = RenderContextProperties.Capabilities.DSASupport.UNSUPPORTED;
			// Iris.logger.info("DSA support not detected.");
		}

		final String vendorName = GL11.glGetString(GL11.GL_VENDOR);
		final String deviceName = GL11.glGetString(GL11.GL_RENDERER);
		final String apiVersion = GL11.glGetString(GL11.GL_RENDERER);

		this.properties = new RenderContextProperties(new RenderContextProperties.Capabilities(computeShaders, dsaSupport), vendorName, deviceName, "OpenGL", apiVersion);

		initialized = true;
	}

	@Override
	public RenderContextProperties properties() {
		return properties;
	}

	@Override
	public Program createProgram(ProgramDescription description) {
		return new GlProgram(description);
	}

	@Override
	public RenderPipeline createRenderPipeline(Program program) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void bindRenderPipeline(RenderPipeline pipeline) {

	}

	@Override
	public void deleteProgram(Program program) {
		GlProgram glProgram = (GlProgram) program;
		GL20C.glDeleteProgram(glProgram.getHandle());
		glProgram.invalidateHandle();
	}
}
