package com.practise.qadma.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "authorities")
public class QadmaUserAuthority implements GrantedAuthority{

    public QadmaUserAuthority(QadmaUserAuthorityType authority) {
        this.authority = authority;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "authority_type")
    private QadmaUserAuthorityType authority;

    @Override
    public String getAuthority() {
        return this.authority.name();
    }
}
