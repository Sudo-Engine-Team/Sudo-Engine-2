#shader vertex
#version 400 core

in vec3 pos;

uniform mat4 proj;
uniform mat4 trans;

void main(){
	gl_Position =  proj*trans*vec4(pos, 1);
}

#shader fragment
#version 400 core

uniform vec4 bgColour;

out vec4 colour;

void main(){
	colour = bgColour;
}
#shader end
