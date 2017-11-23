#version 400 core

in vec3 positions;
in vec2 textureCoords;

uniform mat4 trans;
uniform mat4 pmatrix;

out vec2 passCoords;

void main(void){
	gl_Position =  pmatrix * trans * vec4(positions, 1.0);
	passCoords = textureCoords;
}
