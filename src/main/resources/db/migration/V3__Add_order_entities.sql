create sequence order_items_item_id_seq;
create sequence orders_order_id_seq;

create table if not exists public.orders
(
    order_id         bigint                                              not null default nextval('"orders_order_id_seq"'::regclass),
    user_user_id     bigint                                              not null references users (user_id),
    creation_time    timestamp                                                    default current_timestamp,
    total_price      numeric                                             not null,
    phone            character varying(256) collate pg_catalog."default" not null,
    delivery_address character varying(256) collate pg_catalog."default" not null,
    constraint pk_order_id primary key (order_id)
)
    tablespace pg_default;

alter table public.orders
    owner to postgres;

create table if not exists public.order_items
(
    order_item_id      bigint  not null default nextval('"order_items_item_id_seq"'::regclass),
    order_order_id     bigint  not null references orders (order_id),
    product_product_id bigint  not null references products (product_id),
    quantity           int     not null,
    product_price      numeric not null,
    total_price        numeric not null,
    constraint pk_order_item_id primary key (order_item_id)
)
    tablespace pg_default;

alter table public.order_items
    owner to postgres;

