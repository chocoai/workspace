package com.yhcrt.weihu.bbs.dao.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Criteria;
import org.springframework.stereotype.Repository;

import com.yhcrt.weihu.bbs.Constants;
import com.yhcrt.weihu.bbs.dao.BbsTopicDao;
import com.yhcrt.weihu.bbs.entity.BbsTopic;
import com.yhcrt.weihu.bbs.entity.BbsTopicCountEnum;
import com.yhcrt.weihu.common.hibernate3.Finder;
import com.yhcrt.weihu.common.hibernate3.HibernateBaseDao;
import com.yhcrt.weihu.common.page.Page;
import com.yhcrt.weihu.common.page.Pagination;

@Repository
public class BbsTopicDaoImpl extends HibernateBaseDao<BbsTopic, Integer>
		implements BbsTopicDao {

	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsTopic> getFriendDynamic(List<Integer> friendIds, Integer pageSize, Integer pageNo) {
		Page<BbsTopic> page = new Page<BbsTopic>(pageNo,pageSize);
		List<BbsTopic> list = new ArrayList<BbsTopic>();
		if(pageSize == null){
			page.setPageSize(20);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsTopic t where t.creater.id in (:friendIds) and t.status>=0 order by t.createTime desc ";
		String hqlCount = "select count(*) from BbsTopic t where t.creater.id in (:friendIds) and t.status>=0 ";
		Object obj = getSession().createQuery(hqlCount).setParameterList("friendIds", friendIds).uniqueResult();
		if(obj == null){
			page.setList(list);
			page.setTotalCount(0);
			return page;
		}else{
			page.setTotalCount(((Number)obj).intValue());
		}
		list = getSession().createQuery(hql).setParameterList("friendIds", friendIds).setFirstResult((page.getPageNo()-1)*page.getPageSize())
				.setMaxResults(page.getPageNo()*page.getPageSize()).setCacheable(false).list();
		page.setList(list);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<BbsTopic> getPage(Integer userId, Integer pageSize, Integer pageNo) {
		Page<BbsTopic> page = new Page<BbsTopic>(pageNo,pageSize);
		List<BbsTopic> list = new ArrayList<BbsTopic>();
		if(pageSize == null){
			page.setPageSize(Page.SIZE);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String hql = "from BbsTopic t where t.creater.id="+userId+" and t.status>=0 ";
		String hqlCount = "select count(*) from BbsTopic t where t.creater.id="+userId+" and t.status>=0 ";
		Object obj = getSession().createQuery(hqlCount).uniqueResult();
		if(obj == null){
			page.setList(list);
			page.setTotalCount(0);
			return page;
		}else{
			page.setTotalCount(((Number)obj).intValue());
		}
		list = getSession().createQuery(hql).setFirstResult((page.getPageNo()-1)*page.getPageSize())
				.setMaxResults(page.getPageNo()*page.getPageSize()).setCacheable(false).list();
		page.setList(list);
		return page;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BbsTopic> getIndexNewsList(Integer size) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM BbsTopic t "
				+ "WHERE DATE(t.createTime)=CURDATE() and t.titleImg<>'' AND t.status=0 and t.postType.id<>"+Constants.ACTIVE_TYPES
				+ " ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ");
		List<BbsTopic> list = getSession().createQuery(hql.toString()).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
		//判断，如果不够，则查询一周的   不含今天的
		if(list == null || list.size()<size){
			String hqlWeek = "FROM BbsTopic t "
					+ "WHERE WEEK(t.createTime)=WEEK(NOW()) AND DATE(t.createTime)<>CURDATE() and t.titleImg<>''  AND t.status=0 and t.postType.id<>"+Constants.ACTIVE_TYPES
					+ " ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ";
			List<BbsTopic> listWeek = getSession().createQuery(hqlWeek).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
			if(listWeek != null){
				for(int i=0;i<listWeek.size(); i++){
					list.add(listWeek.get(i));
					if(list.size()>=size){
						break;
					}
				}
			}
		}
		//继续判断，若一周的不够，则查询一个月的     不含本周的
		if(list == null || list.size()<size){
			String hqlMonth = "FROM BbsTopic t "
					+ "WHERE MONTH(t.createTime)=MONTH(NOW()) AND WEEK(t.createTime)<>WEEK(NOW()) and t.titleImg<>''  AND t.status=0 and t.postType.id<>"+Constants.ACTIVE_TYPES
					+ " ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ";
			List<BbsTopic> listMonth = getSession().createQuery(hqlMonth).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
			if(listMonth != null){
				for(int i=0;i<listMonth.size(); i++){
					list.add(listMonth.get(i));
					if(list.size()>=size){
						break;
					}
				}
			}
		}
		//继续判断，若一个月的不够，则查询所有的   不含本月的
		if(list == null || list.size()<size){
			String hqlAll = "FROM BbsTopic t "
					+ "WHERE MONTH(t.createTime)<>MONTH(NOW()) and t.titleImg<>''   AND t.status=0 and t.postType.id<>"+Constants.ACTIVE_TYPES
					+ " ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ";
			List<BbsTopic> listAll = getSession().createQuery(hqlAll).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
			if(listAll != null){
				for(int i=0;i<listAll.size(); i++){
					list.add(listAll.get(i));
					if(list.size()>=size){
						break;
					}
				}
			}
		}
		//要是还是不够，就不管了，照样返回
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Page<BbsTopic> getPage(Integer pageSize,Integer pageNo,Integer forumId,Integer siteId,String type,String typeId){
		Page<BbsTopic> page = new Page<BbsTopic>(pageNo,pageSize);
		List<BbsTopic> list = new ArrayList<BbsTopic>();
		if(pageSize == null){
			page.setPageSize(Page.SIZE);
		}
		if(pageNo == null){
			page.setPageNo(0);
		}
		String sql = "SELECT count(*) FROM bbs_topic t WHERE SITE_ID="+siteId+" AND STATUS=0 AND FORUM_ID="+forumId;
		StringBuffer hql = new StringBuffer();
		hql.append("FROM BbsTopic t where t.site.id="+siteId);
		hql.append(" and t.status=0 and t.forum.id="+forumId);
		if(type.equals("index")){
			
		}else if(type.equals("index_jing")){
			hql.append(" and t.PRIME_LEVEL<>0 ");
			sql = sql + " and t.PRIME_LEVEL<>0 ";
		}else if(type.equals("index_active") && typeId!=null && !typeId.equals("")){
			hql.append(" and t.postType.id="+typeId+" ");
			sql = sql + " and t.postType.id="+typeId;
		}
		hql.append(" order by t.createTime desc ");
		Object obj = getSession().createSQLQuery(sql).uniqueResult();
		if(obj == null){
			page.setList(list);
			page.setTotalCount(0);
			return page;
		}else{
			page.setTotalCount(((Number)obj).intValue());
		}
		list = getSession().createQuery(hql.toString()).setFirstResult((page.getPageNo()-1)*page.getPageSize())
				.setMaxResults(page.getPageNo()*page.getPageSize()).setCacheable(false).list();
		page.setList(list);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsTopic> getTopicIndexActive(Integer size, Integer forumId,Boolean isImage) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM BbsTopic t "
				+ " WHERE t.forum.id="+Constants.ACTIVE_INDEX_FORUM+" and t.postType.id="+Constants.ACTIVE_TYPES+" AND t.status=0 "
				+ " ORDER BY t.createTime DESC ");
		List<BbsTopic> list = getSession().createQuery(hql.toString()).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsTopic> getTopicIndex(Integer size) {
		StringBuffer hql = new StringBuffer();
		hql.append("FROM BbsTopic t "
				+ "WHERE t.forum.id="+Constants.INDEX_TOPIC_FORUM+" and DATE(t.createTime)=CURDATE() and t.titleImg='' AND t.status=0 "
				+ "ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ");
		List<BbsTopic> list = getSession().createQuery(hql.toString()).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
		//判断，如果不够，则查询一周的   不含今天的
		if(list == null || list.size()<size){
			String hqlWeek = "FROM BbsTopic t "
					+ "WHERE t.forum.id="+Constants.INDEX_TOPIC_FORUM+" and WEEK(t.createTime)=WEEK(NOW()) AND DATE(t.createTime)<>CURDATE() and t.titleImg=''  AND t.status=0 "
					+ "ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ";
			List<BbsTopic> listWeek = getSession().createQuery(hqlWeek).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
			if(listWeek != null){
				for(int i=0;i<listWeek.size(); i++){
					list.add(listWeek.get(i));
					if(list.size()>=size){
						break;
					}
				}
			}
		}
		//继续判断，若一周的不够，则查询一个月的     不含本周的
		if(list == null || list.size()<size){
			String hqlMonth = "FROM BbsTopic t "
					+ "WHERE t.forum.id="+Constants.INDEX_TOPIC_FORUM+" and MONTH(t.createTime)=MONTH(NOW()) AND WEEK(t.createTime)<>WEEK(NOW()) and t.titleImg=''  AND t.status=0 "
					+ "ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ";
			List<BbsTopic> listMonth = getSession().createQuery(hqlMonth).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
			if(listMonth != null){
				for(int i=0;i<listMonth.size(); i++){
					list.add(listMonth.get(i));
					if(list.size()>=size){
						break;
					}
				}
			}
		}
		//继续判断，若一个月的不够，则查询所有的   不含本月的
		if(list == null || list.size()<size){
			String hqlAll = "FROM BbsTopic t "
					+ "WHERE t.forum.id="+Constants.INDEX_TOPIC_FORUM+" and MONTH(t.createTime)<>MONTH(NOW()) and t.titleImg=''   AND t.status=0 "
					+ "ORDER BY t.topLevel DESC ,t.primeLevel DESC,t.viewCount DESC ,t.replyCount DESC,t.createTime DESC ";
			List<BbsTopic> listAll = getSession().createQuery(hqlAll).setFirstResult(0).setMaxResults(size).setCacheable(false).list();
			if(listAll != null){
				for(int i=0;i<listAll.size(); i++){
					list.add(listAll.get(i));
					if(list.size()>=size){
						break;
					}
				}
			}
		}
		//要是还是不够，就不管了，照样返回
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsTopic> getTopTopic(Integer webId, Integer ctgId,
			Integer forumId) {
		Finder f = Finder.create("from BbsTopic bean where 1=1");
		f.append(" and bean.website.id=:webId").setParam("webId", webId);
		f.append(" and bean.status>=").append(String.valueOf(BbsTopic.NORMAL));
		f.append(" and (bean.topLevel=3 ");
		f.append(" or (bean.topLevel=2 and bean.category.id=:ctgId)");
		f.setParam("ctgId", ctgId);
		f.append(" or (bean.topLevel=1 and bean.forum.id=:forumId))");
		f.setParam("forumId", forumId);
		f.append(" order by bean.topLevel desc");
		return find(f);
	}
	
	/**
	 * @topic_page 标签所执行的查询方法
	 */
	public Pagination getForTag(Integer siteId, Integer forumId,Integer parentPostTypeId, Integer postTypeId, Short status,
			Short primeLevel, String keyWords, String creater,
			Integer createrId, Short topLevel, int pageNo, int pageSize,String jinghua) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (forumId != null) {
			f.append(" and bean.forum.id=:forumId")
					.setParam("forumId", forumId);
		}
		if(parentPostTypeId!=null){
			f.append(" and bean.postType.parent.id=:parentPostTypeId")
			.setParam("parentPostTypeId", parentPostTypeId);
		}
		if(postTypeId!=null){
			f.append(" and bean.postType.id=:postTypeId")
			.setParam("postTypeId", postTypeId);
		}
		if (status == null) {
			status = 0;
		}
		f.append(" and bean.status>=:status").setParam("status", status);
		if (topLevel != null) {
			if (topLevel != 0) {
				f.append(" and bean.topLevel>=:topLevel").setParam("topLevel",
						topLevel);
			} else {
				f.append(" and bean.topLevel=0");
			}
		}
		if (primeLevel != null) {
			f.append(" and bean.primeLevel >=:primeLevel").setParam(
					"primeLevel", primeLevel);
		}
		if (!StringUtils.isBlank(keyWords)) {
			f.append(" and bean.topicText.title like :keyWords").setParam("keyWords",
					"%" + keyWords + "%");
		}
		if (!StringUtils.isBlank(creater)) {
			f.append(" and bean.creater.username like :creater").setParam(
					"creater", "%" + creater + "%");
		}
		if (createrId != null) {
			f.append(" and bean.creater.id =:createrId").setParam("createrId",
					createrId);
		}
		if("index_jing".equals(jinghua)){
			f.append(" and bean.primeLevel != 0");
		}
		f.append(" order by bean.topLevel desc,bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getForSearchDate(Integer siteId, Integer forumId,
			Short primeLevel, Integer day, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		if (forumId != null) {
			f.append(" and bean.forum.id=:forumId")
					.setParam("forumId", forumId);
		}
		f.append(" and bean.status>=0");
		if (day != null) {
			Calendar now = Calendar.getInstance();
			now.setTime(new Date(System.currentTimeMillis()));
			now.add(Calendar.DATE, -day);
			f.append(" and bean.createTime>:day")
					.setParam("day", now.getTime());
		}
		if (primeLevel != null) {
			f.append(" and bean.primeLevel >=:primeLevel").setParam(
					"primeLevel", primeLevel);
		}
		f.append(" order by bean.topLevel desc,bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getTopicByTime(Integer siteId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" order by bean.lastTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getMemberTopic(Integer siteId, Integer userId,
			int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" and bean.creater.id=:userId");
		f.setParam("userId", userId);
		f.append(" order by bean.lastTime desc");
		return find(f, pageNo, pageSize);
	}

	public Pagination getMemberReply(Integer siteId, Integer userId,
			int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" and bean.haveReply like :userId").setParam("userId",
				"%," + userId + ",%");
		f.append(" order by bean.sortTime desc");
		return find(f, pageNo, pageSize);
	}
	
	public List<BbsTopic> getMemberReply(Integer siteId, Integer userId,
			Integer first,Integer count) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1 ");
		f.append(" and bean.site.id=:siteId").setParam("siteId", siteId);
		f.append(" and bean.haveReply like :userId").setParam("userId",
				"%," + userId + ",%");
		f.append(" order by bean.sortTime desc");
		if(first==null){
			first=0;
		}
		f.setFirstResult(first);
		f.setMaxResults(count);
		return find(f);
	}

	public Pagination getPage(int pageNo, int pageSize) {
		Criteria crit = createCriteria();
		Pagination page = findByCriteria(crit, pageNo, pageSize);
		return page;
	}

	public BbsTopic findById(Integer id) {
		BbsTopic entity = get(id);
		return entity;
	}

	public BbsTopic save(BbsTopic bean) {
		getSession().save(bean);
		return bean;
	}

	public BbsTopic deleteById(Integer id) {
		BbsTopic entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<BbsTopic> getEntityClass() {
		return BbsTopic.class;
	}

	@SuppressWarnings("unchecked")
	public List<BbsTopic> getList(Integer forumId,String keywords,Integer userId,Integer first,Integer count) {
		String hql = "from BbsTopic bean where 1=1 ";
		Finder f=Finder.create(hql);
		if(forumId!=null){
			f.append(" and bean.forum.id=:forumId").setParam("forumId", forumId);
		}
		if(StringUtils.isNotBlank(keywords)){
			f.append(" and bean.topicText.title like :keyWords").setParam("keyWords",
					"%" + keywords + "%");
		}
		if(userId!=null){
			f.append(" and bean.creater.id=:userId").setParam("userId", userId);
		}
		f.append(" order by bean.id desc");
		f.setCacheable(false);
		f.setFirstResult(first);
		f.setMaxResults(count);
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsTopic> getNewList(Integer first,Integer count,Integer orderby) {
		String hql = "from BbsTopic bean ";
		if(orderby==null){
			hql+="order by bean.createTime desc";
		}
		if(orderby.equals(1)){
			hql+="order by bean.createTime desc";
		}else if(orderby.equals(2)){
			hql+="order by bean.viewsDay desc,bean.createTime desc";
		}else if(orderby.equals(3)){
			hql+="order by bean.viewsWeek desc,bean.createTime desc";
		}else if(orderby.equals(4)){
			hql+="order by bean.viewsMonth desc,bean.createTime desc";
		}else if(orderby.equals(5)){
			hql+="order by bean.viewCount desc,bean.createTime desc";
		}else if(orderby.equals(6)){
			hql+="order by bean.replyCount desc,bean.createTime desc";
		}else if(orderby.equals(7)){
			hql+="order by bean.replyCountDay desc,bean.createTime desc";
		}else if(orderby.equals(8)){
			hql+="order by bean.id desc";
		}
		return getSession().createQuery(hql).setFirstResult(first).setMaxResults(count).setCacheable(false).list();
	}
	
	public List<BbsTopic> getTopList(Short topLevel,Integer count,Integer orderby){
		String hql = "from BbsTopic bean ";
		Finder f=Finder.create(hql);
		if(topLevel!=null){
			f.append(" where bean.topLevel>=:topLevel").setParam("topLevel", topLevel);
		}
		if(orderby==null){
			f.append(" order by bean.topLevel desc");
		}
		if(orderby.equals(1)){
			f.append(" order by bean.topLevel desc");
		}else if(orderby.equals(2)){
			f.append(" order by bean.createTime desc");
		}else{
			f.append(" order by bean.id desc");
		}
		f.setMaxResults(count);
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<BbsTopic> getTopicList(Integer userId,Integer bigId,Integer smallId,Integer count) {
		Finder f = Finder.create("select bean from BbsTopic bean where 1=1");
		if(bigId!=null){
			f.append(" and bean.id>:bigId");
			f.setParam("bigId", bigId);
		}else if(smallId!=null){
			f.append(" and bean.id<:smallId");
			f.setParam("smallId",smallId);
		}
		if(userId!=null){
			f.append(" and bean.creater.id=:userId");
			f.setParam("userId", userId);
		}
		if(bigId!=null){
			f.append(" order by bean.id asc");
		}else{
			f.append(" order by bean.id desc");
		}
		if(count!=null){
			f.setMaxResults(count);
		}
		return find(f);
	}
	
	
	
	public List<BbsTopic> getTopicList(){
		String hql = "from BbsTopic bean order by bean.createTime desc";
		return getSession().createQuery(hql).setFirstResult(0).setCacheable(false).list();
	}
	
	
	
	public void updateAllTopicCount(BbsTopicCountEnum e){
		String hql="";
		String updateReplyCountDaySql="";
		if(e==null){
			hql = "update BbsTopic bean set bean.viewsDay=0";
		}
		if(e.equals(BbsTopicCountEnum.day)){
			hql = "update BbsTopic bean set bean.viewsDay=0";
			updateReplyCountDaySql="update BbsTopic bean set bean.replyCountDay=0";
		}else if(e.equals(BbsTopicCountEnum.week)){
			hql = "update BbsTopic bean set bean.viewsWeek=0";
		}else if(e.equals(BbsTopicCountEnum.month)){
			hql = "update BbsTopic bean set bean.viewsMonth=0";
		}
		if(StringUtils.isNotBlank(hql)){
			getSession().createQuery(hql).executeUpdate();
		}
		if(StringUtils.isNotBlank(updateReplyCountDaySql)){
			getSession().createQuery(updateReplyCountDaySql).executeUpdate();
		}
	}

}