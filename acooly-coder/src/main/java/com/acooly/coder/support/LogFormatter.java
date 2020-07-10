/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-01-28 20:19 创建
 */
package com.acooly.coder.support;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

/**
 * @author acooly
 */
public class LogFormatter extends Formatter {

    private final String lineSeparator = System.getProperty("line.separator");

    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Date date = new Date();
    @Override
    public String format(LogRecord record) {
        StringBuffer sb = new StringBuffer();

        date.setTime(record.getMillis());
        String source = record.getLoggerName();
        String message = formatMessage(record);

        sb.append(sdf.format(date)).append(" ");
        sb.append(record.getLevel().getName()).append(" ");
        sb.append(source).append(" - ");
        sb.append(message);
        sb.append(lineSeparator);
        if (record.getThrown() != null) {
            try {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                record.getThrown().printStackTrace(pw);
                pw.close();
                sb.append(sw.toString());
            } catch (Exception ex) {
            }
        }
        return sb.toString();
    }
}
