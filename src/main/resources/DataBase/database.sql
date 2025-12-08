create table residentes (

rsid serial primary key,
rsname text,
rsphone int,
rsemail text,
rsvivienda text
);

select * from residentes;

create table usuarios (

usid serial primary key,
username text,
password int,
role text,
usstatus boolean
);

ALTER TABLE residentes
ADD CONSTRAINT fk_residente
FOREIGN KEY (usfgid) REFERENCES usuarios(usid);



Select * from usuarios join residentes on usid = rsid;

ALTER TABLE residentes
ALTER COLUMN rsphone TYPE text;

update usuarios set role= 'ROLE_USER' ;