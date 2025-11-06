-- Populate user_tb with initial data
INSERT INTO user_tb (first_name, last_name, gender, email, birth_date, phone_number, password, address, state, created_at, updated_at)
VALUES
    ('Nikola', 'Tesla', 'Male', 'nikola.tesla@gmail.com', '1983-09-09', '31998636521', '123456', 'Street 1046 view', 'NY', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Elon', 'Musk', 'Male', 'elon.musk@gmail.com', '1981-05-25', '31996325874', '123456', 'Street 45 Bart', 'CA', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6));
