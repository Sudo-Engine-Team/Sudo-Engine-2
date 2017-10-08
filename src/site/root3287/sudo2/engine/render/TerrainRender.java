package site.root3287.sudo2.engine.render;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.engine.shader.programs.TerrainShader;
import site.root3287.sudo2.terrain.Terrain;
import site.root3287.sudo2.utils.SudoMaths;

public class TerrainRender extends Renderable{
	public Matrix4f view;
	private List<Terrain> terrains = new ArrayList<>();
	
	public TerrainRender(){
		this.shader = new TerrainShader();
	}
	
	public void render(){
		shader.start();
		((TerrainShader)shader).proj.loadMatrix(projection);
		((TerrainShader)shader).view.loadMatrix(view);
		for(Terrain t : terrains){
			Matrix4f trans = SudoMaths.createTransformationMatrix(new Vector3f(t.getX(), 0, t.getZ()));
			((TerrainShader)shader).trans.loadMatrix(trans);
			((TerrainShader)shader).forceColour.loadBoolean(t.isForceColour());
			if(t.isForceColour())
				((TerrainShader) shader).colour.loadVector(t.getColour());
			RenderUtils.bindVAO(t.getModel().getVaoID());
			RenderUtils.enableVertexAttribsArray(0);
			RenderUtils.enableVertexAttribsArray(1);
			RenderUtils.enableVertexAttribsArray(2);
			RenderUtils.bindTexture(0, t.getTexture().getTextureID());
			RenderUtils.enableAlpha();
			RenderUtils.renderElements(GL11.GL_TRIANGLES, t.getModel().getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
			RenderUtils.disableVertexAttribsArray(0);
			RenderUtils.disableVertexAttribsArray(1);
			RenderUtils.disableVertexAttribsArray(2);
			RenderUtils.disableAlpha();
			RenderUtils.unbindVAO();
		}
		terrains.clear();
		shader.stop();
	}
	
	public void addTerrain(Terrain t){
		terrains.add(t);
	}
	
	@Override
	public void dispose() {
		shader.dispose();
	}

}
