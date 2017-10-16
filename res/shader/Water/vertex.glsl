#version 400 core

in vec3 pos;
in vec2 tc;
in vec3 normal;

uniform mat4 proj;
uniform mat4 view;
uniform mat4 trans;

out vec3 ptc;
out vec3 pn;

void main(){
	gl_Position = proj * view * trans * vec4(pos, 1);
	ptc = tc;
	pn = (inverse(trans) * vec4(normal, 0)).xyz;
}
