package web;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CompaniesFinder {

    public static void findCompanies(int numberOfCompanies) throws IOException {

        int companiesCounter = 0;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();

        Set<String> alreadyChecked = new HashSet<>();

        while (companiesCounter < numberOfCompanies) {
            URLConnection connection = new URL("https://finance.yahoo.com/calendar/earnings?day="
                    + date.format(formatter))
                    .openConnection();

            String text = GetterOfDataFromWebsites.getTextFromAWebPage(connection);

            for (String ticker : getAllTickersFromWebPage(text)) {

                if (!alreadyChecked.contains(ticker)) {
                    alreadyChecked.add(ticker);

                    String companyName = getCompanyName(text.substring(text.indexOf(ticker)));

                    String dataFromYahoo = GetterOfDataFromWebsites.fromYahoo(ticker);
                    String dataFromIneed = GetterOfDataFromWebsites.fromIndeed(companyName);

                    if (dataFromYahoo != null && dataFromIneed != null) {
                        companiesCounter++;
                        System.out.println(companiesCounter + "- " + companyName + "\n"
                                + dataFromYahoo + " " + dataFromIneed + "\n");
                    }
                }
                if (companiesCounter == numberOfCompanies) {
                    break;
                }
            }
            date = date.minusDays(1L);
        }
    }

    private static Set<String> getAllTickersFromWebPage(String text) {
        Set<String> result = new HashSet<>();
        Pattern pattern = Pattern.compile("[\\s]([A-Z]{1,5})[\\s][A-Z]([a-z]{1,15})");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            String ticker = text.substring(matcher.start(), matcher.end()).trim();
            ticker = ticker.substring(0, ticker.indexOf(" "));
            result.add(ticker);
        }
        return result;
    }

    private static String getCompanyName(String text) {
        int startIndexOfCompanyName = text.indexOf(" ");
        int endIndexOfCompanyName = text.indexOf(" ", startIndexOfCompanyName + 1);
        endIndexOfCompanyName = text.indexOf(" ", endIndexOfCompanyName + 1);
        return text.substring(startIndexOfCompanyName + 1, endIndexOfCompanyName);
    }
}