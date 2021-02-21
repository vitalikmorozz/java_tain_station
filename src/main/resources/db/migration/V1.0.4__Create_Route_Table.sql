create table route
(
    id bigint not null
        constraint route_pkey
            primary key,
    train_id bigint not null
        constraint fk8v3ar2wt47nlc4kq71g5gn78m
            references train
);

alter table route owner to admin;

