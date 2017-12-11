package io.dropwizard.hibernate;

import org.hibernate.Session;

// A temporary simple solution to assign a session to a thread
public class UnitOfWorkContext {
    private static final ThreadLocal<Session> CONTEXT = new ThreadLocal<>();

    public static void setSession(Session session) {
        CONTEXT.set(session);
    }

    public static Session getSession() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}
