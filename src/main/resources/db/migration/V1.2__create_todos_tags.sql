CREATE TABLE `todos_tags` (
  `todo_id` bigint NOT NULL,
  `tags_id` bigint NOT NULL,
  FOREIGN KEY (todo_id) REFERENCES todos(id),
  FOREIGN KEY (tags_id) REFERENCES tags(id)
);

INSERT INTO todos_tags (todo_id,tags_id) VALUES(1,1);
INSERT INTO todos_tags (todo_id,tags_id) VALUES(1,2);
INSERT INTO todos_tags (todo_id,tags_id) VALUES(1,3);