package flea.market.server.dto.response;

import flea.market.server.domain.region.Dongs;
import lombok.Builder;
import lombok.NoArgsConstructor;


@NoArgsConstructor
public class UserResponseDto {
    private String nickName ;
    private Integer restrictNum;
    private String Phone;

}
