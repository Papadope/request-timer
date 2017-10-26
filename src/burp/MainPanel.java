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

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

/**
 * Main display panel
 */
public class MainPanel extends JPanel implements ITab {

    private LogTableModel logTableModel;
    private JTextField urlFilterText;
    private JCheckBox scopeCheckBox;

    /**
     * Constructor
     *
     * @param extender <code>BurpExtender</code> object
     */
    MainPanel(BurpExtender extender) {

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // main split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

        // table of log entries
        logTableModel = new LogTableModel();
        LogTable logTable = new LogTable(logTableModel);
        JScrollPane scrollPane = new JScrollPane(logTable);
        splitPane.setLeftComponent(scrollPane);

        // tabs with request/response viewers
        JTabbedPane tabs = new JTabbedPane();
        tabs.setBorder(BorderFactory.createLineBorder(Color.black));
        tabs.addTab("Request", logTable.getRequestViewer().getComponent());
        tabs.addTab("Response", logTable.getResponseViewer().getComponent());
        splitPane.setRightComponent(tabs);

        // top control panel
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel toolLabel = new JLabel("Select tool: ");
        controlPanel.add(toolLabel);

        String[] tools = {"All", "Proxy", "Intruder", "Scanner", "Repeater"};
        JComboBox<String> toolList = new JComboBox<>(tools);
        toolList.addActionListener(e -> {
            String tool = (String) ((JComboBox) e.getSource()).getSelectedItem();
            if (tool != null) {
                switch (tool) {
                    case "All":
                        extender.setToolFilter(0);
                        break;
                    case "Proxy":
                        extender.setToolFilter(IBurpExtenderCallbacks.TOOL_PROXY);
                        break;
                    case "Intruder":
                        extender.setToolFilter(IBurpExtenderCallbacks.TOOL_INTRUDER);
                        break;
                    case "Scanner":
                        extender.setToolFilter(IBurpExtenderCallbacks.TOOL_SCANNER);
                        break;
                    case "Repeater":
                        extender.setToolFilter(IBurpExtenderCallbacks.TOOL_REPEATER);
                        break;
                    default:
                        throw new RuntimeException("Unknown tool: " + tool);
                }
            }
        });
        controlPanel.add(toolList);

        JButton startButton = new JButton("Start");
        controlPanel.add(startButton);
        JButton stopButton = new JButton("Stop");
        controlPanel.add(stopButton);
        JButton clearButton = new JButton("Clear");
        stopButton.setEnabled(false);
        controlPanel.add(clearButton);
        JLabel scopeLabel = new JLabel("In-scope items only?");
        controlPanel.add(scopeLabel);
        scopeCheckBox = new JCheckBox();
        controlPanel.add(scopeCheckBox);
        JLabel filterLabel = new JLabel("Filter URL:");
        controlPanel.add(filterLabel);
        urlFilterText = new JTextField(40);
        controlPanel.add(urlFilterText);

        startButton.addActionListener(e -> {
            extender.setRunning(true);
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
        });

        stopButton.setEnabled(false);
        stopButton.addActionListener(e -> {
            extender.setRunning(false);
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
        });

        clearButton.addActionListener(e -> {
            extender.getReqResMap().clear();
            logTableModel.getLogArray().clear();
            logTableModel.fireTableDataChanged();
        });

        controlPanel.setAlignmentX(0);
        add(controlPanel);
        add(splitPane);

        // customize our UI components
        BurpExtender.callbacks.customizeUiComponent(this);
    }

    @Override
    public String getTabCaption() {

        return "Request Timer";
    }

    @Override
    public Component getUiComponent() {

        return this;
    }

    /**
     * Returns the Log table model
     *
     * @return <code>LogTableModel</code> object
     */
    LogTableModel getLogTableModel() {

        return logTableModel;
    }

    /**
     * Returns the text to filter the URL by
     *
     * @return filter text
     */
    String getURLFilterText() {

        return urlFilterText.getText();
    }

    /**
     * Is the in scope only checkbox selected?
     *
     * @return true if selected, false if not
     */
    boolean isScopeSelected() {

        return scopeCheckBox.isSelected();
    }
}
