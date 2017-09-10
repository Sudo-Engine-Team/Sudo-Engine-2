package site.root3287.sudo2.text;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import site.root3287.sudo2.engine.model.Model;
import site.root3287.sudo2.engine.render.Renderable;
import site.root3287.sudo2.utils.SudoMaths;

public class TextRender extends Renderable {
	private List<BitmapFont> fonts = new ArrayList<>();
	
	public TextRender() {
		this.shader = new TextShader();
	}
	
	public void render(){
		shader.start();
		for(BitmapFont f : fonts){
				GL30.glBindVertexArray(f.getModel().getVaoID());
				GL20.glEnableVertexAttribArray(0);
				GL20.glEnableVertexAttribArray(1);
				//GL11.glEnable(GL11.GL_BLEND);
				//GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
				GL11.glDisable(GL11.GL_DEPTH_TEST);
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
	            GL11.glBindTexture(GL11.GL_TEXTURE_2D, f.getTexture().getTextureID());
				((TextShader) shader).loadTranslation(SudoMaths.createTransformationMatrix(f.getPosition(), f.getScale()));
				((TextShader) shader).loadProjection(this.projection);
				GL11.nglDrawElements(GL11.GL_TRIANGLES, f.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
				GL30.glBindVertexArray(0);
				GL20.glDisableVertexAttribArray(0);
				GL20.glDisableVertexAttribArray(1);
				GL11.glDisable(GL11.GL_BLEND);
				GL11.glEnable(GL11.GL_DEPTH_TEST);
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
