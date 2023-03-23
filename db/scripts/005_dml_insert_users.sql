INSERT INTO public.users (username, password, enabled) VALUES ('admin', '$2a$10$Tb3bpVw0ATAr8d.N9w/7C.WVR41fk0qu8BUCY5eFQI7Gff/7wiTS2', true);

INSERT INTO public.authorities (username, authority) VALUES ('admin', 'ROLE_ADMIN');
INSERT INTO public.authorities (username, authority) VALUES ('admin', 'ROLE_USER');
