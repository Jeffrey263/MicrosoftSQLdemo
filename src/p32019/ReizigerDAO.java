package p32019;

import java.sql.Date;
import java.util.List;

public interface ReizigerDAO {
	public List<Reiziger> findAll();
	public Reiziger save(Reiziger r);
	public Reiziger update(Reiziger r);
	public boolean delete(Reiziger r);
	
}
