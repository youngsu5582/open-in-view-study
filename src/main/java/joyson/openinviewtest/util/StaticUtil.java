package joyson.openinviewtest.util;

import jakarta.persistence.EntityManagerFactory;
import org.hibernate.SessionFactory;
import org.hibernate.stat.Statistics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StaticUtil {

    private final SessionFactory sessionFactory;

    @Autowired
    public StaticUtil(final EntityManagerFactory entityManagerFactory) {
        this.sessionFactory = entityManagerFactory.unwrap(SessionFactory.class);
    }

    public void printStatistics() {
        final Statistics stats = sessionFactory.getStatistics();
        stats.setStatisticsEnabled(true);  // 통계 활성화


        System.out.println("=================================================================");
        System.out.println("=================================================================");
        System.out.println("Connection count: "+stats.getConnectCount());
        System.out.println("Entity fetch count: " + stats.getEntityFetchCount());
        System.out.println("Entity load count: " + stats.getEntityLoadCount());
        System.out.println("Query execution count: " + stats.getQueryExecutionCount());
        System.out.println("Transaction count: " + stats.getTransactionCount());
        System.out.println("=================================================================");
        System.out.println("=================================================================");
    }
}
