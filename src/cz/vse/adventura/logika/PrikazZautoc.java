package cz.vse.adventura.logika;

/**
 *  Třída PrikazZautoc implementuje pro hru příkaz zaútoč.
 *
 */

class PrikazZautoc implements IPrikaz{

    private static final String NAZEV = "zautoc";
    private final HerniPlan herniPlan;

    /**
     *  Konstruktor třídy
     *
     *  @param herniPlan herní plán, ve kterém se bude ve hře "chodit"
     */
    public PrikazZautoc(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     *  Provádí příkaz "zaútoč".
     *  Metoda kontroluje jestli má hráč u sebe věc "glock", jestli je zadané npc zabitelné a jestli je naživu.
     *  Pokud jsou tyto podmínky splněné, metoda nastaví pro npc hodnotu parametru nazivu na false a přidá hráči do inventáře
     *  věc, kterou má npc u sebe.
     *  Pokud některá z podmínek splněná není, vypíše se odpovídající chybová hláška.
     */
    @Override
    public String provedPrikaz(String... parametry) {

        if (parametry.length == 0) {
            return "Nevím, na koho zaútočit.";
        }

        String jmenoPostavy = parametry[0];

        Player hrac = herniPlan.getPlayer();

        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        Npc npc = aktualniProstor.vratNpc(jmenoPostavy);


        try  {

            if(hrac.getInvt().containsKey("glock") & npc.isZabitelna() & npc.isNazivu()) {
                npc.setNazivu(false);
                hrac.addInvtVec(npc.getVecCoMa());
                return "Zabil jsi postavu: " + npc.getName() + " a sebral jsi " + npc.getVecCoMa().toString();

            } else if (!hrac.getInvt().containsKey("glock")) {
                return "Nemáš u sebe použitelnou zbraň... Hodil by se ti glock";
            }

            else if (!npc.isZabitelna()){
                return "Tahle postava by se mi mohla ještě hodit...";
            }

            else if (!npc.isNazivu()){
                return "Tahle postava už je mrtvá...";
            }

        }
        catch(NullPointerException e)
        {
            System.out.print("Nikdo takový tady není.");
        }

        return "";

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
