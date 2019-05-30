package bean;

import domain.Category;
import domain.Position;
import ejb.ProductEJBInterface;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
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
    private Set<Position> selectedPositionsView;
    private int deliverHour;
    private int deliverMinute;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/ProductEJB")
    private ProductEJBInterface productEJBInterface;

    public ProductsBean(){
        positionsOrder = new ArrayList<Position>();
        cyclicOrderDeliver = new ArrayList<Date>();
        selectedPositionsView = new HashSet<Position>();
        cyclicOrder = false;
    }

    public void FilterPositions(){
        if(selectedCategory != null)
        {
            System.out.println("Wchodzę t kurwa");
            Category c = (Category)productEJBInterface.getCategoryById(selectedCategory);
            if(c == null)
                selectedPositionsView.clear();
            else
            {
                System.out.println("Wchodzę t kurwa do elssaaaaaa");
                System.out.println(c.getName() +"  kurwa   "+ c.getId());
                selectedPositionsView = c.getPositionSet();
                System.out.println(selectedPositionsView.size());

            }
        }
        else
            selectedPositionsView.clear();
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

    public void AddCyclicDates() throws IOException {
        if(orderDeliver!=null)
        {
            System.out.println("ELO: " + deliverHour + ", " + deliverMinute);
            orderDeliver.setHours(deliverHour);
            orderDeliver.setMinutes(deliverMinute);
            cyclicOrderDeliver.add(orderDeliver);
            //ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            //ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        }
    }

    public void RemoveDateFromCyclicOrder(Date dt){
        if(dt !=null)
            cyclicOrderDeliver.remove(dt);
    }

    public String RedirectToPage(String pageName){
        return "/"+pageName+".xhtml?faces-redirect=true";
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

    public Set<Position> getSelectedPositionsView() {
        return selectedPositionsView;
    }

    public void setSelectedPositionsView(Set<Position> selectedPositionsView) {
        this.selectedPositionsView = selectedPositionsView;
    }

    public int getDeliverHour() {
        return deliverHour;
    }

    public void setDeliverHour(int deliverHour) {
        this.deliverHour = deliverHour;
    }

    public int getDeliverMinute() {
        return deliverMinute;
    }

    public void setDeliverMinute(int deliverMinute) {
        this.deliverMinute = deliverMinute;
    }
}
