package com.example.ordersystem.member.dto;

import com.example.ordersystem.member.domain.Member;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemberSaveReqDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 8)
    private String password;

    public Member toEntity(String encdedPassword){
        return Member.builder().name(this.name).email(this.email)
                .password(encdedPassword)
                .build();
    }
}
