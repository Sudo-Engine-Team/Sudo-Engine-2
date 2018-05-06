package site.root3287.sudo2.engine.texture;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;

public class Texture {
	public static void bind(int target, int id){ GL11.glBindTexture(target, id);}
	public static void bind(int id){ bind(GL11.GL_TEXTURE_2D, id); }
	public static void unbind(){bind(0);}
	public static void remove(int id) { GL11.glDeleteTextures(id);}
	
	private int id;
	private int width, height;
	private ByteBuffer image;
	private boolean flip = true;
	
	public Texture() {
		this.id = GL11.glGenTextures();
	}
	
	public Texture(String path, boolean flip) { 
		this.id = GL11.glGenTextures();
		bind();
		ByteBuffer image;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            /* Prepare image buffers */
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            /* Load image */
            STBImage.stbi_set_flip_vertically_on_load(flip);
            image = STBImage.stbi_load(path, w, h, comp, 4);
            if(image == null) {
            		//TODO: image error
            }
            this.image = image;

            /* Get width and height of image */
            setWidth(w.get());
            setHeight(h.get());
            
            setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
            setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

            uploadData(GL11.GL_RGBA8, width, height, GL11.GL_RGBA, image);
        }
	}
	
	public void bind() {
		bind(this.id);
	}
	
	public void uploadData(int width, int height, ByteBuffer data) {
        uploadData(GL11.GL_RGBA8, width, height, GL11.GL_RGBA, data);
	}
	
	public void uploadData(int internalFormat, int width, int height, int format, ByteBuffer data) {
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, internalFormat, width, height, 0, format, GL11.GL_UNSIGNED_BYTE, data);
	}
	
	public void setParameter(int name, int value) {
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, name, value);
	}
	
	public int getWidth(){return width;}
	public void setWidth(int width) {if(width>0) this.width = width;}
	public int getHeight(){return height;}
	public void setHeight(int height) {if(height>0) this.height = height;}
	public ByteBuffer getImage(){return image;}
	public boolean flipped() {return this.flip;}
	
	public static Texture loadTexture(String path) { return loadTexture(path, true);}
	public static Texture loadTexture(String path, boolean flip) {
		ByteBuffer image;
        int width, height;
        try (MemoryStack stack = MemoryStack.stackPush()) {
            /* Prepare image buffers */
            IntBuffer w = stack.mallocInt(1);
            IntBuffer h = stack.mallocInt(1);
            IntBuffer comp = stack.mallocInt(1);

            /* Load image */
            STBImage.stbi_set_flip_vertically_on_load(flip);
            image = STBImage.stbi_load(path, w, h, comp, 4);
            if(image == null) {
            		//TODO: image error
            }

            /* Get width and height of image */
            width = w.get();
            height = h.get();
        }

        return createTexture(width, height, image);
	}
	
	public static Texture createTexture(int width, int height, ByteBuffer data) {
        Texture texture = new Texture();
        texture.setWidth(width);
        texture.setHeight(height);

        texture.bind();
        texture.setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        texture.setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        texture.uploadData(GL11.GL_RGBA8, width, height, GL11.GL_RGBA, data);

        return texture;
	}
}
