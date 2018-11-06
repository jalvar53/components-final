package main.java.co.edu.eafit.componentes.bankagent.Account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Account {
    private int id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private long balance;
    private long debt;
}
