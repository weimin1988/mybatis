package ${packageName}.service;
import java.util.*;
import lombok.Data;
import ${packageName}.entry.${className};


public interface ${className}Service {

	public Integer save(${className} ${name});
	public Integer updateIgnoreNull(${className} ${name});
	public Integer removeById(Integer id);
	public Integer removeByIds(String ids) ;
	public ${className} getById(Integer id);
	
	public List<${className}> listAll();
	public Integer countAll();
	public List<${className}> listPage(Integer page,Integer limit);
	public List<${className}> listBy${className}(${className} ${name}, Integer page,Integer limit);
	public Integer countBy${className}(${className} ${name});
}