DELETE FROM performance WHERE id = 1;
DELETE FROM performance WHERE id = 2;
DELETE FROM performance WHERE id = 3;

INSERT INTO performance (id, max_weight, exercice_id, user_id) VALUES (1, 50.5, 1, 1);
INSERT INTO performance (id, max_weight, exercice_id, user_id) VALUES (2, 60, 2, 2);
INSERT INTO performance (id, max_weight, exercice_id, user_id) VALUES (3, 10, 3, 3);