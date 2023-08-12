package flea.market.server.controller;

import flea.market.server.dto.request.UserRequestDto;
import flea.market.server.dto.response.UserResponseDto;
import flea.market.server.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UserRequestDto userDTO) {
        userService.createUser(userDTO);
        return ResponseEntity.ok("회원가입 성공해야 돼!");
    }
}
