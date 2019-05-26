package bean;

import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;
import sun.invoke.util.Wrapper;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.*;

@SessionScoped
@ManagedBean(name = "ProductsBean")
public class ProductsBean implements Serializable {

    private Long selectedCategory;
    private Long selectedPosition;

    private List<Position> positionsOrder;

    private boolean cyclicOrder;
    private String orderDetails;
    private Date orderDeliver;
    private List<Date> cyclicOrderDeliver;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/ProductEJB")
    private ProductEJBInterface productEJBInterface;

    public ProductsBean(){
        positionsOrder = new ArrayList<Position>();
        cyclicOrderDeliver = new ArrayList<Date>();
        cyclicOrder = false;
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

    public void AddCyclicDates(){
        if(orderDeliver!=null)
        {
            cyclicOrderDeliver.add(orderDeliver);
        }
    }

    public void RemoveDateFromCyclicOrder(Date dt){
        if(dt !=null)
            cyclicOrderDeliver.remove(dt);
    }



    ///GETTERS AND SETTERS


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

    public boolean isCyclicOrder() {
        return cyclicOrder;
    }

    public void setCyclicOrder(boolean cyclicOrder) {
        this.cyclicOrder = cyclicOrder;
    }

    public String getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(String orderDetails) {
        this.orderDetails = orderDetails;
    }

    public Date getOrderDeliver() {
        return orderDeliver;
    }

    public void setOrderDeliver(Date orderDeliver) {
        this.orderDeliver = orderDeliver;
    }

    public List<Date> getCyclicOrderDeliver() {
        return cyclicOrderDeliver;
    }

    public void setCyclicOrderDeliver(List<Date> cyclicOrderDeliver) {
        this.cyclicOrderDeliver = cyclicOrderDeliver;
    }
}
