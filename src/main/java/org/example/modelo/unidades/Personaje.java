package org.example.modelo.unidades;

//fue el primer nombre q se me ocurriò, despues se puede cambiar.
public class Personaje {
    private Punto ubicacion;
    private boolean vivo;

    public Personaje(int x, int y){
        this.ubicacion = new Punto(x,y);
        this.vivo = true;
    }

    public boolean esta_Vivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }

    public Punto getUbicacion(){
        return ubicacion;
    }

    public void mover(Direccion dir){

        if(ubicacion != null) {
            int[] movimiento = dir.getCambio();
            //se instancia??
            ubicacion = new Punto(ubicacion.x() + movimiento[0], ubicacion.y() + movimiento[1]);
        }

    }
}
