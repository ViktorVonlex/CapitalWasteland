package cz.vse.adventura.logika;

/**
 *  Třída PrikazPoloz implementuje pro hru příkaz polož.
 *
 */

class PrikazPoloz implements IPrikaz {

    private static final String NAZEV = "polož";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */

    public PrikazPoloz(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "polož".
     *  Metoda zkontroluje zda-li má hráč v inventáři věc, kterou se snaží položit.
     *  Pokud ano, tak se daná věc odstraní hráči z inventáře a položí se do současné místnosti.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 ){
            return "Nevím co mám položit.";
        } else if (parametry.length != 1){
            return "Snažíš se položit příliš mnoho věcí.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Player hrac = herniPlan.getPlayer();

        if (hrac.vratInvtVec(nazevVeci)==null) {
            return "Nevím co mám položit.";
        }


        aktualniProstor.addVec(hrac.vratInvtVec(nazevVeci));
        hrac.removeInvtVec(nazevVeci);

        return "Věc byla položena";
    }

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
