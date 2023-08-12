package flea.market.server.domain.region;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "GUS")
@DynamicUpdate
public class Gus {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "si_id", nullable = false)
    private Sis si;

    @Column(name = "gu", nullable = false, length = 100)
    private String gu;

}
