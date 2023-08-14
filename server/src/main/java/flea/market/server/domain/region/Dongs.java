package flea.market.server.domain.region;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "DONGS")
@DynamicUpdate
public class Dongs {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "gu_id", nullable = false)
    private Gus gu;

    @Column(name = "dong", nullable = false, length = 100)
    private String dong;

}
