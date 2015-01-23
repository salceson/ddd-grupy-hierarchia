package agh.ddd.groups.web.requestobjects;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author Michal Janczykowski
 */
public class PollForm {
    private String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private DateTime deadline;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public DateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(DateTime deadline) {
        this.deadline = deadline;
    }
}
