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

use SMO;


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

 -- views

create view studentet as select a.Emri as Emri, a.Mbiemri as Mbiemri, a.StudentId as StudentId, b.Emri as Drejtimi, c.Viti as Viti,   from studenti a
												join drejtimi b on (a.Drejtimi = b.Id)
												join vitiakademik c on (a.Viti = c.Id)


-- procedure

drop procedure if exists checkIfExists;
delimiter //
 create procedure checkIfExists(new_drejtimi varchar(40), new_viti varchar(10), new_salla varchar(10), new_dita varchar(10), new_koha varchar(5))
begin
if exists(select * from oraret where drejtimi = new_drejtimi and viti = new_viti  and salla = new_salla and dita = new_dita and KohaFillimit = new_koha) then
update carry set CID = 1 where id = 2;
 else
update carry set CID = 0 where id = 2;
end if;
end //
delimiter //;


drop procedure if exists deleteFiltered;
delimiter //
 create procedure deleteFiltered()
begin
 SET SQL_SAFE_UPDATES = 0;
delete from filtered;
 SET SQL_SAFE_UPDATES = 1;
end //
delimiter //;



drop procedure if exists insertFiltered;
delimiter //
 create procedure insertFiltered(new_l_u varchar(20),new_drejtimi varchar(40),new_lenda varchar(50),new_profesori varchar(61),new_viti varchar(10),new_salla varchar(10),new_dita varchar(10),new_koha varchar(5))
begin
 INSERT INTO filtered(l_u,drejtimi,lenda,profesori,viti,salla,dita,KohaFillimit)values(new_l_u,new_drejtimi,new_lenda,new_profesori,new_viti,new_salla,new_dita,new_koha);
end //
delimiter //;


drop procedure if exists deleteStudent;
delimiter //
 create procedure deleteStudent(new_id int)
begin
 SET SQL_SAFE_UPDATES = 0;
 delete from user where Id = new_id;
delete from studenti where Id = new_id;
 SET SQL_SAFE_UPDATES = 1;
end //
delimiter //;




drop procedure if exists updateProfesori;
delimiter //
 create procedure updateProfesori(new_id int,new_emri varchar(30), new_mbiemri varchar(30), new_pozita  varchar(50))
begin
 SET SQL_SAFE_UPDATES = 0;
update stafiakademik set Emri = new_emri where Id = new_id;
update stafiakademik set Mbiemri = new_mbiemri where Id = new_id;
update stafiakademik set Pozita = new_pozita where Id = new_id;
 SET SQL_SAFE_UPDATES = 1;
end //
delimiter //;

drop procedure if exists profaBusy;
delimiter //
 create procedure profaBusy(new_dita varchar(10),new_koha varchar(5),new_profa varchar(61))
begin
if exists(select * from oraret where profesori = new_profa and dita = new_dita and KohaFillimit = new_koha) then
update carry set CID = 1 where id = 4;
 else 
update carry set CID = 0 where id = 4;
end if;
end //
delimiter //;