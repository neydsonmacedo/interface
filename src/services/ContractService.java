package services;
import java.time.LocalDate;

import entities.Contract;
import entities.Installment;


public class ContractService {
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService() {
		
	}
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}

	public void processContract(Contract contract,Integer months) {
		double parcela = contract.getTotalValue() / months;
		for (int i = 1; i <= months;i++) {
			double amount = parcela + (parcela * 0.01 * i);
			double paymentFee = onlinePaymentService.paymentFee(amount);
			double interest = onlinePaymentService.interest(parcela,i);
			double amountInstallments = amount + paymentFee;
			contract.setInstallments(new Installment(contract.getDate().plusMonths(i),amountInstallments));
			
			amount = 0.0;
			
		}
	}
}
