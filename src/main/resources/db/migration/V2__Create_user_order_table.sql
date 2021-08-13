create sequence users_user_id_seq;

create table if not exists public.users
(
    user_id integer                                             not null default nextval('"users_user_id_seq"'::regclass),
    name    character varying(256) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT users_pkey PRIMARY KEY (user_id)
)
    TABLESPACE pg_default;

ALTER TABLE public.users
    OWNER to postgres;

CREATE TABLE IF NOT EXISTS public.orders
(
    order_id   integer NOT NULL,
    user_id    integer NOT NULL,
    product_id integer NOT NULL,
    quantity   integer NOT NULL,
    price      numeric NOT NULL,
    CONSTRAINT pk_order_id PRIMARY KEY (order_id, user_id, product_id),
    CONSTRAINT orders_user_id_fkey FOREIGN KEY (user_id)
        REFERENCES public.users (user_id) MATCH SIMPLE,
    constraint orders_product_id_fkey foreign key (product_id)
        references public.products (product_id) match simple
)
    TABLESPACE pg_default;

ALTER TABLE public.orders
    OWNER to postgres;

insert into users(name)
values ('Bob');
insert into users(name)
values ('Jack');
insert into orders(order_id, user_id, product_id, quantity, price)
VALUES (1, 1, 1, 3, 123);
insert into orders(order_id, user_id, product_id, quantity, price)
VALUES (1, 1, 2, 1, 12.21);