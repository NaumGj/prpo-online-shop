package si.fri.prpo.zrna;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSContext;
import javax.jms.JMSDestinationDefinition;
import javax.jms.Queue;
import javax.jms.Topic;

import si.fri.prpo.vmesniki.SpremljanjeIzvajanjaZrnoLocal;
import si.fri.prpo.vmesniki.SpremljanjeIzvajanjaZrnoRemote;

/**
 * Session Bean implementation class SpremljanjeIzvajanjaZrno
 */
@Stateless
@Remote(SpremljanjeIzvajanjaZrnoRemote.class)
@Local(SpremljanjeIzvajanjaZrnoLocal.class)
@JMSDestinationDefinition(
		name = "java:jboss/jms/topics/ProgressTopic",
		interfaceName = "javax.jms.Topic",
		destinationName = "ProgressTopic"
)
public class SpremljanjeIzvajanjaZrno implements SpremljanjeIzvajanjaZrnoRemote, SpremljanjeIzvajanjaZrnoLocal {

	@Inject
	private JMSContext context; 
	
	@Resource(lookup = "java:jboss/jms/topics/ProgressTopic")
	private Topic tema;
	
    /**
     * Default constructor. 
     */
    public SpremljanjeIzvajanjaZrno() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void napredekIzvajanja(String sporocilo) {
		context.createProducer().send(tema, sporocilo);
	}

}
