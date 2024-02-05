delete from alumnos;
insert into alumnos (name) values ('Juanito');
insert into alumnos (name) values ('Jorgito');
insert into alumnos (name) values ('Jaimito');

delete from visitas_pois;
delete from user_roles;
delete from pois;
delete from ciudades;
delete from roles;
delete from visitas;
delete from users;



insert into roles (id,rol) values (1,'ADMIN');
insert into roles (id,rol) values (2,'USER');

insert into users (id,name,password) values (1,'admin','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (2,'pedro','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (3,'juan','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (4,'jose','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (5,'luis','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (6,'maria','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (7,'ana','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (8,'laura','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (9,'pilar','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');
insert into users (id,name,password) values (10,'carmen','$2a$10$aguIiT4W2Ki1eX9CijS8B.GDbc8eMP0dh0NbGdkyrJmz67wBl2tY2');

insert into user_roles (user_id,roles_id) values (1,1);
insert into user_roles (user_id,roles_id) values (2,2);
insert into user_roles (user_id,roles_id) values (3,2);
insert into user_roles (user_id,roles_id) values (4,2);
insert into user_roles (user_id,roles_id) values (5,2);
insert into user_roles (user_id,roles_id) values (6,2);
insert into user_roles (user_id,roles_id) values (7,2);
insert into user_roles (user_id,roles_id) values (8,2);
insert into user_roles (user_id,roles_id) values (9,2);
insert into user_roles (user_id,roles_id) values (10,2);


insert into ciudades (id,nombre) values (1,'Madrid');
insert into ciudades (id,nombre) values (2,'Barcelona');
insert into ciudades (id,nombre) values (3,'Valencia');
insert into ciudades (id,nombre) values (4,'Roma');
insert into ciudades (id,nombre) values (5,'Paris');
insert into ciudades (id,nombre) values (6,'Londres');

insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (1,'Puerta del Sol',1,40.4169,-3.7033,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (2,'Plaza Mayor',1,40.415,-3.707,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (3,'Plaza de Cibeles',1,40.419,-3.693,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (4,'Sagrada Familia',2,41.4036,2.1744,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (5,'Parque Guell',2,41.414,-2.152,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (6,'Catedral de Valencia',3,39.475,-0.375,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (7,'Ciudad de las Artes y las Ciencias',3,39.453,-0.354,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (8,'Coliseo',4,41.890,-12.492,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (9,'Fontana di Trevi',4,41.900,-12.483,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (10,'Torre Eiffel',5,48.858,-2.294,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (11,'Museo del Louvre',5,48.861,-2.335,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (12,'Big Ben',6,51.500,-0.124,'monumento');
insert into pois (id,nombre,ciudad_id,latitud,longitud,tipo) values (13,'London Eye',6,51.503,-0.119,'monumento');


insert into visitas (id,fecha_inicial,fecha_final,user_id) values (1,'2020-01-01','2020-01-01',2);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (2,'2020-01-01','2020-01-10',2);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (3,'2020-01-05','2020-01-11',2);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (4,'2020-01-01','2020-01-01',3);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (5,'2021-01-01','2021-01-10',3);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (6,'2020-01-05','2020-01-11',3);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (7,'2020-01-01','2020-01-01',4);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (8,'2020-01-01','2020-01-10',4);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (9,'2020-01-05','2020-01-11',4);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (10,'2020-01-01','2020-01-01',5);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (11,'2020-01-01','2020-01-10',5);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (12,'2020-01-05','2020-01-11',5);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (13,'2020-01-01','2020-01-01',6);
insert into visitas (id,fecha_inicial,fecha_final,user_id) values (14,'2020-01-01','2020-01-10',6);


insert into visitas_pois (visita_id,poi_id) values (1,1);
insert into visitas_pois (visita_id,poi_id) values (1,2);
insert into visitas_pois (visita_id,poi_id) values (1,3);
insert into visitas_pois (visita_id,poi_id) values (2,12);
insert into visitas_pois (visita_id,poi_id) values (2,13);
insert into visitas_pois (visita_id,poi_id) values (3,10);
insert into visitas_pois (visita_id,poi_id) values (3,11);
insert into visitas_pois (visita_id,poi_id) values (4,1);
insert into visitas_pois (visita_id,poi_id) values (4,2);
insert into visitas_pois (visita_id,poi_id) values (4,3);
insert into visitas_pois (visita_id,poi_id) values (5,1);
insert into visitas_pois (visita_id,poi_id) values (5,2);
insert into visitas_pois (visita_id,poi_id) values (5,3);
insert into visitas_pois (visita_id,poi_id) values (6,6);
insert into visitas_pois (visita_id,poi_id) values (6,7);
insert into visitas_pois (visita_id,poi_id) values (7,6);
insert into visitas_pois (visita_id,poi_id) values (7,7);
insert into visitas_pois (visita_id,poi_id) values (8,8);
insert into visitas_pois (visita_id,poi_id) values (8,9);
insert into visitas_pois (visita_id,poi_id) values (9,1);
insert into visitas_pois (visita_id,poi_id) values (9,2);
insert into visitas_pois (visita_id,poi_id) values (9,3);
insert into visitas_pois (visita_id,poi_id) values (10,1);
insert into visitas_pois (visita_id,poi_id) values (10,2);
insert into visitas_pois (visita_id,poi_id) values (10,3);
insert into visitas_pois (visita_id,poi_id) values (11,1);
insert into visitas_pois (visita_id,poi_id) values (11,2);
insert into visitas_pois (visita_id,poi_id) values (11,3);
insert into visitas_pois (visita_id,poi_id) values (12,8);
insert into visitas_pois (visita_id,poi_id) values (12,9);
insert into visitas_pois (visita_id,poi_id) values (13,1);
insert into visitas_pois (visita_id,poi_id) values (13,2);
insert into visitas_pois (visita_id,poi_id) values (13,3);
insert into visitas_pois (visita_id,poi_id) values (14,4);
insert into visitas_pois (visita_id,poi_id) values (14,5);









