
Capital Wasteland je krátká textová adventura, která se odehrává ve fiktivním světě, který byl zničen jadernou válkou.
Hlavním úkolem hráče je najít klíčky od motorky a palivo, aby mohl odjet do svého úkrytu.
Tyto věci lze najít prozkoumáváním prostorů a různými interakcemi s přítomnými NPC.

Jiří Šuster
Verze: java 8

Pozn.
1) Hru sice lze spustit přes konzoli bez problému, ale veškeré příkazy obsahující diakritiku jsou nefunkční, takže nelze opustit ani počáteční místnost.
Všechen ostatní text se zobrazuje správně. Zkoušel jsem cmd přepnout i do unicode a příkaz echo čřčžř funguje v pořádku, ale když spustím hru tak příkazy stále nefungují.

2) Během vytváření tříd PrikazProzkoumej a PrikazOdevzdej jsem se snažil ošetřit situaci, kdy by hráč zadal příkaz s chybějícím nebo úplně chybným parametrem.
Dlouho se mi to nedařilo a hra pokaždé úplně spadla, nakonec jsem to vzdal s tím, že se k tomu později vrátím. Nicméně když jsem se k tomu nakonec vrátil, problém
jakoby zázrakem úplně zmizel. Aniž bych v těchto třídách cokoli měnil nebo jinde upravoval, moje řešení najednou začalo fungovat.