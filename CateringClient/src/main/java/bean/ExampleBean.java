package bean;

import test.ExampleInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@SessionScoped
@ManagedBean(name = "ExampleBean")
public class ExampleBean {

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/ExampleBeen")
    private ExampleInterface exampleInterface;

    public String foo(){
        return exampleInterface.Hello();
    }
}