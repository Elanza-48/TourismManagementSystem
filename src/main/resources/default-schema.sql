create schema if not exists main;

create table if not exists main.destination_info
(
    id uuid not null
        constraint destination_info_pkey
            primary key,
    name varchar(255),
    description text,
    created_at timestamp,
    last_update timestamp,
    province varchar(255),
    max_stay_duration smallint
);

alter table main.destination_info owner to elanza48;

create table if not exists main.hotel_info
(
    id uuid not null
        constraint hotel_info_pkey
            primary key,
    name varchar(255),
    district varchar(255),
    state varchar(255),
    street varchar(255),
    zip integer,
    email varchar(255)
        constraint uk_7buf3hcywohvmsjxk01sfy0cy
            unique,
    mobile_no bigint
        constraint uk_63r0g6xsvc10yf3sk1db0h94e
            unique,
    base_price integer,
    created_at timestamp,
    last_update timestamp,
    type varchar(255),
    dest_id uuid
        constraint fk3nyry6is77yukwn8qrwindt11
            references main.destination_info
);

alter table main.hotel_info owner to elanza48;

create table if not exists main.tour_package
(
    id uuid not null
        constraint tour_package_pkey
            primary key,
    name varchar(255),
    active boolean,
    activities varchar(255),
    description text,
    events varchar(255),
    created_at timestamp,
    last_update timestamp
);

alter table main.tour_package owner to elanza48;

create table if not exists main.package_destination_map
(
    pkg_id uuid not null
        constraint fkaibog18vwpo2ted1uty503ud7
            references main.tour_package,
    dest_id uuid not null
        constraint fkdltog2upcsu47rs0tce0yyoin
            references main.destination_info,
    constraint package_destination_map_pkey
        primary key (pkg_id, dest_id)
);

alter table main.package_destination_map owner to elanza48;

create table if not exists main.transport_info
(
    id uuid not null
        constraint transport_info_pkey
            primary key,
    name varchar(255),
    description varchar(255),
    public boolean,
    created_at timestamp,
    last_update timestamp,
    mode varchar(255)
);

alter table main.transport_info owner to elanza48;

create table if not exists main.package_transport_map
(
    pkg_id uuid not null
        constraint fktqlxcbxru5ktdhmctno46t89n
            references main.tour_package,
    transport_id uuid not null
        constraint fkf7hrew16is0icrmni1q6vf2p2
            references main.transport_info,
    constraint package_transport_map_pkey
        primary key (pkg_id, transport_id)
);

alter table main.package_transport_map owner to elanza48;

create table if not exists main.user_privilege
(
    id uuid not null
        constraint user_privilege_pkey
            primary key,
    title varchar(255)
        constraint uk_fb300n26btrtq4vfx0323rme4
            unique,
    description varchar(255),
    created_at timestamp,
    last_update timestamp
);

alter table main.user_privilege owner to elanza48;

create table if not exists main.user_role
(
    id uuid not null
        constraint user_role_pkey
            primary key,
    title varchar(255)
        constraint uk_6x3pwox6vp81xlhxja06q1780
            unique,
    description varchar(255),
    created_at timestamp,
    last_update timestamp
);

alter table main.user_role owner to elanza48;

create table if not exists main.user_account
(
    id uuid not null
        constraint user_account_pkey
            primary key,
    name varchar(255),
    district varchar(255),
    state varchar(255),
    street varchar(255),
    zip integer,
    email varchar(255)
        constraint uk_lvq41xhn5q9yj3lxo2ayu86cd
            unique,
    mobile_no bigint
        constraint uk_cvvqrox5ha7mwk75ubajya35y
            unique,
    active boolean,
    dob date,
    gender varchar(255),
    created_at timestamp,
    last_update timestamp,
    password varchar(255),
    suspended boolean,
    role_id uuid
        constraint fk9emra1hnnkfjyk23gytulbhta
            references main.user_role
);

alter table main.user_account owner to elanza48;

create table if not exists main.booking_info
(
    id uuid not null
        constraint booking_info_pkey
            primary key,
    date date,
    created_at timestamp,
    last_update timestamp,
    passenger_count smallint,
    price integer,
    r_type varchar(255),
    status varchar(255),
    t_date date,
    pkg_id uuid
        constraint fkgmg08oj8wiq9vpm1xxqy8vtjs
            references main.tour_package,
    usr_id uuid
        constraint fkpsmvyud2kyu7t2pgvkaofupvl
            references main.user_account
);

alter table main.booking_info owner to elanza48;

create table if not exists main.booking_transport_ticket
(
    id uuid not null
        constraint booking_transport_ticket_pkey
            primary key,
    date date,
    created_at timestamp,
    last_update timestamp,
    seat_no varchar(255),
    booking_id uuid
        constraint fkjcs8x40unllndq12178woqcxs
            references main.booking_info,
    transport_id uuid
        constraint fke1v4en00je65862tg95hweahr
            references main.transport_info
);

alter table main.booking_transport_ticket owner to elanza48;

create table if not exists main.destination_review
(
    id uuid not null
        constraint destination_review_pkey
            primary key,
    date date,
    created_at timestamp,
    last_update timestamp,
    rating smallint,
    review text,
    booking_id uuid
        constraint fkbmwlp8qp6s23b6voygsrnfrc5
            references main.booking_info,
    dest_id uuid
        constraint fks3p2n1ypo374ytq8xj6gbhobh
            references main.destination_info
);

alter table main.destination_review owner to elanza48;

create table if not exists main.enquiry
(
    id uuid not null
        constraint enquiry_pkey
            primary key,
    body text,
    date date,
    created_at timestamp,
    last_update timestamp,
    enq_open boolean,
    subject varchar(255),
    booking_id uuid
        constraint fkb7et3hi2hqf55kqh6k5dq5hxe
            references main.booking_info
);

alter table main.enquiry owner to elanza48;

create table if not exists main.hotel_review
(
    id uuid not null
        constraint hotel_review_pkey
            primary key,
    date date,
    created_at timestamp,
    last_update timestamp,
    rating smallint,
    review text,
    booking_id uuid
        constraint fkiok3vw0psyijrqd4yf77nt0cp
            references main.booking_info,
    hotel_id uuid
        constraint fk934dgkqikwhn1anev1406e26b
            references main.hotel_info
);

alter table main.hotel_review owner to elanza48;

create table if not exists main.payment_info
(
    id uuid not null
        constraint payment_info_pkey
            primary key,
    date date,
    discount smallint,
    gst integer,
    created_at timestamp,
    last_update timestamp,
    mode varchar(255),
    net_charge integer,
    status varchar(255),
    txn_id varchar(255),
    booking_id uuid
        constraint fkcq05b82ft1huyiwymeniiqnet
            references main.booking_info,
    constraint payment_info_check
        check (gst < net_charge)
);

alter table main.payment_info owner to elanza48;

create table if not exists main.user_role_privilege_map
(
    role_id uuid not null
        constraint fkrwsdwuasxltfhpys5wri4n3r8
            references main.user_role,
    privilege_id uuid not null
        constraint fkfyeiwvgai4axa8yy4aae2if60
            references main.user_privilege,
    constraint user_role_privilege_map_pkey
        primary key (role_id, privilege_id)
);

alter table main.user_role_privilege_map owner to elanza48;
