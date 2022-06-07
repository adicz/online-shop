package pl.sda.shop.onlineshop.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import pl.sda.shop.onlineshop.client.model.Weather;

@Component
@RequiredArgsConstructor
public class WeatherClient {

    private static final String WEATHER_API_URL = "https://goweather.herokuapp.com/weather/";

    public Weather callApi(String city) {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(WEATHER_API_URL + city, Weather.class);
    }
}
