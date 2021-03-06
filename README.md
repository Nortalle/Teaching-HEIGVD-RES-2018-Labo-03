# Forged mail SMTP CLIENT

## Description

I developped a client application (TCP) in Java that will use the Socket API to communicate with a SMTP server. it includes partial implementation of the SMTP protocol. The final goal is to randomly send prank, that you define, to a list of contact. Here a sample of some mail I sent :

![](./figures/1.JPG)

## Instructions to set the mock SMTP server up

To test my application, I used MockMock. it's a cross-platform SMTP server built on Javathat allows you to test if outgoing emails are sent (without actually sending them) and to see what they look like. It provides a web interface that displays which emails were sent and shows you what the contents of those emails are. 

If you have Docker on your machine, you can go to the folder **Test**, open a terminal on it and write :

```
docker build -t coucou .
docker run -p 8282:8282 -p 25:25 coucou
```

And the server is ready to listen our forged mail. you can test it without my application like that:

```
telnet localhost 25
[...]
EHLO coucou
[...]
MAIL TO: me.you@us.ch
[...]
```

And if you open your browser to `localhost:8282`, the mail appears here.

**25** is the port by default for this server.

##Instruction to run the forged mail client

You will find in the **config** folder some file, you're free to change it for your pranks

- config.propertiers

  ```
  smtpServerAddress=localhost				//address of the smtp server
  smtpServerPort=25					    //port it listens to
  numberOfGroups=8						//Number of mail you want to sent
  witnessesToCC=vincent.guidoux1@heig-vd.ch //address of the attacker to witness
  ```

- victims.RES.utf8

  A list with the mail of the victims you want to prank

- messages.utf8

  The message you want to sent. it needs a "`Subject: `" at the beginning, and if you want to put more than one message, you need to split them with "`==`"

And you just need to run the `.jar` and the mails are sent.

## Implementation

![](./figures/2.JPG)

(there is a typo in this image. it's **Application-specific business logic**)

### Get Configuration Data

- `ConfigurationManager` gets the data from the configuration's file

### Application-specific business logic

- `mail` : a package composed with `Group`, `Message` et `Person`. Define respectively a group of person to attack, the message you sent, with all the data like the subject, the sender, the receiver and a person with a complete name and an mail address
- `mail` : a package composed with `Prank` and`PrankGenerator`. `PrankGenerator`generates `List<Prank>` .  The **numberOfGroups** given in the configuration files define how many mail will be sent. A mail is sent to a group of person with a random message.o

### Smtp Protocol

- `smtpClient` can send message to the server and port given in the configuration files.

![https://www.afternerd.com/blog/smtp/ ](./figures/SMTP-sequence-diagram.png)





