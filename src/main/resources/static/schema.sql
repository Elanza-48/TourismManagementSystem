create table if not exists destination_info
(
	id uuid not null
		constraint destination_info_pkey
			primary key,
	name varchar(255) not null,
	description text,
	province varchar(255) not null,
	max_stay_duration smallint not null
		constraint destination_info_max_stay_duration_check
			check ((max_stay_duration <= 10) AND (max_stay_duration >= 1))
);

alter table destination_info owner to elanza48;

create table if not exists hotel_info
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
	type varchar(255) not null,
	dest_id uuid
		constraint fk5fqf7uq2lmmjpdgmohy0uh0w1
			references destination_info
);

alter table hotel_info owner to elanza48;

create table if not exists tour_package
(
	id uuid not null
		constraint tour_package_pkey
			primary key,
	name varchar(255) not null,
	activities varchar(255),
	description text,
	events varchar(255),
	is_active boolean not null
);

alter table tour_package owner to elanza48;

create table if not exists package_destination_map
(
	pkg_id uuid not null
		constraint fkrdl0n6lfhnsj9sl5grpohnkyd
			references tour_package,
	dest_id uuid not null
		constraint fk1t2ow0a5oad8gebxkj33xg5gw
			references destination_info,
	constraint package_destination_map_pkey
		primary key (pkg_id, dest_id)
);

alter table package_destination_map owner to elanza48;

create table if not exists transport_info
(
	id uuid not null
		constraint transport_info_pkey
			primary key,
	name varchar(255) not null,
	description varchar(255),
	entity varchar(255) not null,
	mode varchar(255) not null
);

alter table transport_info owner to elanza48;

create table if not exists package_transport_map
(
	pkg_id uuid not null
		constraint fk9memke7qoliougsasdho1tkwe
			references tour_package,
	transport_id uuid not null
		constraint fkf7hrew16is0icrmni1q6vf2p2
			references transport_info,
	constraint package_transport_map_pkey
		primary key (pkg_id, transport_id)
);

alter table package_transport_map owner to elanza48;

create table if not exists user_account
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
	password varchar(255) not null,
	role varchar(255) not null,
	status varchar(255) not null
);

alter table user_account owner to elanza48;

create table if not exists booking_info
(
	id uuid not null
		constraint booking_info_pkey
			primary key,
	date date not null,
	passenger_count smallint not null
		constraint booking_info_passenger_count_check
			check ((passenger_count <= 12) AND (passenger_count >= 1)),
	price integer not null,
	r_type varchar(255) not null,
	status varchar(255) not null,
	t_date date not null,
	pkg_id uuid
		constraint fkelbdjqisvxs739mlsod911w1a
			references tour_package,
	usr_id uuid
		constraint fk6d1chw6nwjy2f3uu3sedmi4yr
			references user_account
);

alter table booking_info owner to elanza48;

create table if not exists booking_transport_ticket
(
	id uuid not null
		constraint booking_transport_ticket_pkey
			primary key,
	date date not null,
	seat_no varchar(255),
	booking_id uuid
		constraint fken34kwktauff7v7cmdigh4g6e
			references booking_info,
	transport_id uuid
		constraint fke1v4en00je65862tg95hweahr
			references transport_info
);

alter table booking_transport_ticket owner to elanza48;

create table if not exists destination_review
(
	id uuid not null
		constraint destination_review_pkey
			primary key,
	date date not null,
	rating smallint
		constraint destination_review_rating_check
			check ((rating <= 10) AND (rating >= 1)),
	review text,
	booking_id uuid
		constraint fkqj9cu61twr3eqp94x8svsfmev
			references booking_info,
	dest_id uuid
		constraint fk5rifvqfr6bru4qxy9e4wfjg3i
			references destination_info
);

alter table destination_review owner to elanza48;

create table if not exists enquiry
(
	id uuid not null
		constraint enquiry_pkey
			primary key,
	body text,
	date date not null,
	enq_open boolean not null,
	subject varchar(255) not null,
	booking_id uuid
		constraint fk7tu0qxpmby0vug7thd1w7s7mm
			references booking_info,
	transport_id uuid
		constraint fk29oqcouj0savhbmunvlpgnb5w
			references transport_info
);

alter table enquiry owner to elanza48;

create table if not exists hotel_review
(
	id uuid not null
		constraint hotel_review_pkey
			primary key,
	date date not null,
	rating smallint
		constraint hotel_review_rating_check
			check ((rating <= 10) AND (rating >= 1)),
	review text,
	booking_id uuid
		constraint fk9mu47qnkfke40xct5ovdg53oh
			references booking_info,
	hotel_id uuid
		constraint fke0v23wothi7yrt4attt34nfn2
			references hotel_info
);

alter table hotel_review owner to elanza48;

create table if not exists payment_info
(
	id uuid not null
		constraint payment_info_pkey
			primary key,
	date date not null,
	discount smallint,
	gst integer not null,
	mode varchar(255) not null,
	net_charge integer not null,
	status varchar(255) not null,
	txn_id varchar(255) not null,
	booking_id uuid
		constraint fk71aagn5gsa0onk975hm748abj
			references booking_info,
	constraint payment_info_check
		check (gst < net_charge)
);

alter table payment_info owner to elanza48;