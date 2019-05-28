package bean;

import com.sun.org.apache.xpath.internal.operations.Mod;
import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;
import javafx.geometry.Pos;

import javax.crypto.spec.PSource;
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


    private boolean modifyCategory;
    private Long selectedCategory;

    private boolean modifyPosition;
    private Long selectedPosition;


    //Constructor
    public ModifyProductBean(){
        posCategory=-1L;
        modifyCategory=false;
        modifyPosition=false;
    }

    public String AddNewCategory(){
        productEJBInterface.createCategory(catName,catDesc);
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String ModifyCategory(){
        Category category =  new Category();
        category.setId(selectedCategory);
        category.setName(catName);
        category.setDescription(catDesc);
        productEJBInterface.updateCategory(category);
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String AddNewPosition(){
        if(posCategory > -1L)
            productEJBInterface.createPosition(posName,posDesc,posPrice,posCategory);
        posCategory=-1L;
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String ModifyPosition(){
        Position position = new Position();
        position.setId(selectedPosition);
        position.setName(posName);
        position.setDescription(posDesc);
        position.setPrice(posPrice);
        Category category = (Category) productEJBInterface.getCategoryById(posCategory);
        position.setCategory(category);
        productEJBInterface.updatePosition(position);
        return "/catering_products.xhtml?faces-redirect=true";
    }

    public String ProcessCategoryApply(){
        if(modifyCategory)
            return  ModifyCategory();
        else
            return AddNewCategory();
    }

    public String ProcessPositionApply(){
        if(modifyPosition)
            return ModifyPosition();
        else
            return AddNewPosition();
    }


    public void UpdateCategoryModifyInput(){
        if(selectedCategory != -1L){
            Category category = (Category) productEJBInterface.getCategoryById(selectedCategory);
            catName = category.getName();
            catDesc = category.getDescription();
        }
    }

    public void UpdatePositionModifyInput(){
        if(selectedPosition != -1L){
            Position position = (Position)productEJBInterface.getPositionById(selectedPosition);
            posName = position.getName();
            posDesc = position.getDescription();
            posPrice = position.getPrice();
            posCategory = position.getCategory().getId();
        }
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


    public boolean isModifyCategory() {
        return modifyCategory;
    }

    public void setModifyCategory(boolean modifyCategory) {
        this.modifyCategory = modifyCategory;
    }

    public boolean isModifyPosition() {
        return modifyPosition;
    }

    public void setModifyPosition(boolean modifyPosition) {
        this.modifyPosition = modifyPosition;
    }

    public ProductEJBInterface getProductEJBInterface() {
        return productEJBInterface;
    }

    public void setProductEJBInterface(ProductEJBInterface productEJBInterface) {
        this.productEJBInterface = productEJBInterface;
    }

    public Long getSelectedCategory() {
        return selectedCategory;
    }

    public void setSelectedCategory(Long selectedCategory) {
        this.selectedCategory = selectedCategory;
    }

    public Long getSelectedPosition() {
        return selectedPosition;
    }

    public void setSelectedPosition(Long selectedPosition) {
        this.selectedPosition = selectedPosition;
    }
}
