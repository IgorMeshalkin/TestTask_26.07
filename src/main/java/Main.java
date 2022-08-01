import web.CompaniesFinder;

public class Main {
    private final static int TARGET_NUMBER_OF_COMPANIES = 50;

    public static void main(String[] args) throws Exception {
        CompaniesFinder.findCompanies(TARGET_NUMBER_OF_COMPANIES);
    }
}