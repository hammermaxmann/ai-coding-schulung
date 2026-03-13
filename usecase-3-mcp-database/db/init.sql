-- ============================================================
-- Food Facts Datenbank - Workshop Use Case 4
-- ============================================================
-- HINWEIS: Dieses Schema enthaelt ABSICHTLICHE Probleme
-- als Lehr-Material fuer den Workshop:
--
--   1. Deutsch/Englisch gemischte Spaltennamen
--   2. Allergene als kommaseparierter TEXT (Denormalisierung)
--   3. Foreign Key auf barcode statt id (naehrwerte)
--   4. Timestamps als TEXT statt TIMESTAMP
--   5. Fehlende Indizes auf Junction Table
--   6. Kein ON DELETE CASCADE
--   7. Inkonsistente Namensgebung (nova_gruppe vs. eco_score)
-- ============================================================

-- Haupttabelle: Produkte
CREATE TABLE produkte (
    id SERIAL PRIMARY KEY,
    barcode VARCHAR(20) UNIQUE NOT NULL,
    produkt_name VARCHAR(255),
    produkt_name_de VARCHAR(255),
    marke VARCHAR(100),
    menge VARCHAR(50),
    verpackung VARCHAR(100),
    nutri_score VARCHAR(1),                -- absichtlich: VARCHAR statt CHAR
    nutri_score_punkte INTEGER,
    nova_gruppe INTEGER,                   -- absichtlich: deutsch
    eco_score VARCHAR(1),                  -- absichtlich: englisch (inkonsistent)
    eco_score_punkte INTEGER,
    allergene TEXT,                         -- absichtlich: kommasepariert statt normalisiert
    spuren TEXT,                            -- absichtlich: kommasepariert
    zutaten_text TEXT,
    zutaten_text_de TEXT,
    bild_url VARCHAR(500),
    erstellt_am TEXT,                       -- absichtlich: TEXT statt TIMESTAMP
    aktualisiert_am TEXT                   -- absichtlich: TEXT statt TIMESTAMP
);

-- Naehrwerte (absichtlich: FK auf barcode statt id)
CREATE TABLE naehrwerte (
    id SERIAL PRIMARY KEY,
    produkt_barcode VARCHAR(20) REFERENCES produkte(barcode),  -- absichtlich: barcode statt id
    kcal DOUBLE PRECISION,                 -- absichtlich: verkuerzter Name
    energie_kj DOUBLE PRECISION,
    fett DOUBLE PRECISION,
    gesaettigte_fettsaeuren DOUBLE PRECISION,
    kohlenhydrate DOUBLE PRECISION,
    zucker DOUBLE PRECISION,
    ballaststoffe DOUBLE PRECISION,
    eiweiss DOUBLE PRECISION,
    salz DOUBLE PRECISION,
    natrium DOUBLE PRECISION
);

