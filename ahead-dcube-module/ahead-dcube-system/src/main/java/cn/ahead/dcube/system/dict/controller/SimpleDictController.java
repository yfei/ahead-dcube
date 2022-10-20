package cn.ahead.dcube.system.dict.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.ahead.dcube.base.response.Response;
import cn.ahead.dcube.log.annotation.Log;
import cn.ahead.dcube.log.enums.OperType;
import cn.ahead.dcube.system.dict.dto.SimpleDictDTO;
import cn.ahead.dcube.system.dict.service.ISimpleDictService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dict/simple")
@Slf4j
public class SimpleDictController {

	@Autowired
	private ISimpleDictService dictService;

	/**
	 * 获取字典
	 * @param request
	 * @param types
	 * @return
	 */
	@PostMapping(value = "/list/types")
	@ResponseBody
	@Log(module = "数据字典", function = "数据字典查询", details = true, oper = OperType.LIST)
	public Response report(HttpServletRequest request) {
		log.debug("获取数据字典....");
		Map<String, List<SimpleDictDTO>> dicts = dictService.listByType();
		return Response.success(dicts);
	}

}
