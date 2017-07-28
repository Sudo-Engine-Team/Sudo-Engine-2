package site.root3287.sudo2.utils;

import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

public class ConsoleHandler extends StreamHandler{
	// Private method to configure a ConsoleHandler from LogManager
    // properties and/or default values as specified in the class
    // javadoc.
    private void configure() {

        setLevel(Level.ALL);
        setFilter(null);
        setFormatter(new LogFormat());
        try {
            setEncoding(null);
        } catch (Exception ex) {
            try {
                setEncoding(null);
            } catch (Exception ex2) {
                // doing a setEncoding with null should always work.
                // assert false;
            }
        }
    }

    /**
     * Create a <tt>ConsoleHandler</tt> for <tt>System.err</tt>.
     * <p>
     * The <tt>ConsoleHandler</tt> is configured based on
     * <tt>LogManager</tt> properties (or their default values).
     *
     */
    public ConsoleHandler() {
        configure();
        setOutputStream(System.out);
    }

    /**
     * Publish a <tt>LogRecord</tt>.
     * <p>
     * The logging request was made initially to a <tt>Logger</tt> object,
     * which initialized the <tt>LogRecord</tt> and forwarded it here.
     * <p>
     * @param  record  description of the log event. A null record is
     *                 silently ignored and is not published
     */
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        flush();
    }

    /**
     * Override <tt>StreamHandler.close</tt> to do a flush but not
     * to close the output stream.  That is, we do <b>not</b>
     * close <tt>System.err</tt>.
     */
    @Override
    public void close() {
        flush();
    }
}
