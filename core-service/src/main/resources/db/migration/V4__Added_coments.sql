create sequence comments_comment_id_seq;

create table if not exists public.comments
(
    comment_id         bigint                                               not null default nextval('"comments_comment_id_seq"'::regclass),
    user_user_id       bigint                                               not null references users (user_id),
    product_product_id bigint                                               not null references products (product_id),
    creation_time      timestamp                                                     default current_timestamp,
    description        character varying(1024) collate pg_catalog."default" not null,
    constraint pk_comment_id primary key (comment_id)
)
    tablespace pg_default;

alter table public.comments
    owner to postgres;

insert into comments(user_user_id, product_product_id, description)
values (1, 1, 'Not bad');