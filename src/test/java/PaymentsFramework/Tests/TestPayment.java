package PaymentsFramework.Tests;

import PaymentsFramework.Utilities.Payment;
import PaymentsFramework.Utilities.PaymentMode;
import org.testng.annotations.Test;

public class TestPayment {

    Payment payment;

    @Test
    public void testPaymentUsingDebitCard(){
        payment = PaymentMode.makePayment("Debit");
        payment.pay();
    }

    @Test
    public void testPaymentUsingCreditCard(){
        payment = PaymentMode.makePayment("Debit");
        payment.pay();
    }

    @Test
    public void testPaymentUsingUPI(){
        payment = PaymentMode.makePayment("UPI");
        payment.pay();
    }

}
