-- init.sql
SET GLOBAL general_log = 'ON';
SET GLOBAL general_log_file = '/var/log/mysql-general.log';

-- (복제용 계정 및 마스터 DB 설정)
CREATE USER 'repl_user'@'%' IDENTIFIED BY 'repl_password';
GRANT REPLICATION SLAVE ON *.* TO 'repl_user'@'%';
FLUSH PRIVILEGES;
