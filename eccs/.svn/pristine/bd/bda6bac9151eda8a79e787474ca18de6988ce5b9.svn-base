
package com.smart.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.smart.model.Backlog;
import com.smart.model.Project;
import com.smart.model.User;
import com.smart.util.Page;
import com.smart.util.StringUtil;
import com.smart.dao.BacklogDao;

/**
 * BacklogService. @author 王金辉 在修改待办事项的状态的时候，由于存在转交的情况，所以在表里面存的数据也是多条
 * 及每转交一次，就会增加一条状态为3（转交状态）的数据
 * 所以在处理的为完成的时候，只将状态为1（没有任何操作的默认状态）的数据的状态设置为完成状态4（完成状态）
 * 删除必须在设置为完成状态后再删除，删除后的状态为-1 如果先删除，那么设置为完成状态的时候是查不到数据的
 */
@Service
public class BacklogService {

	@Autowired
	private BacklogDao backlogDao;

	@Autowired
	private UserService userService;

	public List<Backlog> getList(Integer projectId, Integer status,
			Integer step, Integer userId) {
		return backlogDao.getList(projectId, status, step, userId);
	}

	/**
	 * 对任务的完成情况，进行修改待办事项的状态
	 * 
	 * @param projectId
	 *            项目ID
	 * @param userId
	 *            用户ID
	 */
	public void updateStatus(Integer projectId, Integer userId, Integer status,
			Integer projectStep) {
		if (projectId == null || userId == null) {
			return;
		}
		backlogDao.updateToFinished(projectId, userId, status, projectStep);
	}

	/**
	 * 由于之前别人写的代码有漏洞，所以增加的一个方法，防止在任务转交时转交人为自己时，那么待办事项不新建，只是将该待办事项的创建时间进行更新，
	 * 以后如果转交的地方做了限定：即补不能转交给自己时，该方法也就可以删除了
	 * 
	 * @param projectId
	 *            项目ID
	 * @param userId
	 *            当前用户ID
	 */
	public void updateByProjectIdAndUserIdAndStep(Integer projectId,
			Integer userId, Integer step) {
		if (projectId == null || userId == null || step == null) {
			return;
		}
		Backlog backlog = backlogDao
				.getOneByProjectIdAndUserIdAndStep(projectId, userId, step);
		if (backlog == null) {
			return;
		}
		backlog.setCtime(new Date());
		update(backlog);
	}

	/**
	 * 
	 * @param projectId
	 * @param userId
	 * @return
	 */
	public boolean haveUnfinishedBacklog(Integer projectId, Integer userId,
			Integer step) {
		if (projectId == null || userId == null) {
			return false;
		}
		Backlog backlog = backlogDao
				.getOneByProjectIdAndUserIdAndStep(projectId, userId, step);
		return backlog == null ? false : true;
	}

	/**
	 * 删除待办事项 此方法的用在任务转交时，由于一个任务可能有多个人有权限去做，所以只删除当前用户的待办事项，其他的不删除
	 * 之前代码的漏洞：该用户待办事项删除后，他对任务的操作权限并没有删除，（由于给的时间短了，所以此漏洞暂时不作处理）
	 * 
	 * @param projectId
	 *            项目ID
	 * @param userId
	 *            当前用户ID
	 */
	public void deleteByProjectIdAndUserIdAndStep(Integer projectId,
			Integer userId, Integer step) {
		if (projectId == null || userId == null) {
			return;
		}
		backlogDao.deleteByProjectIdAndUserIdAndStep(projectId, userId, step);
	}

	/**
	 * 删除待办事项 当任务进行到下一个环节时，上一个环节的待办事项全部删除
	 * 
	 * @param projectId
	 *            项目ID
	 */
	public void deleteByProjectIdAndStep(Integer projectId,
			Integer projectStep) {
		if (projectId == null || projectStep == null) {
			return;
		}
		backlogDao.deleteByProjectIdAndStep(projectId, projectStep);
	}

	public void save(Project project, String name, String type, String remark,
			String path, Integer[] userIds, Integer projectStep) {
		if (StringUtil.isBlank(path) || project == null || userIds == null
				|| userIds.length == 0 || StringUtil.isBlank(name)) {
			return;
		}
		List<User> userList = new ArrayList<User>();
		for (int i = 0; i < userIds.length; i++) {
			if (userIds[i] == null) {
				continue;
			}
			userList.add(userService.get(userIds[i]));
		}
		for (int i = 0; i < userList.size(); i++) {
			Backlog backlog = new Backlog();
			backlog.setProject(project);
			backlog.setName(name);
			backlog.setType(type);
			backlog.setRemark(remark);
			backlog.setPath(path);
			backlog.setToUser(userList.get(i));
			backlog.setProjectStep(projectStep);
			save(backlog);
		}
	}

	// ====================== 基本 C R U D 方法 ===========================
	public Backlog get(int id) {
		return backlogDao.get(id);
	}

	public void save(Backlog entity) {
		backlogDao.save(entity);
	}

	public void update(Backlog entity) {
		backlogDao.update(entity);
	}

	public void delete(int id) {
		backlogDao.delete(id);
	}

	public List<Backlog> getAll() {
		return backlogDao.getAll();
	}

	public Page<Backlog> getPage(int pageNo, int pageSize, String no,
			String name, Integer userId, Integer status) {
		StringBuilder hql = new StringBuilder("from Backlog o ");
		List<Object> paramList = new ArrayList<Object>();
		if (status == null) {
			hql.append(" where 1 = 1 ");
		} else {
			hql.append(" where o.status = ? ");
			paramList.add(status);
		}
		if (!StringUtil.isBlank(no)) {
			hql.append("and o.project.no like ? ");
			paramList.add("%" + no + "%");
		}
		if (!StringUtil.isBlank(name)) {
			hql.append("and o.project.name like ? ");
			paramList.add("%" + name + "%");
		}
		if (userId != null) {
			hql.append("and o.toUser.id =?");
			paramList.add(userId);
		}
		hql.append(" order by o.id desc");
		return backlogDao.getPage(pageNo, pageSize, hql.toString(), paramList);
	}

}
