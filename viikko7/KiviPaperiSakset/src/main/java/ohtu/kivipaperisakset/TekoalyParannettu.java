package ohtu.kivipaperisakset;

// "Muistava tekoäly"

import java.util.HashMap;
import java.util.Map;

public class TekoalyParannettu implements TekoalyInterf {

    private String[] muisti;
    private int vapaaMuistiIndeksi;

    public TekoalyParannettu(int muistinKoko) {
        muisti = new String[muistinKoko];
        vapaaMuistiIndeksi = 0;
    }

    public void asetaSiirto(String siirto) {
        // jos muisti täyttyy, unohdetaan viimeinen alkio
        if (vapaaMuistiIndeksi == muisti.length) {
            poistaViimeinenAlkio();
        }

        muisti[vapaaMuistiIndeksi] = siirto;
        vapaaMuistiIndeksi++;
    }

    public void poistaViimeinenAlkio() {
        for (int i = 1; i < muisti.length; i++) {
            muisti[i - 1] = muisti[i];
        }

        vapaaMuistiIndeksi--;
    }

    public String annaSiirto() {
        if (vapaaMuistiIndeksi == 0 || vapaaMuistiIndeksi == 1) {
            return "k";
        }
        
        Map<String, Integer> siirrot = laskeSiirtojenMaara();

        return palautaSiirtoAiempienSiirtojenMukaan(siirrot);
    }
    
    public Map<String, Integer> laskeSiirtojenMaara() {
        HashMap<String, Integer> siirrot = new HashMap<String, Integer>();
        siirrot.put("k", 0);
        siirrot.put("p", 0);
        siirrot.put("s", 0);
        
        String viimeisinSiirto = muisti[vapaaMuistiIndeksi - 1];
        
        for (int i = 0; i < vapaaMuistiIndeksi - 1; i++) {
            if (viimeisinSiirto.equals(muisti[i])) {
                String seuraava = muisti[i + 1];
                siirrot.put(seuraava, siirrot.get(seuraava)+1);
            }
        }
        
        return siirrot;
    }

    public String palautaSiirtoAiempienSiirtojenMukaan(Map<String, Integer> siirrot) {
        // Tehdään siirron valinta esimerkiksi seuraavasti;
        // - jos kiviä eniten, annetaan aina paperi
        // - jos papereita eniten, annetaan aina sakset
        // muulloin annetaan aina kivi
        int k = siirrot.get("k");
        int p = siirrot.get("p");
        int s = siirrot.get("s");
        
        if (k > p && k > s) {
            return "p";
        } else if (p > k && p > s) {
            return "s";
        } else {
            return "k";
        }
    }
}
