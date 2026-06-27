-- =================================================================
-- =================================================================
--           S T O R E D    P R O C E D U R E S
-- =================================================================
-- =================================================================

DELIMITER $$

-- ============================================================
--            STORED PROCEDURES FOR: role
-- ============================================================

DROP PROCEDURE IF EXISTS sp_role_insert$$
CREATE PROCEDURE sp_role_insert(
    IN p_ro_nm_role VARCHAR(20),
    IN p_ro_st_role CHAR(1)
)
    COMMENT 'Inserts a new role and returns the created record.'
BEGIN
    INSERT INTO role (ro_nm_role, ro_st_role, ro_dt_created, ro_dt_updated)
    VALUES (p_ro_nm_role, p_ro_st_role, NOW(), NOW());
    SELECT * FROM role WHERE ro_cd_role = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_role_update$$
CREATE PROCEDURE sp_role_update(
    IN p_ro_cd_role INT UNSIGNED,
    IN p_ro_nm_role VARCHAR(20),
    IN p_ro_st_role CHAR(1)
)
    COMMENT 'Updates an existing role and returns the updated record.'
BEGIN
    UPDATE role
    SET
        ro_nm_role = IFNULL(p_ro_nm_role, ro_nm_role),
        ro_st_role = IFNULL(p_ro_st_role, ro_st_role),
        ro_dt_updated = NOW()
    WHERE
        ro_cd_role = p_ro_cd_role;
    SELECT * FROM role WHERE ro_cd_role = p_ro_cd_role;
END$$

DROP PROCEDURE IF EXISTS sp_role_delete$$
CREATE PROCEDURE sp_role_delete(
    IN p_ro_cd_role INT UNSIGNED
)
    COMMENT 'Deletes a role by its ID'
BEGIN
    DELETE FROM role WHERE ro_cd_role = p_ro_cd_role;
END$$

DROP PROCEDURE IF EXISTS sp_role_search$$
CREATE PROCEDURE sp_role_search(
    IN p_ro_cd_role INT UNSIGNED,
    IN p_ro_nm_role VARCHAR(20),
    IN p_ro_st_role CHAR(1)
)
    COMMENT 'Searches for roles with optional filters'
BEGIN
    SELECT * FROM role
    WHERE
        (p_ro_cd_role IS NULL OR ro_cd_role = p_ro_cd_role) AND
        (p_ro_nm_role IS NULL OR ro_nm_role LIKE CONCAT('%', p_ro_nm_role, '%')) AND
        (p_ro_st_role IS NULL OR ro_st_role = p_ro_st_role);
END$$


-- ============================================================
--            STORED PROCEDURES FOR: user
-- ============================================================

DROP PROCEDURE IF EXISTS sp_user_insert$$
CREATE PROCEDURE sp_user_insert(
    IN p_us_ro_cd_role INT UNSIGNED,
    IN p_us_nm_first_name VARCHAR(100),
    IN p_us_nm_last_name VARCHAR(100),
    IN p_us_de_email VARCHAR(100),
    IN p_us_de_phone VARCHAR(20),
    IN p_us_de_password_hash VARCHAR(255),
    IN p_us_in_veterinarian CHAR(1),
    IN p_us_st_user CHAR(1)
)
    COMMENT 'Inserts a new user and returns the created record.'
BEGIN
    INSERT INTO user (
        us_ro_cd_role, us_nm_first_name, us_nm_last_name, us_de_email,
        us_de_phone, us_de_password_hash, us_in_veterinarian, us_st_user,
        us_dt_last_login, us_dt_created, us_dt_updated
    ) VALUES (
        p_us_ro_cd_role, p_us_nm_first_name, p_us_nm_last_name, p_us_de_email,
        p_us_de_phone, p_us_de_password_hash, p_us_in_veterinarian, p_us_st_user,
        NOW(), NOW(), NOW()
    );
    SELECT * FROM user WHERE us_cd_user = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_user_update$$
CREATE PROCEDURE sp_user_update(
    IN p_us_cd_user INT UNSIGNED,
    IN p_us_ro_cd_role INT UNSIGNED,
    IN p_us_nm_first_name VARCHAR(100),
    IN p_us_nm_last_name VARCHAR(100),
    IN p_us_de_email VARCHAR(100),
    IN p_us_de_phone VARCHAR(20),
    IN p_us_de_password_hash VARCHAR(255),
    IN p_us_in_veterinarian CHAR(1),
    IN p_us_st_user CHAR(1)
)
    COMMENT 'Updates an existing user and returns the updated record.'
