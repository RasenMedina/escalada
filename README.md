# Aplicació Escalada – Persistència de Dades (JDBC)

**Autors:** Hajar Errahmani & Rasen Mediñà  
**Data:** 11 de Maig de 2026

## 1. Descripció del projecte

Aquest projecte consisteix en el desenvolupament d’una aplicació en **Java** per gestionar informació relacionada amb el món de l’escalada: escoles, sectors, vies, llargs i escaladors.

L’aplicació utilitza una **base de dades relacional MySQL** i es connecta mitjançant la **API JDBC**, aplicant una arquitectura basada en **MVC (Model-Vista-Controlador)** i el patró **DAO (Data Access Object)**.

---

## 2. Objectius

- Dissenyar una base de dades relacional normalitzada
- Implementar operacions **CRUD** completes
- Aplicar el patró **MVC**
- Utilitzar **JDBC** per a la persistència de dades
- Implementar el patró **DAO**
- Separar correctament les **capes de l’aplicació**
- Gestionar regles de negoci complexes

---

## 3. Tecnologies utilitzades

- Java
- MySQL
- JDBC (Java Database Connectivity)
- IntelliJ IDEA
- MySQL Workbench
- GitHub

---

## 4. Arquitectura del sistema

El sistema segueix el patró **MVC** amb tres capes diferenciades:

### 4.1. View (Vista)
Gestiona la interacció amb l’usuari:
- Sortida per consola
- Format HTML opcional
- Escriptura a fitxer

**Classes:**
- `Vista`
- `Menus`

### 4.2. Controller (Lògica de negoci)

Gestiona:
- Validacions
- Regles del sistema
- Comunicació entre vista i DAO

**Classes:**
- `EscolaController`
- `SectorController`
- `ViaController`
- `EscaladorController`
- `AssolimentController`
- `LlargController`
- `MenuController`

### 4.3. DAO (Persistència de dades)

S’encarrega de:
- Connexió amb la base de dades
- Execució de consultes SQL
- Conversió de ResultSet a objectes Java

**Elements JDBC utilitzats:**
- `Connection`
- `PreparedStatement`
- `ResultSet`

### 4.4. Package util

Conté utilitats comunes del sistema.

Classe principal:
- `DBConnection` → gestiona la connexió JDBC amb MySQL.

---

## 5. JDBC

JDBC és una API que permet connectar Java amb la bases de dades.

### Funcionament:
1. Obtenir connexió (`DBConnection`)
2. Preparar consulta (`PreparedStatement`)
3. Executar consulta
4. Processar resultats (`ResultSet`)
5. Mapejar resultats a objectes Java

---

## 6. Patró DAO

El patró DAO separa la lògica de negoci de l’accés a dades.

### Interfície genèrica:

```java
public interface DAO<T> {
    void create(T t) throws Exception;
    T getById(int id) throws Exception;
    List<T> getAll() throws Exception;
    void update(T t) throws Exception;
    void delete(int id) throws Exception;
}
```

### Operacions:
- `Create`
- `getById`
- `getAll`
- `update`
- `delete`

### Implementacions:
- `EscaladorDAOMySQL`
- `ViaDAOMySQL`
- `SectorDAOMySQL`
- `EscolaDAOMySQL`
- `LlargDAOMySQL`
- `AssolimentDAOMySQL`

També s’ha preparat el sistema per a:
- `PostgreSQL` (no implementat, només estructural)

---

## 7. Flexibilitat del sistema

L’aplicació s’ha dissenyat seguint el patró DAO per desacoblar la lògica de negoci del sistema gestor de bases de dades.

Actualment existeix una implementació funcional per MySQL:
- `dao.escalada.MySQL`
I una estructura preparada per PostgreSQL:
- `dao.escalada.PostgreSQL`

Això permetria canviar de SGBD amb modificacions mínimes a l’aplicació.

---

## 8. Model de dades

### Entitats principals:
- `Escola`
- `Sector`
- `Via`
- `Llarg`
- `Escalador`
- `Assoliment`

### Relacions:
- Una Escola té múltiples Sectors (1:N)
- Un Sector té múltiples vies (1:N)
- Una Via pot tenir múltiples Llargs (1:N)
- Un Escalador pot crear múltiples vies (1:N)
- Relació N:M entre escaladors i vies → Assoliment (id_escalador, id_via, data)

## 8. Decisions de disseny

### 8.1 Tipus de via
No s’ha implementat una jerarquia de classes (herència).
En lloc d’això, es fa servir un atribut:
- `tipus → (esportiva, clàssica, gel)`

Motiu:
- Totes comparteixen atributs
- Simplificació del model, menys complexitat
- Millor rendiment

Això simplifica:
- El model de dades
- El nombre de taules
- El nombre de DAO

Control de restriccions → a través de codi (Controller)

---

### 8.2 Sectors de gel

S’ha afegit un camp:
- `esGel (boolean)`

Regles:
- `Si esGel = true → només vies de gel`
- `Si esGel = false → només vies esportives o clàssiques`

### 8.3. Llargs

