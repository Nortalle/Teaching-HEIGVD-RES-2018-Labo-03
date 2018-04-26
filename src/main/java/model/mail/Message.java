package model.mail;

public class Message {
    private String from;
    private String body;
    private String[] to = new String[0];
    private String[] cc = new String[0];
    private String[] bcc = new String[0];

    public void setSubject(String subject) {
        this.subject = subject;
    }

    private String subject;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
    }

    public String[] getBcc() {
        return bcc;
    }

    public void setBcc(String[] bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String messageBody) {
        if (messageBody.startsWith("Subject:")) {
            int index = messageBody.indexOf("\r\n");
            setSubject(messageBody.substring(9, index));
            this.body = messageBody.substring(index + 2);
        } else {
            this.body = messageBody;
        }
    }

}
