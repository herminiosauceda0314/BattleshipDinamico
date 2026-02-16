/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipdinamico;

/**
 *
 * @author hermi
 */

public class Player {
    
    private String username;
    private String password;
    private int puntos;
    private String[] logs = new String[10];
    

    public Player(String username, String password) {
        this.username = username;
        this.password = password;
        this.puntos = 0;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public String[] getLogs() {
        return logs;
    }

    public void addLog(String log) {
        for (int i = logs.length - 1; i > 0; i--) {
            logs[i] = logs[i - 1];
        }
        logs[0] = log;
    }

    public void printLogs() {
        int contador = 1;
        boolean tieneLogs = false;

        for (int i = 0; i < 10; i++) {
            if (logs[i] != null) {
                System.out.println(contador + ". " + logs[i]);
                contador++;
                tieneLogs = true;
            }
        }

        if (!tieneLogs) {
            System.out.println("No hay juegos registrados aun.");
        }
    }
}
