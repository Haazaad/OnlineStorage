create sequence users_user_id_seq;
create sequence roles_role_id_seq;

create table if not exists public.users
(
    user_id     bigint                                              not null default nextval('"users_user_id_seq"'::regclass),
    username    character varying(256) collate pg_catalog."default" not null,
    password    character varying(256) collate pg_catalog."default" not null,
    email       character varying(256) collate pg_catalog."default" unique,
    create_date timestamp                                                    default current_timestamp,
    update_date timestamp                                                    default current_timestamp,
    constraint pk_user_id primary key (user_id)
)
    tablespace pg_default;

alter table public.users
    owner to postgres;

create table if not exists public.roles
(
    role_id     bigint                                             not null default nextval('"roles_role_id_seq"'::regclass),
    name        character varying(50) collate pg_catalog."default" not null,
    create_date timestamp                                                   default current_timestamp,
    update_date timestamp                                                   default current_timestamp,
    constraint pk_role_id primary key (role_id)
)
    tablespace pg_default;

alter table public.roles
    owner to postgres;

create table if not exists public.user_roles
(
    user_user_id bigint not null references users (user_id),
    role_role_id bigint not null references roles (role_id),
    constraint pk_user_role_id primary key (user_user_id, role_role_id)
)
    tablespace pg_default;

alter table public.user_roles
    owner to postgres;

insert into users(username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'admin@gmail.com');

insert into roles(name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into user_roles(user_user_id, role_role_id)
values (1, 1),
       (2, 2);