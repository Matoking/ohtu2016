package ohtu.kivipaperisakset;

import java.util.Scanner;

public class Paaohjelma {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nValitse pelataanko"
                    + "\n (a) ihmistä vastaan "
                    + "\n (b) tekoälyä vastaan"
                    + "\n (c) parannettua tekoälyä vastaan"
                    + "\nmuilla valinnoilla lopetataan");

            String vastaus = scanner.nextLine();
            KPSPeli peli = luoPeli(vastaus.charAt(0));
            
            if (peli == null) {
                break;
            }
            
            System.out.println("peli loppuu kun pelaaja antaa virheellisen siirron eli jonkun muun kuin k, p tai s");
            peli.pelaa();
        }

    }
    
    public static KPSPeli luoPeli(char peli) {
        switch(peli) {
            case 'a':
                return new KPSPelaajaVsPelaaja();
            case 'b':
                return new KPSTekoaly(new Tekoaly());
            case 'c':
                return new KPSTekoaly(new TekoalyParannettu(20));
            default:
                return null;
        }
    }
}
