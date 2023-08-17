package flea.market.server.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginResponseDto {

    private String id;
    private String token;

    @Builder
    public LoginResponseDto(String id, String token) {
        this.id = id;
        this.token = token;
    }

}