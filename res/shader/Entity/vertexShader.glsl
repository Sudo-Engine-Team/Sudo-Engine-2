#version 400 core

const int maxLight = 4;

in vec3 position;
in vec2 textureCoords;
in vec3 normal;

out vec2 pass_textureCoords;
out vec3 surfaceNormal;
out vec3 toLightVector[maxLight];
out vec3 toCameraVector;

uniform mat4 transformationMatrix;
uniform mat4 projectionMatrix;
uniform mat4 viewMatrix;
uniform vec3 lightPosition[maxLight];

uniform float useTextureAtlas;
uniform float textureAtlasRows;
uniform vec2 textureAtlasOffset;

void main() {

	vec4 worldPosition = transformationMatrix * vec4(position, 1.0);
	gl_Position = projectionMatrix * viewMatrix * worldPosition;
	pass_textureCoords = textureCoords;
	if(useTextureAtlas > 0){
		pass_textureCoords = (pass_textureCoords / textureAtlasRows) + textureAtlasOffset;
	}
	
	surfaceNormal = (transformationMatrix * vec4(normal, 0.0)).xyz;
	for(int i=0; i<maxLight; i++){
		toLightVector[i] = lightPosition[i] - worldPosition.xyz;
	}
	toCameraVector = (inverse(viewMatrix) * vec4(0.0, 0.0, 0.0, 1.0)).xyz - worldPosition.xyz;
}
