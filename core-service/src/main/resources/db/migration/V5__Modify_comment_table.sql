ALTER TABLE public.comments
    DROP CONSTRAINT comments_user_user_id_fkey;

ALTER TABLE public.comments
    DROP COLUMN user_user_id;

ALTER TABLE public.comments
    ADD COLUMN username character varying(256);