-- Kategorien
CREATE TABLE kategorien (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

-- Junction Table (absichtlich: kein separater Index, kein CASCADE)
CREATE TABLE produkt_kategorien (
    produkt_id INTEGER REFERENCES produkte(id),        -- absichtlich: kein CASCADE
    kategorie_id INTEGER REFERENCES kategorien(id),    -- absichtlich: kein CASCADE
    PRIMARY KEY (produkt_id, kategorie_id)
);

-- Naehrstoff-Levels
CREATE TABLE naehrstoff_levels (
    id SERIAL PRIMARY KEY,
    produkt_barcode VARCHAR(20) REFERENCES produkte(barcode),
    fett_level VARCHAR(10),
    salz_level VARCHAR(10),
    zucker_level VARCHAR(10),
    gesaettigte_fettsaeuren_level VARCHAR(10)
);


-- ============================================================
-- Seed-Daten: 5 bekannte deutsche Produkte
-- ============================================================

-- 1. Nutella
INSERT INTO produkte (barcode, produkt_name, produkt_name_de, marke, menge, verpackung,
    nutri_score, nutri_score_punkte, nova_gruppe, eco_score, eco_score_punkte,
    allergene, spuren, zutaten_text, zutaten_text_de, bild_url, erstellt_am, aktualisiert_am)
VALUES (
    '3017620422003', 'Nutella', 'Nutella Nuss-Nougat-Creme', 'Ferrero', '450 g', 'Glas',
    'e', 26, 4, 'd', 28,
    'Haselnuesse, Milch, Soja', 'Weizen',
    'Zucker, Palmoel, HASELNUESSE (13%), Magermilchpulver (8,7%), fettarmer Kakao (7,4%), Emulgator: Lecithine (Soja), Vanillin.',
    'Zucker, Palmoel, HASELNUESSE (13%), Magermilchpulver (8,7%), fettarmer Kakao (7,4%), Emulgator: Lecithine (Soja), Vanillin.',
    'https://images.openfoodfacts.org/images/products/301/762/042/2003/front_de.jpg',
    '2012-06-20 14:33:06', '2024-03-10 08:00:00'
);

-- 2. Haribo Goldbaeren
INSERT INTO produkte (barcode, produkt_name, produkt_name_de, marke, menge, verpackung,
    nutri_score, nutri_score_punkte, nova_gruppe, eco_score, eco_score_punkte,
    allergene, spuren, zutaten_text, zutaten_text_de, erstellt_am, aktualisiert_am)
VALUES (
    '4001686301104', 'Haribo Goldbaeren', 'Haribo Goldbaeren', 'Haribo', '200 g', 'Tuete',
    'd', 18, 4, 'c', NULL,
    '', NULL,
    'Glukosesirup, Zucker, Gelatine, Saeuerungsmittel: Citronensaeure, Frucht- und Pflanzenkonzentrate, Aroma, Ueberzugsmittel: Bienenwachs, Carnaubawachs.',
    'Glukosesirup, Zucker, Gelatine, Saeuerungsmittel: Citronensaeure, Frucht- und Pflanzenkonzentrate, Aroma, Ueberzugsmittel: Bienenwachs, Carnaubawachs.',
    '2013-09-24 12:00:00', '2024-03-10 08:00:00'
);

-- 3. Milka Alpenmilch Schokolade
INSERT INTO produkte (barcode, produkt_name, produkt_name_de, marke, menge, verpackung,
    nutri_score, nutri_score_punkte, nova_gruppe, eco_score, eco_score_punkte,
    allergene, spuren, zutaten_text, zutaten_text_de, erstellt_am, aktualisiert_am)
VALUES (
    '7622210100014', 'Milka Alpenmilch', 'Milka Alpenmilch Schokolade', 'Milka', '100 g', 'Plastik, Karton',
    'e', 27, 4, 'd', NULL,
    'Milch, Haselnuesse, Soja', 'Weizen, Ei',
    'Zucker, Kakaobutter, MAGERMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator (SOJALECITHIN), HASELNUSSPASTE, Aroma.',
    'Zucker, Kakaobutter, MAGERMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator (SOJALECITHIN), HASELNUSSPASTE, Aroma.',
    '2013-02-05 10:00:00', '2024-03-10 08:00:00'
);

-- 4. Fritz-Kola
INSERT INTO produkte (barcode, produkt_name, produkt_name_de, marke, menge, verpackung,
    nutri_score, nutri_score_punkte, nova_gruppe, eco_score, eco_score_punkte,
    allergene, spuren, zutaten_text, zutaten_text_de, erstellt_am, aktualisiert_am)
VALUES (
    '4260107220022', 'Fritz-Kola', 'Fritz-Kola', 'Fritz-Kulturgut', '330 ml', 'Glasflasche',
    'e', 14, 4, 'b', NULL,
    '', NULL,
    'Wasser, Zucker, Kohlensaeure, Farbstoff: Zuckerkuloer, natuerliches Koffein, Saeuerungsmittel: Phosphorsaeure, natuerliches Aroma.',
    'Wasser, Zucker, Kohlensaeure, Farbstoff: Zuckerkuloer, natuerliches Koffein, Saeuerungsmittel: Phosphorsaeure, natuerliches Aroma.',
    '2014-05-14 16:00:00', '2024-03-10 08:00:00'
);

-- 5. Ritter Sport Voll-Nuss
INSERT INTO produkte (barcode, produkt_name, produkt_name_de, marke, menge, verpackung,
    nutri_score, nutri_score_punkte, nova_gruppe, eco_score, eco_score_punkte,
    allergene, spuren, zutaten_text, zutaten_text_de, erstellt_am, aktualisiert_am)
VALUES (
    '4000417025005', 'Ritter Sport Voll-Nuss', 'Ritter Sport Voll-Nuss', 'Ritter Sport', '100 g', 'Plastik, Karton',
    'e', 25, 4, 'c', NULL,
    'Milch, Haselnuesse, Soja', 'Weizen, Ei, Mandeln',
    'Zucker, HASELNUSSKERNE (20%), Kakaobutter, VOLLMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator: SOJALECITHIN, natuerliches Aroma.',
    'Zucker, HASELNUSSKERNE (20%), Kakaobutter, VOLLMILCHPULVER, Kakaomasse, SUESSMOLKENPULVER, BUTTERREINFETT, Emulgator: SOJALECITHIN, natuerliches Aroma.',
    '2013-06-01 09:00:00', '2024-03-10 08:00:00'
);


-- Naehrwerte

INSERT INTO naehrwerte (produkt_barcode, kcal, energie_kj, fett, gesaettigte_fettsaeuren, kohlenhydrate, zucker, ballaststoffe, eiweiss, salz, natrium)
VALUES
    ('3017620422003', 539.0, 2255.0, 30.9, 10.6, 57.5, 56.3, 0.0, 6.3, 0.107, 0.043),
    ('4001686301104', 343.0, 1436.0, 0.5, 0.1, 77.0, 46.0, 0.0, 6.9, 0.02, 0.008),
    ('7622210100014', 530.0, 2218.0, 29.5, 18.0, 59.0, 58.0, 1.0, 6.3, 0.3, 0.12),
    ('4260107220022', 42.0, 176.0, 0.0, 0.0, 10.1, 10.1, 0.0, 0.0, 0.01, 0.004),
    ('4000417025005', 545.0, 2281.0, 36.0, 14.0, 44.0, 42.0, 3.0, 9.5, 0.24, 0.096);


-- Naehrstoff-Levels

INSERT INTO naehrstoff_levels (produkt_barcode, fett_level, salz_level, zucker_level, gesaettigte_fettsaeuren_level)
VALUES
    ('3017620422003', 'high', 'low', 'high', 'high'),
    ('4001686301104', 'low', 'low', 'high', 'low'),
    ('7622210100014', 'high', 'low', 'high', 'high'),
    ('4260107220022', 'low', 'low', 'high', 'low'),
    ('4000417025005', 'high', 'low', 'high', 'high');


-- Kategorien

INSERT INTO kategorien (name) VALUES
    ('Aufstriche'),
    ('Schokoladenaufstriche'),
    ('Haselnussaufstriche'),
    ('Suessigkeiten'),
    ('Fruchtgummis'),
    ('Gummibaerchen'),
    ('Schokolade'),
    ('Milchschokolade'),
    ('Nussschokolade'),
    ('Getraenke'),
    ('Erfrischungsgetraenke'),
    ('Cola');

-- Produkt-Kategorien Zuordnungen
-- Nutella: Aufstriche, Schokoladenaufstriche, Haselnussaufstriche
INSERT INTO produkt_kategorien (produkt_id, kategorie_id) VALUES
    (1, 1), (1, 2), (1, 3);

-- Haribo: Suessigkeiten, Fruchtgummis, Gummibaerchen
INSERT INTO produkt_kategorien (produkt_id, kategorie_id) VALUES
    (2, 4), (2, 5), (2, 6);

-- Milka: Suessigkeiten, Schokolade, Milchschokolade
INSERT INTO produkt_kategorien (produkt_id, kategorie_id) VALUES
    (3, 4), (3, 7), (3, 8);

-- Fritz-Kola: Getraenke, Erfrischungsgetraenke, Cola
INSERT INTO produkt_kategorien (produkt_id, kategorie_id) VALUES
    (4, 10), (4, 11), (4, 12);

-- Ritter Sport: Suessigkeiten, Schokolade, Milchschokolade, Nussschokolade
INSERT INTO produkt_kategorien (produkt_id, kategorie_id) VALUES
    (5, 4), (5, 7), (5, 8), (5, 9);
