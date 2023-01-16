package com.soapclient.onBoarding;

import com.soapclient.consumingWebService.wsdl.*;
import com.soapclient.onBoarding.consumingWebService.HotelClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@SpringBootApplication
public class OnBoardingApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnBoardingApplication.class, args);
	}
	@Bean
	CommandLineRunner lookup(HotelClient quoteClient)
	{
		return args -> {
			GetAllHotelsResponse getAllHotelsResponse=quoteClient.getAllHotelsResponse();
			GetHotelByIdResponse getHotelByIdResponse=quoteClient.getHotelByIdResponse(100);
			AddHotelResponse addHotelResponse=quoteClient.addHotelResponse("test","test address",4, Collections.singletonList("Pool, restaurant"));
			UpdateHotelResponse updateHotelResponse=quoteClient.updateHotelResponse(1,"updated hotel","updated address",2, Collections.singletonList("Pool restaurant"));
			DeleteHotelByIdResponse deleteHotelByIdResponse=quoteClient.deleteHotelByIdResponse(1);
		};
	}
}
