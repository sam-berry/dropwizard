package io.dropwizard.logging.json;

import ch.qos.logback.access.spi.IAccessEvent;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.json.layout.LogbackAccessRequestJsonLayout;
import io.dropwizard.logging.layout.LayoutFactory;

@JsonTypeName("access-json-file")
public class AccessJsonFileAppenderFactory extends AbstractJsonFileAppenderFactory<IAccessEvent> {

    @Override
    protected LayoutBase<IAccessEvent> buildLayout(LoggerContext context, LayoutFactory<IAccessEvent> layoutFactory) {
        return new LogbackAccessRequestJsonLayout(context, getTimeZone(), getTimestampFormat());
    }
}
