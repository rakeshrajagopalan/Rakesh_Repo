package courierpack;

import java.util.List;
import javax.ejb.Remote;

@Remote
public interface MessageProcessorInterface {
	public void processMessage(List<String> courierDetails);
}
