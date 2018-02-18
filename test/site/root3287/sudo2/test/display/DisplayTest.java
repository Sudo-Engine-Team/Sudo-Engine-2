package site.root3287.sudo2.test.display;

import site.root3287.sudo2.display.Game;
import site.root3287.sudo2.display.Screen;

public class DisplayTest implements Screen{
	public static void main(String[] args) {
		Game game = new Game("SUDO-Engine");
		game.setScreen(new DisplayTest());
		game.run();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resize(float width, float height, float pixelWidth, float pixelHeight, float scaleWidth,
			float scaleHeight) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void destory() {
		// TODO Auto-generated method stub
		
	}
}
