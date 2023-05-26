package ${packageName}.entry;
import java.util.*;
import java.math.BigDecimal;
import cloud.yiyefu.common.Base;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.*;

@Data
public class ${className} extends Base{
	<#list fields as item>
			<#if item.nullable==false>
				<#if item.sqlType=="varchar"||item.sqlType=="char"||item.sqlType=="text">
					<#lt>	@NotBlank(message="${item.label}不能为空")
				<#else>
					<#lt>	@NotNull(message="${item.label}不能为空")
				</#if>
			</#if>
			<#if item.sqlType=="varchar"||item.sqlType=="char">
				<#lt>	@Size(max=${item.size},message="${item.label}最大长度为25")
				<#lt>	private String ${item.name};<#rt>
			<#elseif item.sqlType=="text">
				<#lt>	private String ${item.name};<#rt>
			<#elseif item.sqlType=="int">
				<#lt>	private Integer ${item.name};<#rt> 
			<#elseif item.sqlType=="float">
				<#lt>	private Float ${item.name};<#rt>
			<#elseif item.sqlType=="double">
				<#lt>	private Double ${item.name};<#rt>
			<#elseif item.sqlType=="date"||item.sqlType=="datetime">
				<#lt>	private Date ${item.name};<#rt>
			<#elseif item.sqlType=="decimal">
				<#lt>	private BigDecimal ${item.name};<#rt>
			</#if>
			<#lt>//${item.label}
	</#list>
	
}