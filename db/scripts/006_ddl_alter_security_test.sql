alter table authorities drop column username;
alter table authorities add column id serial primary key;
alter table authorities alter column authority set not null;
alter table authorities add constraint auth_unique unique (authority);

alter table users drop primary key;
alter table users add column id serial primary key;
alter table users add constraint user_unique unique (username);
alter table users add column authority_id int references authorities (id);

update users
SET authority_id =
        (select a.id from authorities a where a.authority = 'ROLE_USER')
where authority_id IS NULL;

alter table users alter column authority_id SET NOT NULL;
