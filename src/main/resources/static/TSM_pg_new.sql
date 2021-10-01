CREATE SCHEMA IF NOT EXISTS tourism_management_system;


---------- ENTITY 1 ----------
CREATE SEQUENCE tourism_management_system.user_Account_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TYPE tourism_management_system.uAcc_role_enum AS ENUM (
    'USER',
    'MANAGER',
    'ADMIN'
);

CREATE TABLE tourism_management_system.user_Account(
  U_ID      BIGINT ,
  PASSWORD  VARCHAR NOT NULL ,
  U_NAME    VARCHAR(30) NOT NULL ,
  U_ADDRESS_1 VARCHAR NOT NULL ,
  U_ADDRESS_2 VARCHAR,
  DISTRICT varchar NOT NULL ,
  STATE VARCHAR(20) NOT NULL ,
  ZIP NUMERIC(6) NOT NULL,
  U_EMAIL   VARCHAR(30) NOT NULL ,
  U_MOB     NUMERIC(10) NOT NULL ,
  ROLE  tourism_management_system.uAcc_role_enum
    NOT NULL DEFAULT 'USER',

  CONSTRAINT uAc_pk PRIMARY KEY (U_ID),
  CONSTRAINT uAc_uEmail_uk UNIQUE (U_EMAIL),
  CONSTRAINT uAc_uMob_uk UNIQUE (U_MOB)
);
COMMENT ON COLUMN tourism_management_system.user_Account.ROLE IS
    '[0:USER | 1:MANAGER | 2:ADMIN]';

CREATE UNIQUE INDEX uAc_index
    ON tourism_management_system.user_Account (U_EMAIL ASC, U_MOB ASC);

ALTER SEQUENCE tourism_management_system.user_Account_id_seq
    OWNED BY tourism_management_system.user_Account.U_ID;


---------- ENTITY 2 ----------
CREATE SEQUENCE tourism_management_system.destination_Info_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TABLE tourism_management_system.destination_Info(
    D_ID BIGINT,
    D_NAME VARCHAR NOT NULL ,
    D_PROVINCE VARCHAR(20) NOT NULL ,
    DESCRIPTION VARCHAR,
    MAX_STAY_DURATION NUMERIC(2) NOT NULL DEFAULT 1,

    CONSTRAINT dInfo_pk PRIMARY KEY (D_ID)
);

ALTER SEQUENCE tourism_management_system.destination_Info_id_seq
    OWNED BY tourism_management_system.destination_Info.D_ID;


---------- ENTITY 3 ----------
CREATE SEQUENCE tourism_management_system.hotel_Info_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TYPE  tourism_management_system.hotel_Info_h_type_enum AS ENUM (
    'STANDARD',
    'PRIME',
    'ROYALE'
);

CREATE TABLE tourism_management_system.hotel_Info(
  H_ID BIGINT ,
  H_NAME VARCHAR(30) NOT NULL ,
  H_ADDRESS varchar,
  ZIP numeric(6) not null,
  H_EMAIL varchar(30),
  H_MOB numeric(10) not null,
  BASE_PRICE INTEGER NOT NULL ,
  H_TYPE tourism_management_system.hotel_Info_h_type_enum
      not null default 'STANDARD',
  D_ID BIGINT,

  CONSTRAINT hInfo_pk PRIMARY KEY (H_ID),
  CONSTRAINT hInfo_hEmail_uk UNIQUE (H_EMAIL),
  CONSTRAINT hInfo_hMob_uk UNIQUE (H_MOB),

  CONSTRAINT hInfo_dInfo_dId FOREIGN KEY (D_ID)
    REFERENCES tourism_management_system.destination_Info(D_ID)
             ON DELETE SET NULL ON UPDATE CASCADE

);
COMMENT ON COLUMN tourism_management_system.hotel_Info.H_TYPE IS
    '[0:STANDARD | 1:PRIME | 2:ROYAL]';

