package io.htmlcss;
import io.htmlcss.api.DatabaseFetcher;

public class test {
    public static void main(String[] args) {
        DatabaseFetcher fetcher = new DatabaseFetcher();
        System.out.println(fetcher.getProducts());
        System.out.println(DatabaseFetcher.toJSON(fetcher.getProducts()));
    }
}
