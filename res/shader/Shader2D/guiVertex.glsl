#version 140

in vec2 position;

out vec2 textureCoords;

uniform mat4 transformation;
uniform mat4 projection;
uniform float useProjection;

uniform float useTextureAtlas;
uniform float textureAtlasRows;
uniform vec2 textureAtlasOffset;


void main(void){
	if(useProjection > 0){
		gl_Position = projection * transformation * vec4(position, 0.0, 1.0);
	}else{
		gl_Position = transformation * vec4(position, 0.0, 1.0);
	}

	vec2 preTextureCoords = vec2(1-(position.x+1.0)/2.0, (position.y+1.0)/2.0);
	if(useTextureAtlas > 0){
		preTextureCoords += textureAtlasOffset;
		preTextureCoords /= textureAtlasRows;
	}
	textureCoords = preTextureCoords;
}
