package site.root3287.sudo2.engine.shader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL32;

import site.root3287.sudo2.display.DisplayManager;

public abstract class Shader {
	protected int programID;
    private int vertexShaderID;
    private int fragmentShaderID;
    private int geometryShaderID;
     
    public Shader(String vertexFile,String fragmentFile){
        vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        geometryShaderID = -1;
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }
    
    public Shader(String vertexFile,String fragmentFile, String geometryFile){
        vertexShaderID = loadShader(vertexFile,GL20.GL_VERTEX_SHADER);
        fragmentShaderID = loadShader(fragmentFile,GL20.GL_FRAGMENT_SHADER);
        geometryShaderID = loadShader(geometryFile, GL32.GL_GEOMETRY_SHADER);
        programID = GL20.glCreateProgram();
        GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }
     
    protected abstract void getAllUniformLocations();
    
    public void start(){
        GL20.glUseProgram(programID);
    }
     
    public void stop(){
        GL20.glUseProgram(0);
    }
     
    public void dispose(){
    	DisplayManager.LOGGER.log(Level.INFO, "Disposing Shader");
        stop();
        GL20.glDetachShader(programID, vertexShaderID);
        GL20.glDetachShader(programID, fragmentShaderID);
        if(geometryShaderID != -1){
        	GL20.glDetachShader(programID, geometryShaderID);
        	GL20.glDeleteShader(geometryShaderID);
        }
        GL20.glDeleteShader(vertexShaderID);
        GL20.glDeleteShader(fragmentShaderID);
        GL20.glDeleteProgram(programID);
    }
     
    protected abstract void bindAttributes();
     
    protected void bindAttribute(int attribute, String variableName){
        GL20.glBindAttribLocation(programID, attribute, variableName);
    }  
     
    private static int loadShader(String file, int type){
        StringBuilder shaderSource = new StringBuilder();
        try{
        	InputStream in = Class.class.getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            while((line = reader.readLine())!=null){
                shaderSource.append(line).append("\n");
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }
        int shaderID = GL20.glCreateShader(type);
        GL20.glShaderSource(shaderID, shaderSource);
        GL20.glCompileShader(shaderID);
        if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
            System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
            System.err.println("Could not compile shader in "+file+"!");
            System.exit(-1);
        }
       DisplayManager.LOGGER.log(Level.INFO, "Loaded Shader: "+file);
        return shaderID;
    }
    public int getProgramID() {
    	return programID;
    }
}
