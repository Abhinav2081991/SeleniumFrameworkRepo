package PaymentsFramework.Utilities;

public class PaymentMode {

    Payment payment;

    public static void makePayment(){
        Payment p = new DebitCard();
        p.pay();
    }


    public static void main(String[] args) {
        makePayment();
    }

}
