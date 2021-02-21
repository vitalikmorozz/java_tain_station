create table train
(
    id bigint not null
        constraint train_pkey
            primary key,
    description varchar(255),
    number bigint,
    seats_count integer
);

alter table train owner to admin;

