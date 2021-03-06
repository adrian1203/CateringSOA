package ejb;

import java.util.List;

public interface ProductEJBInterface {

    public List<Object> getAllCategory();
    public List<Object> getAllPosition();
    public void deletePosition(Long id);
    public void deleteCategory(Long id);
    public void updateCategory(Object object);
    public void updatePosition(Object object);
    public List<Object> getTopPosition();
    public Object getDayPosition();
    public void createCategory(String name, String description );
    public void createPosition(String name, String description, Float price, Long categoryId);
    public Object getCategoryById(Long id);
    public Object getPositionById(Long id);
    public List<Object> getPossitionToApproved();
    public void approvePossition(Long id);
    public void setDayPossition(Long id);



}
