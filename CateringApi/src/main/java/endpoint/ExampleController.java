package endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/example")
public class ExampleController {

    @GET
    public String exampleGET(){
        return "test działania REST";
    }

}
