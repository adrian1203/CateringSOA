package endpoint;


import domain.Category;
import service.CategoryService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/product")
public class ProductController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("categories")
    public List<Category> getAllCategory(){
        return this.categoryService.getAllCategory();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("categories/{id}")
    public Category getCategoryById(@PathParam("id") Long id){
        return this.categoryService.getCategoryById(id);
    }

    private CategoryService categoryService;

    public ProductController() {
        categoryService=new CategoryService();
    }
}
