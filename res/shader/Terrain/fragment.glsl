#version 400 core

in vec2 ptc;
in vec3 pn;
in vec3 toLight;
in vec3 toCam;

uniform sampler2D img;
uniform float forceColour;
uniform vec4 fcolour;
out vec4 colour;

//Lighting
uniform vec3 lightColour;
uniform vec3 lightPosition;

const float ambientLight = 0.1f;

//TODO: make uniform
const float specular = 0.5f;

void main(){
	vec3 finalAmbient = ambientLight * lightColour.xyz;
	vec3 unitNormal = normalize(pn);
	vec3 unitLight = normalize(toLight);
	//Diffuse
	float brightness = max(dot(unitNormal, unitLight), 0.1);
	vec3 finalDiffuse = brightness * lightColour.xyz;
	
	//Specular
	vec3 viewDir = normalize(toCam);
	vec3 reflectDir = reflect(-unitLight, unitNormal);
	float spec = pow(max(dot(viewDir, reflectDir), 0.0), 8);
	vec3 finalSpecular = specular * spec * lightColour.xyz;

	//Texture Colour
	colour = texture(img, ptc);
	if(forceColour > 0){
		colour = fcolour;
	}
	
	colour = vec4((finalDiffuse), 1)*colour;
}
