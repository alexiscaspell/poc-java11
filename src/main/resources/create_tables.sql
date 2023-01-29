use prueba;
create table persona(
id int auto_increment,
nombre varchar(20) null,
apellido varchar(50) null,
dni varchar(50) not null,
fecha_nacimiento datetime,
primary key(id)
);