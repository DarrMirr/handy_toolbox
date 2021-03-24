package com.github.darrmirr;

import com.github.darrmirr.util.ToolFactory;
import com.github.darrmirr.util.MediaTypeTool;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import static com.github.darrmirr.util.ToolFactory.JSON_TYPE;
import static com.github.darrmirr.util.ToolFactory.XML_TYPE;

public class ToolBoxWindowFactory implements ToolWindowFactory {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        var jsonMediaTypeTool = ToolFactory.get(JSON_TYPE);
        var xmlMediaTypeTool = ToolFactory.get(XML_TYPE);

        Content jsonContent = createContent(jsonMediaTypeTool);
        Content xmlContent = createContent(xmlMediaTypeTool);

        toolWindow.getContentManager().addContent(jsonContent);
        toolWindow.getContentManager().addContent(xmlContent);
    }

    private Content createContent(MediaTypeTool mediaTypeTool) {
        var toolBoxWindow = new ToolBoxWindow(mediaTypeTool);
        return ContentFactory.SERVICE
                .getInstance()
                .createContent(toolBoxWindow.getContent(), toolBoxWindow.getDisplayName(), false);
    }
}
