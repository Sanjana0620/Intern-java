package placement;

import java.util.*;

public class LinkShortener {
    private Map<String, String> shortToLongMap;
    private Map<String, String> longToShortMap;
    private int urlCount;
    private static final String BASE_HOST = "https://short.url/";

    public LinkShortener() {
        this.shortToLongMap = new HashMap<>();
        this.longToShortMap = new HashMap<>();
        this.urlCount = 0;
    }

    // Method to generate a short URL for a given long URL
    public String shortenURL(String longURL) {
        if (longToShortMap.containsKey(longURL)) {
            return BASE_HOST + longToShortMap.get(longURL);
        } else {
            String shortURL = generateShortURL();
            shortToLongMap.put(shortURL, longURL);
            longToShortMap.put(longURL, shortURL);
            return BASE_HOST + shortURL;
        }
    }

    // Method to expand a short URL to its original long URL
    public String expandURL(String shortURL) {
        String shortKey = shortURL.substring(BASE_HOST.length());
        if (shortToLongMap.containsKey(shortKey)) {
            return shortToLongMap.get(shortKey);
        } else {
            return "Short URL does not exist in the system.";
        }
    }

    // Generate a short URL using a simple incrementing count
    private String generateShortURL() {
        String shortURL = String.valueOf(urlCount++);
        return shortURL;
    }

    public static void main(String[] args) {
        LinkShortener linkShortener = new LinkShortener();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nWelcome to Link Shortener System");
            System.out.println("1. Shorten URL");
            System.out.println("2. Expand URL");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.print("Enter the long URL to shorten: ");
                    String longURL = scanner.nextLine();
                    String shortenedURL = linkShortener.shortenURL(longURL);
                    System.out.println("Shortened URL: " + shortenedURL);
                    break;
                case 2:
                    System.out.print("Enter the short URL to expand: ");
                    String shortURL = scanner.nextLine();
                    String expandedURL = linkShortener.expandURL(shortURL);
                    System.out.println("Expanded URL: " + expandedURL);
                    break;
                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}
