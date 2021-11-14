-- drop table if exists users;

create table if not exists users
-- create table if not exists USERS
(
    id       bigserial
        constraint users_pkey
            primary key,
    email    varchar(50)
        constraint uk6dotkott2kjsp8vw4d0m25fb7
            unique,
    password varchar(120),
    username varchar(20)
        constraint ukr43af9ap4edm43mmtq01oddj6
            unique
);

create table if not exists roles
(
    id   serial
        constraint roles_pkey
            primary key,
    name varchar(20)
);

create table if not exists user_roles
(
    user_id bigint  not null
        constraint fkhfh9dx7w3ubf1co1vdev94g3f
            references users,
    role_id integer not null
        constraint fkh8ciramu9cc9q3qcqiv4ue8a6
            references roles,
    constraint user_roles_pkey
        primary key (user_id, role_id)
);

create table if not exists refreshtoken
(
    id          bigint       not null
        constraint refreshtoken_pkey
            primary key,
    expiry_date timestamp    not null,
    token       varchar(255) not null
        constraint uk_or156wbneyk8noo4jstv55ii3
            unique,
    user_id     bigint
        constraint fka652xrdji49m4isx38pp4p80p
            references users
);