BEGIN
    UPDATE user
    SET
        us_ro_cd_role = IFNULL(p_us_ro_cd_role, us_ro_cd_role),
        us_nm_first_name = IFNULL(p_us_nm_first_name, us_nm_first_name),
        us_nm_last_name = IFNULL(p_us_nm_last_name, us_nm_last_name),
        us_de_email = IFNULL(p_us_de_email, us_de_email),
        us_de_phone = IFNULL(p_us_de_phone, us_de_phone),
        us_de_password_hash = IFNULL(p_us_de_password_hash, us_de_password_hash),
        us_in_veterinarian = IFNULL(p_us_in_veterinarian, us_in_veterinarian),
        us_st_user = IFNULL(p_us_st_user, us_st_user),
        us_dt_updated = NOW()
    WHERE
        us_cd_user = p_us_cd_user;
    SELECT * FROM user WHERE us_cd_user = p_us_cd_user;
END$$

DROP PROCEDURE IF EXISTS sp_user_delete$$
CREATE PROCEDURE sp_user_delete(
    IN p_us_cd_user INT UNSIGNED
)
BEGIN
    DELETE FROM user WHERE us_cd_user = p_us_cd_user;
END$$

DROP PROCEDURE IF EXISTS sp_user_search$$
CREATE PROCEDURE sp_user_search(
    IN p_us_cd_user INT UNSIGNED,
    IN p_us_ro_cd_role INT UNSIGNED,
    IN p_us_nm_first_name VARCHAR(100),
    IN p_us_nm_last_name VARCHAR(100),
    IN p_us_de_email VARCHAR(100),
    IN p_us_st_user CHAR(1)
)
BEGIN
    SELECT * FROM user
    WHERE
        (p_us_cd_user IS NULL OR us_cd_user = p_us_cd_user) AND
        (p_us_ro_cd_role IS NULL OR us_ro_cd_role = p_us_ro_cd_role) AND
        (p_us_nm_first_name IS NULL OR us_nm_first_name LIKE CONCAT('%', p_us_nm_first_name, '%')) AND
        (p_us_nm_last_name IS NULL OR us_nm_last_name LIKE CONCAT('%', p_us_nm_last_name, '%')) AND
        (p_us_de_email IS NULL OR us_de_email LIKE CONCAT('%', p_us_de_email, '%')) AND
        (p_us_st_user IS NULL OR us_st_user = p_us_st_user);
END$$


-- ============================================================
--            STORED PROCEDURES FOR: estado
-- ============================================================

DROP PROCEDURE IF EXISTS sp_estado_insert$$
CREATE PROCEDURE sp_estado_insert(
    IN p_es_cd_country INT,
    IN p_es_nm_estado VARCHAR(100),
    IN p_es_st_estado CHAR(1)
)
BEGIN
    INSERT INTO estado (es_cd_country, es_nm_estado, es_st_estado, es_dt_created, es_dt_updated)
    VALUES (p_es_cd_country, p_es_nm_estado, p_es_st_estado, NOW(), NOW());
    SELECT * FROM estado WHERE es_cd_estado = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_estado_update$$
CREATE PROCEDURE sp_estado_update(
    IN p_es_cd_estado INT UNSIGNED,
    IN p_es_cd_country INT,
    IN p_es_nm_estado VARCHAR(100),
    IN p_es_st_estado CHAR(1)
)
BEGIN
    UPDATE estado
    SET
        es_cd_country = IFNULL(p_es_cd_country, es_cd_country),
        es_nm_estado = IFNULL(p_es_nm_estado, es_nm_estado),
        es_st_estado = IFNULL(p_es_st_estado, es_st_estado),
        es_dt_updated = NOW()
    WHERE
        es_cd_estado = p_es_cd_estado;
    SELECT * FROM estado WHERE es_cd_estado = p_es_cd_estado;
END$$

DROP PROCEDURE IF EXISTS sp_estado_delete$$
CREATE PROCEDURE sp_estado_delete(
    IN p_es_cd_estado INT UNSIGNED
)
BEGIN
    DELETE FROM estado WHERE es_cd_estado = p_es_cd_estado;
END$$

DROP PROCEDURE IF EXISTS sp_estado_search$$
CREATE PROCEDURE sp_estado_search(
    IN p_es_cd_estado INT UNSIGNED,
    IN p_es_nm_estado VARCHAR(100),
    IN p_es_st_estado CHAR(1)
)
BEGIN
    SELECT * FROM estado
    WHERE
        (p_es_cd_estado IS NULL OR es_cd_estado = p_es_cd_estado) AND
        (p_es_nm_estado IS NULL OR es_nm_estado LIKE CONCAT('%', p_es_nm_estado, '%')) AND
        (p_es_st_estado IS NULL OR es_st_estado = p_es_st_estado);
END$$


-- ============================================================
--            STORED PROCEDURES FOR: ubicacion
-- ============================================================

