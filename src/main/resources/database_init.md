CREATE TABLE t_user (
user_id VARCHAR(30) NOT NULL,
user_name VARCHAR(30) NOT NULL,
user_password VARCHAR(30) NOT NULL,
user_type INT NOT NULL,
PRIMARY KEY (user_id)
);

CREATE TABLE t_doc (
doc_id INT NOT NULL AUTO_INCREMENT,
user_id VARCHAR(30) NOT NULL,
doc_path VARCHAR(100) NOT NULL,
doc_type INT NOT NULL,
doc_status INT DEFAULT 0,
PRIMARY KEY (doc_id),
FOREIGN KEY (user_id) REFERENCES t_user(user_id)
);

CREATE TABLE t_topic (
topic_id INT NOT NULL AUTO_INCREMENT,
topic_name VARCHAR(50) NOT NULL,
topic_description VARCHAR(200) NOT NULL,
student_id VARCHAR(30) NOT NULL,
teacher_id VARCHAR(30) NOT NULL,
topic_status INT DEFAULT 0,
PRIMARY KEY (topic_id),
FOREIGN KEY (student_id) REFERENCES t_user(user_id),
FOREIGN KEY (teacher_id) REFERENCES t_user(user_id)
);

CREATE TABLE t_defense (
defense_id INT NOT NULL AUTO_INCREMENT,
defense_startTime DATETIME,
defense_endTime DATETIME,
defense_score INT DEFAULT -1,
student_id VARCHAR(30) NOT NULL,
defense_status INT DEFAULT 0,
PRIMARY KEY (defense_id),
FOREIGN KEY (student_id) REFERENCES t_user(user_id)
);

CREATE TABLE t_user_association (
association_id INT NOT NULL AUTO_INCREMENT,
student_id VARCHAR(30) NOT NULL,
teacher_id VARCHAR(30) NOT NULL,
PRIMARY KEY (association_id),
FOREIGN KEY (student_id) REFERENCES t_user(user_id),
FOREIGN KEY (teacher_id) REFERENCES t_user(user_id)
);

CREATE TABLE t_student_status (
status_id INT NOT NULL AUTO_INCREMENT,
student_id VARCHAR(30) NOT NULL,
student_status INT NOT NULL,
PRIMARY KEY (status_id),
FOREIGN KEY (student_id) REFERENCES t_user(user_id)
);

