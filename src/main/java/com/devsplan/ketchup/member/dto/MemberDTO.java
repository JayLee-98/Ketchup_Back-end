package com.devsplan.ketchup.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private String memberNo;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String memberName;

    private String memberPW;
    private String phone;
    private String birthDate;
    private char gender;
    private String address;

    @Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
    @NotBlank(message = "이메일은 필수 입력값입니다.")
    private String privateEmail;

    private String companyEmail;
    private DepDTO department;
    private PositionDTO position;
    private String account;
    private String status;
    private String imgUrl;

    private String verifyCode;

    private LocalDateTime resignDateTime;

    private String isFirstLogin;
    private String user_id;
}
