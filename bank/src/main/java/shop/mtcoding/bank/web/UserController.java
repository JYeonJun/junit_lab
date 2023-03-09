package shop.mtcoding.bank.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.bank.dto.ResponseDto;
import shop.mtcoding.bank.service.UserService;

import javax.validation.Valid;

import static shop.mtcoding.bank.dto.user.UserReqDto.*;
import static shop.mtcoding.bank.dto.user.UserRespDto.*;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // /api/s/** 는 인증이 필요한 url 접근이다.
    // 회원가입에서는 인증이 필요하면 안 되므로 /api/join으로 url을 설정하도록 한다.
    @PostMapping("/join")
    public ResponseEntity<?> join(@RequestBody @Valid JoinReqDto joinReqDto, BindingResult bindingResult) {

        JoinRespDto joinRespDto = userService.회원가입(joinReqDto);

        return new ResponseEntity<>(new ResponseDto<>(1, "회원가입 성공", joinRespDto), HttpStatus.CREATED);
    }
}
