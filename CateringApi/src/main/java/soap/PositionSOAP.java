package soap;


import javax.jws.WebService;

@WebService(endpointInterface = "soap.PositionSOAPInterface")
public class PositionSOAP implements PositionSOAPInterface {
    @Override
    public void createPosition(String name, String description, Long categroyId) {

    }
}
