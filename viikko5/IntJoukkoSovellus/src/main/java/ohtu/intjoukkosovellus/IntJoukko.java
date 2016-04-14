
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int kasvatuskoko;     // Uusi taulukko on tämän verran vanhaa suurempi.
    private int[] ljono;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        this(OLETUSKASVATUS);
    }

    public IntJoukko(int kapasiteetti) {
        this(kapasiteetti, OLETUSKASVATUS);

    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0) {
            throw new IndexOutOfBoundsException("Kapasiteetti ei voi olla negatiivinen");
        } else if (kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Kasvatuskoko ei voi olla negatiivinen");
        }
        
        luoJono(kapasiteetti, kasvatuskoko);
    }
    
    private void luoJono(int kapasiteetti, int kasvatuskoko) {
        ljono = new int[kapasiteetti];
        for (int i = 0; i < ljono.length; i++) {
            ljono[i] = 0;
        }
        alkioidenLkm = 0;
        this.kasvatuskoko = kasvatuskoko;
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            lisaaLuku(luku);
            if (alkioidenLkm % ljono.length == 0) {
                kasvataJonoa();
            }
            return true;
        }
        return false;
    }
    
    public void lisaaLuku(int luku) {
        ljono[alkioidenLkm] = luku;
        alkioidenLkm++;
    }
    
    public void kasvataJonoa() {
        int[] taulukkoOld = new int[ljono.length];
        taulukkoOld = ljono;
        kopioiTaulukko(ljono, taulukkoOld);
        ljono = new int[alkioidenLkm + kasvatuskoko];
        kopioiTaulukko(taulukkoOld, ljono);
    }

    public boolean kuuluu(int luku) {
        return getLukuId(luku) != -1;
    }

    public boolean poista(int luku) {
        int id = getLukuId(luku);
        
        if (id != -1) {
            poistaId(id);
            return true;
        }
        
        return false;
    }
    
    public int getLuku(int id) {
        if (alkioidenLkm > id) {
            return ljono[id];
        } else {
            return -1;
        }
    }
    
    public int getLukuId(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == ljono[i]) {
                return i;
            }
        }
        
        return -1;
    }
    
    private void poistaId(int id) {
        int apu;
        
        for (int j = id; j < alkioidenLkm - 1; j++) {
            apu = ljono[j];
            ljono[j] = ljono[j + 1];
            ljono[j + 1] = apu;
        }
        alkioidenLkm--;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int getAlkioidenLkm() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0) {
            return "{}";
        } else if (alkioidenLkm == 1) {
            return "{" + ljono[0] + "}";
        } else {
            return formatoiJono();
        }
    }
    
    private String formatoiJono() {
        String tuotos = "{";
        for (int i = 0; i < alkioidenLkm - 1; i++) {
            tuotos += ljono[i];
            tuotos += ", ";
        }
        tuotos += ljono[alkioidenLkm - 1];
        tuotos += "}";
        return tuotos;
    }

    public int[] toIntArray() {
        int[] taulu = new int[alkioidenLkm];
        for (int i = 0; i < taulu.length; i++) {
            taulu[i] = ljono[i];
        }
        return taulu;
    }
    
    public void concat(IntJoukko joukko) {
        int id = 0;
        while (joukko.getLuku(id++) != -1) {
            lisaa(joukko.getLuku(id-1));
        }
    }
    
    public void poistaJoukko(IntJoukko joukko) {
        int id = 0;
        while (joukko.getLuku(id++) != -1) {
            poista(joukko.getLuku(id-1));
        }
    }
    
    public void slice(IntJoukko a, IntJoukko b) {
        int id = 0;
        while (a.getLuku(id++) != -1) {
            int luku = a.getLuku(id-1);
            if (b.kuuluu(luku)) {
                lisaa(luku);
            }
        }
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko x = new IntJoukko();
        x.concat(a);
        x.concat(b);
        return x;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko y = new IntJoukko();
        y.slice(a, b);
        return y;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko z = new IntJoukko();
        z.concat(a);
        z.poistaJoukko(b);
        return z;
    }
        
}