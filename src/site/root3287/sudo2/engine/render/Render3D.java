package site.root3287.sudo2.engine.render;

import site.root3287.sudo2.engine.camera.Camera;
import site.root3287.sudo2.engine.shader.programs.Shader3D;
import site.root3287.sudo2.engine.shader.programs.Shader3DImage;

public class Render3D extends Renderer {

	private Shader3D shader3d;
	private Shader3DImage shaderImage;
	
	public Render3D() {
		this.shader3d = new Shader3D();
		this.shaderImage = new Shader3DImage();
	}
	
	public void setCamera(Camera c) {
		this.shader3d.start();
		this.shader3d.stop();
		this.shaderImage.start();
		this.shaderImage.stop();
	}

	@Override
	public void dispose() {
		
	}

}
