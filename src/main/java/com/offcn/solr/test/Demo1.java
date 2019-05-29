package com.offcn.solr.test;

import java.awt.event.ItemEvent;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.request.CollectionAdminRequest.Create;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.result.ScoredPage;

import com.ctc.wstx.util.StringUtil;
import com.offcn.pojo.TbGoods;
import com.offcn.pojo.TbGoods;

public class Demo1 {

	static ClassPathXmlApplicationContext app = new ClassPathXmlApplicationContext("classpath:solr.xml");
	static SolrTemplate template = (SolrTemplate) app.getBean("solrTemplate");
	
	public static void main(String[] args) {
		
//		save();
		
//		getData();
		
//		deleteData();
		
//		saveAll();
		
//		deleteAllData();
		
//		fenyefind();
		
		
//		tiaojiansearch();
		
		

	}
	
	
	// 添加数据
	public static void save(){
		TbGoods item = new TbGoods();
		item.setId(1l);
		item.setPrice(new BigDecimal(2000));
		item.setGoodsName("手机");
		// save
		template.saveBean(item);
		template.commit();
		
		System.out.println("ok");
	}
	
	public static void saveAll(){
		List<TbGoods> list = new ArrayList<TbGoods>();
		for(long i = 0; i < 100; i++){
			TbGoods item = new TbGoods();
			item.setId(i);
			item.setGoodsName("手机"+i);
			item.setPrice(new BigDecimal(2000+i));
			list.add(item);
		}
		// 添加多条数据
		template.saveBeans(list);
		template.commit();
	}
	// 根据id查询
	public static void getData(){
		TbGoods TbGoods = template.getById(1l, TbGoods.class);
		System.out.println(TbGoods.getGoodsName());
	}
	
	// 删除
	public static void deleteData(){
		template.deleteById("1");
		template.commit();
		System.out.println("ok");
	}
	
	
	// 分页查询
	
	public static void fenyefind(){
		Query query = new SimpleQuery("*:*");
		// 设置开始索引和每页的数据
		query.setOffset(0);
		query.setRows(20);
		
		// 开始分页查询
		ScoredPage<TbGoods> page = template.queryForPage(query, TbGoods.class);
		System.out.println(page.getTotalElements()+"总条目数");
		
		for(TbGoods item : page){
			System.out.println(item.getGoodsName());
		}
		
	}
	
	
	// 删除solr中的所有的数据
	public static void deleteAllData(){
		Query query = new SimpleQuery("*:*");
		template.delete(query);
		template.commit();
		System.out.println("删除所有数据");
	}
	
	public static void tiaojiansearch(){
		Query query = new SimpleQuery("sellerId:1");
//		Criteria criteria = new Criteria("title").contains("手机");
//		criteria.and("title").
//		query.addCriteria(criteria);
		
		// 设置分页
//		query.setOffset(0);
//		query.setRows(100);
		
		ScoredPage<TbGoods> list = template.queryForPage(query, TbGoods.class);
		
		for(TbGoods item : list){
			System.out.println(item.getGoodsName());
		}
		
	}
}






























