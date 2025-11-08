-- testcontainer_db.user_book_tb definition

CREATE TABLE `user_book_tb` (
  `book_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  PRIMARY KEY (`book_id`,`user_id`),
  KEY `FKhpyr44fpteexodahf0rio84t1` (`user_id`),
  CONSTRAINT `FKg2xav4yscyvc6rkras25l847r` FOREIGN KEY (`book_id`) REFERENCES `book_tb` (`id`),
  CONSTRAINT `FKhpyr44fpteexodahf0rio84t1` FOREIGN KEY (`user_id`) REFERENCES `user_tb` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;