CREATE KEYSPACE application
  WITH REPLICATION = {
  'class' : 'SimpleStrategy',
  'replication_factor' : 1
  };

CREATE TABLE application.user
(
  id               timeuuid,
  email            text,
  firstName        text,
  lastName         text,
  role             text,
  isEnabled        boolean,
  verificationCode int,
  creationTime     timestamp,
  modificationTime timestamp,
  version          int,
  PRIMARY KEY (id, email)
);

CREATE TABLE application.chat
(
  id               timeuuid,
  creationTime     timestamp,
  modificationTime timestamp,
  version          int,
  chat_type        text,
  participant      uuid,
  description      text,
  primary key ((participant),id, chat_type)
);
CREATE TABLE application.contact
(
  id               timeuuid,
  creationTime     timestamp,
  modificationTime timestamp,
  version          int,
  contactId        uuid,
  approved         boolean,
  primary key ((contactId),id, approved)
);
CREATE TABLE application.message
(
  id               timeuuid,
  creationTime     timestamp,
  modificationTime timestamp,
  version          int,
  message_type     text,
  chatId           uuid,
  message_text     text,
  primary key (chatId, id)
);


