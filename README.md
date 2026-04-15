Zdravo ova e Assigment zadaden od strana na Codeit Solution

Upatstvo za kako pravilno da se startuva kodot:

-Se simnuva kako zip file
-Se ranuva celiot proekt vo Intellij,stom kje se otvori potrebno e da se postavi SDK && JDK 17 verzija,zatoa sto vo pom.xml file e staveno deka raboti so niv.
-Posle stavanje na pravilen SDK && JDK 17 verzija, treba da se vleze vo backend papkata vo terminal i so docker da se iskoristi komanda linija ( docker compose up -d),
  so ova kje se sozdade kontenjer za Postgres bazata koja lokalno kje raboti.
-Dokolku sakate da se povrzete na bazata preku Intellij(Data base manager - gore desno kjos) - potrebno da gi poplnite polinjata staveni vo docker-compose.yml koj se naogja vo backend papkata.

Za da se ranuva backend delot treba da bidete vnimatelni zatoa sto postoi dev-properties i test-properties koj treba da navede koj sakate da go koristite.

Ako sakate da ja vklucite aplikacijata i da raboti treba:
  Vo application properties da se stavi sledniov kod - spring.profiles.active=dev
  A pri ranuvanje na aplikacijata da se odbere glavnata klasa.

Ako sakate da ja testirate aplikacijata treba:
Vo application properties da se stavi spring.profiles.active=test
A pri ranuvanje da se odbere klasata za test za run

Na kraj ostanuva front end delot.
Dokolku ste go izvrsile ova so pomos na cd se vrakjate na glavniot folder i se navigirate vo frontend i sledna navigacija vo papkata earthquake_assigment
Posle navigacijata vleguvate vo terminal treba sledniot kod - npm run dev za da startne aplikacijata.

VAZNO 
Koga kje odbirate filterByDate na samiot frontend treba da se odbere 2h povekje nego posakanoto vreme,
poradi toa sto od json formato koj se zema preku api postoi razlicni zoni an vreminja.
Na react odbiras lokalno vreme i go sporeduva so vremeto na zejmotresot koe si ima svoja zona na vreme.
Spored testovite treba 2h ponapred na posakuvaniot lokalen datum da se stavi za tocno da funkcionira.

Uzivajte vo koristenjeto na aplikacijata za Zemjotresi, slobodno iskomentirajte dobri/losi nacin na kodiranje ili bugs.

Okulu samata zadaca posto treba da se iskoristi backend i frontend:
So spring boot izgradiv backend app so layered kako domain,repo,services,web so pocituvanje na SOLID principi za dosta lesno i prakticno gradenje na monolitna applikacija koja kje ovozmozi ponatake skalabilnost i lesno testiranje.
Dodeka za front end iskoristiv react so isto taka layered del za pocist i porazbirliv kod.Ponatake moze lesno i citko da se nadogradi.
Bazata odluciv da ja napraam vo docker posto e najlesen nacin za da se izgradi lokalno.

