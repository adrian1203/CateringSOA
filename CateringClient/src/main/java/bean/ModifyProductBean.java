package bean;

import ejb.ProductEJBInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "ModifyProductBean")
public class ModifyProductBean implements Serializable {

    private String posName;
    private String posDesc;
    private Float posPrice;
    private Long posCategory;

    private String catName;
    private String catDesc;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/ProductEJB")
    private ProductEJBInterface productEJBInterface;


    //Constructor
    public ModifyProductBean(){
        posCategory=-1L;
    }

    public String AddNewCategory(){
        productEJBInterface.createCategory(catName,catDesc);
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String AddNewPosition(){
        if(posCategory > -1L)
            productEJBInterface.createPosition(posName,posDesc,posPrice,posCategory);
        posCategory=-1L;
        return "/catering_products.xhtml?faces-redirect=true";
    }


    public String getPosName() {
        return posName;
    }

    public void setPosName(String posName) {
        this.posName = posName;
    }

    public String getPosDesc() {
        return posDesc;
    }

    public void setPosDesc(String posDesc) {
        this.posDesc = posDesc;
    }

    public Float getPosPrice() {
        return posPrice;
    }

    public void setPosPrice(Float posPrice) {
        this.posPrice = posPrice;
    }

    public Long getPosCategory() {
        return posCategory;
    }

    public void setPosCategory(Long posCategory) {
        this.posCategory = posCategory;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    public String getCatDesc() {
        return catDesc;
    }

    public void setCatDesc(String catDesc) {
        this.catDesc = catDesc;
    }



}
