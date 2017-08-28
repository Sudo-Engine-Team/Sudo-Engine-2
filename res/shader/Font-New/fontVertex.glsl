#version 400 core

in vec2 position;
in vec2 textureCoords;

uniform mat4 projection;
uniform mat4 translation;

out vec2 pass_textureCoords;

void main(void){
	gl_Position = translation*projection*vec4(position, 0.0, 0.0);
	pass_textureCoords = textureCoords;
}