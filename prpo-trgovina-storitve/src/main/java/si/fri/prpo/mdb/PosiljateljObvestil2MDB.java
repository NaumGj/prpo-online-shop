package si.fri.prpo.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Obvestila;
import si.fri.prpo.vmesniki.UpravljalecObvestilSBLocal;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBLocal;

/**
 * Message-Driven Bean implementation class for: PosiljateljObvestil2MDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:jboss/jms/topics/ProgressTopic"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Topic")
		}, 
		mappedName = "java:jboss/jms/topics/ProgressTopic")
public class PosiljateljObvestil2MDB implements MessageListener {

	@EJB
	private UpravljalecObvestilSBLocal upravljalecObvestil;
	
	@EJB
	private UpravljalecNarocilSBLocal upravljalecNarocil;
	
    /**
     * Default constructor. 
     */
    public PosiljateljObvestil2MDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    public void onMessage(Message message) {
    	try {
			System.out.println("MDB 2 je prejelo sporocilo: " + message.getBody(String.class));
//			Obvestila obv = upravljalecObvestil.vrniObvestilo(1);
//			System.out.println(obv.getObvestilo());
			Obvestila obv = new Obvestila();
			obv.setObvestilo(message.getBody(String.class));
//			obv.setNarocilo(upravljalecNarocil.vrniNarocilo(21));
//			System.out.println(obv);
			upravljalecObvestil.shraniObvestilo(obv);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
