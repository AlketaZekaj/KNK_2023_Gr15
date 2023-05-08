drop database if exists MOK;
create database MOK;
use MOK;

drop table if exists User;
drop table if exists Studenti;
drop table if exists Lendet;
drop table if exists StafiAkademik;
drop table if exists Salla;
drop table if exists Drejtimi;
drop table if exists VitiAkademik;
drop table if exists Oraret;

use MOK;
//TRYING FIRST COMMIT

create table VitiAkademik(
Id integer not null auto_increment,
Viti varchar(10),
primary key(Id)
);

create table Drejtimi(
Id integer not null auto_increment,
Emri varchar(40),
primary key(Id)
);

create table Studenti(
Id integer not null auto_increment,
Emri varchar(15),
Mbiemri varchar(15),
StudentId varchar(30),
Drejtimi integer,
Viti integer,
Primary Key(Id),
foreign key(Drejtimi) references drejtimi(ID),
foreign key(Viti) references vitiakademik(ID)
);

create table User(
Id integer not null,
Username varchar(100),
SaltedHash varchar(256),
Salted varchar(100),
primary key(Id),
foreign key(Id)references Studenti(Id)
);

create table Lendet(
Id integer not null auto_increment,
Emri varchar(50),
primary key(Id)
);

create table StafiAkademik(
Id integer not null auto_increment,
Emri varchar(30),
Mbiemri varchar(30),
Pozita varchar(50),
primary key(Id)
);

create table profesorUser(
Id integer not null,
Username varchar(100),
SaltedHash varchar(256),
Salted varchar(100),
primary key(Id),
foreign key(Id)references StafiAkademik(Id)
);

create table Salla(
Id integer not null auto_increment,
Emri varchar(10),
primary key(Id)
);

create table TeacherSubjects(
Id int not null auto_increment,
profesori int,
lenda int,
primary key(Id),
foreign key(profesori) references stafiakademik(Id),
foreign key(lenda) references lendet(Id)
);

create table Oraret(
Id integer not null auto_increment,
l_u varchar(20),
drejtimi varchar(40),
lenda varchar(50),
profesori varchar(61),
viti varchar(10),
salla varchar(10),
dita varchar(10),
KohaFillimit varchar(5),
primary key(Id)
);


 create table carry(Id int not null,
 CID varchar(20),
 primary key(Id)
 );



 create table ditet(
Id int not null auto_increment,
emri varchar(10),
primary key(Id)
);

 create table kohet(
Id int not null  auto_increment,
koha varchar(5),
primary key(Id)
);

 create table filtered(
Id integer not null auto_increment,
l_u varchar(20),
drejtimi varchar(40),
lenda varchar(50),
profesori varchar(61),
viti varchar(10),
salla varchar(10),
dita varchar(10),
KohaFillimit varchar(5),
primary key(Id)
);

