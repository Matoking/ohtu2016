package ohtu.kivipaperisakset;

import java.util.Scanner;

public class KPSTekoaly implements KPSPeli {

    private static final Scanner scanner = new Scanner(System.in);

    private TekoalyInterf tekoaly;
    
    public KPSTekoaly(TekoalyInterf tekoaly) {
        this.tekoaly = tekoaly;
    }
    
    public void pelaa() {
        Tuomari tuomari = new Tuomari();

        String ekanSiirto = KPSPeliUtil.haeEnsimmaisenPelaajanSiirto(scanner);
        String tokanSiirto = KPSPeliUtil.haeTekoalynSiirto(tekoaly);

        while (KPSPeliUtil.onkoOkSiirto(ekanSiirto) && KPSPeliUtil.onkoOkSiirto(tokanSiirto)) {
            tuomari.kirjaaJaTulostaSiirto(ekanSiirto, tokanSiirto);
            
            ekanSiirto = KPSPeliUtil.haeEnsimmaisenPelaajanSiirto(scanner);
            tokanSiirto = KPSPeliUtil.haeTekoalynSiirto(tekoaly);
            
            tekoaly.asetaSiirto(ekanSiirto);
        }

        KPSPeliUtil.paataPeli(tuomari);
    }
}