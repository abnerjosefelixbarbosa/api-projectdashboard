create table if not exists role_tb (
    id text not null,
    name varchar(50) not null unique,
    primary key (id) 
);

create table if not exists responsible_tb (
    id text not null,
    name varchar(100) not null,
    login varchar(100) not null unique,
    password varchar(100) not null unique,
    role_id text not null,
    primary key (id),
    foreign key (role_id) references role_tb(id) 
);

create table if not exists project_tb (
    id text not null,
    name varchar(100) not null,
    budget numeric(32,2) not null,
    start_date date not null,
    end_date date not null,
    responsible_id text not null,
    primary key (id), 
    foreign key (responsible_id) references responsible_tb(id)
);