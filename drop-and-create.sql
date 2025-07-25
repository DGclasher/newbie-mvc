
    set client_min_messages = WARNING;

    drop table if exists beer cascade;

    drop table if exists customer cascade;

    create table beer (
        beer_style smallint not null check (beer_style between 0 and 9),
        price numeric(38,2) not null,
        quantity_on_hand integer,
        version integer,
        created_date timestamp(6),
        updated_date timestamp(6),
        id varchar(36) not null,
        beer_name varchar(50) not null,
        upc varchar(255) not null,
        primary key (id)
    );

    create table customer (
        created_date date,
        updated_date date,
        version integer,
        id varchar(36) not null,
        customer_name varchar(255),
        email varchar(255),
        primary key (id)
    );

    set client_min_messages = WARNING;

    drop table if exists beer cascade;

    drop table if exists customer cascade;

    create table beer (
        beer_style smallint not null check (beer_style between 0 and 9),
        price numeric(38,2) not null,
        quantity_on_hand integer,
        version integer,
        created_date timestamp(6),
        updated_date timestamp(6),
        id varchar(36) not null,
        beer_name varchar(50) not null,
        upc varchar(255) not null,
        primary key (id)
    );

    create table customer (
        created_date date,
        updated_date date,
        version integer,
        id varchar(36) not null,
        customer_name varchar(255),
        email varchar(255),
        primary key (id)
    );

    set client_min_messages = WARNING;

    drop table if exists beer cascade;

    drop table if exists customer cascade;

    create table beer (
        beer_style smallint not null check (beer_style between 0 and 9),
        price numeric(38,2) not null,
        quantity_on_hand integer,
        version integer,
        created_date timestamp(6),
        updated_date timestamp(6),
        id varchar(36) not null,
        beer_name varchar(50) not null,
        upc varchar(255) not null,
        primary key (id)
    );

    create table customer (
        created_date date,
        updated_date date,
        version integer,
        id varchar(36) not null,
        customer_name varchar(255),
        email varchar(255),
        primary key (id)
    );

    set client_min_messages = WARNING;

    drop table if exists beer cascade;

    drop table if exists customer cascade;

    create table beer (
        beer_style smallint not null check (beer_style between 0 and 9),
        price numeric(38,2) not null,
        quantity_on_hand integer,
        version integer,
        created_date timestamp(6),
        updated_date timestamp(6),
        id varchar(36) not null,
        beer_name varchar(50) not null,
        upc varchar(255) not null,
        primary key (id)
    );

    create table customer (
        created_date date,
        updated_date date,
        version integer,
        id varchar(36) not null,
        customer_name varchar(255),
        email varchar(255),
        primary key (id)
    );
