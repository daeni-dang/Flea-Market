package flea.market.server.service;

import flea.market.server.domain.User;
import flea.market.server.dto.request.LoginRequestDto;
import flea.market.server.dto.request.UserRequestDto;
import flea.market.server.dto.response.LoginResponseDto;
import flea.market.server.exception.LoginFailureException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired UserService userService;

    @Test
    void signup() {
        //given
        UserRequestDto dto = new UserRequestDto(
                "abcd",
                "1234",
                "daeni",
                "daeni",
                "010-1234-1234",
                "abcd@test.com",
                50
        );

        //when
        User user = userService.createUser(dto);

        //then
        Assertions.assertThat(user.getId()).isEqualTo("abcd");
    }

    @Test
    void loginSuccess() {
        //given
        //회원가입 먼저
        UserRequestDto dto = new UserRequestDto(
                "abcd",
                "1234",
                "daeni",
                "daeni",
                "010-1234-1234",
                "abcd@test.com",
                50
        );
        userService.createUser(dto);

        //로그인
        LoginRequestDto loginDto = new LoginRequestDto(
                "abcd",
                "1234"
        );

        //when
        LoginResponseDto loginResponseDto = userService.loginUser(loginDto);

        //then
        Assertions.assertThat(loginResponseDto.getId()).isEqualTo("abcd");
    }

    @Test
    void loginFailure() {
        //given
        LoginRequestDto loginDto = new LoginRequestDto(
                "abcd",
                "1234"
        );

        //when //then
        assertThrows(LoginFailureException.class, () -> {
            userService.loginUser(loginDto);
        });

    }

}