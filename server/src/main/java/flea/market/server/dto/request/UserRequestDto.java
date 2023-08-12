package flea.market.server.dto.request;

import flea.market.server.domain.region.Dongs;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
    private String id;
    private String pwd;
    private String userName;
    private String nickName;
    private String phone;
    private String email;
    private Dongs dong;

    @Builder
    public UserRequestDto(String id, String pwd, String userName, String nickName, String phone, String email, Dongs dong) {
        this.id = id;
        this.pwd = pwd;
        this.userName = userName;
        this.nickName = nickName;
        this.phone = phone;
        this.email = email;
        this.dong = dong;
    }
}
