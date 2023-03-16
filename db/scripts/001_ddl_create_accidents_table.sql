create table type
(
    id   serial primary key,
    name text unique not null
);

create table rule
(
    id   serial primary key,
    name text unique not null
);

create table accident
(
    id      serial primary key,
    name    text not null,
    text    text not null,
    address text not null,
    type_id serial references type (id)
);

create table accident_rule
(
    accident_id serial references accident (id),
    rule_id     serial references rule (id),
    primary key (accident_id, rule_id)
);