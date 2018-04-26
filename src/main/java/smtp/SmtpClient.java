package smtp;

import model.mail.Message;

import java.io.*;
import java.net.Socket;
import java.util.Base64;
import java.util.logging.Logger;

public class SmtpClient implements ISmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private String smtpServerAddress;
    private int smtpServerPort;
    private PrintWriter writer;
    private BufferedReader reader;

    private final String EOL = "\r\n";

    public SmtpClient(String smtpServerAddress, int smtpServerPort) {
        this.smtpServerAddress = smtpServerAddress;
        this.smtpServerPort = smtpServerPort;
    }

    @Override
    public void sendMessage(Message message) throws IOException {
        Socket socket = new Socket(smtpServerAddress, smtpServerPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));

        String line = reader.readLine();
        LOG.info(line);
        writer.write("EHLO coucou" + EOL);
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        while (!line.startsWith("250 ")) {
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("MAIL FROM: " + message.getFrom() + EOL);
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        for (String to : message.getTo()) {
            writer.write("RCPT TO: " + to + EOL);
            writer.flush();

            line = reader.readLine();
            LOG.info(line);
        }
        for (String to : message.getCc()) {
            writer.write("RCPT TO: " + to + EOL);
            writer.flush();

            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("DATA" + EOL);
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        writer.write("From: " + message.getFrom() + EOL);

        writer.write("To: ");
        writeTabString(message.getTo());

        writer.write("Cc: ");
        writeTabString(message.getCc());

        if (!message.getSubject().equals("")) {
            writer.write("Subject: " + message.getSubject() + EOL);
            writer.flush();
        }

        writer.write(message.getBody() + EOL + "." + EOL); // End of DATA
        writer.flush();

        writer.write("QUIT" + EOL);
        writer.flush();

        reader.close();
        writer.close();
        socket.close();
    }

    private void writeTabString(String[] recipients) {
        for (int i = 0; i < recipients.length; i++) {
            writer.write(recipients[i]);
            if (i != recipients.length - 1) {
                writer.write(", ");
            }
        }
        writer.write(EOL);
        writer.flush();
    }
}
