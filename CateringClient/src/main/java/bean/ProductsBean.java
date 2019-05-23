package bean;

import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;
import javafx.geometry.Pos;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SessionScoped
@ManagedBean(name = "ProductsBean")
public class ProductsBean implements Serializable {

    private Long selectedCategory;
    private Long selectedPosition;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/ProductEJB")
    private ProductEJBInterface productEJBInterface;

    public ProductsBean(){
    }

    public Set<Position> GetFilteredPositions(){
        if(selectedCategory != null)
        {
            Category c = (Category)productEJBInterface.getCategoryById(selectedCategory);
            return c.getPositionSet();
        }
        else
            return new HashSet<Position>();
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