CREATE UNIQUE INDEX hInfo_index
    ON tourism_management_system.hotel_Info (H_EMAIL);

ALTER SEQUENCE tourism_management_system.hotel_Info_id_seq
    OWNED BY tourism_management_system.hotel_Info.H_ID;


---------- ENTITY 4 ----------
CREATE SEQUENCE tourism_management_system.transport_info_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE ;

CREATE TYPE  tourism_management_system.tInfo_t_mode_enum AS ENUM (
    'BUS',
    'CAR',
    'CRUISE',
    'STEAMER',
    'FLIGHT',
    'RAILWAY'
);

CREATE TYPE  tourism_management_system.tInfo_t_entity_enum AS ENUM (
    'PRIVATE',
    'PUBLIC'
);

CREATE TABLE tourism_management_system.transport_info(
    T_ID bigint,
    T_MODE tourism_management_system.tInfo_t_mode_enum
        not null default 'CAR',
    T_ENTITY tourism_management_system.tInfo_t_entity_enum
        NOT NULL DEFAULT 'PRIVATE',
    T_NAME VARCHAR,
    T_DESCRIPTION VARCHAR,

    CONSTRAINT tInfo_pk PRIMARY KEY (T_ID)
);
COMMENT ON COLUMN tourism_management_system.transport_info.T_MODE IS
    '[0:BUS | 1:CAR | 2:CRUISE | 3:STEAMER | 4:FLIGHT | 5:RAILWAY]';
COMMENT ON COLUMN tourism_management_system.transport_info.T_ENTITY IS
    '[0:PRIVATE | 1:PUBLIC]';

ALTER SEQUENCE  tourism_management_system.transport_info_id_seq
    OWNED BY tourism_management_system.transport_info.T_ID;


---------- ENTITY 5 ----------
CREATE SEQUENCE tourism_management_system.tour_Package_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TABLE tourism_management_system.tour_Package(
    PKG_ID BIGINT ,
    PKG_NAME VARCHAR NOT NULL,
    DESCRIPTION TEXT,
    ACTIVITIES VARCHAR,
    EVENTS varchar,

    CONSTRAINT tPackage_pk PRIMARY KEY (PKG_ID)
);

ALTER SEQUENCE tourism_management_system.tour_Package_id_seq
    OWNED BY tourism_management_system.tour_Package.PKG_ID;


---------- ENTITY 6 ----------
CREATE TABLE tourism_management_system.Package_destination_map(
    PKG_ID BIGINT NOT NULL ,
    D_ID BIGINT NOT NULL ,

    CONSTRAINT tPackage_dInfo_map_tInfo_tId FOREIGN KEY (PKG_ID)
        REFERENCES tourism_management_system.tour_Package(PKG_ID)
                 ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT tPackage_dInfo_map_dInfo_dId FOREIGN KEY (D_ID)
        REFERENCES tourism_management_system.destination_Info(D_ID)
                 ON DELETE CASCADE ON UPDATE CASCADE
);


---------- ENTITY 7 ----------
CREATE TABLE tourism_management_system.package_transport_map(
    PKG_ID BIGINT NOT NULL ,
    T_ID BIGINT NOT NULL ,

    CONSTRAINT tPackage_dInfo_map_tInfo_tId FOREIGN KEY (PKG_ID)
        REFERENCES tourism_management_system.tour_Package(PKG_ID)
                 ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT tPackage_dInfo_map_dInfo_dId FOREIGN KEY (T_ID)
        REFERENCES tourism_management_system.transport_info(T_ID)
                 ON DELETE CASCADE ON UPDATE CASCADE
);


---------- ENTITY 8 ----------
CREATE SEQUENCE tourism_management_system.booking_Info_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TYPE  tourism_management_system.booking_Info_r_type_enum AS ENUM (
    'STANDARD',
    'SUPERIOR',
    'DELUXE'
);

