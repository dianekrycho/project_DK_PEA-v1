create database project;
use project;

CREATE TABLE students (
    id int not null auto_increment, -- toujours mettre null en id lors de la création d'un étudiant 
    name_ varchar(50) not null,
    gender varchar(20) not null,
    email varchar(50),
    birthDate date,
    photo varchar(300),
    mark double,
    commentary varchar(200),
    primary key(id)
);

-- add new student 
insert into students values(1, "diane", "female", null, null, null, null, null);
insert into students values(2, "paul-eloi", "male", null, null, null, null, null);
insert into students values(null, "jessica", "female", null, null, null, null, null);

-- query pour la listview (column index 0) dans l'ordere alpha 
select name_ from students order by name_;

-- edit info (remplacer les '' par textField.getText() et trouver le id de la personne )
update students set name_='', gender='', email='', birthdate='', photo='', mark='', commentary='' where id='';
	
-- average mark query 
select avg(mark) from students;

-- delete student from table (fill '' by id from student you want to delete)
delete from students where id = '';