DROP PROCEDURE IF EXISTS sp_ubicacion_insert$$
CREATE PROCEDURE sp_ubicacion_insert(
    IN p_ur_es_cd_estado INT UNSIGNED,
    IN p_ur_nm_city VARCHAR(100),
    IN p_ur_nm_sector VARCHAR(150),
    IN p_ur_de_address VARCHAR(255),
    IN p_ur_de_reference VARCHAR(255),
    IN p_ur_de_postal_code VARCHAR(10),
    IN p_ur_nu_latitude DECIMAL(10,7),
    IN p_ur_nu_longitude DECIMAL(10,7)
)
BEGIN
    INSERT INTO ubicacion (
        ur_es_cd_estado, ur_nm_city, ur_nm_sector, ur_de_address,
        ur_de_reference, ur_de_postal_code, ur_nu_latitude, ur_nu_longitude,
        ur_dt_created, ur_dt_updated
    ) VALUES (
        p_ur_es_cd_estado, p_ur_nm_city, p_ur_nm_sector, p_ur_de_address,
        p_ur_de_reference, p_ur_de_postal_code, p_ur_nu_latitude, p_ur_nu_longitude,
        NOW(), NOW()
    );
    SELECT * FROM ubicacion WHERE ur_cd_ubicacion = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_ubicacion_update$$
CREATE PROCEDURE sp_ubicacion_update(
    IN p_ur_cd_ubicacion INT UNSIGNED,
    IN p_ur_es_cd_estado INT UNSIGNED,
    IN p_ur_nm_city VARCHAR(100),
    IN p_ur_nm_sector VARCHAR(150),
    IN p_ur_de_address VARCHAR(255),
    IN p_ur_de_reference VARCHAR(255),
    IN p_ur_de_postal_code VARCHAR(10),
    IN p_ur_nu_latitude DECIMAL(10,7),
    IN p_ur_nu_longitude DECIMAL(10,7)
)
BEGIN
    UPDATE ubicacion
    SET
        ur_es_cd_estado = IFNULL(p_ur_es_cd_estado, ur_es_cd_estado),
        ur_nm_city = IFNULL(p_ur_nm_city, ur_nm_city),
        ur_nm_sector = IFNULL(p_ur_nm_sector, ur_nm_sector),
        ur_de_address = IFNULL(p_ur_de_address, ur_de_address),
        ur_de_reference = IFNULL(p_ur_de_reference, ur_de_reference),
        ur_de_postal_code = IFNULL(p_ur_de_postal_code, ur_de_postal_code),
        ur_nu_latitude = IFNULL(p_ur_nu_latitude, ur_nu_latitude),
        ur_nu_longitude = IFNULL(p_ur_nu_longitude, ur_nu_longitude),
        ur_dt_updated = NOW()
    WHERE
        ur_cd_ubicacion = p_ur_cd_ubicacion;
    SELECT * FROM ubicacion WHERE ur_cd_ubicacion = p_ur_cd_ubicacion;
END$$

DROP PROCEDURE IF EXISTS sp_ubicacion_delete$$
CREATE PROCEDURE sp_ubicacion_delete(
    IN p_ur_cd_ubicacion INT UNSIGNED
)
BEGIN
    DELETE FROM ubicacion WHERE ur_cd_ubicacion = p_ur_cd_ubicacion;
END$$

DROP PROCEDURE IF EXISTS sp_ubicacion_search$$
CREATE PROCEDURE sp_ubicacion_search(
    IN p_ur_cd_ubicacion INT UNSIGNED,
    IN p_ur_es_cd_estado INT UNSIGNED,
    IN p_ur_nm_city VARCHAR(100)
)
BEGIN
    SELECT * FROM ubicacion
    WHERE
        (p_ur_cd_ubicacion IS NULL OR ur_cd_ubicacion = p_ur_cd_ubicacion) AND
        (p_ur_es_cd_estado IS NULL OR ur_es_cd_estado = p_ur_es_cd_estado) AND
        (p_ur_nm_city IS NULL OR ur_nm_city LIKE CONCAT('%', p_ur_nm_city, '%'));
END$$


-- ============================================================
--            STORED PROCEDURES FOR: contacto
-- ============================================================

