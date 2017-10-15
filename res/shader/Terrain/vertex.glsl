#version 400 core
in vec3 pos;
in vec2 tc;
in vec3 norm;

uniform mat4 proj;
uniform mat4 view;
uniform mat4 trans;

//Lighting
uniform vec3 lightPosition;

out vec2 ptc;
out vec3 pn;
out vec3 toLight;
out vec3 toCam;

void main(){
	vec4 worldPosition = trans * vec4(pos, 1);
	gl_Position = proj * view * worldPosition;
	ptc = tc;
	pn = (inverse(trans) * vec4(norm, 0)).xyz;
	toLight = lightPosition - worldPosition.xyz;
	toCam = (inverse(view) * vec4(0,0,0,1) - worldPosition).xyz;
}
