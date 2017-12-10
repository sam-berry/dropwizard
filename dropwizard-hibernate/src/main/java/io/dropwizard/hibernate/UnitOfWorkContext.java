package io.dropwizard.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class UnitOfWorkContext {
  protected static final ThreadLocal<SessionFactory> SESSION_FACTORY_CONTEXT = new ThreadLocal<>();
  protected static final ThreadLocal<UnitOfWork> UNIT_OF_WORK_CONTEXT = new ThreadLocal<>();

  protected static void setSessionFactory(SessionFactory sessionFactory) {
    SESSION_FACTORY_CONTEXT.set(sessionFactory);
  }

  public static SessionFactory getSessionFactory() {
    if (SESSION_FACTORY_CONTEXT.get() == null) {
      throw new IllegalStateException("No SessionFactory is set");
    }

    return SESSION_FACTORY_CONTEXT.get();
  }

  protected static void setUnitOfWork(UnitOfWork unitOfWork) {
    UNIT_OF_WORK_CONTEXT.set(unitOfWork);
  }

  public static UnitOfWork getUnitOfWork() {
    if (UNIT_OF_WORK_CONTEXT.get() == null) {
      throw new IllegalStateException("No UnitOfWork is set");
    }

    return UNIT_OF_WORK_CONTEXT.get();
  }

  public static Session getCurrentSession() {
    return getSessionFactory().getCurrentSession();
  }

  protected static void clearContext() {
    SESSION_FACTORY_CONTEXT.remove();
    UNIT_OF_WORK_CONTEXT.remove();
  }
}