DROP PROCEDURE IF EXISTS sp_contacto_insert$$
CREATE PROCEDURE sp_contacto_insert(
    IN p_co_nm_first_name VARCHAR(100),
    IN p_co_nm_last_name VARCHAR(100),
    IN p_co_de_email VARCHAR(100),
    IN p_co_de_phone VARCHAR(20),
    IN p_co_de_whatsapp VARCHAR(20),
    IN p_co_tp_contact_method CHAR(1),
    IN p_co_in_allow_public CHAR(1)
)
BEGIN
    INSERT INTO contacto (
        co_nm_first_name, co_nm_last_name, co_de_email, co_de_phone,
        co_de_whatsapp, co_tp_contact_method, co_in_allow_public,
        co_dt_created, co_dt_updated
    ) VALUES (
        p_co_nm_first_name, p_co_nm_last_name, p_co_de_email, p_co_de_phone,
        p_co_de_whatsapp, p_co_tp_contact_method, p_co_in_allow_public,
        NOW(), NOW()
    );
    SELECT * FROM contacto WHERE co_cd_contacto = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_contacto_update$$
CREATE PROCEDURE sp_contacto_update(
    IN p_co_cd_contacto INT UNSIGNED,
    IN p_co_nm_first_name VARCHAR(100),
    IN p_co_nm_last_name VARCHAR(100),
    IN p_co_de_email VARCHAR(100),
    IN p_co_de_phone VARCHAR(20),
    IN p_co_de_whatsapp VARCHAR(20),
    IN p_co_tp_contact_method CHAR(1),
    IN p_co_in_allow_public CHAR(1)
)
BEGIN
    UPDATE contacto
    SET
        co_nm_first_name = IFNULL(p_co_nm_first_name, co_nm_first_name),
        co_nm_last_name = IFNULL(p_co_nm_last_name, co_nm_last_name),
        co_de_email = IFNULL(p_co_de_email, co_de_email),
        co_de_phone = IFNULL(p_co_de_phone, co_de_phone),
        co_de_whatsapp = IFNULL(p_co_de_whatsapp, co_de_whatsapp),
        co_tp_contact_method = IFNULL(p_co_tp_contact_method, co_tp_contact_method),
        co_in_allow_public = IFNULL(p_co_in_allow_public, co_in_allow_public),
        co_dt_updated = NOW()
    WHERE
        co_cd_contacto = p_co_cd_contacto;
    SELECT * FROM contacto WHERE co_cd_contacto = p_co_cd_contacto;
END$$

DROP PROCEDURE IF EXISTS sp_contacto_delete$$
CREATE PROCEDURE sp_contacto_delete(
    IN p_co_cd_contacto INT UNSIGNED
)
BEGIN
    DELETE FROM contacto WHERE co_cd_contacto = p_co_cd_contacto;
END$$

DROP PROCEDURE IF EXISTS sp_contacto_search$$
CREATE PROCEDURE sp_contacto_search(
    IN p_co_cd_contacto INT UNSIGNED,
    IN p_co_nm_first_name VARCHAR(100),
    IN p_co_nm_last_name VARCHAR(100),
    IN p_co_de_email VARCHAR(100)
)
BEGIN
    SELECT * FROM contacto
    WHERE
        (p_co_cd_contacto IS NULL OR co_cd_contacto = p_co_cd_contacto) AND
        (p_co_nm_first_name IS NULL OR co_nm_first_name LIKE CONCAT('%', p_co_nm_first_name, '%')) AND
        (p_co_nm_last_name IS NULL OR co_nm_last_name LIKE CONCAT('%', p_co_nm_last_name, '%')) AND
        (p_co_de_email IS NULL OR co_de_email LIKE CONCAT('%', p_co_de_email, '%'));
END$$


-- ============================================================
--            STORED PROCEDURES FOR: animal
-- ============================================================

DROP PROCEDURE IF EXISTS sp_animal_insert$$
CREATE PROCEDURE sp_animal_insert(
    IN p_an_re_cd_refugio INT UNSIGNED,
    IN p_an_nm_animal VARCHAR(100),
    IN p_an_tp_animal CHAR(1),
    IN p_an_de_breed VARCHAR(100),
    IN p_an_de_color VARCHAR(100),
    IN p_an_tp_size CHAR(1),
    IN p_an_tp_sex CHAR(1),
    IN p_an_nu_approx_age TINYINT UNSIGNED,
    IN p_an_de_animal TEXT,
    IN p_an_in_require_vet_review CHAR(1),
    IN p_an_de_observacion_vet TEXT,
    IN p_an_st_vet_review CHAR(1)
)
BEGIN
    INSERT INTO animal (
        an_re_cd_refugio, an_nm_animal, an_tp_animal, an_de_breed, an_de_color,
        an_tp_size, an_tp_sex, an_nu_approx_age, an_de_animal,
        an_in_require_vet_review, an_de_observacion_vet, an_st_vet_review,
        an_dt_created, an_dt_updated
    ) VALUES (
        p_an_re_cd_refugio, p_an_nm_animal, p_an_tp_animal, p_an_de_breed, p_an_de_color,
        p_an_tp_size, p_an_tp_sex, p_an_nu_approx_age, p_an_de_animal,
        p_an_in_require_vet_review, p_an_de_observacion_vet, p_an_st_vet_review,
        NOW(), NOW()
    );
    SELECT * FROM animal WHERE an_cd_animal = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_animal_update$$
