package flea.market.server.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Builder
@Getter
public class RegionResponseDto {
    final Integer id;
    final String name;
}
