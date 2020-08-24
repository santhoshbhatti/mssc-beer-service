package guru.springframework.msscbeerservice.web.model.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    BeerDto validBeer;
    @BeforeEach
    public void setUp(){
        validBeer = BeerDto.builder()
                .beerName("Irish Coffee")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(324324L)
                .price(new BigDecimal(100.0))
                .quantityOnHand(10)
                .build();
    }
    @Test
    void getBeerById() throws Exception {
        mockMvc.perform(get("/api/v1/beer/"+ UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = validBeer;
        String beerJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        BeerDto beerDto = validBeer;
        String beerJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID().toString()).
                contentType(MediaType.APPLICATION_JSON)
                .content(beerJson)).andExpect(status().isNoContent());
    }
}