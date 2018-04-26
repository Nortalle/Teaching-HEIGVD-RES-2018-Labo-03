package model.prank;

import model.mail.Message;
import model.mail.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Prank {

    private Person victimSender;
    private final List<Person> victimRecipients = new ArrayList<Person>();
    private final List<Person> witnessRecipients = new ArrayList<Person>();
    private String message;

    public Person getVictimSender() {
        return victimSender;
    }

    public void setVictimSender(Person victimSender) {
        this.victimSender = victimSender;
    }

    public List<Person> getVictimRecipients() {
        return victimRecipients;
    }

    public List<Person> getWitnessRecipients() {
        return witnessRecipients;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void addVictimRecipients(List<Person> all){
        victimRecipients.addAll(all);
    }

    public void addWitnessRecipients(List<Person> all){
        witnessRecipients.addAll(all);
    }

    public Message generateMailMessage(){
        Message msg = new Message();

        msg.setBody(this.message + "\r\n" + victimSender.getFirstName());

        String[] to = victimRecipients.stream().map(p -> p.getAddress()).collect(Collectors.toList()).toArray(new String[]{});
        msg.setTo(to);

        String[] cc = witnessRecipients.stream().map(p -> p.getAddress()).collect(Collectors.toList()).toArray(new String[]{});
        msg.setCc(cc);

        msg.setFrom(victimSender.getAddress());

        return msg;
    }
}