Taula independent:
- `Relació amb via`
- `Ordre dins la via`
- `Llargada i dificultat`

Regles:
- `Les vies esportives tenen 1 llarg`
- `Les vies clàssiques i de gel poden tenir múltiples llargs`
La llargada total es calcula com la suma dels llargs.

### 8.4. Camps calculats (NO guardats)

No s’emmagatzemen:
- `Número de vies d’una escola o sector`
- `Edat de l’escalador`
- `Grau màxim assolit per escalador i nom de la via on s'ha assolit`

Motiu:
- `Es poden obtenir amb consultes SQL(SELECT)`

### 8.5. Estat de la via

Les vies tenen:
- `estat → (Apte, Construcció, Tancada)`
- `data_inici_no_apte`
- `data_fi_no_apte`
- `motiu pel qual no està apte`

Regles:
- `Si la via està 'Apte'→ els camps valen NULL`
- `Si la via està en 'Construcció' o 'Tancada'→ s'especifiquen les dates i el motiu`
Quan la data actual supera data_fi_no_apte, la via passa automàticament a Apte (controlat per lògica de negoci).

### 8.6. Identificadors

Totes les taules tenen:
- `id (PK autoincremental)`

Encara que inicialment es van considerar entitats febles, finalment es va optar per un model uniforme basat en identificadors.

Motiu:
- `S’eviten claus febles → disseny més simple i uniforme`

---

## 10. Estructura de la base de dades

|---------------------------------------|
| Taula     |        Descripció         |
|-----------|---------------------------|
| escola    | Informació de les escoles |
| sector    | Sectors d’una escola      |
| via       | Vies d’escalada           |
| llarg     | Llargs d’una via          |
| escalador | Informació dels escaladors|
| assoliment| Relació escalador-via     |
|---------------------------------------|

**Escola**
- `id (PK)`
- `nom (únic + NN)`
- `lloc`
- `popularitat`
- `aproximació`

**Sector**
- `id (PK)`
- `id_escola (FK + NN)`
- `nom (NN)`
- `latitud`
- `longitud`
- `aproximació`
- `popularitat`
- `esGel (NN)`

**Via**
- `id (PK)`
- `id_sector (FK + NN)`
- `id_escalador creador (FK + NN)`
- `data de creació de la via`
- `nom (NN)`
- `tipus de via (NN)`
- `orientació`
- `ancoratge`
- `tipus de roca`
- `estat (NN)`
- `motiu pel qual no ha estat apte`
- `data inici que no està apte`
- `data fi que no està apte`

**Llarg**
- `id (PK)`
- `id_via (FK + NN)`
- `ordre (NN)`
- `llargada (NN)`
- `dificultat`

**Escalador**
- `id (PK)`
- `dni (únic + NN)`
- `nom (NN)`
- `cognoms`
- `alias`
- `data_naix`
- `estil preferit`

**Assoliment**
- `id (PK)`
- `id_escalador (FK + NN)`
- `id_via (FK + NN)`
- `data`

---

## 11. Regles de negoci implementades

- `No poden existir dues escoles amb el mateix nom`
- `No poden existir sectors amb el mateix nom dins d'una escola`
- `No poden existir vies amb el mateix nom dins d'un sector`
- `Un sector de gel només pot contenir vies de gel`
- `Un sector no gel no pot contenir vies de gel`
- `Validació de grau de dificultat segons tipus de via`
- `Validació d’ancoratges segons tipus de via`
- `Un escalador ha d’existir per crear una via`
- `Control de l'estat de la via a través de dates`

Camps 'Not Null' per taula (els que es consideren mínims i imprescindibles)
- `Escola: id + nom`
- `Sector: id + nom + idEscola + esGel`
- `Via: id + nom + idSector + idEscaladorCreador + tipus + estat`
- `Llarg: id + idVia + ordre + llargada`
- `Escalador: id + dni + nom`
- `Assoliment': id + idEscalador + idVia`

---

## 12. Funcionalitats implementades

**CRUD complet per**
- `Escoles`
- `Sectors`
- `Vies`
- `Escaladors`
- `Llargs`
- `Assoliments`

**Consultes avançades**
- `Vies disponibles d’una escola`
- `Cerca per rang de dificultat`
- `Cerca per estat`
- `Sectors amb més de X vies`
- `Escaladors amb mateix nivell`
- `Vies recentment disponibles`
- `Vies més llargues d’una escola`

---

## 13. Execució

1. Clonar el repositori
2. Crear la base de dades amb escalada.sql
3. Configurar DBConnection

```java
private static final String URL = "jdbc:mysql://localhost:3306/escalada";
private static final String USER = "root";
private static final String PASSWORD = "root";
```

4. Executar Main.java

---

## 14. Conclusions

Aquest projecte permet aplicar de forma pràctica:
- `Arquitectura MVC`
- `Patró DAO`
- `Connexió amb bases de dades amb JDBC`
- `Disseny de bases de dades relacionals`
- `Separació de responsabilitats`

El sistema és **modular** i **escalable**, permetent fàcilment afegir nous sistemes gestors de bases de dades com PostgreSQL.