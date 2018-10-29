package co.edu.eafit.componentes.bankagent.model;

import lombok.Data;

@Data
public class Credit {

    private long amount;
    private String state;
    private Account userAccount;

}
