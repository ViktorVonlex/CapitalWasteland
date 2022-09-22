package cz.vse.adventura.logika;

/**
 *  Třída PrikazInventar implementuje pro hru příkaz inventář.
 *
 */

class PrikazInventar implements IPrikaz{

    private static final String NAZEV = "inventář";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazInventar(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "inventář".
     *  Zkontroluje zda-li má hráč něco v inventáři, a podle toho vrátí buďto výpis obsahu inventáře,
     *  nebo vypíše hlášku "Nic u sebe nemáš."
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length != 0) {
            return "Tenhle příkaz napiš samostatně.";
        }

        Player hrac = herniPlan.getPlayer();

        if (hrac.getInvt().isEmpty()){
            return "Nic u sebe nemáš.";
        }

        return hrac.toString();

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
