package site.root3287.sudo2.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormat extends Formatter{
	//%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %2$s%n%4$s: %5$s%6$s%n
	private static final String format = "[%1$tb %1$td, %1$tY %1$tl:%1$tM:%1$tS %1$Tp %4$s] %5$s%6$s%n";
    private final Date dat = new Date();

	@SuppressWarnings("unused")
	@Override
	public String format(LogRecord record) {
		dat.setTime(record.getMillis());
        String source;
        if (record.getSourceClassName() != null && false) {
            source = record.getSourceClassName();
            if (record.getSourceMethodName() != null) {
               source += " " + record.getSourceMethodName();
            }
        } else {
            source = record.getLoggerName();
        }
        String message = formatMessage(record);
        String throwable = "";
        if (record.getThrown() != null) {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            pw.println();
            record.getThrown().printStackTrace(pw);
            pw.close();
            throwable = sw.toString();
        }
        String s = String.format(format,
                dat,
                source,
                record.getLoggerName(),
                record.getLevel(),
                message,
                throwable);
        s.length();
        return String.format(format,
                             dat,
                             source,
                             record.getLoggerName(),
                             record.getLevel(),
                             message,
                             throwable);
	}

}
