#!/bin/bash
echo "Waiting for MySQL writer to be ready..."
until mysql -h mysql_writer -uroot -proot_password -e "select 1" > /dev/null 2>&1; do
sleep 2
done

echo "Setting up replication..."
MASTER_STATUS=$(mysql -h mysql_writer -uroot -proot_password -e "SHOW MASTER STATUS\G")
CURRENT_LOG=$(echo "$MASTER_STATUS" | grep 'File:' | awk '{print $2}')
CURRENT_POS=$(echo "$MASTER_STATUS" | grep 'Position:' | awk '{print $2}')

mysql -uroot -proot_password -e "
  CHANGE MASTER TO
    MASTER_HOST='mysql_writer',
    MASTER_USER='repl_user',
    MASTER_PASSWORD='repl_password',
    MASTER_LOG_FILE='$CURRENT_LOG',
    MASTER_LOG_POS=$CURRENT_POS;
  START SLAVE;
"
