package PaymentsFramework.Utilities;

public class PaymentMode {

    public static Payment makePayment(String PaymentType){

        switch(PaymentType) {
            case "Debit":
                return new DebitCard();
            case "Credit":
                return new CreditCard();
            case "UPI":
                return new UPI();
            default:
                throw new IllegalArgumentException();
        }
    }

}
