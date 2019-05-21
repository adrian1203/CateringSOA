package bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "PositionBean")
public class PositionBean implements Serializable {

    private String selectedCategory;
    private String selectedSubCategory;

    public PositionBean(){
        selectedCategory="";
    }


    public String getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(String selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public String getSelectedSubCategory() {
        return selectedSubCategory;
    }

    public void setSelectedSubCategory(String selectedSubCategory) {
        this.selectedSubCategory = selectedSubCategory;
    }
}