package flea.market.server.service;

import flea.market.server.domain.User;
import flea.market.server.domain.region.Dongs;
import flea.market.server.dto.request.UserRequestDto;
import flea.market.server.repository.region.DongsRepository;
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
    private final DongsRepository dongsRepository;
    @Transactional
    public User createUser(UserRequestDto userDTO) {
        Dongs dong = dongsRepository.findById(userDTO.getDongId()).orElseThrow(() -> new RuntimeException("동 없어용"));
        Timestamp createdAt = new Timestamp(System.currentTimeMillis());
        User newUser = User.builder()
                .id(userDTO.getId())
                .pwd(userDTO.getPwd())
                .userName(userDTO.getUserName())
                .nickName(userDTO.getNickName())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .dong(dong)
                .createdAt(createdAt)
                .build();

        return userRepository.save(newUser);
    }

    @Transactional
    public Boolean deleteUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저 없어용"));

        user.delete();

        return Boolean.TRUE;
    }
}