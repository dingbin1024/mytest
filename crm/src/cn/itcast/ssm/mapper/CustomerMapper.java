package cn.itcast.ssm.mapper;

import java.util.List;

import cn.itcast.ssm.pojo.Customer;
import cn.itcast.ssm.pojo.QueryVo;

public interface CustomerMapper {
//	select count(*) from customer where cust_name like '%程序员%' and cust_source = '6' and cust_industry = '1' and cust_level='22'
//
//
//			select * from customer where cust_name like '%程序员%' and cust_source = '6' and cust_industry = '1' and cust_level='22'
//			LIMIT (currentPage-1)*pageSize,pageSize
	public int countTotal(QueryVo queryVo);
	
	public List<Customer> findByPage(QueryVo queryVo);

	public Customer findById(long id);

	public void updateById(Customer customer);

	public void deleteById(long id);
	
}
