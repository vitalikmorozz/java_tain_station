create table stoppage
(
    id bigint not null
        constraint stoppage_pkey
            primary key,
    arrival_time timestamp,
    departure_time timestamp,
    stoppage_order integer,
    route_id bigint not null
        constraint fk82lmct8gkkjyhmhankneduij2
            references route,
    station_id bigint not null
        constraint fkm3uh9y2f7450mbtav9wtnr8pl
            references station
);

alter table stoppage owner to admin;

