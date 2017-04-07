package si.fri.prpo.remote;

import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.List;
import java.util.Properties;

import si.fri.prpo.spletna_trgovina_jpa.entitete.Narocilo;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Uporabnik;
import si.fri.prpo.vmesniki.Narocilo.UpravljalecNarocilSBRemote;
import si.fri.prpo.vmesniki.Uporabnik.UpravljalecUporabnikovSBRemote;

public class OdjemalecOddaljen {

	public static void main(String[] args){
		try {
			Properties props = new Properties();
			 
	        props.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
	        props.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	        props.put(Context.PROVIDER_URL, "http-remoting://localhost:4447");
	        props.put(Context.SECURITY_PRINCIPAL, "username");
	        props.put(Context.SECURITY_CREDENTIALS, "password");
	        
	        props.put("org.jboss.ejb.client.scoped.context", true);
	        props.put("remote.connections", "default");
	        props.put("remote.connection.default.host", "localhost");
	        props.put("remote.connection.default.port", "8080");
	        props.put("remote.connectionprovider.create.options.org.xnio.Options.SSL_ENABLED", false);
	        props.put("remote.connection.default.connect.options.org.xnio.Options.SASL_POLICY_NOANONYMOUS", false);
			
	        Context ctx = new InitialContext(props);
			String jndiEjbUporabniki = "ejb:prpo-trgovina-ear-0.0.1-SNAPSHOT/prpo-trgovina-storitve-0.0.1-SNAPSHOT/UpravljalecUporabnikovSB!si.fri.prpo.vmesniki.UpravljalecUporabnikovSBRemote";
//			System.out.println(ctx.lookup(jndi).getClass());
			UpravljalecUporabnikovSBRemote uporabnikZrno = (UpravljalecUporabnikovSBRemote) ctx.lookup(jndiEjbUporabniki);
			List<Uporabnik> userList = uporabnikZrno.vrniVseUporabnike();
			for (Uporabnik u : userList) {
	            System.out.println("ID: " + u.getId() + ", ime: " + u.getIme() + ", priimek: " + u.getPriimek() 
	            + ", uporabnisko ime: " + u.getUporabnisko_ime() + "\n");
	        }
			
			
			String jndiEjbNarocila = "ejb:prpo-trgovina-ear-0.0.1-SNAPSHOT/prpo-trgovina-storitve-0.0.1-SNAPSHOT/UpravljalecNarocilSB!si.fri.prpo.vmesniki.UpravljalecNarocilSBRemote";
			UpravljalecNarocilSBRemote narocilaZrno = (UpravljalecNarocilSBRemote) ctx.lookup(jndiEjbNarocila);
			
			//izpis prvega narocila
			Narocilo nar = narocilaZrno.vrniNarocilo(1);
			System.out.println("ID: " + nar.getId() + ", lokacija dostave: " + nar.getLokacija_Dostave() 
								+ ", nacin prevzema: " + nar.getNacin_prevzema());

		} catch (Exception e) {
			System.out.println("Napaka " + e.getMessage());
			e.printStackTrace();
		}
	}

}