CREATE TYPE  tourism_management_system.booking_Info_b_status_enum AS ENUM (
    'ACTIVE',
    'CANCELLED',
    'COMPLETE'
);

CREATE TABLE tourism_management_system.booking_Info(
  B_ID BIGINT ,
  B_DATE DATE NOT NULL ,
  PKG_ID BIGINT NOT NULL ,
  T_DATE DATE NOT NULL ,
  U_ID BIGINT NOT NULL ,
  PASSENGER_COUNT INTEGER NOT NULL DEFAULT 1
        CHECK ( PASSENGER_COUNT>0 AND PASSENGER_COUNT<=12 ),
  R_TYPE tourism_management_system.booking_Info_r_type_enum
      NOT NULL default 'STANDARD',
  T_PRICE INTEGER NOT NULL DEFAULT 0,
  B_STATUS tourism_management_system.booking_Info_b_status_enum
      NOT NULL default 'ACTIVE',

  CONSTRAINT bInfo_pk PRIMARY KEY (B_ID),

  CONSTRAINT bInfo_uAcc_uId FOREIGN KEY (U_ID)
      REFERENCES tourism_management_system.user_Account(U_ID)
                        ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT bInfo_tPackage_tId FOREIGN KEY (PKG_ID)
      REFERENCES tourism_management_system.tour_Package(PKG_ID)
                        ON DELETE CASCADE ON UPDATE CASCADE
);
COMMENT ON COLUMN tourism_management_system.booking_Info.R_TYPE IS
    '[0:Standard | 1:Superior | 2:Deluxe]';
COMMENT ON COLUMN tourism_management_system.booking_Info.B_STATUS IS
    '[0:Active | 1:Cancelled | 2:Complete]';

ALTER SEQUENCE tourism_management_system.booking_Info_id_seq
    OWNED BY tourism_management_system.booking_Info.B_ID;


---------- ENTITY 9 ----------
CREATE TABLE tourism_management_system.booking_transport_ticket(
	TICKET_ID VARCHAR ,
    B_ID BIGINT NOT NULL ,
    T_ID BIGINT NOT NULL,
    
	CONSTRAINT booking_transport_ticket_pk PRIMARY KEY (TICKET_ID),

    CONSTRAINT booking_transport_ticket_bInfo_bId FOREIGN KEY (B_ID)
        REFERENCES tourism_management_system.booking_Info(B_ID)
                 ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT booking_transport_ticket_tInfo_tId FOREIGN KEY (T_ID)
        REFERENCES tourism_management_system.transport_info(T_ID)
                 ON DELETE CASCADE ON UPDATE CASCADE
);

