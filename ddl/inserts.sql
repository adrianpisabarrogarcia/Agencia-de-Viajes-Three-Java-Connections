-- H2
INSERT INTO AGENCIA
VALUES (1,
        'Viajes Eroski',
        '2018-01-01',
        'Calle de la agencia Eroski',
        '123456789',
        'vitoria@viajeseroski.es',
        'www.viajeseroski.es');
-- SQLLite
INSERT INTO AGENCIA
VALUES (1,
        'Viajes Bakomat',
        '2019-02-02',
        'Calle de la agencia Bakomat',
        '945175259',
        'vitoria@vaijesbakomat.es',
        'www.viajesbakomat.es');
-- MySQL
INSERT INTO AGENCIA
VALUES (1,
        'Viajes Bidasoa',
        '2020-03-03',
        'Calle de la agencia Bidasoa',
        '045175260',
        'vitoria@viajesbidasoa.es',
        'www.viajesbakomat.es');

-- Empleado
INSERT INTO EMPLEADO
VALUES (1,
        '12345678A',
        'Juan',
        'Pérez',
        '1990-01-01',
        '2018-01-01',
        'Española',
        'Director',
        true);
INSERT INTO EMPLEADO
VALUES (2,
        '12345678B',
        'Pedro',
        'García',
        '1990-01-01',
        '2018-01-01',
        'Española',
        'Director',
        true);
INSERT INTO EMPLEADO
VALUES (3,
        '12345678C',
        'Luis',
        'González',
        '1990-01-01',
        '2018-01-01',
        'Española',
        'Director',
        true);
INSERT INTO EMPLEADO
VALUES (4,
        '12345678D',
        'Ana',
        'Martínez',
        '1990-01-01',
        '2018-01-01',
        'Española',
        'Director',
        true);
INSERT INTO EMPLEADO
VALUES (5,
        '12345678E',
        'María',
        'Rodríguez',
        '1990-01-01',
        '2018-01-01',
        'Española',
        'Director',
        true);
-- Cliente
INSERT INTO CLIENTE
VALUES (1,
        '12345678F',
        'Juan',
        'Pérez',
        30,
        'Estudiante',
        true);
INSERT INTO CLIENTE
VALUES (2,
        '12345678G',
        'Pedro',
        'García',
        30,
        'Estudiante',
        true);
INSERT INTO CLIENTE
VALUES (3,
        '12345678H',
        'Luis',
        'González',
        30,
        'Estudiante',
        true);
INSERT INTO CLIENTE
VALUES (4,
        '12345678I',
        'Ana',
        'Martínez',
        30,
        'Estudiante',
        true);
INSERT INTO CLIENTE
VALUES (5,
        '12345678J',
        'María',
        'Rodríguez',
        30,
        'Estudiante',
        true);
-- Visita Guiada
INSERT INTO VISITAGUIADA
VALUES (1,
        'Guggenheim',
        30,
        'Calle de la visita guiada',
        'Turismo',
        'Bilbao',
        10.0,
        '2022-01-01',
        '12:00:00',
        '13:00:00',
        1,
        true);
INSERT INTO VISITAGUIADA
VALUES (2,
        'Artium',
        20,
        'Calle de la visita guiada',
        'Arte',
        'Vitoria',
        15.5,
        '2022-02-02',
        '10:00:00',
        '15:00:00',
        2,
        true);
INSERT INTO VISITAGUIADA
VALUES (3,
        'Parque Salburua',
        10,
        'Calle de la visita guiada',
        'Turismo',
        'Vitoria',
        10.0,
        '2022-03-03',
        '10:00:00',
        '11:30:00',
        3,
        true);
INSERT INTO VISITAGUIADA
VALUES (4,
        'Templo de Debod',
        30,
        'Calle de la visita guiada',
        'Fotografía',
        'Madrid',
        20.0,
        '2022-04-04',
        '16:00:00',
        '17:00:00',
        1,
        true);
INSERT INTO VISITAGUIADA
VALUES (5,
        'Plaza de las Ventas',
        10,
        'Calle de la visita guiada',
        'Tauromaquia',
        'Madrid',
        30.0,
        '2022-05-05',
        '17:00:00',
        '18:00:00',
        5,
        true);
-- Compra Visita Guiada
INSERT INTO COMPRAVISITAGUIADA
VALUES (1,
        '2021-01-01',
        1,
        1);
INSERT INTO COMPRAVISITAGUIADA
VALUES (2,
        '2021-02-02',
        2,
        1);
INSERT INTO COMPRAVISITAGUIADA
VALUES (3,
        '2021-02-03',
        2,
        2);
INSERT INTO COMPRAVISITAGUIADA
VALUES (4,
        '2021-01-04',
        1,
        2);
INSERT INTO COMPRAVISITAGUIADA
VALUES (5,
        '2021-01-05',
        2,
        3);
INSERT INTO COMPRAVISITAGUIADA
VALUES (6,
        '2021-01-06',
        3,
        3);
INSERT INTO COMPRAVISITAGUIADA
VALUES (7,
        '2021-01-07',
        3,
        4);
INSERT INTO COMPRAVISITAGUIADA
VALUES (8,
        '2021-01-08',
        4,
        4);
INSERT INTO COMPRAVISITAGUIADA
VALUES (9,
        '2021-01-09',
        4,
        5);
INSERT INTO COMPRAVISITAGUIADA
VALUES (10,
        '2021-01-10',
        5,
        5);
