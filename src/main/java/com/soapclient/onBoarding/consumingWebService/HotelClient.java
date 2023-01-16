package com.soapclient.onBoarding.consumingWebService;
import com.soapclient.consumingWebService.wsdl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import java.util.List;

public class HotelClient extends WebServiceGatewaySupport
{
    private static final Logger log= LoggerFactory.getLogger(HotelClient.class);
    public GetAllHotelsResponse getAllHotelsResponse()
    {
        GetAllHotelsRequest request=new GetAllHotelsRequest();
        log.info("Requesting all hotels");
        GetAllHotelsResponse response=(GetAllHotelsResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/hotels",request,
                        new SoapActionCallback(
                                "http://onBoarding.com/hotels/GetAllHotelsRequest"
                        ));
        for (HotelDetails entity : response.getHotelDetails()) {
            log.info(entity.getHotelId() +" "+ entity.getName());
        }
        return response;
    }
    public GetHotelByIdResponse getHotelByIdResponse(int id)
    {
        GetHotelByIdRequest request=new GetHotelByIdRequest();
        request.setHotelId(id);
        log.info("Getting the hotel information with id "+id);
        GetHotelByIdResponse response=(GetHotelByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/hotels",request,
                new SoapActionCallback(
                "http://onBoarding.com/hotels/GetHotelByIdRequest"
        ));
        log.info(response.getHotelDetails().getHotelId()+" "+ response.getHotelDetails().getName());
        return response;
    }
    public AddHotelResponse addHotelResponse(String name, String address, float rating, List<String> amenities)
    {
        AddHotelRequest request= new AddHotelRequest();
        request.setName(name);
        request.setAddress(address);
        request.setRating(rating);
        request.setAmenities(amenities);
        log.info("Adding the hotel information with name "+name);
        AddHotelResponse response=(AddHotelResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/hotels",request,
                        new SoapActionCallback(
                                "http://onBoarding.com/hotels/AddHotelRequest"
                        ));
        log.info(response.getServiceStatus().getStatusCode());
        return response;
    }
    public UpdateHotelResponse updateHotelResponse(int id,String name, String address, float rating, List<String> amenities)
    {
        UpdateHotelRequest request=new UpdateHotelRequest();
        HotelDetails hotelDetails=new HotelDetails();
        hotelDetails.setHotelId(id);
        hotelDetails.setAddress(address);
        hotelDetails.setRating(rating);
        hotelDetails.setAmenities(amenities);
        hotelDetails.setName(name);
        request.setHotelDetails(hotelDetails);
        log.info("Updating hotel with id "+ id);
        UpdateHotelResponse response=(UpdateHotelResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/hotels",request,
                        new SoapActionCallback(
                                "http://onBoarding.com/hotels/UpdateHotelRequest"
                        ));
        log.info(response.getServiceStatus().getStatusCode());
        return response;
    }
    public DeleteHotelByIdResponse deleteHotelByIdResponse(int id)
    {
        DeleteHotelByIdRequest request= new DeleteHotelByIdRequest();
        request.setHotelId(id);
        log.info("Deleting hotel with id "+id);
        DeleteHotelByIdResponse response=(DeleteHotelByIdResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://localhost:8080/ws/hotels",request,
                        new SoapActionCallback(
                                "http://onBoarding.com/hotels/DeleteHotelByIdRequest"
                        ));
        log.info(response.getServiceStatus().getStatusCode()+ " ");
        return response;
    }
}
