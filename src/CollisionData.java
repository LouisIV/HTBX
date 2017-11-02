public class CollisionData {
	private int damage;
	private CollisionType type;
	
	
	public CollisionData() {
		this.damage = 0;
		this.type = CollisionType.enemyShip;
	}
	public CollisionData(int damage, CollisionType type) {
		this.damage = damage;
		this.type = type;
	}
	public CollisionData(CollisionData toCopy) {
		this.damage = toCopy.getDamage();
		this.type = toCopy.getType();
	}
	public int getDamage() {
		return this.damage;
	}
	public CollisionType getType() {
		return type;
	}
}