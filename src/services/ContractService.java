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
			double interest = onlinePaymentService.interest(parcela,i);
			double paymentFee = onlinePaymentService.paymentFee(parcela + interest);			
			double amount = parcela + interest + paymentFee;
			contract.setInstallments(new Installment(contract.getDate().plusMonths(i),amount));
			
			amount = 0.0;
			
		}
	}
}
