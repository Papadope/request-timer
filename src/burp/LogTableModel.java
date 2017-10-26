/*
 * MIT License
 *
 * Copyright (c) 2017 Nick Taylor
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package burp;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 * Log table object
 */
public class LogTableModel extends AbstractTableModel {

    private final java.util.List<Log> logArray = new ArrayList<>();

    @Override
    public int getRowCount() {

        return logArray.size();
    }

    @Override
    public int getColumnCount() {

        return 6;
    }

    @Override
    public String getColumnName(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return "Timestamp";
            case 1:
                return "Tool";
            case 2:
                return "Request URL";
            case 3:
                return "MIME Type";
            case 4:
                return "Response Time (ms)";
            case 5:
                return "HTTP Status";
            default:
                return "";
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {

        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            case 4:
                return Long.class;
            case 5:
                return Short.class;
            default:
                return Object.class;
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        Log logEntry = logArray.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return logEntry.timestamp;
            case 1:
                return logEntry.tool;
            case 2:
                return logEntry.url.toString();
            case 3:
                return logEntry.mimeType;
            case 4:
                return logEntry.time;
            case 5:
                return logEntry.status;
            default:
                return "";
        }
    }

    /**
     * Returns the <code>Log</code>
     *
     * @return <code>List<Log></code>
     */
    List<Log> getLogArray() {

        return logArray;
    }

}

