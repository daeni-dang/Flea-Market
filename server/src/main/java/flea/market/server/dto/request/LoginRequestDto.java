package flea.market.server.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {

    private String id;
    private String pwd;

    @Builder
    public LoginRequestDto(String id, String pwd) {
        this.id = id;
        this.pwd = pwd;
    }

}