import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GetterOfDataFromWebsites {

    public static String fromYahoo(String tickerSymbol) throws IOException {
        String url = "https://finance.yahoo.com/quote/" + tickerSymbol + "/profile?p=" + tickerSymbol;

        URLConnection connection = new URL(url).openConnection();
        String text = getTextFromAWebPage(connection);

        return text.substring(text.indexOf("Industry:") + 9, text.indexOf("Full Time Employees:")).trim();
    }

    public static String fromIndeed(String companyName) throws IOException {
        String url = "https://www.indeed.com/cmp/" + companyName + "/about";

        URLConnection connection = new URL(url).openConnection();
        String text = getTextFromAWebPage(connection);

        text = text.substring(text.indexOf("Headquarters"));
        return text.substring(text.indexOf("Industry") + 8, text.indexOf("Links")).trim();

    }

    private static String getTextFromAWebPage(URLConnection connection) throws IOException {
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        Document doc = Jsoup.parse(builder.toString());
        return doc.body().text();
    }
}
