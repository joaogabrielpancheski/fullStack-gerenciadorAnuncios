package br.grupointegrado.ads.gerenciadorDeAnuncios.utils;

import br.grupointegrado.ads.gerenciadorDeAnuncios.modelos.Contato;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * @author jgpancheski
 */
public class JavaMailApp {

    public void enviarEmail(Contato contato) {
        Properties props = new Properties();

        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("outsid3rtestemail@gmail.com", "javamail956");
            }
        });

        session.setDebug(true);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("outsid3rtestemail@gmail.com")); //Remetente

            Address[] toUser = InternetAddress.parse("joao.pancheski@grupointegrado.br"); //Destinatário(s)

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject("Nova mensagem de " + contato.getNome());//Assunto
            message.setText("Nome: " + contato.getNome() + ". \nE-mail: " + contato.getEmail() + ". \nMensagem: " + contato.getMensagem());

            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
