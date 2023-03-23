public class Players extends Person{

    private int amountOfMoney;

    public Players(String firstName) {
        super(firstName);
        this.amountOfMoney = 1000;
    }

    public Players(String firstName, String lastName) {
        super(firstName, lastName);
        this.amountOfMoney = 1000;
    }

    public int getAmountOfMoney() {
        return amountOfMoney;
    }

    public void setAmountOfMoney(int amountOfMoney) {
        this.amountOfMoney = amountOfMoney;
    }

    @Override
    public String toString () {
        return (getFirstName() + " " + getLastName() + ": " + getAmountOfMoney());
    }
}
