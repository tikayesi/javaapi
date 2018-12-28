package com.sti.dao.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sti.dao.api.TransactionDao;
import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Transaction;
import com.sti.dao.model.dto.CommonResponse;
import com.sti.dao.model.dto.TransactionDto;

@RestController
@RequestMapping("/transaction")
@SuppressWarnings("rawtypes")
public class TransactionController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private TransactionDao transactionDao;
	
	
	@GetMapping("/transget")
	public String transget(@RequestParam(value="id", defaultValue="") int id) {
		try {
			Transaction transaction = transactionDao.getById(id);
			if(transaction==null) {
				return "data tidak ditemukan";
			} else {
				return "hello " + transaction.getType()+"\n amount: " +transaction.getAmount()+
						"\n amount sign: " + transaction.getAmountSign()+ "\n Account id: "+
						transaction.getAccountId();
			}
		} catch (NumberFormatException e) {
			return "salah format input";
		} catch (Exception e) {
			return String.format("terjadi kesalahan sitem : %s", e.getMessage());
		}
	}
	
	@PostMapping("/transpost")
	public CommonResponse insert(@RequestBody TransactionDto transactionDto) throws ErrorException {
		try {
			Transaction transaction = modelMapper.map(transactionDto, Transaction.class);

			transaction.setIdTransaction(0);
			transaction = transactionDao.save(transaction);
			return new CommonResponse<TransactionDto>(modelMapper.map(transaction, TransactionDto.class));
		} catch (ErrorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@PutMapping("/transaput")
	public CommonResponse update(@RequestBody TransactionDto transactionDto) throws ErrorException {
		try {
			Transaction checkTransaction = transactionDao.getById(transactionDto.getIdTransaction());
			if(checkTransaction == null) {
				return new CommonResponse("14", "transaction tidak ditemukan");
			}
			
			if(transactionDto.getType()!=null) {
				checkTransaction.setType(transactionDto.getType());
			}
			if(transactionDto.getAmount()!=null) {
				checkTransaction.setAmount(transactionDto.getAmount());
			}
			if(transactionDto.getAmountSign()!=null) {
				checkTransaction.setAmountSign(transactionDto.getAmountSign());
			}
			if(transactionDto.getAccountId()!=null) {
				checkTransaction.setAccountId(transactionDto.getAccountId());
			}
			
			checkTransaction =  transactionDao.save(checkTransaction);
			
			return new CommonResponse<TransactionDto>(modelMapper.map(checkTransaction, TransactionDto.class));
		} catch (ErrorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
		}
	
	@DeleteMapping("/transdelet/{id}")
	public CommonResponse delete(@PathVariable("id") Integer transactionNumber) throws ErrorException {
		try {
			Transaction checkTransaction = transactionDao.getById(transactionNumber);
			if(checkTransaction == null) {
				return new CommonResponse("06", "transaction tidak ditemukan");
			}
			transactionDao.delete(checkTransaction);
			return new CommonResponse();
		} catch (ErrorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	
	@GetMapping("/translist")
	public List<Transaction> cobalist() throws ErrorException{
		return transactionDao.getList();
	}
	
	@GetMapping(value="/list")
	public CommonResponse getList(@RequestParam(name="transaction", defaultValue="") String transaction) throws ErrorException{
		try {
			LOGGER.info("transaction get list, params : {}", transaction);
			List<Transaction> transactions = transactionDao.getList();
		
			return new CommonResponse<List<TransactionDto>>(transactions.stream().map(cust -> modelMapper.map(cust, TransactionDto.class)).collect(Collectors.toList()));
		} catch (ErrorException e) {
			throw e;
		} catch(NumberFormatException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
}
