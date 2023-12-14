package proyectoSyPHundirLaFlota.servidor.models;

import java.io.Serializable;

public class Coordenada implements Serializable{
	
	int ejeX;
	int ejeY;
	char valoDentro;
	
	public Coordenada(int ejeX, int ejeY, char valoDentro) {
		this.ejeX = ejeX;
		this.ejeY = ejeY;
		this.valoDentro = valoDentro;
	}
	
	public Coordenada(int ejeX, int ejeY) {
		this.ejeX = ejeX;
		this.ejeY = ejeY;
		this.valoDentro = '0';
	}

	public int getEjeX() {
		return ejeX;
	}

	public void setEjeX(int ejeX) {
		this.ejeX = ejeX;
	}

	public int getEjeY() {
		return ejeY;
	}

	public void setEjeY(int ejeY) {
		this.ejeY = ejeY;
	}

	public char getValoDentro() {
		return valoDentro;
	}

	public void setValoDentro(char valoDentro) {
		this.valoDentro = valoDentro;
	}
	
	

}
