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
values ('First', 256);
insert into "products" (title, price)
values ('Second', 43.22);
insert into "products" (title, price)
values ('Third', 23);