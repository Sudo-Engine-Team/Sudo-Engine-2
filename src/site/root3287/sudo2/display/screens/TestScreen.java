package site.root3287.sudo2.display.screens;

import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.render.Render;

public class TestScreen implements Screen {

	private Render render;
	
	@Override
	public void init() {
		this.render = new Render();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

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
