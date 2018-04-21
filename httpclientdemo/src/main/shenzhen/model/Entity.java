package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author demo
 */
@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class Entity {
    private String name;
    private String password;
}
