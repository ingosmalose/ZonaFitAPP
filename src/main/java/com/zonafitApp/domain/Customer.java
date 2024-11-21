package com.zonafitApp.domain;

import java.util.Objects;

public class Customer {
    private int id;
    private String firstName;
    private String lastName;
    private int member;

    public Customer(){}

    public Customer(int id){
        this.id = id;
    }

    public Customer(String firstName, String lastName, int member){
        this.firstName = firstName;
        this.lastName=lastName;
        this.member = member;
    }

    public Customer(int id, String firstName, String lastName, int member){
        this( firstName,  lastName,  member);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getMember() {
        return member;
    }

    public void setMenber(int member) {
        this.member = member;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", member=" + member +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return id == customer.id && member == customer.member && Objects.equals(firstName, customer.firstName) && Objects.equals(lastName, customer.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, member);
    }
}
