create schema if not exists main;

create table if not exists main.destination_info
(
    id uuid not null
        constraint destination_info_pkey
            primary key,
    name varchar(255) not null,
    description text,
    created_at timestamp,
    last_update timestamp,
    province varchar(2) not null,
    max_stay_duration smallint not null
        constraint destination_info_max_stay_duration_check
            check ((max_stay_duration >= 1) AND (max_stay_duration <= 10))
);

alter table main.destination_info owner to elanza48;

create table if not exists main.hotel_info
(
    id uuid not null
        constraint hotel_info_pkey
            primary key,
    name varchar(255) not null,
    district varchar(255),
    state varchar(255),
    street varchar(255),
    zip integer,
    email varchar(255) not null
        constraint uk_l8bt72o5k90md3trnaiht3buj
            unique,
    mobile_no bigint not null
        constraint uk_d6f91gbew38eo1ip5unmnawwf
            unique,
    base_price integer not null,
    created_at timestamp,
    last_update timestamp,
    type varchar(255) not null,
    dest_id uuid
        constraint fk5fqf7uq2lmmjpdgmohy0uh0w1
            references main.destination_info
);

alter table main.hotel_info owner to elanza48;

create table if not exists main.tour_package
(
    id uuid not null
        constraint tour_package_pkey
            primary key,
    name varchar(255) not null,
    active boolean not null,
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
        constraint fkrdl0n6lfhnsj9sl5grpohnkyd
            references main.tour_package,
    dest_id uuid not null
        constraint fk1t2ow0a5oad8gebxkj33xg5gw
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
    name varchar(255) not null,
    description varchar(255),
    public boolean not null,
    created_at timestamp,
    last_update timestamp,
    mode varchar(255) not null
);

alter table main.transport_info owner to elanza48;

create table if not exists main.package_transport_map
(
    pkg_id uuid not null
        constraint fk9memke7qoliougsasdho1tkwe
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
    title varchar(255) not null
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
    title varchar(255) not null
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
    name varchar(255) not null,
    district varchar(255),
    state varchar(255),
    street varchar(255),
    zip integer,
    email varchar(255) not null
        constraint uk_hl02wv5hym99ys465woijmfib
            unique,
    mobile_no bigint not null
        constraint uk_nfugw5dmwb1j6muyt5qdbr896
            unique,
    active boolean not null,
    dob date not null,
    gender integer not null,
    created_at timestamp,
    last_update timestamp,
    password varchar(255) not null,
    suspended boolean not null,
    role_id uuid
        constraint fkl1qv5l0fqqfrvlccnnyoatxtc
            references main.user_role
);

alter table main.user_account owner to elanza48;

create table if not exists main.booking_info
(
    id uuid not null
        constraint booking_info_pkey
            primary key,
    date date not null,
    created_at timestamp,
    last_update timestamp,
    passenger_count smallint not null
        constraint booking_info_passenger_count_check
            check ((passenger_count >= 1) AND (passenger_count <= 12)),
    price integer not null,
    r_type varchar(255) not null,
    status varchar(255) not null,
    t_date date not null,
    pkg_id uuid
        constraint fkelbdjqisvxs739mlsod911w1a
            references main.tour_package,
    usr_id uuid
        constraint fk6d1chw6nwjy2f3uu3sedmi4yr
            references main.user_account
);

alter table main.booking_info owner to elanza48;

create table if not exists main.booking_transport_ticket
(
    id uuid not null
        constraint booking_transport_ticket_pkey
            primary key,
    date date not null,
    created_at timestamp,
    last_update timestamp,
    seat_no varchar(255),
    booking_id uuid
        constraint fken34kwktauff7v7cmdigh4g6e
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
    date date not null,
    created_at timestamp,
    last_update timestamp,
    rating smallint
        constraint destination_review_rating_check
            check ((rating >= 1) AND (rating <= 10)),
    review text,
    booking_id uuid
        constraint fkqj9cu61twr3eqp94x8svsfmev
            references main.booking_info,
    dest_id uuid
        constraint fk5rifvqfr6bru4qxy9e4wfjg3i
            references main.destination_info
);

alter table main.destination_review owner to elanza48;

create table if not exists main.enquiry
(
    id uuid not null
        constraint enquiry_pkey
            primary key,
    body text,
    date date not null,
    created_at timestamp,
    last_update timestamp,
    enq_open boolean not null,
    subject varchar(255) not null,
    booking_id uuid
        constraint fk7tu0qxpmby0vug7thd1w7s7mm
            references main.booking_info
);

alter table main.enquiry owner to elanza48;

create table if not exists main.hotel_review
(
    id uuid not null
        constraint hotel_review_pkey
            primary key,
    date date not null,
    created_at timestamp,
    last_update timestamp,
    rating smallint
        constraint hotel_review_rating_check
            check ((rating >= 1) AND (rating <= 10)),
    review text,
    booking_id uuid
        constraint fk9mu47qnkfke40xct5ovdg53oh
            references main.booking_info,
    hotel_id uuid
        constraint fke0v23wothi7yrt4attt34nfn2
            references main.hotel_info
);

alter table main.hotel_review owner to elanza48;

create table if not exists main.payment_info
(
    id uuid not null
        constraint payment_info_pkey
            primary key,
    date date not null,
    discount smallint,
    gst integer not null,
    created_at timestamp,
    last_update timestamp,
    mode varchar(255) not null,
    net_charge integer not null,
    status varchar(255) not null,
    txn_id varchar(255) not null,
    booking_id uuid
        constraint fk71aagn5gsa0onk975hm748abj
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
