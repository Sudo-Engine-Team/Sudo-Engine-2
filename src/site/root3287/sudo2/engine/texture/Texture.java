package site.root3287.sudo2.engine.texture;

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.stb.STBImage;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.util.vector.Vector2f;

import de.matthiasmann.twl.utils.PNGDecoder;
import de.matthiasmann.twl.utils.PNGDecoder.Format;

public class Texture {
	public static void bind(int target, int id){ GL11.glBindTexture(target, id);}
	public static void bind(int id){ bind(GL11.GL_TEXTURE_2D, id); }
	public static void unbind(){bind(0);}
	public static void remove(int id) { GL11.glDeleteTextures(id);}
	
	private int id;
	private float width, height, textureWidth, textureHeight;
	private ByteBuffer image;
	private boolean flip = true;
	private Vector2f offset = new Vector2f();
	
	public Texture() {
		this.id = GL11.glGenTextures();
	}
	
	public Texture(InputStream path, boolean flip) { 
		this.id = GL11.glGenTextures();
		bind();
		
		PNGDecoder decoder;
		try {
		decoder = new PNGDecoder(path);
		this.image = BufferUtils.createByteBuffer(decoder.getWidth()*decoder.getHeight()*4);
		decoder.decode(image, decoder.getWidth()*4, Format.RGBA);
		this.image.flip();
		
        /* Get width and height of image */
        setImageWidth(decoder.getWidth());
        setImageHeight(decoder.getHeight());
        setTextureWidth(decoder.getWidth());
        setTextureHeight(decoder.getHeight());
            
        setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        uploadData(GL11.GL_RGBA, (int)width, (int)height, image);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void bind() {
		bind(this.id);
	}
	
	public void uploadData(int width, int height, ByteBuffer data) {
        uploadData(GL11.GL_RGBA, width, height, data);
	}
	
	public void uploadData(int internalFormat, int width, int height, ByteBuffer data) {
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, internalFormat, width, height, 0, internalFormat, GL11.GL_UNSIGNED_BYTE, data);
	}
	
	public void setParameter(int name, int value) {
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, name, value);
	}
	
	public int getID() { return this.id;}
	public float getImageWidth(){return width;}
	public void setImageWidth(int width) {if(width>0) this.width = width;}
	public float getImageHeight(){return height;}
	public void setImageHeight(int height) {if(height>0) this.height = height;}
	public float getTextureWidth() {return this.textureWidth;}
	public float getTextureHeight() {return this.textureHeight;}
	public void setTextureWidth(float w) { this.textureWidth = w;}
	public void setTextureHeight(float h) { this.textureHeight = h;}
	public ByteBuffer getImage(){return image;}
	public boolean flipped() {return this.flip;}
	public Vector2f getOffset() {return this.offset;}
	public void setTextureOffset(Vector2f offset) {this.offset = offset;}
	
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
        texture.setImageWidth(width);
        texture.setImageHeight(height);

        texture.bind();
        texture.setParameter(GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
        texture.setParameter(GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);

        texture.uploadData(GL11.GL_RGBA, width, height, data);

        return texture;
	}
	
	@Override
	public Texture clone(){
		Texture texture = new Texture();
		texture.flip = this.flip;
		texture.height = this.height;
		texture.id = this.id;
		texture.image = this.image;
		texture.offset = this.offset;
		texture.textureHeight = this.textureHeight;
		texture.textureWidth = this.textureWidth;
		texture.width = this.width;
		return texture;
	}
}
