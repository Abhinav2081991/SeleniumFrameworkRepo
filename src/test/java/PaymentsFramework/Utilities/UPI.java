package PaymentsFramework.Utilities;

public class UPI implements Payment{

    @Override
    public void pay(){
        System.out.println("Implementation of pay method using UPI");
    }
}
