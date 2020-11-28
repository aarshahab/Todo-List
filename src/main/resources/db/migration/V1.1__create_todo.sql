CREATE TABLE `todos` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `is_finished` bit(1) NOT NULL DEFAULT 0,
  `is_active` bit(1) NOT NULL DEFAULT 1,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

 INSERT INTO todos (name) VALUES('backend');
 INSERT INTO todos (name) VALUES('frontend');