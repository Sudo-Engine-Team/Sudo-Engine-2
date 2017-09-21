package site.root3287.sudo2.engine.render;

import site.root3287.sudo2.engine.shader.programs.Shader2D;

public class Render2D extends Renderable{
	
	public Render2D(){
		this.shader = new Shader2D();
	}
	
	@Override
	public void dispose() {
		shader.dispose();
	}

}
