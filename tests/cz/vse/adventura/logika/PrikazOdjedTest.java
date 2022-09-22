package cz.vse.adventura.logika;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/*******************************************************************************
 * Testovací třída PrikazOdjedTest slouží k otestování
 * třídy PrikazOdjed
 *
 */
public class PrikazOdjedTest {

    private Hra hra1;

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @Before
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @After
    public void tearDown() {
    }

    /***************************************************************************
     * Testuje, že lze odjet do závěrečné místnosti až po položení všech tří potřebných věcí.
     */
    @Test
    public  void testLzeOdjet(){
        Vec benzin = new Vec ("benzín", true,"",false,true);
        Vec motorka = new Vec("motorka",false,"",false, true);
        Vec klicky = new Vec("klíčky", true,"Klíčky od motorky v parkovacím domě.",true,true);
        hra1.getHerniPlan().getAktualniProstor().addVec(benzin);
        hra1.getHerniPlan().getAktualniProstor().addVec(motorka);
        hra1.zpracujPrikaz("odjeď úkryt");
        assertEquals("parkovací_dům",hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.getHerniPlan().getAktualniProstor().addVec(klicky);
        hra1.zpracujPrikaz("odjeď úkryt");
        assertEquals("úkryt", hra1.getHerniPlan().getAktualniProstor().getNazev());

    }
}
