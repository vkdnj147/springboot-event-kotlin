CREATE USER 'linaexample'@'%' IDENTIFIED WITH mysql_native_password BY 'Linaexample@!prod1';
CREATE DATABASE linaexample;

GRANT ALL ON linaexample.* TO 'linaexample'@'%';

FLUSH PRIVILEGES;
