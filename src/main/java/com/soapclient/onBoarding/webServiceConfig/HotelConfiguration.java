package com.soapclient.onBoarding.webServiceConfig;

import com.soapclient.onBoarding.consumingWebService.HotelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class HotelConfiguration
{
    @Bean
    public Jaxb2Marshaller marshaller()
    {
        Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
        marshaller.setContextPath("com.soapclient.consumingWebService.wsdl");
        return marshaller;
    }
    @Bean
    public HotelClient hotelClient(Jaxb2Marshaller marshaller)
    {
        HotelClient client =new HotelClient();
        client.setDefaultUri("http://localhost:8080/ws");
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