CREATE PROCEDURE sp_animal_update(
    IN p_an_cd_animal INT UNSIGNED,
    IN p_an_re_cd_refugio INT UNSIGNED,
    IN p_an_nm_animal VARCHAR(100),
    IN p_an_tp_animal CHAR(1),
    IN p_an_de_breed VARCHAR(100),
    IN p_an_de_color VARCHAR(100),
    IN p_an_tp_size CHAR(1),
    IN p_an_tp_sex CHAR(1),
    IN p_an_nu_approx_age TINYINT UNSIGNED,
    IN p_an_de_animal TEXT,
    IN p_an_in_require_vet_review CHAR(1),
    IN p_an_de_observacion_vet TEXT,
    IN p_an_st_vet_review CHAR(1)
)
BEGIN
    UPDATE animal
    SET
        an_re_cd_refugio = IFNULL(p_an_re_cd_refugio, an_re_cd_refugio),
        an_nm_animal = IFNULL(p_an_nm_animal, an_nm_animal),
        an_tp_animal = IFNULL(p_an_tp_animal, an_tp_animal),
        an_de_breed = IFNULL(p_an_de_breed, an_de_breed),
        an_de_color = IFNULL(p_an_de_color, an_de_color),
        an_tp_size = IFNULL(p_an_tp_size, an_tp_size),
        an_tp_sex = IFNULL(p_an_tp_sex, an_tp_sex),
        an_nu_approx_age = IFNULL(p_an_nu_approx_age, an_nu_approx_age),
        an_de_animal = IFNULL(p_an_de_animal, an_de_animal),
        an_in_require_vet_review = IFNULL(p_an_in_require_vet_review, an_in_require_vet_review),
        an_de_observacion_vet = IFNULL(p_an_de_observacion_vet, an_de_observacion_vet),
        an_st_vet_review = IFNULL(p_an_st_vet_review, an_st_vet_review),
        an_dt_updated = NOW()
    WHERE
        an_cd_animal = p_an_cd_animal;
    SELECT * FROM animal WHERE an_cd_animal = p_an_cd_animal;
END$$

DROP PROCEDURE IF EXISTS sp_animal_delete$$
CREATE PROCEDURE sp_animal_delete(
    IN p_an_cd_animal INT UNSIGNED
)
BEGIN
    DELETE FROM animal WHERE an_cd_animal = p_an_cd_animal;
END$$

DROP PROCEDURE IF EXISTS sp_animal_search$$
CREATE PROCEDURE sp_animal_search(
    IN p_an_cd_animal INT UNSIGNED,
    IN p_an_tp_animal CHAR(1),
    IN p_an_tp_size CHAR(1),
    IN p_an_tp_sex CHAR(1),
    IN p_an_st_vet_review CHAR(1)
)
BEGIN
    SELECT * FROM animal
    WHERE
        (p_an_cd_animal IS NULL OR an_cd_animal = p_an_cd_animal) AND
        (p_an_tp_animal IS NULL OR an_tp_animal = p_an_tp_animal) AND
        (p_an_tp_size IS NULL OR an_tp_size = p_an_tp_size) AND
        (p_an_tp_sex IS NULL OR an_tp_sex = p_an_tp_sex) AND
        (p_an_st_vet_review IS NULL OR an_st_vet_review = p_an_st_vet_review);
END$$


-- ============================================================
--            STORED PROCEDURES FOR: refugio
-- ============================================================

DROP PROCEDURE IF EXISTS sp_refugio_insert$$
CREATE PROCEDURE sp_refugio_insert(
    IN p_re_cd_contacto INT UNSIGNED,
    IN p_re_ur_cd_ubicacion INT UNSIGNED,
    IN p_re_nm_refugio VARCHAR(150),
    IN p_re_st_refugio CHAR(1),
    IN p_re_nu_capacity_total SMALLINT UNSIGNED,
    IN p_re_nu_capacity_available SMALLINT UNSIGNED,
    IN p_re_tp_species_allowed CHAR(1),
    IN p_re_tp_animal_special_needs VARCHAR(2),
    IN p_re_in_has_pets CHAR(1),
    IN p_re_tp_housing VARCHAR(2),
    IN p_re_in_fence_housing CHAR(1),
    IN p_re_de_additional_note TEXT,
    IN p_re_de_observacion_vet TEXT
)
BEGIN
    INSERT INTO refugio (
        re_cd_contacto, re_ur_cd_ubicacion, re_nm_refugio, re_st_refugio,
        re_nu_capacity_total, re_nu_capacity_available, re_tp_species_allowed,
        re_tp_animal_special_needs, re_in_has_pets, re_tp_housing,
        re_in_fence_housing, re_de_additional_note, re_de_observacion_vet,
        re_dt_created, re_dt_updated
    ) VALUES (
        p_re_cd_contacto, p_re_ur_cd_ubicacion, p_re_nm_refugio, p_re_st_refugio,
        p_re_nu_capacity_total, p_re_nu_capacity_available, p_re_tp_species_allowed,
        p_re_tp_animal_special_needs, p_re_in_has_pets, p_re_tp_housing,
        p_re_in_fence_housing, p_re_de_additional_note, p_re_de_observacion_vet,
        NOW(), NOW()
    );
    SELECT * FROM refugio WHERE re_cd_refugio = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_refugio_update$$
