package net.coderbot.iris.render.pipeline;

import org.apache.commons.lang3.Validate;
import org.jetbrains.annotations.Nullable;

import net.coderbot.iris.render.pipeline.state.BlendFunc;
import net.coderbot.iris.render.pipeline.state.CullMode;
import net.coderbot.iris.render.pipeline.state.PrimitiveTopology;
import net.coderbot.iris.render.shader.Program;

// Based off of https://github.com/CaffeineMC/sodium-fabric/blob/1.19.x/next/components/gfx/src/main/java/net/caffeinemc/gfx/api/pipeline/RenderPipelineDescription.java
public final class RenderPipelineDescription {
	public final String name;
	public final CullMode cullMode;
	@Nullable public final BlendFunc blendFunc;
	public final Program program;
	public final PrimitiveTopology primitiveTopology;

	private RenderPipelineDescription(String name, CullMode cullMode, @Nullable BlendFunc blendFunc, Program program, PrimitiveTopology primitiveTopology) {
		this.name = name;
		this.cullMode = cullMode;
		this.blendFunc = blendFunc;
		this.program = program;
		this.primitiveTopology = primitiveTopology;
	}

	public static Builder builder() {
		return new Builder();
	}

	private static class Builder {
		private CullMode cullMode = null;
		private BlendFunc blendFunc = null;
		private Program program = null;
		private PrimitiveTopology primitiveTopology = null;

		public Builder setCullingMode(CullMode cullMode) {
			this.cullMode = cullMode;
			return this;
		}

		public Builder setBlendFunction(BlendFunc mode) {
			this.blendFunc = mode;
			return this;
		}

		public Builder setProgram(Program program) {
			this.program = program;
			return this;
		}

		public Builder setTopology(PrimitiveTopology topology) {
			this.primitiveTopology = topology;
			return this;
		}

		public RenderPipelineDescription build(String name) {
			return new RenderPipelineDescription(name, Validate.notNull(cullMode), blendFunc, Validate.notNull(program), Validate.notNull(primitiveTopology));
		}
	}
}
