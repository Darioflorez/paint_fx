# Krav:

# Om Applikationen

Anv�ndaren ska kunna rita olika typer av objekt:
* linjer
* Ovaler
* Flerh�rningar
Genom att v�lja typ fr�n en meny eller knappsats.

Anv�ndaren ska kunna v�lja ett objekt f�r att:
* Ta bort
* �ndra egenskaper

Objekt egenskaper:
* F�rg
* Linjebredd
* Fyllning (av/p�)

Det ska vara m�jlig att spara och l�sa in filer med ritningar

# Om implementationen

programmet vara skrivet utifr�n de objektorienterade principer som inkapsling, h�g kohesion, l�g koppling, programmering mot interface (till skillnad
fr�n programmering mot implementationer) m.fl. principer.

Applikationen vara skriven med fokus p� att g�ra det l�tt att i efterhand utveckla programmet;
De ska vara relativt enkelt att l�gga till nya 
* vyer med altenentiva presentationer av modellen
* ritbara typer

Designm�nster att anv�nda:
* Model-View-Controller (separata paket f�r de olika delarna)
* Subject-Observer, t.ex. f�r att uppdatera olika delvyer d� modellen �ndrats
* Command f�r att implementera undo/redo-funktionalitet
* Template-metoder
* Fa�ade, t.ex. f�r att d�lja komplexitet i modellen
* N�gon form av skapelsem�nster f�r att skapa de ritbara objekten, ex. Prototype
* N�gon form av automatisk uppdatering av de knappar eller menyalternativ utifr�n vilka
ritbara typer som finns i modellen (t.ex. genom att unders�ka vilka abstract factory-metoder
som finns i modellen)

