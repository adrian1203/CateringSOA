package soapService;


import service.PositionService;
import sopaServiceInterface.PositionSOAPInterface;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(endpointInterface = "sopaServiceInterface.PositionSOAPInterface")
public class PositionSOAP implements PositionSOAPInterface {

    private PositionService positionService;


    @WebMethod()
    public void createPosition(String name, String description,Float price, Long categroyId) {

        positionService.createPosition(name,description, price,categroyId, true);
    }

    public PositionSOAP() {
        positionService = new PositionService();
    }
    //Do testów tmp;

//    <Envelope xmlns="http://schemas.xmlsoap.org/soap/envelope/">
////    <Body>
////        <createPosition xmlns="http://sopaServiceInterface/">
////            <arg0 xmlns="">siaka</arg0>
////            <arg1 xmlns="">siaka</arg1>
////            <arg2 xmlns="">12.36</arg2>
////            <arg3 xmlns="">1</arg3>
////        </createPosition>
////    </Body>
////</Envelope>
}
