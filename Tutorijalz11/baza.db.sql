BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "grad" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"broj_stanovnika"	INTEGER,
	"drzava"	INTEGER,
	"slika" TEXT,
	"postanski_broj" TEXT,
	PRIMARY KEY("id"),
	FOREIGN KEY("drzava") REFERENCES "drzava"("id")
);
CREATE TABLE IF NOT EXISTS "drzava" (
	"id"	INTEGER,
	"naziv"	TEXT,
	"glavni_grad"	INTEGER,
	PRIMARY KEY("id"),
	FOREIGN KEY("glavni_grad") REFERENCES "grad"("id")
);
INSERT INTO "grad" VALUES (1,'Pariz',2206488,1,"C:\Users\User\Desktop\2019_RPR_Projekti\Tutorijal11\resources\images\pariz.jpg", "75000");
INSERT INTO "grad" VALUES (2,'London',8825000,2, "C:\Users\User\Desktop\2019_RPR_Projekti\Tutorijal11\resources\images\london.jpg", "NW");
INSERT INTO "grad" VALUES (3,'Beč',1899055,3,"C:\Users\User\Desktop\2019_RPR_Projekti\Tutorijal11\resources\images\bec.jpeg", "1010");
INSERT INTO "grad" VALUES (4,'Manchester',545500,2, "C:\Users\User\Desktop\2019_RPR_Projekti\Tutorijal11\resources\images\manchester.jpeg", "M1");
INSERT INTO "grad" VALUES (5,'Graz',280200,3, "C:\Users\User\Desktop\2019_RPR_Projekti\Tutorijal11\resources\images\graz.jpg", "8010");
INSERT INTO "drzava" VALUES (1,'Francuska',1);
INSERT INTO "drzava" VALUES (2,'Velika Britanija',2);
INSERT INTO "drzava" VALUES (3,'Austrija',3);
COMMIT;