CREATE PROCEDURE sp_refugio_update(
    IN p_re_cd_refugio INT UNSIGNED,
    IN p_re_cd_contacto INT UNSIGNED,
    IN p_re_ur_cd_ubicacion INT UNSIGNED,
    IN p_re_nm_refugio VARCHAR(150),
    IN p_re_st_refugio CHAR(1),
    IN p_re_nu_capacity_total SMALLINT UNSIGNED,
    IN p_re_nu_capacity_available SMALLINT UNSIGNED,
    IN p_re_tp_species_allowed CHAR(1),
    IN p_re_tp_animal_special_needs VARCHAR(2),
    IN p_re_in_has_pets CHAR(1),
    IN p_re_tp_housing VARCHAR(2),
    IN p_re_in_fence_housing CHAR(1),
    IN p_re_de_additional_note TEXT,
    IN p_re_de_observacion_vet TEXT
)
BEGIN
    UPDATE refugio
    SET
        re_cd_contacto = IFNULL(p_re_cd_contacto, re_cd_contacto),
        re_ur_cd_ubicacion = IFNULL(p_re_ur_cd_ubicacion, re_ur_cd_ubicacion),
        re_nm_refugio = IFNULL(p_re_nm_refugio, re_nm_refugio),
        re_st_refugio = IFNULL(p_re_st_refugio, re_st_refugio),
        re_nu_capacity_total = IFNULL(p_re_nu_capacity_total, re_nu_capacity_total),
        re_nu_capacity_available = IFNULL(p_re_nu_capacity_available, re_nu_capacity_available),
        re_tp_species_allowed = IFNULL(p_re_tp_species_allowed, re_tp_species_allowed),
        re_tp_animal_special_needs = IFNULL(p_re_tp_animal_special_needs, re_tp_animal_special_needs),
        re_in_has_pets = IFNULL(p_re_in_has_pets, re_in_has_pets),
        re_tp_housing = IFNULL(p_re_tp_housing, re_tp_housing),
        re_in_fence_housing = IFNULL(p_re_in_fence_housing, re_in_fence_housing),
        re_de_additional_note = IFNULL(p_re_de_additional_note, re_de_additional_note),
        re_de_observacion_vet = IFNULL(p_re_de_observacion_vet, re_de_observacion_vet),
        re_dt_updated = NOW()
    WHERE
        re_cd_refugio = p_re_cd_refugio;
    SELECT * FROM refugio WHERE re_cd_refugio = p_re_cd_refugio;
END$$

DROP PROCEDURE IF EXISTS sp_refugio_delete$$
CREATE PROCEDURE sp_refugio_delete(
    IN p_re_cd_refugio INT UNSIGNED
)
BEGIN
    DELETE FROM refugio WHERE re_cd_refugio = p_re_cd_refugio;
END$$

DROP PROCEDURE IF EXISTS sp_refugio_search$$
CREATE PROCEDURE sp_refugio_search(
    IN p_re_cd_refugio INT UNSIGNED,
    IN p_re_ur_cd_ubicacion INT UNSIGNED,
    IN p_re_st_refugio CHAR(1),
    IN p_re_tp_species_allowed CHAR(1)
)
BEGIN
    SELECT * FROM refugio
    WHERE
        (p_re_cd_refugio IS NULL OR re_cd_refugio = p_re_cd_refugio) AND
        (p_re_ur_cd_ubicacion IS NULL OR re_ur_cd_ubicacion = p_re_ur_cd_ubicacion) AND
        (p_re_st_refugio IS NULL OR re_st_refugio = p_re_st_refugio) AND
        (p_re_tp_species_allowed IS NULL OR re_tp_species_allowed = p_re_tp_species_allowed);
END$$


-- ============================================================
--            STORED PROCEDURES FOR: solicitud
-- ============================================================

