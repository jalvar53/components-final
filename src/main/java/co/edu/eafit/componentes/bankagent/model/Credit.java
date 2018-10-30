package co.edu.eafit.componentes.bankagent.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credit {
    private int id;
    private int userId;
    private long amount;
    private String state;
}
