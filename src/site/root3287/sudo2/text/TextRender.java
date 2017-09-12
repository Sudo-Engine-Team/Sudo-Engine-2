package site.root3287.sudo2.text;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Vector2f;

import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.render.Renderable;
import site.root3287.sudo2.engine.texture.Texture;
import site.root3287.sudo2.utils.SudoMaths;

public class TextRender extends Renderable {
	private List<BitmapFont> fonts = new ArrayList<>();
	private float[] pos = {-1,1,0,-1,-1,0,1,1,0,1,-1,0};
	private int[] ind = {0,1,2,2,1,3};
	private float[] tc = {36f/512f, 292f/512f, 36f/512f, (292+73)/512f, (36+33)/512f, 292/512f, (36+33)/512f, (292+73)/512f};
	private Model m ;
	private Texture t;
	public TextRender() {
		this.shader = new TextShader();
		m = Loader.getInstance().loadToVAO(pos, tc, ind);
		t = new Texture(Loader.getInstance().loadTexture("res/fonts/Arial/*.png"));
	}
	
	public void render(){
		shader.start();
		for(BitmapFont f : fonts){
			GL30.glBindVertexArray(f.getModel().getVaoID());
			GL20.glEnableVertexAttribArray(0);
			GL20.glEnableVertexAttribArray(1);
			((TextShader) shader).loadProjection(projection);
			((TextShader) shader).loadTranslation(SudoMaths.createTransformationMatrix(new Vector2f(), new Vector2f(100,100)));
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, f.getTexture().getTextureID());
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			GL11.glDrawElements(GL11.GL_TRIANGLES, f.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			GL20.glDisableVertexAttribArray(0);
			GL20.glDisableVertexAttribArray(1);
			GL30.glBindVertexArray(0);
			GL11.glDisable(GL11.GL_BLEND);
		}
		shader.stop();
		fonts.clear();
	}

	@Override
	public void dispose() {
		shader.dispose();
	}

	public void addFont(BitmapFont f) {
		this.fonts.add(f);
	}
}
