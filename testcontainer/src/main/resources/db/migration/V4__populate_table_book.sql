-- Populate book_tb with initial data
INSERT INTO book_tb (launch_date, price, title, description, created_at, updated_at)
VALUES
    ('2008-08-01 00:00:00', 89.99, 'Clean Code', 'A Handbook of Agile Software Craftsmanship', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2002-11-15 00:00:00', 79.99, 'Refactoring', 'Improving the Design of Existing Code', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2003-08-20 00:00:00', 95.00, 'Domain-Driven Design', 'Tackling Complexity in the Heart of Software', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('1994-10-31 00:00:00', 85.50, 'Design Patterns', 'Elements of Reusable Object-Oriented Software', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2017-12-27 00:00:00', 69.90, 'Effective Java', 'Best Practices for the Java Platform', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2015-06-08 00:00:00', 45.00, 'You Don''t Know JS', 'Scope and Closures', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('1999-10-30 00:00:00', 72.50, 'The Pragmatic Programmer', 'Your Journey to Mastery', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2004-09-21 00:00:00', 68.00, 'Working Effectively with Legacy Code', 'Strategies for Improving Large Codebases', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2002-11-18 00:00:00', 55.99, 'Test Driven Development', 'By Example', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2020-02-25 00:00:00', 82.00, 'Spring in Action', 'Covers Spring 5 and Spring Boot 2', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6)),
    ('2015-02-20 00:00:00', 88.90, 'Building Microservices', 'Designing Fine-Grained Systems', CURRENT_TIMESTAMP(6), CURRENT_TIMESTAMP(6));