package net.coderbot.iris.render.gl;

import java.util.function.Consumer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32C;
import org.lwjgl.opengl.GL43C;

import it.unimi.dsi.fastutil.objects.Reference2IntMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import net.coderbot.iris.render.pipeline.state.PrimitiveTopology;
import net.coderbot.iris.render.shader.ShaderType;

public final class GlTypeConverter {
	private static final Reference2IntMap<PrimitiveTopology> PRIMITIVE_TOPOLOGIES = makeConversion(map -> {
		map.put(PrimitiveTopology.TRIANGLES, GL11.GL_TRIANGLES);
		map.put(PrimitiveTopology.TRIANGLE_STRIP, GL11.GL_TRIANGLE_STRIP);
		map.put(PrimitiveTopology.TRIANGLE_FAN, GL11.GL_TRIANGLE_FAN);
		map.put(PrimitiveTopology.LINES, GL11.GL_LINES);
		map.put(PrimitiveTopology.LINE_STRIP, GL11.GL_LINE_STRIP);
	});

	private static final Reference2IntMap<ShaderType> SHADER_TYPES = makeConversion(map -> {
		map.put(ShaderType.VERTEX, GL20.GL_VERTEX_SHADER);
		map.put(ShaderType.FRAGMENT, GL20.GL_FRAGMENT_SHADER);
		map.put(ShaderType.GEOMETRY, GL32C.GL_GEOMETRY_SHADER);
		map.put(ShaderType.COMPUTE, GL43C.GL_COMPUTE_SHADER);
	});

	public static int convert(PrimitiveTopology value) {
		return PRIMITIVE_TOPOLOGIES.getInt(value);
	}

	public static int convert(ShaderType value) {
		return SHADER_TYPES.getInt(value);
	}

	private static <T> Reference2IntMap<T> makeConversion(Consumer<Reference2IntMap<T>> consumer) {
		final Reference2IntMap<T> map = new Reference2IntOpenHashMap<>();
		map.defaultReturnValue(-1);
		consumer.accept(map);
		return map;
	}
}
