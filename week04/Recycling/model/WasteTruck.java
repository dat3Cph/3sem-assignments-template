package Recycling.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class WasteTruck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="brand")
    private String brand;

    @Column(name="capacity")
    private int capacity;

    @Column(name="is_available")
    private boolean isAvailable;

    @Column(name="registration_number")
    private String registrationNumber;

    @OneToMany(mappedBy = "wasteTruck")
    private Set<Driver> drivers = new HashSet<>();


    public WasteTruck(String brand, int capacity, String registrationNumber) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registrationNumber;
    }

    public void addDriver(Driver driver){
        this.drivers.add(driver);
        if(driver != null){
            driver.addTruck(this);
        }
    }



}
