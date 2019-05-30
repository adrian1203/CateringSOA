import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;

public class RestClient {

    public void findCategoriesAll(String lanaguge) throws IOException, HttpException, URISyntaxException {

        ObjectMapper mapper = new ObjectMapper();
        List<Category> categories=new LinkedList<Category>();
        String json = getResponse("http://localhost:8080/CateringApi-1.0-SNAPSHOT/rest/categories", lanaguge);
        if (json != null) {
            System.out.println(json);
            categories = mapper.readValue(json, new TypeReference<List<Category>>(){});
            printCategories(categories);
        }
    }

    public void findCategoriesById(Long id, String language) throws IOException, HttpException, URISyntaxException {
        ObjectMapper mapper = new ObjectMapper();
        Category category=new Category();
        String url = "http://localhost:8080/CateringApi-1.0-SNAPSHOT/rest/categories/"+id;
        String json = getResponse(url, language);
        if (json != null) {
            category = mapper.readValue(json, Category.class);
            printDetails(category);
        }
    }

    public String getResponse(String url, String language) throws IOException, HttpException, URISyntaxException{
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        request.setHeader("Content-Language",language);
        HttpResponse response = client.execute(request);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String json = "";
        List<Category> categories=new LinkedList<Category>();
        if ((json = rd.readLine()) != null) {
           return json;
        }
        return "";
    }

    public void printCategories(List<Category>  categories){
        categories.forEach(elem->{
            System.out.println("Id: " +elem.getId() + " Nazwa: "+elem.getName()+ " Opis: "+elem.getDescription());
        });
    }
    public void printDetails(Category category){
        System.out.println("Id: " +category.getId() + " Nazwa: "+category.getName()+ " Opis: "+category.getDescription());
        System.out.println("Pozycje w danej kategori:");
        category.getPositionSet().forEach(elem->{
            System.out.println("Id: " +elem.getId() + " Nazwa: "+elem.getName()+ " Opis: "+elem.getDescription());
        });

    }
}
