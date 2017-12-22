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
    
    public Shader(String file){
    	ShaderProgram[] prgrams = loadShader(file);
    	vertexShaderID = prgrams[0].id;
    	fragmentShaderID = prgrams[1].id;
    	geometryShaderID = prgrams[2].id;
        programID = GL20.glCreateProgram();
    	GL20.glAttachShader(programID, vertexShaderID);
        GL20.glAttachShader(programID, fragmentShaderID);
        if(geometryShaderID != -1){
        	 GL20.glAttachShader(programID, geometryShaderID);
        	 System.out.println("HI");
        }
        bindAttributes();
        GL20.glLinkProgram(programID);
        GL20.glValidateProgram(programID);
        getAllUniformLocations();
    }
    
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
    
    public static ShaderProgram[] loadShader(String file){
    	ShaderProgram[] programs = new ShaderProgram[3];
    	
    	StringBuilder vertexShaderSource = new StringBuilder();
    	StringBuilder fragmentShaderSource = new StringBuilder();
    	StringBuilder geometryShaderSource = new StringBuilder();
    	
    	ShaderType currentShader = null;
    	
    	try{
        	InputStream in = Class.class.getResourceAsStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String line;
            int lineNum = 0;
            while((line = reader.readLine())!=null){
            	if(line.startsWith("#shader")){
            		String[] prams = line.split(" ");
            		if(prams[1].equalsIgnoreCase("vertex")){
            			currentShader = ShaderType.VERTEX;
            		}else if(prams[1].equalsIgnoreCase("fragment")){
            			currentShader = ShaderType.FRAGEMENT;
            		}else if(prams[1].equalsIgnoreCase("geometry")){
            			currentShader = ShaderType.GEOMETRY;
            		}else if(prams[1].equals("end")){
            			currentShader = null;
            		}
            	}else{
            		if(currentShader == null){
            			System.err.println("Line "+lineNum+": Shader not defined! Expected #shader <type>. Please use #shader <type> after #shader end");
            			System.exit(0);
            		}
            		switch (currentShader) {
					case VERTEX:
						vertexShaderSource.append(line).append("\n");
						break;
					case FRAGEMENT:
						fragmentShaderSource.append(line).append("\n");
						break;
					case GEOMETRY:
						geometryShaderSource.append(line).append("\n");
						break;
					default:
						System.err.println("Shader not defined! Use #shader <type> per shader!");
						break;
					}
            	}
            	lineNum++;
            }
            reader.close();
        }catch(IOException e){
            e.printStackTrace();
            System.exit(-1);
        }

    	for (int i=0; i < 3; i++){
    		programs[i] = new ShaderProgram();
    		programs[i].source = vertexShaderSource.toString();
    		switch(i){
	    		case 0:
	    			programs[i].type = ShaderType.VERTEX;
	    			programs[i].id = GL20.glCreateShader(GL20.GL_VERTEX_SHADER);
	    			GL20.glShaderSource(programs[i].id, vertexShaderSource);
	    			programs[i].source = vertexShaderSource.toString();
	    			break;
	    		case 1:
	    			programs[i].type = ShaderType.FRAGEMENT;
	    			programs[i].id = GL20.glCreateShader(GL20.GL_FRAGMENT_SHADER);
	    			GL20.glShaderSource(programs[i].id, fragmentShaderSource);
	    			programs[i].source = fragmentShaderSource.toString();
	    			break;
	    		case 2:
	    			if(geometryShaderSource.length() != 0){
	    				programs[i].type = ShaderType.GEOMETRY;
	    				programs[i].id = GL20.glCreateShader(GL32.GL_GEOMETRY_SHADER);
	    				GL20.glShaderSource(programs[i].id, geometryShaderSource);
	    				programs[i].source = geometryShaderSource.toString();
	    			}else{
	    				programs[i].type = ShaderType.GEOMETRY;
	    				programs[i].id = -1;
	    				programs[i].source = null;
	    			}
	    			break;
    		}
    		
    		if(programs[i].id == -1){
    			continue;
    		}
    		
    		GL20.glCompileShader(programs[i].id);
    		if(GL20.glGetShaderi(programs[i].id, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
    			System.out.println(GL20.glGetShaderInfoLog(programs[i].id, 500));
    			System.err.println("Could not compile shader in "+file+"!");
    			System.exit(-1);
    		}
    		DisplayManager.LOGGER.log(Level.INFO, "Shader loaded: "+file);
    	}
    	return programs;
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
       DisplayManager.LOGGER.log(Level.INFO, "Shader Loaded: "+file);
        return shaderID;
    }
    public int getProgramID() {
    	return programID;
    }
    
    private static class ShaderProgram{
    	public int id;
    	@SuppressWarnings("unused")
		public String source;
    	@SuppressWarnings("unused")
		public ShaderType type;
    }
    
    private static enum ShaderType{
    	VERTEX, GEOMETRY, FRAGEMENT, NULL
    }
}
