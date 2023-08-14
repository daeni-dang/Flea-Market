package flea.market.server.controller;

import flea.market.server.domain.region.Sis;
import flea.market.server.dto.response.RegionResponseDto;
import flea.market.server.service.RegionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/region")
public class RegionController {

    private final RegionService regionService;

    @GetMapping("/si")
    public ResponseEntity<List<Sis>> showSis() {

        return ResponseEntity.ok(regionService.getSis());
    }

    @GetMapping("/gu/{siId}")
    public ResponseEntity<List<RegionResponseDto>> showGus(@PathVariable Integer siId) {

        return ResponseEntity.ok(regionService.getGus(siId));
    }

    @GetMapping("/dong/{guId}")
    public ResponseEntity<List<RegionResponseDto>> showDongs(@PathVariable Integer guId){

        return ResponseEntity.ok(regionService.getDongs(guId));
    }

}
