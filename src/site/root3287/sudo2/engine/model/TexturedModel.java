package site.root3287.sudo2.engine.model;

import site.root3287.sudo2.engine.texture.AbstractTexture;

public class TexturedModel{
	private Model model;
	private AbstractTexture texture;
	
	private float shineDamper = 1;
    private float reflectivity = 0;
    
    private boolean hasTranspancy = false;
    private boolean useFakeLight = false;
   
    public TexturedModel(Model model, AbstractTexture texture) {
		super();
		this.model = model;
		this.texture = texture;
	}
    
	public float getShineDamper() {
		return shineDamper;
	}

	public void setShineDamper(float shineDamper) {
		this.shineDamper = shineDamper;
	}

	public float getReflectivity() {
		return reflectivity;
	}

	public void setReflectivity(float reflectivity) {
		this.reflectivity = reflectivity;
	}

	public boolean isHasTranspancy() {
		return hasTranspancy;
	}

	public void setHasTranspancy(boolean hasTranspancy) {
		this.hasTranspancy = hasTranspancy;
	}

	public boolean isUseFakeLight() {
		return useFakeLight;
	}

	public void setUseFakeLight(boolean useFakeLight) {
		this.useFakeLight = useFakeLight;
	}
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public AbstractTexture getTexture() {
		return texture;
	}
	public void setTexture(AbstractTexture texture) {
		this.texture = texture;
	}
}
