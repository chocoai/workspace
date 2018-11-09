package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPostMessageAudio;
import com.whty.wfd.page.model.TPostMessageAudioExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TPostMessageAudioMapper {
	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	long countByExample(TPostMessageAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int deleteByExample(TPostMessageAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int insert(TPostMessageAudio record);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int insertSelective(TPostMessageAudio record);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	List<TPostMessageAudio> selectByExample(TPostMessageAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	TPostMessageAudio selectByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int updateByExampleSelective(@Param("record") TPostMessageAudio record,
			@Param("example") TPostMessageAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int updateByExample(@Param("record") TPostMessageAudio record, @Param("example") TPostMessageAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int updateByPrimaryKeySelective(TPostMessageAudio record);

	/**
	 *
	 * @mbg.generated 2018-09-18
	 */
	int updateByPrimaryKey(TPostMessageAudio record);

	/**
	 * 批量添加语音
	 */
	int insertManyAudio(List<TPostMessageAudio> tPostMessageAudios);
}