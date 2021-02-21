create table ticket
(
    id bigint not null
        constraint ticket_pkey
            primary key,
    passenger_first_name varchar(255),
    passenger_last_name varchar(255),
    route_id bigint not null
        constraint fkyr2pu9q0aw7h6xmw5n2yae6w
            references route,
    user_id bigint not null
        constraint fkmvugyjf7b45u0juyue7k3pct0
            references users
);

alter table ticket owner to admin;

