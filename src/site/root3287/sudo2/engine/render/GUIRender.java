package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import site.root3287.sudo2.engine.shader.programs.GUIShader;
import site.root3287.sudo2.gui.GUIWidget;
import site.root3287.sudo2.utils.SudoMaths;

public class GUIRender extends Renderable{

	List<GUIWidget> widgets= new ArrayList<>();
	
	public GUIRender() {
		shader = new GUIShader();
	}
	
	public void render(GUIWidget w){
		shader.start();
		((GUIShader)shader).proj.loadMatrix(projection);
		((GUIShader)shader).trans.loadMatrix(SudoMaths.createTransformationMatrix(w.getPosition(), w.getScale()));
		RenderUtils.bindVAO(w.getModel().getID());
		RenderUtils.enableVertexAttribsArray(0);
		RenderUtils.renderElements(GL11.GL_TRIANGLES, w.getModel().getSize(), GL11.GL_UNSIGNED_INT, 0);
		RenderUtils.disableVertexAttribsArray(0);
		RenderUtils.unbindVAO();
		shader.stop();
	}
	
	public void render(){
		shader.start();
		((GUIShader)shader).proj.loadMatrix(projection);
		for(GUIWidget w : widgets){
			((GUIShader)shader).trans.loadMatrix(SudoMaths.createTransformationMatrix(w.getPosition(), w.getScale()));
			((GUIShader)shader).bgColour.loadVector(w.getBackgroundColour());
			RenderUtils.bindVAO(w.getModel().getID());
			RenderUtils.enableVertexAttribsArray(0);
			RenderUtils.renderElements(GL11.GL_TRIANGLES, w.getModel().getSize(), GL11.GL_UNSIGNED_INT, 0);
			RenderUtils.disableVertexAttribsArray(0);
		}
		RenderUtils.unbindVAO();
		shader.stop();
		widgets.clear();
	}
	
	public void addGUI(GUIWidget w){
		addGUI(w, false);
	}
	public void addGUI(GUIWidget w, boolean child){
		if(child){
			for(GUIWidget c : w.getChildren()){
				addGUI(c, true);
			}
		}else{
			if(w.getParent() != null){
				addGUI(w.getParent());
			}else{
				addGUI(w, true);
			}
		}
		widgets.add(w);
	}
	
	@Override
	public void dispose() {
		shader.dispose();
	}
	
}
