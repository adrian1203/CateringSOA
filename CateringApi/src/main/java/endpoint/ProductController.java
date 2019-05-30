package endpoint;


import domain.Category;
import service.CategoryService;
import service.PositionService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("categories")
public class ProductController {


    Logger logger = Logger.getLogger(ProductController.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Category> getAllCategory(@Context HttpHeaders httpHeaders) {
        if (httpHeaders.getLanguage() != null) {
            return categoryService.translateCategory(httpHeaders.getLanguage().toString(), this.categoryService.getAllCategory());
        }
        return this.categoryService.getAllCategory();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("{id}")
    public Category getCategoryById(@PathParam("id") Long id, @Context HttpHeaders httpHeaders) {
        if (httpHeaders.getLanguage() != null) {
            return categoryService.translateCategory(httpHeaders.getLanguage().toString(), categoryService.getCategoryById(id));
        }
        return this.categoryService.getCategoryById(id);
    }


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("dupa")
    public Category getCategoryById() {
        positionService.getTopPosition();
        return null;
    }

    private CategoryService categoryService;
    private PositionService positionService;

    public ProductController() {

        categoryService = new CategoryService();
        positionService = new PositionService();
    }


}
