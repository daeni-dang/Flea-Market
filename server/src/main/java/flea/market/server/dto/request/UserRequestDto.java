package flea.market.server.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserRequestDto {
    private String id;
    private String pwd;
    private String userName;
    private String nickName;
    private String phone;
    private String email;
    private String dong;
    private String si;
    private String gu;

}
