package bean;

import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    private List<Position> positionsOrder;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/ProductEJB")
    private ProductEJBInterface productEJBInterface;

    public ProductsBean(){
        positionsOrder = new ArrayList<Position>();
    }

    public Set<Position> GetFilteredPositions(){
        if(selectedCategory != null)
        {
            Category c = (Category)productEJBInterface.getCategoryById(selectedCategory);
            if(c == null)
                return new HashSet<Position>();
            else
                return c.getPositionSet();
        }
        else
            return new HashSet<Position>();
    }


    public void AddSelectedPositionToOrder(){
        if(selectedPosition != null)
        {
            Position position = (Position) productEJBInterface.getPositionById(selectedPosition);
            if(position != null)
                positionsOrder.add(position);
        }
    }

    public void RemoveSelectedPositionFromOrder(Position p){
        positionsOrder.remove(p);
    }

    public Float SumOrderPrice(){
        Float sum=0.f;
        for(Position p : positionsOrder)
            sum += p.getPrice();
        return sum;
    }


    public List<Position> getPositionsOrder() {
        return positionsOrder;
    }

    public void setPositionsOrder(List<Position> positionsOrder) {
        this.positionsOrder = positionsOrder;
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
