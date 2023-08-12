package flea.market.server.repository;

import flea.market.server.domain.region.Dongs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface DongsRepository extends JpaRepository<Dongs, Integer> {
    @Query(value = "SELECT d FROM Dongs d WHERE d.gu.si.si = :siName AND d.gu.gu = :guName AND d.dong = :dongName")
    Optional<Dongs> findDongBySiGuDong(@Param("siName") String siName, @Param("guName") String guName, @Param("dongName") String dongName);

}