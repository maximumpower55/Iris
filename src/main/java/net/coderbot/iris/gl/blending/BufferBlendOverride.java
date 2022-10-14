package net.coderbot.iris.gl.blending;

import net.coderbot.iris.render.pipeline.state.BlendFunc;

public class BufferBlendOverride {
	private final int index;
	private final BlendFunc blendMode;

	public BufferBlendOverride(int index, BlendFunc blendMode) {
		this.index = index;
		this.blendMode = blendMode;
	}

	public void apply() {
		BlendModeStorage.overrideBufferBlend(this.index, this.blendMode);
	}
}
