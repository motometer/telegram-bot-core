create table telegram_users (
  id bigint not null,
  telegram_user_id bigint not null unique,
  first_name varchar(255) not null,
  last_name varchar(255),
  is_bot bool default false,
  language_code varchar (8),
  username varchar(255),
  primary key (id)
);

create sequence telegram_users_seq start 1 increment by 1;

commit;