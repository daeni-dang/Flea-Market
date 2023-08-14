package flea.market.server.repository.region;

import flea.market.server.domain.region.Gus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GusRepository extends JpaRepository<Gus, Long> {

    List<Gus> findGuBySi_Id(Integer siId);
}
