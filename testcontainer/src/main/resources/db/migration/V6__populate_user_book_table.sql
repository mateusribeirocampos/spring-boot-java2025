-- Populate user_book_tb with initial data (Real book authors)
INSERT INTO user_book_tb (user_id, book_id)
VALUES
    -- Clean Code (book_id: 1)
    (1, 1),   -- Robert C. Martin

    -- Refactoring (book_id: 2)
    (2, 2),   -- Martin Fowler

    -- Domain-Driven Design (book_id: 3)
    (3, 3),   -- Eric Evans

    -- Design Patterns (book_id: 4) - Gang of Four
    (4, 4),   -- Erich Gamma
    (5, 4),   -- Richard Helm
    (6, 4),   -- Ralph Johnson
    (7, 4),   -- John Vlissides

    -- Effective Java (book_id: 5)
    (8, 5),   -- Joshua Bloch

    -- You Don't Know JS (book_id: 6)
    (9, 6),   -- Kyle Simpson

    -- The Pragmatic Programmer (book_id: 7)
    (10, 7),  -- Andrew Hunt
    (11, 7),  -- David Thomas

    -- Working Effectively with Legacy Code (book_id: 8)
    (12, 8),  -- Michael Feathers

    -- Test Driven Development (book_id: 9)
    (13, 9),  -- Kent Beck

    -- Spring in Action (book_id: 10)
    (14, 10), -- Craig Walls

    -- Building Microservices (book_id: 11)
    (15, 11); -- Sam Newman