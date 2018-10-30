package co.edu.eafit.componentes.bankagent.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account {
    private int id;
    private String username;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private long balance;
    private long debt;
}
