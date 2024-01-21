package pl.uwb.f4group.citybikerent.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bike")
public class Bike {

    @Id
    private Long id;
    private Long number;
    private String brand;
    private boolean available;
}