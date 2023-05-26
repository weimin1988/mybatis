package ${packageName}.mapper;
import ${packageName}.entry.${className};
import java.util.*;
import org.apache.ibatis.annotations.*;
public interface ${className}Mapper {
	@Select("SELECT * FROM ${tableName}  WHERE id=<#noparse>#{id}</#noparse>")
	public ${className} selectById(Integer id);

	@Delete("DELETE  FROM ${tableName} WHERE id=<#noparse>#{id}</#noparse>")
	public Integer deleteById(Integer id);
	
	@Delete("DELETE  FROM ${tableName} WHERE id in (<#noparse>${ids}</#noparse>)")
	public Integer deleteByIds(String ids);
	
	@Select("SELECT count(*) FROM ${tableName}")
	public Integer countAll();
	
	@Select("SELECT * FROM ${tableName} ORDER BY id DESC")
	public List<${className}> selectAll();
	@Select("SELECT * FROM ${tableName} ORDER by id DESC LIMIT <#noparse>#{start} ,#{limit}</#noparse> ")
	public List<${className}> selectPage(Integer start,Integer limit);
	
	public Integer updateIgnoreNull(${className} ${name});
	
	public Integer insertIgnoreNull(${className} ${name});

	public Integer countBy${className}(${className} ${name});
	public List<${className}> selectBy${className}(${className} ${name},Integer start,Integer limit);
	public Integer updateById(${className} ${name});
	public Integer insert(${className} ${name});
}