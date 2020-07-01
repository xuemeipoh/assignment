/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebScraping;

import java.io.*;
import java.util.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

/**
 *
 * @author Poh Xue Mei
 */
public class WebScraping {

    static ArrayList<String> websites = new ArrayList<String>();
    static HashMap<String, String> ws = new HashMap<String, String>();

    /**
     */
    public static void getWebsite() {
        try {
            Scanner sc = new Scanner(new FileInputStream("top-1m.csv"));
            while (sc.hasNext()) {
                String website[] = sc.nextLine().split(",");
                websites.add(website[1]);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        getWebsite();
        for (int i = 0; i < websites.size(); i++) {
            try {
                Document doc = Jsoup.connect("https://" + websites.get(i)).get();
                String content = doc.body().text().toLowerCase();
                String[] wordArray = content.split(" ");
                boolean gotWord = false;
                for (int j = 0; j < wordArray.length; j++) {
                    for (Map.Entry me : ws.entrySet()) {
                        if (wordArray[j] == me.getKey()) {
                            gotWord = true;
                        }
                    }
                    if (!gotWord) {
                        ws.put(wordArray[j], websites.get(i));
                    }
                    else{
                        ws.put(wordArray[j], ws.get(wordArray[j])+websites.get(i));
                    }
                }

            } catch (IOException e) {
            }
        }
        for (Map.Entry me : ws.entrySet()) {
            System.out.println("Key: " + me.getKey() + " & Value: " + me.getValue());
        }
        Scanner scn = new Scanner(System.in);
        System.out.println("Enter");
        String a = scn.next();
        
        try {
            FileOutputStream fos = new FileOutputStream("hashmap.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(ws);
            oos.close();
            fos.close();
            System.out.printf("Serialized HashMap data is saved in hashmap.ser");
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

}
