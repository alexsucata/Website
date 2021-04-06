package com.sda.website.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name = "clients")
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer clientId;

    @NotBlank(message = "Name can not be blank")
    private String name;

    @NotBlank(message = "Address can not be blank")
    @Pattern(regexp = "[a-z, A-Z, 0-9]*",message = "Address contains forbidden characters")
    private String address;

    @NotBlank(message = "Phone can not be blank")
    private String phone;

    @ManyToOne
    @JoinColumn(name = "cityId")
    private CityEntity city;

    @NotBlank(message = "Country can not be blank")
    private String country;

    @OneToMany(mappedBy = "client")
    private List<OrderEntity> orders;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public CityEntity getCity() {
        return city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }
}
