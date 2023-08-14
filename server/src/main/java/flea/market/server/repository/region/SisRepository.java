package flea.market.server.repository.region;

import flea.market.server.domain.region.Sis;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SisRepository extends JpaRepository<Sis, Long> {
    List<Sis> findAll();
}