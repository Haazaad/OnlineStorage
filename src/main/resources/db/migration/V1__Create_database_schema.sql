create sequence products_product_id_seq;

create table if not exists public.products
(
    product_id integer                                             not null default nextval('"products_product_id_seq"'::regclass),
    title      character varying(256) collate pg_catalog."default" not null,
    price      numeric                                             not null,
    constraint pk_product_id primary key (product_id)
)
    tablespace pg_default;

alter table public.products
    owner to postgres;

insert into "products" (title, price)
values ('First', 256),
       ('Second', 43.22),
       ('Third', 23),
       ('Bread', 12.21),
       ('Milk', 23.02),
       ('Beer', 56.89),
       ('Onion', 32.12),
       ('Potato', 34.12),
       ('Watermelon', 25),
       ('Apple', 12),
       ('Spread', 123.12),
       ('Monitor', 4321.85),
       ('Notebook', 24856.32),
       ('TV', 123412.2),
       ('Mouse', 321),
       ('Phone', 12386),
       ('Keyboard', 453),
       ('Water', 18.32),
       ('Meet', 319),
       ('Oil', 654),
       ('Pasta', 89);