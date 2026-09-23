package vn.iotstar.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ResetPasswordDTO {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 6, message = "Mật khẩu tối thiểu 6 ký tự")
    private String password;

    @NotBlank
    private String confirmPassword;

    @NotBlank(message = "OTP không được để trống")
    @Size(min = 6, max = 6, message = "OTP gồm 6 số")
    private String otp;
}
