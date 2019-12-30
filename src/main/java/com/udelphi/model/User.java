package com.udelphi.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import com.udelphi.model.audit.Auditable;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class User extends Auditable{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;
    @Column(name = "name")
    private String name;
    @NaturalId
    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, mappedBy = "userId")
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "client_id")
    private Set<Order> orders = new HashSet<>();

    public User() {
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public void addRole(Role role) {
        roles.add(role);
    }
    public void deleteRole(Integer roleId){
        Role roleForRemove = roles.stream()
                .filter(role -> role.getId().equals(roleId))
                .findFirst()
                .orElseThrow(()-> new EntityNotFoundException("Role not found with id: " + roleId));

        roles.remove(roleForRemove);
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }


     public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString(){
        return email;
    }
}
