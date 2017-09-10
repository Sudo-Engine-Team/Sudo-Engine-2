#version 400 core

in vec3 position;
in vec2 textureCoords;

uniform mat4 projection;
uniform mat4 translation;

out vec2 pass_texture;

void main(){
	gl_Position = translation*projection*vec4(position, 1.0);
	pass_texture = textureCoords;
}
