#version 400 core

in vec3 pos;
in vec2 tc;

uniform mat4 proj;
uniform mat4 trans;
uniform mat4 tcTrans;

out vec2 ptc;

void main(){
	ptc = (tcTrans*vec4(tc, 0, 1)).xy;
	gl_Position = proj * trans * vec4(pos, 1.0);
}
