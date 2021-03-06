# Projekt 1 skupine Asi-FX
V tej mapi se nahaja delo/koda projekta **Projekt 1** skupine Asi-FX.

### Člani skupine:
- **Jaša Kert Pust**: _vodja_, front-end
- **David Jerman**: front-end, JavaFX specialist
- **Rok Mlinar Vahtar**: back-end
- **Gašper Suhadolnik**: back-end, dokumentacija

Pomembne povezave: [navodila](https://ekm.vegova.si/ekm1/main/wiki/index.php?cidReq=201314RSO04&id_session=26&gidReq=0&gradebook=0&origin=&action=showpage&title=opredelitev_projekta&group_id=0), [skupine](https://ekm.vegova.si/ekm1/main/wiki/index.php?cidReq=201314RSO04&id_session=26&gidReq=0&gradebook=0&origin=&action=showpage&title=projekt_1&group_id=0)

### Dokumentacija:

[**Razredni diagram**](https://gitlab.vegova.si/SerhioN/podjetje-sqmalsys/-/blob/Asi-FX/Asi-FX/dokumentacija/Razredni_Diagram/RazredniDiagram.png) se kot slika (.png) nahaja v mapi _dokumentacija/Razredni_Diagram/_.  
[**Dokumentacija**](https://gitlab.vegova.si/SerhioN/podjetje-sqmalsys/-/tree/Asi-FX/Asi-FX/dokumentacija/Dokumentacija) metod v razredu se nahaja v dokumentih v mapi _dokumentacija/Dokumentacija/_.  
_Opomba: priporočen je prenos in ekstrakcija arhivske datoteke [**dokumentacija_web.zip**](https://gitlab.vegova.si/SerhioN/podjetje-sqmalsys/-/raw/Asi-FX/Asi-FX/dokumentacija/Dokumentacija/dokumentacija_web.zip), saj se v njej nahajajo tudi javascript datoteke, ki omogočajo pravilno delovanje prikaza dokumentacije, ki jo je treba odpreti z brskalnikom._



---
### Changelog:  
_razvojne verzije_
- `25. 1. 2021:`
  - `ustvarjena mapa tega projekta, ustvarjen je ta dokument`
  - `spisano ogrodje za razred backend.java, dodajanje nekaterih privatnih funkcij`
  - `dodano ogrodje za grafično SQL aplikacijo`
- `26. 1. 2021:`
  - `dodajanje funkcionalnosti razredu backend.java (metode checkConnection(), setupNameIp_map())`
- `27. 1. 2021:`
  - `dodajanje funkcionalnosti razredu backend.java (metoda executeQuery(String sql))`
- `28. 1. 2021:`
  - `dodana večina grafičnih elementov (njihova funkcionalnost še ni implementirana)`
  - `dodajanje funkcionalnosti razredu backend.java 
    (metode: setCurrentDatabase(String database), getCustomQuery(String query), closeConnection(), changeDatabase(String database), queryToStringArray(String sql))`
- `29. 1.2021:`
  - `Grafični del: `
    - `spremenjen izgled aplikacije v "temni način"`
    - `dodani preostali grafični elementi, dodano ogrodje za izbirni meni`
    - `dopolnitev komentarjev`
  - `back-end:`
    - `dodajanje novih metod: getAllTables, getAllUsers, getDataSelect, getDataUpdate`
    - `dopolnitev komentarjev`
- `30. 1. 2021:`
  - `Grafični del: `
    - `dodana možnost izbiranja med temno in svetlo temo`
    - `urejanje kode (izbris nepotrebe kode, dodajanje komentarjev, ...)`
- `31. 1. 2021:`
  - `Grafični del: `
    - `dodajanje funkcionalnosti: povezava po meri, tooltip, nalaganje/shranjevanje konfiguracije`
    - `dodana metoda za polnjenje tabel`
  - `back-end:`
    - `dodani (spiani) metodi za zajem podatkov: getDataInsert, getDataDelete`
- `1. 2. 2021:`
  - `Grafični del: `
    - `dodajanje funkcionalnosti: polnjenje tabel s podatki`
    - `dodajanje metod: resetTableInfo, setTableAuthorToLabels, resetLabels`
    - `odpravljanje napak, dopolnitev komentarjev`
  - `back-end:`
    - `dodajanje funkcionalnosti (metod): ipToName, connect, disconnect`
    - `odpravljanje napak, dopolnitev komentarjev`
- `2. 2. 2021:`
  - `Grafični del: `
    - `dodajanje nove funkcionalnost: jezikovni paketi`
    - `dodan meni za spreminjanje jezika`
  - `back-end:`
    - `dodajanje nove funkcionalnosti: jezikovni paket`
    - `dodan nov razred: Language`
  - `podprti jeziki: slovenščina, angleščina, nemščina, ruščina, japonščina, kitajščina`
---
_verzija 1.0_
- `3. 2. 2021:`
  - `dodano polnjenje lista tabel za izbranega uporabnika`
  - `dodana podprta jezika: hrvaščina, mistični jezik`
  - `manjši popravki`
- `4. 2. 2021:`
  - `manjši popravki pri prikazu podatkov`