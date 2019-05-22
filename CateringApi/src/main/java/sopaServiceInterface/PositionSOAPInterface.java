package sopaServiceInterface;


import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use= SOAPBinding.Use.LITERAL)
public interface PositionSOAPInterface {

    @WebMethod
    public void createPosition(String name, String description, Float price,Long categroyId);
}
