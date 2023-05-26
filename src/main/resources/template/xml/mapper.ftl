<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${packageName}.mapper.${className}Mapper">
	<resultMap type="${packageName}.entry.${className}" id="BaseResultMap">
		<#list fields as field >
			<#if field.pk>
			<#lt>		<id column="${field.tableItemName}" property="${field.name}" />
			<#else>
			<#lt>		<result column="${field.tableItemName}" property="${field.name}" />
			</#if>
		</#list>
	</resultMap>
	<update id="updateIgnoreNull" parameterType="${packageName}.entry.${className}" >
		UPDATE  ${tableName} SET 
		<trim  suffixOverrides=",">
			<#list  fields as field >
				<#lt>			<if test="${field.name}!=null">
				<#lt>				${field.tableItemName}=<#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>,
				<#lt>			</if>
			</#list>
		</trim>
	 	WHERE id=<#noparse>#{id}</#noparse>
	</update>
	<insert id="insertIgnoreNull" parameterType="${packageName}.entry.${className}" useGeneratedKeys="true" keyProperty="id" >
		INSERT INTO  ${tableName} 
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list  fields as field >
				<#lt>			<if test="${field.name}!=null">
				<#lt>				${field.tableItemName},	
				<#lt>			</if>
			</#list>
		</trim>
		 VALUES 
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list  fields as field >
				<#lt>			<if test="${field.name}!=null">
				<#lt>				<#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>,	
				<#lt>			</if>
			</#list>
		</trim>
	</insert>
	<select id="countBy${className}" resultType="int" >
		SELECT count(*) FROM ${tableName} 
			 WHERE 1=1 
			<#list  fields as field >
				<#lt>			<if test="${field.name}!=null">
				<#lt>				AND ${field.tableItemName}=<#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>
				<#lt>			</if>
			</#list>
	</select>
	<select id="selectBy${className}" resultMap="BaseResultMap" >
		SELECT * FROM ${tableName}
			 WHERE 1=1 
			 <#list  fields as field >
			 	<#lt>			<if test="${name}.${field.name}!=null">
				<#lt>				AND ${field.tableItemName}=<#noparse>#{</#noparse>${name}.${field.name}<#noparse>}</#noparse>
				<#lt>			</if>
			 </#list>
			<if test="start!=null and limit!=null" >
				ORDER by id DESC  LIMIT <#noparse>#{start},#{limit}</#noparse>
			</if>
	</select>
	<update id="updateById" parameterType="${packageName}.entry.${className}" >
		UPDATE  ${tableName} SET 
		<trim  suffixOverrides=",">
			<#list  fields as field >
				<#lt>			${field.tableItemName}=<#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>,
			</#list>
		</trim>
	 	WHERE id=<#noparse>#{id}</#noparse>
	</update>
	<insert id="insert" parameterType="${packageName}.entry.${className}" useGeneratedKeys="true" keyProperty="id" >
		INSERT INTO  ${tableName} 
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list  fields as field >
				<#lt>			${field.tableItemName},	
			</#list>
		</trim>
		 VALUES 
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list  fields as field >
				<#lt>			<#noparse>#{</#noparse>${field.name}<#noparse>}</#noparse>,	
			</#list>
		</trim>
	</insert>
</mapper>