package io.htmlcss;
import io.htmlcss.api.databaseFetcher;

public class test {
    public static void main(String[] args) {
        databaseFetcher fetcher = new databaseFetcher();
        System.out.println(fetcher.getProducts());
        System.out.println(databaseFetcher.toJSON(fetcher.getProducts()));
    }
}
