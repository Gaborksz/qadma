package com.practise.qadma.auth.payload;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SignInDTO {

    private String userName;

    private String password;
}
