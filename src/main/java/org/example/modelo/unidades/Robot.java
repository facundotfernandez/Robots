package org.example.modelo.unidades;

public class Robot extends Personaje{
  private final int puntaje;
  private final Jugador jugador;

    public Robot(int x, int y, Jugador jugador) {
        super(x, y);
        this.puntaje = 20; //x ahora lo dejo asi pero habria q ponerlo como constante  supong
        this.jugador = jugador;
    }



}

