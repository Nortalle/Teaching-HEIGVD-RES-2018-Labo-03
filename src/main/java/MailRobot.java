import config.ConfigurationManager;
import model.mail.Message;
import model.prank.Prank;
import model.prank.PrankGenerator;
import smtp.SmtpClient;

import java.io.IOException;
import java.util.List;

public class MailRobot {

    public static void main(String[] args) throws IOException {

        ConfigurationManager config = new ConfigurationManager();

        System.out.println(config.getSmtpServerAdress());
        System.out.println(config.getSmtpServerPort());

        SmtpClient client = new SmtpClient(config.getSmtpServerAdress(), config.getSmtpServerPort());

        PrankGenerator prank = new PrankGenerator(config);

        List<Prank> pranks = prank.generatePranks();

        for(Prank p : pranks){
            Message message = p.generateMailMessage();
            client.sendMessage(p.generateMailMessage());
        }
    }
}
