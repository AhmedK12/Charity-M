package com.example.helptek.role;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

import java.util.Objects;

@Data
@Entity
@Table(name = "role", uniqueConstraints = { @UniqueConstraint(columnNames = { "name" }) })
public class Role {

    private static final long serialVersionUID = 1620497096945021365L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @NaturalId
    @Column(length = 60)
    private RoleName name;



    public Role() {
    }

    public Role(RoleName name) {
        super();
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Role role = (Role) o;
        return Objects.equals(getId(), role.getId()) && getName() == role.getName();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Role{" + "id=" + id + ", name=" + name + '}';
    }

    public Role(Long id, RoleName name) {
        super();
        this.id = id;
        this.name = name;
    }
}
