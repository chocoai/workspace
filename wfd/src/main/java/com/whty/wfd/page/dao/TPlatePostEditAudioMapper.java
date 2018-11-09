package com.whty.wfd.page.dao;

import com.whty.wfd.page.model.TPlatePostEditAudio;
import com.whty.wfd.page.model.TPlatePostEditAudioExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TPlatePostEditAudioMapper {
	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	long countByExample(TPlatePostEditAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int deleteByExample(TPlatePostEditAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int insert(TPlatePostEditAudio record);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int insertSelective(TPlatePostEditAudio record);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	List<TPlatePostEditAudio> selectByExample(TPlatePostEditAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	TPlatePostEditAudio selectByPrimaryKey(Integer id);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByExampleSelective(@Param("record") TPlatePostEditAudio record,
			@Param("example") TPlatePostEditAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByExample(@Param("record") TPlatePostEditAudio record,
			@Param("example") TPlatePostEditAudioExample example);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByPrimaryKeySelective(TPlatePostEditAudio record);

	/**
	 *
	 * @mbg.generated 2018-09-27
	 */
	int updateByPrimaryKey(TPlatePostEditAudio record);
}