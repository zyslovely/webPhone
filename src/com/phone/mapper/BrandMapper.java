package com.phone.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.phone.meta.Brand;

/**
 * @author zhengyisheng E-mail:zhengyisheng@gmail.com
 * @version CreateTime：2013-4-17 下午07:59:50
 * @see Class Description
 */
public interface BrandMapper {
	/**
	 * 添加品牌
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brand
	 * @return
	 */
	public int addBrand(Brand brand);

	/**
	 * 品牌列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @param brandId
	 * @return
	 */
	public List<Brand> getBrandListByIds(
			@Param(value = "brandIdList") List<Long> brandIds);

	/**
	 * 得到所有品牌列表
	 * 
	 * @auther zyslovely@gmail.com
	 * @return
	 */
	public List<String> getAllBrandList();

	/**
	 * 通过品牌名称获取品牌ID
	 * 
	 * @param brand
	 * @return
	 */
	public long getBrandByBrand(@Param(value = "brand") String brand);
}
