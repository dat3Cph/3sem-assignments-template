package Recycling.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Driver {

    @Id
    @Column(name="id")
    private String id;

    @Column(name="employment_date")
    @Temporal(TemporalType.DATE)
    private Date employmentDate;

    @Column(name="name")
    private String name;

    @Column(name="salary")
    private BigDecimal salary;

    @Column(name="surname")
    private String surname;

    @Column(name="truck_id")
    @ManyToOne
    private WasteTruck wasteTruck;

    public Driver(String name, BigDecimal salary, String surname) {
        this.name = name;
        this.salary = salary;
        this.surname = surname;
    }


    @PrePersist
    private void generateIdAndDayOfEmployment(){
        Random random = new Random();
        this.employmentDate = Date.valueOf(LocalDate.now());

        String[] date = this.employmentDate.toString().split("-",3);
        String year = date[0].substring(2);

        StringBuilder result = new StringBuilder();
        result.append(year)
                .append(date[1])
                .append(date[2])
                .append("-")
                .append(this.name.charAt(0))
                .append(this.surname.charAt(0))
                .append("-")
                .append(random.nextInt(100,999))
                .append(this.surname.substring(this.surname.length()-1).toUpperCase());

        if(validateDriverId(result.toString())){
            this.id = result.toString();
        }

    }


    public static Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }
}
