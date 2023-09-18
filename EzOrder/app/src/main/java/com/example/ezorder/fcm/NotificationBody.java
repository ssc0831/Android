package com.example.ezorder.fcm;

public class NotificationBody {
    private String to;
    private String priority;
    private NotificationData data;
    private NotificationData notification;

    public NotificationBody(String to,String priority, NotificationData data, NotificationData notification) {
        this.to = to;
        this.priority = priority;
        this.data = data;
        this.notification = notification;
    }

    public NotificationData getNotification() {
        return notification;
    }

    public void setNotification(NotificationData notification) {
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public NotificationData getData() {
        return data;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setData(NotificationData data) {
        this.data = data;
    }

    public static class NotificationData {
        private String title;
        private String body;

        public NotificationData(String title, String body) {
            this.title = title;
            this.body = body;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setMessage(String body) {
            this.body = body;
        }

        public String getTitle() {
            return title;
        }

        public String getMessage() {
            return body;
        }
    }
}
