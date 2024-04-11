package org.example.modelo.unidades;

//fue el primer nombre q se me ocurriò, despues se puede cambiar.
public class Personaje {
    private int fila;
    private int columna;
    private boolean vivo;

    public Personaje(int fila, int columna){
        this.fila = fila;
        this.columna = columna;
        this.vivo = true;
    }

    public boolean esta_Vivo() {
        return vivo;
    }
    public void setVivo(boolean vivo) {
        this.vivo = vivo;
    }
    //no se si habria q verificar aca en esta funcion si se nos va de la grilla en la q estamos o, si lo verificamos en otro lado.
    public void mover(Direccion dir){
        switch (dir){
            case ARRIBA:
                if (fila > 0){
                    fila--;
                }
                break;
            case ABAJO:
                fila++;
                break;
            case IZQUIERDA:
                if(columna > 0){
                    columna--;
                }
                break;
            case DERECHA:
                columna++;
                break;
            case ARRIBA_IZQ:
                if(fila > 0 && columna > 0){
                    fila--;
                    columna--;
                }
                break;
            case ARRIBA_DER:
                if(fila > 0){
                    fila--;
                    columna++;
                }
                break;
            case ABAJO_IZQ:
                if (columna > 0){
                    fila++;
                    columna--;
                }
                break;
            case ABAJO_DER:
                fila++;
                columna--;
                break;
        }
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }
}
