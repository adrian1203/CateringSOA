package bean;


import domain.*;
import ejb.OrderEJBInterface;
import ejb.OrderMDBInterface;
import ejb.PermanentOrderIEJBnterface;
import ejb.ProductEJBInterface;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/OrderEJB")
    private OrderEJBInterface orderEJBInterface;

    @EJB(lookup = "java:global/CateringApi-1.0-SNAPSHOT/PermanentOrderIEJB")
    private PermanentOrderIEJBnterface permanentOrderIEJBnterface;



    public ProductsBean(){
        positionsOrder = new ArrayList<Position>();
        cyclicOrderDeliver = new ArrayList<Date>();
        selectedPositionsView = new HashSet<Position>();
        cyclicOrder = false;
    }

    public List<Object> GetAllCategories() {
        return productEJBInterface.getAllCategory();
    }


    public void FilterPositions(){
        if(selectedCategory != null)
        {
            Category c = (Category)productEJBInterface.getCategoryById(selectedCategory);
            if(c == null)
                selectedPositionsView.clear();
            else
            {
                selectedPositionsView = c.getPositionSet();
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
            orderDeliver.setHours(deliverHour);
            orderDeliver.setMinutes(deliverMinute);
            cyclicOrderDeliver.add(orderDeliver);
        }
    }

    public void RemoveDateFromCyclicOrder(Date dt){
        if(dt !=null)
            cyclicOrderDeliver.remove(dt);
    }

    public String RedirectToPage(String pageName){
        return "/"+pageName+".xhtml?faces-redirect=true";
    }

    public String MakeOrder(Long userID){
        if(cyclicOrder)
            return MakeCyclicOrder(userID);
        else
            return MakeNormalOrder(userID);

    }

    public String MakeCyclicOrder(Long userId){
        Set<PermanetOrderDate> permanetOrderDates = new HashSet<>();
        for(Date d : cyclicOrderDeliver){
            permanetOrderDates.add(new PermanetOrderDate(d));
        }

        permanentOrderIEJBnterface.createOrder(orderDetails,new HashSet<Object>(positionsOrder),userId,new HashSet<Object>(permanetOrderDates));
        ClearAll();
        return RedirectToPage("catering_products");
    }

    public String MakeNormalOrder(Long userId){
        orderDeliver.setHours(deliverHour);
        orderDeliver.setMinutes(deliverMinute);
        orderEJBInterface.createOrder(orderDeliver, orderDetails, new HashSet<Object>(selectedPositionsView),userId);
        ClearAll();
        return RedirectToPage("catering_products");
    }

    public void ApprovedPosition(Long posId){
        productEJBInterface.approvePossition(posId);
    }

    public void ClearAll(){
        orderDeliver=null;
        orderDetails=null;
        cyclicOrder=false;
        cyclicOrderDeliver.clear();
        selectedPositionsView.clear();
        positionsOrder.clear();
        deliverHour=0;
        deliverMinute=0;
    }

    public String GetPositionFromSet(Set<Object> objectSet){
        if(objectSet != null && objectSet.size() > 0)
        {
            String positions ="";
            for(Object o : objectSet){
                Position p = (Position)o;
                positions += "* "+p.getName() + "\n";
            }
            return positions;
        }
        else
            return " Brak pozycji";
    }

    public String SetDayPosition(Long posID){
        productEJBInterface.setDayPossition(posID);
        return RedirectToPage("catering_products");
    }

    public String GetDayPosition(){
        Position position = (Position) productEJBInterface.getDayPosition();

        if(position == null)
            return "Pozycja dnia nie została jeszcze wybrana!";
        else
        {
            return position.getName() + " za " + position.getPrice() + "zł";
        }
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
