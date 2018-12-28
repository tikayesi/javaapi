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

import com.sti.dao.api.AccountDao;
import com.sti.dao.exception.ErrorException;
import com.sti.dao.model.Account;
import com.sti.dao.model.dto.AccountDto;
import com.sti.dao.model.dto.CommonResponse;

@RestController
@RequestMapping("/account")
@SuppressWarnings("rawtypes")
public class AccountController {
	
private static final Logger LOGGER = LoggerFactory.getLogger(AccountController.class);
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private AccountDao accountDao;
	

	@GetMapping(value="/{id}")
	public CommonResponse getById(@PathVariable("id") String accountNumber) throws ErrorException {
		LOGGER.info("accountNumber : {}", accountNumber);
		try {
			Account account = accountDao.getById(Integer.parseInt(accountNumber));
			
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (ErrorException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("01", e.getMessage());
		} catch (NumberFormatException e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", "parameter harus angka");

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@PostMapping("/inipost")
	public CommonResponse insert(@RequestBody AccountDto accountDto) throws ErrorException {
		try {
			Account account = modelMapper.map(accountDto, Account.class);

			account.setAccountNumber(0);
			account = accountDao.save(account);
			return new CommonResponse<AccountDto>(modelMapper.map(account, AccountDto.class));
		} catch (ErrorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
	}
	
	@PutMapping("/iniput")
	public CommonResponse update(@RequestBody AccountDto accountDto) throws ErrorException {
		try {
			Account checkAccount = accountDao.getById(accountDto.getAccountNumber());
			if(checkAccount == null) {
				return new CommonResponse("14", "account tidak ditemukan");
			}
			
			if(accountDto.getOpenDate()!=null) {
				checkAccount.setOpenDate(accountDto.getOpenDate());
			}
			if(accountDto.getBalance()!=0) {
				checkAccount.setBalance(accountDto.getBalance());
			}
			if(accountDto.getCustomerId()!=null) {
				checkAccount.setCustomerId(accountDto.getCustomerId());
			}
			
			checkAccount =  accountDao.save(checkAccount);
			
			return new CommonResponse<AccountDto>(modelMapper.map(checkAccount, AccountDto.class));
		} catch (ErrorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}
		}
	
	@DeleteMapping("/inidelet/{id}")
	public CommonResponse delete(@PathVariable("id") Integer accountNumber) throws ErrorException {
		try {
			Account checkAccount = accountDao.getById(accountNumber);
			if(checkAccount == null) {
				return new CommonResponse("06", "account tidak ditemukan");
			}
			accountDao.delete(checkAccount);
			return new CommonResponse();
		} catch (ErrorException e) {
			return new CommonResponse("01", e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new CommonResponse("06", e.getMessage());
		}

	}
	
	
	@GetMapping("/inilist")
	public List<Account> inigetlist() throws ErrorException{
		return accountDao.getList();
	}
	
	@GetMapping(value="/list")
	public CommonResponse getList(@RequestParam(name="account", defaultValue="") String account) throws ErrorException{
		try {
			LOGGER.info("account get list, params : {}", account);
			List<Account> accounts = accountDao.getList();
		
			return new CommonResponse<List<AccountDto>>(accounts.stream().map(cust -> modelMapper.map(cust, AccountDto.class)).collect(Collectors.toList()));
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
