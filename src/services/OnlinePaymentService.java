package services;

public interface OnlinePaymentService {
	double paymentFee(Double amout);
	double interest(Double amout, Integer months);
}
