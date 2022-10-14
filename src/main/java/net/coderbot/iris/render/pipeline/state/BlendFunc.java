package net.coderbot.iris.render.pipeline.state;

import java.util.OptionalInt;

// Based off of https://github.com/CaffeineMC/sodium-fabric/blob/1.19.x/next/components/gfx/src/main/java/net/caffeinemc/gfx/api/pipeline/state/BlendFunc.java
public class BlendFunc {
	public final OptionalInt buffer;
	public final SrcFactor srcRgb, srcAlpha;
	public final DstFactor dstRgb, dstAlpha;

	public BlendFunc(OptionalInt buffer, SrcFactor srcRgb, DstFactor dstRgb, SrcFactor srcAlpha, DstFactor dstAlpha) {
		this.buffer = buffer;
		this.srcRgb = srcRgb;
		this.dstRgb = dstRgb;
		this.srcAlpha = srcAlpha;
		this.dstAlpha = dstAlpha;
	}

	public enum SrcFactor {
		ZERO,
		ONE,
		SRC_COLOR,
		ONE_MINUS_SRC_COLOR,
		DST_COLOR,
		ONE_MINUS_DST_COLOR,
		SRC_ALPHA,
		ONE_MINUS_SRC_ALPHA,
		DST_ALPHA,
		ONE_MINUS_DST_ALPHA,
		CONSTANT_COLOR,
		ONE_MINUS_CONSTANT_COLOR,
		CONSTANT_ALPHA,
		ONE_MINUS_CONSTANT_ALPHA,
		SRC_ALPHA_SATURATE;
	}

	public enum DstFactor {
		ZERO,
		ONE,
		SRC_COLOR,
		ONE_MINUS_SRC_COLOR,
		DST_COLOR,
		ONE_MINUS_DST_COLOR,
		SRC_ALPHA,
		ONE_MINUS_SRC_ALPHA,
		DST_ALPHA,
		ONE_MINUS_DST_ALPHA,
		CONSTANT_COLOR,
		ONE_MINUS_CONSTANT_COLOR,
		CONSTANT_ALPHA,
		ONE_MINUS_CONSTANT_ALPHA;
	}
}
