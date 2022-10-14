package net.coderbot.iris.render.context;

import net.coderbot.iris.render.shader.Program;

public interface ResourceDestructors {
	void deleteProgram(Program program);
}
