CREATE USER 'nwttim3'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON events.* TO 'nwttim3'@'%';
GRANT ALL PRIVILEGES ON jwt_security.* TO 'nwttim3'@'%';
GRANT ALL PRIVILEGES ON electionmanagement.* TO 'nwttim3'@'%';
GRANT ALL PRIVILEGES ON voterdb.* TO 'nwttim3'@'%';
GRANT ALL PRIVILEGES ON resultdb.* TO 'nwttim3'@'%';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS events;
CREATE DATABASE IF NOT EXISTS jwt_security;
CREATE DATABASE IF NOT EXISTS electionmanagement;
CREATE DATABASE IF NOT EXISTS voterdb;
CREATE DATABASE IF NOT EXISTS resultdb;