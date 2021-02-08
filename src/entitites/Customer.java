package entitites;

import java.time.LocalDate;
import java.util.Objects;

public class Customer {
    private int id;
    private String name;
    private String address;
    private String email;
    private String ccNo;
    private String ccType;
    private LocalDate maturity;

    public Customer() {
    }

    public Customer(int id, String name, String address,
                    String email, String ccNo, String ccType,
                    LocalDate maturity) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.ccNo = ccNo;
        this.ccType = ccType;
        this.maturity = maturity;
    }

    public Customer(String name, String address, String email,
                    String ccNo, String ccType, LocalDate maturity) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.ccNo = ccNo;
        this.ccType = ccType;
        this.maturity = maturity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCcNo() {
        return ccNo;
    }

    public void setCcNo(String ccNo) {
        this.ccNo = ccNo;
    }

    public String getCcType() {
        return ccType;
    }

    public void setCcType(String ccType) {
        this.ccType = ccType;
    }

    public LocalDate getMaturity() {
        return maturity;
    }

    public void setMaturity(LocalDate maturity) {
        this.maturity = maturity;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", ccNo='" + ccNo + '\'' +
                ", ccType='" + ccType + '\'' +
                ", maturity=" + maturity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer customer = (Customer) o;
        return getId() == customer.getId() && getName().equals(customer.getName()) && getAddress().equals(customer.getAddress()) && getEmail().equals(customer.getEmail()) && getCcNo().equals(customer.getCcNo()) && getCcType().equals(customer.getCcType()) && getMaturity().equals(customer.getMaturity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getAddress(), getEmail(), getCcNo(), getCcType(), getMaturity());
    }
}