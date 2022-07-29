package web;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompanyFinder {

    private final static int TARGET_NUMBER_OF_COMPANIES = 50;

    public static Map<String, String> findCompany() throws IOException {
        System.out.println("Companies search started..." + "\n");

        Map<String, String> companies = new HashMap<>();
        Set<String> alreadyChecked = new HashSet<>();

        int successCounter = 0;
        int pageCounter = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();

        while (successCounter < TARGET_NUMBER_OF_COMPANIES) {
            URLConnection connection = new URL("https://finance.yahoo.com/calendar/earnings?from=2022-07-24&to=2022-07-30&day=" + date.format(formatter)).openConnection();

            String text = GetterOfDataFromWebsites.getTextFromAWebPage(connection);

            for (String ticker : getAllTickersFromWebPage(text)) {

                text = text.substring(text.indexOf(ticker));

                int startIndexOfCompanyName = text.indexOf(" ");
                int endIndexOfCompanyName = text.indexOf(" ", startIndexOfCompanyName + 1);
                endIndexOfCompanyName = text.indexOf(" ", endIndexOfCompanyName + 1);
                String companyName = text.substring(startIndexOfCompanyName + 1, endIndexOfCompanyName);

                if (!alreadyChecked.contains(ticker)) {
                    if (checkCompanyOnIndeed(companyName)) {
                        companies.put(companyName, ticker);
                        successCounter++;
                        System.out.println("(" + ticker + ") " + companyName + " added to \"companies\"");
                    }
                    alreadyChecked.add(ticker);
                    System.out.println("Total checked records: " + (alreadyChecked.size()));
                    System.out.println("Of them successfully: " + successCounter + "\n");
                }
                if (successCounter == TARGET_NUMBER_OF_COMPANIES) {
                    break;
                }
            }
            date = date.minusDays(1L);
            pageCounter++;
            System.out.println("Checked " + pageCounter + (pageCounter == 1 ? " web page" : " web pages"));
        }

        System.out.println("Company search completed" + "\n");

        return companies;
    }

    private static boolean checkCompanyOnIndeed(String companyName) {
        String url = "https://www.indeed.com/cmp/" + companyName + "/about";
        String text;
        try {
            URLConnection connection = new URL(url).openConnection();
            text = GetterOfDataFromWebsites.getTextFromAWebPage(connection);
        } catch (IOException e) {
            return false;
        }
        return text.contains("Industry") && text.contains("Headquarters") && text.contains("Links");
    }

    private static List<String> getAllTickersFromWebPage(String text) {
        List<String> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("[\\s]([A-Z]{1,4})[\\s][A-Z]([a-z]{1,15})");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String ticker = text.substring(matcher.start(), matcher.end()).trim();
            ticker = ticker.substring(0, ticker.indexOf(" "));

            if (!result.contains(ticker)) {
                result.add(ticker);
            }
        }

        return result;
    }
}
