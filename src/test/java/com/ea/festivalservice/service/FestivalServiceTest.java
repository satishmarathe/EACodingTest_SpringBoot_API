package com.ea.festivalservice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.ea.festivalservice.common.ConstantsIfc;
import com.ea.festivalservice.common.RateLimitException;
import com.ea.festivalservice.model.MusicFestival;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Tests the FestivalService.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
class FestivalServiceTest {

    /**
     * The service that we want to test.
     */
    @Autowired
    private FestivalService service;    

    @Test
    @DisplayName("Test get Festivals from Service class ")
    void testGetFestivals() {
        List<MusicFestival> products = service.getAllFestivals();

        Assertions.assertEquals(1, products.size(), "findAll should return 1 Festival");
    }
    
    @Test
    @DisplayName("Test get Festivals returns a 429 error ")
    void testGetFestivals429() {
    	
    	ConstantsIfc.rateLimitCount.add(4);
        
    	assertThrows(RateLimitException.class, () -> {
    		service.getAllFestivals();
    	});

        
    }

    
}