---------- ENTITY 10 ----------
CREATE SEQUENCE tourism_management_system.destination_review_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TABLE tourism_management_system.destination_review(
    D_REV_ID bigint,
    D_ID bigint not null ,
    B_ID bigint not null ,
    D_RATING numeric(2) CHECK ( D_RATING BETWEEN 1 AND 10),
    D_REVIEW TEXT,

    CONSTRAINT destination_review_pk PRIMARY KEY (D_REV_ID),

    CONSTRAINT dReview_dInfo_dId FOREIGN KEY (D_ID)
        REFERENCES tourism_management_system.destination_Info(D_ID)
                        ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT dReview_bInfo_bId FOREIGN KEY (B_ID)
        REFERENCES tourism_management_system.booking_Info(B_ID)
                        ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER SEQUENCE tourism_management_system.destination_review_id_seq
    OWNED BY tourism_management_system.destination_review.D_REV_ID;


---------- ENTITY 11 ----------
CREATE SEQUENCE tourism_management_system.hotel_review_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TABLE tourism_management_system.hotel_review(
    H_REV_ID bigint,
    H_ID bigint not null ,
    B_ID bigint not null ,
    H_RATING numeric(2) CHECK ( H_RATING BETWEEN 1 AND 10),
    H_REVIEW TEXT,

    CONSTRAINT review_pk PRIMARY KEY (H_REV_ID),

    CONSTRAINT hReview_hInfo_dId FOREIGN KEY (H_ID)
        REFERENCES tourism_management_system.hotel_Info(H_ID)
                        ON DELETE CASCADE ON UPDATE CASCADE,

    CONSTRAINT hReview_bInfo_bId FOREIGN KEY (B_ID)
        REFERENCES tourism_management_system.booking_Info(B_ID)
                        ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER SEQUENCE tourism_management_system.hotel_review_id_seq
    OWNED BY tourism_management_system.hotel_review.H_REV_ID;


---------- ENTITY 12 ----------
CREATE SEQUENCE tourism_management_system.payment_info_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TYPE  tourism_management_system.payment_info_mode_enum AS ENUM (
    'CARD',
    'UPI',
    'NET BANKING',
    'WALLET',
    'CRYPTOCURRENCY'
);

CREATE TYPE  tourism_management_system.payment_info_status_enum AS ENUM (
    'SUCCESS',
    'PENDING',
    'FAILED'
);

CREATE TABLE tourism_management_system.payment_Info(
    P_ID bigint,
    B_ID bigint not null ,
    MODE tourism_management_system.payment_info_mode_enum
        not null DEFAULT 'CARD',
    DISCOUNT SMALLINT DEFAULT 0,
    NET_CHARGE INT NOT NULL DEFAULT 0,
    GST INT NOT NULL DEFAULT 0,
        CHECK ( GST<NET_CHARGE ),
    TXN_ID VARCHAR NOT NULL ,
    P_STATUS tourism_management_system.payment_info_status_enum
        NOT NULL,

    CONSTRAINT pInfo_pk PRIMARY KEY (P_ID),
    CONSTRAINT pInfo_bInfo_bId FOREIGN KEY (B_ID)
        REFERENCES tourism_management_system.booking_Info(B_ID)
                        ON DELETE SET NULL ON UPDATE CASCADE
);

COMMENT ON COLUMN tourism_management_system.payment_Info.MODE IS
    '[1:CASH | 2:CARD | 3:UPI | 4:NET BANKING | 5:WALLET | 6:CRYPTOCURRENCY]';
COMMENT ON COLUMN tourism_management_system.payment_Info.MODE IS
    '[1:SUCCESS | 2:PENDING | 3:FAILED]';

ALTER SEQUENCE tourism_management_system.payment_info_id_seq
    OWNED BY tourism_management_system.payment_Info.P_ID;


---------- ENTITY 13 ----------
CREATE SEQUENCE tourism_management_system.enquiry_id_seq
    AS INTEGER MINVALUE 1 NO CYCLE;

CREATE TABLE tourism_management_system.enquiry(
    ENQ_ID bigint,
    B_ID bigint not null ,
    P_ID BIGINT NOT NULL ,
    T_ID BIGINT NOT NULL,
    ENQ_SUBJECT varchar not null,
    ENQ_BODY text,
    ENQ_OPEN BOOL DEFAULT TRUE,

    CONSTRAINT enquiry_pk PRIMARY KEY (ENQ_ID),
    CONSTRAINT enquiry_bInfo_bId FOREIGN KEY (B_ID)
        REFERENCES tourism_management_system.booking_Info(B_ID)
                        ON DELETE SET NULL ON UPDATE CASCADE,

    CONSTRAINT enquiry_pInfo_pId FOREIGN KEY (P_ID)
        REFERENCES tourism_management_system.payment_Info(P_ID)
                        ON DELETE SET NULL ON UPDATE CASCADE,

    CONSTRAINT enquiry_tInfo_tId FOREIGN KEY (T_ID)
        REFERENCES tourism_management_system.transport_info(T_ID)
                        ON DELETE SET NULL ON UPDATE CASCADE
);

ALTER SEQUENCE tourism_management_system.enquiry_id_seq
    OWNED BY tourism_management_system.enquiry.ENQ_ID;

