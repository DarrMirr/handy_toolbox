package com.github.darrmirr;

import com.github.darrmirr.util.MediaTypeTool;

import javax.swing.*;

import static com.github.darrmirr.functions.UIActionFunctions.*;

public class ToolBoxWindow {
    private JPanel windowContent;
    private JTextArea outputArea;
    private JTextArea inputArea;
    private JButton prettyButton;
    private JButton outputCopyButton;
    private JButton outputClearButton;
    private JButton inputClearButton;
    private JButton inputCopyButton;
    private JPanel outputButtonPanel;
    private JPanel inputButtonPanel;
    private JButton minifyButton;
    private JButton switchButton;
    private JButton inputPasteButton;
    private JButton outputPasteButton;
    private JLabel toolLabel;
    private JScrollPane inputScrollPane;
    private JScrollPane outputScrollPane;
    private JPanel operationPanel;

    public ToolBoxWindow(MediaTypeTool mediaTypeTool) {
        toolLabel.setText(mediaTypeTool.typeName());
        prettyButton.addActionListener(event -> {
            var text = inputArea.getText();
            var prettyJson = mediaTypeTool.prettyPrint(text);
            outputArea.setText(prettyJson);
        });
        minifyButton.addActionListener(event -> {
            var text = inputArea.getText();
            var minifiedJson = mediaTypeTool.minify(text);
            outputArea.setText(minifiedJson);
        });
        switchButton.addActionListener(switchContent(inputArea, outputArea));
        outputClearButton.addActionListener(event -> outputArea.setText(""));
        inputClearButton.addActionListener(event -> inputArea.setText(""));
        outputCopyButton.addActionListener(copy2clipboard(outputArea));
        inputCopyButton.addActionListener(copy2clipboard(inputArea));
        outputPasteButton.addActionListener(pasteFromClipboard(outputArea));
        inputPasteButton.addActionListener(pasteFromClipboard(inputArea));
    }

    public JPanel getContent() {
        return windowContent;
    }

    public String getDisplayName() {
        return toolLabel.getText();
    }
}
