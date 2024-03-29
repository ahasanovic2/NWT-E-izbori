# NWT-E-izbori
Projekat koji predstavlja simulaciju izbornog procesa u Bosni i Hercegovini.

## Članovi tima
1. [Ahmedin Hasanović](mailto:ahasanovic2@etf.unsa.ba)
2. [Vedad Dervišević](mailto:vdervisevi1@etf.unsa.ba)
3. [Ema Kalmar](mailto:ekalmar1@etf.unsa.ba)
4. [Amina Šehić](mailto:asehic2@etf.unsa.ba)

## Backend mikroservisi
Servisi pobrojani ispod čine backend dio aplikacije:
1. Config server
2. Eureka service
3. Api gateway
4. System events
5. Auth service
6. Election Management
7. Voting service
8. Result service
## Pokretanje putem Docker-a
Backend dio projekta je uvezan tako da je moguće mikroservise pokrenuti putem Docker kontejnera. Međutim, da bi se to uspjelo, potrebna je određena priprema. 
Instalacije koje su potrebne za pokretanje:
1. Docker
2. Poželjno je da onaj koji pokreće ima instaliran IntelliJ IDEA (JDK je obavezan)
3. Sublime text editor

Koraci koje je potrebno napraviti kako bi se kreirali Docker kontejneri i uspješno pokrenuli su sljedeći:
1. Potrebno je dodati ***JAVA_HOME*** sistemsku environment varijablu za pokretanje Java aplikacija putem komandne linije.
   1. U putanju je potrebno staviti putanju do direktorija u kojem se nalazi JDK instalacija. Primjer jedne takve je: ```C:\Users\ahasa\.jdks\openjdk-20.0.1```
2. Svaki od servisa navedenih u drugoj sekciji ovog dokumenta, izuzev servisa ***Config server*** i ***Eureka server***, sadrži ***wait-for-it.sh*** shell skriptu. Po defaultu, ta skripta je pisana u ***Windows*** encodingu. Za uspješno pokretanje putem Docker-a
potrebno je otvoriti svaku od tih skripti putem ***Sublime Text***-a i prebaciti line encoding na ***Unix*** putem sljedećih komandi: ***View->Line Endings->Unix*** i nakon toga spasiti datoteku
3. Kroz terminal (ili Command Prompt) potrebno je navigirati se do direktorija svakog mikroservisa i ukucati komandu: ```mvn clean package -D skipTests```
    1. Ovaj korak je neophodan kako bi se kreirali .jar datoteke koje će Docker koristiti pri izgradnji Images-a
4. Kroz terminal potrebno je navigirati se u direktorij u kojem se nalaze direktoriji svih mikroservisa
5. U terminalu ukucati komandu ```docker-compose up --build```
    1. Ovom komandom će se izgraditi slike i pokrenuti kontejneri.

## Video uredak
Na sljedećem linku prikazano je buildanje projekta putem dockera i prikaz svih funkcionalnosti preko frontenda: [VIDEO](https://www.youtube.com/playlist?list=PL06yrr3lRzGTUdAz4TFbXfj19k1e1_wiu). Prikaz frontend dijela i svih funkcionalnosti aplikacije počinje od 10:45 (10 minuta i 45 sekundi videa).