DROP PROCEDURE IF EXISTS sp_solicitud_insert$$
CREATE PROCEDURE sp_solicitud_insert(
    IN p_so_an_cd_animal INT UNSIGNED,
    IN p_so_co_cd_contacto INT UNSIGNED,
    IN p_so_ur_cd_ubicacion INT UNSIGNED,
    IN p_so_tp_solicitud CHAR(1),
    IN p_so_dt_evento DATETIME,
    IN p_so_st_solicitud CHAR(1),
    IN p_so_de_observacion_vet TEXT,
    IN p_so_de_s3_folder_path VARCHAR(500),
    IN p_so_de_main_photo_url VARCHAR(500)
)
BEGIN
    INSERT INTO solicitud (
        so_an_cd_animal, so_co_cd_contacto, so_ur_cd_ubicacion, so_tp_solicitud,
        so_dt_evento, so_st_solicitud, so_de_observacion_vet, so_de_s3_folder_path,
        so_de_main_photo_url, so_dt_created, so_dt_updated
    ) VALUES (
        p_so_an_cd_animal, p_so_co_cd_contacto, p_so_ur_cd_ubicacion, p_so_tp_solicitud,
        p_so_dt_evento, p_so_st_solicitud, p_so_de_observacion_vet, p_so_de_s3_folder_path,
        p_so_de_main_photo_url, NOW(), NOW()
    );
    SELECT * FROM solicitud WHERE so_cd_solicitud = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_solicitud_update$$
CREATE PROCEDURE sp_solicitud_update(
    IN p_so_cd_solicitud INT UNSIGNED,
    IN p_so_an_cd_animal INT UNSIGNED,
    IN p_so_co_cd_contacto INT UNSIGNED,
    IN p_so_ur_cd_ubicacion INT UNSIGNED,
    IN p_so_tp_solicitud CHAR(1),
    IN p_so_dt_evento DATETIME,
    IN p_so_st_solicitud CHAR(1),
    IN p_so_de_observacion_vet TEXT,
    IN p_so_de_s3_folder_path VARCHAR(500),
    IN p_so_de_main_photo_url VARCHAR(500)
)
BEGIN
    UPDATE solicitud
    SET
        so_an_cd_animal = IFNULL(p_so_an_cd_animal, so_an_cd_animal),
        so_co_cd_contacto = IFNULL(p_so_co_cd_contacto, so_co_cd_contacto),
        so_ur_cd_ubicacion = IFNULL(p_so_ur_cd_ubicacion, so_ur_cd_ubicacion),
        so_tp_solicitud = IFNULL(p_so_tp_solicitud, so_tp_solicitud),
        so_dt_evento = IFNULL(p_so_dt_evento, so_dt_evento),
        so_st_solicitud = IFNULL(p_so_st_solicitud, so_st_solicitud),
        so_de_observacion_vet = IFNULL(p_so_de_observacion_vet, so_de_observacion_vet),
        so_de_s3_folder_path = IFNULL(p_so_de_s3_folder_path, so_de_s3_folder_path),
        so_de_main_photo_url = IFNULL(p_so_de_main_photo_url, so_de_main_photo_url),
        so_dt_updated = NOW()
    WHERE
        so_cd_solicitud = p_so_cd_solicitud;
    SELECT * FROM solicitud WHERE so_cd_solicitud = p_so_cd_solicitud;
END$$

DROP PROCEDURE IF EXISTS sp_solicitud_delete$$
CREATE PROCEDURE sp_solicitud_delete(
    IN p_so_cd_solicitud INT UNSIGNED
)
BEGIN
    DELETE FROM solicitud WHERE so_cd_solicitud = p_so_cd_solicitud;
END$$

DROP PROCEDURE IF EXISTS sp_solicitud_search$$
CREATE PROCEDURE sp_solicitud_search(
    IN p_so_cd_solicitud INT UNSIGNED,
    IN p_so_tp_solicitud CHAR(1),
    IN p_so_st_solicitud CHAR(1),
    IN p_so_ur_cd_ubicacion INT UNSIGNED
)
BEGIN
    SELECT * FROM solicitud
    WHERE
        (p_so_cd_solicitud IS NULL OR so_cd_solicitud = p_so_cd_solicitud) AND
        (p_so_tp_solicitud IS NULL OR so_tp_solicitud = p_so_tp_solicitud) AND
        (p_so_st_solicitud IS NULL OR so_st_solicitud = p_so_st_solicitud) AND
        (p_so_ur_cd_ubicacion IS NULL OR so_ur_cd_ubicacion = p_so_ur_cd_ubicacion);
END$$


-- ============================================================
--            STORED PROCEDURES FOR: revision_veterinaria
-- ============================================================

