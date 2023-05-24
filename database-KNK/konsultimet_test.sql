create database konsultimet_test;
use konsultimet_test;

create table konsultimet (
	Profesori varchar(255),
    Studenti varchar(255),
    Lenda varchar(255),
    Koha_fillimi datetime,
    primary key(Profesori, Koha_fillimi)
);

create table Profesoret (
	ID varchar(255),
    name varchar(255) not null,
    username varchar(255) not null unique,
    password varchar(255) not null,
    email varchar(255) not null unique,
    phone varchar(255) unique,
    website varchar(255),
    primary key(ID)
);

CREATE TABLE users(
id bigint not null,
username varchar(100) not null,
password varchar(100) not null,
primary key(id)
);


INSERT INTO users(id,username,password)
VALUES('210757100073','aladini','aladini123');
INSERT INTO users(id,username,password)
VALUES('210756100113','alketa','1111');
INSERT INTO users(id,username,password)
VALUES('210756100075','ardit','1111');
INSERT INTO users(id,username,password)
VALUES('210756100078','blerona','1111');
INSERT INTO users(id,username,password)
VALUES('210658100025','endi','1111');


insert into Profesoret values (20071407981, "Blend Arifaj", "blendarifaj", "1111",  "blend.arifaj@uni-pr.edu", "049726672", "blendarifaj.uni-pr.edu");
insert into Profesoret values (15012500901, "Valon Raca", "valonraca", "1111",  "valon.raca@uni-pr.edu", "049111467", "valonraca.uni-pr.edu");
insert into Profesoret values (14022866974, "Daline Vranovci",  "dalinevranovci", "1111", "daline.vranovci@uni-pr.edu", "044615901", "dalinevranovci.uni-pr.edu");
insert into Profesoret values (15503641122, "Isak Shabani", "isakshabani", "1111", "isak.shabani@uni-pr.edu", "045122345", "isakshabani.uni-pr.edu");
insert into Profesoret values (12467090003, "Qamil Kabashi",  "qamilkabashi", "1111", "qamil.kabashi@uni-pr.edu", "049222094", "qamilkabashi.uni-pr.edu");

insert into konsultimet values ("Blend Arifaj", "Endi Rashica", "Komunikimi njeri kompjuter", "2023-06-11 09:30:00");
insert into konsultimet values ("Blend Arifaj", "Blerona Jashanica", "Komunikimi njeri kompjuter", "2023-06-11 09:00:00");
insert into konsultimet values ("Blend Arifaj", "Ardit Haklaj", "Komunikimi njeri kompjuter", "2023-06-15 08:30:00");
insert into konsultimet values ("Blend Arifaj", "Alketa Zekaj", "Programim i orientuar ne objekte", "2023-06-15 08:00:00");
insert into konsultimet values ("Blend Arifaj", "Aladin Bajra", "Programim i orientuar ne objekte", "2023-06-15 11:00:00");



insert into konsultimet values ("Daline Vranovci", "Aladin Bajra ", "Programimi ne Ueb2", "2023-06-03 11:30:00");
insert into konsultimet values ("Daline Vranovci", "Alketa Zekaj", "Baza e te dhenave", "2023-06-07 13:00:00");
insert into konsultimet values ("Daline Vranovci", "Ardit Haklaj", "Baza e te dhenave", "2023-06-07 13:30:00");
insert into konsultimet values ("Daline Vranovci", "Blerona Jashanica", "Programimi ne Ueb2", "2023-06-13 08:00:00");
insert into konsultimet values ("Daline Vranovci", "Endi Rashica", "Baza e te dhenave", "2023-06-23 08:30:00");
insert into konsultimet values ("Daline Vranovci", "Blerona Jashanica", "Programimi ne Ueb2", "2023-06-23 09:00:00");
insert into konsultimet values ("Daline Vranovci", "Alketa Zekaj", "Baza e te dhenave", "2023-06-23 09:30:00");

insert into konsultimet values ("Valon Raca", "Aladin Bajra", "Arkitektura e Kompjutereve", "2023-06-13 12:30:00");
insert into konsultimet values ("Valon Raca", "Alketa Zekaj", "Arkitektura e Kompjutereve", "2023-06-17 11:00:00");
insert into konsultimet values ("Valon Raca", "Ardit Haklaj", "Arkitektura e Kompjutereve", "2023-06-19 10:30:00");
insert into konsultimet values ("Valon Raca", "Endi Rashica", "Arkitektura e Kompjutereve", "2023-06-21 11:00:00");
insert into konsultimet values ("Valon Raca", "Blerona Jashanica", "Arkitektura e Kompjutereve", "2023-06-21 15:30:00");

insert into konsultimet values ("Qamil Kabashi", "Blerona Jashanica", "Elektronike", "2022-06-15 15:00:00");
insert into konsultimet values ("Qamil Kabashi", "Alketa Zekaj", "Elektronike", "2022-06-16 11:30:00");
insert into konsultimet values ("Qamil Kabashi", "Endi Rashica", "Elektronike", "2022-06-16 12:00:00");
insert into konsultimet values ("Qamil Kabashi", "Ardit Haklaj", "Elektronike", "2022-06-18 12:30:00");
insert into konsultimet values ("Qamil Kabashi", "Aladin Bajra", "Elektronike", "2022-06-18 13:00:00");
insert into konsultimet values ("Qamil Kabashi", "Ardit Haklaj", "Elektronike", "2022-06-25 13:30:00");
