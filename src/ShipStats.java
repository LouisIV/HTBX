public class ShipStats {

	private int speed;
	private int damage;
	private int health_max;
	private int shield_max;


	public ShipStats(int speed, int damage, int health_max, int shield_max) {
		this.speed = speed;
		this.damage = damage;
		this.health_max = health_max;
		this.shield_max = shield_max;
	}

	public static ShipStats PlayerStats(int base_hp) {
		return new ShipStats(1, 3, base_hp, 1);
	}

	public static ShipStats EnemyStats_Boss() {
		return new ShipStats(0, 0, 2250, 0);
	}
	
	public static ShipStats EnemyStats_01() {
		return new ShipStats(1, 1, 120, 0);
	}

	public static ShipStats EnemyStats_02() {
		return new ShipStats(1, 1, 160, 0);
	}

	public static ShipStats EnemyStats_03() {
		return new ShipStats(1, 1, 220, 0);
	}

	public static ShipStats EnemyStats_Blinker() {
		return new ShipStats(1, 1, 30, 0);
	}

	public static ShipStats EnemyStats_Banshe() {
		return new ShipStats(5, 1, 30, 0);
	}
	
	public static ShipStats bossBulletEmitter_0() {
		return new ShipStats(5, 1, 30, 0);
	}
	
	public static ShipStats EnemyStats_BossBarrier() {
		return new ShipStats(0, 0, 100, 0);
	}

	public float getTurningSpeed() {
		return 5f;
	}

	public int getSpeedSetting() {
		return this.speed;
	}

	public float getSpeedValue() {
		switch(this.speed) {
		case 0:
			return 5;
		case 1:
			return 6f;
		case 2:
			return 8f;
		case 3:
			return 10f;
		case 4:
			return 15f;
		default:
			return 7;
		}
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	public int getShieldMax() {
		return shield_max;
	}
	public void setShieldMax(int shield_max) {
		this.shield_max = shield_max;
	}
	public int getHealthMax() {
		return health_max;
	}
	public void setHealthMax(int health_max) {
		this.health_max = health_max;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage = damage;
	}
	public void incSpeed(int update) {
		this.speed += update;
	}
	public void incShieldMax(int update) {
		this.shield_max += update;
	}
	public void incHealthMax(int update) {
		this.health_max += update;
	}
	public void incDamage(int update) {
		this.damage += update;
	}


}