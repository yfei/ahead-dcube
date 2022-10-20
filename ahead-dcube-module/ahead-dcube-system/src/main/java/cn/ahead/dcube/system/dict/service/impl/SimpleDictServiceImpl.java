package cn.ahead.dcube.system.dict.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import cn.ahead.dcube.jpa.service.AbstractAheadService;
import cn.ahead.dcube.system.dict.dao.SimpleDictRepository;
import cn.ahead.dcube.system.dict.dto.SimpleDictDTO;
import cn.ahead.dcube.system.dict.entity.SimpleDictEntity;
import cn.ahead.dcube.system.dict.service.ISimpleDictService;

@Service
public class SimpleDictServiceImpl extends AbstractAheadService implements ISimpleDictService {

	@Autowired
	public SimpleDictRepository repository;

	/**
	 * 初始化加载
	 */
//	@PostConstruct
//	public void init() {
//		Map<String, String> flatChildren = this.listFlatChildren("PROJECT_TYPE", null);
//		flatChildren.forEach((k, v) -> {
//			System.out.println(k + ">>>" + v);
//		});
//	}

	@Override
	public List<SimpleDictDTO> listChildren(String type, String pcode, boolean cascade) {
		List<SimpleDictEntity> dicts = this.children(type, pcode);
		List<SimpleDictDTO> dictDtos = new ArrayList<>();
		dicts.forEach(d -> {
			SimpleDictDTO dto = d.convert();
			if (cascade) {
				List<SimpleDictDTO> children = this.listChildren(type, d.getCode(), cascade);
				dto.setChildren(children);
			}
			dictDtos.add(dto);
		});
		return dictDtos;
	}

	@Override
	public Map<String, String> listFlatChildren(String type, String pcode) {
		Map<String, String> kvMap = new HashMap<String, String>();
		List<SimpleDictEntity> dicts = this.children(type, pcode);
		for (SimpleDictEntity dict : dicts) {
			kvMap.put(dict.getCode(), dict.getName());
			// 查找子节点
			Map<String, String> childrenMap = this.listFlatChildren(type, dict.getCode());
			kvMap.putAll(childrenMap);
		}
		return kvMap;
	}

	private List<SimpleDictEntity> children(String type, String pcode) {
		Sort sort = Sort.by(Direction.ASC, "type").and(Sort.by(Direction.ASC, "sortNum"));
		// 查询条件
		Specification<SimpleDictEntity> spec = new Specification<SimpleDictEntity>() {
			@Override
			public Predicate toPredicate(Root<SimpleDictEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				predicateList.add(cb.equal(root.get("type"), type));
				if (StringUtils.hasLength(pcode)) {
					predicateList.add(cb.equal(root.get("parentCode"), pcode));
				} else {
					predicateList.add(cb.isNull(root.get("parentCode")));
				}
				return query.where(predicateList.toArray(new Predicate[predicateList.size()])).getRestriction();
			}
		};
		return repository.findAll(spec.and(this.normalSpec()), sort);
	}

	@Override
	public Map<String, List<SimpleDictDTO>> listByType() {
		Map<String, List<SimpleDictDTO>> dictMap = new HashMap<>();

		Sort sort = Sort.by(Direction.ASC, "type").and(Sort.by(Direction.ASC, "sortNum"));
		// 查询条件
		Specification<SimpleDictEntity> spec = new Specification<SimpleDictEntity>() {
			@Override
			public Predicate toPredicate(Root<SimpleDictEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<>();
				predicateList.add(cb.isNull(root.get("parentCode")));
				return query.where(predicateList.toArray(new Predicate[predicateList.size()])).getRestriction();
			}
		};
		List<SimpleDictEntity> dicts = repository.findAll(spec.and(this.normalSpec()), sort);

		dicts.forEach(d -> {
			List<SimpleDictDTO> dictsByType = new ArrayList<>();
			if (dictMap.containsKey(d.getType())) {
				dictsByType = dictMap.get(d.getType());
			} else {
				dictMap.put(d.getType(), dictsByType);
			}
			SimpleDictDTO dto = d.convert();
			// 查询子字典
			List<SimpleDictDTO> children = this.listChildren(d.getType(), d.getCode(), true);
			dto.setChildren(children);

			dictsByType.add(dto);

		});
		return dictMap;
	}

	private Specification<SimpleDictEntity> normalSpec() {
		return (root, query, cb) -> {
			return cb.or(cb.notEqual(root.get("status"), SimpleDictDTO.STATUS_UNUSE),cb.isNull(root.get("status")));
		};
	}
}
