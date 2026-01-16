package PaymentsFramework.Utilities;

public class CreditCard implements Payment{

    @Override
    public void pay(){
        System.out.println("Implementation of pay method using Credit card");
    }
}
