package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSPeliUtil {
    public static boolean onkoOkSiirto(String siirto) {
        return "k".equals(siirto) || "p".equals(siirto) || "s".equals(siirto);
    }
    
    public static String haeEnsimmaisenPelaajanSiirto(Scanner scanner) {
        System.out.print("Ensimmäisen pelaajan siirto: ");
        return scanner.nextLine();
    }
    
    public static String haeTekoalynSiirto(TekoalyInterf tekoaly) {
        String siirto = tekoaly.annaSiirto();
        System.out.println("Tietokone valitsi: " + siirto);
        return siirto;
    }
    
    public static void paataPeli(Tuomari tuomari) {
        System.out.println();
        System.out.println("Kiitos!");
        System.out.println(tuomari);
    }
}
