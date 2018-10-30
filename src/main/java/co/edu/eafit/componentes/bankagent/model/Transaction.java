package co.edu.eafit.componentes.bankagent.model;

import lombok.Data;

import java.util.Date;

@Data
public class Transaction {
    private int id;
    private int receiverId;
    private int senderId;
    private long amount;
    private Date timestamp;
}
