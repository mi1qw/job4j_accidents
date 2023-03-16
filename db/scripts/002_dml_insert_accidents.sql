INSERT INTO public.rule (id, name) VALUES (1, 'Статья. 1');
INSERT INTO public.rule (id, name) VALUES (2, 'Статья. 2');
INSERT INTO public.rule (id, name) VALUES (3, 'Статья. 3');

INSERT INTO public.type (id, name) VALUES (1, 'Две машины');
INSERT INTO public.type (id, name) VALUES (2, 'Машина и человек');
INSERT INTO public.type (id, name) VALUES (3, 'Машина и велосипед');

INSERT INTO public.accident (id, name, text, address, type_id) VALUES (1, 'Нурушение парковки', 'Парковка транспортных средств на местах, предназначенных для бесплатной парковки транспортных средств, лицами, которые не имеют соответствующих льгот', 'ул.Васина д1', 2);
INSERT INTO public.accident (id, name, text, address, type_id) VALUES (2, 'Нурушение парковки', 'Парковка транспортных средств на местах, предназначенных для бесплатной парковки транспортных средств, лицами, которые не имеют соответствующих льгот', 'ул.Анны д1', 2);
INSERT INTO public.accident (id, name, text, address, type_id) VALUES (3, 'Нарушение переезда перекрёстка', 'Не уступил дорогу транспортным средствам, приближающимся по главной дороге', 'ул.Петра д1', 1);

INSERT INTO public.accident_rule (accident_id, rule_id) VALUES (1, 1);
INSERT INTO public.accident_rule (accident_id, rule_id) VALUES (2, 1);
INSERT INTO public.accident_rule (accident_id, rule_id) VALUES (3, 1);
INSERT INTO public.accident_rule (accident_id, rule_id) VALUES (3, 2);
