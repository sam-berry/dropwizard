package io.dropwizard.logging.json;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.contrib.jackson.JacksonJsonFormatter;
import ch.qos.logback.core.LayoutBase;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.json.layout.JsonStacktraceLayout;
import io.dropwizard.logging.layout.LayoutFactory;

@JsonTypeName("json-file")
public class ClassicJsonFileAppenderFactory extends AbstractJsonFileAppenderFactory<ILoggingEvent> {

    @Override
    @SuppressWarnings("unchecked")
    protected LayoutBase<ILoggingEvent> buildLayout(LoggerContext context, LayoutFactory<ILoggingEvent> layoutFactory) {
        JacksonJsonFormatter jsonFormatter = new JacksonJsonFormatter();
        jsonFormatter.setPrettyPrint(isPrettyPrint());
        JsonStacktraceLayout jsonLayout = new JsonStacktraceLayout(isIncludeStackTrace());
        jsonLayout.setJsonFormatter(jsonFormatter);
        jsonLayout.setContext(context);
        jsonLayout.setTimestampFormat(getTimestampFormat());
        jsonLayout.setTimestampFormatTimezoneId(timeZone.getID());
        jsonLayout.setAppendLineSeparator(true);
        return jsonLayout;
    }

}
