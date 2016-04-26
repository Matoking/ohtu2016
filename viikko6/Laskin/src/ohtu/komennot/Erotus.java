package ohtu.komennot;

import javax.swing.JTextField;
import ohtu.Sovelluslogiikka;

/**
 *
 * @author matoking
 */
public class Erotus implements Komento {
    private Sovelluslogiikka sovellus;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    
    private int edellinenTulos;
    
    public Erotus(Sovelluslogiikka sovellus, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = sovellus;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
    }

    @Override
    public void suorita() {
        int arvo, tulos;
        
        try {
            arvo = Integer.parseInt(syotekentta.getText());
            tulos = Integer.parseInt(tuloskentta.getText());
        } catch (Exception e) {
            return;
        }
        
        edellinenTulos = tulos;
        tulos -= arvo;
        
        tuloskentta.setText("" + tulos);
    }

    @Override
    public void peru() {
        tuloskentta.setText("" + edellinenTulos);
    }
    
    
}
