INSERT INTO public.users (username, password, enabled) VALUES ('admin', '$2a$10$4/.l3nbySMoS80P.cpvT6.SVMzRRNRn0FfnxXLxOovz16jbVb/yly', true);
INSERT INTO public.role (role) VALUES ('ROLE_ADMIN');
INSERT INTO public.user_roles (role_id, user_id) VALUES ((SELECT id FROM public.users WHERE username = 'admin'),(SELECT id FROM public.role WHERE role = 'ROLE_ADMIN'));


