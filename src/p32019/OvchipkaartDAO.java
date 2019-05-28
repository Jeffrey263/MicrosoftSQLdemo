package p32019;

import java.util.List;

public interface OvchipkaartDAO {
	public List<OVchipkaart> findAll();
	public List<OVchipkaart> findByKaartnummer(int kaartnummer);
	//public List<Product> findByKaartnummer(OVchipkaart kaart);
	public List<OVchipkaart> findByReiziger(Reiziger r);
	public OVchipkaart save(OVchipkaart kaart);
	public OVchipkaart update(OVchipkaart kaart);
	public boolean delete(OVchipkaart kaart);
	
}
