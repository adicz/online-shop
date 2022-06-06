package pl.sda.shop.onlineshop.client.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather {

    String temperature;
    String wind;
    String description;

}
