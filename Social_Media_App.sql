create database socialMediaAppDatabase;
use socialMediaAppDatabase;

create table user(
id int primary key auto_increment,
nume varchar(20),
prenume varchar(20),
email varchar(50),
numar_telefon varchar(10)
);

select * from user;

insert into user (nume,prenume,email,numar_telefon) values ('nume1','prenume1','email1@gmail.com','0728371874');
insert into user (nume,prenume,email,numar_telefon) values ('nume2','prenume2','email2@gmail.com','0783715231');
insert into user (nume,prenume,email,numar_telefon) values ('nume3','prenume3','email3@gmail.com','0799999999');
insert into user (nume,prenume,email,numar_telefon) values ('Marius','Andrei','ma@gmail.com','0111111111');


create table post(
id int primary key auto_increment,
user_id int,
mesaj varchar(400),
an int,
luna int,
zi int,
ora int,
minut int,
foreign key (user_id) references user (id) on delete cascade
);

select * from post;

insert into post (user_id,mesaj,an,luna,zi,ora,minut) values (3,'Afara ploua',2023,1,4,17,44);
insert into post (user_id,mesaj,an,luna,zi,ora,minut) values (4,'Nu mai e valabil',2011,2,7,12,44);
insert into post (user_id,mesaj,an,luna,zi,ora,minut) values (5,'Feeling good at Vama Veche',2022,8,7,12,44);


create table comment(
id int primary key auto_increment,
post_id int,
user_id int,
mesaj varchar(400),
an int,
luna int,
zi int,
ora int,
minut int,
foreign key (post_id) references post (id),
foreign key (user_id) references user (id)
);

select * from comment;

insert into comment (post_id,user_id,mesaj,an,luna,zi,ora,minut) values (2,4,'BLABLABLABLA',2023,2,3,4,55);

update comment set mesaj = 'ALT BLABLABLABLA' where id = 2;