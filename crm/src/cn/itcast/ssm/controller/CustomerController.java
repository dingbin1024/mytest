package cn.itcast.ssm.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.itcast.common.utils.PageBean;
import cn.itcast.ssm.pojo.BaseDict;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.QueryVo;
import cn.itcast.ssm.service.BaseDictService;
import cn.itcast.ssm.service.CustomerService;

@Controller
public class CustomerController {

	
	@Autowired
	private BaseDictService baseDictService;
	
	@Autowired
	private CustomerService  customerService;
	
	@Value("${baseDict_industry}")
	private String baseDict_industry;
	@Value("${baseDict_level}")
	private String baseDict_level;
	@Value("${baseDict_source}")
	private String baseDict_source;
	
	@RequestMapping("/list")
	public String list(QueryVo queryVo,Model model) throws Exception{
//		初始化查询条件 开始-----
//		客户行业   baseDict_industry=001
		List<BaseDict> industryList = baseDictService.findByTypeCode(baseDict_industry);
		model.addAttribute("industryList", industryList);
//		客户等级   baseDict_level=006
		List<BaseDict> levelList = baseDictService.findByTypeCode(baseDict_level);
		model.addAttribute("levelList", levelList);
//		客户来源 baseDict_source=002
		List<BaseDict> sourceList = baseDictService.findByTypeCode(baseDict_source);
		model.addAttribute("sourceList", sourceList);
//		初始化查询条件 结束-----
		
//		显示客户列表数据
//		初始化当前页码
		if(queryVo.getCurrentPage()==0){
			queryVo.setCurrentPage(1);
		}
//		初始化每页显示的条数
		if(queryVo.getPageSize()==0){
			queryVo.setPageSize(5);
		}
//		初始化每页查询的起始位置
		queryVo.setStartNum((queryVo.getCurrentPage()-1)*queryVo.getPageSize());
		
		if(StringUtils.isNotBlank(queryVo.getCustName())){
			String custName = new String(queryVo.getCustName().getBytes("iso8859-1"),"utf-8");
			queryVo.setCustName(custName);
		}
		PageBean<Customer> pageBean = customerService.findByPage(queryVo);
		model.addAttribute("page", pageBean);
		
//		数据回显
		model.addAttribute("custSource", queryVo.getCustSource());
		model.addAttribute("custLevel", queryVo.getCustLevel());
		model.addAttribute("custIndustry", queryVo.getCustIndustry());
		model.addAttribute("custName", queryVo.getCustName());
		
		return "customer";
	}
	
	
	@RequestMapping("/edit")
	@ResponseBody 
	public Customer edit(long id){
		Customer customer = customerService.findOne(id);
		return customer;
	}
	
	@RequestMapping("/update")
	@ResponseBody 
	public Boolean update(Customer customer){
		  try {
			customerService.update(customer);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@RequestMapping("/delete")
	@ResponseBody 
	public Boolean delete(long id){
		try {
			customerService.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
}
