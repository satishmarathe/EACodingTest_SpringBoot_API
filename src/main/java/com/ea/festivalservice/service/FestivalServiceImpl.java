package com.ea.festivalservice.service;

import org.springframework.stereotype.Service;

import com.ea.festivalservice.common.ConstantsIfc;
import com.ea.festivalservice.common.RateLimitException;
import com.ea.festivalservice.model.Band;
import com.ea.festivalservice.model.Festival;
import com.ea.festivalservice.model.MusicFestival;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FestivalServiceImpl implements FestivalService {

   

   

    
    
    /** my method **/
    public List<MusicFestival> getAllFestivals(){
    	if(ConstantsIfc.rateLimitCount.size() > 0 && ConstantsIfc.rateLimitCount.get(0) >=3){
			/** layman implementation of rate limit where after 3 requests we will send a 429 limit response 
			  * and then again the counter to 0 to allow next requests to be processed
			  */
			  ConstantsIfc.rateLimitCount.clear();
			throw new RateLimitException("Number of tries exceeded");
		}else {
			/** increment the count of requests processed for rate limit **/
			Integer count = 0;
			if(ConstantsIfc.rateLimitCount.size() > 0){
				count = ConstantsIfc.rateLimitCount.get(0);
			}			
			ConstantsIfc.rateLimitCount.clear();
			ConstantsIfc.rateLimitCount.add(count+1);
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
	    	return mockMusicFestivalList;
		}
    	
    }
}
