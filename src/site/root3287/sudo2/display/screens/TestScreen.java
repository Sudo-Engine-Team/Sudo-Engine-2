package site.root3287.sudo2.display.screens;

import org.lwjgl.util.vector.Vector3f;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.render.Render;
import site.root3287.sudo2.entities.CubeEntity;
import site.root3287.sudo2.entities.Light;

public class TestScreen implements Screen {

	private Render render;
	private CubeEntity cube;
	private Light light;
	
	@Override
	public void init() {
		this.render = new Render();
		cube = new CubeEntity();
		this.light = new Light(new Vector3f(0,10,0), new Vector4f(1, 1, 1, 1));
	}

	@Override
	public void update() {
		this.render.addEntity(cube);
		this.render.addLight(this.light);
	}
	
	@Override
	public void render() {
		this.render.render();
	}

	@Override
	public void destory() {
		this.render.dispose();
		Loader.getInstance().destory();
	}
}
