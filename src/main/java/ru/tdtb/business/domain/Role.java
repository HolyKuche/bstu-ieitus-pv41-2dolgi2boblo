package ru.tdtb.business.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "ROLE")
public class Role extends AbstractDomain<Long> {
    @Id
    @Column(name = "id")
    private Long id;
    @Column
    private String name;

    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }
}
