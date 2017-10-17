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

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Data object containing request info
 */
class Log {

    final LocalDateTime timestamp;
    final String tool;
    final IHttpRequestResponsePersisted requestResponse;
    final URL url;
    final long time;
    final short status;
    final String mimeType;

    /**
     * Constructor
     *
     * @param timestamp time of request
     * @param tool originating tool
     * @param requestResponse <code>IHttpRequestResponsePersisted</code> object
     * @param url request URL
     * @param status HTTP status
     * @param mimeType stated MIME type
     * @param time time taken
     */
    Log(LocalDateTime timestamp, String tool, IHttpRequestResponsePersisted requestResponse, URL url, short status, String mimeType, long time) {

        this.timestamp = timestamp;
        this.tool = tool;
        this.requestResponse = requestResponse;
        this.url = url;
        this.time = time;
        this.status = status;
        this.mimeType = mimeType;
    }
}

