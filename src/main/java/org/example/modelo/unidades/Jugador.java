package org.example.modelo.unidades;

public class Jugador extends Personaje{
    private final String nombre;
    private int tp_segura;

    public Jugador(String nombre, int tps) {
        super(0);
        this.nombre = nombre;
        this.tp_segura = tps;
    }

    public void add_puntos(int puntos){
        this.puntaje += puntos;
    }
    public void add_tps_seguros(int tps){
        this.tp_segura += tps;
    }

    private void hay_Teletrnsp_segura_disp() {
        if(tp_segura <= 0)throw new IndexOutOfBoundsException("no hay tps seguros disponibles.");
    }

    public void usar_teletransp_segura(){
        hay_Teletrnsp_segura_disp();
        tp_segura--;
    }

    public String getNombre() {
        return nombre;
    }
}
