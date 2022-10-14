package net.coderbot.iris.gl.blending;

import net.coderbot.iris.render.pipeline.state.BlendFunc;

public class BlendModeOverride {
	public static final BlendModeOverride OFF = new BlendModeOverride(null);

	private final BlendFunc blendMode;

	public BlendModeOverride(BlendFunc blendMode) {
		this.blendMode = blendMode;
	}

	public void apply() {
		BlendModeStorage.overrideBlend(this.blendMode);
	}

	public static void restore() {
		BlendModeStorage.restoreBlend();
	}
}
