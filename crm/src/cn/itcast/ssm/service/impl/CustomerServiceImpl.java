package cn.itcast.ssm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.itcast.common.utils.PageBean;
import cn.itcast.ssm.mapper.CustomerMapper;
import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.QueryVo;
import cn.itcast.ssm.service.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerMapper  customerMapper;
	@Override
	public PageBean<Customer> findByPage(QueryVo queryVo) {
		int totalCount = customerMapper.countTotal(queryVo);
		List<Customer> list = customerMapper.findByPage(queryVo);
		
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrentPage(queryVo.getCurrentPage());
		pageBean.setList(list);
		pageBean.setPageSize(queryVo.getPageSize());
		pageBean.setTotalCount(totalCount);
		return pageBean;
	}
	@Override
	public Customer findOne(long id) {
		
		return customerMapper.findById(id);
	}
	@Override
	public void update(Customer customer) {
		customerMapper.updateById(customer);
		
	}
	@Override
	public void deleteById(long id) {
		customerMapper.deleteById(id);
	}

}
