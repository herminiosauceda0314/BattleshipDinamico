/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package battleshipdinamico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author hermi
 */
public class Battleship {
    
    private ArrayList<Player> jugadores = new ArrayList<>();
    private Player jugadorActual = null;
    private Scanner sc = new Scanner(System.in);
    private Random rand = new Random();
    private String dificultad = "EASY";
    private String modo = "TUTORIAL";
    private String[][] board1 = new String[8][8];
    private String[][] board2 = new String[8][8];
    private HashMap<String, ArrayList<int[]>> barcos1 = new HashMap<>();
    private HashMap<String, ArrayList<int[]>> barcos2 = new HashMap<>();
    private int barcosRestantes1;
    private int barcosRestantes2;
    private Player oponente;
    
    public void menuInicial(){
        while (true) {
            System.out.println("\n--Menu de Inicio--");
            System.out.println("1. Login");
            System.out.println("2. Crear Player");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opcion: ");
            int opcion;       
            
            try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                sc.nextLine(); 
                continue;     
            }
            
            switch (opcion) {
                case 1:
                    login();
                    break;
                case 2:
                    crearPlayer();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.err.println("Opcion invalida.");
            }
        }
    }
    
    public void crearPlayer() {
        System.out.println("\nMenu Crear Player: ");
        System.out.print("Ingrese un username unico: ");
        String username = sc.nextLine();

        boolean unico = true;
        for (Player p : jugadores) {
            if (p.getUsername().equalsIgnoreCase(username)) {
                unico = false;
                break;
            }
        }

        if (!unico) {
            System.err.println("Error: El username ya existe. Intente de nuevo.");
            return; 
        }           
            System.out.print("Ingrese password: ");
            String password = sc.nextLine();
            
            Player nuevoPlayer = new Player(username, password);
            jugadores.add(nuevoPlayer);
            jugadorActual = nuevoPlayer; 
            System.out.println("\nPlayer creado exitosamente. Bienvenido, " + username + "!");
            menuPrincipal(); 
    }
    
    public void login(){
        System.out.println("\nMenu Login: ");
        System.out.print("Ingrese username: ");
        String username = sc.nextLine();
        System.out.print("Ingrese password: ");
        String password = sc.nextLine();

        boolean encontrado = false;
            for (Player p : jugadores) { 
                if (p.getUsername().equalsIgnoreCase(username) && p.getPassword().equals(password)) {
                    jugadorActual = p;
                    encontrado = true;
                    break;
                }
            }
            if (encontrado) {
                System.out.println("\nLogin exitoso. Bienvenido, " + jugadorActual.getUsername() + "!");
                menuPrincipal();
            } else {
                System.err.println("Error: Usuario o contraseña incorrectos. Intente de nuevo.");    
                }
        }
    
    public void menuPrincipal() {
        while (true) {
        System.out.println("\nPlayer: " + jugadorActual.getUsername());
        System.out.println("--Menu Principal--");
        System.out.println("1. Jugar Battleship");
        System.out.println("2. Configuracion");
        System.out.println("3. Reportes");
        System.out.println("4. Mi Perfil");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opcion: ");
        int opcion;
        
        try {
                opcion = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                sc.nextLine(); 
                continue;     
        }

        switch (opcion) {
            case 1:
                iniciarJuego();
                break;
            case 2:
                menuConfiguracion();
                break;
            case 3:
                menuReportes();
                break;
            case 4:
                menuPerfil();
                break;
            case 5:
                System.out.println("Cerrando sesion...");
                jugadorActual = null;
                menuInicial();
                return;  
            default:
                System.err.println("Opcion invalida. Intente de nuevo.");
            }
        }
    }
    
    private void menuConfiguracion() {
        while (true) {
            
        System.out.println("\nMenu Configuracion: ");
        System.out.println("a. Dificultad (Actual: " + dificultad + ")");
        System.out.println("b. Modo de Juego (Actual: " + modo + ")");
        System.out.println("c. Regresar al Menu Principal");
        System.out.print("Seleccione una opcion: ");
        String opcion = sc.nextLine().toLowerCase();
        
        if (opcion.isEmpty()) {
            System.err.println("Error: No ingresaste nada.");
            continue;
        }

        char letra = opcion.charAt(0);

        if (!Character.isLetter(letra)) {
            System.err.println("Error: '" + opcion + "' es un numero o simbolo. Solo se permiten letras.");
            continue;
        }
        
        
        switch (opcion) {
                case "a":
                    System.out.println("----------");
                    System.out.println("Opciones de Dificultad:");
                    System.out.println("1. EASY (5 barcos)");
                    System.out.println("2. NORMAL (4 barcos)");
                    System.out.println("3. EXPERT (2 barcos)");
                    System.out.println("4. GENIUS (1 barco)");
                    System.out.print("Seleccione la dificultad: ");
                    int dif;
                    
                    try {
                            dif = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                            sc.nextLine(); 
                            continue;     
                    }
                    
                    switch (dif) {
                        case 1:
                            dificultad = "EASY";
                            break;
                        case 2:
                            dificultad = "NORMAL";
                            break;
                        case 3:
                            dificultad = "EXPERT";
                            break;
                        case 4:
                            dificultad = "GENIUS";
                            break;
                        default:
                            System.err.println("Opcion invalida.");
                            break;
                        }
                    
                    System.out.println("----------");
                    System.out.println("Dificultad de juego actual: " + dificultad);
                    break;
                case "b":
                    System.out.println("----------");
                    System.out.println("Opciones de Modo:");
                    System.out.println("1. ARCADE (barcos ocultos)");
                    System.out.println("2. TUTORIAL (barcos visibles)");
                    System.out.print("Seleccione el modo: ");
                    int mod;
                    
                    try {
                            mod = sc.nextInt();
                            sc.nextLine();
                        } catch (Exception e) {
                            System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                            sc.nextLine(); 
                            continue;     
                    }
                    
                    switch (mod) {
                        case 1:
                            modo = "ARCADE";
                            break;
                        case 2:
                            modo = "TUTORIAL";
                            break;
                        default:
                            System.err.println("Opcion invalida. Se mantiene tutorial como predeterminado");
                            break;
                    }
                    
                    System.out.println("----------");
                    System.out.println("Modo de juego actual: " + modo);
                    break;
                case "c":
                    return;
                default:
                    System.err.println("Opcion invalida.");
                }
            }
    }
    
    private void menuReportes() {
        while (true) {
            System.out.println("\nMenu Reportes: ");
            System.out.println("a. Descripcion de mis ultimos 10 juegos");
            System.out.println("b. Ranking de Jugadores");
            System.out.println("c. Regresar al Menu Principal");
            System.out.print("Seleccione una opcion: ");
            String opcion = sc.nextLine().toLowerCase();
            
            if (opcion.isEmpty()) {
                System.err.println("Error: No ingresaste nada.");
                continue;
            }

            char letra = opcion.charAt(0);

            if (!Character.isLetter(letra)) {
                System.err.println("Error: '" + opcion + "' es un numero o simbolo. Solo se permiten letras.");
                continue;
            }

            switch (opcion) {
                case "a":
                    System.out.println("----------");
                    System.out.println("Ultimos 10 juegos de " + jugadorActual.getUsername() + ":");
                    jugadorActual.printLogs();
                    break;
                case "b":
                    System.out.println("----------");
                    System.out.println("Ranking de Jugadores (ordenado por puntos):");
                    jugadores.sort((p1, p2) -> Integer.compare(p2.getPuntos(), p1.getPuntos()));
                    int contador = 1;
                    for (Player p : jugadores) {
                        System.out.println(contador + ". " + p.getUsername() + " - Puntos: " + p.getPuntos());
                        contador++;
                    }
                    break;
                case "c":
                    return;
                default:
                    System.err.println("Opcion invalida.");
                }
            }
    }
    
    private void menuPerfil() {
        while (true) {
        System.out.println("\nMenu Perfil: ");
        System.out.println("a. Ver mis Datos");
        System.out.println("b. Modificar mis datos");
        System.out.println("c. Eliminar mi cuenta");
        System.out.println("d. Regresar al Menu Principal");
        System.out.print("Seleccione una opcion: ");
        String opcion = sc.nextLine().toLowerCase();
        
        if (opcion.isEmpty()) {
            System.err.println("Error: No ingresaste nada.");
            continue;
        }

        char letra = opcion.charAt(0);

        if (!Character.isLetter(letra)) {
            System.err.println("Error: '" + opcion + "' es un numero o simbolo. Solo se permiten letras.");
            continue;
        }
        
        switch (opcion) {
            case "a":
                System.out.println("----------");
                System.out.println("--- Datos de " + jugadorActual.getUsername() + " ---");
                System.out.println("Username: " + jugadorActual.getUsername());
                System.out.println("Password: " + jugadorActual.getPassword());
                System.out.println("Puntos: " + jugadorActual.getPuntos());
                
                break;
                
            case "b":
                System.out.println("----------");
                System.out.print("Nuevo username: ");
                String nuevoUsername = sc.nextLine();
                
                boolean disponible = true;
                for (Player p : jugadores){
                    if(p.getUsername().equalsIgnoreCase(nuevoUsername) && p != jugadorActual){
                        disponible = false;
                        break;
                    }
                }
                if (!disponible){
                    System.err.println("Error: El username '" + nuevoUsername + "' ya esta en uso por otro jugador. Intente de nuevo.");
                }else{
                    System.out.print("Nuevo password: ");
                    String nuevoPassword = sc.nextLine();
                    
                    jugadorActual.setUsername(nuevoUsername);
                    jugadorActual.setPassword(nuevoPassword);
                    System.out.println("Datos modificados exitosamente.");
                }
                break;
                
            case "c":
                System.out.println("----------");
                System.out.print("Seguro que desea eliminar la cuenta? (Si/No): ");
                String confirmar = sc.nextLine();
                if (confirmar.equalsIgnoreCase("Si")) {
                    jugadores.remove(jugadorActual);
                    System.out.println("Cuenta eliminada. Volviendo al menu de inicio.");
                    jugadorActual = null;
                    menuInicial();
                    
                    return;
                    
                } else if (confirmar.equalsIgnoreCase("No")) {
                    return;
                    
                }else{
                    System.err.println("Opcion invalida. Debes escribir 'Si' o 'No'.");
                }
                break;
            case "d":
                return;
            default:
                System.err.println("Opcion invalida.");
            }
        }
    }
    
    public void iniciarTablero() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board1[i][j] = "~";
                board2[i][j] = "~";
            }
        }
    }
    
    public int numeroBarcos() {     
        switch (dificultad) {
            case "EASY": 
                return 5;
            case "NORMAL": 
                return 4;
            case "EXPERT": 
                return 2;
            case "GENIUS": 
                return 1;
            default: 
                return 5;
            }
    }
    
    private void colocarBarcos(Player p, String[][] board, HashMap<String, ArrayList<int[]>> barcos, int numBarcos) {
        boolean modoEasy = dificultad.equals("EASY");
        int contadorDT = 0;

        for (int i = 0; i < numBarcos; i++) {
            while (true) {
                System.out.println("\nBarco " + (i + 1) + " de " + numBarcos);
                System.out.print("Codigo del barco (PA, AZ, SM, DT): ");
                String codigo = sc.nextLine().toUpperCase();
                
                if (!codigo.equals("PA") && !codigo.equals("AZ") && !codigo.equals("SM") && !codigo.equals("DT")) {
                System.err.println("Error: Codigo '" + codigo + "' no valido. Solo se permite: PA, AZ, SM o DT.");
                continue;
                }

                boolean barcoColocado = barcos.containsKey(codigo);
                boolean colocarBarco = false;

                if (!barcoColocado) {
                    colocarBarco = true;
                } else if (codigo.equals("DT") && modoEasy && contadorDT < 1) {
                    colocarBarco = true;
                    contadorDT++; 
                }

                if (colocarBarco) {
                    System.out.print("Fila (0-7): ");
                    int fila;
                    
                    try {
                        fila = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                        sc.nextLine();
                        continue;     
                    }
                    
                    System.out.print("Columna (0-7): ");
                    int col;
                    
                    try {
                        col = sc.nextInt();
                        sc.nextLine();
                    } catch (Exception e) {
                        System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                        sc.nextLine();
                        continue;     
                    }
                    
                    int tamaño;
                        switch (codigo) {
                            case "PA":
                                tamaño = 5;
                                break;
                            case "AZ":
                                tamaño = 4;
                                break;
                            case "SM":
                                tamaño = 3;
                                break;
                            default:
                                //el DT
                                tamaño = 2;
                                break;
                        }
                        
                    if (fila < 0 || fila > 7 || col < 0 || (col + tamaño) > 8) {
                        System.err.println("Error: El barco se sale de los limites.");
                        continue;
                    }

                    boolean overlap = false;
                    for (int j = 0; j < tamaño; j++) {
                        if (!board[fila][col + j].equals("~")) {
                            overlap = true;
                            break;
                        }
                    }

                    if (overlap) {
                        System.err.println("Error: Ya hay un barco en esa posicion.");
                        continue;
                    }

                    ArrayList<int[]> posiciones = new ArrayList<>();
                    for (int j = 0; j < tamaño; j++) {
                        board[fila][col + j] = codigo;
                        posiciones.add(new int[]{fila, col + j});
                    }

                    String llaveMapa;
                        if (barcoColocado) {
                            llaveMapa = codigo + "2";
                        } else {
                            llaveMapa = codigo;
                        }
                        barcos.put(llaveMapa, posiciones);     
                    break;
                } else {
                    System.err.println("Error: Este tipo de barco ya fue colocado o no se permite repetir.");
                }
            }
        }
    }
    
    private void mostrarTablero(String[][] board, Player p) {
        System.out.println("\n--- TABLERO DE " + p.getUsername() + " ---");
        System.out.println("   0  1  2  3  4  5  6  7"); 
        for (int i = 0; i < 8; i++) {
            System.out.print(i + "  "); 
            for (int j = 0; j < 8; j++) {
                String celda = board[i][j];

                if (modo.equals("TUTORIAL")) {
                    System.out.print(celda + "  ");
                } else {
                    if (celda.equals("X") || celda.equals("F")) {
                        System.out.print(celda + "  "); 
                    } else {
                        System.out.print("~" + "  "); 
                    }
                }
            }
            System.out.println();
        }
    }
    
    private boolean procesarBomba(int fila, int col, String[][] board, HashMap<String, ArrayList<int[]>> barcos, Player defensor, boolean esTurnoP1) {
        if (board[fila][col].equals("~")) {
            board[fila][col] = "F";
            System.out.println("Fallo!");
            return false; 
            
        } else if (board[fila][col].equals("F") || board[fila][col].equals("X")) {
            System.err.println("Error: Ya bombardeaste aqui anteriormente.");
            return false;
            
        } else {
            String codigo = board[fila][col];
            board[fila][col] = "X";
            System.out.println("Impacto en un barco tipo: " + codigo + "!");

            boolean barcoHundido = true;
            for (int[] pos : barcos.get(codigo)) {
                if (!board[pos[0]][pos[1]].equals("X")) {
                    barcoHundido = false;
                    break;
                }
            }

            if (barcoHundido) {
                System.out.println("El barco " + codigo + " se ha hundido!");
                if (esTurnoP1) {
                    barcosRestantes2--; 
                } else {
                    barcosRestantes1--;
                }
            }

            System.out.println("El tablero de " + defensor.getUsername() + " se esta regenerando...");
            regenerarTablero(defensor, board, barcos); 

            return true; 
        }
    }
    
    private boolean confirmarRetiro(Player atacante, Player defensor) {
        System.out.print("Seguro que desea retirarse, " + atacante.getUsername() + " ? (Si/No): ");
        String opcion = sc.nextLine().trim(); 

        if (opcion.equalsIgnoreCase("Si")) {
            System.out.println("\n" + atacante.getUsername() + " se ha retirado.");
            System.out.println("El ganador es " + defensor.getUsername() + " por retirarse!");
            defensor.setPuntos(defensor.getPuntos() + 3);
            
            atacante.addLog(atacante.getUsername() + " se retiro del juego dejando de ganador a " + defensor.getUsername());
            defensor.addLog(defensor.getUsername() + " gano, su oponente " + atacante.getUsername() + " se retiro (+3 pts)");
            
            return true;          
        } else if (opcion.equalsIgnoreCase("No")) {
            System.out.println("Continuamos la batalla!");
            
            return false;
        } else {
            System.err.println("Opcion invalida. Debes escribir 'Si' o 'No'.");
            return false; 
        }
    }
    
    private void regenerarTablero(Player p, String[][] board, HashMap<String, ArrayList<int[]>> barcos) {
        ArrayList<int[]> barcosImpactados = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (board[i][j].equals("X")) {
                    barcosImpactados.add(new int[]{i, j});
                }
            }
        }

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                board[i][j] = "~";
            }
        }

        for (String codigoBarco : barcos.keySet()) {
            ArrayList<int[]> posicionesViejas = barcos.get(codigoBarco);

            int partesVivas = 0;
            for (int[] posVieja : posicionesViejas) {
                boolean golpeado = false;
                for (int[] imp : barcosImpactados) {
                    if (imp[0] == posVieja[0] && imp[1] == posVieja[1]) {
                        golpeado = true;
                        break;
                    }
                }
                if (!golpeado) {
                    partesVivas++;
                }
            }

            if (partesVivas == 0) 
                continue;

            boolean colocado = false;
            while (!colocado) {
                int fila = rand.nextInt(8);
                int col = rand.nextInt(8 - partesVivas + 1);

                boolean choque = false;
                for (int j = 0; j < partesVivas; j++) {
                    if (!board[fila][col + j].equals("~")) {
                        choque = true;
                        break;
                    }
                    
                    for (int[] imp : barcosImpactados){
                        if(imp[0] == fila && imp[1] == (col + j)){
                            choque = true;
                            break;
                        }
                    }
                    if (choque)
                        break;
                }

                if (!choque) {
                    ArrayList<int[]> posicionesNuevas = new ArrayList<>();

                    String nombreVisual = codigoBarco.startsWith("DT") ? "DT" : codigoBarco;

                    for (int j = 0; j < partesVivas; j++) {
                        board[fila][col + j] = nombreVisual;
                        posicionesNuevas.add(new int[]{fila, col + j});
                    }

                    barcos.put(codigoBarco, posicionesNuevas);
                    colocado = true;
                }
            }
        }

        for (int[] pos : barcosImpactados) {
            board[pos[0]][pos[1]] = "X";
        }
    }
    
    public void iniciarJuego() {
        System.out.println("Ingrese username del Player 2 (o EXIT para cancelar): ");
        String user2 = sc.nextLine();
        if (user2.equalsIgnoreCase("EXIT")) return;

        oponente = null;
        for (Player p : jugadores) {
            if (p.getUsername().equalsIgnoreCase(user2) && !p.equals(jugadorActual)) {
                oponente = p;
                break;
            }
        }

        if (oponente == null) {
            System.err.println("Error: Usuario no encontrado o es el mismo Player 1.");
            return;
        }

        barcos1.clear();
        barcos2.clear();
        iniciarTablero();
        int numBarcos = numeroBarcos();
        barcosRestantes1 = numBarcos;
        barcosRestantes2 = numBarcos;

        System.out.println("\n--- " + jugadorActual.getUsername() + " coloca sus barcos ---");
        colocarBarcos(jugadorActual, board1, barcos1, numBarcos);

        System.out.println("\n--- " + oponente.getUsername() + " coloca sus barcos ---");
        colocarBarcos(oponente, board2, barcos2, numBarcos);

        boolean turnoP1 = true;
        while (barcosRestantes1 > 0 && barcosRestantes2 > 0) {
            Player atacante = turnoP1 ? jugadorActual : oponente;
            Player defensor = turnoP1 ? oponente : jugadorActual;
            String[][] tableroDefensor = turnoP1 ? board2 : board1;
            HashMap<String, ArrayList<int[]>> barcosDefensor = turnoP1 ? barcos2 : barcos1;

            System.out.println("\n========================================");
            System.out.println("Turno de: " + atacante.getUsername());
            System.out.println("--- " + defensor.getUsername() + " tiene " + (turnoP1 ? barcosRestantes2 : barcosRestantes1) + " barcos vivos ---");

            mostrarTablero(tableroDefensor, defensor);

            System.out.println("Coordenadas de bomba (-1 para retirarse):");
            System.out.print("Fila (0-7): ");
            
            int fila;
            
            try {
                fila = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                sc.nextLine();
                continue;     
            }

            if (fila == -1) {
                if (confirmarRetiro(atacante, defensor)) return;
                else continue; 
            }

            System.out.print("Columna (0-7): ");
            int col;
            
            try {
                col = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.err.println("Error: Debes ingresar un numero, no letras o simbolos.");
                sc.nextLine();
                continue;     
            }

            if (col == -1) {
                if (confirmarRetiro(atacante, defensor)) return;
                else continue;
            }

            if (fila < 0 || fila > 7 || col < 0 || col > 7) {
                System.err.println("Coordenadas fuera de rango! Pierde el turno.");
                turnoP1 = !turnoP1;
                continue;
            }

            procesarBomba(fila, col, tableroDefensor, barcosDefensor, defensor, turnoP1);

            turnoP1 = !turnoP1;
        }

        Player ganador = (barcosRestantes1 == 0) ? oponente : jugadorActual;
        Player perdedor = (ganador == jugadorActual) ? oponente : jugadorActual;

        System.out.println("\nFelicidades " + ganador.getUsername() + "!");
        System.out.println(ganador.getUsername() + " hundido todos los barcos de " + perdedor.getUsername());
        ganador.setPuntos(ganador.getPuntos() + 3); 

        String finalLog = ganador.getUsername() + " hundido todos los barcos de " + perdedor.getUsername() + " en modo " + dificultad;
        ganador.addLog(finalLog);
        perdedor.addLog(perdedor.getUsername() + " perdio contra " + ganador.getUsername() + " en modo " + dificultad);
        perdedor.addLog(finalLog);
        
        menuPrincipal();
    }
}
