package pl.sda.shop.onlineshop.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.sda.shop.onlineshop.model.enumerated.NotifyOption;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public String username;
    public String password;
    public String firstname;
    public String lastname;
    public String email;
    @OneToOne
    public Address address;
    public String image;
    @Enumerated(EnumType.STRING)
    public NotifyOption notifyOption;

}
