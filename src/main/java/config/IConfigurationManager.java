package config;

import model.mail.Person;

import java.security.KeyStore;
import java.util.List;

public interface IConfigurationManager {
    List<String> getMessages();

    int getNumberOfGroups();

    public String getSmtpServerAdress();

    public int getSmtpServerPort() ;

    public List<Person> getVictims() ;

    public List<Person> getWitnessesToCC() ;
}
