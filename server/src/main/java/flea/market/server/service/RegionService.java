package flea.market.server.service;

import flea.market.server.domain.region.Dongs;
import flea.market.server.domain.region.Gus;
import flea.market.server.domain.region.Sis;
import flea.market.server.dto.response.RegionResponseDto;
import flea.market.server.repository.region.DongsRepository;
import flea.market.server.repository.region.GusRepository;
import flea.market.server.repository.region.SisRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class RegionService {

    private final SisRepository sisRepository;
    private final GusRepository gusRepository;
    private final DongsRepository dongsRepository;

    public List<RegionResponseDto> readSiList() {
        List<Sis> tmpList = sisRepository.findAll();
        List<RegionResponseDto> siResult = new ArrayList<>();

        for (Sis gu : tmpList) {
            RegionResponseDto regionResponseDto = RegionResponseDto.builder()
                    .id(gu.getId())
                    .name(gu.getSi())
                    .build();
            siResult.add(regionResponseDto);
        }

        return siResult;
    }

    public List<RegionResponseDto> readGuList(Integer siId) {

        List<Gus> tmpList = gusRepository.findGuBySi_Id(siId);
        List<RegionResponseDto> guResult = new ArrayList<>();

        for (Gus gu : tmpList) {
            RegionResponseDto regionResponseDto = RegionResponseDto.builder()
                    .id(gu.getId())
                    .name(gu.getGu())
                    .build();
            guResult.add(regionResponseDto);
        }

        return guResult;
    }

    public List<RegionResponseDto> readDongList(Integer guId) {
        List<Dongs> tmpList = dongsRepository.findDongsByGu_Id(guId);
        List<RegionResponseDto> dongResult = new ArrayList<>();

        for (Dongs dong : tmpList) {
            RegionResponseDto regionResponseDto = RegionResponseDto.builder()
                    .id(dong.getId())
                    .name(dong.getDong())
                    .build();
            dongResult.add(regionResponseDto);
        }

        return dongResult;
    }
}
