package si.fri.prpo.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

import si.fri.prpo.fasade.UpravljalecNarocniskihRazmerijSBLocal;
import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.izjeme.NiNaZalogiException;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;

/**
 * Message-Driven Bean implementation class for: NovoNarociloMDB
 */
@MessageDriven(
		activationConfig = { @ActivationConfigProperty(
				propertyName = "destination", propertyValue = "java:jboss/jms/queues/NarocilaQueue"), @ActivationConfigProperty(
				propertyName = "destinationType", propertyValue = "javax.jms.Queue")
		}, 
		mappedName = "NarocilaQueue")
public class NovoNarociloMDB implements MessageListener {

	@EJB
	private UpravljalecNarocniskihRazmerijSBLocal upravljalecRazmerij;
	
    /**
     * Default constructor. 
     */
    public NovoNarociloMDB() {
        // TODO Auto-generated constructor stub
    }
	
	/**
     * @see MessageListener#onMessage(Message)
     */
    @TransactionAttribute(value=TransactionAttributeType.REQUIRED)
    public void onMessage(Message message) {
    	try {
			upravljalecRazmerij.vstaviNarociloInGenerirajRacun(message.getBody(Narocilo.class));
			System.out.println(message.getBody(Narocilo.class).getLokacija_Dostave());
			System.out.println(this);
			System.out.println(message.getBody(Narocilo.class).getClass());
//			throw new RuntimeException();
		} catch (NiNaZalogiException e) {
			try {
				message.acknowledge();
			} catch (JMSException e1) {
				System.err.println("JMSException in NiNaZalogiException");
			}
		} catch (IzdelekNotFoundException e) {
			try {
				message.acknowledge();
			} catch (JMSException e1) {
				System.err.println("JMSException in IzdelekNotFoundException");
			}
		} catch (JMSException e) {
//			try {
//				message.acknowledge();
//			} catch (JMSException e1) {
			System.err.println("JMSException");
//			}
		}
    }

}
