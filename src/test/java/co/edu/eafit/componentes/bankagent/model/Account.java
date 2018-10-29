package co.edu.eafit.componentes.bankagent.model;

import lombok.Data;

@Data
public class Account {

    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private long balance;
    private long debt;

}
