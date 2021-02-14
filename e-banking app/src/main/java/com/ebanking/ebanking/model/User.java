package com.ebanking.ebanking.model;

import com.ebanking.ebanking.model.enumerations.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bank_users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    private PrimaryAccount primaryAccount;

    @OneToMany(mappedBy = "user")
    private List<Recipient> recipientList;

    public User(String username, String password, String firstName, String lastName, String email, Role role, PrimaryAccount primaryAccount) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
        this.primaryAccount = primaryAccount;
        this.recipientList = new ArrayList<>();
    }
}
