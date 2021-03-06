drop table addresses if exists;
create table addresses (
    id bigint generated by default as identity,
    street varchar(255),
    city varchar(255),
    zip_code varchar(255),
    is_contact boolean,
    person_id bigint not null,
    primary key (id)
);

drop table people if exists;
create table people (
    id bigint generated by default as identity,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    email varchar(255) not null unique,
    primary key (id)
);

alter table addresses 
    add constraint person_fk 
    foreign key (person_id) 
    references people;

delete from addresses;
delete from people;

insert into people (id, first_name, last_name, email) values (1, 'John', 'Doe', 'john.doe@somemail.com');
insert into people (id, first_name, last_name, email) values (2, 'Mary', 'Adams', 'mary.adams@othermail.com');
insert into people (id, first_name, last_name, email) values (3, 'Peter', 'Smith', 'p.smith@wherever.com');

insert into addresses(id, street, city, zip_code, is_contact, person_id) values (1, 'Street One', 'London',    'OWAFEWFEW', true, 1);
insert into addresses(id, street, city, zip_code, is_contact, person_id) values (2, 'Street Two', 'Birmingham', 'AFILWFEWE', false, 1);
insert into addresses(id, street, city, zip_code, is_contact, person_id) values (3, 'Street Three', 'Liverpool', 'WAFEIWEWE', false, 1);
insert into addresses(id, street, city, zip_code, is_contact, person_id) values (4, 'Street Four', 'Edinburgh',    'AFAEWEEFW', true, 2);
insert into addresses(id, street, city, zip_code, is_contact, person_id) values (5, 'Street Five', 'Glasgow',    'WEWFWEEEW', false, 2);
insert into addresses(id, street, city, zip_code, is_contact, person_id) values (6, 'Street Six', 'Leeds', 'WAFEIWEWE', true, 3);