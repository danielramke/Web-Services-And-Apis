package de.lyth.vehicleapi.model.car;

import de.lyth.vehicleapi.model.Condition;
import de.lyth.vehicleapi.model.Location;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * This class stored all the information for a car.
 */
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Condition condition;

    @Valid
    @Embedded
    private CarDetails details = new CarDetails();

    @Valid
    @Embedded
    private Location location = new Location(0D, 0D);

    @Transient
    private String price;
}
