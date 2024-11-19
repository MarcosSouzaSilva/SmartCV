ALTER TABLE personal_info
ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users(id);
