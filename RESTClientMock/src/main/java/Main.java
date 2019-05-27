import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.apache.http.HttpException;


public class Main {

    public static void main(String[] args) throws IOException, HttpException, URISyntaxException {
        RestClient restClient = new RestClient();


        while (true) {
            System.out.println("Aby wyświetlić wszytskie kategorie wciśnij A");
            System.out.println("Aby wyświetlić szcegóły kategori  kategorii podaj jej id");
            System.out.print("Wbieram: ");
            Scanner scan = new Scanner(System.in);
            String value = scan.nextLine();
            System.out.println("Wybierz język 0-domyśliny 1-angielski 2-niemiecki");
            String language = scan.nextLine();
            if (Integer.valueOf(language) != null) {
                switch (Integer.valueOf(language)) {
                    case 1:
                        language = "en-us";
                        break;
                    case 2:
                        language = "de";
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


}
