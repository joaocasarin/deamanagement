CREATE TABLE user (
	id INTEGER NOT NULL AUTO_INCREMENT,
	name VARCHAR(256) NOT NULL,
	email VARCHAR(256) NOT NULL,
	password VARCHAR(100) NOT NULL,
	linkedin VARCHAR(128) NOT NULL,
	CONSTRAINT user_PK PRIMARY KEY (id)
);

CREATE TABLE student (
	id INTEGER auto_increment NOT NULL,
	university varchar(128) NULL,
	graduation varchar(128) NULL,
	finish_date DATE NULL,
	user_id INTEGER NOT NULL,
	CONSTRAINT student_PK PRIMARY KEY (id),
	CONSTRAINT student_FK FOREIGN KEY (user_id) REFERENCES deamanagement.`user`(id) ON DELETE CASCADE ON UPDATE CASCADE
)