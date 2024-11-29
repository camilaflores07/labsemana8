/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jueguitosemana8;

/**
 *
 * @author 50494
 */
import javax.swing.*;
import java.util.*;


public class Menu {
    private Map<String, Jugador> usuarios;
    private Jugador jugadorActual;

    public Menu() {
        this.usuarios = new HashMap<>();
    }

    public void mostrarMenuInicio() {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Menu Inicio \n\n1. Iniciar Sesion\n2. Registro\n3. Salir");
            if (opcion == null) break;

            switch (opcion) {
                case "1":
                    iniciarSesion();
                    break;
                case "2":
                    registro();
                    break;
                case "3":
                    System.exit(0); // Salir
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida ");
            }
        }
    }

    private void iniciarSesion() {
        String username = JOptionPane.showInputDialog("Ingrese su username: ");
        String password = JOptionPane.showInputDialog("Ingrese su contraseña: ");

        if (usuarios.containsKey(username) && usuarios.get(username).getPassword().equals(password)) {
            jugadorActual = usuarios.get(username);
            JOptionPane.showMessageDialog(null, "Bienvenido " + jugadorActual.getUsername());
            menuPrincipal();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecta ");
        }
    }

    private void registro() {
        String username = JOptionPane.showInputDialog("Ingrese su username ");
        if (!Jugador.UsernameValido(username, usuarios)) {
            JOptionPane.showMessageDialog(null, "El username ya existe, intente con otro ");
            return;
        }
        String password = JOptionPane.showInputDialog("Ingrese su contraseña (5 caracteres):");
        if (!Jugador.PasswordValido(password)) {
            JOptionPane.showMessageDialog(null, "La contraseña debe tener 5 caracteres ");
            return;
        }
        usuarios.put(username, new Jugador(username, password));
        JOptionPane.showMessageDialog(null, "Registro exitoso, Ahora puede iniciar sesion ");
    }

    private void menuPrincipal() {
        while (true) {
            String opcion = JOptionPane.showInputDialog("Menu Principal\n\n1. Jugar X-0\n2. Ranking\n3. Cerrar sesion\n4. Salir");
            if (opcion == null) break;

            switch (opcion) {
                case "1":
                    jugarX0();
                    break;
                case "2":
                    mostrarRanking();
                    break;
                case "3":
                    jugadorActual = null;
                    JOptionPane.showMessageDialog(null, "Sesion cerrada ");
                    return;
                case "4":
                    System.exit(0); // Salir
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida ");
            }
        }
    }

    private void jugarX0() {
        String jugadorOUsername = JOptionPane.showInputDialog("Ingrese el username del segundo jugador: ");
        if (!usuarios.containsKey(jugadorOUsername) || jugadorOUsername.equals(jugadorActual.getUsername())) {
            JOptionPane.showMessageDialog(null, "Jugador no valido ");
            return;
        }
        Jugador jugadorO = usuarios.get(jugadorOUsername);
        Juego juego = new Juego(jugadorActual, jugadorO);

        while (true) {
            juego.mostrarTablero();
            String input = JOptionPane.showInputDialog("Jugador " + juego.getTurno() + ", ingrese fila y columna (0-2) separados por espacio ");
            if (input == null) break;

            String[] parts = input.split(" ");
            try {
                int fila = Integer.parseInt(parts[0]);
                int columna = Integer.parseInt(parts[1]);

                if (juego.hacerMovimiento(fila, columna)) {
                    if (juego.verificarGanador()) {
                        JOptionPane.showMessageDialog(null, "El jugador " + juego.getTurno() + " ha ganado");
                        if (juego.getTurno().equals("X")) {
                            juego.getJugadorX().aumentarPuntos();
                        } else {
                            juego.getJugadorO().aumentarPuntos();
                        }
                        break;
                    }
                    if (juego.esEmpate()) {
                        JOptionPane.showMessageDialog(null, "Empate ");
                        break;
                    }
                    juego.cambiarTurno();
                } else {
                    JOptionPane.showMessageDialog(null, "Movimiento invalido, intente de nuevo ");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Entrada invalida, intente de nuevo.");
            }
        }
    }

    private void mostrarRanking() {
        StringBuilder sb = new StringBuilder();
        usuarios.values().stream()
            .sorted(Comparator.comparingInt(Jugador::getPuntos).reversed())
            .forEach(jugador -> sb.append(jugador.getUsername()).append(": ").append(jugador.getPuntos()).append(" puntos\n"));
        JOptionPane.showMessageDialog(null, sb.toString());
    }
}