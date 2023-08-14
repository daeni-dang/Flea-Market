package flea.market.server.repository.region;

import flea.market.server.domain.region.Dongs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DongsRepository extends JpaRepository<Dongs, Integer> {
//  시, 구, 동의 이름으로 동 반환
    @Query(value = "SELECT d FROM Dongs d WHERE d.gu.si.si = :siName AND d.gu.gu = :guName AND d.dong = :dongName")
    Optional<Dongs> findDongBySiGuDong(@Param("siName") String siName, @Param("guName") String guName, @Param("dongName") String dongName);

    List<Dongs> findDongsByGu_Id(Integer guId);
}