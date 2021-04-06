
package mail;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {
    public static String sendMail(String subject,String body,String remail){
       try{ 
        final String aemail="testmailid799@gmail.com";
        final String apass="test@1234";
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");  
        properties.put("mail.smtp.socketFactory.port", "465");  
        properties.put("mail.smtp.socketFactory.class",  
              "javax.net.ssl.SSLSocketFactory");  
        properties.put("mail.smtp.auth", "true");  
        properties.put("mail.smtp.port", "465");
        Session ses = Session.getInstance(properties,    
            new javax.mail.Authenticator() {  
             protected PasswordAuthentication getPasswordAuthentication() {  
             return new PasswordAuthentication(aemail, apass); } 
            });  

        Message message = new MimeMessage(ses);  
        message.setFrom(new InternetAddress(aemail));  
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(remail));  
        message.setSubject(subject);  
        message.setText(body);  
        Transport.send(message);
        return "success";
       }catch(Exception ex){
           ex.printStackTrace();
           return ex+""; 
       }
       }
}