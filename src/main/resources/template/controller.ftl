package ${packageName}.controller;
import java.util.*;
import lombok.Data;
import ${packageName}.entry.*;
import ${packageName}.service.*;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;

@RestController
@RequestMapping("/${path}/${name}")
public class ${className}Controller {

	@Autowired
	${className}Service ${name}Service;
	@RequestMapping("/save")
	public Integer save( @Valid ${className} ${name}){
		return ${name}Service.save(${name});
	}

	@RequestMapping("/remove")
	public Integer removeById(Integer id){
		return ${name}Service.removeById(id);
	}

	@RequestMapping("/removes")
	public Integer removeByIds(String ids)  throws TypeException{
		return ${name}Service.removeByIds(ids);
	}

	@RequestMapping("/get")
	public ${className} get(Integer id){
		return ${name}Service.getById(id);
	}

	
	@RequestMapping("/listAll")
	public ResultListData listAll(){
		return ResultListData.builder().count(${name}Service.countAll()).list(${name}Service.listAll()).build();
	}

	@RequestMapping("/countAll")
	public Integer countAll(){
		return ${name}Service.countAll();
	}

	@RequestMapping("/list")
	public ResultListData listPage(Integer page,Integer limit){
		return ResultListData.builder().count(${name}Service.countAll()).list(${name}Service.listPage(page,limit)).build();
	}

	@RequestMapping("/listBy${className}")
	public ResultListData listBy${className}(${className} ${name},Integer page,Integer limit){
		return ResultListData.builder().count(${name}Service.countBy${className}(${name})).list(${name}Service.listBy${className}(${name},page,limit)).build();
	}

}