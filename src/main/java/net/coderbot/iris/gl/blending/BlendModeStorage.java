package net.coderbot.iris.gl.blending;

import com.mojang.blaze3d.platform.GlStateManager;

import net.coderbot.iris.mixin.GlStateManagerAccessor;
import net.coderbot.iris.mixin.statelisteners.BooleanStateAccessor;
import net.coderbot.iris.render.IrisRenderSystem;
import net.coderbot.iris.render.pipeline.state.BlendFunc;

public class BlendModeStorage {
	private static boolean originalBlendEnable;
	private static BlendFunc originalBlend;
	private static boolean blendLocked;

	public static boolean isBlendLocked() {
		return blendLocked;
	}

	public static void overrideBlend(BlendFunc override) {
		if (!blendLocked) {
			// Only save the previous state if the blend mode wasn't already locked
			GlStateManager.BlendState blendState = GlStateManagerAccessor.getBLEND();

			originalBlendEnable = ((BooleanStateAccessor) blendState.mode).isEnabled();
			originalBlend = new BlendFunc(blendState.srcRgb, blendState.dstRgb, blendState.srcAlpha, blendState.dstAlpha);
		}

		blendLocked = false;

		if (override == null) {
			GlStateManager._disableBlend();
		} else {
			GlStateManager._enableBlend();
			GlStateManager._blendFuncSeparate(override.getSrcRgb(), override.getDstRgb(), override.getSrcAlpha(), override.getDstAlpha());
		}

		blendLocked = true;
	}

	public static void overrideBufferBlend(int index, BlendFunc override) {
		if (!blendLocked) {
			// Only save the previous state if the blend mode wasn't already locked
			GlStateManager.BlendState blendState = GlStateManagerAccessor.getBLEND();

			originalBlendEnable = ((BooleanStateAccessor) blendState.mode).isEnabled();
			originalBlend = new BlendFunc(blendState.srcRgb, blendState.dstRgb, blendState.srcAlpha, blendState.dstAlpha);
		}

		if (override == null) {
			IrisRenderSystem.disableBufferBlend(index);
		} else {
			IrisRenderSystem.enableBufferBlend(index);
			IrisRenderSystem.blendFuncSeparatei(index, override.getSrcRgb(), override.getDstRgb(), override.getSrcAlpha(), override.getDstAlpha());
		}

		blendLocked = true;
	}

	public static void deferBlendModeToggle(boolean enabled) {
		originalBlendEnable = enabled;
	}

	public static void deferBlendFunc(int srcRgb, int dstRgb, int srcAlpha, int dstAlpha) {
		originalBlend = new BlendFunc(srcRgb, dstRgb, srcAlpha, dstAlpha);
	}

	public static void restoreBlend() {
		if (!blendLocked) {
			return;
		}

		blendLocked = false;

		if (originalBlendEnable) {
			GlStateManager._enableBlend();
		} else {
			GlStateManager._disableBlend();
		}

		GlStateManager._blendFuncSeparate(originalBlend.getSrcRgb(), originalBlend.getDstRgb(),
				originalBlend.getSrcAlpha(), originalBlend.getDstAlpha());
	}
}
