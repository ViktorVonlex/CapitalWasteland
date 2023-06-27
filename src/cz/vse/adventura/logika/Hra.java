package cz.vse.adventura.logika;

/**
 *  Třída Hra - třída představující logiku adventury.
 * 
 *  Toto je hlavní třída  logiky aplikace.  Tato třída vytváří instanci třídy HerniPlan, která inicializuje mistnosti hry
 *  a vytváří seznam platných příkazů a instance tříd provádějící jednotlivé příkazy.
 *  Vypisuje uvítací a ukončovací text hry.
 *  Také vyhodnocuje jednotlivé příkazy zadané uživatelem.
 *
 *@author     Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 *@version    pro školní rok 2016/2017
 */

public class Hra implements IHra {
    private SeznamPrikazu platnePrikazy;    // obsahuje seznam přípustných příkazů
    private HerniPlan herniPlan;
    private boolean konecHry = false;

    /**
     *  Vytváří hru a inicializuje místnosti (prostřednictvím třídy HerniPlan) a seznam platných příkazů.
     */
    public Hra() {
        herniPlan = new HerniPlan();
        platnePrikazy = new SeznamPrikazu();
        platnePrikazy.vlozPrikaz(new PrikazNapoveda(platnePrikazy));
        platnePrikazy.vlozPrikaz(new PrikazJdi(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazKonec(this));
        platnePrikazy.vlozPrikaz(new PrikazSeber(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPoloz(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdjed(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazProzkoumej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazPromluv(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazOdevzdej(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazInventar(herniPlan));
        platnePrikazy.vlozPrikaz(new PrikazZautoc(herniPlan));
    }

    /**
     *  Vrátí úvodní zprávu pro hráče.
     */
    public String vratUvitani() {
        return  "Vítejte ve hře Capital Wasteland!!! \n" +
                "----------------------------------------------------------------------------------------------\n" +
                "Svět je zpustošen jadernou válkou, kterou jen málokdo přežil.\n" +
               "Probouzíte se v opuštěném parkovacím domě uprostřed bývalého hlavního města.\n" +
               "Matně si vybavujete, že jste opustili bezpečí svého úkrytu, kvůli nedostatku\n" +
                "pitné vody. Po cestě vás však přepadla skupina banditů, která vás z neznámých\n" +
                "důvodů nechala naživu a zanechala právě zde...\n"+
                "----------------------------------------------------------------------------------------------\n"+
                "Vaším úkolem je uvést do provozu motorku, se kterou budete moci bez problému projet \n" +
                "nebezpečné město až do svého úkrytu. Na první pohled je vám jasné, že motorka je určitě\n" +
                "bez paliva. Rovněž bude potřeba najít klíčky od motorky...\n" +
                "Když si nebudete vědět rady, použijte příkaz 'nápověda'\n" +
                "----------------------------------------------------------------------------------------------\n" +
               herniPlan.getAktualniProstor().dlouhyPopis() + "\n";

    }
    
    /**
     *  Vrátí závěrečnou zprávu pro hráče.
     */
    public String vratEpilog() {
        return "Chystáš se na krátký odpočinek po náročné cestě, když najednou \n"
                + "zaslechneš venku několik hlasů... Bandité, kteří tě přepadli, tě nechali\n"
                + "naživu, jen aby tě mohli sledovat až tvého úkrytu plného zbraní a zásob.\n"
                + "Souboj je nevyhnutelný, pokračování příště...\n"
                + "----------------------------------------------------------------------------------------------\n"
                + "Díky, že jste si zahráli!!";
    }
    
    /** 
     * Vrací true, pokud hra skončila.
     */
     public boolean konecHry() {
        return konecHry;
    }

    /**
     *  Metoda zpracuje řetězec uvedený jako parametr, rozdělí ho na slovo příkazu a další parametry.
     *  Pak otestuje zda příkaz je klíčovým slovem  např. jdi.
     *  Pokud ano spustí samotné provádění příkazu.
     *
     *@param  radek  text, který zadal uživatel jako příkaz do hry.
     *@return          vrací se řetězec, který se má vypsat na obrazovku
     */
     public String zpracujPrikaz(String radek) {
        String [] slova = radek.split("[ \t]+");
        String slovoPrikazu = slova[0];
        String []parametry = new String[slova.length-1];
        for(int i=0 ;i<parametry.length;i++){
           	parametry[i]= slova[i+1];  	
        }
        String textKVypsani=" .... ";
        if (platnePrikazy.jePlatnyPrikaz(slovoPrikazu)) {
            IPrikaz prikaz = platnePrikazy.vratPrikaz(slovoPrikazu);
            textKVypsani = prikaz.provedPrikaz(parametry);
        }
        else {
            textKVypsani="Nevím co tím myslíš? Tento příkaz neznám. ";
        }
        return textKVypsani;
    }
    
    
     /**
     *  Nastaví, že je konec hry, metodu využívá třída PrikazKonec,
     *  mohou ji použít i další implementace rozhraní Prikaz.
     *  
     *  @param  konecHry  hodnota false= konec hry, true = hra pokračuje
     */
    void setKonecHry(boolean konecHry) {
        this.konecHry = konecHry;
    }
    
     /**
     *  Metoda vrátí odkaz na herní plán, je využita hlavně v testech,
     *  kde se jejím prostřednictvím získává aktualní místnost hry.
     *  
     *  @return     odkaz na herní plán
     */
     public HerniPlan getHerniPlan(){
        return herniPlan;
     }
    
}

