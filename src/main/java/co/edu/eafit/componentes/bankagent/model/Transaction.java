package co.edu.eafit.componentes.bankagent.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Transaction {
    private int id;
    private int receiverId;
    private int senderId;
    private long amount;
    private Date timestamp;
}
