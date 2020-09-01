package guru.springframework.msscbeerservice.web.model.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.msscbeerservice.bootstrap.BeerLoader;
import guru.springframework.msscbeerservice.web.model.BeerDto;
import guru.springframework.msscbeerservice.web.model.BeerStyleEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BeerController.class)
/*this is currently failing as @WebMvcTest does not bringup or does not instanriate
* the service layer . Now we have error creating the BeerController as the
* @WebMvcTest is not instantiating the service layer ---- BeerService and
* not wiring in the BeerServiceImpl. So we need to mock the Service layer for WebMvcTest */
class BeerControllerTest {
    @Autowired
    MockMvc mockMvc;

    @MockBean
    BeerService beerService;
    @Autowired
    ObjectMapper objectMapper;
    BeerDto validBeer;
    @BeforeEach
    public void setUp(){
        validBeer = BeerDto.builder()
                .beerName("Irish Coffee")
                .beerStyle(BeerStyleEnum.ALE)
                .upc(BeerLoader.BEER_1_UPC)
                .price(new BigDecimal(100.0))
                .quantityOnHand(10)
                .build();
    }
    @Test
    void getBeerById() throws Exception {
        given(beerService.getBeerById(any())).willReturn(validBeer);
        mockMvc.perform(get("/api/v1/beer/"+ UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void saveNewBeer() throws Exception {
        given(beerService.saveBeer(any())).willReturn(validBeer);
        BeerDto beerDto = validBeer;
        String beerJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(post("/api/v1/beer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerJson))
                .andExpect(status().isCreated());
    }

    @Test
    void updateBeer() throws Exception {
        given(beerService.updateBeer(any(),any())).willReturn(validBeer);
        BeerDto beerDto = validBeer;
        String beerJson = objectMapper.writeValueAsString(beerDto);
        mockMvc.perform(put("/api/v1/beer/"+UUID.randomUUID().toString()).
                contentType(MediaType.APPLICATION_JSON)
                .content(beerJson)).andExpect(status().isNoContent());
    }
}