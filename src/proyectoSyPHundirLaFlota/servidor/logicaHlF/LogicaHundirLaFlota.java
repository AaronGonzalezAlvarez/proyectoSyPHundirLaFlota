package proyectoSyPHundirLaFlota.servidor.logicaHlF;

import java.util.ArrayList;
import java.util.Random;

import proyectoSyPHundirLaFlota.servidor.models.Coordenada;

public class LogicaHundirLaFlota {
    
    public char[][] tablero;    
    public int size;
    
    public enum Direccion {
    SUMA, RESTA, MULTIPLICACION, DIVISION
    }
    
    public LogicaHundirLaFlota(){
        this.size =10;
        this.tablero = new char[this.size][this.size];
        crear();
    }
    
    public LogicaHundirLaFlota(int num){
        this.size =num;
        this.tablero = new char[this.size][this.size];
        crear();
    }
    
    public void crear(){
        for(int x=0;x<this.size;x++){
            for(int y=0;y<this.size;y++){
                this.tablero[x][y] = ' ';
            }
        }
    }
    
    public void imprimirTablero(){
        System.out.println("   0 1 2 3 4 5 6 7 8 9");
        for(int x=0;x<this.size;x++){
        for(int y=0;y<this.size;y++){  
            if(y == 0){
                System.out.print( x +" |" + this.tablero[x][y]);
            }else{
                System.out.print(" " + this.tablero[x][y]);
            }
            
        }
            System.out.println("");
    }
    }
    
    public boolean colocarBarco(int size, int fila , int columna, String direccion){
        int sum = 0;
        if("horizontal".equals(direccion)){
            sum = size + columna;
        }else if("vertical".equals(direccion)){
            sum = size + fila;
        }
        
        boolean valor = false;
        boolean colocar = true;
        if(sum <= this.size){
            
            //verificaciones horizontales
            if("horizontal".equals(direccion)){
                for(int x = 0; x<size;x++){
                    if(solapamiento(fila, columna + x)){
                        valor= false;
                        colocar = false;
                        System.out.println("Barco no colocado por solapamiento");
                        break;
                    }
                }                
            }
            
            
            //verificaciones verticales
            if("vertical".equals(direccion)){
                for(int x = 0; x<size;x++){
                    if(solapamiento(fila + x,columna)){
                        valor= false;
                        colocar = false;
                        System.out.println("Barco no colocado por solapamiento");
                        break;
                    }
                }
            }
                        
            //colocar barco
            if(colocar){
                if("horizontal".equals(direccion)){
                    for(int x = 0 ; x<size;x++){
                        //try {
                            this.tablero[fila][columna + x] = 'B';                                                        
                            if(x == 0){
                                if(!limites(fila , columna -1)){
                                    this.tablero[fila][columna - 1] = '*';
                                }                                
                                if(!limites(fila + 1, columna - 1)){
                                    this.tablero[fila+1][columna - 1] = '*';
                                }
                                
                                if(!limites(fila - 1, columna - 1)){
                                    this.tablero[fila-1][columna -1] = '*';
                                }                                                                                                
                            }else if(x == size - 1){
                                if(!limites(fila , columna + size)){
                                    this.tablero[fila][columna +size] = '*';
                                }
                                if(!limites(fila - 1, columna + size)){
                                    this.tablero[fila - 1][columna +size] = '*';
                                } 
                                if(!limites(fila + 1, columna + size)){
                                    this.tablero[fila + 1][columna +size] = '*';
                                } 
                            }                                                        
                            
                            if(!limites(fila + 1, columna + x)){
                                this.tablero[fila + 1][columna + x] = '*';
                            }
                            if(!limites(fila - 1, columna + x)){
                                this.tablero[fila - 1][columna + x] = '*';  
                            }                                                                              
                            valor = true;                            
                        /*} catch (Exception e) {
                        }*/                        
                    }                 
                }else if("vertical".equals(direccion)){
                    for(int x = 0 ; x<size;x++){
                        this.tablero[fila + x][columna] = 'B';
            
                        if(x == 0){
                                if(!limites(fila -1, columna)){
                                    this.tablero[fila -1][columna] = '*';
                                }                                
                                if(!limites(fila - 1, columna + 1)){
                                    this.tablero[fila-1][columna + 1] = '*';
                                }
                                
                                if(!limites(fila - 1, columna - 1)){
                                    this.tablero[fila-1][columna -1] = '*';
                                }                                                                                                
                            }else if(x == size - 1){
                                if(!limites(fila +size, columna)){
                                    this.tablero[fila+size][columna] = '*';
                                }
                                if(!limites(fila +size, columna -1)){
                                    this.tablero[fila +size][columna -1] = '*';
                                } 
                                if(!limites(fila + size, columna + 1)){
                                    this.tablero[fila + size][columna +1] = '*';
                                } 
                            }  
                        
                        if(!limites(fila + x, columna + 1)){
                            this.tablero[fila + x][columna + 1] = '*';
                        }
                        if(!limites(fila + x, columna - 1)){
                            this.tablero[fila + x][columna - 1] = '*';
                        }
                        
                        valor = true;
                    }
                }  
                System.out.println("Barco colocado");
            }
            
        }else{
            System.out.println("Barco se sale del tablero");
        }
        return valor;
    }
    
    //true se solapa , false no
    public boolean solapamiento(int fila , int columna){
        return this.tablero[fila][columna] == 'B' || this.tablero[fila][columna] == '*';        
    }
    
    /*public boolean limites( int fila, int columna) {  
        return fila> this.size || fila< this.size || columna < 0 || columna > this.size;
    }*/
    public boolean limites(int fila, int columna) {
        return fila < 0 || fila >= this.size || columna < 0 || columna >= this.size;
    }

    
    public boolean disparar(int fila , int columna){
        return this.tablero[fila][columna] == 'B';  
    }
    
    public void addBarcoAleatorios() {
    	Random rand = new Random();
    	Boolean ok = false;
        int aux=0;
        while(!ok) {
            int eje = rand.nextInt(2);
            int pX = rand.nextInt(10);
            int pY = rand.nextInt(10);
            boolean add = false;
            int sizeBarco = rand.nextInt(3) + 2;
            if(eje == 0) {
            	while(!add){
                	add = colocarBarco(sizeBarco, pX, pY, "horizontal");
                    pX = rand.nextInt(10);
                    pY = rand.nextInt(10);
                    sizeBarco = rand.nextInt(3) + 2;
                }
            }else {
            	while(!add){
            		add = colocarBarco(sizeBarco, pX, pY, "vertical");
            		pX = rand.nextInt(10);
                    pY = rand.nextInt(10);
                    sizeBarco = rand.nextInt(3) + 2;
                } 
            }
            aux++;
            if(aux == 4) {
            	ok=true;
            }
            
        }
    }
    
	public ArrayList<Coordenada> enviarTablero() {
		ArrayList<Coordenada> envio = new ArrayList<>();
		for (int x = 0; x < this.size; x++) {
			for (int y = 0; y < this.size; y++) {
				char valor = this.tablero[x][y];
				envio.add(new Coordenada(x, y, valor));
			}
		}
		return envio;
	}
    
}
