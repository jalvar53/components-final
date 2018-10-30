package co.edu.eafit.componentes.bankagent.CLI;

import java.io.IOException;

public class Utils {

    public static void showMenu() {
        try {
            clearScreen();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Bakagents");
    }

    private static void clearScreen() throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    }
}
