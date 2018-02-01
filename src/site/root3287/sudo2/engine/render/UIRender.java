package site.root3287.sudo2.engine.render;

import org.lwjgl.opengl.GL11;

import site.root3287.sudo2.UI.UIText;
import site.root3287.sudo2.UI.UIWidget;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.shader.programs.UIShader;
import site.root3287.sudo2.utils.SudoMaths;

public class UIRender extends Renderable{

	TextRender textRender;
	
	public UIRender() {
		this.shader = new UIShader();
		this.textRender = new TextRender();
	}
	
	public void render(UIWidget w){
		if(!w.isVisable())
			return;
		
		w.getVAO().bind();
		this.shader.start();
		((UIShader)shader).trans.loadMatrix(SudoMaths.createTransformationMatrix(w.getAbsolutePosition(), w.getScale()));
		((UIShader)shader).colour.loadVector(w.getColour());
		
		//Render this GUI
		RenderUtils.disableDepthTest();
		RenderUtils.enableVertexAttribsArray(0);
		RenderUtils.enableVertexAttribsArray(1);
		RenderUtils.renderElements(GL11.GL_TRIANGLES, w.getVAO().getSize(), GL11.GL_UNSIGNED_INT, 0);
		RenderUtils.disableVertexAttribsArray(0);
		RenderUtils.disableVertexAttribsArray(1);
		RenderUtils.enableDepthTest();
		this.shader.stop();
		VAO.unbind();
		
		// Recursively go though and render the children.
		for(UIWidget child : w.getChildren()){
			//TODO check if the child is a text object, if so use the text renderer.
			if(child instanceof UIText){
				if(this.textRender.projection == null){
					this.textRender.projection = this.projection;
				}
				textRender.render(((UIText)child).getBitmapFont());
				continue;
			}
			render(child);
		}
	}
	
	@Override
	public void dispose() {
		this.textRender.dispose();
		this.shader.dispose();
	}

}
