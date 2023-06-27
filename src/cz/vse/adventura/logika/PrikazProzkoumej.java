package cz.vse.adventura.logika;

/**
 *  Třída PrikazProzkoumej implementuje pro hru příkaz prozkoumej.
 *
 */

class PrikazProzkoumej implements IPrikaz{

    private static final String NAZEV = "prozkoumej";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazProzkoumej(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "prozkoumej".
     *  Metoda vrací textový řetězec popisu zadané věci."
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            return "Nevím, jaký předmět mám prozkoumat.";
        }

        String predmet = parametry[0];
        Player hrac = herniPlan.getPlayer();

        Vec vec = hrac.vratInvtVec(predmet);

        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Vec objekt = aktualniProstor.vratVec(predmet);

        try  {
            if (objekt == null) {
                return vec.getPopisPredmetu();
            }
            if (vec == null)
                return objekt.getPopisPredmetu();

        }
        catch(NullPointerException e)
            {
                System.out.print("Takový předmět se zde nenachází, ani ho nemám v inventáři.");
            }

   return "";}

    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
