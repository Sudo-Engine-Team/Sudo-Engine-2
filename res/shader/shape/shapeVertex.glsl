#version 400 core

in vec3 positions;
in vec2 textureCoords;

uniform vec2 pos;
uniform vec2 scl;
uniform mat4 pmatrix;

out vec2 passCoords;

void main(void){
	gl_Position =  pmatrix *vec4((positions.xy*scl + pos), positions.z, 1.0);
	passCoords = textureCoords;
}
