package bean;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;

@SessionScoped
@ManagedBean(name = "ModifyProductBean")
public class ModifyProductBean implements Serializable {

    private String posName;
    private String posDesc;
    private Float posPrice;
    private String posCategory;

    private String catName;
    private String catDesc;


    public ModifyProductBean(){

    }


    public String AddNewCategory(){
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String AddNewPosition(){
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

    public void setPosDesc(String posDescr) {
        this.posDesc = posDescr;
    }

    public Float getPosPrice() {
        return posPrice;
    }

    public void setPosPrice(Float posPrice) {
        this.posPrice = posPrice;
    }

    public String getPosCategory() {
        return posCategory;
    }

    public void setPosCategory(String posCategory) {
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
