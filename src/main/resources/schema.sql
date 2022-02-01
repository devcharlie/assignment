DROP TABLE IF EXISTS ARTISTS;
CREATE TABLE ARTISTS(
                        ID IDENTITY NOT NULL PRIMARY KEY,
                        FIRST_NAME VARCHAR(50) NOT NULL,
                        MIddLE_NAME VARCHAR(50),
                        LAST_NAME VARCHAR(50) NOT NULL,
                        CATEGORY VARCHAR(20) NOT NULL CHECK (CATEGORY IN('ACTOR', 'PAINTER', 'SCULPTOR')),
                        BIRTHDAY DATE,
                        EMAIL VARCHAR(50),
                        NOTES VARCHAR(200)
);