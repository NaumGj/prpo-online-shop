package si.fri.prpo.soap.v1.impl;


import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.jws.WebService;
import javax.ws.rs.core.Response;

import si.fri.prpo.izjeme.IzdelekNotFoundException;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekFault;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekFaultSporocilo;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekFault_Exception;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekOdgovorSporocilo;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekRequest;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekResponse;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekeOdgovorSporocilo;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekeRequest;
import si.fri.prpo.sheme.izdelki.sporocila.v1.VrniIzdelekeResponse;
import si.fri.prpo.sheme.izdelki.v1.IzdelkiType;
import si.fri.prpo.sheme.izdelki.v1.NapakeType;
import si.fri.prpo.spletna_trgovina_jpa.entitete.Izdelek;
import si.fri.prpo.vmesniki.Izdelek.UpravljalecIzdelkovSBLocal;
import si.fri.prpo.vmesniki.soap.v1.IzdelkiPort;

@WebService(serviceName = "izdelki", endpointInterface = "si.fri.prpo.vmesniki.soap.v1.IzdelkiPort", targetNamespace = "http://prpo.fri.si/storitve/izdelki/v1")
public class IzdelkiPortImpl implements IzdelkiPort {
	
	@EJB
	private UpravljalecIzdelkovSBLocal upravljalecIzdelkov;
	
	public VrniIzdelekResponse vrniIzdelek(VrniIzdelekRequest parameters) throws VrniIzdelekFault_Exception {
		VrniIzdelekResponse resp = new VrniIzdelekResponse();
		VrniIzdelekOdgovorSporocilo vizp = new VrniIzdelekOdgovorSporocilo();
		Izdelek izd;
		try {
			izd = upravljalecIzdelkov.vrniIzdelek(parameters.getInput().getId());
		} catch (IzdelekNotFoundException e) {
			VrniIzdelekFault napaka = new VrniIzdelekFault();
			VrniIzdelekFaultSporocilo sporocilo = new VrniIzdelekFaultSporocilo();
			NapakeType nt = new NapakeType();
			nt.setStatus(Response.Status.NOT_FOUND.getStatusCode());
			nt.setKoda(String.valueOf(Response.Status.NOT_FOUND.getStatusCode()) + "001");
			nt.setSporocilo(e.getMessage());
			sporocilo.setNapaka(nt);
			napaka.setFault(sporocilo);
			throw new VrniIzdelekFault_Exception("Napaka v SOAP pri vracanju izdelka", napaka);
		}
		IzdelkiType izdelek = new IzdelkiType();
		izdelek.setId(izd.getId());
		izdelek.setIme(izd.getIme());
		izdelek.setKategorija(izd.getKategorija());
		izdelek.setCena(izd.getCena());
		izdelek.setDavek(izd.getDavek());
		izdelek.setOpis(izd.getOpis());
		izdelek.setZaloga(izd.getZaloga());
		vizp.setIzdelek(izdelek);
		resp.setOutput(vizp);
		return resp;
	}

	public VrniIzdelekeResponse vrniIzdeleke(VrniIzdelekeRequest parameters) {
		VrniIzdelekeResponse resp = new VrniIzdelekeResponse();
		VrniIzdelekeOdgovorSporocilo vizp = new VrniIzdelekeOdgovorSporocilo();
		List<Izdelek> izdelki = new ArrayList<Izdelek>();
//		List<Izdelek> izdelki = upravljalecIzdelkov.vrniOstranjeneIzdelke(parameters.getInput().getOffset(), parameters.getInput().getLimit());
		List<IzdelkiType> izdeleki = new ArrayList<IzdelkiType>();
		for(Izdelek izd : izdelki) {
			IzdelkiType izdelek = new IzdelkiType();
			izdelek.setId(izd.getId());
			izdelek.setIme(izd.getIme());
			izdelek.setKategorija(izd.getKategorija());
			izdelek.setCena(izd.getCena());
			izdelek.setDavek(izd.getDavek());
			izdelek.setOpis(izd.getOpis());
			izdelek.setZaloga(izd.getZaloga());
			izdeleki.add(izdelek);
		}
		vizp.setIzdeleki(izdeleki);
		resp.setOutput(vizp);
		return resp;
	}
}