package bahar.com.entity.core.character;

import bahar.com.entity.model.Envanter;

public class Player extends CoreEntitiy
{
    private int maxHealth;
    private int blok;
    private Envanter envanter;
    public Player(String name,int id, int damage, int health, int money)
    {
        super(name, id, damage, health, money);
        this.maxHealth = health; // maximum can oluşturulan karakterin canı kadar olucaktır
        this.blok = 0; // Oyun başladığında
        this.envanter = new Envanter();
    }

    public void updateDmage()
    {
        super.setDamage(this.getDamage() + this.envanter.getWeapon().getDmage());
    }

    public void updateBlock()
    {
        this.setBlok(envanter.getArmor().getBlock());
    }


    //-------------- GETTER & SETTER ----------------
    public int getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    public int getBlok() {
        return blok;
    }

    public void setBlok(int blok) {
        this.blok = blok;
    }

    public Envanter getEnvanter() {
        return envanter;
    }

    public void setEnvanter(Envanter envanter) {
        this.envanter = envanter;
    }
}
