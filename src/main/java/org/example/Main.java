package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    /**
     * Fetches the full HTML source of the given URL.
     *
     * @param urlStr the URL to fetch
     * @return the raw HTML content as a String
     * @throws IOException if an I/O error occurs
     */
    public static String getPageSource(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        // Set HTTP method and headers
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0");
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        connection.connect();

        // Read the response stream
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream()))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            return content.toString();
        } finally {
            connection.disconnect();
        }
    }

    public static void main(String[] args) {
        String html="";
        try {
             html = getPageSource("https://al.ebileta.al/biglietteria/listaEventiPub.do");
            System.out.println(html);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(html.contains("serbia")){
            throw new RuntimeException("erdhen biletat");
        }
    }
}
