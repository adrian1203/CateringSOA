package bean;

import ejb.ProductEJBInterface;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "PositionBean")
public class PositionBean implements Serializable {

    private String selectedCategory;
    private String selectedSubCategory;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/ProductEJB")
    private ProductEJBInterface productEJBInterface;

    public PositionBean(){
        selectedCategory="";
    }

    public ProductEJBInterface getProductEJBInterface() {
        return productEJBInterface;
    }

    public void setProductEJBInterface(ProductEJBInterface productEJBInterface) {
        this.productEJBInterface = productEJBInterface;
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
