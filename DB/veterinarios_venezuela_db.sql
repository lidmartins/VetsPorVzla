-- ============================================================
--  VETERINARIOS POR VENEZUELA — Database Schema
--  MySQL 8.x / Aurora MySQL 8.x
--  Generated for: veterinariosporvenezuela.com
--  Charset: utf8mb4 | Collation: utf8mb4_unicode_ci
-- ============================================================

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

CREATE DATABASE IF NOT EXISTS veterinarios_venezuela
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE veterinarios_venezuela;

-- ============================================================
-- TABLE: Role
-- ============================================================
DROP TABLE IF EXISTS role;
CREATE TABLE IF NOT EXISTS role (
  ro_cd_role        INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  ro_nm_role        VARCHAR(20)     NOT NULL COMMENT 'ADMIN | VET | PUBLICO | SUPERADMIN',
  ro_st_role        CHAR(1)         NOT NULL DEFAULT 'A' COMMENT 'A=Activo | I=Inactivo',
  ro_dt_created     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ro_dt_updated     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_role          PRIMARY KEY (ro_cd_role),
  CONSTRAINT uq_role_nm       UNIQUE (ro_nm_role),
  CONSTRAINT ck_role_st       CHECK (ro_st_role IN ('A','I'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='User roles: ADMIN, VET, PUBLICO, SUPERADMIN';


-- ============================================================
-- TABLE: User
-- ============================================================
DROP TABLE IF EXISTS user;
CREATE TABLE IF NOT EXISTS user (
  us_cd_user            INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  us_ro_cd_role         INT UNSIGNED    NOT NULL COMMENT 'FK → role.ro_cd_role',
  us_nm_first_name      VARCHAR(100)    NOT NULL,
  us_nm_last_name       VARCHAR(100)    NOT NULL,
  us_de_email           VARCHAR(100)    NOT NULL,
  us_de_phone           VARCHAR(20)     NOT NULL,
  us_de_password_hash   VARCHAR(255)    NOT NULL,
  us_in_veterinarian    CHAR(1)         NOT NULL DEFAULT 'S' COMMENT 'S=Si | N=No',
  us_st_user            CHAR(1)         NOT NULL COMMENT 'A=Activo | I=Inactivo | B=Bloqueado',
  us_dt_last_login      DATETIME        NOT NULL,
  us_dt_created         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  us_dt_updated         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_user            PRIMARY KEY (us_cd_user),
  CONSTRAINT uq_user_email      UNIQUE (us_de_email),
  CONSTRAINT fk_user_role       FOREIGN KEY (us_ro_cd_role)
                                  REFERENCES role (ro_cd_role)
                                  ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ck_user_vet        CHECK (us_in_veterinarian IN ('S','N')),
  CONSTRAINT ck_user_st         CHECK (us_st_user IN ('A','I','B'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='Platform users (admin, vets, public)';


-- ============================================================
-- TABLE: Estado  (Venezuelan states)
-- ============================================================
DROP TABLE IF EXISTS estado;
CREATE TABLE IF NOT EXISTS estado (
  es_cd_estado      INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  es_cd_country     INT             NOT NULL DEFAULT 58,
  es_nm_estado      VARCHAR(100)    NOT NULL,
  es_st_estado      CHAR(1)         NOT NULL COMMENT 'A=Activo | I=Inactivo',
  es_dt_created     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  es_dt_updated     DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_estado          PRIMARY KEY (es_cd_estado),
  CONSTRAINT uq_estado_nm       UNIQUE (es_nm_estado),
  CONSTRAINT ck_estado_st       CHECK (es_st_estado IN ('A','I'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='Venezuelan states/provinces';


-- ============================================================
-- TABLE: Ubicacion  (Location)
-- ============================================================
DROP TABLE IF EXISTS ubicacion;
CREATE TABLE IF NOT EXISTS ubicacion (
  ur_cd_ubicacion     INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  ur_es_cd_estado     INT UNSIGNED    NOT NULL COMMENT 'FK → estado.es_cd_estado',
  ur_nm_city          VARCHAR(100)    NOT NULL,
  ur_nm_sector        VARCHAR(150)    NOT NULL,
  ur_de_address       VARCHAR(255)    NOT NULL,
  ur_de_reference     VARCHAR(255)        NULL,
  ur_de_postal_code   VARCHAR(10)         NULL,
  ur_nu_latitude      DECIMAL(10,7)       NULL,
  ur_nu_longitude     DECIMAL(10,7)       NULL,
  ur_dt_created       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  ur_dt_updated       DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_ubicacion       PRIMARY KEY (ur_cd_ubicacion),
  CONSTRAINT fk_ubicacion_estado FOREIGN KEY (ur_es_cd_estado)
                                   REFERENCES estado (es_cd_estado)
                                   ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='Detailed geographic locations (city/sector/address)';


-- ============================================================
-- TABLE: Contacto
-- ============================================================
DROP TABLE IF EXISTS contacto;
CREATE TABLE IF NOT EXISTS contacto (
  co_cd_contacto        INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  co_nm_first_name      VARCHAR(100)    NOT NULL,
  co_nm_last_name       VARCHAR(100)    NOT NULL,
  co_de_email           VARCHAR(100)    NOT NULL,
  co_de_phone           VARCHAR(20)     NOT NULL,
  co_de_whatsapp        VARCHAR(20)     NOT NULL,
  co_tp_contact_method  CHAR(1)         NOT NULL DEFAULT 'W' COMMENT 'W=WhatsApp | P=Phone | E=Email | A=Any',
  co_in_allow_public    CHAR(1)         NOT NULL DEFAULT 'S' COMMENT 'S=Si | N=No',
  co_dt_created         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  co_dt_updated         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_contacto        PRIMARY KEY (co_cd_contacto),
  CONSTRAINT ck_contact_method  CHECK (co_tp_contact_method IN ('W','P','E','A')),
  CONSTRAINT ck_contact_public  CHECK (co_in_allow_public IN ('S','N'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='Contact information for reporters, shelter owners, etc.';


-- ============================================================
-- TABLE: Animal
-- ============================================================
DROP TABLE IF EXISTS animal;
CREATE TABLE IF NOT EXISTS animal (
  an_cd_animal            INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  an_re_cd_refugio        INT UNSIGNED        NULL COMMENT 'FK → refugio.re_cd_refugio (optional)',
  an_report_type          CHAR(1)         NOT NULL COMMENT 'P=Perdida | E=Encontrada',
  an_nm_animal            VARCHAR(100)        NULL COMMENT 'Name of the animal (optional)',
  an_tp_animal            CHAR(1)          NULL COMMENT 'G=Gato | P=Perro',
  an_de_breed             VARCHAR(100)        NULL,
  an_de_color             VARCHAR(100)     NULL,
  an_tp_size              CHAR(1)          NULL COMMENT 'P=Pequeño | M=Mediano | G=Grande',
  an_tp_sex               CHAR(1)          NULL COMMENT 'M=Macho | H=Hembra',
  an_nu_approx_age        TINYINT UNSIGNED    NULL COMMENT 'Approximate age in years',
  an_de_animal            TEXT             NULL COMMENT 'Physical description',
  an_in_require_vet_review CHAR(1)         NULL COMMENT 'S=Si | N=No',
  an_de_observacion_vet   TEXT                NULL COMMENT 'Observacion from vet',
  an_st_vet_review        CHAR(1)          NULL DEFAULT 'P' COMMENT 'P=Pendiente | A=Activo | R=Revisado',
  an_ubicacion            VARCHAR(255)     NOT NULL,
  an_telefono             VARCHAR(20)      NOT NULL,
  an_dt_created           DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  an_dt_updated           DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_animal              PRIMARY KEY (an_cd_animal),
  CONSTRAINT fk_animal_refugio      FOREIGN KEY (an_re_cd_refugio)
                                      REFERENCES refugio (re_cd_refugio)
                                      ON UPDATE CASCADE ON DELETE SET NULL,
  CONSTRAINT ck_animal_report_type  CHECK (an_report_type IN ('P','E')),
  CONSTRAINT ck_animal_tp           CHECK (an_tp_animal IN ('G','P')),
  CONSTRAINT ck_animal_size         CHECK (an_tp_size IN ('P','M','G')),
  CONSTRAINT ck_animal_sex          CHECK (an_tp_sex IN ('M','H')),
  CONSTRAINT ck_animal_vet_req      CHECK (an_in_require_vet_review IN ('S','N')),
  CONSTRAINT ck_animal_vet_st       CHECK (an_st_vet_review IN ('P','A','R'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci
  COMMENT='Animals registered on the platform';


-- ============================================================
-- TABLE: Refugio  (Shelter)
-- Note: Animal has FK to Refugio, so Refugio must be created
--       before Animal. The FK on Animal referencing Refugio is
--       declared above but Refugio table is defined here.
--       We use FOREIGN KEY CHECKS = 0 at the top to allow this.
-- ============================================================
DROP TABLE IF EXISTS refugio;
CREATE TABLE IF NOT EXISTS refugio (
  re_cd_refugio               INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  re_cd_contacto              INT UNSIGNED    NOT NULL COMMENT 'FK → contacto.co_cd_contacto',
  re_ur_cd_ubicacion          INT UNSIGNED    NOT NULL COMMENT 'FK → ubicacion.ur_cd_ubicacion',
  re_nm_refugio               VARCHAR(150)    NOT NULL,
  re_st_refugio               CHAR(1)         NOT NULL COMMENT 'P=Pendiente | A=Activo | X=Lleno | I=Inactivo | R=Rechazado',
  re_nu_capacity_total        SMALLINT UNSIGNED NOT NULL,
  re_nu_capacity_available    SMALLINT UNSIGNED NOT NULL,
  re_tp_species_allowed       CHAR(1)         NOT NULL COMMENT 'G=Gato | P=Perro | A=Ambos',
  re_tp_animal_special_needs  VARCHAR(2)          NULL COMMENT 'AH=Heridos | CA=Cachorros | AM=Adultos mayores',
  re_in_has_pets              CHAR(1)         NOT NULL COMMENT 'S=Si | N=No',
  re_tp_housing               VARCHAR(2)      NOT NULL COMMENT 'CP=Casa-con-patio | CS=Casa-sin-patio | AP=Apartamento',
  re_in_fence_housing         CHAR(1)         NOT NULL COMMENT 'C=Completo | P=Parcial | N=No',
  re_de_additional_note       TEXT                NULL,
  re_de_observacion_vet       TEXT                NULL COMMENT 'Vet rejection reason',
  re_dt_created               DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  re_dt_updated               DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_refugio             PRIMARY KEY (re_cd_refugio),
  CONSTRAINT fk_refugio_contacto    FOREIGN KEY (re_cd_contacto)
                                      REFERENCES contacto (co_cd_contacto)
                                      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_refugio_ubicacion   FOREIGN KEY (re_ur_cd_ubicacion)
                                      REFERENCES ubicacion (ur_cd_ubicacion)
                                      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ck_refugio_st          CHECK (re_st_refugio IN ('P','A','X','I','R')),
  CONSTRAINT ck_refugio_species     CHECK (re_tp_species_allowed IN ('G','P','A')),
  CONSTRAINT ck_refugio_special     CHECK (re_tp_animal_special_needs IS NULL OR re_tp_animal_special_needs IN ('AH','CA','AM')),
  CONSTRAINT ck_refugio_has_pets    CHECK (re_in_has_pets IN ('S','N')),
  CONSTRAINT ck_refugio_housing     CHECK (re_tp_housing IN ('CP','CS','AP')),
  CONSTRAINT ck_refugio_fence       CHECK (re_in_fence_housing IN ('C','P','N'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='Temporary animal shelters registered by volunteers';


-- ============================================================
-- TABLE: Solicitud  (Lost / Found report)
-- ============================================================
DROP TABLE IF EXISTS solicitud;
CREATE TABLE IF NOT EXISTS solicitud (
  so_cd_solicitud           INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  so_an_cd_animal           INT UNSIGNED    NOT NULL COMMENT 'FK → animal.an_cd_animal',
  so_co_cd_contacto         INT UNSIGNED    NOT NULL COMMENT 'FK → contacto.co_cd_contacto',
  so_ur_cd_ubicacion        INT UNSIGNED    NOT NULL COMMENT 'FK → ubicacion.ur_cd_ubicacion',
  so_tp_solicitud           CHAR(1)         NOT NULL COMMENT 'P=Perdida | E=Encontrada',
  so_dt_evento              DATETIME        NOT NULL COMMENT 'Date + time of the event',
  so_st_solicitud           CHAR(1)         NOT NULL COMMENT 'P=Pendiente | R=Rechazada | A=Activa',
  so_de_observacion_vet     TEXT                NULL COMMENT 'Rejection reason from vet',
  so_de_s3_folder_path      VARCHAR(500)    NOT NULL COMMENT 'S3 folder path for photos',
  so_de_main_photo_url      VARCHAR(500)    NOT NULL COMMENT 'URL of the main photo',
  so_dt_created             DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  so_dt_updated             DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_solicitud           PRIMARY KEY (so_cd_solicitud),
  CONSTRAINT fk_solicitud_animal    FOREIGN KEY (so_an_cd_animal)
                                      REFERENCES animal (an_cd_animal)
                                      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_solicitud_contacto  FOREIGN KEY (so_co_cd_contacto)
                                      REFERENCES contacto (co_cd_contacto)
                                      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_solicitud_ubicacion FOREIGN KEY (so_ur_cd_ubicacion)
                                      REFERENCES ubicacion (ur_cd_ubicacion)
                                      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ck_solicitud_tp        CHECK (so_tp_solicitud IN ('P','E')),
  CONSTRAINT ck_solicitud_st        CHECK (so_st_solicitud IN ('P','R','A'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='Lost or found animal reports submitted by the public';


-- ============================================================
-- TABLE: RevisionVeterinaria  (Vet Review)
-- ============================================================
DROP TABLE IF EXISTS revision_veterinaria;
CREATE TABLE IF NOT EXISTS revision_veterinaria (
  rv_cd_revision_vet    INT UNSIGNED    NOT NULL AUTO_INCREMENT,
  rv_an_cd_animal       INT UNSIGNED    NOT NULL COMMENT 'FK → animal.an_cd_animal',
  rv_us_cd_user         INT UNSIGNED    NOT NULL COMMENT 'FK → user.us_cd_user (vet)',
  rv_st_vet_review      CHAR(1)         NOT NULL DEFAULT 'P' COMMENT 'P=Pendiente | A=Activo | R=Revisado',
  rv_de_comment         TEXT                NULL,
  rv_dt_created         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP,
  rv_dt_updated         DATETIME        NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

  CONSTRAINT pk_revision_vet        PRIMARY KEY (rv_cd_revision_vet),
  CONSTRAINT fk_rev_vet_animal      FOREIGN KEY (rv_an_cd_animal)
                                      REFERENCES animal (an_cd_animal)
                                      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT fk_rev_vet_user        FOREIGN KEY (rv_us_cd_user)
                                      REFERENCES user (us_cd_user)
                                      ON UPDATE CASCADE ON DELETE RESTRICT,
  CONSTRAINT ck_rev_vet_st          CHECK (rv_st_vet_review IN ('P','A','R'))
) ENGINE=InnoDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci
  COMMENT='Veterinary review log for each animal';


-- ============================================================
-- RE-ENABLE FK CHECKS
-- ============================================================
SET FOREIGN_KEY_CHECKS = 1;


-- ============================================================
-- INDEXES  (beyond PKs/UQs already created above)
-- ============================================================

-- animal
CREATE INDEX idx_animal_refugio        ON animal         (an_re_cd_refugio);
CREATE INDEX idx_animal_tp             ON animal         (an_tp_animal);
CREATE INDEX idx_animal_vet_review     ON animal         (an_st_vet_review);

-- solicitud
CREATE INDEX idx_solicitud_animal      ON solicitud      (so_an_cd_animal);
CREATE INDEX idx_solicitud_contacto    ON solicitud      (so_co_cd_contacto);
CREATE INDEX idx_solicitud_ubicacion   ON solicitud      (so_ur_cd_ubicacion);
CREATE INDEX idx_solicitud_tp          ON solicitud      (so_tp_solicitud);
CREATE INDEX idx_solicitud_st          ON solicitud      (so_st_solicitud);
CREATE INDEX idx_solicitud_dt_evento   ON solicitud      (so_dt_evento DESC);

-- refugio
CREATE INDEX idx_refugio_contacto      ON refugio        (re_cd_contacto);
CREATE INDEX idx_refugio_ubicacion     ON refugio        (re_ur_cd_ubicacion);
CREATE INDEX idx_refugio_st            ON refugio        (re_st_refugio);

-- revision_veterinaria
CREATE INDEX idx_rev_vet_animal        ON revision_veterinaria (rv_an_cd_animal);
CREATE INDEX idx_rev_vet_user          ON revision_veterinaria (rv_us_cd_user);
CREATE INDEX idx_rev_vet_st            ON revision_veterinaria (rv_st_vet_review);

-- ubicacion
CREATE INDEX idx_ubicacion_estado      ON ubicacion      (ur_es_cd_estado);

-- user
CREATE INDEX idx_user_role             ON user           (us_ro_cd_role);
CREATE INDEX idx_user_st               ON user           (us_st_user);


-- ============================================================
-- SEED DATA
-- ============================================================

-- Roles
INSERT INTO role (ro_nm_role, ro_st_role) VALUES
  ('ADMIN',      'A'),
  ('VET',        'A');

-- Venezuelan states (24 estados + Distrito Capital)
INSERT INTO estado (es_nm_estado, es_cd_country, es_st_estado) VALUES
  ('Amazonas',          58,'A'),
  ('Anzoátegui',        58 ,'A'),
  ('Apure',             58 ,'A'),
  ('Aragua',            58 ,'A'),
  ('Barinas',           58 ,'A'),
  ('Bolívar',           58 ,'A'),
  ('Carabobo',          58 ,'A'),
  ('Cojedes',           58 ,'A'),
  ('Delta Amacuro',     58 ,'A'),
  ('Distrito Capital',  58 ,'A'),
  ('Falcón',            58 ,'A'),
  ('Guárico',           58 ,'A'),
  ('Lara',              58 ,'A'),
  ('Mérida',            58 ,'A'),
  ('Miranda',           58 ,'A'),
  ('Monagas',           58 ,'A'),
  ('Nueva Esparta',     58 ,'A'),
  ('Portuguesa',        58 ,'A'),
  ('Sucre',             58 ,'A'),
  ('Táchira',           58 ,'A'),
  ('Trujillo',          58 ,'A'),
  ('La Guaira',         58 ,'A'),
  ('Yaracuy',           58 ,'A'),
  ('Zulia',             58 ,'A');


-- ============================================================
-- QUICK-REFERENCE: Column Code Mapping
-- ============================================================
/*
  ── Animal ──────────────────────────────────────────
  an_tp_animal:           G=Gato        P=Perro
  an_tp_size:             P=Pequeño     M=Mediano     G=Grande
  an_tp_sex:              M=Macho       H=Hembra
  an_in_require_vet_review: S=Si        N=No
  an_st_vet_review:       P=Pendiente   A=Activo      R=Revisado

  ── Solicitud ───────────────────────────────────────
  so_tp_solicitud:        P=Perdida     E=Encontrada
  so_st_solicitud:        P=Pendiente   R=Rechazada   A=Activa

  ── Refugio ─────────────────────────────────────────
  re_st_refugio:          P=Pendiente   A=Activo      X=Lleno   I=Inactivo  R=Rechazado
  re_tp_species_allowed:  G=Gato        P=Perro       A=Ambos
  re_tp_animal_special_needs: AH=Heridos-en-recuperación  CA=Cachorros  AM=Adultos-mayores
  re_in_has_pets:         S=Si          N=No
  re_tp_housing:          CP=Casa-con-patio  CS=Casa-sin-patio  AP=Apartamento
  re_in_fence_housing:    C=Completo    P=Parcial     N=No

  ── RevisionVeterinaria ─────────────────────────────
  rv_st_vet_review:       P=Pendiente   A=Activo      R=Revisado

  ── Contacto ────────────────────────────────────────
  co_tp_contact_method:   W=WhatsApp    P=Phone       E=Email   A=Any
  co_in_allow_public:     S=Si          N=No

  ── Estado ──────────────────────────────────────────
  es_st_estado:           A=Activo      I=Inactivo

  ── User ────────────────────────────────────────────
  us_in_veterinarian:     S=Si          N=No
  us_st_user:             A=Activo      I=Inactivo    B=Bloqueado

  ── Role ────────────────────────────────────────────
  ro_st_role:             A=Activo      I=Inactivo
  ro_nm_role:             SUPERADMIN | ADMIN | VET | PUBLICO
*/
