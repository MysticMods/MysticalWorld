package epicsquid.mysticalworld.capability;

public class AnimalCooldownCapability {
    private long cooldown = 0;

    public boolean canHarvest() {
        System.out.println(cooldown);
        System.out.println(System.currentTimeMillis());
        return cooldown <= System.currentTimeMillis();
    }

    public long getCooldown() {
        return cooldown;
    }

    /**
     * @param cooldown Cooldown in ticks/milliseconds
     */
    public void setCooldown(long cooldown) {

        cooldown = (cooldown/20) * 1000;
        this.cooldown = System.currentTimeMillis() + cooldown;
    }
}
