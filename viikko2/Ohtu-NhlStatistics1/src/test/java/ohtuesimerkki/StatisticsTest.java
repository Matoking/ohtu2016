package ohtuesimerkki;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author matoking
 */
public class StatisticsTest {
    
    Statistics stats;
    Reader readerStub = new Reader() {
        public List<Player> getPlayers() {
            ArrayList<Player> players = new ArrayList<Player>();

            players.add(new Player("Semenko", "EDM", 4, 12));
            players.add(new Player("Lemieux", "PIT", 45, 54));
            players.add(new Player("Kurri",   "EDM", 37, 53));
            players.add(new Player("Yzerman", "DET", 42, 56));
            players.add(new Player("Gretzky", "EDM", 35, 89));

            return players;
        }
    }
    
    public StatisticsTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        stats = new Statistics(readerStub);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void pelaajaLoytyyNimenPerusteella() {
        Player loytynyt = stats.search("Lemieux");
        
        assertEquals(loytynyt.getName(), "Lemieux");
        assertEquals(loytynyt.getPoints(), 99);
    }
    
    @Test
    public void searchPalauttaaNullJosPelaajaaEiLoydy() {
        Player kadonnut = stats.search("Joku");
        
        assertEquals(kadonnut, null);
    }
    
    @Test
    public void pelaajatLoytyvatJoukkueenPerusteella() {
        List<Player> pelaajat = stats.team("EDM");
        
        assertEquals(pelaajat.size(), 3);
    }

    @Test
    public void pelaajatPisteidenMukaisessaJarjestyksessa() {
        List<Player> players = stats.topScorers(4);
        
        assertEquals(players.get(0).getPoints(), 124);
        assertEquals(players.get(1).getPoints(), 99);
        assertEquals(players.get(2).getPoints(), 98);
        assertEquals(players.get(3).getPoints(), 90);
        assertEquals(players.get(4).getPoints(), 16);
    }
}
