CREATE SEQUENCE USER_ID_SEQ;
CREATE SEQUENCE DEBT_ID_SEQ;
CREATE SEQUENCE ROLE_ID_SEQ;

CREATE TABLE USER_ (
  ID BIGINT PRIMARY KEY,
  FIRST_NAME VARCHAR(200) NOT NULL,
  LAST_NAME VARCHAR(200) NOT NULL,
  LOGIN VARCHAR(200) NOT NULL UNIQUE,
  HASH_PASS VARCHAR(200) NOT NULL,
  API_TOKEN VARCHAR(1000)
);

CREATE UNIQUE INDEX idx_user_id ON USER_ (ID);
CREATE UNIQUE INDEX idx_user_api_token ON USER_ (API_TOKEN);

CREATE TABLE DEBT (
  ID BIGINT PRIMARY KEY ,
  MONEY BIGINT NOT NULL,
  INIT_DATE_TIME TIMESTAMP WITH TIME ZONE NOT NULL,
  DESCRIPTION VARCHAR(200),
  WHO_ID BIGINT NOT NULL,
  WHOM_ID BIGINT NOT NULL
);

CREATE UNIQUE INDEX idx_debt_id ON DEBT (ID);

CREATE TABLE USER_2_USER (
  USER1_ID BIGINT NOT NULL,
  USER2_ID BIGINT NOT NULL
);

CREATE TABLE ROLE (
  ID BIGINT PRIMARY KEY,
  NAME VARCHAR(200) NOT NULL UNIQUE
);

INSERT INTO ROLE VALUES (NEXTVAL('ROLE_ID_SEQ'), 'USER');

CREATE TABLE USER_2_ROLE (
  USER_ID BIGINT NOT NULL,
  ROLE_ID BIGINT NOT NULL
);