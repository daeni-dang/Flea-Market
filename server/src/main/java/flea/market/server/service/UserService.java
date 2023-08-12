package flea.market.server.service;

import flea.market.server.domain.User;
import flea.market.server.dto.request.UserRequestDto;
import flea.market.server.dto.response.UserResponseDto;
import flea.market.server.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserRequestDto userDTO) {
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        User newUser = User.builder()
                .id(userDTO.getId())
                .pwd(userDTO.getPwd())
                .userName(userDTO.getUserName())
                .nickName(userDTO.getNickName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .dong(userDTO.getDong())
                .createdAt(createdAt)
                .build();

        return userRepository.save(newUser);
    }
//    public UserResponseDto readUserProfile(Long userId) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));
//
//        return UserResponseDto.builder()
//                .id(user.getId())
//                .nickName(user.getNickName())
//                .restrictNum(user.getRestrictNum())
//                .build();
//    }

//    @Transactional
//    public UserResponseDto updateUserProfile(Long userId, String name) {
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));
//
//
//        return UserResponseDto.builder()
//                .id(user.getId())
//                .nickName(user.getNickName())
//                .restrictNum(user.getRestrictNum())
//                .build();
//    }

    @Transactional
    public Boolean deleteUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        user.delete();

        return Boolean.TRUE;
    }
}