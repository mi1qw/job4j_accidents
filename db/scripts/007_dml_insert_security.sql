insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$8BrluZLTg67swfYWjgtBUO2pQEv/LvzeuHH/Z2KYi/JFTH.nppiJy',
        (select id from authorities where authority = 'ROLE_ADMIN'));
