package shenzhen.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author demo
 */

@Data
@ToString
@NoArgsConstructor
@Entity("employees")//collection名字
@Indexes(@Index(value = "salary", fields = @Field("salary")))
public class Employee {
    @Id
    private ObjectId id;
    @Property("a")
    private String name;
    private Integer age;
    @Reference
    private Employee manager;
    @Reference
    private List<Employee> directReports = new ArrayList<>();
    @Property("wage")//key的名字
    private Double salary;



    public Employee(final String name, final Double salary) {
        this.name = name;
        this.salary = salary;
    }


}
