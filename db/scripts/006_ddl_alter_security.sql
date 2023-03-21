truncate table authorities;
alter table authorities drop column username;
alter table authorities add column id serial primary key;
alter table authorities alter column authority set not null;
alter table authorities add constraint auth_unique unique (authority);

drop table users;
CREATE TABLE users
(
    id           serial primary key,
    username     VARCHAR(50)  NOT NULL unique,
    password     VARCHAR(100) NOT NULL,
    enabled      boolean default true,
    authority_id int          not null references authorities (id)
);
