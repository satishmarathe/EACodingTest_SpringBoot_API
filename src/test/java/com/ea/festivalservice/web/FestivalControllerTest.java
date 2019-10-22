package com.ea.festivalservice.web;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.ea.festivalservice.common.RateLimitException;
import com.ea.festivalservice.model.Band;
import com.ea.festivalservice.model.MusicFestival;
import com.ea.festivalservice.service.FestivalService;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class FestivalControllerTest {
	@MockBean
	private FestivalService service;

	@Autowired
	private MockMvc mockMvc;



	@Test
	@DisplayName("GET /api/v1/festivals - Found and Validated response")
	void testGetFestivals() throws Exception {
		// Setup our mocked service
		List<MusicFestival> mockMusicFestivalList = new ArrayList<>();

		MusicFestival festival1 = new MusicFestival();
		festival1.setName("Spring festival");
		Band b1 = new Band();
		b1.setName("ACDC");
		b1.setRecordLabel("Elektra Records");

		Band b2 = new Band();
		b2.setName("Sting");
		b2.setRecordLabel("Interscope");

		List<Band> bandList = new ArrayList<>();
		bandList.add(b1);
		bandList.add(b2);

		festival1.setBands(bandList);

		mockMusicFestivalList.add(festival1);
		doReturn(mockMusicFestivalList).when(service).getAllFestivals();


		mockMvc.perform(get("/api/v1/festivals"))

		// Validate the response code and content type
		.andExpect(status().isOk())
		.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
		.andExpect(content().json("[{\"name\":\"Spring festival\",\"bands\":[{\"name\":\"ACDC\",\"recordLabel\":\"Elektra Records\"},{\"name\":\"Sting\",\"recordLabel\":\"Interscope\"}]}]"));
	}

	@Test
	@DisplayName("GET /api/v1/festivals - 429 response")
	void testGetFestivals429() throws Exception {
		// Setup our mocked service
		RateLimitException  rte = new RateLimitException("too many requests");
		doThrow(rte).when(service).getAllFestivals();


		mockMvc.perform(get("/api/v1/festivals"))

		// Validate the response code and content type
		.andExpect(status().is(429));

	}




	static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
