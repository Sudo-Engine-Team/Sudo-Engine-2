package site.root3287.sudo2.engine.render;

import java.util.logging.Level;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL32;
import org.lwjgl.opengl.GL40;
import org.lwjgl.util.vector.Vector4f;

import site.root3287.sudo2.display.Application;
import site.root3287.sudo2.display.DisplayManager;

public class RenderUtils {
	private static boolean wireframe = false;
	
	public static void enableCulling(){
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
	}
	public static void disableCulling(){
		GL11.glDisable(GL11.GL_CULL_FACE);
	}
	public static void enableDepthTest() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	public static void disableDepthTest() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
	}
	public static void clear(){
		clear(DisplayManager.BACKGROUND_COLOUR);
	}
	public static void clear(Vector4f colour){
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(colour.x, colour.y,
				colour.z, colour.w);
	}
	public static void toggleWireframe(){
		if(wireframe){
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
			wireframe = false;
		}else{
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
			wireframe = true;
		}
	}
	public static boolean isWireframe(){
		return wireframe;
	}
	public static void bindTexture(int index, int textureID) {
		int base = 0x84c0;
		if(index > 31) {
			return;
		}
		GL13.glActiveTexture(base+index);
	}
	
	public static void enableAlpha(){
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	public static void disableAlpha(){
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	 /**
     * <p><a target="_blank" href="http://docs.gl/gl4/glDrawArrays">Reference Page</a></p>
     * 
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices. Elements {@code first} through
     * 
     * <code>first + count &ndash; 1</code>
     * 
     * <p>of each enabled non-instanced array are transferred to the GL.</p>
     * 
     * <p>If an array corresponding to an attribute required by a vertex shader is not enabled, then the corresponding element is taken from the current attribute
     * state. If an array is enabled, the corresponding current vertex attribute value is unaffected by the execution of this function.</p>
     *
     * @param mode  the kind of primitives being constructed
     * @param first the first vertex to transfer to the GL
     * @param count the number of vertices after {@code first} to transfer to the GL
     */
	public static void renderArray(int mode, int first, int count) {
		clearGLErrors();
		GL11.glDrawArrays(mode, first, count);
		assert(checkGLError());
	}
	
	/**
     * <p><a target="_blank" href="http://docs.gl/gl4/glDrawElements">Reference Page</a></p>
     * 
     * Constructs a sequence of geometric primitives by successively transferring elements for {@code count} vertices to the GL.
     * The i<sup>th</sup> element transferred by {@code DrawElements} will be taken from element {@code indices[i]} (if no element array buffer is bound), or
     * from the element whose index is stored in the currently bound element array buffer at offset {@code indices + i}.
     *
     * @param mode    the kind of primitives being constructed. One of:<br><table><tr><td>{@link #GL_POINTS POINTS}</td><td>{@link #GL_LINE_STRIP LINE_STRIP}</td><td>{@link #GL_LINE_LOOP LINE_LOOP}</td><td>{@link #GL_LINES LINES}</td><td>{@link #GL_POLYGON POLYGON}</td><td>{@link #GL_TRIANGLE_STRIP TRIANGLE_STRIP}</td><td>{@link #GL_TRIANGLE_FAN TRIANGLE_FAN}</td></tr><tr><td>{@link #GL_TRIANGLES TRIANGLES}</td><td>{@link #GL_QUAD_STRIP QUAD_STRIP}</td><td>{@link #GL_QUADS QUADS}</td><td>{@link GL32#GL_LINES_ADJACENCY LINES_ADJACENCY}</td><td>{@link GL32#GL_LINE_STRIP_ADJACENCY LINE_STRIP_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLES_ADJACENCY TRIANGLES_ADJACENCY}</td><td>{@link GL32#GL_TRIANGLE_STRIP_ADJACENCY TRIANGLE_STRIP_ADJACENCY}</td></tr><tr><td>{@link GL40#GL_PATCHES PATCHES}</td></tr></table>
     * @param count   the number of vertices to transfer to the GL
     * @param type    indicates the type of index values in {@code indices}. One of:<br><table><tr><td>{@link #GL_UNSIGNED_BYTE UNSIGNED_BYTE}</td><td>{@link #GL_UNSIGNED_SHORT UNSIGNED_SHORT}</td><td>{@link #GL_UNSIGNED_INT UNSIGNED_INT}</td></tr></table>
     * @param indices the index values
     */
	public static void renderElements(int mode, int count, int type, long indices) {
		clearGLErrors();
		GL11.glDrawElements(mode, count, type, indices);
		assert(checkGLError() == false);
	}
	
	public static void bindVAO(int vao){
		GL30.glBindVertexArray(vao);
	}
	
	public static void unbindVAO(){
		bindVAO(0);
	}
	
	public static void enableVertexAttribsArray(int id){
		GL20.glEnableVertexAttribArray(id);
	}
	public static void disableVertexAttribsArray(int id){
		GL20.glDisableVertexAttribArray(id);
	}
	
	public static void clearGLErrors() {
		while(GL11.glGetError() != GL11.GL_NO_ERROR);
	}
	public static boolean checkGLError() {
		int e;
		boolean hasError = false;
		while((e = GL11.glGetError()) != GL11.GL_NO_ERROR) {
			Application.LOGGER.log(Level.SEVERE, "OpenGl has recieved an Error! (" +e+ ": 0x0" + Integer.toHexString(e)+")");
			hasError = true;
		}
		return hasError;
	}
}
