package pl.sda.shop.onlineshop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    public Long id;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public String street;
    public String houseNumber;
    public String postalCode;
    public String city;
    public String Country;
}
