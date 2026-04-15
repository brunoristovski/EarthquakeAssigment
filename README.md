Zdravo ova e Assigment zadaden od strana na Codeit Solution

Upatstvo za kako pravilno da se startuva kodot:

Se simnuva kako zip file

Se otvara proektot kako cela papka vo Intellij.
Gore levo izbirame File->Project Structure -> se stava JDK 17 verzija(mora so nea ,dokolku nemate mozete preku intellij da simnete)
Kaj Run kopceto imate 3 tocki kliknete na nego i na Edit kopceto - odbere SDK java 17(mora dokolku nemate mozete da simnete preku intellij)

Posle stavanje na JDK && SDK verzija 17 idat sledni cekori za navigacija vo lokalen terminal (pripazete na redosledot) ->

  cd EarthquakeAssigment-main
  cd backend
  docker compose up -d (kreirame lokalna baza so pomos na kontenirizacija)
  run na aplikacijata da ja iskreira data-schema 
  cd ..
  cd frontend
  cd earthuquake_assigment
  npm install
  npm run dev
  
VAZNO 
Koga kje odbirate filterByDate na samiot frontend treba da se odbere 2h povekje nego posakanoto vreme,
poradi toa sto od json formato koj se zema preku api postoi razlicni zoni an vreminja.
Na react odbiras lokalno vreme i go sporeduva so vremeto na zejmotresot koe si ima svoja zona na vreme.
Spored testovite treba 2h ponapred na posakuvaniot lokalen datum da se stavi za tocno da funkcionira.

Dokolku sakate da se povrzete na bazata preku Intellij(Data base manager - gore desno kjos) - potrebno da gi poplnite polinjata staveni vo docker-compose.yml koj se naogja vo backend papkata.

Ako sakate da ja Testirate aplikacijata treba:
Vo application properties da se stavi spring.profiles.active=test
A pri ranuvanje da se odbere klasata za test za run koja se naogja vo backend/test/java/imeto_na_klasata.java


Uzivajte vo koristenjeto na aplikacijata za Zemjotresi, slobodno iskomentirajte dobri/losi nacin na kodiranje ili bugs.

Okulu samata zadaca posto treba da se iskoristi backend i frontend:
So spring boot izgradiv backend app so layered kako domain,repo,services,web so pocituvanje na SOLID principi za dosta lesno i prakticno gradenje na monolitna applikacija koja kje ovozmozi ponatake skalabilnost i lesno testiranje.
Dodeka za front end iskoristiv react so isto taka layered del za pocist i porazbirliv kod.Ponatake moze lesno i citko da se nadogradi.
Bazata odluciv da ja napraam vo docker posto e najlesen nacin za da se izgradi lokalno.

