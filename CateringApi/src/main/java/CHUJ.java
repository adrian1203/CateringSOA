import test.ExampleInterface;

import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(ExampleInterface.class)
public class CHUJ implements ExampleInterface {

    public String Hello() {
        return "JebAAAC";
    }

}
