package net.coderbot.iris.render;

import net.coderbot.iris.render.context.RenderContext;
import net.coderbot.iris.render.gl.context.GlRenderContext;

public final class GlobalRenderContext {
	public static RenderContext get() {
		return GlRenderContext.INSTANCE.get();
	}
}
