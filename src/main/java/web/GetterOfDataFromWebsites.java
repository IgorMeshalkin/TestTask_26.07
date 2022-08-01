package web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class GetterOfDataFromWebsites {

    public static String fromYahoo(String tickerSymbol) {
        String url = "https://finance.yahoo.com/quote/" + tickerSymbol + "/profile?p=" + tickerSymbol;

        String text;
        try {
            URLConnection connection = new URL(url).openConnection();
            text = getTextFromAWebPage(connection);
        } catch (IOException e) {
            return null;
        }

        if (!validationYahoo(text)) {
            return null;
        } else {
            return text.substring(text.indexOf("Industry:") + 9, text.indexOf("Full Time Employees:")).trim();
        }
    }

    public static String fromIndeed(String companyName) {
        String url = "https://www.indeed.com/cmp/" + companyName + "/about";

        String text;
        try {
            URLConnection connection = new URL(url).openConnection();
            text = getTextFromAWebPage(connection);
        } catch (IOException e) {
            return null;
        }

        if (!validationIndeed(text)) {
            return null;
        } else {
            text = text.substring(text.indexOf("Headquarters"));
            return text.substring(text.indexOf("Industry") + 8, text.indexOf("Links")).trim();
        }
    }

    protected static String getTextFromAWebPage(URLConnection connection) throws IOException {
        Scanner scanner = new Scanner(connection.getInputStream());
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine()) {
            builder.append(scanner.nextLine());
        }
        scanner.close();
        Document doc = Jsoup.parse(builder.toString());
        return doc.body().text();
    }

    private static boolean validationYahoo(String text) {
        return text.contains("Industry:") && text.contains("Full Time Employees:");
    }

    private static boolean validationIndeed(String text) {
        return text.contains("Industry") && text.contains("Headquarters") && text.contains("Links");
    }
}
