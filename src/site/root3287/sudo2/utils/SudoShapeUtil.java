package site.root3287.sudo2.utils;

import site.root3287.sudo2.engine.VAO;

public class SudoShapeUtil {
	public static VAO generateRoundedSquare(float radius) {
		VAO vao = new VAO();
		VBO pos = new VBO();
		IBO ind = new IBO();
		
		//TODO: Logic
		
		vao.addIBO(ind);
		vao.addVBO(0, 3, pos);
		return vao;
	}
}
