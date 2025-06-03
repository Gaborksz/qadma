package com.practise.qadma.auth.payload;

import lombok.*;

import java.util.List;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QadmaUserDTO {

    private long id;
    private String username;
    private List<QadmaUserAuthorityDTO> authorities;
}
