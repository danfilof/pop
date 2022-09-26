package com.anfilofyev;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.*;

public class Searcher {

    static List<String> pops = Arrays.asList("one piece", "naruto", "avatar", "attack on titan", "demon slayer");
    static List<String> pops1 = Arrays.asList("funko pop avatar");
    //static List<String> pops1 = Arrays.asList("funko pop one piece", "funko pop naruto", "funko pop avatar", "funko pop attack on titan", "funko pop demon slayer");

    public static final String SEARCH_URL = "";

    public static void searchLoop() throws IOException {
//        for (int i = 0; i < pops.size(); i++) {
//            System.out.println("FIGURINE: " + pops.get(i).toUpperCase(Locale.ROOT));
//            firugines(pops.get(i));
//            System.out.println("==========================================================================");
//    }
//        System.out.println("=======================================");
//        System.out.println("=======================================");
//        System.out.println("=======================================");
//        System.out.println("=======================================");
//        System.out.println("=======================================");
//        System.out.println("=======================================");

        for (int i = 0; i < pops1.size(); i++) {
            System.out.println("GAMESTOP: " + pops1.get(i).toUpperCase(Locale.ROOT));
            gameStop(pops1.get(i));
            System.out.println("==========================================================================");
        }
    }



    public static void firugines(String searchTerm) throws IOException {

        final String SEARCH_URL = "https://www.figurines-goodies.com/recherche?search_query=";

        //Taking search term input from console
        Scanner scanner = new Scanner(System.in);


        String finalSearchTerm = searchTerm.replace(" ", "+");
        String reference = searchTerm.replace(" ", "-");

        scanner.close();
        String searchURL = SEARCH_URL + finalSearchTerm + "&submit_search=&autocaptcha=1306535573&orderby=quantity&orderway=desc";
        //without proper User-Agent, we will get 403 error
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        //below will print HTML data, save it to a file and open in browser to compare
        //System.out.println(doc.html());


        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("a[href]");

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();

            if (linkHref.contains(reference) && linkHref.contains("html") && linkText.contains("Disponible")){
                System.out.println(linkHref + " || " + linkText);
            }
        }
    }

    public static void gameStop(String searchTerm) throws IOException {

        ArrayList<String> doubled = new ArrayList<String>();


        final String SEARCH_URL = "https://www.gamestop.ch/SearchResult/QuickSearch?q=";

        String finalSearchTerm = searchTerm.replace(" ", "+");
        String reference = searchTerm.replace("funko pop", "");

        String searchURL = SEARCH_URL + finalSearchTerm;
        //without proper User-Agent, we will get 403 error
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        //below will print HTML data, save it to a file and open in browser to compare
        //System.out.println(doc.html());


        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly

        Elements results = doc.select("a[href]");

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();
            if (linkText.contains("POP!")) {
                String pop = "https://www.gamestop.ch/" + linkHref + " " + linkText;
               if (doubled.contains(pop)){
               } else {
                   doubled.add(pop);
               }
            }
        }
        for (int i = 0; i < doubled.size();i++)
        {
            System.out.println(doubled.get(i));
        }
    }
}
