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

import javax.swing.JTable;

/**
 * Display table model
 */
public class LogTable extends JTable implements IMessageEditorController {

    private LogTableModel logTableModel;
    private IMessageEditor requestViewer;
    private IMessageEditor responseViewer;
    private IHttpRequestResponse currentlyDisplayedItem;

    /**
     * Constructor
     *
     * @param logTableModel <code>LogTableModel</code> object
     */
    LogTable(LogTableModel logTableModel) {

        super(logTableModel);
        this.logTableModel = logTableModel;
        this.requestViewer = BurpExtender.callbacks.createMessageEditor(this, false);
        this.responseViewer = BurpExtender.callbacks.createMessageEditor(this, false);
        setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        getColumnModel().getColumn(0).setMinWidth(200);
        getColumnModel().getColumn(1).setMinWidth(100);
        getColumnModel().getColumn(2).setPreferredWidth(1000);
        getColumnModel().getColumn(3).setMinWidth(100);
        getColumnModel().getColumn(4).setMinWidth(150);
        getColumnModel().getColumn(5).setMinWidth(100);
        setAutoCreateRowSorter(true);
    }

    @Override
    public byte[] getRequest() {

        return currentlyDisplayedItem.getRequest();
    }

    @Override
    public byte[] getResponse() {

        return currentlyDisplayedItem.getResponse();
    }

    @Override
    public IHttpService getHttpService() {

        return currentlyDisplayedItem.getHttpService();
    }

    @Override
    public void changeSelection(int row, int col, boolean toggle, boolean extend) {
        // show the log entry for the selected row
        Log logEntry = logTableModel.getLogArray().get(convertRowIndexToModel(row));
        requestViewer.setMessage(logEntry.requestResponse.getRequest(), true);
        responseViewer.setMessage(logEntry.requestResponse.getResponse(), false);
        currentlyDisplayedItem = logEntry.requestResponse;

        super.changeSelection(row, col, toggle, extend);
    }

    /**
     * Returns the request viewer object
     *
     * @return <code>IMessageEditor</code> object
     */
    IMessageEditor getRequestViewer() {

        return requestViewer;
    }

    /**
     * Returns the response viewer object
     *
     * @return <code>IMessageEditor</code> object
     */
    IMessageEditor getResponseViewer() {

        return responseViewer;
    }

}
