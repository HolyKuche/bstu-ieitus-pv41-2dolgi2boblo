ALTER TABLE user_2_user ADD CONSTRAINT user_2_user_key UNIQUE (user1_id, user2_id);
ALTER TABLE user_2_role ADD CONSTRAINT user_2_role_key UNIQUE (user_id, role_id);