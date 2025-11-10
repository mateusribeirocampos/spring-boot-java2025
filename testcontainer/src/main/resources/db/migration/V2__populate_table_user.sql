-- Populate user_tb with initial data (Real authors of technical books)
INSERT INTO user_tb (first_name, last_name, gender, email, birth_date, phone_number, password, address, state, created_at, updated_at)
VALUES
    ('Robert', 'Martin', 'Male', 'robert.martin@gmail.com', '1952-12-05', '31998636521', '123456', 'Clean Code Street 101', 'IL', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Martin', 'Fowler', 'Male', 'martin.fowler@gmail.com', '1963-12-18', '31996325874', '123456', 'Refactoring Avenue 200', 'UK', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Eric', 'Evans', 'Male', 'eric.evans@gmail.com', '1960-01-01', '31987654321', '123456', 'Domain Drive 300', 'CA', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Erich', 'Gamma', 'Male', 'erich.gamma@gmail.com', '1961-03-13', '31976543210', '123456', 'Design Pattern Lane 401', 'CH', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Richard', 'Helm', 'Male', 'richard.helm@gmail.com', '1960-01-01', '31965432109', '123456', 'Design Pattern Lane 402', 'AU', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Ralph', 'Johnson', 'Male', 'ralph.johnson@gmail.com', '1955-10-07', '31954321098', '123456', 'Design Pattern Lane 403', 'IL', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('John', 'Vlissides', 'Male', 'john.vlissides@gmail.com', '1961-08-02', '31943210987', '123456', 'Design Pattern Lane 404', 'NY', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Joshua', 'Bloch', 'Male', 'joshua.bloch@gmail.com', '1961-08-28', '31932109876', '123456', 'Effective Java Road 500', 'CA', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Kyle', 'Simpson', 'Male', 'kyle.simpson@gmail.com', '1980-01-01', '31921098765', '123456', 'JavaScript Boulevard 600', 'TX', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Andrew', 'Hunt', 'Male', 'andrew.hunt@gmail.com', '1964-01-01', '31910987654', '123456', 'Pragmatic Street 701', 'NC', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('David', 'Thomas', 'Male', 'david.thomas@gmail.com', '1956-01-01', '31909876543', '123456', 'Pragmatic Street 702', 'TX', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Michael', 'Feathers', 'Male', 'michael.feathers@gmail.com', '1970-01-01', '31998765432', '123456', 'Legacy Code Avenue 800', 'MI', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Kent', 'Beck', 'Male', 'kent.beck@gmail.com', '1961-03-31', '31987654320', '123456', 'TDD Drive 900', 'OR', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Craig', 'Walls', 'Male', 'craig.walls@gmail.com', '1970-01-01', '31976543219', '123456', 'Spring Street 1000', 'TX', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('Sam', 'Newman', 'Male', 'sam.newman@gmail.com', '1975-01-01', '31965432118', '123456', 'Microservices Road 1100', 'UK', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6));