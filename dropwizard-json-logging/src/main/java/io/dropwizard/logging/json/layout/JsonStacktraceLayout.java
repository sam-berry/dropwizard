package io.dropwizard.logging.json.layout;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.contrib.json.classic.JsonLayout;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonStacktraceLayout extends JsonLayout {

    private static final String STACKTRACE_ATTR_NAME = "stacktrace";

    private final boolean includeStacktrace;

    public JsonStacktraceLayout(boolean includeStacktrace) {
        this.includeStacktrace = includeStacktrace;
    }

    @Override
    protected Map toJsonMap(ILoggingEvent event) {
        IThrowableProxy throwableProxy = event.getThrowableProxy();
        if (!includeStacktrace || throwableProxy == null || throwableProxy.getStackTraceElementProxyArray() == null) {
            return super.toJsonMap(event);
        }

        Map<String, Object> map = super.toJsonMap(event);

        String exceptions = Arrays.stream(throwableProxy.getStackTraceElementProxyArray())
            .map(StackTraceElementProxy::getSTEAsString)
            .collect(Collectors.joining(System.lineSeparator()));
        if (exceptions.length() > 0) {
            map.put(STACKTRACE_ATTR_NAME, exceptions);
        }

        return map;

    }
}
