package shenzhen.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author demo
 */

@Data
@Entity(value = "employees" ,noClassnameStored = true)//collection名字
//@Indexes(@Index(value = "salary", fields = @Field("salary")))
public class Employee implements Serializable {
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
    @Property
   private BigDecimal money;


//
//    public Employee(final String name, final Double salary) {
//        this.name = name;
//        this.salary = salary;
//    }

    public static void main(String[] args) {
    }

}
