package bean;


import domain.CateringUser;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@Singleton
@Startup
public class CateringApplicationManager {
    
    private List<CateringUser> loggedUsers;
    
    @PostConstruct
    public void setupCateringApplication(){
        this.loggedUsers=new ArrayList<CateringUser>();
    }
}
