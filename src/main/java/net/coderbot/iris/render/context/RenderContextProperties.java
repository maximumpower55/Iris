package net.coderbot.iris.render.context;

// Based off of https://github.com/CaffeineMC/sodium-fabric/blob/1.19.x/next/components/gfx/src/main/java/net/caffeinemc/gfx/api/device/RenderDeviceProperties.java
public final class RenderContextProperties {
	public final Capabilities capabilities;

	public final String vendorName;
	public final String deviceName;
	public final String apiName;
	public final String apiVersion;

	public RenderContextProperties(
		Capabilities capabilities,
		String vendorName,
		String deviceName,
		String apiName,
		String apiVersion
	) {
		this.capabilities = capabilities;
		this.vendorName = vendorName;
		this.deviceName = deviceName;
		this.apiName = apiName;
		this.apiVersion = apiVersion;
	}

	public static class Capabilities {
		public final boolean computeShaders;

		public final DSASupport dsaSupport;

		public Capabilities(
			boolean computeShaders,
			DSASupport dsaSupport
		) {
			this.computeShaders = computeShaders;
			this.dsaSupport = dsaSupport;
		}

		public static enum DSASupport {
			UNSUPPORTED,
			ARB,
			CORE
		}
	}
}
