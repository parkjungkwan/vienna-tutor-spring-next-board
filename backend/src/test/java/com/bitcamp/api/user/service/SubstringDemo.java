package com.bitcamp.api.user.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.startsWith;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
public class SubstringDemo {

    @Test
    public void 문자열_분할() throws Exception {

        String str = "a,b,c";

        System.out.print("str : " + str);
        // a,b,c,d,e,f
        String[] arr = null;
        assertThat(arr.length).isEqualTo(6);
    }

    @Test
    public void 주민번호로_성별_구분() throws Exception {
        String human1 = "970301-1";
        String human2 = "950101-2";
        String human3 = "020101-3";
        String human4 = "050101-4";
        // 외국인
        String human5 = "730101-5";
        String human6 = "820101-6";
        String human7 = "120101-7";
        String human8 = "050101-8";

        assertThat(getGender(human1)).isEqualTo("M");

        // a,b,c,d,e,f
        String[] arr = null;
        // 주민번호를 통해서 나이와 성별(gender)를 출력하시오. 단 나이는 만나이로 표기하시오.

    }

    private String getGender(String ssn) {
        // M : 남성, F : 여성
        return switch (ssn.charAt(7)) {
            case '1', '3', '5' -> "M";
            case '2', '4', '6' -> "F";
            default -> "Error";
        };

    }

    @Test
    public void now() {
        LocalDate now = LocalDate.now();
        int year = now.getYear();
        assertThat(year).isEqualTo(2024);
        int month = now.getMonthValue();
        assertThat(month).isEqualTo(4);
        int day = now.getDayOfMonth();
        assertThat(day).isEqualTo(24);

    }

    @Test
    public void birthYear() {
        String ssn = "970301-1";
        int birthYear = Integer.parseInt(ssn.substring(0, 2));

        assertThat(birthYear).isEqualTo(1997);

        String ssn2 = "020101-4";
        int birthYear2 = Integer.parseInt(ssn2.substring(0, 2));

        assertThat(birthYear2).isEqualTo(2002);

    }

    @Test
    public void getAge() {

        LocalDate now = LocalDate.now();
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();

        String ssn = "920425-1";
        int birthYear = Integer.parseInt(ssn.substring(0, 2));
        birthYear = switch (ssn.charAt(7)) {
            case '1', '2', '5', '6' -> birthYear + 1900;
            case '3', '4', '7', '8' -> birthYear + 2000;
            default -> birthYear + 1800;
        };
        int age = year - birthYear;
        int birthMonth = Integer.parseInt(ssn.substring(2, 4));
        int birthDay = Integer.parseInt(ssn.substring(4, 6));
        assertThat(birthMonth - month).isEqualTo(0);

        if (birthMonth > month) {
            age=age-1;

        } else if (birthMonth == month) {

            if (birthDay > day) {
                age--;
            }

        }

        assertThat(age).isEqualTo(31);
    }

    @Test
    public void getAgeUsingLambda() {
        String ssn2 = "920425-1";
        int fullYear = Integer.parseInt(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")));
        int age = Stream.of(ssn2)
                .map(ssn -> Integer.parseInt(ssn.substring(0, 2)))
                .map(birthYear -> switch (ssn2.charAt(7)) {
                    case '1', '2', '5', '6' -> birthYear + 1900;
                    case '3', '4', '7', '8' -> birthYear + 2000;
                    default -> birthYear + 1800;
                })
                .map(i -> i * 10000) // 19920000
                .map(i -> i + Integer.parseInt(ssn2.substring(2, 6))) // 19920425
                .map(i -> (fullYear - i)/10000) // 31
                .findFirst()
                .get()           
                
        ;
        assertThat(age).isEqualTo(31);

    }

}
