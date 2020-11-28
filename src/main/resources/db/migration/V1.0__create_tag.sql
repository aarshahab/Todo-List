CREATE TABLE `tags` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `is_active` bit(1) NOT NULL DEFAULT 1,
  `created_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
);

INSERT INTO tags (name) VALUES('java');

INSERT INTO tags (name) VALUES('golang');

INSERT INTO tags (name) VALUES('c#');

INSERT INTO tags (name) VALUES('scala');

