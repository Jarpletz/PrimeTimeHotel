CREATE TABLE Reservations (
                              id NUMBER(11) NOT NULL,
                              user_id NUMBER(11),
                              room_id NUMBER(11),
                              start_date DATE,
                              end_date DATE,
                              status NUMBER(4),
                              CONSTRAINT pk_reservations PRIMARY KEY (id)
);

CREATE SEQUENCE reservations_seq START WITH 1;

CREATE TABLE Rooms (
                       id NUMBER(11) NOT NULL,
                       room_number NUMBER(11),
                       quality_level NUMBER(4),
                       room_type NUMBER(11),
                       floor NUMBER(11) DEFAULT 1,
                       rate NUMBER(20,2),
                       smoking_status CHAR(1) DEFAULT '0' CHECK (smoking_status IN ('0','1')),
                       num_single_beds NUMBER(11) DEFAULT 0,
                       num_double_beds NUMBER(11) DEFAULT 0,
                       num_queen_beds NUMBER(11) DEFAULT 0,
                       CONSTRAINT pk_rooms PRIMARY KEY (id)
);

CREATE SEQUENCE rooms_seq START WITH 1;

CREATE TABLE Accounts (
                          id NUMBER(11) NOT NULL,
                          payment_id NUMBER(11),
                          username VARCHAR2(255) DEFAULT '',
                          password VARCHAR2(255) DEFAULT '',
                          first_name VARCHAR2(255) DEFAULT '',
                          last_name VARCHAR2(255) DEFAULT '',
                          phone_number VARCHAR2(255) DEFAULT '',
                          email VARCHAR2(255) DEFAULT '',
                          CONSTRAINT pk_accounts PRIMARY KEY (id)
);

CREATE SEQUENCE accounts_seq START WITH 1;

CREATE TABLE PaymentInfo (
                             id NUMBER(11) NOT NULL,
                             provider VARCHAR2(255) DEFAULT '',
                             card_number NUMBER(19),
                             expiration_date DATE,
                             security_code NUMBER(11),
                             zipcode NUMBER(11),
                             CONSTRAINT pk_paymentinfo PRIMARY KEY (id)
);

CREATE SEQUENCE paymentinfo_seq START WITH 1;

CREATE TABLE StoreItem (
                           id NUMBER(11) NOT NULL,
                           price NUMBER(20,2) DEFAULT 0,
                           name VARCHAR2(255) DEFAULT '',
                           description VARCHAR2(1000) DEFAULT '',
                           image BLOB,
                           CONSTRAINT pk_storeitem PRIMARY KEY (id)
);

CREATE SEQUENCE storeitem_seq START WITH 1;
