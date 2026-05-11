CREATE DATABASE escalada;

USE escalada;

-- creació de tables ===============================================================================================================================================================
DROP TABLE IF EXISTS escoles;
CREATE TABLE escoles (
	id_escola INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nom VARCHAR(20) NOT NULL UNIQUE,
    lloc VARCHAR(20) COMMENT "població",
    popularitat ENUM("baixa", "mitjana", "alta"),
    aproximacio VARCHAR(45) COMMENT "breu descripció de com arribar",
    
	CONSTRAINT pk_escola PRIMARY KEY (id_escola)
);

DROP TABLE IF EXISTS sectors;
CREATE TABLE  sectors (
	id_sector INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_escola INT UNSIGNED NOT NULL,
    nom_sector VARCHAR(20) NOT NULL,
    longitud DECIMAL(11,8) COMMENT "Coordenades",
    latitude DECIMAL(11,8) COMMENT "Coordenades",
    aproximacio VARCHAR(40) COMMENT "breu descripció de com arribar",
    popularitat ENUM("baixa", "mitjana", "alta"),
    es_gel BOOLEAN NOT NULL COMMENT "per saber si és de gel o és clàssica/esportiva",
    
	CONSTRAINT pk_sector PRIMARY KEY (id_sector),
	CONSTRAINT fk_escola FOREIGN KEY (id_escola) REFERENCES escoles (id_escola)
		ON DELETE RESTRICT
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS escaladors;
CREATE TABLE escaladors(
	id_escalador INT UNSIGNED NOT NULL AUTO_INCREMENT,
    dni CHAR(9) NOT NULL,
    nom VARCHAR(20) NOT NULL,
    cognoms VARCHAR(20),
    alias VARCHAR(15),
    data_naix DATE,
    estil_preferit ENUM('esportiva', 'classica', 'gel'),
    
    CONSTRAINT pk_escalador PRIMARY KEY (id_escalador)
);

DROP TABLE IF EXISTS vies;
CREATE TABLE vies (
	id_via INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_sector INT UNSIGNED NOT NULL,
    id_escalador_creador INT UNSIGNED NOT NULL,
    nom VARCHAR(20) NOT NULL,
    data_creacio DATE COMMENT "Data en què s’ha creat la via",
    orientacio ENUM("N", "NE", "NO", "SE", "SO", "E", "O", "S") 
			COMMENT "Indica l’orientació del sector (nord, sud, est, oest i combinacions diagonals)",
    ancoratge ENUM('friends', 'tascons', 'bagues', 'pitons', 'Tricams', 'BigBros', 'spits', 'parabolts', 'quimics')
			COMMENT "Tipus d’ancoratge utilitzat en la via (cada via en té només un)",
    tipus_roca ENUM('conglomerat', 'granit', 'calcaria', 'arenisca', 'altres'),
    tipus_via ENUM('esportiva', 'classica', 'gel') NOT NULL,
    estat ENUM('Apte', 'construcció', 'tancada') NOT NULL
			COMMENT "Estat actual de la via. Si està en construcció o tancada, es definirà una data límit i després tornarà automàticament a estat apte",
    motiu_no_apte VARCHAR(100),
    data_inici_no_apte DATE,
    data_fi_no_apte DATE,
    
    CONSTRAINT pk_via PRIMARY KEY (id_via),
    CONSTRAINT fk_sector FOREIGN KEY (id_sector) REFERENCES sectors (id_sector)
		ON DELETE RESTRICT
        ON UPDATE CASCADE,
    CONSTRAINT fk_escalador_creador FOREIGN KEY (id_escalador_creador) REFERENCES escaladors (id_escalador)
		ON DELETE RESTRICT
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS llargs;
CREATE TABLE llargs (
	id_llarg INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_via INT UNSIGNED NOT NULL,
    ordre INT UNSIGNED NOT NULL COMMENT "Començant per sota",
    llargada SMALLINT NOT NULL,
    grau_dificultat ENUM('4', '4+', '5', '5+', '6a', '6a+', '6b', '6b+', '6c', '6c+', '7a', '7a+', '7b', '7b+', '7c', '7c+', '8a', '8a+', '8b', '8c', '8c+' , '9a' , '9a+' , '9b' , '9b+' , '9c' ,'9c+'),
    
    CONSTRAINT pk_llarg PRIMARY KEY (id_llarg),
    CONSTRAINT fk_via FOREIGN KEY (id_via) REFERENCES vies(id_via)
		ON DELETE RESTRICT
        ON UPDATE CASCADE
);

DROP TABLE IF EXISTS assoliments;
CREATE TABLE assoliments (
	id_assoliment INT UNSIGNED NOT NULL AUTO_INCREMENT,
	id_escalador INT UNSIGNED NOT NULL,
    id_via INT UNSIGNED NOT NULL,
    data DATE,
    
    CONSTRAINT pk_assoliment PRIMARY KEY (id_assoliment),
    CONSTRAINT fk_assoliment_escalador FOREIGN KEY (id_escalador) REFERENCES escaladors(id_escalador)
		ON DELETE RESTRICT 
        ON UPDATE CASCADE,
    CONSTRAINT fk_assoliment_via FOREIGN KEY (id_via) REFERENCES vies(id_via)
		ON DELETE RESTRICT 
        ON UPDATE CASCADE
);

-- INDEX ===============================================================================================================================================================
-- ESCOLES --------------------------------------------------------------------
-- FK -> escoles
CREATE INDEX idx_sector_escola ON sectors(id_escola);
-- Cerca per nom de sector
CREATE INDEX idx_sector_nom ON sectors(nom_sector);


-- VIES -----------------------------------------------------------------------
-- FK -> sectors
CREATE INDEX idx_via_sector ON vies(id_sector);

-- FK -> escaladors
CREATE INDEX idx_via_escalador ON vies(id_escalador_creador);

-- Cerca per estat
CREATE INDEX idx_via_estat ON vies(estat);

-- Cerca per tipus de via
CREATE INDEX idx_via_tipus ON vies(tipus_via);

-- Cerca per nom
CREATE INDEX idx_via_nom ON vies(nom);

-- 
-- LLARGS --------------------------------------------------------------------
-- FK -> vies
CREATE INDEX idx_llarg_via ON llargs(id_via);

-- Cerca per dificultat
CREATE INDEX idx_llarg_dificultat ON llargs(grau_dificultat);

-- Cerca per ordre dins la via
CREATE INDEX idx_llarg_ordre ON llargs(ordre);

-- ESCALADORS -------------------------------------------------------------------
CREATE UNIQUE INDEX idx_escalador_dni ON escaladors(dni);

-- Cerca per nom
CREATE INDEX idx_escalador_nom ON escaladors(nom);

-- Cerca per estil preferit
CREATE INDEX idx_escalador_estil ON escaladors(estil_preferit);

-- ASSOLIMENTS -------------------------------------------------------------------
-- FK -> escalador
CREATE INDEX idx_assoliment_escalador ON assoliments(id_escalador);

-- FK -> via
CREATE INDEX idx_assoliment_via ON assoliments(id_via);

-- Cerca per data
CREATE INDEX idx_assoliment_data ON assoliments(data);

-- INSERTS ===============================================================================================================================================================
-- INSERTS ESCOLES
INSERT INTO escoles (nom, lloc, popularitat, aproximacio) VALUES
('Siurana', 'Tarragona', 'alta', 'Accés per carretera principal'),
('Margalef', 'Priorat', 'alta', 'Camí asfaltat fins al pàrquing'),
('Montserrat', 'Barcelona', 'alta', 'Sender curt des del monestir'),
('Riglos', 'Osca', 'mitjana', 'Accés per pista forestal'),
('Cavallers', 'Lleida', 'mitjana', 'Aproximació de 20 minuts'),
('Rodellar', 'Osca', 'alta', 'Camí senyalitzat'),
('Albarracin', 'Terol', 'alta', 'Accés des del poble'),
('Terradets', 'Lleida', 'mitjana', 'A peu des de la carretera'),
('Patones', 'Madrid', 'baixa', 'Camí de terra'),
('Chulilla', 'Valencia', 'alta', 'Sender de muntanya');

-- INSERTS SECTORS
INSERT INTO sectors (id_escola, nom_sector, longitud, latitude, aproximacio, popularitat, es_gel) VALUES
(1, 'Can Marges', 0.88765432, 41.23456789, '10 minuts caminant', 'alta', FALSE),
(2, 'Laboratori', 0.76543210, 41.87654321, 'Sender rocós', 'alta', FALSE),
(3, 'Gorros', 1.12345678, 41.54321678, 'Accés amb escales', 'mitjana', FALSE),
(4, 'Pisón', -0.65432123, 42.34567891, 'Camí curt', 'mitjana', FALSE),
(5, 'Comalesbienes', 0.98765432, 42.56789123, '30 minuts de pujada', 'baixa', TRUE),
(6, 'Mascun', -0.34567891, 42.23456789, 'Accés pel riu', 'alta', FALSE),
(7, 'Techos', -1.12345678, 40.56789123, 'Camí entre boscos', 'alta', FALSE),
(8, 'Paret Bucolica', 0.45678912, 42.12345678, 'Accés fàcil', 'mitjana', FALSE),
(9, 'Ponton', -3.65432109, 40.87654321, '15 minuts caminant', 'baixa', FALSE),
(10, 'Oasis', -0.98765432, 39.87654321, 'Descens per sender', 'alta', FALSE);

-- INSERTS ESCALADORS
INSERT INTO escaladors (dni, nom, cognoms, alias, data_naix, estil_preferit) VALUES
('12345678A', 'Marc', 'Soler', 'Marsu', '1998-04-12', 'esportiva'),
('23456789B', 'Laura', 'Garcia', 'LauRock', '1995-07-20', 'classica'),
('34567890C', 'Pol', 'Riera', 'Pollet', '2000-01-15', 'gel'),
('45678901D', 'Anna', 'Vidal', 'Anvi', '1997-11-02', 'esportiva'),
('56789012E', 'Jordi', 'Pons', 'JP', '1992-09-18', 'classica'),
('67890123F', 'Clara', 'Mas', 'ClimbCat', '1999-03-30', 'gel'),
('78901234G', 'Nil', 'Costa', 'Nilet', '2001-12-10', 'esportiva'),
('89012345H', 'Mireia', 'Serra', 'Miri', '1996-05-22', 'classica'),
('90123456I', 'Oriol', 'Puig', 'Ori', '1994-08-14', 'gel'),
('01234567J', 'Eva', 'Ferrer', 'Evix', '2002-06-09', 'esportiva');


-- INSERTS VIES 
INSERT INTO vies (
id_sector, id_escalador_creador, nom, data_creacio, orientacio,
ancoratge, tipus_roca, tipus_via, estat,
motiu_no_apte, data_inici_no_apte, data_fi_no_apte
) VALUES
(1, 1, 'Espurna', '2022-01-10', 'S', 'parabolts', 'calcaria', 'esportiva', 'Apte', NULL, NULL, NULL),
(2, 2, 'Gel Etern', '2021-12-01', 'N', 'pitons', 'granit', 'gel', 'tancada', 'Gel inestable', '2026-01-01', '2026-06-01'),
(3, 3, 'Vent del Nord', '2020-05-15', 'NE', 'friends', 'conglomerat', 'classica', 'Apte', NULL, NULL, NULL),
(4, 4, 'Llum Roja', '2019-07-22', 'O', 'spits', 'calcaria', 'esportiva', 'construcció', 'Instal·lació nova', '2026-03-01', '2026-08-01'),
(5, 5, 'Cascada Blanca', '2018-02-14', 'N', 'quimics', 'granit', 'gel', 'Apte', NULL, NULL, NULL),
(6, 6, 'El Titan', '2023-04-30', 'SE', 'parabolts', 'arenisca', 'esportiva', 'Apte', NULL, NULL, NULL),
(7, 7, 'Fisura Negra', '2022-06-18', 'SO', 'tascons', 'calcaria', 'classica', 'Apte', NULL, NULL, NULL),
(8, 8, 'Cel Blau', '2021-09-09', 'E', 'friends', 'granit', 'classica', 'Apte', NULL, NULL, NULL),
(9, 9, 'Roca Viva', '2020-10-10', 'NO', 'bagues', 'conglomerat', 'classica', 'Apte', NULL, NULL, NULL),
(10, 10, 'Tempesta', '2024-01-01', 'S', 'parabolts', 'calcaria', 'esportiva', 'Apte', NULL, NULL, NULL);

-- INSERTS LLARGS
INSERT INTO llargs (id_via, ordre, llargada, grau_dificultat) VALUES
(1, 1, 25, '6a'),
(2, 1, 40, '7a'),
(3, 1, 30, '6b+'),
(4, 1, 20, '5+'),
(5, 1, 35, '7b'),
(6, 1, 28, '6c'),
(7, 1, 22, '6a+'),
(8, 1, 50, '7c'),
(9, 1, 18, '5'),
(10, 1, 45, '8a');

-- INSERTS ASSOLIMENTS
INSERT INTO assoliments (id_escalador, id_via, data) VALUES
(1, 1, '2024-01-12'),
(2, 2, '2024-02-14'),
(3, 3, '2024-03-16'),
(4, 4, '2024-04-18'),
(5, 5, '2024-05-20'),
(6, 6, '2024-06-22'),
(7, 7, '2024-07-24'),
(8, 8, '2024-08-26'),
(9, 9, '2024-09-28'),
(10, 10, '2024-10-30');