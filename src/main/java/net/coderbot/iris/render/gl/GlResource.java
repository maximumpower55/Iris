package net.coderbot.iris.render.gl;

public abstract class GlResource {
	private static final int INVALID_HANDLE = Integer.MIN_VALUE;

	private int handle = INVALID_HANDLE;

	protected final void setHandle(int handle) {
		if (handle == INVALID_HANDLE) throw new IllegalArgumentException("Handle must be valid");
	}

	public final int getHandle() {
		if (handle == INVALID_HANDLE) throw new IllegalArgumentException("%s handle is invalid".formatted(getClass().getSimpleName()));
		return handle;
	}

	public final void invalidateHandle() {
		handle = INVALID_HANDLE;
	}
}
