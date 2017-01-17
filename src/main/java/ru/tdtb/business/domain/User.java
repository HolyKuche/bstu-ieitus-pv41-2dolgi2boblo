package ru.tdtb.business.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "USER_")
public class User extends AbstractDomain<Long> {
    @Id
    @Column(name = "ID")
    @GeneratedValue(generator = "seq", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "seq", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "LOGIN")
    private String login;

    @Column(name = "HASH_PASS")
    private String hashPass;

    private String apiToken;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_2_USER",
            joinColumns = @JoinColumn(name = "USER1_ID", nullable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "USER2_ID", nullable = false, updatable = false))
    private List<User> friends;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "USER_2_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)},
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID", nullable = false, updatable = false)})
    private List<Role> roles;
}
