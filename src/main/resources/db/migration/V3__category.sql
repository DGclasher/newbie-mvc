drop table if exists category;
drop table if exists beer_category;

create table category (
    id varchar(36) not null primary key,
    description varchar(50),
    created_date timestamp(6),
    last_modified_date timestamp(6) default null,
    version bigint default null
);

create table beer_category (
    beer_id varchar(36) not null,
    category_id varchar(36) not null,
    primary key (beer_id, category_id),
    foreign key (beer_id) references beer(id),
    foreign key (category_id) references category(id)
);