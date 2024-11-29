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

public class Jugador {
    private String username;
    private String password;
    private int puntos;

    public Jugador(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void aumentarPuntos() {
        this.puntos++;
    }

    public static boolean UsernameValido(String username, Map<String, Jugador> usuarios) {
        return !usuarios.containsKey(username);
    }

    public static boolean PasswordValido(String password) {
        return password.length() == 5;
    }
}
