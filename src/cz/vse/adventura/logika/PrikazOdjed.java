package cz.vse.adventura.logika;

/**
 *  Třída PrikazOdjed implementuje pro hru příkaz odjeď.
 *
 */

class PrikazOdjed implements IPrikaz{

    private static final String NAZEV = "odjed";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazOdjed(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "odjeď".
     *  Metoda nejprve zkontroluje zda-li byl jako parametr zadán string "úkryt".
     *  Dále kontroluje jestli se hráč nachází v místnosti s názvem "parkovací_dům".
     *  Následně už jen kontroluje zda-li místnost obsauje všechny potřebné věci pro odjezd,
     *  a pokud ano, tak hráče přemístí do závěrečné místnosti.
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0 ){
            return "Nevím, kam mám jet.";
        } else if (parametry.length != 1){
            return "Nemůžu jet na dvě místa najednou.";
        }


        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        String smer = parametry[0];

        Prostor sousedniProstor = herniPlan.getAktualniProstor().vratSousedniProstor(smer);


        if (sousedniProstor == null) {
            return "Tam na motorce jet nemůžu!";
        }
        else if (!sousedniProstor.getNazev().contains("ukryt")){
            return "Tam na motorce jet nemůžu!";
        }
        else {
            if ( aktualniProstor.podminka(herniPlan.getAktualniProstor())) {
            herniPlan.setAktualniProstor(sousedniProstor);
            return "Odjel jsi na motorce do úkrytu.";
        } else

        return "Nemáš ještě potřebné věci pro odjezd!";

    }

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
