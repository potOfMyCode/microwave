package com.microwave.design;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.atomic.AtomicInteger;

public class LogArea extends JPanel {
    private final static String newline = "\n";
    public static final String DEFAULT_TEXT = "Info:\n";
    private JTextArea textArea;
    private static AtomicInteger numberOfOperation = new AtomicInteger(0);

    public LogArea() {
        // We create a TextArea object
        textArea = new JTextArea(15, 30);
        textArea.setText(DEFAULT_TEXT);
        // We put the TextArea object in a Scrollable Pane
        JScrollPane scrollPane = new JScrollPane(textArea);

        // In order to ensure the scroll Pane object appears in your window,
        // set a preferred size to it!
        scrollPane.setPreferredSize(new Dimension(380, 200));
        // Lines will be wrapped if they are too long to fit within the
        // allocated width
        textArea.setLineWrap(true);
        // Lines will be wrapped at word boundaries (whitespace) if they are
        // too long to fit within the allocated width
        textArea.setWrapStyleWord(true);
        // Assuming this is the chat client's window where we read text sent out
        // and received, we don't want our Text Area to be editable!
        textArea.setEditable(false);
        // We also want a vertical scroll bar on our pane, as text is added to it
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        JButton clearLogButton = new JButton("Clear Log");
        clearLogButton.addActionListener(e -> {
            clearLog();
        });

        this.setLayout(new BorderLayout());
        //adds and centers the text field to the frame
        //adds and centers the scroll pane to the frame
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(clearLogButton, BorderLayout.SOUTH);
    }

    public void clearLog() {
        textArea.setText(DEFAULT_TEXT);
        numberOfOperation.set(0);
    }

    public void append(String text) {
        textArea.append(numberOfOperation.addAndGet(1) + ":\n" + text + newline);
    }

    public void appendImportant(String text) {
        textArea.append(numberOfOperation.addAndGet(1) + ":\n" + "----------> " + text + newline);
    }
}
