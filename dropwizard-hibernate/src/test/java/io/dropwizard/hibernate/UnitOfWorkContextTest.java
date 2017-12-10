package io.dropwizard.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnitOfWorkContextTest {
    private final SessionFactory sessionFactory = mock(SessionFactory.class);
    private final Session session = mock(Session.class);

    private UnitOfWork unitOfWork;

    @Before
    public void setUp() throws Exception {
        this.unitOfWork = ClassWithUnitOfWork.class
            .getDeclaredMethod("example")
            .getAnnotation(UnitOfWork.class);
    }

    @After
    public void tearDown() {
        UnitOfWorkContext.clearContext();
    }

    @Test
    public void remembersSessionFactory() {
        UnitOfWorkContext.setSessionFactory(sessionFactory);
        SessionFactory result = UnitOfWorkContext.getSessionFactory();
        assertThat(result).isEqualTo(sessionFactory);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsIfSessionFactoryIsRetrievedBeforeSet() {
        UnitOfWorkContext.getSessionFactory();
    }

    @Test
    public void remembersUnitOfWork() {
        UnitOfWorkContext.setUnitOfWork(unitOfWork);
        UnitOfWork result = UnitOfWorkContext.getUnitOfWork();
        assertThat(result).isEqualTo(unitOfWork);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsIfUnitOfWorkIsRetrievedBeforeSet() {
        UnitOfWorkContext.getUnitOfWork();
    }

    @Test
    public void retrievesCurrentSession() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        UnitOfWorkContext.setSessionFactory(sessionFactory);
        Session result = UnitOfWorkContext.getCurrentSession();
        assertThat(result).isEqualTo(session);
    }

    @Test(expected = IllegalStateException.class)
    public void throwsIfCurrentSessionIsRetrievedBeforeSet() {
        UnitOfWorkContext.getCurrentSession();
    }

    @Test
    public void clearsContext() {
        UnitOfWorkContext.setSessionFactory(sessionFactory);
        UnitOfWorkContext.clearContext();
        SessionFactory sessionFactoryResult = UnitOfWorkContext.SESSION_FACTORY_CONTEXT.get();
        UnitOfWork unitOfWorkResult = UnitOfWorkContext.UNIT_OF_WORK_CONTEXT.get();
        assertThat(sessionFactoryResult).isNull();
        assertThat(unitOfWorkResult).isNull();
    }
}
