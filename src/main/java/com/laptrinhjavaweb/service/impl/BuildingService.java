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
import org.springframework.transaction.annotation.Transactional;

import com.laptrinhjavaweb.builder.BuildingSearchBuilder;
import com.laptrinhjavaweb.converter.BuildingConverter;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.entity.BuildingEntity;
import com.laptrinhjavaweb.entity.RentAreaEntity;
import com.laptrinhjavaweb.enums.BuildingTypeEnum;
import com.laptrinhjavaweb.repository.BuildingRepository;
import com.laptrinhjavaweb.service.IBuildingService;

@Service
public class BuildingService implements IBuildingService{
	@Autowired
	private  BuildingRepository buildingRepository;
	@Autowired
	private BuildingConverter buildingConverter;
	
	@Override
	public List<BuildingDTO> findAllCustom(Pageable pageable,String property, String order) {
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		List<Object> objects = buildingRepository.findAllCustom(pageable,property,order);
		for(Object item:objects) {
			Object[] items = (Object[]) item;
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setName(items[0]!=null ? items[0].toString() : null);
			buildingDTO.setWard(items[1]!=null ? items[1].toString() : null);
			buildingDTO.setStreet(items[2]!=null ? items[2].toString() : null);
			buildingDTO.setDistrict(items[3]!=null ? items[3].toString() : null);
			buildingDTO.setManagerName(items[4]!=null ? items[4].toString() : null);
			buildingDTO.setManagerPhone(items[5]!=null ? items[5].toString() : null);
			buildingDTO.setFloorArea(items[6]!=null ? Integer.parseInt(items[6].toString()) : null);
			buildingDTO.setRentCost(items[7]!=null ? items[7].toString() : null);
			buildingDTO.setServiceCost(items[8]!=null ? items[8].toString() : null);
			buildingDTO.setDeposit(items[9]!=null ? items[9].toString() : null);
			results.add(buildingDTO);
		}
	return results;
	}
	
	private Map<String, Object> convertToMapProperties(BuildingSearchBuilder builder) {
		Map<String, Object> properties = new HashMap<>();
		try {
			// Vào class Building SearchBuilder lấy field ra
			Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
			for (Field field : fields) {
				if (!field.getName().startsWith("rentArea") && !field.getName().equals("types")
						&& !field.getName().equals("staffId") && !field.getName().startsWith("rentCost")) {
					field.setAccessible(true);
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
	public List<BuildingDTO> search(BuildingSearchBuilder builder,Pageable pageable) {	
		List<BuildingDTO> results = new ArrayList<BuildingDTO>();
		Map<String,Object> params= convertToMapProperties(builder);
		List<Object> objects = buildingRepository.search(params, builder,pageable);
		for(Object item:objects) {
			Object[] items = (Object[]) item;
			BuildingDTO buildingDTO = new BuildingDTO();
			buildingDTO.setName(items[0].toString());
			buildingDTO.setWard(items[1].toString());
			buildingDTO.setStreet(items[2].toString());
			buildingDTO.setDistrict(items[3].toString());
			buildingDTO.setManagerName(items[4].toString());
			buildingDTO.setManagerPhone(items[5].toString());
			buildingDTO.setFloorArea(Integer.parseInt(items[6].toString()));
			buildingDTO.setRentCost(items[7].toString());
			buildingDTO.setServiceCost(items[8].toString());
			buildingDTO.setDeposit(items[9].toString());
			results.add(buildingDTO);
		}
	return results;
	}
	
	@Transactional
	public BuildingDTO save(BuildingDTO dto) {
		BuildingEntity buildingEntity=buildingConverter.convertDTOToEntity(dto);		
		//spring data jpa
		BuildingEntity newBuilding = buildingRepository.save(buildingEntity);
			List<RentAreaEntity> areaEntities = new ArrayList<RentAreaEntity>();
			for(String rentarea:dto.getRentArea()) {
				RentAreaEntity rentAreaEntity = new RentAreaEntity();
				rentAreaEntity.setValue(rentarea);
				rentAreaEntity.setBuilding(buildingEntity);
				areaEntities.add(rentAreaEntity);
			}
			newBuilding.getRentAreas().clear();
			newBuilding.getRentAreas().addAll(areaEntities);
		//Hàm save để lưu thông tin của rentArea vào bảng rentarea
		buildingRepository.save(newBuilding);
		return buildingConverter.convertEntityToDTO(newBuilding);	
	}
	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id:ids) {
			BuildingEntity buildingEntity=buildingRepository.findById(id).get();
			//delete with one to many (RentArea)
			if(buildingEntity.getRentAreas()!=null && buildingEntity.getRentAreas().size()>0) {
				buildingEntity.getRentAreas().remove(buildingEntity.getRentAreas().iterator().next());
			}
			//delete with many to many (AssignmentBuilding)
			if(buildingEntity.getStaffs()!=null && buildingEntity.getStaffs().size()>0) {
				buildingEntity.getStaffs().remove(buildingEntity.getStaffs().iterator().next());
			}
			buildingRepository.delete(buildingEntity);
		}
	}
	@Override
	public BuildingDTO findById(Long id) {		
		return buildingConverter.convertEntityToDTO(buildingRepository.findById(id).get());
	}

	@Override
	public int totalItem() {
		return(int)buildingRepository.count();
	}

	@Override
	public List<BuildingDTO> findAll(Pageable pageable, String role,Long staffid) {	
		List<BuildingDTO> listBuildings = new ArrayList<BuildingDTO>();
		if(role.equals("MANAGER")) {
			Page<BuildingEntity>buildings = buildingRepository.findAll(pageable);
			for(BuildingEntity building:buildings) {
				listBuildings.add(buildingConverter.convertEntityToDTO(building));
			}
		}
		else if(role.equals("STAFF")) {
			List<BuildingEntity> buildings = buildingRepository.findAllByStaff(pageable, staffid);
			for(BuildingEntity building:buildings) {
				listBuildings.add(buildingConverter.convertEntityToDTO(building));
			}
		}
		
		return listBuildings;
	}

	@Override
	public Map<String, String> getBuildingType() {
		Map<String, String> buildingTypes = new HashMap<String,String>();
		BuildingTypeEnum[] types = BuildingTypeEnum.values();
		for(BuildingTypeEnum type: types) {
			buildingTypes.put(type.toString(), type.getValue());
		}
		return buildingTypes;
	}
	
}

		