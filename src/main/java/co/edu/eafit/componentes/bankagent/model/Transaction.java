package co.edu.eafit.componentes.bankagent.model;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction {

    private Account receiver;
    private Account sender;
    private long amount;
    private Date timestamp;

}
