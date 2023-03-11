package shop.mtcoding.bank.config.jwt;

import org.junit.jupiter.api.Test;
import shop.mtcoding.bank.config.auth.LoginUser;
import shop.mtcoding.bank.domain.user.User;
import shop.mtcoding.bank.domain.user.UserEnum;

import static org.assertj.core.api.Assertions.*;

class JwtProcessTest {

    @Test
    public void create_test() throws Exception {

        // given
        User user1 = User.builder().id(1L).role(UserEnum.CUSTOMER).build();
        User user2 = User.builder().id(2L).role(UserEnum.ADMIN).build();
        LoginUser loginUser1 = new LoginUser(user1);
        LoginUser loginUser2 = new LoginUser(user2);

        // when
        String jwtToken1 = JwtProcess.create(loginUser1);
        String jwtToken2 = JwtProcess.create(loginUser2);
        System.out.println("jwtToken1 = " + jwtToken1);
        System.out.println("jwtToken2 = " + jwtToken2);

        // then
        assertThat(jwtToken1.startsWith(JwtVO.TOKEN_PREFIX)).isTrue();
        assertThat(jwtToken2.startsWith(JwtVO.TOKEN_PREFIX)).isTrue();

    }

    @Test
    public void verify_test() throws Exception {

        // given
        String jwtToken1
                = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYW5rIiwicm9sZSI6IkNVU1RPTUVSIiwiaWQiOjEsImV4cCI6MTY3OTEyMzg3OX0._Zxc9zRGjhIfo3TTrYW0HQ3NfhYWYcENmKzUOn-aJor2OOvJFyqmI4s6xRFd9mkSHb9tSU2jHYYuta_K7-mtoQ";

        String jwtToken2
                = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYW5rIiwicm9sZSI6IkFETUlOIiwiaWQiOjIsImV4cCI6MTY3OTEyNDI1Mn0.jwKuwCNVD--rHaGA5yOIgTq0hz095VsFMxoeT8_D6gufCyjzQkjzwn2dDSwPtkQvN7e-sL07GP-Vrk1Xxzgbzw";

        // when
        LoginUser loginUser1 = JwtProcess.verify(jwtToken1);
        LoginUser loginUser2 = JwtProcess.verify(jwtToken2);
        System.out.println("loginUser1.getUser().getId() = " + loginUser1.getUser().getId());
        System.out.println("loginUser2.getUser().getId() = " + loginUser2.getUser().getId());

        // then
        assertThat(loginUser1.getUser().getId()).isEqualTo(1L);
        assertThat(loginUser1.getUser().getRole()).isEqualTo(UserEnum.CUSTOMER);

        assertThat(loginUser2.getUser().getId()).isEqualTo(2L);
        assertThat(loginUser2.getUser().getRole()).isEqualTo(UserEnum.ADMIN);
    }
}