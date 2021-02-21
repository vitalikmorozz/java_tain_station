package moroz.project.train.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class Train {
    @Id
    @GeneratedValue
    private Long id;

    private Long number;

    private String description;

    private Integer seatsCount;

    @OneToMany(mappedBy = "train")
    private List<Route> routes;
}
