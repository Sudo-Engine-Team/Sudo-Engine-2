package site.root3287.sudo2.engine.render;

import org.lwjgl.opengl.GL11;

import site.root3287.sudo2.UI.UIWidget;
import site.root3287.sudo2.engine.VAO;
import site.root3287.sudo2.engine.shader.programs.UIShader;
import site.root3287.sudo2.utils.SudoMaths;

public class UIRender extends Renderable{

	public UIRender() {
		this.shader = new UIShader();
	}
	
	public void render(UIWidget w){
		w.getVAO().bind();
		this.shader.start();
		((UIShader)shader).trans.loadMatrix(SudoMaths.createTransformationMatrix(w.getAbsolutePosition(), w.getScale()));
		RenderUtils.enableVertexAttribsArray(0);
		RenderUtils.enableVertexAttribsArray(1);
		RenderUtils.renderElements(GL11.GL_TRIANGLES, w.getVAO().getSize(), GL11.GL_UNSIGNED_INT, 0);
		RenderUtils.disableVertexAttribsArray(0);
		RenderUtils.disableVertexAttribsArray(1);
		this.shader.stop();
		VAO.unbind();
	}
	
	@Override
	public void dispose() {
		this.shader.dispose();
	}

}
