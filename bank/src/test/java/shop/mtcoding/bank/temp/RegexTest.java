package shop.mtcoding.bank.temp;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.assertj.core.api.Assertions.*;

public class RegexTest {

    @Test
    public void 한글만된다_test() throws Exception {

        String value1 = "가ㄷ나";
        String value2 = "aaaaaaaabc";
        String value3 = "";

        boolean result1 = Pattern.matches("^[ㄱ-ㅎ가-힣]+$", value1);
        boolean result2 = Pattern.matches("^[ㄱ-ㅎ가-힣]+$", value2);
        boolean result3 = Pattern.matches("^[ㄱ-ㅎ가-힣]+$", value3);

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
    }

    @Test
    public void 한글은안된다_test() throws Exception {

        String value1 = "aaaaaaaab!@c";
        String value2 = "가ㄴ다";
        String value3 = "";

        boolean result1 = Pattern.matches("^[^ㄱ-ㅎ가-힣]*$", value1);
        boolean result2 = Pattern.matches("^[^ㄱ-ㅎ가-힣]*$", value2);
        boolean result3 = Pattern.matches("^[^ㄱ-ㅎ가-힣]*$", value3);

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isTrue();
    }

    @Test
    public void 영어만된다_test() throws Exception {

        String value1 = "dsffoijDDSJIAOdjiso";
        String value2 = "abc!@#";
        String value3 = "가ㄴ다";
        String value4 = "";

        boolean result1 = Pattern.matches("^[a-zA-Z]+$", value1);
        boolean result2 = Pattern.matches("^[a-zA-Z]+$", value2);
        boolean result3 = Pattern.matches("^[a-zA-Z]+$", value3);
        boolean result4 = Pattern.matches("^[a-zA-Z]+$", value4);

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result4).isFalse();
    }

    @Test
    public void 영어는안된다_test() throws Exception {

        String value1 = "abc";
        String value2 = "abc!@";
        String value3 = "가ㄴ다!@";
        String value4 = "";

        boolean result1 = Pattern.matches("^[^a-zA-Z]*$", value1);
        boolean result2 = Pattern.matches("^[^a-zA-Z]*$", value2);
        boolean result3 = Pattern.matches("^[^a-zA-Z]*$", value3);
        boolean result4 = Pattern.matches("^[^a-zA-Z]*$", value4);

        assertThat(result1).isFalse();
        assertThat(result2).isFalse();
        assertThat(result3).isTrue();
        assertThat(result4).isTrue();
    }

    @Test
    public void 영어와숫자만된다_test() throws Exception {

        String value1 = "dsffoijDDSJIAOdjiso1432";
        String value2 = "가ㄴ다12";
        String value3 = "dsffoijDDSJIAOdjiso1432!@#";
        String value4 = "";
        boolean result1 = Pattern.matches("^[a-zA-Z0-9]+$", value1);
        boolean result2 = Pattern.matches("^[a-zA-Z0-9]+$", value2);
        boolean result3 = Pattern.matches("^[a-zA-Z0-9]+$", value3);
        boolean result4 = Pattern.matches("^[a-zA-Z0-9]+$", value4);

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result3).isFalse();
    }

    @Test
    public void 영어만되고_길이는최소2최대4이다_test() throws Exception {

        String value1 = "abc";
        String value2 = "abc나";
        String value3 = "abc123";
        String value4 = "abc123!@";

        boolean result1 = Pattern.matches("^[a-zA-Z]{2,4}$", value1);
        boolean result2 = Pattern.matches("^[a-zA-Z]{2,4}$", value2);
        boolean result3 = Pattern.matches("^[a-zA-Z]{2,4}$", value3);
        boolean result4 = Pattern.matches("^[a-zA-Z]{2,4}$", value4);

        assertThat(result1).isTrue();
        assertThat(result2).isFalse();
        assertThat(result3).isFalse();
        assertThat(result4).isFalse();
    }

    @Test
    public void user_username_test() throws Exception {

        String username = "ssar";
        boolean result = Pattern.matches("^[a-zA-Z]{2,4}$", username);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void user_fullname_test() throws Exception {

        String username = "엑스k";
        boolean result = Pattern.matches("^[ㄱ-ㅎ기-힣a-zA-Z]{1,20}$", username);
        System.out.println("테스트 : " + result);
    }

    @Test
    public void user_email_test() throws Exception {

        String email = "ssar@nate.com";
        boolean result = Pattern.matches("^[a-zA-Z0-9]{2,7}@[a-zA-Z가-힣0-9]{2,6}\\.[a-zA-Z]{2,3}$", email);
        System.out.println("테스트 : " + result);
    }
}
