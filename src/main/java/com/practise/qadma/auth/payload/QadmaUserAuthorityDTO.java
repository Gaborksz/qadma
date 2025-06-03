package com.practise.qadma.auth.payload;

import com.practise.qadma.auth.entity.QadmaUserAuthorityType;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class QadmaUserAuthorityDTO implements GrantedAuthority {

    private long id;

    @Getter(AccessLevel.NONE)
    private QadmaUserAuthorityType authority;

    @Override
    public String getAuthority() {
        return this.authority.name();
    }
}
