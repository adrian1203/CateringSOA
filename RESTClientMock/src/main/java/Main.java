import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class Main {

    public static void main(String[] args) throws IOException, HttpException, URISyntaxException {
        RestClient restClient = new RestClient();


        System.out.println("Aby wyświetlić wszytskie kategorie wciśnij A");
        System.out.println("Aby wyświetlić szcegóły kategori  kategorii podaj jej id");
        System.out.print("Wbieram: ");
        Scanner scan = new Scanner(System.in);
        String value = scan.nextLine();
        System.out.println("Wybierz język 0-domyśliny 1-angielski 2-niemiecki");
        String language = scan.nextLine();
        if(Integer.valueOf(language)!=null){
            switch (Integer.valueOf(language)){
                case 1:
                    language="en-us";
                    break;
                case 2:
                    language="de";
            }
        }
        System.out.println("Czekaj na odpowiedź...");
        if (value.equals("A")) {
            restClient.findCategoriesAll(language);
        } else if (Long.valueOf(value) != null) {
            restClient.findCategoriesById(Long.valueOf(value), language);
        }

    }


}
