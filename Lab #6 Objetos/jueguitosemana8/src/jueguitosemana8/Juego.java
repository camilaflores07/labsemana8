/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jueguitosemana8;

/**
 *
 * @author 50494
 */
import java.util.Map;
import javax.swing.JOptionPane;

public class Juego {
    private String[][] tablero;
    private String turno;
    private Jugador jugadorX;
    private Jugador jugadorO;

    public Juego(Jugador jugadorX, Jugador jugadorO) {
        this.tablero = new String[3][3];
        this.turno = "X";
        this.jugadorX = jugadorX;
        this.jugadorO = jugadorO;
        inicializarTablero();
    }

    private void inicializarTablero() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tablero[i][j] = "";
            }
        }
    }

    public void mostrarTablero() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(tablero[i][j].equals("") ? "." : tablero[i][j]);
                if (j < 2) sb.append(" | ");
            }
            sb.append("\n");
            if (i < 2) sb.append("---------\n");
        }
        JOptionPane.showMessageDialog(null, sb.toString());
    }

    public boolean hacerMovimiento(int fila, int columna) {
        if (fila < 0 || fila > 2 || columna < 0 || columna > 2 || !tablero[fila][columna].equals("")) {
            return false;  // Movimiento inválido
        }
        tablero[fila][columna] = turno;
        return true;
    }

    public boolean verificarGanador() {
        for (int i = 0; i < 3; i++) {
            if (tablero[i][0].equals(turno) && tablero[i][1].equals(turno) && tablero[i][2].equals(turno)) {
                return true;
            }
            if (tablero[0][i].equals(turno) && tablero[1][i].equals(turno) && tablero[2][i].equals(turno)) {
                return true;
            }
        }
        if (tablero[0][0].equals(turno) && tablero[1][1].equals(turno) && tablero[2][2].equals(turno)) {
            return true;
        }
        if (tablero[0][2].equals(turno) && tablero[1][1].equals(turno) && tablero[2][0].equals(turno)) {
            return true;
        }
        return false;
    }

    public boolean esEmpate() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tablero[i][j].equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    public void cambiarTurno() {
        turno = turno.equals("X") ? "O" : "X";
    }

    public Jugador getJugadorX() {
        return jugadorX;
    }

    public Jugador getJugadorO() {
        return jugadorO;
    }

    public String getTurno() {
        return turno;
    }
}

