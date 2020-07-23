package com.laptrinhjavaweb.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.api.output.DealOutput;
import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.builder.CustomerSearchBuilder;
import com.laptrinhjavaweb.converter.CustomerConverter;
import com.laptrinhjavaweb.converter.DealConverter;
import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.dto.DealDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.entity.DealEntity;
import com.laptrinhjavaweb.enums.DealTypeEnum;
import com.laptrinhjavaweb.repository.CustomerRepository;
import com.laptrinhjavaweb.repository.DealRepository;
import com.laptrinhjavaweb.service.ICustomerService;
@Service
public class CustomerService implements ICustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerConverter customerConverter;
	
	@Autowired
	private DealRepository dealRepository;
	
	@Autowired
	private DealConverter dealConverter;
	
	@Override
	public List<CustomerDTO> findAll(Pageable pageable) {
		Page<CustomerEntity> entities = customerRepository.findAll(pageable);
		List<CustomerDTO> result = new ArrayList<CustomerDTO>();
		for(CustomerEntity entity:entities) {
			result.add(customerConverter.convertEntityToDTO(entity));
		}
		return result;
	}
	
	@Override
	public List<CustomerDTO> search(CustomerSearchBuilder builder,Pageable pageable) {
		Map<String,Object> params= convertToMapProperties(builder);
		List<CustomerEntity> customerEntities = customerRepository.findAll(params, builder, pageable);
		List<CustomerDTO> results = new ArrayList<CustomerDTO>();
		for(CustomerEntity customerEntity:customerEntities) {
			results.add(customerConverter.convertEntityToDTO(customerEntity));
		}
		return results;
	}
	private Map<String, Object> convertToMapProperties(CustomerSearchBuilder builder) {
		Map<String, Object> properties = new HashMap<>();
		try {
			// Vào class Building SearchBuilder lấy field ra
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
					if (field.get(builder) instanceof String) {
						properties.put(field.getName().toLowerCase(), field.get(builder));
					} else {
						// Do field là kiểu Object nên để dùng được StringUtils phải ép kiểu String
						if (field.get(builder) != null && StringUtils.isNotEmpty((String) field.get(builder))) {
							properties.put(field.getName().toLowerCase(),
									Integer.parseInt((String) field.get(builder)));
						}
					}
				}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
	@Override
	public CustomerDTO findById(Long customerId) {
		return customerConverter.convertEntityToDTO(customerRepository.findById(customerId).get());
		
	}

	@Override
	public CustomerDTO save(CustomerDTO userDTO) {
		CustomerEntity customerEntity = customerRepository.save(customerConverter.convertDTOToEntity(userDTO));
		return customerConverter.convertEntityToDTO(customerEntity);
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id:ids) {
			customerRepository.deleteById(id);
		}
	}

	@Override
	public DealOutput getDeals(Long customerId,Pageable pageable) {
		List<DealEntity> deals = customerRepository.findDealsByCustomer(customerId, pageable);
		List<DealDTO> results = new ArrayList<DealDTO>();
		DealOutput dealOutput = new DealOutput();
		for(DealEntity deal:deals) {
			results.add(dealConverter.convertEntityToDTO(deal));
		}
		List<DealDTO> customerService = new ArrayList<DealDTO>();
		List<DealDTO> leadToVisit = new ArrayList<DealDTO>();
		Map<String,String> dealType = new HashMap<String,String>();
		//Dựa vào code add vào list tương ứng
		for(DealDTO deal:results) {
			if(deal.getCode().equals(DealTypeEnum.CUSTOMER_SERVICE.toString())) {
				customerService.add(deal);
			}else if(deal.getCode().equals(DealTypeEnum.LEAD_CUSTOMER_TO_VISIT.toString())){
				leadToVisit.add(deal);
			}
		}
		dealOutput.setCustomerService(customerService);
		dealOutput.setLeadToVisit(leadToVisit);
		DealTypeEnum[] dealTypeEnum = DealTypeEnum.values();
		//Add DealTypeEnum vào 1 map để trả ra client
		for(DealTypeEnum deal : dealTypeEnum) {
			dealType.put(deal.toString(), deal.getValue());
		}
		dealOutput.setDealType(dealType);
		return dealOutput;
	}

	@Override
	public void saveDeals(List<DealDTO>customerService,List<DealDTO> leadToVisit) {
		if(customerService.size()!=0) {
			for(DealDTO deal:customerService) {
				dealRepository.save(dealConverter.convertDTOToEntity(deal));
			}
		}else if(leadToVisit.size()!=0) {
			for(DealDTO deal:leadToVisit) {
				dealRepository.save(dealConverter.convertDTOToEntity(deal));
			}
		}
	}

	@Override
	public void deleteDeals(Long[] dealIds) {
		for(Long id:dealIds) {
			dealRepository.deleteById(id);
		}
	}

	@Override
	public int totalItem() {
		return (int)customerRepository.count();
	}
}
