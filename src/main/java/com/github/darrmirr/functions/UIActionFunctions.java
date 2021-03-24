package com.github.darrmirr.functions;

import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Optional;

public interface UIActionFunctions {

    static ActionListener copy2clipboard(JTextComponent textComponent) {
        return event -> {
            Optional.ofNullable(textComponent)
                    .map(JTextComponent::getText)
                    .map(StringSelection::new)
                    .ifPresent(stringSelection -> Toolkit
                            .getDefaultToolkit()
                            .getSystemClipboard()
                            .setContents(stringSelection, null));
        };
    }

    static ActionListener pasteFromClipboard(JTextComponent textComponent) {
        return event -> {
            var clipboardContent = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
            try {
                var text = clipboardContent.getTransferData(DataFlavor.stringFlavor);
                Optional.ofNullable(textComponent)
                        .ifPresent(component -> component.setText(text.toString()));
            } catch (UnsupportedFlavorException | IOException e) {
                e.printStackTrace();
            }
        };
    }

    static ActionListener switchContent(JTextComponent from, JTextComponent to) {
        return event -> {
            var fromText = from.getText();
            var toText = to.getText();
            to.setText(fromText);
            from.setText(toText);
        };
    }
}
