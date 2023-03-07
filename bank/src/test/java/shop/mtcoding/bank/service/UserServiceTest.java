package shop.mtcoding.bank.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;
import shop.mtcoding.bank.domain.user.UserRepository;
import shop.mtcoding.bank.handler.ex.CustomApiException;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static shop.mtcoding.bank.service.UserService.*;

@ExtendWith(MockitoExtension.class) // 스프링 관련 Bean들이 하나도 없는 환경
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    // Bean을 직접 등록해줘야 함.

    // Service를 테스트하는 것이므로 Repository를 가짜로 생성한다.
    // @InjectMocks 애노테이션을 가진 UserService에 가짜 UserRepository를 주입해준다.
    @Mock
    private UserRepository userRepository;

    // 스프링의 진짜 BCryptPasswordEncoder를 UserService에 넣는다.
    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void 회원가입_test() throws Exception {

        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("ssar");
        joinReqDto.setEmail("ssar@nate.com");
        joinReqDto.setFullname("쌀");

        // stub 1: 가설
        // 가짜 UserRepository에는 메서드가 없으므로 stub을 생성해줘야 한다.
        when(userRepository.findByUsername(any())).thenReturn(Optional.empty());

        // stub 2
        User ssar = User.builder()
                .id(1L)
                .username("ssar")
                .password("1234")
                .email("ssar@nate.com")
                .fullname("쌀")
                .role(UserEnum.CUSTOMER)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        when(userRepository.save(any())).thenReturn(ssar);

        // when
        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);

        // then
        assertThat(joinRespDto.getId()).isEqualTo(1L);
        assertThat(joinRespDto.getUsername()).isEqualTo("ssar");
    }

    @Test
    public void 회원가입_예외_test() throws Exception {

        // given
        JoinReqDto joinReqDto = new JoinReqDto();
        joinReqDto.setUsername("ssar");
        joinReqDto.setPassword("ssar");
        joinReqDto.setEmail("ssar@nate.com");
        joinReqDto.setFullname("쌀");

        // stub 1: 가설
        // 가짜 UserRepository에는 메서드가 없으므로 stub을 생성해줘야 한다.
        User user = User.builder().build();
        when(userRepository.findByUsername(any())).thenReturn(Optional.of(user));

        // then
        assertThrows(CustomApiException.class, () -> {
            userService.회원가입(joinReqDto);
        });
    }
}
