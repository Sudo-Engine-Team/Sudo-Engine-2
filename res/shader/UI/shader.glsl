#shader vertex
#version 400 core

in vec3 pos;
in vec2 tc;

uniform mat4 proj;
uniform mat4 trans;

out vec2 ptc;

void main(){
	gl_Position =  proj * trans * vec4(pos, 1);
	ptc = tc;
}

#shader fragment
#version 400 core

in vec2 ptc;

uniform vec4 colour;

out vec4 out_colour;

void main(){
	out_colour = colour;
}
#shader end
