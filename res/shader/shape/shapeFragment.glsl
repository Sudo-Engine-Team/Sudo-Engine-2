#version 400 core

in vec4 passCoords;

uniform vec4 col;

out vec4 colour;

void main(void){
	colour = vec4(col);
}
