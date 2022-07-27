import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception {
        int number = 1;
        for (Map.Entry<String, String> entry : CompaniesRegister.companies.entrySet()) {
            System.out.println(number + "- " + entry.getKey() + ": " + "\n"
                    + GetterOfDataFromWebsites.fromYahoo(entry.getValue()) + " "
                    + GetterOfDataFromWebsites.fromIndeed(entry.getKey()) + "\n");
            number++;
        }
    }
}
