create table station
(
    id bigint not null
        constraint station_pkey
            primary key,
    name varchar(255)
);

alter table station owner to admin;

