package Recycling.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;
@NamedQueries({
        @NamedQuery(name="Driver.findAllDriversEmployedAtTheSameYear", query = "select d from Driver d where substring(cast(d.employmentDate as string),1,4) = :value"),
        @NamedQuery(name="Driver.findHighestSalary", query = "select max(d.salary) from Driver d"),
        @NamedQuery(name="Driver.findFirstNameOfDrivers", query = "select d.name from Driver d"),
        @NamedQuery(name="Driver.driverCount", query = "select count(d) from Driver d"),
        @NamedQuery(name="Driver.getDriverWithHighestSalary", query = "select d from Driver d group by d order by max(d.salary) desc")
})
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

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private WasteTruck wasteTruck;

    public Driver(String name, BigDecimal salary, String surname) {
        this.name = name;
        this.salary = salary;
        this.surname = surname;
    }


    @PrePersist
    private void generateIdAndDayOfEmployment() throws Exception{
        Random random = new Random();
        this.employmentDate = Date.valueOf(LocalDate.now());

        String[] date = this.employmentDate.toString().split("-",3);
        String year = date[0].substring(2);

        StringBuilder result = new StringBuilder();
        result.append(date[1])
                .append(date[2])
                .append(year)
                .append("-")
                .append(this.name.charAt(0))
                .append(this.surname.charAt(0))
                .append("-")
                .append(random.nextInt(100,999))
                .append(this.surname.substring(this.surname.length()-1).toUpperCase());

        if(validateDriverId(result.toString())){
            this.id = result.toString();
        }else {
            throw new Exception("Id not valid");
        }

    }


    public void addTruck(WasteTruck wasteTruck){
        this.wasteTruck = wasteTruck;
    }

    public static Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }
}
