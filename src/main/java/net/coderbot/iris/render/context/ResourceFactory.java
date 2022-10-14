package net.coderbot.iris.render.context;

import net.coderbot.iris.render.pipeline.RenderPipeline;
import net.coderbot.iris.render.shader.Program;
import net.coderbot.iris.render.shader.ProgramDescription;

public interface ResourceFactory {
	Program createProgram(ProgramDescription description);

	RenderPipeline createRenderPipeline(Program program);
}
