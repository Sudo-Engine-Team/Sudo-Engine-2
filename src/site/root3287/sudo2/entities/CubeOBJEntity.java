package site.root3287.sudo2.entities;

import org.lwjgl.util.vector.Vector3f;

import site.root3287.sudo2.component.functions.AABBComponent;
import site.root3287.sudo2.component.functions.ModelComponet;
import site.root3287.sudo2.component.functions.TransposeComponent;
import site.root3287.sudo2.engine.Loader;
import site.root3287.sudo2.engine.ModelTexture;
import site.root3287.sudo2.engine.TexturedModel;
import site.root3287.sudo2.engine.objConverter.ModelData;
import site.root3287.sudo2.engine.objConverter.OBJFileLoader;
import site.root3287.sudo2.engine.physics.collision.AABB;

public class CubeOBJEntity extends Entity{

	public CubeOBJEntity() {
		addComponent(new TransposeComponent());
		getComponent(TransposeComponent.class).position = new Vector3f(0,0,0);
		addComponent(new ModelComponet());
		ModelData data = OBJFileLoader.loadOBJ("res/model/Cube/cube.obj");
		getComponent(ModelComponet.class).model = new TexturedModel(Loader.getInstance().loadToVAO(data.getVertices(), data.getTextureCoords(), data.getNormals(), data.getIndices()), new ModelTexture(Loader.getInstance().loadTexture("res/image/white.png")));
		getComponent(ModelComponet.class).model.getTexture().setReflectivity(1.0f);
		getComponent(ModelComponet.class).model.getTexture().setShineDamper(0.75f);
		addComponent(new AABBComponent());
		float scale = getComponent(TransposeComponent.class).scale;
		getComponent(AABBComponent.class).aabbBox = new AABB(getComponent(TransposeComponent.class).position, new Vector3f(1*scale+2, 1*scale+2, 1*scale+2));
	}
	
	@Override
	public void update(float delta) {
		float scale = getComponent(TransposeComponent.class).scale;
		getComponent(AABBComponent.class).aabbBox = new AABB(getComponent(TransposeComponent.class).position, new Vector3f(1*scale+2, 1*scale+2, 1*scale+2));
	}

}
