-- Populate users table
--INSERT INTO users (id, name, classroom_email_address, moodle_email_address, url_photo) VALUES (1, 'Marco Taborda', 'marcotaborda.jr@gmail.com', 'marcotaborda.jr@gmail.com', 'https://lh3.googleusercontent.com/a-/AOh14GhwLCRi1rCuqRktm-DLetBGZ9tmE2OdZTB7Ejfbng=s96-c');

-- Populate moodle_user table
INSERT INTO moodle_student (id, username, email) VALUES (1, 'marco.taborda', 'marcotaborda.jr@gmail.com');
INSERT INTO moodle_student (id, username, email) VALUES (2, 'daniele.rocha', 'floresdaniele3@gmail.com');
INSERT INTO moodle_student (id, username, email) VALUES (3, 'jacson.miranda', 'jacsonmiranda@gmail.com');
INSERT INTO moodle_student (id, username, email) VALUES (4, 'daison.assis', 'daisonassis@gmail.com');
INSERT INTO moodle_student (id, username, email) VALUES (5, 'douglas.brito', 'douglasbrito@gmail.com');

-- Populate moodle_course table
INSERT INTO moodle_course (id, fullname) VALUES (1, 'Teoria da Computação');
INSERT INTO moodle_course (id, fullname) VALUES (2, 'Redes de Computadores');
INSERT INTO moodle_course (id, fullname) VALUES (3, 'Inteligência Artificial I');
INSERT INTO moodle_course (id, fullname) VALUES (4, 'Algoritmos e Programação');
INSERT INTO moodle_course (id, fullname) VALUES (5, 'Fundamentos da Computação');

-- Populate moodle_local_id_course table
INSERT INTO moodle_student_course (id, student_id, course_id) VALUES (1, 1 , 1);
INSERT INTO moodle_student_course (id, student_id, course_id) VALUES (2, 3 , 2);
INSERT INTO moodle_student_course (id, student_id, course_id) VALUES (3, 2 ,4);
INSERT INTO moodle_student_course (id, student_id, course_id) VALUES (4, 5 , 5);
INSERT INTO moodle_student_course (id, student_id, course_id) VALUES (5, 1 , 5);

-- Populate moodle_lesson table
INSERT INTO moodle_lesson (id, name, deadline, activity_link, course_id) VALUES (1, 'Atividade 1', 1596140728, 'https://www.google.com', 1);
INSERT INTO moodle_lesson (id, name, deadline, activity_link, course_id) VALUES (2, 'Prova 2', 1596140728, 'https://www.google.com', 2);
INSERT INTO moodle_lesson (id, name, deadline, activity_link, course_id) VALUES (3, 'Trabalho 12', 1596140728, 'https://www.google.com', 2);
INSERT INTO moodle_lesson (id, name, deadline, activity_link, course_id) VALUES (4, 'Exercício 3', 1596140728, 'https://www.google.com', 4);
INSERT INTO moodle_lesson (id, name, deadline, activity_link, course_id) VALUES (5, 'Atividade 2', 1596140728, 'https://www.google.com', 5);