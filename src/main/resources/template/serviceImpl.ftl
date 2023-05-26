package ${packageName}.service.impl;
import java.util.*;
import lombok.Data;
import clound.yiyefu.common.Util;
import ${packageName}.entry.*;
import ${packageName}.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${packageName}.service.*;
import clound.yiyefu.exception.*;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ${className}ServiceImpl implements ${className}Service{
	@Autowired
	${className}Mapper ${name}Mapper;
	
	@Override
	@Transactional
	public Integer save(${className} ${name}){
		Integer result = -1;
		Boolean isNew = false;
		Integer id = ${name}.getId();
		Util.setBaseEntry(${name});
		if (id == null) {
			isNew = true;
			result = ${name}Mapper.insertIgnoreNull(${name});
			id = ${name}.getId();
		} else {
			result = ${name}Mapper.updateIgnoreNull(${name});
		}
		if (result < 1) {
			return -1;
		}

		return result;
	}
	@Override
	@Transactional
	public Integer updateIgnoreNull(${className} ${name}){
		return ${name}Mapper.updateIgnoreNull(${name});
	}
	
	@Override
	@Transactional
	public Integer removeById(Integer id) {
		return ${name}Mapper.deleteById(id);
	}

	@Override
	@Transactional
	public Integer removeByIds(String ids) throws TypeException{
		String idNum=ids.replace(",","");
		if(!Util.onlyNumber(idNum)){
			throw new TypeException("传入的id必须为数字!");
		}
		return ${name}Mapper.deleteByIds(ids);
	}

	@Override
	public ${className} getById(Integer id) {
		return ${name}Mapper.selectById(id);
	}

	@Override
	public List<${className}> listAll() {
		return ${name}Mapper.selectAll();
	}

	@Override
	public Integer countAll() {
		return ${name}Mapper.countAll();
	}

	@Override
	public List<${className}> listPage(Integer page, Integer limit) {
		return ${name}Mapper.selectPage((page - 1) * limit, limit);
	}

	@Override
	public List<${className}> listBy${className}(${className} ${name}, Integer page, Integer limit) {
		return ${name}Mapper.selectBy${className}(${name}, (page - 1) * limit, limit);
	}

	@Override
	public Integer countBy${className}(${className} ${name}) {
		return ${name}Mapper.countBy${className}(${name});
	}

}