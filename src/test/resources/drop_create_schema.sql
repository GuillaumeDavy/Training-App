drop table if exists "user" cascade ;
drop table if exists exercice cascade ;
drop table if exists performance cascade ;

create table exercice
(
    id          bigserial
        constraint exercice_pkey
            primary key,
    description varchar(255) not null,
    name        varchar(255) not null
);

create table "user"
(
    id    bigserial
        constraint user_pkey
            primary key,
    email varchar(255) not null
        constraint uk_ob8kqyqqgmefl0aco34akdtpe
            unique,
    name  varchar(255) not null
);

create table performance
(
    id          bigserial
        constraint performance_pkey
            primary key,
    max_weight  real   not null,
    exercice_id bigint not null
        constraint fkdvlf0cxjdjf230522kvxsbwii
            references exercice,
    user_id     bigint not null
        constraint fklk17hyq8kx7hoo48ph7f5lp8t
            references "user"
);