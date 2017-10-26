package io.dropwizard.logging.json;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.contrib.jackson.JacksonJsonFormatter;
import ch.qos.logback.core.LayoutBase;
import ch.qos.logback.core.spi.DeferredProcessingAware;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import io.dropwizard.logging.AbstractAppenderFactory;
import io.dropwizard.logging.FileAppenderFactory;
import io.dropwizard.logging.json.layout.JsonStacktraceLayout;
import io.dropwizard.logging.layout.LayoutFactory;

/**
 * <table>
 * <tr>
 * <td>Name</td>
 * <td>Default</td>
 * <td>Description</td>
 * </tr>
 * <tr>
 * <td>{@code prettyPrint}</td>
 * <td>{@code false}</td>
 * <td>Whether jackson json printing should beautify the output for human readability</td>
 * </tr>
 * <tr>
 * <td>{@code includeStackTrace}</td>
 * <td>true</td>
 * <td>
 * whether to include the stacktrace along with the exception and other details
 * </td>
 * </tr>
 * <tr>
 * <td>{@code timestampFormat}</td>
 * <td>{@code None}</td>
 * <td>The formatter string to use to format timestamps. Defaults to the time elapsed since unix epoch</td>
 * </tr>
 * </table>
 *
 * @see AbstractAppenderFactory
 */
public class AbstractJsonFileAppenderFactory<E extends DeferredProcessingAware> extends FileAppenderFactory<E> {

    private boolean prettyPrint;

    private boolean includeStackTrace = true;

    private String timestampFormat;

    @JsonProperty
    public String getTimestampFormat() {
        return timestampFormat;
    }

    @JsonProperty
    public void setTimestampFormat(String timestampFormat) {
        this.timestampFormat = timestampFormat;
    }

    @JsonProperty
    public boolean isPrettyPrint() {
        return prettyPrint;
    }

    @JsonProperty
    public void setPrettyPrint(boolean prettyPrint) {
        this.prettyPrint = prettyPrint;
    }

    @JsonProperty
    public boolean isIncludeStackTrace() {
        return includeStackTrace;
    }

    @JsonProperty
    public void setIncludeStackTrace(boolean includeStackTrace) {
        this.includeStackTrace = includeStackTrace;
    }
}
