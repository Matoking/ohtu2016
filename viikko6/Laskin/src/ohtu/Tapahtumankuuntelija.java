package ohtu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JTextField;
import ohtu.komennot.Erotus;
import ohtu.komennot.Komento;
import ohtu.komennot.Nollaa;
import ohtu.komennot.Summa;
 
public class Tapahtumankuuntelija implements ActionListener {
    private JButton plus;
    private JButton miinus;
    private JButton nollaa;
    private JButton undo;
    private JTextField tuloskentta;
    private JTextField syotekentta;
    private Sovelluslogiikka sovellus;
    
    private Map<JButton, Komento> komennot;
    private Komento edellinen;
 
    public Tapahtumankuuntelija(JButton plus, JButton miinus, JButton nollaa, JButton undo, JTextField tuloskentta, JTextField syotekentta) {
        this.sovellus = new Sovelluslogiikka();
        
        this.plus = plus;
        this.miinus = miinus;
        this.nollaa = nollaa;
        this.undo = undo;
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        
        this.komennot = new HashMap<JButton, Komento>();
        this.komennot.put(plus, new Summa(sovellus, tuloskentta, syotekentta));
        this.komennot.put(miinus, new Erotus(sovellus, tuloskentta, syotekentta));
        this.komennot.put(nollaa, new Nollaa(sovellus, tuloskentta, syotekentta));
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        Komento komento = komennot.get(ae.getSource());
        
        if  (komento != null) {
            komento.suorita();
            edellinen = komento;
        } else {
            // toiminto oli undo
            edellinen.peru();
            edellinen = null;
        }

        nollaa.setEnabled(sovellus.tulos()!=0);
        undo.setEnabled(edellinen!=null);
    }
 
}