DROP PROCEDURE IF EXISTS sp_revision_veterinaria_insert$$
CREATE PROCEDURE sp_revision_veterinaria_insert(
    IN p_rv_an_cd_animal INT UNSIGNED,
    IN p_rv_us_cd_user INT UNSIGNED,
    IN p_rv_st_vet_review CHAR(1),
    IN p_rv_de_comment TEXT
)
BEGIN
    INSERT INTO revision_veterinaria (
        rv_an_cd_animal, rv_us_cd_user, rv_st_vet_review, rv_de_comment,
        rv_dt_created, rv_dt_updated
    ) VALUES (
        p_rv_an_cd_animal, p_rv_us_cd_user, p_rv_st_vet_review, p_rv_de_comment,
        NOW(), NOW()
    );
    SELECT * FROM revision_veterinaria WHERE rv_cd_revision_vet = LAST_INSERT_ID();
END$$

DROP PROCEDURE IF EXISTS sp_revision_veterinaria_update$$
CREATE PROCEDURE sp_revision_veterinaria_update(
    IN p_rv_cd_revision_vet INT UNSIGNED,
    IN p_rv_an_cd_animal INT UNSIGNED,
    IN p_rv_us_cd_user INT UNSIGNED,
    IN p_rv_st_vet_review CHAR(1),
    IN p_rv_de_comment TEXT
)
BEGIN
    UPDATE revision_veterinaria
    SET
        rv_an_cd_animal = IFNULL(p_rv_an_cd_animal, rv_an_cd_animal),
        rv_us_cd_user = IFNULL(p_rv_us_cd_user, rv_us_cd_user),
        rv_st_vet_review = IFNULL(p_rv_st_vet_review, rv_st_vet_review),
        rv_de_comment = IFNULL(p_rv_de_comment, rv_de_comment),
        rv_dt_updated = NOW()
    WHERE
        rv_cd_revision_vet = p_rv_cd_revision_vet;
    SELECT * FROM revision_veterinaria WHERE rv_cd_revision_vet = p_rv_cd_revision_vet;
END$$

DROP PROCEDURE IF EXISTS sp_revision_veterinaria_delete$$
CREATE PROCEDURE sp_revision_veterinaria_delete(
    IN p_rv_cd_revision_vet INT UNSIGNED
)
BEGIN
    DELETE FROM revision_veterinaria WHERE rv_cd_revision_vet = p_rv_cd_revision_vet;
END$$

DROP PROCEDURE IF EXISTS sp_revision_veterinaria_search$$
CREATE PROCEDURE sp_revision_veterinaria_search(
    IN p_rv_cd_revision_vet INT UNSIGNED,
    IN p_rv_an_cd_animal INT UNSIGNED,
    IN p_rv_us_cd_user INT UNSIGNED,
    IN p_rv_st_vet_review CHAR(1)
)
BEGIN
    SELECT * FROM revision_veterinaria
    WHERE
        (p_rv_cd_revision_vet IS NULL OR rv_cd_revision_vet = p_rv_cd_revision_vet) AND
        (p_rv_an_cd_animal IS NULL OR rv_an_cd_animal = p_rv_an_cd_animal) AND
        (p_rv_us_cd_user IS NULL OR rv_us_cd_user = p_rv_us_cd_user) AND
        (p_rv_st_vet_review IS NULL OR rv_st_vet_review = p_rv_st_vet_review);
END$$

-- ============================================================
--            STORED PROCEDURES FOR: auth
-- ============================================================

DROP PROCEDURE IF EXISTS sp_user_login_validate$$
CREATE PROCEDURE sp_user_login_validate(
    IN p_username VARCHAR(100)
)
    COMMENT 'Looks up an active user by email for login. Password verification is done in the application layer (BCrypt).'
BEGIN
    DECLARE v_user_count INT DEFAULT 0;

    SELECT COUNT(*) INTO v_user_count
    FROM user u
    WHERE u.us_de_email = p_username
      AND u.us_st_user = 'A';

    IF v_user_count = 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'user/password not found';
    END IF;

    SELECT
        u.us_cd_user                                        AS user_id,
        u.us_de_email                                       AS username,
        CONCAT(u.us_nm_first_name, ' ', u.us_nm_last_name) AS full_name,
        u.us_de_email                                       AS email,
        u.us_de_password_hash                               AS password_hash,
        r.ro_cd_role                                        AS role_id,
        r.ro_nm_role                                        AS role_name
    FROM user u
    INNER JOIN role r ON u.us_ro_cd_role = r.ro_cd_role
    WHERE u.us_de_email = p_username
      AND u.us_st_user = 'A';
END$$

DELIMITER ;