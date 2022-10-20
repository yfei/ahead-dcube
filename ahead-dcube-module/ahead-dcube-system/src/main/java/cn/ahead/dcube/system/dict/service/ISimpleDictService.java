package cn.ahead.dcube.system.dict.service;

import java.util.List;
import java.util.Map;

import cn.ahead.dcube.base.service.IService;
import cn.ahead.dcube.system.dict.dto.SimpleDictDTO;

public interface ISimpleDictService extends IService {

	/**
	 * 根据类型和pcode得到子列表
	 * 
	 * @param type
	 * @param pcode
	 * @param cascade 是否级联查询
	 * @return
	 */
	public List<SimpleDictDTO> listChildren(String type, String pcode, boolean cascade);

	/**
	 * 根据类型和pcode得到子列表,并进行扁平化
	 * 
	 * @param type
	 * @param pcode
	 * @return
	 */
	public Map<String, String> listFlatChildren(String type, String pcode);
	
	/**
	 * 查询所有的,并按照type分类
	 * @return
	 */
	public Map<String,List<SimpleDictDTO>> listByType();

}
