package tp.pr2.testprofesor;
import tp.pr2.Item;
import tp.pr2.Player;
import tp.pr2.Room;


public class MockItem extends Item {

	protected MockItem(String id, String description) {
		super(id, description);
	}

	@Override
	public boolean use(Player who, Room where) {
		return false;
	}

	@Override
	public boolean canBeUsed() {
		return false;
	}

}
