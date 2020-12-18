# Where to eat

Az alkalmazás, egy éttermek adatainak feldolgozását valósítja meg. Az éttermekről való információkat eredetileg https://opentable.herokuapp.com kapta volna az alkalmazás, de sajnos ez nem volt megoldható így lett egy másik API. A jelenlegi api a következő: https://ratpark-api.imok.space.

Az alkalmazásomnak három fő oldala van: főoldal, profil hozzáadása és profilok listázása.

#Főoldal

A főoldalon első sorban megjelenik, egy kereső ahol a felhasználók kitudják választani a számukra megfelelő országot. Amiután ezt megtették listázódik az adott ország éttermeinek listája. Alapértelmezettként az US van beállítva.
Ha rálépünk az egyik étteremre a listából akkor előjön az étteremről több információ is. Ezen az oldalon megtudjuk nézni az éttermekről a több képet, ha van. Tudunk hozzáadni képet. Tudjuk a kedvenceinkhez tenni az éttermet. Itt tudni kell hogy mielőtt bekerülne az adatbázisba mint kedvenc étterem ki kell választani a felhasználót, hogy kinek is tegyük be. Ez azért van, mert nincs regisztrációs platform viszont én úgy írtam meg az alkalmazást, hogy több felhasználóra gondoltam. Ha netán meggondolnánk magunkat ki is tudjuk venni ezen az oldalon a kedvenceink közül. Ezen kívül mláthatunk több információt az étteremről és nem utolsó sorban vissza tudunk lépni a fő oldalra is.
 
#Profil hozzáadása

Mivel én több felhasználóval gondoltam így bevezettem egy profil hozzáadása fület is. Itt megkell adni a a felhasználó adatatit, mint például a név, cím, telefonszám, e-mail és profilkép. Ha az adatok megfelelőek akkor a hozzáadás után egyből át is kerülünk a Profil oldalra.

#Profil

A profil oldalon ki kell választani a saját profilt. Ha ez megtörténik akkor megjelennek a profil adatok mint például a név, a profilkép, a telefonszám stb. Ezeken kívül megjelennek a kedvenc éttermek listája is. Itt is ha rákattintunk egy elem listára előjönnek a bővebb információk az éttermekről. Itt is eltudjuk végezni a kép hozzáadást és azt is hogy ki tudjuk venni az adott éttermet a kedvencek közül.


