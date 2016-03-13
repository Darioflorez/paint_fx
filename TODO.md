# Krav:

# Om Applikationen

Användaren ska kunna rita olika typer av objekt:
* ~~linjer~~
* ~~Ovaler~~
* ~~Flerhörningar~~
* ~~Spara och ladda från fil~~

Genom att välja typ från en meny eller knappsats.

Användaren ska kunna välja ett objekt för att:
* ~~Ta bort~~
* ~~Ändra egenskaper~~

Objekt egenskaper:
* ~~Färg~~
* ~~Linjebredd~~
* ~~Fyllning (av/på)~~

Det ska vara möjlig att spara och läsa in filer med ritningar

# Om implementationen

programmet vara skrivet utifrån de objektorienterade principer som inkapsling, hög kohesion, låg koppling, programmering mot interface (till skillnad
från programmering mot implementationer) m.fl. principer.

Applikationen vara skriven med fokus på att göra det lätt att i efterhand utveckla programmet;
De ska vara relativt enkelt att lägga till nya 
* ~~vyer med altenentiva presentationer av modellen~~
* ~~ritbara typer~~

Designmönster att använda:
* ~~Model-View-Controller (separata paket för de olika delarna)~~
* Subject-Observer, t.ex. för att uppdatera olika delvyer då modellen ändrats
* ~~Command för att implementera undo/redo-funktionalitet~~
* ~~Template-metoder~~
* ~~Facade, t.ex. för att dölja komplexitet i modellen~~
* ~~Någon form av skapelsemönster för att skapa de ritbara objekten, ex. Prototype~~
* ~~Någon form av automatisk uppdatering av de knappar eller menyalternativ utifrån vilka
ritbara typer som finns i modellen (t.ex. genom att undersöka vilka abstract factory-metoder
som finns i modellen)~~

