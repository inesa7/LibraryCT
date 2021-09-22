package utilities;

import org.apache.commons.mail.SimpleEmail;

public class GmailUtility {

    public static void sendEmails(String username, String password, String receiver, String subject, String MSG){

        if(receiver.toLowerCase().contains("muhtar")){
            System.err.print("Invalid Email address");
            System.exit(1);
        }

        SimpleEmail email=new SimpleEmail();
        email.setHostName("smtp.googlemail.com");
        email.setSmtpPort(465);
        email.setAuthentication(username, password);
        email.setSSLOnConnect(true);
        email.setSubject(subject);

        try {
            email.setFrom(username);
            email.setMsg(MSG);
            email.addTo(receiver);
            email.send();
            Thread.sleep(500);
        } catch (Exception e) {   }

    }



}
