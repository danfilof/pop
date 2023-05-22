package com.anfilofyev;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.*;

public class Searcher {

    static List<String> pops = Arrays.asList("one piece", "naruto", "avatar", "attack on titan");
    static List<String> pops1 = Arrays.asList("funko pop one piece", "funko pop naruto", "funko pop avatar", "funko pop attack on titan");
    //static List<String> pops1 = Arrays.asList("funko pop naruto");


    public static final String SEARCH_URL = "";

    public static void searchLoop() throws IOException {

        //FIGURINES LOOP
        for (int i = 0; i < pops.size(); i++) {
            System.out.println("FIGURINE: " + pops.get(i).toUpperCase(Locale.ROOT));
            firugines(pops.get(i));
            System.out.println("==========================================================================");
    }
        System.out.println("=======================================");


        // ARCHONIA LOOP
        for (int i = 0; i < pops1.size(); i++) {
            System.out.println("ARCHONIA: " + pops1.get(i).toUpperCase(Locale.ROOT));
            archonia(pops1.get(i));
            System.out.println("==========================================================================");
        }

    }



    public static void firugines(String searchTerm) throws IOException {

        final String SEARCH_URL = "https://www.figurines-goodies.com/recherche?search_query=";


        String finalSearchTerm = searchTerm.replace(" ", "+");
        String reference = searchTerm.replace(" ", "-");

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
                System.out.println(linkText);
            }
        }
    }

    public static void archonia(String searchTerm) throws IOException {

        final String SEARCH_URL = "https://www.archonia.com/en-us/search?q-a2%5B0%5D=1&filter_string=";


        String finalSearchTerm = searchTerm.replace(" ", "%20");
        String replacePOP = searchTerm.replace("funko pop", "");
        String reference = searchTerm.replace(" ", "-");

        String searchURL = SEARCH_URL + finalSearchTerm;
        //without proper User-Agent, we will get 403 error
        Document doc = Jsoup.connect(searchURL).userAgent("Mozilla/5.0").get();

        //below will print HTML data, save it to a file and open in browser to compare
        //System.out.println(doc.html());


        //If google search results HTML change the <h3 class="r" to <h3 class="r1"
        //we need to change below accordingly
        Elements results = doc.select("a[href]");
        //System.out.println(results);

        for (Element result : results) {
            String linkHref = result.attr("href");
            String linkText = result.text();


            if (linkText.contains("Pop")){
                System.out.println(linkText);
            }
        }
    }


}
