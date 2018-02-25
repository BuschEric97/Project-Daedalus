/*
 * Program by: Eric Busch
 */

public class DnDToken extends Token
{
    String name, status;
    int hp, maxHp;
    
    @Override
    public String DisplayStats()
    {
        ///TODO: implement this method
        
        return null;
    }
    
    // getters and setters
    public void setName(String tempName)
    { this.name = tempName; }
    public String getName()
    { return this.name; }
    public void setStatus(String tempStatus)
    { this.status = tempStatus; }
    public String getStatus()
    { return this.status; }
    public void setHP(int tempHp)
    { this.hp = tempHp; }
    public int getHP()
    { return this.hp; }
    public void setMaxHP(int tempMaxHp)
    { this.maxHp = tempMaxHp; }
    public int getMaxHP()
    { return this.maxHp; }
}
