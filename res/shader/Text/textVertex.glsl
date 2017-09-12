#version 400 core

in vec3 pos;
in vec2 tc;

uniform mat4 projection;
uniform mat4 translation;

out vec2 ptc;

void main(){
	gl_Position = translation*projection*vec4(pos, 1.0);
	ptc = tc;
}
