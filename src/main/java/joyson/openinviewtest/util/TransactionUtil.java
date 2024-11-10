package joyson.openinviewtest.util;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.lang.reflect.Method;
import java.util.Arrays;

public class TransactionUtil {

    public static void logTransactionStatus() {
        final boolean isActive = TransactionSynchronizationManager.isActualTransactionActive();
        final boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        final String transactionName = TransactionSynchronizationManager.getCurrentTransactionName();
        final Integer isolationLevel = TransactionSynchronizationManager.getCurrentTransactionIsolationLevel();
        System.out.println("=================================================================");
        System.out.println("=================================================================");
        System.out.println("트랜잭션 활성 상태: " + isActive);
        System.out.println("트랜잭션 읽기 전용: " + isReadOnly);
        System.out.println("트랜잭션 이름: " + transactionName);
        System.out.println("트랜잭션 격리 수준: " + isolationLevel);
        System.out.println("=================================================================");
        System.out.println("=================================================================");

    }

    public static void checkTransactional(final Class<?> clazz) {
        for (final Method method : clazz.getDeclaredMethods()) {
            System.out.println(method.getName());
            Arrays.stream(method.getAnnotations())
                    .forEach(System.out::println);
            if (method.isAnnotationPresent(Transactional.class)) {
                final Transactional transactional = method.getAnnotation(Transactional.class);
                System.out.println("메소드: " + method.getName());
                System.out.println("트랜잭션 전파 옵션: " + transactional.propagation());
                System.out.println("읽기 전용 여부: " + transactional.readOnly());
                System.out.println("트랜잭션 격리 수준: " + transactional.isolation());
            }
        }
    }
}
