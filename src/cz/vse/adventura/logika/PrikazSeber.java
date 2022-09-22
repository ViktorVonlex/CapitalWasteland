package cz.vse.adventura.logika;

/**
 *  Třída PrikazSeber implementuje pro hru příkaz seber.
 *
 */

class PrikazSeber implements IPrikaz{

    private static final String NAZEV = "seber";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "seber".
     *  Zkontroluje zda-li se zadaná věc nachází v současném prostoru, jestli je sebratelná, a jestli má hráč ještě místo v inventáři.
     *  Pokud jsou tyto podmínky splněny, věc je přidáná hráči do inventáře a odstraněna z prostoru
     *  a vypíše se hláška "Věc byla sebrána" společně s obsahem inventáře.
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0 ){
            return "Nevím co mám sebrat.";
        } else if (parametry.length != 1){
            return "Snažíš se sebrat příliš mnoho věcí.";
        }

        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Vec vec = aktualniProstor.vratVec(nazevVeci);
        Player hrac = herniPlan.getPlayer();


        if (vec == null){
            return "Taková věc se zde nenachází.";
        } else if (!vec.isSebratelna()) {
            return "Tuto věc nelze sebrat.";
        }

        if (hrac.mistoInvt == 0) {
            return "Nemáš místo v inventáři!";
        }

        aktualniProstor.removeVec(nazevVeci);
        hrac.addInvtVec(vec);

        return "Věc byla sebrána" +"\n"
                + hrac.toString();
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
