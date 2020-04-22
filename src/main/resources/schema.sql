CREATE TABLE user_detials (
  user_id bigint AUTO_INCREMENT  PRIMARY KEY,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  status int(4) DEFAULT 1
);
CREATE TABLE api (
  api_id bigint AUTO_INCREMENT  PRIMARY KEY,
  api_url VARCHAR(250) NOT NULL,
  status int NOT NULL default 1
);
CREATE TABLE user_api_rule (
  user_api_id bigint AUTO_INCREMENT  PRIMARY KEY,
  user_id bigint NOT NULL,
  api_id bigint NOT NULL,
  token int default 0,
  status int DEFAULT 1
);