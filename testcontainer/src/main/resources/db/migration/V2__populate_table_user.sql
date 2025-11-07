-- Populate user_tb with initial data
INSERT INTO user_tb (first_name, last_name, gender, email, birth_date, phone_number, password, address, state, created_at, updated_at)
VALUES
    ('Nikola', 'Tesla', 'Male', 'nikola.tesla@gmail.com', '1983-09-09', '31998636521', '123456', 'Street 1046 view', 'NY', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Elon', 'Musk', 'Male', 'elon.musk@gmail.com', '1981-05-25', '31996325874', '123456', 'Street 45 Bart', 'CA', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Ada', 'Lovelace', 'Female', 'ada.lovelace@gmail.com', '1990-12-10', '31987654321', '123456', 'Street 42 Computing', 'LO', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Alan', 'Turing', 'Male', 'alan.turing@gmail.com', '1985-06-23', '31976543210', '123456', 'Enigma Avenue 1942', 'CA', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Grace', 'Hopper', 'Female', 'grace.hopper@gmail.com', '1988-03-15', '31965432109', '123456', 'COBOL Street 123', 'BO', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Dennis', 'Ritchie', 'Male', 'dennis.ritchie@gmail.com', '1982-09-09', '31954321098', '123456', 'Unix Road 456', 'BE', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Margaret', 'Hamilton', 'Female', 'margaret.hamilton@gmail.com', '1991-08-17', '31943210987', '123456', 'Apollo Lane 789', 'HO', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Linus', 'Torvalds', 'Male', 'linus.torvalds@gmail.com', '1989-12-28', '31932109876', '123456', 'Linux Boulevard 321', 'HE', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Tim', 'Berners-Lee', 'Male', 'tim.bernerslee@gmail.com', '1987-06-08', '31921098765', '123456', 'WWW Street 1989', 'GE', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Katherine', 'Johnson', 'Female', 'katherine.johnson@gmail.com', '1993-11-26', '31910987654', '123456', 'NASA Drive 246', 'VI', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Donald', 'Knuth', 'Male', 'donald.knuth@gmail.com', '1980-01-10', '31909876543', '123456', 'Algorithm Avenue 135', 'ST', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Barbara', 'Liskov', 'Female', 'barbara.liskov@gmail.com', '1986-11-07', '31998765432', '123456', 'Abstraction Street 579', 'MI', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6));