import web.CompanyFinder;
import web.GetterOfDataFromWebsites;

import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        Map<String, String> companies = CompanyFinder.findCompany();

        System.out.println("Started searching and merging fields \"Industry\"..." + "\n");

        int number = 1;
        for (Map.Entry<String, String> entry : companies.entrySet()) {
            System.out.println(number + "- " + entry.getKey() + ": " + "\n"
                    + GetterOfDataFromWebsites.fromYahoo(entry.getValue()) + " "
                    + GetterOfDataFromWebsites.fromIndeed(entry.getKey()) + "\n");
            number++;
        }

        System.out.println("Finish");


    }
}
