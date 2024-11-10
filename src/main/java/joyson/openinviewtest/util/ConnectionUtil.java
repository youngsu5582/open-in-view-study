package joyson.openinviewtest.util;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
@Component
public class ConnectionUtil {
    private final HikariDataSource dataSource;

    // writeDataSource 빈을 명시적으로 주입
    public ConnectionUtil(@Qualifier("writeDataSource") final DataSource dataSource) {
        this.dataSource = (HikariDataSource) dataSource;
    }

    public void logConnections() {
        final int activeConnections = dataSource.getHikariPoolMXBean().getActiveConnections();
        final int idleConnections = dataSource.getHikariPoolMXBean().getIdleConnections();
        final int totalConnections = dataSource.getHikariPoolMXBean().getTotalConnections();
        final int threadsAwaitingConnection = dataSource.getHikariPoolMXBean().getThreadsAwaitingConnection();

        System.out.println("활성화 된 커넥션 수 : " + activeConnections);
        System.out.println("현재 사용되지 않는 커넥션 수 : " + idleConnections);
        System.out.println("총 커넥션 수 : " + totalConnections);
        System.out.println("커넥션을 기다리는 스레드 수 : " + threadsAwaitingConnection);
    }
}

