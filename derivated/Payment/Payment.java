package main.java.co.edu.eafit.componentes.bankagent.Payment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import java.sql.Date;

@Data
@AllArgsConstructor
@ToString
public class Payment {
    private int id;
    private int payerId;
    private long amount;
    private Date timestamp;
}
