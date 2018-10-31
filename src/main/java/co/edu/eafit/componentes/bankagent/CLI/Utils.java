package co.edu.eafit.componentes.bankagent.CLI;

public class Utils {

    static void clearScreen() {
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
    }

    static void insertLogo() {
        System.out.println(" ____              _        _                    _");
        System.out.println("| __ )  __ _ _ __ | | __   / \\   __ _  ___ _ __ | |_ ___");
        System.out.println("|  _ \\ / _` | '_ \\| |/ /  / _ \\ / _` |/ _ \\ '_ \\| __/ __|");
        System.out.println("| |_) | (_| | | | |   <  / ___ \\ (_| |  __/ | | | |_\\__ \\");
        System.out.println("|____/ \\__,_|_| |_|_|\\_\\/_/   \\_\\__, |\\___|_| |_|\\__|___/");
        System.out.println("                                |___/");
    }

    static void insertDivision() {
        System.out.println("---------------------");
    }

    static void waitInput() {
        System.out.println();
        System.out.println("Press enter to continue...");
        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
