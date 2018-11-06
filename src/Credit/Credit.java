package Credit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Credit {
    private int id;
    private int userId;
    private long amount;
    private String state;
}
