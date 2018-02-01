package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;

import site.root3287.sudo2.engine.shader.programs.TextShader;
import site.root3287.sudo2.text.BitmapFont;
import site.root3287.sudo2.utils.SudoMaths;

public class TextRender extends Renderable {
	private List<BitmapFont> fonts = new ArrayList<>();
	public TextRender() {
		this.shader = new TextShader();
	}
	
	public void render(){
		shader.start();
		RenderUtils.disableDepthTest();
		for(BitmapFont f : fonts){
			RenderUtils.bindVAO(f.getModel().getID());
			
			((TextShader) shader).location_isDF.loadBoolean(f.isDistanceField());
			((TextShader) shader).loadProjection(projection);
			((TextShader) shader).loadTranslation(SudoMaths.createTransformationMatrix(f.getPosition(), f.getScale()));
			((TextShader) shader).loadColour(f.getColour());
			((TextShader) shader).location_isDF.loadBoolean(Boolean.parseBoolean(f.getFile().getFileInfo().get("distanceField")));
			
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, f.getTexture().getTextureID());
			if(f.isDistanceField())
				f.getTexture().setTexturePrametersi(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			RenderUtils.enableVertexAttribsArray(0);
			RenderUtils.enableVertexAttribsArray(1);
			GL11.glDrawElements(GL11.GL_TRIANGLES, f.getModel().getSize(), GL11.GL_UNSIGNED_INT, 0);
			RenderUtils.disableVertexAttribsArray(0);
			RenderUtils.disableVertexAttribsArray(1);
			GL11.glDisable(GL11.GL_BLEND);
		}
		RenderUtils.enableDepthTest();
		RenderUtils.unbindVAO();
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

	public void render(BitmapFont f) {
		shader.start();
		RenderUtils.disableDepthTest();
			RenderUtils.bindVAO(f.getModel().getID());
			
			((TextShader) shader).location_isDF.loadBoolean(f.isDistanceField());
			((TextShader) shader).loadProjection(projection);
			((TextShader) shader).loadTranslation(SudoMaths.createTransformationMatrix(f.getPosition(), f.getScale()));
			((TextShader) shader).loadColour(f.getColour());
			((TextShader) shader).location_isDF.loadBoolean(Boolean.parseBoolean(f.getFile().getFileInfo().get("distanceField")));
			
			GL13.glActiveTexture(GL13.GL_TEXTURE0);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, f.getTexture().getTextureID());
			if(f.isDistanceField())
				f.getTexture().setTexturePrametersi(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
			
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			RenderUtils.enableVertexAttribsArray(0);
			RenderUtils.enableVertexAttribsArray(1);
			GL11.glDrawElements(GL11.GL_TRIANGLES, f.getModel().getSize(), GL11.GL_UNSIGNED_INT, 0);
			RenderUtils.disableVertexAttribsArray(0);
			RenderUtils.disableVertexAttribsArray(1);
			GL11.glDisable(GL11.GL_BLEND);
		RenderUtils.enableDepthTest();
		RenderUtils.unbindVAO();
		shader.stop();
	}
}
