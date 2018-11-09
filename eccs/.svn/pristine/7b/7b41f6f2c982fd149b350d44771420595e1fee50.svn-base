package com.smart.web.action.t_cult;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.smart.model.T_file;
import com.smart.model.T_hrtrain;
import com.smart.model.User;
import com.smart.service.T_fileService;
import com.smart.service.T_hrtrainService;
import com.smart.service.UserService;
import com.smart.util.DateUtils;
import com.smart.util.FileRepository;
import com.smart.util.Page;
import com.smart.web.action.BaseAction;

@ParentPackage("control-user")
public class T_cultAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Autowired
	private T_hrtrainService t_hrtrainService;

	@Autowired
	private T_fileService t_fileService;

	private T_file t_file;

	private T_hrtrain t_hrtrain;

	@Autowired
	private UserService userService;

	private String rtimec;// 开始时间-查询

	private String rtimej;// 结束时间-查询

	private String Stringid;// id-删除

	private String file_id;// 附件id

	private String name;// 选择用户

	private String values;

	private String oldids;// 已选择的用户

	private String oldnames;

	private String start;//

	private String type;//

	private Map<String, String> map;//

	private File file;

	private String fileFileName;

	/**
	 * 列表and条件查询
	 */
	@Action("list")
	public String list() {
		t_hrtrain = t_hrtrain == null ? new T_hrtrain() : t_hrtrain;
		Page<T_hrtrain> pageBean = t_hrtrainService.getPage(pageNo, 10,
				t_hrtrain.getTheme(), t_hrtrain.getTeach(), rtimec, rtimej);
		String[] str = {};
		for (int i = 0; i < pageBean.getList().size(); i++) {
			String train = "";
			if (pageBean.getList().get(i).getTrainee() != null
					&& !"".equals(pageBean.getList().get(i).getTrainee())) {
				str = pageBean.getList().get(i).getTrainee().split(",");
				for (int j = 0; j < str.length; j++) {
					if (train != "" && userService
							.get(Integer.parseInt(str[j])) != null) {
						train = train + "," + userService
								.get(Integer.parseInt(str[j])).getName();
					} else {
						if (userService.get(Integer.parseInt(str[j])) != null) {
							train = userService.get(Integer.parseInt(str[j]))
									.getName();
						}
					}
				}

			}
			pageBean.getList().get(i).setTraineestr(train);
		}

		put("pageBean", pageBean);
		return "list";
	}

	/**
	 * 新建
	 * 
	 * @return
	 */
	@Action("new")
	public String news() {
		if (t_hrtrain != null && t_hrtrain.getId() != null) {
			t_hrtrain = t_hrtrainService.get(t_hrtrain.getId());
			if (t_hrtrain.getTrainee() != null
					&& !"".equals(t_hrtrain.getTrainee())) {
				String[] str = t_hrtrain.getTrainee().split(",");
				String train = "";
				for (int i = 0; i < str.length; i++) {
					if (train != "" && userService
							.get(Integer.parseInt(str[i])) != null) {
						train = train + "," + userService
								.get(Integer.parseInt(str[i])).getName();
					} else {
						if (userService.get(Integer.parseInt(str[i])) != null) {
							train = userService.get(Integer.parseInt(str[i]))
									.getName();
						}
					}
				}
				t_hrtrain.setTraineestr(train);
			}

			put("t_hrtrain", t_hrtrain);
			List<T_file> list = t_fileService.getList(t_hrtrain.getId(), 1);
			put("list", list);

		}
		return "new";
	}

	/**
	 * 保存
	 * 
	 * @return
	 */
	@Action(value = "save", results = {
			@Result(name = "list", location = "list", type = "redirectAction") })
	public String saves() {
		Date date = new Date();
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String rctime = format.format(date);
		// 新增
		if (t_hrtrain != null && t_hrtrain.getId() == null) {
			t_hrtrain.setUser_id(this.getUser());
			t_hrtrain.setRtime(rctime);
			t_hrtrainService.save(t_hrtrain);
		}
		// 编辑
		if (t_hrtrain != null && t_hrtrain.getId() != null) {
			T_hrtrain px = t_hrtrainService.get(t_hrtrain.getId());
			px.setTheme(t_hrtrain.getTheme());
			px.setMode(t_hrtrain.getMode());
			px.setTdate(t_hrtrain.getTdate());
			px.setTplace(t_hrtrain.getTplace());
			px.setContent(t_hrtrain.getContent());
			px.setTeach(t_hrtrain.getTeach());
			px.setTrainee(t_hrtrain.getTrainee());
			px.setRemark(t_hrtrain.getRemark());
			t_hrtrainService.update(px);
		}
		if (file_id != null) {
			String[] fileid = file_id.split(",");
			for (int i = 0; i < fileid.length; i++) {
				T_file px = t_fileService
						.get(Integer.parseInt(fileid[i].trim()));
				px.setType_id(t_hrtrain.getId());
				t_fileService.update(px);
			}
		}
		return "list";
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	@Action("delete")
	public String delete() {
		if (Stringid != null) {
			String[] str = Stringid.split(",");
			for (int i = 0; i < str.length; i++) {
				t_hrtrain = t_hrtrainService.get(Integer.parseInt(str[i]));
				t_hrtrain.setStatus(-1);
				t_hrtrainService.update(t_hrtrain);
			}
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 删除附件
	 * 
	 * @param id
	 * @return
	 */
	@Action("deletefile")
	public String deletefile() {
		if (Stringid != null) {
			String[] str = Stringid.split(",");
			for (int i = 0; i < str.length; i++) {
				t_file = t_fileService.get(Integer.parseInt(str[i].trim()));
				file = new File(t_file.getPath());
				file.delete();
				t_file.setStatus(-1);
				t_fileService.update(t_file);
			}
		}
		write("1"); // ajax请求用write返回数据
		return null;
	}

	/**
	 * 查看
	 * 
	 * @param id
	 * @return
	 */
	@Action("show")
	public String show() {
		if (t_hrtrain != null && t_hrtrain.getId() != null) {
			t_hrtrain = t_hrtrainService.get(t_hrtrain.getId());
			String[] str = {};// 受训人员ID
			String train = "";// 受训人员姓名
			if (t_hrtrain.getTrainee() != null
					&& !"".equals(t_hrtrain.getTrainee())) {
				str = t_hrtrain.getTrainee().split(",");
				for (int j = 0; j < str.length; j++) {
					if (train != "" && userService
							.get(Integer.parseInt(str[j])) != null) {
						train = train + "," + userService
								.get(Integer.parseInt(str[j])).getName();
					} else {
						if (userService.get(Integer.parseInt(str[j])) != null) {
							train = userService.get(Integer.parseInt(str[j]))
									.getName();
						}
					}
				}
				t_hrtrain.setTraineestr(train);
			}
			put("t_hrtrain", t_hrtrain);
			List<T_file> lists = t_fileService.getList(t_hrtrain.getId(), 1);
			put("lists", lists);
		}
		return "show";
	}

	/**
	 * 选择受训人员
	 * 
	 * @param id
	 * @return
	 */
	@Action("selectUser")
	public String selectUser() {
		Page<User> pageBean = userService.getAll1(getPageNo(), 10, name,
				this.getMyDept().getComid());
		put("pageBean", pageBean);
		return "selectUser";
	}

	@SuppressWarnings("unchecked")
	@Action("selectUser1")
	public String selectUser1() {
		if (type.equals("0")) {// 将id保存到session
			if ((Map<String, String>) getSession().get("jld_session") == null) {
				map = new HashMap<String, String>();
			} else {
				map = (Map<String, String>) getSession().get("jld_session");
			}
			if (start != null && values != null) {
				if (start.equals("1") && map.get(values) == null) {
					String vname = userService.get(Integer.parseInt(values))
							.getName();
					map.put(values, vname);
				}
				if (map.get(values) != null && start.equals("0")) {
					map.remove(values);
				}
			}
			if (oldids != null && oldnames != null) {
				String ids[] = oldids.split(",");
				String names[] = oldnames.split(",");
				for (int i = 0; i < ids.length; i++) {
					if (ids[i] != null) {
						// 由于从session中获取的names[i]是乱码，所以此处利用ids[i]来查找其对应的name，而不用session中的names
						names[i] = userService.get(Integer.parseInt(ids[i]))
								.getName();
						map.put(ids[i], names[i]);
					}
				}
			}
			getSession().put("jld_session", map);
			String ids = "";
			String names = "";
			if (map != null) {
				Iterator<Entry<String, String>> it = map.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry<String, String> entry = (Map.Entry<String, String>) it
							.next();
					String value = (String) entry.getKey();
					String name = (String) entry.getValue();
					if (ids.equals("")) {
						ids = value;
						names = name;
					} else {
						ids = ids + "," + value;
						names = names + "," + name;
					}
				}
			}
			write(ids + "==,,,==" + names);
		} else if (type.equals("1")) {// 清除session
			getSession().remove("jld_session");
			write("1");
		}
		return null;
	}

	/**
	 * 文件上传保存
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action("uploadFile")
	public String uploadFile() throws Exception {

		String filedir = "t_cult/" + this.getUser().getId() + "/";
		FileRepository fileRepository = new FileRepository();
		String root = fileRepository.storeByExt(filedir, fileFileName, file);

		T_file fi = new T_file();
		fi.setSize(file.getTotalSpace());
		fi.setName(fileFileName);
		fi.setPath(root);
		fi.setUser(this.getUser());
		fi.setRtime(DateUtils.getCurrentTime());
		fi.setRemarks(t_file.getRemarks());
		fi.setType(1);// 培训管理
		t_fileService.save(fi);
		JSONObject json = new JSONObject();
		json.put("id", fi.getId());
		json.put("name", fi.getName());
		write(json.toString());
		return null;
	}

	/*--------------------get和set----------------------------*/
	public T_hrtrain getT_hrtrain() {
		return t_hrtrain;
	}

	public void setT_hrtrain(T_hrtrain t_hrtrain) {
		this.t_hrtrain = t_hrtrain;
	}

	public String getRtimec() {
		return rtimec;
	}

	public void setRtimec(String rtimec) {
		this.rtimec = rtimec;
	}

	public String getRtimej() {
		return rtimej;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRtimej(String rtimej) {
		this.rtimej = rtimej;
	}

	public String getStringid() {
		return Stringid;
	}

	public void setStringid(String stringid) {
		Stringid = stringid;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public T_file getT_file() {
		return t_file;
	}

	public void setT_file(T_file t_file) {
		this.t_file = t_file;
	}

	public String getFile_id() {
		return file_id;
	}

	public void setFile_id(String file_id) {
		this.file_id = file_id;
	}

	public String getValues() {
		return values;
	}

	public void setValues(String values) {
		this.values = values;
	}

	public String getStart() {
		return start;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Map<String, String> getMap() {
		return map;
	}

	public void setMap(Map<String, String> map) {
		this.map = map;
	}

	public String getOldids() {
		return oldids;
	}

	public void setOldids(String oldids) {
		this.oldids = oldids;
	}

	public String getOldnames() {
		return oldnames;
	}

	public void setOldnames(String oldnames) {
		this.oldnames = oldnames;
	}

}
