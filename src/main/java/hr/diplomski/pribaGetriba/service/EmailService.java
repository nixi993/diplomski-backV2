package hr.diplomski.pribaGetriba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	@Autowired
    private JavaMailSender emailSender;

    public void posaljiMailKlijentu(String emailTo,String username,String password) {
    	
    	try {
    		
	        SimpleMailMessage message = new SimpleMailMessage();         
	        message.setFrom("nikola.pribanic93@gmail.com");
	        message.setTo(emailTo); 
	        message.setSubject("Status vozila"); 
	        message.setText("Poštovanje,\n " +
	        		"prijavom na link: http://localhost:3000/prijava možete provjeriti status naloga o vašem vozilu.\n "+
	        		"Podaci za prijavu u aplikaciju su username: " + username + " i password: " + password +
	        		"Lp, Nikola");
	        emailSender.send(message);
	     
    	}catch(Exception ex) {
	    	 System.out.println(ex.getMessage());
	    	 throw ex;	    	 
	     }
      
    }

}
