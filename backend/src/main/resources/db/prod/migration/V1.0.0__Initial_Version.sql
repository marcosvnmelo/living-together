create table expenses (
  id int8 not null,
  date timestamp,
  description varchar(255),
  name varchar(255),
  value float8,
  house_id int8 not null,
  primary key (id)
);

create table houses (
  id int8 not null,
  address varchar(255),
  name varchar(255),
  manager_id int8 not null,
  primary key (id)
);

create table tasks (
  id int8 not null,
  date timestamp,
  description varchar(255),
  done boolean default false not null,
  name varchar(255),
  house_id int8 not null,
  responsible_user_id int8,
  primary key (id)
);

create table users (
  id int8 not null,
  last_name varchar(255),
  name varchar(255),
  current_house_id int8,
  primary key (id)
);

create sequence hibernate_sequence start 1 increment 1;

alter table
  if exists expenses
add
  constraint FKrbgn2dl64d0sow2364odaqpt5 foreign key (house_id) references houses;

alter table
  if exists houses
add
  constraint FKpttg4yk0fvsj9kk4v270b1jh6 foreign key (manager_id) references users;

alter table
  if exists tasks
add
  constraint FKl869ndivfqg64x11mhyahebui foreign key (house_id) references houses;

alter table
  if exists tasks
add
  constraint FK1ijeq0gjsuges4hrbjsqfeqmg foreign key (responsible_user_id) references users;

alter table
  if exists users
add
  constraint FKo6r5njei3ytkt32nk062fyydy foreign key (current_house_id) references houses;
