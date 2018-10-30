package co.edu.eafit.componentes.bankagent.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Date;

@Data
@AllArgsConstructor
public class Payment {
    private int id;
    private int payerId;
    private long amount;
    private int creditId;
    private Date timestamp;
}
