package net.coderbot.iris.render.context;

import net.coderbot.iris.render.pipeline.RenderPipeline;

public interface RenderContext extends ResourceFactory, ResourceDestructors {
	RenderContextProperties properties();

	void bindRenderPipeline(RenderPipeline pipeline);
}
