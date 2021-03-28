package moroz.project.train.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import moroz.project.train.interfaces.IBaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Train implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long number;

    private String description;

    private Integer seatsCount;

    @OneToMany(mappedBy = "train")
    private List<Route> routes;
}
