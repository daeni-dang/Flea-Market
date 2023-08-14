package flea.market.server.controller;

import flea.market.server.domain.User;
import flea.market.server.dto.request.UserRequestDto;
import flea.market.server.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRequestDto userDTO) {
        //반환값을 바꿔줄 필요 있음
        User user = userService.createUser(userDTO);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
