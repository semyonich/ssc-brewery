package guru.sfg.brewery.web.controllers.api;

import guru.sfg.brewery.web.controllers.BaseIT;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class BeerRestControllerIT extends BaseIT {

    @Test
    @SneakyThrows
    void findBeers() {
        mockMvc.perform(get("/api/v1/beer"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void findBeerById() {
        mockMvc.perform(get("/api/v1/beer/{beerId}", "48adc8d1-0b8c-4164-a69f-86087a502237"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void findBeerByUpc() {
        mockMvc.perform(get("/api/v1/beerUpc/{upc}", "upc"))
                .andExpect(status().isOk());
    }
}