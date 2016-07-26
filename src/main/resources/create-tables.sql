CREATE TABLE user (
  id INTEGER IDENTITY PRIMARY KEY,
  name VARCHAR(64),
  password VARCHAR(255)
);

CREATE TABLE user_statistic(
  id INTEGER IDENTITY PRIMARY KEY,
  user_id INTEGER,
  action_time TIMESTAMP,
  action VARCHAR(30),
  description  VARCHAR(255),
  FOREIGN KEY (user_id) REFERENCES user(id)
);