-- Create book_tb table
CREATE TABLE book_tb (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  author VARCHAR(80) NOT NULL,
  launch_date DATETIME(6) NOT NULL,
  price DOUBLE NOT NULL,
  title VARCHAR(100) NOT NULL,
  description TEXT,
  created_at DATETIME(6) NOT NULL,
  updated_at DATETIME(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
