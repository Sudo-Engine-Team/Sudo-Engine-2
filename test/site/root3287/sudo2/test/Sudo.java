package site.root3287.sudo2.test;

import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.display.Game;
import site.root3287.sudo2.display.Screen;
import site.root3287.sudo2.engine.IBO;
import site.root3287.sudo2.engine.Renderable2D;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.VBO;
import site.root3287.sudo2.engine.camera.OrthographicCamera;
import site.root3287.sudo2.engine.render.Render2D;
import site.root3287.sudo2.engine.render.RenderUtils;
import site.root3287.sudo2.engine.texture.Texture;
import site.root3287.sudo2.ui.UI;
import site.root3287.sudo2.ui.nineSlice.NineSlice;
import site.root3287.sudo2.utils.SudoFile;

public class Sudo implements Screen{
	
	public static void main(String[] args) {
		Game game = new Game("test");
		game.setScreen(new Sudo(game));
		game.run();
	}
	
	Render2D render;
	Game game;
	OrthographicCamera c;
	NineSlice patch;
	Renderable2D obj;
	
	public Sudo(Game game) {
		this.game = game;
	}

	@Override
	public void init() {
		this.c = new OrthographicCamera();
		this.c.setDimension(game.getWidth(), game.getHeight(), game.getNearPlane(), game.getFarPlane());
		UI.DISPLAY_SIZE = new Vector2f(game.getWidth(), game.getHeight());
		UI.CAMERA = c;
		
		patch = new NineSlice(new Texture(SudoFile.getInternal("/test/TextureAtlas128.png"), false), 16, 16, new Vector2f(0, 0), new Vector2f(1, 0), new Vector2f(2, 0), new Vector2f(0, 1), new Vector2f(1, 1), new Vector2f(1, 2), new Vector2f(2, 0), new Vector2f(2, 1), new Vector2f(2, 2));		
		VAO model = new VAO();
		IBO ibo = new IBO();
		ibo.setData(new int[] {
				0,1,2,2,1,3
		});
		VBO pos = new VBO();
		pos.setData(new float[] {-1,1,0,-1,-1,0,1,1,0,1,-1,0});
		VBO tc = new VBO();
		tc.setData(new float[] {0,0,0,1,1,0,1,1});
		model.addIBO(ibo);
		model.addVBO(0, 3, pos);
		model.addVBO(1, 2, tc);
		patch.setModel(model);
		
		obj = new Renderable2D(new Texture(SudoFile.getInternal("/test/TextureAtlas128.png"), false));
		obj.setModel(model);
		
		render = new Render2D();
	}

	@Override
	public void update(float delta) {
		c.update(delta);
		render.setCamera(c);
		//patch.render(render);
	}

	@Override
	public void render() {
		RenderUtils.clear(game.getBackgroundColour());
		patch.render(render);
	}

	@Override
	public void resize(float width, float height, float pixelWidth, float pixelHeight, float scaleWidth,
			float scaleHeight) {
		UI.DISPLAY_SIZE = new Vector2f(width, height);
		
	}

	@Override
	public void destory() {
		render.dispose();
		patch.dispose();
	}

}
