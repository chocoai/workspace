package com.whty.wfd.page.controller;

import com.whty.wfd.page.model.TUser;
import com.whty.wfd.page.model.TUserFavoriteMessageKey;
import com.whty.wfd.page.model.TUserFavoritePost;
import com.whty.wfd.page.model.TUserFavoritePostKey;
import com.whty.wfd.page.service.CollectionService;
import com.whty.wfd.page.vo.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CollectionController {

	@Autowired
	private CollectionService collectionService;

	@RequestMapping("getCollection.html")
	public String getCollection(HttpServletRequest request, ModelMap map) {
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		List<Collection> collections = collectionService.getCollectionPost(tUser.getId());
		map.put("collections", collections);
		return "collection/collection";
	}

	@RequestMapping("addCollection")
	@ResponseBody
	public Integer addCollection(HttpServletRequest request, String postId, boolean type) {
		Integer result = 0;
		try {
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			if (type) {
				result = collectionService.deleteCollectionPost(tUser.getId(), postId);
			} else {
				result = collectionService.addCollectionPost(tUser.getId(), postId);
			}
		} catch (Exception e) {
			result = 2;
			return result;
		}
		return result;
	}

	@RequestMapping("optCollectionMessage")
	@ResponseBody
	public Integer optCollectionMessage(HttpServletRequest request, String messageId, boolean type) {
		Integer result = 0;
		try {
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			if (type) {
				result = collectionService.deleteCollectionMessage(tUser.getId(), messageId);
			} else {
				result = collectionService.addCollectionMessage(tUser.getId(), messageId);
			}
		} catch (Exception e) {
			result = 2;
			return result;
		}
		return result;
	}

	@RequestMapping("addCollectionMessage")
	@ResponseBody
	public Integer addCollectionMessage(HttpServletRequest request, String messageId) {
		Integer result = 0;
		try {
			TUser tUser = (TUser) request.getSession().getAttribute("userObj");
			result = collectionService.addCollectionMessage(tUser.getId(), messageId);
		} catch (Exception e) {
			result = 2;
			return result;
		}
		return result;
	}

	@RequestMapping("deleteCollection")
	@ResponseBody
	public Integer deleteCollection(HttpServletRequest request, String[] platePostIds, Integer[] userIds,
			String[] mPlatePostIds, Integer[] mUserIds) {
		List<TUserFavoritePostKey> keys = new ArrayList<>();
		List<TUserFavoriteMessageKey> mKeys = new ArrayList<>();
		if (platePostIds != null && platePostIds.length > 0) {
			for (int i = 0; i < platePostIds.length; i++) {
				TUserFavoritePostKey tUserFavoritePostKey = new TUserFavoritePost();
				tUserFavoritePostKey.setPlatePostId(platePostIds[i]);
				tUserFavoritePostKey.setUserId(userIds[i]);
				keys.add(tUserFavoritePostKey);
			}
		}
		if (mPlatePostIds != null && mPlatePostIds.length > 0) {
			for (int i = 0; i < mPlatePostIds.length; i++) {
				TUserFavoriteMessageKey tUserFavoriteMessageKey = new TUserFavoriteMessageKey();
				tUserFavoriteMessageKey.setPostMessageId(mPlatePostIds[i]);
				tUserFavoriteMessageKey.setUserId(mUserIds[i]);
				mKeys.add(tUserFavoriteMessageKey);
			}
		}
		collectionService.deleteCollectionList(keys, mKeys);
		TUser tUser = (TUser) request.getSession().getAttribute("userObj");
		List<Collection> collections = collectionService.getCollectionPost(tUser.getId());
		return collections.size();
	}
}