package endpoint;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/example")
public class ExampleController {

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String exampleGET(){
        return "test działania REST";
    }

}
