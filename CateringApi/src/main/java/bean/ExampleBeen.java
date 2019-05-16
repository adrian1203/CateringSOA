package bean;

import test.ExampleInterface;

import javax.ejb.Remote;
import javax.ejb.Stateful;

@Stateful
@Remote(ExampleInterface.class)
public class ExampleBeen implements ExampleInterface {


    public String Hello() {
        return "Jebać EJB bo jest kurwa od tego REST!!!!";
    }
}
