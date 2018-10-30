package co.edu.eafit.componentes.bankagent;

import co.edu.eafit.componentes.bankagent.CLI.Utils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankagentApplication {

    public static void main(String[] args) {
        SpringApplication.run(BankagentApplication.class, args);
        Utils.showMenu();
    }
}

