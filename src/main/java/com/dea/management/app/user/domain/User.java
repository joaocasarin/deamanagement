package com.dea.management.app.user.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NamedQuery(name = "myQuery", query = "SELECT u FROM User u WHERE u.name = :name")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String linkedin;
}
